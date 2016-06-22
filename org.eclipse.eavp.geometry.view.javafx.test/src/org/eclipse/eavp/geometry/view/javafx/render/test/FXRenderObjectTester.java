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
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.HashSet;
import java.util.Set;

import org.eclipse.eavp.geometry.view.javafx.render.FXRenderObject;
import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.EList;
import org.junit.Test;

import geometry.GeometryFactory;
import geometry.Shape;
import geometry.Triangle;
import geometry.Union;
import geometry.Vertex;
import geometry.impl.ShapeImpl;
import javafx.scene.shape.MeshView;
import javafx.scene.shape.TriangleMesh;
import model.impl.MeshCacheImpl;

/**
 * A class to test the functionality of the FXRenderObject.
 * 
 * @author Robert Smith
 *
 */
public class FXRenderObjectTester {

	/**
	 * Check that the object is properly initialized.
	 */
	@Test
	public void checkConstruction() {

		// The cache that will provide the render objects with their meshes
		TestCache cache = new TestCache();

		// Create a node with type "test".
		ShapeImpl testShape = new ShapeImpl() {

			@Override
			public String getType() {
				return "test";
			}
		};

		// Create a render object which will render based on type.
		FXRenderObject renderType = new FXRenderObject(testShape, cache);

		// The result should contain a MeshView based on mesh1 from the cache
		assertTrue(renderType.getRender().getChildren()
				.get(0) instanceof MeshView);
		assertTrue(cache.getMesh1() == cache.getLast());

		// Create a shape with the first set of triangles.
		Shape shape1 = GeometryFactory.eINSTANCE.createShape();
		shape1.getTriangles().addAll(cache.getSet1());

		// Create a render object based on the shape
		FXRenderObject renderTri1 = new FXRenderObject(shape1, cache);

		// The result should contain a MeshView based on mesh1 from the cache
		assertTrue(renderTri1.getRender().getChildren()
				.get(0) instanceof MeshView);
		assertTrue(cache.getMesh1() == cache.getLast());

		// Create a shape with the first set of triangles.
		Shape shape2 = GeometryFactory.eINSTANCE.createShape();
		shape2.getTriangles().addAll(cache.getSet2());

		// Create a render object based on the shape
		FXRenderObject renderTri2 = new FXRenderObject(shape2, cache);

		// The result should contain a MeshView based on mesh2 from the cache
		assertTrue(
				renderTri2.getMesh().getChildren().get(0) instanceof MeshView);
		assertTrue(cache.getMesh2() == cache.getLast());

		// Move the shape to another location
		Vertex vertex = GeometryFactory.eINSTANCE.createVertex();
		vertex.setX(3);
		vertex.setY(4);
		vertex.setZ(5);
		shape2.setCenter(vertex);

		// The mesh should have moved to the new point
		assertEquals(3d, renderTri2.getMesh().getTranslateX(), 0.01d);
		assertEquals(4d, renderTri2.getMesh().getTranslateY(), 0.01d);
		assertEquals(5d, renderTri2.getMesh().getTranslateZ(), 0.01d);
	}

	/**
	 * Test that the object will properly handle child objects based on its
	 * type.
	 */
	@Test
	public void checkChildren() {

		// The cache that will provide the render objects with their meshes
		TestCache cache = new TestCache();

		// Create a node with type "test".
		ShapeImpl testShape = new ShapeImpl() {

			@Override
			public String getType() {
				return "test";
			}
		};

		// Create a render object
		FXRenderObject render = new FXRenderObject(testShape, cache);

		// Create a child shape
		ShapeImpl childShape = new ShapeImpl() {

			@Override
			public String getType() {
				return "test";
			}
		};

		// Render the child shape
		FXRenderObject child = new FXRenderObject(childShape, cache);

		// Have the render handle the list of children
		EList<model.IRenderElement<javafx.scene.Group>> childList = new BasicEList<model.IRenderElement<javafx.scene.Group>>();
		childList.add(child);
		render.handleChildren(childList);

		// Since the render does not have an operation type, it should simply
		// ignore its children
		assertFalse(render.getMesh().getChildren().contains(child.getMesh()));

		// Create a union and render it
		Union union = GeometryFactory.eINSTANCE.createUnion();
		FXRenderObject unionRender = new FXRenderObject(union, cache);

		// Have the union handle the children
		unionRender.handleChildren(childList);

		// The union should have added the children to its own node
		assertTrue(
				unionRender.getMesh().getChildren().contains(child.getMesh()));

	}

