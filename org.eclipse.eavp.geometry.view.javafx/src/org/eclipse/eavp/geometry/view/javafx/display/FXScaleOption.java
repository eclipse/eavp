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
package org.eclipse.eavp.geometry.view.javafx.display;

import org.eclipse.eavp.geometry.view.model.RenderObject;
import org.eclipse.eavp.geometry.view.model.impl.ScaleOptionImpl;

import javafx.scene.Group;

/**
 * A DisplayOption for an FXRenderObject that sets the object's scale.
 * 
 * The decorator will read properties from its source IRenderElement in order to
 * configure itself. The properties used for this decorator are:
 * 
 * "Scale"- A double defining how large to draw the shape, with 1 representing
 * the element drawn at its normal scale.
 * 
 * @author Robert Smith
 *
 */
public class FXScaleOption extends ScaleOptionImpl<Group> {

	/**
	 * The default constructor.
	 * 
	 * @param parent
	 *            The parent render object which this option will be applied to.
	 */
	public FXScaleOption(RenderObject parent) {
		super();

		this.parent = parent;
		parent.registerOption(this);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.eavp.geometry.view.model.impl.DisplayOptionImpl#modify(java.
	 * lang.Object)
	 */
	@Override
	public void modify(Group element) {

		double scale = (double) parent.getProperty(PROPERTY_NAME_SCALE);

		// Set the scale,
		element.setScaleX(scale);
		element.setScaleY(scale);
		element.setScaleZ(scale);
	}
}
