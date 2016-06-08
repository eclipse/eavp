/*******************************************************************************
 * Copyright (c) 2015 UT-Battelle, LLC.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   Robert Smith
 *******************************************************************************/
package org.eclipse.eavp.viz.service.javafx.mesh.datatypes;

import org.eclipse.eavp.viz.modeling.base.BasicView;
import org.eclipse.eavp.viz.modeling.base.IMesh;
import org.eclipse.eavp.viz.modeling.base.ITransparentView;
import org.eclipse.eavp.viz.modeling.base.IWireframeView;
import org.eclipse.eavp.viz.modeling.base.Representation;

import javafx.scene.Group;

/**
 * A class which provides and manages a simple empty node for a JavaFX part,
 * under which the face's children that have graphical representations (such as
 * edges and vertices), can be grouped.
 * 
 * @author Robert Smith
 *
 */
public class FXFaceView extends BasicView
		implements ITransparentView, IWireframeView {

	/**
	 * The node which will contain the polygon's children.
	 */
	private Group node;

	/**
	 * Whether or not the view is transparent. If true, the view will be
	 * invisible. Otherwise it will be visible.
	 */
	private boolean transparent;

	/**
	 * Whether or not the view is drawn as a wireframe. If true, the view will
	 * be drawn as a wireframe. Otherwise, it will be drawn solid.
	 */
	private boolean wireframe;

	/**
	 * The nullary constructor.
	 */
	public FXFaceView() {
		super();
	}

	/**
	 * The default constructor.
	 * 
	 * @param model
	 */
	public FXFaceView(IMesh model) {
		super();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.eavp.viz.modeling.AbstractView#getRepresentation()
	 */
	@Override
	public Representation<Group> getRepresentation() {
		return new Representation<Group>(node);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#clone()
	 */
	@Override
	public Object clone() {

		// Create a new AbstractView and make it a copy of this
		FXFaceView clone = new FXFaceView();
		clone.copy(this);

		return clone;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.eavp.viz.modeling.base.IWireframeView#isWireframe()
	 */
	@Override
	public boolean isWireframe() {
		return wireframe;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.eavp.viz.modeling.base.IWireframeView#setWireframeMode(
	 * boolean)
	 */
	@Override
	public void setWireframeMode(boolean on) {
		wireframe = on;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.eavp.viz.modeling.base.ITransparentView#isTransparent()
	 */
	@Override
	public boolean isTransparent() {
		return transparent;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.eavp.viz.modeling.base.ITransparentView#setTransparentMode(
	 * boolean)
	 */
	@Override
	public void setTransparentMode(boolean transparent) {
		this.transparent = transparent;
	}
}
