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
package org.eclipse.eavp.tests.viz.service.connections;

import static org.junit.Assert.assertEquals;

import org.eclipse.eavp.viz.service.connections.preferences.AbstractVizConnectionPreferences;
import org.junit.Test;

/**
 * A class to test the AbstractVizConnectionPreferences for the ability to
 * correctly manage a set of preferences.
 * 
 * @author Robert Smith
 *
 */
public class AbstractVizConnectionPreferencesTester {

	/**
	 * Check that the null constructor is working correctly.
	 */
	@Test
	public void checkConstruction() {
		AbstractVizConnectionPreferences prefs = new AbstractVizConnectionPreferences() {

		};

		// Check that the null constructor correctly initializes the
		// preferences.
		assertEquals("localhost", prefs.getHostName());
		assertEquals("default connection name", prefs.getName());
		assertEquals(0, prefs.getPort());
		assertEquals("default user name", prefs.getUsername());
	}

	/**
	 * Check that the preferences can be serialized and reconstructed from the
	 * string representation.
	 */
	@Test
	public void checkSerialize() {

		AbstractVizConnectionPreferences prefs = new AbstractVizConnectionPreferences() {

		};

		// Set up a connection
		prefs.setHostName("Remote Host");
		prefs.setName("Test Connection");
		prefs.setPort(1234);
		prefs.setUsername("EAVP User");

		// Check that variables were stored correctly
		assertEquals("Remote Host", prefs.getHostName());
		assertEquals("Test Connection", prefs.getName());
		assertEquals(1234, prefs.getPort());
		assertEquals("EAVP User", prefs.getUsername());

		// Get the serialized representation of the preferences
		String serialized = prefs.serialize();

		// Check that the preferences string is as expected
		assertEquals("Remote Host,Test Connection,1234,EAVP User", serialized);

		// Create a new preferences set from the string
		AbstractVizConnectionPreferences deserialized = new AbstractVizConnectionPreferences(
				serialized) {

		};

		// Check that the preferences are correctly configured
		assertEquals("Remote Host", deserialized.getHostName());
		assertEquals("Test Connection", deserialized.getName());
		assertEquals(1234, deserialized.getPort());
		assertEquals("EAVP User", deserialized.getUsername());
	}

}
