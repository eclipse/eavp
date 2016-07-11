/*******************************************************************************
 * Copyright (c) 2016 UT-Battelle, LLC.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   Robert Smith
 *******************************************************************************/
package org.eclipse.eavp.viz.service.geometry.widgets;

import org.eclipse.january.geometry.INode;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

/**
 * This class consists of two SWT controls, a Label and a Text box. Each widget
 * is associated with a INode and a string property name. The widget is
 * responsible for displaying the property name as a label and getting/setting
 * the property from the INode in the Text box, and for hiding itself when the
 * INode is null.
 * 
 * @author Robert Smith
 *
 */
public class TransformationPropertyWidget {

	/**
	 * The label displaying the property name.
	 */
	protected Label label;

	/**
	 * The current property's name.
	 */
	protected String name;

	/**
	 * The parent composite which contains this widget.
	 */
	protected Composite parent;

	/**
	 * The source node from which the displayed property originated and which is
	 * to be updated based on user actions.
	 */
	protected INode source;

	/**
	 * The text box allowing the property's value to be edited.
	 */
	protected RealSpinner spinner;

	/**
	 * The current property value.
	 */
	protected double value;

	/**
	 * The default constructor, giving the two controls that the widget will
	 * manage.
	 * 
	 * @param label
	 *            The label that will display the property name.
	 * @param text
	 *            The spinner allowing the property's value to be edited.
	 */
	public TransformationPropertyWidget(Composite parent) {

		// Save the reference to the parent
		this.parent = parent;

		// Create a label and spinner
		label = new Label(parent, SWT.NONE);
		label.setLayoutData(
				new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1));
		spinner = new RealSpinner(parent);
		spinner.getControl().setLayoutData(
				new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1));

		// Set the controls to be invisible, initially
		// label.setVisible(false);
		spinner.setVisible(false);

		// Listen to the spinner
		spinner.listen(new RealSpinnerListener() {

			@Override
			public void update(RealSpinner realSpinner) {

				// Get the spinner's new value and update the source's property
				// with it
				value = realSpinner.getValue();

				// Set the source's property if the widget currently has a
				// source
				source.setProperty(name, value);
			}

		});
	}

	/**
	 * Set the property displayed by this widget. If the property cannot be
	 * displayed, such as if it is a null value, the widget will hide its
	 * controls.
	 * 
	 * @param source
	 *            The INode whose property is being displayed. If null, the
	 *            widget will hide its controls.
	 * @param name
	 *            The property's name, which will be displayed ad a label. If
	 *            null, the widget will hide its controls
	 * @param value
	 *            The property's current value, which will initialize the
	 *            spinner.
	 */
	public void setProperty(INode source, String name, double value) {

		// Save the variables
		this.name = name;
		this.value = value;
		this.source = source;

		// If the property is valid, handle it
		if (source != null && name != null) {

			// Set up the controls to have the correct information
			label.setText(name);
			spinner.setValue(value);

			// Set the controls to be visible
			label.setVisible(true);
			spinner.setVisible(true);

			// Layout the parent so the label can be redrawn large enough to
			// hold its text, then layout the next composite up so that this
			// widget will be sized large enough to hold its label
			parent.layout();
			parent.getParent().layout();

		}

		// If the property cannot be displayed, hide the controls instead
		else {
			label.setVisible(false);
			spinner.setVisible(false);
		}
	}
}
