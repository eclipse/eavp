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
package org.eclipse.eavp.tests.viz.service.rcp.styles;

import static org.junit.Assert.assertTrue;

import org.eclipse.eavp.viz.service.rcp.csv.series.XYZPlotStyle;
import org.junit.Test;

/**
 * A class to test the functionality of the XYZPlotStyle.
 * 
 * @author Robert Smith
 *
 */
public class XYZPlotStyleTester {

	/**
	 * Test that the style can be cloned.
	 */
	@Test
	public void checkClone() {

		// Create a style and clone it
		XYZPlotStyle style = new XYZPlotStyle();
		XYZPlotStyle clone = (XYZPlotStyle) style.clone();

		// Check that the clone is equal to the original.
		assertTrue(clone.equals(style));
	}
}
