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

import org.eclipse.eavp.geometry.view.javafx.decorators.FXOpacityDecorator;
import org.eclipse.eavp.geometry.view.javafx.render.FXMeshCache;
import org.eclipse.eavp.geometry.view.javafx.render.FXRenderObject;
import org.junit.Test;

import geometry.GeometryFactory;
import geometry.Shape;

/**
 * A class to test the functionality of the FXOpacityDecorator.
 * 
 * @author Robert Smith
 *
 */
public class FXOpacityDecoratorTester {

	/**
	 * Check that the decorator will set the object's material correctly.
	 */
	@Test
	public void checkMesh() {

		// Create a render object
		Shape shape = GeometryFactory.eINSTANCE.createShape();
		FXRenderObject object = new FXRenderObject(shape, new FXMeshCache());

		// Create an opacity decorator for it
		FXOpacityDecorator decorator = new FXOpacityDecorator();
		decorator.setSource(object);

		// Set the opacity
		decorator.setOpacity(0);

		// The child's opacity should have been changed
		assertEquals(0, decorator.getMesh().getOpacity(), .01d);

		// Make the shape opaque again and check that it worked
		decorator.setOpacity(100);
		assertEquals(1, decorator.getMesh().getOpacity(), .01d);

		// Opacities below 100 are set to 0 to avoid JavaFX rendering bugs.
		decorator.setOpacity(50);
		assertEquals(0, decorator.getMesh().getOpacity(), .01d);

		// Sending an update from the shape should also set the opacity
		shape.changeDecoratorProperty("opacity", 100);
		assertEquals(100, decorator.getOpacity(), .01d);
		assertEquals(1, decorator.getMesh().getOpacity(), .01d);
	}
}