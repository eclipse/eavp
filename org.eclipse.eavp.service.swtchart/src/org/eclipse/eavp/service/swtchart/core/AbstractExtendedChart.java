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
package org.eclipse.eavp.service.swtchart.core;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.eclipse.eavp.service.swtchart.barcharts.IBarSeriesSettings;
import org.eclipse.eavp.service.swtchart.exceptions.SeriesException;
import org.eclipse.swt.widgets.Composite;
import org.swtchart.IAxis;
import org.swtchart.IAxis.Direction;
import org.swtchart.IAxisSet;
import org.swtchart.ISeries;
import org.swtchart.ISeries.SeriesType;
import org.swtchart.ISeriesSet;
import org.swtchart.Range;

public abstract class AbstractExtendedChart extends AbstractHandledChart implements IChartDataCoordinates, IRangeSupport, IExtendedChart {

	private int seriesMaxDataPoints;
	private double minX;
	private double maxX;
	private double minY;
	private double maxY;
	private RangeRestriction rangeRestriction;
	/*
	 * The extended values are only used internally.
	 */
	private double extendedMinX;
	private double extendedMaxX;
	private double extendedMinY;
	private double extendedMaxY;
	//
	private Map<Integer, IAxisSettings> xAxisSettingsMap;
	private Map<Integer, IAxisSettings> yAxisSettingsMap;
	private Map<String, ISeriesSettings> seriesSettingsMap;

	public AbstractExtendedChart(Composite parent, int style) {
		super(parent, style);
		xAxisSettingsMap = new HashMap<Integer, IAxisSettings>();
		yAxisSettingsMap = new HashMap<Integer, IAxisSettings>();
		seriesSettingsMap = new HashMap<String, ISeriesSettings>();
		resetCoordinates();
	}

	@Override
	public int getSeriesMaxDataPoints() {

		return seriesMaxDataPoints;
	}

	@Override
	public double getMinX() {

		return minX;
	}

	@Override
	public double getMaxX() {

		return maxX;
	}

	@Override
	public double getMinY() {

		return minY;
	}

	@Override
	public double getMaxY() {

		return maxY;
	}

	@Override
	public RangeRestriction getRangeRestriction() {

		return rangeRestriction;
	}

	@Override
	public void setRangeRestriction(RangeRestriction rangeRestriction) {

		this.rangeRestriction = rangeRestriction;
	}

	public IAxisSettings getXAxisSettings(int index) {

		return xAxisSettingsMap.get(index);
	}

	public IAxisSettings getYAxisSettings(int index) {

		return yAxisSettingsMap.get(index);
	}

	public void putXAxisSettings(int key, IAxisSettings axisSettings) {

		xAxisSettingsMap.put(key, axisSettings);
	}

	public void removeXAxisSettings() {

		Set<Integer> keySet = xAxisSettingsMap.keySet();
		for(int key : keySet) {
			if(key != BaseChart.ID_PRIMARY_X_AXIS) {
				xAxisSettingsMap.remove(key);
			}
		}
	}

	public void putYAxisSettings(int key, IAxisSettings axisSettings) {

		yAxisSettingsMap.put(key, axisSettings);
	}

	public void removeYAxisSettings() {

		Set<Integer> keySet = yAxisSettingsMap.keySet();
		for(int key : keySet) {
			if(key != BaseChart.ID_PRIMARY_Y_AXIS) {
				yAxisSettingsMap.remove(key);
			}
		}
	}

	public ISeriesSettings getSeriesSettings(String id) {

		return seriesSettingsMap.get(id);
	}

	@Override
	public void setRange(IAxis axis, int xStart, int xStop, boolean adjustMinMax) {

		if(axis != null && Math.abs(xStop - xStart) > 0 && !isUpdateSuspended()) {
			double start = axis.getDataCoordinate(Math.min(xStart, xStop));
			double stop = axis.getDataCoordinate(Math.max(xStart, xStop));
			setRange(axis, start, stop, adjustMinMax);
		}
	}

