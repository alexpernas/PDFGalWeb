/*
 * PDFGalWeb
 * Copyright (c) 2014, Alejandro Pernas Pan, All rights reserved.
 * 
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 3.0 of the License, or (at your option) any later version.

 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 * 
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library.
 */

package org.pdfgal.pdfgalweb.controllers;

import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.pdfgal.pdfgalweb.forms.DownloadForm;
import org.pdfgal.pdfgalweb.forms.MergeForm;
import org.pdfgal.pdfgalweb.services.MergeService;
import org.pdfgal.pdfgalweb.utils.Constants;
import org.pdfgal.pdfgalweb.utils.PDFGalWebUtils;
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
	private MergeService mergeService;

	@Autowired
	private MergeValidator mergeValidator;

	@Autowired
	private PDFGalWebUtils pdfGalWebUtils;

	@InitBinder(MERGE_FORM)
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
		return this.getModelAndView();
	}

	@RequestMapping(method = RequestMethod.POST)
	public final ModelAndView merge(@ModelAttribute(MERGE_FORM) @Valid final MergeForm mergeForm,
			final BindingResult result, final HttpServletResponse response) {

		if (result.hasErrors()) {
			return new ModelAndView("merge");
		}

		final List<MultipartFile> files = mergeForm.getFiles();
		final String fileName = mergeForm.getFileName();

		DownloadForm downloadForm = new DownloadForm();

		try {
			downloadForm = this.mergeService.merge(files, fileName, response);
		} catch (final Exception e) {
			// Default error is added
			result.addError(this.pdfGalWebUtils.createDefaultFieldError(MERGE_FORM, "fileName",
					fileName, "merge.validator.error"));
			return new ModelAndView("merge");
		}

		final ModelAndView mav = this.getModelAndView();
		mav.addObject(Constants.DOWNLOAD_FORM, downloadForm);

		return mav;
	}

	/**
	 * Returns the {@link ModelAndView} for protection.
	 * 
	 * @return
	 */
	private ModelAndView getModelAndView() {

		final ModelAndView mav = new ModelAndView("merge");
		mav.addObject(MERGE_FORM, new MergeForm());
		return mav;
	}
}
