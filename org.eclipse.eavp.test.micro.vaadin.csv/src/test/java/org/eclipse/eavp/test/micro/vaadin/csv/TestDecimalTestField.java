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
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.eavp.micro.main.java.CSVGrid;
import org.eclipse.eavp.micro.vaadin.csv.ColorPickerLayout;
import org.eclipse.eavp.micro.vaadin.csv.DecimalTextField;
import org.junit.Before;
import org.junit.Test;

import com.vaadin.shared.ui.colorpicker.Color;

/**
 * A class for testing the DecimalTextField
 * 
 * @author Robert Smith
 *
 */
public class TestDecimalTestField {
	
	@Test
	public void testBounds() {
		
		//Create the field with an initial value
		DecimalTextField field = new DecimalTextField(0d, 1d);
		field.setValue("0.5");
		
		//Set invalid lower/upper values and check that the change wasn't made
		field.setValue("-1");
		assertTrue("0.5".equals(field.getValue()));
		assertTrue(0.5 - field.getNumericalValue() == 0);
		field.setValue("2");
		assertTrue("0.5".equals(field.getValue()));
		assertTrue(0.5 - field.getNumericalValue() == 0);
		
		//Remove the lower bound, change the upper bound, and make sure the bounds are respected
		field.setBounds(null, 0.75);
		field.setValue("1");
		assertTrue("0.5".equals(field.getValue()));
		assertTrue(0.5 - field.getNumericalValue() == 0);
		field.setValue("-1");
		assertTrue("-1".equals(field.getValue()));
		assertTrue(-1 - field.getNumericalValue() == 0);
		
		//Remove the upper bound and set a lower bound and make sure the bounds are respected
		field.setBounds(-2d, null);
		field.setValue("-3");
		assertTrue("-1".equals(field.getValue()));
		assertTrue(-1 - field.getNumericalValue() == 0);
		field.setValue("1");
		assertTrue("1".equals(field.getValue()));
		assertTrue(1 - field.getNumericalValue() == 0);
	}
	
	/**
	 * Test that the field can be marked optional or not
	 */
	@Test
	public void testOptional() {
		
		//The field for testing
		DecimalTextField field = new DecimalTextField();
		field.setValue("2.5");
		
		//Set the field to non-optional and try to delete the value
		field.setOptional(false);
		field.setValue("");
		assertTrue("2.5".equals(field.getValue()));
		assertTrue(2.5 - field.getNumericalValue() == 0);
		
		//Set the field to optional and delete the value
		field.setOptional(true);
		field.setValue("");
		assertTrue("".equals(field.getValue()));
		assertNull(field.getNumericalValue());
	}
	
	/**
	 * Test that the field accepts numbers correctly
	 */
	@Test
	public void testValue() {
		
		//The field for testing
		DecimalTextField field = new DecimalTextField();
		
		//Set a number
		field.setValue("1");
		assertTrue("1".equals(field.getValue()));
		assertTrue(1 - field.getNumericalValue() == 0);
		
		//Set a decimal number
		field.setValue("2.5");
		assertTrue("2.5".equals(field.getValue()));
		assertTrue(2.5 - field.getNumericalValue() == 0);
		
		//Set a non-number
		field.setValue("bad value");
		assertTrue("2.5".equals(field.getValue()));
		assertTrue(2.5 - field.getNumericalValue() == 0);
	}
}
