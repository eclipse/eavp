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

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.EmptyStackException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Stack;

import org.eclipse.eavp.service.swtchart.barcharts.IBarSeriesSettings;
import org.eclipse.eavp.service.swtchart.exceptions.SeriesException;
import org.eclipse.eavp.service.swtchart.linecharts.ILineSeriesSettings;
import org.eclipse.eavp.service.swtchart.scattercharts.IScatterSeriesSettings;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.graphics.Cursor;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.swtchart.IAxis;
import org.swtchart.IAxis.Position;
import org.swtchart.IAxisSet;
import org.swtchart.IBarSeries;
import org.swtchart.ILineSeries;
import org.swtchart.ISeries;
import org.swtchart.LineStyle;
import org.swtchart.Range;

public class BaseChart extends AbstractExtendedChart implements IChartDataCoordinates, IRangeSupport, IExtendedChart {

	public static final int ID_PRIMARY_X_AXIS = 0;
	public static final int ID_PRIMARY_Y_AXIS = 0;
	public static final String DEFAULT_TITLE_X_AXIS = "X-Axis";
	public static final String DEFAULT_TITLE_Y_AXIS = "Y-Axis";
	//
	private static final int MOUSE_BUTTON_NULL = 0;
	private static final int MOUSE_BUTTON_LEFT = 1;
	// private static final int MOUSE_BUTTON_MIDDLE = 2;
	// private static final int MOUSE_BUTTON_RIGHT = 3; // Used by the menu
	//
	// private static final int KEY_CODE_S = 115;
	private static final int KEY_CODE_Z = 122;
	//
	private Map<Integer, Map<Integer, IEventProcessor>> mouseDoubleClickEvents;
	private Map<Integer, IEventProcessor> mouseWheelEvents;
	private Map<Integer, Map<Integer, IEventProcessor>> mouseDownEvents;
	private Map<Integer, Map<Integer, IEventProcessor>> mouseMoveEvents;
	private Map<Integer, Map<Integer, IEventProcessor>> mouseUpEvents;
	private Map<Integer, Map<Integer, IEventProcessor>> keyUpEvents;
	/*
	 * Prevent accidental zooming.
	 * At least 30% of the chart width or height needs to be selected.
	 */
	private static final int MIN_SELECTION_PERCENTAGE = 30;
	private static final long DELTA_CLICK_TIME = 100;
	/*
	 * To prevent that the data is redrawn on mouse events too
	 * often, a trigger determines e.g. that the redraw event
	 * is called only at every xth event.
	 */
	private int redrawFrequency = 1;
	private int redrawCounter = 0;
	//
	private UserSelection userSelection;
	private List<ICustomSelectionHandler> customRangeSelectionHandlers;
	private List<ICustomSelectionHandler> customPointSelectionHandlers;
	private long clickStartTime;
	private Set<String> selectedSeriesIds;
	//
	private Cursor defaultCursor;
	/*
	 * Do/Undo
	 */
	private Stack<double[]> handledSelectionEvents;
	private double[] redoSelection;
	/*
	 * Shift series
	 */
	public static final int SHIFT_CONSTRAINT_NONE = 0;
	public static final int SHIFT_CONSTRAINT_SELECTION = 1 << 0;
	public static final int SHIFT_CONSTRAINT_DELETE_X = 1 << 1;
	public static final int SHIFT_CONSTRAINT_DELETE_Y = 1 << 2;
	public static final int SHIFT_CONSTRAINT_CLINCH_X = 1 << 3;
	public static final int SHIFT_CONSTRAINT_STRETCH_X = 1 << 4;
	public static final int SHIFT_CONSTRAINT_BROADEN_X = 1 << 5;
	public static final int SHIFT_CONSTRAINT_NARROW_X = 1 << 6;
	//
	private boolean supportDataShift;
	private static final long DELTA_MOVE_TIME = 350;
	private long moveStartTime = 0;
	private int xMoveStart = 0;
	private int yMoveStart = 0;
	private Map<String, List<double[]>> dataShiftHistory;

	private class SelectHideSeriesEventProcessor implements IEventProcessor {

		private int hideMask;

		public SelectHideSeriesEventProcessor(int hideMask) {
			this.hideMask = hideMask;
		}

		@Override
		public void handleEvent(Event event) {

			if((event.stateMask & hideMask) == hideMask) {
				/*
				 * Hide
				 */
				String selectedSeriesId = getSelectedSeriedId(event);
				if(selectedSeriesId.equals("")) {
					resetSeriesSettings();
				} else {
					hideSeries(selectedSeriesId);
					redraw();
				}
			} else {
				/*
				 * Select
				 */
				String selectedSeriesId = getSelectedSeriedId(event);
				if(selectedSeriesId.equals("")) {
					resetSeriesSettings();
				} else {
					selectSeries(selectedSeriesId);
					redraw();
				}
			}
		}
	}

	private class SelectDataPointEventProcessor implements IEventProcessor {

		@Override
		public void handleEvent(Event event) {

			// double x = getSelectedPrimaryAxisValue(event.x, IExtendedChart.X_AXIS);
			// double y = getSelectedPrimaryAxisValue(event.y, IExtendedChart.Y_AXIS);
			fireUpdateCustomPointSelectionHandlers(event);
		}
	}

