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

import org.eclipse.eavp.service.swtchart.converter.MillisecondsToMinuteConverter;
import org.eclipse.eavp.service.swtchart.converter.RelativeIntensityConverter;
import org.eclipse.eavp.service.swtchart.core.ColorAndFormatSupport;
import org.eclipse.eavp.service.swtchart.core.IChartSettings;
import org.eclipse.eavp.service.swtchart.core.IPrimaryAxisSettings;
import org.eclipse.eavp.service.swtchart.core.ISecondaryAxisSettings;
import org.eclipse.eavp.service.swtchart.core.ISeriesData;
import org.eclipse.eavp.service.swtchart.core.SecondaryAxisSettings;
import org.eclipse.eavp.service.swtchart.demos.Activator;
import org.eclipse.eavp.service.swtchart.demos.preferences.LineSeriesPreferenceConstants;
import org.eclipse.eavp.service.swtchart.demos.preferences.LineSeriesPreferencePage;
import org.eclipse.eavp.service.swtchart.demos.support.SeriesConverter;
import org.eclipse.eavp.service.swtchart.linecharts.ILineSeriesData;
import org.eclipse.eavp.service.swtchart.linecharts.ILineSeriesSettings;
import org.eclipse.eavp.service.swtchart.linecharts.LineChart;
import org.eclipse.eavp.service.swtchart.linecharts.LineSeriesData;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.preference.IPreferencePage;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.preference.PreferenceConverter;
import org.eclipse.jface.preference.PreferenceDialog;
import org.eclipse.jface.preference.PreferenceManager;
import org.eclipse.jface.preference.PreferenceNode;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.swtchart.IAxis.Position;
import org.swtchart.LineStyle;

public class LineSeries_Preferences_Part extends Composite {

	private LineChart lineChart;

	@Inject
	public LineSeries_Preferences_Part(Composite parent) {
		super(parent, SWT.NONE);
		setBackground(ColorAndFormatSupport.COLOR_WHITE);
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
		gridDataComposite.horizontalAlignment = SWT.END;
		compositeButtons.setLayoutData(gridDataComposite);
		compositeButtons.setLayout(new GridLayout(2, false));
		//
		Button buttonPreferences = new Button(compositeButtons, SWT.PUSH);
		buttonPreferences.setText("Preferences");
		buttonPreferences.addSelectionListener(new SelectionAdapter() {

			@Override
			public void widgetSelected(SelectionEvent e) {

				IPreferencePage preferencePage = new LineSeriesPreferencePage();
				preferencePage.setTitle("Line Series Part Preferences");
				PreferenceManager preferenceManager = new PreferenceManager();
				preferenceManager.addToRoot(new PreferenceNode("1", preferencePage));
				//
				PreferenceDialog preferenceDialog = new PreferenceDialog(Display.getCurrent().getActiveShell(), preferenceManager);
				preferenceDialog.create();
				preferenceDialog.setMessage("Settings");
				if(preferenceDialog.open() == PreferenceDialog.OK) {
					MessageDialog.openInformation(Display.getDefault().getActiveShell(), "Settings", "The settings have been set successfully. Please use the load button to refresh the chart.");
				}
			}
		});
		//
		Button buttonLoad = new Button(compositeButtons, SWT.PUSH);
		buttonLoad.setText("Load Settings");
		buttonLoad.addSelectionListener(new SelectionAdapter() {

			@Override
			public void widgetSelected(SelectionEvent e) {

				try {
					MessageDialog.openInformation(Display.getDefault().getActiveShell(), "Settings", "Work in progress ...");
					// applyChartSettings();
					// applySeriesSettings();
				} catch(Exception e1) {
					System.out.println(e1);
				}
			}
		});
		//
		lineChart = new LineChart(this, SWT.NONE);
		lineChart.setLayoutData(new GridData(GridData.FILL_BOTH));
		//
		applyChartSettings();
		applySeriesSettings();
	}

