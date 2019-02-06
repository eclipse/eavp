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

import com.vaadin.shared.ui.ValueChangeMode;
import com.vaadin.ui.TextField;

/**
 * An extension of the Vaadin TextField that only accepts numbers as input,
 * possibly within some specified bounds.
 *
 * @author Robert Smith
 *
 * @param <T>
 *            The class of the number this field will accept.
 */
abstract public class NumericalTextField<T extends Number> extends TextField {

	/**
	 * The minimum valid value.
	 */
	protected T lowerBound;

	/**
	 * The current value.
	 */
	protected T numericalValue;

	/**
	 * Whether the field is optional, and thus may take an empty string to
	 * represent a null value.
	 */
	protected boolean optional;

	/**
	 * The maximum valid value.
	 */
	protected T upperBound;

	/**
	 * The nullary constructor.
	 */
	public NumericalTextField() {
		super();
		
		//Wait 5 seconds to make sure the user is done typing
		setValueChangeMode(ValueChangeMode.LAZY);
		this.setValueChangeTimeout(5000);
		
		// Initialize the field to be required
		optional = false;

		// Get a final self reference
		final NumericalTextField self = this;

		this.addValueChangeListener(new ValueChangeListener<String>() {

			/*
			 * (non-Javadoc)
			 *
			 * @see
			 * com.vaadin.data.HasValue.ValueChangeListener#valueChange(com.
			 * vaadin.data.HasValue.ValueChangeEvent)
			 */
			@Override
			public void valueChange(ValueChangeEvent<String> event) {

				// If the new value is not valid, reset to the old value.
				if (!validate(event.getValue())) {
					self.setValue(event.getOldValue());
				}
			}

		});
	}

	/**
	 * The dconstructor for creating a field that has lower and upper bounds
	 * set.
	 *
	 * @param lowerBound
	 *            The lower bound
	 * @param upperBound
	 *            The upper bound
	 */
	public NumericalTextField(T lowerBound, T upperBound) {
		this();
		setBounds(lowerBound, upperBound);
	}

	/**
	 * Get the value of this field as a number.
	 *
	 * @return
	 */
	public T getNumericalValue() {
		return numericalValue;
	}

	/**
	 * Check whether the field is optional.
	 * 
	 * @return True if the field is optional, false if it is not.
	 */
	public boolean isOptional() {
		return optional;
	}

	/**
	 * Set the bounds for the range of valid values and update the numerical
	 * value to respect them. Settng a null bound will remove that bound.
	 *
	 * @param lowerBound
	 *            The lower bound
	 * @param upperBound
	 *            The upper bound
	 */
	public abstract void setBounds(T lowerBound, T upperBound);

	/**
	 * Set the field as either optional or required.
	 * 
	 * @param optional
	 *            True to set the field to required, false to set it as
	 *            optional.
	 */
	public void setOptional(boolean optional) {
		this.optional = optional;
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
		if ("-".equals(value) || validate(value)) {

			// Set the value to the text field, replacing null with the empty
			// string
			if (value != null) {
				super.setValue(value);
			} else {
				super.setValue("");
			}
		}
	}
	
	/**
	 * Set the value to a number and display a string representation of it.
	 * 
	 * @param value The new value
	 */
	public void setNumericalValue(T value) {
		numericalValue = value;
		super.setValue(String.valueOf(value));
	}

	/**
	 * Validate whether the proposed string is a valid value for this field and
	 * save it as the new value if so.
	 *
	 * @param value
	 *            The string to test.
	 * @return True if value is parsable as a number and is no lower than
	 *         lowerBound and no higher than upperBound, if the bounds have been
	 *         set, or if this field is optional and the value is null.
	 */
	protected abstract boolean validate(String value);
}
