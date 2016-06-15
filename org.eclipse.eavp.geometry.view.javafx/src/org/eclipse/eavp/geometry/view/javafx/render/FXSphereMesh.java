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

		//The array of points, in the order first point's x, y, and z coordinates, second point's x, y, and z, etc. There will be RESOLUTION many points per circle, and RESOLUTION many circles, though the top and bottom "circles" will be radius 0 and thus only a single point.
		float[] points = new float[(RESOLUTION * (RESOLUTION - 2) + 2) * 3]; 
		
		float radiusStepSize = 1 / (RESOLUTION - 1);
		
		float radius = radiusStepSize;
		
		for(int i = 0; i <= RESOLUTION; i ++){
			
			float[] circl = MeshUtils.createCircle(, samples)
		}
		
	}

}
