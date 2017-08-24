/*******************************************************************************
 * Copyright (c) 2017 UT-Battelle, LLC.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   Robert Smith
 *   
 *******************************************************************************/
package org.eclipse.eavp.tests.viz.service;

import org.eclipse.eavp.viz.service.IVizCanvas;
import org.eclipse.eavp.viz.service.VizProgressMonitor;
import org.eclipse.eavp.viz.service.drawhandler.AbstractDrawHandler;

/**
 * A basic IDrawHandler implementation for testing purposes
 * 
 * @author Robert Smith
 *
 */
public class FakeDrawHandler extends AbstractDrawHandler {

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.eavp.viz.service.drawhandler.IDrawHandler#draw(org.eclipse.eavp.viz.service.IVizCanvas)
	 */
	@Override
	public void draw(IVizCanvas canvas) {
		//Nothing to do
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.eavp.viz.service.drawhandler.IDrawHandler#save(org.eclipse.eavp.viz.service.VizProgressMonitor, boolean)
	 */
	@Override
	public boolean save(VizProgressMonitor monitor, boolean saveAs) {
		//Nothing to do
		return false;
	}

}
