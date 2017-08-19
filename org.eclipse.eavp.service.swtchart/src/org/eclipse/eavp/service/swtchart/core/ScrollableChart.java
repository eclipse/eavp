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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.eavp.service.swtchart.exceptions.SeriesException;
import org.eclipse.eavp.service.swtchart.linecharts.LineChart;
import org.eclipse.eavp.service.swtchart.marker.CenterMarker;
import org.eclipse.eavp.service.swtchart.marker.PositionLegend;
import org.eclipse.eavp.service.swtchart.marker.PositionMarker;
import org.eclipse.eavp.service.swtchart.menu.ISeriesExportConverter;
import org.eclipse.eavp.service.swtchart.menu.ImageBMPExport;
import org.eclipse.eavp.service.swtchart.menu.ImageJPGExport;
import org.eclipse.eavp.service.swtchart.menu.ImagePNGExport;
import org.eclipse.eavp.service.swtchart.menu.ImageRScriptExport;
import org.eclipse.eavp.service.swtchart.menu.LaTeXTableExport;
import org.eclipse.eavp.service.swtchart.menu.PrinterExport;
import org.eclipse.eavp.service.swtchart.menu.TabSeparatedValuesExport;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StyleRange;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Slider;
import org.swtchart.IAxis;
import org.swtchart.IAxis.Direction;
import org.swtchart.IAxis.Position;
import org.swtchart.IAxisSet;
import org.swtchart.IAxisTick;
import org.swtchart.IGrid;
import org.swtchart.ILegend;
import org.swtchart.IPlotArea;
import org.swtchart.ISeries;
import org.swtchart.ISeriesSet;
import org.swtchart.ITitle;
import org.swtchart.Range;

public class ScrollableChart extends Composite implements IScrollableChart, IEventHandler, IExtendedChart {

	public static final int NO_COMPRESS_TO_LENGTH = Integer.MAX_VALUE;
	//
	private static final int MILLISECONDS_SHOW_RANGE_INFO_HINT = 1000;
	//
	private static final String MENU_MARKER_AND_LEGENDS = "Marker / Legends";
	private static final String MENU_EXPORT_CHART_SELECTION = "Export Chart Selection";
	//
	private static final String RESET_CHART = "Reset Chart (1:1)";
	private static final String RESET_SELECTED_SERIES = "Reset Selected Series";
	private static final String SHOW_RANGE_SELECTOR = "Show Range Selector";
	private static final String SHOW_POSITION_MARKER = "Show Position Marker";
	private static final String HIDE_POSITION_MARKER = "Hide Position Marker";
	private static final String SHOW_CENTER_MARKER = "Show Center Marker";
	private static final String HIDE_CENTER_MARKER = "Hide Center Marker";
	private static final String SHOW_POSITION_LEGEND = "Show Position Legend";
	private static final String HIDE_POSITION_LEGEND = "Hide Position Legend";
	private static final String SHOW_SERIES_LEGEND = "Show Series Legend";
	private static final String HIDE_SERIES_LEGEND = "Hide Series Legend";
	//
	private Map<String, ISeriesExportConverter> exportConverterMap;
	//
	private Slider sliderVertical;
	private Slider sliderHorizontal;
	private RangeSelector rangeSelector;
	private BaseChart baseChart;
	//
	private IChartSettings chartSettings;
	private boolean showRangeSelectorHint = true;
	private RangeHintPaintListener rangeHintPaintListener;
	/*
	 * This list contains all scrollable charts
	 * that are linked with the current editor.
	 */
	private List<ScrollableChart> linkedScrollableCharts;
	//
	private PositionMarker positionMarker;
	private CenterMarker centerMarker;
	private PositionLegend positionLegend;
	//
	private MenuItem positionMarkerMenuItem = null;
	private MenuItem centerMarkerMenuItem = null;
	private MenuItem positionLegendMenuItem = null;
	private MenuItem seriesLegendMenuItem = null;

