package org.pdfgal.pdfgalweb.validators;

import org.pdfgal.pdfgalweb.forms.SplitForm;
import org.pdfgal.pdfgalweb.model.enumerated.PDFEncryptionType;
import org.pdfgal.pdfgalweb.validators.utils.ValidatorUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.springframework.web.multipart.MultipartFile;

@Component
public class SplitValidator implements Validator {

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
		if (file == null || file.getSize() == 0) {
			errors.reject("file", "split.validator.file.required");
		} else {
			final PDFEncryptionType validation = this.validatorUtils
					.validatePDF(file);
			if (PDFEncryptionType.NON_PDF.equals(validation)) {
				errors.rejectValue("files",
						"split.validator.file.incorrect.pdf");
			} else if (PDFEncryptionType.ENCRYPTED.equals(validation)) {
				errors.rejectValue("files",
						"split.validator.file.incorrect.encrypted");
			}
		}

	}
}
