package org.pdfgal.pdfgalweb.validators;

import java.io.IOException;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.pdfgal.pdfgalweb.forms.ReindexForm;
import org.pdfgal.pdfgalweb.model.enumerated.PDFEncryptionType;
import org.pdfgal.pdfgalweb.utils.PDFGalWebUtils;
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

	@Autowired
	private PDFGalWebUtils pdfGalWebUtils;

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

		// Pages validation
		this.validatePages(file, reindexForm.getPagesList(), errors);
	}

	/**
	 * This method validates the selected pages for reindexation.
	 * 
	 * @param file
	 * @param pagesList
	 * @param errors
	 */
	private void validatePages(final MultipartFile file, final List<Integer> pagesList,
			final Errors errors) {

		if (CollectionUtils.isNotEmpty(pagesList)) {
			try {
				final Integer totalPages = this.pdfGalWebUtils.getPages(file);
				Integer previous = null;
				for (final Integer page : pagesList) {
					if (page != null
							&& ((page < 1) || (page > totalPages) || (previous != null && previous >= page))) {
						errors.rejectValue("numberingStylesList", "reindex.validator.page");
					}
					previous = page;
				}
			} catch (final IOException e) {
				if (!errors.hasErrors()) {
					errors.rejectValue("numberingStylesList", "reindex.validator.error");
				}
			}
		} else {
			errors.rejectValue("numberingStylesList", "common.validator.required");
		}
	}

}
