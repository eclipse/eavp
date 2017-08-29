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

import org.eclipse.eavp.service.swtchart.core.BaseChart;
import org.eclipse.eavp.service.swtchart.core.IChartSettings;
import org.eclipse.eavp.service.swtchart.core.ISeriesData;
import org.eclipse.eavp.service.swtchart.customcharts.ChromatogramChart;
import org.eclipse.eavp.service.swtchart.demos.Activator;
import org.eclipse.eavp.service.swtchart.demos.support.SeriesConverter;
import org.eclipse.eavp.service.swtchart.linecharts.ILineSeriesData;
import org.eclipse.eavp.service.swtchart.linecharts.ILineSeriesSettings;
import org.eclipse.eavp.service.swtchart.linecharts.LineChart;
import org.eclipse.eavp.service.swtchart.linecharts.LineSeriesData;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;

public class LineSeries_Edit_Part extends Composite {

	private Combo comboSelectSeries;
	private ChromatogramChart chromatogramChart;

	@Inject
	public LineSeries_Edit_Part(Composite parent) {
		super(parent, SWT.NONE);
		setBackground(Display.getDefault().getSystemColor(SWT.COLOR_WHITE));
		try {
			initialize();
		} catch(Exception e) {
			System.out.println(e);
		}
	}

	private void initialize() throws Exception {

		this.setLayout(new GridLayout(1, true));
		/*
		 * Buttons
		 */
		Composite compositeButtons = new Composite(this, SWT.NONE);
		GridData gridDataComposite = new GridData(GridData.FILL_HORIZONTAL);
		gridDataComposite.horizontalAlignment = SWT.BEGINNING;
		compositeButtons.setLayoutData(gridDataComposite);
		compositeButtons.setLayout(new GridLayout(7, false));
		//
		createLabel(compositeButtons);
		createCombo(compositeButtons);
		createButtonUp(compositeButtons);
		createButtonDown(compositeButtons);
		createButtonLeft(compositeButtons);
		createButtonRight(compositeButtons);
		createButtonReset(compositeButtons);
		//
		createChart(this);
	}

	private void createLabel(Composite parent) {

		Label label = new Label(parent, SWT.NONE);
		label.setText("Select a serie:");
	}

	private void createCombo(Composite parent) {

		comboSelectSeries = new Combo(parent, SWT.BORDER | SWT.READ_ONLY);
		comboSelectSeries.addSelectionListener(new SelectionAdapter() {

			@Override
			public void widgetSelected(SelectionEvent e) {

				BaseChart baseChart = chromatogramChart.getBaseChart();
				String selectedSeriesId = comboSelectSeries.getText().trim();
				baseChart.resetSelectedSeries();
				baseChart.selectSeries(selectedSeriesId);
			}
		});
	}

	private void createButtonUp(Composite parent) {

		Button button = new Button(parent, SWT.PUSH);
		button.setToolTipText("Move Up");
		button.setText(Activator.getDefault() != null ? "" : "Move Up");
		button.setImage(Activator.getDefault() != null ? Activator.getDefault().getImage(Activator.ICON_UP) : null);
		button.addSelectionListener(new SelectionAdapter() {

			@Override
			public void widgetSelected(SelectionEvent e) {

				BaseChart baseChart = chromatogramChart.getBaseChart();
				double shiftY = baseChart.getMaxY() / 30.0d;
				String selectedSeriesId = comboSelectSeries.getText().trim();
				baseChart.shiftSeries(selectedSeriesId, 0.0d, shiftY);
				baseChart.redraw();
			}
		});
	}

	private void createButtonDown(Composite parent) {

		Button button = new Button(parent, SWT.PUSH);
		button.setToolTipText("Move Down");
		button.setText(Activator.getDefault() != null ? "" : "Move Down");
		button.setImage(Activator.getDefault() != null ? Activator.getDefault().getImage(Activator.ICON_DOWN) : null);
		button.addSelectionListener(new SelectionAdapter() {

			@Override
			public void widgetSelected(SelectionEvent e) {

				BaseChart baseChart = chromatogramChart.getBaseChart();
				double shiftY = (baseChart.getMaxY() / 30.0d) * -1.0d;
				String selectedSeriesId = comboSelectSeries.getText().trim();
				baseChart.shiftSeries(selectedSeriesId, 0.0d, shiftY);
				baseChart.redraw();
			}
		});
	}

