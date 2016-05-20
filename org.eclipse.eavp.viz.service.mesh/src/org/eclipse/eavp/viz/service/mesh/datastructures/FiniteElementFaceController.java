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

import java.util.Set;

import org.eclipse.eavp.viz.modeling.FaceController;
import org.eclipse.eavp.viz.modeling.base.BasicView;

/**
 * A controller for a Face that keeps track of its associated FieldVariables for
 * a finite element problem.
 * 
 * @author Robert Smith
 *
 */
public class FiniteElementFaceController extends FaceController {

	/**
	 * The nullary constructor.
	 */
	public FiniteElementFaceController() {

	}

	/**
	 * The default constructor.
	 * 
	 * @param model
	 *            The part's internal representation.
	 * @param view
	 *            The part's representation in the graphics rendering program.
	 */
	public FiniteElementFaceController(FiniteElementFace model,
			BasicView view) {
		super(model, view);
	}

	/**
	 * Get the value for one of this face's field variables.
	 * 
	 * @param name
	 *            The field variable whose value is to be retrieved
	 * @return The field variable's value.
	 */
	public double getFieldVariable(String name) {
		return ((FiniteElementFace) model).getFieldVariable(name);
	}

	/**
	 * Get the set of strings for which this face has configured properties.
	 * 
	 * @return A set containing each Field Variable in the mesh of which this
	 *         face is a part.
	 */
	public Set<String> getFieldVariableNames() {
		return ((FiniteElementFace) model).getFieldVariableNames();
	}

	/**
	 * Remove a field variable from the face.
	 * 
	 * @param name
	 *            The name of the variable to remove.
	 */
	public void removeFieldVariable(String name) {
		((FiniteElementFace) model).removeFieldVariable(name);
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
		((FiniteElementFace) model).setFieldVariable(name, value);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.eavp.viz.modeling.FaceController#clone()
	 */
	@Override
	public Object clone() {

		// Create a copy of the model
		FiniteElementFaceController clone = new FiniteElementFaceController();
		clone.copy(this);

		// Refresh the view to be in sync with the model
		clone.refresh();

		return clone;
	}
}
