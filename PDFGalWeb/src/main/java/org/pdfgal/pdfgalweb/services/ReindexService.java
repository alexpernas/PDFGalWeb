package org.pdfgal.pdfgalweb.services;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.pdfgal.pdfgal.model.enumerated.NumberingStyle;
import org.pdfgal.pdfgalweb.forms.DownloadForm;
import org.springframework.web.multipart.MultipartFile;

public interface ReindexService {

	public DownloadForm reindex(MultipartFile file, List<Integer> pagesList,
			List<NumberingStyle> numberingStylesList,
			HttpServletResponse response) throws Exception;

}
