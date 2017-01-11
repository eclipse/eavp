/*******************************************************************************
 * Copyright (c) 2015 UT-Battelle, LLC.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   Robert Smith
 *******************************************************************************/
package org.eclipse.eavp.tests.viz.modeling;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.eavp.tests.viz.modeling.utils.TestController;
import org.eclipse.eavp.tests.viz.modeling.utils.TestMesh;
import org.eclipse.eavp.tests.viz.modeling.utils.TestView;
import org.eclipse.eavp.viz.modeling.base.BasicView;
import org.eclipse.eavp.viz.modeling.base.IController;
import org.eclipse.eavp.viz.modeling.base.IView;
import org.eclipse.eavp.viz.modeling.base.Representation;
import org.eclipse.eavp.viz.modeling.base.Transformation;
import org.junit.Before;
import org.junit.Test;

/**
 * A class for testing the functionality of the AbstractView.
 * 
 * @author Robert Smith
 *
 */
public class BasicViewTester {

	/**
	 * The entities for the component
	 */
	List<IController> entities;

	/**
	 * The controller's model
	 */
	TestMesh component;

	/**
	 * The controller's view
	 */
	TestView view;

	/**
	 * The controller to test
	 */
	TestController controller;

	/**
	 * Initialize the controller before each test.
	 */
	@Before
	public void beforeEachTest() {
		entities = new ArrayList<IController>();
		component = new TestMesh(entities);
		view = new TestView();
		controller = new TestController(component, view);
	}

	/**
	 * Test the view's ability to notify observers of changes.
	 */
	@Test
	public void testUpdateNotification() {

		//Register the view to listen to a test object
		Transformation source = new Transformation();
		source.register(view);
		
		// Update the transformation
		source.setSize(2);

		// Wait for the notification thread
		try {
			Thread.sleep(50);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		// Check that the view was notified
		assertTrue(view.isUpdated());

	}

	/**
	 * Check the AbstractView's equality testing.
	 */
	@Test
	public void checkEquality() {

		// Create objects for testing
		IView object = new TestRepresentationView(0);
		IView equalObject = new TestRepresentationView(0);
		IView unequalObject = new TestRepresentationView(1);

		// An object should equal itself
		assertTrue(object.equals(object));

		// Check that a comparison of two equal objects returns true
		assertTrue(object.equals(equalObject));
		assertTrue(equalObject.equals(object));

		// Check that a comparison of two unequal objects returns false
		assertFalse(object.equals(unequalObject));
	}
	
	/**
	 * An extension of BasicView that allows the setting of a simple Representation to the view for testing purposes.
	 * 
	 * @author Robert Smith
	 *
	 */
	private class TestRepresentationView extends BasicView{
		
		/**
		 * The data to return for this view's representation
		 */
		int data;
		
		/**
		 * The default constructor.
		 * 
		 * @param i The number to return as this view's representation
		 */
		public TestRepresentationView(Integer i){
			super();
			data = i;
		}
		
		/*
		 * (non-Javadoc)
		 * @see org.eclipse.eavp.viz.modeling.base.BasicView#getRepresentation()
		 */
		public Representation<Integer> getRepresentation(){
			
			//Create a new representation for the data
			return new Representation<Integer>(data);
		}
	}
}