/*******************************************************************************
 * Copyright (c) 2017 UT-Battelle, LLC.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   Jordan Deyton, Robert Smith
 *   
 *******************************************************************************/
package org.eclipse.eavp.tests.viz.service.rcp.connections;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import org.eclipse.core.runtime.preferences.IEclipsePreferences;
import org.eclipse.eavp.tests.viz.service.connections.FakeClient;
import org.eclipse.eavp.tests.viz.service.connections.FakeVizConnection;
import org.eclipse.eavp.tests.viz.service.connections.FakeVizConnectionListener;
import org.eclipse.eavp.viz.service.connections.ConnectionState;
import org.eclipse.eavp.viz.service.connections.IVizConnection;
import org.eclipse.eavp.viz.service.connections.VizConnection;
import org.eclipse.eavp.viz.service.rcp.connection.RCPConnectionManager;
import org.eclipse.eavp.viz.service.rcp.preferences.CustomScopedPreferenceStore;
import org.junit.Before;
import org.junit.Test;

/**
 * A class to test the functionality of the RCPConnectionManager
 * 
 * @author Jordan Deyton, Robert Smith
 *
 */
public class RCPConnectionManagerTester {

	/**
	 * The preferences store for the test. The manager will pull preferences
	 * from this.
	 */
	private CustomScopedPreferenceStore store;

	/**
	 * The ID of the preference node under which connection preferences will be
	 * stored.
	 */
	private static final String NODE_ID = "org.eclipse.eavp.viz.service.connections.test";

	/**
	 * The fake connections (which wrap the custom connection API) created by
	 * the connection manager.
	 */
	private Map<String, FakeVizConnection> fakeConnections;

	/**
	 * The connection manager that will be tested.
	 */
	private RCPConnectionManager<FakeClient> manager;

	/**
	 * Initializes the viz connection manager that is tested as well as any
	 * other class variables frequently used to test the connection.
	 */
	@Before
	public void beforeEachTest() {
		// Initialize the map of created viz connections.
		fakeConnections = new HashMap<String, FakeVizConnection>();

		// Initialize the connection manager.
		manager = new RCPConnectionManager<FakeClient>() {
			@Override
			protected VizConnection<FakeClient> createConnection(String name,
					String preferences) {
				// Create a new fake connection and store it in the map.
				FakeVizConnection fakeConnection = new FakeVizConnection();
				fakeConnections.put(name, fakeConnection);
				return fakeConnection;
			}
		};

		// Create a new, empty preference store.
		store = new CustomScopedPreferenceStore(getClass());
		store.removeNode(NODE_ID);

		return;
	}

	/**
	 * Checks that the preference store can be set and loaded.
	 */
	@Test
	public void checkSetPreferenceStore() {

		// Set up a preference node for a first connection.
		final String nodeId1 = NODE_ID;
		IEclipsePreferences node1 = store.getNode(nodeId1);
		IVizConnection<FakeClient> connection1;
		String connection1Name;
		String connection1Host;
		int connection1Port;
		String connection1Path;
		// Set up a preference node for a second connection.
		final String nodeId2 = NODE_ID + "2";
		IEclipsePreferences node2 = store.getNode(nodeId2);
		IVizConnection<FakeClient> connection2;
		String connection2Name;
		String connection2Host;
		int connection2Port;
		String connection2Path;

		// Use a fake listener to determine that the first connection won't be
		// disconnected even though it is removed from the manager.
		FakeVizConnectionListener listener = new FakeVizConnectionListener();

		// Add a connection to the first preference node.
		connection1Name = "magic sword";
		connection1Host = "electrodungeon";
		connection1Port = 9000;
		connection1Path = "/home/music";
		node1.put(connection1Name, connection1Host + "," + connection1Port + ","
				+ connection1Path);

		// Add a connection to the second preference node.
		connection2Name = "trevor something";
		connection2Host = "electrodungeon";
		connection2Port = 9001;
		connection2Path = "/home/music/different/path";
		node2.put(connection2Name, connection2Host + "," + connection2Port + ","
				+ connection2Path);

		// Set the preference store using the first node.
		ArrayList<Future<ConnectionState>> states = manager
				.setPreferenceStore(store, nodeId1);

		// Make sure that the first connection was added to the manager.
		// Check getConnections()
		assertEquals(1, manager.getConnections().size());
		assertTrue(manager.getConnections().contains(connection1Name));
		// Check getConnectionsForHost(String)
		assertEquals(1, manager.getConnectionsForHost(connection1Host).size());
		assertTrue(manager.getConnectionsForHost(connection1Host)
				.contains(connection1Name));
		// Check getConnection(String)
		assertNotNull(manager.getConnection(connection1Name));

		// Check the first connection's properties.
		connection1 = manager.getConnection(connection1Name);
		assertEquals(connection1Name, connection1.getName());
		assertEquals(connection1Host, connection1.getHost());
		assertEquals(connection1Port, connection1.getPort());
		assertEquals(connection1Path, connection1.getPath());

		// The second connection should not yet exist.
		assertNull(manager.getConnection(connection2Name));

		// The connection should have been initialized. However, this is done on
		// a separate thread. We should give the CPU some leeway to establish
		// the connection.
		long sleepTime = 0;
		long threshold = 2000;
		long interval = 50;
		while (connection1.getState() != ConnectionState.Connected
				&& sleepTime < threshold) {
			try {
				Thread.sleep(interval);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			sleepTime += interval;
		}

		// For each of the connections, wait until their states are resolved.
		for (int i = 0; i < states.size(); i++) {

			try {
				states.get(i).get();
			} catch (InterruptedException | ExecutionException e) {
				e.printStackTrace();
				fail("Failed while attempting to get the value of future connection state.");
			}
		}

		assertEquals(ConnectionState.Connected, connection1.getState());

		// Add the listener to the first connection.
		connection1.addListener(listener);

		// Switch to the second preference store that has a different
		// connection.
		manager.setPreferenceStore(store, nodeId2);

		// Make sure that the first connection was added to the manager.
		// Check getConnections()
		assertEquals(1, manager.getConnections().size());
		assertTrue(manager.getConnections().contains(connection2Name));
		// Check getConnectionsForHost(String)
		assertEquals(1, manager.getConnectionsForHost(connection2Host).size());
		assertTrue(manager.getConnectionsForHost(connection2Host)
				.contains(connection2Name));
		// Check getConnection(String)
		assertNotNull(manager.getConnection(connection2Name));

		// Check the second connection's properties.
		connection2 = manager.getConnection(connection2Name);
		assertEquals(connection2Name, connection2.getName());
		assertEquals(connection2Host, connection2.getHost());
		assertEquals(connection2Port, connection2.getPort());
		assertEquals(connection2Path, connection2.getPath());

		// The first connection should not exist in the manager.
		assertNull(manager.getConnection(connection1Name));

		// The connection should have been initialized. However, this is done on
		// a separate thread. We should give the CPU some leeway to establish
		// the connection.
		sleepTime = 0;
		threshold = 2000;
		interval = 50;
		while (connection2.getState() != ConnectionState.Connected
				&& sleepTime < threshold) {
			try {
				Thread.sleep(interval);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			sleepTime += interval;
		}
		assertEquals(ConnectionState.Connected, connection2.getState());

		// The first connection should still be connected.
		assertFalse(listener.wasNotified());
		assertEquals(ConnectionState.Connected, connection1.getState());

		// Remove the second preference node. The first one will be removed
		// automatically since its ID is the default one.
		store.removeNode(nodeId2);

		return;
	}
}
