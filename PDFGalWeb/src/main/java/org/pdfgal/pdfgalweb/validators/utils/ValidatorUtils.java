package org.pdfgal.pdfgalweb.validators.utils;

import org.pdfgal.pdfgalweb.model.enumerated.PDFEncryptionType;
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

}
