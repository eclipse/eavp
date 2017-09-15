/*******************************************************************************
 * Copyright (c) 2017 UT-Battelle, LLC.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   Robert Smith
 *   
 *******************************************************************************/
package org.eclipse.eavp.tests.viz.service.rcp.connections;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.eclipse.eavp.viz.service.rcp.connection.RCPConnection;
import org.junit.Test;

/**
 * A class to test the functionality of the RCPConnectionPlot.
 * 
 * @author Robert Smith
 *
 */
public class RCPConnectionPlotTester {

	/**
	 * Test that a connection sent to the PlotComposite when set.
	 */
	@Test
	public void checkConnection() {

		// Get a composite
		TestConnectionPlotComposite composite = TestConnectionPlotCompositeFactory
				.createComposite();

		// Create a plot from the composite
		TestRCPConnectionPlot plot = new TestRCPConnectionPlot(composite);

		// Create and set a connection to the plot
		RCPConnection connection = new RCPConnection() {

			@Override
			protected Object connectToWidget() {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			protected boolean disconnectFromWidget(Object widget) {
				// TODO Auto-generated method stub
				return false;
			}

		};

		try {
			plot.setConnection(connection);
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}

		// Check that the composite got the connection
		assertTrue(connection == composite.getConnection());

	}
}
