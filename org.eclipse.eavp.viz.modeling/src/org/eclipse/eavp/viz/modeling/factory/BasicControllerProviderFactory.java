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
package org.eclipse.eavp.viz.modeling.factory;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.eavp.viz.modeling.base.IMesh;

/**
 * A base implementation of IControllerFactory, which structures the factory as
 * a map between the types of input objects and provider classes.
 * 
 * @author Robert Smith
 *
 */
public class BasicControllerProviderFactory implements IControllerProviderFactory {

	/**
	 * A map from class types to IControllerProviders, where each entry maps a
	 * type to the IControllerProvider which handles the creation of controllers
	 * for objects of that type.
	 */
	protected Map<Class, IControllerProvider> typeMap;

	/**
	 * The default constructor.
	 */
	public BasicControllerProviderFactory() {
		typeMap = new HashMap<Class, IControllerProvider>();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.eavp.viz.modeling.IControllerFactory#
	 * createController( org.eclipse.eavp.viz.modeling.AbstractMesh)
	 */
	@Override
	public IControllerProvider createProvider(IMesh model) {

		// Get the provider for the model's type
		IControllerProvider provider = typeMap.get(model.getClass());

		// If a provider was found, create a controller and view for the model
		// and return them
		if (provider != null) {
			return provider;// .createController(model);
		}

		// If none was found, return null.
		else {
			return null;
		}
	}
}
