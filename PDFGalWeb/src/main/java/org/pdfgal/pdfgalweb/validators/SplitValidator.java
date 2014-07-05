package org.pdfgal.pdfgalweb.validators;

import java.io.IOException;
import java.util.StringTokenizer;

import org.apache.commons.lang3.StringUtils;
import org.pdfgal.pdfgal.utils.PDFUtils;
import org.pdfgal.pdfgalweb.forms.SplitForm;
import org.pdfgal.pdfgalweb.model.enumerated.PDFEncryptionType;
import org.pdfgal.pdfgalweb.model.enumerated.SplitMode;
import org.pdfgal.pdfgalweb.utils.FileUtils;
import org.pdfgal.pdfgalweb.validators.utils.ValidatorUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.springframework.web.multipart.MultipartFile;

@Component
public class SplitValidator implements Validator {

	@Autowired
	private FileUtils fileUtils;

	@Autowired
	private PDFUtils pdfUtils;

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
		this.validatorUtils.validateFile(file, errors,
				PDFEncryptionType.NON_ENCRYPTED);

		// SplitMode and pages validation
		final SplitMode splitMode = splitForm.getSplitMode();
		final String pages = splitForm.getPages();
		if (SplitMode.NUMBER_OF_PAGES.equals(splitMode)) {
			// Pages validation when SplitMode refers to the number of pages for
			// each new document
			if (!this.validateNumberOfPages(pages)) {
				errors.rejectValue("pages", "split.validator.pages.number");
			}
		} else if (SplitMode.CONCRETE_PAGES_TO_SPLIT.equals(splitMode)) {
			// Pages validation when SplitMode refers to the concrete pages to
			// split the document
			if (!this.validateConcretePages(pages, file)) {
				errors.rejectValue("pages", "split.validator.pages.concrete");
			}
		}
	}

	/**
	 * Validates if argument is an {@link Integer} greater than 0.
	 * 
	 * @param pages
	 * @return
	 */
	private boolean validateNumberOfPages(final String pages) {

		boolean result = true;

		try {
			final Integer pagesValue = Integer.valueOf(pages);
			if (pagesValue.compareTo(new Integer(1)) < 0) {
				result = false;
			}
		} catch (final NumberFormatException e) {
			result = false;
		}

		return result;
	}

	/**
	 * Validates if argument is a string of incremented {@link Integer}
	 * separated by commas.
	 * 
	 * @param pages
	 * @param file
	 * @return
	 */
	private boolean validateConcretePages(final String pages,
			final MultipartFile file) {

		boolean result = true;

		if (StringUtils.isBlank(pages)) {
			result = false;
		} else {
			final StringTokenizer st = new StringTokenizer(pages, ",");
			Integer previous = null;
			try {
				final Integer filePages = this.getPages(file);
				while (st.hasMoreElements()) {
					final Integer current = Integer.valueOf(st.nextToken());
					if ((current.compareTo(filePages) > 0)
							|| (current.compareTo(new Integer(2)) < 0)
							|| (previous != null && current.compareTo(previous) <= 0)) {
						result = false;
						break;
					}
					previous = current;
				}
			} catch (final NumberFormatException e) {
				// Any of the tokens is not an Integer
				result = false;
			} catch (final IOException e) {
				// Problem with the PDF file, we show here no message, message
				// will be shown below upload button
				result = true;
			}
		}

		return result;
	}

	/**
	 * This method returns the number of pages of the document, or throws an
	 * {@link IOException} in case document can't be loaded or it is no PDF.
	 * 
	 * @param file
	 * @return
	 * @throws IOException
	 */
	private Integer getPages(final MultipartFile file) throws IOException {
		final String uri = this.fileUtils.saveFile(file);
		final Integer result = this.pdfUtils.getPages(uri);
		this.fileUtils.delete(uri);
		return result;
	}
}
