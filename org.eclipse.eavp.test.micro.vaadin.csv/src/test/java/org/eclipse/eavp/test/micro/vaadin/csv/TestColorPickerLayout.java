/*******************************************************************************
 * Copyright (c) 2019 UT-Battelle, LLC.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   Initial API and implementation and/or initial documentation - 
 *   Robert Smith
 *******************************************************************************/
package test.java.org.eclipse.eavp.test.micro.vaadin.csv;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.eavp.micro.main.java.CSVGrid;
import org.eclipse.eavp.micro.vaadin.csv.ColorPickerLayout;
import org.junit.Before;
import org.junit.Test;

import com.vaadin.shared.ui.colorpicker.Color;

/**
 * A class for testing the ColorPickerLayout.
 * 
 * @author Robert Smith
 *
 */
public class TestColorPickerLayout {
	
	/**
	 * Test that the color is initialized and set correctly.
	 */
	@Test
	public void testColor() {
		
		//Create the layout with color 0 1 2
		ColorPickerLayout layout = new ColorPickerLayout("Test", "#000102");
		
		//Check that the css string is right
		assertTrue("#000102".equals(layout.getCSS()));
		
		//Check that the color object has the right values
		Color color = layout.getValue();
		assertEquals(0, color.getRed());
		assertEquals(1, color.getGreen());
		assertEquals(2, color.getBlue());
		
		//Set the color to 3 4 5
		color.setRed(3);
		color.setGreen(4);
		color.setBlue(5);
		layout.setValue(color);
		
		//Check that the css string has changed
		assertTrue("#030405".equals(layout.getCSS()));
		
		//Check that the color object has the right values
		color = layout.getValue();
		assertEquals(3, color.getRed());
		assertEquals(4, color.getGreen());
		assertEquals(5, color.getBlue());
	}
}
