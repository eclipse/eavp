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
package org.eclipse.eavp.viz.service.geometry.reactor.test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.eclipse.eavp.viz.modeling.base.BasicView;
import org.eclipse.eavp.viz.modeling.properties.MeshProperty;
import org.eclipse.eavp.viz.service.geometry.reactor.JunctionController;
import org.eclipse.eavp.viz.service.geometry.reactor.JunctionRefactor;
import org.junit.Test;

/**
 * A class to test the functionality of the JunctionController
 * 
 * @author Robert Smith
 *
 */
public class JunctionControllerTester {

	/**
	 * Check that the part is cloned correctly.
	 */
	@Test
	public void checkClone() {

		// Create a junction
		JunctionController exchanger = new JunctionController(
				new JunctionRefactor(), new BasicView());
		exchanger.setProperty(MeshProperty.INNER_RADIUS, "Property");

		// Clone it and check that they are identical
		JunctionController clone = (JunctionController) exchanger.clone();
		assertTrue(exchanger.equals(clone));
	}
	
	/**
	 * Test that the shape can be made transparent.
	 */
	public void checkTransparency() {

		// Create a junction
		JunctionRefactor mesh = new JunctionRefactor();
		JunctionController shape = new JunctionController(mesh, new TestView());

		// The view should start off opaque
		assertFalse(shape.isTransparent());

		// Make the view transparent
		shape.setTransparentMode(true);

		// Check that the transparency flag is set
		assertTrue(shape.isTransparent());
	}

	/**
	 * Test that the shape can rendered in wireframe mode
	 */
	public void checkWireframe() {

		// Create a junction
		JunctionRefactor mesh = new JunctionRefactor();
		JunctionController shape = new JunctionController(mesh, new TestView());

		// The view should start off drawn normally
		assertFalse(shape.isWireframe());

		// Make the shape wireframe
		shape.setWireframeMode(true);

		// Check that the wireframe flag has been set
		assertTrue(shape.isWireframe());
	}
}
