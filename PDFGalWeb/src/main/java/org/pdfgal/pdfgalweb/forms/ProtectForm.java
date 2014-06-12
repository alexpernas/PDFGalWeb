package org.pdfgal.pdfgalweb.forms;

import java.io.Serializable;

import org.springframework.web.multipart.MultipartFile;

public class ProtectForm implements Serializable {

	private static final long serialVersionUID = -4937588904222693545L;

	private MultipartFile file;

	private String password;

	private String repeatedPassword;

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

	/**
	 * @return the repeatedPassword
	 */
	public String getRepeatedPassword() {
		return this.repeatedPassword;
	}

	/**
	 * @param repeatedPassword the repeatedPassword to set
	 */
	public void setRepeatedPassword(final String repeatedPassword) {
		this.repeatedPassword = repeatedPassword;
	}

}
