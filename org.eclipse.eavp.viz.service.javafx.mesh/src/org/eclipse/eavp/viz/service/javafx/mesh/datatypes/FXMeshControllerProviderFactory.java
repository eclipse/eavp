/*******************************************************************************
 * Copyright (c) 2015 UT-Battelle, LLC.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   Robert Smith
 *******************************************************************************/
package org.eclipse.eavp.viz.service.javafx.mesh.datatypes;

import org.eclipse.eavp.viz.modeling.DetailedEdge;
import org.eclipse.eavp.viz.modeling.Edge;
import org.eclipse.eavp.viz.modeling.Face;
import org.eclipse.eavp.viz.modeling.LinearEdge;
import org.eclipse.eavp.viz.modeling.Vertex;
import org.eclipse.eavp.viz.modeling.base.IMesh;
import org.eclipse.eavp.viz.modeling.factory.BasicControllerProviderFactory;
import org.eclipse.eavp.viz.modeling.factory.IControllerProvider;
import org.eclipse.eavp.viz.service.mesh.datastructures.NekPolygonController;
import org.eclipse.eavp.viz.service.mesh.datastructures.NekPolygon;

/**
 * A factory which creates JavaFX specific AbstractViews and AbstractControllers
 * for an AbstractMeshComponent.
 * 
 * @author Robert Smith
 *
 */
public class FXMeshControllerProviderFactory
		extends BasicControllerProviderFactory {

	/**
	 * The default cosntructor.
	 */
	public FXMeshControllerProviderFactory() {
		super();

		// Set the EdgeMesh provider
		typeMap.put(Edge.class,
				new IControllerProvider<FXEdgeController>() {
					@Override
					public FXEdgeController createController(IMesh model) {

						// If the model is an edge component, create an edge
						// with a
						// linear
						// edge view
						FXLinearEdgeView view = new FXLinearEdgeView(
								(Edge) model);
						return new FXEdgeController((Edge) model, view);
					}
				});

		// TODO find a way to avoid enumerating every subclass of EdgeMesh here
		// Copy the EdgeMesh provider to the other EdgeMesh classes
		typeMap.put(DetailedEdge.class, typeMap.get(Edge.class));
		typeMap.put(LinearEdge.class, typeMap.get(Edge.class));

		// Set the NekPolygonMesh provider
		typeMap.put(NekPolygon.class,
				new IControllerProvider<NekPolygonController>() {
					@Override
					public NekPolygonController createController(IMesh model) {

						// Create a NekPolygonController with a face view
						FXFaceView view = new FXFaceView(model);
						return new NekPolygonController((Face) model, view);
					}
				});

		// Set the VertexMesh provider
		typeMap.put(Vertex.class,
				new IControllerProvider<FXVertexController>() {
					@Override
					public FXVertexController createController(IMesh model) {

						// Create a vertex controller
						FXVertexView view = new FXVertexView(
								(Vertex) model);
						return new FXVertexController((Vertex) model, view);
					}
				});
	}

}
