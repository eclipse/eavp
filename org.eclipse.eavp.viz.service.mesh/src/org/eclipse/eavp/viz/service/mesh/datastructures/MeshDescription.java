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
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.adapters.XmlAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import org.eclipse.eavp.viz.modeling.DetailedEdgeMesh;
import org.eclipse.eavp.viz.modeling.VertexController;
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
 * This class expects its input to be NekPolygonControllers with
 * DetailedEdgeController and VertexController children. No two faces can share
 * the same MeshProperty.ID value, nor can any two vertices.
 * 
 * Not all properties are preserved between the tree fed to the MeshDescription
 * as input and the unpacked output. Transformation information is removed
 * except for the translations of the vertices, as is all property information
 * except ID numbers. The ID MeshProperty for edges may also be changed by the
 * process.
 * 
 * The XML output from a persisted MeshDescription will be in the following
 * format:
 * 
 * <Mesh> <edgeProperties> EdgeID BoundaryConditionType BoundaryConditionValue1
 * ... BoundaryConditionValue5 , BoundaryConditionType ... ; EdgeID ... EdgeID
 * BoundaryConditionType BoundaryConditionValue1 ... BoundaryConditionValue5 ,
 * BoundaryConditionType ... ; EdgeID ... ... </edgeProperties> <edges> EdgeID
 * EdgeID ... </edges> <faces> FaceID EdgeID EdgeID EdgeID FaceID EdgeID EdgeID
 * EdgeID ... </faces> <polygonProperties> GroupNumber MaterialID
 * 
 * GroupNumber MaterialID GroupNumber MaterialID ... </polygonProperties>
 * <transformations> CoordinateX CoordinateY CoordinateZ CoordinateX CoordinateY
 * CoordinateZ ... </transformations> <vertices>VertexID VertexID
 * ...</vertices> </Mesh>
 * 
 * The edgeProperties block is formatted with each polygon's <edgeProperties> on
 * its own line. Each edge's properties on a line are separated by semicolons.
 * Each edge is given an ID followed by two BoundaryConditions separated by
 * commas. A BoundaryCondition is specified by a string indicating a
 * BoundaryConditionType followed by five floating point numbers specifying its
 * values. If the BoundaryConditionType is left blank in the XML, it will count
 * as BoundaryConditionType.None. If the values are left blank, this will
 * signify that the values are all 0.
 * 
 * An Edge's ID will be set such that
 * 
 * Floor(EdgeID / (Highest VertexID + 1)) = The first endpoint's ID
 * 
 * EdgeID % (Highest VertexID + 1) = The second endpoint's ID
 * 
 * For non-directed meshes, an Edge will have the lower of its two possible IDs.
 * 
 * Each face will be specified on its own line in the <faces> block. The line
 * will start with the face's ID, the same one specified in the original data
 * structure, and will be followed by some number of edge IDs which specify
 * which edges are included in the polygon.
 * 
 * Each face will have a corresponding line in the <polygonProperties> block,
 * specifying its GroupNumber and MeaterialID. If the line is blank, this
 * signifies the default values of "nul1" and "0". respectively.
 * 
 * Each vertex will have a corresponding line in the transformations block,
 * giving its X, Y, and Z coordinates.
 * 
 * The <vertices> block will simply contain all the IDs for the vertices.
 * 
 * The intended use for this class is for a hierarchy of objects for a Mesh
 * Editor, collected under a single root node represented by a BasicController,
 * to be persisted in JAXB through the use of a getter function for a virtual
 * field or an XMLAdapter, which will be responsible for creating the
 * MeshDescription from the data hierarchy and for restoring a data hierarchy
 * given a valid MeshDescription from JAXB.
 * 
 * @author Robert Smith
 *
 */
@XmlRootElement(name = "Mesh")
public class MeshDescription {

	/**
	 * A list of vertices by global ID
	 */
	int[] vertices;

	/**
	 * A list of edges. Edges are specified in the format (global ID, global ID
	 * of vertex endpoint 1, global ID of vertex endpoint 2).
	 */
	int[] edges;

	/**
	 * A list of faces. Faces are specified in the format (global ID, global ID
	 * of edge 1, global ID of edge 2, ... global ID of last edge, -1)
	 */
	int[] faces;

	/**
	 * A list of the transformations in the format rotation x, rotation y,
	 * rotation z, scale x, scale y, scale z, skew x, skew y, skew z,
	 * translation x, translation y, translation z). An object's transformation
	 * will begin at location global ID * 9.
	 */
	double[][] transformations;

