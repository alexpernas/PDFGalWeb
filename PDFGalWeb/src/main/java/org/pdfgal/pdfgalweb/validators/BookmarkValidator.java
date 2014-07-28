package org.pdfgal.pdfgalweb.validators;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.pdfgal.pdfgal.model.PDFGalBookmark;
import org.pdfgal.pdfgalweb.forms.BookmarkForm;
import org.pdfgal.pdfgalweb.model.enumerated.PDFEncryptionType;
import org.pdfgal.pdfgalweb.validators.utils.ValidatorUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import org.springframework.web.multipart.MultipartFile;

public class BookmarkValidator implements Validator {

	@Autowired
	private ValidatorUtils validatorUtils;

	@Override
	public boolean supports(final Class<?> clazz) {
		return BookmarkForm.class.equals(clazz);
	}

	@Override
	public void validate(final Object target, final Errors errors) {

		final BookmarkForm bookmarkForm = (BookmarkForm) target;

		// File validation
		final MultipartFile file = bookmarkForm.getFile();
		this.validatorUtils.validateFile(file, errors, PDFEncryptionType.NON_ENCRYPTED);

		// Title validation
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "title", "common.validator.required");

		// Bookmarks validation
		validateBookmarks(bookmarkForm.getBookmarks(), errors);
	}

	/**
	 * Validates the a {@link PDFGalBookmark} list.
	 * @param bookmarksList
	 * @param errors
	 */
	private void validateBookmarks(final List<PDFGalBookmark> bookmarksList, final Errors errors) {

		boolean allEmpty = true;

		if (CollectionUtils.isNotEmpty(bookmarksList)) {

			for (final PDFGalBookmark bookmark : bookmarksList) {

				if (bookmark != null) {

					if (bookmark.getPage() != null && StringUtils.isNotBlank(bookmark.getText())) {
						allEmpty = false;

					} else if (bookmark.getPage() != null
							|| StringUtils.isNotBlank(bookmark.getText())) {
						allEmpty = false;
						errors.rejectValue("bookmarks", "bookmark.validator.bookmarks.incomplete");
						break;
					}
				}
			}
		}

		if (allEmpty) {
			errors.rejectValue("bookmarks", "common.validator.required");
		}
	}

}
