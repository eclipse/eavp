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
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Event;

public class MouseMoveSelectionEvent extends AbstractHandledEventProcessor implements IHandledEventProcessor {

	@Override
	public int getEvent() {

		return BaseChart.EVENT_MOUSE_MOVE;
	}

	@Override
	public int getStateMask() {

		return SWT.BUTTON1;
	}

	@Override
	public void handleEvent(BaseChart baseChart, Event event) {

		/*
		 * Set Selection Range
		 */
		baseChart.getUserSelection().setStopCoordinate(event.x, event.y);
		baseChart.increaseRedrawCounter();
		if(baseChart.isRedraw()) {
			/*
			 * Rectangle is drawn here:
			 * void paintControl(PaintEvent e)
			 */
			baseChart.redraw();
			baseChart.resetRedrawCounter();
		}
	}
}
