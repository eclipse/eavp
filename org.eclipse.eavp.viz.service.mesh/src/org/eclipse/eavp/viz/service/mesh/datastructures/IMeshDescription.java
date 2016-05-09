package org.eclipse.eavp.viz.service.mesh.datastructures;

import java.util.ArrayList;
import java.util.HashMap;

import org.eclipse.eavp.viz.modeling.base.IController;
import org.eclipse.eavp.viz.modeling.factory.IControllerProviderFactory;
import org.eclipse.eavp.viz.service.mesh.datastructures.MeshDescription.FaceLine;
import org.eclipse.eavp.viz.service.mesh.datastructures.MeshDescription.VertexLine;

public interface IMeshDescription {

	/**
	 * Getter method for the edges.
	 * 
	 * @return An array of global ids for the edges, along with their endpoints
	 *         as indices into the vertices array
	 */
	int[] getEdges();

	/**
	 * Getter method for the faces.
	 * 
	 * @return An array of global ids for the faces, along with their
	 *         constituent edges as indices into the edges array
	 */
	int[] getFaces();

	/**
	 * Getter method for the edge properties.
	 * 
	 * @return A list of maps between integer ids for edges and their edge
	 *         properties, one for each face in the faces array
	 */
	ArrayList<HashMap<Integer, EdgeProperties>> getEdgeProperties();

	/**
	 * Getter method for the polygon properties
	 * 
	 * @return A list of all the PolygonProperties in the hierarchy in the same
	 *         order as their corresponding polygons in the faces array
	 */
	ArrayList<PolygonProperties> getPolygonProperties();

	/**
	 * Getter method for the transformation.
	 * 
	 * @return An array of numbers representing the spatial data for a modeling
	 *         object. Each object's data begins at location (12 * their global
	 *         ID) and is stored as three values representing the x, y, and z
	 *         directions for each of the four attributes (rotation, scale,
	 *         skew, and translation).
	 */
	double[][] getTransformations();

	/**
	 * Getter method for the vertices.
	 * 
	 * @return An array of global ids for the vertices
	 */
	int[] getVertices();

	/**
	 * Setter method for the edges.
	 * 
	 * @param edges
	 *            An array of global ids for the edges, along with their
	 *            endpoints as indices into the vertices array
	 */
	void setEdges(int[] edges);

	/**
	 * Setter method for the faces.
	 * 
	 * @param faces
	 *            An array of global ids for the faces, along with their
	 *            constituent edges as indices into the edges array
	 */
	void setFaces(int[] faces);

	/**
	 * Setter method for the edge properties.
	 * 
	 * @param properties
	 *            A list of maps between integer ids for edges and their edge
	 *            properties, one for each face in the faces array
	 */
	void setEdgeProperties(
			ArrayList<HashMap<Integer, EdgeProperties>> properties);

	/**
	 * Setter method for the polygon properties.
	 * 
	 * @param polygonProperties
	 *            A list of the PolygonProperties in the mesh, in the same order
	 *            as their corresponding polygons are in the faces array.
	 */
	void setPolygonProperties(ArrayList<PolygonProperties> polygonProperties);

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
	void setTransformations(double[][] transformations);

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
	IController unpack(IControllerProviderFactory factory);

	/**
	 * Get the information about the mesh's faces formatted into a list of
	 * FaceLines.
	 * 
	 * This method is used for persistence by JAXB and is not intended to be
	 * called.
	 * 
	 * @return A list of all the lines in an xml block defininf the faces
	 */
	ArrayList<FaceLine> getFacesBlock();

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
	void setFacesBlock(ArrayList<FaceLine> block);

	/**
	 * Get the information about the mesh's vertices formatted into a block of
	 * VertexLines.
	 * 
	 * This method is used for persistence with JAXB and is not intended to be
	 * called.
	 * 
	 * @return A list of all the lines in the xml block defining the vertices
	 */
	ArrayList<VertexLine> getVerticesBlock();

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
	void setVerticesBlock(ArrayList<VertexLine> block);

	/**
	 * Create a deep copy duplicate of this object.
	 * 
	 * @return An IMeshDescription with identical values as this object.
	 */
	Object clone();

	/**
	 * Copy the source object's data into this, making it into a copy.
	 * 
	 * @param source
	 *            The object of which this should be made into a copy
	 */
	void copy(IMeshDescription source);

}