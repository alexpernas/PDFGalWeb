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

import org.apache.commons.lang3.StringUtils;
import org.pdfgal.pdfgalweb.forms.ProtectForm;
import org.pdfgal.pdfgalweb.model.enumerated.PDFEncryptionType;
import org.pdfgal.pdfgalweb.validators.utils.ValidatorUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import org.springframework.web.multipart.MultipartFile;

@Component
public class ProtectValidator implements Validator {

	@Autowired
	private ValidatorUtils validatorUtils;

	@Override
	public boolean supports(final Class<?> clazz) {
		return ProtectForm.class.equals(clazz);
	}

	@Override
	public void validate(final Object target, final Errors errors) {

		final ProtectForm protectForm = (ProtectForm) target;

		// File validation
		final MultipartFile file = protectForm.getFile();
		this.validatorUtils.validateFile(file, errors, PDFEncryptionType.NON_ENCRYPTED);

		// Password validation
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "common.validator.required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "repeatedPassword",
				"common.validator.required");

		final String password = protectForm.getPassword();
		final String repeatedPassword = protectForm.getRepeatedPassword();
		if (StringUtils.isNotBlank(password) && StringUtils.isNotBlank(repeatedPassword)
				&& !password.equals(repeatedPassword)) {
			errors.rejectValue("repeatedPassword", "protect.validator.passwords");
		}
	}
}
