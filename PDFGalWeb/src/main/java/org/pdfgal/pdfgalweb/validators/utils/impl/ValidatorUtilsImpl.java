package org.pdfgal.pdfgalweb.validators.utils.impl;

import org.apache.commons.lang3.StringUtils;
import org.pdfgal.pdfgal.validator.PDFGalValidator;
import org.pdfgal.pdfgalweb.model.enumerated.PDFEncryptionType;
import org.pdfgal.pdfgalweb.utils.FileUtils;
import org.pdfgal.pdfgalweb.validators.utils.ValidatorUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class ValidatorUtilsImpl implements ValidatorUtils {

	private final String FILE_NAME_PATTERN = "([\\w\\s-.])*";

	@Autowired
	private FileUtils fileUtils;

	@Autowired
	private PDFGalValidator pdfGalValidator;

	@Override
	public boolean validateFileName(final String fileName) {

		boolean result = false;

		if (StringUtils.isNotBlank(fileName)
				&& fileName.matches(this.FILE_NAME_PATTERN)) {
			result = true;
		}

		return result;
	}

	@Override
	public PDFEncryptionType validatePDF(final MultipartFile file) {

		PDFEncryptionType result = PDFEncryptionType.NON_PDF;

		final String uri = this.fileUtils.saveFile(file);

		if (this.pdfGalValidator.isPDF(uri)) {
			if (this.pdfGalValidator.isEncrypted(uri)) {
				result = PDFEncryptionType.ENCRYPTED;
			} else {
				result = PDFEncryptionType.NON_ENCRYPTED;
			}
		}

		this.fileUtils.delete(uri);

		return result;
	}
}
