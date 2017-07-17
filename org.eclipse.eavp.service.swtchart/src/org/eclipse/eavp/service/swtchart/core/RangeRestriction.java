/*******************************************************************************
 * Copyright (c) 2017 Lablicate GmbH.
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

public class RangeRestriction {

	public static final int NONE = 0; // No flag is set.
	public static final int ZERO_X = 1; // Only values x >= 0 are displayed.
	public static final int ZERO_Y = 2; // Only values y >= 0 are displayed.
	public static final int RESTRICT_ZOOM = 4; // It's not possible to zoom outside of the min/max values.
	//
	private double factorExtendMinX;
	private double factorExtendMaxX;
	private double factorExtendMinY;
	private double factorExtendMaxY;
	//
	private int selection;

	public RangeRestriction() {
		this(NONE);
	}

	public RangeRestriction(int selection) {
		this.selection = selection;
		factorExtendMinX = 0.0d;
		factorExtendMaxX = 0.0d;
		factorExtendMinY = 0.0d;
		factorExtendMaxY = 0.0d;
	}

	public boolean isZeroX() {

		return (selection & ZERO_X) == ZERO_X;
	}

	/**
	 * 0 is the lowest x value.
	 * Otherwise, the lowest x values of the series is used.
	 * 
	 * @param zeroX
	 */
	public void setZeroX(boolean zeroX) {

		flagSelection(zeroX, ZERO_X);
	}

	public boolean isZeroY() {

		return (selection & ZERO_Y) == ZERO_Y;
	}

	/**
	 * 0 is the lowest y value.
	 * Otherwise, the lowest y values of the series is used.
	 * 
	 * @param zeroY
	 */
	public void setZeroY(boolean zeroY) {

		flagSelection(zeroY, ZERO_Y);
	}

	public boolean isRestrictZoom() {

		return (selection & RESTRICT_ZOOM) == RESTRICT_ZOOM;
	}

	/**
	 * Set true if zooming shall not exceed the min/max values.
	 * 
	 * @param restrictZoom
	 */
	public void setRestrictZoom(boolean restrictZoom) {

		flagSelection(restrictZoom, RESTRICT_ZOOM);
	}

	private void flagSelection(boolean flag, int constant) {

		if(flag) {
			selection |= constant;
		} else {
			selection &= Integer.MAX_VALUE - constant;
		}
	}

	public double getFactorExtendMinX() {

		return factorExtendMinX;
	}

	public void setFactorExtendMinX(double factorExtendMinX) {

		this.factorExtendMinX = factorExtendMinX;
	}

	public double getFactorExtendMaxX() {

		return factorExtendMaxX;
	}

	public void setFactorExtendMaxX(double factorExtendMaxX) {

		this.factorExtendMaxX = factorExtendMaxX;
	}

	public double getFactorExtendMinY() {

		return factorExtendMinY;
	}

	public void setFactorExtendMinY(double factorExtendMinY) {

		this.factorExtendMinY = factorExtendMinY;
	}

	public double getFactorExtendMaxY() {

		return factorExtendMaxY;
	}

	public void setFactorExtendMaxY(double factorExtendMaxY) {

		this.factorExtendMaxY = factorExtendMaxY;
	}

	public void setFactorExtend(double factorExtend) {

		this.factorExtendMinX = factorExtend;
		this.factorExtendMaxX = factorExtend;
		this.factorExtendMinY = factorExtend;
		this.factorExtendMaxY = factorExtend;
	}
}
