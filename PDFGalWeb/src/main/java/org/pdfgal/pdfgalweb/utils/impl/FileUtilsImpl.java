package org.pdfgal.pdfgalweb.utils.impl;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.pdfgal.pdfgalweb.utils.FileUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class FileUtilsImpl implements FileUtils {

	@Override
	public boolean delete(final String uri) {

		final File file = new File(uri);
		return file.delete();

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
}
