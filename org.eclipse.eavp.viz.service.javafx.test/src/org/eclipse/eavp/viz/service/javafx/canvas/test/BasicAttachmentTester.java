/*******************************************************************************
 * Copyright (c) 2016 UT-Battelle, LLC.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   Tony McCrary (tmccrary@l33tlabs.com)
 *******************************************************************************/
package org.eclipse.eavp.viz.service.javafx.canvas.test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.List;
import java.util.Map;

import org.eclipse.eavp.viz.service.javafx.canvas.BasicAttachment;
import org.eclipse.eavp.viz.service.javafx.scene.model.IAttachment;
import org.eclipse.eavp.viz.service.javafx.scene.model.INode;
import org.eclipse.january.geometry.Geometry;
import org.eclipse.january.geometry.GeometryFactory;
import org.eclipse.january.geometry.Shape;
import org.junit.Test;

/**
 * A class to test the functionality of AbstractAttachment.
 * 
 * @author Robert Smith
 *
 */
public class BasicAttachmentTester {

	/**
	 * Check that the AbstractAttachment's process of attaching/detaching to a
	 * node is working correctly.
	 */
	@Test
	public void checkAttachment() {

		BasicAttachment attachment = new BasicAttachment() {

			@Override
			public void removeGeometry(Geometry geom) {
			}

			@Override
			public Class<?> getType() {
				return null;
			}

			@Override
			protected void processShape(
					org.eclipse.january.geometry.INode shape) {
			}

			@Override
			protected void disposeShape(
					org.eclipse.january.geometry.INode shape) {
			}
		};

		// Create some AbstractControllers for the attachment to contain
		Geometry geometry1 = GeometryFactory.eINSTANCE.createGeometry();
		Geometry geometry2 = GeometryFactory.eINSTANCE.createGeometry();
		Shape shape1 = GeometryFactory.eINSTANCE.createShape();
		shape1.setName("shape1");
		Shape shape2 = GeometryFactory.eINSTANCE.createShape();
		shape2.setName("shape2");
		Shape shape3 = GeometryFactory.eINSTANCE.createShape();
		shape3.setName("shape3");

		// Geometry 1 has shape1, geometry2 has the other two shapes
		geometry1.addNode(shape1);
		geometry2.addNode(shape2);
		geometry2.addNode(shape3);

		// Add the first geometry to the attachment, the shapes should be null
		// since it is not attached to a node
		attachment.addGeometry(geometry1);
		assertTrue(attachment.getShapes(false).isEmpty());

		// Add a shape to the attachment and check that its in the correct list
		attachment.addShape(shape1);
		assertTrue(attachment.getShape(0) == shape1);

		// Add a geometry with multiple shapes and ensure its shapes weren't
		// added
		attachment.addGeometry(geometry2);
		assertTrue(attachment.getShapes(false).size() == 1);

		// Detach, re-add both geometries, attach again, and check that all the
		// shapes are now present
		TestNode node = new TestNode();
		attachment.detach(node);
		attachment.addGeometry(geometry1);
		attachment.addGeometry(geometry2);
		attachment.attach(node);
		assertTrue(attachment.getShape(0) == shape1);
		assertTrue(attachment.getShape(1) == shape2);
		assertTrue(attachment.getShape(2) == shape3);

		// Detach the node and check that the shapes have been cleared
		attachment.detach(node);
		assertTrue(attachment.getShapes(false).isEmpty());

		// Attach to a node again. The geometry list should also have been
		// cleared, and so the geometries' shapes should not be added upon the
		// attach
		attachment.attach(node);
		assertTrue(attachment.getShapes(false).isEmpty());

	}

	/**
	 * Test the getter and setter methods for the class's data members.
	 */
	@Test
	public void checkData() {

		BasicAttachment attachment = new BasicAttachment() {

			@Override
			public void removeGeometry(Geometry geom) {
			}

			@Override
			public Class<?> getType() {
				return null;
			}

			@Override
			protected void processShape(
					org.eclipse.january.geometry.INode shape) {
			}

			@Override
			protected void disposeShape(
					org.eclipse.january.geometry.INode shape) {
			}
		};

		// Check the immutable field
		attachment.setImmutable(false);
		assertFalse(attachment.isImmutable());
		attachment.setImmutable(true);
		assertTrue(attachment.isImmutable());

		// Check the visible field
		attachment.setVisible(false);
		assertFalse(attachment.isVisible());
		attachment.setVisible(true);
		assertTrue(attachment.isVisible());
	}

	/**
	 * A basic implementation of INode.
	 * 
	 * @author Robert Smith
	 *
	 */
	private class TestNode implements INode {

		@Override
		public INode getParent() {
			return null;
		}

		@Override
		public void setParent(INode parent) {
		}

		@Override
		public String getName() {
			return null;
		}

		@Override
		public void addChild(INode node) {
		}

		@Override
		public void removeChild(INode node) {
		}

		@Override
		public void removeAllChildren() {
		}

		@Override
		public List<INode> getChildren(boolean copy) {
			return null;
		}

		@Override
		public void setVisible(boolean visible) {
		}

		@Override
		public boolean isVisible() {
			return false;
		}

		@Override
		public void attach(IAttachment attachment) {
		}

		@Override
		public void detach(IAttachment attachment) {
		}

		@Override
		public boolean hasAttachment(Class<?> attachmentClass) {
			return false;
		}

		@Override
		public boolean supports(IAttachment attachment) {
			return false;
		}

		@Override
		public Map<Class<?>, List<IAttachment>> getAttachments() {
			return null;
		}

		@Override
		public List<IAttachment> getAttachments(Class<?> clazz) {
			return null;
		}

		@Override
		public Map<String, Object> getProperties() {
			return null;
		}

		@Override
		public Object getProperty(String key) {
			return null;
		}

		@Override
		public void setProperty(String key, Object value) {
		}

		@Override
		public boolean hasProperty(String key) {
			return false;
		}

	}
}
