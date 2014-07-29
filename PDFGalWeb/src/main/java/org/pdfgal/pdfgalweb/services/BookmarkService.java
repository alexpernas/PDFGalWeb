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

package org.pdfgal.pdfgalweb.services;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.pdfgal.pdfgalweb.forms.DownloadForm;
import org.springframework.web.multipart.MultipartFile;

public interface BookmarkService {

	DownloadForm addBookmarks(MultipartFile file, String title, List<Integer> pagesList,
			List<String> textsList, HttpServletResponse response) throws Exception;

}
