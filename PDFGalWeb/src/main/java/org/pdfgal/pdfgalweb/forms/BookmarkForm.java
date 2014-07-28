package org.pdfgal.pdfgalweb.forms;

import java.io.Serializable;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

public class BookmarkForm implements Serializable {

	private static final long serialVersionUID = -1181042274844785913L;

	private MultipartFile file;

	private String title;

	private List<Integer> pagesList;

	private List<String> textsList;

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
	 * @return the pagesList
	 */
	public List<Integer> getPagesList() {
		return this.pagesList;
	}

	/**
	 * @param pagesList the pagesList to set
	 */
	public void setPagesList(final List<Integer> pagesList) {
		this.pagesList = pagesList;
	}

	/**
	 * @return the textsList
	 */
	public List<String> getTextsList() {
		return this.textsList;
	}

	/**
	 * @param textsList the textsList to set
	 */
	public void setTextsList(final List<String> textsList) {
		this.textsList = textsList;
	}

}
