package org.pdfgal.pdfgalweb.forms;

import java.io.Serializable;

public class DownloadForm implements Serializable {

	private static final long serialVersionUID = -5865480822922158864L;

	private String uri;

	private String name;

	private String content;

	public DownloadForm() {
		super();
	}

	public DownloadForm(final String uri, final String name,
			final String content) {
		super();
		this.uri = uri;
		this.name = name;
		this.content = content;
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

	public String getContent() {
		return this.content;
	}

	public void setContent(final String content) {
		this.content = content;
	}

}
