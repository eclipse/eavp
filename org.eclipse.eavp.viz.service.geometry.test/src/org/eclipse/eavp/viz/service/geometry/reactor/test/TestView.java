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
package org.eclipse.eavp.viz.service.geometry.reactor.test;

import org.eclipse.eavp.viz.modeling.base.BasicView;
import org.eclipse.eavp.viz.modeling.base.ITransparentView;
import org.eclipse.eavp.viz.modeling.base.IWireframeView;

/**
 * An IView that keeps track of its transparency and wireframe status, for use
 * in testing the ITransparencyControllers and IWireframeControllers.
 * 
 * @author Robert Smith
 *
 */
public class TestView extends BasicView
		implements ITransparentView, IWireframeView {

	/**
	 * Whether the view is currently transparent.
	 */
	boolean transparent = false;

	/**
	 * Whether the view is currently a wireframe
	 */
	boolean wireframe = false;

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
		return false;
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
