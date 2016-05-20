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

/**
 * A FieldVariable is a variable in a finite element method problem,
 * representing some physical quantity of the Mesh. FieldVariables serve as
 * descriptions of such a variable for an entire mesh, allowing each instance of
 * a Face subclass in the same mesh to have an instance setting that
 * FieldVariable's value for that entity.
 * 
 * @author Robert Smith
 *
 */
public class FieldVariable {

	/**
	 * An optional number giving the field a default value with which it will be
	 * initialized and which will be set in cases where this variable is not
	 * specified for a given Face.
	 */
	private Double defaultValue;

	/**
	 * The FieldVariable's name.
	 */
	private String name;

	/**
	 * The units in which the FieldVariable is to be measured.
	 */
	private String units;

	/**
	 * The default constructor.
	 * 
	 * @param name
	 *            The variable's name.
	 * @param units
	 *            The units used to measure the variable.
	 */
	public FieldVariable(String name, String units) {
		this.name = name;
		this.units = units;
		defaultValue = null;
	}

	/**
	 * A constructor that also allows the setting of a default value for this
	 * variable.
	 * 
	 * @param name
	 *            The variable's name.
	 * @param units
	 *            The units used to measure the variable.
	 * @param defaulValue
	 *            The starting value for this variable
	 */
	public FieldVariable(String name, String units, double defaulValue) {
		this(name, units);
		this.defaultValue = defaultValue;
	}

	/**
	 * Get this variable's default value.
	 * 
	 * @return The variable's default value.
	 */
	public double getDefaultValue() {
		return defaultValue;
	}

	/**
	 * Getter method for the variable's name.
	 * 
	 * @return The variable's name.
	 */
	public String getName() {
		return name;
	}

	/**
	 * Getter method for the variable's units.
	 * 
	 * @return The variable's units.
	 */
	public String getUnits() {
		return units;
	}
}
