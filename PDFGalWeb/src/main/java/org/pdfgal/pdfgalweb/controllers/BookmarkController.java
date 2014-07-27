package org.pdfgal.pdfgalweb.controllers;

import org.pdfgal.pdfgalweb.forms.BookmarkForm;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/bookmark")
public class BookmarkController extends BaseController {

	private static final long serialVersionUID = 3847291724996674100L;

	private static final String BOOKMARK_FORM = "bookmarkForm";

	/**
	 * Start Page for bookmarking.
	 * 
	 * @return ModelAndView
	 */
	@RequestMapping(method = RequestMethod.GET)
	public final ModelAndView getInicioPage() {
		return this.getModelAndView();
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
