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

import org.eclipse.eavp.service.swtchart.exceptions.SeriesException;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Slider;
import org.swtchart.IAxis;
import org.swtchart.IAxis.Direction;
import org.swtchart.IAxisSet;
import org.swtchart.ISeries;
import org.swtchart.ISeries.SeriesType;

import org.swtchart.ISeriesSet;
import org.swtchart.Range;

public class ScrollableChart extends Composite implements IScrollableChart, IEventHandler, IExtendedChart {

	private static final int Y_ACTIVE_AREA_RANGE_INFO = 47;
	private static final int MILLISECONDS_SHOW_RANGE_INFO_HINT = 1000;
	private static final Color COLOR_RED = Display.getCurrent().getSystemColor(SWT.COLOR_RED);
	//
	private Slider sliderVertical;
	private Slider sliderHorizontal;
	private Composite compositeChartArea;
	private RangeInfoUI rangeInfoUI;
	private BaseChart baseChart;
	//
	private IChartSettings chartSettings;
	private boolean showRangeInfoHint = true;

	public ScrollableChart(Composite parent, int style) {
		super(parent, style);
		chartSettings = new ChartSettings();
		initialize();
	}

	@Override
	public IChartSettings getChartSettings() {

		return chartSettings;
	}

	@Override
	public void applySettings(IChartSettings chartSettings) {

		this.chartSettings = chartSettings;
		/*
		 * Modify the chart.
		 */
		baseChart.suspendUpdate(true);
		modifyChart();
		baseChart.suspendUpdate(false);
		/*
		 * Adjust the series.
		 */
		ISeriesSet seriesSet = baseChart.getSeriesSet();
		if(seriesSet.getSeries().length > 0) {
			adjustRange(true);
			baseChart.redraw();
		}
	}

	@Override
	public BaseChart getBaseChart() {

		return baseChart;
	}

	@Override
	public ISeries createSeries(SeriesType seriesType, double[] xSeries, double[] ySeries, String id) throws SeriesException {

		ISeries series = baseChart.createSeries(seriesType, xSeries, ySeries, id);
		resetSlider();
		displayRangeInfo();
		return series;
	}

	@Override
	public void deleteSeries(String id) {

		baseChart.deleteSeries(id);
		resetSlider();
	}

	@Override
	public void setRange(String axis, Range range) {

		if(axis != null && range != null) {
			setRange(axis, range.lower, range.upper);
		}
	}

	@Override
	public void setRange(String axis, double start, double stop) {

		baseChart.setRange(axis, start, stop);
		setSliderSelection(false);
	}

	@Override
	public void adjustRange(boolean adjustMinMax) {

		baseChart.adjustRange(adjustMinMax);
		resetSlider();
	}

	@Override
	public void adjustSecondaryXAxes() {

		baseChart.adjustSecondaryXAxes();
	}

	@Override
	public void adjustSecondaryYAxes() {

		baseChart.adjustSecondaryYAxes();
	}

	@Override
	public void handleEvent(Event event) {

		switch(event.type) {
			case SWT.KeyDown:
				handleKeyDownEvent(event);
				break;
			case SWT.KeyUp:
				handleKeyUpEvent(event);
				break;
			case SWT.MouseMove:
				handleMouseMoveEvent(event);
				break;
			case SWT.MouseDown:
				handleMouseDownEvent(event);
				break;
			case SWT.MouseUp:
				handleMouseUpEvent(event);
				break;
			case SWT.MouseWheel:
				handleMouseWheel(event);
				break;
			case SWT.MouseDoubleClick:
				handleMouseDoubleClick(event);
				break;
			case SWT.Selection:
				handleSelectionEvent(event);
				break;
		}
	}

	@Override
	public void handleMouseMoveEvent(Event event) {

		baseChart.handleMouseMoveEvent(event);
	}

	@Override
	public void handleMouseDownEvent(Event event) {

		baseChart.handleMouseDownEvent(event);
	}

	@Override
	public void handleMouseUpEvent(Event event) {

		baseChart.handleMouseUpEvent(event);
	}

