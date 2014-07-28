package org.pdfgal.pdfgalweb.forms;

import java.io.Serializable;
import java.util.List;

import org.pdfgal.pdfgal.model.PDFGalBookmark;
import org.springframework.web.multipart.MultipartFile;

public class BookmarkForm implements Serializable {

	private static final long serialVersionUID = -1181042274844785913L;

	private MultipartFile file;

	private String title;

	private List<PDFGalBookmark> bookmarks;

	public MultipartFile getFile() {
		return this.file;
	}

	public void setFile(final MultipartFile file) {
		this.file = file;
	}

	/**
	 * @return the title
	 */
	public String getTitle() {
		return this.title;
	}

	/**
	 * @param title the title to set
	 */
	public void setTitle(final String title) {
		this.title = title;
	}

	/**
	 * @return the bookmarks
	 */
	public List<PDFGalBookmark> getBookmarks() {
		return this.bookmarks;
	}

	/**
	 * @param bookmarks the bookmarks to set
	 */
	public void setBookmarks(final List<PDFGalBookmark> bookmarks) {
		this.bookmarks = bookmarks;
	}

}
