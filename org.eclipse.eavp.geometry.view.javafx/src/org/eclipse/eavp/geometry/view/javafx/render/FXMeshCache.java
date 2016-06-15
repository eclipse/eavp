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
package org.eclipse.eavp.geometry.view.javafx.render;

import javafx.scene.shape.TriangleMesh;
import model.impl.MeshCacheImpl;

/**
 * A cache for triangle meshes for JavaFX. An FXMeshCache will handle two cases.
 * 
 * In the first, the cache is supplied with a String for a type of mesh. The
 * cache should supply the single mesh used for all meshes of that type, as
 * specified by an FXShapeMesh.
 * 
 * In the second, the cache is supplied with a set of Triangles. The cache
 * should either retrieve the existing TriangleMesh for that list or else it
 * will be responsible for creating a new TriangleMesh based on the geometry of
 * the input triangles.
 * 
 * @author Robert Smith
 *
 */
public class FXMeshCache extends MeshCacheImpl<TriangleMesh> {

	public FXMeshCache() {

	}

}
