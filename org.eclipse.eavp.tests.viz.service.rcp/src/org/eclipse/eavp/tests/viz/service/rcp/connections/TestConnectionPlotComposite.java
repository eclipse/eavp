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

import org.eclipse.eavp.viz.service.connections.IVizConnection;
import org.eclipse.eavp.viz.service.rcp.connection.ConnectionPlotComposite;
import org.eclipse.swt.widgets.Composite;

/**
 * A ConnectionPlotComposite that exposes the underlying connection for testing
 * purposes.
 * 
 * @author Robert Smith
 *
 */
public class TestConnectionPlotComposite
		extends ConnectionPlotComposite<Composite> {

	/**
	 * The default constructor.
	 * 
	 * @param parent
	 *            The parent composite.
	 * @param style
	 *            The SWT style.
	 */
	public TestConnectionPlotComposite(Composite parent, int style) {
		super(parent, style);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.eavp.viz.service.rcp.connection.ConnectionPlotComposite#
	 * getConnection()
	 */
	@Override
	public IVizConnection getConnection() {
		return connection;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.eavp.viz.service.rcp.connection.ConnectionPlotComposite#
	 * getConnectionPreferencePageID()
	 */
	@Override
	protected String getConnectionPreferencePageID() {
		return "";
	}

}
