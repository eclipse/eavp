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
import javax.xml.bind.annotation.adapters.XmlAdapter;

import org.eclipse.eavp.viz.modeling.properties.IMeshCategory;

/**
 * A class to adapt an IMesh's entities map into a format usable by JAXB.
 * 
 * @author Robert Smith
 *
 */
public class EntityMapAdapter extends
		XmlAdapter<EntityMapAdapter.EntityMapAdaption, Map<IMeshCategory, ArrayList<IController>>> {

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * javax.xml.bind.annotation.adapters.XmlAdapter#marshal(java.lang.Object)
	 */
	@Override
	public EntityMapAdaption marshal(
			Map<IMeshCategory, ArrayList<IController>> input) throws Exception {

		// Check that the input exists
		if (null == input) {
			return null;
		}

		// Create an adaption that has each category and its contents as a
		// separate entry in its data
		EntityMapAdaption adaption = new EntityMapAdaption();
		for (Map.Entry<IMeshCategory, ArrayList<IController>> entry : input
				.entrySet()) {
			adaption.entry
					.add(new EntityEntry(entry.getKey(), entry.getValue()));
		}
		return adaption;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * javax.xml.bind.annotation.adapters.XmlAdapter#unmarshal(java.lang.Object)
	 */
	@Override
	public Map<IMeshCategory, ArrayList<IController>> unmarshal(
			EntityMapAdaption input) throws Exception {

		// Check that the input exists
		if (null == input) {
			return null;
		}

		// Create a map of categories to controllers and populate it with the
		// categories from the adaption
		Map<IMeshCategory, ArrayList<IController>> map = new HashMap<IMeshCategory, ArrayList<IController>>();
		for (EntityEntry entry : input.entry) {
			map.put(entry.key, entry.value);
		}
		return map;
	}

	/**
	 * A representation of the entities map as a list of objects containing an
	 * IMeshCategory and the IControllers in that category
	 * 
	 * @author Robert Smith
	 *
	 */
	public static class EntityMapAdaption {

		// Initialize the list
		public List<EntityEntry> entry = new ArrayList<EntityEntry>();
	}

	/**
	 * A single entry in an entities map, consisting of an IMeshCategory and the
	 * IControllers which belong to it.
	 * 
	 * @author Robert Smith
	 *
	 */
	public static class EntityEntry {

		/**
		 * The category this entry represents
		 */
		@XmlAnyElement
		public IMeshCategory key;

		/**
		 * The list of parts belonging to this category.
		 */
		@XmlAnyElement
		public ArrayList<IController> value;

		/**
		 * The nullary constructor.
		 */
		public EntityEntry() {
			// Nothing to do
		}

		/**
		 * The default constructor
		 * 
		 * @param key
		 *            This entry's category
		 * @param value
		 *            The list of parts belonging to this category
		 */
		public EntityEntry(IMeshCategory key, ArrayList<IController> value) {
			this.key = key;
			this.value = value;
		}

	}
}
