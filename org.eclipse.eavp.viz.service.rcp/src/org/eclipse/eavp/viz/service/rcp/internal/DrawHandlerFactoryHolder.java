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
package org.eclipse.eavp.viz.service.rcp.internal;

import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.Platform;
import org.eclipse.eavp.viz.service.drawhandler.IDrawHandlerFactory;
import org.eclipse.eavp.viz.service.internal.VizServiceFactoryHolder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
	public void setVizServiceFactory(IDrawHandlerFactory input) {
		DrawHandlerFactoryHolder.factory = input;
		Logger staticLogger = LoggerFactory
				.getLogger(VizServiceFactoryHolder.class);

		IConfigurationElement[] elements = Platform.getExtensionRegistry()
				.getConfigurationElementsFor(
						"org.eclipse.eavp.viz.service.IDrawHandlerFactory");
		staticLogger.info("Available configuration elements");
		for (IConfigurationElement element : elements) {
			staticLogger.info(element.getName());
		}

		return;
	}

	/**
	 * Remove the given DrawHandlerFactory if it is held by the
	 * DrawHandlerFactoryHolder.
	 * 
	 * @input A DrawHandlerFactory to remove.
	 */
	public void unsetVizServiceFactory(IDrawHandlerFactory input) {
		if (input == factory) {
			factory = null;
		}
		return;
	}

}
