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

import java.util.ArrayList;
import java.util.List;

import org.dussan.vaadin.dcharts.base.elements.XYaxis;
import org.dussan.vaadin.dcharts.data.Ticks;
import org.dussan.vaadin.dcharts.metadata.renderers.AxisRenderers;

import com.vaadin.data.HasValue.ValueChangeEvent;
import com.vaadin.data.HasValue.ValueChangeListener;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
import com.vaadin.ui.Notification;

/**
 * This class is a VAADIN window with controls for editing an axis for a
 * CSVGraph.
 *
 * @author Robert Smith
 * @author Yuya Kawakami
 *
 */
public class CSVAxisWindow extends Window {

	/**
	 * The axis being edited.
	 */
	private XYaxis axis;
	
	/**
	 * String of the axis being edited.
	 */
	
	private String axisName;

	/**
	 * Widget for picking the border color.
	 */
	private ColorPickerLayout borderColorPicker;

	/**
	 * Widget for picking the border width.
	 */
	private DecimalTextField borderWidthText;

	/**
	 * Widget for setting whether to draw major gridlines.
	 */
	private CheckBox drawMajorGridlinesBox;

	/**
	 * Widget for setting whether to draw major tickmarks.
	 */
	private CheckBox drawMajorTickMarksBox;

	/**
	 * Widget for setting whether to draw minor gridlines.
	 */
	private CheckBox drawMinorGridlinesBox;

	/**
	 * Widget for setting whether to draw minor tickmarks.
	 */
	private CheckBox drawMinorTickMarksBox;

	/**
	 * Widget for setting the min value.
	 */
	private DecimalTextField minText;

	/**
	 * Widget for setting the max value.
	 */
	private DecimalTextField maxText;

	/**
	 * Widget for setting the number of tick marks.
	 */
	private IntegerTextField tickNumberText;

	/**
	 * Widget for setting the axis type
	 */
	private ComboBox<AxisRenderers> typeCombo;
	
	/**
	 * Storing the previous AxisRenderder
	 */
	
	private AxisRenderers prevRenderer;

	/**
	 * Widget for setting the precision for the number format.
	 */
	private IntegerTextField decimalPlaceText;

	/**
	 * Widget for setting scientific notation.
	 */
	private CheckBox scientificNotationBox;

	/**
	 * The parent graph that contains the axis and which launched this window.
	 */
	private CSVGraph graph;

