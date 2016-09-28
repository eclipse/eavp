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
 *   Claire Saunders, Matthew Wang, Anna Wojtowicz
 *   Robert Smith
 *******************************************************************************/
package org.eclipse.eavp.viz.service.geometry.widgets;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.events.FocusListener;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.KeyListener;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Text;

/**
 * <p>
 * Wrapper for a spinner-like SWT widget which supports integers and helpful key
 * commands for value manipulation
 * </p>
 * 
 * @author Andrew P. Belt, Robert Smith
 */
public class IntegerSpinner implements ISpinner<Integer> {
	/**
	 * <p>
	 * The internal text box
	 * </p>
	 * 
	 */
	private Text textWidget;

	/**
	 * The minimum value to allow, inclusive
	 */
	private int minimum = Integer.MIN_VALUE;

	/**
	 * The maximum value to allow, inclusive
	 */
	private int maximum = Integer.MAX_VALUE;

	/**
	 * A human readable name describing the property represented by this
	 * spinner's value.
	 */
	private String name;

	/**
	 * The latest valid value
	 */
	private int value;

	/**
	 * The listeners to be notified of changes to the value
	 */
	private List<ISpinnerListener> listeners = new ArrayList<ISpinnerListener>();

	/**
	 * <p>
	 * Initializes the object and adds the widget to the given parent
	 * </p>
	 * 
	 * @param parent
	 *            <p>
	 *            The parent of the new RealSpinner
	 *            </p>
	 */
	public IntegerSpinner(Composite parent) {

		textWidget = new Text(parent, SWT.LEFT | SWT.BORDER);

		// Add a FocusListener

		FocusListener focusListener = new FocusListener() {

			@Override
			public void focusGained(FocusEvent event) {

				// Select all the text in the Text widget

				textWidget.selectAll();
			}

			@Override
			public void focusLost(FocusEvent event) {

				// Just validate the text

				validateText();
			}
		};

		textWidget.addFocusListener(focusListener);

		// Add a DefaultSelection listener

		Listener defaultSelectionListener = new Listener() {

			@Override
			public void handleEvent(Event event) {

				// Validate the text and select all in the text box

				validateText();
				textWidget.selectAll();
			}
		};

		textWidget.addListener(SWT.DefaultSelection, defaultSelectionListener);

		// Add a key listener for key commands

		KeyListener keyListener = new KeyAdapter() {

			@Override
			public void keyPressed(KeyEvent e) {

				int change;

				// Calculate the change amount depending on the button pressed

				if (e.keyCode == SWT.ARROW_UP) {
					change = 1;
				} else if (e.keyCode == SWT.ARROW_DOWN) {
					change = -1;
				} else if (e.keyCode == SWT.PAGE_UP) {
					change = 10;
				} else if (e.keyCode == SWT.PAGE_DOWN) {
					change = -10;
				} else {
					return;
				}

				// Increase the RealSpinner value by the computed amount

				setValue(value + change);
			}
		};

		textWidget.addKeyListener(keyListener);

		// Set the value to zero as a default

		setValue(0);

	}

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
	@Override
	public void setValue(Integer value) {

		// Clip the value to the closest allowed number

		if (value < minimum) {
			value = minimum;
		} else if (value > maximum) {
			value = maximum;
		}
		this.value = value;

		// Set the widget text

		textWidget.setText(String.valueOf(value));

		// Notify each listener

		for (ISpinnerListener listener : listeners) {
			listener.update(this);
		}

	}

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
	@Override
	public Integer getValue() {

		// Return the last value

		return value;

	}

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
	@Override
	public void setBounds(Integer minimum, Integer maximum) {

		// Assert that the bounds contain at least one possible allowed value

		if (minimum > maximum) {
			return;
		}
		// Set the fields

		this.minimum = minimum;
		this.maximum = maximum;

		// Clip the current value if needed

		setValue(value);

	}

	/**
	 * Adds a RealSpinnerListener to its listeners list to be notified of
	 * changes to the value
	 * 
	 * @param listener
	 *            The listener to notified of changes to the value
	 */
	@Override
	public void listen(ISpinnerListener listener) {

		// Add the given listener to the listeners list

		if (listener != null && !listeners.contains(listener)) {
			listeners.add(listener);
		}
	}

	/**
	 * Returns the Text control widget wrapped in this RealSpinner instance
	 * 
	 * @return The Text widget
	 */
	@Override
	public Control getControl() {
		return textWidget;
	}

	/**
	 * Getter method for the name.
	 * 
	 * @return The name of the property controlled by this spinner
	 */
	@Override
	public String getName() {
		return name;
	}

	/**
	 * Setter method for the name.
	 * 
	 * @param name
	 *            The name for the property controlled by this spinner.
	 */
	@Override
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Checks the state of the Text widget and updates the value and text
	 * accordingly
	 */
	protected void validateText() {

		// Check if the input can be parsed as a double

		try {
			// Parse the text and set it as the new value

			int newValue = Integer.valueOf(textWidget.getText());

			setValue(newValue);
		} catch (NumberFormatException e) {

			// Restore the value of the text box to the last valid value

			setValue(value);
		}
	}

	/**
	 * Sets the spinner's visibility.
	 * 
	 * @param visible
	 *            If false, the spinner's text widget will not be drawn.
	 *            Otherwise, the text box will be made visible.
	 */
	@Override
	public void setVisible(boolean visible) {
		textWidget.setVisible(visible);
	}
}
