package org.pdfgal.pdfgalweb.forms;

import java.io.Serializable;

import org.pdfgal.pdfgalweb.model.enumerated.SplitMode;
import org.springframework.web.multipart.MultipartFile;

public class SplitForm implements Serializable {

	private static final long serialVersionUID = -3475336906273517756L;

	private MultipartFile file;

	private SplitMode splitMode;

	/**
	 * @return the file
	 */
	public MultipartFile getFile() {
		return this.file;
	}

	/**
	 * @param file the file to set
	 */
	public void setFile(final MultipartFile file) {
		this.file = file;
	}

	/**
	 * @return the splitMode
	 */
	public SplitMode getSplitMode() {
		return this.splitMode;
	}

	/**
	 * @param splitMode the splitMode to set
	 */
	public void setSplitMode(final SplitMode splitMode) {
		this.splitMode = splitMode;
	}

}
