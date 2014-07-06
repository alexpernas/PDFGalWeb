package org.pdfgal.pdfgalweb.controllers;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.pdfgal.pdfgalweb.forms.DownloadForm;
import org.pdfgal.pdfgalweb.forms.ProtectForm;
import org.pdfgal.pdfgalweb.services.ProtectService;
import org.pdfgal.pdfgalweb.utils.PDFGalWebUtils;
import org.pdfgal.pdfgalweb.validators.ProtectValidator;
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
@RequestMapping("/protect")
public class ProtectController extends BaseController {

	private static final long serialVersionUID = 7810627798019860038L;

	private static final String PROTECT_FORM = "protectForm";

	@Autowired
	private ProtectService protectService;

	@Autowired
	private ProtectValidator protectValidator;

	@Autowired
	private PDFGalWebUtils pdfGalWebUtils;

	@InitBinder(PROTECT_FORM)
	protected void initBinder(final WebDataBinder binder) {
		binder.setValidator(this.protectValidator);
	}

	/**
	 * Start Page for protecting.
	 * 
	 * @return ModelAndView
	 */
	@RequestMapping(method = RequestMethod.GET)
	public final ModelAndView getInicioPage() {
		return this.getModelAndView();
	}

	@RequestMapping(method = RequestMethod.POST)
	public final ModelAndView protect(
			@ModelAttribute(PROTECT_FORM) @Valid final ProtectForm protectForm,
			final BindingResult result, final HttpServletResponse response) {

		if (result.hasErrors()) {
			return new ModelAndView("protect");
		}

		final MultipartFile file = protectForm.getFile();
		final String password = protectForm.getPassword();
		final String repeatedPassword = protectForm.getRepeatedPassword();

		DownloadForm downloadForm = new DownloadForm();

		try {
			downloadForm = this.protectService.protect(file, password,
					repeatedPassword, response);
		} catch (final Exception e) {
			// Default error is added
			result.addError(this.pdfGalWebUtils.createDefaultFieldError(
					PROTECT_FORM, "repeatedPassword", repeatedPassword,
					"protect.validator.error"));
			return new ModelAndView("protect");
		}

		final ModelAndView mav = this.getModelAndView();
		mav.addObject("downloadForm", downloadForm);

		return mav;
	}

	/**
	 * Returns the {@link ModelAndView} for protection.
	 * 
	 * @return
	 */
	private ModelAndView getModelAndView() {

		final ModelAndView mav = new ModelAndView("protect");
		mav.addObject(PROTECT_FORM, new ProtectForm());
		return mav;
	}
}
