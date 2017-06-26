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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.eclipse.eavp.service.swtchart.core.ColorAndFormatSupport;
import org.eclipse.eavp.service.swtchart.core.ISeriesData;
import org.eclipse.eavp.service.swtchart.customcharts.ChromatogramChart;
import org.eclipse.eavp.service.swtchart.demos.support.SeriesConverter;
import org.eclipse.eavp.service.swtchart.linecharts.ILineSeriesData;
import org.eclipse.eavp.service.swtchart.linecharts.ILineSeriesSettings;
import org.eclipse.eavp.service.swtchart.linecharts.LineSeriesData;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.widgets.Composite;

public class LineSeries_4_Part extends ChromatogramChart {

	@Inject
	public LineSeries_4_Part(Composite parent) {
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
		Map<Integer, Color> colors = new HashMap<Integer, Color>();
		colors.put(3, ColorAndFormatSupport.COLOR_RED);
		colors.put(4, ColorAndFormatSupport.COLOR_BLACK);
		colors.put(5, ColorAndFormatSupport.COLOR_GRAY);
		colors.put(6, ColorAndFormatSupport.COLOR_DARK_RED);
		colors.put(7, ColorAndFormatSupport.COLOR_DARK_GRAY);
		//
		List<ILineSeriesData> lineSeriesDataList = new ArrayList<ILineSeriesData>();
		for(int i = 3; i <= 7; i++) {
			ISeriesData seriesData = SeriesConverter.getSeriesXY(SeriesConverter.LINE_SERIES + i);
			ILineSeriesData lineSeriesData = new LineSeriesData(seriesData);
			ILineSeriesSettings lineSerieSettings = lineSeriesData.getLineSeriesSettings();
			lineSerieSettings.setLineColor(colors.get(i));
			lineSerieSettings.setEnableArea(false);
			lineSeriesDataList.add(lineSeriesData);
			addSeriesData(lineSeriesDataList);
		}
	}
}
