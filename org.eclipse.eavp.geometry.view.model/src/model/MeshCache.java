/**
 */
package model;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.january.geometry.Triangle;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Mesh Cache</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * A MeshCache is a central repository for data objects of type T, where T is a data object specific to the rendering engine associated with the cache. The cache will be responsible for creating and maintaining these objects. The MeshCache will supply references to them to Meshes on request, allowing multiple Meshes to draw from the same data object as a part of the Flyweight pattern. 
 * <!-- end-model-doc -->
 *
 *
 * @see model.ModelPackage#getMeshCache()
 * @model
 * @generated
 */
public interface MeshCache<T> extends EObject {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Get the mesh for a given type, creating it if it does not already exist. It is assummed that all shapes of the type will make use of the same mesh, possibly transformed geometriclly, for rendering. For example, all shapes of type "RECTANGULAR_PRISM" might use the same eight pointed mesh, with each one distinguished by a differing position in three dimensional space or by changing its dimensions. The operation will return null for types which do not have such a prototypical mesh. In this case, getMesh(EEList<Triangle>) should be used instead.
	 * <!-- end-model-doc -->
	 * @model annotation="http://www.eclipse.org/emf/2002/GenModel body='// For the base implementation, assume that the cache was loaded during\r\n// initialization.\r\nreturn typeCache.get(type);'"
	 * @generated
	 */
	T getMesh(String type);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Get the mesh specified by the given triangles, creating it if it does not already exist. 
	 * <!-- end-model-doc -->
	 * @model trianglesMany="false"
	 *        annotation="http://www.eclipse.org/emf/2002/GenModel body='// The ID of the triangle list\r\nint ID = -1;\r\n\r\n// Whether the triangle list has been found in the cache\r\nboolean found = false;\r\n\r\n// Convert the list to a set\r\nSet<Triangle> triangleSet = new HashSet(triangles);\r\n\r\n// Check each set in the cache for a match\r\nfor (int i : sourceTriangleCache.keySet()) {\r\n\tID = i;\r\n\r\n\t// If a match is found, stop the search\r\n\tif (triangleSet.equals(sourceTriangleCache.get(i))) {\r\n\t\tfound = true;\r\n\t\tbreak;\r\n\t}\r\n}\r\n\r\n// If the list was found, return the mesh with the same ID\r\nif (found) {\r\n\treturn triangleCache.get(ID);\r\n}\r\n\r\n// If the list was not found, create a mesh based on it and insert both\r\n// into the caches.\r\nelse {\r\n\tsourceTriangleCache.put(ID, triangleSet);\r\n\tT mesh = createMesh(triangles);\r\n\ttriangleCache.put(ID, mesh);\r\n\treturn mesh;\r\n}'"
	 * @generated
	 */
	T getMesh(EList<Triangle> triangles);

} // MeshCache
