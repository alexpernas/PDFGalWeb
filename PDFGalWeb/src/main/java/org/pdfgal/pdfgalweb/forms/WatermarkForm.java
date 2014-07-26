package org.pdfgal.pdfgalweb.forms;

import java.io.Serializable;

import org.pdfgal.pdfgal.model.enumerated.WatermarkPosition;
import org.pdfgal.pdfgalweb.model.enumerated.CustomColor;
import org.springframework.web.multipart.MultipartFile;

public class WatermarkForm implements Serializable {

	private static final long serialVersionUID = -394294045916014550L;

	private MultipartFile file;

	private String text;

	private CustomColor customColor;

	private Float Alpha;

	private WatermarkPosition watermarkPosition;

	private String pages;

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
	 * @return the text
	 */
	public String getText() {
		return this.text;
	}

	/**
	 * @param text
	 *            the text to set
	 */
	public void setText(final String text) {
		this.text = text;
	}

	public CustomColor getCustomColor() {
		return this.customColor;
	}

	public void setCustomColor(final CustomColor customColor) {
		this.customColor = customColor;
	}

	/**
	 * @return the alpha
	 */
	public Float getAlpha() {
		return this.Alpha;
	}

	/**
	 * @param alpha
	 *            the alpha to set
	 */
	public void setAlpha(final Float alpha) {
		this.Alpha = alpha;
	}

	/**
	 * @return the watermarkPosition
	 */
	public WatermarkPosition getWatermarkPosition() {
		return this.watermarkPosition;
	}

	/**
	 * @param watermarkPosition
	 *            the watermarkPosition to set
	 */
	public void setWatermarkPosition(final WatermarkPosition watermarkPosition) {
		this.watermarkPosition = watermarkPosition;
	}

	/**
	 * @return the pages
	 */
	public String getPages() {
		return this.pages;
	}

	/**
	 * @param pages
	 *            the pages to set
	 */
	public void setPages(final String pages) {
		this.pages = pages;
	}

}