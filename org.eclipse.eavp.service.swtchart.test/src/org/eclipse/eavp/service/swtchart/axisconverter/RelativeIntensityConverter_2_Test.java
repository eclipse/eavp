/*******************************************************************************
 * Copyright (c) 2017, 2018 Lablicate GmbH.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 * Philip Wenig - initial API and implementation
 *******************************************************************************/
package org.eclipse.eavp.service.swtchart.axisconverter;

import org.eclipse.eavp.service.swtchart.core.IChartDataCoordinates;
import org.eclipse.swt.SWT;

import junit.framework.TestCase;

public class RelativeIntensityConverter_2_Test extends TestCase {

	private RelativeIntensityConverter relativeIntensityConverter;

	private class TestChartDataCoordinates implements IChartDataCoordinates {

		@Override
		public int getSeriesMaxDataPoints() {

			return 0;
		}

		@Override
		public double getMinX() {

			return 0;
		}

		@Override
		public double getMaxX() {

			return 5000.0d;
		}

		@Override
		public double getMinY() {

			return 0;
		}

		@Override
		public double getMaxY() {

			return 0;
		}
	}

	@Override
	protected void setUp() throws Exception {

		super.setUp();
		relativeIntensityConverter = new RelativeIntensityConverter(SWT.HORIZONTAL, true);
		relativeIntensityConverter.setChartDataCoordinates(new TestChartDataCoordinates());
	}

	@Override
	protected void tearDown() throws Exception {

		super.tearDown();
	}

	public void test1() {

		assertEquals(0.0d, relativeIntensityConverter.convertToSecondaryUnit(0.0d));
	}

	public void test2() {

		assertEquals(100.0d, relativeIntensityConverter.convertToSecondaryUnit(5000.0d));
	}

	public void test3() {

		assertEquals(0.0d, relativeIntensityConverter.convertToPrimaryUnit(0.0d));
	}

	public void test4() {

		assertEquals(5000.0d, relativeIntensityConverter.convertToPrimaryUnit(100.0d));
	}
}
