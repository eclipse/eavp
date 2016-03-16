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
package org.eclipse.eavp.viz.modeling.test;

import static org.junit.Assert.assertTrue;

import org.eclipse.eavp.viz.modeling.ShapeController;
import org.eclipse.eavp.viz.modeling.ShapeMesh;
import org.eclipse.eavp.viz.modeling.base.BasicView;
import org.eclipse.eavp.viz.modeling.properties.MeshProperty;
import org.junit.Test;

/**
 * A class to test the functionality of ShapeController.
 * 
 * @author Robert Smith
 *
 */
public class ShapeControllerTester {

	/**
	 * Check that ShapeControllers can be properly cloned.
	 */
	@Test
	public void checkClone() {

		// Create a cloned shape and check that it is identical to the original
		ShapeController shape = new ShapeController(new ShapeMesh(),
				new BasicView());
		shape.setProperty(MeshProperty.DESCRIPTION, "Property");
		ShapeController clone = (ShapeController) shape.clone();
		assertTrue(shape.equals(clone));
	}
}