	/**
	 * This constructor is used, when clazz.newInstance() is needed.
	 */
	public ScrollableChart() {
		this(getSeparateShell(), SWT.NONE);
	}

	public ScrollableChart(Composite parent, int style) {
		super(parent, style);
		//
		chartSettings = new ChartSettings();
		exportConverterMap = new HashMap<String, ISeriesExportConverter>();
		linkedScrollableCharts = new ArrayList<ScrollableChart>();
		//
		initialize();
	}

	private class RangeHintPaintListener implements PaintListener {

		@Override
		public void paintControl(PaintEvent e) {

			/*
			 * Rectangle (Double Click -> show Range Info)
			 */
			if(!rangeSelector.isVisible() && chartSettings.isEnableRangeSelector()) {
				if(showRangeSelectorHint) {
					int lineWidth = 1;
					Rectangle rectangle = baseChart.getBounds();
					int width = rectangle.width - lineWidth;
					e.gc.setForeground(chartSettings.getColorHintRangeSelector());
					e.gc.setLineWidth(lineWidth);
					Rectangle rectangleInfo = new Rectangle(0, 0, width, 26);
					e.gc.drawRectangle(rectangleInfo);
					//
					ITitle title = getBaseChart().getTitle();
					if(title.getForeground().equals(baseChart.getBackground())) {
						/*
						 * Draw the message.
						 */
						String label = "Double click to show range info.";
						Point labelSize = e.gc.textExtent(label);
						e.gc.drawText(label, (int)(width / 2.0d - labelSize.x / 2.0d), 5, true);
					}
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
							showRangeSelectorHint = false;
							baseChart.redraw();
						}
					});
				}
			}
		}
	}

	@Override
	public IChartSettings getChartSettings() {

		return chartSettings;
	}

	public void addLinkedScrollableChart(ScrollableChart scrollableChart) {

		linkedScrollableCharts.add(scrollableChart);
	}

	public void removeLinkedScrollableChart(ScrollableChart scrollableChart) {

		linkedScrollableCharts.remove(scrollableChart);
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
		}
		baseChart.redraw();
	}

	@Override
	public BaseChart getBaseChart() {

		return baseChart;
	}

	@Override
	public ISeries createSeries(ISeriesData seriesData, ISeriesSettings seriesSettings) throws SeriesException {

		ISeries series = baseChart.createSeries(seriesData, seriesSettings);
		resetSlider();
		return series;
	}

	@Override
	public void deleteSeries() {

		for(ISeries series : baseChart.getSeriesSet().getSeries()) {
			baseChart.deleteSeries(series.getId());
		}
		resetSlider();
		redraw();
	}

	@Override
	public void deleteSeries(String id) {

		baseChart.deleteSeries(id);
		resetSlider();
		redraw();
	}

	@Override
	public void appendSeries(ISeriesData seriesData) {

		baseChart.appendSeries(seriesData);
		adjustRange(true);
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
		updateLinkedCharts();
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
		//
		if(positionMarker.isDraw()) {
			positionMarker.setActualPosition(event.x, event.y);
			getBaseChart().getPlotArea().redraw();
		}
		//
		if(positionLegend.isDraw()) {
			positionLegend.setActualPosition(event.x, event.y);
			getBaseChart().getPlotArea().redraw();
		}
	}

	@Override
	public void handleMouseDownEvent(Event event) {

		baseChart.handleMouseDownEvent(event);
	}

	@Override
	public void handleMouseUpEvent(Event event) {

		baseChart.handleMouseUpEvent(event);
		updateLinkedCharts();
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
		widgetSelected(event);
	}

	@Override
	public void paintControl(PaintEvent e) {

		baseChart.paintControl(e);
	}

	protected ISeriesData calculateSeries(ISeriesData seriesData) {

		return calculateSeries(seriesData, NO_COMPRESS_TO_LENGTH); // No compression.
	}

	/**
	 * Use compress series only if it's absolutely necessary.
	 * 
	 * @param seriesData
	 * @param compressToLength
	 * @return ISeriesData
	 */
	protected ISeriesData calculateSeries(ISeriesData seriesData, int compressToLength) {

		double[] xSeries = seriesData.getXSeries();
		double[] ySeries = seriesData.getYSeries();
		int seriesLength = ySeries.length;
		//
		if(seriesLength > compressToLength) {
			/*
			 * Capture the compressed data.
			 * The final size is not known yet.
			 */
			List<Double> xSeriesCompressed = new ArrayList<Double>();
			List<Double> ySeriesCompressed = new ArrayList<Double>();
			/*
			 * First x,y value.
			 */
			xSeriesCompressed.add(xSeries[0]);
			ySeriesCompressed.add(ySeries[0]);
			//
			int moduloValue = seriesLength / compressToLength;
			for(int i = 1; i < ySeries.length - 1; i++) {
				/*
				 * Filter the values.
				 */
				double y = ySeries[i];
				boolean addValue = false;
				//
				if(moduloValue > 0 && i % moduloValue == 0) {
					addValue = true;
				}
				//
				if(addValue) {
					xSeriesCompressed.add(xSeries[i]);
					ySeriesCompressed.add(y);
				}
			}
			/*
			 * Last x,y value.
			 */
			xSeriesCompressed.add(xSeries[xSeries.length - 1]);
			ySeriesCompressed.add(ySeries[ySeries.length - 1]);
			/*
			 * Compression
			 */
			double[] xCompressed = xSeriesCompressed.stream().mapToDouble(d -> d).toArray();
			double[] yCompressed = ySeriesCompressed.stream().mapToDouble(d -> d).toArray();
			//
			return new SeriesData(xCompressed, yCompressed, seriesData.getId());
		} else {
			/*
			 * No compression.
			 */
			return seriesData;
		}
	}

	/**
	 * Returns whether the series exceeds the given length hint or not.
	 * 
	 * @param xSeries
	 * @param ySeries
	 * @param lengthHintDataPoints
	 * @return boolean
	 */
	protected boolean isLargeDataSet(double[] xSeries, double[] ySeries, int lengthHintDataPoints) {

		boolean isLargeDataSet = false;
		if(xSeries.length == ySeries.length) {
			if(xSeries.length > lengthHintDataPoints) {
				isLargeDataSet = true;
			}
		}
		return isLargeDataSet;
	}

	/**
	 * Create a shell with max width and height.
	 * 
	 * @return Shell
	 */
	private static Shell getSeparateShell() {

		Display display = Display.getDefault();
		Rectangle bounds = display.getBounds();
		Shell shell = new Shell(display);
		shell.setLayout(new FillLayout());
		shell.setSize(bounds.width, bounds.height);
		shell.setLocation(0, 0);
		return shell;
	}

	private void modifyChart() {

		setSliderVisibility();
		setRangeInfoVisibility(chartSettings.isEnableRangeSelector());
		//
		ITitle title = baseChart.getTitle();
		title.setText(chartSettings.getTitle());
		title.setVisible(chartSettings.isTitleVisible());
		title.setForeground(chartSettings.getTitleColor());
		//
		ILegend legend = baseChart.getLegend();
		legend.setPosition(chartSettings.getLegendPosition());
		legend.setVisible(chartSettings.isLegendVisible());
		/*
		 * Primary and Secondary axes
		 */
		addPrimaryAxisX(chartSettings);
		addPrimaryAxisY(chartSettings);
		addSecondaryAxesX(chartSettings);
		addSecondaryAxesY(chartSettings);
		/*
		 * Range Info
		 */
		rangeSelector.resetRanges();
		//
		setBackground(chartSettings.getBackground());
		baseChart.setOrientation(chartSettings.getOrientation());
		baseChart.setBackground(chartSettings.getBackgroundChart());
		baseChart.setBackgroundInPlotArea(chartSettings.getBackgroundPlotArea());
		baseChart.enableCompress(chartSettings.isEnableCompress());
		baseChart.setRangeRestriction(chartSettings.getRangeRestriction());
		/*
		 * Additional actions.
		 */
		setCustomPaintListener();
		updateRangeHintPaintListener();
		setMenuItems();
	}

	private void setCustomPaintListener() {

		setPositionMarker();
		setCenterMarker();
		setPositionLegend();
	}

	private void setPositionMarker() {

		IPlotArea plotArea = (IPlotArea)baseChart.getPlotArea();
		//
		if(positionMarker != null) {
			plotArea.removeCustomPaintListener(positionMarker);
		}
		//
		positionMarker = new PositionMarker();
		positionMarker.setForegroundColor(chartSettings.getColorPositionMarker());
		plotArea.addCustomPaintListener(positionMarker);
		//
		if(chartSettings.isShowPositionMarker()) {
			positionMarker.setDraw(true);
		} else {
			positionMarker.setDraw(false);
		}
	}

	private void setCenterMarker() {

		IPlotArea plotArea = (IPlotArea)baseChart.getPlotArea();
		//
		if(centerMarker != null) {
			plotArea.removeCustomPaintListener(centerMarker);
		}
		//
		centerMarker = new CenterMarker();
		centerMarker.setForegroundColor(chartSettings.getColorCenterMarker());
		plotArea.addCustomPaintListener(centerMarker);
		//
		if(chartSettings.isShowCenterMarker()) {
			centerMarker.setDraw(true);
		} else {
			centerMarker.setDraw(false);
		}
	}

	private void setPositionLegend() {

		IPlotArea plotArea = (IPlotArea)baseChart.getPlotArea();
		//
		if(positionLegend != null) {
			plotArea.removeCustomPaintListener(positionLegend);
		}
		//
		positionLegend = new PositionLegend(baseChart);
		positionLegend.setForegroundColor(chartSettings.getColorPositionLegend());
		plotArea.addCustomPaintListener(positionLegend);
		//
		if(chartSettings.isShowPositionLegend()) {
			positionLegend.setDraw(true);
		} else {
			positionLegend.setDraw(false);
		}
	}

	private void updateRangeHintPaintListener() {

		if(rangeHintPaintListener != null) {
			baseChart.removePaintListener(rangeHintPaintListener);
		}
		//
		rangeHintPaintListener = new RangeHintPaintListener();
		baseChart.addPaintListener(rangeHintPaintListener);
	}

	private void setMenuItems() {

		/*
		 * Create the menu if requested.
		 */
		if(chartSettings.isCreateMenu()) {
			/*
			 * Add the default export converter.
			 */
			addSeriesExportConverter(new ImageJPGExport());
			addSeriesExportConverter(new ImagePNGExport());
			addSeriesExportConverter(new ImageBMPExport());
			addSeriesExportConverter(new TabSeparatedValuesExport());
			addSeriesExportConverter(new LaTeXTableExport());
			addSeriesExportConverter(new ImageRScriptExport());
			addSeriesExportConverter(new PrinterExport());
			//
			createPopupMenu();
		} else {
			/*
			 * No menu
			 */
			exportConverterMap.clear();
			baseChart.getPlotArea().setMenu(null);
		}
	}

	private void addSeriesExportConverter(ISeriesExportConverter seriesExportConverter) {

		exportConverterMap.put(seriesExportConverter.getName(), seriesExportConverter);
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

	private void setRangeInfoVisibility(boolean isVisible) {

		GridData gridData = (GridData)rangeSelector.getLayoutData();
		gridData.exclude = !isVisible;
		rangeSelector.setVisible(isVisible);
		layout(false);
	}

	private void resetSlider() {

		setSliderSelection(true);
		updateLinkedCharts();
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
			/*
			 * Set the range info and update linked charts.
			 */
			displayRangeInfo(xAxis, yAxis);
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

	private void addPrimaryAxisX(IChartSettings chartSettings) {

		IAxisSet axisSet = baseChart.getAxisSet();
		IAxis xAxisPrimary = axisSet.getXAxis(BaseChart.ID_PRIMARY_X_AXIS);
		IPrimaryAxisSettings primaryAxisSettings = chartSettings.getPrimaryAxisSettingsX();
		setAxisSettings(xAxisPrimary, primaryAxisSettings);
		baseChart.putXAxisSettings(BaseChart.ID_PRIMARY_X_AXIS, primaryAxisSettings);
	}

	private void addPrimaryAxisY(IChartSettings chartSettings) {

		IAxisSet axisSet = baseChart.getAxisSet();
		IAxis yAxisPrimary = axisSet.getYAxis(BaseChart.ID_PRIMARY_Y_AXIS);
		IPrimaryAxisSettings primaryAxisSettings = chartSettings.getPrimaryAxisSettingsY();
		setAxisSettings(yAxisPrimary, primaryAxisSettings);
		baseChart.putYAxisSettings(BaseChart.ID_PRIMARY_Y_AXIS, primaryAxisSettings);
	}

	private void addSecondaryAxesX(IChartSettings chartSettings) {

		IAxisSet axisSet = baseChart.getAxisSet();
		for(int id : axisSet.getXAxisIds()) {
			if(id != BaseChart.ID_PRIMARY_X_AXIS) {
				axisSet.deleteXAxis(id);
			}
		}
		/*
		 * Remove all items except the primary axis settings.
		 */
		baseChart.removeXAxisSettings();
		/*
		 * Add the axis settings.
		 */
		for(ISecondaryAxisSettings secondaryAxisSettings : chartSettings.getSecondaryAxisSettingsListX()) {
			int xAxisId = axisSet.createXAxis();
			IAxis xAxisSecondary = axisSet.getXAxis(xAxisId);
			setAxisSettings(xAxisSecondary, secondaryAxisSettings);
			baseChart.putXAxisSettings(xAxisId, secondaryAxisSettings);
		}
	}

	private void addSecondaryAxesY(IChartSettings chartSettings) {

		IAxisSet axisSet = baseChart.getAxisSet();
		for(int id : axisSet.getYAxisIds()) {
			if(id != BaseChart.ID_PRIMARY_Y_AXIS) {
				axisSet.deleteYAxis(id);
			}
		}
		/*
		 * Remove all items except the primary axis settings.
		 */
		baseChart.removeYAxisSettings();
		/*
		 * Add the axis settings.
		 */
		for(ISecondaryAxisSettings secondaryAxisSettings : chartSettings.getSecondaryAxisSettingsListY()) {
			int yAxisId = axisSet.createYAxis();
			IAxis yAxisSecondary = axisSet.getYAxis(yAxisId);
			setAxisSettings(yAxisSecondary, secondaryAxisSettings);
			baseChart.putYAxisSettings(yAxisId, secondaryAxisSettings);
		}
	}

	private void setAxisSettings(IAxis axis, IAxisSettings axisSettings) {

		if(axis != null && axisSettings != null) {
			//
			String axisText = axisSettings.getTitle();
			ITitle title = axis.getTitle();
			title.setText(axisText);
			title.setVisible(axisSettings.isVisible());
			//
			IAxisTick axisTick = axis.getTick();
			axisTick.setFormat(axisSettings.getDecimalFormat());
			axisTick.setVisible(axisSettings.isVisible());
			//
			IGrid grid = axis.getGrid();
			grid.setForeground(axisSettings.getGridColor());
			grid.setStyle(axisSettings.getGridLineStyle());
			//
			axis.setPosition(axisSettings.getPosition());
			/*
			 * Set the color on demand.
			 */
			Color color = axisSettings.getColor();
			if(color != null) {
				title.setForeground(color);
				axisTick.setForeground(color);
			}
			/*
			 * Add a space between the scale and the label.
			 */
			Font font = title.getFont();
			StyleRange styleRange = new StyleRange();
			styleRange.length = axisText.length();
			styleRange.foreground = color;
			styleRange.font = font;
			styleRange.rise = getAxisExtraSpaceTitle(axis, axisSettings);
			title.setStyleRanges(new StyleRange[]{styleRange});
			//
			axis.enableLogScale(axisSettings.isEnableLogScale());
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

	private int getAxisExtraSpaceTitle(IAxis axis, IAxisSettings axisSettings) {

		int extraSpaceTitle = axisSettings.getExtraSpaceTitle();
		int orientation = getChartSettings().getOrientation();
		Direction direction = axis.getDirection();
		/*
		 * Default orientation == SWT.HORIZONTAL
		 */
		if(direction.equals(Direction.X)) {
			/*
			 * X-Axis
			 * Primary = bottom side
			 * Secondary = top side
			 */
			if(axisSettings.getPosition().equals(Position.Primary)) {
				extraSpaceTitle *= -1;
			}
		} else {
			/*
			 * Y-Axis
			 * Primary = left side
			 * Secondary = right side
			 */
			if(axisSettings.getPosition().equals(Position.Secondary)) {
				extraSpaceTitle *= -1;
			}
		}
		/*
		 * Switch the side of the margin.
		 */
		if(orientation == SWT.VERTICAL) {
			extraSpaceTitle *= -1;
		}
		//
		return extraSpaceTitle;
	}

	private void fireUpdateCustomSelectionHandlers(Event event) {

		baseChart.fireUpdateCustomSelectionHandlers(event);
		updateLinkedCharts();
	}

	private void displayRangeInfo(IAxis xAxis, IAxis yAxis) {

		rangeSelector.adjustRanges();
	}

	private void initialize() {

		this.setLayout(new FillLayout());
		Composite composite = new Composite(this, SWT.NONE);
		composite.setLayout(new GridLayout(2, false));
		/*
		 * Composite
		 */
		createSliderVertical(composite);
		createChart(composite);
		createSliderHorizontal(composite);
	}

	private void createSliderVertical(Composite parent) {

		sliderVertical = new Slider(parent, SWT.VERTICAL);
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
					displayRangeInfo(xAxis, yAxis);
					fireUpdateCustomSelectionHandlers(event);
					baseChart.redraw();
				}
			}
		});
	}

	private void createChart(Composite parent) {

		/*
		 * Chart Area
		 */
		Composite composite = new Composite(parent, SWT.NONE);
		composite.setLayoutData(new GridData(GridData.FILL_BOTH));
		composite.setLayout(new GridLayout(1, true));
		//
		createRangeInfoUI(composite);
		createBaseChart(composite);
	}

	private void createRangeInfoUI(Composite parent) {

		rangeSelector = new RangeSelector(parent, SWT.NONE, this);
		rangeSelector.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
	}

	private void createBaseChart(Composite parent) {

		/*
		 * Chart Plot
		 */
		baseChart = new BaseChart(parent, SWT.NONE);
		baseChart.setLayoutData(new GridData(GridData.FILL_BOTH));
		/*
		 * Set the slider range.
		 */
		baseChart.addCustomSelectionHandler(new ICustomSelectionHandler() {

			@Override
			public void handleUserSelection(Event event) {

				setSliderSelection(false);
				updateLinkedCharts();
			}
		});
		/*
		 * Activate the range info UI on double click.
		 */
		baseChart.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseDoubleClick(MouseEvent e) {

				if(chartSettings.isEnableRangeSelector()) {
					if(!rangeSelector.isVisible()) {
						if(e.y <= 47) {
							/*
							 * Show the range info composite.
							 */
							showRangeSelectorHint = true;
							showRangeSelector(showRangeSelectorHint);
						}
					}
				}
			}
		});
		/*
		 * Show the range info hint.
		 */
		rangeHintPaintListener = new RangeHintPaintListener();
		baseChart.addPaintListener(rangeHintPaintListener);
		/*
		 * Add the listeners.
		 */
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
	}

	private void createSliderHorizontal(Composite parent) {

		sliderHorizontal = new Slider(parent, SWT.HORIZONTAL);
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
					displayRangeInfo(xAxis, yAxis);
					fireUpdateCustomSelectionHandlers(event);
					baseChart.redraw();
				}
			}
		});
	}

	private void updateLinkedCharts() {

		IAxisSet axisSet = baseChart.getAxisSet();
		Range rangeX = axisSet.getXAxis(BaseChart.ID_PRIMARY_X_AXIS).getRange();
		Range rangeY = axisSet.getYAxis(BaseChart.ID_PRIMARY_Y_AXIS).getRange();
		/*
		 * Adjust the range of the linked charts.
		 */
		for(ScrollableChart linkedScrollableChart : linkedScrollableCharts) {
			IAxisSet axisSetLinked = linkedScrollableChart.getBaseChart().getAxisSet();
			axisSetLinked.getXAxis(BaseChart.ID_PRIMARY_X_AXIS).setRange(rangeX);
			axisSetLinked.getYAxis(BaseChart.ID_PRIMARY_Y_AXIS).setRange(rangeY);
			linkedScrollableChart.getBaseChart().adjustSecondaryAxes();
			linkedScrollableChart.getBaseChart().redraw();
			/*
			 * The method setSliderSelection(...) is private.
			 * But as we are in the same class, it works.
			 * Funny, I assumed that only protected and public
			 * methods are accessible.
			 */
			linkedScrollableChart.setSliderSelection(false);
		}
	}

	private void showRangeSelector(boolean showRangeSelector) {

		GridData gridData = (GridData)rangeSelector.getLayoutData();
		gridData.exclude = !showRangeSelector;
		rangeSelector.setVisible(showRangeSelector);
		Composite parent = rangeSelector.getParent();
		parent.layout(false);
		parent.redraw();
	}

	public void widgetSelected(Event e) {

		if(!(e.widget instanceof MenuItem)) {
			return;
		}
		//
		MenuItem menuItem = (MenuItem)e.widget;
		if(menuItem.getText().equals(RESET_CHART)) {
			adjustRange(true);
			redraw();
		} else if(menuItem.getText().equals(RESET_SELECTED_SERIES)) {
			baseChart.resetSelectedSeries();
		} else if(menuItem.getText().equals(SHOW_RANGE_SELECTOR)) {
			showRangeSelector(true);
		} else if(menuItem.getText().equals(SHOW_POSITION_MARKER)) {
			positionMarkerMenuItem.setText(HIDE_POSITION_MARKER);
			positionMarker.setDraw(true);
			redraw();
		} else if(menuItem.getText().equals(HIDE_POSITION_MARKER)) {
			positionMarkerMenuItem.setText(SHOW_POSITION_MARKER);
			positionMarker.setDraw(false);
			redraw();
		} else if(menuItem.getText().equals(SHOW_CENTER_MARKER)) {
			centerMarkerMenuItem.setText(HIDE_CENTER_MARKER);
			centerMarker.setDraw(true);
			redraw();
		} else if(menuItem.getText().equals(HIDE_CENTER_MARKER)) {
			centerMarkerMenuItem.setText(SHOW_CENTER_MARKER);
			centerMarker.setDraw(false);
			redraw();
		} else if(menuItem.getText().equals(SHOW_POSITION_LEGEND)) {
			positionLegendMenuItem.setText(HIDE_POSITION_LEGEND);
			positionLegend.setDraw(true);
			redraw();
		} else if(menuItem.getText().equals(HIDE_POSITION_LEGEND)) {
			positionLegendMenuItem.setText(SHOW_POSITION_LEGEND);
			positionLegend.setDraw(false);
			redraw();
		} else if(menuItem.getText().equals(SHOW_SERIES_LEGEND)) {
			if(seriesLegendMenuItem != null) {
				seriesLegendMenuItem.setText(HIDE_SERIES_LEGEND);
				baseChart.getLegend().setVisible(true);
				baseChart.redraw();
			}
		} else if(menuItem.getText().equals(HIDE_SERIES_LEGEND)) {
			if(seriesLegendMenuItem != null) {
				seriesLegendMenuItem.setText(SHOW_SERIES_LEGEND);
				baseChart.getLegend().setVisible(false);
				baseChart.redraw();
			}
		} else if(exportConverterMap.containsKey(menuItem.getText())) {
			ISeriesExportConverter seriesExportConverter = exportConverterMap.get(menuItem.getText());
			seriesExportConverter.export(getShell(), this);
		}
	}

	private void createPopupMenu() {

		Composite plotArea = baseChart.getPlotArea();
		Menu menu = new Menu(plotArea);
		plotArea.setMenu(menu);
		//
		createBasicMenuItems(menu);
		new MenuItem(menu, SWT.SEPARATOR);
		createCustomPaintListenerMenuItems(menu);
		new MenuItem(menu, SWT.SEPARATOR);
		createExportMenuItems(menu);
	}

	private void createBasicMenuItems(Menu menu) {

		MenuItem menuItem;
		//
		menuItem = new MenuItem(menu, SWT.PUSH);
		menuItem.setText(RESET_CHART);
		menuItem.addListener(SWT.Selection, this);
		//
		if(this instanceof LineChart) {
			menuItem = new MenuItem(menu, SWT.PUSH);
			menuItem.setText(RESET_SELECTED_SERIES);
			menuItem.addListener(SWT.Selection, this);
		}
		//
		if(chartSettings.isEnableRangeSelector()) {
			menuItem = new MenuItem(menu, SWT.PUSH);
			menuItem.setText(SHOW_RANGE_SELECTOR);
			menuItem.addListener(SWT.Selection, this);
		}
	}

	private void createCustomPaintListenerMenuItems(Menu menu) {

		MenuItem menuItem;
		//
		menuItem = new MenuItem(menu, SWT.CASCADE);
		menuItem.setText(MENU_MARKER_AND_LEGENDS);
		Menu markerAndLegendsMenu = new Menu(menuItem);
		menuItem.setMenu(markerAndLegendsMenu);
		/*
		 * Optional
		 */
		if(chartSettings.isShowPositionMarker()) {
			positionMarkerMenuItem = new MenuItem(markerAndLegendsMenu, SWT.PUSH);
			positionMarkerMenuItem.setText(HIDE_POSITION_MARKER);
			positionMarkerMenuItem.addListener(SWT.Selection, this);
		}
		//
		if(chartSettings.isShowCenterMarker()) {
			centerMarkerMenuItem = new MenuItem(markerAndLegendsMenu, SWT.PUSH);
			centerMarkerMenuItem.setText(HIDE_CENTER_MARKER);
			centerMarkerMenuItem.addListener(SWT.Selection, this);
		}
		//
		if(chartSettings.isShowPositionLegend()) {
			positionLegendMenuItem = new MenuItem(markerAndLegendsMenu, SWT.PUSH);
			positionLegendMenuItem.setText(HIDE_POSITION_LEGEND);
			positionLegendMenuItem.addListener(SWT.Selection, this);
		}
		/*
		 * Obligatory
		 */
		seriesLegendMenuItem = new MenuItem(markerAndLegendsMenu, SWT.PUSH);
		seriesLegendMenuItem.setText(baseChart.getLegend().isVisible() ? HIDE_SERIES_LEGEND : SHOW_SERIES_LEGEND);
		seriesLegendMenuItem.addListener(SWT.Selection, this);
	}

	private void createExportMenuItems(Menu menu) {

		MenuItem menuItem;
		//
		menuItem = new MenuItem(menu, SWT.CASCADE);
		menuItem.setText(MENU_EXPORT_CHART_SELECTION);
		Menu exportMenu = new Menu(menuItem);
		menuItem.setMenu(exportMenu);
		//
		for(String name : exportConverterMap.keySet()) {
			menuItem = new MenuItem(exportMenu, SWT.PUSH);
			menuItem.setText(name);
			menuItem.addListener(SWT.Selection, this);
		}
	}
}