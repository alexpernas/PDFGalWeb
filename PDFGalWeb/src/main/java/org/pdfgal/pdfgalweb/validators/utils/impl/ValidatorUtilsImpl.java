package org.pdfgal.pdfgalweb.validators.utils.impl;

import org.apache.commons.lang3.StringUtils;
import org.pdfgal.pdfgalweb.validators.utils.ValidatorUtils;
import org.springframework.stereotype.Component;

@Component
public class ValidatorUtilsImpl implements ValidatorUtils {

	private final String FILE_NAME_PATTERN = "([\\w\\s-.])*";

	@Override
	public boolean validateFileName(final String fileName) {

		boolean result = false;

		if (StringUtils.isNotBlank(fileName)
				&& fileName.matches(this.FILE_NAME_PATTERN)) {
			result = true;
		}

		return result;
	}
}
