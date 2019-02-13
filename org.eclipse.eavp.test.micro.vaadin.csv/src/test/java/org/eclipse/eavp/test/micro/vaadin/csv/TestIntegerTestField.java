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
import org.eclipse.eavp.micro.vaadin.csv.IntegerTextField;
import org.junit.Before;
import org.junit.Test;

import com.vaadin.shared.ui.colorpicker.Color;

/**
 * A class for testing the IntegerTextField
 * 
 * @author Robert Smith
 *
 */
public class TestIntegerTestField {
	
	@Test
	public void testBounds() {
		
		//Create the field with an initial value
		IntegerTextField field = new IntegerTextField(0, 1);
		field.setValue("1");
		
		//Set invalid lower/upper values and check that the change wasn't made
		field.setValue("-2");
		assertTrue("1".equals(field.getValue()));
		assertTrue(1 == field.getNumericalValue());
		field.setValue("2");
		assertTrue("1".equals(field.getValue()));
		assertTrue(1 == field.getNumericalValue());
		
		//Remove the lower bound, change the upper bound, and make sure the bounds are respected
		field.setBounds(null, 1);
		field.setValue("2");
		assertTrue("1".equals(field.getValue()));
		assertTrue(1 == field.getNumericalValue());
		field.setValue("-2");
		assertTrue("-2".equals(field.getValue()));
		assertTrue(-2 == field.getNumericalValue());
		
		//Remove the upper bound and set a lower bound and make sure the bounds are respected
		field.setBounds(-4, null);
		field.setValue("-6");
		assertTrue("-2".equals(field.getValue()));
		assertTrue(-2 == field.getNumericalValue());
		field.setValue("2");
		assertTrue("2".equals(field.getValue()));
		assertTrue(2 == field.getNumericalValue());
	}
	
	/**
	 * Test that the field can be marked optional or not
	 */
	@Test
	public void testOptional() {
		
		//The field for testing
		IntegerTextField field = new IntegerTextField();
		field.setValue("5");
		
		//Set the field to non-optional and try to delete the value
		field.setOptional(false);
		field.setValue("");
		assertTrue("5".equals(field.getValue()));
		assertTrue(5 - field.getNumericalValue() == 0);
		
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
		IntegerTextField field = new IntegerTextField();
		
		//Set a number
		field.setValue("1");
		assertTrue("1".equals(field.getValue()));
		assertTrue(1 - field.getNumericalValue() == 0);
		
		//Set a decimal number
		field.setValue("2.5");
		assertTrue("1".equals(field.getValue()));
		assertTrue(1 == field.getNumericalValue());
		
		//Set a non-number
		field.setValue("bad value");
		assertTrue("1".equals(field.getValue()));
		assertTrue(1 == field.getNumericalValue());
	}
}
