/*******************************************************************************
 * Copyright (c) 2016 UT-Battelle, LLC.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   Robert Smith
 *   
 *******************************************************************************/
package org.eclipse.eavp.viz.service.connections.preferences.test;

import static org.junit.Assert.assertEquals;

import org.eclipse.eavp.viz.service.connections.preferences.CellColumnLabelProvider;
import org.junit.Test;

/**
 * A class to test the functionality of the CellColumLabelProvider.
 * 
 * @author Robert Smith
 *
 */
public class CellColumnLabelProviderTester {

	/**
	 * Check that the provider correctly delegates to its internal provider.
	 */
	@Test
	public void checkDelegation() {
		CellColumnLabelProvider provider = new CellColumnLabelProvider(
				new TestVizCellContentProvider());

		// Check each of the functions to ensure they are delegated to the
		// TestVizCellContentProvider
		assertEquals("tooltip", provider.getToolTipText(new TestEntry()));
		assertEquals("text: off", provider.getText(new TestEntry()));
	}

}
