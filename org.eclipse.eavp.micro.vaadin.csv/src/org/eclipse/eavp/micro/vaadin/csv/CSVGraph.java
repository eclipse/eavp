/*******************************************************************************
 * Copyright (c) 2019 UT-Battelle, LLC.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   Robert Smith
 *******************************************************************************/
package org.eclipse.eavp.micro.vaadin.csv;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import org.dussan.vaadin.dcharts.DCharts;
import org.dussan.vaadin.dcharts.base.elements.XYaxis;
import org.dussan.vaadin.dcharts.base.elements.XYseries;
import org.dussan.vaadin.dcharts.base.renderers.MarkerRenderer;
import org.dussan.vaadin.dcharts.base.renderers.TickRenderer;
import org.dussan.vaadin.dcharts.data.DataSeries;
import org.dussan.vaadin.dcharts.data.Ticks;
import org.dussan.vaadin.dcharts.events.click.ChartDataClickEvent;
import org.dussan.vaadin.dcharts.events.click.ChartDataClickHandler;
import org.dussan.vaadin.dcharts.metadata.LegendPlacements;
import org.dussan.vaadin.dcharts.metadata.SeriesToggles;
import org.dussan.vaadin.dcharts.metadata.XYaxes;
import org.dussan.vaadin.dcharts.metadata.renderers.AxisRenderers;
import org.dussan.vaadin.dcharts.metadata.renderers.LabelRenderers;
import org.dussan.vaadin.dcharts.metadata.styles.MarkerStyles;
import org.dussan.vaadin.dcharts.options.Axes;
import org.dussan.vaadin.dcharts.options.Cursor;
import org.dussan.vaadin.dcharts.options.Legend;
import org.dussan.vaadin.dcharts.options.Options;
import org.dussan.vaadin.dcharts.options.Series;
import org.dussan.vaadin.dcharts.renderers.legend.EnhancedLegendRenderer;
import org.eclipse.eavp.micro.main.java.CSVGrid;
import org.eclipse.eavp.micro.main.java.InputStreamUtils;

import com.vaadin.contextmenu.ContextMenu;
import com.vaadin.contextmenu.Menu;
import com.vaadin.contextmenu.MenuItem;
import com.vaadin.event.ShortcutAction;
import com.vaadin.event.ShortcutListener;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.ListSelect;
import com.vaadin.ui.Notification;
import com.vaadin.ui.TextField;
import com.vaadin.ui.TwinColSelect;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;

/**
 * This class is an extension of DCharts for the plotting of CSV data in Vaadin.
 * It exposes several heavily used features available in DCharts by default
 * through function calls, specifically with ease of use for graphing data from
 * a CSVGrid in mind. It also adds user controls for easy setting of changing
 * the graph in real time after it has already been displayed to the user.
 *
 * @author Robert Smith
 * @author Yuya Kawakami
 *
 */
public class CSVGraph extends DCharts {

	// TODO Should be gotten through hateos
	// The url for the RDF service
	private static final String BASE_URL = "http://localhost:8080/org.eclipse.eavp.micro.state-1.0-SNAPSHOT/state/";

	/**
	 * The series values and names to be used in the plot.
	 */
	public CSVGrid data;

	/**
	 * The list of names of the currently graphed dependent data series, as
	 * column/row names in data.
	 */
	private List<String> dependentData;

	/**
	 * The identifier to be used in calls to the rdf service.
	 */
	private String identifier;

	/**
	 * The name of the data row/column currently being used as the independent
	 * series in the plot.
	 */
	private String independentData;

	/**
	 * The local copy of the DChart options, maintained only because the
	 * superclass's options class member is private.
	 */
	private Options localOptions;

	/**
	 * The DCharts data series which is currently being displayed in the chart.
	 */
	private DataSeries series;

	/**
	 * The Series list with set options for the series by name. Each key corresponds
	 * to a column or row from the data, and, if it exists, has an XYseries that
	 * should be used instead of the default XYseries when graphing that row/column.
	 */
	public HashMap<String, XYseries> seriesDisplayOptions;

	/**
	 * The DChart x axis.
	 */
	private XYaxis xAxis;

