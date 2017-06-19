/*******************************************************************************
 * Copyright (c) 2017 UT-Battelle, LLC.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   Initial API and implementation and/or initial documentation -
 *   Robert Smith
 *******************************************************************************/
package org.eclipse.eavp.viz.service.drawhandler;

import org.eclipse.eavp.viz.service.VizProgressMonitor;

/**
 * An abstract implementation of IDrawHandler with basic functionality.
 * 
 * @author Robert Smith
 *
 */
abstract public class AbstractDrawHandler<T> implements IDrawHandler<T> {

	/**
	 * Whether or not this draw handler has succesfully drawn a canvas.
	 */
	protected boolean drawn;

	/**
	 * The parent UI element into which the resulting drawing will be placed as
	 * a child.
	 */
	protected T parent;

	/**
	 * The resulting UI element into which the IVizCanvas was drawn.
	 */
	protected T result;

	public AbstractDrawHandler() {
		drawn = false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.eavp.viz.service.drawhandler.IDrawHandler#getResult()
	 */
	@Override
	public T getResult() {
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.eavp.viz.service.drawhandler.IDrawHandler#isDrawn()
	 */
	@Override
	public boolean isDrawn() {
		return drawn;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.eavp.viz.service.drawhandler.IDrawHandler#save(org.eclipse.
	 * eavp.viz.service.VizProgressMonitor)
	 */
	@Override
	public boolean save(VizProgressMonitor monitor) {
		return save(monitor, false);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.eavp.viz.service.drawhandler.IDrawHandler#setParent(java.lang
	 * .Object)
	 */
	@Override
	public void setParent(T parent) {
		this.parent = parent;
	}
}