	private class ResetSeriesEventProcessor implements IEventProcessor {

		@Override
		public void handleEvent(Event event) {

			adjustRange(true);
			fireUpdateCustomRangeSelectionHandlers(event);
			redraw();
		}
	}

	private class ZoomEventProcessor implements IEventProcessor {

		@Override
		public void handleEvent(Event event) {

			IAxis xAxis = getAxisSet().getXAxis(ID_PRIMARY_X_AXIS);
			IAxis yAxis = getAxisSet().getYAxis(ID_PRIMARY_Y_AXIS);
			//
			RangeRestriction rangeRestriction = getRangeRestriction();
			if(isZoomXAndY(rangeRestriction)) {
				/*
				 * X and Y zoom.
				 */
				zoomX(xAxis, event);
				zoomY(yAxis, event);
			} else {
				/*
				 * X or Y zoom.
				 */
				if(rangeRestriction.isXZoomOnly()) {
					zoomX(xAxis, event);
				} else if(rangeRestriction.isYZoomOnly()) {
					zoomY(yAxis, event);
				}
			}
			/*
			 * Adjust the range if it shall not exceed the initial
			 * min and max values.
			 */
			if(rangeRestriction.isRestrictZoom()) {
				/*
				 * Adjust the primary axes.
				 * The secondary axes are adjusted by setting the range.
				 */
				Range rangeX = xAxis.getRange();
				Range rangeY = yAxis.getRange();
				setRange(xAxis, rangeX.lower, rangeX.upper, true);
				setRange(yAxis, rangeY.lower, rangeY.upper, true);
			} else {
				/*
				 * Update the secondary axes.
				 */
				adjustSecondaryXAxes();
				adjustSecondaryYAxes();
			}
			//
			fireUpdateCustomRangeSelectionHandlers(event);
			redraw();
		}
	}

	private class MouseDownEventProcessor implements IEventProcessor {

		@Override
		public void handleEvent(Event event) {

			userSelection.setStartCoordinate(event.x, event.y);
			clickStartTime = System.currentTimeMillis();
		}
	}

	private class MouseMoveSelectionEventProcessor implements IEventProcessor {

		@Override
		public void handleEvent(Event event) {

			userSelection.setStopCoordinate(event.x, event.y);
			redrawCounter++;
			if(redrawCounter >= redrawFrequency) {
				/*
				 * Rectangle is drawn here:
				 * void paintControl(PaintEvent e)
				 */
				redraw();
				redrawCounter = 0;
			}
		}
	}

	private class MouseMoveCursorEventProcessor implements IEventProcessor {

		@Override
		public void handleEvent(Event event) {

			String selectedSeriesId = getSelectedSeriedId(event);
			if(selectedSeriesId.equals("")) {
				setCursor(defaultCursor);
			} else {
				setCursor(Display.getDefault().getSystemCursor(SWT.CURSOR_HAND));
			}
		}
	}

	private class MouseMoveShiftEventProcessor implements IEventProcessor {

		@Override
		public void handleEvent(Event event) {

			if(supportDataShift && selectedSeriesIds.size() > 0) {
				/*
				 * Only shift if series have been selected.
				 */
				if(moveStartTime == 0) {
					/*
					 * Start
					 */
					setCursor(Display.getDefault().getSystemCursor(SWT.CURSOR_SIZENWSE));
					moveStartTime = System.currentTimeMillis();
					xMoveStart = event.x;
					yMoveStart = event.y;
				} else {
					long deltaTime = System.currentTimeMillis() - moveStartTime;
					if(deltaTime <= DELTA_MOVE_TIME) {
						/*
						 * Shift
						 */
						moveStartTime = System.currentTimeMillis();
						//
						double shiftX = getShiftValue(xMoveStart, event.x, IExtendedChart.X_AXIS);
						double shiftY = getShiftValue(yMoveStart, event.y, IExtendedChart.Y_AXIS);
						//
						for(String selectedSeriesId : selectedSeriesIds) {
							ISeries dataSeries = getSeriesSet().getSeries(selectedSeriesId);
							if(dataSeries != null) {
								shiftSeries(selectedSeriesId, shiftX, shiftY);
							}
						}
						redraw();
						//
						xMoveStart = event.x;
						yMoveStart = event.y;
					} else {
						/*
						 * Default
						 */
						moveStartTime = 0;
						xMoveStart = 0;
						yMoveStart = 0;
					}
				}
			}
		}
	}