	@Override
	public void setRange(IAxis axis, double start, double stop, boolean adjustMinMax) {

		if(axis != null && Math.abs(stop - start) > 0 && !isUpdateSuspended()) {
			double min = Math.min(start, stop);
			double max = Math.max(start, stop);
			axis.setRange(new Range(min, max));
			if(adjustMinMax) {
				adjustMinMaxRange(axis);
			}
			//
			if(axis.getDirection() == Direction.X) {
				adjustSecondaryXAxes();
			} else if(axis.getDirection() == Direction.Y) {
				adjustSecondaryYAxes();
			}
		}
	}

	@Override
	public void adjustMinMaxRange(IAxis axis) {

		if(axis != null && !isUpdateSuspended()) {
			Range range = axis.getRange();
			if(axis.getDirection().equals(Direction.X)) {
				/*
				 * X-AXIS
				 */
				if(rangeRestriction.isZeroX()) {
					range.lower = (range.lower < 0) ? 0 : range.lower;
				} else {
					range.lower = (range.lower < minX) ? minX : range.lower;
				}
				extendRange(range, extendedMinX, extendedMaxX, rangeRestriction.getFactorExtendMinX(), rangeRestriction.getFactorExtendMaxX());
			} else {
				/*
				 * Y-AXIS
				 */
				if(rangeRestriction.isForceZeroMinY()) {
					range.lower = 0.0d;
				} else {
					if(rangeRestriction.isZeroY()) {
						range.lower = (range.lower < 0) ? 0 : range.lower;
					} else {
						range.lower = (range.lower < minY) ? minY : range.lower;
					}
				}
				//
				extendRange(range, extendedMinY, extendedMaxY, rangeRestriction.getFactorExtendMinY(), rangeRestriction.getFactorExtendMaxY());
			}
			/*
			 * Adjust the range.
			 */
			axis.setRange(range);
		}
	}

	private void extendRange(Range range, double min, double max, double factorExtendMin, double factorExtendMax) {

		/*
		 * Min
		 */
		if(factorExtendMin != 0.0d) {
			if(range.lower != min) {
				double lowerCalculated;
				if(range.lower > 0.0d) {
					lowerCalculated = range.lower - range.lower * factorExtendMin;
				} else {
					lowerCalculated = range.lower + range.lower * factorExtendMin;
				}
				//
				if(lowerCalculated <= min) {
					range.lower = min;
				} else {
					range.lower = (range.lower < min) ? min : range.lower;
				}
			}
		} else {
			range.lower = (range.lower < min) ? min : range.lower;
		}
		/*
		 * Max
		 */
		if(factorExtendMax != 0.0d) {
			if(range.upper != max) {
				double upperCalculated;
				if(range.upper > 0.0d) {
					upperCalculated = range.upper + range.upper * factorExtendMax;
				} else {
					upperCalculated = range.upper - range.upper * factorExtendMax;
				}
				//
				if(upperCalculated >= max) {
					range.upper = max;
				} else {
					range.upper = (range.upper > max) ? max : range.upper;
				}
			}
		} else {
			range.upper = (range.upper > max) ? max : range.upper;
		}
	}

	@Override
	public ISeries createSeries(ISeriesData seriesData, ISeriesSettings seriesSettings) throws SeriesException {

		SeriesType seriesType = getSeriesType(seriesSettings);
		double[] xSeries = seriesData.getXSeries();
		double[] ySeries = seriesData.getYSeries();
		//
		if(xSeries.length == ySeries.length) {
			/*
			 * Put the settings to the map.
			 */
			String id = seriesData.getId();
			seriesSettingsMap.put(id, seriesSettings);
			//
			ISeriesSet seriesSet = getSeriesSet();
			ISeries series = seriesSet.createSeries(seriesType, id);
			series.setXSeries(xSeries);
			series.setYSeries(ySeries);
			calculateCoordinates(series);
			return series;
		} else {
			throw new SeriesException("The length of x and y series differs.");
		}
	}

	@Override
	public void deleteSeries(String id) {

		ISeriesSet seriesSet = getSeriesSet();
		if(seriesSet.getSeries(id) != null) {
			resetCoordinates();
			seriesSet.deleteSeries(id);
			seriesSettingsMap.remove(id);
			for(ISeries series : seriesSet.getSeries()) {
				calculateCoordinates(series);
			}
		}
	}

