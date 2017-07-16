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
package org.eclipse.eavp.service.swtchart.marker;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.widgets.Display;

public class CenterMarker implements IExtendedPaintListener {

	@SuppressWarnings("unused")
	private int x;
	@SuppressWarnings("unused")
	private int y;
	private Color foregroundColor;
	private boolean draw;

	public CenterMarker() {
		x = -1;
		y = -1;
		foregroundColor = Display.getDefault().getSystemColor(SWT.COLOR_BLACK);
		draw = true;
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
	public void paintControl(PaintEvent e) {

		if(draw) {
			e.gc.setForeground(foregroundColor);
			int currentLineStyle = e.gc.getLineStyle();
			e.gc.setLineStyle(SWT.LINE_DASHDOT);
			int width = e.width / 2;
			e.gc.drawLine(width, 0, width, e.height);
			e.gc.setLineStyle(currentLineStyle);
		}
	}

	@Override
	public boolean drawBehindSeries() {

		return false;
	}
}
