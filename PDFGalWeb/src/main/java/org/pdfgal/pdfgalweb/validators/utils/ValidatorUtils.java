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

package org.pdfgal.pdfgalweb.validators.utils;

import org.pdfgal.pdfgalweb.model.enumerated.PDFEncryptionType;
import org.springframework.validation.Errors;
import org.springframework.web.multipart.MultipartFile;

public interface ValidatorUtils {

	/**
	 * Validates if the parameter is a valid file name. This string is valid
	 * when contains alphanumeric characters, spaces, character _, character -
	 * or dot.
	 * 
	 * @param fileName
	 * @return
	 */
	public boolean validateFileName(String fileName);

	/**
	 * This method validates a file, it returns a {@link PDFEncryptionType}
	 * indicating if the file is not a PDF, if it is an encrypted PDF or if it
	 * is not an encrypted PDF.
	 * 
	 * @param file
	 * @return
	 */
	public PDFEncryptionType validatePDF(MultipartFile file);

	/**
	 * This method validates a file, it returns a {@link PDFEncryptionType}
	 * indicating if the file is not a PDF, if it is an encrypted PDF or if it
	 * is not an encrypted PDF. Parameter represents the URI where file is
	 * stored.
	 * 
	 * @param file
	 * @return
	 */
	public PDFEncryptionType validatePDF(String uri);

	/**
	 * This method validates a file, setting the corresponding error on argument
	 * errors, there is needed the {@link PDFEncryptionType} to see if file must
	 * be encrypted or not.
	 * 
	 * @param file
	 * @param errors
	 * @param pdfEncryptionType
	 */
	public void validateFile(MultipartFile file, Errors errors,
			PDFEncryptionType pdfEncryptionType);

	/**
	 * Validates if argument is a string of incremented {@link Integer}
	 * separated by delimiters.
	 * 
	 * @param pages
	 * @param file
	 * @param delim1
	 *            First delimiter for tokens.
	 * @param delim2
	 *            Second delimiter for tokens.
	 * @param testMoreThanOne
	 *            It is tested that pages are more than 1.
	 * @param notBlankPages
	 *            True: Argument pages cannot be blank. False: Argument pages
	 *            can be blank.
	 * @return
	 */
	boolean validateConcretePages(String pages, MultipartFile file,
			String delim1, String delim2, boolean testMoreThanOne,
			boolean notBlankPages);

}
