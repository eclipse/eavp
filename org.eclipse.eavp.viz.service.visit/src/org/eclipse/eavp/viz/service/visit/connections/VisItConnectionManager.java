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
package org.eclipse.eavp.viz.service.visit.connections;

import java.util.List;

import org.eclipse.eavp.viz.service.connections.VizConnection;
import org.eclipse.eavp.viz.service.connections.VizConnectionManager;
import org.eclipse.remote.core.IRemoteConnection;
import org.eclipse.remote.core.IRemoteConnectionType;
import org.eclipse.remote.core.IRemoteServicesManager;
import org.osgi.framework.BundleContext;
import org.osgi.framework.FrameworkUtil;
import org.osgi.framework.ServiceReference;

import gov.lbnl.visit.swt.VisItSwtConnection;

/**
 * This class provides an {@link VizConnectionManager} that creates and manages
 * preferences for {@link VisItConnection}s.
 * 
 * @author Jordan Deyton
 *
 */
public class VisItConnectionManager
		extends VizConnectionManager<VisItSwtConnection> {

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.eavp.viz.service.connections.VizConnectionManager#
	 * createConnection(java.lang.String, java.lang.String)
	 */
	@Override
	protected VizConnection<VisItSwtConnection> createConnection(String name,
			String preferences) {
		VisItConnection connection = new VisItConnection();

		VisItVizConnectionPreferences connectionPreferences = new VisItVizConnectionPreferences(
				preferences, ",");

		// // Split the string using the delimiter. The -1 is necessary to
		// include
		// // empty values from the split.
		// String[] split =
		// preferences.split(getConnectionPreferenceDelimiter(),
		// -1);
		//
		// try {
		// // Get the additional properties, if possible.
		// String gateway = split[3];
		// int gatewayPort = Integer.parseInt(split[4]);
		// String username = split[5];
		//
		// // Set the connection's properties.
		// connection.setProperty("gateway", gateway);
		// connection.setProperty("localGatewayPort",
		// Integer.toString(gatewayPort));
		// connection.setProperty("username", username);
		// } catch (IndexOutOfBoundsException | NullPointerException
		// | NumberFormatException e) {
		// // Cannot add the connection.
		// connection = null;
		// }

		// Get the additional properties, if possible.
		String gateway = connectionPreferences.getProxy();
		int gatewayPort = connectionPreferences.getProxyPort();
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

					// Get the names of all current
					// connections
					for (IRemoteConnection remoteConnection : type
							.getConnections()) {
						if (remoteConnection.getName().equals(
								connectionPreferences.getConnectionName())) {
							// remoteConnection.getName();
							username = remoteConnection.getWorkingCopy()
									.getAttribute("USERNAME_ATTR");
						}
					}
				}
			}
		}

		// Set the preferences to the connection
		connection.setProperty("gateway", gateway);
		connection.setProperty("localGatewayPort",
				Integer.toString(gatewayPort));
		connection.setProperty("username", username);

		return connection;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.eavp.viz.service.connections.VizConnectionManager#
	 * updateConnectionPreferences(org.eclipse.eavp.viz.service.connections.
	 * VizConnection, java.lang.String)
	 */
	@Override
	protected boolean updateConnectionPreferences(
			VizConnection<VisItSwtConnection> connection, String preferences) {
		boolean requiresReset = super.updateConnectionPreferences(connection,
				preferences);

		VisItVizConnectionPreferences connectionPreferences = new VisItVizConnectionPreferences(
				preferences, ",");

		try {
			// Get the additional properties, if possible.
			String gateway = connectionPreferences.getProxy();
			int gatewayPort = connectionPreferences.getProxyPort();
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

						// Get the names of all current
						// connections
						for (IRemoteConnection remoteConnection : type
								.getConnections()) {
							if (remoteConnection.getName()
									.equals(connectionPreferences
											.getConnectionName())) {
								// remoteConnection.getName();
								username = remoteConnection.getWorkingCopy()
										.getAttribute("USERNAME_ATTR");
							}
						}
					}
				}
			}

			// If any of these change, the connection will need to be reset.
			requiresReset |= connection.setProperty("gateway", gateway);
			requiresReset |= connection.setProperty("localGatewayPort",
					Integer.toString(gatewayPort));
			requiresReset |= connection.setProperty("username", username);
			requiresReset |= connection
					.setPath(connectionPreferences.getExecutablePath());
		} catch (IndexOutOfBoundsException | NullPointerException
				| NumberFormatException e) {
			// Cannot update the connection.
			requiresReset = false;
		}

		return requiresReset;
	}
}
