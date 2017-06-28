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
package org.eclipse.eavp.viz.service.internal;

import org.eclipse.eavp.viz.service.drawhandler.IDrawHandlerFactory;

/**
 * Holder class for a DrawHandlerFactory.
 * 
 * @author Robert Smith
 *
 */

public class DrawHandlerFactoryHolder {
	// The VizServiceFactory held by the VizServiceFactory
	private static IDrawHandlerFactory factory;

	/**
	 * Getter for the held DrawHandlerFactory.
	 * 
	 * @return the held DrawHandlerFactory
	 */
	public static IDrawHandlerFactory getFactory() {
		return factory;
	}

	/**
	 * Setter for the VizServiceFactory.
	 * 
	 * @param input
	 *            the VizServiceFactory to hold
	 */
	public void setDrawHandlerFactory(IDrawHandlerFactory input) {
		DrawHandlerFactoryHolder.factory = input;
		return;
	}

	/**
	 * Remove the given DrawHandlerFactory if it is held by the
	 * DrawHandlerFactoryHolder.
	 * 
	 * @input A DrawHandlerFactory to remove.
	 */
	public void unsetDrawHandlerFactory(IDrawHandlerFactory input) {
		if (input == factory) {
			factory = null;
		}
		return;
	}

}
