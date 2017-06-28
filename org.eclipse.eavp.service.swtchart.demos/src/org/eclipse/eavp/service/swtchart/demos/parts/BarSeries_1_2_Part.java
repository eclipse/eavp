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

import org.eclipse.eavp.service.swtchart.barcharts.BarSeriesData;
import org.eclipse.eavp.service.swtchart.barcharts.IBarSeriesData;
import org.eclipse.eavp.service.swtchart.barcharts.IBarSeriesSettings;
import org.eclipse.eavp.service.swtchart.core.ColorAndFormatSupport;
import org.eclipse.eavp.service.swtchart.core.ISeriesData;
import org.eclipse.eavp.service.swtchart.customcharts.MassSpectrumChart;
import org.eclipse.eavp.service.swtchart.demos.support.SeriesConverter;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;

public class BarSeries_1_2_Part extends MassSpectrumChart {

	@Inject
	public BarSeries_1_2_Part(Composite parent) {
		super(parent, SWT.NONE);
		setBackground(ColorAndFormatSupport.COLOR_WHITE);
		initialize();
	}

	private void initialize() {

		setNumberOfHighestIntensitiesToLabel(5);
		setLabelOption(LabelOption.EXACT);
		setCustomLabels(null);
		/*
		 * Create series.
		 */
		List<IBarSeriesData> barSeriesDataList = new ArrayList<IBarSeriesData>();
		ISeriesData seriesData = SeriesConverter.getSeriesXY(SeriesConverter.BAR_SERIES_1);
		//
		IBarSeriesData barSeriesData = new BarSeriesData(seriesData);
		IBarSeriesSettings barSeriesSettings = barSeriesData.getBarSeriesSettings();
		barSeriesSettings.setDescription("");
		barSeriesDataList.add(barSeriesData);
		/*
		 * Set series.
		 */
		addSeriesData(barSeriesDataList);
	}
}
