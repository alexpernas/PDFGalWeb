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

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * Utils interface for ZIP files.
 * 
 * @author Alex
 * 
 */
public interface ZipUtils {

	/**
	 * Saves the files located at URIs set on the urisList into a new ZIP file,
	 * named fileName.
	 * 
	 * @param urisList
	 * @param fileName
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public String zipFiles(List<String> urisList, String fileName) throws FileNotFoundException,
			IOException;

	/**
	 * Saves the files inside the parameter's ZIP. Returns the URIs where files
	 * were saved.
	 * 
	 * @param inputStream
	 * @return
	 * @throws IOException
	 */
	public List<String> saveFilesFromZip(InputStream inputStream) throws IOException;

	/**
	 * Returns true in case inputStream represents a ZIP file, returns false
	 * otherwise.
	 * 
	 * @param inputStream
	 * @return
	 */
	public boolean isZip(InputStream inputStream);

}
