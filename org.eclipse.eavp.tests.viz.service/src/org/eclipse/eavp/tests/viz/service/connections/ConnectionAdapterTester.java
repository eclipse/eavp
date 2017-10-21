/*******************************************************************************
 * Copyright (c) 2016 UT-Battelle, LLC.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   Robert Smith
 *******************************************************************************/
package org.eclipse.eavp.tests.viz.service.connections;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.eclipse.eavp.viz.datastructures.ui.VizEntry;
import org.eclipse.eavp.viz.service.connections.ConnectionAdapter;
import org.eclipse.eavp.viz.service.connections.ConnectionState;
import org.junit.Test;

/**
 * A class to test the functionality of the ConnectionAdapter.
 * 
 * @author Robert Smith
 *
 */
public class ConnectionAdapterTester {

	/**
	 * Checks that the adapter can correctly manage its state while it connects
	 * and disconnects.
	 */
	@Test
	public void checkConnectionState() {
		FakeConnectionAdapter adapter = new FakeConnectionAdapter();

		// Try to connect with blocking turned on to avoid threading issues. It
		// should fail.
		assertFalse(adapter.connect(true));
		assertEquals(ConnectionState.Failed, adapter.getState());

		// Set the adapter to succeed and try to disconnect with blocking when
		// there is no connection.
		adapter.setFailure(false);
		assertTrue(adapter.disconnect(true));
		assertEquals(ConnectionState.Failed, adapter.getState());

		// Connect
		assertTrue(adapter.connect(true));
		assertEquals(ConnectionState.Connected, adapter.getState());

		// Try to connect again when already connected
		assertTrue(adapter.connect(true));
		assertEquals(ConnectionState.Connected, adapter.getState());

		// Set the adapter to fail and try to discconnect
		adapter.setFailure(true);
		assertFalse(adapter.disconnect(true));
		assertEquals(ConnectionState.Connected, adapter.getState());

		// Successfully disconnect this time
		adapter.setFailure(false);
		assertTrue(adapter.disconnect(true));
		assertEquals(ConnectionState.Disconnected, adapter.getState());
	}

	/**
	 * A simple ConnectionAdapter for testing purposes, which allows the user to
	 * specify whether or not it should succeed when attempting to connect or
	 * disconnect.
	 * 
	 * @author Robert Smith
	 *
	 */
	private class FakeConnectionAdapter
			extends ConnectionAdapter<FakeVizConnection> {

		/**
		 * Whether or not the adapter should succeed at opening/closing its
		 * connection
		 */
		boolean fail = true;

		/*
		 * (non-Javadoc)
		 * 
		 * @see org.eclipse.eavp.viz.service.connections.ConnectionAdapter#
		 * openConnection()
		 */
		@Override
		protected FakeVizConnection openConnection() {

			// Return null if the connection failed
			if (fail) {
				return null;

				// Otherwise create a new connection
			} else {
				return new FakeVizConnection();
			}
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see org.eclipse.eavp.viz.service.connections.ConnectionAdapter#
		 * closeConnection(java.lang.Object)
		 */
		@Override
		protected boolean closeConnection(FakeVizConnection connection) {
			return !fail;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see org.eclipse.eavp.viz.service.connections.ConnectionAdapter#
		 * setConnectionProperties(java.util.List)
		 */
		@Override
		public boolean setConnectionProperties(List<VizEntry> properties) {
			return false;
		}

		/**
		 * Set whether the adapter should fail when attempting a connection
		 * action.
		 * 
		 * @param fail
		 *            True if the adapter should fail when attempting to connect
		 *            or disconnect. False if it should succeed.
		 */
		public void setFailure(boolean fail) {
			this.fail = fail;
		}

	}
}