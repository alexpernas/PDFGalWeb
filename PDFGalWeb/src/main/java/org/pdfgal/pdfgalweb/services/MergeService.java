package org.pdfgal.pdfgalweb.services;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.pdfgal.pdfgalweb.forms.DownloadForm;
import org.springframework.web.multipart.MultipartFile;

public interface MergeService {

	public DownloadForm merge(List<MultipartFile> files, String fileName,
			HttpServletResponse response) throws Exception;

}
