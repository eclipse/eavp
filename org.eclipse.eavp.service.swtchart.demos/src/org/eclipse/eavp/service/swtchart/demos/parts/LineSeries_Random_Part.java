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

import org.eclipse.eavp.service.swtchart.core.ColorAndFormatSupport;
import org.eclipse.eavp.service.swtchart.core.ISeriesData;
import org.eclipse.eavp.service.swtchart.core.SeriesData;
import org.eclipse.eavp.service.swtchart.customcharts.ChromatogramChart;
import org.eclipse.eavp.service.swtchart.linecharts.ILineSeriesData;
import org.eclipse.eavp.service.swtchart.linecharts.ILineSeriesSettings;
import org.eclipse.eavp.service.swtchart.linecharts.LineSeriesData;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;

public class LineSeries_Random_Part extends Composite {

	private static final String ID = "LINE_SERIES_RANDOM";
	private static int x = 1;
	private static int xDelta = 20;
	//
	private Button buttonStart;
	private ChromatogramChart chromatogramChart;

	@Inject
	public LineSeries_Random_Part(Composite parent) {
		super(parent, SWT.NONE);
		setBackground(ColorAndFormatSupport.COLOR_WHITE);
		try {
			initialize();
		} catch(Exception e) {
			System.out.println(e);
		}
	}

	private void initialize() throws Exception {

		this.setLayout(new FillLayout());
		Composite composite = new Composite(this, SWT.NONE);
		composite.setLayout(new GridLayout(1, false));
		//
		buttonStart = new Button(composite, SWT.PUSH);
		buttonStart.setText("Push to record new segments");
		buttonStart.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		buttonStart.addSelectionListener(new SelectionAdapter() {

			@Override
			public void widgetSelected(SelectionEvent e) {

				Display.getDefault().asyncExec(new Runnable() {

					@Override
					public void run() {

						chromatogramChart.appendSeries(getRandomSeriesData());
					}
				});
			}
		});
		/*
		 * Create series.
		 */
		chromatogramChart = new ChromatogramChart(composite, SWT.BORDER);
		chromatogramChart.setLayoutData(new GridData(GridData.FILL_BOTH));
		//
		List<ILineSeriesData> lineSeriesDataList = new ArrayList<ILineSeriesData>();
		ISeriesData seriesData = getRandomSeriesData();
		ILineSeriesData lineSeriesData = new LineSeriesData(seriesData);
		ILineSeriesSettings lineSerieSettings = lineSeriesData.getLineSeriesSettings();
		lineSerieSettings.setLineColor(ColorAndFormatSupport.COLOR_RED);
		lineSerieSettings.setEnableArea(true);
		lineSeriesDataList.add(lineSeriesData);
		chromatogramChart.addSeriesData(lineSeriesDataList);
	}

	private static ISeriesData getRandomSeriesData() {

		int length = 1000;
		double[] xSeries = new double[length];
		double[] ySeries = new double[length];
		//
		for(int i = 0; i < length; i++) {
			xSeries[i] = x;
			ySeries[i] = Math.pow(Math.random() * 1000.0d, 2);
			x += xDelta;
		}
		return new SeriesData(xSeries, ySeries, ID);
	}
}
