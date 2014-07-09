package org.pdfgal.pdfgalweb.services.impl;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.pdfbox.exceptions.COSVisitorException;
import org.pdfgal.pdfgal.pdfgal.PDFGal;
import org.pdfgal.pdfgalweb.forms.DownloadForm;
import org.pdfgal.pdfgalweb.services.MergeService;
import org.pdfgal.pdfgalweb.utils.FileUtils;
import org.pdfgal.pdfgalweb.utils.ZipUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class MergeServiceImpl implements MergeService {

	@Autowired
	private FileUtils fileUtils;

	@Autowired
	private PDFGal pdfGal;

	@Autowired
	private ZipUtils zipUtils;

	@Override
	public DownloadForm merge(final List<MultipartFile> files,
			final String fileName, final HttpServletResponse response)
			throws Exception {

		DownloadForm result = new DownloadForm();

		if (CollectionUtils.isNotEmpty(files)
				&& StringUtils.isNotBlank(fileName)) {

			List<String> inputUris = new ArrayList<String>();
			final String outputUri = this.fileUtils
					.getAutogeneratedName(fileName);

			if (files.size() == 1) {
				// It's a ZIP file
				final InputStream fileInputStream = files.get(0)
						.getInputStream();
				inputUris = this.zipUtils.saveFilesFromZip(fileInputStream);
				fileInputStream.close();

			} else {
				// They are PDF files
				inputUris = this.fileUtils.saveFile(files);
			}

			try {
				// Merge documents
				this.pdfGal.merge(inputUris, outputUri);

				this.fileUtils.delete(inputUris);

				result = new DownloadForm(outputUri, fileName + ".pdf");

			} catch (COSVisitorException | IOException e) {
				this.fileUtils.delete(inputUris);
				this.fileUtils.delete(outputUri);
				throw e;
			}
		}

		return result;
	}
}
