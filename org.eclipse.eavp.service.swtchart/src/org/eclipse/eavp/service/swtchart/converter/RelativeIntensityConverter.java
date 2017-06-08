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
package org.eclipse.eavp.service.swtchart.converter;

import org.eclipse.eavp.service.swtchart.core.AbstractAxisScaleConverter;
import org.eclipse.eavp.service.swtchart.core.IAxisScaleConverter;
import org.eclipse.eavp.service.swtchart.core.IChartDataCoordinates;
import org.eclipse.swt.SWT;

public class RelativeIntensityConverter extends AbstractAxisScaleConverter implements IAxisScaleConverter {

	private int orientation;

	/**
	 * Select the orientation:
	 * X-Axis: SWT.HORIZONTAL
	 * Y-AXis: SWT.VERTICAL
	 */
	public RelativeIntensityConverter(int orientation) {
		this.orientation = orientation;
	}

	@Override
	public double convertToSecondaryUnit(double primaryValue) {

		IChartDataCoordinates chartDataCoordinates = getChartDataCoordinates();
		double convertedValue = 0;
		if(chartDataCoordinates != null) {
			/*
			 * Delta
			 */
			double delta;
			if(orientation == SWT.VERTICAL) {
				delta = chartDataCoordinates.getMaxY() - chartDataCoordinates.getMinY();
			} else {
				delta = chartDataCoordinates.getMaxX() - chartDataCoordinates.getMinX();
			}
			/*
			 * Calculation
			 */
			if(delta != 0) {
				convertedValue = (100.0d / delta) * primaryValue;
			}
		}
		return convertedValue;
	}

	@Override
	public double convertToPrimaryUnit(double secondaryValue) {

		IChartDataCoordinates chartDataCoordinates = getChartDataCoordinates();
		double convertedValue = 0;
		if(chartDataCoordinates != null) {
			/*
			 * Delta
			 */
			double delta;
			if(orientation == SWT.VERTICAL) {
				delta = chartDataCoordinates.getMaxY() - chartDataCoordinates.getMinY();
			} else {
				delta = chartDataCoordinates.getMaxX() - chartDataCoordinates.getMinX();
			}
			/*
			 * Calculation
			 */
			convertedValue = delta * (secondaryValue / 100.0d);
		}
		return convertedValue;
	}
}
