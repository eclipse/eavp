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
 * A class which gives a representation of a cylinder in JavaFX as a
 * TriangleMesh.
 * 
 * @author Robert Smith
 *
 */
public class FXCylinderMesh implements IFXShapeMesh {

	/**
	 * The resolution of the cylinder, measured in the number of points used to
	 * describe the circles at its ends.
	 */
	final private int RESOLUTION = 50;

	/**
	 * The number of vertical segments into which the mesh will be divided.
	 */
	final private int SEGMENTS = 10;

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.eavp.geometry.view.javafx.render.IFXShapeMesh#getMesh()
	 */
	@Override
	public TriangleMesh getMesh() {

		// Initialize the mesh
		TriangleMesh mesh = new TriangleMesh();

		// The x and z vertices for the cylinder
		float[] circle = new float[RESOLUTION * 2];

		// Get the unit circle's coordinates
		circle = MeshUtils.createCircle(1, RESOLUTION);

		// The number of coordinates required to specify every 3D vertex for a
		// cylinder of with SEGMENTS extra circles above the base circle and
		// RESOLUTION points per circle.
		int blockSize = (SEGMENTS + 1) * RESOLUTION * 3;

		// A list of all vertices in the mesh, in the ordering first vertex's x,
		// y, and z coordinates, second vertex's x, y, and z coordinates, etc.
		float[] vertices = new float[blockSize + 6];

		for (int i = 0; i <= SEGMENTS; i++) {
			for (int j = 0; j < RESOLUTION; j++) {

				// Index in the array where the current outer vertex's data will
				// start
				int index = i * RESOLUTION * 3 + j * 3;

				// X coordinate of the unit circle
				vertices[index] = circle[j * 2];

				// Y coordinate of the current segment's height
				vertices[index + 1] = -1 + i * 2 / SEGMENTS;

				// Z coordinate of the unit circle
				vertices[index + 2] = circle[j * 2 + 1];

			}
		}

		// Add a vertex at the center of the bottom circle
		vertices[blockSize] = 0;
		vertices[blockSize + 1] = -1f;
		vertices[blockSize + 2] = 0;

		// Add a vertex at the center of the top circle
		vertices[blockSize + 3] = 0;
		vertices[blockSize + 4] = 1;
		vertices[blockSize + 5] = 0;

		// Add the vertices to the mesh
		mesh.getPoints().addAll(vertices);

		// Do not apply a texture, instead add a single dummy coordinate.
		float[] texCoords = { 0, 0 };
		mesh.getTexCoords().addAll(texCoords);

		// A list of all the indices into the coordinate and texture coordinate
		// arrays needed to construct the faces for the side of the tube, three
		// coordinates per the two faces per one vertex on each of the circles.
		int[] indices = new int[SEGMENTS * RESOLUTION * 12];

		// An array of which contains the mesh's smoothing group numbers,
		// indexed by face number
		int[] smoothingGroups = new int[SEGMENTS * RESOLUTION * 2];

		// The index of the next empty location in the indices array
		int i = 0;

		// Construct the side out of identical vertical segments, one at a time.
		for (int axialSegment = 0; axialSegment < SEGMENTS; axialSegment++) {

			// Add two triangles for each vertex along the current circle
			for (int radialSegment = 0; radialSegment < RESOLUTION; radialSegment++) {

				// Create a triangle between the current vertex, the next vertex
				// along the circle, and the vertex immediately above this one.
				// Include 0s as references to the dummy value in the texture
				// coordinate array.
				indices[i] = (axialSegment + 1) * RESOLUTION
						+ ((radialSegment + 1) % RESOLUTION);
				i++;
				indices[i] = 0;
				i++;
				indices[i] = axialSegment * RESOLUTION
						+ ((radialSegment + 1) % RESOLUTION);
				i++;
				indices[i] = 0;
				i++;
				indices[i] = axialSegment * RESOLUTION + radialSegment;
				i++;
				indices[i] = 0;

				// Set this face's smoothing group
				smoothingGroups[i / 6] = 1;
				i++;

				// Create a triangle between the current vertex, the vertex
				// immediately above it, and the last one along the circle from
				// that one. Include 0s as references to the dummy value in the
				// texture coordinate array.
				indices[i] = axialSegment * RESOLUTION + radialSegment;
				i++;
				indices[i] = 0;
				i++;
				indices[i] = (axialSegment + 1) * RESOLUTION + radialSegment;
				i++;
				indices[i] = 0;
				i++;
				indices[i] = (axialSegment + 1) * RESOLUTION
						+ ((radialSegment + 1) % RESOLUTION);
				i++;
				indices[i] = 0;

				// Set this face's smoothing group
				smoothingGroups[i / 6] = 1;
				i++;
			}
		}

		// Add the indices and smoothing groups to the mesh
		mesh.getFaces().addAll(indices);
		mesh.getFaceSmoothingGroups().addAll(smoothingGroups);

		// Reset the index
		i = 0;

		// Create an array for two triangles per radial sample to define the top
		// and bottom
		indices = new int[RESOLUTION * 2 * 6];

		// Create a new smoothing group array for the new faces
		smoothingGroups = new int[RESOLUTION * 2];

		// Add two triangles for each vertex along the top and bottom circles
		for (int radialSegment = 0; radialSegment < RESOLUTION; radialSegment++) {

			// Create a triangle between the current vertex,the next vertex
			// along the circle, and the center point. Include 0s as
			// references to the dummy value in the texture coordinate array.
			indices[i] = blockSize / 3;
			i++;
			indices[i] = 0;
			i++;
			indices[i] = radialSegment;
			i++;
			indices[i] = 0;
			i++;
			indices[i] = ((radialSegment + 1) % RESOLUTION);
			i++;
			indices[i] = 0;

			// Set this face's smoothing group
			smoothingGroups[i / 6] = 2;
			i++;

			// Create a triangle between the current vertex,the next vertex
			// along the circle, and the center point. Include 0s as
			// references to the dummy value in the texture coordinate array.
			indices[i] = ((SEGMENTS) * RESOLUTION)
					+ ((radialSegment + 1) % RESOLUTION);
			i++;
			indices[i] = 0;
			i++;
			indices[i] = ((SEGMENTS) * RESOLUTION) + radialSegment;
			i++;
			indices[i] = 0;
			i++;
			indices[i] = blockSize / 3 + 1;
			i++;
			indices[i] = 0;

			// Set this face's smoothing group
			smoothingGroups[i / 6] = 2;
			i++;
		}

		// Add the top and bottom faces and smoothing groups to the mesh
		mesh.getFaces().addAll(indices);
		mesh.getFaceSmoothingGroups().addAll(smoothingGroups);

		return mesh;
	}
}
