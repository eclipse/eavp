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
package org.eclipse.eavp.tests.geometry.view.javafx.display;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Map;

import org.eclipse.eavp.geometry.view.javafx.display.FXOpacityOption;
import org.eclipse.eavp.geometry.view.javafx.render.FXMeshCache;
import org.eclipse.eavp.geometry.view.javafx.render.FXRenderObject;
import org.eclipse.eavp.geometry.view.model.ComboDisplayOptionData;
import org.eclipse.eavp.geometry.view.model.DisplayOptionType;
import org.eclipse.eavp.geometry.view.model.IDisplayOptionData;
import org.eclipse.eavp.geometry.view.model.impl.OpacityOptionImpl;
import org.eclipse.january.geometry.GeometryFactory;
import org.eclipse.january.geometry.Shape;
import org.junit.Ignore;
import org.junit.Test;

/**
 * A class to test the functionality of the FXOpacityDecorator.
 * 
 * @author Robert Smith
 *
 */
@Ignore
public class FXOpacityOptionTester {

	/**
	 * Check that the decorator will set the object's material correctly.
	 */
	@Test
	public void checkMesh() {

		// Create a render object
		Shape shape = GeometryFactory.eINSTANCE.createShape();
		FXRenderObject object = new FXRenderObject(shape, new FXMeshCache());

		// Create an opacity decorator for it
		FXOpacityOption decorator = new FXOpacityOption(object);

		// Set the opacity
		object.setProperty(OpacityOptionImpl.PROPERTY_NAME_OPACITY, 0d);

		// The child's opacity should have been changed
		assertEquals(0d, object.getMesh().getOpacity(), .01d);

		// Make the shape opaque again and check that it worked
		object.setProperty(OpacityOptionImpl.PROPERTY_NAME_OPACITY, 100d);
		assertEquals(1d, object.getMesh().getOpacity(), .01d);

		// Opacities below 100 are set to 0 to avoid JavaFX rendering bugs.
		object.setProperty(OpacityOptionImpl.PROPERTY_NAME_OPACITY, 50d);
		assertEquals(0d, object.getMesh().getOpacity(), .01d);

		// Sending an update from the shape should also set the opacity
		shape.changeDecoratorProperty(OpacityOptionImpl.PROPERTY_NAME_OPACITY,
				100d);
		assertEquals(100d,
				(double) object
						.getProperty(OpacityOptionImpl.PROPERTY_NAME_OPACITY),
				.01d);
		assertEquals(1d, object.getMesh().getOpacity(), .01d);

		// Deactivating the option should leave the object opaque
		object.setProperty(OpacityOptionImpl.PROPERTY_NAME_OPACITY, 0d);
		decorator.setActive(false);
		assertEquals(0d,
				(double) object
						.getProperty(OpacityOptionImpl.PROPERTY_NAME_OPACITY),
				.01d);
		assertEquals(1d, object.getMesh().getOpacity(), .01d);
	}

	/**
	 * Check the IDisplayOptionData from the option.
	 */
	@Test 
	public void checkData() {
		// Create a render object
		Shape shape = GeometryFactory.eINSTANCE.createShape();
		FXRenderObject object = new FXRenderObject(shape, new FXMeshCache());

		// Create an opacity decorator for it
		FXOpacityOption decorator = new FXOpacityOption(object);

		// Check that the data is of the right type
		IDisplayOptionData genericData = decorator.getDisplayOptionData();
		assertTrue(genericData instanceof ComboDisplayOptionData);
		ComboDisplayOptionData data = (ComboDisplayOptionData) genericData;

		// Check that the data members are right
		assertTrue(data.getDisplayOption() == decorator);
		assertTrue(data.getDisplayOptionType() == DisplayOptionType.COMBO);

		// The map of combo selections to property values
		Map<String, Map<String, Object>> map = data
				.getTextToPropertyValuesMap();

		// Check that opacity is set correctly for each option
		double d = (double) map.get("Opaque")
				.get(OpacityOptionImpl.PROPERTY_NAME_OPACITY);
		System.out.println(d);
		assertTrue(map.get("Opaque")
				.get(OpacityOptionImpl.PROPERTY_NAME_OPACITY).equals(100d));
		assertTrue(map.get("Transparent")
				.get(OpacityOptionImpl.PROPERTY_NAME_OPACITY).equals(0d));
		assertTrue(map.get("default")
				.get(OpacityOptionImpl.PROPERTY_NAME_OPACITY).equals(100d));

	}
}