/*******************************************************************************
 * Copyright (c) 2017 UT-Battelle, LLC.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   Robert Smith - Initial API and implementation and/or initial documentation
 *   
 *******************************************************************************/
package org.eclipse.eavp.viz.service;

/**
 * A wrapper for the implementation specific progress monitor object used to provide status information for jobs being performed by an IPlot.
 * 
 * @author Robert Smith
 *
 */
public class VizProgressMonitor<T> {

	/**
	 * The real monitor class used by the implementation
	 */
	private T concreteMonitor;
	
	/**
	 * The default constructor.
	 * 
	 * @param monitor The monitor object to be wrapped.
	 */
	public VizProgressMonitor(T monitor) {
		concreteMonitor = monitor;
	}
	
	/**
	 * Getter method for the monitor.
	 * 
	 * @return The real monitor object.
	 */
	public T getMonitor() {
		return concreteMonitor;
	}
	
}
