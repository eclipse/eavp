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

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.eclipse.eavp.service.swtchart.converter.MillisecondsToMinuteConverter;
import org.eclipse.eavp.service.swtchart.converter.RelativeIntensityConverter;
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
import org.eclipse.swt.widgets.Display;
import org.swtchart.IAxis.Position;
import org.swtchart.LineStyle;

public class CustomLineChart1 extends LineChart {

	private static final int LENGTH_HINT_DATA_POINTS = 15000;
	//
	private boolean enableRangeUI;
	private boolean showAxisTitle;
	private boolean enableHorizontalSlider;
	private String seriesXY;

	public CustomLineChart1(Composite parent, int style, boolean enableRangeUI, boolean showAxisTitle, boolean enableHorizontalSlider, String seriesXY) {
		super(parent, style);
		this.enableRangeUI = enableRangeUI;
		this.showAxisTitle = showAxisTitle;
		this.enableHorizontalSlider = enableHorizontalSlider;
		this.seriesXY = seriesXY;
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
			chartSettings.setEnableRangeUI(enableRangeUI);
			chartSettings.setHorizontalSliderVisible(enableHorizontalSlider);
			chartSettings.setVerticalSliderVisible(false);
			chartSettings.setUseZeroX(true);
			chartSettings.setUseZeroY(true);
			//
			IPrimaryAxisSettings primaryAxisSettingsX = chartSettings.getPrimaryAxisSettingsX();
			primaryAxisSettingsX.setTitle("Retention Time (milliseconds)");
			primaryAxisSettingsX.setDecimalFormat(new DecimalFormat(("0.0##"), new DecimalFormatSymbols(Locale.ENGLISH)));
			primaryAxisSettingsX.setColor(Display.getDefault().getSystemColor(SWT.COLOR_BLACK));
			primaryAxisSettingsX.setPosition(Position.Secondary);
			primaryAxisSettingsX.setVisible(false);
			primaryAxisSettingsX.setGridLineStyle(LineStyle.NONE);
			//
			IPrimaryAxisSettings primaryAxisSettingsY = chartSettings.getPrimaryAxisSettingsY();
			primaryAxisSettingsY.setTitle("Intensity");
			primaryAxisSettingsY.setDecimalFormat(new DecimalFormat(("0.0#E0"), new DecimalFormatSymbols(Locale.ENGLISH)));
			primaryAxisSettingsY.setColor(Display.getDefault().getSystemColor(SWT.COLOR_BLACK));
			primaryAxisSettingsY.setPosition(Position.Primary);
			primaryAxisSettingsY.setVisible(true);
			primaryAxisSettingsY.setGridLineStyle(LineStyle.NONE);
			/*
			 * Secondary X-Axes
			 */
			String axisTitle = (showAxisTitle) ? "Minutes" : "";
			ISecondaryAxisSettings secondaryAxisSettingsX1 = new SecondaryAxisSettings(axisTitle, "Minutes", new MillisecondsToMinuteConverter());
			secondaryAxisSettingsX1.setPosition(Position.Primary);
			secondaryAxisSettingsX1.setDecimalFormat(new DecimalFormat(("0.00"), new DecimalFormatSymbols(Locale.ENGLISH)));
			secondaryAxisSettingsX1.setColor(Display.getDefault().getSystemColor(SWT.COLOR_BLACK));
			chartSettings.getSecondaryAxisSettingsListX().add(secondaryAxisSettingsX1);
			/*
			 * Secondary Y-Axes
			 */
			ISecondaryAxisSettings secondaryAxisSettingsY1 = new SecondaryAxisSettings("Relative Intensity [%]", new RelativeIntensityConverter(SWT.VERTICAL, true));
			secondaryAxisSettingsY1.setPosition(Position.Secondary);
			secondaryAxisSettingsY1.setDecimalFormat(new DecimalFormat(("0.00"), new DecimalFormatSymbols(Locale.ENGLISH)));
			secondaryAxisSettingsY1.setColor(Display.getDefault().getSystemColor(SWT.COLOR_BLACK));
			chartSettings.getSecondaryAxisSettingsListY().add(secondaryAxisSettingsY1);
			//
			applySettings(chartSettings);
		} catch(Exception e) {
			System.out.println(e);
		}
	}

	private void addDemoSeries() {

		List<ILineSeriesData> lineSeriesDataList = new ArrayList<ILineSeriesData>();
		ISeriesData seriesData = SeriesConverter.getSeriesXY(seriesXY);
		//
		ILineSeriesData lineSeriesData = new LineSeriesData(seriesData);
		ILineSeriesSettings lineSerieSettings = lineSeriesData.getLineSeriesSettings();
		lineSerieSettings.setEnableArea(true);
		lineSeriesDataList.add(lineSeriesData);
		/*
		 * Set series.
		 */
		addSeriesData(lineSeriesDataList, LENGTH_HINT_DATA_POINTS);
	}
}
