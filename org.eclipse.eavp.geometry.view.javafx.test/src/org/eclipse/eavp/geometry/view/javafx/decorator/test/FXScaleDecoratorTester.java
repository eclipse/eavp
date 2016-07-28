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
package org.eclipse.eavp.geometry.view.javafx.decorator.test;

import static org.junit.Assert.assertEquals;

import org.eclipse.eavp.geometry.view.javafx.decorators.FXScaleDecorator;
import org.eclipse.eavp.geometry.view.javafx.render.FXMeshCache;
import org.eclipse.eavp.geometry.view.javafx.render.FXRenderObject;
import org.eclipse.january.geometry.GeometryFactory;
import org.eclipse.january.geometry.Shape;
import org.junit.Test;

/**
 * A class to test the functionality of the FXScaleDecorator.
 * 
 * @author Robert Smith
 *
 */
public class FXScaleDecoratorTester {

	/**
	 * Check that the decorator will set the object's scale correctly.
	 */
	@Test
	public void checkMesh() {

		// Create a render object
		Shape shape = GeometryFactory.eINSTANCE.createShape();
		FXRenderObject object = new FXRenderObject(shape, new FXMeshCache());

		// Create a scale decorator for it
		FXScaleDecorator decorator = new FXScaleDecorator();
		decorator.setSource(object);

		// Set the scale
		decorator.setScale(0.5);

		// The child's scale in all directions should have been changed
		assertEquals(0.5, decorator.getMesh().getScaleX(), .01d);
		assertEquals(0.5, decorator.getMesh().getScaleY(), .01d);
		assertEquals(0.5, decorator.getMesh().getScaleZ(), .01d);

		// Sending an update from the shape should also set the scale
		shape.changeDecoratorProperty("scale", 10d);
		assertEquals(10, decorator.getMesh().getScaleX(), .01d);
		assertEquals(10, decorator.getMesh().getScaleY(), .01d);
		assertEquals(10, decorator.getMesh().getScaleZ(), .01d);

		// Setting the property should also set the scale for the decorator
		// and object
		decorator.setProperty("scale", 5d);
		assertEquals(5, decorator.getMesh().getScaleX(), .01d);
		assertEquals(5, decorator.getMesh().getScaleY(), .01d);
		assertEquals(5, decorator.getMesh().getScaleZ(), .01d);
	}
}