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

	private boolean useZeroY;
	private boolean useZeroX;
	private double length;
	private double minX;
	private double maxX;
	private double minY;
	private double maxY;
	private boolean useRangeRestriction;
	private double factorExtendMinX;
	private double factorExtendMaxX;
	private double factorExtendMinY;
	private double factorExtendMaxY;
	/*
	 * The extended values are only used internally.
	 */
	private double extendedMinX;
	private double extendedMaxX;
	private double extendedMinY;
	private double extendedMaxY;
	/*
	 * The settings are used to get the description
	 * or to get the IAxisScaleConverter of the
	 * secondary axes.
	 */
	private Map<Integer, IAxisSettings> xAxisSettingsMap;
	private Map<Integer, IAxisSettings> yAxisSettingsMap;

	public AbstractExtendedChart(Composite parent, int style) {
		super(parent, style);
		xAxisSettingsMap = new HashMap<Integer, IAxisSettings>();
		yAxisSettingsMap = new HashMap<Integer, IAxisSettings>();
		resetCoordinates();
	}

	@Override
	public boolean isUseZeroY() {

		return useZeroY;
	}

	@Override
	public void setUseZeroY(boolean useZeroY) {

		this.useZeroY = useZeroY;
	}

	@Override
	public boolean isUseZeroX() {

		return useZeroX;
	}

	@Override
	public void setUseZeroX(boolean useZeroX) {

		this.useZeroX = useZeroX;
	}

	@Override
	public double getLength() {

		return length;
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
	public boolean isUseRangeRestriction() {

		return useRangeRestriction;
	}

	@Override
	public void setUseRangeRestriction(boolean useRangeRestriction) {

		this.useRangeRestriction = useRangeRestriction;
	}

	@Override
	public double getFactorExtendMinX() {

		return factorExtendMinX;
	}

	@Override
	public void setFactorExtendMinX(double factorExtendMinX) {

		this.factorExtendMinX = factorExtendMinX;
	}

	@Override
	public double getFactorExtendMaxX() {

		return factorExtendMaxX;
	}

	@Override
	public void setFactorExtendMaxX(double factorExtendMaxX) {

		this.factorExtendMaxX = factorExtendMaxX;
	}

	@Override
	public double getFactorExtendMinY() {

		return factorExtendMinY;
	}

	@Override
	public void setFactorExtendMinY(double factorExtendMinY) {

		this.factorExtendMinY = factorExtendMinY;
	}

	@Override
	public double getFactorExtendMaxY() {

		return factorExtendMaxY;
	}

	@Override
	public void setFactorExtendMaxY(double factorExtendMaxY) {

		this.factorExtendMaxY = factorExtendMaxY;
	}

	public Map<Integer, IAxisSettings> getXAxisSettingsMap() {

		return xAxisSettingsMap;
	}

	public Map<Integer, IAxisSettings> getYAxisSettingsMap() {

		return yAxisSettingsMap;
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
				if(useZeroX) {
					range.lower = (range.lower < 0) ? 0 : range.lower;
				} else {
					range.lower = (range.lower < minX) ? minX : range.lower;
				}
				extendRange(range, extendedMinX, extendedMaxX, factorExtendMinX, factorExtendMaxX);
			} else {
				/*
				 * Y-AXIS
				 */
				if(useZeroY) {
					range.lower = (range.lower < 0) ? 0 : range.lower;
				} else {
					range.lower = (range.lower < minY) ? minY : range.lower;
				}
				extendRange(range, extendedMinY, extendedMaxY, factorExtendMinY, factorExtendMaxY);
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
	public ISeries createSeries(SeriesType seriesType, double[] xSeries, double[] ySeries, String id) throws SeriesException {

		if(xSeries.length == ySeries.length) {
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
					Range adjustedRange = new Range(start, end);
					axis.setRange(adjustedRange);
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
					Range adjustedRange = new Range(start, end);
					axis.setRange(adjustedRange);
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

		length = 0;
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
		length = Math.max(length, xSeries.length);
		minX = Math.min(minX, seriesMinX);
		minX = (useZeroX && minX < 0.0d) ? 0.0d : minX;
		maxX = Math.max(maxX, seriesMaxX);
		minY = Math.min(minY, seriesMinY);
		minY = (useZeroY && minY < 0.0d) ? 0.0d : minY;
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
			extendedMinX -= extendedMinX * factorExtendMinX;
		} else {
			extendedMinX += extendedMinX * factorExtendMinX;
		}
		/*
		 * Max X
		 */
		extendedMaxX = maxX;
		if(extendedMaxX > 0.0d) {
			extendedMaxX += extendedMaxX * factorExtendMaxX;
		} else {
			extendedMaxX -= extendedMaxX * factorExtendMaxX;
		}
		/*
		 * Min Y
		 */
		extendedMinY = minY;
		if(extendedMinY > 0.0d) {
			extendedMinY -= extendedMinY * factorExtendMinY;
		} else {
			extendedMinY += extendedMinY * factorExtendMinY;
		}
		/*
		 * Max Y
		 */
		extendedMaxY = maxY;
		if(extendedMaxY > 0.0d) {
			extendedMaxY += extendedMaxY * factorExtendMaxY;
		} else {
			extendedMaxY -= extendedMaxY * factorExtendMaxY;
		}
	}
}
