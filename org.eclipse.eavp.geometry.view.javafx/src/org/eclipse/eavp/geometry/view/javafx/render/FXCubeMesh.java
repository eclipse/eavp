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
 * A class which gives a representation of a cube in JavaFX as a TriangleMesh.
 * 
 * @author Robert Smith
 *
 */
public class FXCubeMesh implements IFXShapeMesh {

	@Override
	public TriangleMesh getMesh() {

		TriangleMesh mesh = new TriangleMesh();

		// The array of points in order x, y, z coordinates for the first
		// points, x, y, z for the second, etc.
		//
		// We will specify the points in two squares. Points 0-3 will be the
		// left hand square and 4-7 will be the right hand square. The cube will
		// be formed by combined the two through connecting points which are
		// congruent modulo 4.
		float[] points = {

				// Point 0
				-1, -1, -1,

				// Point 1
				-1, -1, 1,

				// Point 2
				-1, 1, -1,

				// Point 3
				-1, 1, 1,

				// Point 4
				1, -1, -1,

				// Point 5
				1, -1, 1,

				// Point 6
				1, 1, -1,

				// Point 7
				1, 1, 1, };

		mesh.getPoints().setAll(points);

		// We will provide no default texture to the mesh so set some dummy
		// coordinates instead
		mesh.getTexCoords().addAll(0f, 0f);

		// The array of faces, structured as each face being a block of vertex1,
		// 0, vertex2, 0, vertex3, 0, where the vertices are indices into the
		// points array and the zeroes are indices for the single point in the
		// texture coordinate array.
		int[] faces = {

				// Left side Face 1
				2, 0, 0, 0, 1, 0,

				// Left side Face 2
				3, 0, 2, 0, 1, 0,

				// Top side Face 1
				2, 0, 3, 0, 7, 0,

				// Top side Face 2
				2, 0, 7, 0, 6, 0,

				// Front side Face 1
				2, 0, 6, 0, 0, 0,

				// Front side Face 2
				6, 0, 4, 0, 0, 0,

				// Back side Face 1
				7, 0, 3, 0, 1, 0,

				// Back side Face 2
				7, 0, 1, 0, 5, 0,

				// Bottom side Face 1
				1, 0, 0, 0, 5, 0,

				// Bottom side Face 2
				5, 0, 0, 0, 4, 0,

				// Right side Face 1
				6, 0, 7, 0, 4, 0,

				// Right side Face 2
				7, 0, 5, 0, 4, 0 };

		mesh.getFaces().setAll(faces);

		// The smoothing groups, with smoothingGroups[i] giving the smoothing
		// group for the face with index i.
		int[] smoothingGroups = {

				// Left side
				0, 0,

				// Top side
				1, 1,

				// Front side
				2, 2,

				// Back side
				3, 3,

				// Bottom side
				4, 4,

				// Right side
				5, 5 };

		mesh.getFaceSmoothingGroups().setAll(smoothingGroups);

		return mesh;
	}

}
