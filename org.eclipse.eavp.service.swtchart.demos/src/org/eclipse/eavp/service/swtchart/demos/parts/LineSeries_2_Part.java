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

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.inject.Inject;

import org.eclipse.eavp.service.swtchart.core.IChartSettings;
import org.eclipse.eavp.service.swtchart.core.IPrimaryAxisSettings;
import org.eclipse.eavp.service.swtchart.core.ISecondaryAxisSettings;
import org.eclipse.eavp.service.swtchart.core.ISeriesData;
import org.eclipse.eavp.service.swtchart.core.SecondaryAxisSettings;
import org.eclipse.eavp.service.swtchart.demos.converter.ScanToSecondsConverter;
import org.eclipse.eavp.service.swtchart.demos.support.SeriesConverter;
import org.eclipse.eavp.service.swtchart.linecharts.ILineSeriesData;
import org.eclipse.eavp.service.swtchart.linecharts.ILineSeriesSettings;
import org.eclipse.eavp.service.swtchart.linecharts.LineChart;
import org.eclipse.eavp.service.swtchart.linecharts.LineSeriesData;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.swtchart.IAxis.Position;

public class LineSeries_2_Part extends LineChart {

	@Inject
	public LineSeries_2_Part(Composite parent) {
		super(parent, SWT.NONE);
		setBackground(getDisplay().getSystemColor(SWT.COLOR_WHITE));
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
		chartSettings.setCreateMenu(true);
		chartSettings.setOrientation(SWT.HORIZONTAL);
		chartSettings.setHorizontalSliderVisible(true);
		chartSettings.setVerticalSliderVisible(false);
		chartSettings.getRangeRestriction().setZeroX(false);
		chartSettings.getRangeRestriction().setZeroY(false);
		/*
		 * Primary X-Axis
		 */
		IPrimaryAxisSettings primaryAxisSettingsX = chartSettings.getPrimaryAxisSettingsX();
		primaryAxisSettingsX.setTitle("scan");
		primaryAxisSettingsX.setDecimalFormat(new DecimalFormat(("0.0##"), new DecimalFormatSymbols(Locale.ENGLISH)));
		primaryAxisSettingsX.setColor(getDisplay().getSystemColor(SWT.COLOR_BLACK));
		primaryAxisSettingsX.setPosition(Position.Primary);
		primaryAxisSettingsX.setVisible(false);
		/*
		 * Primary Y-Axis
		 */
		IPrimaryAxisSettings primaryAxisSettingsY = chartSettings.getPrimaryAxisSettingsY();
		primaryAxisSettingsY.setTitle("Intensity");
		primaryAxisSettingsY.setDecimalFormat(new DecimalFormat(("0.0#E0"), new DecimalFormatSymbols(Locale.ENGLISH)));
		primaryAxisSettingsY.setColor(getDisplay().getSystemColor(SWT.COLOR_BLACK));
		/*
		 * Secondary X-Axes
		 */
		ISecondaryAxisSettings secondaryAxisSettingsX1 = new SecondaryAxisSettings("t1 (sec)", new ScanToSecondsConverter());
		secondaryAxisSettingsX1.setPosition(Position.Primary);
		secondaryAxisSettingsX1.setDecimalFormat(new DecimalFormat(("0"), new DecimalFormatSymbols(Locale.ENGLISH)));
		secondaryAxisSettingsX1.setColor(getDisplay().getSystemColor(SWT.COLOR_BLACK));
		chartSettings.getSecondaryAxisSettingsListX().add(secondaryAxisSettingsX1);
		//
		applySettings(chartSettings);
		/*
		 * Create series.
		 */
		List<ILineSeriesData> lineSeriesDataList = new ArrayList<ILineSeriesData>();
		ISeriesData seriesData = SeriesConverter.getSeriesXY(SeriesConverter.LINE_SERIES_2);
		//
		ILineSeriesData lineSeriesData = new LineSeriesData(seriesData);
		ILineSeriesSettings lineSeriesSettings = lineSeriesData.getLineSeriesSettings();
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
