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
package org.eclipse.eavp.viz.service.geometry.test.persistence;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import org.eclipse.eavp.viz.modeling.ShapeController;
import org.eclipse.eavp.viz.modeling.ShapeMesh;
import org.eclipse.eavp.viz.modeling.base.BasicView;
import org.eclipse.eavp.viz.modeling.base.IMesh;
import org.eclipse.eavp.viz.modeling.factory.BasicControllerProviderFactory;
import org.eclipse.eavp.viz.modeling.factory.IControllerProvider;
import org.eclipse.eavp.viz.modeling.properties.MeshCategory;
import org.eclipse.eavp.viz.modeling.properties.MeshProperty;
import org.eclipse.eavp.viz.service.geometry.persistence.PersistableCube;
import org.eclipse.eavp.viz.service.geometry.persistence.PersistableCylinder;
import org.eclipse.eavp.viz.service.geometry.persistence.PersistableShape;
import org.eclipse.eavp.viz.service.geometry.persistence.PersistableSphere;
import org.eclipse.eavp.viz.service.geometry.persistence.PersistableTube;
import org.eclipse.eavp.viz.service.geometry.persistence.PersistableUnion;
import org.eclipse.eavp.viz.service.geometry.shapes.GeometryMeshProperty;
import org.junit.Test;

/**
 * A class to test the functionality of the PersistableShape class.
 * 
 * @author Robert Smith
 *
 */
public class PersistableShapeTester {

	/**
	 * Test that a hierarchy of ShapeControllers representing a geometry can be
	 * compressed into PersistableShapes and restored to their original state.
	 */
	@Test
	public void checkPackaging() {

		// Create the geometry
		ShapeController root = createGeometry();

		// Create a mesh description of the hierarchy
		PersistableShape compressed = PersistableShape
				.compress((ShapeMesh) root.getModel());

		// Retrieve the data structures from the description
		ShapeController unpacked = compressed
				.unpack(new FakeControllerProviderFactory());

		// Check that the input and output are identical.
		assertTrue(root.equals(unpacked));
	}

	/**
	 * Test that a hierarchy of PersistableShapes can be persisted to xml with
	 * JAXB and unmarshaled with no changes in data.
	 */
	@Test
	public void checkPersistence() {

		// Create the geometry
		ShapeController root = createGeometry();

		// Create a mesh description of the hierarchy
		PersistableShape compressed = PersistableShape
				.compress((ShapeMesh) root.getModel());

		// Create an output stream for a test file
		OutputStream outputStream = null;
		try {
			outputStream = new FileOutputStream("test.xml");
		} catch (FileNotFoundException e2) {
			e2.printStackTrace();
			fail("Could not open test.xml file for writing.");
		}

		// Initialize the data structures for the JAXB context
		JAXBContext jaxbContext = null;
		Class[] classArray = {};
		ArrayList<Class> classList = new ArrayList<Class>();

		// Add all neccesary classes to the list
		classList.add(PersistableCube.class);
		classList.add(PersistableCylinder.class);
		classList.add(PersistableShape.class);
		classList.add(PersistableSphere.class);
		classList.add(PersistableTube.class);
		classList.add(PersistableUnion.class);

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
			marshaller.marshal(compressed, outputStream);
			outputStream.close();
		} catch (JAXBException | IOException e1) {
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
		PersistableShape restoredShape = null;

		// Unmarshal the objects from xml
		try {
			Unmarshaller unmarshaller = null;
			unmarshaller = jaxbContext.createUnmarshaller();
			Object restored = unmarshaller.unmarshal(inputStream);

			// Cast the object as a mesh
			restoredShape = (PersistableShape) restored;
		} catch (JAXBException e) {
			e.printStackTrace();
			fail("Error while unmarshaling from .xml file");
		}

		// Retrieve the data structures from the description
		ShapeController unpacked = compressed
				.unpack(new FakeControllerProviderFactory());

		// Check that the input and output are identical.
		assertTrue(root.equals(unpacked));

	}

