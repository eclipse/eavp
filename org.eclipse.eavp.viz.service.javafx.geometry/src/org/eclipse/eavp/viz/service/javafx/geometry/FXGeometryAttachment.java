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
import org.eclipse.eavp.viz.service.javafx.canvas.FXAttachment;

import javafx.scene.Group;
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
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.eavp.viz.service.javafx.canvas.FXAttachment#createElement(
	 * geometry.INode)
	 */
	@Override
	protected IRenderElement<Group> createElement(geometry.INode node) {

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
		wireframeDecorator.setSource(render);

		return wireframeDecorator;
	}

}
