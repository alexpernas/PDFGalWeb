package org.pdfgal.pdfgalweb.validators;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.pdfgal.pdfgal.model.PDFGalBookmark;
import org.pdfgal.pdfgalweb.forms.BookmarkForm;
import org.pdfgal.pdfgalweb.model.enumerated.PDFEncryptionType;
import org.pdfgal.pdfgalweb.utils.PDFGalWebUtils;
import org.pdfgal.pdfgalweb.validators.utils.ValidatorUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import org.springframework.web.multipart.MultipartFile;

@Component
public class BookmarkValidator implements Validator {

	@Autowired
	private ValidatorUtils validatorUtils;

	@Autowired
	private PDFGalWebUtils pdfGalWebUtils;

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
		validateBookmarks(file, bookmarkForm.getPagesList(), bookmarkForm.getTextsList(), errors);
	}

	/**
	 * Validates the a {@link PDFGalBookmark} list.
	 * @param file
	 * @param pagesList
	 * @param textsList
	 * @param errors
	 */
	private void validateBookmarks(final MultipartFile file, final List<Integer> pagesList,
			final List<String> textsList, final Errors errors) {

		boolean allEmpty = true;

		if (CollectionUtils.isNotEmpty(pagesList) && CollectionUtils.isNotEmpty(textsList)) {

			if (pagesList.size() != textsList.size()) {
				errors.rejectValue("textsList", "bookmark.validator.bookmarks.incomplete");

			} else {

				final List<Integer> pagesCopy = new ArrayList<Integer>();
				pagesCopy.addAll(pagesList);

				try {
					final Integer totalPages = this.pdfGalWebUtils.getPages(file);

					for (int i = 0; i < pagesList.size(); i++) {

						final Integer page = pagesList.get(i);
						final String text = textsList.get(i);

						if (page != null && StringUtils.isNotBlank(text)) {
							allEmpty = false;

						} else if (page != null || StringUtils.isNotBlank(text)) {
							allEmpty = false;
							errors.rejectValue("textsList",
									"bookmark.validator.bookmarks.incomplete");
							break;
						}

						if ((page != null) && (totalPages != null) && (page < 1)
								&& (page > totalPages)) {
							errors.rejectValue("textsList",
									"bookmark.validator.bookmarks.inexisting");
							break;
						}

						// It is tested that page is not repeated
						pagesCopy.remove(0);
						if (page != null && pagesCopy.contains(page)) {
							errors.rejectValue("textsList", "bookmark.validator.bookmarks.repeated");
							break;
						}
					}
				} catch (final IOException e) {
					if (!errors.hasErrors()) {
						errors.rejectValue("textsList", "bookmark.validator.error");
					}
				}
			}
		}

		if (allEmpty) {
			errors.rejectValue("textsList", "common.validator.required");
		}
	}
}
