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
package org.eclipse.eavp.viz.modeling.base;

/**
 * An interface for controllers which can display their parts as transparent.
 * 
 * @author Robert Smith
 *
 */
public interface ITransparentController extends IController {

	/**
	 * Method to check whether or not the controller is currently in transparent
	 * mode.
	 * 
	 * @return False if the part is visible. True otherwise.
	 */
	public boolean isTransparent();

	/**
	 * Sets whether or not the part should be rendered transparently.
	 * 
	 * @param transparant
	 *            Whether or not the object will be transparent. If true, the
	 *            object will be rendered fully transparent, making it
	 *            invisible. Otherwise, the object will be rendered visibly.
	 */
	public void setTransparentMode(boolean transparent);
}