	/**
	 * Create a geometry for testing purposes.
	 * 
	 * @return An example tree of ShapeControllers
	 */
	public ShapeController createGeometry() {

		// The root of the new hierarchy
		ShapeController root = new ShapeController(new ShapeMesh(),
				new BasicView());

		// Create a cube
		ShapeController cube = new ShapeController(new ShapeMesh(),
				new BasicView());
		cube.setProperty(MeshProperty.NAME, "Cube");
		cube.setProperty(MeshProperty.ID, "1");
		cube.setProperty(MeshProperty.TYPE, "Cube");

		// Create a second cube with a new ID number
		ShapeController cube2 = new ShapeController(new ShapeMesh(),
				new BasicView());
		cube2.setProperty(MeshProperty.NAME, "Cube");
		cube2.setProperty(MeshProperty.ID, "2");
		cube2.setProperty(MeshProperty.TYPE, "Cube");

		// Create a cylinder
		ShapeController cylinder = new ShapeController(new ShapeMesh(),
				new BasicView());
		cylinder.setProperty(MeshProperty.NAME, "Cylinder");
		cylinder.setProperty(MeshProperty.ID, "1");
		cylinder.setProperty(MeshProperty.TYPE, "Cylinder");

		// Create a second cylinder with the same ID number, as if were a copy
		// of the first
		ShapeController cylinder2 = new ShapeController(new ShapeMesh(),
				new BasicView());
		cylinder2.setProperty(MeshProperty.NAME, "Cylinder");
		cylinder2.setProperty(MeshProperty.ID, "1");
		cylinder2.setProperty(MeshProperty.TYPE, "Cylinder");
		cylinder2.setRotation(1.0, 2.0, 3.0);

		// Create a union
		ShapeController union = new ShapeController(new ShapeMesh(),
				new BasicView());
		union.setProperty(MeshProperty.NAME, "Union");
		union.setProperty(MeshProperty.ID, "2");
		union.setProperty(MeshProperty.TYPE, "None");
		union.setProperty(GeometryMeshProperty.OPERATOR, "Union");
		union.setTranslation(4.0, 5.0, 6.0);

		// Create a sphere with an ID number missing from those assigned to
		// spheres, as though a previously created sphere had been deleted
		ShapeController sphere = new ShapeController(new ShapeMesh(),
				new BasicView());
		sphere.setProperty(MeshProperty.NAME, "Sphere");
		sphere.setProperty(MeshProperty.ID, "2");
		sphere.setProperty(MeshProperty.TYPE, "Sphere");

		// Create a union named Replication, as though created by the replicate
		// function
		ShapeController union2 = new ShapeController(new ShapeMesh(),
				new BasicView());
		union2.setProperty(MeshProperty.NAME, "Replication");
		union2.setProperty(MeshProperty.ID, "1");
		union2.setProperty(MeshProperty.TYPE, "None");
		union2.setProperty(GeometryMeshProperty.OPERATOR, "Union");

		// Create a tube
		ShapeController tube = new ShapeController(new ShapeMesh(),
				new BasicView());
		tube.setProperty(MeshProperty.NAME, "Tube");
		tube.setProperty(MeshProperty.ID, "1");
		tube.setProperty(MeshProperty.TYPE, "Tube");

		// Create a second tube
		ShapeController tube2 = new ShapeController(new ShapeMesh(),
				new BasicView());
		tube2.setProperty(MeshProperty.NAME, "Tube");
		tube2.setProperty(MeshProperty.ID, "2");
		tube2.setProperty(MeshProperty.TYPE, "Tube");

		// Structure the unions by having union as the parent to sphere and
		// union2, which is itself parent to tube and tube2
		union2.addEntityToCategory(tube, MeshCategory.CHILDREN);
		union2.addEntityToCategory(tube2, MeshCategory.CHILDREN);
		union.addEntityToCategory(union2, MeshCategory.CHILDREN);
		union.addEntityToCategory(sphere, MeshCategory.CHILDREN);

		// Add all the top level shapes to the root
		root.addEntityToCategory(cube, MeshCategory.CHILDREN);
		root.addEntityToCategory(cube2, MeshCategory.CHILDREN);
		root.addEntityToCategory(cylinder, MeshCategory.CHILDREN);
		root.addEntityToCategory(cylinder2, MeshCategory.CHILDREN);
		root.addEntityToCategory(union, MeshCategory.CHILDREN);

		return root;
	}

	/**
	 * An IControllerProviderFactory that will create providers with simple base
	 * classes for testing purposes.
	 * 
	 * @author Robert Smith
	 *
	 */
	private class FakeControllerProviderFactory
			extends BasicControllerProviderFactory {

		/**
		 * The default constructor
		 */
		public FakeControllerProviderFactory() {
			super();

			// Set the EdgeMesh provider
			typeMap.put(ShapeMesh.class,
					new IControllerProvider<ShapeController>() {
						@Override
						public ShapeController createController(IMesh model) {

							// If the model is an edge component, create an edge
							// with a basic view
							BasicView view = new BasicView();
							return new ShapeController((ShapeMesh) model, view);
						}
					});

		}
	}
}
