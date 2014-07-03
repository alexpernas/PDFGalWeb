package org.pdfgal.pdfgalweb.services.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.pdfbox.exceptions.COSVisitorException;
import org.pdfgal.pdfgal.pdfgal.PDFGal;
import org.pdfgal.pdfgalweb.model.enumerated.SplitMode;
import org.pdfgal.pdfgalweb.services.SplitService;
import org.pdfgal.pdfgalweb.utils.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class SplitServiceImpl implements SplitService {

	@Autowired
	private FileUtils fileUtils;

	@Autowired
	private PDFGal pdfGal;

	@Override
	public void split(final MultipartFile file, final SplitMode splitMode,
			final String pages, final HttpServletResponse response)
			throws Exception {

		if (!file.isEmpty() && splitMode != null
				&& StringUtils.isNotEmpty(pages)) {

			final String originalName = file.getOriginalFilename();
			final String inputUri = this.fileUtils.saveFile(file);
			final String outputUri = this.fileUtils
					.getAutogeneratedName(originalName);
			List<String> outputUris = new ArrayList<String>();

			try {

				if (splitMode.equals(SplitMode.NUMBER_OF_PAGES)) {
					outputUris = this.splitNumberOfPages(file, pages, inputUri,
							outputUri);

				} else if (splitMode.equals(SplitMode.CONCRETE_PAGES_TO_SPLIT)) {
					outputUris = this.splitConcretePages(file, pages, inputUri,
							outputUri);

				}

				// If everything is OK, created files are prepared for download
				this.fileUtils.prepareFileDownload(response, outputUris,
						originalName);
				// Temporal files are deleted from system (outputUris has
				// already been deleted).
				this.fileUtils.delete(inputUri);

			} catch (final Exception e) {
				// Temporal files are deleted from system (outputUris has
				// already been deleted).
				this.fileUtils.delete(inputUri);
				throw e;
			}
		}
	}

	/**
	 * This method splits the file, having each one of the new files the number
	 * of pages (except the last one).
	 * 
	 * @param file
	 * @param pages
	 * @return The list of URIs where files are saved.
	 * @throws NumberFormatException
	 * @throws IOException
	 * @throws COSVisitorException
	 */
	private List<String> splitNumberOfPages(final MultipartFile file,
			final String pages, final String inputUri, final String outputUri)
			throws NumberFormatException, COSVisitorException, IOException {

		final Integer pagesInteger = Integer.parseInt(pages);

		// File is splitted
		return this.pdfGal.split(inputUri, outputUri, pagesInteger);
	}

	/**
	 * This method splits the file, each new file will start at each one of the
	 * pages.
	 * 
	 * @param file
	 * @param pages
	 * @return The list of URIs where files are saved.
	 * @throws NumberFormatException
	 * @throws IOException
	 * @throws COSVisitorException
	 */
	private List<String> splitConcretePages(final MultipartFile file,
			final String pages, final String inputUri, final String outputUri)
			throws NumberFormatException, COSVisitorException, IOException {

		final List<Integer> pagesList = new ArrayList<Integer>();

		final StringTokenizer stringTokenizer = new StringTokenizer(pages, ",");

		while (stringTokenizer.hasMoreElements()) {
			pagesList.add(Integer.parseInt((String) stringTokenizer
					.nextElement()));
		}

		// File is splitted
		return this.pdfGal.split(inputUri, outputUri, pagesList);
	}
}