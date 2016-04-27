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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.bind.annotation.XmlAnyElement;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.adapters.XmlAdapter;

import org.eclipse.eavp.viz.modeling.properties.IMeshProperty;

/**
 * A class to adapt an IMesh's properties map into a format usable by JAXB.
 * 
 * @author Robert Smith
 *
 */
public class PropertyMapAdapter extends
		XmlAdapter<PropertyMapAdapter.PropertyMapAdaption, Map<IMeshProperty, String>> {

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * javax.xml.bind.annotation.adapters.XmlAdapter#marshal(java.lang.Object)
	 */
	@Override
	public PropertyMapAdaption marshal(Map<IMeshProperty, String> input)
			throws Exception {

		// Check that the input exists
		if (null == input) {
			return null;
		}

		// Create an adaption that has each property and its value as a separate
		// entry in its data
		PropertyMapAdaption adapttion = new PropertyMapAdaption();
		for (Map.Entry<IMeshProperty, String> entry : input.entrySet()) {
			adapttion.entry
					.add(new PropertyEntry(entry.getKey(), entry.getValue()));
		}
		return adapttion;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * javax.xml.bind.annotation.adapters.XmlAdapter#unmarshal(java.lang.Object)
	 */
	@Override
	public Map<IMeshProperty, String> unmarshal(PropertyMapAdaption input)
			throws Exception {

		// Check that the input exists
		if (null == input) {
			return null;
		}

		// Create a map of properties to values and populate it with data from
		// the adaption
		Map<IMeshProperty, String> map = new HashMap<IMeshProperty, String>();
		for (PropertyEntry entry : input.entry) {
			map.put(entry.key, entry.value);
		}
		return map;
	}

	/**
	 * A representation of the properties map as a list of objects containing a
	 * property and its associated value
	 * 
	 * @author Robert Smith
	 *
	 */
	public static class PropertyMapAdaption {

		// Initialize the list
		public List<PropertyEntry> entry = new ArrayList<PropertyEntry>();
	}

	/**
	 * A single entry in a properties map, consisting of a property and its
	 * associated value.
	 * 
	 * @author Robert Smith
	 *
	 */
	public static class PropertyEntry {

		/**
		 * The property this entry represents.
		 */
		@XmlAnyElement
		public IMeshProperty key;

		/**
		 * The property's value.
		 */
		@XmlAttribute
		public String value;

		/**
		 * The nullary constructor
		 */
		public PropertyEntry() {
			// Nothing to do
		}

		/**
		 * The default constructor.
		 * 
		 * @param key
		 *            The property the entry will represent.
		 * @param value
		 *            The property's value.
		 */
		public PropertyEntry(IMeshProperty key, String value) {
			this.key = key;
			this.value = value;
		}
	}
}