	@Override
	public void appendSeries(ISeriesData seriesData) {

		if(seriesData != null) {
			ISeriesSet seriesSet = getSeriesSet();
			ISeries series = seriesSet.getSeries(seriesData.getId());
			if(series != null) {
				/*
				 * Append the data.
				 */
				double[] xSeriesNew = concatenateSeries(series.getXSeries(), seriesData.getXSeries());
				series.setXSeries(xSeriesNew);
				double[] ySeriesNew = concatenateSeries(series.getYSeries(), seriesData.getYSeries());
				series.setYSeries(ySeriesNew);
				//
				calculateCoordinates(series);
			}
		}
	}

	private SeriesType getSeriesType(ISeriesSettings seriesSettings) {

		SeriesType seriesType = SeriesType.LINE; // Default
		if(seriesSettings instanceof IBarSeriesSettings) {
			seriesType = SeriesType.BAR;
		}
		//
		return seriesType;
	}

	private double[] concatenateSeries(double[] a, double[] b) {

		int length = a.length + b.length;
		double[] c = new double[length];
		System.arraycopy(a, 0, c, 0, a.length);
		System.arraycopy(b, 0, c, a.length, b.length);
		return c;
	}

	@Override
	public void setRange(String axis, double start, double stop) {

		IAxisSet axisSet = getAxisSet();
		IAxis selectedAxis = (axis.equals(IExtendedChart.X_AXIS)) ? axisSet.getXAxis(BaseChart.ID_PRIMARY_X_AXIS) : axisSet.getYAxis(BaseChart.ID_PRIMARY_Y_AXIS);
		setRange(selectedAxis, start, stop, true);
	}

	@Override
	public void adjustRange(boolean adjustMinMax) {

		if(!isUpdateSuspended()) {
			getAxisSet().adjustRange();
			if(adjustMinMax) {
				adjustMinMaxRange(getAxisSet().getXAxis(BaseChart.ID_PRIMARY_X_AXIS));
				adjustMinMaxRange(getAxisSet().getYAxis(BaseChart.ID_PRIMARY_Y_AXIS));
			}
			/*
			 * Adjust the secondary axes.
			 */
			adjustSecondaryXAxes();
			adjustSecondaryYAxes();
		}
	}

	public void adjustSecondaryAxes() {

		adjustSecondaryXAxes();
		adjustSecondaryYAxes();
	}

	@Override
	public void adjustSecondaryXAxes() {

		IAxisSet axisSet = getAxisSet();
		IAxis xAxis = axisSet.getXAxis(BaseChart.ID_PRIMARY_X_AXIS);
		Range range = xAxis.getRange();
		for(int id : axisSet.getXAxisIds()) {
			if(id != BaseChart.ID_PRIMARY_X_AXIS) {
				IAxis axis = axisSet.getXAxis(id);
				IAxisSettings axisSettings = xAxisSettingsMap.get(id);
				if(axis != null && axisSettings instanceof ISecondaryAxisSettings) {
					IAxisScaleConverter axisScaleConverter = ((ISecondaryAxisSettings)axisSettings).getAxisScaleConverter();
					axisScaleConverter.setChartDataCoordinates(this);
					double start = axisScaleConverter.convertToSecondaryUnit(range.lower);
					double end = axisScaleConverter.convertToSecondaryUnit(range.upper);
					if(end > start) {
						Range adjustedRange = new Range(start, end);
						axis.setRange(adjustedRange);
					} else {
						System.out.println("Can't set secondary x axes range: " + start + "\t" + end);
					}
				}
			}
		}
	}

