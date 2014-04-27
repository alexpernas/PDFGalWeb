package org.pdfgal.pdfgalweb.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/home")
public class HomeController extends BaseController {

	private static final long serialVersionUID = 2879730384874298410L;

	/**
	 * Start Page.
	 * 
	 * @return ModelAndView
	 */
	@RequestMapping(method = RequestMethod.GET)
	public final ModelAndView getInicioPage() {
		ModelAndView mav =  new ModelAndView("home");
		return mav;
	}
	
}
