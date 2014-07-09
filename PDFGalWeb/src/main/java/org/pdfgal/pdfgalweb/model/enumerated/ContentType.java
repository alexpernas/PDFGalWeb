package org.pdfgal.pdfgalweb.model.enumerated;

/**
 * Content type of files.
 * 
 * @author Alex
 * 
 */
public enum ContentType {

	PDF("application/pdf"), ZIP("application/zip");

	private String value;

	private ContentType(final String value) {
		this.value = value;
	}

	public String getValue() {
		return this.value;
	}

	public void setValue(final String value) {
		this.value = value;
	}

}
