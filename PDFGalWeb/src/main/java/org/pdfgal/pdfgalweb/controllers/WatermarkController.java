package org.pdfgal.pdfgalweb.controllers;

import java.util.ArrayList;
import java.util.List;

import org.pdfgal.pdfgalweb.forms.WatermarkForm;
import org.pdfgal.pdfgalweb.utils.PDFGalWebUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/watermark")
public class WatermarkController extends BaseController {

	private static final long serialVersionUID = -2248308724303578028L;

	private static final String WATERMARK_FORM = "watermarkForm";

	@Autowired
	private PDFGalWebUtils pdfGalWebUtils;

	/**
	 * Start Page for adding watermark.
	 * 
	 * @return ModelAndView
	 */
	@RequestMapping(method = RequestMethod.GET)
	public final ModelAndView getInicioPage() {
		return this.getModelAndView();
	}

	/**
	 * Returns the {@link ModelAndView} for adding watermark.
	 * 
	 * @return
	 */
	private ModelAndView getModelAndView() {

		final ModelAndView mav = new ModelAndView("watermark");

		mav.addObject(WATERMARK_FORM, new WatermarkForm());
		mav.addObject("colors", this.pdfGalWebUtils.getColors());

		final List<Float> alphaList = new ArrayList<Float>();
		for (float i = 0.1f; i <= 1.0f; i = i + 0.1f) {
			alphaList.add(i);
		}
		mav.addObject("alphaList", this.pdfGalWebUtils.getColors());

		return mav;
	}
}
