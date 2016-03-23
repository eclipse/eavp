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
package org.eclipse.eavp.viz.modeling.test.utils;

import org.eclipse.eavp.viz.datastructures.VizObject.IManagedUpdateable;
import org.eclipse.eavp.viz.datastructures.VizObject.SubscriptionType;
import org.eclipse.eavp.viz.modeling.base.BasicController;
import org.eclipse.eavp.viz.modeling.base.BasicMesh;
import org.eclipse.eavp.viz.modeling.base.BasicView;

/**
 * A simple AbstractController implementation that keeps track of whether it has
 * been updated for testing purposes.
 * 
 * @author Robert Smith
 *
 */
public class TestController extends BasicController {

	/**
	 * Whether the controller has received an update since the last time it was
	 * tested.
	 */
	boolean updated = false;

	/**
	 * The default constructor.
	 * 
	 * @param model
	 *            The controller's model
	 * @param view
	 *            The controller's view
	 */
	public TestController(BasicMesh model, BasicView view) {
		super(model, view);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.eavp.viz.modeling.AbstractController#update(org.
	 * eclipse.ice.viz.service.datastructures.VizObject.IManagedVizUpdateable,
	 * org.eclipse.eavp.viz.service.datastructures.VizObject.
	 * UpdateableSubscriptionType[])
	 */
	@Override
	public void update(IManagedUpdateable component, SubscriptionType[] type) {
		super.update(component, type);
		updated = true;
	}

	/**
	 * Checks whether the controller has been updated and returns it to its
	 * default state.
	 * 
	 * @return True if the controller has received an update since the last time
	 *         this function was called. False if it has not.
	 */
	public boolean isUpdated() {
		boolean temp = updated;
		updated = false;
		return temp;
	}

}