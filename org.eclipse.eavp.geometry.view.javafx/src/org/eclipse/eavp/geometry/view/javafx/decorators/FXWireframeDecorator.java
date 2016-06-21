/*******************************************************************************
 * Copyright (c) 2016 UT-Battelle, LLC.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   Robert Smith
 *******************************************************************************/
package org.eclipse.eavp.geometry.view.javafx.decorators;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.Notifier;

import geometry.GeometryPackage;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.shape.DrawMode;
import javafx.scene.shape.MeshView;
import model.IRenderElement;
import model.impl.WireframeDecoratorImpl;

/**
 * A decorator for FXRenderObjects that will allow them to be drawn in wireframe
 * mode.
 * 
 * @author Robert Smith
 *
 */
public class FXWireframeDecorator extends WireframeDecoratorImpl<Group> {

	/**
	 * Set all the MeshViews in the group to the given draw mode, as well as the
	 * children of any child groups.
	 * 
	 * @param group
	 *            The group whose children will have their draw modes set.
	 * @param mode
	 *            The mode to be set to the group's children.
	 */
	private void setMode(Group group, DrawMode mode) {

		// Handle each of the group's children
		for (Node node : group.getChildren()) {

			// If the node is a mesh view, set its material
			if (node.getClass() == MeshView.class) {
				((MeshView) node).setDrawMode(mode);
			}

			// Otherwise, recursively handle the child group
			else if (node.getClass() == Group.class) {
				setMaterial((Group) node, mode);
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see model.impl.RenderObjectDecoratorImpl#getMesh()
	 */
	@Override
	public Group getMesh() {

		// Get the source's group
		Group group = source.getMesh();

		// Set the group's children to the correct drawing mode.
		if (!wireframe) {
			setMode(group, DrawMode.FILL);
		} else {
			setMode(group, DrawMode.LINE);
		}

		return group;
	}
	
	/*
	 * (non-Javadoc)
	 * @see model.impl.RenderObjectDecoratorImpl#setSource(model.IRenderElement)
	 */
	@Override
	public void setSource(IRenderElement<Group> newSource){
		
		//Register as a listener to it
		newSource.getBase().eAdapters().add(new Adapter(){

			@Override
			public void notifyChanged(Notification notification) {
				
				//If a property was set, check if it was relevant to this decorator
				if(notification.getEventType() == GeometryPackage.SHAPE___SET_PROPERTY__STRING_DOUBLE){
					
					//If the wireframe was changed, update to the new value
					if(notification.getOldStringValue().equals("wireframe")){
						
						//A 1 will signal that the 
						if(notification.getNewDoubleValue() == 1d){
							wireframe = true;
						} else{
							wireframe = false;
						} 
					}
				}
			}

			@Override
			public Notifier getTarget() {
				return null;
			}

			@Override
			public void setTarget(Notifier newTarget) {
			}

			@Override
			public boolean isAdapterForType(Object type) {
				return false;
			}
			
		});
	
		//Set the source
		super.setSource(newSource);
	}
}
