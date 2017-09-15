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
package org.eclipse.eavp.tests.viz.service.rcp.connections.preferences;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.eclipse.eavp.viz.service.rcp.connections.preferences.DynamicComboFieldEditor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.junit.Test;

/**
 * A class to test the functionality of the DynamicComboFieldEditor
 * 
 * @author Robert Smith
 *
 */
public class DynamicComboFieldEditorTester {

	/**
	 * Check that the editor correctly maintains its current and allowed values.
	 */
	@Test
	public void checkValues() {

		// Create a new thread to access UI classes
		new Thread(new Runnable() {
			@Override
			public void run() {

				// Create an editor with the possible values "off"
				// and "on"
				final ArrayList<String> allowedValues = new ArrayList<String>();
				allowedValues.add("off");
				allowedValues.add("on");
				final DynamicComboFieldEditor editor = new DynamicComboFieldEditor(
						"", "", new Composite(new Shell(Display.getDefault()),
								SWT.NONE),
						allowedValues);

				// Check the editor's initial state
				assertEquals(0, editor.getIndex());
				assertEquals("off", editor.getValue());

				// Set the editor to its second value
				editor.setValue(1);
				assertEquals(1, editor.getIndex());
				assertEquals("on", editor.getValue());

				// Try to set the editor to a negative value and
				// check that it was
				// ignored
				editor.setValue(-1);
				assertEquals(1, editor.getIndex());

				// Try to set the editor to an out of bounds value
				// and check that it was
				// ignored
				editor.setValue(2);
				assertEquals(1, editor.getIndex());

				// Try to set the editor to an invalid string
				editor.setValue("up");
				assertEquals(1, editor.getIndex());
				assertEquals("on", editor.getValue());

				// Set the editor by text string and check that the
				// new index is correct
				editor.setValue("off");
				assertEquals(0, editor.getIndex());

				// Set a new list of allowed values
				allowedValues.clear();
				allowedValues.add("down");
				allowedValues.add("up");
				allowedValues.add("left");
				allowedValues.add("right");
				editor.setAllowedValues(allowedValues);

				// Set each of the new values and check that they
				// were set correctly
				editor.setValue(0);
				assertEquals("down", editor.getValue());
				editor.setValue(1);
				assertEquals("up", editor.getValue());
				editor.setValue(2);
				assertEquals("left", editor.getValue());
				editor.setValue(3);
				assertEquals("right", editor.getValue());

				// Try to set one of the old values and check that
				// it was ignored
				editor.setValue("off");
				assertEquals("right", editor.getValue());

			}
		}).start();

	}
}