	private double getShiftValue(int positionStart, int positionStop, String orientation) {

		double shiftValue = 0.0d;
		double start;
		double stop;
		int length;
		/*
		 * Get the axis.
		 */
		if(orientation.equals(IExtendedChart.X_AXIS)) {
			IAxis axis = getAxisSet().getXAxis(BaseChart.ID_PRIMARY_X_AXIS);
			start = axis.getRange().lower;
			stop = axis.getRange().upper;
			length = getPlotArea().getBounds().width;
		} else {
			IAxis axis = getAxisSet().getYAxis(BaseChart.ID_PRIMARY_Y_AXIS);
			start = axis.getRange().lower;
			stop = axis.getRange().upper;
			length = getPlotArea().getBounds().height;
		}
		//
		if(positionStart > 0 && positionStop > 0 && positionStart < length && positionStop < length) {
			//
			double delta = stop - start;
			double percentageStart;
			double percentageStop;
			//
			if(orientation.equals(IExtendedChart.X_AXIS)) {
				percentageStart = ((100.0d / length) * positionStart) / 100.0d;
				percentageStop = ((100.0d / length) * positionStop) / 100.0d;
			} else {
				percentageStart = (100.0d - ((100.0d / length) * positionStart)) / 100.0d;
				percentageStop = (100.0d - ((100.0d / length) * positionStop)) / 100.0d;
			}
			//
			shiftValue = (start + delta * percentageStop) - (start + delta * percentageStart);
		}
		return shiftValue;
	}

	private class MouseUpEventProcessor implements IEventProcessor {

		@Override
		public void handleEvent(Event event) {

			long deltaTime = System.currentTimeMillis() - clickStartTime;
			if(deltaTime >= DELTA_CLICK_TIME) {
				handleUserSelection(event);
			}
		}
	}

	private class UndoRedoEventProcessor implements IEventProcessor {

		private int redoMask;

		public UndoRedoEventProcessor(int redoMask) {
			this.redoMask = redoMask;
		}

		@Override
		public void handleEvent(Event event) {

			if((event.stateMask & redoMask) == redoMask) {
				/*
				 * Redo
				 */
				redoSelection();
			} else {
				/*
				 * Undo
				 */
				undoSelection();
			}
			redraw();
		}
	}

	public BaseChart(Composite parent, int style) {
		super(parent, style);
		defaultCursor = getCursor();
		/*
		 * Rectangle range selection.
		 */
		userSelection = new UserSelection();
		customRangeSelectionHandlers = new ArrayList<ICustomSelectionHandler>();
		customPointSelectionHandlers = new ArrayList<ICustomSelectionHandler>();
		selectedSeriesIds = new HashSet<String>();
		initializeEventProcessors();
		/*
		 * Create the default x and y axis.
		 */
		IAxisSet axisSet = getAxisSet();
		//
		IAxis xAxisPrimary = axisSet.getXAxis(ID_PRIMARY_X_AXIS);
		xAxisPrimary.getTitle().setText(DEFAULT_TITLE_X_AXIS);
		xAxisPrimary.setPosition(Position.Primary);
		xAxisPrimary.getTick().setFormat(new DecimalFormat());
		xAxisPrimary.enableLogScale(false);
		xAxisPrimary.enableCategory(false);
		xAxisPrimary.enableCategory(false);
		xAxisPrimary.setCategorySeries(new String[]{});
		//
		IAxis yAxisPrimary = axisSet.getYAxis(ID_PRIMARY_Y_AXIS);
		yAxisPrimary.getTitle().setText(DEFAULT_TITLE_Y_AXIS);
		yAxisPrimary.setPosition(Position.Primary);
		yAxisPrimary.getTick().setFormat(new DecimalFormat());
		yAxisPrimary.enableLogScale(false);
		yAxisPrimary.enableCategory(false);
		//
		handledSelectionEvents = new Stack<double[]>();
		redoSelection = null;
		//
		supportDataShift = false;
		dataShiftHistory = new HashMap<String, List<double[]>>();
	}

	@Override
	public ISeries createSeries(ISeriesData seriesData, ISeriesSettings seriesSettings) throws SeriesException {

		ISeries series = super.createSeries(seriesData, seriesSettings);
		calculateRedrawFrequency();
		return series;
	}

	@Override
	public void deleteSeries(String id) {

		super.deleteSeries(id);
		calculateRedrawFrequency();
		dataShiftHistory.remove(id);
	}

	@Override
	public void appendSeries(ISeriesData seriesData) {

		super.appendSeries(seriesData);
		calculateRedrawFrequency();
	}

	private void calculateRedrawFrequency() {

		/*
		 * The frequency might be calculated here to increase the performance.
		 * I've not found a smart solution yet to improve the speed when
		 * displaying large data sets.
		 */
		redrawFrequency = 2;
	}

	public double getSelectedPrimaryAxisValue(int position, String orientation) {

		double primaryValue = 0.0d;
		double start;
		double stop;
		int length;
		//
		if(orientation.equals(IExtendedChart.X_AXIS)) {
			IAxis axis = getAxisSet().getXAxis(BaseChart.ID_PRIMARY_X_AXIS);
			start = axis.getRange().lower;
			stop = axis.getRange().upper;
			length = getPlotArea().getBounds().width;
		} else {
			IAxis axis = getAxisSet().getYAxis(BaseChart.ID_PRIMARY_Y_AXIS);
			start = axis.getRange().lower;
			stop = axis.getRange().upper;
			length = getPlotArea().getBounds().height;
		}
		//
		if(position <= 0) {
			primaryValue = start;
		} else if(position > length) {
			primaryValue = stop;
		} else {
			double delta = stop - start;
			double percentage;
			if(orientation.equals(IExtendedChart.X_AXIS)) {
				percentage = ((100.0d / length) * position) / 100.0d;
			} else {
				percentage = (100.0d - ((100.0d / length) * position)) / 100.0d;
			}
			primaryValue = start + delta * percentage;
		}
		return primaryValue;
	}