	@Override
	public void handleMouseWheel(Event event) {

		baseChart.handleMouseWheel(event);
	}

	@Override
	public void handleMouseDoubleClick(Event event) {

		baseChart.handleMouseDoubleClick(event);
	}

	@Override
	public void handleKeyDownEvent(Event event) {

		baseChart.handleKeyDownEvent(event);
	}

	@Override
	public void handleKeyUpEvent(Event event) {

		baseChart.handleKeyUpEvent(event);
	}

	@Override
	public void handleSelectionEvent(Event event) {

		baseChart.handleSelectionEvent(event);
	}

	@Override
	public void paintControl(PaintEvent e) {

		baseChart.paintControl(e);
	}

	private void modifyChart() {

		setSliderVisibility();
		setRangeInfoVisibility();
		//
		baseChart.getTitle().setText(chartSettings.getTitle());
		baseChart.getTitle().setVisible(chartSettings.isTitleVisible());
		baseChart.getTitle().setForeground(chartSettings.getTitleColor());
		/*
		 * Primary axes
		 */
		IAxisSet axisSet = baseChart.getAxisSet();
		IAxis xAxisPrimary = axisSet.getXAxis(BaseChart.ID_PRIMARY_X_AXIS);
		setAxisSettings(xAxisPrimary, chartSettings.getPrimaryAxisSettingsX());
		IAxis yAxisPrimary = axisSet.getYAxis(BaseChart.ID_PRIMARY_Y_AXIS);
		setAxisSettings(yAxisPrimary, chartSettings.getPrimaryAxisSettingsY());
		/*
		 * Range Info
		 */
		rangeInfoUI.setXYLabels(xAxisPrimary.getTitle().getText(), yAxisPrimary.getTitle().getText());
		/*
		 * Secondary axes
		 */
		addSecondaryAxesX(axisSet, chartSettings);
		addSecondaryAxesY(axisSet, chartSettings);
		//
		baseChart.setOrientation(chartSettings.getOrientation());
		baseChart.getLegend().setVisible(chartSettings.isLegendVisible());
		baseChart.setBackground(chartSettings.getBackground());
		baseChart.setBackgroundInPlotArea(chartSettings.getBackgroundInPlotArea());
		baseChart.enableCompress(chartSettings.isEnableCompress());
		baseChart.setUseZeroX(chartSettings.isUseZeroX());
		baseChart.setUseZeroY(chartSettings.isUseZeroY());
	}

	private void setSliderVisibility() {

		/*
		 * pack(); doesn't work???!!! Why? It should!
		 * Exclude and layout did the trick.
		 */
		GridData gridDataVertical = (GridData)sliderVertical.getLayoutData();
		gridDataVertical.exclude = !chartSettings.isVerticalSliderVisible();
		sliderVertical.setVisible(chartSettings.isVerticalSliderVisible());
		//
		GridData gridDataHorizontal = (GridData)sliderHorizontal.getLayoutData();
		gridDataHorizontal.exclude = !chartSettings.isHorizontalSliderVisible();
		sliderHorizontal.setVisible(chartSettings.isHorizontalSliderVisible());
		//
		layout(false);
	}

	private void setRangeInfoVisibility() {

		GridData gridData = (GridData)rangeInfoUI.getLayoutData();
		gridData.exclude = !chartSettings.isRangeInfoVisible();
		rangeInfoUI.setVisible(chartSettings.isRangeInfoVisible());
		//
		layout(false);
	}

	private void resetSlider() {

		setSliderSelection(true);
	}

