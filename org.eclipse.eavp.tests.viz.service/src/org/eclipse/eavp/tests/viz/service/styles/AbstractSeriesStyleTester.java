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
package org.eclipse.eavp.tests.viz.service.styles;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.eclipse.eavp.viz.service.styles.AbstractSeriesStyle;
import org.junit.Test;

/**
 * A class to test the functionality of the AbstractSeriesStyle.
 * 
 * @author Robert Smith
 *
 */
public class AbstractSeriesStyleTester {

	/**
	 * Check the equality testing between styles.
	 */
	@Test
	public void checkEquality() {

		// Create a testing object
		TestSeriesStyle object = new TestSeriesStyle();
		object.setProperty("test", "value");

		// Create an equal object
		TestSeriesStyle equalObject = new TestSeriesStyle();
		equalObject.setProperty("test", "value");

		// Create an object with different properties.
		TestSeriesStyle unequalObject = new TestSeriesStyle();
		unequalObject.setProperty("test", "different value");

		// An object should equal itself
		assertTrue(object.equals(object));

		// Objects with the same properties should be equal
		assertTrue(object.equals(equalObject));
		assertTrue(equalObject.equals(object));

		// Objects with different properities should not be equal.
		assertFalse(object.equals(unequalObject));

		// Copy an object and check if the results are equal to the original
		TestSeriesStyle copy = new TestSeriesStyle();
		copy.copy(object);
		assertTrue(object.equals(copy));
	}

	/**
	 * Check the style's properties.
	 */
	@Test
	public void checkProperties() {

		// Create a style for testing
		TestSeriesStyle style = new TestSeriesStyle();

		// Check that the property starts off as null
		assertNull(style.getProperty("test"));

		// Try to set a property that doesn't exist and check that it is ignored
		assertFalse(style.setProperty("invalid", "property"));
		assertNull(style.getProperty("invalid"));

		// Set the property to a valid value
		assertTrue(style.setProperty("test", "value"));
		assertEquals("value", style.getProperty("test"));

		// Set the property to a new value of the same type
		assertTrue(style.setProperty("test", "new value"));
		assertEquals("new value", style.getProperty("test"));

		// Try to set the property to a new value of a different type. This
		// should be ignored.
		assertFalse(style.setProperty("test", 1));
		assertEquals("new value", style.getProperty("test"));

		// Set the property to null
		assertTrue(style.setProperty("test", null));
		assertNull(style.getProperty("test"));

		// Set the property to a number
		assertTrue(style.setProperty("test", 1));
		assertEquals(1, style.getProperty("test"));

		// Set the property to a different number
		assertTrue(style.setProperty("test", 2));
		assertEquals(2, style.getProperty("test"));

		// Try to set the property back to a string. This should be ignored.
		assertFalse(style.setProperty("test", "value"));
		assertEquals(2, style.getProperty("test"));
	}

	/**
	 * A concrete implementation of AbstractSeriesStyle with a single property
	 * 
	 * @author Robert Smith
	 *
	 */
	private class TestSeriesStyle extends AbstractSeriesStyle {

		public TestSeriesStyle() {
			properties.put("test", null);
		}
	}
}
