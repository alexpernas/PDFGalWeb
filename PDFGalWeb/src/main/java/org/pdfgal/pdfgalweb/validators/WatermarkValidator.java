package org.pdfgal.pdfgalweb.validators;

import org.apache.commons.lang3.StringUtils;
import org.pdfgal.pdfgal.model.enumerated.WatermarkPosition;
import org.pdfgal.pdfgal.validator.PDFGalValidator;
import org.pdfgal.pdfgalweb.forms.WatermarkForm;
import org.pdfgal.pdfgalweb.model.enumerated.PDFEncryptionType;
import org.pdfgal.pdfgalweb.utils.FileUtils;
import org.pdfgal.pdfgalweb.validators.utils.ValidatorUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import org.springframework.web.multipart.MultipartFile;

@Component
public class WatermarkValidator implements Validator {

	@Autowired
	private FileUtils fileUtils;

	@Autowired
	private PDFGalValidator pdfGalValidator;

	@Autowired
	private ValidatorUtils validatorUtils;

	@Override
	public boolean supports(final Class<?> clazz) {
		return WatermarkForm.class.equals(clazz);
	}

	@Override
	public void validate(final Object target, final Errors errors) {

		final WatermarkForm watermarkForm = (WatermarkForm) target;

		// File validation
		final MultipartFile file = watermarkForm.getFile();
		this.validatorUtils.validateFile(file, errors,
				PDFEncryptionType.NON_ENCRYPTED);

		// Text validation
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "text",
				"common.validator.required");
		this.validateTextLenght(watermarkForm.getText(),
				watermarkForm.getWatermarkPosition(), file, errors);

		// Pages validation
	}

	/**
	 * Valdiation of text length.
	 * 
	 * @param text
	 * @param watermarkPosition
	 * @param file
	 * @param errors
	 */
	private void validateTextLenght(final String text,
			final WatermarkPosition watermarkPosition,
			final MultipartFile file, final Errors errors) {

		if (StringUtils.isNotEmpty(text) && watermarkPosition != null
				&& file != null && errors != null) {

			final Integer textLenght = text.length();
			boolean excesiveLength = true;

			if (WatermarkPosition.CENTER.equals(watermarkPosition)) {

				if (textLenght <= 12) {
					excesiveLength = false;

				} else {
					final String uri = this.fileUtils.saveFile(file);

					if (textLenght <= 18
							&& this.pdfGalValidator.allLandscape(uri)) {
						excesiveLength = false;
					}
					this.fileUtils.delete(uri);
				}

			} else if (textLenght <= 21) {
				excesiveLength = false;
			}

			if (excesiveLength) {
				errors.rejectValue("text", "watermark.validator.passwords");
			}
		}
	}
}
