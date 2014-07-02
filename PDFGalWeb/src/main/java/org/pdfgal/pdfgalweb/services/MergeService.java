package org.pdfgal.pdfgalweb.services;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.web.multipart.MultipartFile;

public interface MergeService {

	public void merge(List<MultipartFile> files, String fileName,
			HttpServletResponse response);

}