	/**
	 * A list of the EdgeProperties for the polygons. The properties for the
	 * face with index i in the faces array will have its properties stored in
	 * index i of this array.
	 */
	ArrayList<HashMap<Integer, EdgeProperties>> edgeProperties;

	/**
	 * A list of the PolygonProperties for the polygons. The proper
	 */
	ArrayList<PolygonProperties> polygonProperties;

	/**
	 * The default constructor.
	 */
	public MeshDescription() {

		// Initialize the data members
		vertices = null;
		edges = null;
		faces = null;
		transformations = null;
		edgeProperties = new ArrayList<HashMap<Integer, EdgeProperties>>();
		polygonProperties = new ArrayList<PolygonProperties>();
	}

	/**
	 * A constructor that creates a compacted description of a mesh.
	 * 
	 * @param root
	 *            The root of the mesh that is to be described.
	 */
	public MeshDescription(IController root) {
		this();

		// Initialize the lists
		ArrayList<VertexController> tempVertices = new ArrayList<VertexController>();
		ArrayList<IController> tempEdges = new ArrayList<IController>();
		ArrayList<NekPolygonController> tempFaces = new ArrayList<NekPolygonController>();

		// Recursively sort all the entities into the correct lists
		for (IController mesh : root.getEntities()) {
			sortMesh(mesh, tempVertices, tempEdges, tempFaces);
		}

		// The total number of vertices
		int numVertices = tempVertices.size();

		// The largest vertex id seen
		int maxVertex = 0;

		// Populate the vertices array with the vertices' IDs
		vertices = new int[numVertices];
		for (int i = 0; i < numVertices; i++) {
			vertices[i] = Integer
					.parseInt(tempVertices.get(i).getProperty(MeshProperty.ID));

			// Keep track of the largest ID
			if (vertices[i] > maxVertex) {
				maxVertex = vertices[i];
			}
		}

		// The row size is one larger than seen, since IDs can start at 0
		maxVertex++;

		// Initialize the array of edges
		edges = new int[tempEdges.size()];

		// The next unused index in the edges array
		int edgeIndex = 0;

		// A map from an edge's original ID to its new ID.
		HashMap<Integer, Integer> edgeIDMap = new HashMap<Integer, Integer>();

		// Handle the edges
		for (int i = 0; i < tempEdges.size(); i++) {

			// Get the edge's vertices
			ArrayList<IController> edgeVertices = tempEdges.get(i)
					.getEntitiesFromCategory(MeshCategory.VERTICES);

			// Get the endpoints' IDs
			int endpoint1 = Integer
					.parseInt(edgeVertices.get(0).getProperty(MeshProperty.ID));
			int endpoint2 = Integer
					.parseInt(edgeVertices.get(1).getProperty(MeshProperty.ID));
			
			//Sort the endpoints
			if(endpoint2 < endpoint1){
				int temp = endpoint1;
				endpoint1 = endpoint2;
				endpoint2 = temp;
			}

			// Calculate the edge's ID and add it to the array
			edges[edgeIndex] = endpoint1 * maxVertex + endpoint2;

			// Put the new ID in the map
			edgeIDMap.put(
					Integer.valueOf(
							tempEdges.get(i).getProperty(MeshProperty.ID)),
					edges[edgeIndex]);
			edgeIndex++;
		}

		// Calculate the size of the faces array. Each face requires one integer
		// for its global id plus one more for each of its edges
		int facesSize = 0;
		for (IController face : tempFaces) {

			// Add space for the global id and an end of polygon flag
			facesSize = facesSize + 2;

			// Add space for an index for each edge
			facesSize = facesSize
					+ face.getEntitiesFromCategory(MeshCategory.EDGES).size();
		}

		// Initialize the faces array
		faces = new int[facesSize];

		// The next index in the faces array to write to
		int faceIndex = 0;

		// Handle each face
		for (NekPolygonController face : tempFaces) {

			// Record the face's global id
			faces[faceIndex] = Integer
					.parseInt(face.getProperty(MeshProperty.ID));
			faceIndex++;

			// For each edge in the face, add its id to the array
			for (IController edge : face
					.getEntitiesFromCategory(MeshCategory.EDGES)) {

				// Use the map to convert from the edge's old ID to its new id
				faces[faceIndex] = edgeIDMap.get(
						Integer.parseInt(edge.getProperty(MeshProperty.ID)));
				faceIndex++;
			}

			// Place a -1 to signal that the current face has ended
			faces[faceIndex] = -1;
			faceIndex++;

			// Create a copy of the face's EdgeProperties map, with each
			// properties using the edge's new ID as a key
			HashMap<Integer, EdgeProperties> clone = new HashMap<Integer, EdgeProperties>();
			for (Integer i : face.getEdgeProperties().keySet()) {
				clone.put(edgeIDMap.get(i), face.getEdgeProperties().get(i));
			}

			// Add the face's properties to the description
			edgeProperties.add(clone);
			polygonProperties.add(face.getPolygonProperties());
		}

		// Initialize the transformations array
		transformations = new double[tempVertices.size()][3];

		// The index for the start of the current transformation's data block in
		// the array.
		int transformIndex = 0;

		// Handle each entity's transformation
		for (VertexController entity : tempVertices) {

			// Serialize the transformation into an array of numbers.
			transformations[transformIndex][0] = entity.getX();
			transformations[transformIndex][1] = entity.getY();
			transformations[transformIndex][2] = entity.getZ();
			transformIndex++;
		}

	}

