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
package org.eclipse.eavp.service.swtchart.core;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.widgets.Display;
import org.swtchart.ILineSeries.PlotSymbolType;

public abstract class AbstractPointSeriesSettings extends AbstractSeriesSettings implements IPointSeriesSettings {

	private PlotSymbolType symbolType;
	private int symbolSize;
	private Color symbolColor;

	public AbstractPointSeriesSettings() {
		symbolType = PlotSymbolType.NONE;
		symbolSize = 8;
		symbolColor = Display.getDefault().getSystemColor(SWT.COLOR_BLACK);
	}

	@Override
	public PlotSymbolType getSymbolType() {

		return symbolType;
	}

	@Override
	public void setSymbolType(PlotSymbolType symbolType) {

		this.symbolType = symbolType;
	}

	@Override
	public int getSymbolSize() {

		return symbolSize;
	}

	@Override
	public void setSymbolSize(int symbolSize) {

		this.symbolSize = symbolSize;
	}

	@Override
	public Color getSymbolColor() {

		return symbolColor;
	}

	@Override
	public void setSymbolColor(Color symbolColor) {

		this.symbolColor = symbolColor;
	}
}
