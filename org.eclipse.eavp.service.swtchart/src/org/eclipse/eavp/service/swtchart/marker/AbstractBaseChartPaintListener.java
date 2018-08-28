/*******************************************************************************
 * Copyright (c) 2017, 2018 Lablicate GmbH.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 * Dr. Philip Wenig - initial API and implementation
 *******************************************************************************/
package org.eclipse.eavp.service.swtchart.marker;

import org.eclipse.eavp.service.swtchart.core.BaseChart;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;

public abstract class AbstractBaseChartPaintListener implements IBaseChartPaintListener {

	private BaseChart baseChart;
	private Color foregroundColor;
	private boolean draw = true;

	public AbstractBaseChartPaintListener(BaseChart baseChart) {
		this.baseChart = baseChart;
		foregroundColor = baseChart.getDisplay().getSystemColor(SWT.COLOR_BLACK);
	}

	@Override
	public BaseChart getBaseChart() {

		return baseChart;
	}

	@Override
	public boolean drawBehindSeries() {

		return false;
	}

	@Override
	public void setForegroundColor(Color foregroundColor) {

		this.foregroundColor = foregroundColor;
	}

	protected Color getForegroundColor() {

		return foregroundColor;
	}

	@Override
	public boolean isDraw() {

		return draw;
	}

	@Override
	public void setDraw(boolean draw) {

		this.draw = draw;
	}
}
