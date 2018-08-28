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
package org.eclipse.eavp.service.swtchart.customcharts;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.List;
import java.util.Locale;

import org.eclipse.eavp.service.swtchart.axisconverter.PassThroughConverter;
import org.eclipse.eavp.service.swtchart.core.IChartSettings;
import org.eclipse.eavp.service.swtchart.core.IPrimaryAxisSettings;
import org.eclipse.eavp.service.swtchart.core.ISecondaryAxisSettings;
import org.eclipse.eavp.service.swtchart.core.RangeRestriction;
import org.eclipse.eavp.service.swtchart.core.SecondaryAxisSettings;
import org.eclipse.eavp.service.swtchart.scattercharts.IScatterSeriesData;
import org.eclipse.eavp.service.swtchart.scattercharts.ScatterChart;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.widgets.Composite;
import org.swtchart.IAxis.Position;

public class PCAChart extends ScatterChart {

	private Color COLOR_BLACK = getDisplay().getSystemColor(SWT.COLOR_BLACK);
	//
	private int symbolSize = 0;
	private String chartTitle = "";
	private String xAxisTitle = "PC1";
	private String yAxisTitle = "PC2";
	private DecimalFormat decimalFormat = new DecimalFormat(("0.0##"), new DecimalFormatSymbols(Locale.ENGLISH));

	public PCAChart() {
		super();
		initialize();
	}

	public PCAChart(Composite parent, int style) {
		super(parent, style);
		initialize();
	}

	@Override
	public void addSeriesData(List<IScatterSeriesData> scatterSeriesDataList) {

		super.addSeriesData(scatterSeriesDataList);
		symbolSize = 0;
		for(IScatterSeriesData scatterSeriesData : scatterSeriesDataList) {
			symbolSize = Math.max(symbolSize, scatterSeriesData.getScatterSeriesSettings().getSymbolSize());
		}
	}

	public void setTitles(String chartTitle, String xAxisTitle, String yAxisTitle) {

		this.chartTitle = chartTitle;
		this.xAxisTitle = xAxisTitle;
		this.yAxisTitle = yAxisTitle;
		//
		IChartSettings chartSettings = getChartSettings();
		chartSettings.setTitle(chartTitle);
		chartSettings.getPrimaryAxisSettingsX().setTitle(xAxisTitle);
		chartSettings.getPrimaryAxisSettingsY().setTitle(yAxisTitle);
		applySettings(chartSettings);
	}

	public void setDecimalFormat(DecimalFormat decimalFormat) {

		this.decimalFormat = decimalFormat;
		//
		IChartSettings chartSettings = getChartSettings();
		chartSettings.getPrimaryAxisSettingsX().setDecimalFormat(decimalFormat);
		chartSettings.getPrimaryAxisSettingsY().setDecimalFormat(decimalFormat);
		for(ISecondaryAxisSettings secondaryAxisSettings : chartSettings.getSecondaryAxisSettingsListX()) {
			secondaryAxisSettings.setDecimalFormat(decimalFormat);
		}
		for(ISecondaryAxisSettings secondaryAxisSettings : chartSettings.getSecondaryAxisSettingsListY()) {
			secondaryAxisSettings.setDecimalFormat(decimalFormat);
		}
		applySettings(chartSettings);
	}

	private void initialize() {

		IChartSettings chartSettings = getChartSettings();
		chartSettings.setTitle(chartTitle);
		chartSettings.setTitleVisible(true);
		chartSettings.setTitleColor(COLOR_BLACK);
		chartSettings.setOrientation(SWT.HORIZONTAL);
		chartSettings.setHorizontalSliderVisible(false);
		chartSettings.setVerticalSliderVisible(false);
		RangeRestriction rangeRestriction = chartSettings.getRangeRestriction();
		rangeRestriction.setZeroX(false);
		rangeRestriction.setZeroY(false);
		rangeRestriction.setRestrictZoom(false);
		rangeRestriction.setExtendTypeX(RangeRestriction.ExtendType.RELATIVE);
		rangeRestriction.setExtendTypeY(RangeRestriction.ExtendType.RELATIVE);
		rangeRestriction.setExtend(0.25d);
		chartSettings.setShowAxisZeroMarker(true);
		chartSettings.setColorAxisZeroMarker(COLOR_BLACK);
		chartSettings.setShowSeriesLabelMarker(true);
		chartSettings.setColorSeriesLabelMarker(COLOR_BLACK);
		//
		setPrimaryAxisSet(chartSettings);
		addSecondaryAxisSet(chartSettings);
		//
		applySettings(chartSettings);
	}

	private void setPrimaryAxisSet(IChartSettings chartSettings) {

		IPrimaryAxisSettings primaryAxisSettingsX = chartSettings.getPrimaryAxisSettingsX();
		primaryAxisSettingsX.setTitle(xAxisTitle);
		primaryAxisSettingsX.setDecimalFormat(decimalFormat);
		primaryAxisSettingsX.setColor(COLOR_BLACK);
		//
		IPrimaryAxisSettings primaryAxisSettingsY = chartSettings.getPrimaryAxisSettingsY();
		primaryAxisSettingsY.setTitle(yAxisTitle);
		primaryAxisSettingsY.setDecimalFormat(decimalFormat);
		primaryAxisSettingsY.setColor(COLOR_BLACK);
	}

	private void addSecondaryAxisSet(IChartSettings chartSettings) {

		ISecondaryAxisSettings secondaryAxisSettingsX = new SecondaryAxisSettings(xAxisTitle, new PassThroughConverter());
		secondaryAxisSettingsX.setTitle("");
		secondaryAxisSettingsX.setPosition(Position.Secondary);
		secondaryAxisSettingsX.setDecimalFormat(decimalFormat);
		secondaryAxisSettingsX.setColor(COLOR_BLACK);
		chartSettings.getSecondaryAxisSettingsListX().add(secondaryAxisSettingsX);
		//
		ISecondaryAxisSettings secondaryAxisSettingsY = new SecondaryAxisSettings(yAxisTitle, new PassThroughConverter());
		secondaryAxisSettingsY.setPosition(Position.Secondary);
		secondaryAxisSettingsY.setDecimalFormat(decimalFormat);
		secondaryAxisSettingsY.setColor(COLOR_BLACK);
		chartSettings.getSecondaryAxisSettingsListY().add(secondaryAxisSettingsY);
	}
}
