package org.pdfgal.pdfgalweb.utils.impl;

import org.pdfgal.pdfgalweb.utils.PDFGalWebUtils;
import org.springframework.stereotype.Component;
import org.springframework.validation.FieldError;

@Component
public class PDFGalWebUtilsImpl implements PDFGalWebUtils {

	@Override
	public FieldError createDefaultFieldError(final String objectName,
			final String field, final Object rejectedValue, final String code) {

		final String[] codes = new String[1];
		codes[0] = code;
		return new FieldError(objectName, field, rejectedValue, false, codes,
				null, null);
	}
}
