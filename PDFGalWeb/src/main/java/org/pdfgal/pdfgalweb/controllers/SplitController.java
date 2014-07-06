package org.pdfgal.pdfgalweb.controllers;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.pdfgal.pdfgalweb.forms.DownloadForm;
import org.pdfgal.pdfgalweb.forms.SplitForm;
import org.pdfgal.pdfgalweb.model.enumerated.SplitMode;
import org.pdfgal.pdfgalweb.services.SplitService;
import org.pdfgal.pdfgalweb.utils.PDFGalWebUtils;
import org.pdfgal.pdfgalweb.validators.SplitValidator;
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
@RequestMapping("/split")
public class SplitController extends BaseController {

	private static final long serialVersionUID = 2062284077983922984L;

	private static final String SPLIT_FORM = "splitForm";

	@Autowired
	private SplitService splitService;

	@Autowired
	private SplitValidator splitValidator;

	@Autowired
	private PDFGalWebUtils pdfGalWebUtils;

	@InitBinder(SPLIT_FORM)
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
		return this.getModelAndView(true);
	}

	@RequestMapping(method = RequestMethod.POST)
	public final ModelAndView split(
			@ModelAttribute(SPLIT_FORM) @Valid final SplitForm splitForm,
			final BindingResult result, final HttpServletResponse response) {

		if (result.hasErrors()) {
			return this.getModelAndView(false);
		}

		final MultipartFile file = splitForm.getFile();
		final SplitMode splitMode = splitForm.getSplitMode();
		final String pages = splitForm.getPages();

		DownloadForm downloadForm = new DownloadForm();

		try {
			downloadForm = this.splitService.split(file, splitMode, pages,
					response);
		} catch (final Exception e) {
			// Default error is added
			result.addError(this.pdfGalWebUtils.createDefaultFieldError(
					SPLIT_FORM, "pages", pages, "split.validator.error"));
			return this.getModelAndView(false);
		}

		final ModelAndView mav = this.getModelAndView(true);
		mav.addObject("downloadForm", downloadForm);

		return mav;
	}

	/**
	 * Returns a new {@link ModelAndView} for splitting page, including the
	 * {@link SplitMode} but not the {@link SplitForm}.
	 * 
	 * @param splitForm
	 * 
	 * @return
	 */
	private ModelAndView getModelAndView(final boolean splitForm) {
		final ModelAndView mav = new ModelAndView("split");
		mav.addObject("splitModes", SplitMode.values());
		if (splitForm) {
			mav.addObject(SPLIT_FORM, new SplitForm());
		}
		return mav;
	}
}
