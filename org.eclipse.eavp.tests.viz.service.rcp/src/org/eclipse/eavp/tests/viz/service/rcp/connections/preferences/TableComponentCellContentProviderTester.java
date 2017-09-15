/*******************************************************************************
 * Copyright (c) 2016 UT-Battelle, LLC.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   Robert Smith
 *******************************************************************************/
package org.eclipse.eavp.tests.viz.service.rcp.connections.preferences;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.eclipse.eavp.viz.service.rcp.connections.preferences.TableComponentCellContentProvider;
import org.junit.Test;

/**
 * A class to test to functionality of the TableComponentCellContentProvider.
 * 
 * @author Robert Smith
 *
 */
public class TableComponentCellContentProviderTester {

	/**
	 * Check that the provider gets its values from the correct place in the
	 * list, corresponding to its index.
	 */
	@Test
	public void checkValues() {

		// Create a row of entries
		ArrayList<TestEntry> row = new ArrayList<TestEntry>();
		row.add(new TestEntry("0"));
		row.add(new TestEntry("1"));

		// Create two providers, set for different columns
		TableComponentCellContentProvider provider0 = new TableComponentCellContentProvider(
				new TestVizCellContentProvider(), 0);
		TableComponentCellContentProvider provider1 = new TableComponentCellContentProvider(
				new TestVizCellContentProvider(), 1);

		// Check that the providers are getting their text correctly
		assertEquals("text: off0", provider0.getText(row));
		assertEquals("text: off1", provider1.getText(row));

		// Check that the providers are getting their tooltip text correctly
		assertEquals("tooltip0", provider0.getToolTipText(row));
		assertEquals("tooltip1", provider1.getToolTipText(row));

		// Check that the providers are getting their values correctly
		assertEquals("off0", provider0.getValue(row));
		assertEquals("off1", provider1.getValue(row));

		// Check that the providers are properly testing for enabled/valid
		// status
		assertTrue(provider0.isEnabled(row));
		assertTrue(provider0.isValid(row));

		// Set the provider to a new value and check that the change was
		// recorded
		provider0.setValue(row, "on0");
		assertEquals("on0", provider0.getValue(row));
	}
}