	/**
	 * Create a deep copy of the given edge properties map
	 * 
	 * @param source
	 *            The map to clone
	 * @return A copy of the map filled with clones of its EdgeProperties.
	 */
	private static HashMap<Integer, EdgeProperties> cloneMap(
			HashMap<Integer, EdgeProperties> source) {

		// Create a deep copy of the map
		HashMap<Integer, EdgeProperties> clone = new HashMap<Integer, EdgeProperties>();
		for (Integer i : source.keySet()) {
			clone.put(i, (EdgeProperties) source.get(i).clone());
		}
		return clone;
	}

	/**
	 * Getter method for the edges.
	 * 
	 * @return An array of global ids for the edges, along with their endpoints
	 *         as indices into the vertices array
	 */
	@XmlTransient
	public int[] getEdges() {
		return edges;
	}

	/**
	 * Getter method for the faces.
	 * 
	 * @return An array of global ids for the faces, along with their
	 *         constituent edges as indices into the edges array
	 */
	@XmlTransient
	public int[] getFaces() {
		return faces;
	}

	/**
	 * Getter method for the edge properties.
	 * 
	 * @return A list of maps between integer ids for edges and their edge
	 *         properties, one for each face in the faces array
	 */
	@XmlTransient
	public ArrayList<HashMap<Integer, EdgeProperties>> getEdgeProperties() {
		return edgeProperties;
	}

