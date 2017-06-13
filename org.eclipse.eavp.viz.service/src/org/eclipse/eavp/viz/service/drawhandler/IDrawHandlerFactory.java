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
package org.eclipse.eavp.viz.service.drawhandler;

import org.eclipse.eavp.viz.service.IVizCanvas;

/**
 * An interface for classes which produce IDrawHandlers for IVizCanvases, based on the concrete type of the input IVizCanvas and the available IDrawHandler services.
 * 
 * @author Robert Smith
 *
 */
public interface IDrawHandlerFactory {

	/**
	 * Get a new IDrawHandler for the input canvas.
	 * 
	 * @param canvas The canvas for which an IDrawHandler will be created.
	 * @return An IDrawHandler compatible with the current windowing system and the canvas or null if now IDrawHandlerProvider has been registered for the canvas's class.
	 */
	IDrawHandler getHandler(IVizCanvas canvas);
	
	/**
	 * Register a new provider with the factory.
	 * 
	 * Behavior is undefined in the event that two different IDrawHandlers are registered for the same class. Since IDrawHandlers are specific to a single windowing system each, multiple IDrawHandler services for a single IVizCanvas type (such as RCPCSVDrawHandler and VaadinDrawHandler) should never be started.
	 * 
	 * It is intended that this function only be invoked by the OSGi service manager. 
	 * 
	 * @param provider The new handler to register.
	 */
	void register(IDrawHandlerProvider provider);
	
	/**
	 * Unregister a provider from the factory.
	 * 
	 * It is intended that this function only be invoked by the OSGi service manager.
	 * 
	 * @param provider The handler to unregister.
	 */
	void unregister(IDrawHandlerProvider provider);
}
