package org.pdfgal.pdfgalweb.controllers;

import javax.servlet.http.HttpServletResponse;

import org.pdfgal.pdfgalweb.forms.SplitForm;
import org.pdfgal.pdfgalweb.model.enumerated.SplitMode;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/split")
public class SplitController extends BaseController {

	private static final long serialVersionUID = 2062284077983922984L;

	private static final String SPLIT_FORM = "splitForm";

	/**
	 * Start Page for splitting.
	 * 
	 * @return ModelAndView
	 */
	@RequestMapping(method = RequestMethod.GET)
	public final ModelAndView getInicioPage() {
		final ModelAndView mav = new ModelAndView("split");
		mav.addObject(SPLIT_FORM, new SplitForm());
		mav.addObject("splitModes", SplitMode.values());
		return mav;
	}

	@RequestMapping(method = RequestMethod.POST)
	public final ModelAndView protect(
			@ModelAttribute(SPLIT_FORM) final SplitForm splitForm,
			final HttpServletResponse response) {
		return null;
	}

}
