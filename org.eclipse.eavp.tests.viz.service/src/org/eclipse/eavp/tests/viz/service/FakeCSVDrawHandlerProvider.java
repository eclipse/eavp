/*******************************************************************************
 * Copyright (c) 2017 UT-Battelle, LLC.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   Jordan Deyton - Initial API and implementation and/or initial documentation 
 *   
 *******************************************************************************/
package org.eclipse.eavp.tests.viz.service;

import org.eclipse.eavp.viz.service.csv.CSVPlot;
import org.eclipse.eavp.viz.service.drawhandler.IDrawHandler;
import org.eclipse.eavp.viz.service.drawhandler.IDrawHandlerProvider;

/**
 * An IDrawHandlerProvider that gives the FakeDrawHandler.
 * 
 * @author Robert Smith
 *
 */
public class FakeCSVDrawHandlerProvider implements IDrawHandlerProvider {

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.eavp.viz.service.drawhandler.IDrawHandlerProvider#createHandler()
	 */
	@Override
	public IDrawHandler createHandler() {
		return new FakeDrawHandler();
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.eavp.viz.service.drawhandler.IDrawHandlerProvider#getCanvasClass()
	 */
	@Override
	public Class getCanvasClass() {
		return CSVPlot.class;
	}

}
