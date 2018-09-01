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

import org.eclipse.eavp.service.swtchart.axisconverter.PassThroughConverter;

import junit.framework.TestCase;

public class PassThroughConverter_1_Test extends TestCase {

	private PassThroughConverter passThroughConverter;

	@Override
	protected void setUp() throws Exception {

		super.setUp();
		passThroughConverter = new PassThroughConverter();
	}

	@Override
	protected void tearDown() throws Exception {

		super.tearDown();
	}

	public void test1() {

		assertEquals(-1.0d, passThroughConverter.convertToSecondaryUnit(-1.0d));
	}

	public void test2() {

		assertEquals(0.0d, passThroughConverter.convertToSecondaryUnit(0.0d));
	}

	public void test3() {

		assertEquals(1.0d, passThroughConverter.convertToSecondaryUnit(1.0d));
	}
}
