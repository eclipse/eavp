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
import org.eclipse.swt.events.PaintEvent;

public class PositionMarker extends AbstractExtendedPaintListener implements IExtendedPaintListener {

	public PositionMarker(BaseChart baseChart) {
		super(baseChart);
	}

	@Override
	public void paintControl(PaintEvent e) {

		/*
		 * Plots a vertical/horizontal line in the plot area at the x position of the mouse.
		 */
		if(isDraw()) {
			int x = getX();
			int y = getY();
			e.gc.setForeground(getForegroundColor());
			if(x > 0 && x < e.width && y > 0 && y < e.height) {
				e.gc.drawLine(x, 0, x, e.height);
				e.gc.drawLine(0, y, e.width, y);
			}
		}
	}
}
