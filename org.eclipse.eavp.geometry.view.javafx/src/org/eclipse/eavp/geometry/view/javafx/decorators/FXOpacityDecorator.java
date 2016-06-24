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

import org.eclipse.emf.common.notify.Notification;

import geometry.GeometryPackage;
import javafx.scene.Group;
import model.ModelPackage;
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
	 * 
	 * @see
	 * model.impl.RenderObjectDecoratorImpl#handleUpdate(org.eclipse.emf.common.
	 * notify.Notification)
	 */
	@Override
	protected void handleUpdate(Notification notification) {

		// If a property was set, check if it was relevant to this
		// decorator
		if (notification.getFeatureID(
				GeometryPackage.class) == ModelPackage.RENDER_OBJECT___SET_PROPERTY__STRING_OBJECT) {

			// If the opacity was changed, update to the new value
			if (notification.getOldStringValue().equals("opacity")) {
				setOpacity((double) notification.getNewValue());
			}
		}

		// Pass the update to own listeners
		super.handleUpdate(notification);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see model.impl.OpacityDecoratorImpl#clone()
	 */
	@Override
	public Object clone() {

		// Create a new color decorator
		FXOpacityDecorator clone = new FXOpacityDecorator();

		// Copy this object's data into the clone
		clone.copy(this);
		return clone;
	}
}
