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
package test.java.org.eclipse.eavp.test.micro;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.eavp.micro.main.java.CSVGrid;
import org.junit.Before;
import org.junit.Test;

/**
 * A class for testing the CSVGrid.
 * 
 * @author Robert Smith
 *
 */
public class TestCSVGrid {

	//The grid for testing
	private CSVGrid grid;
	
	/**
	 * Construct a grid before each test
	 */
	@Before
	public void setup() {
		grid = new CSVGrid();
		
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
	}
	
	/**
	 * Test that grids are correctly converted to JSON.
	 */
	@Test
	public void testFullJSON() {
		
		//Convert to JSON and check that the data is present and default column/row names were inserted
		String json = grid.getFullJSON();
		assertTrue(json.contains("\"columns\":[[1.0,2.0,3.0],[4.0,5.0,6.0],[7.0,8.0,9.0]]"));
		assertTrue(json.contains("\"columnNames\":[\"Column 1\",\"Column 2\",\"Column 3\"]"));
		assertTrue(json.contains("\"rows\":[[1.0,4.0,7.0],[2.0,5.0,8.0],[3.0,6.0,9.0]]"));
		assertTrue(json.contains("\"rowNames\":[\"Row 1\",\"Row 2\",\"Row 3\"]"));
		
		//Add a column name and check that it is present and that other column continue to be named correctly
		List<String> colNames = grid.getColumnNames();
		colNames.add("foo");
		json = grid.getFullJSON();
		assertTrue(json.contains("\"columnNames\":[\"foo\",\"Column 2\",\"Column 3\"]"));
		
		//Check that json is correct when all columns are named
		colNames.add("bar");
		colNames.add("foobar");
		json = grid.getFullJSON();
		assertTrue(json.contains("\"columnNames\":[\"foo\",\"bar\",\"foobar\"]"));
		
		//Add a row name and check that it is present and that other row names continue to be named correctly
		List<String> rowNames = grid.getRowNames();
		rowNames.add("fizz");
		json = grid.getFullJSON();
		assertTrue(json.contains("\"rowNames\":[\"fizz\",\"Row 2\",\"Row 3\"]"));
		
		//Check that json is correct when all rows are named
		rowNames.add("buzz");
		rowNames.add("fizzbuzz");
		json = grid.getFullJSON();
		assertTrue(json.contains("\"rowNames\":[\"fizz\",\"buzz\",\"fizzbuzz\"]"));
	}
	
	/**
	 * Test that the check for whether x can be displayed logarithmaclly is working
	 */
	@Test
	public void testCanLogX() {
		
		//Check that the grid x axis can be displayed as a log
		assertTrue(grid.getCanLogX());
		
		//Add a negative value and then check that the grid x axis cannot be displayed as a log
		grid.getRows().get(0).add(-1d);
		grid.checkCanLogX();
		assertFalse(grid.getCanLogX());
	}
	
	/**
	 * Test that the check for whether y can be displayed logarithmaclly is working
	 */
	@Test
	public void testCanLogY() {
		
		//Check that the grid x axis can be displayed as a log
		assertTrue(grid.getCanLogY());
		
		//Add a negative value and then check that the grid x axis cannot be displayed as a log
		grid.getColumns().get(0).add(-1d);
		grid.checkCanLogY();
		assertFalse(grid.getCanLogY());
	}
	
	/**
	 * Test that columns can be get and set
	 */
	@Test
	public void testColumns() {
		
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
		
		//Check that the columns are correct
		assertEquals(columns, grid.getColumns());
	}
	
	/**
	 * Test that rows can be get and set
	 */
	@Test
	public void testRows() {
		
		//Add the data to the grid
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
		
		//Check that the rows are correct
		assertEquals(rows, grid.getRows());
	}
}
