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
package org.eclipse.eavp.geometry.view.javafx.render.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.eclipse.eavp.geometry.view.javafx.render.FXMeshCache;
import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.EList;
import org.eclipse.january.geometry.GeometryFactory;
import org.eclipse.january.geometry.Triangle;
import org.eclipse.january.geometry.Vertex;
import org.junit.Test;

import javafx.collections.ObservableFloatArray;
import javafx.scene.shape.ObservableFaceArray;
import javafx.scene.shape.TriangleMesh;

/**
 * A class to test the functionality of the FXMeshCache
 * 
 * @author Robert Smith
 *
 */
public class FXMeshCacheTester {

	/**
	 * Test that each type is given an appropriate kind of mesh.
	 */
	@Test
	public void checkTypeMeshes() {

		// The cache for testing
		FXMeshCache cache = new FXMeshCache();

		// The cache should return a TriangleMesh for each of these valid types
		assertNotNull(cache.getMesh("cube"));
		assertNotNull(cache.getMesh("cylinder"));
		assertNotNull(cache.getMesh("sphere"));
		assertNotNull(cache.getMesh("tube"));

		// Try to get meshes for types that will not be recognized by the mesh.
		// This should result in a null return.
		assertNull(cache.getMesh("not in the cache"));
		assertNull(cache.getMesh((String) null));
	}

	/**
	 * Test that the mesh can create a mesh for a set of triangles correctly.
	 */
	@Test
	public void checkTriangleMeshes() {

		// The cache for testing
		FXMeshCache cache = new FXMeshCache();

		// Create a triangle with vertices (1,1,1), (1, 2, 1), and (1, 1, 3)
		Triangle tri1 = GeometryFactory.eINSTANCE.createTriangle();
		Vertex v1 = GeometryFactory.eINSTANCE.createVertex();
		v1.setX(1);
		v1.setY(1);
		v1.setZ(1);
		Vertex v2 = GeometryFactory.eINSTANCE.createVertex();
		v2.setX(1);
		v2.setY(2);
		v2.setZ(1);
		Vertex v3 = GeometryFactory.eINSTANCE.createVertex();
		v3.setX(1);
		v3.setY(1);
		v3.setZ(3);
		tri1.getVertices().add(v1);
		tri1.getVertices().add(v2);
		tri1.getVertices().add(v3);

		// Create a triangle with vertices (2, 2, 3), (1, 2, 1), and (1, 1, 3).
		// We cannot reuse v2 and v3 because we cannot add the same vertex to
		// more than one list
		Triangle tri2 = GeometryFactory.eINSTANCE.createTriangle();
		Vertex v22 = GeometryFactory.eINSTANCE.createVertex();
		v22.setX(1);
		v22.setY(2);
		v22.setZ(1);
		Vertex v33 = GeometryFactory.eINSTANCE.createVertex();
		v33.setX(1);
		v33.setY(1);
		v33.setZ(3);
		Vertex v4 = GeometryFactory.eINSTANCE.createVertex();
		v4.setX(2);
		v4.setY(2);
		v4.setZ(3);
		tri2.getVertices().add(v22);
		tri2.getVertices().add(v33);
		tri2.getVertices().add(v4);

		// Create a list of triangles
		EList<Triangle> triangles = new BasicEList<Triangle>();
		triangles.add(tri1);
		triangles.add(tri2);

		// Let the cache construct a mesh
		TriangleMesh mesh = cache.getMesh(triangles);

		// Get the array of points
		ObservableFloatArray points = mesh.getPoints();

		// First point is (1, 1, 1)
		assertTrue(1f == points.get(0));
		assertTrue(1f == points.get(1));
		assertTrue(1f == points.get(2));

		// Second point is (1, 2, 1)
		assertTrue(1f == points.get(3));
		assertTrue(2f == points.get(4));
		assertTrue(1f == points.get(5));

		// Third point is (1, 1, 3)
		assertTrue(1f == points.get(6));
		assertTrue(1f == points.get(7));
		assertTrue(3f == points.get(8));

		// Fourth point is (2, 2, 3)
		assertTrue(2f == points.get(9));
		assertTrue(2f == points.get(10));
		assertTrue(3f == points.get(11));

		// The the array of texture cordinates
		ObservableFloatArray texCoords = mesh.getTexCoords();

		// There should be one texture coordinate
		assertTrue(0f == texCoords.get(0));
		assertTrue(0f == texCoords.get(1));

		// Get the faces
		ObservableFaceArray faces = mesh.getFaces();

		// The first face is between vertices 0, 1, and 2. There are 0s as dummy
		// texture values between all vertices of all faces.
		assertTrue(0 == faces.get(0));
		assertTrue(0 == faces.get(1));
		assertTrue(1 == faces.get(2));
		assertTrue(0 == faces.get(3));
		assertTrue(2 == faces.get(4));
		assertTrue(0 == faces.get(5));

		// The second face is between vertices 1, 2, and 3
		assertTrue(1 == faces.get(6));
		assertTrue(0 == faces.get(7));
		assertTrue(2 == faces.get(8));
		assertTrue(0 == faces.get(9));
		assertTrue(3 == faces.get(10));
		assertTrue(0 == faces.get(11));

		// The second half of the array is a mirror for the first half. Each
		// faces is repeated with the triangles specified in the opposite order.
		assertTrue(1 == faces.get(12));
		assertTrue(0 == faces.get(13));
		assertTrue(0 == faces.get(14));
		assertTrue(0 == faces.get(15));
		assertTrue(2 == faces.get(16));
		assertTrue(0 == faces.get(17));

		assertTrue(2 == faces.get(18));
		assertTrue(0 == faces.get(19));
		assertTrue(1 == faces.get(20));
		assertTrue(0 == faces.get(21));
		assertTrue(3 == faces.get(22));
		assertTrue(0 == faces.get(23));

		// Try an empty list. The result should be an empty mesh
		TriangleMesh emptyMesh = cache.getMesh(new BasicEList<Triangle>());
		assertEquals(0, emptyMesh.getPoints().size());

		// Null should also result in an empty mesh
		TriangleMesh nullMesh = cache.getMesh((BasicEList<Triangle>) null);
		assertEquals(0, nullMesh.getPoints().size());
	}
}
