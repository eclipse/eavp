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

/**
 * An interface for provider classes which instantiate IDrawHandlers. Each IDrawHandlerProvider implementation will always create new instance of exactly one specific implementation of IDrawHandler. 
 * 
 * @author Robert Smith
 *
 */
public interface IDrawHandlerProvider {

	/**
	 * Create a new instance of this class's implementation of IDrawHandler.
	 * 
	 * @return A new IDrawHandler.
	 */
	IDrawHandler createHandler();
	
	/**
	 * Get the concrete class of the IVizCanvas subclass for which this IDrawHandler is able to perform drawing. 
	 * 
	 * @return The subclass of IVizCanvas this IDrawHandler is compatible with.
	 */
	Class getCanvasClass();
}
