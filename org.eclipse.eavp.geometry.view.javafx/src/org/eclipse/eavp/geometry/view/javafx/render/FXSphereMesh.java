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

import org.eclipse.eavp.viz.modeling.MeshUtils;

import javafx.scene.shape.TriangleMesh;

/**
 * A class which supplies a JavaFX TriangleMesh describing a sphere.
 * 
 * @author Robert Smith
 *
 */
public class FXSphereMesh implements IFXShapeMesh {

	/**
	 * A constant defining how many vertices will be rendered along the sphere's
	 * circumference.
	 */
	private final static int RESOLUTION = 25;

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.eavp.geometry.view.javafx.render.IFXShapeMesh#getMesh()
	 */
	@Override
	public TriangleMesh getMesh() {

		// The mesh being constructed
		TriangleMesh mesh = new TriangleMesh();

		// The array of points, in the order first point's x, y, and z
		// coordinates, second point's x, y, and z, etc. There will be
		// RESOLUTION many points per circle, and RESOLUTION many circles,
		// though the top and bottom "circles" will be radius 0 and thus only a
		// single point.
		float[] points = new float[(RESOLUTION * (RESOLUTION - 2) + 2) * 3];

		// The amount of change between one layer and the next, both in the
		// layer's height and the radius of the circle at that height
		float stepSize = 2f / (RESOLUTION - 1);

		// The radius starts off one step above 0.
		float radius;

		// The height starts one step above the sphere's nadir at height -1
		float height;

		// Add each circle's points to the sphere
		for (int i = 0; i < RESOLUTION - 2; i++) {

			// Get the radius of the sphere at the proper height. For the bottom
			// half of the sphere, the radius will increase from zero. For the
			// top half, it will decrease from one. At exactly half way through
			// the sphere, the radius will be exactly 1.
			if (i < (RESOLUTION - 3) / 2) {
				radius = (float) Math
						.sqrt(1f - (Math.pow(1f - (stepSize * (i + 1f)), 2)));
			} else if (i > (RESOLUTION - 1) / 2 - 1) {
				radius = (float) Math
						.sqrt(1f - (Math.pow(1f - (((i + 1f) * stepSize)), 2)));
			} else {
				radius = 1f;
			}

			// The height is one step above -1 for each circle
			height = -1 + ((i + 1) * stepSize);

			// Create a circle of the correct radius
			float[] circle = MeshUtils.createCircle(radius, RESOLUTION);

			// Add the circle's coordinates to the points as its X and Z values,
			// with Y values given by the current height
			for (int j = 0; j < RESOLUTION; j++) {
				points[(RESOLUTION * i + j) * 3] = circle[j * 2];
				points[(RESOLUTION * i + j) * 3 + 1] = height;
				points[(RESOLUTION * i + j) * 3 + 2] = circle[j * 2 + 1];
			}

			// In the bottom half, the circles on the sphere increase in size up
			// to the sphere's radius, and decrease from the full radius down to
			// a point on the top half.
			if (i <= RESOLUTION / 2) {
				radius = (float) Math.pow(stepSize, (i + 1) * 2);
			} else {
				radius = (float) Math.pow(stepSize, (RESOLUTION - i + 1) * 2);
			}

			// The next circle will be one step above this one.
			height += stepSize;

		}

		// The index of the lowest point on the sphere
		int nadir = RESOLUTION * (RESOLUTION - 2);

		// The index of the highest point on the sphere
		int apex = nadir + 1;

		// Finally add the top and bottom points, (0, -1, 0) and (0, 1, 0)
		points[nadir * 3] = 0;
		points[nadir * 3 + 1] = -1;
		points[nadir * 3 + 2] = 0;
		points[nadir * 3 + 3] = 0;
		points[nadir * 3 + 4] = 1;
		points[nadir * 3 + 5] = 0;

		// Set the mesh's points.
		mesh.getPoints().setAll(points);

		// We will not provide a texture by default, so add a dummy texture
		// coordinate instead.
		mesh.getTexCoords().setAll(new float[] { 0f, 0f });

		// The array of faces as indices into the points and texture coordinate
		// arrays. There are six points per face, and two faces for each of the
		// RESOLUTION many points on the RESOLUTION many layers, except the top
		// and bottom layers which have only half that many.
		int[] faces = new int[RESOLUTION * (RESOLUTION - 2) * 12];

		// For each of the resolution layers of the sphere, excluding the
		// special cases of the top and bottom ones...
		for (int i = 0; i < RESOLUTION - 3; i++) {

			// Add two faces at each point
			for (int j = 0; j < RESOLUTION; j++) {

				// The first face will go from the point to the next one along
				// the circle to the point above the original on the next circle
				// up. include 0s as dummy indices into the texture.
				faces[(i * RESOLUTION + j) * 12] = i * RESOLUTION + j;
				faces[(i * RESOLUTION + j) * 12 + 1] = 0;
				faces[(i * RESOLUTION + j) * 12 + 2] = (i + 1) * RESOLUTION + j;
				faces[(i * RESOLUTION + j) * 12 + 3] = 0;
				faces[(i * RESOLUTION + j) * 12 + 4] = (j != RESOLUTION - 1)
						? i * RESOLUTION + j + 1 : i * RESOLUTION;
				faces[(i * RESOLUTION + j) * 12 + 5] = 0;

				// The second face will go from the point to the one above it on
				// the next circle up to the point before that one on that
				// higher circle. Include 0s as dummy indices into the texture.
				faces[(i * RESOLUTION + j) * 12 + 6] = i * RESOLUTION + j;
				faces[(i * RESOLUTION + j) * 12 + 7] = 0;
				faces[(i * RESOLUTION + j) * 12 + 8] = (j != 0)
						? (i + 1) * RESOLUTION + j - 1
						: (i + 2) * RESOLUTION - 1;
				faces[(i * RESOLUTION + j) * 12 + 9] = 0;
				faces[(i * RESOLUTION + j) * 12 + 10] = (i + 1) * RESOLUTION
						+ j;
				faces[(i * RESOLUTION + j) * 12 + 11] = 0;
			}
		}

		// The index of the start of the block of the faces on the bottom of the
		// sphere
		int indexBottomFaces = RESOLUTION * (RESOLUTION - 3) * 12;

		// The index of the start of the block of the faces on the top of the
		// sphere
		int indexTopFaces = indexBottomFaces + (RESOLUTION * 6);

		// The index of the start of the block of points on the highest circle
		// in the sphere
		int indexTopPoints = RESOLUTION * (RESOLUTION - 3);

		// Add the faces from the top and bottom circles to the top and bottom
		// points
		for (int i = 0; i < RESOLUTION; i++) {

			// Create a face between two points on the bottom circle and the
			// nadir. Include 0s as dummy indices into the texture.
			faces[indexBottomFaces + (i * 6)] = i;
			faces[indexBottomFaces + (i * 6) + 1] = 0;
			faces[indexBottomFaces + (i * 6) + 2] = (i + 1) % RESOLUTION;
			faces[indexBottomFaces + (i * 6) + 3] = 0;
			faces[indexBottomFaces + (i * 6) + 4] = nadir;
			faces[indexBottomFaces + (i * 6) + 5] = 0;

			// Create a face between two points on the top circle and the apex.
			// Include 0s as dummy indices into the texture.
			faces[indexTopFaces + (i * 6)] = indexTopPoints
					+ ((i + 1) % RESOLUTION);
			faces[indexTopFaces + (i * 6) + 1] = 0;
			faces[indexTopFaces + (i * 6) + 2] = indexTopPoints + i;
			faces[indexTopFaces + (i * 6) + 3] = 0;
			faces[indexTopFaces + (i * 6) + 4] = apex;
			faces[indexTopFaces + (i * 6) + 5] = 0;
		}

		// Set the finished faces to the mesh
		mesh.getFaces().setAll(faces);

		return mesh;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.eavp.geometry.view.javafx.render.IFXShapeMesh#getType()
	 */
	@Override
	public String getType() {
		return "sphere";
	}

}
