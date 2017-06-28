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

import org.swtchart.Range;

public interface IScrollableChart {

	IChartSettings getChartSettings();

	void applySettings(IChartSettings chartSettings);

	BaseChart getBaseChart();

	/**
	 * Delete all series.
	 */
	void deleteSeries();

	/**
	 * Sets the range, based on the range coordinates.
	 * It's only possible to set the range for the primary axes as
	 * the range for secondary axes is calculated dynamically.
	 * 
	 * Use: IExtendedChart.X_AXIS or IExtendedChart.Y_AXIS.
	 * 
	 * @param axis
	 * @param start
	 * @param stop
	 * @param adjustMinMax
	 */
	void setRange(String axis, Range range);
}
