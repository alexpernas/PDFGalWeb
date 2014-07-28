package org.pdfgal.pdfgalweb.services;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.pdfgal.pdfgal.model.PDFGalBookmark;
import org.pdfgal.pdfgalweb.forms.DownloadForm;
import org.springframework.web.multipart.MultipartFile;

public interface BookmarkService {

	DownloadForm addBookmarks(MultipartFile file, String title,
			List<PDFGalBookmark> pdfGalBookmarksList, HttpServletResponse response)
			throws Exception;

}
