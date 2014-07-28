package org.pdfgal.pdfgalweb.controllers;

import org.pdfgal.pdfgal.model.enumerated.NumberingStyle;
import org.pdfgal.pdfgalweb.forms.ReindexForm;
import org.pdfgal.pdfgalweb.validators.ReindexValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/reindex")
public class ReindexController extends BaseController {

	private static final long serialVersionUID = 3454787986580258288L;

	private static final String REINDEX_FORM = "reindexForm";

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
