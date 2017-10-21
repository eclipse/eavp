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
package org.eclipse.eavp.tests.viz.service.javafx.geometry;

import org.eclipse.eavp.viz.service.javafx.geometry.FXGeometryAttachment;
import org.eclipse.eavp.viz.service.javafx.geometry.FXGeometryAttachmentManager;
import org.eclipse.january.geometry.Geometry;
import org.eclipse.january.geometry.GeometryFactory;
import org.eclipse.january.geometry.Sphere;
import org.junit.Test;

import javafx.scene.Group;

/**
 * A class to test the functionality of FXGeometryAttachment.
 * 
 * @author Robert Smith
 *
 */
public class FXGeometryAttachmentTester {

	/**
	 * Checks that the attachment handles updates correctly.
	 */
	@Test
	public void checkUpdates() {

		// Create an attachment
		FXGeometryAttachment attachment = new FXGeometryAttachment(
				new FXGeometryAttachmentManager());

		// Create a sphere shape
		Sphere sphere = GeometryFactory.eINSTANCE.createSphere();
		sphere.setRadius(1);

		Geometry geometry = GeometryFactory.eINSTANCE.createGeometry();
		geometry.addNode(sphere);

		// Set the shape to the attachment
		attachment.addGeometry(geometry);

		// The attachment's JavaFX node should now contain the shape's JavaFX
		// representation
		((Group) attachment.getFxNode().getChildren().get(0)).getChildren()
				.contains(attachment.getRender(sphere).getMesh());
	}
}
