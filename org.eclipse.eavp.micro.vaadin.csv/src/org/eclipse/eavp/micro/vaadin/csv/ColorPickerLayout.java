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

import com.vaadin.data.HasValue.ValueChangeEvent;
import com.vaadin.data.HasValue.ValueChangeListener;
import com.vaadin.shared.ui.colorpicker.Color;
import com.vaadin.ui.ColorPicker;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;

/**
 * A ColorPickerLayout is a Vaadin Layout containing a labeled ColorPicker with
 * a preset listener to keep track of the chosen color.
 *
 * @author Robert Smith
 *
 */
public class ColorPickerLayout extends HorizontalLayout {

	/**
	 * The colorpicker widget.
	 */
	private ColorPicker picker;

	/**
	 * The default constructor.
	 *
	 * @param name
	 *            The label that will be displayed by the picker.
	 * @param value
	 *            The html representation of the initial color value, with or
	 *            without the alpha channel specified.
	 */
	public ColorPickerLayout(String name, String value) {
		super();

		// Create the widgets
		picker = new ColorPicker();

		try {

			// If the value is of the format #XXXXXX create the color with red,
			// green, and blue values.
			if (value.length() == 7) {
				int red = Integer.parseInt(value.substring(1, 2));
				int green = Integer.parseInt(value.substring(3, 4));
				int blue = Integer.parseInt(value.substring(5, 6));

				picker.setValue(new Color(red, green, blue));
			} else if (value.length() == 9) {

				// If the value is of the format #XXXXXXXX create the color with
				// alpha, red, green, and blue values.
				int alpha = Integer.parseInt(value.substring(1, 2));
				int red = Integer.parseInt(value.substring(3, 4));
				int green = Integer.parseInt(value.substring(5, 6));
				int blue = Integer.parseInt(value.substring(7, 8));

				picker.setValue(new Color(alpha, red, green, blue));
			}
		} catch (NumberFormatException e) {
			// If the value isn't properly formatted, ignore it and leave the
			// picker with the default value.
		}

		Label label = new Label(name);

		// Get a final self reference
		final ColorPickerLayout self = this;

		picker.addValueChangeListener(new ValueChangeListener<Color>() {

			/*
			 * (non-Javadoc)
			 *
			 * @see
			 * com.vaadin.data.HasValue.ValueChangeListener#valueChange(com.
			 * vaadin.data.HasValue.ValueChangeEvent)
			 */
			@Override
			public void valueChange(ValueChangeEvent<Color> event) {

				// Save the new color value to the layout.
				self.setValue(event.getValue());
			}

		});

		// Add the components to the layout
		addComponent(label);
		addComponent(picker);
	}

	/**
	 * Get the CSS representation of the value.
	 *
	 * @return A string of the format "#XXXXXX" if the alpha channel is 0 or
	 *         "#XXXXXXXX" if it is not.
	 */
	public String getCSS() {
		String css = picker.getValue().getCSS();

		// If the alpha channel isn't 0, add it to the string
		if (picker.getValue().getAlpha() > 0) {
			css.replaceAll("#", "#" + picker.getValue().getAlpha());
		}

		return css;
	}

	/**
	 * Getter method for the value.
	 *
	 * @return The selected Color.
	 */
	public Color getValue() {
		return picker.getValue();
	}

	/**
	 * Setter method for the value.
	 *
	 * @param newValue
	 *            The new color for the ColorPicker.
	 */
	public void setValue(Color newValue) {
		picker.setValue(newValue);
	}
}
