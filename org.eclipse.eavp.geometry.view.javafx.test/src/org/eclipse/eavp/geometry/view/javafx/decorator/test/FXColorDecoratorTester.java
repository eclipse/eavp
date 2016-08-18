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
import static org.junit.Assert.assertTrue;

import org.eclipse.eavp.geometry.view.javafx.decorators.FXColorDecorator;
import org.eclipse.eavp.geometry.view.javafx.render.FXMeshCache;
import org.eclipse.eavp.geometry.view.javafx.render.FXRenderObject;
import org.eclipse.january.geometry.GeometryFactory;
import org.eclipse.january.geometry.Shape;
import org.junit.Test;

import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.MeshView;

/**
 * A class to test the functionality of the FXColorDecorator.
 * 
 * @author Robert Smith
 *
 */
public class FXColorDecoratorTester {

	/**
	 * Check that the decorator will set the object's material correctly.
	 */
	@Test
	public void checkMesh() {

		// Create a render object
		Shape shape = GeometryFactory.eINSTANCE.createShape();
		FXRenderObject object = new FXRenderObject(
				GeometryFactory.eINSTANCE.createCube(), new FXMeshCache());

		// Create a color decorator for it
		FXColorDecorator decorator = new FXColorDecorator();
		decorator.setSource(object);

		// Set the color
		decorator.setBlue(0);
		decorator.setGreen(100);
		decorator.setRed(200);

		// We cannot test PhongMaterials for equality as JavaFX does not
		// overload equals() or provide methods for getting a PhongMaterial's
		// attributes, so instead just check that the decorator returned a
		// meshview.
		assertTrue(
				decorator.getMesh().getChildren().get(0) instanceof MeshView);

		// Setting the properties should also set the variables
		decorator.setProperty("red", 1);
		assertEquals(1, decorator.getRed());
		decorator.setProperty("green", 2);
		assertEquals(2, decorator.getGreen());
		decorator.setProperty("blue", 3);
		assertEquals(3, decorator.getBlue());

		// Set a material to the object
		PhongMaterial material = new PhongMaterial(Color.AQUA);
		object.setProperty("material", material);

		// The material should have been saved by the decorator.
		assertEquals(material, decorator.getMaterial());

	}
}
