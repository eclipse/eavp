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

import org.eclipse.eavp.viz.service.geometry.widgets.RealSpinner;
import org.eclipse.eavp.viz.service.geometry.widgets.TransformationPropertyWidget;
import org.eclipse.january.geometry.GeometryFactory;
import org.eclipse.january.geometry.Shape;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
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

		// Create a test widget
		TestTransformationPropertyWidget widget = new TestTransformationPropertyWidget(
				new Composite(new Shell(Display.getDefault()), SWT.NONE));

		// Create a shape
		Shape shape = GeometryFactory.eINSTANCE.createShape();
		shape.setProperty("test", 0);

		// Set the widget to display the shape's "test" property
		widget.setProperty(shape, "test", 0);

		// The label should be visible and displaying the word "test"
		assertTrue("test".equals(widget.getLabel().getText()));

		// The text box should be visible and displaying the value 0
		assertTrue("0"
				.equals(((Text) widget.getSpinner().getControl()).getText()));

		// Set the widget's text
		((Text) widget.getSpinner().getControl()).setText("8");

		// The shape's property should have been updated to the new value
		assertEquals(8, shape.getProperty("test"));

		// Create a second shape
		Shape shape2 = GeometryFactory.eINSTANCE.createShape();
		shape2.setProperty("test", 0);

		// Set the widget to display the same property on the new shape
		widget.setProperty(shape2, "test", 0);

		// Neither shape's value should have been changed by this
		assertEquals(8, shape.getProperty("test"));
		assertEquals(0, shape2.getProperty("test"));

		// Set the widget's value
		((Text) widget.getSpinner().getControl()).setText("9");

		// The original shape's value should not have been changed
		assertEquals(8, shape.getProperty("test"));

		// Check that the current value had its property set
		assertEquals(9, shape2.getProperty("test"));

		// Set a second property on the shape
		shape2.setProperty("second", 1);
		widget.setProperty(shape2, "second", 1);

		// The shape's properties should not have been changed
		assertEquals(8, shape2.getProperty("test"));
		assertEquals(1, shape2.getProperty("second"));

		// Set the widget's value
		((Text) widget.getSpinner().getControl()).setText("2");

		// Only the current property should have changed
		assertEquals(8, shape2.getProperty("test"));
		assertEquals(2, shape2.getProperty("second"));

		// Set a null shape
		widget.setProperty(null, null, 0);
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
		public RealSpinner getSpinner() {
			return spinner;
		}

	}
}
