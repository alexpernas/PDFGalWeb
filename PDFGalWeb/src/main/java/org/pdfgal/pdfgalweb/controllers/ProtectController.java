package org.pdfgal.pdfgalweb.controllers;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.pdfgal.pdfgal.pdfgal.PDFGal;
import org.pdfgal.pdfgalweb.forms.ProtectForm;
import org.pdfgal.pdfgalweb.utils.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/protect")
public class ProtectController extends BaseController {

	private static final long serialVersionUID = 7810627798019860038L;

	private static final String PROTECT_FORM = "protectForm";

	@Autowired
	private FileUtils fileUtils;

	@Autowired
	private PDFGal pdfGal;

	/**
	 * Start Page for protecting.
	 * 
	 * @return ModelAndView
	 */
	@RequestMapping(method = RequestMethod.GET)
	public final ModelAndView getInicioPage() {
		final ModelAndView mav = new ModelAndView("protect");
		mav.addObject(PROTECT_FORM, new ProtectForm());
		return mav;
	}

	@RequestMapping(method = RequestMethod.POST)
	public final ModelAndView protect(@ModelAttribute(PROTECT_FORM) final ProtectForm protectForm,
			final HttpServletResponse response) {

		final MultipartFile file = protectForm.getFile();
		final String password = protectForm.getPassword();
		final String repeatedPassword = protectForm.getRepeatedPassword();

		if (!file.isEmpty() && StringUtils.isNotBlank(password)
				&& StringUtils.isNotBlank(repeatedPassword) && password.equals(repeatedPassword)) {
			try {
				final String originalName = file.getOriginalFilename();
				final String inputUri = this.fileUtils.saveFile(file);
				final String outputUri = this.fileUtils.getAutogeneratedName(originalName);

				// File is protected
				this.pdfGal.protect(inputUri, outputUri, password);

				// The protected file is prepared for download
				this.fileUtils.prepareFileDownload(response, outputUri, originalName);

				// Temporal files are deleted from system
				this.fileUtils.delete(inputUri);
				this.fileUtils.delete(outputUri);

			} catch (final Exception e) {
				// TODO Facer que amose mensaxe de erro en caso de que non se
				// cargara ning�n ficheiro, ou non se protexera, ou o que sexa
				// (un catch para cada cousa) e incluir no log.
				return this.getInicioPage();
			}
		} else {
			// TODO Facer que amose mensaxe de erro en caso de que non se
			// cargara ning�n ficheiro, ou alg�n contrasinal � baleiro, ou alg�n
			// contrasinal non existe.
			return this.getInicioPage();
		}

		return null;
	}
}
