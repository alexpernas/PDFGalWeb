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
		this.validatorUtils.validateFile(file, errors,
				PDFEncryptionType.NON_ENCRYPTED);

		// Password validation
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password",
				"common.validator.required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "repeatedPassword",
				"common.validator.required");

		final String password = protectForm.getPassword();
		final String repeatedPassword = protectForm.getRepeatedPassword();
		if (StringUtils.isNotBlank(password)
				&& StringUtils.isNotBlank(repeatedPassword)
				&& !password.equals(repeatedPassword)) {
			errors.rejectValue("repeatedPassword",
					"protect.validator.passwords");
		}
	}
}
