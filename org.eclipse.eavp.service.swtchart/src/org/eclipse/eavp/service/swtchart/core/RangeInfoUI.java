/*******************************************************************************
 * Copyright (c) 2017 Lablicate GmbH.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 * Dr. Philip Wenig - initial API and implementation
 *******************************************************************************/
package org.eclipse.eavp.service.swtchart.core;

import java.text.DecimalFormat;
import java.text.ParseException;

import org.eclipse.eavp.service.swtchart.Activator;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;
import org.swtchart.IAxis;
import org.swtchart.IAxisSet;
import org.swtchart.Range;

public class RangeInfoUI extends Composite {

	private ScrollableChart scrollableChart;
	//
	private Text textStartX;
	private Text textStopX;
	private Combo comboScaleX;
	private Text textStartY;
	private Text textStopY;
	private Combo comboScaleY;

	public RangeInfoUI(Composite parent, int style, ScrollableChart scrollableChart) {
		super(parent, style);
		this.scrollableChart = scrollableChart;
		createControl();
	}

	public void resetRanges() {

		/*
		 * X Axes
		 */
		String[] itemsX = getItems(IExtendedChart.X_AXIS);
		comboScaleX.setItems(itemsX);
		if(itemsX.length > 0) {
			comboScaleX.select(0);
		}
		/*
		 * Y Axes
		 */
		String[] itemsY = getItems(IExtendedChart.Y_AXIS);
		comboScaleY.setItems(itemsY);
		if(itemsY.length > 0) {
			comboScaleY.select(0);
		}
	}

	public void adjustRanges() {

		BaseChart baseChart = scrollableChart.getBaseChart();
		//
		int indexX = (comboScaleX.getSelectionIndex() >= 0) ? comboScaleX.getSelectionIndex() : BaseChart.ID_PRIMARY_X_AXIS;
		int indexY = (comboScaleY.getSelectionIndex() >= 0) ? comboScaleY.getSelectionIndex() : BaseChart.ID_PRIMARY_Y_AXIS;
		IAxis xAxis = baseChart.getAxisSet().getXAxis(indexX);
		IAxis yAxis = baseChart.getAxisSet().getYAxis(indexY);
		Range rangeX = xAxis.getRange();
		Range rangeY = yAxis.getRange();
		//
		DecimalFormat decimalFormatX = getDecimalFormat(IExtendedChart.X_AXIS, indexX);
		DecimalFormat decimalFormatY = getDecimalFormat(IExtendedChart.Y_AXIS, indexY);
		//
		if(rangeX != null && rangeY != null) {
			/*
			 * Update the text boxes.
			 */
			textStartX.setText(decimalFormatX.format(rangeX.lower));
			textStopX.setText(decimalFormatX.format(rangeX.upper));
			textStartY.setText(decimalFormatY.format(rangeY.lower));
			textStopY.setText(decimalFormatY.format(rangeY.upper));
			/*
			 * Redraw the base chart.
			 */
			baseChart.redraw();
		}
	}

