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
package org.eclipse.eavp.viz.service.mesh.datastructures.test;

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

import org.eclipse.eavp.viz.modeling.DetailedEdgeController;
import org.eclipse.eavp.viz.modeling.DetailedEdgeMesh;
import org.eclipse.eavp.viz.modeling.FaceMesh;
import org.eclipse.eavp.viz.modeling.VertexController;
import org.eclipse.eavp.viz.modeling.VertexMesh;
import org.eclipse.eavp.viz.modeling.base.BasicController;
import org.eclipse.eavp.viz.modeling.base.BasicMesh;
import org.eclipse.eavp.viz.modeling.base.BasicView;
import org.eclipse.eavp.viz.modeling.base.EntityMapEntry;
import org.eclipse.eavp.viz.modeling.base.IController;
import org.eclipse.eavp.viz.modeling.base.IMesh;
import org.eclipse.eavp.viz.modeling.base.PropertyMapEntry;
import org.eclipse.eavp.viz.modeling.base.Transformation;
import org.eclipse.eavp.viz.modeling.factory.BasicControllerProviderFactory;
import org.eclipse.eavp.viz.modeling.factory.IControllerProvider;
import org.eclipse.eavp.viz.modeling.properties.MeshCategory;
import org.eclipse.eavp.viz.modeling.properties.MeshProperty;
import org.eclipse.eavp.viz.service.mesh.datastructures.MeshDescription;
import org.eclipse.eavp.viz.service.mesh.datastructures.NekPolygonController;
import org.eclipse.eavp.viz.service.mesh.datastructures.NekPolygonMesh;
import org.junit.Test;

/**
 * A class to test the functionality of the MeshDescription.
 * 
 * @author Robert Smith
 *
 */
public class MeshDescriptionTester {

	/**
	 * Test that a hierarchy of object can be placed into and correctly
	 * retrieved from a MeshDescription.
	 */
	@Test
	public void checkPackaging() {

		// The root object for the hierarchy
		BasicController root = new BasicController(new BasicMesh(),
				new BasicView());

		// Create the vertices. In space they are laid out thusly:
		// 1 2 5
		// 4 3 6
		VertexController vertex1 = new VertexController(new VertexMesh(),
				new BasicView());
		vertex1.setTranslation(0, 0, 0);
		VertexController vertex2 = new VertexController(new VertexMesh(),
				new BasicView());
		vertex2.setTranslation(1, 0, 0);
		VertexController vertex3 = new VertexController(new VertexMesh(),
				new BasicView());
		vertex3.setTranslation(1, 1, 0);
		VertexController vertex4 = new VertexController(new VertexMesh(),
				new BasicView());
		vertex4.setTranslation(0, 1, 0);
		VertexController vertex5 = new VertexController(new VertexMesh(),
				new BasicView());
		vertex5.setTranslation(2, 0, 0);
		VertexController vertex6 = new VertexController(new VertexMesh(),
				new BasicView());
		vertex6.setTranslation(2, 1, 0);

		// Create the edges. In space they are laid out thusly:
		// -1- -5-
		// | | |
		// 4 2 6
		// | | |
		// -3- -7-
		DetailedEdgeController edge1 = new DetailedEdgeController(
				new DetailedEdgeMesh(vertex1, vertex2), new BasicView());
		edge1.setProperty(MeshProperty.ID, "1");
		DetailedEdgeController edge2 = new DetailedEdgeController(
				new DetailedEdgeMesh(vertex2, vertex3), new BasicView());
		edge2.setProperty(MeshProperty.ID, "2");
		DetailedEdgeController edge3 = new DetailedEdgeController(
				new DetailedEdgeMesh(vertex3, vertex4), new BasicView());
		edge3.setProperty(MeshProperty.ID, "3");
		DetailedEdgeController edge4 = new DetailedEdgeController(
				new DetailedEdgeMesh(vertex4, vertex1), new BasicView());
		edge4.setProperty(MeshProperty.ID, "4");
		DetailedEdgeController edge5 = new DetailedEdgeController(
				new DetailedEdgeMesh(vertex2, vertex5), new BasicView());
		edge5.setProperty(MeshProperty.ID, "5");
		DetailedEdgeController edge6 = new DetailedEdgeController(
				new DetailedEdgeMesh(vertex5, vertex6), new BasicView());
		edge6.setProperty(MeshProperty.ID, "6");
		DetailedEdgeController edge7 = new DetailedEdgeController(
				new DetailedEdgeMesh(vertex6, vertex3), new BasicView());
		edge7.setProperty(MeshProperty.ID, "7");

		// Create the faces. Face 1 on the left, 2 on the right.
		NekPolygonController face1 = new NekPolygonController(
				new NekPolygonMesh(), new BasicView());
		face1.addEntityToCategory(edge1, MeshCategory.EDGES);
		face1.addEntityToCategory(edge2, MeshCategory.EDGES);
		face1.addEntityToCategory(edge3, MeshCategory.EDGES);
		face1.addEntityToCategory(edge4, MeshCategory.EDGES);
		NekPolygonController face2 = new NekPolygonController(
				new NekPolygonMesh(), new BasicView());
		face2.addEntityToCategory(edge2, MeshCategory.EDGES);
		face2.addEntityToCategory(edge5, MeshCategory.EDGES);
		face2.addEntityToCategory(edge6, MeshCategory.EDGES);
		face2.addEntityToCategory(edge7, MeshCategory.EDGES);

		// Create a mesh description of the hierarchy
		MeshDescription desc = new MeshDescription(root);

		// Retrieve the data structures from the description
		IController unpacked = desc.unpack(new FakeControllerProviderFactory());

		// Check that the input and output are identical.
		assertTrue(root.equals(unpacked));

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
			marshaller.marshal(desc, outputStream);
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
		MeshDescription restoredMesh = null;

		// Unmarshal the objects from xml
		try {
			Unmarshaller unmarshaller = null;
			unmarshaller = jaxbContext.createUnmarshaller();
			Object restored = unmarshaller.unmarshal(inputStream);

			// Cate the object as a mesh
			restoredMesh = (MeshDescription) restored;
		} catch (JAXBException e) {

			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// Check that the the restored object is equal to the original
		assertTrue(desc.equals(
				restoredMesh.unpack(new FakeControllerProviderFactory())));
	}

	/**
	 * An IControllerProviderFactory that will create providers with simple base
	 * classes for testing purpostes.
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
			typeMap.put(DetailedEdgeMesh.class,
					new IControllerProvider<DetailedEdgeController>() {
						@Override
						public DetailedEdgeController createController(
								IMesh model) {

							// If the model is an edge component, create an edge
							// with a basic view
							BasicView view = new BasicView();
							return new DetailedEdgeController(
									(DetailedEdgeMesh) model, view);
						}
					});

			// Set the NekPolygonMesh provider
			typeMap.put(NekPolygonMesh.class,
					new IControllerProvider<NekPolygonController>() {
						@Override
						public NekPolygonController createController(
								IMesh model) {

							// Create a NekPolygonController with a basic view
							BasicView view = new BasicView();
							return new NekPolygonController((FaceMesh) model,
									view);
						}
					});

			// Set the VertexMesh provider
			typeMap.put(VertexMesh.class,
					new IControllerProvider<VertexController>() {
						@Override
						public VertexController createController(IMesh model) {

							// Create a vertex controller
							BasicView view = new BasicView();
							return new VertexController((VertexMesh) model,
									view);
						}
					});
		}
	}

}
