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
package org.eclipse.eavp.viz.service.geometry.test.widgets;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.eclipse.eavp.viz.service.geometry.widgets.ISpinner;
import org.eclipse.eavp.viz.service.geometry.widgets.ISpinnerListener;
import org.eclipse.eavp.viz.service.geometry.widgets.RealSpinner;
import org.eclipse.eavp.viz.service.geometry.widgets.TransformationPropertyWidget;
import org.eclipse.january.geometry.GeometryFactory;
import org.eclipse.january.geometry.INode;
import org.eclipse.january.geometry.Shape;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.PlatformUI;
import org.junit.Test;

/**
 * A class to test the functionality of the TransformationPropertyWidget.
 * 
 * @author Robert Smith
 *
 */
public class TransformationPropertyWidgetTester {

	/**
	 * Check that the widget will correctly display its property and allow it to
	 * be edited.
	 */
	@Test
	public void checkWidget() {

		// Run on the UI thread
		PlatformUI.getWorkbench().getDisplay().syncExec(new Runnable() {
			@Override
			public void run() {

				// Create a test widget
				TestTransformationPropertyWidget widget = new TestTransformationPropertyWidget(
						new Composite(new Shell(Display.getDefault()),
								SWT.NONE));

				// Create a shape
				Shape shape = GeometryFactory.eINSTANCE.createShape();
				shape.setProperty("test", 0d);

				// Set the widget to display the shape's "test" property
				widget.setProperty(shape, "test", 0d);

				// The label should be visible and displaying the word "test"
				assertTrue("test".equals(widget.getLabel().getText()));

				// The text box should be visible and displaying the value 0
				assertTrue("0.0".equals(
						((Text) widget.getSpinner().getControl()).getText()));

				// Set the widget's text
				((Text) widget.getSpinner().getControl()).setText("8");
				widget.update();

				// The shape's property should have been updated to the new
				// value
				assertEquals(8, shape.getProperty("test"), 0.1);

				// Create a second shape
				Shape shape2 = GeometryFactory.eINSTANCE.createShape();
				shape2.setProperty("test", 0d);

				// Set the widget to display the same property on the new shape
				widget.setProperty(shape2, "test", 0d);

				// Neither shape's value should have been changed by this
				assertEquals(8, shape.getProperty("test"), 0.1);
				assertEquals(0, shape2.getProperty("test"), 0.1);

				// Set the widget's value
				((Text) widget.getSpinner().getControl()).setText("9");
				widget.update();

				// The original shape's value should not have been changed
				assertEquals(8, shape.getProperty("test"), 0.1d);

				// Check that the current value had its property set
				assertEquals(9, shape2.getProperty("test"), 0.1d);

				// Set a second property on the shape
				shape2.setProperty("second", 1d);
				widget.setProperty(shape2, "second", 1d);
				widget.update();

				// The shape's properties should not have been changed
				assertEquals(9, shape2.getProperty("test"), 0.1d);
				assertEquals(1, shape2.getProperty("second"), 0.1d);

				// Set the widget's value
				((Text) widget.getSpinner().getControl()).setText("2");
				widget.update();

				// Only the current property should have changed
				assertEquals(9, shape2.getProperty("test"), 0.1);
				assertEquals(2, shape2.getProperty("second"), 0.1);

				// Set a null shape
				widget.setProperty(null, null, 0);

			}
		});
	}

	/**
	 * An extension of TransofrmationPropertyWidget that exposes several data
	 * members for testing purposes.
	 * 
	 * @author Robert Smith
	 *
	 */
	private class TestTransformationPropertyWidget
			extends TransformationPropertyWidget {

		/**
		 * The default constructor.
		 * 
		 * @param parent
		 *            The composite in which the widget would be drawn.
		 */
		public TestTransformationPropertyWidget(Composite parent) {
			super(parent);

			// Save the reference to the parent
			this.parent = parent;

			// Create a test spinner
			spinner = new TestSpinner(parent);
			spinner.getControl().setLayoutData(
					new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1));

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
					source.setProperty(name, (double) value);
				}

			});
		}

		/**
		 * Getter for the label.
		 * 
		 * @return The widget's label.
		 */
		public Label getLabel() {
			return label;
		}

		/**
		 * Getter for the widget's spinner
		 * 
		 * @return The widget's spinner
		 */
		public ISpinner getSpinner() {
			return spinner;
		}

		/**
		 * Update the widget's value based on the spinner's state.
		 */
		public void update() {
			((TestSpinner) spinner).update();
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see org.eclipse.eavp.viz.service.geometry.widgets.
		 * TransformationPropertyWidget#setProperty(org.eclipse.january.geometry
		 * .INode, java.lang.String, java.lang.Number)
		 */
		@Override
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
					spinner = new TestSpinner(parent);
				}
				spinner.getControl().setLayoutData(new GridData(SWT.CENTER,
						SWT.CENTER, false, false, 1, 1));
				spinner.getControl().setBackground(
						Display.getCurrent().getSystemColor(SWT.COLOR_WHITE));

				// Set the controls to be invisible, initially
				// label.setVisible(false);
				spinner.setVisible(false);

				final INode finalSource = this.source;
				final String finalName = this.name;

				// Listen to the spinner
				spinner.listen(new ISpinnerListener() {

					@Override
					public void update(ISpinner realSpinner) {

						// Get the spinner's new value and update the source's
						// property
						// with it
						value = realSpinner.getValue();

						// Set the source's property if the widget currently has
						// a
						// source
						if (!decoratorProperty) {
							finalSource.setProperty(finalName,
									value.doubleValue());
						} else {
							finalSource.changeDecoratorProperty(finalName,
									value);
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

	/**
	 * A simple extension of RealSpinner that exposes functionality for testing
	 * purposes.
	 * 
	 * @author Robert Smith
	 *
	 */
	private class TestSpinner extends RealSpinner {

		/**
		 * The default constructor.
		 * 
		 * @param parent
		 *            The composite in which the spinner would be drawn.
		 */
		public TestSpinner(Composite parent) {
			super(parent);
		}

		/**
		 * Update the spinner based on the text's contents.
		 */
		public void update() {
			validateText();
		}
	}
}
