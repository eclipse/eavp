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
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
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
	 * Whether this widget is currently displaying a property for the source's
	 * IRenderElement rather than the INode itself.
	 */
	protected boolean decoratorProperty;

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
	protected ISpinner spinner;

	/**
	 * The current property value.
	 */
	protected Number value;

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
				new GridData(SWT.CENTER, SWT.CENTER, false, false, 1, 1));
		label.setBackground(
				Display.getCurrent().getSystemColor(SWT.COLOR_WHITE));
	}

	/**
	 * Getter method for the widget's wrapped Text control.
	 * 
	 * @return The widget's text control.
	 */
	public Control getTextControl() {
		return spinner.getControl();
	}

	/**
	 * Set the inclusive bounds for the widget's possible values.
	 * 
	 * @param min
	 *            The minimum value the widget will accept.
	 * @param max
	 *            The maximum value the widget will accept.
	 */
	public void setBounds(Number min, Number max) {
		spinner.setBounds(min, max);
	}

	/**
	 * Set the widget to handle a property of either the source or its
	 * IRenderElement.
	 * 
	 * @param decoratorProperty
	 *            The widget's new assignment for the location of its managed
	 *            property. If false, the property will be set the the source
	 *            INode. If true, the property will be set to the source's
	 *            IRenderElement.
	 */
	public void setDecoratorProperty(boolean decoratorProperty) {
		this.decoratorProperty = decoratorProperty;
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
	public void setProperty(INode source, String name, Number newValue) {

		// Save the variables
		this.name = name;
		this.value = newValue;
		this.source = source;

		// If the property is valid, handle it
		if (source != null && name != null) {

			// Dispose the old spinner
			if (spinner != null) {
				spinner.getControl().dispose();
			}

			// Create a new one of the correct type for the input
			if (value.getClass() == Double.class) {
				spinner = new RealSpinner(parent);
			} else {
				spinner = new IntegerSpinner(parent);
			}
			spinner.getControl().setLayoutData(
					new GridData(SWT.CENTER, SWT.CENTER, false, false, 1, 1));
			spinner.getControl().setBackground(
					Display.getCurrent().getSystemColor(SWT.COLOR_WHITE));

			// Set the controls to be invisible, initially
			// label.setVisible(false);
			spinner.setVisible(false);

			// Listen to the spinner
			spinner.listen(new ISpinnerListener() {

				@Override
				public void update(ISpinner realSpinner) {

					// Get the spinner's new value and update the source's
					// property
					// with it
					value = realSpinner.getValue();

					// Set the source's property if the widget currently has a
					// source
					if (!decoratorProperty) {
						source.setProperty(name, value.doubleValue());
					} else {
						source.changeDecoratorProperty(name, value);
					}

				}

			});

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
			if (spinner != null) {
				spinner.setVisible(false);
			}
		}
	}
}
