/*******************************************************************************
 * Copyright (c) 2016- UT-Battelle, LLC.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   Robert Smith
 *******************************************************************************/
package org.eclipse.eavp.viz.service.mesh.datastructures;

import java.util.ArrayList;
import java.util.HashMap;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlList;
import javax.xml.bind.annotation.XmlRootElement;

import org.eclipse.eavp.viz.modeling.DetailedEdgeMesh;
import org.eclipse.eavp.viz.modeling.EdgeMesh;
import org.eclipse.eavp.viz.modeling.VertexMesh;
import org.eclipse.eavp.viz.modeling.base.BasicController;
import org.eclipse.eavp.viz.modeling.base.BasicMesh;
import org.eclipse.eavp.viz.modeling.base.BasicView;
import org.eclipse.eavp.viz.modeling.base.IController;
import org.eclipse.eavp.viz.modeling.base.Transformation;
import org.eclipse.eavp.viz.modeling.factory.IControllerProvider;
import org.eclipse.eavp.viz.modeling.factory.IControllerProviderFactory;
import org.eclipse.eavp.viz.modeling.properties.MeshCategory;
import org.eclipse.eavp.viz.modeling.properties.MeshProperty;

/**
 * This class holds all the information required to represent a mesh from the
 * Mesh Editor in compact form for easy persistence.
 * 
 * @author Robert Smith
 *
 */
@XmlRootElement(name = "Mesh")
public class MeshDescription {

	/**
	 * A list of vertices by global ID
	 */
	@XmlElement
	@XmlList
	int[] vertices;

	/**
	 * A list of edges. Edges are specified in the format (global ID, global ID
	 * of vertex endpoint 1, global ID of vertex endpoint 2).
	 */
	@XmlElement
	@XmlList
	int[] edges;

	/**
	 * A list of faces. Faces are specified in the format (global ID, global ID
	 * of edge 1, global ID of edge 2, ... global ID of last edge, -1)
	 */
	@XmlElement
	@XmlList
	int[] faces;

	/**
	 * A list of the transformations in the format rotation x, rotation y,
	 * rotation z, scale x, scale y, scale z, skew x, skew y, skew z,
	 * translation x, translation y, translation z). An object's transformation
	 * will begin at location global ID * 9.
	 */
	@XmlElement
	@XmlList
	double[] transformations;

	/**
	 * A list of the EdgeProperties for the polygons. The properties for the
	 * face with global ID i will be in position i - vertices.size() -
	 * edges.size().
	 */
	@XmlElement
	ArrayList<HashMap<Integer, EdgeProperties>> properties;

