/*******************************************************************************
 * Copyright (c) 2017 UT-Battelle, LLC.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   Robert Smith - Initial API and implementation and/or initial documentation
 *   
 *******************************************************************************/
package org.eclipse.eavp.viz.service.paraview.connections;

import org.eclipse.eavp.viz.service.connections.preferences.RCPVizConnectionPreferences;

/**
 * A preferences set which keeps track of ParaView specific connection
 * preferences, including the executable path, server script path and visualizer
 * port.
 * 
 * @author Robert Smith
 *
 */
public class ParaViewVizConnectionPreferences
		extends RCPVizConnectionPreferences {

	final static private int numParaViewPrefs = 3;

	/**
	 * The path to the ParaView executable.
	 */
	private String executablePath;

	/**
	 * The path to the http_pvw_server.py file
	 */
	private String scriptPath;

	/**
	 * The port number to be used by the web visualizer
	 */
	private int visualizerPort;

	/**
	 * The nullary constructor.
	 */
	public ParaViewVizConnectionPreferences() {
		super();
		executablePath = "";
		scriptPath = "";
		visualizerPort = 1;
	}

	/**
	 * The constructor for creating a preferences set from a string
	 * representation, as from .serialize().
	 * 
	 * @param data
	 *            The string representation of the preferences, in alphabetical
	 *            order for each IVizConnectionPreferences class in the
	 *            hierarchy, separated by commas.
	 */
	public ParaViewVizConnectionPreferences(String data) {
		super(data);

		// Split the data into tokens for the various data fields
		String[] tokens = data.split(",", -1);

		// If the string was not formatted correctly, use the default values
		if (tokens.length < getNumPreferences()) {
			executablePath = "";
			scriptPath = "";
			visualizerPort = 1;
		} else {

			// Get the starting point for this class's preferences in the string
			int start = super.getNumPreferences();

			// Place the data into the class variables
			executablePath = tokens[start];
			scriptPath = tokens[start + 1];
			visualizerPort = Integer.parseInt(tokens[start + 2]);

		}
	}

	/**
	 * Getter method for the executable path.
	 * 
	 * @return The path to the ParaView executable.
	 */
	public String getExecutablePath() {
		return executablePath;
	}

	/**
	 * Getter method for the script path.
	 * 
	 * @return The path to the server script
	 */
	public String getScriptPath() {
		return scriptPath;
	}

	/**
	 * Getter method for the visualizer port
	 * 
	 * @return The port on which the ParaView web visualizer will be opened.
	 */
	public int getVisualizerPort() {
		return visualizerPort;
	}

	/**
	 * Setter method for the executable path.
	 * 
	 * @param executablePath
	 *            The new path to the ParaView executable.
	 */
	public void setExecutablePath(String executablePath) {
		this.executablePath = executablePath;
	}

	/**
	 * Setter method for the script path.
	 * 
	 * @param scriptPath
	 *            The new path to the server script.
	 */
	public void setScriptPath(String scriptPath) {
		this.scriptPath = scriptPath;
	}

	/**
	 * Setter method for the visualizer port.
	 * 
	 * @param visualizerPort
	 *            The new port on which the ParaView web visualizer should be
	 *            opened.
	 */
	public void setVisualizerPort(int visualizerPort) {
		this.visualizerPort = visualizerPort;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.eavp.viz.service.connections.preferences.
	 * RCPVizConnectionPreferences#getNumPreferences()
	 */
	@Override
	protected int getNumPreferences() {
		return super.getNumPreferences() + numParaViewPrefs;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.eavp.viz.service.connections.preferences.
	 * RCPVizConnectionPreferences#serialize()
	 */
	@Override
	public String serialize() {
		return super.serialize() + "," + executablePath + "," + scriptPath + ","
				+ visualizerPort;
	}

}
