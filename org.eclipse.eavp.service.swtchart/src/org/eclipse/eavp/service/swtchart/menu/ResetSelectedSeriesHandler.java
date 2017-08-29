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
package org.eclipse.eavp.service.swtchart.menu;

import org.eclipse.eavp.service.swtchart.core.BaseChart;
import org.eclipse.eavp.service.swtchart.core.ScrollableChart;
import org.eclipse.eavp.service.swtchart.linecharts.LineChart;
import org.eclipse.swt.widgets.Shell;

public class ResetSelectedSeriesHandler extends AbstractMenuEntry implements IMenuEntry {

	@Override
	public String getCategory() {

		return IMenuCategories.RANGE_SELECTION;
	}

	@Override
	public String getName() {

		return "Reset Selected Series";
	}

	@Override
	public boolean isEnabled(ScrollableChart scrollableChart) {

		if(scrollableChart instanceof LineChart) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public void execute(Shell shell, ScrollableChart scrollableChart) {

		BaseChart baseChart = scrollableChart.getBaseChart();
		baseChart.resetSelectedSeries();
	}
}
