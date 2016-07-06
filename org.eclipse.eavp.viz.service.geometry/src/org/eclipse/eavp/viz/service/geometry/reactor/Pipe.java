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

import org.eclipse.january.geometry.impl.TubeImpl;

/**
 * A directional Tube with extra information used in a reactor.
 * 
 * @author Robert Smith
 *
 */
public class Pipe extends TubeImpl {

	/**
	 * The number of rods present in this pipe.
	 */
	private int numRods;

	/**
	 * This pipe's pitch.
	 */
	private double pitch;

	/**
	 * The diameter of the pipe's rods.
	 */
	private double rodDiameter;

	/**
	 * The default constructor.
	 * 
	 * @param length
	 *            The pipe's length.
	 * @param radius
	 *            The pipe's radius.
	 */
	public Pipe(double length, double radius) {
		super();

		// Initialize the data members
		setHeight(length);
		setRadius(radius);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.january.geometry.impl.TubeImpl#getInnerRadius()
	 */
	@Override
	public double getInnerRadius() {
		return getRadius();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.january.geometry.impl.TubeImpl#setInnerRadius(double)
	 */
	@Override
	public void setInnerRadius(double newInnerRadius) {

		// Set the radius instead
		setRadius(newInnerRadius);
	}

	/**
	 * Getter method for the number of rods.
	 * 
	 * @return The number of rods in the pipe.
	 */
	public int getNumRods() {
		return numRods;
	}

	/**
	 * Getter method for the pitch.
	 * 
	 * @return The pipe's pitch.
	 */
	public double getPitch() {
		return pitch;
	}

	/**
	 * Getter method for the rod diameter.
	 * 
	 * @return The rods' diameter.
	 */
	public double getRodDiameter() {
		return rodDiameter;
	}

	/**
	 * Setter method for the number of rods.
	 * 
	 * @param numRods
	 *            The number of rods in the pipe.
	 */
	public void setNumRods(int numRods) {
		this.numRods = numRods;
	}

	/**
	 * Setter method for the pitch.
	 * 
	 * @param pitch
	 *            The pipe's new pitch.
	 */
	public void setPitch(double pitch) {
		this.pitch = pitch;
	}

	/**
	 * Setter method for the rod diameter.
	 * 
	 * @param rodDiameter
	 *            The pipe's new rod diameter.
	 */
	public void setRodDiameter(double rodDiameter) {
		this.rodDiameter = rodDiameter;
	}
}
