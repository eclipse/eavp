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
package org.eclipse.eavp.service.swtchart.demos.parts;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.inject.Inject;

import org.eclipse.eavp.service.swtchart.converter.MillisecondsToMinuteConverter;
import org.eclipse.eavp.service.swtchart.converter.MillisecondsToScanNumberConverter;
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

public class LineSeries_3_Part extends LineChart {

	@Inject
	public LineSeries_3_Part(Composite parent) {
		super(parent, SWT.NONE);
		setBackground(Display.getDefault().getSystemColor(SWT.COLOR_WHITE));
		try {
			initialize();
		} catch(Exception e) {
			System.out.println(e);
		}
	}

	private void initialize() throws Exception {

		/*
		 * Chart Settings
		 */
		IChartSettings chartSettings = getChartSettings();
		chartSettings.setOrientation(SWT.HORIZONTAL);
		chartSettings.setHorizontalSliderVisible(true);
		chartSettings.setVerticalSliderVisible(true);
		chartSettings.getRangeRestriction().setZeroX(true);
		chartSettings.getRangeRestriction().setZeroY(true);
		chartSettings.setEnableRangeSelector(true);
		chartSettings.setShowPositionMarker(true);
		chartSettings.setShowCenterMarker(true);
		chartSettings.setShowPositionLegend(true);
		chartSettings.setCreateMenu(true);
		/*
		 * Primary X-Axis
		 */
		IPrimaryAxisSettings primaryAxisSettingsX = chartSettings.getPrimaryAxisSettingsX();
		primaryAxisSettingsX.setTitle("Retention Time (milliseconds)");
		primaryAxisSettingsX.setDecimalFormat(new DecimalFormat(("0.0##"), new DecimalFormatSymbols(Locale.ENGLISH)));
		primaryAxisSettingsX.setColor(Display.getDefault().getSystemColor(SWT.COLOR_BLACK));
		primaryAxisSettingsX.setPosition(Position.Secondary);
		primaryAxisSettingsX.setVisible(false);
		primaryAxisSettingsX.setGridLineStyle(LineStyle.NONE);
		/*
		 * Primary Y-Axis
		 */
		IPrimaryAxisSettings primaryAxisSettingsY = chartSettings.getPrimaryAxisSettingsY();
		primaryAxisSettingsY.setTitle("Intensity");
		primaryAxisSettingsY.setDecimalFormat(new DecimalFormat(("0.0#E0"), new DecimalFormatSymbols(Locale.ENGLISH)));
		primaryAxisSettingsY.setColor(Display.getDefault().getSystemColor(SWT.COLOR_BLACK));
		primaryAxisSettingsY.setGridLineStyle(LineStyle.NONE);
		/*
		 * Secondary X-Axes
		 */
		ISecondaryAxisSettings secondaryAxisSettingsX1 = new SecondaryAxisSettings("Scan Number", new MillisecondsToScanNumberConverter(50, 50));
		secondaryAxisSettingsX1.setPosition(Position.Primary);
		secondaryAxisSettingsX1.setDecimalFormat(new DecimalFormat(("0"), new DecimalFormatSymbols(Locale.ENGLISH)));
		secondaryAxisSettingsX1.setColor(Display.getDefault().getSystemColor(SWT.COLOR_BLACK));
		chartSettings.getSecondaryAxisSettingsListX().add(secondaryAxisSettingsX1);
		//
		ISecondaryAxisSettings secondaryAxisSettingsX2 = new SecondaryAxisSettings("Minutes", new MillisecondsToMinuteConverter());
		secondaryAxisSettingsX2.setPosition(Position.Primary);
		secondaryAxisSettingsX2.setDecimalFormat(new DecimalFormat(("0.00"), new DecimalFormatSymbols(Locale.ENGLISH)));
		secondaryAxisSettingsX2.setColor(Display.getDefault().getSystemColor(SWT.COLOR_BLACK));
		chartSettings.getSecondaryAxisSettingsListX().add(secondaryAxisSettingsX2);
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
		/*
		 * Create series.
		 */
		List<ILineSeriesData> lineSeriesDataList = new ArrayList<ILineSeriesData>();
		ISeriesData seriesData = SeriesConverter.getSeriesXY(SeriesConverter.LINE_SERIES_3);
		//
		ILineSeriesData lineSeriesData = new LineSeriesData(seriesData);
		ILineSeriesSettings lineSerieSettings = lineSeriesData.getLineSeriesSettings();
		lineSerieSettings.setEnableArea(true);
		lineSeriesDataList.add(lineSeriesData);
		/*
		 * Set series.
		 */
		addSeriesData(lineSeriesDataList);
	}
}
