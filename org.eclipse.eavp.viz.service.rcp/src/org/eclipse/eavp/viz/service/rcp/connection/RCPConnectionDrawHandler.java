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

import org.eclipse.eavp.viz.service.IVizCanvas;
import org.eclipse.eavp.viz.service.connections.ConnectionPlotComposite;
import org.eclipse.eavp.viz.service.connections.IVizConnection;
import org.eclipse.eavp.viz.service.drawhandler.IDrawHandler;
import org.eclipse.swt.SWT;
import org.eclipse.swt.SWTException;
import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.widgets.Composite;

/**
 * An implementation of IDrawHandler that sets up a connection in RCP
 * environments for ConnectionPlots.
 * 
 * @author Robert Smith
 *
 */
abstract public class RCPConnectionDrawHandler
		implements IDrawHandler<Composite> {

	/**
	 * Creates a composite that renders plot content using an
	 * {@link IVizConnection}.
	 * 
	 * @param parent
	 *            The parent composite.
	 * @return A new, basic connection plot composite.
	 */
	protected abstract ConnectionPlotComposite<Composite> createPlotComposite(
			Composite parent);

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.eavp.viz.service.drawhandler.IDrawHandler#draw(org.eclipse.
	 * eavp.viz.service.IVizCanvas)
	 */
	@Override
	public void draw(IVizCanvas canvas) {

		// If necessary, create a new plot composite.
		if (plotComposite == null) {
			// Check the parent Composite.
			if (parent == null) {
				throw new SWTException(SWT.ERROR_NULL_ARGUMENT, "IPlot error: "
						+ "Cannot draw plot in a null Composite.");
			} else if (parent.isDisposed()) {
				throw new SWTException(SWT.ERROR_WIDGET_DISPOSED,
						"IPlot error: "
								+ "Cannot draw plot in a disposed Composite.");
			}

			// Create a plot composite.
			plotComposite = createPlotComposite(parent);
			plotComposite.setConnectionPlot(this);
			plotComposite.setConnection(connection);
			plotComposite.refresh();
			// Its reference should be unset when disposed.
			plotComposite.addDisposeListener(new DisposeListener() {
				@Override
				public void widgetDisposed(DisposeEvent e) {
					plotComposite = null;
				}
			});
		}
		// Otherwise, ignore the parent argument and trigger a normal refresh.
		else {
			plotComposite.refresh();
		}

		return plotComposite;
	}
}
