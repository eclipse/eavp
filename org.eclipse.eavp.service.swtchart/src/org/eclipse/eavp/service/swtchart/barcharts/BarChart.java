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
package org.eclipse.eavp.service.swtchart.barcharts;

import java.util.List;

import org.eclipse.eavp.service.swtchart.core.BaseChart;
import org.eclipse.eavp.service.swtchart.core.ISeriesData;
import org.eclipse.eavp.service.swtchart.core.ScrollableChart;
import org.eclipse.eavp.service.swtchart.exceptions.SeriesException;
import org.eclipse.swt.widgets.Composite;
import org.swtchart.IBarSeries;
import org.swtchart.IBarSeries.BarWidthStyle;

public class BarChart extends ScrollableChart {

	private static final int LENGTH_HINT_DATA_POINTS = 5000;

	public BarChart() {
		super();
	}

	public BarChart(Composite parent, int style) {
		super(parent, style);
	}

	public void addSeriesData(List<IBarSeriesData> barSeriesDataList) {

		addSeriesData(barSeriesDataList, Integer.MAX_VALUE);
	}

	/**
	 * BarWidthStyle.STRETCHED will be used automatically instead of BarWidthStyle.FIXED
	 * if the series data is too large. This leads to a better performance.
	 * 
	 * @param barSeriesDataList
	 */
	public void addSeriesData(List<IBarSeriesData> barSeriesDataList, int compressToLength) {

		/*
		 * Suspend the update when adding new data to improve the performance.
		 */
		if(barSeriesDataList != null && barSeriesDataList.size() > 0) {
			BaseChart baseChart = getBaseChart();
			baseChart.suspendUpdate(true);
			for(IBarSeriesData barSeriesData : barSeriesDataList) {
				/*
				 * Get the series data and apply the settings.
				 */
				try {
					ISeriesData seriesData = barSeriesData.getSeriesData();
					ISeriesData optimizedSeriesData = calculateSeries(seriesData, compressToLength);
					IBarSeriesSettings barSeriesSettings = barSeriesData.getBarSeriesSettings();
					barSeriesSettings.getSeriesSettingsHighlight(); // Initialize
					IBarSeries barSeries = (IBarSeries)createSeries(optimizedSeriesData, barSeriesSettings);
					baseChart.applyBarSeriesSettings(barSeries, barSeriesSettings);
					/*
					 * Automatically use stretched if it is a large data set.
					 */
					if(isLargeDataSet(optimizedSeriesData.getXSeries(), optimizedSeriesData.getYSeries(), LENGTH_HINT_DATA_POINTS)) {
						barSeries.setBarWidthStyle(BarWidthStyle.STRETCHED);
					} else {
						barSeries.setBarWidthStyle(barSeriesSettings.getBarWidthStyle());
					}
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
