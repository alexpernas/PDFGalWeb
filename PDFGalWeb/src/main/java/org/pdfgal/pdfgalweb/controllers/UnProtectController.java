package org.pdfgal.pdfgalweb.controllers;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.pdfgal.pdfgalweb.forms.UnProtectForm;
import org.pdfgal.pdfgalweb.services.UnProtectService;
import org.pdfgal.pdfgalweb.utils.PDFGalWebUtils;
import org.pdfgal.pdfgalweb.validators.UnProtectValidator;
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
@RequestMapping("/unprotect")
public class UnProtectController extends BaseController {

	private static final long serialVersionUID = 8795712832938321649L;

	private static final String UNPROTECT_FORM = "unProtectForm";

	@Autowired
	private UnProtectService unProtectService;

	@Autowired
	private UnProtectValidator unProtectValidator;

	@Autowired
	private PDFGalWebUtils pdfGalWebUtils;

	@InitBinder
	protected void initBinder(final WebDataBinder binder) {
		binder.setValidator(this.unProtectValidator);
	}

	/**
	 * Start Page for unprotecting.
	 * 
	 * @return ModelAndView
	 */
	@RequestMapping(method = RequestMethod.GET)
	public final ModelAndView getInicioPage() {
		final ModelAndView mav = new ModelAndView("unprotect");
		mav.addObject(UNPROTECT_FORM, new UnProtectForm());
		return mav;
	}

	@RequestMapping(method = RequestMethod.POST)
	public final ModelAndView unProtect(
			@ModelAttribute(UNPROTECT_FORM) @Valid final UnProtectForm unProtectForm,
			final BindingResult result, final HttpServletResponse response) {

		if (result.hasErrors()) {
			return new ModelAndView("unprotect");
		}

		final MultipartFile file = unProtectForm.getFile();
		final String password = unProtectForm.getPassword();

		try {
			this.unProtectService.unProtect(file, password, response);
		} catch (final Exception e) {
			// Default error is added
			result.addError(this.pdfGalWebUtils.createDefaultFieldError(
					UNPROTECT_FORM, "password", password,
					"unprotect.validator.error"));
			return new ModelAndView("unprotect");
		}

		return null;
	}
}
