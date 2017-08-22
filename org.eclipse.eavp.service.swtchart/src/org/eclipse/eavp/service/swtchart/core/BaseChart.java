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
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.eavp.service.swtchart.linecharts.ILineSeriesSettings;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.swtchart.IAxis;
import org.swtchart.IAxis.Position;
import org.swtchart.IAxisSet;
import org.swtchart.ILineSeries;
import org.swtchart.ISeries;
import org.swtchart.Range;

public class BaseChart extends AbstractExtendedChart implements IChartDataCoordinates, IRangeSupport, IExtendedChart {

	public static final int ID_PRIMARY_X_AXIS = 0;
	public static final int ID_PRIMARY_Y_AXIS = 0;
	public static final String DEFAULT_TITLE_X_AXIS = "X-Axis";
	public static final String DEFAULT_TITLE_Y_AXIS = "Y-Axis";
	//
	public static final int MOUSE_BUTTON_LEFT = 1;
	public static final int MOUSE_BUTTON_MIDDLE = 2;
	public static final int MOUSE_BUTTON_RIGHT = 3;
	//
	private Map<Integer, Map<Integer, IEventProcessor>> mouseDoubleClickEvents;
	/*
	 * Prevent accidental zooming.
	 * At least 30% of the chart width or height needs to be selected.
	 */
	private static final int MIN_SELECTION_PERCENTAGE = 30;
	private static final long DELTA_CLICK_TIME = 100;
	/*
	 * To prevent that the data is redrawn on mouse events too
	 * often, a trigger determines e.g. that the redraw event
	 * is called only at every fifth event.
	 */
	private static final int TRIGGER_REDRAW_EVENT = 5;
	private int redrawCounter = 0;
	//
	private UserSelection userSelection;
	private List<ICustomSelectionHandler> customSelectionHandlers;
	private long clickStartTime;
	private Set<String> selectedSeriesIds;

	private class SelectSeriesEventProcessor implements IEventProcessor {

		@Override
		public void handleEvent(Event event) {

			String selectedSeriesId = getSelectedSeriedId(event);
			if(selectedSeriesId.equals("")) {
				resetSelectedSeries();
			} else {
				selectSeries(selectedSeriesId);
			}
		}
	}

	private class HideSeriesEventProcessor implements IEventProcessor {

		@Override
		public void handleEvent(Event event) {

			String selectedSeriesId = getSelectedSeriedId(event);
			if(selectedSeriesId.equals("")) {
				resetSelectedSeries();
			} else {
				hideSeries(selectedSeriesId);
			}
		}
	}

	private class ResetSeriesEventProcessor implements IEventProcessor {

		@Override
		public void handleEvent(Event event) {

			adjustRange(true);
			fireUpdateCustomSelectionHandlers(event);
			redraw();
		}
	}

	public BaseChart(Composite parent, int style) {
		super(parent, style);
		/*
		 * Add the mouse double click events.
		 */
		mouseDoubleClickEvents = new HashMap<Integer, Map<Integer, IEventProcessor>>();
		mouseDoubleClickEvents.put(MOUSE_BUTTON_LEFT, new HashMap<Integer, IEventProcessor>());
		mouseDoubleClickEvents.get(MOUSE_BUTTON_LEFT).put(SWT.CTRL, new SelectSeriesEventProcessor());
		mouseDoubleClickEvents.get(MOUSE_BUTTON_LEFT).put(SWT.SHIFT, new HideSeriesEventProcessor());
		mouseDoubleClickEvents.get(MOUSE_BUTTON_LEFT).put(SWT.NONE, new ResetSeriesEventProcessor());
		mouseDoubleClickEvents.put(MOUSE_BUTTON_MIDDLE, new HashMap<Integer, IEventProcessor>());
		mouseDoubleClickEvents.put(MOUSE_BUTTON_RIGHT, new HashMap<Integer, IEventProcessor>());
		/*
		 * Rectangle range selection.
		 */
		userSelection = new UserSelection();
		customSelectionHandlers = new ArrayList<ICustomSelectionHandler>();
		selectedSeriesIds = new HashSet<String>();
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
	}

	public boolean addCustomSelectionHandler(ICustomSelectionHandler customSelectionHandler) {

		return customSelectionHandlers.add(customSelectionHandler);
	}

