package org.pdfgal.pdfgalweb.utils.impl;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.pdfgal.pdfgalweb.utils.FileUtils;
import org.pdfgal.pdfgalweb.utils.ZipUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class FileUtilsImpl implements FileUtils {

	@Autowired
	private ZipUtils zipUtils;

	@Override
	public boolean delete(final String uri) {

		final File file = new File(uri);
		return file.delete();

	}

	@Override
	public boolean delete(final List<String> urisList) {

		boolean result = true;

		if (CollectionUtils.isNotEmpty(urisList)) {
			for (final String uri : urisList) {
				result = this.delete(uri);
				if (!result) {
					break;
				}
			}
		}

		return result;

	}

	@Override
	public String getAutogeneratedName(final String originalName) {

		final String random = RandomStringUtils.randomAlphanumeric(20);
		return random + originalName;

	}

	@Override
	public void prepareFileDownload(final HttpServletResponse response,
			final String uri, final String fileName) throws IOException {

		final FileInputStream fileInputStream = new FileInputStream(uri);
		response.setContentType("application/pdf");
		response.setHeader("Content-Disposition", "attachment; filename="
				+ fileName);
		IOUtils.copyLarge(fileInputStream, response.getOutputStream());

	}

	@Override
	public void prepareFileDownload(final HttpServletResponse response,
			final List<String> urisList, final String fileName)
			throws FileNotFoundException, IOException {

		// It's supposed that file extension (dot included) is 4 digits.
		final String fileNameWithoutExtension = fileName.substring(0,
				fileName.length() - 4);

		// Files are included into a ZIP File
		final String zipUri = this.zipUtils.zipFiles(urisList,
				fileNameWithoutExtension);

		// File is prepared for download
		this.prepareFileDownload(response, zipUri, fileNameWithoutExtension
				+ ".zip");

		// File is deleted from system
		this.delete(zipUri);
	}

	@Override
	public String saveFile(final MultipartFile file) {

		String name = null;

		if (!file.isEmpty()) {
			try {
				final String originalName = file.getOriginalFilename();
				name = this.getAutogeneratedName(originalName);
				final byte[] bytes = file.getBytes();
				final BufferedOutputStream stream = new BufferedOutputStream(
						new FileOutputStream(new File(name)));
				stream.write(bytes);
				stream.close();
			} catch (final Exception e) {
				name = null;// TODO Incluir no log.
			}
		}

		return name;
	}

	@Override
	public List<String> saveFile(final List<MultipartFile> files) {

		final List<String> result = new ArrayList<String>();

		if (CollectionUtils.isNotEmpty(files)) {
			for (final MultipartFile file : files) {

				final String path = this.saveFile(file);

				if (StringUtils.isBlank(path)) {
					this.delete(result);
					return null;
				}

				result.add(path);
			}
		}

		return result;
	}
}