	public void setSupportDataShift(boolean supportDataShift) {

		this.supportDataShift = supportDataShift;
	}

	private void initializeEventProcessors() {

		mouseDoubleClickEvents = new HashMap<Integer, Map<Integer, IEventProcessor>>();
		mouseDoubleClickEvents.put(MOUSE_BUTTON_LEFT, new HashMap<Integer, IEventProcessor>());
		mouseDoubleClickEvents.get(MOUSE_BUTTON_LEFT).put(SWT.CTRL, new SelectHideSeriesEventProcessor(SWT.SHIFT));
		mouseDoubleClickEvents.get(MOUSE_BUTTON_LEFT).put(SWT.SHIFT, new ResetSeriesEventProcessor());
		mouseDoubleClickEvents.get(MOUSE_BUTTON_LEFT).put(SWT.NONE, new SelectDataPointEventProcessor());
		//
		mouseWheelEvents = new HashMap<Integer, IEventProcessor>();
		mouseWheelEvents.put(SWT.NONE, new ZoomEventProcessor());
		//
		mouseDownEvents = new HashMap<Integer, Map<Integer, IEventProcessor>>();
		mouseDownEvents.put(MOUSE_BUTTON_LEFT, new HashMap<Integer, IEventProcessor>());
		mouseDownEvents.get(MOUSE_BUTTON_LEFT).put(SWT.NONE, new MouseDownEventProcessor()); // Start Selection
		//
		mouseMoveEvents = new HashMap<Integer, Map<Integer, IEventProcessor>>();
		mouseMoveEvents.put(MOUSE_BUTTON_NULL, new HashMap<Integer, IEventProcessor>());
		mouseMoveEvents.get(MOUSE_BUTTON_NULL).put(SWT.BUTTON1, new MouseMoveSelectionEventProcessor()); // Set Selection Range
		mouseMoveEvents.get(MOUSE_BUTTON_NULL).put(SWT.CTRL, new MouseMoveShiftEventProcessor()); // Shift the selected series
		mouseMoveEvents.get(MOUSE_BUTTON_NULL).put(SWT.NONE, new MouseMoveCursorEventProcessor());
		//
		mouseUpEvents = new HashMap<Integer, Map<Integer, IEventProcessor>>();
		mouseUpEvents.put(MOUSE_BUTTON_LEFT, new HashMap<Integer, IEventProcessor>());
		mouseUpEvents.get(MOUSE_BUTTON_LEFT).put(SWT.BUTTON1, new MouseUpEventProcessor()); // Stop Selection
		//
		keyUpEvents = new HashMap<Integer, Map<Integer, IEventProcessor>>();
		keyUpEvents.put(KEY_CODE_Z, new HashMap<Integer, IEventProcessor>());
		keyUpEvents.get(KEY_CODE_Z).put(SWT.CTRL, new UndoRedoEventProcessor(SWT.SHIFT));
	}

	public boolean addCustomRangeSelectionHandler(ICustomSelectionHandler customSelectionHandler) {

		return customRangeSelectionHandlers.add(customSelectionHandler);
	}

	public boolean removeCustomRangeSelectionHandler(ICustomSelectionHandler customSelectionHandler) {

		return customRangeSelectionHandlers.remove(customSelectionHandler);
	}

	public boolean addCustomPointSelectionHandler(ICustomSelectionHandler customSelectionHandler) {

		return customPointSelectionHandlers.add(customSelectionHandler);
	}

	public boolean removeCustomPointSelectionHandler(ICustomSelectionHandler customSelectionHandler) {

		return customPointSelectionHandlers.remove(customSelectionHandler);
	}

	/**
	 * Returns the set of selected series ids.
	 * The list is unmodifiable.
	 * 
	 * @return Set<String>
	 */
	public Set<String> getSelectedSeriesIds() {

		return Collections.unmodifiableSet(selectedSeriesIds);
	}

	@Override
	public void paintControl(PaintEvent e) {

		if(userSelection.isActive()) {
			/*
			 * Draw the rectangle of the user selection.
			 */
			int currentLineStyle = e.gc.getLineStyle();
			e.gc.setLineStyle(SWT.LINE_DOT);
			//
			int xMin = Math.min(userSelection.getStartX(), userSelection.getStopX());
			int xMax = Math.max(userSelection.getStartX(), userSelection.getStopX());
			int yMin = Math.min(userSelection.getStartY(), userSelection.getStopY());
			int yMax = Math.max(userSelection.getStartY(), userSelection.getStopY());
			//
			RangeRestriction rangeRestriction = getRangeRestriction();
			if(isZoomXAndY(rangeRestriction)) {
				/*
				 * X and Y zoom.
				 */
				e.gc.drawRectangle(xMin, yMin, xMax - xMin, yMax - yMin);
			} else {
				/*
				 * X or Y zoom.
				 */
				if(rangeRestriction.isXZoomOnly()) {
					e.gc.drawLine(xMin, yMin, xMax, yMin);
				} else if(rangeRestriction.isYZoomOnly()) {
					e.gc.drawLine(xMin, yMin, xMin, yMax);
				}
			}
			//
			e.gc.setLineStyle(currentLineStyle);
		}
	}

