package org.pdfgal.pdfgalweb.controllers;

import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.pdfgal.pdfgal.model.enumerated.NumberingStyle;
import org.pdfgal.pdfgalweb.forms.DownloadForm;
import org.pdfgal.pdfgalweb.forms.ReindexForm;
import org.pdfgal.pdfgalweb.services.ReindexService;
import org.pdfgal.pdfgalweb.utils.Constants;
import org.pdfgal.pdfgalweb.utils.PDFGalWebUtils;
import org.pdfgal.pdfgalweb.validators.ReindexValidator;
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
@RequestMapping("/reindex")
public class ReindexController extends BaseController {

	private static final long serialVersionUID = 3454787986580258288L;

	private static final String REINDEX_FORM = "reindexForm";

	@Autowired
	private PDFGalWebUtils pdfGalWebUtils;

	@Autowired
	private ReindexService reindexService;

	@Autowired
	private ReindexValidator reindexValidator;

	@InitBinder(REINDEX_FORM)
	protected void initBinder(final WebDataBinder binder) {
		binder.setValidator(this.reindexValidator);
	}

	/**
	 * Start Page for reindexing.
	 * 
	 * @return ModelAndView
	 */
	@RequestMapping(method = RequestMethod.GET)
	public final ModelAndView getInicioPage() {
		return this.getModelAndView(true);
	}

	@RequestMapping(method = RequestMethod.POST)
	public final ModelAndView reindex(
			@ModelAttribute(REINDEX_FORM) @Valid final ReindexForm reindexForm,
			final BindingResult result, final HttpServletResponse response) {

		if (result.hasErrors()) {
			return this.getModelAndView(false);
		}

		final MultipartFile file = reindexForm.getFile();
		final List<Integer> pagesList = reindexForm.getPagesList();
		final List<NumberingStyle> numberingStylesList = reindexForm
				.getNumbergingStylesList();

		DownloadForm downloadForm = new DownloadForm();

		try {
			downloadForm = this.reindexService.reindex(file, pagesList,
					numberingStylesList, response);
		} catch (final Exception e) {
			// Default error is added
			result.addError(this.pdfGalWebUtils.createDefaultFieldError(
					REINDEX_FORM, "numberingStylesList", numberingStylesList,
					"reindex.validator.error"));
			return this.getModelAndView(false);
		}

		final ModelAndView mav = this.getModelAndView(true);
		mav.addObject(Constants.DOWNLOAD_FORM, downloadForm);

		return mav;
	}

	/**
	 * Returns a new {@link ModelAndView} for reindexing page, including the
	 * {@link ReindexForm} depending on the argument.
	 * 
	 * @param splitForm
	 * 
	 * @return
	 */
	private ModelAndView getModelAndView(final boolean reindexForm) {
		final ModelAndView mav = new ModelAndView("reindex");
		mav.addObject("numberingStyles", NumberingStyle.values());
		if (reindexForm) {
			mav.addObject(REINDEX_FORM, new ReindexForm());
		}
		return mav;
	}
}
