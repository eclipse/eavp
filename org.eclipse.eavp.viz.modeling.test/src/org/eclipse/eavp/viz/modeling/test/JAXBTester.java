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
package org.eclipse.eavp.viz.modeling.test;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import org.eclipse.eavp.viz.modeling.base.BasicController;
import org.eclipse.eavp.viz.modeling.base.BasicMesh;
import org.eclipse.eavp.viz.modeling.base.BasicView;
import org.eclipse.eavp.viz.modeling.base.EntityMapEntry;
import org.eclipse.eavp.viz.modeling.base.PropertyMapEntry;
import org.eclipse.eavp.viz.modeling.base.Transformation;
import org.eclipse.eavp.viz.modeling.properties.MeshCategory;
import org.eclipse.eavp.viz.modeling.properties.MeshProperty;
import org.eclipse.eavp.viz.modeling.properties.MeshType;
import org.junit.Test;

/**
 * A class to test the JAXB persistence for the eavp.viz.modeling package data
 * structures.
 * 
 * @author Robert Smith
 *
 */
public class JAXBTester {

	/**
	 * Check if a modeling object can be marshaled/unmarshaled correctly.
	 */
	@Test
	public void checkJaxb() {

		// Create a basic mesh
		BasicMesh dataObject = new BasicMesh();

		// Initialize some properties and the mesh type
		dataObject.setProperty(MeshProperty.DESCRIPTION,
				"test item description");
		dataObject.setProperty(MeshProperty.ID, "0");
		dataObject.setType(MeshType.CONSTRUCTIVE);

		// Create a child entity and add it to the object under the default
		// category
		BasicMesh childMesh = new BasicMesh();
		childMesh.setProperty(MeshProperty.NAME, "first child");
		BasicController child = new BasicController(childMesh, new BasicView());
		dataObject.addEntity(child);

		// Create another child entity
		BasicMesh childMesh2 = new BasicMesh();
		childMesh2.setProperty(MeshProperty.NAME, "second child");
		BasicController child2 = new BasicController(childMesh2,
				new BasicView());
		dataObject.addEntity(child2);

		// Create an output stream for a test file
		OutputStream outputStream = null;
		try {
			outputStream = new FileOutputStream("test.xml");
		} catch (FileNotFoundException e2) {
			e2.printStackTrace();
			fail("Could not open test.xml file for writing.");
		}

		outputStream = System.out;

		// Initialize the data structures for the JAXB context
		JAXBContext jaxbContext = null;
		Class[] classArray = {};
		ArrayList<Class> classList = new ArrayList<Class>();

		// Add all neccesary classes to the list
		classList.add(BasicMesh.class);
		classList.add(MeshCategory.class);
		classList.add(MeshProperty.class);
		classList.add(BasicController.class);
		classList.add(BasicView.class);
		classList.add(EntityMapEntry.class);
		classList.add(PropertyMapEntry.class);
		classList.add(Transformation.class);

		// Create a context
		try {
			jaxbContext = JAXBContext
					.newInstance(classList.toArray(classArray));
		} catch (JAXBException e) {
			e.printStackTrace();
			fail("Failed to make new JAXBContext.");
		}

		// Marshal the objects to xml
		Marshaller marshaller;
		try {
			marshaller = jaxbContext.createMarshaller();
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT,
					Boolean.TRUE);

			// Write to file
			marshaller.marshal(dataObject, outputStream);
		} catch (JAXBException e1) {
			e1.printStackTrace();
			fail("Failed to marshal to .xml file.");
		}

		// Get an input stream for the file we just wrote
		InputStream inputStream = null;
		try {
			inputStream = new FileInputStream("test.xml");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			fail("Failed to get input stream from test.xml.");
		}

		// The mesh read from the persisted xml file
		BasicMesh restoredMesh = null;

		// Unmarshal the objects from xml
		try {
			Unmarshaller unmarshaller = null;
			unmarshaller = jaxbContext.createUnmarshaller();
			Object restored = unmarshaller.unmarshal(inputStream);

			// Cate the object as a mesh
			restoredMesh = (BasicMesh) restored;
		} catch (JAXBException e) {

			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// Check that the the restored object is equal to the original
		assertTrue(dataObject.equals(restoredMesh));

	}
}
