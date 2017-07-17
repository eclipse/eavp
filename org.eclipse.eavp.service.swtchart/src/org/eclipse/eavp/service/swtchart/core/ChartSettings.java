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

import java.util.ArrayList;
import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.widgets.Display;

public class ChartSettings implements IChartSettings {

	private boolean enableRangeUI;
	private Color colorHintRangeUI;
	private boolean verticalSliderVisible;
	private boolean horizontalSliderVisible;
	//
	private String title;
	private boolean titleVisible;
	private Color titleColor;
	//
	private int legendPosition;
	private boolean legendVisible;
	//
	private IPrimaryAxisSettings primaryAxisSettingsX;
	private IPrimaryAxisSettings primaryAxisSettingsY;
	private List<ISecondaryAxisSettings> secondaryAxisSettingsListX;
	private List<ISecondaryAxisSettings> secondaryAxisSettingsListY;
	//
	private int orientation;
	private Color background;
	private Color backgroundChart;
	private Color backgroundPlotArea;
	private boolean enableCompress;
	private RangeRestriction rangeRestriction;
	private double factorExtendMinX;
	private double factorExtendMaxX;
	private double factorExtendMinY;
	private double factorExtendMaxY;
	//
	private boolean showPositionMarker;
	private Color colorPositionMarker;
	private boolean showCenterMarker;
	private Color colorCenterMarker;
	private boolean showPositionLegend;
	private Color colorPositionLegend;
	//
	private boolean createMenu;

	public ChartSettings() {
		//
		Display display = Display.getDefault();
		/*
		 * Set the chart.
		 */
		enableRangeUI = false;
		colorHintRangeUI = display.getSystemColor(SWT.COLOR_RED);
		verticalSliderVisible = false; // https://bugs.eclipse.org/bugs/show_bug.cgi?id=511257
		horizontalSliderVisible = true;
		/*
		 * If the title is empty, it won't be displayed.
		 * To display a space on top of the chart, a default
		 * title is set and WHITE is used to hide it.
		 */
		title = "Chart Title";
		titleVisible = true;
		titleColor = display.getSystemColor(SWT.COLOR_WHITE);
		//
		legendPosition = SWT.RIGHT;
		legendVisible = false;
		//
		primaryAxisSettingsX = new PrimaryAxisSettings(BaseChart.DEFAULT_TITLE_X_AXIS);
		primaryAxisSettingsY = new PrimaryAxisSettings(BaseChart.DEFAULT_TITLE_Y_AXIS);
		secondaryAxisSettingsListX = new ArrayList<ISecondaryAxisSettings>();
		secondaryAxisSettingsListY = new ArrayList<ISecondaryAxisSettings>();
		//
		orientation = SWT.HORIZONTAL;
		background = display.getSystemColor(SWT.COLOR_WHITE);
		backgroundChart = display.getSystemColor(SWT.COLOR_WHITE);
		backgroundPlotArea = display.getSystemColor(SWT.COLOR_WHITE);
		enableCompress = true;
		rangeRestriction = new RangeRestriction();
		rangeRestriction.setZeroX(true);
		rangeRestriction.setZeroY(true);
		rangeRestriction.setRestrictZoom(true);
		factorExtendMinX = 0.0d;
		factorExtendMaxX = 0.0d;
		factorExtendMinY = 0.0d;
		factorExtendMaxY = 0.0d;
		//
		showPositionMarker = false;
		colorPositionMarker = display.getSystemColor(SWT.COLOR_DARK_GRAY);
		showCenterMarker = false;
		colorCenterMarker = display.getSystemColor(SWT.COLOR_DARK_GRAY);
		showPositionLegend = false;
		colorPositionLegend = display.getSystemColor(SWT.COLOR_DARK_GRAY);
		//
		createMenu = false;
	}

	@Override
	public boolean isEnableRangeUI() {

		return enableRangeUI;
	}

	@Override
	public void setEnableRangeUI(boolean enableRangeUI) {

		this.enableRangeUI = enableRangeUI;
	}

	@Override
	public Color getColorHintRangeUI() {

		return colorHintRangeUI;
	}

	@Override
	public void setColorHintRangeUI(Color colorHintRangeUI) {

		this.colorHintRangeUI = colorHintRangeUI;
	}

	@Override
	public boolean isVerticalSliderVisible() {

		return verticalSliderVisible;
	}

	@Override
	public void setVerticalSliderVisible(boolean verticalSliderVisible) {

		/*
		 * There is a bug when using the SWT.RIGHT_TO_LEFT orientation.
		 * https://bugs.eclipse.org/bugs/show_bug.cgi?id=511257
		 * That's why the vertical slider is not visible yet.
		 */
		// this.verticalSliderVisible = verticalSliderVisible;
		this.verticalSliderVisible = false;
		System.out.println("Can't set vertical slider true, see: https://bugs.eclipse.org/bugs/show_bug.cgi?id=511257");
	}

	@Override
	public boolean isHorizontalSliderVisible() {

		return horizontalSliderVisible;
	}

	@Override
	public void setHorizontalSliderVisible(boolean horizontalSliderVisible) {

		this.horizontalSliderVisible = horizontalSliderVisible;
	}

	@Override
	public String getTitle() {

		return title;
	}

	@Override
	public void setTitle(String title) {

		if(title != null) {
			this.title = title;
		} else {
			this.title = "";
		}
	}

