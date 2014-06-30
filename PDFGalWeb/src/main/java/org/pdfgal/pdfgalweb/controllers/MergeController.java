package org.pdfgal.pdfgalweb.controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.pdfbox.exceptions.COSVisitorException;
import org.pdfgal.pdfgal.pdfgal.PDFGal;
import org.pdfgal.pdfgalweb.forms.MergeForm;
import org.pdfgal.pdfgalweb.utils.FileUtils;
import org.pdfgal.pdfgalweb.utils.ZipUtils;
import org.pdfgal.pdfgalweb.validators.MergeValidator;
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
@RequestMapping("/merge")
public class MergeController extends BaseController {

	private static final long serialVersionUID = -7355124251927717885L;

	private static final String MERGE_FORM = "mergeForm";

	@Autowired
	private FileUtils fileUtils;

	@Autowired
	private MergeValidator mergeValidator;

	@Autowired
	private PDFGal pdfGal;

	@Autowired
	private ZipUtils zipUtils;

	@InitBinder
	protected void initBinder(final WebDataBinder binder) {
		binder.setValidator(this.mergeValidator);
	}

	/**
	 * Start Page for merging.
	 * 
	 * @return ModelAndView
	 */
	@RequestMapping(method = RequestMethod.GET)
	public final ModelAndView getInicioPage() {
		final ModelAndView mav = new ModelAndView("merge");
		mav.addObject(MERGE_FORM, new MergeForm());
		return mav;
	}

	@RequestMapping(method = RequestMethod.POST)
	public final ModelAndView protect(
			@ModelAttribute(MERGE_FORM) @Valid final MergeForm mergeForm,
			final BindingResult result, final HttpServletResponse response) {

		if (result.hasErrors()) {
			return new ModelAndView("merge");
		}

		final List<MultipartFile> files = mergeForm.getFiles();
		final String fileName = mergeForm.getFileName();

		if (CollectionUtils.isNotEmpty(files)
				&& StringUtils.isNotBlank(fileName)) {

			List<String> inputUris = new ArrayList<String>();
			final String outputUri = this.fileUtils
					.getAutogeneratedName(fileName);

			if (files.size() == 1) {
				// It's a ZIP file
				try {
					inputUris = this.zipUtils.saveFilesFromZip(files.get(0)
							.getInputStream());
				} catch (final IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			} else {
				// They are PDF files
				inputUris = this.fileUtils.saveFile(files);
			}

			try {
				// Merge documents
				this.pdfGal.merge(inputUris, outputUri);

				// File is prepared for download
				this.fileUtils.prepareFileDownload(response, outputUri,
						fileName);

			} catch (COSVisitorException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				this.fileUtils.delete(inputUris);
				this.fileUtils.delete(outputUri);
			}

		} else {
			// TODO
		}

		// TODO

		return null;
	}
}
