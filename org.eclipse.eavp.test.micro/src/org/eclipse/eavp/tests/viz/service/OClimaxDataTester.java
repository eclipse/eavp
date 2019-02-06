package org.eclipse.eavp.tests.viz.service;

import static org.junit.Assert.assertTrue;

import org.eclipse.eavp.micro.iceman.oclimax.OClimaxData;
import org.junit.Test;

public class OClimaxDataTester {
	
	@Test
	public void checkName() {
		OClimaxData data = new OClimaxData();
		data.setName("test");
		assertTrue("test".equals(data.getName()));
	}

}
