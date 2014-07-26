package org.pdfgal.pdfgalweb.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.pdfgal.pdfgal.model.enumerated.WatermarkPosition;
import org.pdfgal.pdfgalweb.forms.DownloadForm;
import org.pdfgal.pdfgalweb.forms.WatermarkForm;
import org.pdfgal.pdfgalweb.model.enumerated.CustomColor;
import org.pdfgal.pdfgalweb.services.WatermarkService;
import org.pdfgal.pdfgalweb.utils.Constants;
import org.pdfgal.pdfgalweb.utils.PDFGalWebUtils;
import org.pdfgal.pdfgalweb.validators.WatermarkValidator;
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
@RequestMapping("/watermark")
public class WatermarkController extends BaseController {

	private static final long serialVersionUID = -2248308724303578028L;

	private static final String WATERMARK_FORM = "watermarkForm";

	@Autowired
	private PDFGalWebUtils pdfGalWebUtils;

	@Autowired
	private WatermarkService watermarkService;

	@Autowired
	private WatermarkValidator watermarkValidator;

	@InitBinder(WATERMARK_FORM)
	protected void initBinder(final WebDataBinder binder) {
		binder.setValidator(this.watermarkValidator);
	}

	/**
	 * Start Page for adding watermark.
	 * 
	 * @return ModelAndView
	 */
	@RequestMapping(method = RequestMethod.GET)
	public final ModelAndView getInicioPage() {
		return this.getModelAndView(true);
	}

	@RequestMapping(method = RequestMethod.POST)
	public final ModelAndView watermark(
			@ModelAttribute(WATERMARK_FORM) @Valid final WatermarkForm watermarkForm,
			final BindingResult result, final HttpServletResponse response) {

		if (result.hasErrors()) {
			return this.getModelAndView(false);
		}

		final MultipartFile file = watermarkForm.getFile();
		final String text = watermarkForm.getText();
		final CustomColor customColor = watermarkForm.getCustomColor();
		final Float alpha = watermarkForm.getAlpha();
		final WatermarkPosition watermarkPosition = watermarkForm
				.getWatermarkPosition();
		final String pages = watermarkForm.getPages();

		DownloadForm downloadForm = new DownloadForm();

		try {
			downloadForm = this.watermarkService.putWatermark(file, text,
					customColor, alpha, watermarkPosition, pages, response);
		} catch (final Exception e) {
			// Default error is added
			result.addError(this.pdfGalWebUtils
					.createDefaultFieldError(WATERMARK_FORM, "pages", pages,
							"watermark.validator.error"));
			return this.getModelAndView(false);
		}

		final ModelAndView mav = this.getModelAndView(true);
		mav.addObject(Constants.DOWNLOAD_FORM, downloadForm);

		return mav;
	}

	/**
	 * Returns the {@link ModelAndView} for adding watermark.
	 * 
	 * @param watermarkForm
	 *            Indicates if {@link WatermarkForm} must be included.
	 * 
	 * @return
	 */
	private ModelAndView getModelAndView(final boolean watermarkForm) {

		final ModelAndView mav = new ModelAndView("watermark");

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

		if (watermarkForm) {
			mav.addObject(WATERMARK_FORM, new WatermarkForm());
		}

		return mav;
	}
}