	private void setSliderSelection(boolean calculateIncrement) {

		IAxis xAxis = baseChart.getAxisSet().getXAxis(BaseChart.ID_PRIMARY_X_AXIS);
		IAxis yAxis = baseChart.getAxisSet().getYAxis(BaseChart.ID_PRIMARY_Y_AXIS);
		//
		if(xAxis != null && yAxis != null) {
			/*
			 * Take care of Horizontal or Vertical orientation.
			 */
			int minX = (int)baseChart.getMinX();
			int maxX = (int)baseChart.getMaxX();
			int minY = (int)baseChart.getMinY();
			int maxY = (int)baseChart.getMaxY();
			//
			int minSelectionX = (int)xAxis.getRange().lower;
			int maxSelectionX = (int)xAxis.getRange().upper;
			int thumbSelectionX = (int)(maxSelectionX - minSelectionX);
			//
			int minSelectionY = (int)yAxis.getRange().lower;
			int maxSelectionY = (int)yAxis.getRange().upper;
			int thumbSelectionY = (int)(maxSelectionY - minSelectionY);
			//
			boolean isHorizontal = isOrientationHorizontal();
			//
			sliderVertical.setMinimum((isHorizontal) ? minY : minX);
			sliderVertical.setMaximum((isHorizontal) ? maxY : maxX);
			sliderVertical.setThumb((isHorizontal) ? thumbSelectionY : thumbSelectionX);
			sliderVertical.setSelection((isHorizontal) ? minSelectionY : minSelectionX);
			//
			sliderHorizontal.setMinimum((isHorizontal) ? minX : minY);
			sliderHorizontal.setMaximum((isHorizontal) ? maxX : maxY);
			sliderHorizontal.setThumb((isHorizontal) ? thumbSelectionX : thumbSelectionY);
			sliderHorizontal.setSelection((isHorizontal) ? minSelectionX : minSelectionY);
			/*
			 * Calculate the increment.
			 */
			if(calculateIncrement) {
				int thumbX = maxX - minX;
				int thumbY = maxY - minY;
				int incrementX = calculateIncrement(thumbX, baseChart.getLength());
				int incrementY = calculateIncrement(thumbY, baseChart.getLength());
				sliderVertical.setIncrement((isHorizontal) ? incrementY : incrementX);
				sliderHorizontal.setPageIncrement((isHorizontal) ? incrementX : incrementY);
			}
		}
	}

	private boolean isOrientationHorizontal() {

		return (baseChart.getOrientation() == SWT.HORIZONTAL) ? true : false;
	}

	private int calculateIncrement(double selection, double length) {

		if(length == 0) {
			return 0;
		} else {
			int increment = (int)(selection / length);
			return (increment < 1) ? 1 : increment;
		}
	}

	private Range calculateShiftedRange(Range range, Slider slider) {

		int selection = slider.getSelection();
		double min = selection;
		double max = (range.upper - range.lower) + selection;
		return new Range(min, max);
	}

	private void addSecondaryAxesX(IAxisSet axisSet, IChartSettings chartSettings) {

		for(int id : axisSet.getXAxisIds()) {
			if(id != BaseChart.ID_PRIMARY_X_AXIS) {
				axisSet.deleteXAxis(id);
			}
		}
		baseChart.getXAxisScaleConverterMap().clear();
		//
		for(ISecondaryAxisSettings secondaryAxisSettings : chartSettings.getSecondaryAxisSettingsListX()) {
			int xAxisId = axisSet.createXAxis();
			IAxis xAxisSecondary = axisSet.getXAxis(xAxisId);
			setAxisSettings(xAxisSecondary, secondaryAxisSettings);
			baseChart.getXAxisScaleConverterMap().put(xAxisId, secondaryAxisSettings.getAxisScaleConverter());
		}
	}

	private void addSecondaryAxesY(IAxisSet axisSet, IChartSettings chartSettings) {

		for(int id : axisSet.getYAxisIds()) {
			if(id != BaseChart.ID_PRIMARY_Y_AXIS) {
				axisSet.deleteYAxis(id);
			}
		}
		baseChart.getYAxisScaleConverterMap().clear();
		//
		for(ISecondaryAxisSettings secondaryAxisSettings : chartSettings.getSecondaryAxisSettingsListY()) {
			int yAxisId = axisSet.createYAxis();
			IAxis yAxisSecondary = axisSet.getYAxis(yAxisId);
			setAxisSettings(yAxisSecondary, secondaryAxisSettings);
			baseChart.getYAxisScaleConverterMap().put(yAxisId, secondaryAxisSettings.getAxisScaleConverter());
		}
	}

