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
package org.eclipse.eavp.service.swtchart.menu.toggle;

import org.eclipse.eavp.service.swtchart.core.ScrollableChart;
import org.eclipse.eavp.service.swtchart.menu.AbstractChartMenuEntry;
import org.eclipse.eavp.service.swtchart.menu.IChartMenuCategories;
import org.eclipse.eavp.service.swtchart.menu.IChartMenuEntry;
import org.eclipse.swt.widgets.Shell;

public class ToggleSeriesLegendHandler extends AbstractChartMenuEntry implements IChartMenuEntry {

	@Override
	public String getCategory() {

		return IChartMenuCategories.TOGGLE_VISIBILITY;
	}

	@Override
	public String getName() {

		return "Series Legend";
	}

	@Override
	public void execute(Shell shell, ScrollableChart scrollableChart) {

		scrollableChart.toggleSeriesLegendVisibility();
	}
}
