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
package org.eclipse.eavp.viz.service.mesh.datastructures;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;

import org.eclipse.eavp.viz.datastructures.VizObject.IManagedUpdateable;
import org.eclipse.eavp.viz.datastructures.VizObject.IVizUpdateable;
import org.eclipse.eavp.viz.datastructures.VizObject.IVizUpdateableListener;
import org.eclipse.eavp.viz.datastructures.VizObject.SubscriptionType;
import org.eclipse.eavp.viz.modeling.DetailedEdgeController;
import org.eclipse.eavp.viz.modeling.DetailedFace;
import org.eclipse.eavp.viz.modeling.EdgeController;
import org.eclipse.eavp.viz.modeling.base.IController;
import org.eclipse.eavp.viz.modeling.base.IMesh;
import org.eclipse.eavp.viz.modeling.properties.IMeshCategory;
import org.eclipse.eavp.viz.modeling.properties.MeshCategory;
import org.eclipse.eavp.viz.modeling.properties.MeshProperty;

/**
 * The internal representation for a face with a traversable list of vertices
 * and edges which also maintains information needed for Nek5000.
 * 
 * @author Robert Smith
 *
 */
public class NekPolygon extends DetailedFace
		implements IVizUpdateableListener {

	/**
	 * <p>
	 * The properties for each polygon defined in the MESH DATA section of a Nek
	 * rea file. Contains a String representing the material ID, and an integer
	 * representing the group number of the polygon.
	 * </p>
	 * 
	 */
	@XmlElement(name = "PolygonProperties")
	protected PolygonProperties polygonProperties;

	/**
	 * <p>
	 * A map of edge properties for each edge, keyed on the edge IDs.
	 * </p>
	 * 
	 */
	@XmlElementWrapper(name = "EdgeProperties")
	private HashMap<Integer, EdgeProperties> edgeProperties;

	/**
	 * The default constructor
	 */
	public NekPolygon() {
		super();

		// Initialize the boundary condition containers.
		edgeProperties = new HashMap<Integer, EdgeProperties>();

		// Initialize the polygon properties
		polygonProperties = new PolygonProperties();
	}

	/**
	 * A constructor for specifying the child entities.
	 * 
	 * @param entities
	 *            The child entities comprising the face
	 */
	public NekPolygon(List<IController> entities) {
		super(entities);

		// Initialize the boundary condition containers.
		edgeProperties = new HashMap<Integer, EdgeProperties>();

		// Initialize the polygon properties
		polygonProperties = new PolygonProperties();
	}

	/**
	 * Create a set of edge properties for the given edge and place them in the
	 * properties, keyed by that edge's id.
	 * 
	 * @param edge
	 *            The edge for which boundary conditions are being created
	 */
	public void initializeBoundaryConditions(IController edge) {
		// Create an entry for the edge in the map of edge properties.
		EdgeProperties properties = new EdgeProperties();
		edgeProperties.put(Integer.valueOf(edge.getProperty(MeshProperty.ID)),
				properties);

		// Register with all of the boundary conditions in the properties.
		properties.getFluidBoundaryCondition().register(this);
		properties.getThermalBoundaryCondition().register(this);
		for (BoundaryCondition condition : properties
				.getOtherBoundaryConditions()) {
			condition.register(this);
		}
	}

	/**
	 * Getter method for the map of edge properties.
	 * 
	 * @return A map from edge ID numbers to that edge's edge properties.
	 */
	public HashMap<Integer, EdgeProperties> getEdgeProperties() {
		return edgeProperties;
	}

	/**
	 * <p>
	 * Gets the fluid boundary condition for an edge of the polygon.
	 * </p>
	 * 
	 * @param edgeId
	 *            The ID of the edge that has a BoundaryCondition.
	 * @return The edge's BoundaryCondition for this polygon, or null if the
	 *         edge ID is invalid.
	 */
	public BoundaryCondition getFluidBoundaryCondition(int edgeId) {

		// If the edgeId is valid, we can pull the property from the
		// EdgeProperty instance.
		BoundaryCondition condition = null;
		EdgeProperties properties = edgeProperties.get(edgeId);
		if (properties != null) {
			condition = properties.getFluidBoundaryCondition();
		}
		return condition;
	}

	/**
	 * <p>
	 * Gets a passive scalar boundary condition for an edge of the polygon.
	 * </p>
	 * 
	 * @param edgeId
	 *            The ID of the edge that has a BoundaryCondition.
	 * @param otherId
	 *            The ID or index of the set of passive scalar boundary
	 *            conditions.
	 * @return The edge's BoundaryCondition for this polygon, or null if the
	 *         edge ID is invalid.
	 */
	public BoundaryCondition getOtherBoundaryCondition(int edgeId,
			int otherId) {

		// If the edgeId is valid, we can pull the property from the
		// EdgeProperty instance.
		BoundaryCondition condition = null;
		EdgeProperties properties = edgeProperties.get(edgeId);
		if (properties != null) {
			condition = properties.getOtherBoundaryCondition(otherId);
		}
		return condition;
	}

	/**
	 * <p>
	 * Returns the properties for the current polygon.
	 * </p>
	 * 
	 * @return The properties for the current polygon.
	 */
	public PolygonProperties getPolygonProperties() {
		return polygonProperties;
	}

	/**
	 * <p>
	 * Gets the thermal boundary condition for an edge of the polygon.
	 * </p>
	 * 
	 * @param edgeId
	 *            The ID of the edge that has a BoundaryCondition.
	 * @return The edge's BoundaryCondition for this polygon, or null if the
	 *         edge ID is invalid.
	 */
	public BoundaryCondition getThermalBoundaryCondition(int edgeId) {

		// If the edgeId is valid, we can pull the property from the
		// EdgeProperty instance.
		BoundaryCondition condition = null;
		EdgeProperties properties = edgeProperties.get(edgeId);
		if (properties != null) {
			condition = properties.getThermalBoundaryCondition();
		}
		return condition;
	}

	/**
	 * Setter method for the map of edge properties.
	 * 
	 * @param edgeProperties
	 *            A map from edge ID numbers to that edge's edge properties.
	 */
	public void setEdgeProperties(
			HashMap<Integer, EdgeProperties> edgeProperties) {
		this.edgeProperties = edgeProperties;
	}

	/**
	 * <p>
	 * Sets a fluid boundary condition for an edge of the polygon.
	 * </p>
	 * 
	 * @param edgeId
	 *            The ID of the edge that will have the new BoundaryCondition.
	 * @param condition
	 *            The new BoundaryCondition.
	 */
	public void setFluidBoundaryCondition(int edgeId,
			BoundaryCondition condition) {

		// First, check that the edgeId is valid by performing a map lookup.
		EdgeProperties properties = edgeProperties.get(edgeId);
		if (condition != null && properties != null) {
			// If the edgeId is valid, try to set the new condition. If the new
			// condition is set, we need to register with the new condition and
			// notify listeners of the change.
			BoundaryCondition oldCondition = properties
					.getFluidBoundaryCondition();
			if (properties.setFluidBoundaryCondition(condition)) {
				// Unregister from the old condition and register with the new.
				oldCondition.unregister(this);
				condition.register(this);

				// Notify listeners of the change.
				SubscriptionType[] eventType = new SubscriptionType[1];
				eventType[0] = SubscriptionType.PROPERTY;
				updateManager.notifyListeners(eventType);
			}
		}

		return;
	}

	/**
	 * <p>
	 * Sets a passive scalar boundary condition for an edge of the polygon.
	 * </p>
	 * 
	 * @param edgeId
	 *            The ID of the edge that will have the new BoundaryCondition.
	 * @param otherId
	 *            The ID or index of the set of passive scalar boundary
	 *            conditions.
	 * @param condition
	 *            The new BoundaryCondition.
	 */
	public void setOtherBoundaryCondition(int edgeId, int otherId,
			BoundaryCondition condition) {

		// First, check that the edgeId is valid by performing a map lookup.
		EdgeProperties properties = edgeProperties.get(edgeId);
		if (condition != null && properties != null) {
			// If the edgeId is valid, try to set the new condition. If the new
			// condition is set, we need to register with the new condition and
			// notify listeners of the change.
			BoundaryCondition oldCondition = properties
					.getOtherBoundaryCondition(otherId);
			if (properties.setOtherBoundaryCondition(otherId, condition)) {
				// Unregister from the old condition and register with the new.
				// We need a null check because the scalar index ID may be new.
				if (oldCondition != null) {
					oldCondition.unregister(this);
				}
				condition.register(this);

				// Notify listeners of the change.
				SubscriptionType[] eventType = new SubscriptionType[1];
				eventType[0] = SubscriptionType.PROPERTY;
				updateManager.notifyListeners(eventType);
			}
		}

		return;
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
		polygonProperties = new PolygonProperties(materialId, group);

		return;
	}

	/**
	 * <p>
	 * Sets a thermal boundary condition for an edge of the polygon.
	 * </p>
	 * 
	 * @param edgeId
	 *            The ID of the edge that will have the new BoundaryCondition.
	 * @param condition
	 *            The new BoundaryCondition.
	 */
	public void setThermalBoundaryCondition(int edgeId,
			BoundaryCondition condition) {

		// First, check that the edgeId is valid by performing a map lookup.
		EdgeProperties properties = edgeProperties.get(edgeId);
		if (condition != null && properties != null) {
			// If the edgeId is valid, try to set the new condition. If the new
			// condition is set, we need to register with the new condition and
			// notify listeners of the change.
			BoundaryCondition oldCondition = properties
					.getThermalBoundaryCondition();
			if (properties.setThermalBoundaryCondition(condition)) {
				// Unregister from the old condition and register with the new.
				oldCondition.unregister(this);
				condition.register(this);

				// Notify listeners of the change.
				SubscriptionType[] eventType = new SubscriptionType[1];
				eventType[0] = SubscriptionType.PROPERTY;
				updateManager.notifyListeners(eventType);
			}
		}

		return;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.eavp.viz.modeling.AbstractMeshComponent#addEntity( org.
	 * eclipse.ice.viz.service.modeling.IController)
	 */
	@Override
	public void addEntityToCategory(IController entity,
			IMeshCategory category) {

		// If adding an edge, handle it apprioriately
		if (MeshCategory.EDGES.equals(category)) {

			// Queue messages from adding the entity
			updateManager.enqueue();

			// Add the edge to the Edges category by default
			super.addEntityToCategory(entity, category);

			// If the controller already exists, give a reference to it to the
			// edge.
			if (entity instanceof DetailedEdgeController
					&& controller != null) {
				entity.addEntityToCategory(controller, MeshCategory.FACES);
			}

			// When edges are added, create boundary conditions for them.
			initializeBoundaryConditions(entity);

			// Send own update along with the new edge's, if there was one
			updateManager.flushQueue();

		} else {
			super.addEntityToCategory(entity, category);
		}
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 *
	 * org.eclipse.eavp.viz.modeling.AbstractMeshComponent# setController(
	 * org.eclipse.eavp.viz.modeling.IController)
	 */
	@Override
	public void setController(IController controller) {
		super.setController(controller);

		// Give a reference to the controller to the edge's faces
		List<IController> edges = entities.get(MeshCategory.EDGES);
		if (edges != null) {

			// Queue messages from all edges
			updateManager.enqueue();

			for (IController edge : edges) {
				edge.addEntityToCategory(controller, MeshCategory.EDGES);
			}

			// Send messages from all changed edges
			updateManager.flushQueue();
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.eavp.viz.service.datastructures.VizObject.
	 * IManagedVizUpdateableListener#update(org.eclipse.eavp.viz.service.
	 * datastructures.VizObject.IVizUpdateable,
	 * org.eclipse.eavp.viz.service.datastructures.VizObject.
	 * UpdateableSubscription)
	 */
	@Override
	public void update(IManagedUpdateable component, SubscriptionType[] type) {

		// If this was a property update, account for any changes in the edges'
		// IDs
		if (Arrays.asList(type).contains(SubscriptionType.PROPERTY)) {

			// Edges Get the edges and check if the update came from any of them
			ArrayList<EdgeController> edges = getEntitiesFromCategory(
					MeshCategory.EDGES, EdgeController.class);
			if (edges.contains(component)) {

				// Get the IDs currently in the edges
				Set<Integer> currIDs = new HashSet<Integer>();
				for (EdgeController edge : edges) {
					currIDs.add(Integer
							.parseInt(edge.getProperty(MeshProperty.ID)));
				}

				// Get the IDs as they are stored in the properties map.
				Set<Integer> oldIDs = edgeProperties.keySet();

				// If there has been a change in the IDs, handle it.
				if (!currIDs.equals(oldIDs)) {

					// Remove all common elements from both sets. After this the
					// sets should be reduced to the changed ID and its new
					// value
					Set<Integer> intersection = new HashSet<Integer>(currIDs);
					intersection.retainAll(oldIDs);
					currIDs.removeAll(intersection);
					oldIDs.removeAll(intersection);

					// Get the changed ID
					int oldID = oldIDs.iterator().next();

					// Copy the changed edge's properties to the new location
					edgeProperties.put(currIDs.iterator().next(),
							edgeProperties.get(oldID));

					// Remove the old entry from the map
					edgeProperties.remove(oldID);
				}

			}
		}

		// Pass the update to own listeners
		updateManager.notifyListeners(type);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.eavp.viz.modeling.AbstractMeshComponent#clone()
	 */
	@Override
	public Object clone() {
		// Create a new component, and make it a copy of this one.
		NekPolygon clone = new NekPolygon();
		clone.copy(this);
		return clone;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.eavp.viz.modeling.DetailedFaceMesh#copy(org.eclipse.eavp.viz.
	 * modeling.base.IMesh)
	 */
	@Override
	public void copy(IMesh otherObject) {
		super.copy(otherObject);

		// If the source is a NekPolygonMesh, also copy its Nek properties
		if (otherObject instanceof NekPolygon) {
			NekPolygon castObject = (NekPolygon) otherObject;
			edgeProperties = (HashMap<Integer, EdgeProperties>) castObject.edgeProperties
					.clone();
			polygonProperties = (PolygonProperties) castObject.polygonProperties
					.clone();
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.eavp.viz.modeling.base.BasicMesh#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object otherObject) {

		// If the objects were equal otherwise, check the Nek specific data
		boolean equal = super.equals(otherObject);
		if (equal) {
			NekPolygon castObject = (NekPolygon) otherObject;

			// If the Nek data is not equal, the objects are not equal
			if (!edgeProperties.equals(castObject.edgeProperties)
					|| !polygonProperties
							.equals(castObject.polygonProperties)) {
				return false;
			}
		}

		// If the Nek specific data did not make a difference, we may return the
		// super method's output.
		return equal;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.eavp.viz.service.datastructures.VizObject.
	 * IVizUpdateableListener#update(org.eclipse.eavp.viz.service.
	 * datastructures. VizObject.IVizUpdateable)
	 */
	@Override
	public void update(IVizUpdateable component) {

		// This is the IVizUpdateable update method, which will only be
		// triggered by boundary conditions. Thus, this should trigger a
		// Property type update for the part's own listeners
		SubscriptionType[] eventType = new SubscriptionType[1];
		eventType[0] = SubscriptionType.PROPERTY;
		updateManager.notifyListeners(eventType);
	}

}
