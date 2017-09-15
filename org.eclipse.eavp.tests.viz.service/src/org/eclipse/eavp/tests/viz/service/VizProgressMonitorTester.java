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

import static org.junit.Assert.assertTrue;

import org.eclipse.eavp.viz.service.VizProgressMonitor;
import org.junit.Test;

/**
 * A class to test the functionality of the VizProgressMonitor.
 * 
 * @author Robert Smith
 *
 */
public class VizProgressMonitorTester {

	/**
	 * Test that the monitor is properly constructed.
	 */
	@Test
	public void checkConstruction() {
		String internalMonitor = "implementation";
		VizProgressMonitor<String> monitor = new VizProgressMonitor<String>(
				internalMonitor);
		assertTrue(internalMonitor == monitor.getMonitor());
	}
}
