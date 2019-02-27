/*******************************************************************************
 * Copyright (c) 2019 UT-Battelle, LLC.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   Initial API and implementation and/or initial documentation - 
 *   Robert Smith
 *******************************************************************************/
package test.java.org.eclipse.eavp.test.micro.vaadin.csv;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.dussan.vaadin.dcharts.base.elements.XYseries;
import org.dussan.vaadin.dcharts.data.DataSeries;
import org.eclipse.eavp.micro.main.java.CSVGrid;
import org.eclipse.eavp.micro.vaadin.csv.CSVGraph;
import org.eclipse.eavp.micro.vaadin.csv.ColorPickerLayout;
import org.junit.Before;
import org.junit.Test;

import com.vaadin.shared.ui.colorpicker.Color;

/**
 * A class for testing the CSVGraph.
 * 
 * @author Robert Smith
 *
 */
public class TestCSVGraph {

	//The graph for testing
	private CSVGraph graph;
	
	/**
	 * Construct a grid before each test
	 */
	@Before
	public void setup() {
		CSVGrid grid = new CSVGrid();
		
		//Add the data to the grid
		ArrayList<Double> column1 = new ArrayList<Double>();
		column1.add(1d);
		column1.add(2d);
		column1.add(3d);
		ArrayList<Double> column2 = new ArrayList<Double>();
		column2.add(4d);
		column2.add(5d);
		column2.add(6d);
		ArrayList<Double> column3 = new ArrayList<Double>();
		column3.add(7d);
		column3.add(8d);
		column3.add(9d);
		ArrayList<ArrayList<Double>> columns = new ArrayList<ArrayList<Double>>();
		columns.add(column1);
		columns.add(column2);
		columns.add(column3);
		grid.setColumns(columns);
		ArrayList<Double> row1 = new ArrayList<Double>();
		row1.add(1d);
		row1.add(4d);
		row1.add(7d);
		ArrayList<Double> row2 = new ArrayList<Double>();
		row2.add(2d);
		row2.add(5d);
		row2.add(8d);
		ArrayList<Double> row3 = new ArrayList<Double>();
		row3.add(3d);
		row3.add(6d);
		row3.add(9d);
		ArrayList<ArrayList<Double>> rows = new ArrayList<ArrayList<Double>>();
		rows.add(row1);
		rows.add(row2);
		rows.add(row3);
		grid.setRows(rows);
		
		//Set up the series names
		grid.getColumnNames().add("foo");
		grid.getColumnNames().add("bar");
		grid.getColumnNames().add("foobar");
		grid.getRowNames().add("fizz");
		grid.getRowNames().add("buzz");
		grid.getRowNames().add("fizzbuzz");
		
		//Create the graph
		graph = new CSVGraph(grid, "");
	}
	
	/**
	 * Test that the series are set up correctly
	 */
	@Test
	public void testConstruction() {
		
		List<Double> col1 = graph.getSeriesData("foo");
		assertEquals(3, col1.size());
		assertTrue(col1.contains(1d));
		assertTrue(col1.contains(2d));
		assertTrue(col1.contains(3d));
		
		List<Double> row1 = graph.getSeriesData("fizz");
		assertEquals(3, row1.size());
		assertTrue(row1.contains(1d));
		assertTrue(row1.contains(4d));
		assertTrue(row1.contains(7d));
	}
	
	/**
	 * Test setting the dependent and independent series and their displays
	 */
	@Test
	public void testSeries() {
		
		//We can't get the series from the graph itself, so just test setting them
		graph.setIndependentSeries("foobar");
		ArrayList<String> series = new ArrayList<String>();
		series.add("fizz");
		series.add("buzz");
		series.add("bar");
		graph.setDependentSeries(series);
		graph.setData("foo", series);
		
		//Set some options
		HashMap<String, String> options = new HashMap<String, String>();
		options.put("fizz", "showline=false");
		options.put("buzz", "showmarkers=false");
		options.put("bar", "showline=false,showmarkers=false");
		graph.setSeriesDisplayOptions(options);
		HashMap<String, XYseries> displayOptions = graph.getSeriesDisplayOptions();
		
		//Fizz should have only markers
		assertFalse(displayOptions.get("fizz").getShowLine());
		assertTrue(displayOptions.get("fizz").getMarkerOptions().getShow());
		
		//Buzz should have only a line
		assertTrue(displayOptions.get("buzz").getShowLine());
		assertFalse(displayOptions.get("buzz").getMarkerOptions().getShow());
	
		//Bar won't be displayed at all
		assertFalse(displayOptions.get("bar").getShowLine());
		assertFalse(displayOptions.get("bar").getMarkerOptions().getShow());
	}
}
