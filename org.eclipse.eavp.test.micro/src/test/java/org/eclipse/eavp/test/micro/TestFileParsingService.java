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

import static org.junit.Assert.assertTrue;

import org.eclipse.eavp.micro.main.java.FileParsingService;
import org.junit.Test;

/**
 * A class for testing the FileParsingService
 * 
 * @author Robert Smith
 *
 */
public class TestFileParsingService {
	
	/**
	 * Test the service's ability to read files into json
	 */
	@Test
	public void testPlot() {
		
		//The service to test
		FileParsingService service = new FileParsingService();
		
		//Test reading a csv file's contents
		String json = service.plot("test.csv", "1,2,3\n4,5,6\n7,8,9");
		assertTrue(json.contains("\"columns\":[[1.0,4.0,7.0],[2.0,5.0,8.0],[3.0,6.0,9.0]],\"columnNames\":[\"Column 1\",\"Column 2\",\"Column 3\"],\"rows\":[[1.0,2.0,3.0],[4.0,5.0,6.0],[7.0,8.0,9.0]],\"rowNames\":[\"Row 1\",\"Row 2\",\"Row 3\"]"));
		
		//Test read a non csv files contents
		json = service.plot("test.dat", "1 2 3\n4 5 6\n7 8 9");
		assertTrue(json.contains("\"columns\":[[1.0,4.0,7.0],[2.0,5.0,8.0],[3.0,6.0,9.0]],\"columnNames\":[\"Column 1\",\"Column 2\",\"Column 3\"],\"rows\":[[1.0,2.0,3.0],[4.0,5.0,6.0],[7.0,8.0,9.0]],\"rowNames\":[\"Row 1\",\"Row 2\",\"Row 3\"]"));
		
		//Check that the service can handle NaNs by replacing them with 0
		json = service.plot("test.csv", "1,2,3\n4,5,6\n7,8,NaN");
		assertTrue(json.contains("\"columns\":[[1.0,4.0,7.0],[2.0,5.0,8.0],[3.0,6.0,0.0]],\"columnNames\":[\"Column 1\",\"Column 2\",\"Column 3\"],\"rows\":[[1.0,2.0,3.0],[4.0,5.0,6.0],[7.0,8.0,0.0]],\"rowNames\":[\"Row 1\",\"Row 2\",\"Row 3\"]"));
		
		//Test a file with column names
		json = service.plot("test.csv", "col1,col2,col3\n1,2,3\n4,5,6\n7,8,9");
		assertTrue(json.contains("\"columnNames\":[\"col1\",\"col2\",\"col3\"]"));
		
		//Test a file with row names
		json = service.plot("test.csv", "row1,1,2,3\nrow2,4,5,6\nrow3,7,8,9");
		assertTrue(json.contains("\"rowNames\":[\"row1\",\"row2\",\"row3\"]"));
		
		//Test a file with column and row names
		json = service.plot("test.csv", ",col1,col2,col3\nrow1,1,2,3\nrow2,4,5,6\nrow3,7,8,9");
		assertTrue(json.contains("\"columnNames\":[\"col1\",\"col2\",\"col3\"],\"rows\":[[1.0,2.0,3.0],[4.0,5.0,6.0],[7.0,8.0,9.0]],\"rowNames\":[\"row1\",\"row2\",\"row3\"]"));
	}
}