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

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.eclipse.eavp.service.swtchart.core.ColorAndFormatSupport;
import org.eclipse.eavp.service.swtchart.core.ISeriesData;
import org.eclipse.eavp.service.swtchart.customcharts.ChromatogramChart;
import org.eclipse.eavp.service.swtchart.demos.support.SeriesConverter;
import org.eclipse.eavp.service.swtchart.linecharts.ILineSeriesData;
import org.eclipse.eavp.service.swtchart.linecharts.ILineSeriesSettings;
import org.eclipse.eavp.service.swtchart.linecharts.LineSeriesData;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.swtchart.ILineSeries.PlotSymbolType;
import org.swtchart.LineStyle;

public class LineSeries_1_Part extends ChromatogramChart {

	@Inject
	public LineSeries_1_Part(Composite parent) {
		super(parent, SWT.NONE);
		setBackground(ColorAndFormatSupport.COLOR_WHITE);
		try {
			initialize();
		} catch(Exception e) {
			System.out.println(e);
		}
	}

	private void initialize() throws Exception {

		/*
		 * Create series.
		 */
		List<ILineSeriesData> lineSeriesDataList = new ArrayList<ILineSeriesData>();
		//
		ISeriesData seriesData;
		ILineSeriesData lineSeriesData;
		ILineSeriesSettings lineSerieSettings;
		/*
		 * Chromatogram
		 */
		seriesData = SeriesConverter.getSeriesXY(SeriesConverter.LINE_SERIES_1);
		lineSeriesData = new LineSeriesData(seriesData);
		lineSerieSettings = lineSeriesData.getLineSeriesSettings();
		lineSerieSettings.setEnableArea(true);
		lineSeriesDataList.add(lineSeriesData);
		/*
		 * Selected Scans
		 */
		seriesData = SeriesConverter.getSeriesXY(SeriesConverter.LINE_SERIES_1_SELECTED_SCANS);
		lineSeriesData = new LineSeriesData(seriesData);
		lineSerieSettings = lineSeriesData.getLineSeriesSettings();
		lineSerieSettings.setLineStyle(LineStyle.NONE);
		lineSerieSettings.setSymbolType(PlotSymbolType.CROSS);
		lineSerieSettings.setSymbolSize(5);
		lineSerieSettings.setSymbolColor(ColorAndFormatSupport.COLOR_DARK_RED);
		lineSeriesDataList.add(lineSeriesData);
		/*
		 * Active Peaks
		 */
		seriesData = SeriesConverter.getSeriesXY(SeriesConverter.LINE_SERIES_1_ACTIVE_PEAKS);
		lineSeriesData = new LineSeriesData(seriesData);
		lineSerieSettings = lineSeriesData.getLineSeriesSettings();
		lineSerieSettings.setEnableArea(false);
		lineSerieSettings.setLineStyle(LineStyle.NONE);
		lineSerieSettings.setSymbolType(PlotSymbolType.INVERTED_TRIANGLE);
		lineSerieSettings.setSymbolSize(5);
		lineSerieSettings.setLineColor(ColorAndFormatSupport.COLOR_GRAY);
		lineSerieSettings.setSymbolColor(ColorAndFormatSupport.COLOR_DARK_GRAY);
		lineSeriesDataList.add(lineSeriesData);
		/*
		 * Inactive Peaks
		 */
		seriesData = SeriesConverter.getSeriesXY(SeriesConverter.LINE_SERIES_1_INACTIVE_PEAKS);
		lineSeriesData = new LineSeriesData(seriesData);
		lineSerieSettings = lineSeriesData.getLineSeriesSettings();
		lineSerieSettings.setEnableArea(false);
		lineSerieSettings.setLineStyle(LineStyle.NONE);
		lineSerieSettings.setSymbolType(PlotSymbolType.INVERTED_TRIANGLE);
		lineSerieSettings.setSymbolSize(5);
		lineSerieSettings.setLineColor(ColorAndFormatSupport.COLOR_GRAY);
		lineSerieSettings.setSymbolColor(ColorAndFormatSupport.COLOR_GRAY);
		lineSeriesDataList.add(lineSeriesData);
		/*
		 * Peak 1
		 */
		seriesData = SeriesConverter.getSeriesXY(SeriesConverter.LINE_SERIES_1_SELECTED_PEAK_1);
		lineSeriesData = new LineSeriesData(seriesData);
		lineSerieSettings = lineSeriesData.getLineSeriesSettings();
		lineSerieSettings.setEnableArea(true);
		lineSerieSettings.setSymbolType(PlotSymbolType.CIRCLE);
		lineSerieSettings.setSymbolColor(ColorAndFormatSupport.COLOR_DARK_RED);
		lineSerieSettings.setSymbolSize(2);
		lineSerieSettings.setLineColor(ColorAndFormatSupport.COLOR_DARK_RED);
		lineSeriesDataList.add(lineSeriesData);
		/*
		 * Background 1
		 */
		seriesData = SeriesConverter.getSeriesXY(SeriesConverter.LINE_SERIES_1_SELECTED_PEAK_1_BACKGROUND);
		lineSeriesData = new LineSeriesData(seriesData);
		lineSerieSettings = lineSeriesData.getLineSeriesSettings();
		lineSerieSettings.setEnableArea(true);
		lineSerieSettings.setSymbolType(PlotSymbolType.NONE);
		lineSerieSettings.setLineColor(ColorAndFormatSupport.COLOR_BLACK);
		lineSeriesDataList.add(lineSeriesData);
		/*
		 * Peak 2
		 */
		seriesData = SeriesConverter.getSeriesXY(SeriesConverter.LINE_SERIES_1_SELECTED_PEAK_2);
		lineSeriesData = new LineSeriesData(seriesData);
		lineSerieSettings = lineSeriesData.getLineSeriesSettings();
		lineSerieSettings.setEnableArea(true);
		lineSerieSettings.setSymbolType(PlotSymbolType.CIRCLE);
		lineSerieSettings.setSymbolColor(ColorAndFormatSupport.COLOR_DARK_RED);
		lineSerieSettings.setSymbolSize(2);
		lineSerieSettings.setLineColor(ColorAndFormatSupport.COLOR_DARK_RED);
		lineSeriesDataList.add(lineSeriesData);
		/*
		 * Background 2
		 */
		seriesData = SeriesConverter.getSeriesXY(SeriesConverter.LINE_SERIES_1_SELECTED_PEAK_2_BACKGROUND);
		lineSeriesData = new LineSeriesData(seriesData);
		lineSerieSettings = lineSeriesData.getLineSeriesSettings();
		lineSerieSettings.setEnableArea(true);
		lineSerieSettings.setSymbolType(PlotSymbolType.NONE);
		lineSerieSettings.setLineColor(ColorAndFormatSupport.COLOR_BLACK);
		lineSeriesDataList.add(lineSeriesData);
		/*
		 * Identified Scans
		 */
		seriesData = SeriesConverter.getSeriesXY(SeriesConverter.LINE_SERIES_1_IDENTIFIED_SCANS);
		lineSeriesData = new LineSeriesData(seriesData);
		lineSerieSettings = lineSeriesData.getLineSeriesSettings();
		lineSerieSettings.setLineStyle(LineStyle.NONE);
		lineSerieSettings.setSymbolType(PlotSymbolType.CIRCLE);
		lineSerieSettings.setSymbolSize(3);
		lineSerieSettings.setLineColor(ColorAndFormatSupport.COLOR_GRAY);
		lineSerieSettings.setSymbolColor(ColorAndFormatSupport.COLOR_DARK_GRAY);
		lineSeriesDataList.add(lineSeriesData);
		/*
		 * Identified Scans Selected.
		 */
		seriesData = SeriesConverter.getSeriesXY(SeriesConverter.LINE_SERIES_1_IDENTIFIED_SCANS_SELECTED);
		lineSeriesData = new LineSeriesData(seriesData);
		lineSerieSettings = lineSeriesData.getLineSeriesSettings();
		lineSerieSettings.setLineStyle(LineStyle.NONE);
		lineSerieSettings.setSymbolType(PlotSymbolType.CIRCLE);
		lineSerieSettings.setSymbolSize(5);
		lineSerieSettings.setSymbolColor(ColorAndFormatSupport.COLOR_DARK_RED);
		lineSeriesDataList.add(lineSeriesData);
		/*
		 * Set series.
		 */
		addSeriesData(lineSeriesDataList, false);
	}
}
