/*******************************************************************************
 * Copyright (c) 2017, 2018 Lablicate GmbH.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 * Dr. Philip Wenig - initial API and implementation
 *******************************************************************************/
package org.eclipse.eavp.service.swtchart.core;

public class UserSelection {

	private boolean active;
	private int startX;
	private int startY;
	private int stopX;
	private int stopY;

	public UserSelection() {
		reset();
	}

	public boolean isActive() {

		return active;
	}

	public void setActive(boolean active) {

		this.active = active;
	}

	public int getStartX() {

		return startX;
	}

	public void setStartX(int startX) {

		this.startX = startX;
	}

	public int getStartY() {

		return startY;
	}

	public void setStartY(int startY) {

		this.startY = startY;
	}

	public int getStopX() {

		return stopX;
	}

	public void setStopX(int stopX) {

		this.stopX = stopX;
	}

	public int getStopY() {

		return stopY;
	}

	public void setStopY(int stopY) {

		this.stopY = stopY;
	}

	/**
	 * Sets the start coordinate, but don't activates the selection.
	 * An activation could lead to unwanted drawings of the rectangle.
	 * 
	 * @param startX
	 * @param startY
	 */
	public void setStartCoordinate(int startX, int startY) {

		active = false;
		this.startX = startX;
		this.startY = startY;
	}

	/**
	 * Sets the stop coordinate and activates the selection.
	 * 
	 * @param stopX
	 * @param stopY
	 */
	public void setStopCoordinate(int stopX, int stopY) {

		active = true;
		this.stopX = stopX;
		this.stopY = stopY;
	}

	public void reset() {

		active = false;
		startX = 0;
		stopX = 0;
		startY = 0;
		stopY = 0;
	}
}
