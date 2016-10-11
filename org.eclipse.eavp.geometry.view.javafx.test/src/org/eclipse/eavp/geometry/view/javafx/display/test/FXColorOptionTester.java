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

import org.eclipse.eavp.geometry.view.javafx.display.FXColorOption;
import org.eclipse.eavp.geometry.view.javafx.render.FXMeshCache;
import org.eclipse.eavp.geometry.view.javafx.render.FXRenderObject;
import org.eclipse.eavp.geometry.view.model.impl.ColorOptionImpl;
import org.eclipse.january.geometry.GeometryFactory;
import org.eclipse.january.geometry.Shape;
import org.junit.Ignore;
import org.junit.Test;

import javafx.scene.shape.MeshView;

/**
 * A class to test the functionality of the FXColorDecorator.
 * 
 * @author Robert Smith
 *
 */
public class FXColorOptionTester {

	/**
	 * Check that the decorator will set the object's material correctly.
	 */
	@Test @Ignore
	public void checkMesh() {

		// Create a render object
		Shape shape = GeometryFactory.eINSTANCE.createShape();
		FXRenderObject object = new FXRenderObject(
				GeometryFactory.eINSTANCE.createCube(), new FXMeshCache());

		// Create a color decorator for it
		FXColorOption decorator = new FXColorOption(object);

		// Set the color
		object.setProperty(ColorOptionImpl.PROPERTY_NAME_RED, 200);
		object.setProperty(ColorOptionImpl.PROPERTY_NAME_GREEN, 100);
		object.setProperty(ColorOptionImpl.PROPERTY_NAME_BLUE, 0);

		// We cannot test PhongMaterials for equality as JavaFX does not
		// overload equals() or provide methods for getting a PhongMaterial's
		// attributes, so instead just check that the decorator returned a
		// meshview.
		assertTrue(object.getMesh().getChildren().get(0) instanceof MeshView);

	}
}
