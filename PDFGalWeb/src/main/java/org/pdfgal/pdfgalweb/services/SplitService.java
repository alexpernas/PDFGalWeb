package org.pdfgal.pdfgalweb.services;

import javax.servlet.http.HttpServletResponse;

import org.pdfgal.pdfgalweb.model.enumerated.SplitMode;
import org.springframework.web.multipart.MultipartFile;

public interface SplitService {

	public void split(MultipartFile file, SplitMode splitMode, String pages,
			HttpServletResponse response) throws Exception;

}
