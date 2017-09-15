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
package org.eclipse.eavp.tests.viz.service.rcp;

import org.eclipse.eavp.viz.service.IVizCanvas;
import org.eclipse.eavp.viz.service.VizProgressMonitor;
import org.eclipse.eavp.viz.service.drawhandler.AbstractDrawHandler;
import org.eclipse.swt.widgets.Composite;

/**
 * A basic RCP compatible AbstractDrawHandler for testing purposes.
 * 
 * @author Robert Smith
 *
 */
public class FakeRCPDrawHandler extends AbstractDrawHandler<Composite> {

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.eavp.viz.service.drawhandler.IDrawHandler#draw(org.eclipse.
	 * eavp.viz.service.IVizCanvas)
	 */
	@Override
	public void draw(IVizCanvas canvas) {
		result = new Composite(parent, 0);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.eavp.viz.service.drawhandler.IDrawHandler#save(org.eclipse.
	 * eavp.viz.service.VizProgressMonitor, boolean)
	 */
	@Override
	public boolean save(VizProgressMonitor monitor, boolean saveAs) {
		// TODO Auto-generated method stub
		return false;
	}

}