	/**
	 * The default constructor.
	 *
	 * @param graph
	 *            The parent graph.
	 * @param axis
	 *            The axis being edited.
	 * @param tickRenderer
	 *            The renderer for the axis ticks.
	 * @param axisName
	 *            The label for the axis.
	 */
	public CSVAxisWindow(CSVGraph graph, XYaxis axis,
			EditableTickRenderer tickRenderer, String axisName) {
		super(axisName + " Axis Editor");

		// Set the class variables
		this.axis = axis;
		this.graph = graph;

		// Get a final self reference
		final CSVAxisWindow self = this;

		// The main layout for all other controls.
		VerticalLayout content = new VerticalLayout();
		this.setContent(content);

		// Create the border width widget
		Label borderWidthLabel = new Label("Border Width");
		borderWidthText = new DecimalTextField(0d, 20d);
		borderWidthText.setPlaceholder(String.valueOf(axis.getBorderWidth()));
		borderWidthText.setValue(String.valueOf(axis.getBorderWidth()));

		// Create the border width widget
		Label minLabel = new Label("Range Min");
		minText = new DecimalTextField();
		minText.setPlaceholder(String.valueOf(axis.getMin()));
		minText.setValue(String.valueOf(axis.getMin()));

		// Create the border width widget
		Label maxLabel = new Label("Range Max");
		maxText = new DecimalTextField();
		maxText.setPlaceholder(String.valueOf(axis.getMax()));
		maxText.setValue(String.valueOf(axis.getMax()));

		// Create the border color widget
		borderColorPicker = new ColorPickerLayout("Border Color",
				axis.getBorderColor());

		// Create the draw major gridlines widget
		drawMajorGridlinesBox = new CheckBox("Draw Major Gridlines");
		drawMajorGridlinesBox.setValue(axis.getDrawMajorGridlines());

		// Create the draw major tickmarks widget
		drawMajorTickMarksBox = new CheckBox("Draw Major Tick Marks");
		drawMajorTickMarksBox.setValue(axis.getDrawMajorTickMarks());

		// Create the draw minor gridlines widget
		drawMinorGridlinesBox = new CheckBox("Draw Minor Gridlines");
		drawMinorGridlinesBox.setValue(axis.getDrawMinorGridlines());

		// Create the draw minor tickmarks widget
		drawMinorTickMarksBox = new CheckBox("Draw Minor Tick Marks");
		drawMinorTickMarksBox.setValue(axis.getDrawMinorTickMarks());

		// Create the border width widget
		Label tickNumberLabel = new Label("# of Tick Marks");
		tickNumberText = new IntegerTextField(0, 9999);
		tickNumberText.setPlaceholder(String.valueOf(axis.getNumberTicks()));
		tickNumberText.setValue(String.valueOf(axis.getNumberTicks()));

		// The list of axis types
		List<AxisRenderers> typeList = new ArrayList<AxisRenderers>();
		typeList.add(AxisRenderers.LINEAR);
		typeList.add(AxisRenderers.LOG);
		typeList.add(AxisRenderers.PYRAMID);
		
		// Set prevRenderer to Linear;
		prevRenderer = AxisRenderers.LINEAR;

		// Create the type widget
		typeCombo = new ComboBox<AxisRenderers>("Axis Type");
		typeCombo.setItemCaptionGenerator(AxisRenderers::name);
		typeCombo.setItems(typeList);
		typeCombo.setEmptySelectionAllowed(false);
		typeCombo.setValue(axis.getRenderer());
		typeCombo.addValueChangeListener(new ValueChangeListener<AxisRenderers>() {

			@Override
			public void valueChange(ValueChangeEvent<AxisRenderers> event) {
				// Checking if 'LOG' can be applied to the Y axis, if not throw an error
				prevRenderer = event.getOldValue();
				if (typeCombo.getValue().equals(AxisRenderers.LOG)) {
					if (axisName.equals("Y") && !graph.data.getCanLogY()) {
						axis.setRenderer(prevRenderer);
						typeCombo.setValue(axis.getRenderer());
						Notification.show("Warning: Axis Type Error",
								"A LOG axis type cannot be selected since values cross 0. Reverting back to original option.",
								Notification.Type.ERROR_MESSAGE);
					} 

					else if (axisName.equals("X") && !graph.data.getCanLogX()) {
						axis.setRenderer(prevRenderer);
						typeCombo.setValue(axis.getRenderer());
						Notification.show("Warning: Axis Type Error",
								"A LOG axis type cannot be selected since values cross 0. Reverting back to original option.",
								Notification.Type.ERROR_MESSAGE);			
					} 
				}		
			}

		});

		// Read the number of decimal places out of the axis format string
		String formatString = axis.getTickOptions().getFormatString();
		String decimalPlaces = formatString.substring(2,
				formatString.length() - 1);

		// Create the decimal number widget
		Label decimalPlacesLabel = new Label("# of Decimal Places in Numbers");
		decimalPlaceText = new IntegerTextField(0, 99);
		decimalPlaceText.setPlaceholder(decimalPlaces);
		decimalPlaceText.setValue(decimalPlaces);

		// Create the scientific notation widget
		scientificNotationBox = new CheckBox("Scientific Notation");
		scientificNotationBox.setValue(formatString.endsWith("e"));

		// Button to accept the changes
		Button ok = new Button("OK");
		ok.addClickListener(new ClickListener() {

			/*
			 * (non-Javadoc)
			 *
			 * @see com.vaadin.ui.Button.ClickListener#buttonClick(com.vaadin
			 * .ui.Button.ClickEvent)
			 */
			@Override
			public void buttonClick(ClickEvent event) {

				// Submit the new values back to the graph then close
				self.submit();
				self.close();
			}

		});

		// The layout containing all the controls in columns
		HorizontalLayout controls = new HorizontalLayout();
		content.addComponent(controls);

		// Add all the widgets to the window
		VerticalLayout column1 = new VerticalLayout();
		column1.addComponent(borderWidthLabel);
		column1.addComponent(borderWidthText);
		column1.addComponent(borderColorPicker);
		column1.addComponent(drawMajorGridlinesBox);
		column1.addComponent(drawMajorTickMarksBox);
		column1.addComponent(drawMinorGridlinesBox);
		column1.addComponent(drawMinorTickMarksBox);
		column1.addComponent(tickNumberLabel);
		column1.addComponent(tickNumberText);
		controls.addComponent(column1);

		VerticalLayout column2 = new VerticalLayout();
		column2.addComponent(minLabel);
		column2.addComponent(minText);
		column2.addComponent(maxLabel);
		column2.addComponent(maxText);
		column2.addComponent(typeCombo);
		column2.addComponent(decimalPlacesLabel);
		column2.addComponent(decimalPlaceText);
		column2.addComponent(scientificNotationBox);
		controls.addComponent(column2);

		// OK button goes below the controls
		content.addComponent(ok);

		// FIXME: Automatically detect data number size and format accordingly
		// TickRenderer xTickRenderer = new TickRenderer(null, true, true,
		// false,
		// 1, true, true, null, "", "%.1e", "", "", "");

		// xAxis.setTickOptions(xTickRenderer);
		// yAxis.setTickOptions(xTickRenderer);
		// yAxis.getTickOptions().setFormatString("%.2f");
		
		//Set axisName
		this.axisName = axisName;

	}

