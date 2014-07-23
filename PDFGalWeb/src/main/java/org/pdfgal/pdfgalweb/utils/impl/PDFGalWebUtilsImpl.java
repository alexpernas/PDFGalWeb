package org.pdfgal.pdfgalweb.utils.impl;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import org.pdfgal.pdfgalweb.utils.PDFGalWebUtils;
import org.springframework.stereotype.Component;
import org.springframework.validation.FieldError;

@Component
public class PDFGalWebUtilsImpl implements PDFGalWebUtils {

	@Override
	public FieldError createDefaultFieldError(final String objectName, final String field,
			final Object rejectedValue, final String code) {

		final String[] codes = new String[1];
		codes[0] = code;
		return new FieldError(objectName, field, rejectedValue, false, codes, null, null);
	}

	@Override
	public List<Color> getColors() {

		final List<Color> result = new ArrayList<Color>();

		result.add(Color.WHITE);
		result.add(Color.LIGHT_GRAY);
		result.add(Color.GRAY);
		result.add(Color.DARK_GRAY);
		result.add(Color.BLACK);
		result.add(Color.RED);
		result.add(Color.PINK);
		result.add(Color.ORANGE);
		result.add(Color.YELLOW);
		result.add(Color.GREEN);
		result.add(Color.MAGENTA);
		result.add(Color.CYAN);
		result.add(Color.BLUE);

		return result;
	}
}
