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
package org.eclipse.eavp.viz.modeling;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.eclipse.eavp.viz.datastructures.VizObject.IManagedUpdateableListener;
import org.eclipse.eavp.viz.datastructures.VizObject.SubscriptionType;
import org.eclipse.eavp.viz.modeling.base.BasicController;
import org.eclipse.eavp.viz.modeling.base.BasicMesh;
import org.eclipse.eavp.viz.modeling.base.IController;
import org.eclipse.eavp.viz.modeling.base.IMesh;
import org.eclipse.eavp.viz.modeling.properties.IMeshCategory;
import org.eclipse.eavp.viz.modeling.properties.IMeshProperty;
import org.eclipse.eavp.viz.modeling.properties.MeshCategory;
import org.eclipse.eavp.viz.modeling.properties.MeshProperty;
import org.eclipse.eavp.viz.modeling.properties.MeshType;

/**
 * A mesh component representing a shape in a Constructive Solid Geometry tree.
 * 
 * @author Robert Smith
 *
 */
public class ShapeMesh extends BasicMesh {

	/**
	 * The default constructor.
	 */
	public ShapeMesh() {
		super();
		type = MeshType.CONSTRUCTIVE;
		getProperties().put(MeshProperty.TYPE, "None");
	}

	/**
	 * Set the shape's parent shape. Shapes can have at most one parent, and
	 * this operation will remove any existing parent.
	 * 
	 * @param parent
	 *            The new shape which serves as this shape's parent.
	 */
	public void setParent(IController parent) {

		// Get the current list of parents
		List<IController> parentList = getEntitiesFromCategory(
				MeshCategory.PARENT);

		// If there is a parent, unregister it as a listener
		if (parentList != null && !parentList.isEmpty()) {
			unregister(parentList.get(0));
		}

		// Put the new Parent in the entities map, replacing any other.
		ArrayList<IController> newParentList = new ArrayList<IController>();

		// If parent is null, an empty list should be saved, essentially
		// removing the parent object.
		if (parent != null) {
			newParentList.add(parent);
		}

		entities.put(MeshCategory.PARENT, newParentList);

		// Register the parent as a listener and fire an update notification
		register(parent);

		SubscriptionType[] eventTypes = { SubscriptionType.CHILD };
		updateManager.notifyListeners(eventTypes);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.eavp.viz.modeling.AbstractMeshComponent#setProperty(
	 * java.lang.String, java.lang.String)
	 */
	@Override
	public void setProperty(IMeshProperty property, String value) {

		// Queue updates for all selections
		updateManager.enqueue();

		// Set own property
		super.setProperty(property, value);

		// Select/deselect all children as well
		if (MeshProperty.SELECTED.equals(property)) {
			if (entities.get(MeshCategory.CHILDREN) != null) {
				for (IController entity : entities.get(MeshCategory.CHILDREN)) {
					entity.setProperty(MeshProperty.SELECTED, value);
				}
			}
		}

		// Send updates for all selections
		updateManager.flushQueue();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * prototype5.impl.AbstractMeshComponentImpl#addEntity(prototype5.VizObject)
	 */
	@Override
	public void addEntity(IController newEntity) {

		// By default, add an entity as a child shape.
		addEntityToCategory(newEntity, MeshCategory.CHILDREN);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.eavp.viz.modeling.AbstractMeshComponent#
	 * addEntityByCategory(org.eclipse.eavp.viz.modeling. IController,
	 * java.lang.String)
	 */
	@Override
	public void addEntityToCategory(IController newEntity,
			IMeshCategory category) {

		// Fail silently for null objects
		if (newEntity == null) {
			return;
		}

		// If the category is not parent, add the entity normally
		if (category != MeshCategory.PARENT) {

			// Set self as parent to any children
			if (category == MeshCategory.CHILDREN && controller != null) {
				((ShapeController) newEntity).setParent((controller));
			}

			super.addEntityToCategory(newEntity, category);
		}

		// Force changes to the parent category to go through the setParent()
		// function
		else {
			setParent(newEntity);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.eavp.viz.modeling.AbstractMesh#setController(org.
	 * eclipse.ice.viz.service.modeling.IController)
	 */
	@Override
	public void setController(IController controller) {
		super.setController(controller);

		// Set the new controller as the parent to any children
		for (IController child : getEntitiesFromCategory(
				MeshCategory.CHILDREN)) {
			((ShapeController) child).setParent(controller);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.eavp.viz.modeling.AbstractMeshComponent#clone()
	 */
	@Override
	public Object clone() {

		// Make a new shape component and copy the data into it
		ShapeMesh clone = new ShapeMesh();
		clone.copy(this);

		return clone;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.eavp.viz.modeling.AbstractMeshComponent#copy(org.
	 * eclipse.ice.viz.service.modeling.AbstractMeshComponent)
	 */
	@Override
	public void copy(IMesh source) {

		// If the other object is not a ShapeMesh, fail silently
		if (!(source instanceof ShapeMesh)) {
			return;
		}

		// Cast the object
		ShapeMesh castObject = (ShapeMesh) source;

		// Copy the map of entities
		entities = new HashMap<IMeshCategory, ArrayList<IController>>();

		// Copy each child in the entities map
		List<IController> children = castObject.entities
				.get(MeshCategory.CHILDREN);
		if (children != null) {
			for (IController entity : children) {
				addEntity((IController) ((BasicController) entity).clone());
			}
		}

		// Copy each of the other component's data members
		type = castObject.type;
		properties = new HashMap<IMeshProperty, String>(
				castObject.getProperties());
		// Notify listeners of the change
		SubscriptionType[] eventTypes = { SubscriptionType.ALL };
		updateManager.notifyListeners(eventTypes);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.eavp.viz.modeling.AbstractMeshComponent#equals(java.
	 * lang.Object)
	 */
	@Override
	public boolean equals(Object otherObject) {

		// Check if the objects are the same
		if (this == otherObject) {
			return true;
		}

		// Check if the other object is an AbstractMeshComponent and cast it
		if (!(otherObject instanceof BasicMesh)) {
			return false;
		}

		BasicMesh castObject = (BasicMesh) otherObject;

		// Check the types, properties, and entity category for equality
		if (type != castObject.getType()
				|| !getProperties().equals(castObject.getProperties())
				|| !entities.keySet()
						.equals(castObject.getEntityCategories())) {
			return false;
		}

		// For each category, check that the two objects' lists of child
		// entities in that category are equal.
		for (IMeshCategory category : entities.keySet()) {

			// Skip this check for the Parent category. Two parts can be
			// considered equal even if they are in different parts of the
			// model.
			if (!MeshCategory.PARENT.equals(category)) {
				if (!entities.get(category).containsAll(
						castObject.getEntitiesFromCategory(category))
						|| !castObject.getEntitiesFromCategory(category)
								.containsAll(entities.get(category))) {
					return false;
				}
			}
		}

		// All checks passed, so the objects are equal
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		int hash = 9;
		hash += 31 * type.hashCode();
		for (IMeshCategory category : entities.keySet()) {

			// Do not hash the parent shape, to avoid circular hashing
			if (!MeshCategory.PARENT.equals(category)) {
				for (IController entity : getEntitiesFromCategory(category)) {
					hash += 31 * entity.hashCode();
				}
			}
		}
		hash += 31 * getProperties().hashCode();
		return hash;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.eavp.viz.modeling.AbstractMeshComponent#register(org.
	 * eclipse.ice.viz.service.datastructures.VizObject.IVizUpdateableListener)
	 */
	@Override
	public void register(IManagedUpdateableListener listener) {

		// Ignore requests to register own children to prevent circular
		// observation
		if (entities.get(MeshCategory.CHILDREN) == null
				|| !entities.get(MeshCategory.CHILDREN).contains(listener)) {
			super.register(listener);
		}
	}
}
