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

import java.util.HashMap;
import java.util.Set;

import org.eclipse.eavp.viz.datastructures.VizObject.SubscriptionType;
import org.eclipse.eavp.viz.modeling.base.BasicController;
import org.eclipse.eavp.viz.modeling.base.BasicMesh;
import org.eclipse.eavp.viz.modeling.base.IController;
import org.eclipse.eavp.viz.modeling.base.IMesh;
import org.eclipse.eavp.viz.modeling.properties.IMeshCategory;
import org.eclipse.eavp.viz.modeling.properties.IMeshProperty;
import org.eclipse.eavp.viz.modeling.properties.MeshCategory;

/**
 * A mesh component representing a point in three dimensional space.
 * 
 * @Author Robert Smith
 */
public class PointMesh extends BasicMesh {

	/**
	 * The basic constructor
	 */
	public PointMesh() {
		super();
	}

	/**
	 * A constructor for specifying the point's location
	 * 
	 * @param x
	 *            The point's x coordinate
	 * @param y
	 *            The point's y coordinate
	 * @param z
	 *            The point's z coordinate
	 */
	public PointMesh(double x, double y, double z) {
		super();

		//Set the point's location
		transformation.setTranslation(x, y, z);
	}

	/**
	 * Getter for the x coordinate.
	 * 
	 * @return The x coordinate
	 */
	public double getX() {
		return transformation.getTranslation()[0];
	}

	/**
	 * Setter for the x coordinate
	 * 
	 * @param x
	 *            The point's new x coordinate
	 */
	public void setX(double x) {
		transformation.setTranslation(x, getY(), getZ());

		SubscriptionType[] eventTypes = { SubscriptionType.PROPERTY };
		updateManager.notifyListeners(eventTypes);
	}

	/**
	 * Getter for the y coordinate
	 * 
	 * @return The y coordinate
	 */
	public double getY() {
		return transformation.getTranslation()[1];
	}

	/**
	 * Setter for the y coordinate
	 * 
	 * @param y
	 *            The new y coordinate
	 */
	public void setY(double y) {
		transformation.setTranslation(getX(), y, getZ());

		SubscriptionType[] eventTypes = { SubscriptionType.PROPERTY };
		updateManager.notifyListeners(eventTypes);
	}

	/**
	 * Getter for the z coordinate
	 * 
	 * @return The z coordinate
	 */
	public double getZ() {
		return transformation.getTranslation()[2];
	}

	/**
	 * Setter for the z coordinate
	 * 
	 * @param z
	 *            The new z coordinate
	 */
	public void setZ(double z) {
		transformation.setTranslation(getX(), getY(), z);;

		SubscriptionType[] eventTypes = { SubscriptionType.PROPERTY };
		updateManager.notifyListeners(eventTypes);
	}

	/**
	 * Set the point's location. This a convenience method for setting the x, y,
	 * and z coordinates with a single function.
	 * 
	 * @param x
	 *            The new x coordinate
	 * @param y
	 *            The new y coordinate
	 * @param z
	 *            The new z coordinate
	 */
	public void updateLocation(double x, double y, double z) {

		// Queue the individual updates for each coordinate
		updateManager.enqueue();

		// Set each of the new coordinates
		setX(x);
		setY(y);
		setZ(z);

		// Send all updates
		updateManager.flushQueue();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#clone()
	 */
	@Override
	public Object clone() {

		// Create a new component, and make it a copy of this one.
		PointMesh clone = new PointMesh();
		clone.copy(this);
		return clone;
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
		if (!(otherObject instanceof PointMesh)) {
			return false;
		}

		PointMesh castObject = (PointMesh) otherObject;

		// Check the types and properties for equality
		if (type != castObject.type
				|| !getPropertyMap().equals(castObject.getPropertyMap())) {
			return false;
		}
		
		//If the transformations are not equal, the points are not equal
		if(!transformation.equals(castObject.getTransformation())){
			return false;
		}

		// Get the categories of the entities map, disregarding edges
		Set<IMeshCategory> categories = entities.keySet();
		categories.remove(MeshCategory.EDGES);
		Set<IMeshCategory> newCategories = entities.keySet();
		newCategories.remove(MeshCategory.EDGES);

		// If the vertices have different categories, they are not equal
		if (!categories.equals(newCategories)) {
			return false;
		}

		// For each category, check that the two objects' lists of child
		// entities in that category are equal.
		for (IMeshCategory category : categories) {
			if (!entities.get(category)
					.containsAll(castObject.entities.get(category))
					|| !castObject.entities.get(category)
							.containsAll(entities.get(category))) {
				return false;
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

			// Ignore the Edges to prevent circular hashing
			if (MeshCategory.EDGES.equals(category)) {
				continue;
			}

			for (IController entity : getEntitiesFromCategory(category)) {
				hash += 31 * entity.hashCode();
			}
		}
		hash += 31 * getPropertyMap().hashCode();
		hash += 31 * transformation.hashCode();
		
		return hash;
	}
}
