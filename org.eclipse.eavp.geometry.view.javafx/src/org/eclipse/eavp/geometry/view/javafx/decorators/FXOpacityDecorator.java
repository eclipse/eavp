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

import javafx.scene.Group;
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
}
