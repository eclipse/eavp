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
package org.eclipse.eavp.service.swtchart.menu.toggle;

import org.eclipse.eavp.service.swtchart.core.IChartSettings;
import org.eclipse.eavp.service.swtchart.core.ScrollableChart;
import org.eclipse.eavp.service.swtchart.menu.AbstractMenuEntry;
import org.eclipse.eavp.service.swtchart.menu.IMenuCategories;
import org.eclipse.eavp.service.swtchart.menu.IMenuEntry;
import org.eclipse.swt.widgets.Shell;

public class TogglePlotCenterMarkerHandler extends AbstractMenuEntry implements IMenuEntry {

	@Override
	public String getCategory() {

		return IMenuCategories.TOGGLE_VISIBILITY;
	}

	@Override
	public String getName() {

		return "Plot Center Marker";
	}

	@Override
	public boolean isEnabled(ScrollableChart scrollableChart) {

		IChartSettings chartSettings = scrollableChart.getChartSettings();
		if(chartSettings.isShowPlotCenterMarker()) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public void execute(Shell shell, ScrollableChart scrollableChart) {

		scrollableChart.toggleCenterMarkerVisibility();
	}
}
