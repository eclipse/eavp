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
package org.eclipse.eavp.viz.service.javafx.mesh.datatypes;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.eclipse.eavp.viz.modeling.Vertex;
import org.eclipse.eavp.viz.modeling.base.Representation;
import org.junit.Ignore;
import org.junit.Test;

import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.shape.DrawMode;
import javafx.scene.shape.Shape3D;

/**
 * A class to test the functionality of the FXVertexView.
 * 
 * @author Robert Smith
 *
 */
public class FXVertexViewTester {

	/**
	 * Test that FXLinearViews are cloned correctly
	 */
	@Test @Ignore
	public void checkClone() {

		// Create a cloned view and check that it is identical to the original
		Vertex mesh = new Vertex();
		FXVertexView view = new FXVertexView(mesh);
		Object clone = view.clone();

		// A view is not necessarily equal to its clone, as JavaFX classes lack
		// an equals() implementation. Instead check that it is of the proepr
		// type
		assertTrue(clone instanceof FXVertexView);
	}

	/**
	 * Test that the edge can be drawn transparently
	 */
	@Test @Ignore
	public void checkTransparency() {

		// Create a view for a vertex
		FXVertexView view = new FXVertexView(new Vertex(0, 0, 0));

		// The view should start off opaque
		assertFalse(view.isTransparent());

		// Make the view transparent
		view.setTransparentMode(true);

		// Check that the transparency flag is set
		assertTrue(view.isTransparent());

		// Whether a sphere shape has been found while searching the JavaFX
		// node's children.
		boolean sphereFound = false;

		// Get the group containing the node
		Representation<Group> representation = view.getRepresentation();

		// Search all of the node's children
		for (Node node : (representation.getData()).getChildren()) {

			// If the child is a 3D shape, it should be transparent
			if (node instanceof Shape3D) {
				assertTrue(((Shape3D) node).getOpacity() == 0d);
				sphereFound = true;
				break;
			}
		}

		// A child sphere corresponding to the vertex should have been found
		assertTrue(sphereFound);
	}

	/**
	 * Test that the vertex can be drawn in wireframe mode
	 */
	@Test @Ignore
	public void checkWireframe() {

		// Create a view for a vertex
		FXVertexView view = new FXVertexView(new Vertex(0, 0, 0));

		// The view should start off opaque
		assertFalse(view.isWireframe());

		// Make the view transparent
		view.setWireframeMode(true);

		// Check that the transparency flag is set
		assertTrue(view.isWireframe());

		// Whether a sphere shape has been found while searching the JavaFX
		// node's children.
		boolean sphereFound = false;

		// Get the group containing the node
		Representation<Group> representation = view.getRepresentation();

		// Search all of the node's children
		for (Node node : (representation.getData()).getChildren()) {

			// If the child is a 3D shape, it should be transparent
			if (node instanceof Shape3D) {
				assertTrue(((Shape3D) node).getDrawMode() == DrawMode.LINE);
				sphereFound = true;
				break;
			}
		}

		// A child sphere corresponding to the vertex should have been found
		assertTrue(sphereFound);
	}
}
