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

public class PositionMarker implements IExtendedPaintListener {

	private int x;
	private int y;
	private Color foregroundColor;
	private boolean draw;

	public PositionMarker() {
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

		/*
		 * Plots a vertical/horizontal line in the plot area at the x position of the mouse.
		 */
		if(draw) {
			e.gc.setForeground(foregroundColor);
			if(x > 0 && x < e.width && y > 0 && y < e.height) {
				e.gc.drawLine(x, 0, x, e.height);
				e.gc.drawLine(0, y, e.width, y);
			}
		}
	}

	@Override
	public boolean drawBehindSeries() {

		return false;
	}
}
