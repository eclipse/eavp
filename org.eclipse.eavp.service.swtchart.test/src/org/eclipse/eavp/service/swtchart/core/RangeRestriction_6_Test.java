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

public class RangeRestriction_6_Test extends TestCase {

	private RangeRestriction rangeRestriction;

	@Override
	protected void setUp() throws Exception {

		super.setUp();
		rangeRestriction = new RangeRestriction(RangeRestriction.NONE);
	}

	@Override
	protected void tearDown() throws Exception {

		super.tearDown();
	}

	public void test1() {

		rangeRestriction.setZeroX(true);
		rangeRestriction.setZeroY(true);
		rangeRestriction.setRestrictZoom(true);
		assertTrue(rangeRestriction.isZeroX());
		assertTrue(rangeRestriction.isZeroY());
		assertTrue(rangeRestriction.isRestrictZoom());
	}

	public void test2() {

		rangeRestriction.setZeroX(true);
		rangeRestriction.setRestrictZoom(true);
		assertTrue(rangeRestriction.isZeroX());
		assertFalse(rangeRestriction.isZeroY());
		assertTrue(rangeRestriction.isRestrictZoom());
	}

	public void test3() {

		rangeRestriction.setZeroY(true);
		rangeRestriction.setRestrictZoom(true);
		assertFalse(rangeRestriction.isZeroX());
		assertTrue(rangeRestriction.isZeroY());
		assertTrue(rangeRestriction.isRestrictZoom());
	}

	public void test4() {

		rangeRestriction.setZeroX(true);
		rangeRestriction.setZeroY(true);
		assertTrue(rangeRestriction.isZeroX());
		assertTrue(rangeRestriction.isZeroY());
		assertFalse(rangeRestriction.isRestrictZoom());
	}
}
