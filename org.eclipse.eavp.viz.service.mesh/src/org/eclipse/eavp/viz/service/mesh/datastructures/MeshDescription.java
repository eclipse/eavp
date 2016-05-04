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
	@XmlElement
	@XmlList
	public int[] getEdges() {
		return edges;
	}

	/**
	 * Getter method for the faces.
	 * 
	 * @return An array of global ids for the faces, along with their
	 *         constituent edges as indices into the edges array
	 */
	@XmlJavaTypeAdapter(FacesBlockAdapter.class)
	public int[] getFaces() {
		return faces;
	}

	/**
	 * Getter method for the edge properties.
	 * 
	 * @return A list of maps between integer ids for edges and their edge
	 *         properties, one for each face in the faces array
	 */
	@XmlJavaTypeAdapter(EdgePropertiesAdapter.class)
	public ArrayList<HashMap<Integer, EdgeProperties>> getEdgeProperties() {
		return edgeProperties;
	}

	/**
	 * Getter method for the polygon properties
	 * 
	 * @return A list of all the PolygonProperties in the hierarchy in the same
	 *         order as their corresponding polygons in the faces array
	 */
	@XmlJavaTypeAdapter(PolygonPropertiesAdapter.class)
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
	@XmlJavaTypeAdapter(TransformationBlockAdapter.class)
	public double[][] getTransformations() {
		return transformations;
	}

	/**
	 * Getter method for the vertices.
	 * 
	 * @return An array of global ids for the vertices
	 */
	@XmlElement
	@XmlList
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

	public double[][] getVerticesBlock() {

	}

	/**
	 * This class serves as a JAXB adapter for the list of EdgeProperties maps
	 * belonging to the mesh's faces. It allows the data structures of an
	 * ArrayList of HashMaps from integer edgeIDs to corresponding
	 * EdgeProperties to be persisted to and from xml.
	 * 
	 * @author Robert Smith
	 *
	 */
	private static class EdgePropertiesAdapter extends
			XmlAdapter<String, ArrayList<HashMap<Integer, EdgeProperties>>> {

		/*
		 * (non-Javadoc)
		 * 
		 * @see javax.xml.bind.annotation.adapters.XmlAdapter#marshal(java.lang.
		 * Object)
		 */
		@Override
		public String marshal(ArrayList<HashMap<Integer, EdgeProperties>> v)
				throws Exception {

			// Start the output by pushing the first row to a new line
			String output = "\n";

			// Consider each map, which represents the properties for a single
			// face
			for (HashMap<Integer, EdgeProperties> map : v) {

				// Each key in the map represents a single edge
				for (int i : map.keySet()) {

					// Write the edge's id
					output += i + " ";

					// Write the fluid and thermal boundary conditions,
					// separated by commas
					EdgeProperties properties = map.get(i);

					// Get the string representation of the fluid boundary,
					// separating its type and values
					String fluid = properties.getFluidBoundaryCondition()
							.toString();
					int j = fluid.indexOf(' ');
					String fluidType = fluid.substring(0, j);
					String fluidValues = fluid.substring(j);

					// If the type is a non-default value, add it to the string
					if (!"None".equals(fluidType)) {
						output += fluidType;
					}

					// If the values are not the default, add them to the string
					if (!" 0.0 0.0 0.0 0.0 0.0".equals(fluidValues)) {
						output += fluidValues;
					}

					// Place a comma to signal the end of the boundary condition
					output += " , ";

					// Get the string representation of the fluid boundary,
					// separating its type and values
					String thermal = properties.getThermalBoundaryCondition()
							.toString();
					j = thermal.indexOf(' ');
					String thermalType = thermal.substring(0, j);
					String thermalValues = thermal.substring(j);

					// If the type is a non-default value, add it to the string
					if (!"None".equals(thermalType)) {
						output += thermalType;
					}

					// If the values are not the default, add them to the string
					if (!" 0.0 0.0 0.0 0.0 0.0".equals(thermalValues)) {
						output += thermalValues;
					}

					// Place a comma to signal the end of the boundary condition
					output += " , ";

					// Write each additional condition, if any
					for (BoundaryCondition condition : properties
							.getOtherBoundaryConditions()) {
						output += condition.toString() + " ";
					}

					// Mark the end of this edge's properties with a semicolon
					output += "; ";
				}

				// All edges are handled, so this polygon is done. Start the
				// next polygon on a new line
				output += '\n';
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
		public ArrayList<HashMap<Integer, EdgeProperties>> unmarshal(String v)
				throws Exception {

			// Ignoring the initial linebreak at the start of the text, replace
			// linebreaks with a special internal delimiter value and split the
			// input by whitespace
			String[] input = v.substring(1).replace("\n", "/ ").split("\\s+");

			// The list of properties
			ArrayList<HashMap<Integer, EdgeProperties>> output = new ArrayList<HashMap<Integer, EdgeProperties>>();

			// The map currently under construction
			HashMap<Integer, EdgeProperties> map = new HashMap<Integer, EdgeProperties>();

			// The properties set currently under construction
			EdgeProperties properties = null;

			// A regular expression that matches numerical values
			String regex = "[\\x00-\\x20]*[+-]?(((((\\p{Digit}+)(\\.)?((\\p{Digit}+)?)([eE][+-]?(\\p{Digit}+))?)|(\\.((\\p{Digit}+))([eE][+-]?(\\p{Digit}+))?)|(((0[xX](\\p{XDigit}+)(\\.)?)|(0[xX](\\p{XDigit}+)?(\\.)(\\p{XDigit}+)))[pP][+-]?(\\p{Digit}+)))[fFdD]?))[\\x00-\\x20]*";

			// Whether or not the next value marks the start of a new edge
			boolean newEdge = true;

			// The ID of the edge currently under construction
			int currID = 0;

			// The BoundaryCondition currently under construction
			BoundaryCondition currCondition = null;

			// Whether or not currCondition is a thermal boundary. If false,
			// then currCondition is a fluid boundary instead
			boolean thermal = false;

			// The list of values for the current boundary condition
			ArrayList<Float> currValues = new ArrayList<Float>();

			// Process each token in the string
			for (String curr : input) {

				// If this is the start of a new edge, curr is the edge's ID.
				if (newEdge) {

					// For non-empty lines, begin creating the edge's properties
					if (!"/".equals(curr)) {

						// Create new properties for the edge
						properties = new EdgeProperties();

						// Set the current ID
						currID = Integer.parseInt(curr);
						newEdge = false;
					}

					// Empty lines represent the default values, so just add
					// null to the list
					else {

						// Add the current map to the output
						output.add(cloneMap(map));

						// Clear the working copy
						map = new HashMap<Integer, EdgeProperties>();
					}
				}

				// Otherwise, if curr is a number, it must be a value for the
				// current boundary condition
				else if (curr.matches(regex)) {
					currValues.add(Float.parseFloat(curr));
				}

				// A , represents the end of a boundary condition
				else if (",".equals(curr)) {

					// If a condition has not been created, make one now
					if (currCondition == null) {
						currCondition = new BoundaryCondition();
					}

					// Save the array of values and clear the working copy
					currCondition.setValues(currValues);
					currValues = new ArrayList<Float>();

					// Set the condition as the thermal boundary
					if (thermal) {
						properties.setThermalBoundaryCondition(currCondition);

						// The next condition will be a fluid boundary
						thermal = false;
					}

					// Otherwise, set the condition as a fluid boundary
					else {
						properties.setFluidBoundaryCondition(currCondition);

						// The next condition will be a thermal boundary
						thermal = true;
					}

					// Clear the working copy of the condition
					currCondition = null;
				}

				// A ; marks the end of an edge's properties
				else if (";".equals(curr)) {

					// Save the current properties with the edge's ID.
					map.put(currID, properties);

					// The next value will start a new edge
					newEdge = true;
				}

				// A / marks the end of a polygon's properties
				else if ("/".equals(curr)) {

					// Add the current map to the output
					output.add(cloneMap(map));

					// Clear the working copy
					map = new HashMap<Integer, EdgeProperties>();
				}

				// Other non-numerical values must be boundary condition types
				else {

					// Create a new condition of the given type
					currCondition = new BoundaryCondition(
							BoundaryConditionType.valueOf(curr));
				}
			}

			return output;
		}
	}

	/**
	 * This class serves as a JAXB adapter for the faces block of a
	 * MeshDescription's xml representation. It will convert an array of doubles
	 * with "-1" delimiting the end of a face's description into a block of
	 * arbitrarily length rows, removing the "-1" values and placing each face
	 * on its own line.
	 * 
	 * @author Robert Smith
	 *
	 */
	private static class FacesBlockAdapter extends XmlAdapter<String, int[]> {

		/*
		 * (non-Javadoc)
		 * 
		 * @see javax.xml.bind.annotation.adapters.XmlAdapter#marshal(java.lang.
		 * Object)
		 */
		@Override
		public String marshal(int[] v) throws Exception {

			// Start the output by pushing the first row to a new line
			String output = "\n";

			// Iterate through the 2D, * x 3 sized block
			for (int i = 0; i < v.length; i++) {

				// After each number, insert a space
				if (v[i] != -1) {
					output += v[i] + " ";
				}

				// Replace -1 with a linebreak
				else {
					output += '\n';
				}
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
		public int[] unmarshal(String v) throws Exception {

			// Ignoring the initial linebreak at the start of the text, replace
			// linebreaks with "-1" and split the input by whitespace
			String[] input = v.substring(1).replace("\n", "-1 ").split("\\s+");

			// The new faces array
			int[] output;

			// A face must be comprised of an ID and some number EdgeIDs. An
			// input length of 1 means that the faces tag was empty in the xml
			if (input.length > 1) {

				// The array which will hold the coordinates, sized * x 3
				output = new int[input.length];

				// Convert each string to a double and put it in the array
				for (int i = 0; i < input.length; i++) {
					output[i] = Integer.parseInt(input[i]);
				}

			}

			// If the tag was empty, create an empty array
			else {
				output = new int[0];
			}

			return output;
		}
	}

	/**
	 * This class serves as a JAXB adapter for the PolygonProperties block of a
	 * MeshDescription's xml representation. It will output the
	 * PolygonProperties as two tokens each, a Material ID and a Group Number,
	 * with the properties for each polygon separated on a new line, in the
	 * order the faces are defined in the faces block.
	 * 
	 * @author Robert Smith
	 *
	 */
	private static class PolygonPropertiesAdapter
			extends XmlAdapter<String, ArrayList<PolygonProperties>> {

		/*
		 * (non-Javadoc)
		 * 
		 * @see javax.xml.bind.annotation.adapters.XmlAdapter#marshal(java.lang.
		 * Object)
		 */
		@Override
		public String marshal(ArrayList<PolygonProperties> v) throws Exception {

			// Start the output by pushing the first row to a new line
			String output = "\n";

			// Iterate through the list, making each properties list into its
			// own line with the group number and material ID separated by a
			// space
			for (PolygonProperties props : v) {
				String temp = props.getGroupNum() + " " + props.getMaterialId();

				// If the properties are the default, save space by writing an
				// empty line
				if (!"0 nul1".equals(temp)) {
					output += temp;
				}

				// End the line for the current polygon
				output += '\n';
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
		public ArrayList<PolygonProperties> unmarshal(String v)
				throws Exception {

			// Split the input into an array of numbers
			String[] input = v.substring(1).replace("\n", " ; ").split("\\s+");

			// The list of all polygon properties
			ArrayList<PolygonProperties> output = new ArrayList<PolygonProperties>();

			// The current index in the array of input tokens
			int i = 1;

			// Traverse the array
			while (i < input.length) {

				// If the input is a number, set the polygon properties
				if (!(input[i].equals(";") || input[i].isEmpty())) {

					// The properties are defined by the current and next
					// numbers in the input
					output.add(new PolygonProperties(input[i + 1],
							Integer.parseInt(input[i])));

					// Skip two numbers for the properties, then one more for
					// the semicolon that ends the line
					i = i + 3;
				}

				// If the input is a semicolon, this is an empty line
				else {

					// Add null the signify that there are no polygon properties
					// for this polygon
					output.add(null);

					// This line is only one token long, so move along to the
					// next token
					i++;
				}
			}

			return output;
		}
	}

	/**
	 * This class serves as a JAXB adapter for the transformations block of a
	 * MeshDescription's xml representation. It will convert a two dimensional
	 * array of doubles into a table of three doubles on each row, corresponding
	 * to a single vertex's location in three dimensional space.
	 * 
	 * @author Robert Smith
	 *
	 */
	private static class TransformationBlockAdapter
			extends XmlAdapter<String, double[][]> {

		/*
		 * (non-Javadoc)
		 * 
		 * @see javax.xml.bind.annotation.adapters.XmlAdapter#marshal(java.lang.
		 * Object)
		 */
		@Override
		public String marshal(double[][] v) throws Exception {

			// Start the output by pushing the first row to a new line
			String output = "\n";

			// Iterate through the 2D, * x 3 sized block
			for (int i = 0; i < v.length; i++) {
				for (int j = 0; j < 3; j++) {

					// After each number, insert a space
					output += v[i][j] + " ";
				}

				// After each third number, end the line
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
		public double[][] unmarshal(String v) throws Exception {

			// Split the input by whitespace
			String[] input = v.split("\\s+");

			// The array which will hold the coordinates, sized * x 3
			double[][] output = new double[input.length / 3][3];

			// Convert each string to a double and put it in the array
			for (int i = 0; i < input.length - 1; i++) {
				output[i / 3][i % 3] = Double.parseDouble(input[i + 1]);
			}

			return output;
		}
	}

	/**
	 * This class serves as a JAXB adapter for the transformations block of a
	 * MeshDescription's xml representation. It will convert a two dimensional
	 * array of doubles into a table of three doubles on each row, corresponding
	 * to a single vertex's location in three dimensional space.
	 * 
	 * @author Robert Smith
	 *
	 */
	private static class VerticesBlockAdapter
			extends XmlAdapter<String, double[][]> {

		/*
		 * (non-Javadoc)
		 * 
		 * @see javax.xml.bind.annotation.adapters.XmlAdapter#marshal(java.lang.
		 * Object)
		 */
		@Override
		public String marshal(double[][] v) throws Exception {

			// Start the output by pushing the first row to a new line
			String output = "\n";

			// Iterate through the 2D, * x 4 sized block
			for (int i = 0; i < v.length; i++) {
				for (int j = 0; j < 4; j++) {

					// After each number, insert a space
					output += v[i][j] + " ";
				}

				// After each fourth number, end the line
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
		public double[][] unmarshal(String v) throws Exception {

			// Split the input by whitespace
			String[] input = v.split("\\s+");

			// The array which will hold the coordinates, sized * x 4
			double[][] output = new double[input.length / 4][4];

			// Convert each string to a double and put it in the array
			for (int i = 0; i < input.length - 1; i++) {
				output[i / 4][i % 4] = Double.parseDouble(input[i + 1]);
			}

			return output;
		}
	}

	private class VertexLine {

		/**
		 * This vertex's ID
		 */
		public int ID;

		public double x;

		public double y;

		public double z;
	}

}
