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
package org.eclipse.eavp.service.swtchart.demos.swt;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.eavp.service.swtchart.converter.MillisecondsToMinuteConverter;
import org.eclipse.eavp.service.swtchart.converter.RelativeIntensityConverter;
import org.eclipse.eavp.service.swtchart.core.ColorAndFormatSupport;
import org.eclipse.eavp.service.swtchart.core.IChartSettings;
import org.eclipse.eavp.service.swtchart.core.IPrimaryAxisSettings;
import org.eclipse.eavp.service.swtchart.core.ISecondaryAxisSettings;
import org.eclipse.eavp.service.swtchart.core.ISeriesData;
import org.eclipse.eavp.service.swtchart.core.SecondaryAxisSettings;
import org.eclipse.eavp.service.swtchart.demos.support.SeriesConverter;
import org.eclipse.eavp.service.swtchart.linecharts.ILineSeriesData;
import org.eclipse.eavp.service.swtchart.linecharts.ILineSeriesSettings;
import org.eclipse.eavp.service.swtchart.linecharts.LineChart;
import org.eclipse.eavp.service.swtchart.linecharts.LineSeriesData;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.swtchart.IAxis.Position;
import org.swtchart.LineStyle;

public class CustomLineChart1 extends LineChart {

	private boolean enableRangeInfo;
	private boolean showAxisTitle;
	private boolean enableHorizontalSlider;

	public CustomLineChart1(Composite parent, int style, boolean enableRangeInfo, boolean showAxisTitle, boolean enableHorizontalSlider) {
		super(parent, style);
		this.enableRangeInfo = enableRangeInfo;
		this.showAxisTitle = showAxisTitle;
		this.enableHorizontalSlider = enableHorizontalSlider;
		createControl();
	}

	private void createControl() {

		configureChart();
		addDemoSeries();
	}

	private void configureChart() {

		try {
			IChartSettings chartSettings = getChartSettings();
			chartSettings.setOrientation(SWT.HORIZONTAL);
			chartSettings.setEnableRangeInfo(enableRangeInfo);
			chartSettings.setHorizontalSliderVisible(enableHorizontalSlider);
			chartSettings.setVerticalSliderVisible(false);
			chartSettings.setUseZeroX(true);
			chartSettings.setUseZeroY(true);
			//
			IPrimaryAxisSettings primaryAxisSettingsX = chartSettings.getPrimaryAxisSettingsX();
			primaryAxisSettingsX.setTitle("Retention Time (milliseconds)");
			primaryAxisSettingsX.setDecimalFormat(ColorAndFormatSupport.decimalFormatVariable);
			primaryAxisSettingsX.setColor(ColorAndFormatSupport.COLOR_BLACK);
			primaryAxisSettingsX.setPosition(Position.Secondary);
			primaryAxisSettingsX.setVisible(false);
			primaryAxisSettingsX.setGridLineStyle(LineStyle.NONE);
			//
			IPrimaryAxisSettings primaryAxisSettingsY = chartSettings.getPrimaryAxisSettingsY();
			primaryAxisSettingsY.setTitle("Intensity");
			primaryAxisSettingsY.setDecimalFormat(ColorAndFormatSupport.decimalFormatScientific);
			primaryAxisSettingsY.setColor(ColorAndFormatSupport.COLOR_BLACK);
			primaryAxisSettingsY.setPosition(Position.Primary);
			primaryAxisSettingsY.setVisible(true);
			primaryAxisSettingsY.setGridLineStyle(LineStyle.NONE);
			/*
			 * Secondary X-Axes
			 */
			String axisTitle = (showAxisTitle) ? "Minutes" : "";
			ISecondaryAxisSettings secondaryAxisSettingsX1 = new SecondaryAxisSettings(axisTitle, "Minutes", new MillisecondsToMinuteConverter());
			secondaryAxisSettingsX1.setPosition(Position.Primary);
			secondaryAxisSettingsX1.setDecimalFormat(ColorAndFormatSupport.decimalFormatFixed);
			secondaryAxisSettingsX1.setColor(ColorAndFormatSupport.COLOR_BLACK);
			chartSettings.getSecondaryAxisSettingsListX().add(secondaryAxisSettingsX1);
			/*
			 * Secondary Y-Axes
			 */
			ISecondaryAxisSettings secondaryAxisSettingsY1 = new SecondaryAxisSettings("Relative Intensity [%]", new RelativeIntensityConverter(SWT.VERTICAL, true));
			secondaryAxisSettingsY1.setPosition(Position.Secondary);
			secondaryAxisSettingsY1.setDecimalFormat(ColorAndFormatSupport.decimalFormatFixed);
			secondaryAxisSettingsY1.setColor(ColorAndFormatSupport.COLOR_BLACK);
			chartSettings.getSecondaryAxisSettingsListY().add(secondaryAxisSettingsY1);
			//
			applySettings(chartSettings);
		} catch(Exception e) {
			System.out.println(e);
		}
	}

	private void addDemoSeries() {

		List<ILineSeriesData> lineSeriesDataList = new ArrayList<ILineSeriesData>();
		ISeriesData seriesData = SeriesConverter.getSeriesXY(SeriesConverter.LINE_SERIES_1);
		//
		ILineSeriesData lineSeriesData = new LineSeriesData(seriesData);
		ILineSeriesSettings lineSerieSettings = lineSeriesData.getLineSeriesSettings();
		lineSerieSettings.setEnableArea(true);
		lineSeriesDataList.add(lineSeriesData);
		/*
		 * Set series.
		 */
		addSeriesData(lineSeriesDataList, true);
	}
}
