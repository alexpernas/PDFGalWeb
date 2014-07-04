package org.pdfgal.pdfgalweb.validators;

import org.pdfgal.pdfgalweb.forms.ProtectForm;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class ProtectValidator implements Validator {

	@Override
	public boolean supports(final Class<?> clazz) {
		return ProtectForm.class.equals(clazz);
	}

	@Override
	public void validate(final Object target, final Errors errors) {
		// TODO Auto-generated method stub

	}

}