	private void setAxisSettings(IAxis axis, IAxisSettings axisSettings) {

		if(axis != null && axisSettings != null) {
			axis.getTitle().setText(axisSettings.getTitle());
			axis.getTick().setFormat(axisSettings.getDecimalFormat());
			axis.getTitle().setVisible(axisSettings.isVisible());
			axis.getTick().setVisible(axisSettings.isVisible());
			axis.setPosition(axisSettings.getPosition());
			/*
			 * Set the color on demand.
			 */
			Color color = axisSettings.getColor();
			if(color != null) {
				axis.getTitle().setForeground(color);
				axis.getTick().setForeground(color);
			}
			/*
			 * Apply primary axis specific settings.
			 */
			if(axisSettings instanceof IPrimaryAxisSettings) {
				IPrimaryAxisSettings primaryAxisSettings = (IPrimaryAxisSettings)axisSettings;
				axis.enableLogScale(primaryAxisSettings.isEnableLogScale());
				/*
				 * Category is only valid for the X-Axis.
				 */
				if(axis.getDirection() == Direction.X) {
					axis.enableCategory(primaryAxisSettings.isEnableCategory());
					axis.setCategorySeries(primaryAxisSettings.getCategorySeries());
				}
			}
		}
	}

	private void fireUpdateCustomSelectionHandlers(Event event) {

		baseChart.fireUpdateCustomSelectionHandlers(event);
		displayRangeInfo();
	}

	private void displayRangeInfo() {

		IAxis xAxis = baseChart.getAxisSet().getXAxis(BaseChart.ID_PRIMARY_X_AXIS);
		IAxis yAxis = baseChart.getAxisSet().getYAxis(BaseChart.ID_PRIMARY_Y_AXIS);
		rangeInfoUI.setXYRanges(xAxis.getRange(), yAxis.getRange());
	}

