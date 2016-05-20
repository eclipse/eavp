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
package org.eclipse.eavp.viz.service.mesh.datastructures;

import java.util.HashMap;
import java.util.List;
import java.util.Set;

import org.eclipse.eavp.viz.datastructures.VizObject.SubscriptionType;
import org.eclipse.eavp.viz.modeling.DetailedFace;
import org.eclipse.eavp.viz.modeling.base.IController;

/**
 * A Face that keeps track of its associated FieldVariables for a finite element
 * problem and provide BoundaryConditions to their edges based on these
 * variables.
 * 
 * @author Robert Smith
 *
 */
public class FiniteElementFace extends DetailedFace {

	/**
	 * A map from the face's field variable names to those variables' values.
	 */
	HashMap<String, Double> fieldVariables;

	/**
	 * The default constructor.
	 */
	public FiniteElementFace() {
		super();

		// Initialize the list of variables
		fieldVariables = new HashMap<String, Double>();
	}

	/**
	 * A constructor for specifying the child entities.
	 * 
	 * @param entities
	 *            The child entities comprising the face
	 */
	public FiniteElementFace(List<IController> entities) {
		super(entities);

		// Initialize the list of variables
		fieldVariables = new HashMap<String, Double>();
	}

	/**
	 * Get the value for one of this face's field variables.
	 * 
	 * @param name
	 *            The field variable whose value is to be retrieved
	 * @return The field variable's value.
	 */
	public double getFieldVariable(String name) {
		return fieldVariables.get(name);
	}

	/**
	 * Get the set of strings for which this face has configured properties.
	 * 
	 * @return A set containing each Field Variable in the mesh of which this
	 *         face is a part.
	 */
	public Set<String> getFieldVariableNames() {
		return fieldVariables.keySet();
	}

	/**
	 * Remove a field variable from the face.
	 * 
	 * @param name
	 *            The name of the variable to remove.
	 */
	public void removeFieldVariable(String name) {
		fieldVariables.remove(name);
	}

	/**
	 * Set the face's value for one of the field variables.
	 * 
	 * @param name
	 *            The field variable whose value will be set.
	 * @param value
	 *            The field variable's new value.
	 */
	public void setFieldVariable(String name, double value) {
		fieldVariables.put(name, value);

		// Send an update that a property has been changed
		SubscriptionType[] eventTypes = { SubscriptionType.PROPERTY };
		updateManager.notifyListeners(eventTypes);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.eavp.viz.modeling.DetailedFace#clone()
	 */
	@Override
	public Object clone() {

		// Create a new face, and make it a copy of this one.
		FiniteElementFace clone = new FiniteElementFace();
		clone.copy(this);
		return clone;
	}

}
