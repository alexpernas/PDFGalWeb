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

import java.io.IOException;

import org.pdfgal.pdfgal.utils.PDFUtils;
import org.pdfgal.pdfgalweb.utils.FileUtils;
import org.pdfgal.pdfgalweb.utils.PDFGalWebUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.FieldError;
import org.springframework.web.multipart.MultipartFile;

@Component
public class PDFGalWebUtilsImpl implements PDFGalWebUtils {

	@Autowired
	private FileUtils fileUtils;

	@Autowired
	private PDFUtils pdfUtils;

	@Override
	public FieldError createDefaultFieldError(final String objectName, final String field,
			final Object rejectedValue, final String code) {

		final String[] codes = new String[1];
		codes[0] = code;
		return new FieldError(objectName, field, rejectedValue, false, codes, null, null);
	}

	@Override
	public Integer getPages(final MultipartFile file) throws IOException {
		final String uri = this.fileUtils.saveFile(file);
		final Integer result = this.pdfUtils.getPages(uri);
		this.fileUtils.delete(uri);
		return result;
	}
}
