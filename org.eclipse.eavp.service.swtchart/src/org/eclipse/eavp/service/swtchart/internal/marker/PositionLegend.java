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
package org.eclipse.eavp.service.swtchart.internal.marker;

import java.text.DecimalFormat;

import org.eclipse.eavp.service.swtchart.core.BaseChart;
import org.eclipse.eavp.service.swtchart.core.IAxisScaleConverter;
import org.eclipse.eavp.service.swtchart.core.IAxisSettings;
import org.eclipse.eavp.service.swtchart.core.IExtendedChart;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.widgets.Display;
import org.swtchart.IAxis;

public class PositionLegend implements IExtendedPaintListener {

	private int x;
	private int y;
	private Color foregroundColor;
	private boolean draw;
	//
	private BaseChart baseChart;
	private StringBuilder stringBuilder;
	//
	private String[] axisLabelsX;
	private DecimalFormat decimalFormatX;
	private String[] axisLabelsY;
	private DecimalFormat decimalFormatY;

	public PositionLegend(BaseChart baseChart) {
		x = -1;
		y = -1;
		foregroundColor = Display.getDefault().getSystemColor(SWT.COLOR_BLACK);
		draw = true;
		//
		stringBuilder = new StringBuilder();
		this.baseChart = baseChart;
		//
		axisLabelsX = baseChart.getAxisLabels(IExtendedChart.X_AXIS);
		decimalFormatX = baseChart.getDecimalFormat(IExtendedChart.X_AXIS, BaseChart.ID_PRIMARY_X_AXIS);
		axisLabelsY = baseChart.getAxisLabels(IExtendedChart.Y_AXIS);
		decimalFormatY = baseChart.getDecimalFormat(IExtendedChart.Y_AXIS, BaseChart.ID_PRIMARY_Y_AXIS);
	}

	@Override
	public void setActualPosition(int x, int y) {

		this.x = x;
		this.y = y;
	}

	@Override
	public void setForegroundColor(Color foregroundColor) {

		this.foregroundColor = foregroundColor;
	}

	@Override
	public boolean isDraw() {

		return draw;
	}

	@Override
	public void setDraw(boolean draw) {

		this.draw = draw;
	}

	@Override
	public boolean drawBehindSeries() {

		return false;
	}

	@Override
	public void paintControl(PaintEvent e) {

		if(draw) {
			stringBuilder.delete(0, stringBuilder.length());
			e.gc.setForeground(foregroundColor);
			//
			double primaryValueX = getSelectedPrimaryAxisValue(x, IExtendedChart.X_AXIS);
			double primaryValueY = getSelectedPrimaryAxisValue(y, IExtendedChart.Y_AXIS);
			//
			drawXAxes(primaryValueX);
			drawYAxes(primaryValueY);
			e.gc.drawText(stringBuilder.toString(), 10, 10);
		}
	}

	private void drawXAxes(double primaryValueX) {

		/*
		 * X Axes
		 */
		IAxisSettings axisSettingsX = baseChart.getXAxisSettings(BaseChart.ID_PRIMARY_X_AXIS);
		if(axisSettingsX != null && axisSettingsX.isVisible()) {
			stringBuilder.append(axisLabelsX[BaseChart.ID_PRIMARY_X_AXIS]);
			stringBuilder.append(": ");
			stringBuilder.append(decimalFormatX.format(primaryValueX));
		}
		//
		for(int id : baseChart.getAxisSet().getXAxisIds()) {
			if(id != BaseChart.ID_PRIMARY_X_AXIS) {
				IAxisSettings axisSettings = baseChart.getXAxisSettings(id);
				if(axisSettings != null) {
					IAxisScaleConverter axisScaleConverter = baseChart.getAxisScaleConverter(IExtendedChart.X_AXIS, id);
					if(axisSettings.isVisible() && axisScaleConverter != null) {
						if(stringBuilder.length() > 0) {
							stringBuilder.append("\n");
						}
						stringBuilder.append(axisLabelsX[id]);
						stringBuilder.append(": ");
						stringBuilder.append(axisSettings.getDecimalFormat().format(axisScaleConverter.convertToSecondaryUnit(primaryValueX)));
					}
				}
			}
		}
	}

	private void drawYAxes(double primaryValueY) {

		/*
		 * Y Axes
		 */
		IAxisSettings axisSettingsY = baseChart.getYAxisSettings(BaseChart.ID_PRIMARY_Y_AXIS);
		if(axisSettingsY != null && axisSettingsY.isVisible()) {
			if(stringBuilder.length() > 0) {
				stringBuilder.append("\n");
			}
			stringBuilder.append(axisLabelsY[BaseChart.ID_PRIMARY_Y_AXIS]);
			stringBuilder.append(": ");
			stringBuilder.append(decimalFormatY.format(primaryValueY));
		}
		//
		for(int id : baseChart.getAxisSet().getYAxisIds()) {
			if(id != BaseChart.ID_PRIMARY_Y_AXIS) {
				IAxisSettings axisSettings = baseChart.getYAxisSettings(id);
				if(axisSettings != null) {
					IAxisScaleConverter axisScaleConverter = baseChart.getAxisScaleConverter(IExtendedChart.Y_AXIS, id);
					if(axisSettings.isVisible() && axisScaleConverter != null) {
						if(stringBuilder.length() > 0) {
							stringBuilder.append("\n");
						}
						stringBuilder.append(axisLabelsY[id]);
						stringBuilder.append(": ");
						stringBuilder.append(axisSettings.getDecimalFormat().format(axisScaleConverter.convertToSecondaryUnit(primaryValueY)));
					}
				}
			}
		}
	}

	private double getSelectedPrimaryAxisValue(int position, String orientation) {

		double primaryValue = 0.0d;
		if(baseChart != null) {
			//
			double start;
			double stop;
			int length;
			//
			if(orientation.equals(IExtendedChart.X_AXIS)) {
				IAxis axis = baseChart.getAxisSet().getXAxis(BaseChart.ID_PRIMARY_X_AXIS);
				start = axis.getRange().lower;
				stop = axis.getRange().upper;
				length = baseChart.getPlotArea().getBounds().width;
			} else {
				IAxis axis = baseChart.getAxisSet().getYAxis(BaseChart.ID_PRIMARY_Y_AXIS);
				start = axis.getRange().lower;
				stop = axis.getRange().upper;
				length = baseChart.getPlotArea().getBounds().height;
			}
			//
			if(position <= 0) {
				primaryValue = start;
			} else if(position > length) {
				primaryValue = stop;
			} else {
				double delta = stop - start;
				double percentage;
				if(orientation.equals(IExtendedChart.X_AXIS)) {
					percentage = ((100.0d / length) * position) / 100.0d;
				} else {
					percentage = (100.0d - ((100.0d / length) * position)) / 100.0d;
				}
				primaryValue = start + delta * percentage;
			}
		}
		return primaryValue;
	}
}
