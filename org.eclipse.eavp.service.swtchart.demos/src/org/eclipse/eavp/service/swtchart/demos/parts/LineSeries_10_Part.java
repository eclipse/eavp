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

import org.eclipse.eavp.service.swtchart.core.ISeriesData;
import org.eclipse.eavp.service.swtchart.customcharts.ChromatogramChart;
import org.eclipse.eavp.service.swtchart.demos.support.SeriesConverter;
import org.eclipse.eavp.service.swtchart.linecharts.ILineSeriesData;
import org.eclipse.eavp.service.swtchart.linecharts.ILineSeriesSettings;
import org.eclipse.eavp.service.swtchart.linecharts.LineSeriesData;
import org.eclipse.eavp.service.swtchart.marker.LabelMarker;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.swtchart.ILineSeries.PlotSymbolType;
import org.swtchart.IPlotArea;
import org.swtchart.LineStyle;

public class LineSeries_10_Part extends ChromatogramChart {

	private int indexSeries;

	@Inject
	public LineSeries_10_Part(Composite parent) {
		super(parent, SWT.NONE);
		setBackground(Display.getDefault().getSystemColor(SWT.COLOR_WHITE));
		//
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
		 * Chromatogram [0]
		 */
		seriesData = SeriesConverter.getSeriesXY(SeriesConverter.LINE_SERIES_1);
		lineSeriesData = new LineSeriesData(seriesData);
		lineSerieSettings = lineSeriesData.getLineSeriesSettings();
		lineSerieSettings.setEnableArea(true);
		lineSeriesDataList.add(lineSeriesData);
		/*
		 * Active Peaks [1]
		 */
		indexSeries = 1;
		seriesData = SeriesConverter.getSeriesXY(SeriesConverter.LINE_SERIES_1_ACTIVE_PEAKS);
		lineSeriesData = new LineSeriesData(seriesData);
		lineSerieSettings = lineSeriesData.getLineSeriesSettings();
		lineSerieSettings.setEnableArea(false);
		lineSerieSettings.setLineStyle(LineStyle.NONE);
		lineSerieSettings.setSymbolType(PlotSymbolType.INVERTED_TRIANGLE);
		lineSerieSettings.setSymbolSize(5);
		lineSerieSettings.setLineColor(Display.getDefault().getSystemColor(SWT.COLOR_GRAY));
		lineSerieSettings.setSymbolColor(Display.getDefault().getSystemColor(SWT.COLOR_GRAY));
		lineSeriesDataList.add(lineSeriesData);
		/*
		 * Set series.
		 */
		addSeriesData(lineSeriesDataList);
		/*
		 * Add the label marker.
		 */
		IPlotArea plotArea = (IPlotArea)getBaseChart().getPlotArea();
		LabelMarker labelMarker = new LabelMarker(getBaseChart());
		List<String> labels = new ArrayList<String>();
		labels.add("[1]");
		labels.add("[2]");
		labels.add("[3]");
		labels.add("[4]");
		labels.add("[5]");
		labels.add("[6]");
		labels.add("[7]");
		labels.add("[8]");
		labels.add("[9]");
		labels.add("[10]");
		labelMarker.setLabels(labels, indexSeries, SWT.HORIZONTAL);
		plotArea.addCustomPaintListener(labelMarker);
	}
}
