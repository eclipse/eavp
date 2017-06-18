/*******************************************************************************
 * Copyright (c) 2017 Lablicate GmbH.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 * Dr. Philip Wenig - initial API and implementation
 *******************************************************************************/
package org.eclipse.eavp.service.swtchart.customcharts;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.eclipse.eavp.service.swtchart.barcharts.BarChart;
import org.eclipse.eavp.service.swtchart.converter.RelativeIntensityConverter;
import org.eclipse.eavp.service.swtchart.core.BaseChart;
import org.eclipse.eavp.service.swtchart.core.ColorAndFormatSupport;
import org.eclipse.eavp.service.swtchart.core.IAxisSettings;
import org.eclipse.eavp.service.swtchart.core.IChartSettings;
import org.eclipse.eavp.service.swtchart.core.IPrimaryAxisSettings;
import org.eclipse.eavp.service.swtchart.core.ISecondaryAxisSettings;
import org.eclipse.eavp.service.swtchart.core.SecondaryAxisSettings;
import org.eclipse.eavp.service.swtchart.support.BarSeriesIon;
import org.eclipse.eavp.service.swtchart.support.BarSeriesIonComparator;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Composite;
import org.swtchart.IAxis.Position;
import org.swtchart.ICustomPaintListener;
import org.swtchart.IPlotArea;
import org.swtchart.ISeries;

public class MassSpectrumChart extends BarChart {

	private static final int NUMBER_OF_IONS_TO_PAINT = 5;
	private static final DecimalFormat DEFAULT_DECIMAL_FORMAT = new DecimalFormat();
	private BarSeriesIonComparator barSeriesIonComparator = new BarSeriesIonComparator();

	public MassSpectrumChart(Composite parent, int style) {
		super(parent, style);
		initialize();
	}

	private void initialize() {

		IChartSettings chartSettings = getChartSettings();
		chartSettings.setOrientation(SWT.HORIZONTAL);
		chartSettings.setHorizontalSliderVisible(true);
		chartSettings.setVerticalSliderVisible(true);
		chartSettings.setUseZeroX(false);
		chartSettings.setUseZeroY(false);
		chartSettings.setUseRangeRestriction(true);
		chartSettings.setFactorExtendMinX(0.05d);
		chartSettings.setFactorExtendMaxX(0.05d);
		chartSettings.setFactorExtendMaxY(0.1d);
		//
		setPrimaryAxisSet(chartSettings);
		addSecondaryAxisSet(chartSettings);
		applySettings(chartSettings);
		//
		addSeriesLabelMarker();
	}

	private void setPrimaryAxisSet(IChartSettings chartSettings) {

		IPrimaryAxisSettings primaryAxisSettingsX = chartSettings.getPrimaryAxisSettingsX();
		primaryAxisSettingsX.setTitle("m/z");
		primaryAxisSettingsX.setDecimalFormat(ColorAndFormatSupport.decimalFormatVariable);
		primaryAxisSettingsX.setColor(ColorAndFormatSupport.COLOR_BLACK);
		//
		IPrimaryAxisSettings primaryAxisSettingsY = chartSettings.getPrimaryAxisSettingsY();
		primaryAxisSettingsY.setTitle("Intensity");
		primaryAxisSettingsY.setDecimalFormat(ColorAndFormatSupport.decimalFormatScientific);
		primaryAxisSettingsY.setColor(ColorAndFormatSupport.COLOR_BLACK);
	}

	private void addSecondaryAxisSet(IChartSettings chartSettings) {

		ISecondaryAxisSettings secondaryAxisSettingsY = new SecondaryAxisSettings("Relative Intensity [%]", new RelativeIntensityConverter(SWT.VERTICAL, true));
		secondaryAxisSettingsY.setTitle("");
		secondaryAxisSettingsY.setPosition(Position.Secondary);
		secondaryAxisSettingsY.setDecimalFormat(ColorAndFormatSupport.decimalFormatFixed);
		secondaryAxisSettingsY.setColor(ColorAndFormatSupport.COLOR_BLACK);
		chartSettings.getSecondaryAxisSettingsListY().add(secondaryAxisSettingsY);
	}

	private void addSeriesLabelMarker() {

		/*
		 * Plot the series name above the entry.
		 */
		IPlotArea plotArea = (IPlotArea)getBaseChart().getPlotArea();
		plotArea.addCustomPaintListener(new ICustomPaintListener() {

			@Override
			public void paintControl(PaintEvent e) {

				/*
				 * Draw the label
				 */
				DecimalFormat decimalFormat = getDecimalFormatMZ();
				List<BarSeriesIon> barSeriesIons = getBarSeriesIonList();
				Collections.sort(barSeriesIons, barSeriesIonComparator);
				int barSeriesSize = barSeriesIons.size();
				//
				for(int i = 0; i < NUMBER_OF_IONS_TO_PAINT; i++) {
					if(i < barSeriesSize) {
						BarSeriesIon barSeriesIon = barSeriesIons.get(i);
						ISeries barSeries = getBarSeries();
						if(barSeries != null) {
							Point point = barSeries.getPixelCoordinates(barSeriesIon.getIndex());
							String label = decimalFormat.format(barSeriesIon.getMz());
							boolean mirrored = false;
							Point labelSize = e.gc.textExtent(label);
							int x = (int)(point.x + 0.5d - labelSize.x / 2.0d);
							int y = point.y;
							if(!mirrored) {
								y = point.y - labelSize.y;
							}
							e.gc.drawText(label, x, y, true);
						}
					}
				}
			}

			@Override
			public boolean drawBehindSeries() {

				return false;
			}
		});
	}

	private List<BarSeriesIon> getBarSeriesIonList() {

		List<BarSeriesIon> barSeriesIons = new ArrayList<BarSeriesIon>();
		//
		ISeries barSeries = getBaseChart().getSeriesSet().getSeries()[0];
		int widthPlotArea = getBaseChart().getPlotArea().getBounds().width;
		//
		if(barSeries != null) {
			//
			double[] xSeries = barSeries.getXSeries();
			double[] ySeries = barSeries.getYSeries();
			int size = barSeries.getXSeries().length;
			//
			for(int i = 0; i < size; i++) {
				Point point = barSeries.getPixelCoordinates(i);
				if(point.x >= 0 && point.x <= widthPlotArea) {
					barSeriesIons.add(new BarSeriesIon(xSeries[i], ySeries[i], i));
				}
			}
		}
		return barSeriesIons;
	}

	private ISeries getBarSeries() {

		return getBaseChart().getSeriesSet().getSeries()[0];
	}

	private DecimalFormat getDecimalFormatMZ() {

		IAxisSettings axisSettings = getBaseChart().getXAxisSettingsMap().get(BaseChart.ID_PRIMARY_X_AXIS);
		if(axisSettings != null) {
			return axisSettings.getDecimalFormat();
		} else {
			return DEFAULT_DECIMAL_FORMAT;
		}
	}
}
