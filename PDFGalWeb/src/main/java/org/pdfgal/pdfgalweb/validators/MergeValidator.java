package org.pdfgal.pdfgalweb.validators;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.pdfgal.pdfgalweb.forms.MergeForm;
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
	private ValidatorUtils validatorUtils;

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

		final List<MultipartFile> files = mergeForm.getFiles();
		if (CollectionUtils.isEmpty(files)) {
			errors.rejectValue("files", "merge.validator.files.required");

		} else if (files.size() == 1) {
			final MultipartFile multipartFile = files.get(0);
			if (multipartFile.getSize() == 0) {
				errors.rejectValue("files", "merge.validator.files.required");
			} else {

			}

		} else {

		}

	}
	// /**
	// * Validates if the files are PDFs.
	// *
	// * @param files
	// * @return
	// */
	// private boolean validateFiles(final List<MultipartFile> files) {
	//
	// }
}
