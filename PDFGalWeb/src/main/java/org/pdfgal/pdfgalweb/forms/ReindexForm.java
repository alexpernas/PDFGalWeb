package org.pdfgal.pdfgalweb.forms;

import java.io.Serializable;
import java.util.List;

import org.pdfgal.pdfgal.model.enumerated.NumberingStyle;
import org.springframework.web.multipart.MultipartFile;

public class ReindexForm implements Serializable {

	private static final long serialVersionUID = 3911023673406556214L;

	private MultipartFile file;

	private List<Integer> pagesList;

	private List<NumberingStyle> numberingStylesList;

	/**
	 * @return the file
	 */
	public MultipartFile getFile() {
		return this.file;
	}

	/**
	 * @param file
	 *            the file to set
	 */
	public void setFile(final MultipartFile file) {
		this.file = file;
	}

	/**
	 * @return the pagesList
	 */
	public List<Integer> getPagesList() {
		return this.pagesList;
	}

	/**
	 * @param pagesList
	 *            the pagesList to set
	 */
	public void setPagesList(final List<Integer> pagesList) {
		this.pagesList = pagesList;
	}

	public List<NumberingStyle> getNumberingStylesList() {
		return this.numberingStylesList;
	}

	public void setNumberingStylesList(
			final List<NumberingStyle> numberingStylesList) {
		this.numberingStylesList = numberingStylesList;
	}

}