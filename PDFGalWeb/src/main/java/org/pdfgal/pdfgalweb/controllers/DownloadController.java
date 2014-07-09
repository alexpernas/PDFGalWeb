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
		final String contentType = downloadForm.getContent();

		try {
			this.fileUtils
					.prepareFileDownload(response, uri, name, contentType);
			this.fileUtils.delete(uri);
		} catch (final IOException e) {
			this.fileUtils.delete(uri);
		}

		return null;
	}
}