	@Override
	public void handleMouseDownEvent(Event event) {

		handleEvent(mouseDownEvents.get(event.button), event);
	}

	@Override
	public void handleMouseMoveEvent(Event event) {

		handleEvent(mouseMoveEvents.get(event.button), event);
	}

	@Override
	public void handleMouseUpEvent(Event event) {

		handleEvent(mouseUpEvents.get(event.button), event);
	}

	@Override
	public void handleMouseWheel(Event event) {

		handleEvent(mouseWheelEvents, event);
	}

	@Override
	public void handleMouseDoubleClick(Event event) {

		handleEvent(mouseDoubleClickEvents.get(event.button), event);
	}

	@Override
	public void handleKeyUpEvent(Event event) {

		handleEvent(keyUpEvents.get(event.keyCode), event);
	}

	private void handleEvent(Map<Integer, IEventProcessor> eventProcessors, Event event) {

		IEventProcessor eventProcessor = null;
		//
		if(eventProcessors != null) {
			if(event.stateMask == SWT.NONE) {
				/*
				 * Default processor.
				 * The stateMask == 0 is handled differently.
				 */
				eventProcessor = eventProcessors.get(SWT.NONE);
			} else {
				/*
				 * Handle all other stateMasks.
				 */
				exitloop:
				for(int eventMask : eventProcessors.keySet()) {
					/*
					 * Skip the default processor.
					 */
					if(eventMask == SWT.NONE) {
						continue;
					}
					//
					if((event.stateMask & eventMask) == eventMask) {
						eventProcessor = eventProcessors.get(eventMask);
						break exitloop;
					}
				}
			}
		}
		/*
		 * Handle the event.
		 */
		if(eventProcessor != null) {
			eventProcessor.handleEvent(event);
		}
	}

	public void resetSeriesSettings() {

		ISeries[] series = getSeriesSet().getSeries();
		//
		for(ISeries dataSeries : series) {
			ISeriesSettings seriesSettings = getSeriesSettings(dataSeries.getId());
			applySeriesSettings(dataSeries, seriesSettings);
		}
		//
		selectedSeriesIds.clear();
		redraw();
	}

	public void selectSeries(String selectedSeriesId) {

		ISeries dataSeries = getSeriesSet().getSeries(selectedSeriesId);
		ISeriesSettings seriesSettings = getSeriesSettings(selectedSeriesId);
		selectedSeriesIds.add(selectedSeriesId);
		applySeriesSettings(dataSeries, seriesSettings.getSeriesSettingsHighlight());
	}

	private void applySeriesSettings(ISeries dataSeries, ISeriesSettings seriesSettings) {

		if(dataSeries instanceof ILineSeries) {
			ILineSeries lineSeries = (ILineSeries)dataSeries;
			if(seriesSettings instanceof ILineSeriesSettings) {
				/*
				 * Line Series
				 */
				ILineSeriesSettings lineSeriesSettings = (ILineSeriesSettings)seriesSettings;
				applyLineSeriesSettings(lineSeries, lineSeriesSettings);
			} else if(seriesSettings instanceof IScatterSeriesSettings) {
				/*
				 * Scatter Series
				 */
				IScatterSeriesSettings scatterSeriesSettings = (IScatterSeriesSettings)seriesSettings;
				applyScatterSeriesSettings(lineSeries, scatterSeriesSettings);
			}
		} else if(dataSeries instanceof IBarSeries) {
			/*
			 * Bar Series
			 */
			IBarSeries barSeries = (IBarSeries)dataSeries;
			IBarSeriesSettings barSeriesSettings = (IBarSeriesSettings)seriesSettings;
			applyBarSeriesSettings(barSeries, barSeriesSettings);
		}
	}

	public void applyLineSeriesSettings(ILineSeries lineSeries, ILineSeriesSettings lineSeriesSettings) {

		lineSeries.setDescription(lineSeriesSettings.getDescription());
		lineSeries.setVisible(lineSeriesSettings.isVisible());
		lineSeries.setVisibleInLegend(lineSeriesSettings.isVisibleInLegend());
		lineSeries.setAntialias(lineSeriesSettings.getAntialias());
		lineSeries.enableArea(lineSeriesSettings.isEnableArea());
		lineSeries.setSymbolType(lineSeriesSettings.getSymbolType());
		lineSeries.setSymbolSize(lineSeriesSettings.getSymbolSize());
		lineSeries.setSymbolColor(lineSeriesSettings.getSymbolColor());
		lineSeries.setLineColor(lineSeriesSettings.getLineColor());
		lineSeries.setLineWidth(lineSeriesSettings.getLineWidth());
		lineSeries.enableStack(lineSeriesSettings.isEnableStack());
		lineSeries.enableStep(lineSeriesSettings.isEnableStep());
		lineSeries.setLineStyle(lineSeriesSettings.getLineStyle());
	}

