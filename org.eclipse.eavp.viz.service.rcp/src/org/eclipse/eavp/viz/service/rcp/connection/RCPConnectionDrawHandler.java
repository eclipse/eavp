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
import org.eclipse.eavp.viz.service.connections.ConnectionPlot;
import org.eclipse.eavp.viz.service.connections.IVizConnection;
import org.eclipse.eavp.viz.service.drawhandler.AbstractDrawHandler;
import org.eclipse.swt.SWT;
import org.eclipse.swt.SWTException;
import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.PlatformUI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * An implementation of IDrawHandler that sets up a connection in RCP
 * environments for ConnectionPlots.
 * 
 * @author Robert Smith
 *
 * @param <T>
 *            The subclass of IVizConnection used to manage to connection to the
 *            remote service.
 */
abstract public class RCPConnectionDrawHandler<T>
		extends AbstractDrawHandler<Composite> {

	/**
	 * Logger for handling event messages and other information.
	 */
	private static final Logger logger = LoggerFactory
			.getLogger(RCPConnectionDrawHandler.class);

	/**
	 * The last canvas drawn.
	 */
	private IVizCanvas lastCanvas;

	/**
	 * The PlotComposite which manages the resulting composite.
	 */
	protected ConnectionPlotComposite plotComposite;

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

		// Don't try to draw an invalid canvas.
		if (canvas != null) {

			// If necessary, create a new plot composite.
			if (canvas != lastCanvas) {

				// Check the parent Composite.
				if (parent == null) {
					throw new SWTException(SWT.ERROR_NULL_ARGUMENT,
							"IPlot error: "
									+ "Cannot draw plot in a null Composite.");
				} else if (parent.isDisposed()) {
					throw new SWTException(SWT.ERROR_WIDGET_DISPOSED,
							"IPlot error: "
									+ "Cannot draw plot in a disposed Composite.");
				}

				ConnectionPlot castCanvas = (ConnectionPlot) canvas;

				// Create a plot composite.
				plotComposite = createPlotComposite(parent);
				plotComposite.setConnectionPlot(castCanvas);
				try {
					plotComposite.setConnection(castCanvas.getConnection());
				} catch (Exception e1) {
					logger.error(
							"RCPConnectionDrawHandler Error while setting connection to Plot Composite: "
									+ e1.toString());
				}
				plotComposite.refresh();

				// Create a new DisposeListener on the UI thread.
				PlatformUI.getWorkbench().getDisplay().syncExec(new Runnable() {
					@Override
					public void run() {

						// Its reference should be unset when disposed.
						plotComposite.addDisposeListener(new DisposeListener() {
							@Override
							public void widgetDisposed(DisposeEvent e) {
								plotComposite = null;
							}
						});
					}
				});

			}
			// Otherwise, ignore the parent argument and trigger a normal
			// refresh.
			else {
				plotComposite.refresh();
			}
		}

		result = plotComposite;
	}
}
