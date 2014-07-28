package org.pdfgal.pdfgalweb.services;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.pdfgal.pdfgalweb.forms.DownloadForm;
import org.springframework.web.multipart.MultipartFile;

public interface BookmarkService {

	DownloadForm addBookmarks(MultipartFile file, String title, List<Integer> pagesList,
			List<String> textsList, HttpServletResponse response) throws Exception;

}