	public boolean removeCustomSelectionHandler(ICustomSelectionHandler customSelectionHandler) {

		return customSelectionHandlers.remove(customSelectionHandler);
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
	public void handleMouseMoveEvent(Event event) {

		if(event.stateMask == SWT.BUTTON1) {
			userSelection.setStopCoordinate(event.x, event.y);
			redrawCounter++;
			if(redrawCounter == TRIGGER_REDRAW_EVENT) {
				redraw();
				redrawCounter = 0;
			}
		}
	}

	@Override
	public void handleMouseDownEvent(Event event) {

		if(event.button == MOUSE_BUTTON_LEFT) {
			userSelection.setStartCoordinate(event.x, event.y);
			clickStartTime = System.currentTimeMillis();
		}
	}

	@Override
	public void handleMouseUpEvent(Event event) {

		if(event.button == MOUSE_BUTTON_LEFT) {
			long deltaTime = System.currentTimeMillis() - clickStartTime;
			if(deltaTime >= DELTA_CLICK_TIME) {
				handleUserSelection(event);
			}
		}
		/*
		 * Button 3 = Show Menu
		 * See Menu in SrollableChart
		 */
	}

	@Override
	public void handleMouseWheel(Event event) {

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
		fireUpdateCustomSelectionHandlers(event);
		redraw();
	}

	@Override
	public void handleMouseDoubleClick(Event event) {

		IEventProcessor eventProcessor = getEventProcessor(mouseDoubleClickEvents.get(event.button), event);
		if(eventProcessor != null) {
			eventProcessor.handleEvent(event);
		}
	}

	/**
	 * This method may return null.
	 * 
	 * @param eventProcessors
	 * @param event
	 * @return {@link IEventProcessor}
	 */
	private IEventProcessor getEventProcessor(Map<Integer, IEventProcessor> eventProcessors, Event event) {

		IEventProcessor eventProcessor = null;
		if(event.stateMask == SWT.NONE) {
			/*
			 * The stateMask == 0 is handled differently.
			 */
			eventProcessor = eventProcessors.get(SWT.NONE);
		} else {
			/*
			 * Handle all other stateMasks.
			 */
			exitloop:
			for(int eventMask : eventProcessors.keySet()) {
				//
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
		return eventProcessor;
	}

	public void resetSelectedSeries() {

		ISeries[] series = getSeriesSet().getSeries();
		for(ISeries dataSeries : series) {
			if(dataSeries instanceof ILineSeries) {
				ILineSeries lineSeries = (ILineSeries)dataSeries;
				if(lineSeries.getLineWidth() > 0) {
					ISeriesSettings seriesSettings = getSeriesSettings(dataSeries.getId());
					if(seriesSettings instanceof ILineSeriesSettings) {
						/*
						 * Line Series
						 */
						ILineSeriesSettings lineSeriesSettings = (ILineSeriesSettings)seriesSettings;
						lineSeries.setLineWidth(lineSeriesSettings.getLineWidth());
						lineSeries.setVisible(lineSeriesSettings.isVisible());
						lineSeries.setVisibleInLegend(lineSeriesSettings.isVisibleInLegend());
					}
				}
			}
		}
		//
		selectedSeriesIds.clear();
		redraw();
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

	protected void fireUpdateCustomSelectionHandlers(Event event) {

		/*
		 * Handle the custom user selection handlers.
		 */
		for(ICustomSelectionHandler customSelectionHandler : customSelectionHandlers) {
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
		double coordinateX = xAxis.getDataCoordinate(event.x);
		if(event.count > 0) {
			xAxis.zoomIn(coordinateX);
		} else {
			xAxis.zoomOut(coordinateX);
		}
	}

	private void zoomY(IAxis yAxis, Event event) {

		/*
		 * Y Axis
		 */
		double coordinateY = yAxis.getDataCoordinate(event.y);
		if(event.count > 0) {
			yAxis.zoomIn(coordinateY);
		} else {
			yAxis.zoomOut(coordinateY);
		}
	}

	private void selectSeries(String selectedSeriesId) {

		ISeries dataSeries = getSeriesSet().getSeries(selectedSeriesId);
		if(dataSeries instanceof ILineSeries) {
			ILineSeries lineSeries = (ILineSeries)dataSeries;
			if(lineSeries.getLineWidth() > 0) {
				ISeriesSettings seriesSettings = getSeriesSettings(selectedSeriesId);
				if(seriesSettings instanceof ILineSeriesSettings) {
					/*
					 * Line Series
					 */
					selectedSeriesIds.add(selectedSeriesId);
					ILineSeriesSettings lineSeriesSettings = (ILineSeriesSettings)seriesSettings;
					lineSeries.setLineWidth(lineSeriesSettings.getLineWidthSelected());
					redraw();
				}
			}
		}
	}

	private void hideSeries(String selectedSeriesId) {

		ISeries dataSeries = getSeriesSet().getSeries(selectedSeriesId);
		if(dataSeries instanceof ILineSeries) {
			/*
			 * Line Series
			 */
			ILineSeries lineSeries = (ILineSeries)dataSeries;
			selectedSeriesIds.remove(selectedSeriesId);
			lineSeries.setVisible(false);
			lineSeries.setVisibleInLegend(false);
			redraw();
		}
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

		int xStart = userSelection.getStartX();
		int xStop = userSelection.getStopX();
		int yStart = userSelection.getStartY();
		int yStop = userSelection.getStopY();
		IAxis xAxis = getAxisSet().getXAxis(ID_PRIMARY_X_AXIS);
		IAxis yAxis = getAxisSet().getYAxis(ID_PRIMARY_Y_AXIS);
		//
		if((getOrientation() == SWT.HORIZONTAL)) {
			setHorizontalRange(xAxis, yAxis, xStart, xStop, yStart, yStop);
		} else {
			setVerticalRange(xAxis, yAxis, xStart, xStop, yStart, yStop);
		}
		/*
		 * Inform all registered handlers.
		 * Reset the current selection and redraw the chart.
		 */
		fireUpdateCustomSelectionHandlers(event);
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
