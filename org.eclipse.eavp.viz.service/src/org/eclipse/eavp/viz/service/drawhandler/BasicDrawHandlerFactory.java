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

import java.util.HashMap;
import java.util.Map;

import org.eclipse.eavp.viz.service.IVizCanvas;

/**
 * A basic implementation of IDrawHandlerFactory.
 * 
 * @author Robert Smith
 *
 */
public class BasicDrawHandlerFactory implements IDrawHandlerFactory {

	/**
	 * The map from class types of IVizCanvas implementations to the provider for compatible IDrawHandlers. 
	 */
	private Map<Class, IDrawHandlerProvider> classMap;
	
	/**
	 * The nullary constructor.
	 */
	public BasicDrawHandlerFactory() {
		classMap = new HashMap<Class, IDrawHandlerProvider>();
	}
	
	/**
	 * Initializae the service. This function should only be called by the OSGi service manager.
	 */
	public void start() {
		//Nothing to do
	}
	
	/*
	 * (non-Javadoc)
	 * @see org.eclipse.eavp.viz.service.drawhandler.IDrawHandlerFactory#getHandler(org.eclipse.eavp.viz.service.IVizCanvas)
	 */
	@Override
	public IDrawHandler getHandler(IVizCanvas canvas) {
		
		//Get the canvas's class
		Class canvasClass = canvas.getClass();
		
		//If the class is recognized, create the handler for it
		if(classMap.keySet().contains(canvasClass)) {
			return classMap.get(canvasClass).createHandler();
		}
		
		//If the class does not have an IDrawHandlerProvider registered for it, return null
		return null;
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.eavp.viz.service.drawhandler.IDrawHandlerFactory#register(org.eclipse.eavp.viz.service.drawhandler.IDrawHandler)
	 */
	@Override
	public void register(IDrawHandlerProvider provider) {
		classMap.put(provider.getCanvasClass(), provider);
		
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.eavp.viz.service.drawhandler.IDrawHandlerFactory#unregister(org.eclipse.eavp.viz.service.drawhandler.IDrawHandlerProvider)
	 */
	@Override
	public void unregister(IDrawHandlerProvider provider) {
		classMap.remove(provider.getCanvasClass());
		
	}

}
