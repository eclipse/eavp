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
package org.eclipse.eavp.service.swtchart.scattercharts;

import java.util.List;

import org.eclipse.eavp.service.swtchart.core.BaseChart;
import org.eclipse.eavp.service.swtchart.core.ISeriesData;
import org.eclipse.eavp.service.swtchart.core.ScrollableChart;
import org.eclipse.eavp.service.swtchart.exceptions.SeriesException;
import org.eclipse.swt.widgets.Composite;
import org.swtchart.ILineSeries;

public class ScatterChart extends ScrollableChart {

	public ScatterChart() {
		super();
	}

	public ScatterChart(Composite parent, int style) {
		super(parent, style);
	}

	public void addSeriesData(List<IScatterSeriesData> scatterSeriesDataList) {

		/*
		 * Suspend the update when adding new data to improve the performance.
		 */
		if(scatterSeriesDataList != null && scatterSeriesDataList.size() > 0) {
			/*
			 * Set the data.
			 */
			BaseChart baseChart = getBaseChart();
			baseChart.suspendUpdate(true);
			for(IScatterSeriesData scatterSeriesData : scatterSeriesDataList) {
				/*
				 * Get the series data and apply the settings.
				 */
				try {
					ISeriesData seriesData = scatterSeriesData.getSeriesData();
					ISeriesData optimizedSeriesData = calculateSeries(seriesData, ScrollableChart.NO_COMPRESS_TO_LENGTH);
					IScatterSeriesSettings scatterSeriesSettings = scatterSeriesData.getScatterSeriesSettings();
					scatterSeriesSettings.getSeriesSettingsHighlight(); // Initialize
					ILineSeries scatterSeries = (ILineSeries)createSeries(optimizedSeriesData, scatterSeriesSettings);
					baseChart.applyScatterSeriesSettings(scatterSeries, scatterSeriesSettings);
				} catch(SeriesException e) {
					//
				}
			}
			baseChart.suspendUpdate(false);
			adjustRange(true);
			baseChart.redraw();
		}
	}
}
