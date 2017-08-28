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

import java.util.ArrayList;
import java.util.List;

import org.eclipse.eavp.service.swtchart.core.BaseChart;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.graphics.Transform;
import org.eclipse.swt.widgets.Display;
import org.swtchart.ISeries;

public class LabelMarker extends AbstractBaseChartPaintListener implements IBaseChartPaintListener {

	private Transform transform = null;
	private List<String> labels = new ArrayList<String>();
	private int indexSeries = -1;

	public LabelMarker(BaseChart baseChart) {
		super(baseChart);
	}

	public void setLabels(List<String> labels, int indexSeries, int orientation) {

		this.labels = (labels != null) ? labels : new ArrayList<String>();
		this.indexSeries = indexSeries;
		if(orientation == SWT.VERTICAL) {
			transform = new Transform(Display.getDefault());
			transform.rotate(-90);
		} else {
			transform = null;
		}
	}

	@Override
	public void paintControl(PaintEvent e) {

		BaseChart baseChart = getBaseChart();
		Rectangle rectangle = baseChart.getPlotArea().getClientArea();
		ISeries[] series = baseChart.getSeriesSet().getSeries();
		if(indexSeries >= 0 && indexSeries < series.length) {
			ISeries serie = series[indexSeries];
			for(int i = 0; i < labels.size(); i++) {
				/*
				 * Draw the label
				 */
				String label = labels.get(i);
				Point point = serie.getPixelCoordinates(i);
				//
				if(rectangle.contains(point)) {
					Point labelSize = e.gc.textExtent(label);
					int x = -labelSize.x - (point.y - labelSize.x - 15);
					int y = point.x - (labelSize.y / 2);
					//
					GC gc = e.gc;
					if(transform != null) {
						gc.setTransform(transform);
					}
					gc.drawText(label, x, y, true);
					gc.setTransform(null);
				}
			}
		}
	}
}
