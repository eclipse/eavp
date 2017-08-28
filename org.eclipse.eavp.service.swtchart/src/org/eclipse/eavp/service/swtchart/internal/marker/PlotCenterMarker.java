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
import org.eclipse.eavp.service.swtchart.marker.AbstractBaseChartPaintListener;
import org.eclipse.eavp.service.swtchart.marker.IBaseChartPaintListener;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.PaintEvent;

public class PlotCenterMarker extends AbstractBaseChartPaintListener implements IBaseChartPaintListener {

	public PlotCenterMarker(BaseChart baseChart) {
		super(baseChart);
	}

	@Override
	public void paintControl(PaintEvent e) {

		if(isDraw()) {
			int currentLineStyle = e.gc.getLineStyle();
			e.gc.setForeground(getForegroundColor());
			e.gc.setLineStyle(SWT.LINE_DASHDOT);
			int width = e.width / 2;
			e.gc.drawLine(width, 0, width, e.height);
			e.gc.setLineStyle(currentLineStyle);
		}
	}
}
