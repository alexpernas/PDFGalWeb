package org.pdfgal.pdfgalweb.utils;

import org.springframework.validation.FieldError;

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

}
