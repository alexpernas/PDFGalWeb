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

import org.springframework.web.multipart.MultipartFile;

public class MergeForm implements Serializable {

	private static final long serialVersionUID = 4100877460139267074L;

	private List<MultipartFile> files;

	private String fileName;

	public List<MultipartFile> getFiles() {
		return this.files;
	}

	public void setFiles(final List<MultipartFile> files) {
		this.files = files;
	}

	public String getFileName() {
		return this.fileName;
	}

	public void setFileName(final String fileName) {
		this.fileName = fileName;
	}

}
