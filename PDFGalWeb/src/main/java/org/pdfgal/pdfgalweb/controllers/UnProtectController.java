package org.pdfgal.pdfgalweb.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/unprotect")
public class UnProtectController extends BaseController {

	private static final long serialVersionUID = 8795712832938321649L;

	/**
	 * Start Page for unprotecting.
	 * 
	 * @return ModelAndView
	 */
	@RequestMapping(method = RequestMethod.GET)
	public final ModelAndView getInicioPage() {
		ModelAndView mav =  new ModelAndView("unprotect");
		return mav;
	}
}
