/*******************************************************************************
 * Copyright (c) 2015 UT-Battelle, LLC.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   Initial API and implementation and/or initial documentation - 
 *   Robert Smith
 *******************************************************************************/
package org.eclipse.eavp.viz.service;

/**
 * IVizActions are a lightweight representation of a capability for a specific
 * IPlot. They provide a platform neutral way for the user to execute a certain
 * single action, such as reseting the camera or opening a new window with
 * additional controls.
 * 
 * @author Robert Smith
 *
 */
public interface IVizAction {

	/**
	 * Perform the action.
	 * 
	 * @param plot
	 *            The plot on which the action will be performed.
	 */
	void execute(IPlot plot);

	/**
	 * Get the image associated with this action, for use in GUI elements such
	 * as buttons that are associated with the action.
	 * 
	 * @return The image for this action, or null if the action has no
	 *         associated image.
	 */
	Object getImage();

	/**
	 * Get the name that describes this action.
	 * 
	 * @return The action's name.
	 */
	String getName();
}
