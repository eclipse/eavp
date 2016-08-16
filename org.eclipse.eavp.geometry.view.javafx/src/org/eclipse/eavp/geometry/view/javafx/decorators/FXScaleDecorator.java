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

import org.eclipse.eavp.geometry.view.model.impl.ScaleDecoratorImpl;
import org.eclipse.emf.common.notify.Notification;

import javafx.scene.Group;

/**
 * A Decorator for an FXRenderObject that sets the object's scale.
 * 
 * @author Robert Smith
 *
 */
public class FXScaleDecorator extends ScaleDecoratorImpl<Group> {

	/*
	 * (non-Javadoc)
	 * 
	 * @see model.impl.RenderObjectDecoratorImpl#getMesh()
	 */
	@Override
	public Group getMesh() {

		// Get the mesh
		Group group = source.getMesh();

		// Set the scale,
		group.setScaleX(scale);
		group.setScaleY(scale);
		group.setScaleZ(scale);

		return group;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * model.impl.RenderObjectDecoratorImpl#handleUpdate(org.eclipse.emf.common.
	 * notify.Notification)
	 */
	@Override
	protected void handleUpdate(Notification notification) {
		// Get the property
		Object property = notification.getOldValue();

		// If "scale" was the updated property, set the scale to the new value
		if ("scale".equals(property)) {
			setScale((double) notification.getNewValue());
		}

		// Pass the update along to own listeners
		super.handleUpdate(notification);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see model.impl.ColorDecoratorImpl#clone()
	 */
	@Override
	public Object clone() {

		// Create a new color decorator
		FXScaleDecorator clone = new FXScaleDecorator();

		// Copy this object's data into the clone
		clone.copy(this);
		return clone;
	}
}
