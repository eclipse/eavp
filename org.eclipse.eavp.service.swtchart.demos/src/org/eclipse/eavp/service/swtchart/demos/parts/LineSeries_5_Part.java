/*******************************************************************************
 * Copyright (c) 2017, 2018 Lablicate GmbH.
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

import org.eclipse.eavp.service.swtchart.core.IChartSettings;
import org.eclipse.eavp.service.swtchart.core.ISeriesData;
import org.eclipse.eavp.service.swtchart.customcharts.ChromatogramChart;
import org.eclipse.eavp.service.swtchart.demos.support.SeriesConverter;
import org.eclipse.eavp.service.swtchart.linecharts.ILineSeriesData;
import org.eclipse.eavp.service.swtchart.linecharts.ILineSeriesSettings;
import org.eclipse.eavp.service.swtchart.linecharts.LineSeriesData;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;

public class LineSeries_5_Part extends ChromatogramChart {

	@Inject
	public LineSeries_5_Part(Composite parent) {
		super(parent, SWT.NONE);
		setBackground(getDisplay().getSystemColor(SWT.COLOR_WHITE));
		try {
			initialize();
		} catch(Exception e) {
			System.out.println(e);
		}
	}

	private void initialize() throws Exception {

		IChartSettings chartSettings = getChartSettings();
		chartSettings.getRangeRestriction().setZeroY(false);
		applySettings(chartSettings);
		//
		List<ILineSeriesData> lineSeriesDataList = new ArrayList<ILineSeriesData>();
		//
		ISeriesData seriesData;
		ILineSeriesData lineSeriesData;
		ILineSeriesSettings lineSeriesSettings;
		/*
		 * Positive
		 */
		seriesData = SeriesConverter.getSeriesXY(SeriesConverter.LINE_SERIES_5_POSITIVE);
		lineSeriesData = new LineSeriesData(seriesData);
		lineSeriesSettings = lineSeriesData.getLineSeriesSettings();
		lineSeriesSettings.setLineColor(getBaseChart().getDisplay().getSystemColor(SWT.COLOR_RED));
		lineSeriesSettings.setEnableArea(false);
		lineSeriesDataList.add(lineSeriesData);
		/*
		 * Negative
		 */
		seriesData = SeriesConverter.getSeriesXY(SeriesConverter.LINE_SERIES_5_NEGATIVE);
		lineSeriesData = new LineSeriesData(seriesData);
		lineSeriesSettings = lineSeriesData.getLineSeriesSettings();
		lineSeriesSettings.setLineColor(getDisplay().getSystemColor(SWT.COLOR_BLACK));
		lineSeriesSettings.setEnableArea(false);
		ILineSeriesSettings lineSeriesSettingsHighlight = (ILineSeriesSettings)lineSeriesSettings.getSeriesSettingsHighlight();
		lineSeriesSettingsHighlight.setLineWidth(2);
		lineSeriesDataList.add(lineSeriesData);
		/*
		 * Set series.
		 */
		addSeriesData(lineSeriesDataList);
	}
}
