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

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.text.NumberFormat;
import java.text.ParsePosition;
import java.util.ArrayList;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

/**
 * A micro-service for parsing files.
 * 
 * @author Robert Smith
 *
 */
@Path("/fileio")
public class FileParsingService {
	
	final double NaNReplacementValue = 0;

	@POST
	@Path("/csv/csvgrid/fulljson")
	@Consumes(MediaType.APPLICATION_OCTET_STREAM)
	@Produces(MediaType.APPLICATION_OCTET_STREAM)
	public ByteArrayInputStream plot(@QueryParam("filename") String name, InputStream inputStream) {

		// The grid being constructed
		CSVGrid grid = new CSVGrid();
		
		//Convert the input into a string
		String input = InputStreamUtils.readStringStream(inputStream);

		if (input == null) {
			// TODO Read local file
		} else {

			try {
				String decoded = URLDecoder.decode(input, "UTF-8");

				decoded = decoded.replaceAll("#.*", "");
				
				// Split the input into rows
				String rows[] = decoded.split("\\r?\\n");

				// Csv files use commas
				if (name.endsWith(".csv")) {
					readCSVGrid(grid, rows, ",");
				} else {
					readCSVGrid(grid, rows, null);
				}
				
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		try {
			return new ByteArrayInputStream(grid.getFullJSON().getBytes("UTF-8"));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new ByteArrayInputStream(null);
		}
	}

	/**
	 * Check whether the string is parsable as a number.
	 * 
	 * @param target
	 *            The string to check.
	 * @return True if the string does not represent a number, false if it does.
	 */
	private static boolean isNotNumeric(String target) {
		NumberFormat formatter = NumberFormat.getInstance();
		ParsePosition pos = new ParsePosition(0);
		formatter.parse(target, pos);
		return target.length() != pos.getIndex();
	}

	/**
	 * Read the provided strings into the grid
	 * 
	 * @param grid
	 *            The grid to have data loaded into it.
	 * @param input
	 *            The array of strings containing the text data to be loaded.
	 * @param specifiedDelimiter
	 *            The delimiter that separates columns within a row. If not
	 *            specified, it will default to " ".
	 */
	private void readCSVGrid(CSVGrid grid, String[] input,
			String specifiedDelimiter) {

		// The delimiter character separating columns within a row.
		String delimiter = " ";

		if (specifiedDelimiter != null) {
			delimiter = specifiedDelimiter;
		}

		String firstRow[] = input[0].split(delimiter);

		// Whether the first value in the first row is a name instead of a
		// number
		boolean firstValueName = false;

		if (isNotNumeric(firstRow[0])) {
			firstValueName = true;
		}

		// Number of names found in the first row
		int nameCount = 0;

		// Count the number of names in the first row
		for (String header : firstRow) {
			if (isNotNumeric(header)) {
				nameCount++;
			}
		}

		// Whether there are names for the columns or rows.
		boolean columnNames = false;
		boolean rowNames = false;

		// If there are multiple names in the first row, there are column names
		if (nameCount > 1) {
			columnNames = true;
		} else if (nameCount == 1) {

			// If there is only one column, and its top value is a name, only
			// that column has a name
			if (firstRow.length == 1) {
				columnNames = true;
			}

			// If only the first value is a name, then names belong only to rows
			else if (firstValueName && firstRow.length != 1) {
				columnNames = false;
				rowNames = true;
			}
		}

		// Check whether rows have names
		if (!rowNames) {

			// Count the number of names in the fist column
			nameCount = 0;
			for (String row : input) {
				
				if (isNotNumeric(row.split(delimiter)[0])) {
					nameCount++;
				}
			}

			// If there are multiple rows with names, or one row with a name and
			// it isn't a column name, or the only row has a name, then rows
			// have names
			if (nameCount > 1 || (nameCount == 1
					&& (!columnNames || input.length == 1))) {
				rowNames = true;
			}
		}

		// Whether we have yet to create the grid columns.
		boolean noColumns = true;

		for (int i = 0; i < input.length; i++) {
			String row[] = input[i].split(delimiter);

			// If there are column names, set them to the grid
			if (i == 0 && columnNames) {

				for (int j = 0; j < row.length; j++) {

					// Doon't add the empty column name for the column of row
					// names
					if (j > 0 || !rowNames) {
						grid.getColumnNames().add(row[j]);
					}
				}

				continue;
			}

			// The row being added
			ArrayList<Double> newRow = new ArrayList<Double>();

			// If this is the first row of numbers, columns have yet to be
			// created
			if (noColumns) {

				// Add each value in the row
				for (int j = 0; j < row.length; j++) {

					// If there are no names for rows, or this is not the first
					// column, add the value
					if (!rowNames || j > 0) {

						// Check to see if value is numeric
						if (isNotNumeric(row[j])) {
							row[j] = Double.toString(NaNReplacementValue);
						}


						// Add the value to the row
						double tokenValue = Double.parseDouble(row[j]);
						newRow.add(tokenValue);

						// Add the value to a new column
						ArrayList<Double> newColumn = new ArrayList<Double>();
						newColumn.add(tokenValue);
						grid.getColumns().add(newColumn);
					} else {

						// If this is the first column of a named row, add the
						// name to the list of row names
						grid.getRowNames().add(row[0]);
					}
				}

				grid.getRows().add(newRow);

				noColumns = false;
				continue;
			}

			// If checks above failed, this is a regular row, so add its values
			// and name
			for (int j = 0; j < row.length; j++) {

				// If there are no names for rows, or this is not the first
				// column, add the value
				if (!rowNames || j > 0) {
					
					// Check to see if value is numeric
					if (isNotNumeric(row[j])) {
						row[j] = Double.toString(NaNReplacementValue);
					}


					// Add the value to the row and column

					double tokenValue = Double.parseDouble(row[j]);
					newRow.add(tokenValue);

					// If there are row names, the first column is for names and
					// so not included as a column in the data
					int columnIndex = rowNames ? j - 1 : j;
					grid.getColumns().get(columnIndex).add(tokenValue);
				} else {

					// If this is the first column of a named row, add the name
					// to the list of row names
					grid.getRowNames().add(row[0]);
				}
			}

			grid.getRows().add(newRow);
		}
	}
}
