package org.pdfgal.pdfgalweb.controllers;

import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.pdfgal.pdfgal.model.PDFGalBookmark;
import org.pdfgal.pdfgalweb.forms.BookmarkForm;
import org.pdfgal.pdfgalweb.forms.DownloadForm;
import org.pdfgal.pdfgalweb.services.BookmarkService;
import org.pdfgal.pdfgalweb.utils.Constants;
import org.pdfgal.pdfgalweb.utils.PDFGalWebUtils;
import org.pdfgal.pdfgalweb.validators.BookmarkValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/bookmark")
public class BookmarkController extends BaseController {

	private static final long serialVersionUID = 3847291724996674100L;

	private static final String BOOKMARK_FORM = "bookmarkForm";

	@Autowired
	private BookmarkService bookmarkService;

	@Autowired
	private BookmarkValidator bookmarkValidator;

	@Autowired
	private PDFGalWebUtils pdfGalWebUtils;

	@InitBinder(BOOKMARK_FORM)
	protected void initBinder(final WebDataBinder binder) {
		binder.setValidator(this.bookmarkValidator);
	}

	/**
	 * Start Page for bookmarking.
	 * 
	 * @return ModelAndView
	 */
	@RequestMapping(method = RequestMethod.GET)
	public final ModelAndView getInicioPage() {
		return this.getModelAndView();
	}

	@RequestMapping(method = RequestMethod.POST)
	public final ModelAndView protect(
			@ModelAttribute(BOOKMARK_FORM) @Valid final BookmarkForm bookmarkForm,
			final BindingResult result, final HttpServletResponse response) {

		if (result.hasErrors()) {
			return new ModelAndView("bookmark");
		}

		final MultipartFile file = bookmarkForm.getFile();
		final String title = bookmarkForm.getTitle();
		final List<PDFGalBookmark> pdfGalBookmarksList = bookmarkForm.getBookmarks();

		DownloadForm downloadForm = new DownloadForm();

		try {
			downloadForm = this.bookmarkService.addBookmarks(file, title, pdfGalBookmarksList,
					response);
		} catch (final Exception e) {
			// Default error is added
			result.addError(this.pdfGalWebUtils.createDefaultFieldError(BOOKMARK_FORM, "bookmarks",
					pdfGalBookmarksList, "protect.validator.error"));
			return new ModelAndView("bookmark");
		}

		final ModelAndView mav = this.getModelAndView();
		mav.addObject(Constants.DOWNLOAD_FORM, downloadForm);

		return mav;
	}

	/**
	 * Returns the {@link ModelAndView} for bookmarking.
	 * 
	 * @return
	 */
	private ModelAndView getModelAndView() {

		final ModelAndView mav = new ModelAndView("bookmark");
		mav.addObject(BOOKMARK_FORM, new BookmarkForm());
		return mav;
	}

}
