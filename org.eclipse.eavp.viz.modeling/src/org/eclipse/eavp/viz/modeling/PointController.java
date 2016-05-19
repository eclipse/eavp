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

/**
 * A controller for a Point model part.
 * 
 * @author Robert Smith
 *
 */
public class PointController extends BasicController {

	/**
	 * The nullary constructor.
	 */
	public PointController() {
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
	public PointController(Point model, BasicView view) {
		super(model, view);
	}

	/**
	 * Getter for the x coordinate.
	 * 
	 * @return The x coordinate
	 */
	public double getX() {
		return ((Point) model).getX();
	}

	/**
	 * Setter for the x coordinate
	 * 
	 * @param x
	 *            The point's new x coordinate
	 */
	public void setX(double x) {
		((Point) model).setX(x);
	}

	/**
	 * Getter for the y coordinate
	 * 
	 * @return The y coordinate
	 */
	public double getY() {
		return ((Point) model).getY();
	}

	/**
	 * Setter for the y coordinate
	 * 
	 * @param y
	 *            The new y coordinate
	 */
	public void setY(double y) {
		((Point) model).setY(y);
	}

	/**
	 * Getter for the z coordinate
	 * 
	 * @return The z coordinate
	 */
	public double getZ() {
		return ((Point) model).getZ();
	}

	/**
	 * Setter for the z coordinate
	 * 
	 * @param z
	 *            The new z coordinate
	 */
	public void setZ(double z) {
		((Point) model).setZ(z);
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
		((Point) model).updateLocation(x, y, z);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#clone()
	 */
	@Override
	public Object clone() {

		// Create a copy of the model
		PointController clone = new PointController();
		clone.copy(this);

		// Refresh the view to be in sync with the model
		clone.refresh();

		return clone;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.eavp.viz.modeling.AbstractController#copy(org.
	 * eclipse. ice.viz.service.modeling.AbstractController)
	 */
	@Override
	public void copy(IController otherObject) {

		// Check that the source object is an AbstractController, failing
		// silently if not and casting it if so
		if (!(otherObject instanceof PointController)) {
			return;
		}
		BasicController castObject = (BasicController) otherObject;

		// Create the model and give it a reference to this
		model = new Point();
		model.setController(this);

		// Copy the other object's data members
		model.copy(castObject.getModel());
		view = (BasicView) castObject.getView().clone();

		// Register as a listener to the model and view
		model.register(this);
		view.register(this);
	}
}
