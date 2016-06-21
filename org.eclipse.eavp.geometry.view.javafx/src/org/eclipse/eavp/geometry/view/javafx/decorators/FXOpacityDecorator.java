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
import geometry.INode;
import javafx.scene.Group;
import model.IRenderElement;
import model.impl.OpacityDecoratorImpl;

/**
 * A decorator for FXRenderObjects that allows the shape to be made transparent.
 * 
 * @author Robert Smith
 *
 */
public class FXOpacityDecorator extends OpacityDecoratorImpl<Group> {

	/*
	 * (non-Javadoc)
	 * 
	 * @see model.impl.RenderObjectDecoratorImpl#getMesh()
	 */
	@Override
	public Group getMesh() {

		// Get the group from the source
		Group group = source.getMesh();

		// FIXME When JavaFX offers full functionality for opaque shapes, the
		// opacity should simply be passed in to the group rather than snapping
		// it to either fully opaque or fully translucent.
		// Set the group's opacity.
		if (!(opacity < 100)) {
			group.setOpacity(1);
		} else {
			group.setOpacity(0);
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
					
					//If the opacirt was changed, update to the new value
					if(notification.getOldStringValue().equals("opacity")){
						setOpacity(notification.getNewDoubleValue());
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
