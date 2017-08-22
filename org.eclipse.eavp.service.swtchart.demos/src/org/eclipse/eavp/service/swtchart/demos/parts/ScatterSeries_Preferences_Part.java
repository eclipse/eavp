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
import org.eclipse.eavp.service.swtchart.demos.Activator;
import org.eclipse.eavp.service.swtchart.demos.preferences.ScatterSeriesDataPreferencePage;
import org.eclipse.eavp.service.swtchart.demos.preferences.ScatterSeriesPreferenceConstants;
import org.eclipse.eavp.service.swtchart.demos.preferences.ScatterSeriesPreferencePage;
import org.eclipse.eavp.service.swtchart.demos.preferences.ScatterSeriesPrimaryAxesPreferencePage;
import org.eclipse.eavp.service.swtchart.demos.support.SeriesConverter;
import org.eclipse.eavp.service.swtchart.scattercharts.IScatterSeriesData;
import org.eclipse.eavp.service.swtchart.scattercharts.IScatterSeriesSettings;
import org.eclipse.eavp.service.swtchart.scattercharts.ScatterChart;
import org.eclipse.eavp.service.swtchart.scattercharts.ScatterSeriesData;
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
import org.swtchart.ILineSeries.PlotSymbolType;
import org.swtchart.LineStyle;

public class ScatterSeries_Preferences_Part extends Composite {

	private ScatterChart scatterChart;
	private Map<RGB, Color> colors;

	@Inject
	public ScatterSeries_Preferences_Part(Composite parent) {
		super(parent, SWT.NONE);
		colors = new HashMap<>();
		try {
			initialize();
		} catch(Exception e) {
			System.out.println(e);
		}
	}

	@Override
	public void dispose() {

		for(Color color : colors.values()) {
			color.dispose();
		}
		super.dispose();
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
		compositeButtons.setLayout(new GridLayout(1, false));
		//
		Button buttonOpenSettings = new Button(compositeButtons, SWT.PUSH);
		modifySettingsButton(buttonOpenSettings);
		buttonOpenSettings.addSelectionListener(new SelectionAdapter() {

			@Override
			public void widgetSelected(SelectionEvent e) {

				IPreferencePage preferencePage = new ScatterSeriesPreferencePage();
				preferencePage.setTitle("Chart Settings");
				IPreferencePage preferencePrimaryAxesPage = new ScatterSeriesPrimaryAxesPreferencePage();
				preferencePrimaryAxesPage.setTitle("Primary Axes");
				IPreferencePage preferenceDataPage = new ScatterSeriesDataPreferencePage();
				preferenceDataPage.setTitle("Series Data");
				//
				PreferenceManager preferenceManager = new PreferenceManager();
				preferenceManager.addToRoot(new PreferenceNode("1", preferencePage));
				preferenceManager.addToRoot(new PreferenceNode("2", preferencePrimaryAxesPage));
				preferenceManager.addToRoot(new PreferenceNode("3", preferenceDataPage));
				//
				PreferenceDialog preferenceDialog = new PreferenceDialog(Display.getDefault().getActiveShell(), preferenceManager);
				preferenceDialog.create();
				preferenceDialog.setMessage("Settings");
				if(preferenceDialog.open() == PreferenceDialog.OK) {
					try {
						applyChartSettings();
						applySeriesSettings();
					} catch(Exception e1) {
						MessageDialog.openError(Display.getDefault().getActiveShell(), "Settings", "Something has gone wrong to apply the chart settings.");
					}
				}
			}
		});
		//
		scatterChart = new ScatterChart(this, SWT.NONE);
		scatterChart.setLayoutData(new GridData(GridData.FILL_BOTH));
		//
		applyChartSettings();
		applySeriesSettings();
	}

	private void modifySettingsButton(Button button) {

		button.setToolTipText("Open the Settings");
		if(Activator.getDefault() != null) {
			button.setText("");
			button.setImage(Activator.getDefault().getImage(Activator.ICON_OPEN_SETTINGS));
		} else {
			button.setText("Settings");
		}
	}

