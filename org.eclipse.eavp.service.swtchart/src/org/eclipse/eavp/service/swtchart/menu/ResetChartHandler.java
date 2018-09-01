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
package org.eclipse.eavp.service.swtchart.menu;

import org.eclipse.eavp.service.swtchart.core.ScrollableChart;
import org.eclipse.swt.widgets.Shell;

public class ResetChartHandler extends AbstractChartMenuEntry implements IChartMenuEntry {

	public static final String NAME = "Reset Chart (1:1)";

	@Override
	public String getCategory() {

		return IChartMenuCategories.STANDARD_OPERATION;
	}

	@Override
	public String getName() {

		return NAME;
	}

	@Override
	public void execute(Shell shell, ScrollableChart scrollableChart) {

		scrollableChart.adjustRange(true);
		scrollableChart.redraw();
	}
}
