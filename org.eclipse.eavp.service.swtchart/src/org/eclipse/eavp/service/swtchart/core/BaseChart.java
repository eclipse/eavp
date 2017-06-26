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
import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.swtchart.IAxis;
import org.swtchart.IAxis.Position;
import org.swtchart.IAxisSet;
import org.swtchart.Range;

public class BaseChart extends AbstractExtendedChart implements IChartDataCoordinates, IRangeSupport, IExtendedChart {

	public static final int ID_PRIMARY_X_AXIS = 0;
	public static final int ID_PRIMARY_Y_AXIS = 0;
	public static final String DEFAULT_TITLE_X_AXIS = "X-Axis";
	public static final String DEFAULT_TITLE_Y_AXIS = "Y-Axis";
	//
	private static final String RESET_CHART = "Reset Chart";
	private static final String EXPORT_SELECTION = "Export Chart Selection";
	private static final String EXPORT_SELECTION_TSV = "*.tsv";
	private static final String EXPORT_SELECTION_LATEX = "*.tex";
	private static final String EXPORT_SELECTION_PNG = "*.png";
	/*
	 * Prevent accidental zooming.
	 * At least 30% of the chart width or height needs to be selected.
	 */
	private static final int MIN_SELECTION_PERCENTAGE = 30;
	private static final long DELTA_CLICK_TIME = 100;
	//
	private UserSelection userSelection;
	private List<ICustomSelectionHandler> customSelectionHandlers;
	private long clickStartTime;

