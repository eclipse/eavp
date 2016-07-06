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
package org.eclipse.eavp.viz.service.geometry.reactor;

import org.eclipse.eavp.viz.modeling.TubeController;
import org.eclipse.eavp.viz.modeling.base.BasicController;
import org.eclipse.eavp.viz.modeling.base.BasicView;
import org.eclipse.eavp.viz.modeling.base.IController;
import org.eclipse.eavp.viz.modeling.base.IWireframeController;
import org.eclipse.eavp.viz.modeling.base.IWireframeView;

/**
 * A Pipe part for the Reactor Analyzer.
 * 
 * @author Robert Smith
 *
 */
public class PipeController extends TubeController
		implements IWireframeController {

	/**
	 * The nullary constructor.
	 */
	public PipeController() {
		super();
	}

	/**
	 * The default constructor
	 * 
	 * @param model
	 * @param view
	 */
	public PipeController(PipeRefactor model, BasicView view) {
		super(model, view);
	}

	/**
	 * Get the farthest points in all three directions for the pipe's lower end
	 * 
	 * @return The pipe's lower boundary's minimum and maximum x, y, and z
	 *         coordinates
	 */
	public Extrema getLowerExtrema() {
		return ((IPipeView) view).getLowerExtrema();
	}

	/**
	 * Convenience getter method for the number of rods.
	 * 
	 * @return The number of rods in a SubChannel pipe
	 */
	public int getNumRods() {
		return ((PipeRefactor) model).getNumRods();
	}

	/**
	 * Convenience getter method for the pitch
	 * 
	 * @return The pipe's pitch
	 */
	public double getPitch() {
		return ((PipeRefactor) model).getPitch();
	}

	/**
	 * Convenience getter method for the rod diameter
	 * 
	 * @return The pipe's rod diameter, under the assumption that all rods are
	 *         of uniform size.
	 */
	public double getRodDiameter() {
		return ((PipeRefactor) model).getRodDiameter();
	}

	/**
	 * Get the farthest points in all three directions for the pipe's upper end
	 * 
	 * @return The pipe's upper boundary's minimum and maximum x, y, and z
	 *         coordinates
	 */
	public Extrema getUpperExtrema() {
		return ((IPipeView) view).getUpperExtrema();
	}

	/**
	 * Convenience setter method for a SubChannel Pipe's number of rods. Does
	 * nothing for non-SubChannel pipes.
	 * 
	 * @param numRods
	 *            The number of rods in the SubChannel
	 */
	public void setNumRods(int numRods) {
		((PipeRefactor) model).setNumRods(numRods);
	}

	/**
	 * Convenience setter method for the pipe's pitch
	 * 
	 * @param pitch
	 */
	public void setPitch(double pitch) {
		((PipeRefactor) model).setPitch(pitch);
	}

	/**
	 * Convenience setter method for the pipe's rod diameter
	 * 
	 * @param rodDiameter
	 *            The pipe's rod diameter
	 */
	public void setRodDiameter(double rodDiameter) {
		((PipeRefactor) model).setRodDiameter(rodDiameter);
	}

	/**
	 * Convience setter method for the pipe's length
	 * 
	 * @param length
	 *            The pipe's length
	 */
	public void setLength(Double length) {
		((PipeRefactor) model).setLength(length);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.eavp.viz.service.reactor.javafx.datatypes.WireFramePart#
	 * setWireFrameMode(boolean)
	 */
	@Override
	public void setWireframeMode(boolean on) {
		((IWireframeView) view).setWireframeMode(on);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.eavp.viz.modeling.AbstractController#clone()
	 */
	@Override
	public Object clone() {

		// Create a new shape from clones of the model and view
		PipeController clone = new PipeController();

		// Copy any other data into the clone
		clone.copy(this);

		return clone;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.eavp.viz.modeling.AbstractController#copy(org. eclipse.
	 * ice.viz.service.modeling.AbstractController)
	 */
	@Override
	public void copy(IController source) {

		// Check that the source object is an IController, failing
		// silently if not and casting it if so
		if (!(source instanceof PipeController)) {
			return;
		}
		BasicController castObject = (BasicController) source;

		// Create the model and give it a reference to this
		model = new PipeRefactor();
		model.setController(this);

		// Copy the other object's data members
		model.copy(castObject.getModel());
		view = (BasicView) castObject.getView().clone();

		// Register as a listener to the model and view
		model.register(this);
		view.register(this);
	}

}
