/*******************************************************************************
 * Copyright (c) 2015 UT-Battelle, LLC.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   Jordan Deyton, Robert Smith - Initial API and implementation and/or initial documentation
 *******************************************************************************/
package org.eclipse.eavp.tests.viz.service.connections;

import java.util.HashSet;
import java.util.Set;

import org.eclipse.eavp.viz.modeling.factory.IControllerProviderFactory;
import org.eclipse.eavp.viz.service.IRenderElementHolder;
import org.eclipse.eavp.viz.service.IVizCanvas;
import org.eclipse.eavp.viz.service.connections.ConnectionPlot;
import org.eclipse.eavp.viz.service.connections.ConnectionVizService;
import org.eclipse.eavp.viz.service.connections.IVizConnectionManager;
import org.eclipse.eavp.viz.service.connections.VizConnection;
import org.eclipse.eavp.viz.service.connections.VizConnectionManager;
import org.eclipse.january.geometry.Geometry;

/**
 * A simple ConnectionVizService implementation for testing purposes.
 * 
 * @author Jordan Deyton, Robert Smith
 *
 */
public class FakeConnectionVizService extends ConnectionVizService<FakeClient> {

	/**
	 * Allow the explicit addition of new connections by delegating to the
	 * connection manager
	 * 
	 * @param name
	 *            The new connection name
	 */
	public void addConnection(String name, String preferences) {

		// Delegate to the fake connection manager
		manager.addConnection(name, preferences);
	}

	@Override
	public String getName() {
		return "Fake Connection Viz Service";
	}

	@Override
	public String getVersion() {
		return "0.0";
	}

	@Override
	protected IVizConnectionManager<FakeClient> createConnectionManager() {
		return new VizConnectionManager<FakeClient>() {
			@Override
			protected VizConnection<FakeClient> createConnection(String name,
					String preferences) {
				return new FakeVizConnection();
			}
		};
	}

	@Override
	protected ConnectionPlot<FakeClient> createConnectionPlot() {
		return new ConnectionPlot<FakeClient>() {

			@Override
			public IRenderElementHolder getRenderElementHolder(
					Geometry geometry) {
				return null;
			}
		};
	}

	@Override
	protected String getConnectionPreferencesNodeId() {
		return "org.eclipse.eavp.viz.service.connections.test";
	}

	@Override
	protected Set<String> findSupportedExtensions() {
		Set<String> extensions = new HashSet<String>();
		extensions.add("csv");
		return extensions;
	}

	@Override
	public IControllerProviderFactory getControllerProviderFactory() {
		return null;
	}

	@Override
	public IVizCanvas createCanvas(Geometry geometry) throws Exception {
		return null;
	}
}
