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
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.inject.Inject;

import org.eclipse.eavp.service.swtchart.core.IChartSettings;
import org.eclipse.eavp.service.swtchart.core.IPrimaryAxisSettings;
import org.eclipse.eavp.service.swtchart.core.ISeriesData;
import org.eclipse.eavp.service.swtchart.core.RangeRestriction;
import org.eclipse.eavp.service.swtchart.core.SeriesData;
import org.eclipse.eavp.service.swtchart.scattercharts.IScatterSeriesData;
import org.eclipse.eavp.service.swtchart.scattercharts.IScatterSeriesSettings;
import org.eclipse.eavp.service.swtchart.scattercharts.ScatterChart;
import org.eclipse.eavp.service.swtchart.scattercharts.ScatterSeriesData;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.swtchart.ILineSeries.PlotSymbolType;

public class ScatterSeries_3_Part extends ScatterChart {

	@Inject
	public ScatterSeries_3_Part(Composite parent) {
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
		RangeRestriction rangeRestriction = chartSettings.getRangeRestriction();
		rangeRestriction.setFactorExtendMinX(0.25d);
		rangeRestriction.setFactorExtendMaxX(0.1d);
		rangeRestriction.setFactorExtendMinY(0.25d);
		rangeRestriction.setFactorExtendMaxY(0.1d);
		rangeRestriction.setRestrictZoom(false);
		chartSettings.setCreateMenu(true);
		//
		IPrimaryAxisSettings primaryAxisSettingsX = chartSettings.getPrimaryAxisSettingsX();
		primaryAxisSettingsX.setTitle("1st Dimension");
		primaryAxisSettingsX.setDecimalFormat(new DecimalFormat(("0"), new DecimalFormatSymbols(Locale.ENGLISH)));
		primaryAxisSettingsX.setColor(Display.getDefault().getSystemColor(SWT.COLOR_BLACK));
		//
		IPrimaryAxisSettings primaryAxisSettingsY = chartSettings.getPrimaryAxisSettingsY();
		primaryAxisSettingsY.setTitle("2nd Dimension");
		primaryAxisSettingsY.setDecimalFormat(new DecimalFormat(("0.000"), new DecimalFormatSymbols(Locale.ENGLISH)));
		primaryAxisSettingsY.setColor(Display.getDefault().getSystemColor(SWT.COLOR_BLACK));
		//
		applySettings(chartSettings);
		/*
		 * Create series.
		 */
		Map<Integer, Color> colors = new HashMap<Integer, Color>();
		colors.put(1, Display.getDefault().getSystemColor(SWT.COLOR_RED));
		colors.put(2, Display.getDefault().getSystemColor(SWT.COLOR_GRAY));
		colors.put(3, Display.getDefault().getSystemColor(SWT.COLOR_GREEN));
		colors.put(4, Display.getDefault().getSystemColor(SWT.COLOR_YELLOW));
		colors.put(5, Display.getDefault().getSystemColor(SWT.COLOR_CYAN));
		colors.put(6, Display.getDefault().getSystemColor(SWT.COLOR_BLUE));
		colors.put(7, Display.getDefault().getSystemColor(SWT.COLOR_BLACK));
		colors.put(8, Display.getDefault().getSystemColor(SWT.COLOR_DARK_MAGENTA));
		colors.put(9, Display.getDefault().getSystemColor(SWT.COLOR_DARK_CYAN));
		colors.put(10, Display.getDefault().getSystemColor(SWT.COLOR_DARK_GREEN));
		colors.put(11, Display.getDefault().getSystemColor(SWT.COLOR_DARK_RED));
		//
		List<IScatterSeriesData> scatterSeriesDataList = new ArrayList<IScatterSeriesData>();
		for(int i = 1; i <= 5; i++) {
			for(int j = 1; j <= 20; j++) {
				String id = "Series_" + i + "-" + j;
				int y = getRandomValue(10.0d);
				double[] xSeries = new double[]{i};
				double[] ySeries = new double[]{y};
				ISeriesData seriesData = new SeriesData(xSeries, ySeries, id);
				IScatterSeriesData scatterSeriesData = new ScatterSeriesData(seriesData);
				IScatterSeriesSettings scatterSeriesSettings = scatterSeriesData.getScatterSeriesSettings();
				scatterSeriesSettings.setDescription(id);
				scatterSeriesSettings.setSymbolSize(getRandomValue(30.0d));
				scatterSeriesSettings.setSymbolColor(colors.get(y));
				scatterSeriesSettings.setSymbolType(PlotSymbolType.CIRCLE);
				scatterSeriesDataList.add(scatterSeriesData);
			}
		}
		addSeriesData(scatterSeriesDataList);
	}

	private int getRandomValue(double multiplier) {

		return (int)(Math.random() * multiplier + 1);
	}
}
