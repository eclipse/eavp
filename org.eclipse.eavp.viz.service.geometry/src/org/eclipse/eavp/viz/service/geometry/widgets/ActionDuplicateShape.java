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
import java.util.ArrayList;
import java.util.List;

import org.eclipse.eavp.geometry.view.model.IRenderElement;
import org.eclipse.eavp.geometry.view.model.impl.ColorOptionImpl;
import org.eclipse.eavp.viz.service.IRenderElementHolder;
import org.eclipse.january.geometry.Geometry;
import org.eclipse.january.geometry.INode;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.ITreeSelection;
import org.eclipse.jface.viewers.TreePath;
import org.eclipse.jface.viewers.TreeSelection;
import org.eclipse.ui.internal.util.BundleUtility;
import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;

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

				// Try to get the selected shape's parent shape
				// We can assume that if the parent exists, it is a ComplexShape

				INode parentShape = selectedShape.getBase().getParent();

				if (parentShape != null) {

					// Find the index of the selected shape in the list of its
					// siblings

					List<INode> childShapes = parentShape.getNodes();
					int selectedShapeIndex = childShapes
							.indexOf(selectedShape.getBase());

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

				// Get the render elements
				IRenderElementHolder holder = view.getHolder();

				// A list of all new shapes added by the clone operation
				ArrayList<INode> newShapes = new ArrayList<INode>();

				// Add the clone and all of its children
				newShapes.add(clonedShape.getBase());
				for (int i = 0; i < newShapes.size(); i++) {
					newShapes.addAll(newShapes.get(i).getNodes());
				}

				// Get the original set of renders, in the same order as the
				// clones are in their own list
				ArrayList<IRenderElement> originalRenders = new ArrayList<IRenderElement>();
				originalRenders.add(selectedShape);
				for (int i = 0; i < originalRenders.size(); i++) {
					for (INode node : originalRenders.get(i).getBase()
							.getNodes()) {
						originalRenders.add(holder.getRender(node));
					}
				}

				// Copy the color information from the original renders
				for (int i = 0; i < newShapes.size(); i++) {

					// Get the new and original shapes
					INode node = newShapes.get(i);
					IRenderElement originalElement = originalRenders.get(i);

					// Get the cloned node's render
					IRenderElement element = holder.getRender(node);

					// Get the default values for the colors from the original
					// shape
					int red = (int) originalElement.getProperty("defaultRed");
					int green = (int) originalElement
							.getProperty("defaultGreen");
					int blue = (int) originalElement.getProperty("defaultBlue");

					// Set the clone's default and current colors
					element.setProperty(ColorOptionImpl.PROPERTY_NAME_RED, red);
					element.setProperty(ColorOptionImpl.PROPERTY_NAME_GREEN,
							green);
					element.setProperty(ColorOptionImpl.PROPERTY_NAME_BLUE,
							blue);
					element.setProperty("defaultRed", red);
					element.setProperty("defaultGreen", green);
					element.setProperty("defaultBlue", blue);
				}

			}
		}

	}
}