/*******************************************************************************
 * Copyright (c) 2019 UT-Battelle, LLC.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   Robert Smith
 *******************************************************************************/
package org.eclipse.eavp.micro.vaadin.csv;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;

import com.vaadin.data.converter.StringToDoubleConverter;

/**
 * A converter for showing full extent of a decimal number in vaadin controls.
 * 
 * @author Robert Smith
 *
 */
public class FullStringToDoubleConverter extends StringToDoubleConverter{

	/**
	 * The default constructor
	 * 
	 * @param errorMessage The error message to display 
	 */
	public FullStringToDoubleConverter(String errorMessage) {
		super(errorMessage);
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.vaadin.data.converter.AbstractStringToNumberConverter#getFormat(java.util.Locale)
	 */
	@Override
	protected NumberFormat getFormat(Locale locale) {
		return new DecimalFormat("##########################################################.##########################################################");
	}

}