	@Override
	public void adjustSecondaryYAxes() {

		IAxisSet axisSet = getAxisSet();
		IAxis yAxis = axisSet.getYAxis(BaseChart.ID_PRIMARY_Y_AXIS);
		Range range = yAxis.getRange();
		for(int id : axisSet.getYAxisIds()) {
			if(id != BaseChart.ID_PRIMARY_Y_AXIS) {
				IAxis axis = axisSet.getYAxis(id);
				IAxisSettings axisSettings = yAxisSettingsMap.get(id);
				if(axis != null && axisSettings instanceof ISecondaryAxisSettings) {
					IAxisScaleConverter axisScaleConverter = ((ISecondaryAxisSettings)axisSettings).getAxisScaleConverter();
					axisScaleConverter.setChartDataCoordinates(this);
					double start = axisScaleConverter.convertToSecondaryUnit(range.lower);
					double end = axisScaleConverter.convertToSecondaryUnit(range.upper);
					if(end > start) {
						Range adjustedRange = new Range(start, end);
						axis.setRange(adjustedRange);
					} else {
						System.out.println("Can't set secondary y axes range: " + start + "\t" + end);
					}
				}
			}
		}
	}

	/*
	 * Min/max values will be set dynamically via Math.min and Math.max.
	 * Using the default double value 0 could lead to errors when using
	 * Math.min(...), hence initialize the values with the lowest/highest value.
	 */
	private void resetCoordinates() {

		seriesMaxDataPoints = 0;
		minX = Double.POSITIVE_INFINITY;
		maxX = Double.NEGATIVE_INFINITY;
		minY = Double.POSITIVE_INFINITY;
		maxY = Double.NEGATIVE_INFINITY;
	}

	private void calculateCoordinates(ISeries series) {

		double[] xSeries = series.getXSeries();
		double[] ySeries = series.getYSeries();
		//
		double seriesMinX = Arrays.stream(xSeries).min().getAsDouble();
		double seriesMaxX = Arrays.stream(xSeries).max().getAsDouble();
		double seriesMinY = Arrays.stream(ySeries).min().getAsDouble();
		double seriesMaxY = Arrays.stream(ySeries).max().getAsDouble();
		//
		seriesMaxDataPoints = Math.max(seriesMaxDataPoints, xSeries.length);
		updateCoordinates(seriesMinX, seriesMaxX, seriesMinY, seriesMaxY);
	}

	protected void updateCoordinates(double seriesMinX, double seriesMaxX, double seriesMinY, double seriesMaxY) {

		minX = Math.min(minX, seriesMinX);
		minX = (rangeRestriction.isZeroX() && minX < 0.0d) ? 0.0d : minX;
		maxX = Math.max(maxX, seriesMaxX);
		minY = Math.min(minY, seriesMinY);
		minY = (rangeRestriction.isZeroY() && minY < 0.0d) ? 0.0d : minY;
		minY = (rangeRestriction.isForceZeroMinY()) ? 0.0d : minY;
		maxY = Math.max(maxY, seriesMaxY);
		//
		calculateExtendedCoordinates();
	}

	private void calculateExtendedCoordinates() {

		/*
		 * Min X
		 */
		extendedMinX = minX;
		if(extendedMinX > 0.0d) {
			extendedMinX -= extendedMinX * rangeRestriction.getFactorExtendMinX();
		} else {
			extendedMinX += extendedMinX * rangeRestriction.getFactorExtendMinX();
		}
		/*
		 * Max X
		 */
		extendedMaxX = maxX;
		if(extendedMaxX > 0.0d) {
			extendedMaxX += extendedMaxX * rangeRestriction.getFactorExtendMaxX();
		} else {
			extendedMaxX -= extendedMaxX * rangeRestriction.getFactorExtendMaxX();
		}
		/*
		 * Min Y
		 */
		extendedMinY = minY;
		if(extendedMinY > 0.0d) {
			extendedMinY -= extendedMinY * rangeRestriction.getFactorExtendMinY();
		} else {
			extendedMinY += extendedMinY * rangeRestriction.getFactorExtendMinY();
		}
		/*
		 * Max Y
		 */
		extendedMaxY = maxY;
		if(extendedMaxY > 0.0d) {
			extendedMaxY += extendedMaxY * rangeRestriction.getFactorExtendMaxY();
		} else {
			extendedMaxY -= extendedMaxY * rangeRestriction.getFactorExtendMaxY();
		}
	}
}