	public void applyScatterSeriesSettings(ILineSeries scatterSeries, IScatterSeriesSettings scatterSeriesSettings) {

		scatterSeries.setDescription(scatterSeriesSettings.getDescription());
		scatterSeries.setVisible(scatterSeriesSettings.isVisible());
		scatterSeries.setVisibleInLegend(scatterSeriesSettings.isVisibleInLegend());
		scatterSeries.enableArea(false);
		scatterSeries.setSymbolType(scatterSeriesSettings.getSymbolType());
		scatterSeries.setSymbolSize(scatterSeriesSettings.getSymbolSize());
		scatterSeries.setSymbolColor(scatterSeriesSettings.getSymbolColor());
		scatterSeries.setLineStyle(LineStyle.NONE);
	}

	public void applyBarSeriesSettings(IBarSeries barSeries, IBarSeriesSettings barSeriesSettings) {

		barSeries.setDescription(barSeriesSettings.getDescription());
		barSeries.setVisible(barSeriesSettings.isVisible());
		barSeries.setVisibleInLegend(barSeriesSettings.isVisibleInLegend());
		barSeries.setBarColor(barSeriesSettings.getBarColor());
		barSeries.setBarPadding(barSeriesSettings.getBarPadding());
		barSeries.setBarWidth(barSeriesSettings.getBarWidth());
	}

	public List<double[]> getDataShiftHistory(String selectedSeriesId) {

		List<double[]> dataShifts = dataShiftHistory.get(selectedSeriesId);
		if(dataShifts != null) {
			return Collections.unmodifiableList(dataShifts);
		} else {
			return null;
		}
	}

	public void shiftSeries(String selectedSeriesId, double shiftX, double shiftY) {

		shiftSeries(selectedSeriesId, shiftX, shiftY, SHIFT_CONSTRAINT_NONE);
	}

	public void shiftSeries(String selectedSeriesId, double shiftX, double shiftY, int shiftConstraints) {

		if(supportDataShift) {
			ISeries dataSeries = getSeriesSet().getSeries(selectedSeriesId);
			if(dataSeries != null) {
				//
				if(shiftX != 0.0d || shiftY != 0.0d) {
					//
					double seriesMinX = Double.MAX_VALUE;
					double seriesMaxX = Double.MIN_VALUE;
					double seriesMinY = Double.MAX_VALUE;
					double seriesMaxY = Double.MIN_VALUE;
					//
					if(shiftX != 0.0d) {
						double[] xSeriesShifted = adjustArray(dataSeries.getXSeries(), shiftX);
						dataSeries.setXSeries(xSeriesShifted);
						seriesMinX = xSeriesShifted[0];
						seriesMaxX = xSeriesShifted[xSeriesShifted.length - 1];
					}
					//
					if(shiftY != 0.0d) {
						double[] ySeriesShifted = adjustArray(dataSeries.getYSeries(), shiftY);
						dataSeries.setYSeries(ySeriesShifted);
						seriesMinY = ySeriesShifted[0];
						seriesMaxY = ySeriesShifted[ySeriesShifted.length - 1];
					}
					/*
					 * Track the shifts.
					 */
					Range rangeX = getAxisSet().getXAxis(ID_PRIMARY_X_AXIS).getRange();
					Range rangeY = getAxisSet().getYAxis(ID_PRIMARY_Y_AXIS).getRange();
					List<double[]> shiftRecord = getShiftRecord(selectedSeriesId);
					shiftRecord.add(new double[]{rangeX.lower, rangeX.upper, shiftX, rangeY.lower, rangeY.upper, shiftY, shiftConstraints});
					//
					updateCoordinates(seriesMinX, seriesMaxX, seriesMinY, seriesMaxY);
				}
			}
		}
	}

	private List<double[]> getShiftRecord(String selectedSeriesId) {

		List<double[]> shiftRecord = dataShiftHistory.get(selectedSeriesId);
		if(shiftRecord == null) {
			shiftRecord = new ArrayList<double[]>();
			dataShiftHistory.put(selectedSeriesId, shiftRecord);
		}
		return shiftRecord;
	}

	private double[] adjustArray(double[] series, double shift) {

		for(int i = 0; i < series.length; i++) {
			series[i] += shift;
		}
		return series;
	}

	public String[] getAxisLabels(String axisOrientation) {

		IAxis[] axes = getAxes(axisOrientation);
		int size = axes.length;
		String[] items = new String[size];
		//
		for(int i = 0; i < size; i++) {
			/*
			 * Get the label.
			 */
			String label;
			IAxisSettings axisSettings = getAxisSettings(axisOrientation, i);
			if(axisSettings != null) {
				label = axisSettings.getLabel();
			} else {
				label = "not set";
			}
			items[i] = label;
		}
		return items;
	}

	public DecimalFormat getDecimalFormat(String axisOrientation, int id) {

		DecimalFormat decimalFormat;
		IAxisSettings axisSettings = getAxisSettings(axisOrientation, id);
		//
		if(axisSettings != null) {
			decimalFormat = axisSettings.getDecimalFormat();
		} else {
			decimalFormat = new DecimalFormat();
		}
		return decimalFormat;
	}

