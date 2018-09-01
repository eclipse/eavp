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

import java.util.Set;

import org.eclipse.eavp.service.swtchart.core.BaseChart;
import org.eclipse.eavp.service.swtchart.core.IExtendedChart;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Event;
import org.swtchart.ISeries;

public class MouseMoveShiftEvent extends AbstractHandledEventProcessor implements IHandledEventProcessor {

	private int shiftMask = SWT.SHIFT;

	@Override
	public int getEvent() {

		return BaseChart.EVENT_MOUSE_MOVE;
	}

	@Override
	public int getStateMask() {

		return SWT.CTRL;
	}

	@Override
	public void handleEvent(BaseChart baseChart, Event event) {

		if((event.stateMask & shiftMask) == shiftMask) {
			/*
			 * Shift the selected series
			 */
			boolean supportDataShift = baseChart.getChartSettings().isSupportDataShift();
			Set<String> selectedSeriesIds = baseChart.getSelectedSeriesIds();
			if(supportDataShift && selectedSeriesIds.size() > 0) {
				/*
				 * Only shift if series have been selected.
				 */
				if(baseChart.getMoveStartTime() == 0) {
					/*
					 * Start
					 */
					baseChart.setCursor(baseChart.getDisplay().getSystemCursor(SWT.CURSOR_SIZENWSE));
					baseChart.setMoveStartTime(System.currentTimeMillis());
					baseChart.setXMoveStart(event.x);
					baseChart.setYMoveStart(event.y);
				} else {
					long deltaTime = System.currentTimeMillis() - baseChart.getMoveStartTime();
					if(deltaTime <= BaseChart.DELTA_MOVE_TIME) {
						/*
						 * Shift
						 */
						baseChart.setMoveStartTime(System.currentTimeMillis());
						//
						double shiftX = baseChart.getShiftValue(baseChart.getXMoveStart(), event.x, IExtendedChart.X_AXIS);
						double shiftY = baseChart.getShiftValue(baseChart.getYMoveStart(), event.y, IExtendedChart.Y_AXIS);
						//
						for(String selectedSeriesId : selectedSeriesIds) {
							ISeries dataSeries = baseChart.getSeriesSet().getSeries(selectedSeriesId);
							if(dataSeries != null) {
								baseChart.shiftSeries(selectedSeriesId, shiftX, shiftY);
							}
						}
						baseChart.redraw();
						//
						baseChart.setXMoveStart(event.x);
						baseChart.setYMoveStart(event.y);
					} else {
						/*
						 * Default
						 */
						baseChart.setMoveStartTime(0);
						baseChart.setXMoveStart(0);
						baseChart.setYMoveStart(0);
					}
				}
			}
		}
	}
}
