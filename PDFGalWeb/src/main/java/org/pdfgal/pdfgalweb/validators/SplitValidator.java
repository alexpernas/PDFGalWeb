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

import org.pdfgal.pdfgalweb.forms.SplitForm;
import org.pdfgal.pdfgalweb.model.enumerated.PDFEncryptionType;
import org.pdfgal.pdfgalweb.model.enumerated.SplitMode;
import org.pdfgal.pdfgalweb.utils.FileUtils;
import org.pdfgal.pdfgalweb.validators.utils.ValidatorUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.springframework.web.multipart.MultipartFile;

@Component
public class SplitValidator implements Validator {

	@Autowired
	private FileUtils fileUtils;

	@Autowired
	private ValidatorUtils validatorUtils;

	@Override
	public boolean supports(final Class<?> clazz) {
		return SplitForm.class.equals(clazz);
	}

	@Override
	public void validate(final Object target, final Errors errors) {

		final SplitForm splitForm = (SplitForm) target;

		// File validation
		final MultipartFile file = splitForm.getFile();
		this.validatorUtils.validateFile(file, errors, PDFEncryptionType.NON_ENCRYPTED);

		// SplitMode and pages validation
		final SplitMode splitMode = splitForm.getSplitMode();
		final String pages = splitForm.getPages();
		if (SplitMode.NUMBER_OF_PAGES.equals(splitMode)) {
			// Pages validation when SplitMode refers to the number of pages for
			// each new document
			if (!this.validateNumberOfPages(pages)) {
				errors.rejectValue("pages", "split.validator.pages.number");
			}
		} else if (SplitMode.CONCRETE_PAGES_TO_SPLIT.equals(splitMode)) {
			// Pages validation when SplitMode refers to the concrete pages to
			// split the document
			if (!this.validatorUtils.validateConcretePages(pages, file, ",", null, true)) {
				errors.rejectValue("pages", "split.validator.pages.concrete");
			}
		}
	}

	/**
	 * Validates if argument is an {@link Integer} greater than 0.
	 * 
	 * @param pages
	 * @return
	 */
	private boolean validateNumberOfPages(final String pages) {

		boolean result = true;

		try {
			final Integer pagesValue = Integer.valueOf(pages);
			if (pagesValue.compareTo(new Integer(1)) < 0) {
				result = false;
			}
		} catch (final NumberFormatException e) {
			result = false;
		}

		return result;
	}
}
