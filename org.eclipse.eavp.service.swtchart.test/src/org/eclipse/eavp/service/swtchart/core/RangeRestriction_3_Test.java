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

public class RangeRestriction_3_Test extends TestCase {

	private RangeRestriction rangeRestriction;

	@Override
	protected void setUp() throws Exception {

		super.setUp();
		rangeRestriction = new RangeRestriction(RangeRestriction.ZERO_X);
	}

	@Override
	protected void tearDown() throws Exception {

		super.tearDown();
	}

	public void test1() {

		assertTrue(rangeRestriction.isZeroX());
	}

	public void test2() {

		assertFalse(rangeRestriction.isZeroY());
	}

	public void test3() {

		assertFalse(rangeRestriction.isRestrictZoom());
	}
}
