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

		BaseChart baseChart = scrollableChart.getBaseChart();
		IAxisSet axisSet = baseChart.getAxisSet();
		/*
		 * X, Y Axes
		 */
		String[] itemsX = getItems(axisSet.getXAxes());
		comboScaleX.setItems(itemsX);
		if(itemsX.length > 0) {
			comboScaleX.select(0);
		}
		//
		String[] itemsY = getItems(axisSet.getYAxes());
		comboScaleY.setItems(itemsY);
		if(itemsY.length > 0) {
			comboScaleY.select(0);
		}
	}

	public void adjustRanges() {

		BaseChart baseChart = scrollableChart.getBaseChart();
		IAxis xAxis = baseChart.getAxisSet().getXAxis(comboScaleX.getSelectionIndex());
		IAxis yAxis = baseChart.getAxisSet().getYAxis(comboScaleY.getSelectionIndex());
		Range rangeX = xAxis.getRange();
		Range rangeY = yAxis.getRange();
		//
		if(rangeX != null && rangeY != null) {
			textStartX.setText(Double.toString(rangeX.lower));
			textStopX.setText(Double.toString(rangeX.upper));
			textStartY.setText(Double.toString(rangeY.lower));
			textStopY.setText(Double.toString(rangeY.upper));
			/*
			 * Redraw the base chart.
			 */
			scrollableChart.getBaseChart().redraw();
		}
	}

	private void createControl() {

		setLayout(new GridLayout(8, false));
		//
		textStartX = new Text(this, SWT.BORDER);
		textStartX.setText("");
		textStartX.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		//
		textStopX = new Text(this, SWT.BORDER);
		textStopX.setText("");
		textStopX.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		//
		comboScaleX = new Combo(this, SWT.READ_ONLY);
		comboScaleX.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		comboScaleX.addSelectionListener(new SelectionAdapter() {

			@Override
			public void widgetSelected(SelectionEvent e) {

				int selectionIndex = comboScaleX.getSelectionIndex();
				BaseChart baseChart = scrollableChart.getBaseChart();
				IAxis xAxis = baseChart.getAxisSet().getXAxis(selectionIndex);
				Range rangeX = xAxis.getRange();
				if(rangeX != null) {
					textStartX.setText(Double.toString(rangeX.lower));
					textStopX.setText(Double.toString(rangeX.upper));
				}
			}
		});
		//
		textStartY = new Text(this, SWT.BORDER);
		textStartY.setText("");
		textStartY.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		//
		textStopY = new Text(this, SWT.BORDER);
		textStopY.setText("");
		textStopY.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		//
		comboScaleY = new Combo(this, SWT.READ_ONLY);
		comboScaleY.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		comboScaleY.addSelectionListener(new SelectionAdapter() {

			@Override
			public void widgetSelected(SelectionEvent e) {

				int selectionIndex = comboScaleY.getSelectionIndex();
				BaseChart baseChart = scrollableChart.getBaseChart();
				IAxis yAxis = baseChart.getAxisSet().getYAxis(selectionIndex);
				Range rangeY = yAxis.getRange();
				if(rangeY != null) {
					textStartY.setText(Double.toString(rangeY.lower));
					textStopY.setText(Double.toString(rangeY.upper));
				}
			}
		});
		//
		Button buttonSetRange = new Button(this, SWT.PUSH);
		buttonSetRange.setText("Set Range");
		buttonSetRange.setLayoutData(getButtonGridData());
		buttonSetRange.addSelectionListener(new SelectionAdapter() {

			@Override
			public void widgetSelected(SelectionEvent e) {

				try {
					setRange(IExtendedChart.X_AXIS);
					setRange(IExtendedChart.Y_AXIS);
				} catch(Exception e1) {
					System.out.println(e1);
				}
			}
		});
		//
		Button buttonHide = new Button(this, SWT.PUSH);
		buttonHide.setText("Hide");
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

	private void setRange(String axis) {

		BaseChart baseChart = scrollableChart.getBaseChart();
		//
		double valueStart;
		double valueStop;
		if(axis.equals(IExtendedChart.X_AXIS)) {
			valueStart = Double.valueOf(textStartX.getText().trim());
			valueStop = Double.valueOf(textStopX.getText().trim());
		} else {
			valueStart = Double.valueOf(textStartY.getText().trim());
			valueStop = Double.valueOf(textStopY.getText().trim());
		}
		/*
		 * Get the correct range.
		 */
		int selectedAxis;
		if(axis.equals(IExtendedChart.X_AXIS)) {
			selectedAxis = comboScaleX.getSelectionIndex();
		} else {
			selectedAxis = comboScaleY.getSelectionIndex();
		}
		//
		Range range = null;
		if(selectedAxis == 0) {
			range = new Range(valueStart, valueStop);
		} else {
			IAxisScaleConverter axisScaleConverter;
			if(axis.equals(IExtendedChart.X_AXIS)) {
				axisScaleConverter = baseChart.getXAxisScaleConverterMap().get(selectedAxis);
			} else {
				axisScaleConverter = baseChart.getYAxisScaleConverterMap().get(selectedAxis);
			}
			//
			if(axisScaleConverter != null) {
				valueStart = axisScaleConverter.convertToPrimaryUnit(valueStart);
				valueStop = axisScaleConverter.convertToPrimaryUnit(valueStop);
				range = new Range(valueStart, valueStop);
			}
		}
		/*
		 * Apply the range.
		 */
		if(range != null) {
			scrollableChart.setRange(axis, range);
		}
	}

	private String[] getItems(IAxis[] axes) {

		int size = axes.length;
		String[] items = new String[size];
		for(int i = 0; i < size; i++) {
			String label = axes[i].getTitle().getText();
			if(label.equals("")) {
				label = "no label available";
			}
			items[i] = label;
		}
		return items;
	}

	private GridData getButtonGridData() {

		GridData gridData = new GridData();
		gridData.widthHint = 100;
		return gridData;
	}
}
