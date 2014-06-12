package org.pdfgal.pdfgalweb.forms;

import java.io.Serializable;

import org.springframework.web.multipart.MultipartFile;

public class UnProtectForm implements Serializable {

	private static final long serialVersionUID = 4797712968597841453L;

	private MultipartFile file;

	private String password;

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
	 * @return the password
	 */
	public String getPassword() {
		return this.password;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(final String password) {
		this.password = password;
	}

}