	/**
	 * A constructor that creates a compacted description of a mesh.
	 * 
	 * @param root
	 *            The root of the mesh that is to be described.
	 */
	public MeshDescription(IController root) {

		// Initialize the lists
		ArrayList<IController> tempVertices = new ArrayList<IController>();
		ArrayList<IController> tempEdges = new ArrayList<IController>();
		ArrayList<IController> tempFaces = new ArrayList<IController>();

		// Recursively sort all the entities into the correct lists
		for (IController mesh : root.getEntities()) {
			sortMesh(mesh, tempVertices, tempEdges, tempFaces);
		}

		// The total number of vertices
		int numVertices = tempVertices.size();

		// The next unassigned global ID.
		int gid;

		// Populate the vertices array with integers from 0 up.
		vertices = new int[numVertices];
		for (gid = 0; gid < numVertices; gid++) {
			vertices[gid] = gid;
		}

		// Handle the edges
		edges = new int[tempEdges.size() * 3];
		for (IController edge : tempEdges) {

			// Set the global ID to the first cell in this edge's data block
			edges[gid - numVertices] = gid;

			// The offset from the start of the data block to the next vertex.
			int i = 0;

			// Get the edge's vertices
			ArrayList<IController> edgeVertices = edge
					.getEntitiesFromCategory(MeshCategory.VERTICES);

			// Check if each vertex is in the edge's endpoints.
			for (IController vertex : tempVertices) {

				// If a vertex is an endpoint, put its index into the edges
				// array.
				if (edgeVertices.contains(vertex)) {
					edges[gid - numVertices + i] = tempVertices.indexOf(vertex);
					i++;
				}
			}

			// Finished with this edge, so increment the global id
			gid++;
		}

		// Calculate the size of the faces array. Each face requires one integer
		// for its global id plus one more for each of its edges
		int facesSize = 0;
		for (IController face : tempFaces) {

			// Add space for the global id
			facesSize++;

			// Add space for an index for each edge
			for (IController edge : face
					.getEntitiesFromCategory(MeshCategory.EDGES)) {
				facesSize++;
			}
		}

		// Initialize the faces array
		faces = new int[facesSize];

		// The next index in the faces array to write to
		int faceIndex = 0;

		// Handle each face
		for (IController face : tempFaces) {

			// Record the face's global id
			faces[faceIndex] = gid;
			faceIndex++;
			gid++;

			// For each edge in the face, calculate and record its global id
			// based on its position in the array of edges.
			for (IController edge : face
					.getEntitiesFromCategory(MeshCategory.EDGES)) {
				faces[faceIndex] = tempEdges.indexOf(edge) + numVertices;
				faceIndex++;
			}

			// Add the face's edge properties to the description
			properties.add(((NekPolygonController) face).getEdgeProperties());
		}

		// Get a list of all entities
		ArrayList<IController> allEntities = new ArrayList<IController>();
		allEntities.addAll(tempVertices);
		allEntities.addAll(tempEdges);
		allEntities.addAll(tempFaces);

		// Initialize the transformations array
		transformations = new double[allEntities.size() * 12];

		// The index for the start of the current transformation's data block in
		// the array.
		int transformIndex = 0;

		// Handle each entity's transformation
		for (IController entity : allEntities) {
			Transformation transform = entity.getTransformation();

			// Get all of the transformation's data
			double[] rotation = transform.getRotation();
			double[] scale = transform.getScale();
			double size = transform.getSize();
			double[] skew = transform.getSkew();
			double[] translation = transform.getTranslation();

			// Serialize the transformation into an array of numbers. Note that
			// size will be removed, and instead factored into the scale values.
			transformations[transformIndex] = rotation[0];
			transformations[transformIndex + 1] = rotation[1];
			transformations[transformIndex + 2] = rotation[2];
			transformations[transformIndex + 3] = scale[0] * size;
			transformations[transformIndex + 4] = scale[1] * size;
			transformations[transformIndex + 5] = scale[2] * size;
			transformations[transformIndex + 6] = skew[0];
			transformations[transformIndex + 7] = skew[1];
			transformations[transformIndex + 8] = skew[2];
			transformations[transformIndex + 9] = translation[0];
			transformations[transformIndex + 10] = translation[1];
			transformations[transformIndex + 11] = translation[1];
			transformIndex = transformIndex + 12;
		}
	}

	/**
	 * Getter method for the edges.
	 * 
	 * @return An array of global ids for the edges, along with their endpoints
	 *         as indices into the vertices array
	 */
	public int[] getEdges() {
		return edges;
	}

	/**
	 * Getter method for the faces.
	 * 
	 * @return An array of global ids for the faces, along with their
	 *         constituent edges as indices into the edges array
	 */
	public int[] getFaces() {
		return faces;
	}

	/**
	 * Getter method for the properties.
	 * 
	 * @return A list of maps between integer ids for edges and their edge
	 *         properties, one for each face in the faces array
	 */
	public ArrayList<HashMap<Integer, EdgeProperties>> getProperties() {
		return properties;
	}

	/**
	 * Getter method for the transformation.
	 * 
	 * @return An array of numbers representing the spatial data for a modeling
	 *         object. Each object's data begins at location (12 * their global
	 *         ID) and is stored as three values representing the x, y, and z
	 *         directions for each of the four attributes (rotation, scale,
	 *         skew, and translation).
	 */
	public double[] getTransformation() {
		return transformations;
	}

