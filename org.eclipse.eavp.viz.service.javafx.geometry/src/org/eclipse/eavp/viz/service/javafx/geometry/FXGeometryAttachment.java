/*******************************************************************************
 * Copyright (c) 2015 UT-Battelle, LLC.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   Tony McCrary (tmccrary@l33tlabs.com), Robert Smith
 *******************************************************************************/
package org.eclipse.eavp.viz.service.javafx.geometry;

import org.eclipse.eavp.geometry.view.javafx.display.FXColorOption;
import org.eclipse.eavp.geometry.view.javafx.display.FXOpacityOption;
import org.eclipse.eavp.geometry.view.javafx.display.FXScaleOption;
import org.eclipse.eavp.geometry.view.javafx.display.FXWireframeOption;
import org.eclipse.eavp.geometry.view.javafx.render.FXRenderObject;
import org.eclipse.eavp.geometry.view.model.IRenderElement;
import org.eclipse.eavp.geometry.view.model.impl.ColorOptionImpl;
import org.eclipse.eavp.viz.service.color.ColorProvider;
import org.eclipse.eavp.viz.service.geometry.widgets.ShapeTreeView;
import org.eclipse.eavp.viz.service.javafx.canvas.FXAttachment;
import org.eclipse.ui.IViewPart;
import org.eclipse.ui.PlatformUI;

import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.input.PickResult;
import javafx.scene.shape.MeshView;

/**
 * <p>
 * Implementation of FXAttachment for the Geometry Editor.
 * </p>
 * 
 * @author Tony McCrary (tmccrary@l33tlabs.com), Robert Smith, Kasper Gammeltoft
 *
 */
public class FXGeometryAttachment extends FXAttachment {

	private boolean selectMultiple;

	/**
	 * The default constructor.
	 * 
	 * @param manager
	 *            The manager which will oversee this attachment
	 */
	public FXGeometryAttachment(FXGeometryAttachmentManager manager) {
		super(manager);
		selectMultiple = false;

		// Add block to run when the mouse is clicked on the geometry
		// canvas. This will allow for selection in the Editor.
		super.fxAttachmentNode.setOnMouseClicked((event) -> {
			selectMultiple = event.isControlDown();

			// Get the pick result, see if we are selecting a node
			PickResult pick = event.getPickResult();

			// Get the node
			Node selected = pick.getIntersectedNode();

			// The render to select
			IRenderElement selectedRender = null;

			// All geometry editor objects in the root are now TriangleMeshViews
			if (selected instanceof MeshView) {
				MeshView view = (MeshView) selected;

				// Go through the rendered nodes and find the node that
				// corresponds
				// to the selected MeshView
				for (IRenderElement element : renderedNodes) {
					Object mesh = element.getMesh();
					// Test each mesh with the selected one
					if (element.getMesh().equals(view)) {
						selectedRender = element;
						// If the mesh is a group node, check each child node
					} else if (mesh instanceof Group) {
						Group group = (Group) mesh;
						for (Node node : group.getChildren()) {
							if (node.equals(view)) {
								// Finally set the render element
								selectedRender = element;
								break;
							}
						}
					}
					// If we found the selected render, break out of the loop
					if (selectedRender != null) {
						break;
					}
				}

			}
			// Set the selection in the tree view, which also sets the
			// transformation view
			IViewPart treeView = PlatformUI.getWorkbench()
					.getActiveWorkbenchWindow().getActivePage()
					.findView(ShapeTreeView.ID);

			if (selectMultiple) {
				((ShapeTreeView) treeView).toggleSelected(selectedRender);
			} else {
				((ShapeTreeView) treeView).setSelected(selectedRender);
			}
		});

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.eavp.viz.service.javafx.canvas.FXAttachment#createElement(
	 * geometry.INode)
	 */
	@Override
	protected IRenderElement<Group> createElement(
			org.eclipse.january.geometry.INode node) {

		// Create the base render object
		FXRenderObject render = new FXRenderObject(node, cache);

		// Add a color option
		FXColorOption colorOption = new FXColorOption(render);

		// Give the render a default color from the provider
		int[] color = ColorProvider.getNextColor();
		render.setProperty("defaultRed", color[0]);
		render.setProperty("defaultGreen", color[1]);
		render.setProperty("defaultBlue", color[2]);

		render.setProperty(ColorOptionImpl.PROPERTY_NAME_RED, color[0]);
		render.setProperty(ColorOptionImpl.PROPERTY_NAME_GREEN, color[1]);
		render.setProperty(ColorOptionImpl.PROPERTY_NAME_BLUE, color[2]);

		// Add the other options
		FXOpacityOption opacityOption = new FXOpacityOption(render);
		FXScaleOption scaleOption = new FXScaleOption(render);
		FXWireframeOption wireframeOption = new FXWireframeOption(render);

		return render;
	}

	@Override
	public IRenderElement getRender(org.eclipse.january.geometry.INode node) {

		IRenderElement returnRender = null;
		// Search the rendered elements to see if the shape is
		// already in the list
		for (IRenderElement<Group> render : renderedNodes) {
			if (render.getBase() == node) {
				returnRender = render;
				break;
			}
		}

		return returnRender;
	}

}