	private void createButtonLeft(Composite parent) {

		Button button = new Button(parent, SWT.PUSH);
		button.setToolTipText("Move Left");
		button.setText(Activator.getDefault() != null ? "" : "Move Left");
		button.setImage(Activator.getDefault() != null ? Activator.getDefault().getImage(Activator.ICON_LEFT) : null);
		button.addSelectionListener(new SelectionAdapter() {

			@Override
			public void widgetSelected(SelectionEvent e) {

				BaseChart baseChart = chromatogramChart.getBaseChart();
				double shiftX = (baseChart.getMaxX() / 30.0d) * -1.0d;
				String selectedSeriesId = comboSelectSeries.getText().trim();
				baseChart.shiftSeries(selectedSeriesId, shiftX, 0.0d);
				baseChart.redraw();
			}
		});
	}

	private void createButtonRight(Composite parent) {

		Button button = new Button(parent, SWT.PUSH);
		button.setToolTipText("Move Right");
		button.setText(Activator.getDefault() != null ? "" : "Move Right");
		button.setImage(Activator.getDefault() != null ? Activator.getDefault().getImage(Activator.ICON_RIGHT) : null);
		button.addSelectionListener(new SelectionAdapter() {

			@Override
			public void widgetSelected(SelectionEvent e) {

				BaseChart baseChart = chromatogramChart.getBaseChart();
				double shiftX = baseChart.getMaxX() / 30.0d;
				String selectedSeriesId = comboSelectSeries.getText().trim();
				baseChart.shiftSeries(selectedSeriesId, shiftX, 0.0d);
				baseChart.redraw();
			}
		});
	}

	private void createButtonReset(Composite parent) {

		Button button = new Button(parent, SWT.PUSH);
		button.setToolTipText("Reset the data");
		button.setText(Activator.getDefault() != null ? "" : "Reset");
		button.setImage(Activator.getDefault() != null ? Activator.getDefault().getImage(Activator.ICON_RESET) : null);
		button.addSelectionListener(new SelectionAdapter() {

			@Override
			public void widgetSelected(SelectionEvent e) {

				loadChromatogramData();
			}
		});
	}

	private void createChart(Composite parent) {

		chromatogramChart = new ChromatogramChart(parent, SWT.BORDER);
		chromatogramChart.setLayoutData(new GridData(GridData.FILL_BOTH));
		//
		IChartSettings chartSettings = chromatogramChart.getChartSettings();
		chartSettings.setCreateMenu(true);
		chromatogramChart.applySettings(chartSettings);
		//
		loadChromatogramData();
	}

	private void loadChromatogramData() {

		chromatogramChart.deleteSeries();
		//
		Map<Integer, Color> colors = new HashMap<Integer, Color>();
		colors.put(1, Display.getDefault().getSystemColor(SWT.COLOR_RED));
		colors.put(2, Display.getDefault().getSystemColor(SWT.COLOR_BLACK));
		colors.put(3, Display.getDefault().getSystemColor(SWT.COLOR_GRAY));
		colors.put(4, Display.getDefault().getSystemColor(SWT.COLOR_DARK_RED));
		colors.put(5, Display.getDefault().getSystemColor(SWT.COLOR_GRAY));
		//
		String[] items = new String[6];
		items[0] = "No Selection";
		//
		List<ILineSeriesData> lineSeriesDataList = new ArrayList<ILineSeriesData>();
		for(int i = 1; i <= 5; i++) {
			ISeriesData seriesData = SeriesConverter.getSeriesXY(SeriesConverter.LINE_SERIES + "4_" + i);
			items[i] = seriesData.getId();
			ILineSeriesData lineSeriesData = new LineSeriesData(seriesData);
			ILineSeriesSettings lineSerieSettings = lineSeriesData.getLineSeriesSettings();
			lineSerieSettings.setLineColor(colors.get(i));
			lineSerieSettings.setEnableArea(false);
			lineSeriesDataList.add(lineSeriesData);
		}
		chromatogramChart.addSeriesData(lineSeriesDataList, LineChart.MEDIUM_COMPRESSION);
		comboSelectSeries.setItems(items);
		comboSelectSeries.select(0);
	}
}
