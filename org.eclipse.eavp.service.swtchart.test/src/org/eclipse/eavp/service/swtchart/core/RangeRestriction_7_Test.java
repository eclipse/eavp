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
package org.eclipse.eavp.service.swtchart.core;

import junit.framework.TestCase;

public class RangeRestriction_7_Test extends TestCase {

	private RangeRestriction rangeRestriction;

	@Override
	protected void setUp() throws Exception {

		super.setUp();
	}

	@Override
	protected void tearDown() throws Exception {

		super.tearDown();
	}

	public void test1() {

		rangeRestriction = new RangeRestriction(RangeRestriction.ZERO_X | RangeRestriction.ZERO_Y | RangeRestriction.RESTRICT_ZOOM);
		assertTrue(rangeRestriction.isZeroX());
		assertTrue(rangeRestriction.isZeroY());
		assertTrue(rangeRestriction.isRestrictZoom());
	}

	public void test2() {

		rangeRestriction = new RangeRestriction(RangeRestriction.ZERO_X | RangeRestriction.RESTRICT_ZOOM);
		assertTrue(rangeRestriction.isZeroX());
		assertFalse(rangeRestriction.isZeroY());
		assertTrue(rangeRestriction.isRestrictZoom());
	}

	public void test3() {

		rangeRestriction = new RangeRestriction(RangeRestriction.ZERO_Y | RangeRestriction.RESTRICT_ZOOM);
		assertFalse(rangeRestriction.isZeroX());
		assertTrue(rangeRestriction.isZeroY());
		assertTrue(rangeRestriction.isRestrictZoom());
	}

	public void test4() {

		rangeRestriction = new RangeRestriction(RangeRestriction.ZERO_X | RangeRestriction.ZERO_Y);
		assertTrue(rangeRestriction.isZeroX());
		assertTrue(rangeRestriction.isZeroY());
		assertFalse(rangeRestriction.isRestrictZoom());
	}
}
