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

import java.util.ArrayList;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.PlatformUI;

/**
 * A class that creates the TestConnectionPlotComposite objects for testing
 * purposes.
 * 
 * @author Robert Smith
 *
 */
public class TestConnectionPlotCompositeFactory {

	static public TestConnectionPlotComposite createComposite() {

		// A list to hold the composite.
		final ArrayList<TestConnectionPlotComposite> list = new ArrayList<TestConnectionPlotComposite>();

		// Create a new ConnectionPlotComposite on the UI thread.
		PlatformUI.getWorkbench().getDisplay().syncExec(new Runnable() {
			@Override
			public void run() {

				TestConnectionPlotComposite composite = new TestConnectionPlotComposite(
						new Composite(new Shell(Display.getDefault()), 0), 0) {

					@Override
					protected String getConnectionPreferencePageID() {
						return "";
					}

				};

				// Add the composite to the list
				list.add(composite);

			}
		});

		// Return the composite saved to the list.
		return list.get(0);
	}

}
