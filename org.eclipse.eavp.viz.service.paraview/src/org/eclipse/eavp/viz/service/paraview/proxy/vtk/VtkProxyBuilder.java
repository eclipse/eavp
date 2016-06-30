/*******************************************************************************
 * Copyright (c) 2015 UT-Battelle, LLC.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   Jordan Deyton - Initial API and implementation and/or initial documentation
 *   
 *******************************************************************************/
package org.eclipse.eavp.viz.service.paraview.proxy.vtk;

import java.net.URI;
import java.util.HashSet;
import java.util.Set;

import org.eclipse.eavp.viz.service.paraview.proxy.AbstractParaViewProxyBuilder;
import org.eclipse.eavp.viz.service.paraview.proxy.IParaViewProxy;
import org.eclipse.eavp.viz.service.paraview.proxy.IParaViewProxyBuilder;

/**
 * This class provides an {@link IParaViewProxyBuilder} for the vtk format.
 * As such, it is responsible for creating {@link IParaViewProxy} instances that
 * can support vtk files. Support includes the file extensions:
 * <ul>
 * <li>{@code .vtk}</li>
 * <li>{@code .vts}</li>
 * </ul>
 * 
 * @author Jordan Deyton
 *
 */
public class VtkProxyBuilder extends AbstractParaViewProxyBuilder {

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.eavp.viz.service.paraview.proxy.AbstractParaViewProxyBuilder#createConcreteProxy(java.net.URI)
	 */
	@Override
	protected IParaViewProxy createConcreteProxy(URI uri) {
		return new VtkProxy(uri);
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.eavp.viz.service.paraview.proxy.AbstractParaViewProxyBuilder#findExtensions()
	 */
	@Override
	protected Set<String> findExtensions() {
		Set<String> extensions = new HashSet<String>(1);
		extensions.add("vtk");
		extensions.add("vts");
		return extensions;
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.eavp.viz.service.paraview.proxy.IParaViewProxyBuilder#getName()
	 */
	@Override
	public String getName() {
		return "Default Vtk Proxy Builder";
	}
}
