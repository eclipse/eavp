/*******************************************************************************
 * Copyright (c) 2016 UT-Battelle, LLC.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   Jordan Deyton - Initial API and implementation and/or initial documentation
 *   Robert Smith
 *   
 *******************************************************************************/
package org.eclipse.eavp.viz.service.paraview.proxy.vtk;

import java.net.URI;
import java.util.List;

import org.eclipse.eavp.viz.service.paraview.proxy.AbstractParaViewProxy;
import org.eclipse.eavp.viz.service.paraview.proxy.IParaViewProxy;
import org.eclipse.eavp.viz.service.paraview.proxy.IParaViewProxyFactory;
import org.eclipse.eavp.viz.service.paraview.proxy.ProxyFeature;
import org.eclipse.eavp.viz.service.paraview.proxy.ProxyFeature.ColorByLocation;
import org.eclipse.eavp.viz.service.paraview.proxy.ProxyFeature.ColorByMode;

/**
 * This class provides a concrete {@link IParaViewProxy} that supports loading
 * and rendering vtk files.
 * <p>
 * <b>Note:</b> In practice, instances of this class should not be instantiated.
 * Rather, they should be obtained from the {@link IParaViewProxyFactory}.
 * </p>
 * 
 * @author Jordan Deyton, Robert Smith
 *
 */
public class VtkProxy extends AbstractParaViewProxy {

	/**
	 * The default constructor. The associated Silo file's URI <i>must</i> be
	 * specified.
	 * 
	 * @param uri
	 *            The URI for the Silo file.
	 * @throws NullPointerException
	 *             If the specified URI is null.
	 */
	protected VtkProxy(URI uri) throws NullPointerException {
		super(uri);
		// Nothing to do yet.
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.eavp.viz.service.paraview.proxy.AbstractParaViewProxy#findFeatures()
	 */
	@Override
	protected List<ProxyFeature> findFeatures() {

		// Initialize the list of supported features.
		List<ProxyFeature> features = super.findFeatures();

		features.add(new ProxyFeature("Cell Arrays", 0, ColorByMode.ARRAY,
				ColorByLocation.CELLS) {
			@Override
			protected int getProxyId() {
				return getFileId();
			}
		});
		features.add(new ProxyFeature("Point Arrays", 1, ColorByMode.ARRAY,
				ColorByLocation.POINTS) {
			@Override
			protected int getProxyId() {
				return getFileId();
			}
		});

		return features;
	}

}
