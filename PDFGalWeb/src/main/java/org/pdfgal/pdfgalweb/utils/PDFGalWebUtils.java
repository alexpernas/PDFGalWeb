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

package org.pdfgal.pdfgalweb.utils;

import java.io.IOException;

import org.springframework.validation.FieldError;
import org.springframework.web.multipart.MultipartFile;

/**
 * General interface of utils for PDFGalWeb.
 * 
 * @author Alex
 * 
 */
public interface PDFGalWebUtils {

	/**
	 * Creates a new generic {@link FieldError} for the objectName, field,
	 * rejectedValue and message (code) in arguments.
	 * 
	 * @param objectName
	 * @param field
	 * @param rejectedValue
	 * @param code
	 * @return
	 */
	public FieldError createDefaultFieldError(String objectName, String field,
			Object rejectedValue, String code);

	/**
	 * This method returns the number of pages of the document, or throws an
	 * {@link IOException} in case document can't be loaded or it is no PDF.
	 * 
	 * @param file
	 * @return
	 * @throws IOException
	 */
	public Integer getPages(MultipartFile file) throws IOException;

}
