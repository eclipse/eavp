/*******************************************************************************
 * Copyright (c) 2015 UT-Battelle, LLC.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   Jordan Deyton - Initial API and implementation and/or initial documentation
 *   
 *******************************************************************************/
package org.eclipse.eavp.viz.service.paraview.connections;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Locale;
import java.util.concurrent.ExecutionException;

import org.eclipse.eavp.viz.service.connections.IVizConnection;
import org.eclipse.eavp.viz.service.connections.VizConnection;
import org.eclipse.eavp.viz.service.paraview.web.HttpParaViewWebClient;
import org.eclipse.eavp.viz.service.paraview.web.IParaViewWebClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Provides an {@link IVizConnection} for connecting to
 * {@link IParaViewWebClient}s. This connection specifically uses the
 * {@link HttpParaViewWebClient} implementation for the web client.
 * 
 * @author Jordan Deyton
 *
 */
public class ParaViewConnection extends VizConnection<IParaViewWebClient> {

	/**
	 * Logger for handling event messages and other information.
	 */
	private static final Logger logger = LoggerFactory
			.getLogger(ParaViewConnection.class);

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.eavp.viz.service.connections.VizConnection#connectToWidget()
	 */
	@Override
	protected IParaViewWebClient connectToWidget() {

		// Set the default return value.
		IParaViewWebClient client = null;

		// The OS specific string that describes the path to ParaView the base
		// ParaView directory given the ParaView installation path.
		String osPath = "";

		// Check the operating system and set the path accordingly.
		// TODO Add support for windows here
		String OS = System.getProperty("os.name", "generic")
				.toLowerCase(Locale.ENGLISH);
		if ((OS.indexOf("mac") >= 0) || (OS.indexOf("darwin") >= 0)) {

			// For Mac, go inside the application's contents
			osPath = "/paraview.app/Contents";
		}

		// Get the properties for the paraview python command line
		String path = getPath();
		String host = getHost();
		String port = Integer.toString(getPort());
		String serverPath = getProperty("serverPath");

		// The system delimiter for directories
		String delimiter = System.getProperty("file.separator");

		// If the path ends with the delimiter, remove it
		if (path.endsWith(delimiter)) {
			path = path.substring(0, path.length() - 1);
		}

		// If the server path ends with the delimiter, remove it
		if (serverPath.endsWith(delimiter)) {
			serverPath = serverPath.substring(0, path.length() - 1);
		}

		// Run the process that will launch the http server in ParaView Python
		try {			
			Process process = Runtime.getRuntime().exec(path + osPath
					+ "/bin/pvpython " + getProperty("serverPath")
					+ "/http_pvw_server.py --host " + host + " --port " + port);
		} catch (IOException e1) {
			logger.error("Failed to execute ParaView process.");
		}

		
		// Try to create and connect to a ParaView web client.
		boolean connected = false;
		try {
			
			
			// Create an HTTP implementation of the ParaView web client..
			client = new HttpParaViewWebClient();
			// Set up the HTTP URL
			String url = "http://" + host + ":" + port + "/rpc-http/";
			
			//while(!connected){
				System.out.println("Here");
			// Try to connect.
			connected = client.connect(url).get();
			//}
			
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}

		// If the connection was not successful, we should return null.
		if (!connected) {
			client = null;
		}

		return client;
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.eavp.viz.service.connections.VizConnection#
	 * disconnectFromWidget(java.lang.Object)
	 */
	@Override
	protected boolean disconnectFromWidget(IParaViewWebClient widget) {
		boolean closed = false;
		// Attempt to disconnect, returning the success of the operation.
		if (widget != null) {
			try {
				closed = widget.disconnect().get();
			} catch (InterruptedException | ExecutionException e) {
				e.printStackTrace();
			}
		}
		return closed;
	}

}
