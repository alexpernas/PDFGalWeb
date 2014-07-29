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
	public void prepareFileDownload(final HttpServletResponse response, final String uri,
			final String fileName) throws IOException {

		final FileInputStream fileInputStream = new FileInputStream(uri);
		response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
		IOUtils.copyLarge(fileInputStream, response.getOutputStream());
		fileInputStream.close();
	}

	@Override
	public String prepareZipFile(final List<String> urisList, final String fileName)
			throws FileNotFoundException, IOException {

		// It's supposed that file extension (dot included) is 4 digits.
		final String fileNameWithoutExtension = this.getFileNameWithoutExtension(fileName);

		// Files are included into a ZIP File
		return this.zipUtils.zipFiles(urisList, fileNameWithoutExtension);
	}

	@Override
	public String saveFile(final MultipartFile file) {

		String name = null;

		if (!file.isEmpty()) {
			try {
				final String originalName = file.getOriginalFilename();
				name = this.getAutogeneratedName(originalName);
				final byte[] bytes = file.getBytes();
				final BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(
						new File(name)));
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

				if (file.getSize() > 0) {

					final String path = this.saveFile(file);

					if (StringUtils.isBlank(path)) {
						this.delete(result);
						return null;
					}

					result.add(path);
				}
			}
		}

		return result;
	}

	@Override
	public String getFileNameWithoutExtension(final String fileName) {

		String result = "";

		if (StringUtils.isNotBlank(fileName) && fileName.length() > 4) {
			result = fileName.substring(0, fileName.length() - 4);
		}

		return result;
	}
}
