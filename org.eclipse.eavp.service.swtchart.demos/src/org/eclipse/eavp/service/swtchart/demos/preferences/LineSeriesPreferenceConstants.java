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
	//
	public static final String P_PRIMARY_X_AXIS_TITLE = "primaryXAxisTitle";
	public static final String DEF_PRIMARY_X_AXIS_TITLE = "Milliseconds";
	public static final String P_PRIMARY_X_AXIS_DESCRIPTION = "primaryXAxisDescription";
	public static final String DEF_PRIMARY_X_AXIS_DESCRIPTION = "Retention Time (milliseconds)";
	public static final String P_PRIMARY_X_AXIS_DECIMAL_FORMAT_PATTERN = "primaryXAxisDecimalFormatPattern";
	public static final String DEF_PRIMARY_X_AXIS_DECIMAL_FORMAT_PATTERN = "0.0##";
	public static final String P_PRIMARY_X_AXIS_DECIMAL_FORMAT_LOCALE = "primaryXAxisDecimalFormatLocale";
	public static final String DEF_PRIMARY_X_AXIS_DECIMAL_FORMAT_LOCALE = Locale.ENGLISH.getLanguage();
	public static final String P_PRIMARY_X_AXIS_COLOR = "primaryXAxisColor";
	public static final String DEF_PRIMARY_X_AXIS_COLOR = "0,0,0";
	public static final String P_PRIMARY_X_AXIS_POSITION = "primaryXAxisPosition";
	public static final String DEF_PRIMARY_X_AXIS_POSITION = Position.Secondary.toString();
	public static final String P_PRIMARY_X_AXIS_VISIBLE = "primaryXAxisVisible";
	public static final boolean DEF_PRIMARY_X_AXIS_VISIBLE = false;
	public static final String P_PRIMARY_X_AXIS_GRID_LINE_STYLE = "primaryXAxisGridLineStyle";
	public static final String DEF_PRIMARY_X_AXIS_GRID_LINE_STYLE = LineStyle.NONE.toString();
	//
	public static final String P_PRIMARY_Y_AXIS_TITLE = "primaryYAxisTitle";
	public static final String DEF_PRIMARY_Y_AXIS_TITLE = "Intensity";
	public static final String P_PRIMARY_Y_AXIS_DESCRIPTION = "primaryYAxisDescription";
	public static final String DEF_PRIMARY_Y_AXIS_DESCRIPTION = "Intensity";
	public static final String P_PRIMARY_Y_AXIS_DECIMAL_FORMAT_PATTERN = "primaryYAxisDecimalFormatPattern";
	public static final String DEF_PRIMARY_Y_AXIS_DECIMAL_FORMAT_PATTERN = "0.0#E0";
	public static final String P_PRIMARY_Y_AXIS_DECIMAL_FORMAT_LOCALE = "primaryYAxisDecimalFormatLocale";
	public static final String DEF_PRIMARY_Y_AXIS_DECIMAL_FORMAT_LOCALE = Locale.ENGLISH.getLanguage();
	public static final String P_PRIMARY_Y_AXIS_COLOR = "primaryYAxisColor";
	public static final String DEF_PRIMARY_Y_AXIS_COLOR = "0,0,0";
	public static final String P_PRIMARY_Y_AXIS_POSITION = "primaryYAxisPosition";
	public static final String DEF_PRIMARY_Y_AXIS_POSITION = Position.Primary.toString();
	public static final String P_PRIMARY_Y_AXIS_VISIBLE = "primaryYAxisVisible";
	public static final boolean DEF_PRIMARY_Y_AXIS_VISIBLE = true;
	public static final String P_PRIMARY_Y_AXIS_GRID_LINE_STYLE = "primaryYAxisGridLineStyle";
	public static final String DEF_PRIMARY_Y_AXIS_GRID_LINE_STYLE = LineStyle.NONE.toString();
	//
	public static final String P_SECONDARY_X_AXIS_TITLE = "secondaryXAxisTitle";
	public static final String DEF_SECONDARY_X_AXIS_TITLE = "Min";
	public static final String P_SECONDARY_X_AXIS_DESCRIPTION = "secondaryXAxisDescription";
	public static final String DEF_SECONDARY_X_AXIS_DESCRIPTION = "Minutes";
	public static final String P_SECONDARY_X_AXIS_DECIMAL_FORMAT_PATTERN = "secondaryXAxisDecimalFormatPattern";
	public static final String DEF_SECONDARY_X_AXIS_DECIMAL_FORMAT_PATTERN = "0.0##";
	public static final String P_SECONDARY_X_AXIS_DECIMAL_FORMAT_LOCALE = "secondaryXAxisDecimalFormatLocale";
	public static final String DEF_SECONDARY_X_AXIS_DECIMAL_FORMAT_LOCALE = Locale.ENGLISH.getLanguage();
	public static final String P_SECONDARY_X_AXIS_COLOR = "secondaryXAxisColor";
	public static final String DEF_SECONDARY_X_AXIS_COLOR = "0,0,0";
	public static final String P_SECONDARY_X_AXIS_POSITION = "secondaryXAxisPosition";
	public static final String DEF_SECONDARY_X_AXIS_POSITION = Position.Primary.toString();
	public static final String P_SECONDARY_X_AXIS_VISIBLE = "secondaryXAxisVisible";
	public static final boolean DEF_SECONDARY_X_AXIS_VISIBLE = true;
	public static final String P_SECONDARY_X_AXIS_GRID_LINE_STYLE = "secondaryXAxisGridLineStyle";
	public static final String DEF_SECONDARY_X_AXIS_GRID_LINE_STYLE = LineStyle.DASH.toString();
	//
	public static final String P_SECONDARY_Y_AXIS_TITLE = "secondaryYAxisTitle";
	public static final String DEF_SECONDARY_Y_AXIS_TITLE = "Int [%]";
	public static final String P_SECONDARY_Y_AXIS_DESCRIPTION = "secondaryYAxisDescription";
	public static final String DEF_SECONDARY_Y_AXIS_DESCRIPTION = "Relative Intensity [%]";
	public static final String P_SECONDARY_Y_AXIS_DECIMAL_FORMAT_PATTERN = "secondaryYAxisDecimalFormatPattern";
	public static final String DEF_SECONDARY_Y_AXIS_DECIMAL_FORMAT_PATTERN = "0.0#E0";
	public static final String P_SECONDARY_Y_AXIS_DECIMAL_FORMAT_LOCALE = "secondaryYAxisDecimalFormatLocale";
	public static final String DEF_SECONDARY_Y_AXIS_DECIMAL_FORMAT_LOCALE = Locale.ENGLISH.getLanguage();
	public static final String P_SECONDARY_Y_AXIS_COLOR = "secondaryYAxisColor";
	public static final String DEF_SECONDARY_Y_AXIS_COLOR = "0,0,0";
	public static final String P_SECONDARY_Y_AXIS_POSITION = "secondaryYAxisPosition";
	public static final String DEF_SECONDARY_Y_AXIS_POSITION = Position.Secondary.toString();
	public static final String P_SECONDARY_Y_AXIS_VISIBLE = "secondaryYAxisVisible";
	public static final boolean DEF_SECONDARY_Y_AXIS_VISIBLE = true;
	public static final String P_SECONDARY_Y_AXIS_GRID_LINE_STYLE = "secondaryYAxisGridLineStyle";
	public static final String DEF_SECONDARY_Y_AXIS_GRID_LINE_STYLE = LineStyle.DASH.toString();
	//
	public static final String P_ANTIALIAS_SERIES_1 = "antialiasSeries1";
	public static final int DEF_ANTIALIAS_SERIES_1 = SWT.DEFAULT;
	public static final String P_DESCRIPTION_SERIES_1 = "descriptionSeries1";
	public static final String DEF_DESCRIPTION_SERIES_1 = "Measurement 1";
	public static final String P_ENABLE_AREA_SERIES_1 = "enableAreaSeries1";
	public static final boolean DEF_ENABLE_AREA_SERIES_1 = true;
	public static final String P_ENABLE_STACK_SERIES_1 = "enableStackSeries1";
	public static final boolean DEF_ENABLE_STACK_SERIES_1 = false;
	public static final String P_ENABLE_STEP_SERIES_1 = "enableStepSeries1";
	public static final boolean DEF_ENABLE_STEP_SERIES_1 = false;
	public static final String P_LINE_COLOR_SERIES_1 = "lineColorSeries1";
	public static final String DEF_LINE_COLOR_SERIES_1 = "255,0,0";
	public static final String P_LINE_STYLE_SERIES_1 = "lineStyleSeries1";
	public static final String DEF_LINE_STYLE_SERIES_1 = LineStyle.SOLID.toString();
	public static final String P_LINE_WIDTH_SERIES_1 = "lineWidthSeries1";
	public static final int DEF_LINE_WIDTH_SERIES_1 = 1;
	public static final String P_SYMBOL_COLOR_SERIES_1 = "symbolColorSeries1";
	public static final String DEF_SYMBOL_COLOR_SERIES_1 = "0,0,0";
	public static final String P_SYMBOL_SIZE_SERIES_1 = "symbolSizeSeries1";
	public static final int DEF_SYMBOL_SIZE_SERIES_1 = 8;
	public static final String P_SYMBOL_TYPE_SERIES_1 = "symbolTypeSeries1";
	public static final String DEF_SYMBOL_TYPE_SERIES_1 = PlotSymbolType.NONE.toString();
	public static final String P_VISIBLE_SERIES_1 = "visibleSeries1";
	public static final boolean DEF_VISIBLE_SERIES_1 = true;
	public static final String P_VISIBLE_IN_LEGEND_SERIES_1 = "visibleInLegendSeries1";
	public static final boolean DEF_VISIBLE_IN_LEGEND_SERIES_1 = true;
	//
	public static final String P_ANTIALIAS_SERIES_2 = "antialiasSeries2";
	public static final int DEF_ANTIALIAS_SERIES_2 = SWT.DEFAULT;
	public static final String P_DESCRIPTION_SERIES_2 = "descriptionSeries2";
	public static final String DEF_DESCRIPTION_SERIES_2 = "Measurement 2";
	public static final String P_ENABLE_AREA_SERIES_2 = "enableAreaSeries2";
	public static final boolean DEF_ENABLE_AREA_SERIES_2 = true;
	public static final String P_ENABLE_STACK_SERIES_2 = "enableStackSeries2";
	public static final boolean DEF_ENABLE_STACK_SERIES_2 = false;
	public static final String P_ENABLE_STEP_SERIES_2 = "enableStepSeries2";
	public static final boolean DEF_ENABLE_STEP_SERIES_2 = false;
	public static final String P_LINE_COLOR_SERIES_2 = "lineColorSeries2";
	public static final String DEF_LINE_COLOR_SERIES_2 = "125,125,125";
	public static final String P_LINE_STYLE_SERIES_2 = "lineStyleSeries2";
	public static final String DEF_LINE_STYLE_SERIES_2 = LineStyle.SOLID.toString();
	public static final String P_LINE_WIDTH_SERIES_2 = "lineWidthSeries2";
	public static final int DEF_LINE_WIDTH_SERIES_2 = 1;
	public static final String P_SYMBOL_COLOR_SERIES_2 = "symbolColorSeries2";
	public static final String DEF_SYMBOL_COLOR_SERIES_2 = "0,0,0";
	public static final String P_SYMBOL_SIZE_SERIES_2 = "symbolSizeSeries2";
	public static final int DEF_SYMBOL_SIZE_SERIES_2 = 8;
	public static final String P_SYMBOL_TYPE_SERIES_2 = "symbolTypeSeries2";
	public static final String DEF_SYMBOL_TYPE_SERIES_2 = PlotSymbolType.NONE.toString();
	public static final String P_VISIBLE_SERIES_2 = "visibleSeries2";
	public static final boolean DEF_VISIBLE_SERIES_2 = true;
	public static final String P_VISIBLE_IN_LEGEND_SERIES_2 = "visibleInLegendSeries2";
	public static final boolean DEF_VISIBLE_IN_LEGEND_SERIES_2 = true;
}