	private void createControl() {

		setLayout(new GridLayout(8, false));
		//
		textStartX = new Text(this, SWT.BORDER);
		textStartX.setText("");
		textStartX.setLayoutData(getTextGridData());
		//
		textStopX = new Text(this, SWT.BORDER);
		textStopX.setText("");
		textStopX.setLayoutData(getTextGridData());
		//
		comboScaleX = new Combo(this, SWT.READ_ONLY);
		comboScaleX.setLayoutData(getComboGridData());
		comboScaleX.addSelectionListener(new SelectionAdapter() {

			@Override
			public void widgetSelected(SelectionEvent e) {

				int selectionIndex = comboScaleX.getSelectionIndex();
				BaseChart baseChart = scrollableChart.getBaseChart();
				IAxis xAxis = baseChart.getAxisSet().getXAxis(selectionIndex);
				Range rangeX = xAxis.getRange();
				if(rangeX != null) {
					DecimalFormat decimalFormatX = getDecimalFormat(IExtendedChart.X_AXIS, selectionIndex);
					textStartX.setText(decimalFormatX.format(rangeX.lower));
					textStopX.setText(decimalFormatX.format(rangeX.upper));
				}
			}
		});
		//
		textStartY = new Text(this, SWT.BORDER);
		textStartY.setText("");
		textStartY.setLayoutData(getTextGridData());
		//
		textStopY = new Text(this, SWT.BORDER);
		textStopY.setText("");
		textStopY.setLayoutData(getTextGridData());
		//
		comboScaleY = new Combo(this, SWT.READ_ONLY);
		comboScaleY.setLayoutData(getComboGridData());
		comboScaleY.addSelectionListener(new SelectionAdapter() {

			@Override
			public void widgetSelected(SelectionEvent e) {

				int selectionIndex = comboScaleY.getSelectionIndex();
				BaseChart baseChart = scrollableChart.getBaseChart();
				IAxis yAxis = baseChart.getAxisSet().getYAxis(selectionIndex);
				Range rangeY = yAxis.getRange();
				if(rangeY != null) {
					DecimalFormat decimalFormatY = getDecimalFormat(IExtendedChart.Y_AXIS, selectionIndex);
					textStartY.setText(decimalFormatY.format(rangeY.lower));
					textStopY.setText(decimalFormatY.format(rangeY.upper));
				}
			}
		});
		//
		Button buttonSetRange = new Button(this, SWT.PUSH);
		buttonSetRange.setText("");
		buttonSetRange.setToolTipText("Set the current selection.");
		buttonSetRange.setImage(Activator.getDefault().getImage(Activator.ICON_SET_RANGE));
		buttonSetRange.setLayoutData(getButtonGridData());
		buttonSetRange.addSelectionListener(new SelectionAdapter() {

			@Override
			public void widgetSelected(SelectionEvent e) {

				try {
					setRange();
				} catch(Exception e1) {
					System.out.println(e1);
				}
			}
		});
		//
		Button buttonHide = new Button(this, SWT.PUSH);
		buttonHide.setText("");
		buttonHide.setToolTipText("Hide the range info UI.");
		buttonHide.setImage(Activator.getDefault().getImage(Activator.ICON_HIDE));
		buttonHide.setLayoutData(getButtonGridData());
		buttonHide.addSelectionListener(new SelectionAdapter() {

			@Override
			public void widgetSelected(SelectionEvent e) {

				GridData gridData = (GridData)getLayoutData();
				gridData.exclude = true;
				setVisible(false);
				Composite parent = getParent();
				parent.layout(false);
				parent.redraw();
			}
		});
	}

	private void setRange() {

		try {
			/*
			 * Get the start and stop value.
			 */
			Range rangeX = getRange(IExtendedChart.X_AXIS);
			Range rangeY = getRange(IExtendedChart.Y_AXIS);
			/*
			 * Apply the range.
			 */
			if(rangeX != null && rangeY != null) {
				scrollableChart.setRange(IExtendedChart.X_AXIS, rangeX);
				scrollableChart.setRange(IExtendedChart.Y_AXIS, rangeY);
			}
		} catch(ParseException e) {
			System.out.println(e);
		}
	}

	private Range getRange(String axis) throws ParseException {

		/*
		 * Get the decimal format.
		 */
		DecimalFormat decimalFormat;
		int selectedAxis;
		//
		if(axis.equals(IExtendedChart.X_AXIS)) {
			selectedAxis = comboScaleX.getSelectionIndex();
			decimalFormat = getDecimalFormat(IExtendedChart.X_AXIS, selectedAxis);
		} else {
			selectedAxis = comboScaleY.getSelectionIndex();
			decimalFormat = getDecimalFormat(IExtendedChart.Y_AXIS, selectedAxis);
		}
		/*
		 * Get the start and stop value.
		 */
		double valueStart;
		double valueStop;
		//
		if(axis.equals(IExtendedChart.X_AXIS)) {
			valueStart = decimalFormat.parse(textStartX.getText().trim()).doubleValue();
			valueStop = decimalFormat.parse(textStopX.getText().trim()).doubleValue();
		} else {
			valueStart = decimalFormat.parse(textStartY.getText().trim()).doubleValue();
			valueStop = decimalFormat.parse(textStopY.getText().trim()).doubleValue();
		}
		/*
		 * Convert the range on demand.
		 */
		Range range = null;
		if(selectedAxis == 0) {
			range = new Range(valueStart, valueStop);
		} else {
			IAxisScaleConverter axisScaleConverter = getAxisScaleConverter(axis, selectedAxis);
			if(axisScaleConverter != null) {
				valueStart = axisScaleConverter.convertToPrimaryUnit(valueStart);
				valueStop = axisScaleConverter.convertToPrimaryUnit(valueStop);
				range = new Range(valueStart, valueStop);
			}
		}
		//
		return range;
	}

