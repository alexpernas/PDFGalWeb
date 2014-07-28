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
