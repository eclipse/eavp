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
import java.util.List;
import java.util.Locale;

import javax.inject.Inject;

import org.eclipse.eavp.service.swtchart.axisconverter.RelativeIntensityConverter;
import org.eclipse.eavp.service.swtchart.core.BaseChart;
import org.eclipse.eavp.service.swtchart.core.IChartSettings;
import org.eclipse.eavp.service.swtchart.core.ICustomSelectionHandler;
import org.eclipse.eavp.service.swtchart.core.IExtendedChart;
import org.eclipse.eavp.service.swtchart.core.IPrimaryAxisSettings;
import org.eclipse.eavp.service.swtchart.core.ISecondaryAxisSettings;
import org.eclipse.eavp.service.swtchart.core.ISeriesData;
import org.eclipse.eavp.service.swtchart.core.SecondaryAxisSettings;
import org.eclipse.eavp.service.swtchart.demos.Activator;
import org.eclipse.eavp.service.swtchart.demos.support.SeriesConverter;
import org.eclipse.eavp.service.swtchart.linecharts.ILineSeriesData;
import org.eclipse.eavp.service.swtchart.linecharts.ILineSeriesSettings;
import org.eclipse.eavp.service.swtchart.linecharts.LineChart;
import org.eclipse.eavp.service.swtchart.linecharts.LineSeriesData;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.swtchart.IAxis.Position;
import org.swtchart.LineStyle;
import org.swtchart.Range;

public class LineSeries_Selection_Part extends Composite {

	private Text textRangeXStart;
	private Text textRangeXStop;
	private Text textRangeYStart;
	private Text textRangeYStop;
	private Text textX;
	private Text textY;
	//
	private LineChart lineChart;

	@Inject
	public LineSeries_Selection_Part(Composite parent) {
		super(parent, SWT.NONE);
		try {
			initialize();
		} catch(Exception e) {
			System.out.println(e);
		}
	}

	@Override
	public void dispose() {

		super.dispose();
	}

	private void initialize() throws Exception {

		this.setLayout(new GridLayout(1, true));
		this.setBackground(Display.getDefault().getSystemColor(SWT.COLOR_WHITE));
		//
		Composite compositeInfo = new Composite(this, SWT.NONE);
		GridData gridDataComposite = new GridData(GridData.FILL_HORIZONTAL);
		gridDataComposite.horizontalAlignment = SWT.BEGINNING;
		compositeInfo.setLayoutData(gridDataComposite);
		compositeInfo.setLayout(new GridLayout(13, false));
		//
		createLabel(compositeInfo, "X-Start:");
		textRangeXStart = createText(compositeInfo);
		createLabel(compositeInfo, "X-Stop:");
		textRangeXStop = createText(compositeInfo);
		createLabel(compositeInfo, "Y-Start:");
		textRangeYStart = createText(compositeInfo);
		createLabel(compositeInfo, "Y-Stop:");
		textRangeYStop = createText(compositeInfo);
		createLabel(compositeInfo, "X:");
		textX = createText(compositeInfo);
		createLabel(compositeInfo, "Y:");
		textY = createText(compositeInfo);
		createButtonReset(compositeInfo);
		//
		lineChart = new LineChart(this, SWT.NONE);
		lineChart.setLayoutData(new GridData(GridData.FILL_BOTH));
		lineChart.getBaseChart().addCustomRangeSelectionHandler(new ICustomSelectionHandler() {

			@Override
			public void handleUserSelection(Event event) {

				BaseChart baseChart = lineChart.getBaseChart();
				Range rangeX = baseChart.getAxisSet().getXAxis(BaseChart.ID_PRIMARY_X_AXIS).getRange();
				Range rangeY = baseChart.getAxisSet().getYAxis(BaseChart.ID_PRIMARY_Y_AXIS).getRange();
				DecimalFormat decimalFormatX = baseChart.getDecimalFormat(IExtendedChart.X_AXIS, BaseChart.ID_PRIMARY_X_AXIS);
				DecimalFormat decimalFormatY = baseChart.getDecimalFormat(IExtendedChart.Y_AXIS, BaseChart.ID_PRIMARY_Y_AXIS);
				textRangeXStart.setText(decimalFormatX.format(rangeX.lower));
				textRangeXStop.setText(decimalFormatX.format(rangeX.upper));
				textRangeYStart.setText(decimalFormatY.format(rangeY.lower));
				textRangeYStop.setText(decimalFormatY.format(rangeY.upper));
			}
		});
		lineChart.getBaseChart().addCustomPointSelectionHandler(new ICustomSelectionHandler() {

			@Override
			public void handleUserSelection(Event event) {

				BaseChart baseChart = lineChart.getBaseChart();
				double x = baseChart.getSelectedPrimaryAxisValue(event.x, IExtendedChart.X_AXIS);
				double y = baseChart.getSelectedPrimaryAxisValue(event.y, IExtendedChart.Y_AXIS);
				DecimalFormat decimalFormatX = baseChart.getDecimalFormat(IExtendedChart.X_AXIS, BaseChart.ID_PRIMARY_X_AXIS);
				DecimalFormat decimalFormatY = baseChart.getDecimalFormat(IExtendedChart.Y_AXIS, BaseChart.ID_PRIMARY_Y_AXIS);
				textX.setText(decimalFormatX.format(x));
				textY.setText(decimalFormatY.format(y));
			}
		});
		applyChartSettings();
	}

