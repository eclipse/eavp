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
package org.eclipse.eavp.viz.service.connections;

import java.util.Set;
import java.util.concurrent.Future;

/**
 * An implementation of this interface manages multiple {@link IVizConnection}s
 * and provides them to viz services to distribute to appropriate plot instances
 * (depending on their URIs).
 * <p>
 * Furthermore, it should also coordinates connection updates based on the
 * settings stored in the Eclipse preferences. At startup, it creates
 * connections for stored preferences and launches them in the background. These
 * connections can then be consumed by client code in a viz service.
 * </p>
 * 
 * @author Jordan Deyton
 *
 * @param <T>
 *            The type of the underlying connection widget.
 */
public interface IVizConnectionManager<T> {

	/**
	 * The default delimiter for connection preferences when they are
	 * saved/loaded to Eclipse preferences.
	 */
	static final String DEFAULT_CONNECTION_PREFERENCE_DELIMITER = ",";

	/**
	 * Adds a new connection based on the specified name and preference value.
	 * The connection will attempt to connect.
	 * 
	 * @param name
	 *            The name of the connection (a preference name in the store).
	 * @param preferences
	 *            The preference value for the connection. This value should
	 *            come straight from the {@link #preferenceStore}.
	 * 
	 * @return The Future state of the connection being added.
	 */
	public Future<ConnectionState> addConnection(String name,
			String preferences);
	
	/**
	 * Gets the viz connection with the specified name. Names should be
	 * retrieved from either {@link #getConnections()} or
	 * {@link #getConnectionsForHost(String)}.
	 * 
	 * @param name
	 *            The name of the connection to acquire.
	 * @return The associated viz connection, or {@code null} if there is no
	 *         connection for the specified name.
	 */
	IVizConnection<T> getConnection(String name);

	/**
	 * Gets the names of all available connections.
	 * 
	 * @return A lexicographically ordered set of available connection names.
	 */
	Set<String> getConnections();

	/**
	 * Gets all connections available for the specified host.
	 * 
	 * @param host
	 *            The host machine. Must not be {@code null}, but may be
	 *            "localhost" or otherwise a resolvable hostname.
	 * @return A lexicographically ordered set of connection names associated
	 *         with the specified host. This set may be empty if there are no
	 *         connections to the host.
	 * @throws NullPointerException
	 *             If the specified host is {@code null}.
	 */
	Set<String> getConnectionsForHost(String host) throws NullPointerException;
	
	/**
	 * Removes a connection based on the specified name. The connection will be
	 * disconnected.
	 * 
	 * @param name
	 *            The name of the connection to remove.
	 */
	public void removeConnection(String name);
}