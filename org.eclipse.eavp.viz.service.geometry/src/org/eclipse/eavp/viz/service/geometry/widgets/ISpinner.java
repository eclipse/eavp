/*******************************************************************************
 * Copyright (c) 2012, 2014, 2016 UT-Battelle, LLC.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   Initial API and implementation and/or initial documentation - Jay Jay Billings,
 *   Jordan H. Deyton, Dasha Gorin, Alexander J. McCaskey, Taylor Patterson,
 *   Claire Saunders, Matthew Wang, Anna Wojtowicz, Robert Smith
 *******************************************************************************/
package org.eclipse.eavp.viz.service.geometry.widgets;

import org.eclipse.swt.widgets.Control;

/**
 * <p>
 * Wrapper interface for a spinner-like SWT widget which supports numnbers and
 * helpful key commands for value manipulation
 * </p>
 * 
 * @author Andrew P. Belt, Robert Smith
 */
public interface ISpinner<T extends Number> {

	/**
	 * <p>
	 * Returns the real input value
	 * </p>
	 * 
	 * @return
	 *         <p>
	 *         The value
	 *         </p>
	 */
	T getValue();

	/**
	 * <p>
	 * Sets the minimum and maximum bounds (inclusive)
	 * </p>
	 * 
	 * @param minimum
	 *            <p>
	 *            The minimum value to enforce
	 *            </p>
	 * @param maximum
	 *            <p>
	 *            The maximum value to enforce
	 *            </p>
	 */
	void setBounds(T minimum, T maximum);

	/**
	 * Adds a RealSpinnerListener to its listeners list to be notified of
	 * changes to the value
	 * 
	 * @param listener
	 *            The listener to notified of changes to the value
	 */
	void listen(ISpinnerListener listener);

	/**
	 * Returns the Text control widget wrapped in this RealSpinner instance
	 * 
	 * @return The Text widget
	 */
	Control getControl();

	/**
	 * Getter method for the name.
	 * 
	 * @return The name of the property controlled by this spinner
	 */
	String getName();

	/**
	 * Setter method for the name.
	 * 
	 * @param name
	 *            The name for the property controlled by this spinner.
	 */
	void setName(String name);

	/**
	 * <p>
	 * Replaces the value with the given number
	 * </p>
	 * 
	 * @param value
	 *            <p>
	 *            The new value
	 *            </p>
	 */
	void setValue(T value);

	/**
	 * Sets the spinner's visibility.
	 * 
	 * @param visible
	 *            If false, the spinner's text widget will not be drawn.
	 *            Otherwise, the text box will be made visible.
	 */
	void setVisible(boolean visible);

}