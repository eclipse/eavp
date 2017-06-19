/*******************************************************************************
 * Copyright (c) 2017 UT-Battelle, LLC.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   Robert Smith - Initial API and implementation and/or
 *     initial documentation
 *******************************************************************************/
package org.eclipse.eavp.viz.service.rcp.connection;

import java.util.ArrayList;
import java.util.concurrent.Future;

import org.eclipse.core.runtime.preferences.IEclipsePreferences;
import org.eclipse.core.runtime.preferences.IEclipsePreferences.IPreferenceChangeListener;
import org.eclipse.core.runtime.preferences.IEclipsePreferences.PreferenceChangeEvent;
import org.eclipse.eavp.viz.service.connections.ConnectionState;
import org.eclipse.eavp.viz.service.connections.VizConnectionManager;
import org.eclipse.eavp.viz.service.rcp.preferences.CustomScopedPreferenceStore;
import org.osgi.service.prefs.BackingStoreException;

/**
 * An extension of IVizConnectionManager that accepts a
 * CustomScopedPreferencesStore.
 * 
 * @author Robert Smith
 *
 */
abstract public class RCPConnectionManager<T> extends VizConnectionManager<T> {

	/**
	 * The listener that handles adding, updating, and removing viz connections
	 * to this manager. This needs to be registered with the current preference
	 * store's node under which connection preferences are stored.
	 */
	private final IPreferenceChangeListener preferenceListener;

	/**
	 * The current preference store associated with this manager. Connections
	 * should be loaded and updated based on the contents of this store.
	 */
	private CustomScopedPreferenceStore preferenceStore;

	public RCPConnectionManager() {
		super();

		// Create the preference listener.
		preferenceListener = createPreferenceListener();
	}

	/**
	 * Creates a listener that appropriately adds, updates, or removes
	 * connections based on the values in the {@link #preferenceStore}.
	 * 
	 * @return A new property change listener that can be registered with the
	 *         preference store.
	 */
	private IPreferenceChangeListener createPreferenceListener() {
		return new IPreferenceChangeListener() {
			@Override
			public void preferenceChange(PreferenceChangeEvent event) {
				String name = event.getKey();
				Object oldValue = event.getOldValue();
				Object newValue = event.getNewValue();

				// Add, update, or remove depending on whether the old/new
				// values are null.
				if (oldValue != null) {
					if (newValue != null) {
						updateConnection(name, newValue.toString());
					} else {
						removeConnection(name);
					}
				} else if (newValue != null) {
					addConnection(name, newValue.toString());
				}

				return;
			}
		};
	}

	/**
	 * Sets the preference store used by the manager. This will first cause any
	 * existing connections to be terminated. If any connections can be loaded
	 * from the store, the manager will attempt to connect to them.
	 * 
	 * @param store
	 *            The new store. Should not be {@code null}.
	 * @param preferenceNodeId
	 *            The ID of the preference node. Connection preferences will be
	 *            found under this node. Should not be {@code null}.
	 * @return A list of Future states for each of the connections added to the
	 *         preference store.
	 * @throws NullPointerException
	 *             If the preference node ID is {@code null} and the store is
	 *             not.
	 */
	public ArrayList<Future<ConnectionState>> setPreferenceStore(
			CustomScopedPreferenceStore store, String preferenceNodeId)
			throws NullPointerException {
		// Throw an exception if the preference node ID is null. We must have a
		// valid node ID if we have a store.
		if (store != null && preferenceNodeId == null) {
			throw new NullPointerException("VizConnectionManager error: "
					+ "Preference node ID cannot be null.");
		}

		// A list of future references to conenction states of all attempted
		// connections for the manager
		ArrayList<Future<ConnectionState>> states = new ArrayList<Future<ConnectionState>>();

		if (store != preferenceStore
				|| !preferenceNodeId.equals(connectionsNodeId)) {
			// If the old store/node ID is valid, unregister the preferences
			// listener and remove all current connections from the manager.
			if (preferenceStore != null) {
				preferenceStore.getNode(connectionsNodeId)
						.removePreferenceChangeListener(preferenceListener);
				preferenceStore = null;
				connectionsNodeId = null;

				// Remove all current connections.
				connectionsByName.clear();
				connectionsByHost.clear();
			}

			// If the new store/node ID is valid, add all new connections, then
			// register the preferences listener.
			if (store != null) {
				// Get the node under which the connections will be stored.
				IEclipsePreferences node = store.getNode(preferenceNodeId);
				// Add all connections in the new preference store.
				try {
					String[] connectionNames = node.keys();
					for (String connection : connectionNames) {
						String preferences = node.get(connection, null);
						states.add(addConnection(connection, preferences));
					}
				} catch (BackingStoreException e) {
					e.printStackTrace();
				}

				// Register with the new store.
				node.addPreferenceChangeListener(preferenceListener);
			}

			// Update the references to the store and the ID.
			preferenceStore = store;
			connectionsNodeId = preferenceNodeId;
		}

		return states;
	}

}
