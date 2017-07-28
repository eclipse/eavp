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

	public static final int NONE = 1; // No flag is set.
	public static final int ZERO_X = 2; // Only values x >= 0 are displayed.
	public static final int ZERO_Y = 4; // Only values y >= 0 are displayed.
	public static final int RESTRICT_ZOOM = 8; // It's not possible to zoom outside of the min/max values.
	public static final int X_ZOOM_ONLY = 16; // When doing a user selection, only zoom x.
	public static final int Y_ZOOM_ONLY = 32; // When doing a user selection, only zoom y.
	public static final int FORCE_ZERO_MIN_Y = 64; // Instead of starting with the lowest Y values, 0 is set.
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

		return isFlagSet(ZERO_X);
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

		return isFlagSet(ZERO_Y);
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

		return isFlagSet(RESTRICT_ZOOM);
	}

	/**
	 * Set true if zooming shall not exceed the min/max values.
	 * 
	 * @param restrictZoom
	 */
	public void setRestrictZoom(boolean restrictZoom) {

		flagSelection(restrictZoom, RESTRICT_ZOOM);
	}

	public boolean isXZoomOnly() {

		return isFlagSet(X_ZOOM_ONLY);
	}

	/**
	 * Set true if only the x-Axis shall be zoomed.
	 * 
	 * @param xZoomOnly
	 */
	public void setXZoomOnly(boolean xZoomOnly) {

		flagSelection(xZoomOnly, X_ZOOM_ONLY);
	}

	public boolean isYZoomOnly() {

		return isFlagSet(Y_ZOOM_ONLY);
	}

	/**
	 * Set true if only the y-Axis shall be zoomed.
	 * 
	 * @param yZoomOnly
	 */
	public void setYZoomOnly(boolean yZoomOnly) {

		flagSelection(yZoomOnly, Y_ZOOM_ONLY);
	}

	public boolean isForceZeroMinY() {

		return isFlagSet(FORCE_ZERO_MIN_Y);
	}

	/**
	 * Set true if only lowest y value starts at 0 in any case.
	 * 
	 * @param forceZeroMinY
	 */
	public void setForceZeroMinY(boolean forceZeroMinY) {

		flagSelection(forceZeroMinY, FORCE_ZERO_MIN_Y);
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

	private boolean isFlagSet(int constant) {

		return (selection & constant) == constant;
	}

	private void flagSelection(boolean flag, int constant) {

		if(flag) {
			selection |= constant;
		} else {
			selection &= Integer.MAX_VALUE - constant;
		}
	}
}
