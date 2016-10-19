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
import java.util.Iterator;
import java.util.List;

import org.eclipse.eavp.geometry.view.model.IRenderElement;
import org.eclipse.eavp.geometry.view.model.impl.ColorOptionImpl;
import org.eclipse.eavp.viz.service.IRenderElementHolder;
import org.eclipse.eavp.viz.service.geometry.widgets.ShapeTreeContentProvider.BlankShape;
import org.eclipse.january.geometry.Geometry;
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
import org.eclipse.ui.views.properties.IPropertySheetPage;
import org.eclipse.ui.views.properties.tabbed.ITabbedPropertySheetPageContributor;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage;

/**
 * <p>
 * Eclipse UI view containing a toolbar and TreeViewer to manipulate the
 * structure and elements in a Constructive Solid Geometry (CSG) tree
 * </p>
 * 
 * @author Andrew P. Belt, Kasper Gammeltoft, Robert Smith, Jay Jay Billings,
 *         Jordan Deyton
 */
public class ShapeTreeView extends ViewPart implements
		ISelectionChangedListener, ITabbedPropertySheetPageContributor {

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

	private Action importSTL;

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

		// Set the viewer as a selection provider
		getSite().setSelectionProvider(treeViewer);

		// Add selection listener to TreeViewer

		treeViewer.addSelectionChangedListener(this);
	}

	public List<IRenderElement> getSelectedElements() {
		return selectedShapes;
	}

	/**
	 * Sets the selected item in the tree viewer
	 * 
	 * @param selection
	 *            The node to select
	 */
	public void setSelected(IRenderElement selection) {
		ArrayList<IRenderElement> selectionElements = new ArrayList<IRenderElement>();
		selectionElements.add(selection);
		setSelected(selectionElements);
	}

	/**
	 * Sets the selected items in the tree viwer
	 * 
	 * @param selection
	 *            The nodes to select in the viewer
	 */
	public void setSelected(List<IRenderElement> selection) {
		// Create a tree selection for the new selected elements
		ITreeSelection treeSelection = new ITreeSelection() {

			@Override
			public Object getFirstElement() {
				if (selection.isEmpty()) {
					return null;
				} else {
					return selection.get(0);
				}
			}

			@Override
			public Iterator iterator() {
				return selection.iterator();
			}

			@Override
			public int size() {
				return selection.size();
			}

			@Override
			public Object[] toArray() {
				return selection.toArray();
			}

			@Override
			public List toList() {
				return selection;
			}

			@Override
			public boolean isEmpty() {
				return selection.isEmpty();
			}

			@Override
			public TreePath[] getPaths() {
				TreePath[] paths = new TreePath[selection.size()];
				for (int i = 0; i < selection.size(); i++) {
					paths[i] = new TreePath(
							new IRenderElement[] { selection.get(i) });
				}
				return paths;
			}

			@Override
			public TreePath[] getPathsFor(Object element) {
				return new TreePath[] { new TreePath(new IRenderElement[] {
						selection.get(selection.indexOf(element)) }) };
			}

		};
		treeViewer.setSelection(treeSelection);
	}

	/**
	 * Toggles if the given element is selected or not. If the element is
	 * selected, it becomes unselected, and if unselected, becomes selected.
	 * 
	 * @param selected
	 *            The element who's selected state should be toggled.
	 */
	public void toggleSelected(IRenderElement selected) {
		if (selected != null) {
			// Try removing it from selected shapes, it will succeed
			// if the element is selected
			if (!selectedShapes.remove(selected)) {
				// If not in selected shapes, add this element
				selectedShapes.add(selected);
			} else {
				// Unselect the shape
				unselect(selected);
			}
			// Update the selections in the tree viewer
			this.setSelected(selectedShapes);
		}
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

		importSTL = new ActionImportGeometry(this);

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

		toolbarManager.add(importSTL);

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
	 * Helper method used to re-color a shape that was unselected in the editor.
	 * 
	 * @param selectedShape
	 *            The shape that is being unselected.
	 */
	private void unselect(IRenderElement selectedShape) {
		//Nothing to do for current implementations
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

		if (paths.length == 1) {

			// Only one item is selected

			Object selectedObject = paths[0].getLastSegment();

			if (selectedObject instanceof IRenderElement) {
				IRenderElement selectedShape = (IRenderElement) selectedObject;

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

			}
		} else {

			// Multiple or zero items are selected

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

		// Update the list of last-selected shapes

		selectedShapes.clear();

		for (TreePath path : paths) {
			Object selectedObject = path.getLastSegment();

			// Only include IShapes, not ShapeTreeLabelProvider::BlankShapes

			if (selectedObject instanceof IRenderElement) {

				// Set the shape's color to red to mark it as selected
				selectedShapes.add((IRenderElement) selectedObject);
			}
		}
	}

	/**
	 * Converts the given object to an integer
	 * 
	 * @param obj
	 *            The object to get the integer value of
	 * @return Returns an int- giving the best
	 */
	private int getIntValue(Object obj) {
		int val = -1;
		// If it is an int, cast it
		if (obj instanceof Integer) {
			val = (Integer) obj;
			// If it is a double, get the int value
		} else if (obj instanceof Double) {
			val = ((Double) obj).intValue();
		} else {
			// Try to get an int value from it
			try {
				val = Integer.parseInt(obj.toString());
			} catch (NumberFormatException e) {
				// TODO Some warning here in the logger
			}
		}
		return val;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.ui.views.properties.tabbed.
	 * ITabbedPropertySheetPageContributor#getContributorId()
	 */
	@Override
	public String getContributorId() {
		return getSite().getId();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.ui.part.WorkbenchPart#getAdapter(java.lang.Class)
	 */
	@Override
	public Object getAdapter(Class adapter) {
		if (adapter == IPropertySheetPage.class) {
			return new TabbedPropertySheetPage(this);
		}
		return super.getAdapter(adapter);
	}
}