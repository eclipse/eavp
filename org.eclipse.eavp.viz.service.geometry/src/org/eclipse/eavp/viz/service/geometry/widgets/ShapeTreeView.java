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

import org.eclipse.eavp.viz.service.IRenderElementHolder;
import org.eclipse.eavp.viz.service.geometry.widgets.ShapeTreeContentProvider.BlankShape;
import org.eclipse.january.geometry.Geometry;
import org.eclipse.january.geometry.INode;
import org.eclipse.january.geometry.Triangle;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.ITreeSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.TreePath;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IActionBars;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.part.ViewPart;

import model.IRenderElement;

/**
 * <p>
 * Eclipse UI view containing a toolbar and TreeViewer to manipulate the
 * structure and elements in a Constructive Solid Geometry (CSG) tree
 * </p>
 * 
 * @author Andrew P. Belt
 */
public class ShapeTreeView extends ViewPart
		implements ISelectionChangedListener {

	/**
	 * <p>
	 * The main TreeViewer occupying the entire space of the view
	 * </p>
	 * 
	 */
	TreeViewer treeViewer;

	/**
	 * Eclipse view ID
	 */
	public static final String ID = "org.eclipse.eavp.viz.service.geometry.widgets.ShapeTreeView";

	/**
	 * A list of shapes of the last selection event
	 */
	private ArrayList<IRenderElement> selectedShapes = new ArrayList<IRenderElement>();

	/**
	 * The holder for the render elements used to model the view's contents in
	 * 3D.
	 */
	private IRenderElementHolder holder;

	// The actions for manipulating shapes
	private DropdownAction addPrimitiveShapes;
	private DropdownAction addComplexShapes;
	private Action duplicateShapes;
	private Action replicateShapes;
	private Action deleteShape;

	/**
	 * <p>
	 * Creates the SWT controls for this ShapeTreeView. It is assumed that
	 * setRenderElementHolder() has already been called so that the content
	 * provider will have access to the render elements.
	 * </p>
	 * 
	 * @param parent
	 *            <p>
	 *            The parent Composite
	 *            </p>
	 */
	@Override
	public void createPartControl(Composite parent) {

		// Create the actions

		createActions();

		// Make a TreeViewer and add a content provider to it

		treeViewer = new TreeViewer(parent);
		
		ShapeTreeContentProvider contentProvider = new ShapeTreeContentProvider(
				holder);
		treeViewer.setContentProvider(contentProvider);

		// Add label provider to TreeViewer

		ShapeTreeLabelProvider labelProvider = new ShapeTreeLabelProvider();
		treeViewer.setLabelProvider(labelProvider);

		// Add selection listener to TreeViewer

		treeViewer.addSelectionChangedListener(this);
	}

	/**
	 * <p>
	 * Creates actions required for manipulating the ShapeTreeView and adds them
	 * to the view's toolbar
	 * </p>
	 * 
	 */
	private void createActions() {

		// Get the toolbar

		IActionBars actionBars = getViewSite().getActionBars();
		IToolBarManager toolbarManager = actionBars.getToolBarManager();

		// Create the add shapes menu managers

		addPrimitiveShapes = new DropdownAction("Add Primitives");
		addComplexShapes = new DropdownAction("Add Complex");

		// Add the PrimitiveShape actions

		Action addSphere = new ActionAddShape(this, "sphere");
		addPrimitiveShapes.addAction(addSphere);

		Action addCube = new ActionAddShape(this, "cube");
		addPrimitiveShapes.addAction(addCube);

		Action addCylinder = new ActionAddShape(this, "cylinder");
		addPrimitiveShapes.addAction(addCylinder);

		Action addTube = new ActionAddShape(this, "tube");
		addPrimitiveShapes.addAction(addTube);

		// Add the ComplexShape actions

		Action addUnion = new ActionAddShape(this, "union");
		addComplexShapes.addAction(addUnion);

		Action addIntersection = new ActionAddShape(this, "intersection");
		addIntersection.setEnabled(false);
		addComplexShapes.addAction(addIntersection);

		Action addComplement = new ActionAddShape(this, "complement");
		addComplement.setEnabled(false);
		addComplexShapes.addAction(addComplement);

		// Add the Duplicate Shapes action

		duplicateShapes = new ActionDuplicateShape(this);

		// Add the Replicate action

		replicateShapes = new ActionReplicateShape(this);

		// Add the DeleteShape action

		deleteShape = new ActionDeleteShape(this);

		// Add the top level menus to the toolbar
		toolbarManager.add(addPrimitiveShapes);
		toolbarManager.add(addComplexShapes);
		toolbarManager.add(duplicateShapes);
		toolbarManager.add(replicateShapes);
		toolbarManager.add(deleteShape);

	}

	/**
	 * Getter method for the holder.
	 * 
	 * @return The holder for the IRenderElements displayed by this view.
	 */
	public IRenderElementHolder getHolder() {
		return holder;
	}

	/**
	 * Set the holder for the render elements used to model the view in 3D.
	 * 
	 * @param holder
	 *            The new render element holder.
	 */
	public void setRenderElementHolder(IRenderElementHolder holder) {
		this.holder = holder;
		((ShapeTreeContentProvider) treeViewer.getContentProvider())
				.setRenderElementHolder(holder);

	}

	/**
	 * 
	 * @param geometry
	 */
	public void setGeometry(Geometry renderElements) {

		// Set the TreeViewer's input
		System.out.println(renderElements);

		for(INode node : renderElements.getNodes()) {
			if (node == null) {
				System.out.println("Has null node!");
				renderElements.getNodes().remove(node);
			} else {
				for (Triangle t : node.getTriangles()) {
					if (t == null) {
						System.out.println("Has null triangle!");
						node.getTriangles().remove(t);
					}
				}
			}
		}
		this.treeViewer.setInput(renderElements);

	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see IWorkbenchPart#setFocus()
	 */
	@Override
	public void setFocus() {
		// TODO Auto-generated method stub

	}

	/**
	 * Updates the disabled state of the action icons and the state of the
	 * TransformationView
	 */
	@Override
	public void selectionChanged(SelectionChangedEvent event) {

		// Get the current selection

		ITreeSelection selection = (ITreeSelection) event.getSelection();
		TreePath[] paths = selection.getPaths();

		// Get the TransformationView

		TransformationView transformationView = (TransformationView) getSite()
				.getPage().findView(TransformationView.ID);

		if (paths.length == 1) {

			// Only one item is selected

			Object selectedObject = paths[0].getLastSegment();

			if (selectedObject instanceof IRenderElement) {
				IRenderElement selectedShape = (IRenderElement) selectedObject;

				// Set the TransformationView's shape

				if (transformationView != null) {
					transformationView.setShape(selectedShape);
				}

				// Enable/disable action buttons

				addPrimitiveShapes.setEnabled(true);
				addComplexShapes.setEnabled(true);
				duplicateShapes.setEnabled(true);
				replicateShapes.setEnabled(true);
				deleteShape.setEnabled(true);
			} else if (selectedObject instanceof BlankShape) {

				// Enable/disable action buttons

				addPrimitiveShapes.setEnabled(true);
				addComplexShapes.setEnabled(true);
				duplicateShapes.setEnabled(false);
				replicateShapes.setEnabled(false);
				deleteShape.setEnabled(false);

				// Set the TransformationView to a blank state

				if (transformationView != null) {
					transformationView.setShape(null);
				}
			}
		} else {

			// Multiple or zero items are selected

			if (transformationView != null) {
				transformationView.setShape(null);
			}
			if (paths.length > 1) {

				// Multiple items are selected.

				// Enable/disable action buttons

				addPrimitiveShapes.setEnabled(false);
				addComplexShapes.setEnabled(false);
				duplicateShapes.setEnabled(true);
				replicateShapes.setEnabled(false);
				deleteShape.setEnabled(true);
			} else {

				// Zero items are selected

				// Enable/disable action buttons

				addPrimitiveShapes.setEnabled(true);
				addComplexShapes.setEnabled(true);
				duplicateShapes.setEnabled(false);
				replicateShapes.setEnabled(false);
				deleteShape.setEnabled(false);
			}
		}

		// Reset the shapes to their default colors to show they are no longer
		// selected

		for (IRenderElement selectedShape : selectedShapes) {

			int red = selectedShape.getProperty("defaultRed") != null
					? (int) selectedShape.getProperty("defaultRed") : 127;
			int green = selectedShape.getProperty("defaultGreen") != null
					? (int) selectedShape.getProperty("defaultGreen") : 127;
			int blue = selectedShape.getProperty("defaultBlue") != null
					? (int) selectedShape.getProperty("defaultBlue") : 127;

			selectedShape.setProperty("red", red);
			selectedShape.setProperty("green", green);
			selectedShape.setProperty("blue", blue);
		}

		// Update the list of last-selected shapes

		selectedShapes.clear();

		for (TreePath path : paths) {
			Object selectedObject = path.getLastSegment();

			// Only include IShapes, not ShapeTreeLabelProvider::BlankShapes

			if (selectedObject instanceof IRenderElement) {

				// Set the shape's color to red to mark it as selected
				IRenderElement selectedShape = (IRenderElement) selectedObject;
				selectedShape.setProperty("red", 255);
				selectedShape.setProperty("green", 0);
				selectedShape.setProperty("blue", 0);
				selectedShapes.add(selectedShape);
			}
		}
	}
}
