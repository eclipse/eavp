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
package org.eclipse.eavp.viz.service.javafx.mesh.datatypes;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.eclipse.eavp.viz.modeling.Face;
import org.junit.Test;

/**
 * A class to test the functionality of the FXFaceView.
 * 
 * @author Robert Smith
 *
 */
public class FXFaceViewTester {

	/**
	 * Test that FXFaceViews are cloned correctly
	 */
	@Test
	public void checkClone() {

		// Create a cloned view and check that it is identical to the original
		Face mesh = new Face();
		FXFaceView view = new FXFaceView(mesh);
		Object clone = view.clone();

		// A view is not necessarily equal to its clone, as JavaFX classes lack
		// an equals() implementation. Instead check the clone's type.
		assertTrue(clone instanceof FXFaceView);
	}

	/**
	 * Test that the view correctly manages its own state for transparency and
	 * wireframe.
	 */
	@Test
	public void checkState() {

		// Create the face view
		Face mesh = new Face();
		FXFaceView view = new FXFaceView(mesh);

		// Check that the view starts out solid and visible
		assertFalse(view.isTransparent());
		assertFalse(view.isWireframe());

		// Set the view to transparent
		view.setTransparentMode(true);
		assertTrue(view.isTransparent());

		// Set the view to opaque
		view.setTransparentMode(false);
		assertFalse(view.isTransparent());

		// Set the view to wireframe
		view.setWireframeMode(true);
		assertTrue(view.isWireframe());

		// Set the view to solid
		view.setWireframeMode(false);
		assertFalse(view.isWireframe());
	}
}
