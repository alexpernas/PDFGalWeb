package org.pdfgal.pdfgalweb.controllers;

import java.util.ArrayList;
import java.util.List;

import org.pdfgal.pdfgal.model.enumerated.WatermarkPosition;
import org.pdfgal.pdfgalweb.forms.WatermarkForm;
import org.pdfgal.pdfgalweb.model.enumerated.CustomColor;
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
		mav.addObject("colors", CustomColor.values());

		final List<Float> alphaList = new ArrayList<Float>();
		for (float i = 0.1f; i < 1.05f; i = i + 0.1f) {
			// Float is formatted because it will not always be #.#, this is
			// because sometimes, in float type, 0.1 + 0.1 may result 0.2000001
			final String value = String.valueOf(i);
			alphaList.add(Float.valueOf(value.substring(0, 3)));
		}
		mav.addObject("alphaList", alphaList);

		mav.addObject("watermarkPositions", WatermarkPosition.values());

		return mav;
	}
}
