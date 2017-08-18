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

import org.eclipse.eavp.service.swtchart.core.ScrollableChart;
import org.eclipse.swt.widgets.Shell;

public interface ISeriesExportConverter {

	String MESSAGE_OK = "The data has been exported successully.";
	String MESSAGE_ERROR = "Sorry, something has gone wrong to export the data.";

	String getName();

	void export(Shell shell, ScrollableChart scrollableChart);
}
