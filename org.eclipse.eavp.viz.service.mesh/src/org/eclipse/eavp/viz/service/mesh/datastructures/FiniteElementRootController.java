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

import org.eclipse.eavp.viz.modeling.base.BasicController;
import org.eclipse.eavp.viz.modeling.base.BasicView;

/**
 * A controller for the root element of a finite element mesh.
 * 
 * @author Robert Smith
 *
 */
public class FiniteElementRootController extends BasicController {

	/**
	 * The nullary constructor.
	 */
	public FiniteElementRootController() {

	}

	/**
	 * The default constructor.
	 * 
	 * @param model
	 *            The internal representation of the part.
	 * @param view
	 *            The part's graphical representation
	 */
	public FiniteElementRootController(FiniteElementRoot model,
			BasicView view) {
		super(model, view);
	}

	/**
	 * Add a new field variable to the finite element mesh. This variable will
	 * be set to each of the faces in the mesh with its default value.
	 * 
	 * @param newVariable
	 *            The field variable to add to the mesh
	 */
	public void addFieldVariable(FieldVariable newVariable) {
		((FiniteElementRoot) model).addFieldVariable(newVariable);
	}

	/**
	 * Remove a field variable from the mesh and all of its faces.
	 * 
	 * @param variable
	 *            The variable to remvoe
	 */
	public void removeFieldVariable(FieldVariable variable) {
		((FiniteElementRoot) model).removeFieldVariable(variable);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.eavp.viz.modeling.FaceController#clone()
	 */
	@Override
	public Object clone() {

		// Create a copy of the model
		FiniteElementRootController clone = new FiniteElementRootController();
		clone.copy(this);

		// Refresh the view to be in sync with the model
		clone.refresh();

		return clone;
	}
}
