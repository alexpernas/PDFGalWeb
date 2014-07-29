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

package org.pdfgal.pdfgalweb.validators;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.pdfgal.pdfgal.validator.PDFGalValidator;
import org.pdfgal.pdfgalweb.forms.MergeForm;
import org.pdfgal.pdfgalweb.model.enumerated.PDFEncryptionType;
import org.pdfgal.pdfgalweb.utils.FileUtils;
import org.pdfgal.pdfgalweb.utils.ZipUtils;
import org.pdfgal.pdfgalweb.validators.utils.ValidatorUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import org.springframework.web.multipart.MultipartFile;

@Component
public class MergeValidator implements Validator {

	@Autowired
	private FileUtils fileUtils;

	@Autowired
	private PDFGalValidator pdfGalValidator;

	@Autowired
	private ValidatorUtils validatorUtils;

	@Autowired
	private ZipUtils zipUtils;

	@Override
	public boolean supports(final Class<?> clazz) {
		return MergeForm.class.equals(clazz);
	}

	@Override
	public void validate(final Object target, final Errors errors) {

		final MergeForm mergeForm = (MergeForm) target;

		// File name validator
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "fileName",
				"merge.validator.file.name.required");
		if (!this.validatorUtils.validateFileName(mergeForm.getFileName())) {
			errors.rejectValue("fileName", "merge.validator.file.name.invalid");
		}

		// Uploaded files validator
		final List<MultipartFile> files = mergeForm.getFiles();

		if (this.isEmpty(files)) {
			// There are no files uploaded
			errors.rejectValue("files", "merge.validator.files.required");

		} else if (files.size() == 1) {
			// There is only one file uploaded
			this.validateZipFile(files.get(0), errors);

		} else {
			// There are more than one file uploaded
			final PDFEncryptionType validation = this.validatePDF(files);
			if (PDFEncryptionType.NON_PDF.equals(validation)) {
				errors.rejectValue("files", "merge.validator.files.incorrect.pdf");
			} else if (PDFEncryptionType.ENCRYPTED.equals(validation)) {
				errors.rejectValue("files", "merge.validator.files.incorrect.encrypted");
			}
		}
	}

	/**
	 * Makes the validation for a single ZIP file
	 * 
	 * @param file
	 * @param errors
	 */
	private void validateZipFile(final MultipartFile file, final Errors errors) {

		try {
			InputStream fileInputStream = file.getInputStream();

			if (!this.zipUtils.isZip(fileInputStream)) {
				// In case the file is not ZIP we show error, file must be a
				// ZIP
				errors.rejectValue("files", "merge.validator.files.incorrect.zip");

			} else {
				// If file is ZIP, every file inside must be PDF
				List<String> urisList = new ArrayList<String>();

				// InputStream is closed previously so we must get it again
				fileInputStream = file.getInputStream();
				urisList = this.zipUtils.saveFilesFromZip(fileInputStream);

				for (final String uri : urisList) {
					if (!this.pdfGalValidator.isPDF(uri)) {
						errors.rejectValue("files", "merge.validator.files.incorrect.zip.pdf");
						break;
					}
					if (this.pdfGalValidator.isEncrypted(uri)) {
						errors.rejectValue("files", "merge.validator.files.incorrect.zip.encrypted");
						break;
					}
				}

				this.fileUtils.delete(urisList);
			}

			fileInputStream.close();

		} catch (final IOException e) {
			errors.rejectValue("files", "merge.validator.files.incorrect.zip");
		}
	}

	/**
	 * This method returns true when every file on the list is empty, or the
	 * list is empty.
	 * 
	 * @param files
	 * @return
	 */
	private boolean isEmpty(final List<MultipartFile> files) {

		boolean result = true;

		if (CollectionUtils.isNotEmpty(files)) {
			for (final MultipartFile file : files) {
				if (file.getSize() != 0) {
					result = false;
					break;
				}
			}
		}

		return result;
	}

	/**
	 * This method validates a files list, it returns a
	 * {@link PDFEncryptionType} indicating if one the files is not a PDF, if it
	 * is an encrypted PDF or if each one of the are not encrypted PDFs.
	 * 
	 * @param files
	 * @return
	 */
	private PDFEncryptionType validatePDF(final List<MultipartFile> files) {

		PDFEncryptionType result = PDFEncryptionType.NON_ENCRYPTED;

		for (final MultipartFile file : files) {
			if (file.getSize() > 0) {
				final PDFEncryptionType validation = this.validatorUtils.validatePDF(file);
				if (!PDFEncryptionType.NON_ENCRYPTED.equals(validation)) {
					result = validation;
					break;
				}
			}
		}

		return result;
	}
}
