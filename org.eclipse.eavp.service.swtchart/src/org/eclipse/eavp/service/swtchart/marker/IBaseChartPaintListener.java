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
package org.eclipse.eavp.service.swtchart.marker;

import org.eclipse.eavp.service.swtchart.core.BaseChart;
import org.eclipse.swt.graphics.Color;
import org.swtchart.ICustomPaintListener;

public interface IBaseChartPaintListener extends ICustomPaintListener {

	BaseChart getBaseChart();

	void setForegroundColor(Color foregroundColor);

	boolean isDraw();

	void setDraw(boolean draw);
}
