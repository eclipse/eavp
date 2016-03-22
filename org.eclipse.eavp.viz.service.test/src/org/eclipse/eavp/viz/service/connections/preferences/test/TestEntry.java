/*******************************************************************************
 * Copyright (c) 2015 UT-Battelle, LLC.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   Jordan Deyton - Initial API and implementation and/or initial documentation
 *
 *******************************************************************************/

package org.eclipse.eavp.viz.service.connections.preferences.test;

import org.eclipse.eavp.viz.datastructures.VizAllowedValueType;
import org.eclipse.eavp.viz.datastructures.VizEntry;

/**
 * A test implementation of VizEntry with preset allowed values and convenience
 * methods for setting its attributes.
 * 
 * @author Robert Smith
 *
 */
public class TestEntry extends VizEntry {

	/**
	 * The default constructor.
	 */
	public TestEntry() {
		super();
		allowedValues.add("off");
		allowedValues.add("on");
		iEntryContentProvider.setAllowedValues(allowedValues);
		objectDescription = "tooltip";
		value = "off";
	}

	/**
	 * A constructor which appends a unique name onto each returned string, so
	 * that different test entries can be easilty distinguished.
	 * 
	 * @param name
	 */
	public TestEntry(String name) {
		super();
		allowedValues.add("off" + name);
		allowedValues.add("on" + name);
		iEntryContentProvider.setAllowedValues(allowedValues);
		objectDescription = "tooltip" + name;
		value = "off" + name;
	}

	/**
	 * Set the entry's type to discrete.
	 */
	public void setDiscrete() {
		allowedValueType = VizAllowedValueType.Discrete;
		iEntryContentProvider.setAllowedValueType(VizAllowedValueType.Discrete);
	}

	/**
	 * Set the entry's secret flag.
	 */
	public void setSecret() {
		secretFlag = true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.eavp.viz.datastructures.VizEntry#getValue()
	 */
	@Override
	public String getValue() {
		return value;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.eavp.viz.datastructures.VizEntry#getValueType()
	 */
	@Override
	public VizAllowedValueType getValueType() {
		return allowedValueType;
	}

}