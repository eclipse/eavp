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
package org.eclipse.eavp.tests.viz.service.internal;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.eclipse.eavp.viz.service.drawhandler.BasicDrawHandlerFactory;
import org.eclipse.eavp.viz.service.drawhandler.IDrawHandlerFactory;
import org.eclipse.eavp.viz.service.internal.DrawHandlerFactoryHolder;
import org.junit.Test;

/**
 * A class to test the functionality of the DrawHandlerFactoryHolder.
 * 
 * @author Robert Smith
 *
 */
public class DrawHandlerFactoryHolderTester {

	/**
	 * Checks that the factory holder's methods for registering, getting, and
	 * removing a factory are functioning.
	 * 
	 */
	@Test
	public void checkFactorySetting() {

		// The holder for testing
		DrawHandlerFactoryHolder holder = new DrawHandlerFactoryHolder();

		// The holder should start empty
		assertNull(holder.getFactory());

		// Add a factory to the holder and check that it can be retrieved
		IDrawHandlerFactory factory = new BasicDrawHandlerFactory();
		holder.setDrawHandlerFactory(factory);
		assertTrue(factory == holder.getFactory());

		// Remove the factory and check that it is gone
		holder.unsetDrawHandlerFactory(factory);
		assertNull(holder.getFactory());

	}
}
