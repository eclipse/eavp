/*******************************************************************************
 * Copyright (c) 2017 UT-Battelle, LLC.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   Robert Smith
 *   
 *******************************************************************************/
package org.eclipse.eavp.tests.viz.service.drawhandler;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.eclipse.eavp.tests.viz.service.FakePlot;
import org.eclipse.eavp.viz.service.IVizCanvas;
import org.eclipse.eavp.viz.service.VizProgressMonitor;
import org.eclipse.eavp.viz.service.drawhandler.AbstractDrawHandler;
import org.eclipse.eavp.viz.service.drawhandler.BasicDrawHandlerFactory;
import org.eclipse.eavp.viz.service.drawhandler.IDrawHandler;
import org.eclipse.eavp.viz.service.drawhandler.IDrawHandlerProvider;
import org.junit.Test;

/**
 * A class to test the functionality of the BasicDrawHandlerFactory.
 * 
 * @author Robert Smith
 *
 */
public class BasicDrawHandlerFactoryTester {

	@Test
	public void checkRegistration() {

		// The factory for testing
		BasicDrawHandlerFactory factory = new BasicDrawHandlerFactory();

		// The canvas for testing
		FakeVizCanvas canvas = new FakeVizCanvas();

		// By default, an unrecognized canvas returns null
		assertNull(factory.getHandler(canvas));

		// The handler the factory will provide
		final IDrawHandler handler = new AbstractDrawHandler<String>() {

			/*
			 * (non-Javadoc)
			 * 
			 * @see
			 * org.eclipse.eavp.viz.service.drawhandler.IDrawHandler#draw(org.
			 * eclipse.eavp.viz.service.IVizCanvas)
			 */
			@Override
			public void draw(IVizCanvas canvas) {
				// Nothing to do
			}

			/*
			 * (non-Javadoc)
			 * 
			 * @see
			 * org.eclipse.eavp.viz.service.drawhandler.IDrawHandler#save(org.
			 * eclipse.eavp.viz.service.VizProgressMonitor, boolean)
			 */
			@Override
			public boolean save(VizProgressMonitor monitor, boolean saveAs) {
				// Nothing to do
				return false;
			}

		};

		// The provider for the FakeVizCanvas
		IDrawHandlerProvider provider = new IDrawHandlerProvider() {

			/*
			 * (non-Javadoc)
			 * 
			 * @see
			 * org.eclipse.eavp.viz.service.drawhandler.IDrawHandlerProvider#
			 * createHandler()
			 */
			@Override
			public IDrawHandler createHandler() {
				return handler;
			}

			/*
			 * (non-Javadoc)
			 * 
			 * @see
			 * org.eclipse.eavp.viz.service.drawhandler.IDrawHandlerProvider#
			 * getCanvasClass()
			 */
			@Override
			public Class getCanvasClass() {
				return FakeVizCanvas.class;
			}

		};

		// Register the provider
		factory.register(provider);

		// Check that the factory returns the correct handler
		assertTrue(factory.getHandler(canvas) == handler);

		// The handler the factory will provide
		final IDrawHandler handler2 = new AbstractDrawHandler<String>() {

			/*
			 * (non-Javadoc)
			 * 
			 * @see
			 * org.eclipse.eavp.viz.service.drawhandler.IDrawHandler#draw(org.
			 * eclipse.eavp.viz.service.IVizCanvas)
			 */
			@Override
			public void draw(IVizCanvas canvas) {
				// Nothing to do
			}

			/*
			 * (non-Javadoc)
			 * 
			 * @see
			 * org.eclipse.eavp.viz.service.drawhandler.IDrawHandler#save(org.
			 * eclipse.eavp.viz.service.VizProgressMonitor, boolean)
			 */
			@Override
			public boolean save(VizProgressMonitor monitor, boolean saveAs) {
				// Nothing to do
				return false;
			}

		};

		// The provider for the FakePlot class
		IDrawHandlerProvider provider2 = new IDrawHandlerProvider() {

			/*
			 * (non-Javadoc)
			 * 
			 * @see
			 * org.eclipse.eavp.viz.service.drawhandler.IDrawHandlerProvider#
			 * createHandler()
			 */
			@Override
			public IDrawHandler createHandler() {
				return handler2;
			}

			/*
			 * (non-Javadoc)
			 * 
			 * @see
			 * org.eclipse.eavp.viz.service.drawhandler.IDrawHandlerProvider#
			 * getCanvasClass()
			 */
			@Override
			public Class getCanvasClass() {
				return FakePlot.class;
			}

		};

		// Register a provider for the FakePlot class
		factory.register(provider2);

		// Check that the factory still returns the correct handler
		assertTrue(factory.getHandler(canvas) == handler);

		// A new provider for the FakeCanvas class
		IDrawHandlerProvider provider3 = new IDrawHandlerProvider() {

			/*
			 * (non-Javadoc)
			 * 
			 * @see
			 * org.eclipse.eavp.viz.service.drawhandler.IDrawHandlerProvider#
			 * createHandler()
			 */
			@Override
			public IDrawHandler createHandler() {
				return handler2;
			}

			/*
			 * (non-Javadoc)
			 * 
			 * @see
			 * org.eclipse.eavp.viz.service.drawhandler.IDrawHandlerProvider#
			 * getCanvasClass()
			 */
			@Override
			public Class getCanvasClass() {
				return FakeVizCanvas.class;
			}

		};

		// Register a new provider for the FakeCanvas class
		factory.register(provider3);

		// The old handler should have been replaced with the new one
		assertTrue(factory.getHandler(canvas) == handler2);

		// Unregistering the provider should return the factory to the default
		// behavior for that class
		factory.unregister(provider3);
		assertNull(factory.getHandler(canvas));
	}
}
