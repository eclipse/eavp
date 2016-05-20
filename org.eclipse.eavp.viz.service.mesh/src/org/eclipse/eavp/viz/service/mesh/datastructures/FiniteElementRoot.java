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

import java.util.ArrayList;

import org.eclipse.eavp.viz.datastructures.VizObject.SubscriptionType;
import org.eclipse.eavp.viz.modeling.base.BasicMesh;
import org.eclipse.eavp.viz.modeling.base.IController;
import org.eclipse.eavp.viz.modeling.properties.IMeshCategory;
import org.eclipse.eavp.viz.modeling.properties.MeshCategory;

/**
 * A modeling part intended to serve as the root of a the tree for a Mesh
 * Editor. It will collect the top level Faces that comprise the mesh and manage
 * their shared FieldVariables.
 * 
 * It is assumed that the only Faces added to this part will be
 * FiniteElementFaces, as other kinds of faces will not be able to hold the
 * finite element field variables this class tracks
 * 
 * @author Robert Smith
 *
 */
public class FiniteElementRoot extends BasicMesh {

	/**
	 * The list of variables set for this mesh. Each of which should be
	 * instantiated for each of the root's faces.
	 */
	private ArrayList<FieldVariable> fieldVariables;

	/**
	 * The default constructor.
	 */
	public FiniteElementRoot() {
		super();

		// Instantiate the list of variables
		fieldVariables = new ArrayList<FieldVariable>();
	}

	/**
	 * Add a new field variable to the finite element mesh. This variable will
	 * be set to each of the faces in the mesh with its default value.
	 * 
	 * @param newVariable
	 *            The field variable to add to the mesh
	 */
	public void addFieldVariable(FieldVariable newVariable) {

		// Save the variable in the list
		fieldVariables.add(newVariable);

		// Queue updates from changing the faces
		updateManager.enqueue();

		// Get the variable's values
		String name = newVariable.getName();
		double value = newVariable.getDefaultValue();

		// For each face, add the variable's default value to it
		for (FiniteElementFaceController face : getEntitiesFromCategory(
				MeshCategory.FACES, FiniteElementFaceController.class)) {
			face.setFieldVariable(name, value);
		}

		// Send an update that a property has been changed
		SubscriptionType[] eventTypes = { SubscriptionType.PROPERTY };
		updateManager.notifyListeners(eventTypes);
		updateManager.flushQueue();

	}

	/**
	 * Remove a field variable from the mesh and all of its faces.
	 * 
	 * @param variable
	 *            The variable to remvoe
	 */
	public void removeFieldVariable(FieldVariable variable) {

		// If the variable is not in the mesh, fail silently
		if (fieldVariables.contains(variable)) {

			// Remove the variable from the list
			fieldVariables.remove(variable);

			// Get the variable's name
			String name = variable.getName();

			// Remove the variable from each face
			for (FiniteElementFaceController face : getEntitiesFromCategory(
					MeshCategory.FACES, FiniteElementFaceController.class)) {
				face.removeFieldVariable(name);
			}

			// Send an update that a property has been changed
			SubscriptionType[] eventTypes = { SubscriptionType.PROPERTY };
			updateManager.notifyListeners(eventTypes);
			updateManager.flushQueue();
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.eavp.viz.modeling.base.BasicMesh#addEntityToCategory(org.
	 * eclipse.eavp.viz.modeling.base.IController,
	 * org.eclipse.eavp.viz.modeling.properties.IMeshCategory)
	 */
	@Override
	public void addEntityToCategory(IController newEntity,
			IMeshCategory category) {
		super.addEntityToCategory(newEntity, category);

		// If the entity is a FiniteElementFace being added to the faces
		// category, give it each of the variables.
		if (MeshCategory.FACES.equals(category)
				&& newEntity.getClass() == FiniteElementFaceController.class) {

			// Cast the entity
			FiniteElementFaceController face = (FiniteElementFaceController) newEntity;

			// Set each field variable to its default value
			for (FieldVariable variable : fieldVariables) {
				face.setFieldVariable(variable.getName(),
						variable.getDefaultValue());
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.eavp.viz.modeling.DetailedFace#clone()
	 */
	@Override
	public Object clone() {

		// Create a new face, and make it a copy of this one.
		FiniteElementRoot clone = new FiniteElementRoot();
		clone.copy(this);
		return clone;
	}
}
