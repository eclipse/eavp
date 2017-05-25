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
package org.eclipse.eavp.viz.service.paraview.connections;

import org.eclipse.eavp.viz.service.connections.VizConnection;
import org.eclipse.eavp.viz.service.connections.VizConnectionManager;
import org.eclipse.eavp.viz.service.connections.preferences.IVizConnectionPreferences;
import org.eclipse.eavp.viz.service.paraview.web.IParaViewWebClient;

/**
 * A VizConnectionManager that connects the preferences from the ParaView
 * preference page to the concrete ParaViewConnections.
 * 
 * @author Robert Smith
 *
 */
public class ParaViewConnectionManager
		extends VizConnectionManager<IParaViewWebClient> {

	/**
	 * The index for the path to the server script in the row template for the
	 * ParaView preference page.
	 */
	final static int SERVER_PATH_INDEX = 3;

	/**
	 * The index for the port number for the web visualizer in the row template
	 * for the ParaView preference page.
	 */
	final static int VISUALIZER_PORT_INDEX = 4;

	/**
	 * The index for the name of the remote operating system in the row template
	 * for the ParaView preference page.
	 */
	final static int REMOTE_OS_INDEX = 5;

	/**
	 * The index for the version number of the remote ParaView installation in
	 * the row template for the ParaView preference page.
	 */
	final static int REMOTE_VERSION_NUMBER_INDEX = 6;

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.eavp.viz.service.connections.VizConnectionManager#
	 * createConnection(java.lang.String, java.lang.String)
	 */
	@Override
	protected VizConnection<IParaViewWebClient> createConnection() {

		// Create the connection
		ParaViewConnection connection = new ParaViewConnection();
		return connection;
	}

	@Override
	protected IVizConnectionPreferences createPreferences(
			String serialPreferences) {
		return new ParaViewVizConnectionPreferences(serialPreferences);
	}

}
