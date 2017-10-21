/*******************************************************************************
 * Copyright (c) 2015 UT-Battelle, LLC.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   Robert Smith
 *
 *******************************************************************************/
package org.eclipse.eavp.tests.viz.service.connections.preferences;

import org.eclipse.eavp.viz.datastructures.ui.VizEntry;
import org.eclipse.eavp.viz.service.connections.preferences.IVizCellContentProvider;
import org.eclipse.swt.graphics.Image;

/**
 * A simple implementation of IVizCellContentProvider for testing purposes.
 * 
 * @author Robert Smith
 *
 */
class TestVizCellContentProvider implements IVizCellContentProvider {

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.eavp.viz.service.connections.preferences.
	 * IVizCellContentProvider#isValid(java.lang.Object)
	 */
	@Override
	public boolean isValid(Object element) {

		// Return true for all non-null objects
		return element != null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.eavp.viz.service.connections.preferences.
	 * IVizCellContentProvider#isEnabled(java.lang.Object)
	 */
	@Override
	public boolean isEnabled(Object element) {

		// Return true for all non-null objects
		return element != null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.eavp.viz.service.connections.preferences.
	 * IVizCellContentProvider#getText(java.lang.Object)
	 */
	@Override
	public String getText(Object element) {

		// Return the value with "text: " prepended
		return "text: " + (((VizEntry) element).getValue());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.eavp.viz.service.connections.preferences.
	 * IVizCellContentProvider#getToolTipText(java.lang.Object)
	 */
	@Override
	public String getToolTipText(Object element) {

		// Return the entry's description
		return ((VizEntry) element).getDescription();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.eavp.viz.service.connections.preferences.
	 * IVizCellContentProvider#getValue(java.lang.Object)
	 */
	@Override
	public Object getValue(Object element) {
		return ((VizEntry) element).getValue();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.eavp.viz.service.connections.preferences.
	 * IVizCellContentProvider#setValue(java.lang.Object, java.lang.Object)
	 */
	@Override
	public boolean setValue(Object element, Object value) {
		return ((VizEntry) element).setValue((String) value);
	}

	@Override
	public Image getImage(Object element) {
		// TODO Auto-generated method stub
		return null;
	}

}