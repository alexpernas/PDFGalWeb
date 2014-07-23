package org.pdfgal.pdfgalweb.forms;

import java.awt.Color;
import java.io.Serializable;

import org.springframework.web.multipart.MultipartFile;

public class WatermarkForm implements Serializable {

	private static final long serialVersionUID = -394294045916014550L;

	private MultipartFile file;

	private String text;

	private Color color;

	private Float Alpha;

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
	 * @return the text
	 */
	public String getText() {
		return this.text;
	}

	/**
	 * @param text the text to set
	 */
	public void setText(final String text) {
		this.text = text;
	}

	/**
	 * @return the color
	 */
	public Color getColor() {
		return this.color;
	}

	/**
	 * @param color the color to set
	 */
	public void setColor(final Color color) {
		this.color = color;
	}

	/**
	 * @return the alpha
	 */
	public Float getAlpha() {
		return this.Alpha;
	}

	/**
	 * @param alpha the alpha to set
	 */
	public void setAlpha(final Float alpha) {
		this.Alpha = alpha;
	}

}
