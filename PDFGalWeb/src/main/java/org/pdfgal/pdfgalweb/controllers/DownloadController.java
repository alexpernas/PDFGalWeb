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

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.pdfgal.pdfgalweb.forms.DownloadForm;
import org.pdfgal.pdfgalweb.utils.Constants;
import org.pdfgal.pdfgalweb.utils.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/download")
public class DownloadController extends BaseController {

	private static final long serialVersionUID = 7621068961777256341L;

	@Autowired
	private FileUtils fileUtils;

	@RequestMapping(method = RequestMethod.GET)
	public final ModelAndView getDownload(
			@ModelAttribute(Constants.DOWNLOAD_FORM) final DownloadForm downloadForm,
			final HttpServletResponse response) {

		final String uri = downloadForm.getUri();
		final String name = downloadForm.getName();

		try {
			this.fileUtils.prepareFileDownload(response, uri, name);
			this.fileUtils.delete(uri);
		} catch (final IOException e) {
			this.fileUtils.delete(uri);
		}

		return null;
	}
}
