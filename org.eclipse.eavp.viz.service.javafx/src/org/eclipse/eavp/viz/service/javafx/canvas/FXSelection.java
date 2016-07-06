/*******************************************************************************
 * Copyright (c) 2015-2016 UT-Battelle, LLC.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   Tony McCrary (tmccrary@l33tlabs.com), Robert Smith
 *******************************************************************************/
package org.eclipse.eavp.viz.service.javafx.canvas;

import org.eclipse.jface.viewers.StructuredSelection;

import model.IRenderElement;

/**
 * 
 * @author Tony McCrary (tmccrary@l33tlabs.com), Robert Smith
 *
 */
public class FXSelection extends StructuredSelection {

	/** */
	private final IRenderElement shape;

	/**
	 * 
	 * @param modelShape
	 */
	public FXSelection(IRenderElement modelShape) {
		super(modelShape);

		this.shape = modelShape;
	}

	public IRenderElement getShape() {
		return shape;
	}

}
