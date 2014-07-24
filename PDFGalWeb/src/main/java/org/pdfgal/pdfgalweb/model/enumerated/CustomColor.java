package org.pdfgal.pdfgalweb.model.enumerated;

import java.awt.Color;

public enum CustomColor {
	BLACK(Color.BLACK), DARK_GRAY(Color.DARK_GRAY), GRAY(Color.GRAY), LIGHT_GRAY(Color.LIGHT_GRAY), WHITE(
			Color.WHITE), RED(Color.RED), PINK(Color.PINK), ORANGE(Color.ORANGE), YELLOW(
			Color.YELLOW), GREEN(Color.GREEN), MAGENTA(Color.MAGENTA), CYAN(Color.CYAN), BLUE(
			Color.BLUE);

	private Color color;

	/**
	 * @param color
	 */
	private CustomColor(final Color color) {
		this.color = color;
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

}