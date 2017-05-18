/*******************************************************************************
 * Copyright (c) 2017 UT-Battelle, LLC.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   Initial API and implementation and/or initial documentation - Robert Smith
 *******************************************************************************/
package org.eclipse.eavp.viz.service.connections.preferences;

/**
 * The base abstract implementation for IVizConnectionPreferences
 * 
 * @author Robert Smith
 *
 */
abstract public class AbstractVizConnectionPreferences
		implements IVizConnectionPreferences {

	/**
	 * There are three preferences held in this class.
	 */
	private static final int numAbstractPrefs = 3;

	/**
	 * The name of the remote connection (or "localhost") which will be used for
	 * some of the preferences.
	 */
	private String hostName;

	/**
	 * The connection's name. This should be unique among
	 * VizConnectionPreferences for the same IVizService.
	 */
	private String name;

	/**
	 * The port number for the connection
	 */
	private int port;

	/**
	 * The username used when connecting.
	 */
	private String username;

	/**
	 * The default constructor.
	 */
	public AbstractVizConnectionPreferences() {
		hostName = "localhost";
		name = "";
		port = 0;
	}

	/**
	 * A constructor for loading preferences from a string, using the same
	 * format as those created by serialize().
	 * 
	 * @param data
	 *            The string representation of an
	 *            AbstractVizConnectionPreferences to load into the new object.
	 */
	public AbstractVizConnectionPreferences(String data) {

		// Split the string by the delimiter to separate the data fields
		String[] tokens = data.split(",", -1);

		// If string wasn't properly formatted, use the default values instead
		if (tokens.length < 3) {
			hostName = "localhost";
			name = "";
			port = 0;
		} else {

			// Apply the data to the fields in alphabetic order
			hostName = tokens[0];
			name = tokens[1];

			try {
				port = Integer.parseInt(tokens[2]);
			} catch (NumberFormatException e) {

				// If port was not a number, use the default
				port = 0;
			}
		}
	}

	/**
	 * Get the number of different preferences values held by this class.
	 */
	protected int getNumPreferences() {
		return numAbstractPrefs;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.eavp.viz.service.connections.preferences.
	 * IVizConnectionPreferences#getConnectionName()
	 */
	@Override
	public String getHostName() {
		return hostName;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.eavp.viz.service.connections.preferences.
	 * IVizConnectionPreferences#getName()
	 */
	@Override
	public String getName() {
		return name;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.eavp.viz.service.connections.preferences.
	 * IVizConnectionPreferences#getPort()
	 */
	@Override
	public int getPort() {
		return port;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.eavp.viz.service.connections.preferences.
	 * IVizConnectionPreferences#getUsername()
	 */
	@Override
	public String getUsername() {
		return username;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.eavp.viz.service.connections.preferences.
	 * IVizConnectionPreferences#serialize()
	 */
	@Override
	public String serialize() {
		return hostName + ',' + name + ',' + port;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.eavp.viz.service.connections.preferences.
	 * IVizConnectionPreferences#setConnectionName(java.lang.String)
	 */
	@Override
	public void setHostName(String connectionName) {
		this.hostName = connectionName;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.eavp.viz.service.connections.preferences.
	 * IVizConnectionPreferences#setName(java.lang.String)
	 */
	@Override
	public void setName(String name) {
		this.name = name;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.eavp.viz.service.connections.preferences.
	 * IVizConnectionPreferences#setPort(int)
	 */
	@Override
	public void setPort(int port) {
		this.port = port;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.eavp.viz.service.connections.preferences.
	 * IVizConnectionPreferences#setUsername(java.lang.String)
	 */
	@Override
	public void setUsername(String username) {
		this.username = username;
	}
}
