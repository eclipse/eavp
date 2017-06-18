/*******************************************************************************
 * Copyright (c) 2017 Lablicate GmbH.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 * Philip Wenig - initial API and implementation
 *******************************************************************************/
package org.eclipse.eavp.service.swtchart.support;

public class BarSeriesIon {

	private double mz;
	private double intensity;
	private int index;

	public BarSeriesIon(double mz, double intensity, int index) {
		this.mz = mz;
		this.intensity = intensity;
		this.index = index;
	}

	public double getMz() {

		return mz;
	}

	public double getIntensity() {

		return intensity;
	}

	public int getIndex() {

		return index;
	}
}
