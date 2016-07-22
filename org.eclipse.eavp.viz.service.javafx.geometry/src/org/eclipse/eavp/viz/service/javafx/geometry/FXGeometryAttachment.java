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


import org.eclipse.eavp.geometry.view.javafx.decorators.FXColorDecorator;
import org.eclipse.eavp.geometry.view.javafx.decorators.FXOpacityDecorator;
import org.eclipse.eavp.geometry.view.javafx.decorators.FXWireframeDecorator;
import org.eclipse.eavp.geometry.view.javafx.render.FXRenderObject;
import org.eclipse.eavp.viz.service.geometry.widgets.ShapeTreeView;
import org.eclipse.eavp.viz.service.geometry.widgets.TransformationView;
import org.eclipse.eavp.viz.service.javafx.canvas.FXAttachment;
import org.eclipse.ui.IViewPart;
import org.eclipse.ui.PlatformUI;

import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.input.PickResult;
import javafx.scene.shape.MeshView;
import model.IRenderElement;

/**
 * <p>
 * Implementation of FXAttachment for the Geometry Editor.
 * </p>
 * 
 * @author Tony McCrary (tmccrary@l33tlabs.com), Robert Smith
 *
 */
public class FXGeometryAttachment extends FXAttachment {
	

	/**
	 * The default constructor.
	 * 
	 * @param manager
	 *            The manager which will oversee this attachment
	 */
	public FXGeometryAttachment(FXGeometryAttachmentManager manager) {
		super(manager);
		
		// Add block to run when the mouse is clicked on the geometry
		// canvas. This will allow for selection in the Editor.
		super.fxAttachmentNode.setOnMouseClicked((event)->{
			
			// Get the pick result, see if we are selecting a node
			PickResult pick = event.getPickResult();
			
			// Get the node
			Node selected = pick.getIntersectedNode();
			
			// The render to select
			IRenderElement selectedRender = null;
			
			// All geometry editor objects in the root are now TriangleMeshViews
			if (selected instanceof MeshView) {
				MeshView view = (MeshView) selected;
				
				// Go through the rendered nodes and find the node that corresponds
				// to the selected MeshView
				for(IRenderElement element : renderedNodes) {
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
					if (selectedRender != null) {
						break;
					}
				}
				
			}
			IViewPart treeView = PlatformUI.getWorkbench().getActiveWorkbenchWindow()
					.getActivePage().findView(ShapeTreeView.ID);
			
			((ShapeTreeView) treeView).setSelected(selectedRender);
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
	protected IRenderElement<Group> createElement(org.eclipse.january.geometry.INode node) {

		// Create the base render object
		FXRenderObject render = new FXRenderObject(node, cache);

		// Add a color decorator
		FXColorDecorator colorDecorator = new FXColorDecorator();
		colorDecorator.setSource(render);

		// Add an opacity decorator
		FXOpacityDecorator opacityDecorator = new FXOpacityDecorator();
		opacityDecorator.setSource(colorDecorator);

		// Add a wireframe decorator
		FXWireframeDecorator wireframeDecorator = new FXWireframeDecorator();
		wireframeDecorator.setSource(opacityDecorator);

		return wireframeDecorator;
	}
	
	@Override
	public IRenderElement getRender(org.eclipse.january.geometry.INode node) {
		
		IRenderElement returnRender = null;
		// Search the rendered elements to see if the shape is
		// already in the list
		for (IRenderElement<Group> render : renderedNodes) {
			if (render.getBase().equals(node)) {
				returnRender = render;
				break;
			}
		}
		
		return returnRender;
	}

}
