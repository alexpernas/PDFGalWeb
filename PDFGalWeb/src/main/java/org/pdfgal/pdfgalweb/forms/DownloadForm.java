package org.pdfgal.pdfgalweb.forms;

import java.io.Serializable;

public class DownloadForm implements Serializable {

	private static final long serialVersionUID = -5865480822922158864L;

	private String uri;

	private String name;

	public DownloadForm() {
		super();
	}

	public DownloadForm(final String uri, final String name) {
		super();
		this.uri = uri;
		this.name = name;
	}

	public String getUri() {
		return this.uri;
	}

	public void setUri(final String uri) {
		this.uri = uri;
	}

	public String getName() {
		return this.name;
	}

	public void setName(final String name) {
		this.name = name;
	}

}
