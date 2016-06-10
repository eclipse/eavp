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
package org.eclipse.eavp.viz.service.javafx.geometry.datatypes.test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.eclipse.eavp.viz.modeling.Shape;
import org.eclipse.eavp.viz.modeling.base.Representation;
import org.eclipse.eavp.viz.modeling.properties.MeshProperty;
import org.eclipse.eavp.viz.service.javafx.geometry.datatypes.FXShapeView;
import org.junit.Test;

import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.shape.Box;
import javafx.scene.shape.Cylinder;
import javafx.scene.shape.DrawMode;
import javafx.scene.shape.MeshView;
import javafx.scene.shape.Shape3D;
import javafx.scene.shape.Sphere;

/**
 * A class to test the functionality of the FXShape.
 * 
 * @author Robert Smith
 *
 */
public class FXShapeViewTester {

	/**
	 * Test that FXShapeViews are cloned correctly
	 */
	@Test
	public void checkClone() {

		// Create a cloned view and check that it is identical to the original
		Shape mesh = new Shape();
		mesh.setProperty(MeshProperty.TYPE, "Cube");
		FXShapeView view = new FXShapeView(mesh);
		Object clone = view.clone();

		// A cloned IView is not necessarily equivalent to its clone, because
		// the JavaFX data types do not have custom implementations of the
		// equals method.
		assertTrue(clone instanceof FXShapeView);
	}

	/**
	 * Check that the view is constructed properly.
	 */
	@Test
	public void checkConstruction() {

		// Create a cube named "test"
		Shape mesh = new Shape();
		mesh.setProperty(MeshProperty.NAME, "test");
		mesh.setProperty(MeshProperty.TYPE, "Cube");

		// Create a view for it
		FXShapeView view = new FXShapeView(mesh);

		// Whether a box shape has been found while searching the JavaFX node's
		// children.
		boolean boxFound = false;

		// Check that the JavaFX node has the correct name
		Representation<Group> representation = view.getRepresentation();
		assertTrue("test".equals(representation.getData().getId()));

		// Search all of the node's children
		for (Node node : (representation.getData()).getChildren()) {

			// If the child is a 3D shape, it should be in fill mode by default
			if (node instanceof Shape3D) {
				assertTrue(((Shape3D) node).getDrawMode() == DrawMode.FILL);
				assertTrue(((Shape3D) node).getOpacity() == 100d);

				// Check if a box has been found
				if (node instanceof Box) {
					boxFound = true;
				}
			}
		}

		// A child box corresponding to the cube should have been found
		assertTrue(boxFound);
	}

	/**
	 * Check that the view updates itself correctly when it refreshes based on
	 * new information from the model.
	 */
	@Test
	public void checkRefresh() {

		// Create a cube
		Shape mesh = new Shape();
		mesh.setProperty(MeshProperty.TYPE, "Cube");

		// Create a view for it
		FXShapeView view = new FXShapeView(mesh);

		// Whether or not the current shape has been found in the view's
		// children
		boolean found = false;

		// Search all of the node's children
		Representation<Group> representation = view.getRepresentation();
		for (Node node : representation.getData().getChildren()) {

			// If the child is a 3D shape, it should be in fill mode by default
			if (node instanceof Shape3D) {
				assertTrue(((Shape3D) node).getDrawMode() == DrawMode.FILL);

				// Check if a box has been found
				if (node instanceof Box) {
					found = true;
				}
			}
		}

		// Change the shape to a cylinder
		mesh.setProperty(MeshProperty.TYPE, "Cylinder");

		// Search all of the node's children
		found = false;
		representation = view.getRepresentation();
		for (Node node : representation.getData().getChildren()) {

			// If the child is a 3D shape, it should be in fill mode by default
			if (node instanceof Shape3D) {
				assertTrue(((Shape3D) node).getDrawMode() == DrawMode.FILL);

				// Check if a box has been found
				if (node instanceof Cylinder) {
					found = true;
				}
			}
		}

		mesh.setProperty(MeshProperty.TYPE, "Sphere");

		found = false;

		// Search all of the node's children
		representation = view.getRepresentation();
		for (Node node : representation.getData().getChildren()) {

			// If the child is a 3D shape, it should be in fill mode by default
			if (node instanceof Shape3D) {
				assertTrue(((Shape3D) node).getDrawMode() == DrawMode.FILL);

				// Check if a box has been found
				if (node instanceof Sphere) {
					found = true;
				}
			}
		}

		mesh.setProperty(MeshProperty.TYPE, "Tube");

		found = false;

		// Search all of the node's children
		representation = view.getRepresentation();
		for (Node node : representation.getData().getChildren()) {

			// If the child is a 3D shape, it should be in fill mode by default
			if (node instanceof Shape3D) {
				assertTrue(((Shape3D) node).getDrawMode() == DrawMode.FILL);

				// Check if a box has been found
				if (node instanceof MeshView) {
					found = true;
				}
			}
		}
	}

	/**
	 * Test that the shape can be made transparent.
	 */
	public void checkTransparency() {

		// Create a cube named "test"
		Shape mesh = new Shape();
		mesh.setProperty(MeshProperty.NAME, "test");
		mesh.setProperty(MeshProperty.TYPE, "Cube");

		// Create a view for it
		FXShapeView view = new FXShapeView(mesh);

		// The view should start off opaque
		assertFalse(view.isTransparent());

		// Make the view transparent
		view.setTransparentMode(true);

		// Check that the transparency flag is set
		assertTrue(view.isTransparent());

		// Whether a box shape has been found while searching the JavaFX node's
		// children.
		boolean boxFound = false;

		// Get the group containing the node
		Representation<Group> representation = view.getRepresentation();

		// Search all of the node's children
		for (Node node : (representation.getData()).getChildren()) {

			// If the child is a 3D shape, it should be transparent
			if (node instanceof Shape3D) {
				assertTrue(((Shape3D) node).getOpacity() == 0d);
				boxFound = true;
				break;
			}
		}

		// A child box corresponding to the cube should have been found
		assertTrue(boxFound);
	}

	/**
	 * Test that the shape can rendered in wireframe mode
	 */
	public void checkWireframe() {

		// Create a cube named "test"
		Shape mesh = new Shape();
		mesh.setProperty(MeshProperty.NAME, "test");
		mesh.setProperty(MeshProperty.TYPE, "Cube");

		// Create a view for it
		FXShapeView view = new FXShapeView(mesh);

		// The view should start off drawn normally
		assertFalse(view.isWireframe());

		// Make the view transparent
		view.setWireframeMode(true);

		// Check that the wireframe flag has been set
		assertTrue(view.isWireframe());

		// Whether a box shape has been found while searching the JavaFX node's
		// children.
		boolean boxFound = false;

		// Get the group containing the node
		Representation<Group> representation = view.getRepresentation();

		// Search all of the node's children
		for (Node node : (representation.getData()).getChildren()) {

			// If the child is a 3D shape, it should be drawn as a wireframe
			if (node instanceof Shape3D) {
				assertTrue(((Shape3D) node).getDrawMode() == DrawMode.LINE);
				boxFound = true;
				break;
			}
		}

		// A child box corresponding to the cube should have been found
		assertTrue(boxFound);
	}
}
