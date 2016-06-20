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

import javafx.geometry.Point3D;
import javafx.scene.shape.TriangleMesh;

/**
 * This extension of TriangleMesh creates a two dimensional line between two
 * points in three dimensional space for JavaFX.
 * 
 * @author Robert Smith
 *
 */
public class LineTriangleMesh extends TriangleMesh {

	/**
	 * The default constructor which sets up the mesh to display a line.
	 * 
	 * @param start
	 *            The line's start point.
	 * @param end
	 *            The line's end point.
	 */
	public LineTriangleMesh(Point3D start, Point3D end) {
		super();

		// Get the angle between the two points
		Point3D perp = start.crossProduct(end);

		// Remove the y component from the vector and normalize it to length 1.
		perp.add(0, -1 * perp.getY(), 0);
		perp = perp.normalize();

		// Get the start point's coordinates
		float startX = (float) start.getX();
		float startY = (float) start.getY();
		float startZ = (float) start.getZ();

		// Get the end point's coordinates
		float endX = (float) end.getX();
		float endY = (float) end.getY();
		float endZ = (float) end.getZ();

		// Get the perpendicular vector's x and y amounts
		float perpX = (float) perp.getX();
		float perpZ = (float) perp.getZ();

		// If the line would have no width, instead set it to draw along the x
		// axis.
		if (perpX == 0 && perpZ == 0) {
			perpX = 1;
		}

		// Set the points array. The array will form a rectangle centered along
		// the line, with the perpendicular vector providing the x and z
		// direction offsets to give the rectangle its width.
		getPoints().addAll(new float[] { startX - perpX, startY, startZ - perpZ,
				startX + perpX, startY, startZ + perpZ, endX - perpX, endY,
				endZ - perpZ, endX + perpX, endY, endZ + perpZ });

		// There will be no texture on the line, so add a dummy coordinate
		// instead.
		getTexCoords().addAll(new float[] { 0, 0 });

		// Create faces for the line. There will be two triangles, which
		// together will form a rectangle. Each triangle will be drawn twice,
		// facing opposite directions each time, to ensure that the line is
		// displayed in the proper color from both directions.
		getFaces().addAll(new int[] { 0, 0, 1, 0, 2, 0, 1, 0, 0, 0, 2, 0, 1, 0,
				2, 0, 3, 0, 2, 0, 1, 0, 3, 0 });
	}
}
