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
package org.eclipse.eavp.geometry.view.javafx.display.test;

import static org.junit.Assert.assertEquals;

import org.eclipse.eavp.geometry.view.javafx.display.FXScaleOption;
import org.eclipse.eavp.geometry.view.javafx.render.FXMeshCache;
import org.eclipse.eavp.geometry.view.javafx.render.FXRenderObject;
import org.eclipse.eavp.geometry.view.model.impl.ScaleOptionImpl;
import org.eclipse.january.geometry.GeometryFactory;
import org.eclipse.january.geometry.Shape;
import org.junit.Test;

/**
 * A class to test the functionality of the FXScaleDecorator.
 * 
 * @author Robert Smith
 *
 */
public class FXScaleOptionTester {

	/**
	 * Check that the decorator will set the object's scale correctly.
	 */
	@Test
	public void checkMesh() {

		// Create a render object
		Shape shape = GeometryFactory.eINSTANCE.createShape();
		FXRenderObject object = new FXRenderObject(shape, new FXMeshCache());

		// Create a scale decorator for it
		FXScaleOption decorator = new FXScaleOption(object);

		// Set the scale
		object.setProperty(ScaleOptionImpl.PROPERTY_NAME_SCALE, 0.5);

		// The child's scale in all directions should have been changed
		assertEquals(0.5, object.getMesh().getScaleX(), .01d);
		assertEquals(0.5, object.getMesh().getScaleY(), .01d);
		assertEquals(0.5, object.getMesh().getScaleZ(), .01d);

		// Sending an update from the shape should also set the scale
		shape.changeDecoratorProperty(ScaleOptionImpl.PROPERTY_NAME_SCALE, 10d);
		assertEquals(10, object.getMesh().getScaleX(), .01d);
		assertEquals(10, object.getMesh().getScaleY(), .01d);
		assertEquals(10, object.getMesh().getScaleZ(), .01d);

		// Deactivating the option should leave the object with a 1:1 scale
		decorator.setActive(false);
		assertEquals(1, object.getMesh().getScaleX(), .01d);
		assertEquals(1, object.getMesh().getScaleY(), .01d);
		assertEquals(1, object.getMesh().getScaleZ(), .01d);
	}
}
