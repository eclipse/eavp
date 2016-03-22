/*******************************************************************************
 * Copyright (c) 2016 UT-Battelle, LLC.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   Robert Smith
 *   
 *******************************************************************************/
package org.eclipse.eavp.viz.service.connections.preferences.test;

import static org.junit.Assert.assertEquals;

import org.eclipse.eavp.viz.service.connections.preferences.CellColumnLabelProvider;
import org.eclipse.eavp.viz.service.connections.preferences.IVizCellContentProvider;
import org.eclipse.swt.graphics.Image;
import org.junit.Test;

/**
 * A class to test the functionality of the CellColumLabelProvider.
 * 
 * @author Robert Smith
 *
 */
public class CellColumnLabelProviderTester {

	/**
	 * Check that the provider correctly delegates to its internal provider.
	 */
	@Test
	public void checkDelegation() {
		CellColumnLabelProvider provider = new CellColumnLabelProvider(
				new TestVizCellContentProvider());

		// Check each of the functions to ensure they are delegated to the
		// TestVizCellContentProvider
		assertEquals("Tool Tip", provider.getToolTipText(null));
		assertEquals("Text", provider.getText(null));
	}

	/**
	 * A simple implementation of IVizCellContentProvider for testing purposes.
	 * 
	 * @author Robert Smith
	 *
	 */
	private class TestVizCellContentProvider
			implements IVizCellContentProvider {

		/*
		 * (non-Javadoc)
		 * 
		 * @see org.eclipse.eavp.viz.service.connections.preferences.
		 * IVizCellContentProvider#isValid(java.lang.Object)
		 */
		@Override
		public boolean isValid(Object element) {
			// TODO Auto-generated method stub
			return false;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see org.eclipse.eavp.viz.service.connections.preferences.
		 * IVizCellContentProvider#isEnabled(java.lang.Object)
		 */
		@Override
		public boolean isEnabled(Object element) {
			// TODO Auto-generated method stub
			return false;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see org.eclipse.eavp.viz.service.connections.preferences.
		 * IVizCellContentProvider#getText(java.lang.Object)
		 */
		@Override
		public String getText(Object element) {
			return "Text";
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see org.eclipse.eavp.viz.service.connections.preferences.
		 * IVizCellContentProvider#getToolTipText(java.lang.Object)
		 */
		@Override
		public String getToolTipText(Object element) {
			return "Tool Tip";
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see org.eclipse.eavp.viz.service.connections.preferences.
		 * IVizCellContentProvider#getValue(java.lang.Object)
		 */
		@Override
		public Object getValue(Object element) {
			return null;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see org.eclipse.eavp.viz.service.connections.preferences.
		 * IVizCellContentProvider#setValue(java.lang.Object, java.lang.Object)
		 */
		@Override
		public boolean setValue(Object element, Object value) {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public Image getImage(Object element) {
			// TODO Auto-generated method stub
			return null;
		}

	}
}
