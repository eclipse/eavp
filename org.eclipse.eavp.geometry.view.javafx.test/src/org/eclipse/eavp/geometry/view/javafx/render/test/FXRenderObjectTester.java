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

import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.EList;

import geometry.GeometryFactory;
import geometry.Triangle;
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
	public void checkConstruction() {

	}

	private class testCache extends MeshCacheImpl<TriangleMesh> {

		public testCache(){
			super();
			
			
			TriangleMesh mesh1 = new TriangleMesh();
			mesh1.getPoints().addAll(0);
			typeCache.put("test", mesh1);
			
			TriangleMesh mesh2 = new TriangleMesh();
			mesh2.getPoints().addAll(1);
			triangleCache.put(0, mesh2);
			
			Triangle triangle = GeometryFactory.eINSTANCE.createTriangle();
			triangle.getVertices().add(GeometryFactory.eINSTANCE.createVertex());
			triangle.getVertices().get(0).setX(2);
			EList<Triangle> triangles = new BasicEList<Triangle>();
			//sourceTriangleCache.put(0,)
		}
	}
}