	private void applyChartSettings() throws Exception {

		IPreferenceStore preferenceStore = Activator.getDefault().getPreferenceStore();
		setBackground(Display.getDefault().getSystemColor(SWT.COLOR_WHITE));
		//
		Color colorHintRangeSelector = getColor(PreferenceConverter.getColor(preferenceStore, ScatterSeriesPreferenceConstants.P_COLOR_HINT_RANGE_SELECTOR));
		Color colorTitle = getColor(PreferenceConverter.getColor(preferenceStore, ScatterSeriesPreferenceConstants.P_TITLE_COLOR));
		Color colorBackground = getColor(PreferenceConverter.getColor(preferenceStore, ScatterSeriesPreferenceConstants.P_BACKGROUND));
		Color colorBackgroundChart = getColor(PreferenceConverter.getColor(preferenceStore, ScatterSeriesPreferenceConstants.P_BACKGROUND_CHART));
		Color colorBackgroundPlotArea = getColor(PreferenceConverter.getColor(preferenceStore, ScatterSeriesPreferenceConstants.P_BACKGROUND_PLOT_AREA));
		Color colorPrimaryXAxis = getColor(PreferenceConverter.getColor(preferenceStore, ScatterSeriesPreferenceConstants.P_PRIMARY_X_AXIS_COLOR));
		Color colorPrimaryYAxis = getColor(PreferenceConverter.getColor(preferenceStore, ScatterSeriesPreferenceConstants.P_PRIMARY_Y_AXIS_COLOR));
		Locale localePrimaryXAxis = new Locale(preferenceStore.getString(ScatterSeriesPreferenceConstants.P_PRIMARY_X_AXIS_DECIMAL_FORMAT_LOCALE));
		Locale localePrimaryYAxis = new Locale(preferenceStore.getString(ScatterSeriesPreferenceConstants.P_PRIMARY_Y_AXIS_DECIMAL_FORMAT_LOCALE));
		Color colorPositionMarker = getColor(PreferenceConverter.getColor(preferenceStore, ScatterSeriesPreferenceConstants.P_COLOR_POSITION_MARKER));
		Color colorCenterMarker = getColor(PreferenceConverter.getColor(preferenceStore, ScatterSeriesPreferenceConstants.P_COLOR_CENTER_MARKER));
		Color colorPositionLegend = getColor(PreferenceConverter.getColor(preferenceStore, ScatterSeriesPreferenceConstants.P_COLOR_POSITION_LEGEND));
		//
		IChartSettings chartSettings = scatterChart.getChartSettings();
		chartSettings.setEnableRangeSelector(preferenceStore.getBoolean(ScatterSeriesPreferenceConstants.P_ENABLE_RANGE_SELECTOR));
		chartSettings.setColorHintRangeSelector(colorHintRangeSelector);
		chartSettings.setRangeSelectorDefaultAxisX(preferenceStore.getInt(ScatterSeriesPreferenceConstants.P_RANGE_SELECTOR_DEFAULT_AXIS_X));
		chartSettings.setRangeSelectorDefaultAxisY(preferenceStore.getInt(ScatterSeriesPreferenceConstants.P_RANGE_SELECTOR_DEFAULT_AXIS_Y));
		chartSettings.setVerticalSliderVisible(preferenceStore.getBoolean(ScatterSeriesPreferenceConstants.P_VERTICAL_SLIDER_VISIBLE));
		chartSettings.setHorizontalSliderVisible(preferenceStore.getBoolean(ScatterSeriesPreferenceConstants.P_HORIZONTAL_SLIDER_VISIBLE));
		chartSettings.setTitle(preferenceStore.getString(ScatterSeriesPreferenceConstants.P_TITLE));
		chartSettings.setTitleVisible(preferenceStore.getBoolean(ScatterSeriesPreferenceConstants.P_TITLE_VISIBLE));
		chartSettings.setTitleColor(colorTitle);
		chartSettings.setLegendPosition(preferenceStore.getInt(ScatterSeriesPreferenceConstants.P_LEGEND_POSITION));
		chartSettings.setLegendVisible(preferenceStore.getBoolean(ScatterSeriesPreferenceConstants.P_LEGEND_VISIBLE));
		chartSettings.setOrientation(preferenceStore.getInt(ScatterSeriesPreferenceConstants.P_ORIENTATION));
		chartSettings.setBackground(colorBackground);
		chartSettings.setBackgroundChart(colorBackgroundChart);
		chartSettings.setBackgroundPlotArea(colorBackgroundPlotArea);
		chartSettings.setEnableCompress(preferenceStore.getBoolean(ScatterSeriesPreferenceConstants.P_ENABLE_COMPRESS));
		RangeRestriction rangeRestriction = chartSettings.getRangeRestriction();
		rangeRestriction.setZeroX(preferenceStore.getBoolean(ScatterSeriesPreferenceConstants.P_ZERO_X));
		rangeRestriction.setZeroY(preferenceStore.getBoolean(ScatterSeriesPreferenceConstants.P_ZERO_Y));
		rangeRestriction.setRestrictZoom(preferenceStore.getBoolean(ScatterSeriesPreferenceConstants.P_RESTRICT_ZOOM));
		rangeRestriction.setXZoomOnly(preferenceStore.getBoolean(ScatterSeriesPreferenceConstants.P_X_ZOOM_ONLY));
		rangeRestriction.setYZoomOnly(preferenceStore.getBoolean(ScatterSeriesPreferenceConstants.P_Y_ZOOM_ONLY));
		rangeRestriction.setForceZeroMinY(preferenceStore.getBoolean(ScatterSeriesPreferenceConstants.P_FORCE_ZERO_MIN_Y));
		rangeRestriction.setFactorExtendMinX(preferenceStore.getDouble(ScatterSeriesPreferenceConstants.P_FACTOR_EXTEND_MIN_X));
		rangeRestriction.setFactorExtendMaxX(preferenceStore.getDouble(ScatterSeriesPreferenceConstants.P_FACTOR_EXTEND_MAX_X));
		rangeRestriction.setFactorExtendMinY(preferenceStore.getDouble(ScatterSeriesPreferenceConstants.P_FACTOR_EXTEND_MIN_Y));
		rangeRestriction.setFactorExtendMaxY(preferenceStore.getDouble(ScatterSeriesPreferenceConstants.P_FACTOR_EXTEND_MAX_Y));
		//
		chartSettings.setShowPositionMarker(preferenceStore.getBoolean(ScatterSeriesPreferenceConstants.P_SHOW_POSITION_MARKER));
		chartSettings.setColorPositionMarker(colorPositionMarker);
		chartSettings.setShowCenterMarker(preferenceStore.getBoolean(ScatterSeriesPreferenceConstants.P_SHOW_CENTER_MARKER));
		chartSettings.setColorCenterMarker(colorCenterMarker);
		chartSettings.setShowPositionLegend(preferenceStore.getBoolean(ScatterSeriesPreferenceConstants.P_SHOW_POSITION_LEGEND));
		chartSettings.setColorPositionLegend(colorPositionLegend);
		chartSettings.setCreateMenu(preferenceStore.getBoolean(ScatterSeriesPreferenceConstants.P_CREATE_MENU));
		/*
		 * Primary X-Axis
		 */
		IPrimaryAxisSettings primaryAxisSettingsX = chartSettings.getPrimaryAxisSettingsX();
		primaryAxisSettingsX.setTitle(preferenceStore.getString(ScatterSeriesPreferenceConstants.P_PRIMARY_X_AXIS_TITLE));
		primaryAxisSettingsX.setDescription(preferenceStore.getString(ScatterSeriesPreferenceConstants.P_PRIMARY_X_AXIS_DESCRIPTION));
		primaryAxisSettingsX.setDecimalFormat(new DecimalFormat((preferenceStore.getString(ScatterSeriesPreferenceConstants.P_PRIMARY_X_AXIS_DECIMAL_FORMAT_PATTERN)), new DecimalFormatSymbols(localePrimaryXAxis)));
		primaryAxisSettingsX.setColor(colorPrimaryXAxis);
		primaryAxisSettingsX.setPosition(Position.valueOf(preferenceStore.getString(ScatterSeriesPreferenceConstants.P_PRIMARY_X_AXIS_POSITION)));
		primaryAxisSettingsX.setVisible(preferenceStore.getBoolean(ScatterSeriesPreferenceConstants.P_PRIMARY_X_AXIS_VISIBLE));
		primaryAxisSettingsX.setGridLineStyle(LineStyle.valueOf(preferenceStore.getString(ScatterSeriesPreferenceConstants.P_PRIMARY_X_AXIS_GRID_LINE_STYLE)));
		primaryAxisSettingsX.setEnableLogScale(preferenceStore.getBoolean(ScatterSeriesPreferenceConstants.P_PRIMARY_X_AXIS_ENABLE_LOG_SCALE));
		primaryAxisSettingsX.setExtraSpaceTitle(preferenceStore.getInt(ScatterSeriesPreferenceConstants.P_PRIMARY_X_AXIS_EXTRA_SPACE_TITLE));
		/*
		 * Primary Y-Axis
		 */
		IPrimaryAxisSettings primaryAxisSettingsY = chartSettings.getPrimaryAxisSettingsY();
		primaryAxisSettingsY.setTitle(preferenceStore.getString(ScatterSeriesPreferenceConstants.P_PRIMARY_Y_AXIS_TITLE));
		primaryAxisSettingsY.setDescription(preferenceStore.getString(ScatterSeriesPreferenceConstants.P_PRIMARY_Y_AXIS_DESCRIPTION));
		primaryAxisSettingsY.setDecimalFormat(new DecimalFormat((preferenceStore.getString(ScatterSeriesPreferenceConstants.P_PRIMARY_Y_AXIS_DECIMAL_FORMAT_PATTERN)), new DecimalFormatSymbols(localePrimaryYAxis)));
		primaryAxisSettingsY.setColor(colorPrimaryYAxis);
		primaryAxisSettingsY.setPosition(Position.valueOf(preferenceStore.getString(ScatterSeriesPreferenceConstants.P_PRIMARY_Y_AXIS_POSITION)));
		primaryAxisSettingsY.setVisible(preferenceStore.getBoolean(ScatterSeriesPreferenceConstants.P_PRIMARY_Y_AXIS_VISIBLE));
		primaryAxisSettingsY.setGridLineStyle(LineStyle.valueOf(preferenceStore.getString(ScatterSeriesPreferenceConstants.P_PRIMARY_Y_AXIS_GRID_LINE_STYLE)));
		primaryAxisSettingsY.setEnableLogScale(preferenceStore.getBoolean(ScatterSeriesPreferenceConstants.P_PRIMARY_Y_AXIS_ENABLE_LOG_SCALE));
		primaryAxisSettingsY.setExtraSpaceTitle(preferenceStore.getInt(ScatterSeriesPreferenceConstants.P_PRIMARY_Y_AXIS_EXTRA_SPACE_TITLE));
		//
		scatterChart.applySettings(chartSettings);
	}

