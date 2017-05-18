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
package org.eclipse.eavp.viz.service.visit.connections;

import org.eclipse.eavp.viz.service.connections.preferences.RCPVizConnectionPreferences;

/**
 * A class containing the custom preferences for a VisIt connection page.
 * 
 * @author Robert Smith
 *
 */
public class VisItVizConnectionPreferences extends RCPVizConnectionPreferences {

	/**
	 * There is one preference added for VisIt applications.
	 */
	private static final int numVisItPrefs = 3;

	/**
	 * The path to the VisIt executable.
	 */
	private String executablePath;

	/**
	 * The proxy that will be used for the connection.
	 */
	private String proxy;

	/**
	 * The port for the connection to the proxy.
	 */
	private int proxyPort;

	/**
	 * The default constructor.
	 */
	public VisItVizConnectionPreferences() {
		super();
		executablePath = "";
		proxy = "";
		proxyPort = 0;
	}

	/**
	 * The constructor for building a preferences set from a string
	 * representation, as from serialize()
	 * 
	 * @param data
	 *            The serialized representation of the preferences
	 */
	public VisItVizConnectionPreferences(String data) {
		super(data);

		// The index of the start of this subclass's preferences in the string
		int start = super.getNumPreferences();

		// Split the data into tokens for the various data fields
		String[] tokens = data.split(",", -1);

		// If the string was not formatted correctly, use the default values
		if (tokens.length < getNumPreferences()) {
			executablePath = "";
			proxy = "";
			proxyPort = 0;
		} else {

			// Place the data into the class variables
			executablePath = tokens[start];
			proxy = tokens[start + 1];

			try {
				proxyPort = Integer.parseInt(tokens[start + 2]);
			} catch (NumberFormatException e) {

				// Use the default value if the proxyPort is not a number
				proxyPort = 0;
			}
		}
	}

	/**
	 * Getter method for the executable path.
	 * 
	 * @return The executable path.
	 */
	public String getExecutablePath() {
		return executablePath;
	}

	/**
	 * Getter method for the proxy.
	 * 
	 * @return The proxy, or an empty string if no proxy is to be used
	 */
	public String getProxy() {
		return proxy;
	}

	/**
	 * Getter method for the proxy port number.
	 * 
	 * @return The port to be used for the proxy connection.
	 */
	public int getProxyPort() {
		return proxyPort;
	}

	/**
	 * Setter method for the executable path.
	 * 
	 * @param executablePath
	 *            The new executable path
	 */
	public void setExecutablePath(String executablePath) {
		this.executablePath = executablePath;
	}

	/**
	 * Setter method for the proxy.
	 * 
	 * @param proxy
	 *            The new proxy, or an empty string to set a direct connection
	 *            with no proxy.
	 */
	public void setProxy(String proxy) {
		this.proxy = proxy;
	}

	/**
	 * Setter method for the proxy port number.
	 * 
	 * @param proxyPort
	 *            The new port number for the proxy connection.
	 */
	public void setProxyPort(int proxyPort) {
		this.proxyPort = proxyPort;
	}

	/**
	 * Get the number of different preferences values held by this class.
	 */
	@Override
	protected int getNumPreferences() {
		return super.getNumPreferences() + numVisItPrefs;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.eavp.viz.service.connections.preferences.
	 * AbstractVizConnectionPreferences#serialize(java.lang.String)
	 */
	@Override
	public String serialize() {
		return super.serialize() + "," + executablePath + "," + proxy + ","
				+ proxyPort;
	}
}
