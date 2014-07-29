/*
 * PDFGalWeb
 * Copyright (c) 2014, Alejandro Pernas Pan, All rights reserved.
 * 
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 3.0 of the License, or (at your option) any later version.

 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 * 
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library.
 */

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