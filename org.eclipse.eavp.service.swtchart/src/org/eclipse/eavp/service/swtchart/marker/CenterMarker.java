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
import org.swtchart.ICustomPaintListener;

public class CenterMarker implements ICustomPaintListener {

	private Color foregroundColor;
	private boolean draw;

	public CenterMarker() {
		foregroundColor = Display.getCurrent().getSystemColor(SWT.COLOR_DARK_GRAY);
		draw = true;
	}

	public boolean isDraw() {

		return draw;
	}

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