	/**
	 * A simple MeshCache implementation for testing purposes. It comes
	 * initialized with some dummy data.
	 * 
	 * @author Robert Smith
	 *
	 */
	private class TestCache extends MeshCacheImpl<TriangleMesh> {

		/**
		 * The last trianglemesh retrieved from this cache.
		 */
		private TriangleMesh last;

		/**
		 * The mesh associated with the type "test" and with set1.
		 */
		private TriangleMesh mesh1;

		/**
		 * The mesh associated with set2.
		 */
		private TriangleMesh mesh2;

		/**
		 * The first triangle set.
		 */
		private Set<Triangle> set1;

		/**
		 * The second triangle set.
		 */
		private Set<Triangle> set2;

		/**
		 * The default constructor.
		 */
		private TestCache() {
			super();

			// Create a triangle mesh
			mesh1 = new TriangleMesh();
			mesh1.getPoints().addAll(0);
			typeCache.put("test", mesh1);

			// Create a triangle
			Triangle triangle = GeometryFactory.eINSTANCE.createTriangle();
			triangle.getVertices()
					.add(GeometryFactory.eINSTANCE.createVertex());
			triangle.getVertices().get(0).setX(2);

			// Create a second triangle
			Triangle triangle2 = GeometryFactory.eINSTANCE.createTriangle();
			triangle2.getVertices()
					.add(GeometryFactory.eINSTANCE.createVertex());
			triangle2.getVertices().get(0).setY(2);

			// Add the first triangle mesh and the first triangle list to the
			// caches with shared ID number 0.
			set1 = new HashSet<Triangle>();
			set1.add(triangle);
			set1.add(triangle2);
			sourceTriangleCache.put(0, set1);
			triangleCache.put(0, mesh1);

			// Create a second triangle mesh
			mesh2 = new TriangleMesh();
			mesh2.getPoints().addAll(1);

			// Create a third triangle
			Triangle triangle3 = GeometryFactory.eINSTANCE.createTriangle();
			triangle3.getVertices()
					.add(GeometryFactory.eINSTANCE.createVertex());
			triangle3.getVertices().get(0).setX(2);
			triangle3.getVertices().get(0).setZ(2);

			// Create a fourth triangle
			Triangle triangle4 = GeometryFactory.eINSTANCE.createTriangle();
			triangle4.getVertices()
					.add(GeometryFactory.eINSTANCE.createVertex());
			triangle4.getVertices().get(0).setY(2);
			triangle4.getVertices().get(0).setZ(2);

			// Create a second set
			set2 = new HashSet<Triangle>();
			set2.add(triangle3);
			set2.add(triangle4);

			// Add the second triangle mesh and the second triangle list to the
			// caches with shared ID number 1.
			sourceTriangleCache.put(1, set2);
			triangleCache.put(1, mesh2);
		}

		/**
		 * Getter method for the last mesh retrieved.
		 * 
		 * @return The mesh retrieved from this cache by the last call to one of
		 *         the getMesh() functions;
		 */
		public TriangleMesh getLast() {
			return last;
		}

		/**
		 * Getter method for the first mesh.
		 * 
		 * @return the first mesh.
		 */
		public TriangleMesh getMesh1() {
			return mesh1;
		}

		/**
		 * Getter method for the second mesh.
		 * 
		 * @return the second mesh.
		 */
		public TriangleMesh getMesh2() {
			return mesh2;
		}

		/**
		 * Getter method for the first set.
		 * 
		 * @return The first set.
		 */
		public Set<Triangle> getSet1() {
			return set1;
		}

		/**
		 * Getter method for the second set.
		 * 
		 * @return The second set.
		 */
		public Set<Triangle> getSet2() {
			return set2;
		}

		@Override
		public TriangleMesh getMesh(String type) {

			// Save the retrieved mesh
			last = super.getMesh(type);
			return last;
		}

		@Override
		public TriangleMesh getMesh(EList<Triangle> triangles) {

			// Save the retrieved mesh
			last = super.getMesh(triangles);
			return last;
		}
	}
}
