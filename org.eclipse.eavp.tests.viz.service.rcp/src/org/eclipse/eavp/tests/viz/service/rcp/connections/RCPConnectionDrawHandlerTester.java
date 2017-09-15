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

import org.eclipse.eavp.viz.service.VizProgressMonitor;
import org.eclipse.eavp.viz.service.connections.ConnectionPlot;
import org.eclipse.eavp.viz.service.rcp.connection.ConnectionPlotComposite;
import org.eclipse.eavp.viz.service.rcp.connection.RCPConnectionDrawHandler;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.PlatformUI;
import org.junit.Test;

/**
 * A class for testing the functionality of the RCPConectionDrawHandler.
 * 
 * @author Robert Smith
 *
 */
public class RCPConnectionDrawHandlerTester {

	/**
	 * Check that the handler creates a composite correctly.
	 */
	@Test
	public void checkDraw() {

		// Create a new draw handler that simply creates a new
		// ConnectionPlotComposite
		final RCPConnectionDrawHandler<String> handler = new RCPConnectionDrawHandler<String>() {

			@Override
			public boolean save(VizProgressMonitor monitor, boolean saveAs) {
				// TODO Auto-generated method stub
				return false;
			}

			@Override
			protected ConnectionPlotComposite<Composite> createPlotComposite(
					Composite parent) {

				return TestConnectionPlotCompositeFactory.createComposite();

			}
		};

		// On the UI thread, give the handler a parent.
		PlatformUI.getWorkbench().getDisplay().syncExec(new Runnable() {
			@Override
			public void run() {
				handler.setParent(
						new Composite(new Shell(Display.getDefault()), 0));
			}
		});

		// Try to draw a ConnectionPlot
		handler.draw(new ConnectionPlot<Composite>() {

		});

		// A composite should have been created
		assertTrue(handler.getResult() instanceof ConnectionPlotComposite);
	}
}
