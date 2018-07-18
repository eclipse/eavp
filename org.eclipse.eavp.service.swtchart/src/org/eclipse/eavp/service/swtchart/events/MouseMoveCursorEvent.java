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
import org.eclipse.swt.graphics.Cursor;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.ToolTip;

public class MouseMoveCursorEvent extends AbstractHandledEventProcessor implements IHandledEventProcessor {

	private Cursor defaultCursor = Display.getDefault().getSystemCursor(SWT.CURSOR_ARROW);
	private ToolTip tip;

	public MouseMoveCursorEvent() {
		tip = new ToolTip(Display.getDefault().getActiveShell(), SWT.NONE);
	}

	@Override
	public int getEvent() {

		return BaseChart.EVENT_MOUSE_MOVE;
	}

	@Override
	public int getStateMask() {

		return SWT.NONE;
	}

	@Override
	public void handleEvent(BaseChart baseChart, Event event) {

		String selectedSeriesId = baseChart.getSelectedseriesId(event);
		if(selectedSeriesId.equals("")) {
			baseChart.setCursor(defaultCursor);
			tip.setVisible(false);
		} else {
			baseChart.setCursor(Display.getDefault().getSystemCursor(SWT.CURSOR_HAND));
			if(baseChart.getChartSettings().isEnableTooltips()) {
				tip.setMessage(selectedSeriesId);
				tip.setVisible(true);
				tip.setAutoHide(false);
			}
		}
	}
}
