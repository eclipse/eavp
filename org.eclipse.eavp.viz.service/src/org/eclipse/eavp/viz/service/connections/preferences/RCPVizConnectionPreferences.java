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

import java.util.List;

import org.eclipse.remote.core.IRemoteConnection;
import org.eclipse.remote.core.IRemoteConnectionType;
import org.eclipse.remote.core.IRemoteServicesManager;
import org.osgi.framework.BundleContext;
import org.osgi.framework.FrameworkUtil;
import org.osgi.framework.ServiceReference;

/**
 * An Eclipse RCP app specific extension of AbstractVizConnectionPreferences for
 * connections that draw from the Parallel Tools Platform's connections for
 * connection preferences.
 * 
 * @author Robert Smith
 *
 */
public abstract class RCPVizConnectionPreferences
		extends AbstractVizConnectionPreferences {

	/**
	 * There is one preference added for RCP applications.
	 */
	private static final int numRCPPrefs = 1;

	/**
	 * The name of the PTP connection used to obtain some of this preference
	 * set's preferences. It may also be "localhost" to specify default local
	 * connection.
	 */
	private String connectionName;

	/**
	 * The default constructor
	 */
	public RCPVizConnectionPreferences() {
		super();
		connectionName = "localhost";
	}

	/**
	 * The constructor for building a preferences set from a string
	 * representation, as from serialize()
	 * 
	 * @param data
	 *            The serialized representation of the preferences
	 */
	public RCPVizConnectionPreferences(String data) {
		super(data);

		// Split the data into tokens for the various data fields
		String[] tokens = data.split(",", -1);

		// If the string was not formatted correctly, use the default values
		if (tokens.length < getNumPreferences()) {
			connectionName = "";
		} else {

			// Place the data into the class variables
			connectionName = tokens[super.getNumPreferences()];
		}
	}

	/**
	 * Getter method for name of the remote connection from which some
	 * preferences are drawn.
	 * 
	 * @return
	 */
	public String getConnectionName() {
		return connectionName;
	}

	/**
	 * Setter method for the name of the remote connection from which some
	 * preferences are drawn. The connection name should be either localhost or
	 * the name of a PTP remote connection.
	 * 
	 * @param connectionName
	 *            the new connection name
	 */
	public void setConnectionName(String connectionName) {
		this.connectionName = connectionName;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.eavp.viz.service.connections.preferences.
	 * AbstractVizConnectionPreferences#getHostName()
	 */
	@Override
	public String getHostName() {

		// If the connection can't be found, return an empty hostname
		String hostName = "";

		BundleContext context = FrameworkUtil.getBundle(getClass())
				.getBundleContext();

		// Get the remote services manager
		if (context != null) {
			ServiceReference<IRemoteServicesManager> ref = context
					.getServiceReference(IRemoteServicesManager.class);

			List<IRemoteConnectionType> types = context.getService(ref)
					.getRemoteConnectionTypes();

			// Find the SSH connections
			for (IRemoteConnectionType type : types) {
				if ("SSH".equals(type.getName())) {

					// Search the names of all current connections for the one
					// chosen by this preferences set
					for (IRemoteConnection remoteConnection : type
							.getConnections()) {
						if (remoteConnection.getName().equals(connectionName)) {

							// Get the address attribute from the working
							// connection
							hostName = remoteConnection.getWorkingCopy()
									.getAttribute("ADDRESS_ATTR");
						}
					}
				}
			}
		}

		return hostName;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.eavp.viz.service.connections.preferences.
	 * AbstractVizConnectionPreferences#getUsername()
	 */
	@Override
	public String getUsername() {

		// If the connection can't be found, return an empty hostname
		String username = "";

		BundleContext context = FrameworkUtil.getBundle(getClass())
				.getBundleContext();

		// Get the remote services manager
		if (context != null) {
			ServiceReference<IRemoteServicesManager> ref = context
					.getServiceReference(IRemoteServicesManager.class);

			List<IRemoteConnectionType> types = context.getService(ref)
					.getRemoteConnectionTypes();

			// Find the SSH connections
			for (IRemoteConnectionType type : types) {
				if ("SSH".equals(type.getName())) {

					// Search the names of all current connections for the one
					// chosen by this preferences set
					for (IRemoteConnection remoteConnection : type
							.getConnections()) {
						if (remoteConnection.getName().equals(connectionName)) {

							// Get the username attribute from the working
							// connection
							username = remoteConnection.getWorkingCopy()
									.getAttribute("USERNAME_ATTR");
						}
					}
				}
			}
		}

		return username;
	}

	/**
	 * Get the number of different preferences values held by this class.
	 */
	@Override
	protected int getNumPreferences() {
		return super.getNumPreferences() + numRCPPrefs;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.eavp.viz.service.connections.preferences.
	 * IVizConnectionPreferences#serialize()
	 */
	@Override
	public String serialize() {
		return super.serialize() + "," + connectionName;
	}
}
