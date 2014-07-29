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
import java.util.List;

import org.pdfgal.pdfgal.model.enumerated.NumberingStyle;
import org.springframework.web.multipart.MultipartFile;

public class ReindexForm implements Serializable {

	private static final long serialVersionUID = 3911023673406556214L;

	private MultipartFile file;

	private List<Integer> pagesList;

	private List<NumberingStyle> numberingStylesList;

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
	 * @return the pagesList
	 */
	public List<Integer> getPagesList() {
		return this.pagesList;
	}

	/**
	 * @param pagesList the pagesList to set
	 */
	public void setPagesList(final List<Integer> pagesList) {
		this.pagesList = pagesList;
	}

	public List<NumberingStyle> getNumberingStylesList() {
		return this.numberingStylesList;
	}

	public void setNumberingStylesList(final List<NumberingStyle> numberingStylesList) {
		this.numberingStylesList = numberingStylesList;
	}

}