	private void applySeriesSettings() {

		IPreferenceStore preferenceStore = Activator.getDefault().getPreferenceStore();
		Color symbolColorSeries1 = getColor(PreferenceConverter.getColor(preferenceStore, ScatterSeriesPreferenceConstants.P_SYMBOL_COLOR_SERIES_1));
		//
		scatterChart.deleteSeries();
		List<IScatterSeriesData> scatterSeriesDataList = new ArrayList<IScatterSeriesData>();
		ISeriesData seriesData;
		IScatterSeriesData scatterSeriesData;
		IScatterSeriesSettings scatterSerieSettings;
		/*
		 * Series 1
		 */
		seriesData = SeriesConverter.getSeriesXY(SeriesConverter.SCATTER_SERIES + "2_2");
		scatterSeriesData = new ScatterSeriesData(seriesData);
		scatterSerieSettings = scatterSeriesData.getScatterSeriesSettings();
		scatterSerieSettings.setDescription(preferenceStore.getString(ScatterSeriesPreferenceConstants.P_DESCRIPTION_SERIES_1));
		scatterSerieSettings.setSymbolColor(symbolColorSeries1);
		scatterSerieSettings.setSymbolSize(preferenceStore.getInt(ScatterSeriesPreferenceConstants.P_SYMBOL_SIZE_SERIES_1));
		scatterSerieSettings.setSymbolType(PlotSymbolType.valueOf(preferenceStore.getString(ScatterSeriesPreferenceConstants.P_SYMBOL_TYPE_SERIES_1)));
		scatterSerieSettings.setVisible(preferenceStore.getBoolean(ScatterSeriesPreferenceConstants.P_VISIBLE_SERIES_1));
		scatterSerieSettings.setVisibleInLegend(preferenceStore.getBoolean(ScatterSeriesPreferenceConstants.P_VISIBLE_IN_LEGEND_SERIES_1));
		scatterSeriesDataList.add(scatterSeriesData);
		//
		scatterChart.addSeriesData(scatterSeriesDataList);
	}

	private Color getColor(RGB rgb) {

		Color color = colors.get(rgb);
		if(color == null) {
			color = new Color(Display.getDefault(), rgb);
			colors.put(rgb, color);
		}
		return color;
	}
}