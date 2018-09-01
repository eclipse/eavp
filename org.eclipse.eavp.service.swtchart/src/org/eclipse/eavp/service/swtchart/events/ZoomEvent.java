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
package org.eclipse.eavp.service.swtchart.events;

import org.eclipse.eavp.service.swtchart.core.BaseChart;
import org.eclipse.eavp.service.swtchart.core.RangeRestriction;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Event;
import org.swtchart.IAxis;
import org.swtchart.IAxisSet;
import org.swtchart.Range;

public class ZoomEvent extends AbstractHandledEventProcessor implements IHandledEventProcessor {

	@Override
	public int getEvent() {

		return BaseChart.EVENT_MOUSE_WHEEL;
	}

	@Override
	public int getButton() {

		return BaseChart.BUTTON_WHEEL;
	}

	@Override
	public int getStateMask() {

		return SWT.NONE;
	}

	@Override
	public void handleEvent(BaseChart baseChart, Event event) {

		IAxisSet axisSet = baseChart.getAxisSet();
		IAxis xAxis = axisSet.getXAxis(BaseChart.ID_PRIMARY_X_AXIS);
		IAxis yAxis = axisSet.getYAxis(BaseChart.ID_PRIMARY_Y_AXIS);
		//
		RangeRestriction rangeRestriction = baseChart.getRangeRestriction();
		if(baseChart.isZoomXAndY(rangeRestriction)) {
			/*
			 * X and Y zoom.
			 */
			baseChart.zoomX(xAxis, event);
			baseChart.zoomY(yAxis, event);
		} else {
			/*
			 * X or Y zoom.
			 */
			if(rangeRestriction.isXZoomOnly()) {
				baseChart.zoomX(xAxis, event);
			} else if(rangeRestriction.isYZoomOnly()) {
				baseChart.zoomY(yAxis, event);
			}
		}
		/*
		 * Adjust the range if it shall not exceed the initial
		 * min and max values.
		 */
		if(rangeRestriction.isRestrictZoom()) {
			/*
			 * Adjust the primary axes.
			 * The secondary axes are adjusted by setting the range.
			 */
			Range rangeX = xAxis.getRange();
			Range rangeY = yAxis.getRange();
			baseChart.setRange(xAxis, rangeX.lower, rangeX.upper, true);
			baseChart.setRange(yAxis, rangeY.lower, rangeY.upper, true);
		} else {
			/*
			 * Update the secondary axes.
			 */
			baseChart.adjustSecondaryXAxes();
			baseChart.adjustSecondaryYAxes();
		}
		//
		baseChart.fireUpdateCustomRangeSelectionHandlers(event);
		baseChart.redraw();
	}
}