	/**
	 * May return null.
	 * 
	 * axis =
	 * IExtendedChart.X_AXIS
	 * or
	 * IExtendedChart.Y_AXIS
	 * 
	 * @param axisOrientation
	 * @param id
	 * @return IAxisScaleConverter
	 */
	public IAxisScaleConverter getAxisScaleConverter(String axisOrientation, int id) {

		IAxisScaleConverter axisScaleConverter = null;
		IAxisSettings axisSettings = null;
		//
		if(axisOrientation.equals(IExtendedChart.X_AXIS)) {
			axisSettings = getXAxisSettings(id);
		} else {
			axisSettings = getYAxisSettings(id);
		}
		//
		if(axisSettings instanceof ISecondaryAxisSettings) {
			axisScaleConverter = ((ISecondaryAxisSettings)axisSettings).getAxisScaleConverter();
		}
		//
		return axisScaleConverter;
	}

	protected void fireUpdateCustomRangeSelectionHandlers(Event event) {

		/*
		 * Handle the custom user selection handlers.
		 */
		for(ICustomSelectionHandler customSelectionHandler : customRangeSelectionHandlers) {
			try {
				customSelectionHandler.handleUserSelection(event);
			} catch(Exception e) {
				System.out.println(e);
			}
		}
	}

	protected void fireUpdateCustomPointSelectionHandlers(Event event) {

		/*
		 * Handle the custom user selection handlers.
		 */
		for(ICustomSelectionHandler customSelectionHandler : customPointSelectionHandlers) {
			try {
				customSelectionHandler.handleUserSelection(event);
			} catch(Exception e) {
				System.out.println(e);
			}
		}
	}

	private void zoomX(IAxis xAxis, Event event) {

		/*
		 * X Axis
		 */
		trackUndoSelection();
		double coordinateX = xAxis.getDataCoordinate(event.x);
		if(event.count > 0) {
			xAxis.zoomIn(coordinateX);
		} else {
			xAxis.zoomOut(coordinateX);
		}
		trackRedoSelection();
	}

	private void zoomY(IAxis yAxis, Event event) {

		/*
		 * Y Axis
		 */
		trackUndoSelection();
		double coordinateY = yAxis.getDataCoordinate(event.y);
		if(event.count > 0) {
			yAxis.zoomIn(coordinateY);
		} else {
			yAxis.zoomOut(coordinateY);
		}
		trackRedoSelection();
	}

	private void hideSeries(String selectedSeriesId) {

		ISeries dataSeries = getSeriesSet().getSeries(selectedSeriesId);
		selectedSeriesIds.remove(selectedSeriesId);
		dataSeries.setVisible(false);
		dataSeries.setVisibleInLegend(false);
	}

	private String getSelectedSeriedId(Event event) {

		ISeries[] series = getSeriesSet().getSeries();
		String selectedSeriesId = "";
		/*
		 * Get the selected series id.
		 */
		exitloop:
		for(ISeries dataSeries : series) {
			if(dataSeries != null) {
				int size = dataSeries.getXSeries().length;
				for(int i = 0; i < size; i++) {
					Point point = dataSeries.getPixelCoordinates(i);
					if(isDataSeriesSelected(point, event, 8)) {
						selectedSeriesId = dataSeries.getId();
						break exitloop;
					}
				}
			}
		}
		//
		return selectedSeriesId;
	}

	private boolean isDataSeriesSelected(Point point, Event event, int delta) {

		if(point.x >= event.x - delta && point.x <= event.x + delta) {
			if(point.y >= event.y - delta && point.y <= event.y + delta) {
				return true;
			}
		}
		return false;
	}

	private void handleUserSelection(Event event) {

		int minSelectedWidth;
		int minSelectedHeight;
		int deltaWidth;
		int deltaHeight;
		//
		Rectangle bounds = getPlotArea().getBounds();
		if((getOrientation() == SWT.HORIZONTAL)) {
			minSelectedWidth = bounds.width / MIN_SELECTION_PERCENTAGE;
			deltaWidth = Math.abs(userSelection.getStartX() - event.x);
			minSelectedHeight = bounds.height / MIN_SELECTION_PERCENTAGE;
			deltaHeight = Math.abs(userSelection.getStartY() - event.y);
		} else {
			minSelectedWidth = bounds.height / MIN_SELECTION_PERCENTAGE;
			deltaWidth = Math.abs(userSelection.getStartY() - event.y);
			minSelectedHeight = bounds.width / MIN_SELECTION_PERCENTAGE;
			deltaHeight = Math.abs(userSelection.getStartX() - event.x);
		}
		/*
		 * Prevent accidental zooming.
		 */
		RangeRestriction rangeRestriction = getRangeRestriction();
		if(rangeRestriction.isYZoomOnly()) {
			if(deltaHeight >= minSelectedHeight) {
				handleUserSelectionXY(event);
			}
		} else {
			if(deltaWidth >= minSelectedWidth) {
				handleUserSelectionXY(event);
			}
		}
		//
		userSelection.reset();
		redraw();
	}

