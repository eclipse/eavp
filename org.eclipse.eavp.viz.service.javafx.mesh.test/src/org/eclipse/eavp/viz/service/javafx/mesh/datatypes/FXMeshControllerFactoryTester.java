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

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.eclipse.eavp.viz.modeling.DetailedEdgeMesh;
import org.eclipse.eavp.viz.modeling.EdgeMesh;
import org.eclipse.eavp.viz.modeling.LinearEdgeMesh;
import org.eclipse.eavp.viz.modeling.VertexMesh;
import org.eclipse.eavp.viz.modeling.base.BasicMesh;
import org.eclipse.eavp.viz.modeling.base.IMesh;
import org.eclipse.eavp.viz.service.mesh.datastructures.NekPolygonController;
import org.eclipse.eavp.viz.service.mesh.datastructures.NekPolygonMesh;
import org.junit.Test;

/**
 * A class to test the functionality of the FXMeshControllerFactory
 * 
 * @author Robert Smith
 *
 */
public class FXMeshControllerFactoryTester {

	/**
	 * Check that the factory creates the correct controllers for the
	 * appropriate classes.
	 */
	@Test
	public void checkCreation() {

		// The factory to be tested
		FXMeshControllerProviderFactory factory = new FXMeshControllerProviderFactory();

		// Create an edge mesh and send it to the factory
		EdgeMesh edgeMesh = new EdgeMesh();
		FXEdgeController edge = (FXEdgeController) factory
				.createProvider(edgeMesh).createController(edgeMesh);

		// The controller's model should be the mesh and its view should be an
		// FXLinearEdgeView
		assertTrue(edge.getModel() == edgeMesh);
		assertTrue(edge.getView() instanceof FXLinearEdgeView);

		// Create a face edge mesh and send it to the factory
		DetailedEdgeMesh faceEdgeMesh = new DetailedEdgeMesh();
		FXEdgeController faceEdge = (FXEdgeController) factory
				.createProvider(faceEdgeMesh).createController(faceEdgeMesh);

		// The controller's model should be the mesh and its view should be an
		// FXLinearEdgeView
		assertTrue(faceEdge.getModel() == faceEdgeMesh);
		assertTrue(faceEdge.getView() instanceof FXLinearEdgeView);

		// Create a face edge mesh and send it to the factory
		LinearEdgeMesh linearEdgeMesh = new LinearEdgeMesh();
		FXEdgeController linearEdge = (FXEdgeController) factory
				.createProvider(linearEdgeMesh)
				.createController(linearEdgeMesh);

		// The controller's model should be the mesh and its view should be an
		// FXLinearEdgeView
		assertTrue(linearEdge.getModel() == linearEdgeMesh);
		assertTrue(linearEdge.getView() instanceof FXLinearEdgeView);

		// Create a Nek polygon mesh and send it to the factory
		NekPolygonMesh nekPolygonMesh = new NekPolygonMesh();
		NekPolygonController nekPolygon = (NekPolygonController) factory
				.createProvider(nekPolygonMesh)
				.createController(nekPolygonMesh);

		// The controller's model should be the mesh and its view should be a
		// FXFaceView
		assertTrue(nekPolygon.getModel() == nekPolygonMesh);
		assertTrue(nekPolygon.getView() instanceof FXFaceView);

		// Create a vertex mesh and send it to the factory
		VertexMesh vertexMesh = new VertexMesh();
		FXVertexController vertex = (FXVertexController) (factory
				.createProvider(vertexMesh).createController(vertexMesh));

		// The controller's model should be the mesh and its view should be a
		// FXVertexView
		assertTrue(vertex.getModel() == vertexMesh);
		assertTrue(vertex.getView() instanceof FXVertexView);

		// Try to send an unrecognized input mesh to the factory
		IMesh mesh = new BasicMesh();
		assertNull(factory.createProvider(mesh));
	}
}
