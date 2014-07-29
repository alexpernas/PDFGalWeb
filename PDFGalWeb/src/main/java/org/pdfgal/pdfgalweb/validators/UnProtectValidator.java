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

import org.pdfgal.pdfgalweb.forms.UnProtectForm;
import org.pdfgal.pdfgalweb.model.enumerated.PDFEncryptionType;
import org.pdfgal.pdfgalweb.validators.utils.ValidatorUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import org.springframework.web.multipart.MultipartFile;

@Component
public class UnProtectValidator implements Validator {

	@Autowired
	ValidatorUtils validatorUtils;

	@Override
	public boolean supports(final Class<?> clazz) {
		return UnProtectForm.class.equals(clazz);
	}

	@Override
	public void validate(final Object target, final Errors errors) {

		final UnProtectForm unProtectForm = (UnProtectForm) target;

		// File validation
		final MultipartFile file = unProtectForm.getFile();
		this.validatorUtils.validateFile(file, errors, PDFEncryptionType.ENCRYPTED);

		// Password validation
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "common.validator.required");
	}

}