	/**
	 * Getter method for the polygon properties
	 * 
	 * @return A list of all the PolygonProperties in the hierarchy in the same
	 *         order as their corresponding polygons in the faces array
	 */
	@XmlTransient
	public ArrayList<PolygonProperties> getPolygonProperties() {
		return polygonProperties;
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
	@XmlTransient
	public double[][] getTransformations() {
		return transformations;
	}

	/**
	 * Getter method for the vertices.
	 * 
	 * @return An array of global ids for the vertices
	 */
	@XmlTransient
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
	 * Setter method for the edge properties.
	 * 
	 * @param properties
	 *            A list of maps between integer ids for edges and their edge
	 *            properties, one for each face in the faces array
	 */
	public void setEdgeProperties(
			ArrayList<HashMap<Integer, EdgeProperties>> properties) {
		this.edgeProperties = properties;
	}

	/**
	 * Setter method for the polygon properties.
	 * 
	 * @param polygonProperties
	 *            A list of the PolygonProperties in the mesh, in the same order
	 *            as their corresponding polygons are in the faces array.
	 */
	public void setPolygonProperties(
			ArrayList<PolygonProperties> polygonProperties) {
		this.polygonProperties = polygonProperties;
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
	public void setTransformations(double[][] transformations) {
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
	private void sortMesh(IController mesh,
			ArrayList<VertexController> vertices, ArrayList<IController> edges,
			ArrayList<NekPolygonController> faces) {

		// If this part is a polygon, handle it
		if (mesh.getClass() == NekPolygonController.class) {

			// If the face has not already been put into the mesh, add it and
			// all its children
			if (!faces.contains(mesh)) {
				faces.add((NekPolygonController) mesh);

				// Handle all the polygon's edges
				for (IController child : mesh
						.getEntitiesFromCategory(MeshCategory.EDGES)) {

					// If the edge is not already recorded, save it
					if (!edges.contains(child)) {
						edges.add(child);
					}
				}

				// Handle all the polygon's vertices
				for (VertexController child : mesh.getEntitiesFromCategory(
						MeshCategory.VERTICES, VertexController.class)) {

					// If the vertex is not already recorded, save it
					if (!vertices.contains(child)) {
						vertices.add(child);
					}
				}
			}
		}

		// for unrecognized parts, try to search all of their children
		else {
			for (IController child : mesh.getEntities()) {
				sortMesh(child, vertices, edges, faces);
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
		IControllerProvider<VertexController> vertexProvider = factory
				.createProvider(new VertexMesh());
		IControllerProvider edgeProvider = factory
				.createProvider(new DetailedEdgeMesh());
		IControllerProvider<NekPolygonController> faceProvider = factory
				.createProvider(new NekPolygonMesh());

		// For each id in the vertices array
		for (int i = 0; i < vertices.length; i++) {

			// Create a vertex with the given id
			VertexMesh mesh = new VertexMesh();
			mesh.setProperty(MeshProperty.ID, Integer.toString(vertices[i]));
			VertexController vertex = vertexProvider.createController(mesh);

			// Set the vertex's name
			vertex.setProperty(MeshProperty.NAME, "Vertex");

			// TODO remove this section when transformations are moved to the
			// IMesh
			vertex.setX(transformations[i][0]);
			vertex.setY(transformations[i][1]);
			vertex.setZ(transformations[i][2]);

			// Create the transformation and apply it
			Transformation transform = new Transformation();
			transform.setTranslation(transformations[i][0],
					transformations[i][1], transformations[i][2]);
			vertex.setTransformation(transform);

			// Add the vertex to the list
			tempVertices.add(vertex);
		}

		for (int i = 0; i < edges.length; i++) {

			// Create a new edge with the correct ID
			DetailedEdgeMesh mesh = new DetailedEdgeMesh();
			mesh.setProperty(MeshProperty.ID, Integer.toString(edges[i]));
			IController edge = edgeProvider.createController(mesh);

			// Set the edge's name
			edge.setProperty(MeshProperty.NAME, "Edge");

			// Calculate the endpoints' IDs based on the edge ID
			int endpoint1 = edges[i] / (vertices.length + 1);
			int endpoint2 = edges[i] % (vertices.length + 1);

			// This is a bidirectional mesh, so sort the endpoints
			if (endpoint2 < endpoint1) {
				int temp = endpoint1;
				endpoint1 = endpoint2;
				endpoint2 = temp;
			}

			// Find the vertices with the correct IDs and set them as
			// the edge's endpoints
			for (IController vertex : tempVertices) {

				// Get the vertex's id
				int vertexID = Integer
						.parseInt(vertex.getProperty(MeshProperty.ID));

				// If it matches either endpoint, add it to the edge
				if (vertexID == endpoint1 || vertexID == endpoint2) {
					edge.addEntityToCategory(vertex, MeshCategory.VERTICES);
				}
			}

			// Save the finished edge to the list
			tempEdges.add(edge);
		}

		// Whether or not to begin the creation of a new polygon
		boolean newPolygon = true;

		// The number of polygons created thus far
		int numPolygons = 0;

		// The polygon currently under construction
		NekPolygonController face = null;

		// Consider each individual value in the faces array, as its structure
		// is unknown.
		for (int i = 0; i < faces.length; i++) {

			// The current id to process
			int id = faces[i];

			// -1 is a delimiter value signaling to create a new polygon
			if (id == -1) {

				// Get the edge properties for this polygon
				HashMap<Integer, EdgeProperties> props = edgeProperties
						.get(numPolygons);

				// A null value represents the default properties, so ignore it
				if (props != null) {

					// Assign the clone to the face
					face.setEdgeProperties(cloneMap(props));
				}

				// Get the polygon properties for this face
				PolygonProperties polygonProps = polygonProperties
						.get(numPolygons);

				// If the properties are non-null, apply them to the face
				if (polygonProps != null) {
					face.setPolygonProperties(polygonProps.getMaterialId(),
							polygonProps.getGroupNum());
				}

				// The current polygon is finished
				numPolygons++;
				newPolygon = true;
				continue;
			}

			// If this is the first value of a new face, it is that face's
			// global id
			if (newPolygon) {

				// Create a mesh with the given ID
				NekPolygonMesh mesh = new NekPolygonMesh();
				mesh.setProperty(MeshProperty.ID, Integer.toString(faces[i]));
				face = faceProvider.createController(mesh);

				// Add the face as a child entity to the root
				root.addEntity(face);

				// The next value will be for an edge
				newPolygon = false;
			}

			// Otherwise, the id is for one of the current face's edges
			else {

				// Search for the edge with the given ID and add it to the face
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

	/**
	 * Get the information about the mesh's faces formatted into a list of
	 * FaceLines.
	 * 
	 * This method is used for persistence by JAXB and is not intended to be
	 * called.
	 * 
	 * @return A list of all the lines in an xml block defininf the faces
	 */
	@XmlJavaTypeAdapter(FacesBlockAdapter.class)
	public ArrayList<FaceLine> getFacesBlock() {

		// The full list of all faces information
		ArrayList<FaceLine> block = new ArrayList<FaceLine>();

		// Whether or not the current value is a face ID. If false, then the
		// current value is either an edge or a special delimiter value
		boolean currID = true;

		// The ordinal number of the current polygon being converted
		int currPolygon = 0;

		// The line currently under construction
		FaceLine line = null;

		// Iterate through each value of the faces array
		for (int i = 0; i < faces.length; i++) {

			// If this is a face ID, create a new face
			if (currID) {
				line = new FaceLine();
				line.FaceID = faces[i];

				// Get the face's corresponding properties and set them
				PolygonProperties props = polygonProperties.get(currPolygon);
				line.GroupNum = props.getGroupNum();
				line.MaterialID = props.getMaterialId();

				// The next number will be an edge ID
				currID = false;
			} else {

				// Get the current value from the array
				int curr = faces[i];

				// If it is not -1, then it is an edge ID
				if (curr != -1) {

					// Create a new edge with this ID
					EdgeLine edge = new EdgeLine();
					edge.EdgeID = curr;

					// Get the properties of edge curr for polygon i
					EdgeProperties props = edgeProperties.get(currPolygon)
							.get(curr);

					String boundary = "";

					// If a boundary condition is set, convert it to a string
					// and add it to the edge
					if (props != null) {
						String fluid = props.getFluidBoundaryCondition()
								.toString();
						if (fluid.startsWith("None")) {
							fluid = fluid.replaceFirst("None ", "");
						}
						if (fluid.endsWith("0.0 0.0 0.0 0.0 0.0")) {
							fluid = fluid.replaceFirst("0.0 0.0 0.0 0.0 0.0",
									"");
						}

						String thermal = props.getThermalBoundaryCondition()
								.toString();
						if (thermal.startsWith("None")) {
							thermal = thermal.replaceFirst("None ", "");
						}
						if (thermal.endsWith("0.0 0.0 0.0 0.0 0.0")) {
							thermal = thermal
									.replaceFirst("0.0 0.0 0.0 0.0 0.0", "");
						}

						boundary += fluid + "," + thermal;
					}

					// Othewise add two commas to show the empty conditions
					else {
						boundary += ",";
					}

					edge.BoundaryConditions.add(boundary);

					// Add the finished edge to the face
					line.Edges.add(edge);
				}

				// If the values is -1, the face is finished
				else {

					// The next value will be a face's ID
					currID = true;

					// Increment the polygon index
					currPolygon++;

					// And the finished line to the block
					block.add(line);
				}
			}
		}

		return block;
	}

	/**
	 * Set the information about the mesh's faces formatted into a block of
	 * FaceLines.
	 * 
	 * This method is used for persistence with JAXB and is not intended to be
	 * called
	 * 
	 * @param block
	 *            A list of all lines in the xml block defining the faces
	 */
	public void setFacesBlock(ArrayList<FaceLine> block) {

		// A temporary ArrayList version of the faces array
		ArrayList<Integer> tempFacesArray = new ArrayList<Integer>();

		// A temporary unsorted ArrayList version of the edges array
		ArrayList<Integer> edges = new ArrayList<Integer>();

		// Iterate through each line
		for (int i = 0; i < block.size(); i++) {
			FaceLine line = block.get(i);

			// Add the face ID to the faces array
			tempFacesArray.add(line.FaceID);

			// Create a new map from edge IDs to EdgeProperties
			HashMap<Integer, EdgeProperties> edgeMap = new HashMap<Integer, EdgeProperties>();

			// Handle each edge's description
			for (EdgeLine edgeLine : line.Edges) {

				// Add this edge's id to the face
				tempFacesArray.add(edgeLine.EdgeID);

				// Add the ID to the list of IDs if it hasn't been seen before
				if (!edges.contains(edgeLine.EdgeID)) {
					edges.add(edgeLine.EdgeID);
				}

				// Create a new EdgeProperties for this edge
				EdgeProperties properties = new EdgeProperties();

				// Handle each boundary condition in the edge
				for (int j = 0; j < edgeLine.BoundaryConditions.size(); j++) {
					String boundaryCondition = edgeLine.BoundaryConditions
							.get(j);
					BoundaryCondition boundary = new BoundaryCondition();

					// Split the boundary condition description on whitespace
					String[] props = boundaryCondition.split("\\s+");

					// The properties will be 1 or 6 tokens long depending on
					// whether or not type and/or values are specified
					if (props.length == 6) {

						// If the first string isn't empty, it's a condition
						// type
						if (!props[0].isEmpty()) {
							boundary.setType(
									BoundaryConditionType.valueOf(props[0]));
						}

						// Regardless of the first value, the next 5 will always
						// be the condition's values
						ArrayList<Float> values = new ArrayList<Float>();
						values.add(Float.parseFloat(props[1]));
						values.add(Float.parseFloat(props[2]));
						values.add(Float.parseFloat(props[3]));
						values.add(Float.parseFloat(props[4]));
						values.add(Float.parseFloat(props[5]));
						boundary.setValues(values);
					}

					// If there is only one token, check whether or not it is
					// empty
					else if (!props[0].isEmpty()) {

						// Set the condition type if it is not
						boundary.setType(
								BoundaryConditionType.valueOf(props[0]));
					}

					// The first condition will be for fluids
					if (j == 0) {
						properties.setFluidBoundaryCondition(boundary);
					}

					// The second will be a thermal boundary
					else {
						properties.setThermalBoundaryCondition(boundary);
					}

					// else if(!props[0])

					// else{
					// values.add(Float.parseFloat(props[0]));
					// values.add(Float.parseFloat(props[1]));
					// values.add(Float.parseFloat(props[2]));
					// values.add(Float.parseFloat(props[3]));
					// values.add(Float.parseFloat(props[4]));
					//
					// boundary.setValues(values);
					// }
				}

				// Put edge's the properties in the map
				edgeMap.put(edgeLine.EdgeID, properties);

			}

			// Put the face's properties in the map
			edgeProperties.add(edgeMap);

			// Add a "-1" to show where the face ends
			tempFacesArray.add(-1);

			// If the polygon has non-default properties, set them
			if (line.GroupNum != 0 || !"nul1".equals(line.MaterialID)) {
				polygonProperties.add(
						new PolygonProperties(line.MaterialID, line.GroupNum));
			}

			// Otherwise, add null to signify the default values
			else {
				polygonProperties.add(null);
			}

		}

		// After reading in the entire face block, convert the faces'
		// information into an array
		faces = new int[tempFacesArray.size()];
		for (int i = 0; i < tempFacesArray.size(); i++) {
			faces[i] = tempFacesArray.get(i);
		}

		// Do the same for the edges
		this.edges = new int[edges.size()];
		for (int i = 0; i < edges.size(); i++) {
			this.edges[i] = edges.get(i);
		}
	}

	/**
	 * Get the information about the mesh's vertices formatted into a block of
	 * VertexLines.
	 * 
	 * This method is used for persistence with JAXB and is not intended to be
	 * called.
	 * 
	 * @return A list of all the lines in the xml block defining the vertices
	 */
	@XmlElement(name = "vertices")
	@XmlJavaTypeAdapter(VerticesBlockAdapter.class)
	public ArrayList<VertexLine> getVerticesBlock() {

		// The full vertex block
		ArrayList<VertexLine> block = new ArrayList<VertexLine>();

		// Add each vertex as its own line
		for (int i = 0; i < vertices.length; i++) {
			VertexLine line = new VertexLine();

			// Get the id and coordinates
			line.ID = vertices[i];
			line.x = transformations[i][0];
			line.y = transformations[i][1];
			line.z = transformations[i][2];

			// Add the line to the block
			block.add(line);
		}

		return block;
	}

	/**
	 * Set the information about the mesh's vertices formatted into a block of
	 * VertexLines.
	 * 
	 * This method is used for persistence with JAXB and is not intended to be
	 * called
	 * 
	 * @param block
	 *            A list of all lines in the xml block defining the vertices
	 */
	public void setVerticesBlock(ArrayList<VertexLine> block) {

		// There is one vertex per line in the block
		int vertexNum = block.size();

		// Initialize the arrays
		vertices = new int[vertexNum];
		transformations = new double[vertexNum][3];

		// Iterate through the block one line at a time
		for (int i = 0; i < vertexNum; i++) {

			// Get the current line
			VertexLine line = block.get(i);

			// Put the ID field into the vertices array
			vertices[i] = line.ID;

			// Put the coordinates into the transformations array
			transformations[i][0] = line.x;
			transformations[i][1] = line.y;
			transformations[i][2] = line.z;
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object otherObject) {

		// If the other object is not of the same type, they are not equal
		if (!(otherObject instanceof MeshDescription)) {
			return false;
		}

		// Cast the other object
		MeshDescription castObject = (MeshDescription) otherObject;

		// If any of the arrays/lists are of different sizes, then the objects
		// are not equal.
		if (vertices.length != castObject.vertices.length
				|| edges.length != castObject.edges.length
				|| faces.length != castObject.faces.length
				|| transformations.length != castObject.transformations.length) {
			return false;
		}

		// If any of the vertex IDs are different, the objects are not equal
		for (int i = 0; i < vertices.length; i++) {
			if (vertices[i] != castObject.vertices[i]) {
				return false;
			}
		}

		// If any of the edges are different, the objects are not equal
		for (int i = 0; i < edges.length; i++) {
			if (edges[i] != castObject.edges[i]) {
				return false;
			}
		}

		// If any of the faces are different, the objects are not equal
		for (int i = 0; i < faces.length; i++) {
			if (faces[i] != castObject.faces[i]) {
				return false;
			}
		}

		// If any of the vertex coordinates are different, the objects are not
		// equal
		for (int i = 0; i < transformations.length; i++) {
			for (int j = 0; j < 3; j++) {
				if (transformations[i][j] != castObject.transformations[i][j]) {
					return false;
				}
			}
		}

		// Check each map in the list of edge properties
		for (int i = 0; i < edgeProperties.size(); i++) {

			// Get the maps from both objects
			HashMap<Integer, EdgeProperties> map = edgeProperties.get(i);
			HashMap<Integer, EdgeProperties> otherMap = castObject.edgeProperties
					.get(i);

			// If the maps don't have the same edges, the object is not equal
			if (!map.keySet().equals(otherMap.keySet())) {
				return false;
			}
			;

			// If the edge's properties are not equal, this object is also not
			// equal
			for (Integer j : map.keySet()) {
				if (!map.get(j).equals(otherMap.get(j))) {
					return false;
				}
			}
		}

		// Check each PolygonProperties in order. If any are inequal, then these
		// objects are inequal
		for (int i = 0; i < polygonProperties.size(); i++) {
			if (!polygonProperties.get(i)
					.equals(castObject.polygonProperties.get(i))) {
				return false;
			}
		}

		// All checks passed, so these objects are equal
		return true;

	}

	/**
	 * This class serves as a JAXB adapter for the vertices block of a
	 * MeshDescription's xml representation. It will convert an ArrayList of
	 * VertexLines into a a string of xml code, with each vertex's information
	 * on a new line, with the vertex id followed by the three coordinates.
	 * 
	 * @author Robert Smith
	 *
	 */
	private static class FacesBlockAdapter
			extends XmlAdapter<String, ArrayList<FaceLine>> {

		/*
		 * (non-Javadoc)
		 * 
		 * @see javax.xml.bind.annotation.adapters.XmlAdapter#marshal(java.lang.
		 * Object)
		 */
		@Override
		public String marshal(ArrayList<FaceLine> v) throws Exception {

			// Start the output by pushing the first row to a new line
			String output = "\n";

			// Iterate through the list, writing each line
			for (FaceLine line : v) {

				// Start the line with the face's id
				output += line.FaceID + ",";

				// Add the group number if it is non-default
				if (line.GroupNum != 0) {
					output += line.GroupNum;
				}

				output += ",";

				// Add the material ID if it is non-default
				if (!"nul1".equals(line.MaterialID)) {
					output += line.MaterialID;
				}

				// Write a ; to signal the end of the face's properties
				output += ";";

				// Handle each edge in the face
				for (EdgeLine edge : line.Edges) {

					// Start with the edge's ID
					output += edge.EdgeID + ",";

					// Write each boundary condition, seperated by commas
					for (String boundary : edge.BoundaryConditions) {
						output += boundary + ",";
					}

					// Write a ; to signal the end of the edge's properties
					output += ";";
				}

				// Start a new line for the new polygon.
				output += "\n";

			}

			return output;

		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see
		 * javax.xml.bind.annotation.adapters.XmlAdapter#unmarshal(java.lang.
		 * Object)
		 */
		@Override
		public ArrayList<FaceLine> unmarshal(String v) throws Exception {

			// Split the input by new line
			String[] input = v.split("\\r?\\n");

			// The full list of faces
			ArrayList<FaceLine> output = new ArrayList<FaceLine>();

			// Iterate through each face
			for (int i = 1; i < input.length; i++) {

				// Create a face line to hold this line's information
				FaceLine face = new FaceLine();

				// Split the information in the line by the edge it refers to
				String[] edges = input[i].split(";");

				// The first "edge" is a special section with information for
				// the face, deliminated by commas
				String[] faceProps = edges[0].split(",");

				// Get the face's ID
				face.FaceID = Integer.parseInt(faceProps[0]);

				// If the first segment is not empty, set the group number
				if (faceProps.length >= 2 && !faceProps[1].isEmpty()) {
					face.GroupNum = Integer.parseInt(faceProps[1]);
				}

				// If the second segment is not empty, set the material ID
				if (faceProps.length >= 3 && !faceProps[2].isEmpty()) {
					face.MaterialID = faceProps[2];
				}

				// Iterate through the edges
				for (int j = 1; j < edges.length; j++) {
					EdgeLine edge = new EdgeLine();

					// Split each edge into its boundary conditions
					String[] edgeProps = edges[j].split(",");

					// The first segment is the edgeID
					edge.EdgeID = Integer.parseInt(edgeProps[0]);

					// Add all other segments as boundary conditions
					for (int k = 1; k < edgeProps.length; k++) {
						edge.BoundaryConditions.add(edgeProps[k]);
					}

					// Add the finished edge
					face.Edges.add(edge);
				}

				// Add the finished face
				output.add(face);
			}

			return output;
		}
	}

	/**
	 * This class serves as a JAXB adapter for the vertices block of a
	 * MeshDescription's xml representation. It will convert an ArrayList of
	 * VertexLines into a a string of xml code, with each vertex's information
	 * on a new line, with the vertex id followed by the three coordinates.
	 * 
	 * @author Robert Smith
	 *
	 */
	private static class VerticesBlockAdapter
			extends XmlAdapter<String, ArrayList<VertexLine>> {

		/*
		 * (non-Javadoc)
		 * 
		 * @see javax.xml.bind.annotation.adapters.XmlAdapter#marshal(java.lang.
		 * Object)
		 */
		@Override
		public String marshal(ArrayList<VertexLine> v) throws Exception {

			// Start the output by pushing the first row to a new line
			String output = "\n";

			// Iterate through the list, writing each line
			for (VertexLine line : v) {

				// Format the line as "ID x y z"
				output += line.ID + " " + line.x + " " + line.y + " " + line.z
						+ '\n';
			}

			return output;

		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see
		 * javax.xml.bind.annotation.adapters.XmlAdapter#unmarshal(java.lang.
		 * Object)
		 */
		@Override
		public ArrayList<VertexLine> unmarshal(String v) throws Exception {

			// Split the input by new line
			String[] input = v.split("\\r?\\n");

			ArrayList<VertexLine> output = new ArrayList<VertexLine>();

			// Convert each string to a double and put it in the array
			for (int i = 1; i < input.length; i++) {

				// Split the line into tokens
				String[] inputLine = input[i].split("\\s+");

				// Convert the tokens into a vertex's information
				VertexLine line = new VertexLine();
				line.ID = Integer.parseInt(inputLine[0]);
				line.x = Double.parseDouble(inputLine[1]);
				line.y = Double.parseDouble(inputLine[2]);
				line.z = Double.parseDouble(inputLine[3]);

				// Save the line to the output array
				output.add(line);
			}

			return output;
		}
	}

	/**
	 * A temporary internal representation of a line of the vertices xml block
	 * 
	 * @author Robert Smith
	 *
	 */
	public static class VertexLine {

		/**
		 * This vertex's unique global ID
		 */
		public int ID;

		/**
		 * The x coordinate
		 */
		public double x;

		/**
		 * The y coordinate
		 */
		public double y;

		/**
		 * The z coordinate
		 */
		public double z;
	}

	/**
	 * A temporary internal representation of a line of the faces xml block
	 * 
	 * @author Robert Smith
	 *
	 */
	public static class FaceLine {

		/**
		 * The face's unique global ID
		 */
		public int FaceID;

		/**
		 * The face's group number.
		 */
		public int GroupNum = 0;

		/**
		 * The face's material ID.
		 */
		public String MaterialID = "None";

		/**
		 * A list of all the edges contained in the face
		 */
		public ArrayList<EdgeLine> Edges = new ArrayList<EdgeLine>();
	}

	/**
	 * A temporary internal representation of a segment of a FaceLine defining
	 * an Edge.
	 * 
	 * @author Robert Smith
	 *
	 */
	public static class EdgeLine {

		/**
		 * The edge's unique global ID. See the documentation for
		 * MeshDescription for information on how this relates to the edge's
		 * vertices
		 */
		public int EdgeID;

		/**
		 * A list of all the types for the boundary conditions on the edge.
		 */
		public ArrayList<String> BoundaryConditions = new ArrayList<String>();
	}
}
