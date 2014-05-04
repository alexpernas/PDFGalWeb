package org.pdfgal.pdfgalweb.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/protect")
public class ProtectController extends BaseController {

	private static final long serialVersionUID = 7810627798019860038L;

	/**
	 * Start Page for protecting.
	 * 
	 * @return ModelAndView
	 */
	@RequestMapping(method = RequestMethod.GET)
	public final ModelAndView getInicioPage() {
		ModelAndView mav =  new ModelAndView("protect");
		return mav;
	}
}
