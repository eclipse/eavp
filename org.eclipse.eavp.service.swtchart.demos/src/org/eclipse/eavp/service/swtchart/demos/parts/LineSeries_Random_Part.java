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
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;

public class LineSeries_Random_Part extends Composite {

	private static final String ID = "LINE_SERIES_RANDOM";
	private static int x = 0;
	private static int xDelta = 10;
	//
	private Button buttonStart;
	private Button buttonStop;
	private Button buttonReset;
	private ChromatogramChart chromatogramChart;
	//
	private Display display = Display.getDefault();
	private Acquisition acquisition;
	private Recording recording;

	@Inject
	public LineSeries_Random_Part(Composite parent) {
		super(parent, SWT.NONE);
		setBackground(ColorAndFormatSupport.COLOR_WHITE);
		try {
			initialize();
			acquisition = new Acquisition();
			recording = new Recording();
		} catch(Exception e) {
			System.out.println(e);
		}
	}

	private class Acquisition {

		private boolean recordData = false;

		public boolean isRecordData() {

			return recordData;
		}

		public void setRecordData(boolean recordData) {

			this.recordData = recordData;
		}
	}

	private class Recording implements Runnable {

		@Override
		public void run() {

			if(acquisition.isRecordData()) {
				chromatogramChart.appendSeries(getRandomSeriesData());
			}
			display.timerExec(500, this);
		}
	}

	private void initialize() throws Exception {

		this.setLayout(new GridLayout(1, true));
		/*
		 * Buttons
		 */
		Composite compositeButtons = new Composite(this, SWT.NONE);
		GridData gridDataComposite = new GridData(GridData.FILL_HORIZONTAL);
		gridDataComposite.horizontalAlignment = SWT.END;
		compositeButtons.setLayoutData(gridDataComposite);
		compositeButtons.setLayout(new GridLayout(3, false));
		//
		buttonStart = new Button(compositeButtons, SWT.PUSH);
		buttonStart.setText("Start");
		buttonStart.setEnabled(true);
		buttonStart.addSelectionListener(new SelectionAdapter() {

			@Override
			public void widgetSelected(SelectionEvent e) {

				acquisition.setRecordData(true);
				display.asyncExec(recording);
				//
				setButtonsEnabled(true);
			}
		});
		//
		buttonStop = new Button(compositeButtons, SWT.PUSH);
		buttonStop.setText("Stop");
		buttonStop.setEnabled(false);
		buttonStop.addSelectionListener(new SelectionAdapter() {

			@Override
			public void widgetSelected(SelectionEvent e) {

				acquisition.setRecordData(false);
				display.timerExec(-1, recording);
				//
				setButtonsEnabled(false);
			}
		});
		//
		buttonReset = new Button(compositeButtons, SWT.PUSH);
		buttonReset.setText("Reset");
		buttonReset.setEnabled(true);
		buttonReset.addSelectionListener(new SelectionAdapter() {

			@Override
			public void widgetSelected(SelectionEvent e) {

				acquisition.setRecordData(false);
				display.timerExec(-1, recording);
				//
				chromatogramChart.deleteSeries();
				x = 0;
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
		});
		/*
		 * Chart
		 */
		chromatogramChart = new ChromatogramChart(this, SWT.BORDER);
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

	private void setButtonsEnabled(boolean recordData) {

		buttonStart.setEnabled(!recordData);
		buttonStop.setEnabled(recordData);
		buttonReset.setEnabled(!recordData);
	}

	private static ISeriesData getRandomSeriesData() {

		int length = 101;
		double[] xSeries = new double[length];
		double[] ySeries = new double[length];
		//
		double a = Math.random(); // height
		double i = -5.0d;
		double iDelta = 0.1d;
		//
		for(int j = 0; j < length; j++) {
			xSeries[j] = x;
			ySeries[j] = a * Math.exp(-i * i / 2) / Math.sqrt(2 * Math.PI);
			x += xDelta;
			i += iDelta;
		}
		return new SeriesData(xSeries, ySeries, ID);
	}
}
