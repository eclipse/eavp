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
 * A controller for a shape part, which exposes ShapeComponent functionality.
 * 
 * @author Robert Smith
 *
 */
public class ShapeController extends BasicController
		implements ITransparentController, IWireframeController {

	/**
	 * THe nullary constructor
	 */
	public ShapeController() {
		super();
	}

	/**
	 * The default constructor.
	 * 
	 * @param model
	 *            The controller's model
	 * @param view
	 *            The controller's view
	 */
	public ShapeController(Shape model, BasicView view) {
		super(model, view);
	}

	/**
	 * Set the shape's parent shape. Shapes can have at most one parent, and
	 * this operation will remove any existing parent.
	 * 
	 * @param parent
	 *            The new shape which serves as this shape's parent.
	 */
	public void setParent(IController parent) {
		((Shape) model).setParent(parent);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.eavp.viz.modeling.IController#clone()
	 */
	@Override
	public Object clone() {

		// Create a new shape from clones of the model and view
		ShapeController clone = new ShapeController();

		// Copy any other data into the clone
		clone.copy(this);

		return clone;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.eavp.viz.modeling.IController#copy(org. eclipse.
	 * ice.viz.service.modeling.IController)
	 */
	@Override
	public void copy(IController source) {

		// Check that the source object is an IController, failing
		// silently if not and casting it if so
		if (!(source instanceof ShapeController)) {
			return;
		}
		BasicController castObject = (BasicController) source;

		// Create the model and give it a reference to this
		model = new Shape();
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
	 * @see org.eclipse.eavp.viz.modeling.base.ITransparentController#
	 * setTransparentMode(boolean)
	 */
	@Override
	public void setTransparentMode(boolean transparent) {
		((ITransparentView) view).setTransparentMode(transparent);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.eavp.viz.modeling.base.IWireframeController#setWireFrameMode(
	 * boolean)
	 */
	@Override
	public void setWireframeMode(boolean on) {
		((IWireframeView) view).setWireframeMode(on);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.eavp.viz.modeling.base.ITransparentController#
	 * getTransparentMode()
	 */
	@Override
	public boolean isTransparent() {
		return ((ITransparentView) view).isTransparent();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.eavp.viz.modeling.base.IWireframeController#getWireFrameMode(
	 * )
	 */
	@Override
	public boolean isWireframe() {
		return ((IWireframeView) view).isWireframe();
	}

}