	/**
	 * Getter method for the vertices.
	 * 
	 * @return An array of global ids for the vertices
	 */
	public int[] getVertices() {
		return vertices;
	}

	/**
	 * Setter method for the edges.
	 * 
	 * @param edges
	 *            An array of global ids for the edges, along with their
	 *            endpoints as indices into the vertices array
	 */
	public void setEdges(int[] edges) {
		this.edges = edges;
	}

	/**
	 * Setter method for the faces.
	 * 
	 * @param faces
	 *            An array of global ids for the faces, along with their
	 *            constituent edges as indices into the edges array
	 */
	public void setFaces(int[] faces) {
		this.faces = faces;
	}

	/**
	 * Setter method for the properties.
	 * 
	 * @param properties
	 *            A list of maps between integer ids for edges and their edge
	 *            properties, one for each face in the faces array
	 */
	public void setProperties(
			ArrayList<HashMap<Integer, EdgeProperties>> properties) {
		this.setProperties(properties);
	}

	/**
	 * Setter method for the transformations.
	 * 
	 * @param transformations
	 *            An array of numbers representing the spatial data for a
	 *            modeling object. Each object's data begins at location (12 *
	 *            their global ID) and is stored as three values representing
	 *            the x, y, and z directions for each of the four attributes
	 *            (rotation, scale, skew, and translation).
	 */
	public void setTransformations(double[] transformations) {
		this.transformations = transformations;
	}

	/**
	 * Setter method for the vertices.
	 * 
	 * @param vertices
	 *            An array of global ids for the vertices
	 */
	public void setVertices(int[] vertices) {
		this.vertices = vertices;
	}

	/**
	 * Sort the controller and all its children into the correct list. Once this
	 * process is complete, then each modeling part in the tree should be in one
	 * of the lists exactly once.
	 * 
	 * @param mesh
	 *            The mesh to be sorted.
	 * @param vertices
	 *            The list of vertices.
	 * @param edges
	 *            The list of edges.
	 * @param faces
	 *            The list of faces.
	 */
	private void sortMesh(IController mesh, ArrayList<IController> vertices,
			ArrayList<IController> edges, ArrayList<IController> faces) {

		// Add the mesh to the vertices list and recurse if it has not been seen
		if (mesh.getClass() == VertexMesh.class) {
			if (!vertices.contains(mesh)) {
				vertices.add(mesh);
				sortMesh(mesh, vertices, edges, faces);
			}
		}

		// Add the mesh to the edges list and recurse if it has not been seen
		else if (mesh.getClass() == EdgeMesh.class) {
			if (!edges.contains(mesh)) {
				edges.add(mesh);
				sortMesh(mesh, vertices, edges, faces);
			}
		}

		// Add the mesh to the faces list and recurse if it has not been seen
		else if (mesh.getClass() == NekPolygonMesh.class) {
			if (!faces.contains(mesh)) {
				faces.add(mesh);
				sortMesh(mesh, vertices, edges, faces);
			}
		}
	}

