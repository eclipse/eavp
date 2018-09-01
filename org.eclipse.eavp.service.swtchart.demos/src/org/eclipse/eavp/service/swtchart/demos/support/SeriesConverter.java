/*******************************************************************************
 * Copyright (c) 2017, 2018 Lablicate GmbH.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 * Dr. Philip Wenig - initial API and implementation
 *******************************************************************************/
package org.eclipse.eavp.service.swtchart.demos.support;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.eavp.service.swtchart.core.ISeriesData;
import org.eclipse.eavp.service.swtchart.core.SeriesData;

public class SeriesConverter {

	public static final String LINE_SERIES = "LineSeries";
	public static final String SCATTER_SERIES = "ScatterSeries";
	//
	public static final String LINE_SERIES_1 = "LineSeries1";
	public static final String LINE_SERIES_1_ACTIVE_PEAKS = "LineSeries1_ActivePeaks";
	public static final String LINE_SERIES_1_INACTIVE_PEAKS = "LineSeries1_InactivePeaks";
	public static final String LINE_SERIES_1_SELECTED_PEAK_1 = "LineSeries1_SelectedPeak_1";
	public static final String LINE_SERIES_1_SELECTED_PEAK_1_BACKGROUND = "LineSeries1_SelectedPeak_1_Background";
	public static final String LINE_SERIES_1_SELECTED_PEAK_2 = "LineSeries1_SelectedPeak_2";
	public static final String LINE_SERIES_1_SELECTED_PEAK_2_BACKGROUND = "LineSeries1_SelectedPeak_2_Background";
	public static final String LINE_SERIES_1_SELECTED_SCANS = "LineSeries1_SelectedScans";
	public static final String LINE_SERIES_1_IDENTIFIED_SCANS = "LineSeries1_IdentifiedScans";
	public static final String LINE_SERIES_1_IDENTIFIED_SCANS_SELECTED = "LineSeries1_IdentifiedScansSelected";
	public static final String LINE_SERIES_1_BASELINE = "LineSeries1_Baseline";
	public static final String LINE_SERIES_2 = "LineSeries2";
	public static final String LINE_SERIES_3 = "LineSeries3";
	public static final String LINE_SERIES_4_1 = "LineSeries4_1";
	public static final String LINE_SERIES_4_2 = "LineSeries4_2";
	public static final String LINE_SERIES_4_3 = "LineSeries4_3";
	public static final String LINE_SERIES_4_4 = "LineSeries4_4";
	public static final String LINE_SERIES_4_5 = "LineSeries4_5";
	public static final String LINE_SERIES_5_POSITIVE = "LineSeries5_Positive";
	public static final String LINE_SERIES_5_NEGATIVE = "LineSeries5_Negative";
	public static final String LINE_SERIES_6 = "LineSeries6";
	public static final String LINE_SERIES_7 = "LineSeries7";
	//
	public static final String BAR_SERIES_1 = "BarSeries1";
	public static final String BAR_SERIES_2 = "BarSeries2";
	public static final String BAR_SERIES_3_POSITIVE = "BarSeries3-Positive";
	public static final String BAR_SERIES_3_NEGATIVE = "BarSeries3-Negative";
	//
	public static final String SCATTER_SERIES_1 = "ScatterSeries1";
	public static final String SCATTER_SERIES_2_1 = "ScatterSeries2_1";
	public static final String SCATTER_SERIES_2_2 = "ScatterSeries2_2";
	public static final String SCATTER_SERIES_2_3 = "ScatterSeries2_3";
	public static final String SCATTER_SERIES_2_4 = "ScatterSeries2_4";
	public static final String SCATTER_SERIES_2_5 = "ScatterSeries2_5";
	public static final String SCATTER_SERIES_2_6 = "ScatterSeries2_6";
	public static final String SCATTER_SERIES_2_7 = "ScatterSeries2_7";
	public static final String SCATTER_SERIES_2_8 = "ScatterSeries2_8";
	public static final String SCATTER_SERIES_2_9 = "ScatterSeries2_9";
	//
	public static final String MEASUREMENT_SERIES_1_READINGS = "Measurement1_Readings";
	public static final String MEASUREMENT_SERIES_1_REGRESSION = "Measurement1_Regression";
	public static final String MEASUREMENT_SERIES_2_READINGS_1 = "Measurement2_Readings_1";
	public static final String MEASUREMENT_SERIES_2_REGRESSION_1 = "Measurement2_Regression_1";
	public static final String MEASUREMENT_SERIES_2_READINGS_2 = "Measurement2_Readings_2";
	public static final String MEASUREMENT_SERIES_2_REGRESSION_2 = "Measurement2_Regression_2";
	public static final String MEASUREMENT_SERIES_2_READINGS_3 = "Measurement2_Readings_3";
	public static final String MEASUREMENT_SERIES_2_REGRESSION_3 = "Measurement2_Regression_3";
	public static final String MEASUREMENT_SERIES_3_READINGS = "Measurement3_Readings";
	public static final String MEASUREMENT_SERIES_3_REGRESSION = "Measurement3_Regression";

