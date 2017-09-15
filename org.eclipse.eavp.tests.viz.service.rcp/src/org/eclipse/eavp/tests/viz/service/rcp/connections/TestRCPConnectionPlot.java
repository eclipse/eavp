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

import org.eclipse.eavp.viz.service.rcp.connection.ConnectionPlotComposite;
import org.eclipse.eavp.viz.service.rcp.connection.RCPConnectionPlot;
import org.eclipse.swt.widgets.Composite;

/**
 * An RCPConnectionPlot implementation which allows programmatic setting of the
 * PlotComposite for testing purposes.
 * 
 * @author Robert Smith
 *
 */
public class TestRCPConnectionPlot extends RCPConnectionPlot<Composite> {

	/**
	 * The default constructor.
	 * 
	 * @param composite
	 *            The composite for the plot.
	 */
	public TestRCPConnectionPlot(ConnectionPlotComposite<Composite> composite) {
		super();
		plotComposite = composite;
	}
}