	/**
	 * Unpack the condensed representation into a full set of modeling data
	 * objects.
	 * 
	 * @param factory
	 *            The factory that will create the rendering engine appropriate
	 *            IControllers for the meshes.
	 * @return The root node of a data structure containing the mesh represented
	 *         by this object, in the form of a collection of IControllers,
	 *         IMeshes, and IView
	 */
	public IController unpack(IControllerProviderFactory factory) {

		// Create a root for the hierarchy
		IController root = new BasicController(new BasicMesh(),
				new BasicView());

		// Create temporary arrays to hold the unpacked vertices and edges
		ArrayList<IController> tempVertices = new ArrayList<IController>();
		ArrayList<IController> tempEdges = new ArrayList<IController>();

		// Get controller providers for the necessary classes
		IControllerProvider vertexProvider = factory
				.createProvider(new VertexMesh());
		IControllerProvider edgeProvider = factory
				.createProvider(new DetailedEdgeMesh());
		IControllerProvider faceProvider = factory
				.createProvider(new NekPolygonMesh());

		// For each id in the vertices array
		for (int i : vertices) {

			// Create a vertex with the given id
			VertexMesh mesh = new VertexMesh();
			mesh.setProperty(MeshProperty.ID, Integer.toString(i));
			IController vertex = vertexProvider.createController(mesh);

			// Create the transformation and apply it
			Transformation transform = new Transformation();
			transform.setRotation(transformations[i * 9],
					transformations[i * 9 + 1], transformations[i * 9 + 2]);
			transform.setScale(transformations[i * 9 + 3],
					transformations[i * 9 + 4], transformations[i * 9 + 5]);
			transform.setSkew(transformations[i * 9 + 6],
					transformations[i * 9 + 7], transformations[i * 9 + 8]);
			transform.setTranslation(transformations[i * 9 + 9],
					transformations[i * 9 + 10], transformations[i * 9 + 11]);
			vertex.setTransformation(transform);

			// Add the vertex to the list
			tempVertices.add(vertex);
		}

		// Handle each of the edges
		for (int i = 0; i < edges.length; i = i + 3) {

			//
			DetailedEdgeMesh mesh = new DetailedEdgeMesh();
			mesh.setProperty(MeshProperty.ID, Integer.toString(edges[i]));
			IController edge = edgeProvider.createController(mesh);

			edge.addEntityToCategory(tempVertices.get(edges[i + 1]),
					MeshCategory.VERTICES);
			edge.addEntityToCategory(tempVertices.get(edges[i + 2]),
					MeshCategory.VERTICES);

			int edgeIndex = i + vertices.length;

			Transformation transform = new Transformation();
			transform.setRotation(transformations[edgeIndex * 9],
					transformations[edgeIndex * 9 + 1],
					transformations[edgeIndex * 9 + 2]);
			transform.setScale(transformations[edgeIndex * 9 + 3],
					transformations[edgeIndex * 9 + 4],
					transformations[edgeIndex * 9 + 5]);
			transform.setSkew(transformations[edgeIndex * 9 + 6],
					transformations[edgeIndex * 9 + 7],
					transformations[edgeIndex * 9 + 8]);
			transform.setTranslation(transformations[edgeIndex * 9 + 9],
					transformations[edgeIndex * 9 + 10],
					transformations[edgeIndex * 9 + 11]);

			edge.setTransformation(transform);

			tempEdges.add(edge);
		}

		boolean newPolygon = true;
		IController face = null;

		for (int i = 0; i < faces.length; i++) {

			int id = faces[i];

			if (id == -1) {
				newPolygon = true;
				continue;
			}

			if (newPolygon) {
				NekPolygonMesh mesh = new NekPolygonMesh();
				mesh.setProperty(MeshProperty.ID, Integer.toString(faces[id]));
				face = faceProvider.createController(mesh);

				Transformation transform = new Transformation();
				transform.setRotation(transformations[id * 9],
						transformations[id * 9 + 1],
						transformations[id * 9 + 2]);
				transform.setScale(transformations[id * 9 + 3],
						transformations[id * 9 + 4],
						transformations[id * 9 + 5]);
				transform.setSkew(transformations[id * 9 + 6],
						transformations[id * 9 + 7],
						transformations[id * 9 + 8]);
				transform.setTranslation(transformations[id * 9 + 9],
						transformations[id * 9 + 10],
						transformations[id * 9 + 11]);

				face.setTransformation(transform);

				root.addEntity(face);
			}

			else {
				for (IController edge : tempEdges) {
					if (id == Integer
							.parseInt(edge.getProperty(MeshProperty.ID))) {
						face.addEntityToCategory(edge, MeshCategory.EDGES);
					}
				}
			}

		}

		return root;

	}
}
