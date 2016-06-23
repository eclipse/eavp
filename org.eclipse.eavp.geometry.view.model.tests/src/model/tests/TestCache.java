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
package model.tests;

import org.eclipse.emf.common.util.EList;

import geometry.Triangle;
import model.impl.MeshCacheImpl;

/**
 * A simple implementation of MeshCache that provides some default values for
 * testing.
 * 
 * @author Robert Smith
 *
 * @generated NOT
 */
public class TestCache extends MeshCacheImpl<String> {

	/**
	 * The default constructor.
	 */
	public TestCache() {

		// Initialize the TestCache with two values, "test1" with a value of
		// "value1" and "test2" with a value of "value2".
		typeCache.put("test1", "value1");
		typeCache.put("test2", "value2");
	}

	@Override
	public String getMesh(EList<Triangle> triangles) {

		// TODO store this mesh with a key so that it can be reused in future
		// calls
		String mesh = "";

		for (Triangle tri : triangles) {
			mesh += tri.getNormal();
			mesh += tri.getVertices().get(0);
			mesh += tri.getVertices().get(1);
			mesh += tri.getVertices().get(2);
		}

		return mesh;
	}
}
