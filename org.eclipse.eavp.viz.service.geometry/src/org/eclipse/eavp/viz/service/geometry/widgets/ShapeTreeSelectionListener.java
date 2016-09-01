/*******************************************************************************
 * Copyright (c) 2012, 2014 UT-Battelle, LLC.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   Initial API and implementation and/or initial documentation - Jay Jay Billings,
 *   Jordan H. Deyton, Dasha Gorin, Alexander J. McCaskey, Taylor Patterson,
 *   Claire Saunders, Matthew Wang, Anna Wojtowicz
 *******************************************************************************/
package org.eclipse.eavp.viz.service.geometry.widgets;

import java.util.ArrayList;

import org.eclipse.eavp.geometry.view.model.IRenderElement;
import org.eclipse.eavp.geometry.view.model.impl.ColorOptionImpl;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.ITreeSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.TreePath;
import org.eclipse.ui.IWorkbenchPage;

/**
 * <p>
 * Handles the selection or deselection of shape items in the shape TreeViewer
 * </p>
 * 
 * @author Jay Jay Billings
 */
public class ShapeTreeSelectionListener implements ISelectionChangedListener {
	/**
	 * <p>
	 * The reference to the current IWorkbenchPage
	 * </p>
	 * 
	 */
	private IWorkbenchPage workbenchPage;

	/**
	 * A list of shapes of the last selection event
	 */
	private ArrayList<IRenderElement> selectedShapes = new ArrayList<IRenderElement>();

	/**
	 * <p>
	 * The constructor for setting the internal reference to the current
	 * IWorkbenchPage
	 * </p>
	 * 
	 * @param workbenchPage
	 *            <p>
	 *            The current IWorkbenchPage
	 *            </p>
	 */
	public ShapeTreeSelectionListener(IWorkbenchPage workbenchPage) {

		this.workbenchPage = workbenchPage;

	}

	/**
	 * <p>
	 * Triggered with the shape tree selection is changed
	 * </p>
	 * <p>
	 * The current implementation updates the referenced shape of the
	 * TransformationView.
	 * </p>
	 * 
	 * @param event
	 *            <p>
	 *            The selection event
	 *            </p>
	 */
	public void selectionChanged(Class event) {
		// TODO Auto-generated method stub

	}

	/**
	 * Changes the state of the TransformationView according to the currently
	 * selected shape
	 * 
	 * @see ISelectionChangedListener#selectionChanged(SelectionChangedEvent
	 *      event)
	 */
	@Override
	public void selectionChanged(SelectionChangedEvent event) {

		ITreeSelection selection = (ITreeSelection) event.getSelection();
		TreePath[] paths = selection.getPaths();

		// Selected shapes are drawn red, so restore the shapes to their natural
		// colors
		for (IRenderElement shape : selectedShapes) {

			// Get the default red value from the shape
			Object red = shape.getProperty("defaultRed");

			// If the shape has default values, set them to the current values
			if (red != null) {
				shape.setProperty(ColorOptionImpl.PROPERTY_NAME_RED,
						(double) red);
				shape.setProperty(ColorOptionImpl.PROPERTY_NAME_GREEN,
						(double) shape.getProperty("defaultGreen"));
				shape.setProperty(ColorOptionImpl.PROPERTY_NAME_BLUE,
						(double) shape.getProperty("defaultBlue"));
			}

			// If the shape lacks defaults, set it to grey
			else {
				shape.setProperty(ColorOptionImpl.PROPERTY_NAME_RED, 127d);
				shape.setProperty(ColorOptionImpl.PROPERTY_NAME_GREEN, 127d);
				shape.setProperty(ColorOptionImpl.PROPERTY_NAME_BLUE, 127d);
			}
		}

		selectedShapes.clear();

		// Set the "selected" value to true for newly selected shapes

		for (TreePath path : paths) {
			Object selectedObject = path.getLastSegment();

			// Only perform the action for selected IShapes
			// (rather than GeometryComponents or null)

			if (selectedObject instanceof IRenderElement) {
				IRenderElement selectedShape = (IRenderElement) selectedObject;

				// Set the shape to red
				selectedShape.setProperty(ColorOptionImpl.PROPERTY_NAME_RED,
						255);
				selectedShape.setProperty(ColorOptionImpl.PROPERTY_NAME_GREEN,
						0);
				selectedShape.setProperty(ColorOptionImpl.PROPERTY_NAME_BLUE,
						0);

				// Add the shape to the list of selected shapes
				selectedShapes.add(selectedShape);
			}
		}

	}
}