package org.pdfgal.pdfgalweb.validators;

import org.pdfgal.pdfgalweb.forms.MergeForm;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class MergeValidator implements Validator {

	@Override
	public boolean supports(final Class<?> clazz) {
		return MergeForm.class.equals(clazz);
	}

	@Override
	public void validate(final Object target, final Errors errors) {

		final MergeForm mergeForm = (MergeForm) target;

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "fileName",
				"common.subtitle");

	}
}
