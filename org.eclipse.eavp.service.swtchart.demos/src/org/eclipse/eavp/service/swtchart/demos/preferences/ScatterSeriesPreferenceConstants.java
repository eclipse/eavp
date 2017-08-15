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

import java.util.Locale;

import org.eclipse.swt.SWT;
import org.swtchart.IAxis.Position;
import org.swtchart.ILineSeries.PlotSymbolType;
import org.swtchart.LineStyle;

public class ScatterSeriesPreferenceConstants {

	public static final String POSTFIX = "ScatterSeries";
	//
	public static final String P_ENABLE_RANGE_SELECTOR = "enableRangeSelector" + POSTFIX;
	public static final boolean DEF_ENABLE_RANGE_SELECTOR = true;
	public static final String P_COLOR_HINT_RANGE_SELECTOR = "colorHintRangeSelector" + POSTFIX;
	public static final String DEF_COLOR_HINT_RANGE_SELECTOR = "255,0,0";
	public static final String P_RANGE_SELECTOR_DEFAULT_AXIS_X = "rangeSelectorDefaultAxisX" + POSTFIX;
	public static final int DEF_RANGE_SELECTOR_DEFAULT_AXIS_X = 0;
	public static final String P_RANGE_SELECTOR_DEFAULT_AXIS_Y = "rangeSelectorDefaultAxisY" + POSTFIX;
	public static final int DEF_RANGE_SELECTOR_DEFAULT_AXIS_Y = 0;
	//
	public static final String P_VERTICAL_SLIDER_VISIBLE = "verticalSliderVisible" + POSTFIX;
	public static final boolean DEF_VERTICAL_SLIDER_VISIBLE = false;
	public static final String P_HORIZONTAL_SLIDER_VISIBLE = "HorizontalSliderVisible" + POSTFIX;
	public static final boolean DEF_HORIZONTALSLIDER_VISIBLE = true;
	//
	public static final String P_TITLE = "title" + POSTFIX;
	public static final String DEF_TITLE = "Scatter Series";
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
	public static final String P_BACKGROUND_CHART = "backgroundChart" + POSTFIX;
	public static final String DEF_BACKGROUND_CHART = "255,255,255";
	public static final String P_BACKGROUND_PLOT_AREA = "backgroundPlotArea" + POSTFIX;
	public static final String DEF_BACKGROUND_PLOT_AREA = "255,255,255";
	//
	public static final String P_ENABLE_COMPRESS = "enableCompress" + POSTFIX;
	public static final boolean DEF_ENABLE_COMPRESS = true;
	public static final String P_ZERO_Y = "zeroY" + POSTFIX;
	public static final boolean DEF_ZERO_Y = false;
	public static final String P_ZERO_X = "zeroX" + POSTFIX;
	public static final boolean DEF_ZERO_X = false;
	public static final String P_RESTRICT_ZOOM = "restrictZoom" + POSTFIX;
	public static final boolean DEF_RESTRICT_ZOOM = false;
	public static final String P_X_ZOOM_ONLY = "xZoomOnly" + POSTFIX;
	public static final boolean DEF_X_ZOOM_ONLY = false;
	public static final String P_Y_ZOOM_ONLY = "yZoomOnly" + POSTFIX;
	public static final boolean DEF_Y_ZOOM_ONLY = false;
	public static final String P_FORCE_ZERO_MIN_Y = "forceZeroMinY" + POSTFIX;
	public static final boolean DEF_FORCE_ZERO_MIN_Y = false;
	public static final String P_FACTOR_EXTEND_MIN_X = "factorExtendMinX" + POSTFIX;
	public static final double DEF_FACTOR_EXTEND_MIN_X = 0.1d;
	public static final String P_FACTOR_EXTEND_MAX_X = "factorExtendMaxX" + POSTFIX;
	public static final double DEF_FACTOR_EXTEND_MAX_X = 0.1d;
	public static final String P_FACTOR_EXTEND_MIN_Y = "factorExtendMinY" + POSTFIX;
	public static final double DEF_FACTOR_EXTEND_MIN_Y = 0.1d;
	public static final String P_FACTOR_EXTEND_MAX_Y = "factorExtendMaxY" + POSTFIX;
	public static final double DEF_FACTOR_EXTEND_MAX_Y = 0.1d;
	//
	public static final String P_SHOW_POSITION_MARKER = "showPositionMarker" + POSTFIX;
	public static final boolean DEF_SHOW_POSITION_MARKER = true;
	public static final String P_COLOR_POSITION_MARKER = "colorPositionMarker" + POSTFIX;
	public static final String DEF_COLOR_POSITION_MARKER = "100,100,100";
	public static final String P_SHOW_CENTER_MARKER = "showCenterMarker" + POSTFIX;
	public static final boolean DEF_SHOW_CENTER_MARKER = false;
	public static final String P_COLOR_CENTER_MARKER = "colorCenterMarker" + POSTFIX;
	public static final String DEF_COLOR_CENTER_MARKER = "100,100,100";
	public static final String P_SHOW_POSITION_LEGEND = "showPositionLegend" + POSTFIX;
	public static final boolean DEF_SHOW_POSITION_LEGEND = true;
	public static final String P_COLOR_POSITION_LEGEND = "colorPositionLegend" + POSTFIX;
	public static final String DEF_COLOR_POSITION_LEGEND = "100,100,100";
	//
	public static final String P_CREATE_MENU = "createMenu" + POSTFIX;
	public static final boolean DEF_CREATE_MENU = true;
	//
	public static final String P_PRIMARY_X_AXIS_TITLE = "primaryXAxisTitle" + POSTFIX;
	public static final String DEF_PRIMARY_X_AXIS_TITLE = "Param 1";
	public static final String P_PRIMARY_X_AXIS_DESCRIPTION = "primaryXAxisDescription" + POSTFIX;
	public static final String DEF_PRIMARY_X_AXIS_DESCRIPTION = "Parameter 1";
	public static final String P_PRIMARY_X_AXIS_DECIMAL_FORMAT_PATTERN = "primaryXAxisDecimalFormatPattern" + POSTFIX;
	public static final String DEF_PRIMARY_X_AXIS_DECIMAL_FORMAT_PATTERN = "0";
	public static final String P_PRIMARY_X_AXIS_DECIMAL_FORMAT_LOCALE = "primaryXAxisDecimalFormatLocale" + POSTFIX;
	public static final String DEF_PRIMARY_X_AXIS_DECIMAL_FORMAT_LOCALE = Locale.ENGLISH.getLanguage();
	public static final String P_PRIMARY_X_AXIS_COLOR = "primaryXAxisColor" + POSTFIX;
	public static final String DEF_PRIMARY_X_AXIS_COLOR = "0,0,0";
	public static final String P_PRIMARY_X_AXIS_POSITION = "primaryXAxisPosition" + POSTFIX;
	public static final String DEF_PRIMARY_X_AXIS_POSITION = Position.Primary.toString();
	public static final String P_PRIMARY_X_AXIS_VISIBLE = "primaryXAxisVisible" + POSTFIX;
	public static final boolean DEF_PRIMARY_X_AXIS_VISIBLE = true;
	public static final String P_PRIMARY_X_AXIS_GRID_LINE_STYLE = "primaryXAxisGridLineStyle" + POSTFIX;
	public static final String DEF_PRIMARY_X_AXIS_GRID_LINE_STYLE = LineStyle.DASH.toString();
	public static final String P_PRIMARY_X_AXIS_ENABLE_LOG_SCALE = "primaryXAxisEnableLogScale" + POSTFIX;
	public static final boolean DEF_PRIMARY_X_AXIS_ENABLE_LOG_SCALE = false;
	//
	public static final String P_PRIMARY_Y_AXIS_TITLE = "primaryYAxisTitle" + POSTFIX;
	public static final String DEF_PRIMARY_Y_AXIS_TITLE = "Param 2";
	public static final String P_PRIMARY_Y_AXIS_DESCRIPTION = "primaryYAxisDescription" + POSTFIX;
	public static final String DEF_PRIMARY_Y_AXIS_DESCRIPTION = "Parameter 2";
	public static final String P_PRIMARY_Y_AXIS_DECIMAL_FORMAT_PATTERN = "primaryYAxisDecimalFormatPattern" + POSTFIX;
	public static final String DEF_PRIMARY_Y_AXIS_DECIMAL_FORMAT_PATTERN = "0.000";
	public static final String P_PRIMARY_Y_AXIS_DECIMAL_FORMAT_LOCALE = "primaryYAxisDecimalFormatLocale" + POSTFIX;
	public static final String DEF_PRIMARY_Y_AXIS_DECIMAL_FORMAT_LOCALE = Locale.ENGLISH.getLanguage();
	public static final String P_PRIMARY_Y_AXIS_COLOR = "primaryYAxisColor" + POSTFIX;
	public static final String DEF_PRIMARY_Y_AXIS_COLOR = "0,0,0";
	public static final String P_PRIMARY_Y_AXIS_POSITION = "primaryYAxisPosition" + POSTFIX;
	public static final String DEF_PRIMARY_Y_AXIS_POSITION = Position.Primary.toString();
	public static final String P_PRIMARY_Y_AXIS_VISIBLE = "primaryYAxisVisible" + POSTFIX;
	public static final boolean DEF_PRIMARY_Y_AXIS_VISIBLE = true;
	public static final String P_PRIMARY_Y_AXIS_GRID_LINE_STYLE = "primaryYAxisGridLineStyle" + POSTFIX;
	public static final String DEF_PRIMARY_Y_AXIS_GRID_LINE_STYLE = LineStyle.DASH.toString();
	public static final String P_PRIMARY_Y_AXIS_ENABLE_LOG_SCALE = "primaryYAxisEnableLogScale" + POSTFIX;
	public static final boolean DEF_PRIMARY_Y_AXIS_ENABLE_LOG_SCALE = false;
	//
	public static final String P_DESCRIPTION_SERIES_1 = "descriptionSeries1" + POSTFIX;
	public static final String DEF_DESCRIPTION_SERIES_1 = "Measurement 1";
	public static final String P_SYMBOL_COLOR_SERIES_1 = "symbolColorSeries1" + POSTFIX;
	public static final String DEF_SYMBOL_COLOR_SERIES_1 = "255,0,0";
	public static final String P_SYMBOL_SIZE_SERIES_1 = "symbolSizeSeries1" + POSTFIX;
	public static final int DEF_SYMBOL_SIZE_SERIES_1 = 5;
	public static final String P_SYMBOL_TYPE_SERIES_1 = "symbolTypeSeries1" + POSTFIX;
	public static final String DEF_SYMBOL_TYPE_SERIES_1 = PlotSymbolType.CIRCLE.toString();
	public static final String P_VISIBLE_SERIES_1 = "visibleSeries1" + POSTFIX;
	public static final boolean DEF_VISIBLE_SERIES_1 = true;
	public static final String P_VISIBLE_IN_LEGEND_SERIES_1 = "visibleInLegendSeries1" + POSTFIX;
	public static final boolean DEF_VISIBLE_IN_LEGEND_SERIES_1 = true;
}
