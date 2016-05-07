/*******************************************************************************
 * Copyright (c) 2016 UT-Battelle, LLC.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   Robert Smith
 *******************************************************************************/
package org.eclipse.eavp.viz.modeling.base;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAnyElement;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

import org.eclipse.eavp.viz.modeling.properties.IMeshProperty;

/**
 * A class which contains an IMeshProperty and its setting. This class is meant
 * to hold an IMesh's properties map in a piecewise manner in a class with JAXB
 * support so that the information in the HashMap can be persisted.
 * 
 * @author Robert Smith
 *
 */
@XmlRootElement(name = "PropertyMapEntity")
@XmlAccessorType(XmlAccessType.FIELD)
public class PropertyMapEntry {

	/**
	 * The property this entry represents.
	 */
	@XmlAnyElement
	private IMeshProperty property;

	/**
	 * The string setting for the property.
	 */
	@XmlAttribute
	private String value;

	/**
	 * The nullary constructor.
	 */
	public PropertyMapEntry() {
		property = null;
		value = null;
	}

	/**
	 * The default constructor.
	 * 
	 * @param property
	 *            The property this entry represents.
	 * @param value
	 *            The property's value.
	 */
	public PropertyMapEntry(IMeshProperty property, String value) {
		this.property = property;
		this.value = value;
	}

	/**
	 * Getter method for the property.
	 * 
	 * @return The entry's property.
	 */
	public IMeshProperty getProperty() {
		return property;
	}

	/**
	 * Getter method for the value.
	 * 
	 * @return The property's value.
	 */
	public String getValue() {
		return value;
	}

	/**
	 * The setter method for the property.
	 * 
	 * @param property
	 *            The entry's new property.
	 */
	public void setProperty(IMeshProperty property) {
		this.property = property;
	}

	/**
	 * The setter method for the value.
	 * 
	 * @param value
	 *            The entry's new value.
	 */
	public void setValue(String value) {
		this.value = value;
	}

}
