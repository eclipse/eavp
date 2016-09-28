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
package org.eclipse.eavp.geometry.view.javafx.display;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.eavp.geometry.view.model.ComboDisplayOptionData;
import org.eclipse.eavp.geometry.view.model.IDisplayOptionData;
import org.eclipse.eavp.geometry.view.model.ModelFactory;
import org.eclipse.eavp.geometry.view.model.RenderObject;
import org.eclipse.eavp.geometry.view.model.impl.OpacityOptionImpl;

import javafx.scene.Group;

/**
 * A DisplayOption that allows the shape to be made transparent.
 * 
 * The decorator will read properties from its source IRenderElement in order to
 * configure itself. The properties used for this decorator are:
 * 
 * "Opacity"- A double that sets how transparent the element is, with 0 being
 * fully transparent and 100 being fully opaque.
 * 
 * @author Robert Smith
 *
 */
public class FXOpacityOption extends OpacityOptionImpl<Group> {

	/**
	 * The default constructor.
	 * 
	 * @param parent
	 *            The parent render object which this option will be applied to.
	 */
	public FXOpacityOption(RenderObject parent) {
		super();

		this.parent = parent;
		parent.registerOption(this);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.eavp.geometry.view.model.impl.DisplayOptionImpl#modify(java.
	 * lang.Object)
	 */
	@Override
	public void modify(Group element) {

		double opacity = (double) parent.getProperty(PROPERTY_NAME_OPACITY);

		// FIXME When JavaFX offers full functionality for opaque shapes, the
		// opacity should simply be passed in to the group rather than snapping
		// it to either fully opaque or fully translucent.
		// Set the group's opacity.
		if (!(opacity < 100)) {
			element.setOpacity(1);
		}

		// Check that the opacity is valid before setting it
		else if (opacity >= 0) {
			element.setOpacity(0);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.eavp.geometry.view.model.impl.DisplayOptionImpl#
	 * getDisplayOptionData()
	 * 
	 * @generated NOT
	 */
	@Override
	public IDisplayOptionData getDisplayOptionData() {
		ComboDisplayOptionData data = ModelFactory.eINSTANCE
				.createComboDisplayOptionData();

		// Set the data's reference to its parent option
		data.setDisplayOption(this);

		// Get the data's map
		Map<String, Map<String, Object>> dataMap = data
				.getTextToPropertyValuesMap();

		// Place an entry associating "Opaque" with opacity 100.
		Map<String, Object> opaqueMap = new HashMap<String, Object>();
		opaqueMap.put(PROPERTY_NAME_OPACITY, 100d);
		dataMap.put("Opaque", opaqueMap);

		// Place an entry associating "Transparent" with opacity 0
		Map<String, Object> transparentMap = new HashMap<String, Object>();
		transparentMap.put(PROPERTY_NAME_OPACITY, 0d);
		dataMap.put("Transparent", transparentMap);

		// The default value is opaque
		dataMap.put("default", opaqueMap);

		return data;
	}
}
