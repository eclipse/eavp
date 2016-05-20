/*******************************************************************************
 * Copyright (c) 2014 UT-Battelle, LLC.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   Initial API and implementation and/or initial documentation - Jay Jay Billings,
 *   Jordan H. Deyton, Dasha Gorin, Alexander J. McCaskey, Taylor Patterson,
 *   Claire Saunders, Matthew Wang, Anna Wojtowicz
 *******************************************************************************/
package org.eclipse.eavp.viz.service.mesh.datastructures;

import java.util.ArrayList;
import java.util.HashMap;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * <p>
 * This class contains properties of an edge. These properties can be maintained
 * by either a Polygon or the Edge itself. All properties are private fields but
 * can be accessed via getters and setters.
 * </p>
 * <p>
 * </p>
 * <p>
 * Currently, the properties only contain the boundary conditions of edges
 * (faces). The intent is to let the Polygon (element) manage these properties
 * since in Nek the elements have boundary conditions for all their faces.
 * Shared faces can have two fluid, thermal, or nth passive scalar boundary
 * conditions.
 * </p>
 * 
 * @author Jordan H. Deyton
 */
@XmlRootElement(name = "EdgeProperties")
@XmlAccessorType(XmlAccessType.FIELD)
public class EdgeProperties {

	/**
	 * A map of BoundaryConditions for the edge from the name of the field
	 * variable they are associated with.
	 */
	HashMap<String, BoundaryCondition> boundaryConditions;

	/**
	 * <p>
	 * The default constructor for properties of an Edge. This initializes every
	 * property it can to non-null values.
	 * </p>
	 * 
	 */
	public EdgeProperties() {

		// Initialize the boundary condition fields.
		boundaryConditions = new HashMap<String, BoundaryCondition>();

		return;
	}

	/**
	 * <p>
	 * Sets the specified boundary condition for the edge.
	 * </p>
	 * 
	 * @param fieldVariable
	 *            The field variable for which this boundary condition applies.
	 * @param condition
	 *            The new BoundaryCondition.
	 * @return True if the new condition was successfully set, false otherwise.
	 */
	public boolean setBoundaryCondition(String fieldVariable,
			BoundaryCondition condition) {

		boolean changed = false;

		// Only set the boundary condition if:
		// The new condition is not null
		// The new condition is not the same one as the old one
		if (condition != null
				&& condition != boundaryConditions.get(fieldVariable)) {
			boundaryConditions.put(fieldVariable, condition);
			changed = true;
		}

		return changed;
	}

	/**
	 * <p>
	 * Gets the edge's boundary condition for the specified field variable.
	 * </p>
	 * 
	 * @return The edge's BoundaryCondition. This should never be null.
	 */
	public BoundaryCondition getBoundaryCondition(String fieldVariable) {
		return boundaryConditions.get(fieldVariable);
	}

	/**
	 * <p>
	 * Gets boundary conditions for the edge.
	 * </p>
	 * 
	 * @return An ArrayList of all boundary conditions for the edge. If boundary
	 *         conditions exist, this will be an empty list.
	 */
	public ArrayList<BoundaryCondition> getBoundaryConditions() {

		// The list of all boundary conditions
		ArrayList<BoundaryCondition> conditions = new ArrayList<BoundaryCondition>();

		// Get each boundary condition from the map and add it to the list
		for (BoundaryCondition condition : boundaryConditions.values()) {
			conditions.add(condition);
		}
		return conditions;
	}

	/**
	 * <p>
	 * This operation returns the hash value of the EdgeProperties.
	 * </p>
	 * 
	 * @return The hash of the Object.
	 */
	@Override
	public int hashCode() {

		// Hash based on super's hashCode.
		int hash = 31;

		// Add local hashes.
		hash = 31 * hash + boundaryConditions.hashCode();

		return hash;
	}

	/**
	 * <p>
	 * This operation is used to check equality between this EdgeProperties and
	 * another. It returns true if they are equal and false if they are not.
	 * </p>
	 * 
	 * @param otherObject
	 *            The other Object that should be compared with this one.
	 * @return True if the Objects are equal, false otherwise.
	 */
	@Override
	public boolean equals(Object otherObject) {

		// By default, the objects are not equivalent.
		boolean equals = false;

		// Check the reference.
		if (this == otherObject) {
			equals = true;
		}
		// Check the information stored in the other object.
		else if (otherObject != null && otherObject instanceof EdgeProperties) {

			// We can now cast the other object.
			EdgeProperties conditions = (EdgeProperties) otherObject;

			// Check that the they have the same kinds of boundary conditions
			if (boundaryConditions.keySet()
					.equals(conditions.boundaryConditions)) {

				// Assume they are equal until proven otherwise
				equals = true;

				// Check each boundary condition for equality
				for (String variable : boundaryConditions.keySet()) {
					if (!boundaryConditions.get(variable).equals(
							conditions.boundaryConditions.get(variable))) {

						// If two boundary conditions are not equal, the
						// conditions as a whole are not equal
						equals = false;
						break;
					}
				}
			}
		}

		return equals;
	}

	/**
	 * <p>
	 * This operation copies the contents of a EdgeProperties into the current
	 * object using a deep copy.
	 * </p>
	 * 
	 * @param properties
	 *            The Object from which the values should be copied.
	 */
	public void copy(EdgeProperties properties) {

		// Check the parameters.
		if (properties == null) {
			return;
		}

		// Remove the current map of boundary conditions
		boundaryConditions = new HashMap<String, BoundaryCondition>();

		// For each field variable in the other properties, but a clone of that
		// variable's boundary condition in the map
		for (String variable : properties.boundaryConditions.keySet()) {
			boundaryConditions.put(variable,
					(BoundaryCondition) properties.boundaryConditions
							.get(variable).clone());
		}

		return;
	}

	/**
	 * <p>
	 * This operation returns a clone of the EdgeProperties using a deep copy.
	 * </p>
	 * 
	 * @return The new clone.
	 */
	@Override
	public Object clone() {

		// Initialize a new object.
		EdgeProperties object = new EdgeProperties();

		// Copy the contents from this one.
		object.copy(this);

		// Return the newly instantiated object.
		return object;
	}

}
