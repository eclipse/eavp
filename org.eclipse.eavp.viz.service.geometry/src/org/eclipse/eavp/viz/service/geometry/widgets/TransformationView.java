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

import java.util.ArrayList;

import org.eclipse.jface.databinding.swt.IWidgetValueProperty;
import org.eclipse.jface.databinding.swt.WidgetProperties;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.ui.part.ViewPart;

import geometry.GeometryFactory;
import geometry.Vertex;
import model.IRenderElement;

/**
 * <p>
 * Eclipse UI view with a series of inputs for manipulating the position and
 * transformation of a selected shape
 * </p>
 * 
 * @author Andrew P. Belt, Robert Smith
 */
public class TransformationView extends ViewPart {

	/**
	 * Eclipse view ID
	 */
	public static final String ID = "org.eclipse.eavp.viz.service.geometry.widgets.TransformationView";
	/**
	 * The tree spinners that set the object's center
	 */
	private RealSpinner[] translateSpinners = new RealSpinner[3];

	/**
	 * A list of optional spinners which have been added to the view to expose
	 * all of the shape's properties.
	 */
	private ArrayList<RealSpinner> propertySpinners = new ArrayList<RealSpinner>();

	/**
	 * A combo box listing the options for how to display the shape.
	 */
	private Combo opacityCombo;

	/**
	 * <p>
	 * The IShape which represents the state of the TransformationView
	 * </p>
	 * 
	 */
	private IRenderElement currentShape;

	/**
	 * Defines whether degrees or radians are used for rotation angles
	 */
	private boolean degrees = true;

	/**
	 * 
	 * @param enabled
	 */
	private void setSpinnersEnabled(boolean enabled) {

		// Enable each spinner
		for (RealSpinner translateSpinner : translateSpinners) {
			translateSpinner.getControl().setEnabled(enabled);
		}
		for (RealSpinner spinner : propertySpinners) {
			spinner.getControl().setEnabled(enabled);
		}

	}

	/**
	 * 
	 * @param parent
	 */
	@Override
	public void createPartControl(Composite parent) {

		// Create a scrolled composite - scroll bars!
		ScrolledComposite scrolledParent = new ScrolledComposite(parent,
				SWT.BORDER | SWT.H_SCROLL | SWT.V_SCROLL);
		// Create a sub-composite to hold the actual widgets
		final Composite realParent = new Composite(scrolledParent, SWT.NONE);

		// Create the layout for the view
		layout(realParent);

		// Tell the scrolled composite to watch the real parent composite
		scrolledParent.setContent(realParent);
		// Set the expansion sizes and minimum size of the scrolled composite
		scrolledParent.setExpandHorizontal(true);
		scrolledParent.setExpandVertical(true);
		scrolledParent
				.setMinSize(realParent.computeSize(SWT.DEFAULT, SWT.DEFAULT));
		scrolledParent.setShowFocusedControl(true);

	}

	private void layout(Composite parent) {

		// Main layout
		parent.setLayout(new GridLayout(4, false));
		parent.setLayoutData(
				new GridData(SWT.FILL, SWT.FILL, false, false, 1, 1));

		// Coordinate labels

		Label labelX = new Label(parent, SWT.NONE);
		labelX.setLayoutData(
				new GridData(SWT.CENTER, SWT.CENTER, false, false, 1, 1));
		labelX.setText("X");

		Label labelY = new Label(parent, SWT.NONE);
		labelY.setLayoutData(
				new GridData(SWT.CENTER, SWT.CENTER, false, false, 1, 1));
		labelY.setText("Y");

		Label labelZ = new Label(parent, SWT.NONE);
		labelZ.setLayoutData(
				new GridData(SWT.CENTER, SWT.CENTER, false, false, 1, 1));
		labelZ.setText("Z");

		// Translation
		Label translateLabel = new Label(parent, SWT.NONE);
		translateLabel.setLayoutData(
				new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1));
		translateLabel.setText("Translate");
		for (int i = 0; i < 3; i++) {
			translateSpinners[i] = new RealSpinner(parent);
			translateSpinners[i].getControl().setLayoutData(
					new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
			translateSpinners[i].setBounds(-1.0e6, 1.0e6);
		}

		// Create a label for the opacity combo
		Label opacityLabel = new Label(parent, SWT.NONE);
		opacityLabel.setLayoutData(
				new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1));
		opacityLabel.setText("Opacity:");

		// Initialize the opacity combo box
		opacityCombo = new Combo(parent, SWT.READ_ONLY);
		opacityCombo.setLayoutData(
				new GridData(SWT.FILL, SWT.FILL, false, false, 1, 1));
		opacityCombo
				.setItems(new String[] { "Solid", "Wireframe", "Transparent" });

