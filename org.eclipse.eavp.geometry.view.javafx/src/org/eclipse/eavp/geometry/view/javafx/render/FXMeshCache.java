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
package org.eclipse.eavp.geometry.view.javafx.render;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.eavp.geometry.view.model.impl.MeshCacheImpl;
import org.eclipse.emf.common.util.EList;
import org.eclipse.january.geometry.Triangle;
import org.eclipse.january.geometry.Vertex;

import javafx.scene.shape.TriangleMesh;

/**
 * A cache for triangle meshes for JavaFX. An FXMeshCache will handle two cases.
 * 
 * In the first, the cache is supplied with a String for a type of mesh. The
 * cache should supply the single mesh used for all meshes of that type, as
 * specified by an FXShapeMesh.
 * 
 * In the second, the cache is supplied with a set of Triangles. The cache
 * should either retrieve the existing TriangleMesh for that list or else it
 * will be responsible for creating a new TriangleMesh based on the geometry of
 * the input triangles.
 * 
 * @author Robert Smith
 *
 */
public class FXMeshCache extends MeshCacheImpl<TriangleMesh> {

	/**
	 * The nullary constructor.
	 */
	public FXMeshCache() {
		super();

		// Populate the type cache with the IFXShapeMeshes from this package.
		typeCache.put("cube", new FXCubeMesh().getMesh());
		typeCache.put("cylinder", new FXCylinderMesh().getMesh());
		typeCache.put("sphere", new FXSphereMesh().getMesh());
		typeCache.put("tube", new FXTubeMesh().getMesh());
	}

	/**
	 * Register each available mesh provided through OSGI. This function is
	 * intended solely for use by the OSGI framework.
	 * 
	 * @param mesh
	 *            The new mesh to be registered.
	 */
	public void register(IFXShapeMesh mesh) {
		typeCache.put(mesh.getType(), mesh.getMesh());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * model.impl.MeshCacheImpl#createMesh(org.eclipse.emf.common.util.EList)
	 */
	@Override
	protected TriangleMesh createMesh(EList<Triangle> triangles) {

		// The mesh being constructed
		TriangleMesh mesh = new TriangleMesh();

		// A map from a vertex's coordinates to its ID. The nested maps give the
		// x, y, and z coordinates in order.
		HashMap<Double, Map<Double, Map<Double, Integer>>> xCoords = new HashMap<Double, Map<Double, Map<Double, Integer>>>();

		// The lists of points in the mesh, in the order x, y, and z coordinates
		// for the first point, x, y, and z for the second point, etc.
		ArrayList<Float> pointsList = new ArrayList<Float>();
		ArrayList<Integer> facesList = new ArrayList<Integer>();

		// The next unassigned point ID.
		int nextPointID = 0;

		// Add each triangle to the mesh
		for (Triangle tri : triangles) {

			// Add each vertex to the face
			for (int i = 0; i < 3; i++) {
				Vertex vert = tri.getVertices().get(i);

				// Get the vertex's x coordinate
				Map<Double, Map<Double, Integer>> yCoords = xCoords
						.get(vert.getX());

				// If the x coordinate was recognized, continue
				if (yCoords != null) {

					// Get the vertex's y coordinate.
					Map<Double, Integer> zCoords = yCoords.get(vert.getY());

					// If the y coordinate was recognized, continue
					if (zCoords != null) {

						// Get the vertex's ID
						Integer ID = zCoords.get(vert.getZ());

						// If the z coordinate was recognized, add the ID to the
						// face
						if (ID != null) {
							facesList.add(ID);
						} else {

							// If the vertex is not recognized, add it
							zCoords.put(vert.getZ(), nextPointID);
							addVertex(vert, pointsList, facesList, nextPointID);
							nextPointID++;

						}

					} else {

						// If the vertex is not recognized, add it
						zCoords = new HashMap<Double, Integer>();
						zCoords.put(vert.getZ(), nextPointID);
						addVertex(vert, pointsList, facesList, nextPointID);
						nextPointID++;
					}

					// Save the modified z coordinate map
					yCoords.put(vert.getY(), zCoords);
				} else {

					// If the vertex was not recognized, add it
					Map<Double, Integer> zCoords = new HashMap<Double, Integer>();
					zCoords.put(vert.getZ(), nextPointID);
					addVertex(vert, pointsList, facesList, nextPointID);
					yCoords = new HashMap<Double, Map<Double, Integer>>();
					yCoords.put(vert.getY(), zCoords);
					nextPointID++;
				}

				// Save the modified y coordinate map
				xCoords.put(vert.getX(), yCoords);

				// Add one of the three default texture vertices
				facesList.add(i);
			}
		}

		// FIXME When JavaFX properly supports turning cull faces off (ie the
		// normally culled face is no longer displayed as pure black regardless
		// of material color or lighting) or if it is decided we will only
		// accept meshes specified according to the right hand rule, remove this
		// section.
		// A list of the faces specified in the opposite direction. Since we
		// know nothing about the topology of the mesh, we cannot say which side
		// of any triangle needs to be rendered. Thus we create two JavaFX
		// triangles for every real one, each facing the opposite direction, so
		// that the mesh will be visible either way.
		ArrayList<Integer> reverseFaces = (ArrayList<Integer>) facesList
				.clone();

		// Switch the first two vertices of every face
		for (int i = 0; i < facesList.size(); i++) {

			// Faces are specified as blocks of six numbers: vertex1, 0,
			// vertex2, 0, vertex3, 0
			if (i % 6 == 0) {
				reverseFaces.set(i, facesList.get(i + 2));
			} else if (i % 6 == 2) {
				reverseFaces.set(i, facesList.get(i - 2));
			}
		}

		// Append the reversed face list to the original
		facesList.addAll(reverseFaces);

		// Convert the list to an array
		float[] points = new float[pointsList.size()];
		for (int i = 0; i < pointsList.size(); i++) {
			points[i] = pointsList.get(i);
		}

		// Convert the list to an array
		int[] faces = new int[facesList.size()];
		for (int i = 0; i < facesList.size(); i++) {
			faces[i] = facesList.get(i);
		}

		// Set all the points on the mesh and faces to the mesh, setting the
		// texture to simply be a triangle cut from one side of the texture
		// image to the center point of the opposite side.
		mesh.getPoints().setAll(points);
		mesh.getTexCoords().setAll(new float[] { 0.5f, 0.5f, 0f, 1f, 1f, 1f });
		mesh.getFaces().setAll(faces);

		return mesh;
	}

	/**
	 * Add the vertex to the lists with the given ID
	 * 
	 * @param vert
	 *            The vertex to add to the lists
	 * @param pointsList
	 *            The list of points
	 * @param facesList
	 *            The list of faces
	 * @param nextPointID
	 *            The ID number this vertex will be assigned
	 */
	private void addVertex(Vertex vert, ArrayList<Float> pointsList,
			ArrayList<Integer> facesList, int nextPointID) {

		// Add the vertex's three coordinates to the points list
		pointsList.add((float) vert.getX());
		pointsList.add((float) vert.getY());
		pointsList.add((float) vert.getZ());

		// Add the ID to the face list
		facesList.add(nextPointID);
	}

}
