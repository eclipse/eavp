/*******************************************************************************
 * Copyright (c) 2015 UT-Battelle, LLC.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   Robert Smith
 *******************************************************************************/
package org.eclipse.eavp.viz.modeling.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.eclipse.eavp.viz.modeling.LinearEdge;
import org.eclipse.eavp.viz.modeling.VertexController;
import org.eclipse.eavp.viz.modeling.Vertex;
import org.eclipse.eavp.viz.modeling.base.BasicView;
import org.eclipse.eavp.viz.modeling.properties.MeshCategory;
import org.eclipse.eavp.viz.modeling.properties.MeshProperty;
import org.junit.Test;

/**
 * A class to test LinearEdge's functionality
 * 
 * @author Robert Smith
 *
 */
public class LinearEdgeTester {

	/**
	 * Check that LinearEdgeMeshes are cloned correctly.
	 */
	@Test
	public void checkClone() {

		// Clone a mesh and check that the result is identical
		LinearEdge mesh = new LinearEdge();
		mesh.setProperty(MeshProperty.DESCRIPTION, "Property");
		LinearEdge clone = (LinearEdge) mesh.clone();
		assertTrue(mesh.equals(clone));
	}

	/**
	 * Tests the line's length as the vertices are changed
	 */
	@Test
	public void checkLength() {
		// Create the edge
		LinearEdge edge = new LinearEdge();

		// The edge should initially have length 0
		assertEquals(0, Double.compare(edge.getLength(), 0d));

		// Create some vertices
		VertexController vertex1 = new VertexController(new Vertex(0, 0, 0),
				new BasicView());
		VertexController vertex2 = new VertexController(new Vertex(1, 1, 1),
				new BasicView());
		VertexController vertex3 = new VertexController(new Vertex(2, 2, 2),
				new BasicView());

		// Add the first two vertices to the edge.
		edge.addEntityToCategory(vertex1, MeshCategory.VERTICES);
		edge.addEntityToCategory(vertex2, MeshCategory.VERTICES);

		// Check that the edge has the correct length
		assertTrue(Double.compare(1.73, edge.getLength()) <= .1d);

		// Replace the second vertex with the third
		edge.removeEntity(vertex2);
		edge.addEntityToCategory(vertex3, MeshCategory.VERTICES);

		// Check that the edge's length has been updated
		assertTrue(Double.compare(3.46, edge.getLength()) <= .1d);

	}
}