	private void initialize() {

		this.setLayout(new FillLayout());
		Composite composite = new Composite(this, SWT.NONE);
		composite.setLayout(new GridLayout(2, false));
		/*
		 * Slider Vertical
		 */
		sliderVertical = new Slider(composite, SWT.VERTICAL);
		sliderVertical.setLayoutData(new GridData(GridData.FILL_VERTICAL));
		sliderVertical.setOrientation(SWT.RIGHT_TO_LEFT); // See Bug #511257
		sliderVertical.setVisible(true);
		sliderVertical.addListener(SWT.Selection, new Listener() {

			@Override
			public void handleEvent(Event event) {

				IAxis xAxis = baseChart.getAxisSet().getXAxis(BaseChart.ID_PRIMARY_X_AXIS);
				IAxis yAxis = baseChart.getAxisSet().getYAxis(BaseChart.ID_PRIMARY_Y_AXIS);
				//
				if(xAxis != null && yAxis != null) {
					Range range = calculateShiftedRange(yAxis.getRange(), sliderVertical);
					if(isOrientationHorizontal()) {
						yAxis.setRange(range);
						baseChart.adjustMinMaxRange(yAxis);
						adjustSecondaryYAxes();
					} else {
						xAxis.setRange(range);
						baseChart.adjustMinMaxRange(xAxis);
						adjustSecondaryXAxes();
					}
					//
					fireUpdateCustomSelectionHandlers(event);
					baseChart.redraw();
				}
			}
		});
		/*
		 * Chart Area
		 */
		compositeChartArea = new Composite(composite, SWT.NONE);
		compositeChartArea.setLayoutData(new GridData(GridData.FILL_BOTH));
		compositeChartArea.setLayout(new GridLayout(1, true));
		/*
		 * Chart Range Settings
		 */
		rangeInfoUI = new RangeInfoUI(compositeChartArea, SWT.NONE);
		rangeInfoUI.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		/*
		 * Chart Plot
		 */
		baseChart = new BaseChart(compositeChartArea, SWT.NONE);
		baseChart.setLayoutData(new GridData(GridData.FILL_BOTH));
		baseChart.addCustomSelectionHandler(new ICustomSelectionHandler() {

			@Override
			public void handleUserSelection(Event event) {

				setSliderSelection(false);
			}
		});
		baseChart.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseDoubleClick(MouseEvent e) {

				if(chartSettings.isRangeInfoVisible()) {
					if(!rangeInfoUI.isVisible()) {
						if(e.y <= Y_ACTIVE_AREA_RANGE_INFO) {
							/*
							 * Show the range info composite.
							 */
							GridData gridData = (GridData)rangeInfoUI.getLayoutData();
							gridData.exclude = false;
							rangeInfoUI.setVisible(true);
							Composite parent = getParent();
							parent.layout(false);
							showRangeInfoHint = true;
							parent.redraw();
						}
					}
				}
			}
		});
		baseChart.addPaintListener(new PaintListener() {

			@Override
			public void paintControl(PaintEvent e) {

				/*
				 * Rectangle (Double Click -> show Range Info)
				 */
				if(!rangeInfoUI.isVisible()) {
					if(showRangeInfoHint) {
						//
						int lineWidth = 1;
						Rectangle rectangle = baseChart.getBounds();
						int width = rectangle.width - lineWidth;
						e.gc.setForeground(COLOR_RED);
						e.gc.setLineWidth(lineWidth);
						e.gc.drawRectangle(0, 0, width, Y_ACTIVE_AREA_RANGE_INFO);
						//
						String label = "Double click to show range info.";
						Point labelSize = e.gc.textExtent(label);
						e.gc.drawText(label, (int)(width / 2.0d - labelSize.x / 2.0d), 5, true);
						/*
						 * Hide the rectangle after x milliseconds.
						 */
						Display.getDefault().asyncExec(new Runnable() {

							@Override
							public void run() {

								try {
									Thread.sleep(MILLISECONDS_SHOW_RANGE_INFO_HINT);
								} catch(InterruptedException e) {
									System.out.println(e);
								}
								showRangeInfoHint = false;
								baseChart.redraw();
							}
						});
					}
				}
			}
		});
		//
		Composite plotArea = baseChart.getPlotArea();
		plotArea.addListener(SWT.KeyDown, this);
		plotArea.addListener(SWT.KeyUp, this);
		plotArea.addListener(SWT.MouseMove, this);
		plotArea.addListener(SWT.MouseDown, this);
		plotArea.addListener(SWT.MouseUp, this);
		plotArea.addListener(SWT.MouseWheel, this);
		plotArea.addListener(SWT.MouseDoubleClick, this);
		plotArea.addListener(SWT.Resize, this);
		plotArea.addPaintListener(this);
		/*
		 * Slider Horizontal
		 */
		sliderHorizontal = new Slider(composite, SWT.HORIZONTAL);
		GridData gridData = new GridData(GridData.FILL_HORIZONTAL);
		gridData.horizontalSpan = 2;
		sliderHorizontal.setLayoutData(gridData);
		sliderHorizontal.setOrientation(SWT.LEFT_TO_RIGHT);
		sliderHorizontal.setVisible(true);
		sliderHorizontal.addListener(SWT.Selection, new Listener() {

			@Override
			public void handleEvent(Event event) {

				IAxis xAxis = baseChart.getAxisSet().getXAxis(BaseChart.ID_PRIMARY_X_AXIS);
				IAxis yAxis = baseChart.getAxisSet().getYAxis(BaseChart.ID_PRIMARY_Y_AXIS);
				//
				if(xAxis != null && yAxis != null) {
					Range range = calculateShiftedRange(xAxis.getRange(), sliderHorizontal);
					if(isOrientationHorizontal()) {
						xAxis.setRange(range);
						baseChart.adjustMinMaxRange(xAxis);
						adjustSecondaryXAxes();
					} else {
						yAxis.setRange(range);
						baseChart.adjustMinMaxRange(yAxis);
						adjustSecondaryYAxes();
					}
					//
					fireUpdateCustomSelectionHandlers(event);
					baseChart.redraw();
				}
			}
		});
	}
}