	private void applyChartSettings() throws Exception {

		IPreferenceStore preferenceStore = Activator.getDefault().getPreferenceStore();
		//
		IChartSettings chartSettings = lineChart.getChartSettings();
		chartSettings.setEnableRangeUI(preferenceStore.getBoolean(LineSeriesPreferenceConstants.P_ENABLE_RANGE_UI));
		chartSettings.setVerticalSliderVisible(preferenceStore.getBoolean(LineSeriesPreferenceConstants.P_VERTICAL_SLIDER_VISIBLE));
		chartSettings.setHorizontalSliderVisible(preferenceStore.getBoolean(LineSeriesPreferenceConstants.P_HORIZONTAL_SLIDER_VISIBLE));
		chartSettings.setTitle(preferenceStore.getString(LineSeriesPreferenceConstants.P_TITLE));
		chartSettings.setTitleVisible(preferenceStore.getBoolean(LineSeriesPreferenceConstants.P_TITLE_VISIBLE));
		Color colorTitle = getColor(PreferenceConverter.getColor(preferenceStore, LineSeriesPreferenceConstants.P_TITLE_COLOR));
		chartSettings.setTitleColor(colorTitle);
		chartSettings.setLegendPosition(preferenceStore.getInt(LineSeriesPreferenceConstants.P_LEGEND_POSITION));
		chartSettings.setLegendVisible(preferenceStore.getBoolean(LineSeriesPreferenceConstants.P_LEGEND_VISIBLE));
		chartSettings.setOrientation(preferenceStore.getInt(LineSeriesPreferenceConstants.P_ORIENTATION));
		Color colorBackground = getColor(PreferenceConverter.getColor(preferenceStore, LineSeriesPreferenceConstants.P_BACKGROUND));
		chartSettings.setBackground(colorBackground);
		Color colorBackgroundInPlotArea = getColor(PreferenceConverter.getColor(preferenceStore, LineSeriesPreferenceConstants.P_BACKGROUND_IN_PLOT_AREA));
		chartSettings.setBackground(colorBackgroundInPlotArea);
		chartSettings.setEnableCompress(preferenceStore.getBoolean(LineSeriesPreferenceConstants.P_ENABLE_COMPRESS));
		chartSettings.setUseZeroX(preferenceStore.getBoolean(LineSeriesPreferenceConstants.P_USE_ZERO_X));
		chartSettings.setUseZeroY(preferenceStore.getBoolean(LineSeriesPreferenceConstants.P_USE_ZERO_Y));
		chartSettings.setUseRangeRestriction(preferenceStore.getBoolean(LineSeriesPreferenceConstants.P_USE_RANGE_RESTRICTION));
		chartSettings.setFactorExtendMinX(preferenceStore.getDouble(LineSeriesPreferenceConstants.P_FACTOR_EXTEND_MIN_X));
		chartSettings.setFactorExtendMaxX(preferenceStore.getDouble(LineSeriesPreferenceConstants.P_FACTOR_EXTEND_MAX_X));
		chartSettings.setFactorExtendMinY(preferenceStore.getDouble(LineSeriesPreferenceConstants.P_FACTOR_EXTEND_MIN_Y));
		chartSettings.setFactorExtendMaxY(preferenceStore.getDouble(LineSeriesPreferenceConstants.P_FACTOR_EXTEND_MAX_Y));
		chartSettings.setShowPositionMarker(preferenceStore.getBoolean(LineSeriesPreferenceConstants.P_SHOW_POSITION_MARKER));
		chartSettings.setShowCenterMarker(preferenceStore.getBoolean(LineSeriesPreferenceConstants.P_SHOW_CENTER_MARKER));
		chartSettings.setShowPositionLegend(preferenceStore.getBoolean(LineSeriesPreferenceConstants.P_SHOW_POSITION_LEGEND));
		chartSettings.setCreateMenu(preferenceStore.getBoolean(LineSeriesPreferenceConstants.P_CREATE_MENU));
		/*
		 * Primary X-Axis
		 */
		IPrimaryAxisSettings primaryAxisSettingsX = chartSettings.getPrimaryAxisSettingsX();
		primaryAxisSettingsX.setTitle("Retention Time (milliseconds)");
		primaryAxisSettingsX.setDecimalFormat(ColorAndFormatSupport.decimalFormatVariable);
		primaryAxisSettingsX.setColor(ColorAndFormatSupport.COLOR_BLACK);
		primaryAxisSettingsX.setPosition(Position.Secondary);
		primaryAxisSettingsX.setVisible(false);
		primaryAxisSettingsX.setGridLineStyle(LineStyle.NONE);
		/*
		 * Primary Y-Axis
		 */
		IPrimaryAxisSettings primaryAxisSettingsY = chartSettings.getPrimaryAxisSettingsY();
		primaryAxisSettingsY.setTitle("Intensity");
		primaryAxisSettingsY.setDecimalFormat(ColorAndFormatSupport.decimalFormatScientific);
		primaryAxisSettingsY.setColor(ColorAndFormatSupport.COLOR_BLACK);
		primaryAxisSettingsY.setGridLineStyle(LineStyle.NONE);
		/*
		 * Secondary X-Axes
		 */
		chartSettings.getSecondaryAxisSettingsListX().clear();
		ISecondaryAxisSettings secondaryAxisSettingsX = new SecondaryAxisSettings("Minutes", new MillisecondsToMinuteConverter());
		secondaryAxisSettingsX.setPosition(Position.Primary);
		secondaryAxisSettingsX.setDecimalFormat(ColorAndFormatSupport.decimalFormatFixed);
		secondaryAxisSettingsX.setColor(ColorAndFormatSupport.COLOR_BLACK);
		chartSettings.getSecondaryAxisSettingsListX().add(secondaryAxisSettingsX);
		/*
		 * Secondary Y-Axes
		 */
		chartSettings.getSecondaryAxisSettingsListY().clear();
		ISecondaryAxisSettings secondaryAxisSettingsY = new SecondaryAxisSettings("Relative Intensity [%]", new RelativeIntensityConverter(SWT.VERTICAL, true));
		secondaryAxisSettingsY.setPosition(Position.Secondary);
		secondaryAxisSettingsY.setDecimalFormat(ColorAndFormatSupport.decimalFormatFixed);
		secondaryAxisSettingsY.setColor(ColorAndFormatSupport.COLOR_BLACK);
		chartSettings.getSecondaryAxisSettingsListY().add(secondaryAxisSettingsY);
		//
		lineChart.applySettings(chartSettings);
		//
		colorTitle.dispose();
		colorBackground.dispose();
		colorBackgroundInPlotArea.dispose();
	}

	private void applySeriesSettings() {

		List<ILineSeriesData> lineSeriesDataList = new ArrayList<ILineSeriesData>();
		ISeriesData seriesData = SeriesConverter.getSeriesXY(SeriesConverter.LINE_SERIES_3);
		//
		ILineSeriesData lineSeriesData = new LineSeriesData(seriesData);
		ILineSeriesSettings lineSerieSettings = lineSeriesData.getLineSeriesSettings();
		lineSerieSettings.setEnableArea(true);
		lineSeriesDataList.add(lineSeriesData);
		//
		lineChart.addSeriesData(lineSeriesDataList);
	}

	private Color getColor(RGB rgb) {

		return new Color(Display.getCurrent(), rgb);
	}
}
