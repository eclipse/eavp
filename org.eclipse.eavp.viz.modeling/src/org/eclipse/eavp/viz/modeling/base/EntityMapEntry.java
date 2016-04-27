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

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAnyElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.eclipse.eavp.viz.modeling.properties.IMeshCategory;

/**
 * A class which contains an array list of IControllers associated with an
 * IMeshCategory. This class is meant to hold an IMesh's entities map in a
 * piecewise manner, in a class with JAXB support so that the information in the
 * HashMap can be persisted.
 * 
 * @author Robert Smith
 *
 */
@XmlRootElement(name = "EntityMapEntry")
@XmlAccessorType(XmlAccessType.FIELD)
public class EntityMapEntry {

	/**
	 * The map category this entry represents.
	 */
	@XmlAnyElement
	private IMeshCategory category;

	/**
	 * The list of entities within the given category.
	 */
	@XmlAnyElement
	private ArrayList<IController> values;

	/**
	 * The nullary constructor.
	 */
	public EntityMapEntry() {
		category = null;
		values = null;
	}

	/**
	 * The default constructor.
	 * 
	 * @param category
	 *            The category for this entry.
	 * @param values
	 *            The entities under that category in the map.
	 */
	public EntityMapEntry(IMeshCategory category,
			ArrayList<IController> values) {
		this.category = category;
		this.values = values;
	}

	/**
	 * Getter method for the category.
	 * 
	 * @return The entry's category
	 */
	public IMeshCategory getCategory() {
		return category;
	}

	/**
	 * Getter method for the values.
	 * 
	 * @return The entry's values.
	 */
	public ArrayList<IController> getValues() {
		return values;
	}

	/**
	 * Setter method for the category.
	 * 
	 * @param category
	 *            The entry's new category
	 */
	public void setCategory(IMeshCategory category) {
		this.category = category;
	}

	/**
	 * Setter method for the values.
	 * 
	 * @param values
	 *            The entry's new values.
	 */
	public void setValues(ArrayList<IController> values) {
		this.values = values;
	}

}
