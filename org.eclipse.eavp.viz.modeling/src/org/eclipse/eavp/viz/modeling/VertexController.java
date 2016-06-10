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

import org.eclipse.eavp.viz.modeling.base.BasicController;
import org.eclipse.eavp.viz.modeling.base.BasicView;
import org.eclipse.eavp.viz.modeling.base.IController;
import org.eclipse.eavp.viz.modeling.base.ITransparentController;
import org.eclipse.eavp.viz.modeling.base.ITransparentView;
import org.eclipse.eavp.viz.modeling.base.IWireframeController;
import org.eclipse.eavp.viz.modeling.base.IWireframeView;

/**
 * A controller for a Vertex model part.
 * 
 * @author Robert Smith
 *
 */
public class VertexController extends PointController
		implements ITransparentController, IWireframeController {

	/**
	 * The nullary constructor
	 */
	public VertexController() {
		super();
	}

	/**
	 * The default constructor
	 * 
	 * @param model
	 *            The controller's model
	 * @param view
	 *            The controller's view
	 */
	public VertexController(Vertex model, BasicView view) {
		super(model, view);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#clone()
	 */
	@Override
	public Object clone() {

		// Create a copy of the model
		VertexController clone = new VertexController();
		clone.copy(this);

		return clone;
	}

	/**
	 * Deep copy the given object's data into this one.
	 * 
	 * @param otherObject
	 *            The object to copy into this one.
	 */
	@Override
	public void copy(IController otherObject) {

		// Check that the source object is an IController, failing
		// silently if not and casting it if so
		if (!(otherObject instanceof VertexController)) {
			return;
		}
		BasicController castObject = (BasicController) otherObject;

		// Create the model and give it a reference to this
		model = new Vertex();
		model.setController(this);

		// Copy the other object's data members
		model.copy(castObject.getModel());
		view = (BasicView) castObject.getView().clone();

		// Register as a listener to the model and view
		model.register(this);
		view.register(this);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.eavp.viz.modeling.base.IWireframeController#isWireframe()
	 */
	@Override
	public boolean isWireframe() {
		return ((IWireframeView) view).isWireframe();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.eavp.viz.modeling.base.IWireframeController#setWireframeMode(
	 * boolean)
	 */
	@Override
	public void setWireframeMode(boolean on) {
		((IWireframeView) view).setWireframeMode(on);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.eavp.viz.modeling.base.ITransparentController#isTransparent()
	 */
	@Override
	public boolean isTransparent() {
		return ((ITransparentView) view).isTransparent();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.eavp.viz.modeling.base.ITransparentController#
	 * setTransparentMode(boolean)
	 */
	@Override
	public void setTransparentMode(boolean transparent) {
		((ITransparentView) view).setTransparentMode(transparent);
	}
}
