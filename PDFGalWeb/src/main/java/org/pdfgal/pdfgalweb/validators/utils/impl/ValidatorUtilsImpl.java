/*
 * PDFGalWeb
 * Copyright (c) 2014, Alejandro Pernas Pan, All rights reserved.
 * 
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 3.0 of the License, or (at your option) any later version.

 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 * 
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library.
 */

package org.pdfgal.pdfgalweb.validators.utils.impl;

import java.io.IOException;
import java.util.StringTokenizer;

import org.apache.commons.lang3.StringUtils;
import org.pdfgal.pdfgal.utils.PDFUtils;
import org.pdfgal.pdfgal.validator.PDFGalValidator;
import org.pdfgal.pdfgalweb.model.enumerated.PDFEncryptionType;
import org.pdfgal.pdfgalweb.utils.FileUtils;
import org.pdfgal.pdfgalweb.utils.PDFGalWebUtils;
import org.pdfgal.pdfgalweb.validators.utils.ValidatorUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.web.multipart.MultipartFile;

@Component
public class ValidatorUtilsImpl implements ValidatorUtils {

	private final String FILE_NAME_PATTERN = "([\\w\\s-.])*";

	@Autowired
	private FileUtils fileUtils;

	@Autowired
	private PDFGalValidator pdfGalValidator;

	@Autowired
	private PDFGalWebUtils pdfGalWebUtils;

	@Autowired
	private PDFUtils pdfUtils;

	@Override
	public boolean validateFileName(final String fileName) {

		boolean result = false;

		if (StringUtils.isNotBlank(fileName)
				&& fileName.matches(this.FILE_NAME_PATTERN)) {
			result = true;
		}

		return result;
	}

	@Override
	public PDFEncryptionType validatePDF(final MultipartFile file) {

		final String uri = this.fileUtils.saveFile(file);

		final PDFEncryptionType result = this.validatePDF(uri);

		this.fileUtils.delete(uri);

		return result;
	}

	@Override
	public PDFEncryptionType validatePDF(final String uri) {

		PDFEncryptionType result = PDFEncryptionType.NON_PDF;

		if (this.pdfGalValidator.isPDF(uri)) {
			if (this.pdfGalValidator.isEncrypted(uri)) {
				result = PDFEncryptionType.ENCRYPTED;
			} else {
				result = PDFEncryptionType.NON_ENCRYPTED;
			}
		}

		return result;
	}

	@Override
	public void validateFile(final MultipartFile file, final Errors errors,
			final PDFEncryptionType pdfEncryptionType) {

		if (file == null || file.getSize() == 0) {
			errors.rejectValue("file", "common.validator.file.required");

		} else {
			final PDFEncryptionType validation = this.validatePDF(file);
			if (PDFEncryptionType.NON_PDF.equals(validation)) {
				errors.rejectValue("file",
						"common.validator.file.incorrect.pdf");

			} else if (!validation.equals(pdfEncryptionType)) {

				if (PDFEncryptionType.NON_ENCRYPTED.equals(pdfEncryptionType)) {
					errors.rejectValue("file",
							"common.validator.file.incorrect.encrypted.true");
				} else if (PDFEncryptionType.ENCRYPTED
						.equals(pdfEncryptionType)) {
					errors.rejectValue("file",
							"common.validator.file.incorrect.encrypted.false");
				}
			}
		}
	}

	@Override
	public boolean validateConcretePages(final String pages,
			final MultipartFile file, final String delim1, final String delim2,
			final boolean testMoreThanOne, final boolean notBlankPages) {

		boolean result = true;

		if (StringUtils.isBlank(pages) && notBlankPages) {
			result = false;
		} else {
			if (StringUtils.isNotBlank(pages)) {
				try {
					final Integer filePages = this.pdfGalWebUtils
							.getPages(file);
					result = this.validateConcretePagesLoop(filePages, pages,
							delim1, delim2, testMoreThanOne);
				} catch (final NumberFormatException e) {
					// Any of the tokens is not an Integer
					result = false;
				} catch (final IOException e) {
					// Problem with the PDF file, we show here no message,
					// message
					// will be shown below upload button
					result = true;
				}
			}
		}

		return result;
	}

	/**
	 * This method represents the loop for the validateConcretePages method.
	 * 
	 * @param totalPages
	 * @param pages
	 * @param delim1
	 * @param delim2
	 * @param moreThanOne
	 * @return
	 */
	private boolean validateConcretePagesLoop(final Integer totalPages,
			final String pages, final String delim1, final String delim2,
			final boolean testMoreThanOne) {

		boolean result = false;

		if (totalPages != null && StringUtils.isNotEmpty(pages)
				&& StringUtils.isNotEmpty(delim1)) {

			result = true;

			final StringTokenizer st = new StringTokenizer(pages, delim1);
			Integer previous = null;
			String token = null;
			if ("-".equals(delim1) && st.countTokens() != 2) {
				result = false;
			} else {
				while (st.hasMoreElements()) {
					try {
						token = st.nextToken();
						final Integer current = Integer.valueOf(token);
						boolean isMoreThan = true;
						if (testMoreThanOne) {
							isMoreThan = (current.compareTo(new Integer(2)) < 0);
						} else {
							isMoreThan = (current.compareTo(new Integer(1)) < 0);
						}
						if ((current.compareTo(totalPages) > 0)
								|| isMoreThan
								|| (previous != null && current
										.compareTo(previous) <= 0)) {
							result = false;
							break;
						}
						previous = current;

					} catch (final Exception e) {
						if (StringUtils.isNotEmpty(delim2)) {
							result = this.validateConcretePagesLoop(totalPages,
									token, delim2, null, testMoreThanOne);

						} else {
							result = false;
						}

						if (!result) {
							break;
						}
					}
				}
			}
		}

		return result;
	}
}
