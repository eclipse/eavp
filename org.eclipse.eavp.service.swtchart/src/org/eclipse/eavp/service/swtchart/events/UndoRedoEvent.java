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

public class UndoRedoEvent extends AbstractHandledEventProcessor implements IHandledEventProcessor {

	private int redoMask = SWT.SHIFT;

	@Override
	public int getEvent() {

		return BaseChart.EVENT_KEY_UP;
	}

	@Override
	public int getButton() {

		return BaseChart.KEY_CODE_Z;
	}

	@Override
	public int getStateMask() {

		return SWT.CTRL;
	}

	@Override
	public void handleEvent(BaseChart baseChart, Event event) {

		if((event.stateMask & redoMask) == redoMask) {
			/*
			 * Redo
			 */
			baseChart.redoSelection();
		} else {
			/*
			 * Undo
			 */
			baseChart.undoSelection();
		}
		baseChart.redraw();
	}
}
