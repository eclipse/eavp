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
package org.eclipse.eavp.micro.main.java;

import java.util.ArrayList;

import com.google.gson.Gson;

/**
 * A CSVGrid is a POJO that contains the floating point data from a csv file.
 * Each value is represented twice, once in its row and once in its column. Each
 * row and column may optionally have a string title.
 * 
 * @author Robert Smith
 * @author Yuya Kawakami
 *
 */
public class CSVGrid {

	/**
	 * The list of columns, with each column being a list of numerical values.
	 */
	private ArrayList<ArrayList<Double>> columns;

	/**
	 * The list of column names, with columnNames[i] being the name for
	 * columns[i]. Columns without names are to be represented by an empty
	 * string.
	 */
	private ArrayList<String> columnNames;

	/**
	 * The list of rows, with each column being a list of numerical values.
	 */
	private ArrayList<ArrayList<Double>> rows;

	/**
	 * The list of row names, with rowNames[i] being the name for row[i]. Rows
	 * without names are to be represented by an empty string.
	 */
	private ArrayList<String> rowNames;

	
	/**
	 * Boolean to contain whether or not we can log the X axis
	 */
	private boolean canLogX;
	
	/**
	 * Boolean to contain whether or not we can log the X axis
	 */
	private boolean canLogY;
	
	

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CSVGrid other = (CSVGrid) obj;
		if (canLogX != other.canLogX)
			return false;
		if (canLogY != other.canLogY)
			return false;
		if (columnNames == null) {
			if (other.columnNames != null)
				return false;
		} else if (!columnNames.equals(other.columnNames)) {
			return false;
		}
		if (columns == null) {
			if (other.columns != null)
				return false;
		} else if (!columns.equals(other.columns))
			return false;
		if (rowNames == null) {
			if (other.rowNames != null)
				return false;
		} else if (!rowNames.equals(other.rowNames))
			return false;
		if (rows == null) {
			if (other.rows != null)
				return false;
		} else if (!rows.equals(other.rows))
			return false;
		return true;
	}

	/**
	 * The default constructor.
	 */
	public CSVGrid() {
		columns = new ArrayList<ArrayList<Double>>();
		columnNames = new ArrayList<String>();
		rows = new ArrayList<ArrayList<Double>>();
		rowNames = new ArrayList<String>();
		canLogX = true;
		canLogY = true;
	}
	
	/**
	 * Get the JSON input for a CSVGraph for this grid.
	 * 
	 * @return A JSON representation of this Grid, empty names replaced with
	 *         "Column X" or "Row X" where X is their index.
	 */
	public String getFullJSON() {

		// A copy of the grid with different names
		CSVGrid newGrid = new CSVGrid();

		// Get the lists making up the new grid
		ArrayList<String> newColumnNames = newGrid.getColumnNames();
		ArrayList<String> newRowNames = newGrid.getRowNames();
		ArrayList<ArrayList<Double>> newColumns = newGrid.getColumns();
		ArrayList<ArrayList<Double>> newRows = newGrid.getRows();

		// Copy all column names
		for (String columnName : columnNames) {
			
			if(columnName.startsWith("input=")) {
				columnName = columnName.substring(6);
			}
			
			newColumnNames.add(columnName);
		}

		// Copy all row names
		for (String rowName : rowNames) {
			newRowNames.add(rowName);
		}

		// Copy all columns
		for (ArrayList<Double> column : columns) {
			ArrayList<Double> newColumn = new ArrayList<Double>(column);
			newColumns.add(newColumn);
		}

		// Copy all rows
		for (ArrayList<Double> row : rows) {
			ArrayList<Double> newRow = new ArrayList<Double>(row);
			newRows.add(newRow);
		}

		// Add all column names, creating ones for unnammed columns.
		for (int i = 0; i < columns.size(); i++) {
			if (i >= columnNames.size() || "".equals(columnNames.get(i))) {
				newColumnNames.add("Column " + (i + 1));
			}
		}

		// Add all row names, creating ones for unnammed rows.
		for (int i = 0; i < rows.size(); i++) {
			if (i >= rowNames.size() || "".equals(rowNames.get(i))) {
				newRowNames.add("Row " + (i + 1));
			}
		}

		// Return the converted JSON
		return new Gson().toJson(newGrid);
	}

	/**
	 * Getter method for the rows.
	 * 
	 * @return The list of rows.
	 */
	public ArrayList<ArrayList<Double>> getRows() {
		return rows;
	}

	/**
	 * Getter method for the row names.
	 * 
	 * @return The list of row names.
	 */
	public ArrayList<String> getRowNames() {
		return rowNames;
	}
	
	/**
	 * Check if a double is positive or negative
	 * 
	 * @param num
	 * @return boolean that contains is value is negative or positive
	 */
	
	private boolean isPositive (double num) {
		return Double.compare(0.0, num) < 0; 
	}
	
	/**
	 * Function to check if we can apply 'LOG' to the X axis
	 */
	
	public void checkCanLogX() {
		boolean checkFirst  = isPositive(rows.get(0).get(0));
		boolean ret = true;
		outerloop:
		for (ArrayList<Double> arr : rows) {
			for (double num : arr) {
				if (checkFirst != isPositive(num)) {
					ret = false;
					break outerloop;
				}
				
			}
		}
		setCanLogX(ret);
	}
	
	/**
	 * Function to check if we can apply 'LOG' to the X axis
	 */
	
	public void checkCanLogY() {
		boolean checkFirst  = isPositive(columns.get(0).get(0));
		boolean ret = true;
		outerloop:
		for (ArrayList<Double> arr : columns) {
			for (double num : arr) {
				if (checkFirst != isPositive(num)) {
					ret = false;
					break outerloop;
				}
				
			}
		}
		setCanLogY(ret);
	}
	
	/**
	 * Getter for canLogX
	 * 
	 * @return canLogX
	 */

	public boolean getCanLogX() {
		return canLogX;
	}
	
	/**
	 * Setter for canLogX
	 * 
	 * @param canLogX
	 */

	public void setCanLogX(boolean canLogX) {
		this.canLogX = canLogX;
	}
	/**
	 * Getter for canLogY
	 * 
	 * @return canLogY
	 */

	public boolean getCanLogY() {
		return canLogY;
	}
	
	/**
	 * Setter for canLogY
	 * 
	 * @param canLogY
	 */

	public void setCanLogY(boolean canLogY) {
		this.canLogY = canLogY;
	}
	
	/**
	 * @param columns the columns to set
	 */
	public void setColumns(ArrayList<ArrayList<Double>> columns) {
		this.columns = columns;
	}

	/**
	 * @param rows the rows to set
	 */
	public void setRows(ArrayList<ArrayList<Double>> rows) {
		this.rows = rows;
	}
	
	/**
	 * Getter method for the columns.
	 * 
	 * @return The list of columns.
	 */
	public ArrayList<ArrayList<Double>> getColumns() {
		return columns;
	}

	/**
	 * Getter method for the column names.
	 * 
	 * @return The list of column names.
	 */
	public ArrayList<String> getColumnNames() {
		return columnNames;
	}

}
