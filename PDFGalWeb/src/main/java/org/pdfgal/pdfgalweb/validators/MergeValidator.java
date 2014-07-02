package org.pdfgal.pdfgalweb.validators;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.pdfgal.pdfgalweb.forms.MergeForm;
import org.pdfgal.pdfgalweb.model.enumerated.PDFEncryptionType;
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

		if (this.isEmpty(files)) {
			errors.rejectValue("files", "merge.validator.files.required");

		} else if (files.size() == 1) {
			final MultipartFile file = files.get(0);
			if (!"application/zip".equals(file.getContentType())) {
				errors.rejectValue("files",
						"merge.validator.files.incorrect.zip");

			} else {
				// TODO
			}

		} else {
			final PDFEncryptionType validation = this.validatePDF(files);
			if (PDFEncryptionType.NON_PDF.equals(validation)) {
				errors.rejectValue("files",
						"merge.validator.files.incorrect.pdf");
			} else if (PDFEncryptionType.ENCRYPTED.equals(validation)) {
				errors.rejectValue("files",
						"merge.validator.files.incorrect.encrypted");
			}
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
				final PDFEncryptionType validation = this.validatorUtils
						.validatePDF(file);
				if (!PDFEncryptionType.NON_ENCRYPTED.equals(validation)) {
					result = validation;
					break;
				}
			}
		}

		return result;
	}
}