	public static ISeriesData getSeriesXY(String fileName) {

		return getSeriesXY(fileName, fileName);
	}

	public static ISeriesData getSeriesXY(String fileName, String id) {

		int size = getNumberOfLines(fileName);
		double[] xSeries = new double[size];
		double[] ySeries = new double[size];
		//
		BufferedReader bufferedReader = null;
		try {
			String line;
			int i = 0;
			bufferedReader = new BufferedReader(new InputStreamReader(SeriesConverter.class.getResourceAsStream(fileName)));
			while((line = bufferedReader.readLine()) != null) {
				if(!line.startsWith("#")) {
					String[] values = line.split("\t");
					xSeries[i] = Double.parseDouble(values[0].trim());
					ySeries[i] = Double.parseDouble(values[1].trim());
					i++;
				}
			}
		} catch(Exception e) {
			//
		} finally {
			if(bufferedReader != null) {
				try {
					bufferedReader.close();
				} catch(IOException e) {
					//
				}
			}
		}
		//
		ISeriesData seriesData = new SeriesData(xSeries, ySeries, id);
		return seriesData;
	}

	public static ISeriesData getSeriesFromY(String fileName) {

		int size = getNumberOfLines(fileName);
		double[] ySeries = new double[size];
		//
		BufferedReader bufferedReader = null;
		try {
			String line;
			int i = 0;
			bufferedReader = new BufferedReader(new InputStreamReader(SeriesConverter.class.getResourceAsStream(fileName)));
			while((line = bufferedReader.readLine()) != null) {
				ySeries[i++] = Double.parseDouble(line.trim());
			}
		} catch(Exception e) {
			//
		} finally {
			if(bufferedReader != null) {
				try {
					bufferedReader.close();
				} catch(IOException e) {
					//
				}
			}
		}
		//
		ISeriesData seriesData = new SeriesData(ySeries, fileName);
		return seriesData;
	}

	public static List<ISeriesData> getSeriesScatter(String fileName) {

		List<ISeriesData> scatterSeriesList = new ArrayList<ISeriesData>();
		//
		BufferedReader bufferedReader = null;
		try {
			String line;
			bufferedReader = new BufferedReader(new InputStreamReader(SeriesConverter.class.getResourceAsStream(fileName)));
			while((line = bufferedReader.readLine()) != null) {
				String[] values = line.split("\t");
				String id = values[0].trim();
				double[] xSeries = new double[]{Double.parseDouble(values[1].trim())};
				double[] ySeries = new double[]{Double.parseDouble(values[2].trim())};
				ISeriesData seriesData = new SeriesData(xSeries, ySeries, id);
				scatterSeriesList.add(seriesData);
			}
		} catch(Exception e) {
			//
		} finally {
			if(bufferedReader != null) {
				try {
					bufferedReader.close();
				} catch(IOException e) {
					//
				}
			}
		}
		return scatterSeriesList;
	}

	private static int getNumberOfLines(String fileName) {

		int i = 0;
		BufferedReader bufferedReader = null;
		try {
			bufferedReader = new BufferedReader(new InputStreamReader(SeriesConverter.class.getResourceAsStream(fileName)));
			while((bufferedReader.readLine()) != null) {
				i++;
			}
		} catch(Exception e) {
			//
		} finally {
			if(bufferedReader != null) {
				try {
					bufferedReader.close();
				} catch(IOException e) {
					//
				}
			}
		}
		return i;
	}
}
