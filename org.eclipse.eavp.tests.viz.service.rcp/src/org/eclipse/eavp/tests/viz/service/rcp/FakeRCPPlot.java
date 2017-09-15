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
package org.eclipse.eavp.tests.viz.service.rcp;

import org.eclipse.eavp.tests.viz.service.FakePlot;
import org.eclipse.eavp.viz.service.drawhandler.IDrawHandler;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.PlatformUI;

/**
 * An extension of FakePlot which creates a composite as the result.
 * 
 * @author Robert Smith
 *
 */
public class FakeRCPPlot extends FakePlot {

	/**
	 * The handler for the fake composite
	 */
	private FakeRCPDrawHandler handler;

	/**
	 * The default constructor.
	 */
	public FakeRCPPlot() {
		super();
		handler = new FakeRCPDrawHandler();

		PlatformUI.getWorkbench().getDisplay().syncExec(new Runnable() {
			@Override
			public void run() {
				handler.setParent(
						new Composite(new Shell(Display.getDefault()), 0));
			}
		});
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.eavp.tests.viz.service.FakePlot#draw()
	 */
	@Override
	public void draw() throws Exception {
		try {
			super.draw();
			handler.draw(this);
			child = handler.getResult();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.eavp.tests.viz.service.FakePlot#getDrawHandler()
	 */
	@Override
	public IDrawHandler getDrawHandler() {
		return handler;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.eavp.tests.viz.service.FakePlot#getResult(java.lang.Class)
	 */
	@Override
	public <T> T getResult(Class<T> resultType) {
		return (T) child;
	}

}