	private void handleUserSelectionXY(Event event) {

		/*
		 * Track the selection before the new range is
		 * selected by the user.
		 */
		trackUndoSelection();
		int xStart = userSelection.getStartX();
		int xStop = userSelection.getStopX();
		int yStart = userSelection.getStartY();
		int yStop = userSelection.getStopY();
		setSelectionXY(xStart, xStop, yStart, yStop);
		trackRedoSelection();
		/*
		 * Inform all registered handlers.
		 * Reset the current selection and redraw the chart.
		 */
		fireUpdateCustomRangeSelectionHandlers(event);
	}

	private void trackUndoSelection() {

		Range xRange = getAxisSet().getXAxis(ID_PRIMARY_X_AXIS).getRange();
		Range yRange = getAxisSet().getYAxis(ID_PRIMARY_Y_AXIS).getRange();
		handledSelectionEvents.push(new double[]{xRange.lower, xRange.upper, yRange.lower, yRange.upper});
	}

	private void trackRedoSelection() {

		Range xRange = getAxisSet().getXAxis(ID_PRIMARY_X_AXIS).getRange();
		Range yRange = getAxisSet().getYAxis(ID_PRIMARY_Y_AXIS).getRange();
		redoSelection = new double[]{xRange.lower, xRange.upper, yRange.lower, yRange.upper};
	}

	public void undoSelection() {

		try {
			double[] undoSelection = handledSelectionEvents.pop();
			handleSelection(undoSelection);
		} catch(EmptyStackException e) {
			System.out.println(e);
		}
	}

	public void redoSelection() {

		if(redoSelection != null) {
			handleSelection(redoSelection);
			redoSelection = null;
		}
	}

	private void handleSelection(double[] selection) {

		double xStart = selection[0];
		double xStop = selection[1];
		double yStart = selection[2];
		double yStop = selection[3];
		IAxis xAxis = getAxisSet().getXAxis(ID_PRIMARY_X_AXIS);
		IAxis yAxis = getAxisSet().getYAxis(ID_PRIMARY_Y_AXIS);
		setRange(xAxis, xStart, xStop, false);
		setRange(yAxis, yStart, yStop, false);
	}

	private void setSelectionXY(int xStart, int xStop, int yStart, int yStop) {

		IAxis xAxis = getAxisSet().getXAxis(ID_PRIMARY_X_AXIS);
		IAxis yAxis = getAxisSet().getYAxis(ID_PRIMARY_Y_AXIS);
		//
		if((getOrientation() == SWT.HORIZONTAL)) {
			setHorizontalRange(xAxis, yAxis, xStart, xStop, yStart, yStop);
		} else {
			setVerticalRange(xAxis, yAxis, xStart, xStop, yStart, yStop);
		}
	}

	private void setHorizontalRange(IAxis xAxis, IAxis yAxis, int xStart, int xStop, int yStart, int yStop) {

		RangeRestriction rangeRestriction = getRangeRestriction();
		if(isZoomXAndY(rangeRestriction)) {
			/*
			 * X and Y zoom.
			 */
			setRange(xAxis, xStart, xStop, true);
			setRange(yAxis, yStart, yStop, true);
		} else {
			/*
			 * X or Y zoom.
			 */
			if(rangeRestriction.isXZoomOnly()) {
				setRange(xAxis, xStart, xStop, true);
			} else if(rangeRestriction.isYZoomOnly()) {
				setRange(yAxis, yStart, yStop, true);
			}
		}
	}

	private void setVerticalRange(IAxis xAxis, IAxis yAxis, int xStart, int xStop, int yStart, int yStop) {

		RangeRestriction rangeRestriction = getRangeRestriction();
		if(isZoomXAndY(rangeRestriction)) {
			/*
			 * X and Y zoom.
			 */
			setRange(xAxis, yStart, yStop, true);
			setRange(yAxis, xStart, xStop, true);
		} else {
			/*
			 * X or Y zoom.
			 */
			if(rangeRestriction.isXZoomOnly()) {
				setRange(xAxis, yStart, yStop, true);
			} else if(rangeRestriction.isYZoomOnly()) {
				setRange(yAxis, xStart, xStop, true);
			}
		}
	}

	private IAxis[] getAxes(String axisOrientation) {

		IAxisSet axisSet = getAxisSet();
		//
		if(axisOrientation.equals(IExtendedChart.X_AXIS)) {
			return axisSet.getXAxes();
		} else {
			return axisSet.getYAxes();
		}
	}

	private IAxisSettings getAxisSettings(String axisOrientation, int id) {

		IAxisSettings axisSettings = null;
		if(axisOrientation.equals(IExtendedChart.X_AXIS)) {
			axisSettings = getXAxisSettings(id);
		} else {
			axisSettings = getYAxisSettings(id);
		}
		return axisSettings;
	}

	private boolean isZoomXAndY(RangeRestriction rangeRestriction) {

		boolean zoomXAndY = false;
		if(!rangeRestriction.isXZoomOnly() && !rangeRestriction.isYZoomOnly()) {
			zoomXAndY = true;
		} else if(rangeRestriction.isXZoomOnly() && rangeRestriction.isYZoomOnly()) {
			zoomXAndY = true;
		}
		//
		return zoomXAndY;
	}
}
