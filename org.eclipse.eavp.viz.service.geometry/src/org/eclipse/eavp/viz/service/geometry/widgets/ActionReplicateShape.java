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

import org.eclipse.eavp.geometry.view.model.IRenderElement;
import org.eclipse.eavp.viz.service.IRenderElementHolder;
import org.eclipse.january.geometry.Geometry;
import org.eclipse.january.geometry.GeometryFactory;
import org.eclipse.january.geometry.INode;
import org.eclipse.january.geometry.Union;
import org.eclipse.january.geometry.Vertex;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.ITreeSelection;
import org.eclipse.jface.viewers.TreePath;
import org.eclipse.ui.internal.util.BundleUtility;
import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;

/**
 * <p>
 * Opens a dialog box to replicate the selected shape
 * </p>
 * <p>
 * This action should be enabled only when exactly one shape is selected.
 * </p>
 * 
 * @author Andrew P. Belt
 */
public class ActionReplicateShape extends Action {
	/**
	 * <p>
	 * The current ShapeTreeView
	 * </p>
	 * 
	 */
	private ShapeTreeView view;

	/**
	 * The image descriptor associated with the duplicate action's icon
	 */
	private ImageDescriptor imageDescriptor;

	/**
	 * <p>
	 * Initializes the instance with the current ShapeTreeView
	 * </p>
	 * 
	 * @param view
	 *            <p>
	 *            The current ShapeTreeView
	 *            </p>
	 */
	public ActionReplicateShape(ShapeTreeView view) {

		this.view = view;

		this.setText("Replicate Shape");

		// Load replicate.gif icon from the bundle's icons/ directory

		Bundle bundle = FrameworkUtil.getBundle(getClass());
		URL imagePath = BundleUtility.find(bundle, "icons/replicate.gif");
		imageDescriptor = ImageDescriptor.createFromURL(imagePath);

	}

	@Override
	public ImageDescriptor getImageDescriptor() {
		return imageDescriptor;
	}

	/**
	 * <p>
	 * Opens the replicate dialog box and clones the selected shape to the
	 * properties
	 * </p>
	 * 
	 */
	@Override
	public void run() {

		Geometry geometry = (Geometry) view.treeViewer.getInput();

		// Check that only one shape is selected

		ITreeSelection selection = (ITreeSelection) view.treeViewer
				.getSelection();
		TreePath[] paths = selection.getPaths();

		if (paths.length != 1) {
			return;
		}
		// Get the selected shape from the selection

		Object selectedObject = paths[0].getLastSegment();

		if (!(selectedObject instanceof IRenderElement)) {
			return;
		}
		IRenderElement selectedShape = (IRenderElement) selectedObject;

		// Create a transformation, initialized from the selected shape's
		// transformation
		Vertex selectedCenter = selectedShape.getBase().getCenter();
		double accumulatedX = selectedCenter.getX();
		double accumulatedY = selectedCenter.getY();
		double accumulatedZ = selectedCenter.getZ();

		// Open the dialog

		ReplicateDialog replicateDialog = new ReplicateDialog(
				view.getSite().getShell());

		if (replicateDialog.open() != IDialogConstants.OK_ID) {
			return;
		}
		// Get the dialog parameters

		int quantity = replicateDialog.getQuantity();
		double[] shift = replicateDialog.getShift();

		// Ignore the request if the desired quantity is 1

		if (quantity == 1) {
			return;
		}
		// Get the parent of the shape
		// If the selected shape is a direct child of a GeometryComponent,
		// its parent shape is null.

		INode parentShape = selectedShape.getBase().getParent();

		// Create a new parent union shape

		Union replicateUnion = GeometryFactory.eINSTANCE.createUnion();

		replicateUnion.setName("Replication");
		replicateUnion.setId(selectedShape.getBase().getId());

		for (int i = 1; i <= quantity; i++) {

			// Clone the selected shape

			IRenderElement clonedShape = (IRenderElement) selectedShape.clone();
			clonedShape.getBase().setId(i);

			// Add the translation
			Vertex clonedCenter = clonedShape.getBase().getCenter();
			clonedCenter.setX(accumulatedX);
			clonedCenter.setY(accumulatedY);
			clonedCenter.setZ(accumulatedZ);

			// Add it to the replicated union

			replicateUnion.addNode(clonedShape.getBase());

			// Shift the transform for the next shape
			accumulatedX += shift[0];
			accumulatedY += shift[1];
			accumulatedZ += shift[2];
		}

		// Refresh the TreeViewer

		if (parentShape != null) {

			// The parent is an IShape

			synchronized (geometry) {
				parentShape.addNode(replicateUnion);
				replicateUnion.setParent(parentShape);
			}

			view.treeViewer.refresh(parentShape);
		} else {

			// The parent is the root GeometryComponent

			synchronized (geometry) {
				geometry.addNode(replicateUnion);
			}

			view.treeViewer.refresh();
		}

		// Get the render elements
		IRenderElementHolder holder = view.getHolder();

		IRenderElement unionRender = holder.getRender(replicateUnion);
		unionRender.setProperty("red", -1);
		unionRender.setProperty("green", -1);
		unionRender.setProperty("blue", -1);
		unionRender.setProperty("defaultRed", -1);
		unionRender.setProperty("defaultGreen", -1);
		unionRender.setProperty("defaultBlue", -1);

		for (int i = 0; i < quantity; i++) {

			// A list of all new shapes added by the clone operation
			ArrayList<INode> newShapes = new ArrayList<INode>();

			// Get the original set of renders, in the same order as the
			// clones are in their own list
			ArrayList<IRenderElement> originalRenders = new ArrayList<IRenderElement>();

			for(int j = 0; j < quantity; j++){
				originalRenders.add(selectedShape);
			}
			
			// Add the clone and all of its children
			newShapes.addAll(replicateUnion.getNodes());
			
			for (int j = 0; j < newShapes.size(); j++) {
				newShapes.addAll(newShapes.get(j).getNodes());
				
				if(j >= quantity){
					for(INode newNode : originalRenders.get(j).getBase().getNodes()){
						originalRenders.add(holder.getRender(newNode));
					}
				}
			}



			
			

//			originalRenders.add(selectedShape);
//			for (int j = 0; j < originalRenders.size(); j++) {
//				for (INode node : originalRenders.get(j).getBase().getNodes()) {
//					originalRenders.add(holder.getRender(node));
//				}
//			}

			// Copy the color information from the original renders
			for (int j = 0; j < newShapes.size(); j++) {

				// Get the new and original shapes
				INode node = newShapes.get(j);
				IRenderElement originalElement = originalRenders.get(j);

				// Get the cloned node's render
				IRenderElement element = holder.getRender(node);

				// Get the default values for the colors from the original
				// shape
				int red = (int) originalElement.getProperty("defaultRed");
				int green = (int) originalElement.getProperty("defaultGreen");
				int blue = (int) originalElement.getProperty("defaultBlue");

				// Set the clone's default and current colors
				element.setProperty("red", red);
				element.setProperty("green", green);
				element.setProperty("blue", blue);
				element.setProperty("defaultRed", red);
				element.setProperty("defaultGreen", green);
				element.setProperty("defaultBlue", blue);

				// Copy the material as well
				element.setProperty("material",
						originalElement.getProperty("material"));
			}

		}

		// Remove the selected shape from its original parent

		synchronized (geometry) {
			if (parentShape != null) {
				parentShape.removeNode(selectedShape.getBase());
			} else {
				geometry.removeNode(selectedShape.getBase());
			}
		}

		view.treeViewer.expandToLevel(parentShape, 1);

		view.treeViewer.refresh();

	}
}