	/**
	 * Apply all changes to the axis and refresh the graph.
	 */
	private void submit() {

		// Set the axis properties
		axis.setBorderWidth(
				(float) borderWidthText.getNumericalValue().doubleValue());
		axis.setBorderColor(borderColorPicker.getCSS());
		axis.setDrawMajorGridlines(drawMajorGridlinesBox.getValue());
		axis.setDrawMajorTickMarks(drawMajorTickMarksBox.getValue());
		axis.setDrawMinorGridlines(drawMinorGridlinesBox.getValue());
		axis.setDrawMinorTickMarks(drawMinorTickMarksBox.getValue());
		axis.setMin(minText.getNumericalValue());
		axis.setMax(maxText.getNumericalValue());
		axis.setNumberTicks(tickNumberText.getNumericalValue());
		axis.setRenderer(typeCombo.getValue());
		axis.getTickOptions()
				.setFormatString("%." + decimalPlaceText.getNumericalValue()
						+ (scientificNotationBox.getValue() ? "e" : "f"));

		// The ticks to be set to the axis
		Ticks ticks = new Ticks();

		// FIXME What does dcharts do if there's only 1 tick? 0 ticks?
		// Create the ticks for a logarithmic scale by creating the 1e^X ticks
		// from min to max.
		if (typeCombo.getValue().equals(AxisRenderers.LOG)) { 

			// The min tick is the smallest power of 10 greater than the minimum
			double min = 1;
			if (minText.getNumericalValue() < 1) {
				while (min > minText.getNumericalValue()) {
					min /= 10;
				}
				// min *= 10;
			} else {
				while (min < minText.getNumericalValue()) {
					min *= 10;
				}
				min /= 10;
			}

			// The max tick is the largest power of 10 less than the maximum
			double max = 1;
			if (maxText.getNumericalValue() < 1) {
				while (max > maxText.getNumericalValue()) {
					max /= 10;
				}
				max *= 10;
			} else {
				while (max < maxText.getNumericalValue()) {
					max *= 10;
				}
				// max /= 10;
			}

			// Increase the last tick's size to ensure that it will cover the
			// range
			for (int i = 2; i < 10; i++) {
				if (max * i > maxText.getNumericalValue()) {
					max = max * i;
					break;
				}
			}

			// Create a list of ticks between the min and max
			double i = min;
			while (i <= max) {
				ticks.add(i);
				i = i * 10;
			}
		}

		else if (typeCombo.getValue().equals(AxisRenderers.LINEAR)) {

			// Get the min and max of the range
			double min = minText.getNumericalValue();
			double max = maxText.getNumericalValue();

			// Step size is the number of ticks divided into the range
			double step = (max - min) / axis.getNumberTicks();

			// Create the ticks, with the given number between the given min and
			// max
			for (double i = 0; i < axis.getNumberTicks(); i++) {
				ticks.add(min + (i * step));
			}

			// Explicitly add the max to the ticks instead of approximating
			ticks.add(max);
		}

		// Set the ticks to the axis
		axis.setTicks(ticks);

		// Refresh the graph.
		graph.pushChanges();
	}

}