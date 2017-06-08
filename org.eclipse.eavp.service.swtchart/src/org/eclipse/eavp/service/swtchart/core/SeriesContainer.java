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
package org.eclipse.eavp.service.swtchart.core;

public class SeriesContainer {

	private double[] xSeries;
	private double[] ySeries;

	public SeriesContainer(double[] xSeries, double[] ySeries) {
		this.xSeries = xSeries;
		this.ySeries = ySeries;
	}

	public double[] getXSeries() {

		return xSeries;
	}

	public double[] getYSeries() {

		return ySeries;
	}
}
