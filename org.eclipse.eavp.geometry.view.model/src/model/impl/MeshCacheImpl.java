/**
 */
package model.impl;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;
import org.eclipse.january.geometry.Triangle;
import org.eclipse.swt.widgets.Display;

import model.MeshCache;
import model.ModelPackage;

/**
 * <!-- begin-user-doc --> An implementation of the model object ' <em><b>Mesh
 * Cache</b></em>'. <!-- end-user-doc -->
 *
 * @generated
 */
public class MeshCacheImpl<T> extends MinimalEObjectImpl.Container
		implements MeshCache<T> {
	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	protected MeshCacheImpl() {
		super();

		// Initialize the cache maps
		typeCache = new HashMap<String, T>();
		sourceTriangleCache = new HashMap<Integer, Set<Triangle>>();
		triangleCache = new HashMap<Integer, T>();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ModelPackage.Literals.MESH_CACHE;
	}

	/**
	 * A map of data objects based on the type of the source object they
	 * represent. It is intended that this map be used for meshes in which all
	 * shapes of a given type can use transformed versions of the same mesh (ie
	 * all rectangular prisms using the same cube mesh as a base.)
	 * 
	 * @generated NOT
	 */
	protected HashMap<String, T> typeCache;

	/**
	 * A map from ID numbers to a list of triangles specifying an object's mesh.
	 * 
	 * @generated NOT
	 */
	protected HashMap<Integer, Set<Triangle>> sourceTriangleCache;

	/**
	 * A map from ID numbers to meshes for objects that specified their geometry
	 * through a list of triangles instead of by a type. This map should always
	 * be synchronized with sourceTriangleCache, such that triangleCache.get(i)
	 * will return a mesh corresponding to sourceTriangleCache.get(i) for any
	 * value i.
	 * 
	 * @generated NOT
	 */
	protected HashMap<Integer, T> triangleCache;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	@Override
	public T getMesh(String type) {

		// For the base implementation, assume that the cache was loaded during
		// initialization.
		return typeCache.get(type);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	@Override
	public T getMesh(EList<Triangle> triangles) {

		// The ID of the triangle list
		int ID = -1;

		// Whether the triangle list has been found in the cache
		boolean found = false;

		// Treat null as an empty list
		if (triangles == null) {
			triangles = new BasicEList<Triangle>();
		}

		// Convert the list to a set
		Set<Triangle> triangleSet = new HashSet(triangles);

		// Check each set in the cache for a match
		for (int i : sourceTriangleCache.keySet()) {
			ID = i;

			// If a match is found, stop the search
			if (triangleSet.equals(sourceTriangleCache.get(i))) {
				found = true;
				break;
			}
		}

		// If the list was found, return the mesh with the same ID
		if (found) {
			return triangleCache.get(ID);
		}

		// If the list was not found, create a mesh based on it and insert both
		// into the caches.
		else {
			sourceTriangleCache.put(ID, triangleSet);
			T mesh = createMesh(triangles);
			triangleCache.put(ID, mesh);
			return mesh;
		}
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	@SuppressWarnings("unchecked")
	public Object eInvoke(int operationID, EList<?> arguments)
			throws InvocationTargetException {
		switch (operationID) {
		case ModelPackage.MESH_CACHE___GET_MESH__STRING:
			return getMesh((String) arguments.get(0));
		case ModelPackage.MESH_CACHE___GET_MESH__ELIST:
			return getMesh((EList<Triangle>) arguments.get(0));
		}
		return super.eInvoke(operationID, arguments);
	}

	/**
	 * Create a mesh object for the rendering engine based on the provided
	 * triangle mesh.
	 * 
	 * The base implementation does nothing. It is intended that subclasses
	 * which can take triangles as input will implement their own functions that
	 * construct an appropriate data object to represent the mesh.
	 * 
	 * @param triangles
	 *            The mesh to be created, specified by a series of triangles.
	 * @return A mesh object for use with the graphics engine, based on the
	 *         input triangles.
	 * 
	 * @generated NOT
	 */
	protected T createMesh(EList<Triangle> triangles) {

		// Nothing to do
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.emf.common.notify.impl.BasicNotifierImpl#eNotify(org.eclipse.
	 * emf.common.notify.Notification)
	 */
	@Override
	public void eNotify(Notification notification) {
		// Check if a notification is required
		Adapter[] eAdapters = eBasicAdapterArray();
		if (eAdapters != null && eDeliver()) {

			// If this notification is on the UI thread, launch a new thread to
			// handle it
			Display currDisplay = Display.getCurrent();
			if (currDisplay != null && Thread.currentThread() == currDisplay.getThread()) {

				Thread updateThread = new Thread() {

					@Override
					public void run() {
						for (int i = 0, size = eAdapters.length; i < size; ++i) {
							eAdapters[i].notifyChanged(notification);
						}
					}
				};

				updateThread.run();

			}

			// If we are already off the UI thread, such as being called by a
			// thread created by some other object's eNotify(), then just notify
			// the adapters.
			else {
				for (int i = 0, size = eAdapters.length; i < size; ++i) {
					eAdapters[i].notifyChanged(notification);
				}
			}
		}
	}

} // MeshCacheImpl
