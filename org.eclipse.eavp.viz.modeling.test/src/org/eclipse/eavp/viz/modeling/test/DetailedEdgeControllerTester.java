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

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.eclipse.eavp.viz.datastructures.VizObject.IManagedUpdateable;
import org.eclipse.eavp.viz.datastructures.VizObject.SubscriptionType;
import org.eclipse.eavp.viz.modeling.DetailedEdgeController;
import org.eclipse.eavp.viz.modeling.DetailedEdge;
import org.eclipse.eavp.viz.modeling.FaceController;
import org.eclipse.eavp.viz.modeling.Face;
import org.eclipse.eavp.viz.modeling.VertexController;
import org.eclipse.eavp.viz.modeling.Vertex;
import org.eclipse.eavp.viz.modeling.base.BasicView;
import org.eclipse.eavp.viz.modeling.properties.MeshCategory;
import org.eclipse.eavp.viz.modeling.properties.MeshProperty;
import org.junit.Test;

/**
 * A class to test the functionality of the FaceEdgeMesh
 * 
 * @author Robert Smith
 *
 */
public class DetailedEdgeControllerTester {

	/**
	 * Check that a FaceEdgeMesh does not register as a listener to the Faces it
	 * belongs to.
	 */
	@Test
	public void checkUpdates() {

		// Create an edge
		DetailedEdge edgeMesh = new DetailedEdge();
		TestEdge edge = new TestEdge(edgeMesh, new BasicView());
		Vertex vertexMesh1 = new Vertex(0, 0, 0);
		VertexController vertex1 = new VertexController(vertexMesh1,
				new BasicView());
		Vertex vertexMesh2 = new Vertex(1, 1, 1);
		VertexController vertex2 = new VertexController(vertexMesh2,
				new BasicView());
		edge.addEntityToCategory(vertex1, MeshCategory.VERTICES);
		edge.addEntityToCategory(vertex2, MeshCategory.VERTICES);

		// Clear the edge's updated state
		edge.wasUpdated();

		// Change a vertex and check that the edge was updated
		vertex1.setProperty(MeshProperty.DESCRIPTION, "Value");
		assertTrue(edge.wasUpdated());

		// Add a face to the edge
		Face faceMesh = new Face();
		FaceController face = new FaceController(faceMesh, new BasicView());
		edge.addEntityToCategory(face, MeshCategory.FACES);

		// Reset the edge's updated state
		edge.wasUpdated();

		// Change the face and check that the edge didn't get updated
		face.setProperty(MeshProperty.DESCRIPTION, "Value");
		assertFalse(edge.wasUpdated());
	}

	/**
	 * Check that FaceEdgeControllers can be properly cloned.
	 */
	@Test
	public void checkClone() {

		// Create a cloned edge and check that it is identical to the original
		DetailedEdgeController edge = new DetailedEdgeController(
				new DetailedEdge(), new BasicView());
		edge.setProperty(MeshProperty.DESCRIPTION, "Property");
		DetailedEdgeController clone = (DetailedEdgeController) edge.clone();
		assertTrue(edge.equals(clone));
	}

	/**
	 * A simple extension of FaceEdgeMesh that stores whether or not it has
	 * received an update.
	 * 
	 * @author Robert Smith
	 *
	 */
	private class TestEdge extends DetailedEdgeController {

		/**
		 * THe default constructor.
		 * 
		 * @param model
		 * @param view
		 */
		public TestEdge(DetailedEdge model, BasicView view) {
			super(model, view);
		}

		/**
		 * Whether this class has received an update since the last time it was
		 * checked.
		 */
		private boolean updated = false;

		/*
		 * (non-Javadoc)
		 * 
		 * @see org.eclipse.eavp.viz.modeling.AbstractMesh#update(org.
		 * eclipse. ice.viz.service.datastructures.VizObject.IManagedUpdateable,
		 * org.eclipse.eavp.viz.service.datastructures.VizObject.
		 * SubscriptionType [])
		 */
		@Override
		public void update(IManagedUpdateable component,
				SubscriptionType[] type) {
			updated = true;
		}

		/**
		 * Checks if the object was updated and resets it to its orinigal state.
		 * 
		 * @return True if the object has received an update since the last time
		 *         this function was invoked. False otherwise.
		 */
		public boolean wasUpdated() {
			boolean temp = updated;
			updated = false;
			return temp;
		}
	}
}
