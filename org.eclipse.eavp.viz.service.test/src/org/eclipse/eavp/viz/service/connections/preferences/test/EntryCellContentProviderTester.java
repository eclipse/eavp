/*******************************************************************************
 * Copyright (c) 2016 UT-Battelle, LLC.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   Robert Smith
 *   
 *******************************************************************************/
package org.eclipse.eavp.viz.service.connections.preferences.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.eclipse.eavp.viz.datastructures.VizAllowedValueType;
import org.eclipse.eavp.viz.datastructures.VizEntry;
import org.eclipse.eavp.viz.service.connections.preferences.EntryCellContentProvider;
import org.junit.Test;

/**
 * A class to test the functionality of the EntryCellContentProvider.
 * 
 * @author Robert Smith
 *
 */
public class EntryCellContentProviderTester {

	/**
	 * Test that the provider correctly gets and sets values for its input.
	 */
	@Test
	public void checkValues() {

		// Create a provider and an entry for it to check.
		EntryCellContentProvider provider = new EntryCellContentProvider();
		TestEntry entry = new TestEntry();
		VizEntry nullEntry = null;

		// Only non-null entries should be valid.
		assertFalse(provider.isValid(null));
		assertFalse(provider.isValid(nullEntry));
		assertFalse(provider.isValid(provider));
		assertTrue(provider.isValid(entry));

		// All valid entries should be enabled
		assertFalse(provider.isEnabled(null));
		assertFalse(provider.isEnabled(provider));
		assertFalse(provider.isValid(provider));
		assertTrue(provider.isEnabled(entry));

		// Invalid input should have an empty list of allowed values
		assertTrue(provider.getAllowedValues(null).isEmpty());

		// Check that the provider gets the right list of allwoed values
		assertTrue("off".equals(provider.getAllowedValues(entry).get(0)));
		assertEquals(2, provider.getAllowedValues(entry).size());

		// Check that the provider gets the right values
		assertNull(provider.getValue(null));
		assertTrue("off".equals(provider.getValue(entry)));

		// Check that the provider gets the right tooltip
		assertTrue("Invalid Entry.".equals(provider.getToolTipText(null)));
		assertTrue("tooltip".equals(provider.getToolTipText(entry)));

		// Check that the provider gets the right text
		assertTrue("Invalid Entry.".equals(provider.getText(null)));
		assertTrue("off".equals(provider.getText(entry)));

		// Null should not be secret
		assertFalse(provider.isSecret(null));

		// Check that the provider gets the entry's secret flag correctly
		assertFalse(provider.isSecret(entry));
		entry.setSecret();
		assertTrue(provider.isSecret(entry));

		// Null should not require a combo
		assertFalse(provider.requiresCombo(null));

		// Check that the provider only requires a combo for discrete entries
		assertFalse(provider.requiresCombo(entry));
		entry.setDiscrete();
		assertTrue(provider.requiresCombo(entry));

		// Setting a value to an invalid entry should do nothing
		assertFalse(provider.setValue(null, "ignore"));

		// It should not be possible to set the value to null
		assertFalse(provider.setValue(entry, null));

		// The provider should ignore input not on the allowed values list
		assertFalse(provider.setValue(entry, "invalid"));

		// Set the value to a valid setting and check that it is correct
		assertTrue(provider.setValue(entry, "on"));
		assertTrue("on".equals(provider.getValue(entry)));

	}

	/**
	 * A test implementation of VizEntry with preset allowed values and
	 * convenience methods for setting its attributes.
	 * 
	 * @author Robert Smith
	 *
	 */
	private class TestEntry extends VizEntry {

		/**
		 * The default constructor.
		 */
		public TestEntry() {
			super();
			allowedValues.add("off");
			allowedValues.add("on");
			iEntryContentProvider.setAllowedValues(allowedValues);
			objectDescription = "tooltip";
			value = "off";
		}

		/**
		 * Set the entry's type to discrete.
		 */
		public void setDiscrete() {
			allowedValueType = VizAllowedValueType.Discrete;
			iEntryContentProvider
					.setAllowedValueType(VizAllowedValueType.Discrete);
		}

		/**
		 * Set the entry's secret flag.
		 */
		public void setSecret() {
			secretFlag = true;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see org.eclipse.eavp.viz.datastructures.VizEntry#getValue()
		 */
		@Override
		public String getValue() {
			return value;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see org.eclipse.eavp.viz.datastructures.VizEntry#getValueType()
		 */
		@Override
		public VizAllowedValueType getValueType() {
			return allowedValueType;
		}

	}
}