	@Override
	public boolean isTitleVisible() {

		return titleVisible;
	}

	@Override
	public void setTitleVisible(boolean titleVisible) {

		this.titleVisible = titleVisible;
	}

	@Override
	public Color getTitleColor() {

		return titleColor;
	}

	@Override
	public void setTitleColor(Color titleColor) {

		this.titleColor = titleColor;
	}

	@Override
	public int getLegendPosition() {

		return legendPosition;
	}

	@Override
	public void setLegendPosition(int legendPosition) {

		this.legendPosition = legendPosition;
	}

	@Override
	public boolean isLegendVisible() {

		return legendVisible;
	}

	@Override
	public void setLegendVisible(boolean legendVisible) {

		this.legendVisible = legendVisible;
	}

	@Override
	public IPrimaryAxisSettings getPrimaryAxisSettingsX() {

		return primaryAxisSettingsX;
	}

	@Override
	public IPrimaryAxisSettings getPrimaryAxisSettingsY() {

		return primaryAxisSettingsY;
	}

	@Override
	public List<ISecondaryAxisSettings> getSecondaryAxisSettingsListX() {

		return secondaryAxisSettingsListX;
	}

	@Override
	public List<ISecondaryAxisSettings> getSecondaryAxisSettingsListY() {

		return secondaryAxisSettingsListY;
	}

	@Override
	public int getOrientation() {

		return orientation;
	}

	/**
	 * SWT.HORIZONTAL or SWT.VERTICAL
	 * See:http://www.swtchart.org/doc/index.html#Chart_Orientation
	 * 
	 * @param orientation
	 */
	@Override
	public void setOrientation(int orientation) {

		this.orientation = orientation;
	}

	@Override
	public Color getBackground() {

		return background;
	}

	@Override
	public void setBackground(Color background) {

		this.background = background;
	}

	@Override
	public Color getBackgroundChart() {

		return backgroundChart;
	}

	@Override
	public void setBackgroundChart(Color backgroundChart) {

		this.backgroundChart = backgroundChart;
	}

	@Override
	public Color getBackgroundPlotArea() {

		return backgroundPlotArea;
	}

	@Override
	public void setBackgroundPlotArea(Color backgroundPlotArea) {

		this.backgroundPlotArea = backgroundPlotArea;
	}

	@Override
	public boolean isEnableCompress() {

		return enableCompress;
	}

	@Override
	public void setEnableCompress(boolean enableCompress) {

		this.enableCompress = enableCompress;
	}

	@Override
	public RangeRestriction getRangeRestriction() {

		return rangeRestriction;
	}

	@Override
	public double getFactorExtendMinX() {

		return factorExtendMinX;
	}

	@Override
	public void setFactorExtendMinX(double factorExtendMinX) {

		this.factorExtendMinX = factorExtendMinX;
	}

	@Override
	public double getFactorExtendMaxX() {

		return factorExtendMaxX;
	}

	@Override
	public void setFactorExtendMaxX(double factorExtendMaxX) {

		this.factorExtendMaxX = factorExtendMaxX;
	}

	@Override
	public double getFactorExtendMinY() {

		return factorExtendMinY;
	}

	@Override
	public void setFactorExtendMinY(double factorExtendMinY) {

		this.factorExtendMinY = factorExtendMinY;
	}

	@Override
	public double getFactorExtendMaxY() {

		return factorExtendMaxY;
	}

	@Override
	public void setFactorExtendMaxY(double factorExtendMaxY) {

		this.factorExtendMaxY = factorExtendMaxY;
	}

	@Override
	public void setFactorExtend(double factorExtend) {

		this.factorExtendMinX = factorExtend;
		this.factorExtendMaxX = factorExtend;
		this.factorExtendMinY = factorExtend;
		this.factorExtendMaxY = factorExtend;
	}

	@Override
	public boolean isShowPositionMarker() {

		return showPositionMarker;
	}

	@Override
	public void setShowPositionMarker(boolean showPositionMarker) {

		this.showPositionMarker = showPositionMarker;
	}

	@Override
	public Color getColorPositionMarker() {

		return colorPositionMarker;
	}

	@Override
	public void setColorPositionMarker(Color colorPositionMarker) {

		this.colorPositionMarker = colorPositionMarker;
	}

	@Override
	public boolean isShowCenterMarker() {

		return showCenterMarker;
	}

	@Override
	public void setShowCenterMarker(boolean showCenterMarker) {

		this.showCenterMarker = showCenterMarker;
	}

	@Override
	public Color getColorCenterMarker() {

		return colorCenterMarker;
	}

	@Override
	public void setColorCenterMarker(Color colorCenterMarker) {

		this.colorCenterMarker = colorCenterMarker;
	}

	@Override
	public boolean isShowPositionLegend() {

		return showPositionLegend;
	}

	@Override
	public void setShowPositionLegend(boolean showPositionLegend) {

		this.showPositionLegend = showPositionLegend;
	}

	@Override
	public Color getColorPositionLegend() {

		return colorPositionLegend;
	}

	@Override
	public void setColorPositionLegend(Color colorPositionLegend) {

		this.colorPositionLegend = colorPositionLegend;
	}

	@Override
	public boolean isCreateMenu() {

		return createMenu;
	}

	@Override
	public void setCreateMenu(boolean createMenu) {

		this.createMenu = createMenu;
	}
}