	/**
	 * The label displayed on the x axis.
	 */
	private String xLabel;

	/**
	 * The DChart y axis.
	 */
	private XYaxis yAxis;

	/**
	 * The label displayed on the y axis.
	 */
	private String yLabel;

	/**
	 * The default constructor.
	 *
	 * @param grid
	 *            The CSVgrid which provides data to this plot.
	 * @param identifier
	 *            The unique identifier that will identify this graph's state in
	 *            communications with the rdf service.
	 */
	public CSVGraph(CSVGrid grid, String identifier) {
		super();

		// Initialize all DChart class variables
		data = grid;
		this.identifier = identifier;
		Axes axes = new Axes();
		xAxis = new XYaxis(XYaxes.X).setLabel("").setLabelRenderer(LabelRenderers.CANVAS);
		yAxis = new XYaxis(XYaxes.Y).setLabel("").setLabelRenderer(LabelRenderers.CANVAS);
		axes.addAxis(xAxis);
		axes.addAxis(yAxis);
		xLabel = "";
		yLabel = "";
		localOptions = new Options();
		localOptions.addOption(axes);
		setOptions(localOptions);
		dependentData = new ArrayList<String>();
		seriesDisplayOptions = new HashMap<String, XYseries>();

		// TODO Move this into a context menu option
		// localOptions.addOption(new
		// Cursor().setZoom(true).setShowCursorLegend(false).setShow(true));

		// Initialize the axes
		initializeAxis(xAxis);
		initializeAxis(yAxis);

		boolean defaultRowNames = data.getRowNames().get(0).toLowerCase().equals("row 1");
		boolean defaultColumnNames = data.getColumnNames().get(0).toLowerCase().equals("column 1");

		// Graph all the rows or all the columns, whichever there are fewer of,
		// with the first row arbitrarily taken as the independent series.
		if ((!defaultRowNames && defaultColumnNames) || data.getRowNames().size() <= data.getColumnNames().size()) {
			List<String> dependentRowNames = data.getRowNames().subList(1, data.getRowNames().size());
			setData(data.getRowNames().get(0), dependentRowNames);
		} else {
			List<String> dependentColumnNames = data.getColumnNames().subList(1, data.getColumnNames().size());
			setData(data.getColumnNames().get(0), dependentColumnNames);
		}

		// Check if we can 'LOG' the axes
		data.checkCanLogX();
		data.checkCanLogY();

		// A final reference to this class
		final CSVGraph self = this;

		// Add a context menu to the graph
		ContextMenu menu = new ContextMenu(this, true);

		// Add an option that options a window to select the independent series.
		menu.addItem("Set independent series", new Menu.Command() {

			/*
			 * (non-Javadoc)
			 *
			 * @see com.vaadin.contextmenu.Menu.Command#menuSelected(com.vaadin.
			 * contextmenu.MenuItem)
			 */
			@Override
			public void menuSelected(MenuItem selectedItem) {

				// Create a sub-window and set the content
				final Window subWindow = new Window("Select independent series");
				VerticalLayout subContent = new VerticalLayout();
				subWindow.setContent(subContent);

				// Create a list of series from all possible series in the data.
				List<String> seriesNames = data.getColumnNames();
				seriesNames.addAll(data.getRowNames());
				final ListSelect seriesList = new ListSelect<>("Select the independent series", seriesNames);

				// Button that accepts the selection
				Button ok = new Button("OK");
				ok.addClickListener(new ClickListener() {

					/*
					 * (non-Javadoc)
					 *
					 * @see com.vaadin.ui.Button.ClickListener#buttonClick(com.vaadin
					 * .ui.Button.ClickEvent)
					 */
					@Override
					public void buttonClick(ClickEvent event) {

						// Set the independent series to the user selection
						self.setIndependentSeries((String) seriesList.getSelectedItems().iterator().next());
						subWindow.close();
					}

				});

				// Put some components in it
				subContent.addComponent(seriesList);
				subContent.addComponent(ok);

				// Center it in the browser window
				subWindow.center();

				// Open it in the UI
				getUI().addWindow(subWindow);
			}
		});

		// Add an option to open a window to set the dependent series
		menu.addItem("Set dependent series", new Menu.Command() {

			/*
			 * (non-Javadoc)
			 *
			 * @see com.vaadin.contextmenu.Menu.Command#menuSelected(com.vaadin.
			 * contextmenu.MenuItem)
			 */
			@Override
			public void menuSelected(MenuItem selectedItem) {

				// Create a sub-window and set the content
				final Window subWindow = new Window("Select dependent series");
				VerticalLayout subContent = new VerticalLayout();
				subWindow.setContent(subContent);

				// Create two columns, with the first containing all series
				// names and the second containing the list of series to graph.
				List<String> seriesNames = data.getColumnNames();
				seriesNames.addAll(data.getRowNames());
				final TwinColSelect seriesList = new TwinColSelect<>(
						"Move all dependent series to be graphed into the right column.", seriesNames);

				// Button to accept the changes
				Button ok = new Button("OK");
				ok.addClickListener(new ClickListener() {

					/*
					 * (non-Javadoc)
					 *
					 * @see com.vaadin.ui.Button.ClickListener#buttonClick(com.vaadin
					 * .ui.Button.ClickEvent)
					 */
					@Override
					public void buttonClick(ClickEvent event) {

						// Set the dependent series to the new set of
						// selections.
						self.setDependentSeries(new ArrayList(seriesList.getSelectedItems()));
						subWindow.close();
					}

				});

				// Put some components in it
				subContent.addComponent(seriesList);
				subContent.addComponent(ok);

				// Center it in the browser window
				subWindow.center();

				// Open it in the UI
				getUI().addWindow(subWindow);
			}
		});

		// Add an option to open a window to set the x axis label
		menu.addItem("Set x axis label", new Menu.Command() {

			@Override
			public void menuSelected(MenuItem selectedItem) {

				// Create a sub-window and set the content
				final Window subWindow = new Window("Edit X Axis Label");
				VerticalLayout subContent = new VerticalLayout();
				subWindow.setContent(subContent);

				// A text field to edit the label
				final TextField text = new TextField();
				text.setPlaceholder(self.getXLabel());
				text.setValue(self.getXLabel());

				// On pressing enter, accept the changes
				text.addShortcutListener(new ShortcutListener("Shortcut Name", ShortcutAction.KeyCode.ENTER, null) {

					/*
					 * (non-Javadoc)
					 *
					 * @see com.vaadin.event.ShortcutListener#handleAction(java.lang. Object,
					 * java.lang.Object)
					 */
					@Override
					public void handleAction(Object sender, Object target) {
						self.setXLabel(text.getValue());
						subWindow.close();
					}
				});

				// Button to accept the changes.
				Button ok = new Button("OK");
				ok.addClickListener(new ClickListener() {

					/*
					 * (non-Javadoc)
					 *
					 * @see com.vaadin.ui.Button.ClickListener#buttonClick(com.vaadin
					 * .ui.Button.ClickEvent)
					 */
					@Override
					public void buttonClick(ClickEvent event) {

						// Change the label to the new value
						self.setXLabel(text.getValue());
						subWindow.close();
					}

				});

				// Put some components in it
				subContent.addComponent(text);
				subContent.addComponent(ok);

				// Center it in the browser window
				subWindow.center();

				// Open it in the UI
				getUI().addWindow(subWindow);
			}
		});

		// Add an item to open a window to edit the y axis label
		menu.addItem("Set y axis label", new Menu.Command() {

			/*
			 * (non-Javadoc)
			 *
			 * @see com.vaadin.contextmenu.Menu.Command#menuSelected(com.vaadin.
			 * contextmenu.MenuItem)
			 */
			@Override
			public void menuSelected(MenuItem selectedItem) {

				// Create a sub-window and set the content
				final Window subWindow = new Window("Edit Y Axis Label");
				VerticalLayout subContent = new VerticalLayout();
				subWindow.setContent(subContent);

				// A text field for editing the label
				final TextField text = new TextField();
				text.setPlaceholder(self.getYLabel());
				text.setValue(self.getYLabel());

				// Button to accept the changes
				Button ok = new Button("OK");
				ok.addClickListener(new ClickListener() {

					/*
					 * (non-Javadoc)
					 *
					 * @see com.vaadin.ui.Button.ClickListener#buttonClick(com.vaadin
					 * .ui.Button.ClickEvent)
					 */
					@Override
					public void buttonClick(ClickEvent event) {

						// Change the y axis label to the new value
						self.setYLabel(text.getValue());
						subWindow.close();
					}

				});

				// Put some components in it
				subContent.addComponent(text);
				subContent.addComponent(ok);

				// Center it in the browser window
				subWindow.center();

				// Open it in the UI
				getUI().addWindow(subWindow);
			}
		});

		// Add an item to edit the x axis proerties
		menu.addItem("Edit X Axis", new Menu.Command() {

			/*
			 * (non-Javadoc)
			 *
			 * @see com.vaadin.contextmenu.Menu.Command#menuSelected(com.vaadin.
			 * contextmenu.MenuItem)
			 */
			@Override
			public void menuSelected(MenuItem selectedItem) {
				CSVAxisWindow subWindow = new CSVAxisWindow(self, xAxis, null, "X");
				subWindow.center();
				getUI().addWindow(subWindow);
			}
		});

		// Add an item to edit the y axis proerties
		menu.addItem("Edit Y Axis", new Menu.Command() {

			/*
			 * (non-Javadoc)
			 *
			 * @see com.vaadin.contextmenu.Menu.Command#menuSelected(com.vaadin.
			 * contextmenu.MenuItem)
			 */
			@Override
			public void menuSelected(MenuItem selectedItem) {
				CSVAxisWindow subWindow = new CSVAxisWindow(self, yAxis, null, "Y");
				subWindow.center();
				getUI().addWindow(subWindow);
			}
		});

		// Set up point click listiner
		setEnableChartDataClickEvent(true);
		addHandler(new ChartDataClickHandler() {

			/*
			 * (non-Javadoc)
			 * 
			 * @see
			 * org.dussan.vaadin.dcharts.events.click.ChartDataClickHandler#onChartDataClick
			 * (org.dussan.vaadin.dcharts.events.click.ChartDataClickEvent)
			 */
			@Override
			public void onChartDataClick(ChartDataClickEvent event) {
				
				try {

					// Open the connection
					URL url = new URL(BASE_URL + "put?identifier=" + identifier + "&service=vaadin-csvservice");
					HttpURLConnection connection = (HttpURLConnection) url.openConnection();
					connection.setDoOutput(true);
					connection.setRequestMethod("POST");
					connection.setRequestProperty("Content-Type", "application/octet-stream");

					//Get the list of coordinates
					List list = ((List) event.getChartData().getData()[0]);

					// Send the content with a point identifier to the state service
					connection.getOutputStream().write(("POINT:" + list.get(0) + "," + list.get(1)).getBytes());
					connection.getResponseCode();

				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		});

	}

	/**
	 * Create the ticks for the given axis, updating them to fit the axis's current
	 * values.
	 * 
	 * @param axis
	 *            The axis, x or y, whose ticks are to be drawn.
	 */
	public void createTicks(XYaxes axis) {

		// The ticks to be set to the axis
		Ticks ticks = new Ticks();

		// Get the internal axis
		XYaxis chartAxis = localOptions.getAxes().getAxis(axis);

		// FIXME What does dcharts do if there's only 1 tick? 0 ticks?
		// Create the ticks for a logarithmic scale by creating the 1e^X ticks
		// from min to max.
		if (AxisRenderers.LOG.equals(chartAxis.getRenderer())) {

			// Get the axis min and max
			double axisMin = (double) chartAxis.getMin();
			double axisMax = (double) chartAxis.getMax();

			// TODO Do the same thing for the max in negative graphs
			// Guard against 0 values by setting the min to being the lowest non-zero value
			if (axisMin == 0) {

				// The lowest positve value seen
				Double minValue = null;

				// Check the dependent data for the y column
				if (axis.equals(XYaxes.Y)) {

					// Search each dependent series
					for (String seriesName : data.getColumnNames()) {

						if (dependentData.contains(seriesName)) {

							// The current series
							ArrayList<Double> series = data.getColumns().get(data.getColumnNames().indexOf(seriesName));

							// Initialize the min value, if possible
							if (minValue == null) {
								for (Double value : series) {
									if (value > 0) {
										minValue = value;
										break;
									}
								}
							}

							// Search for any lower values
							if (minValue != null) {
								for (Double value : series) {
									if (value < minValue && value > 0) {
										minValue = value;
									}
								}
							}

						}
					}
				}

				axisMin = minValue;
			}

			// The min tick is the smallest power of 10 greater than the minimum
			double min = 1;
			if (axisMin < 1) {
				while (min > axisMin) {
					min /= 10;
				}
				// min *= 10;
			} else {
				while (min < axisMin) {
					min *= 10;
				}
				min /= 10;
			}

			// The max tick is the largest power of 10 less than the maximum
			double max = 1;
			if (axisMax < 1) {
				while (max > axisMax) {
					max /= 10;
				}
				max *= 10;
			} else {
				while (max < axisMax) {
					max *= 10;
				}
				// max /= 10;
			}

			// Increase the last tick's size to ensure that it will cover the
			// range
			for (int i = 2; i < 10; i++) {
				if (max * i > axisMax) {
					max = max * i;
					break;
				}
			}

			// Create a list of ticks between the min and max
			double i = min;
			while (i <= max) {
				// System.out.println("Tick " + i);
				ticks.add(i);
				i = i * 10;
			}
		}

		else if (AxisRenderers.LINEAR.equals(chartAxis.getRenderer())) {

			// Get the min and max of the range
			double min = (double) chartAxis.getMin();
			double max = (double) chartAxis.getMax();

			// Step size is the number of ticks divided into the range
			double step = (max - min) / chartAxis.getNumberTicks();

			// Create the ticks, with the given number between the given min and
			// max
			for (double i = 0; i < chartAxis.getNumberTicks(); i++) {
				ticks.add(min + (i * step));
			}

			// Explicitly add the max to the ticks instead of approximating
			ticks.add(max);
		}

		// Set the ticks to the axis
		chartAxis.setTicks(ticks);
	}

	/**
	 * Getter method for the local options.
	 * 
	 * @return The local options
	 */
	public Options getLocalOptions() {
		return localOptions;
	}

	/**
	 * Getter method for the graphed series
	 * 
	 * @return The series
	 */
	public DataSeries getSeries() {
		return series;
	}

	/**
	 * Get the series with the given name.
	 *
	 * @param name
	 *            The name of the series to be retrieved.
	 * @return The data of the series of the given name
	 */
	public List<Double> getSeriesData(String name) {

		// Find the name in either the rows or the columns, then return
		// whichever row/column it corresponds to.
		int index = data.getRowNames().indexOf(name);

		if (index >= 0) {
			return data.getRows().get(index);
		} else {
			return data.getColumns().get((data.getColumnNames().indexOf(name)));
		}
	}

	/**
	 * Getter method for the map from series names to graph series with configured
	 * display options.
	 * 
	 * @return The series display options
	 */
	public HashMap<String, XYseries> getSeriesDisplayOptions() {
		return seriesDisplayOptions;
	}

	/**
	 * Getter method for the x axis label.
	 *
	 * @return The label string for the x axis.
	 */
	public String getXLabel() {
		return xLabel;
	}

	/**
	 * Getter method for the y axis label.
	 *
	 * @return The label string for the y axis.
	 */
	public String getYLabel() {
		return yLabel;
	}

	/**
	 * Set up an axis with initial values. This is neccesary because many axis
	 * options are null after the constructor is called.
	 *
	 * @param axis
	 *            The axis to initialize.
	 */
	private void initializeAxis(XYaxis axis) {

		// Set the axis attributes
		axis.setBorderColor("#7f7f7f");
		axis.setBorderWidth(1f);
		axis.setDrawMajorGridlines(true);
		axis.setDrawMajorTickMarks(true);
		axis.setDrawMinorGridlines(false);
		axis.setDrawMinorTickMarks(false);
		axis.setNumberTicks(13);
		axis.setRenderer(AxisRenderers.LINEAR);

		// Set some default tick renderer options
		TickRenderer defaultTickRenderer = new TickRenderer(null, true, true, false, 1, true, true, null, "", "%.3f",
				"", "", "");
		axis.setTickOptions(defaultTickRenderer);
	}

	/**
	 * Set the data being graphed.
	 *
	 * @param independentSeriesName
	 *            The name of the series that will be used for the x axis.
	 * @param dependentSeriesNames
	 *            A list of the names for all series that will be plotted along the
	 *            y axis.
	 */
	public void setData(String independentSeriesName, List<String> dependentSeriesNames) {

		// Whether the user has already been given a warning about mismatched
		// data lengths
		boolean mismatchWarning = false;

		// If the x axis label is the default value, or is empty, set it to the
		// name of the new inddependent data series
		if (xLabel.equals(independentData) || "".equals(xLabel)) {
			setXLabel(independentSeriesName);
		}

		// Save the current data series names
		independentData = independentSeriesName;
		dependentData = dependentSeriesNames;

		// The x axis data
		List<Double> independentSeries = getSeriesData(independentSeriesName);

		// Set the x axis range based on the independent series
		setAxisRange(xAxis, independentSeries.get(0), independentSeries.get(independentSeries.size() - 1));

		// Create a new set of data series, since the old one can't be edited
		series = new DataSeries();

		Double dependentSeriesMin = 0d;
		Double dependentSeriesMax = 0d;

		// Add each series named to the set of series
		for (String seriesName : dependentSeriesNames) {
			List<Double> dependentSeries = getSeriesData(seriesName);

			if (!dependentSeriesNames.get(0).equals(seriesName)) {

				// If this series's extrema are smaller/larger than what has
				// been seen before, save them as the new extrema
				Double seriesMin = Collections.min(dependentSeries);
				Double seriesMax = Collections.max(dependentSeries);
				if (seriesMin < dependentSeriesMin) {
					dependentSeriesMin = seriesMin;
				}
				if (seriesMax > dependentSeriesMax) {
					dependentSeriesMax = seriesMax;
				}
			} else {

				// If this is the first series, take its extrema
				dependentSeriesMin = Collections.min(dependentSeries);
				dependentSeriesMax = Collections.max(dependentSeries);

			}

			// If independent and dependent series aren't the same size, display
			// a warning
			if (!mismatchWarning && dependentSeries.size() != independentSeries.size()) {
				mismatchWarning = true;
				Notification.show("Warning: Series size mismatch",
						"A depdendent series had a different number of values than the independent series. Only the earliest values of the larger series will be displayed.",
						Notification.Type.ERROR_MESSAGE);
			}

			// Create a new series, then add each independent/dependent pair of
			// values
			series.newSeries();
			for (int i = 0; i < independentSeries.size() && i < dependentSeries.size(); i++) {
				series.add(independentSeries.get(i), dependentSeries.get(i));
			}
		}

		// Reset the axis range
		setAxisRange(yAxis, dependentSeriesMin, dependentSeriesMax);

		// Add a legend
		if (series.hasSubSeries()) {
			Legend legend = new Legend().setShow(true).setRendererOptions(
					new EnhancedLegendRenderer().setSeriesToggle(SeriesToggles.SLOW).setSeriesToggleReplot(false))
					.setPlacement(LegendPlacements.OUTSIDE_GRID);
			legend.setLabels(dependentSeriesNames.toArray(new String[dependentSeriesNames.size()]));
			localOptions.setLegend(legend);

		}

		// Refresh for the new data to take effect
		pushChanges();
	}

	/**
	 * Refresh the graph by pushing all local changes to it.
	 */
	public void pushChanges() {
		setDataSeries(series);
		xAxis.setLabel(xLabel);

		yAxis.setLabel(yLabel);
		Axes axes = new Axes();
		axes.addAxis(xAxis);
		axes.addAxis(yAxis);
		localOptions.addOption(axes);

		// Construct the series for the displayed series
		Series series = new Series();

		// Creaate a series for each dependent data series
		for (String name : dependentData) {

			// If there's a custom series already constructed, use it. Otherwise, use a
			// default one
			if (seriesDisplayOptions.containsKey(name)) {
				series.addSeries(seriesDisplayOptions.get(name));
			} else {
				series.addSeries(new XYseries());
			}
		}

		if (!series.getSeries().isEmpty()) {
			localOptions.setSeries(series);
		}
		setOptions(localOptions);
		show();
	}

	/**
	 * Set the bounds for an axis to 5% greater than the given range, so that there
	 * is a bit of room around the edges of the graph.
	 *
	 * @param axis
	 *            The axis whose range will be set.
	 * @param min
	 *            The smallest value on the axis.
	 * @param max
	 *            The largest value on the axis.
	 */
	private void setAxisRange(XYaxis axis, Double min, Double max) {

		// Calculate the range with a margin of empty space
		if (min > 0) {
			min *= 0.95;
		} else {
			min *= 1.05;
		}

		if (max > 0) {
			max *= 1.05;
		} else {
			max *= 0.95;
		}

		// Set the axis range
		axis.setMin(min);
		axis.setMax(max);

	}

	/**
	 * A convienence function that changes the dependent series but not the
	 * independent series. Equivalent to setData(this.independentData, seriesNames).
	 *
	 * @param seriesNames
	 *            The names of the series to be set as the new dependent series.
	 */
	public void setDependentSeries(List<String> seriesNames) {
		setData(independentData, seriesNames);
	}

	/**
	 * A convienence function that changes the independent series but not the
	 * dependent series. Equivalent to setData(seriesName, this.dependentData)
	 *
	 * @param seriesName
	 */
	public void setIndependentSeries(String seriesName) {
		setData(seriesName, dependentData);
	}

	/**
	 * Set the options for how series of a given name will be displayed. Values are
	 * to be of the form "option=value,option=value,option=value..."
	 * 
	 * Supported options are:
	 * 
	 * showline: false will remove the line connecting the points.
	 * 
	 * showmarkers: false remove the markers at each data point
	 * 
	 * @param options
	 *            The map of series names to options. The key is the name of a
	 *            column or row. The value is a string representing the options.
	 */
	public void setSeriesDisplayOptions(HashMap<String, String> options) {

		// Ignore null inputs
		if (options != null) {

			// Add a series for each row/column name that has options set
			for (String name : options.keySet()) {

				// The parsed map of option names to values
				HashMap<String, String> optionMap = new HashMap<String, String>();

				String[] tokens = options.get(name).split(",");

				for (int i = 0; i < tokens.length; i++) {
					if (!tokens[i].isEmpty()) {
						optionMap.put(tokens[i].substring(0, tokens[i].indexOf("=")),
								tokens[i].substring(tokens[i].indexOf("=") + 1));
					}
				}

				// A basic series
				XYseries series = new XYseries();

				// Set the line to either show or not
				if (optionMap.containsKey("showline") && optionMap.get("showline").equals("false")) {
					series = series.setShowLine(false);
				} else {
					series = series.setShowLine(true).setLineWidth(2);
				}

				// Set the markers to either show or not
				if (optionMap.containsKey("showmarkers") && optionMap.get("showmarkers").equals("false")) {
					series = series.setMarkerOptions(new MarkerRenderer().setShow(false));
				} else {
					series = series
							.setMarkerOptions(new MarkerRenderer().setStyle(MarkerStyles.FILLED_CIRLCE).setShow(true));
				}

				// Save the constructed series to the map
				seriesDisplayOptions.put(name, series);
			}
		}
	}

	/**
	 * Change the label on the x axis.
	 *
	 * @param label
	 *            The new label for the x axis.
	 */
	public void setXLabel(String label) {
		xLabel = label;
		pushChanges();

	}

	/**
	 * Change the label on the y axis.
	 *
	 * @param label
	 *            The new label for the y axis.
	 */
	public void setYLabel(String label) {
		yLabel = label;
		pushChanges();
	}

}