		// Empty the old list of property spinners
		propertySpinners.clear();

		// Iterate through the shape's properties, giving each one its own
		// section in the view
		if (currentShape != null) {
			for (String property : currentShape.getBase().getPropertyNames()) {

				// Create a label with the property's name
				Label propertyLabel = new Label(parent, SWT.NONE);
				propertyLabel.setLayoutData(
						new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1));
				propertyLabel.setText(property + ":");

				// Create a spinner for the property's value. All properties
				// have
				// doubles for values.
				RealSpinner propertySpinner = new RealSpinner(parent);
				propertySpinner.getControl().setLayoutData(
						new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
				propertySpinner.setBounds(-1.0e6, 1.0e6);

				// Set the spinner's name
				propertySpinner.setName(property);

				// Add the spinner to the list
				propertySpinners.add(propertySpinner);
			}
		}

		// Set the initial shape
		createListeners();
		setShape(null);
	}

	/**
	 * <p>
	 * Sets the input shape and updates the state of the TransformationView to
	 * manipulate the input shape's transformation
	 * </p>
	 * <p>
	 * Passing a null value for the input shape reinitializes and disables all
	 * the text boxes in the view.
	 * </p>
	 * 
	 * @param shape
	 */
	public void setShape(IRenderElement shape) {

		this.currentShape = shape;

		if (shape != null) {

			// Get the shape's current status
			double opacity = shape.getProperty("opacity") == null ? 100
					: (double) shape.getProperty("opacity");
			boolean wireframe = shape.getProperty("wireframe") == null ? false
					: (boolean) shape.getProperty(("wireframe"));

			// Set the opacity combo's value based on the shape's transparency
			// and wireframe status
			if (opacity == 0d) {
				opacityCombo.select(2);
			} else if (wireframe) {
				opacityCombo.select(1);
			} else {
				opacityCombo.select(0);
			}

			// Set the spinner values

			Vertex center = shape.getBase().getCenter();
			double[] translations = new double[] { center.getX(), center.getY(),
					center.getZ() };
			for (int i = 0; i < 3; i++) {
				translateSpinners[i].setValue(translations[i]);
			}
		}

		// Set the enabled state of the spinners, depending on whether the
		// shape parameter is null
		setSpinnersEnabled(shape != null);
		opacityCombo.setEnabled(shape != null);
	}

	/**
	 * Create the listeners for each control on the view.
	 */
	private void createListeners() {

		IWidgetValueProperty property = WidgetProperties.selection();

		// Create anonymous listener

		RealSpinnerListener listener = new RealSpinnerListener() {

			@Override
			public void update(RealSpinner realSpinner) {

				// Handle a null currentShape

				if (currentShape == null) {
					return;
				}

				// Get the center point from the spinners
				Vertex center = GeometryFactory.eINSTANCE.createVertex();
				center.setX(translateSpinners[0].getValue());
				center.setY(translateSpinners[1].getValue());
				center.setZ(translateSpinners[2].getValue());

				// Reset the shape's center
				if (!currentShape.getBase().getCenter().equals(center)) {
					currentShape.getBase().setCenter(center);
				}
			}
		};

		// Add the listener to each spinner
		for (RealSpinner spinner : translateSpinners) {
			spinner.listen(listener);
		}

		// Add a listener for the combo that will set the shape's opacity
		opacityCombo.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent e) {

				// Get the combo's contents
				String selection = opacityCombo.getText();

				// If it is opaque, set both properties to false
				if ("Solid".equals(selection)) {
					currentShape.setProperty("opacity", 100);
					currentShape.setProperty("wireframe", false);
				}

				// If it is transparent, set it to transparent, leaving its
				// wireframe value alone
				else if ("Transparent".equals(selection)) {
					currentShape.setProperty("opacity", 0);
				}

				// If it is wireframe, set transparency off but wireframe on
				else {
					currentShape.setProperty("opacity", 100);
					currentShape.setProperty("wireframe", true);
				}

			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				// Nothing to do
			}

		});

		// Create a spinner listener that will update the shape's properties
		RealSpinnerListener propertyListener = new RealSpinnerListener() {

			@Override
			public void update(RealSpinner realSpinner) {

				// Get the spinner's name and value
				String name = realSpinner.getName();
				double value = realSpinner.getValue();

				// If the value in the spinner has been changed, set the new
				// value to the shape
				if (value != currentShape.getBase().getProperty(name)) {
					currentShape.setProperty(realSpinner.getName(),
							realSpinner.getValue());
				}
			}
		};

		// Add the listener to each of the property spinners
		for (RealSpinner spinner : propertySpinners) {
			spinner.listen(propertyListener);
		}
	}

	@Override
	public void setFocus() {
		// TODO Auto-generated method stub
	}
}