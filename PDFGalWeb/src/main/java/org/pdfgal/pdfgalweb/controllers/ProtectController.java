package org.pdfgal.pdfgalweb.controllers;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.apache.pdfbox.exceptions.COSVisitorException;
import org.apache.pdfbox.pdmodel.encryption.BadSecurityHandlerException;
import org.pdfgal.pdfgalweb.forms.ProtectForm;
import org.pdfgal.pdfgalweb.services.ProtectService;
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

	@InitBinder
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
		final ModelAndView mav = new ModelAndView("protect");
		mav.addObject(PROTECT_FORM, new ProtectForm());
		return mav;
	}

	@RequestMapping(method = RequestMethod.POST)
	public final ModelAndView protect(
			@ModelAttribute(PROTECT_FORM) @Valid final ProtectForm protectForm,
			final BindingResult result, final HttpServletResponse response) {

		final MultipartFile file = protectForm.getFile();
		final String password = protectForm.getPassword();
		final String repeatedPassword = protectForm.getRepeatedPassword();

		try {
			this.protectService.protect(file, password, repeatedPassword,
					response);
		} catch (COSVisitorException | IOException
				| BadSecurityHandlerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}
}
