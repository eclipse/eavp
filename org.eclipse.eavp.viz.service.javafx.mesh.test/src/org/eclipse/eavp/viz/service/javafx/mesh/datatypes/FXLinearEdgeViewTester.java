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

import org.eclipse.eavp.viz.modeling.Edge;
import org.eclipse.eavp.viz.modeling.LinearEdge;
import org.eclipse.eavp.viz.modeling.Vertex;
import org.eclipse.eavp.viz.modeling.base.Representation;
import org.junit.Test;

import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.shape.DrawMode;
import javafx.scene.shape.Shape3D;

/**
 * A class to test the functionality of the FXLinearEdgeView.
 * 
 * @author Robert Smith
 *
 */
public class FXLinearEdgeViewTester {

	/**
	 * Test that FXLinearViews are cloned correctly
	 */
	@Test
	public void checkClone() {

		// Create a cloned view and check that it is identical to the original
		Edge mesh = new Edge();
		FXLinearEdgeView view = new FXLinearEdgeView(mesh);
		Object clone = view.clone();

		// A view is not equal to its clone because the JavaFX objects lack an
		// equals() implementation
		assertTrue(clone instanceof FXLinearEdgeView);
	}

	/**
	 * Test that the edge can be drawn transparently
	 */
	@Test
	public void checkTransparency() {

		// Create a view for an edge
		FXVertexController v1 = new FXVertexController(new Vertex(0, 0, 0),
				new FXVertexView(new Vertex(0, 0, 0)));
		FXVertexController v2 = new FXVertexController(new Vertex(1, 1, 1),
				new FXVertexView(new Vertex(1, 1, 1)));
		LinearEdge edge = new LinearEdge(v1, v2);
		FXLinearEdgeView view = new FXLinearEdgeView(edge);

		// The view should start off opaque
		assertFalse(view.isTransparent());

		// Make the view transparent
		view.setTransparentMode(true);

		// Check that the transparency flag is set
		assertTrue(view.isTransparent());

		// Whether a clinder shape has been found while searching the JavaFX
		// node's
		// children.
		boolean edgeFound = false;

		// Get the group containing the node
		Representation<Group> representation = view.getRepresentation();

		// Search all of the node's children
		for (Node node : (representation.getData()).getChildren()) {

			// If the child is a 3D shape, it should be transparent
			if (node instanceof Shape3D) {
				assertTrue(((Shape3D) node).getOpacity() == 0d);
				edgeFound = true;
				break;
			}
		}

		// A child box corresponding to the cube should have been found
		assertTrue(edgeFound);
	}

	/**
	 * Test that the edge can be drawn transparently
	 */
	@Test
	public void checkWireframe() {

		// Create a view for an edge
		FXVertexController v1 = new FXVertexController(new Vertex(0, 0, 0),
				new FXVertexView(new Vertex(0, 0, 0)));
		FXVertexController v2 = new FXVertexController(new Vertex(1, 1, 1),
				new FXVertexView(new Vertex(1, 1, 1)));
		LinearEdge edge = new LinearEdge(v1, v2);
		FXLinearEdgeView view = new FXLinearEdgeView(edge);

		// The view should start off opaque
		assertFalse(view.isWireframe());

		// Make the view transparent
		view.setWireframeMode(true);

		// Check that the transparency flag is set
		assertTrue(view.isWireframe());

		// Whether a clinder shape has been found while searching the JavaFX
		// node's children.
		boolean edgeFound = false;

		// Get the group containing the node
		Representation<Group> representation = view.getRepresentation();

		// Search all of the node's children
		for (Node node : (representation.getData()).getChildren()) {

			// If the child is a 3D shape, it should be transparent
			if (node instanceof Shape3D) {
				assertTrue(((Shape3D) node).getDrawMode() == DrawMode.LINE);
				edgeFound = true;
				break;
			}
		}

		// A child box corresponding to the cube should have been found
		assertTrue(edgeFound);
	}
}
