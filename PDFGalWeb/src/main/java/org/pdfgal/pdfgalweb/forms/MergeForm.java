package org.pdfgal.pdfgalweb.forms;

import java.io.Serializable;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

public class MergeForm implements Serializable {

	private static final long serialVersionUID = 4100877460139267074L;

	private List<MultipartFile> files;

	private String fileName;

	public List<MultipartFile> getFiles() {
		return this.files;
	}

	public void setFiles(final List<MultipartFile> files) {
		this.files = files;
	}

	public String getFileName() {
		return this.fileName;
	}

	public void setFileName(final String fileName) {
		this.fileName = fileName;
	}

}
