/*******************************************************************************
 * Copyright (c) 2017 UT-Battelle, LLC.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   Initial API and implementation and/or initial documentation - 
 *   Robert Smith
 *******************************************************************************/
package org.eclipse.eavp.viz.service.rcp.connection;

import org.eclipse.eavp.viz.service.connections.ConnectionPlot;
import org.eclipse.eavp.viz.service.connections.IVizConnection;

/**
 * An extension of ConnectionPlot which uses the RCP specific PlotComposites.
 * 
 * @author Robert Smith
 * 
 * @param <T>
 *            The specific IVizConnection implementation that is used to manage
 *            the connection to the remtoe visualization service.
 */
abstract public class RCPConnectionPlot<T> extends ConnectionPlot<T> {

	/**
	 * The composite in which this plot is rendered.
	 */
	protected ConnectionPlotComposite<T> plotComposite;

	/**
	 * Gets the currently drawn plot composite.
	 * 
	 * @return The current plot composite, or {@code null} if it has not been
	 *         drawn.
	 */
	protected ConnectionPlotComposite<T> getPlotComposite() {
		return plotComposite;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.eavp.viz.service.connections.ConnectionPlot#setConnection(org
	 * .eclipse.eavp.viz.service.connections.IVizConnection)
	 */
	@Override
	public boolean setConnection(IVizConnection<T> connection)
			throws Exception {

		boolean changed = super.setConnection(connection);

		// If the connection changed, set it to the plot composite
		if (changed) {
			// Register with the new connection.
			if (plotComposite != null) {
				plotComposite.setConnection(connection);
			}
		}

		return changed;
	}
}
