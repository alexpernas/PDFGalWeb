package org.pdfgal.pdfgalweb.validators;

import org.pdfgal.pdfgalweb.forms.ReindexForm;
import org.pdfgal.pdfgalweb.model.enumerated.PDFEncryptionType;
import org.pdfgal.pdfgalweb.validators.utils.ValidatorUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.springframework.web.multipart.MultipartFile;

@Component
public class ReindexValidator implements Validator {

	@Autowired
	private ValidatorUtils validatorUtils;

	@Override
	public boolean supports(final Class<?> clazz) {
		return ReindexForm.class.equals(clazz);
	}

	@Override
	public void validate(final Object target, final Errors errors) {

		final ReindexForm reindexForm = (ReindexForm) target;

		// File validation
		final MultipartFile file = reindexForm.getFile();
		this.validatorUtils.validateFile(file, errors, PDFEncryptionType.NON_ENCRYPTED);

		// TODO Continuar
	}

}
