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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javafx.scene.shape.TriangleMesh;

/**
 * A class which supplies a JavaFX Triangle Mesh describing a tube.
 * 
 * @author Robert Smith
 *
 */
public class FXTubeMesh implements IFXShapeMesh {

	/**
	 * Logger for handling event messages and other information.
	 */
	private static final Logger logger = LoggerFactory
			.getLogger(FXTubeMesh.class);

	/**
	 * The tube's height
	 */
	private final double HEIGHT = 2;

	/**
	 * The radius of the hole in the tube
	 */
	private final double INNER_RADIUS = 0.5;

	/**
	 * The tube's radius
	 */
	private final double OUTER_RADIUS = 1;

	/**
	 * The number of sample points along the axis
	 */
	private final int SEGMENTS = 15;

	/**
	 * The number of sample points about the circumference
	 */
	private final int RESOLUTION = 25;

	/**
	 * The JavaFX mesh which represents the tube.
	 */
	TriangleMesh mesh;

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.eavp.geometry.view.javafx.render.IFXShapeMesh#getMesh()
	 */
	@Override
	public TriangleMesh getMesh() {

		// Initialize the mesh
		mesh = new TriangleMesh();

		// The y coordinate of the pipe's bottom edge
		float base = (float) (HEIGHT / -2);

		// The vertices for the inner circle on the lower level
		float[] innerVertices = new float[RESOLUTION * 2];

		// The vertices for the outer circle on the lower level
		float[] outerVertices = new float[RESOLUTION * 2];

		// Get the XZ coordinates for the circles defining the tube's thickness
		innerVertices = MeshUtils.createCircle((float) INNER_RADIUS,
				RESOLUTION);
		outerVertices = MeshUtils.createCircle((float) OUTER_RADIUS,
				RESOLUTION);

		// The number of coordinates required to specify every 3D vertex for a
		// cylinder of height axialSamples with radialSample points per level,
		// including the base.
		int blockSize = (SEGMENTS + 1) * RESOLUTION * 3;

		// A list of all vertices in the mesh, in the ordering first vertex's x,
		// y, and z coordinates, second vertex's x, y, and z coordinates, etc.
		float[] vertices = new float[blockSize * 2];

		for (int i = 0; i <= SEGMENTS; i++) {
			for (int j = 0; j < RESOLUTION; j++) {

				// Index in the array where the current inner vertex's data will
				// start
				int innerIndex = i * RESOLUTION * 3 + j * 3;

				// X coordinate of the inner circle
				vertices[innerIndex] = innerVertices[j * 2];

				// Y coordinate of the current segment's height
				vertices[innerIndex
						+ 1] = (float) (base + i * HEIGHT / SEGMENTS);

				// Z coordinate of the inner circle
				vertices[innerIndex + 2] = innerVertices[j * 2 + 1];

				// Index in the array where the current outer vertex's data will
				// start
				int outerIndex = blockSize + i * RESOLUTION * 3 + j * 3;

				// X coordinate of the inner circle
				vertices[outerIndex] = outerVertices[j * 2];

				// Y coordinate of the current segment's height
				vertices[outerIndex
						+ 1] = (float) (base + i * HEIGHT / SEGMENTS);

				// Z coordinate of the inner circle
				vertices[outerIndex + 2] = outerVertices[j * 2 + 1];

			}
		}

		// Add the vertices to the mesh
		mesh.getPoints().addAll(vertices);

		// Do not apply a texture, instead add a single dummy coordinate.
		float[] texCoords = { 0, 0 };
		mesh.getTexCoords().addAll(texCoords);

		// A list of all the indices into the coordinate and texture coordinate
		// arrays needed to construct the faces for both sides of the tube.
		int[] indices = new int[SEGMENTS * RESOLUTION * 12 * 2];

		// An array of which contains the mesh's smoothing group numbers,
		// indexed by face number
		int[] smoothingGroups = new int[SEGMENTS * RESOLUTION * 2 * 2];

		// The index of the next empty location in the indices array
		int i = 0;

		// The number of vertices needed to fully specify one of the two
		// cylindrical faces of the tube mesh
		int vertexBlockSize = (SEGMENTS + 1) * RESOLUTION;

		// Construct the tube out of identical vertical segments, one at a time.
		for (int axialSegment = 0; axialSegment < SEGMENTS; axialSegment++) {

			// Add two triangles for each vertex along the current circle
			for (int radialSegment = 0; radialSegment < RESOLUTION; radialSegment++) {

				// Create the two triangles for the inner face:
				// Create a triangle between the current vertex, the next vertex
				// along the circle, and the vertex immediately above this one.
				// Include 0s as references to the dummy value in the texture
				// coordinate array.
				indices[i] = axialSegment * RESOLUTION + radialSegment;
				i++;
				indices[i] = 0;
				i++;
				indices[i] = axialSegment * RESOLUTION
						+ ((radialSegment + 1) % RESOLUTION);
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

				// Create a triangle between the current vertex, the vertex
				// immediately above it, and the last one along the circle from
				// that one. Include 0s as references to the dummy value in the
				// texture coordinate array.
				indices[i] = (axialSegment + 1) * RESOLUTION
						+ ((radialSegment + 1) % RESOLUTION);
				i++;
				indices[i] = 0;
				i++;
				indices[i] = (axialSegment + 1) * RESOLUTION + radialSegment;
				i++;
				indices[i] = 0;
				i++;
				indices[i] = axialSegment * RESOLUTION + radialSegment;
				i++;
				indices[i] = 0;

				// Set this face's smoothing group
				smoothingGroups[i / 6] = 1;
				i++;

				// Create the two triangles for the outer face:
				// Create a triangle between the current vertex, the next vertex
				// along the circle, and the vertex immediately above this one.
				// Include 0s as references to the dummy value in the texture
				// coordinate array.
				indices[i] = vertexBlockSize + (axialSegment + 1) * RESOLUTION
						+ ((radialSegment + 1) % RESOLUTION);
				i++;
				indices[i] = 0;
				i++;
				indices[i] = vertexBlockSize + axialSegment * RESOLUTION
						+ ((radialSegment + 1) % RESOLUTION);
				i++;
				indices[i] = 0;
				i++;
				indices[i] = vertexBlockSize + axialSegment * RESOLUTION
						+ radialSegment;
				i++;
				indices[i] = 0;

				// Set this face's smoothing group
				smoothingGroups[i / 6] = 1;
				i++;

				// Create a triangle between the current vertex, the vertex
				// immediately above it, and the last one along the circle from
				// that one. Include 0s as references to the dummy value in the
				// texture coordinate array.
				indices[i] = vertexBlockSize + axialSegment * RESOLUTION
						+ radialSegment;
				i++;
				indices[i] = 0;
				i++;
				indices[i] = vertexBlockSize + (axialSegment + 1) * RESOLUTION
						+ radialSegment;
				i++;
				indices[i] = 0;
				i++;
				indices[i] = vertexBlockSize + (axialSegment + 1) * RESOLUTION
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

		// If the radii are different, the tube also needs a top and bottom edge
		if (INNER_RADIUS != OUTER_RADIUS) {

			// Reset the index
			i = 0;

			// Create an array for four triangles per radial sample
			indices = new int[RESOLUTION * 4 * 6];

			// Create a new smoothing group array for the new faces
			smoothingGroups = new int[RESOLUTION * 4];

			// Add two triangles for each vertex along the current circle
			for (int radialSegment = 0; radialSegment < RESOLUTION; radialSegment++) {

				// Create the triangles for the bottom edge:
				// Create a triangle between the current vertex, the next vertex
				// along the circle, and the corresponding vertex on the other
				// edge. Include 0s as references to the dummy value in the
				// texture coordinate array.
				indices[i] = vertexBlockSize
						+ ((radialSegment + 1) % RESOLUTION);
				i++;
				indices[i] = 0;
				i++;
				indices[i] = ((radialSegment + 1) % RESOLUTION);
				i++;
				indices[i] = 0;
				i++;
				indices[i] = radialSegment;
				i++;
				indices[i] = 0;

				// Set this face's smoothing group
				smoothingGroups[i / 6] = 2;
				i++;

				// Create a triangle between the current vertex, the
				// corresponding vertex on the other edge, and the last one
				// along the circle from the other edge. Include 0s as
				// references to the dummy value in the texture coordinate
				// array.
				indices[i] = radialSegment;
				i++;
				indices[i] = 0;
				i++;
				indices[i] = vertexBlockSize + radialSegment;
				i++;
				indices[i] = 0;
				i++;
				indices[i] = vertexBlockSize
						+ ((radialSegment + 1) % RESOLUTION);
				i++;
				indices[i] = 0;

				// Set this face's smoothing group
				smoothingGroups[i / 6] = 2;
				i++;

				// Create the triangles for the top edge:
				// Create a triangle between the current vertex, the next vertex
				// along the circle, and the corresponding vertex on the other
				// edge. Include 0s as references to the dummy value in the
				// texture coordinate array.
				indices[i] = ((SEGMENTS) * RESOLUTION) + radialSegment;
				i++;
				indices[i] = 0;
				i++;
				indices[i] = ((SEGMENTS) * RESOLUTION)
						+ ((radialSegment + 1) % RESOLUTION);
				i++;
				indices[i] = 0;
				i++;
				indices[i] = ((SEGMENTS) * RESOLUTION) + vertexBlockSize
						+ ((radialSegment + 1) % RESOLUTION);
				i++;
				indices[i] = 0;

				// Set this face's smoothing group
				smoothingGroups[i / 6] = 2;
				i++;

				// Create a triangle between the current vertex, the vertex
				// immediately above it, and the last one along the circle from
				// that one. Include 0s as references to the dummy value in the
				// texture coordinate array.
				indices[i] = ((SEGMENTS) * RESOLUTION) + vertexBlockSize
						+ ((radialSegment + 1) % RESOLUTION);
				i++;
				indices[i] = 0;
				i++;
				indices[i] = ((SEGMENTS) * RESOLUTION) + vertexBlockSize
						+ radialSegment;
				i++;
				indices[i] = 0;
				i++;
				indices[i] = ((SEGMENTS) * RESOLUTION) + radialSegment;
				i++;
				indices[i] = 0;

				// Set this face's smoothing group
				smoothingGroups[i / 6] = 2;
				i++;
			}

			mesh.getFaces().addAll(indices);
			mesh.getFaceSmoothingGroups().addAll(smoothingGroups);
		}

		return mesh;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.eavp.geometry.view.javafx.render.IFXShapeMesh#getType()
	 */
	@Override
	public String getType() {
		return "tube";
	}

}
