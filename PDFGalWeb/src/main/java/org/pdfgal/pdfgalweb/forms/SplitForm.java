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

package org.pdfgal.pdfgalweb.forms;

import java.io.Serializable;

import org.pdfgal.pdfgalweb.model.enumerated.SplitMode;
import org.springframework.web.multipart.MultipartFile;

public class SplitForm implements Serializable {

	private static final long serialVersionUID = -3475336906273517756L;

	private MultipartFile file;

	private SplitMode splitMode;

	private String pages;

	/**
	 * @return the file
	 */
	public MultipartFile getFile() {
		return this.file;
	}

	/**
	 * @param file the file to set
	 */
	public void setFile(final MultipartFile file) {
		this.file = file;
	}

	/**
	 * @return the splitMode
	 */
	public SplitMode getSplitMode() {
		return this.splitMode;
	}

	/**
	 * @param splitMode the splitMode to set
	 */
	public void setSplitMode(final SplitMode splitMode) {
		this.splitMode = splitMode;
	}

	public String getPages() {
		return this.pages;
	}

	public void setPages(final String pages) {
		this.pages = pages;
	}

}
