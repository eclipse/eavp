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

/**
 * An interface for objects which provide a single TriangleMesh that describes a
 * given shape.
 * 
 * @author Robert Smith
 *
 */
public interface IFXShapeMesh {

	/**
	 * Get the mesh representing this shape.
	 * 
	 * @return A TriangleMesh which represents the shape for this kind of
	 *         object.
	 */
	TriangleMesh getMesh();
}
