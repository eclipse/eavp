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
package org.eclipse.eavp.viz.service.connections.preferences.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;

import org.eclipse.eavp.viz.datastructures.VizEntry;
import org.eclipse.eavp.viz.datastructures.VizTableComponent;
import org.eclipse.eavp.viz.service.connections.preferences.TableComponentContentProvider;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.junit.Test;

/**
 * A class to test the functionality of the TableComponentContentProvider.
 * 
 * @author Robert Smith
 *
 */
public class TableComponentContentProviderTester {

	/**
	 * Check that the provider can correctly get the component's rows.
	 */
	@Test
	public void checkElements() {

		// Create a new thread to access UI classes
		new Thread(new Runnable() {
			@Override
			public void run() {

				// Create a component and a provider to contain it
				TableComponentContentProvider provider = new TableComponentContentProvider();
				VizTableComponent component = new VizTableComponent();

				// Add a row template and a row
				ArrayList<VizEntry> template = new ArrayList<VizEntry>();
				template.add(new VizEntry());
				component.setRowTemplate(template);
				component.addRow();

				// Set the component
				provider.inputChanged(new TableViewer(new Composite(
						Display.getDefault().getActiveShell(), SWT.NONE)), null,
						component);

				// Getting elements from anything but the component should get
				// an empty
				// array.
				assertEquals(0, provider.getElements(provider).length);

				// Getting the elements from the component should get the rows,
				// of which
				// there are one
				assertEquals(1, provider.getElements(component).length);

			}
		}).start();
	}

	/**
	 * Check that the component throws an exception when given an invalid
	 * component.
	 */
	@Test
	public void checkException() {

		// Create a new thread to access UI classes
		new Thread(new Runnable() {
			@Override
			public void run() {

				// Create a component and a provider to contain it
				TableComponentContentProvider provider = new TableComponentContentProvider();
				VizTableComponent component = new VizTableComponent();

				// Trying to set a component with no row template should throw a
				// null
				// pointer exception.
				NullPointerException exception = null;
				try {
					provider.inputChanged(new TableViewer(new Composite(
							Display.getDefault().getActiveShell(), SWT.NONE)),
							null, component);
				} catch (NullPointerException e) {
					exception = e;
				}
				assertNotNull(exception);
			}

		}).start();
	}
}
