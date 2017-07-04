/*******************************************************************************
 * Copyright (c) 2017 Lablicate GmbH.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 * Philip Wenig - initial API and implementation
 *******************************************************************************/
package org.eclipse.eavp.service.swtchart.demos.preferences;

import org.eclipse.swt.SWT;

public class LineSeriesPreferenceConstants {

	public static final String POSTFIX = "LineSeries";
	//
	public static final String P_ENABLE_RANGE_UI = "enableRangeUI" + POSTFIX;
	public static final boolean DEF_ENABLE_RANGE_UI = true;
	//
	public static final String P_VERTICAL_SLIDER_VISIBLE = "verticalSliderVisible" + POSTFIX;
	public static final boolean DEF_VERTICAL_SLIDER_VISIBLE = false;
	public static final String P_HORIZONTAL_SLIDER_VISIBLE = "HorizontalSliderVisible" + POSTFIX;
	public static final boolean DEF_HORIZONTALSLIDER_VISIBLE = true;
	//
	public static final String P_TITLE = "title" + POSTFIX;
	public static final String DEF_TITLE = "Line Series";
	public static final String P_TITLE_VISIBLE = "titleVisible" + POSTFIX;
	public static final boolean DEF_TITLE_VISIBLE = false;
	public static final String P_TITLE_COLOR = "titleColor" + POSTFIX;
	public static final String DEF_TITLE_COLOR = "0,0,0";
	//
	public static final String P_LEGEND_POSITION = "legendPosition" + POSTFIX;
	public static final int DEF_LEGEND_POSITION = SWT.RIGHT;
	public static final String P_LEGEND_VISIBLE = "legendVisible" + POSTFIX;
	public static final boolean DEF_LEGEND_VISIBLE = false;
	//
	public static final String P_ORIENTATION = "orientation" + POSTFIX;
	public static final int DEF_ORIENTATION = SWT.HORIZONTAL;
	public static final String P_BACKGROUND = "background" + POSTFIX;
	public static final String DEF_BACKGROUND = "255,255,255";
	public static final String P_BACKGROUND_IN_PLOT_AREA = "backgroundInPlotArea" + POSTFIX;
	public static final String DEF_BACKGROUND_IN_PLOT_AREA = "255,255,255";
	//
	public static final String P_ENABLE_COMPRESS = "enableCompress" + POSTFIX;
	public static final boolean DEF_ENABLE_COMPRESS = true;
	public static final String P_USE_ZERO_Y = "useZeroY" + POSTFIX;
	public static final boolean DEF_USE_ZERO_Y = true;
	public static final String P_USE_ZERO_X = "useZeroX" + POSTFIX;
	public static final boolean DEF_USE_ZERO_X = true;
	public static final String P_USE_RANGE_RESTRICTION = "useRangeRestriction" + POSTFIX;
	public static final boolean DEF_USE_RANGE_RESTRICTION = true;
	public static final String P_FACTOR_EXTEND_MIN_X = "factorExtendMinX" + POSTFIX;
	public static final double DEF_FACTOR_EXTEND_MIN_X = 0.0d;
	public static final String P_FACTOR_EXTEND_MAX_X = "factorExtendMaxX" + POSTFIX;
	public static final double DEF_FACTOR_EXTEND_MAX_X = 0.0d;
	public static final String P_FACTOR_EXTEND_MIN_Y = "factorExtendMinY" + POSTFIX;
	public static final double DEF_FACTOR_EXTEND_MIN_Y = 0.0d;
	public static final String P_FACTOR_EXTEND_MAX_Y = "factorExtendMaxY" + POSTFIX;
	public static final double DEF_FACTOR_EXTEND_MAX_Y = 0.0d;
	//
	public static final String P_SHOW_POSITION_MARKER = "showPositionMarker" + POSTFIX;
	public static final boolean DEF_SHOW_POSITION_MARKER = true;
	public static final String P_SHOW_CENTER_MARKER = "showCenterMarker" + POSTFIX;
	public static final boolean DEF_SHOW_CENTER_MARKER = true;
	public static final String P_SHOW_POSITION_LEGEND = "showPositionLegend" + POSTFIX;
	public static final boolean DEF_SHOW_POSITION_LEGEND = true;
	//
	public static final String P_CREATE_MENU = "createMenu" + POSTFIX;
	public static final boolean DEF_CREATE_MENU = true;
}
