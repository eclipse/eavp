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

import org.eclipse.eavp.viz.modeling.base.Transformation;
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

import geometry.INode;

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
	// /**
	// *
	// */
	// private RealSpinner[] skewSpinners = new RealSpinner[3];
	// /**
	// *
	// */
	// private RealSpinner sizeSpinner;
	// /**
	// *
	// */
	// private RealSpinner[] scaleSpinners = new RealSpinner[3];
	// /**
	// *
	// */
	// private RealSpinner[] rotationSpinners = new RealSpinner[3];
	/**
	 * 
	 */
	private RealSpinner[] translateSpinners = new RealSpinner[3];

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
	private INode currentShape;

	/**
	 * Defines whether degrees or radians are used for rotation angles
	 */
	private boolean degrees = true;

	/**
	 * 
	 * @param enabled
	 */
	private void setSpinnersEnabled(boolean enabled) {

		// sizeSpinner.getControl().setEnabled(enabled);
		//
		// for (RealSpinner scaleSpinner : scaleSpinners) {
		// scaleSpinner.getControl().setEnabled(enabled);
		// }
		//
		// for (RealSpinner rotationSpinner : rotationSpinners) {
		// rotationSpinner.getControl().setEnabled(enabled);
		// }

		for (RealSpinner translateSpinner : translateSpinners) {
			translateSpinner.getControl().setEnabled(enabled);
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

		// Main layout
		realParent.setLayout(new GridLayout(4, false));
		realParent.setLayoutData(
				new GridData(SWT.FILL, SWT.FILL, false, false, 1, 1));

		// Size parameter
		// Label sizeLabel = new Label(realParent, SWT.NONE);
		// sizeLabel.setLayoutData(
		// new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1));
		// sizeLabel.setText("Size");
		//
		// sizeSpinner = new RealSpinner(realParent);
		// sizeSpinner.getControl().setLayoutData(
		// new GridData(SWT.FILL, SWT.CENTER, true, false, 3, 1));
		// sizeSpinner.setBounds(0.0, 1.0e6);
		// sizeSpinner.setValue(1.0);

		// Horizontal line

		// Label separator = new Label(realParent, SWT.SEPARATOR |
		// SWT.HORIZONTAL);
		// separator.setLayoutData(
		// new GridData(SWT.FILL, SWT.CENTER, false, false, 4, 1));

		// Coordinate labels

		Label labelBlank = new Label(realParent, SWT.NONE);

		Label labelX = new Label(realParent, SWT.NONE);
		labelX.setLayoutData(
				new GridData(SWT.CENTER, SWT.CENTER, false, false, 1, 1));
		labelX.setText("X");

		Label labelY = new Label(realParent, SWT.NONE);
		labelY.setLayoutData(
				new GridData(SWT.CENTER, SWT.CENTER, false, false, 1, 1));
		labelY.setText("Y");

		Label labelZ = new Label(realParent, SWT.NONE);
		labelZ.setLayoutData(
				new GridData(SWT.CENTER, SWT.CENTER, false, false, 1, 1));
		labelZ.setText("Z");

		// Translation
		Label translateLabel = new Label(realParent, SWT.NONE);
		translateLabel.setLayoutData(
				new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1));
		translateLabel.setText("Translate");
		for (int i = 0; i < 3; i++) {
			translateSpinners[i] = new RealSpinner(realParent);
			translateSpinners[i].getControl().setLayoutData(
					new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
			translateSpinners[i].setBounds(-1.0e6, 1.0e6);
		}

		// // Rotation
		// Label rotationLabel = new Label(realParent, SWT.NONE);
		// rotationLabel.setLayoutData(
		// new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1));
		// rotationLabel.setText("Rotation");
		// for (int i = 0; i < 3; i++) {
		// rotationSpinners[i] = new RealSpinner(realParent);
		// rotationSpinners[i].getControl().setLayoutData(
		// new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		// rotationSpinners[i].setBounds(-1.0e6, 1.0e6);
		// }
		//
		// // Scale
		// Label scaleLabel = new Label(realParent, SWT.NONE);
		// scaleLabel.setLayoutData(
		// new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1));
		// scaleLabel.setText("Scale");
		// for (int i = 0; i < 3; i++) {
		// scaleSpinners[i] = new RealSpinner(realParent);
		// scaleSpinners[i].getControl().setLayoutData(
		// new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		// scaleSpinners[i].setBounds(0.0, 1.0e6);
		// scaleSpinners[i].setValue(1.0);
		// }

		// Skew
		// TODO When skew is implemented on the JME3 application, uncomment this
		// Label skewLabel = new Label(realParent, SWT.NONE);
		// skewLabel.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, false,
		// false, 1, 1));
		// skewLabel.setText("Skew");
		//
		// for (int i = 0; i < 3; i++) {
		// skewSpinners[i] = new Spinner(realParent, SWT.BORDER);
		// skewSpinners[i].setLayoutData(new GridData(SWT.FILL, SWT.CENTER,
		// true, false, 1, 1));
		// skewSpinners[i].setValues(0, -999000, 999000, 3, 1000, 10000);
		// skewSpinners[i].setEnabled(false);
		// }

		// Create a label for the opacity combo
		Label opacityLabel = new Label(realParent, SWT.NONE);
		opacityLabel.setLayoutData(
				new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1));
		opacityLabel.setText("Opacity:");

		// Initialize the opacity combo box
		opacityCombo = new Combo(realParent, SWT.READ_ONLY);
		opacityCombo.setLayoutData(
				new GridData(SWT.FILL, SWT.FILL, false, false, 1, 1));
		opacityCombo
				.setItems(new String[] { "Solid", "Wireframe", "Transparent" });

		// Set the initial shape
		createListeners();
		setShape(null);

		// Tell the scrolled composite to watch the real parent composite
		scrolledParent.setContent(realParent);
		// Set the expansion sizes and minimum size of the scrolled composite
		scrolledParent.setExpandHorizontal(true);
		scrolledParent.setExpandVertical(true);
		scrolledParent
				.setMinSize(realParent.computeSize(SWT.DEFAULT, SWT.DEFAULT));
		scrolledParent.setShowFocusedControl(true);

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

			// Set the opacity combo's value based on the shape's transparency
			// and wireframe status
			if (shape.isTransparent()) {
				opacityCombo.select(2);
			} else if (shape.isWireframe()) {
				opacityCombo.select(1);
			} else {
				opacityCombo.select(0);
			}
		}

		// Set the spinner values

		double size = transformation.getSize();
		sizeSpinner.setValue(size);

		double[] scale = transformation.getScale();
		for (int i = 0; i < 3; i++) {
			scaleSpinners[i].setValue(scale[i]);
		}

		double[] rotation = transformation.getRotation();

		for (int i = 0; i < 3; i++) {

			// Convert the rotation value to degrees if needed

			if (degrees) {
				rotationSpinners[i].setValue(Math.toDegrees(rotation[i]));
			} else {
				rotationSpinners[i].setValue(rotation[i]);
			}
		}

		double[] translations = transformation.getTranslation();
		for (int i = 0; i < 3; i++) {
			translateSpinners[i].setValue(translations[i]);
		}

		// Set the enabled state of the spinners, depending on whether the
		// shape parameter is null

		setSpinnersEnabled(shape != null);
		opacityCombo.setEnabled(shape != null);
	}

	/**
	 * 
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
				// Get all the spinner values

				double size = sizeSpinner.getValue();

				double scaleX = scaleSpinners[0].getValue();
				double scaleY = scaleSpinners[1].getValue();
				double scaleZ = scaleSpinners[2].getValue();

				double rotationX = rotationSpinners[0].getValue();
				double rotationY = rotationSpinners[1].getValue();
				double rotationZ = rotationSpinners[2].getValue();

				double translationX = translateSpinners[0].getValue();
				double translationY = translateSpinners[1].getValue();
				double translationZ = translateSpinners[2].getValue();

				// Convert rotation from degrees to radians if needed

				if (degrees) {
					rotationX = Math.toRadians(rotationX);
					rotationY = Math.toRadians(rotationY);
					rotationZ = Math.toRadians(rotationZ);
				}

				// Reset the Transformation to the new parameters

				Transformation transformation = new Transformation();
				transformation.setSize(size);
				transformation.setScale(scaleX, scaleY, scaleZ);
				transformation.setRotation(rotationX, rotationY, rotationZ);
				transformation.setTranslation(translationX, translationY,
						translationZ);

				// Reset the shape's transformation
				if (!currentShape.getTransformation().equals(transformation)) {
					currentShape.setTransformation(transformation);
				}
			}
		};

		// Add the listener to each spinner

		sizeSpinner.listen(listener);

		for (RealSpinner spinner : scaleSpinners) {
			spinner.listen(listener);
		}

		for (RealSpinner spinner : rotationSpinners) {
			spinner.listen(listener);
		}
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
					currentShape.setTransparentMode(false);
					currentShape.setWireframeMode(false);
				}

				// If it is transparent, set it to transparent, leaving its
				// wireframe value alone
				else if ("Transparent".equals(selection)) {
					currentShape.setTransparentMode(true);
				}

				// If it is wireframe, set transparency off but wireframe on
				else {
					currentShape.setTransparentMode(false);
					currentShape.setWireframeMode(true);
				}

			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				// Nothing to do
			}

		});
	}

	@Override
	public void setFocus() {
		// TODO Auto-generated method stub

	}
}