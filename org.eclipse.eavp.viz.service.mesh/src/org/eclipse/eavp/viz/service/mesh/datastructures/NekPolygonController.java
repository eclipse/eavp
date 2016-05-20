/*******************************************************************************
 * Copyright (c) 2015 UT-Battelle, LLC.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   Initial API and implementation and/or initial documentation - Jay Jay Billings,
 *   Jordan H. Deyton, Dasha Gorin, Alexander J. McCaskey, Taylor Patterson,
 *   Claire Saunders, Matthew Wang, Anna Wojtowicz, Robert Smith
 *******************************************************************************/
package org.eclipse.eavp.viz.service.mesh.datastructures;

import java.util.HashMap;

import org.eclipse.eavp.viz.modeling.Face;
import org.eclipse.eavp.viz.modeling.FaceController;
import org.eclipse.eavp.viz.modeling.base.BasicController;
import org.eclipse.eavp.viz.modeling.base.BasicView;
import org.eclipse.eavp.viz.modeling.base.IController;
import org.eclipse.eavp.viz.modeling.properties.IMeshProperty;
import org.eclipse.eavp.viz.modeling.properties.MeshCategory;
import org.eclipse.eavp.viz.modeling.properties.MeshProperty;

/**
 * A Face which maintains the information needed for a Polygon in a Nek5000
 * mesh.
 * 
 * @author Jordan H. Deyton
 * @author Robert Smith
 *
 */
public class NekPolygonController extends FaceController {

	/**
	 * The nullary constructor
	 */
	public NekPolygonController() {
		super();
	}

	/**
	 * The default constructor
	 */
	public NekPolygonController(Face model, BasicView view) {
		super(model, view);

		// Set the default name, id, and description.
		model.setProperty(MeshProperty.NAME, "Polygon");
		model.setProperty(MeshProperty.DESCRIPTION, "");

	}

	/**
	 * Getter method for the map of edge properties.
	 * 
	 * @return A map from edge ID numbers to that edge's edge properties.
	 */
	public HashMap<Integer, EdgeProperties> getEdgeProperties() {
		return ((NekPolygon) model).getEdgeProperties();
	}

	/**
	 * Setter method for the map of edge properties.
	 * 
	 * @param edgeProperties
	 *            A map from edge ID numbers to that edge's edge properties.
	 */
	public void setEdgeProperties(
			HashMap<Integer, EdgeProperties> edgeProperties) {
		((NekPolygon) model).setEdgeProperties(edgeProperties);
	}

	/**
	 * <p>
	 * Gets a boundary condition for an edge of the polygon.
	 * </p>
	 * 
	 * @param edgeId
	 *            The ID of the edge that has a BoundaryCondition.
	 * @param variable
	 *            The name of the field variable whose BoundaryCondition is
	 *            sought.
	 * @return The edge's BoundaryCondition for this polygon, or null if the
	 *         edge ID is invalid.
	 */
	public void setBoundaryCondition(int edgeId, String variable,
			BoundaryCondition condition) {
		((NekPolygon) model).setBoundaryCondition(edgeId, variable, condition);
	}

	/**
	 * <p>
	 * Sets a passive scalar boundary condition for an edge of the polygon.
	 * </p>
	 * 
	 * @param edgeId
	 *            The ID of the edge that will have the new BoundaryCondition.
	 * @param variable
	 *            The name of the field variable whose boundary condition is
	 *            being set
	 * @param condition
	 *            The new BoundaryCondition.
	 */
	public BoundaryCondition getBoundaryCondition(int edgeId, String variable) {
		return ((NekPolygon) model).getBoundaryCondition(edgeId, variable);
	}

	/**
	 * <p>
	 * Sets the properties for the current polygon.
	 * </p>
	 * 
	 * @param materialId
	 *            The material ID of the current polygon.
	 * @param group
	 *            The group number of the current polygon.
	 */
	public void setPolygonProperties(String materialId, int group) {
		((NekPolygon) model).setPolygonProperties(materialId, group);
	}

	/**
	 * <p>
	 * Returns the properties for the current polygon.
	 * </p>
	 * 
	 * @return The properties for the current polygon.
	 */
	public PolygonProperties getPolygonProperties() {
		return ((NekPolygon) model).getPolygonProperties();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.eavp.viz.modeling.IController#setProperty( java.
	 * lang.String, java.lang.String)
	 */
	@Override
	public void setProperty(IMeshProperty property, String value) {

		// If the Edge's constructing or selected properties are being changed,
		// propagate that change to its vertices
		if (MeshEditorMeshProperty.UNDER_CONSTRUCTION.equals(property)
				|| MeshProperty.SELECTED.equals(property)) {

			// Queue notifications from changing own edges
			updateManager.enqueue();

			for (IController edge : model
					.getEntitiesFromCategory(MeshCategory.EDGES)) {
				edge.setProperty(property, value);
			}

			// Send all notifications from setting selection or construction
			// states
			updateManager.flushQueue();
		}

		super.setProperty(property, value);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.eavp.viz.modeling.IController#clone()
	 */
	@Override
	public Object clone() {

		// Create a copy of the model
		NekPolygonController clone = new NekPolygonController();
		clone.copy(this);

		// Refresh the view to be in sync with the model
		clone.refresh();

		return clone;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.eavp.viz.modeling.IController#copy(org. eclipse.
	 * ice.viz.service.modeling.IController)
	 */
	@Override
	public void copy(IController otherObject) {

		// Check that the source object is an IController, failing
		// silently if not and casting it if so
		if (!(otherObject instanceof NekPolygonController)) {
			return;
		}
		BasicController castObject = (BasicController) otherObject;

		// Create the model and give it a reference to this
		model = new NekPolygon();
		model.setController(this);

		// Copy the other object's data members
		model.copy(otherObject.getModel());
		view = (BasicView) ((BasicView) otherObject.getView()).clone();

		// Register as a listener to the model and view
		model.register(this);
		view.register(this);
	}
}
