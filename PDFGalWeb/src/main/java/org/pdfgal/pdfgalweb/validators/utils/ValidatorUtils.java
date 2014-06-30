package org.pdfgal.pdfgalweb.validators.utils;

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

}
