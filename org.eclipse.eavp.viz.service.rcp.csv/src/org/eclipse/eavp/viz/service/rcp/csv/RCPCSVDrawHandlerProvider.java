/*******************************************************************************
 * Copyright (c) 2017 UT-Battelle, LLC.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Robert Smith - initial API and implementation 
 *      and/or initial documentation
 *******************************************************************************/
package org.eclipse.eavp.viz.service.rcp.csv;

import org.eclipse.eavp.viz.service.csv.CSVPlot;
import org.eclipse.eavp.viz.service.drawhandler.IDrawHandler;
import org.eclipse.eavp.viz.service.drawhandler.IDrawHandlerProvider;

/**
 * The provider service for the RCPCSVDrawHandler.
 * 
 * @author Robert Smith
 *
 */
public class RCPCSVDrawHandlerProvider implements IDrawHandlerProvider {

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.eavp.viz.service.drawhandler.IDrawHandlerProvider#
	 * createHandler()
	 */
	@Override
	public IDrawHandler createHandler() {
		return new RCPCSVDrawHandler();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.eavp.viz.service.drawhandler.IDrawHandlerProvider#
	 * getCanvasClass()
	 */
	@Override
	public Class getCanvasClass() {
		return CSVPlot.class;
	}

}
