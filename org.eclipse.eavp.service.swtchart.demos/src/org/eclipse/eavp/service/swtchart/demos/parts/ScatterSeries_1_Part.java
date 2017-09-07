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

import org.eclipse.eavp.service.swtchart.core.BaseChart;
import org.eclipse.eavp.service.swtchart.core.IChartSettings;
import org.eclipse.eavp.service.swtchart.core.ISeriesData;
import org.eclipse.eavp.service.swtchart.customcharts.PCAChart;
import org.eclipse.eavp.service.swtchart.demos.support.SeriesConverter;
import org.eclipse.eavp.service.swtchart.scattercharts.IScatterSeriesData;
import org.eclipse.eavp.service.swtchart.scattercharts.IScatterSeriesSettings;
import org.eclipse.eavp.service.swtchart.scattercharts.ScatterSeriesData;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.swtchart.ILineSeries.PlotSymbolType;
import org.swtchart.ISeries;

public class ScatterSeries_1_Part extends PCAChart {

	private Color COLOR_RED = Display.getDefault().getSystemColor(SWT.COLOR_RED);
	private Color COLOR_BLUE = Display.getDefault().getSystemColor(SWT.COLOR_BLUE);
	private Color COLOR_MAGENTA = Display.getDefault().getSystemColor(SWT.COLOR_MAGENTA);
	private Color COLOR_CYAN = Display.getDefault().getSystemColor(SWT.COLOR_CYAN);
	private Color COLOR_GRAY = Display.getDefault().getSystemColor(SWT.COLOR_GRAY);
	//
	private int SYMBOL_SIZE = 8;
	//
	private static final int KEY_CODE_R = 114;
	private static final int KEY_CODE_S = 115;
	private boolean isCustomSelection = false;

	@Inject
	public ScatterSeries_1_Part(Composite parent) {
		super(parent, SWT.NONE);
		setBackground(Display.getDefault().getSystemColor(SWT.COLOR_WHITE));
		initialize();
	}

	private void initialize() {

		/*
		 * Chart Settings
		 */
		IChartSettings chartSettings = getChartSettings();
		chartSettings.setCreateMenu(true);
		applySettings(chartSettings);
		/*
		 * Data
		 */
		List<ISeriesData> scatterSeriesList = SeriesConverter.getSeriesScatter(SeriesConverter.SCATTER_SERIES_1);
		List<IScatterSeriesData> scatterSeriesDataList = new ArrayList<IScatterSeriesData>();
		//
		for(ISeriesData seriesData : scatterSeriesList) {
			IScatterSeriesData scatterSeriesData = new ScatterSeriesData(seriesData);
			IScatterSeriesSettings scatterSeriesSettings = scatterSeriesData.getScatterSeriesSettings();
			/*
			 * Set the color and symbol type.
			 */
			double x = seriesData.getXSeries()[0];
			double y = seriesData.getYSeries()[0];
			applySettings(scatterSeriesSettings, x, y, SYMBOL_SIZE);
			scatterSeriesDataList.add(scatterSeriesData);
		}
		/*
		 * Set series.
		 */
		addSeriesData(scatterSeriesDataList);
	}

	@Override
	public void handleKeyUpEvent(Event event) {

		super.handleKeyUpEvent(event);
		isCustomSelection = false;
		//
		if(event.keyCode == KEY_CODE_R) {
			/*
			 * Reset Selection
			 */
			System.out.println("Reset Selection");
			BaseChart baseChart = getBaseChart();
			baseChart.resetSeriesSettings();
		} else if(event.keyCode == KEY_CODE_S) {
			/*
			 * Custom Selection
			 */
			System.out.println("Custom Selection Activated");
			isCustomSelection = true;
		}
	}

	@Override
	public void handleMouseUpEvent(Event event) {

		super.handleMouseUpEvent(event);
		//
		if(isCustomSelection) {
			/*
			 * Set Selection
			 */
			System.out.println("Set Selection");
			//
			BaseChart baseChart = getBaseChart();
			Rectangle plotAreaBounds = baseChart.getPlotArea().getBounds();
			ISeries[] series = baseChart.getSeriesSet().getSeries();
			//
			for(ISeries scatterSeries : series) {
				if(scatterSeries != null) {
					//
					double[] xSeries = scatterSeries.getXSeries();
					double[] ySeries = scatterSeries.getYSeries();
					int size = scatterSeries.getXSeries().length;
					String id = scatterSeries.getId();
					//
					for(int i = 0; i < size; i++) {
						Point point = scatterSeries.getPixelCoordinates(i);
						if(isPointVisible(point, plotAreaBounds)) {
							System.out.println("\t" + xSeries[i] + "\t" + ySeries[i] + "\t" + point + "\t" + id);
							baseChart.selectSeries(id);
						}
					}
				}
			}
			//
			System.out.println("\tSeries Selected:");
			for(String selectedSeriesId : baseChart.getSelectedSeriesIds()) {
				System.out.println("\t\t" + selectedSeriesId);
			}
			//
			baseChart.redraw();
			isCustomSelection = false;
		}
	}

	private boolean isPointVisible(Point point, Rectangle plotAreaBounds) {

		if(point.x >= 0 && point.x <= plotAreaBounds.width && point.y >= 0 && point.y <= plotAreaBounds.height) {
			return true;
		} else {
			return false;
		}
	}

	private void applySettings(IScatterSeriesSettings scatterSeriesSettings, double x, double y, int symbolSize) {

		scatterSeriesSettings.setSymbolSize(SYMBOL_SIZE);
		//
		if(x > 0 && y > 0) {
			scatterSeriesSettings.setSymbolColor(COLOR_RED);
			scatterSeriesSettings.setSymbolType(PlotSymbolType.SQUARE);
		} else if(x > 0 && y < 0) {
			scatterSeriesSettings.setSymbolColor(COLOR_BLUE);
			scatterSeriesSettings.setSymbolType(PlotSymbolType.TRIANGLE);
		} else if(x < 0 && y > 0) {
			scatterSeriesSettings.setSymbolColor(COLOR_MAGENTA);
			scatterSeriesSettings.setSymbolType(PlotSymbolType.DIAMOND);
		} else if(x < 0 && y < 0) {
			scatterSeriesSettings.setSymbolColor(COLOR_CYAN);
			scatterSeriesSettings.setSymbolType(PlotSymbolType.INVERTED_TRIANGLE);
		}
		//
		IScatterSeriesSettings scatterSeriesSettingsHighlight = (IScatterSeriesSettings)scatterSeriesSettings.getSeriesSettingsHighlight();
		scatterSeriesSettingsHighlight.setSymbolColor(COLOR_GRAY);
		scatterSeriesSettingsHighlight.setSymbolType(PlotSymbolType.CIRCLE);
		scatterSeriesSettingsHighlight.setSymbolSize(symbolSize);
	}
}