	private void createLabel(Composite parent, String text) {

		Label label = new Label(parent, SWT.NONE);
		label.setText(text);
	}

	private Text createText(Composite parent) {

		Text text = new Text(parent, SWT.BORDER);
		text.setText("");
		GridData gridData = new GridData(GridData.FILL_HORIZONTAL);
		gridData.widthHint = 150;
		text.setLayoutData(gridData);
		return text;
	}

	private void createButtonReset(Composite parent) {

		Button button = new Button(parent, SWT.PUSH);
		button.setToolTipText("Reset the data");
		button.setText(Activator.getDefault() != null ? "" : "Reset");
		button.setImage(Activator.getDefault() != null ? Activator.getDefault().getImage(Activator.ICON_RESET) : null);
		button.addSelectionListener(new SelectionAdapter() {

			@Override
			public void widgetSelected(SelectionEvent e) {

				textRangeXStart.setText("");
				textRangeXStop.setText("");
				textRangeYStart.setText("");
				textRangeYStop.setText("");
				textX.setText("");
				textY.setText("");
				lineChart.adjustRange(true);
				lineChart.redraw();
			}
		});
	}

	private void applyChartSettings() throws Exception {

		/*
		 * Chart Settings
		 */
		IChartSettings chartSettings = lineChart.getChartSettings();
		chartSettings.setCreateMenu(true);
		/*
		 * Primary X-Axis
		 */
		IPrimaryAxisSettings primaryAxisSettingsX = chartSettings.getPrimaryAxisSettingsX();
		primaryAxisSettingsX.setTitle("Retention Time (milliseconds)");
		primaryAxisSettingsX.setDecimalFormat(new DecimalFormat(("0.0##"), new DecimalFormatSymbols(Locale.ENGLISH)));
		primaryAxisSettingsX.setColor(Display.getDefault().getSystemColor(SWT.COLOR_BLACK));
		/*
		 * Primary Y-Axis
		 */
		IPrimaryAxisSettings primaryAxisSettingsY = chartSettings.getPrimaryAxisSettingsY();
		primaryAxisSettingsY.setTitle("Intensity");
		primaryAxisSettingsY.setDecimalFormat(new DecimalFormat(("0.0#E0"), new DecimalFormatSymbols(Locale.ENGLISH)));
		primaryAxisSettingsY.setColor(Display.getDefault().getSystemColor(SWT.COLOR_BLACK));
		primaryAxisSettingsY.setGridLineStyle(LineStyle.NONE);
		/*
		 * Secondary Y-Axes
		 */
		ISecondaryAxisSettings secondaryAxisSettingsY1 = new SecondaryAxisSettings("Relative Intensity [%]", new RelativeIntensityConverter(SWT.VERTICAL, true));
		secondaryAxisSettingsY1.setPosition(Position.Secondary);
		secondaryAxisSettingsY1.setDecimalFormat(new DecimalFormat(("0.00"), new DecimalFormatSymbols(Locale.ENGLISH)));
		secondaryAxisSettingsY1.setColor(Display.getDefault().getSystemColor(SWT.COLOR_BLACK));
		chartSettings.getSecondaryAxisSettingsListY().add(secondaryAxisSettingsY1);
		//
		lineChart.applySettings(chartSettings);
		/*
		 * Create series.
		 */
		List<ILineSeriesData> lineSeriesDataList = new ArrayList<ILineSeriesData>();
		ISeriesData seriesData = SeriesConverter.getSeriesXY(SeriesConverter.LINE_SERIES_3);
		//
		ILineSeriesData lineSeriesData = new LineSeriesData(seriesData);
		ILineSeriesSettings lineSerieSettings = lineSeriesData.getLineSeriesSettings();
		lineSerieSettings.setEnableArea(true);
		lineSeriesDataList.add(lineSeriesData);
		/*
		 * Set series.
		 */
		lineChart.addSeriesData(lineSeriesDataList);
	}
}
