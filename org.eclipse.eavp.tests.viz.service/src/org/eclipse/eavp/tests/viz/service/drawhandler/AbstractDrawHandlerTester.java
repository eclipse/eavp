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
package org.eclipse.eavp.tests.viz.service.drawhandler;

import static org.junit.Assert.assertTrue;

import org.eclipse.eavp.viz.service.IVizCanvas;
import org.eclipse.eavp.viz.service.VizProgressMonitor;
import org.eclipse.eavp.viz.service.drawhandler.AbstractDrawHandler;
import org.junit.Test;

/**
 * A class to test the functionality of the AbstractDrawHandler.
 * 
 * @author Robert Smith
 *
 */
public class AbstractDrawHandlerTester {

	/**
	 * Check that the drawHandler correctly records drawing.
	 */
	@Test
	public void checkDrawinng() {

		// Create a string draw handler that "draws" to the parent by generating
		// the string "drawn to parent"
		AbstractDrawHandler<String> handler = new AbstractDrawHandler<String>() {

			/*
			 * (non-Javadoc)
			 * 
			 * @see
			 * org.eclipse.eavp.viz.service.drawhandler.IDrawHandler#draw(org.
			 * eclipse.eavp.viz.service.IVizCanvas)
			 */
			@Override
			public void draw(IVizCanvas canvas) {
				result = "drawn to " + parent;
				drawn = true;
			}

			/*
			 * (non-Javadoc)
			 * 
			 * @see
			 * org.eclipse.eavp.viz.service.drawhandler.IDrawHandler#save(org.
			 * eclipse.eavp.viz.service.VizProgressMonitor, boolean)
			 */
			@Override
			public boolean save(VizProgressMonitor monitor, boolean saveAs) {
				// TODO Auto-generated method stub
				return false;
			}

		};

		// Set the handler's parent and direct it to draw
		handler.setParent("parent");
		handler.draw(null);

		// The handler's result should be set correctly from draw() and the
		// handler should record being drawn.
		assertTrue("drawn to parent".equals(handler.getResult()));
		assertTrue(handler.isDrawn());
	}
}
