/*******************************************************************************
 * Copyright (c) 2016 UT-Battelle, LLC.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   Robert Smith
 *******************************************************************************/
package model.tests;

import model.impl.RenderObjectImpl;

/**
 * A simple RenderObject for testing purposes.
 *
 * @generated NOT
 * 
 * @author Robert Smith
 *
 */
public class TestRenderObject extends RenderObjectImpl<String> {

	/**
	 * The default constructor.
	 */
	public TestRenderObject() {

		// Set the object to use the test cache
		meshCache = new TestCache();
	}
}