	private IAxisScaleConverter getAxisScaleConverter(String axis, int id) {

		IAxisScaleConverter axisScaleConverter = null;
		BaseChart baseChart = scrollableChart.getBaseChart();
		//
		IAxisSettings axisSettings = null;
		if(axis.equals(IExtendedChart.X_AXIS)) {
			axisSettings = baseChart.getXAxisSettingsMap().get(id);
		} else {
			axisSettings = baseChart.getYAxisSettingsMap().get(id);
		}
		//
		if(axisSettings instanceof ISecondaryAxisSettings) {
			axisScaleConverter = ((ISecondaryAxisSettings)axisSettings).getAxisScaleConverter();
		}
		//
		return axisScaleConverter;
	}

	private String[] getItems(String axis) {

		IAxis[] axes = getAxes(axis);
		int size = axes.length;
		String[] items = new String[size];
		//
		for(int i = 0; i < size; i++) {
			/*
			 * Get the label.
			 */
			String label = "";
			String title = axes[i].getTitle().getText();
			String description = getAxisDescription(axis, i);
			//
			if(title.equals("")) {
				/*
				 * Title is not set.
				 * Use the description instead or
				 * print a note that no label is available.
				 */
				if(description.equals("")) {
					label = "label not set";
				} else {
					label = description;
				}
			} else {
				/*
				 * Title is set.
				 * Use description if available
				 * otherwise the title.
				 */
				if(description.equals("")) {
					label = title;
				} else {
					label = description;
				}
			}
			/*
			 * Set the label.
			 */
			items[i] = label;
		}
		return items;
	}

	private IAxis[] getAxes(String axis) {

		BaseChart baseChart = scrollableChart.getBaseChart();
		IAxisSet axisSet = baseChart.getAxisSet();
		//
		if(axis.equals(IExtendedChart.X_AXIS)) {
			return axisSet.getXAxes();
		} else {
			return axisSet.getYAxes();
		}
	}

	private String getAxisDescription(String axis, int id) {

		String description = "";
		//
		IAxisSettings axisSettings = getAxisSettings(axis, id);
		if(axisSettings != null) {
			description = axisSettings.getDescription();
		}
		//
		return description;
	}

	private IAxisSettings getAxisSettings(String axis, int id) {

		BaseChart baseChart = scrollableChart.getBaseChart();
		IAxisSettings axisSettings = null;
		if(axis.equals(IExtendedChart.X_AXIS)) {
			axisSettings = baseChart.getXAxisSettingsMap().get(id);
		} else {
			axisSettings = baseChart.getYAxisSettingsMap().get(id);
		}
		return axisSettings;
	}

	private DecimalFormat getDecimalFormat(String axis, int id) {

		DecimalFormat decimalFormat;
		IAxisSettings axisSettings = getAxisSettings(axis, id);
		//
		if(axisSettings != null) {
			decimalFormat = axisSettings.getDecimalFormat();
		} else {
			decimalFormat = new DecimalFormat();
		}
		return decimalFormat;
	}

	private GridData getTextGridData() {

		GridData gridData = new GridData(GridData.FILL_HORIZONTAL);
		gridData.widthHint = 100;
		return gridData;
	}

	private GridData getComboGridData() {

		GridData gridData = new GridData(GridData.FILL_HORIZONTAL);
		gridData.widthHint = 100;
		return gridData;
	}

	private GridData getButtonGridData() {

		GridData gridData = new GridData();
		gridData.widthHint = 40;
		return gridData;
	}
}
