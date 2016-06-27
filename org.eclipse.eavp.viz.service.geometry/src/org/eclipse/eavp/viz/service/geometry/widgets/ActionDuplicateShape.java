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

import java.net.URL;
import java.util.List;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.ITreeSelection;
import org.eclipse.jface.viewers.TreePath;
import org.eclipse.jface.viewers.TreeSelection;
import org.eclipse.ui.internal.util.BundleUtility;
import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;

import geometry.Geometry;
import geometry.INode;
import model.IRenderElement;

/**
 * 
 * @author Andrew P. Belt
 */
public class ActionDuplicateShape extends Action {
	/**
	 * 
	 */
	private ShapeTreeView view;

	/**
	 * The image descriptor associated with the duplicate action's icon
	 */
	private ImageDescriptor imageDescriptor;

	/**
	 * 
	 * @param view
	 */
	public ActionDuplicateShape(ShapeTreeView view) {

		this.view = view;

		this.setText("Duplicate Shape");

		// Load duplicate.gif icon from the bundle's icons/ directory

		Bundle bundle = FrameworkUtil.getBundle(getClass());
		URL imagePath = BundleUtility.find(bundle, "icons/duplicate.gif");
		imageDescriptor = ImageDescriptor.createFromURL(imagePath);

	}

	/**
	 * Returns the image descriptor associated with the duplicate action's icon
	 * 
	 * @see org.eclipse.jface.action.Action#getImageDescriptor()
	 */
	@Override
	public ImageDescriptor getImageDescriptor() {
		return imageDescriptor;
	}

	/**
	 * 
	 */
	@Override
	public void run() {

		Geometry geometry = (Geometry) view.treeViewer.getInput();

		// Get selection

		ITreeSelection selection = (ITreeSelection) view.treeViewer
				.getSelection();
		TreePath[] paths = selection.getPaths();

		// Iterate through the paths

		for (TreePath path : paths) {
			Object selectedObject = path.getLastSegment();

			if (selectedObject instanceof IRenderElement) {
				IRenderElement selectedShape = (IRenderElement) selectedObject;

				// Clone the shape

				IRenderElement clonedShape = (IRenderElement) selectedShape
						.clone();

				// Remove the selection indicating color from the cloned shape
				if (clonedShape.getProperty("defaultRed") != null) {

					// If there was a default color, restore it to the active
					// color
					clonedShape.setProperty("red",
							clonedShape.getProperty("defaultRed"));
					clonedShape.setProperty("green",
							clonedShape.getProperty("defaultGreen"));
					clonedShape.setProperty("blue",
							clonedShape.getProperty("defaultBlue"));
				}

				// If there was no default, set the shape to grey
				else {
					clonedShape.setProperty("red", 127);
					clonedShape.setProperty("green", 127);
					clonedShape.setProperty("blue", 127);
				}

				// Try to get the selected shape's parent shape
				// We can assume that if the parent exists, it is a ComplexShape

				INode parentShape = selectedShape.getBase().getParent();

				if (parentShape != null) {

					// Find the index of the selected shape in the list of its
					// siblings

					List<INode> childShapes = parentShape.getNodes();
					int selectedShapeIndex = childShapes.indexOf(selectedShape);

					if (selectedShapeIndex < 0) {
						continue;
					}
					// Add the cloned shape to the original shape's parent

					synchronized (geometry) {
						parentShape.addNode(clonedShape.getBase());
					}

					view.treeViewer.refresh(parentShape);
				} else {

					// Find the index of the selected shape in the list of its
					// siblings

					List<INode> childShapes = geometry.getNodes();
					int selectedShapeIndex = childShapes.indexOf(selectedShape);

					// Add the cloned shape to the root GeometryComponent

					synchronized (geometry) {
						geometry.addNode(clonedShape.getBase());
					}

					view.treeViewer.refresh();
				}

				// Collapse the cloned item and select it

				view.treeViewer.collapseToLevel(clonedShape, 0);

				// Change the current selection to the new duplicated shape

				TreePath clonedPath = path.getParentPath()
						.createChildPath(clonedShape);
				TreeSelection clonedSelection = new TreeSelection(clonedPath);
				view.treeViewer.setSelection(clonedSelection);
			}
		}

	}
}