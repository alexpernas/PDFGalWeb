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

package org.pdfgal.pdfgalweb.model.enumerated;

/**
 * This enum type tries to indicate if a PDF file is encrypted, it may be
 * encryted, it may not be encrypted, and it may not be a PDF file.
 * 
 * @author Alex
 * 
 */
public enum PDFEncryptionType {

	NON_PDF, ENCRYPTED, NON_ENCRYPTED;

}
