package org.pdfgal.pdfgalweb.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/merge")
public class MergeController extends BaseController {

	private static final long serialVersionUID = -7355124251927717885L;

	/**
	 * Start Page for merging.
	 * 
	 * @return ModelAndView
	 */
	@RequestMapping(method = RequestMethod.GET)
	public final ModelAndView getInicioPage() {
		ModelAndView mav =  new ModelAndView("merge");
		return mav;
	}
}
