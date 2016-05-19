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

import org.eclipse.eavp.viz.datastructures.VizObject.IManagedUpdateable;
import org.eclipse.eavp.viz.datastructures.VizObject.IManagedUpdateableListener;

/**
 * The view of an IMesh shown to the user. The view is responsible for creating,
 * managing, and updating the datastructure(s) which display the associated
 * IMesh in the view's rendering engine's native data types.
 * 
 * @author Robert Smith
 */
public interface IView<T>
		extends IManagedUpdateable, IManagedUpdateableListener {

	/**
	 * Creates a representation wrapping an object which represents the part's
	 * model in a native data type for the application associated with this
	 * view.
	 * 
	 */
	Representation<T> getRepresentation();
	
	/**
	 * Refreshes the representation of the model.
	 * 
	 * @param model
	 *            A reference to the view's model
	 */
	void refresh(IMesh model);

	/**
	 * Copy another IView's data into this, making it a copy.
	 * 
	 * @param otherObject The object of which this object will become a copy.
	 */
	void copy(IView<T> otherObject);

}