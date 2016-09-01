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

import static org.junit.Assert.assertTrue;

import org.eclipse.eavp.geometry.view.javafx.display.FXWireframeOption;
import org.eclipse.eavp.geometry.view.javafx.render.FXMeshCache;
import org.eclipse.eavp.geometry.view.javafx.render.FXRenderObject;
import org.eclipse.eavp.geometry.view.model.impl.WireframeOptionImpl;
import org.eclipse.january.geometry.GeometryFactory;
import org.eclipse.january.geometry.Shape;
import org.junit.Test;

import javafx.scene.shape.DrawMode;
import javafx.scene.shape.MeshView;

/**
 * A class to test the functionality of the FXWireframeDecorator.
 * 
 * @author Robert Smith
 *
 */
public class FXWireframeOptionTester {

	/**
	 * Check that the decorator will set the object's material correctly.
	 */
	@Test
	public void checkMesh() {

		// Create a render object
		Shape shape = GeometryFactory.eINSTANCE.createShape();
		FXRenderObject object = new FXRenderObject(shape, new FXMeshCache());

		// Create an opacity decorator for it
		FXWireframeOption decorator = new FXWireframeOption(object);

		// Set the shape as a wireframe
		object.setProperty(WireframeOptionImpl.PROPERTY_NAME_WIREFRAME, true);

		// The child's draw mode should have been changed
		assertTrue(((MeshView) object.getMesh().getChildren().get(0))
				.getDrawMode() == DrawMode.LINE);

		// Make the shape solid again and check that it was reset
		object.setProperty(WireframeOptionImpl.PROPERTY_NAME_WIREFRAME, false);
		assertTrue(((MeshView) object.getMesh().getChildren().get(0))
				.getDrawMode() == DrawMode.FILL);

		// Sending an update from the shape should also set the wireframe mode
		shape.changeDecoratorProperty(
				WireframeOptionImpl.PROPERTY_NAME_WIREFRAME, true);
		assertTrue(((MeshView) object.getMesh().getChildren().get(0))
				.getDrawMode() == DrawMode.LINE);

		// Deactivating the option should leave the object filled in
		decorator.setActive(false);
		assertTrue(((MeshView) object.getMesh().getChildren().get(0))
				.getDrawMode() == DrawMode.FILL);
	}
}
