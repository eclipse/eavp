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
package org.eclipse.eavp.viz.service.javafx.canvas.test;

import static org.junit.Assert.assertTrue;

import org.eclipse.eavp.viz.service.javafx.canvas.FXSelection;
import org.junit.Test;

import model.IRenderElement;
import model.ModelFactory;

/**
 * A class to test the functionality of the FXSelection
 * 
 * @author Robert Smith
 *
 */
public class FXSelectionTester {

	/**
	 * Check that the selection holds its input correctly.
	 */
	@Test
	public void checkContents() {

		// Create a part
		IRenderElement part = ModelFactory.eINSTANCE.createRenderObject();

		// Create a selection on the part
		FXSelection selection = new FXSelection(part);

		// Check that the part is in the selection
		assertTrue(part == selection.getShape());
	}
}
