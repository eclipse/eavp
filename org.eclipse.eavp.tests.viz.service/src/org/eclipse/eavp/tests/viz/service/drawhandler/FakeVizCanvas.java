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

import java.net.URI;
import java.util.Map;

import org.eclipse.eavp.viz.service.IRenderElementHolder;
import org.eclipse.eavp.viz.service.IVizCanvas;
import org.eclipse.eavp.viz.service.drawhandler.IDrawHandler;
import org.eclipse.january.geometry.Geometry;

/**
 * A simple implementation of IVizCanvas for testing purposes.
 * 
 * @author Robert Smith
 *
 */
public class FakeVizCanvas implements IVizCanvas {

	@Override
	public void draw() throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public URI getDataSource() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getNumberOfAxes() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Map<String, String> getProperties() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IRenderElementHolder getRenderElementHolder(Geometry geometry) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IDrawHandler getDrawHandler() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> T getResult(Class<T> resultType) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getSourceHost() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isSourceRemote() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void redraw() {
		// TODO Auto-generated method stub

	}

	@Override
	public void setDrawHandler(IDrawHandler handler) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setProperties(Map<String, String> props) throws Exception {
		// TODO Auto-generated method stub

	}

}
