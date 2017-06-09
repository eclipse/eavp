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
package org.eclipse.eavp.service.swtchart.barcharts;

import java.util.List;

import org.eclipse.eavp.service.swtchart.core.BaseChart;
import org.eclipse.eavp.service.swtchart.core.ISeriesData;
import org.eclipse.eavp.service.swtchart.core.ScrollableChart;
import org.eclipse.eavp.service.swtchart.core.SeriesContainer;
import org.eclipse.eavp.service.swtchart.exceptions.SeriesException;
import org.eclipse.swt.widgets.Composite;
import org.swtchart.IBarSeries;
import org.swtchart.IBarSeries.BarWidthStyle;
import org.swtchart.ISeries.SeriesType;

public class BarChart extends ScrollableChart {

	private static final int LENGTH_HINT_DATA_POINTS = 5000;

	public BarChart(Composite parent, int style) {
		super(parent, style);
	}

	/**
	 * BarWidthStyle.STRETCHED will be used automatically instead of BarWidthStyle.FIXED
	 * if the series data is too large. This leads to a better performance.
	 * 
	 * @param barSeriesDataList
	 */
	public void addSeriesData(List<IBarSeriesData> barSeriesDataList, boolean compressSeries) {

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
					SeriesContainer seriesContainer = calculateSeries(seriesData.getXSeries(), seriesData.getYSeries(), LENGTH_HINT_DATA_POINTS, compressSeries);
					IBarSeries barSeries = (IBarSeries)createSeries(SeriesType.BAR, seriesContainer.getXSeries(), seriesContainer.getYSeries(), seriesData.getId());
					//
					IBarSeriesSettings barSeriesSettings = barSeriesData.getBarSeriesSettings();
					barSeries.setDescription(barSeriesSettings.getDescription());
					barSeries.setVisible(barSeriesSettings.isVisible());
					barSeries.setVisibleInLegend(barSeriesSettings.isVisibleInLegend());
					barSeries.setBarColor(barSeriesSettings.getBarColor());
					barSeries.setBarPadding(barSeriesSettings.getBarPadding());
					barSeries.setBarWidth(barSeriesSettings.getBarWidth());
					/*
					 * Automatically use stretched if it is a large data set.
					 */
					if(isLargeDataSet(seriesContainer.getXSeries(), seriesContainer.getYSeries(), LENGTH_HINT_DATA_POINTS)) {
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
