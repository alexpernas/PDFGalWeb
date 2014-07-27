package org.pdfgal.pdfgalweb.forms;

import java.io.Serializable;

import org.springframework.web.multipart.MultipartFile;

public class BookmarkForm implements Serializable {

	private static final long serialVersionUID = -1181042274844785913L;

	private MultipartFile file;

	public MultipartFile getFile() {
		return this.file;
	}

	public void setFile(final MultipartFile file) {
		this.file = file;
	}

}
