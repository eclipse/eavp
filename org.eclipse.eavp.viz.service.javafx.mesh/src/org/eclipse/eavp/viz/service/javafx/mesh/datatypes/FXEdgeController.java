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

import org.eclipse.eavp.viz.datastructures.VizObject.SubscriptionType;
import org.eclipse.eavp.viz.modeling.EdgeController;
import org.eclipse.eavp.viz.modeling.Edge;
import org.eclipse.eavp.viz.modeling.base.BasicView;
import org.eclipse.eavp.viz.modeling.base.IController;
import org.eclipse.eavp.viz.modeling.properties.IMeshProperty;
import org.eclipse.eavp.viz.modeling.properties.MeshCategory;
import org.eclipse.eavp.viz.modeling.properties.MeshProperty;
import org.eclipse.eavp.viz.service.mesh.datastructures.MeshEditorMeshProperty;
import org.junit.Ignore;

/**
 * An extension of edge that manages its vertices' states as being selected and
 * under construction.
 * 
 * @author Robert Smith
 *
 */
public class FXEdgeController extends EdgeController {

	/**
	 * The default constructor
	 * 
	 * @param model
	 *            The edge's model
	 * @param view
	 *            The edge's view
	 */
	public FXEdgeController(Edge model, BasicView view) {
		super(model, view);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.eavp.viz.modeling.AbstractController#setProperty( java.
	 * lang.String, java.lang.String)
	 */
	@Override
	public void setProperty(IMeshProperty property, String value) {

		// Lock notifications from changing own vertices
		updateManager.enqueue();

		// If the Edge's constructing or selected properties are being changed,
		// propagate that change to its vertices
		if (MeshEditorMeshProperty.UNDER_CONSTRUCTION.equals(property)
				|| MeshProperty.SELECTED.equals(property)) {

			for (IController vertex : model
					.getEntitiesFromCategory(MeshCategory.VERTICES)) {
				vertex.setProperty(property, value);
			}
		}

		super.setProperty(property, value);

		// Remove any property notifications from the edge's construction being
		// finished
		if (MeshEditorMeshProperty.UNDER_CONSTRUCTION.equals(property)) {
			updateManager.removeMesaage(SubscriptionType.PROPERTY);
		}

		// Empty the queue
		updateManager.flushQueue();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.eavp.viz.modeling.AbstractController#clone()
	 */
	@Override @Ignore
	public Object clone() {

		// Clone the model and view
		Edge modelClone = (Edge) model.clone();
		BasicView viewClone = (BasicView) view.clone();

		// Create a new controller for the clones and return it
		FXEdgeController clone = new FXEdgeController(modelClone, viewClone);
		return clone;
	}
}
