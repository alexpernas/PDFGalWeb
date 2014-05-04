package org.pdfgal.pdfgalweb.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/split")
public class SplitController extends BaseController {

	private static final long serialVersionUID = 2062284077983922984L;
	
	/**
	 * Start Page for splitting.
	 * 
	 * @return ModelAndView
	 */
	@RequestMapping(method = RequestMethod.GET)
	public final ModelAndView getInicioPage() {
		ModelAndView mav =  new ModelAndView("split");
		return mav;
	}

}
