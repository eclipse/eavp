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
		proxy = "";
		proxyPort = 0;
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
