package org.pdfgal.pdfgalweb.controllers;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.pdfgal.pdfgalweb.forms.SplitForm;
import org.pdfgal.pdfgalweb.model.enumerated.SplitMode;
import org.pdfgal.pdfgalweb.services.SplitService;
import org.pdfgal.pdfgalweb.validators.SplitValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/split")
public class SplitController extends BaseController {

	private static final long serialVersionUID = 2062284077983922984L;

	private static final String SPLIT_FORM = "splitForm";

	@Autowired
	private SplitService splitService;

	@Autowired
	private SplitValidator splitValidator;

	@InitBinder
	protected void initBinder(final WebDataBinder binder) {
		binder.setValidator(this.splitValidator);
	}

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
			@ModelAttribute(SPLIT_FORM) @Valid final SplitForm splitForm,
			final BindingResult result, final HttpServletResponse response) {

		if (result.hasErrors()) {
			return this.getInicioPage();
		}

		final MultipartFile file = splitForm.getFile();
		final SplitMode splitMode = splitForm.getSplitMode();
		final String pages = splitForm.getPages();

		try {
			this.splitService.split(file, splitMode, pages, response);
		} catch (final Exception e) {
			result.addError(new ObjectError("pages", "split.validator.error"));
			return this.getInicioPage();
		}

		return null;
	}
}
