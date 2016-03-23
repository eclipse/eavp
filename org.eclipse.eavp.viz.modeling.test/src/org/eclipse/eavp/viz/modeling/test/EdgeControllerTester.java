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
package org.eclipse.eavp.viz.modeling.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.eclipse.eavp.viz.modeling.EdgeController;
import org.eclipse.eavp.viz.modeling.EdgeMesh;
import org.eclipse.eavp.viz.modeling.LinearEdgeMesh;
import org.eclipse.eavp.viz.modeling.VertexController;
import org.eclipse.eavp.viz.modeling.VertexMesh;
import org.eclipse.eavp.viz.modeling.base.BasicController;
import org.eclipse.eavp.viz.modeling.base.BasicView;
import org.eclipse.eavp.viz.modeling.base.IController;
import org.eclipse.eavp.viz.modeling.properties.MeshCategory;
import org.eclipse.eavp.viz.modeling.properties.MeshProperty;
import org.junit.Test;

/**
 * A class for testing EdgeController's functionality
 * 
 * @author Robert Smith
 *
 */
public class EdgeControllerTester {

	/**
	 * Checks that the edge correctly manages its vertices
	 */
	@Test
	public void checkVertices() {

		// Create the edge
		EdgeMesh edgeMesh = new LinearEdgeMesh();
		EdgeController edge = new EdgeController(edgeMesh, new BasicView());

		// The edge should initially have length 0
		assertEquals(0, Double.compare(edge.getLength(), 0d));

		// Try adding a non-vertex. It should be put in the Default category
		edge.addEntity(new BasicController());
		assertEquals(1,
				edge.getEntitiesFromCategory(MeshCategory.DEFAULT).size());

		// Create some vertices
		VertexController vertex1 = new VertexController(new VertexMesh(0, 0, 0),
				new BasicView());
		VertexController vertex2 = new VertexController(new VertexMesh(1, 1, 1),
				new BasicView());
		VertexController vertex3 = new VertexController(new VertexMesh(2, 2, 2),
				new BasicView());

		// Add all three vertices to the edge.
		edge.addEntityToCategory(vertex1, MeshCategory.VERTICES);
		edge.addEntityToCategory(vertex2, MeshCategory.VERTICES);
		edge.addEntityToCategory(vertex3, MeshCategory.VERTICES);

		// Check the Vertices category to ensure that the edge accepted the
		// first two vertices and ignored the third
		List<IController> vertices = edge
				.getEntitiesFromCategory(MeshCategory.VERTICES);
		assertTrue(vertices.contains(vertex1));
		assertTrue(vertices.contains(vertex2));
		assertFalse(vertices.contains(vertex3));
		assertEquals(2, vertices.size());

		// Replace the second vertex with the third
		edge.removeEntity(vertex2);
		edge.addEntityToCategory(vertex3, MeshCategory.VERTICES);

		// Check the Vertices category to ensure that the last vertex was
		// replaced
		vertices = edge.getEntitiesFromCategory(MeshCategory.VERTICES);
		assertTrue(vertices.contains(vertex1));
		assertFalse(vertices.contains(vertex2));
		assertTrue(vertices.contains(vertex3));
	}

	/**
	 * Check that the part is cloned correctly.
	 */
	@Test
	public void checkClone() {

		// Create an edge
		EdgeController edge = new EdgeController(new EdgeMesh(),
				new BasicView());
		edge.setProperty(MeshProperty.DESCRIPTION, "Property");

		// Clone it and check that they are identical
		EdgeController clone = (EdgeController) edge.clone();
		assertTrue(edge.equals(clone));
	}
}
