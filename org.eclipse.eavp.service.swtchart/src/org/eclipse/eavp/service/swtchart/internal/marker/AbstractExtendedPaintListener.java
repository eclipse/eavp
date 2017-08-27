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

import org.eclipse.eavp.service.swtchart.core.BaseChart;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.widgets.Display;

public abstract class AbstractExtendedPaintListener implements IExtendedPaintListener {

	private BaseChart baseChart;
	private int x = -1;
	private int y = -1;
	private Color foregroundColor = Display.getDefault().getSystemColor(SWT.COLOR_BLACK);
	private boolean draw = true;

	public AbstractExtendedPaintListener(BaseChart baseChart) {
		this.baseChart = baseChart;
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
	public void setActualPosition(int x, int y) {

		this.x = x;
		this.y = y;
	}

	protected int getX() {

		return x;
	}

	protected int getY() {

		return y;
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
