/*******************************************************************************
 * Copyright (c) 2019 UT-Battelle, LLC.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   Robert Smith
 *******************************************************************************/
package org.eclipse.eavp.micro.vaadin.csv;

/**
 * An extension of a Vaadin TextField that accepts a decimal number, optionally
 * only within a certain set of bounds.
 *
 * @author Robert Smith
 *
 */
public class DecimalTextField extends NumericalTextField<Double> {

	/**
	 * The nullary constructor.
	 */
	public DecimalTextField() {
		super();
	}

	/**
	 * The constructor for creating a field that has lower and upper bounds
	 * set.
	 *
	 * @param lowerBound
	 *            The lower bound
	 * @param upperBound
	 *            The upper bound
	 */
	public DecimalTextField(Double lowerBound, Double upperBound) {
		super(lowerBound, upperBound);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * org.eclipse.eavp.micro.main.java.vaadin.NumericalTextField#validate(java.
	 * lang.String)
	 */
	@Override
	protected boolean validate(String value) {

		// If the value is null or empty, it is valid only if the field is
		// optional.
		if (value == null || "".equals(value)) {
			if (optional) {
				numericalValue = null;
				return true;
			} else {
				return false;
			}
		}

		try {

			// Get the numerical value of the string
			double newValue = Double.parseDouble(value);

			// Return false if a bound exists but is not respected by the value
			if ((lowerBound != null && newValue < lowerBound)
					|| (upperBound != null && newValue > upperBound)) {
				return false;
			}

			// Save the new value if it is valid
			numericalValue = newValue;

			return true;
		} catch (NumberFormatException e) {

			// Return false if the value is not parsable
			return false;
		}
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * org.eclipse.eavp.micro.main.java.vaadin.NumericalTextField#setBounds(java
	 * .lang.Number, java.lang.Number)
	 */
	@Override
	public void setBounds(Double lowerBound, Double upperBound) {

		// Save the new bounds
		this.lowerBound = lowerBound;
		this.upperBound = upperBound;
		if (numericalValue != null) {
			if (lowerBound != null && numericalValue < lowerBound) {
				numericalValue = lowerBound.doubleValue();
			} else if (upperBound != null && numericalValue > upperBound) {
				numericalValue = upperBound.doubleValue();
			}
		}
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.vaadin.ui.AbstractTextField#setValue(java.lang.String)
	 */
	@Override
	public void setValue(String value) {

		// Override the default behavior to prevent the setting of invalid
		// values.
		if (validate(value)) {
			super.setValue(value);
		}
	}
}