	public BaseChart(Composite parent, int style) {
		super(parent, style);
		/*
		 * Rectangle range selection.
		 */
		userSelection = new UserSelection();
		customSelectionHandlers = new ArrayList<ICustomSelectionHandler>();
		createMenu();
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

	@Override
	public void paintControl(PaintEvent e) {

		if(userSelection.isActive()) {
			/*
			 * Draw the rectangle of the user selection.
			 */
			int xMin = Math.min(userSelection.getStartX(), userSelection.getStopX());
			int xMax = Math.max(userSelection.getStartX(), userSelection.getStopX());
			int yMin = Math.min(userSelection.getStartY(), userSelection.getStopY());
			int yMax = Math.max(userSelection.getStartY(), userSelection.getStopY());
			e.gc.drawRectangle(xMin, yMin, xMax - xMin, yMax - yMin);
		}
	}

	@Override
	public void handleMouseMoveEvent(Event event) {

		if(event.stateMask == SWT.BUTTON1) {
			userSelection.setStopCoordinate(event.x, event.y);
			redraw();
		}
	}

	@Override
	public void handleMouseDownEvent(Event event) {

		if(event.button == 1) {
			userSelection.setStartCoordinate(event.x, event.y);
			clickStartTime = System.currentTimeMillis();
		}
	}

	@Override
	public void handleMouseUpEvent(Event event) {

		if(event.button == 1) {
			long deltaTime = System.currentTimeMillis() - clickStartTime;
			if(deltaTime >= DELTA_CLICK_TIME) {
				handleUserSelection(event);
			}
		} else if(event.button == 3) {
			// Show menu
			System.out.println("Menu");
		}
	}

	@Override
	public void handleMouseWheel(Event event) {

		IAxis xAxis = getAxisSet().getXAxis(ID_PRIMARY_X_AXIS);
		IAxis yAxis = getAxisSet().getYAxis(ID_PRIMARY_Y_AXIS);
		/*
		 * X Axis
		 */
		double coordinateX = xAxis.getDataCoordinate(event.x);
		if(event.count > 0) {
			xAxis.zoomIn(coordinateX);
		} else {
			xAxis.zoomOut(coordinateX);
		}
		/*
		 * Y Axis
		 */
		double coordinateY = yAxis.getDataCoordinate(event.y);
		if(event.count > 0) {
			yAxis.zoomIn(coordinateY);
		} else {
			yAxis.zoomOut(coordinateY);
		}
		/*
		 * Adjust the range if it shall not exceed the initial
		 * min and max values.
		 */
		if(isUseRangeRestriction()) {
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

		if(event.button == 1) {
			adjustRange(true);
			fireUpdateCustomSelectionHandlers(event);
			redraw();
		}
	}

	private void handleUserSelection(Event event) {

		int minSelectedWidth;
		int deltaWidth;
		//
		if((getOrientation() == SWT.HORIZONTAL)) {
			minSelectedWidth = getPlotArea().getBounds().width / MIN_SELECTION_PERCENTAGE;
			deltaWidth = Math.abs(userSelection.getStartX() - event.x);
		} else {
			minSelectedWidth = getPlotArea().getBounds().height / MIN_SELECTION_PERCENTAGE;
			deltaWidth = Math.abs(userSelection.getStartY() - event.y);
		}
		/*
		 * Prevent accidental zooming.
		 */
		if(deltaWidth >= minSelectedWidth) {
			//
			int xStart = userSelection.getStartX();
			int xStop = userSelection.getStopX();
			int yStart = userSelection.getStartY();
			int yStop = userSelection.getStopY();
			IAxis xAxis = getAxisSet().getXAxis(ID_PRIMARY_X_AXIS);
			IAxis yAxis = getAxisSet().getYAxis(ID_PRIMARY_Y_AXIS);
			//
			if((getOrientation() == SWT.HORIZONTAL)) {
				setRange(xAxis, xStart, xStop, true);
				setRange(yAxis, yStart, yStop, true);
			} else {
				setRange(xAxis, yStart, yStop, true);
				setRange(yAxis, xStart, xStop, true);
			}
			/*
			 * Inform all registered handlers.
			 * Reset the current selection and redraw the chart.
			 */
			fireUpdateCustomSelectionHandlers(event);
			userSelection.reset();
			redraw();
		}
	}

	public String[] getAxisLabels(String axis) {

		IAxis[] axes = getAxes(axis);
		int size = axes.length;
		String[] items = new String[size];
		//
		for(int i = 0; i < size; i++) {
			/*
			 * Get the label.
			 */
			String label = "";
			String title = axes[i].getTitle().getText();
			String description = getAxisDescription(axis, i);
			//
			if(title.equals("")) {
				/*
				 * Title is not set.
				 * Use the description instead or
				 * print a note that no label is available.
				 */
				if(description.equals("")) {
					label = "label not set";
				} else {
					label = description;
				}
			} else {
				/*
				 * Title is set.
				 * Use description if available
				 * otherwise the title.
				 */
				if(description.equals("")) {
					label = title;
				} else {
					label = description;
				}
			}
			/*
			 * Set the label.
			 */
			items[i] = label;
		}
		return items;
	}

	public DecimalFormat getDecimalFormat(String axis, int id) {

		DecimalFormat decimalFormat;
		IAxisSettings axisSettings = getAxisSettings(axis, id);
		//
		if(axisSettings != null) {
			decimalFormat = axisSettings.getDecimalFormat();
		} else {
			decimalFormat = new DecimalFormat();
		}
		return decimalFormat;
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
		} else if(menuItem.getText().equals(EXPORT_SELECTION_TSV)) {
			//
		} else if(menuItem.getText().equals(EXPORT_SELECTION_LATEX)) {
			//
		} else if(menuItem.getText().equals(EXPORT_SELECTION_PNG)) {
			//
		}
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

	private void createMenu() {

		Composite plotArea = getPlotArea();
		Menu menu = new Menu(plotArea);
		plotArea.setMenu(menu);
		//
		MenuItem menuItem;
		/*
		 * Basic
		 */
		menuItem = new MenuItem(menu, SWT.PUSH);
		menuItem.setText(RESET_CHART);
		menuItem.addListener(SWT.Selection, this);
		//
		menuItem = new MenuItem(menu, SWT.SEPARATOR);
		/*
		 * Export
		 */
		menuItem = new MenuItem(menu, SWT.CASCADE);
		menuItem.setText(EXPORT_SELECTION);
		Menu exportMenu = new Menu(menuItem);
		menuItem.setMenu(exportMenu);
		//
		menuItem = new MenuItem(exportMenu, SWT.PUSH);
		menuItem.setText(EXPORT_SELECTION_TSV);
		menuItem.addListener(SWT.Selection, this);
		//
		menuItem = new MenuItem(exportMenu, SWT.PUSH);
		menuItem.setText(EXPORT_SELECTION_LATEX);
		menuItem.addListener(SWT.Selection, this);
		//
		menuItem = new MenuItem(exportMenu, SWT.PUSH);
		menuItem.setText(EXPORT_SELECTION_PNG);
		menuItem.addListener(SWT.Selection, this);
	}

	private IAxis[] getAxes(String axis) {

		IAxisSet axisSet = getAxisSet();
		//
		if(axis.equals(IExtendedChart.X_AXIS)) {
			return axisSet.getXAxes();
		} else {
			return axisSet.getYAxes();
		}
	}

	private String getAxisDescription(String axis, int id) {

		String description = "";
		//
		IAxisSettings axisSettings = getAxisSettings(axis, id);
		if(axisSettings != null) {
			description = axisSettings.getDescription();
		}
		//
		return description;
	}

	private IAxisSettings getAxisSettings(String axis, int id) {

		IAxisSettings axisSettings = null;
		if(axis.equals(IExtendedChart.X_AXIS)) {
			axisSettings = getXAxisSettingsMap().get(id);
		} else {
			axisSettings = getYAxisSettingsMap().get(id);
		}
		return axisSettings;
	}
}
