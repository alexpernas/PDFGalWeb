package org.pdfgal.pdfgalweb.controllers;

import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.pdfgal.pdfgalweb.forms.MergeForm;
import org.pdfgal.pdfgalweb.services.MergeService;
import org.pdfgal.pdfgalweb.validators.MergeValidator;
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
@RequestMapping("/merge")
public class MergeController extends BaseController {

	private static final long serialVersionUID = -7355124251927717885L;

	private static final String MERGE_FORM = "mergeForm";

	@Autowired
	private MergeService mergeService;

	@Autowired
	private MergeValidator mergeValidator;

	@InitBinder
	protected void initBinder(final WebDataBinder binder) {
		binder.setValidator(this.mergeValidator);
	}

	/**
	 * Start Page for merging.
	 * 
	 * @return ModelAndView
	 */
	@RequestMapping(method = RequestMethod.GET)
	public final ModelAndView getInicioPage() {
		final ModelAndView mav = new ModelAndView("merge");
		mav.addObject(MERGE_FORM, new MergeForm());
		return mav;
	}

	@RequestMapping(method = RequestMethod.POST)
	public final ModelAndView merge(
			@ModelAttribute(MERGE_FORM) @Valid final MergeForm mergeForm,
			final BindingResult result, final HttpServletResponse response) {

		if (result.hasErrors()) {
			return new ModelAndView("merge");
		}

		final List<MultipartFile> files = mergeForm.getFiles();
		final String fileName = mergeForm.getFileName();

		this.mergeService.merge(files, fileName, response);

		return null;
	}
}
