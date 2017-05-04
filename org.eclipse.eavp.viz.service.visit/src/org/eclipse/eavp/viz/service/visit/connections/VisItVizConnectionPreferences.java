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

import org.eclipse.eavp.viz.service.connections.preferences.AbstractVizConnectionPreferences;

/**
 * A class containing the custom preferences for a VisIt connection page.
 * 
 * @author Robert Smith
 *
 */
public class VisItVizConnectionPreferences
		extends AbstractVizConnectionPreferences {

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

	public VisItVizConnectionPreferences(String data, String delimiter) {
		super(data, delimiter);

		// Split the data into tokens for the various data fields
		String[] tokens = data.split(delimiter, -1);

		// If the string was not formatted correctly, use the default values
		if (tokens.length < 6) {
			executablePath = "";
			proxy = "";
			proxyPort = 0;
		} else {

			// Place the data into the class variables
			executablePath = tokens[3];
			proxy = tokens[4];

			try {
				proxyPort = Integer.parseInt(tokens[5]);
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.eavp.viz.service.connections.preferences.
	 * AbstractVizConnectionPreferences#serialize(java.lang.String)
	 */
	@Override
	public String serialize(String delimiter) {
		return super.serialize(delimiter) + delimiter + executablePath
				+ delimiter + proxy + delimiter + proxyPort;
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
}
