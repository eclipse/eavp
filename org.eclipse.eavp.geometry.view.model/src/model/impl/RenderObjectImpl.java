/**
 */
package model.impl;

import java.lang.reflect.InvocationTargetException;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

import geometry.INode;
import model.MeshCache;
import model.ModelPackage;
import model.RenderObject;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>Render Object</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link model.impl.RenderObjectImpl#getMeshCache <em>Mesh Cache</em>}</li>
 *   <li>{@link model.impl.RenderObjectImpl#getRender <em>Render</em>}</li>
 *   <li>{@link model.impl.RenderObjectImpl#getSource <em>Source</em>}</li>
 * </ul>
 *
 * @generated
 */
public class RenderObjectImpl<T> extends MinimalEObjectImpl.Container
		implements RenderObject<T> {
	/**
	 * The cached value of the '{@link #getMeshCache() <em>Mesh Cache</em>}' reference.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #getMeshCache()
	 * @generated
	 * @ordered
	 */
	protected MeshCache<?> meshCache;

	/**
	 * The cached value of the '{@link #getRender() <em>Render</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #getRender()
	 * @generated
	 * @ordered
	 */
	protected T render;

	/**
	 * The default value of the '{@link #getSource() <em>Source</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #getSource()
	 * @generated
	 * @ordered
	 */
	protected static final INode SOURCE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getSource() <em>Source</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #getSource()
	 * @generated
	 * @ordered
	 */
	protected INode source = SOURCE_EDEFAULT;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	protected RenderObjectImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ModelPackage.Literals.RENDER_OBJECT;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	@SuppressWarnings("unchecked")
	public MeshCache<?> getMeshCache() {
		if (meshCache != null && meshCache.eIsProxy()) {
			InternalEObject oldMeshCache = (InternalEObject)meshCache;
			meshCache = (MeshCache<?>)eResolveProxy(oldMeshCache);
			if (meshCache != oldMeshCache) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, ModelPackage.RENDER_OBJECT__MESH_CACHE, oldMeshCache, meshCache));
			}
		}
		return meshCache;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public MeshCache<?> basicGetMeshCache() {
		return meshCache;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setMeshCache(MeshCache<?> newMeshCache) {
		MeshCache<?> oldMeshCache = meshCache;
		meshCache = newMeshCache;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ModelPackage.RENDER_OBJECT__MESH_CACHE, oldMeshCache, meshCache));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public T getRender() {
		return render;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public void setRender(T newRender) {
		T oldRender = render;
		render = newRender;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ModelPackage.RENDER_OBJECT__RENDER, oldRender, render));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public INode getSource() {
		return source;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setSource(INode newSource) {
		INode oldSource = source;
		source = newSource;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ModelPackage.RENDER_OBJECT__SOURCE, oldSource, source));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	@Override
	public T getMesh() {
		return render;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case ModelPackage.RENDER_OBJECT__MESH_CACHE:
				if (resolve) return getMeshCache();
				return basicGetMeshCache();
			case ModelPackage.RENDER_OBJECT__RENDER:
				return getRender();
			case ModelPackage.RENDER_OBJECT__SOURCE:
				return getSource();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case ModelPackage.RENDER_OBJECT__MESH_CACHE:
				setMeshCache((MeshCache<?>)newValue);
				return;
			case ModelPackage.RENDER_OBJECT__RENDER:
				setRender((T)newValue);
				return;
			case ModelPackage.RENDER_OBJECT__SOURCE:
				setSource((INode)newValue);
				return;
		}
		super.eSet(featureID, newValue);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eUnset(int featureID) {
		switch (featureID) {
			case ModelPackage.RENDER_OBJECT__MESH_CACHE:
				setMeshCache((MeshCache<?>)null);
				return;
			case ModelPackage.RENDER_OBJECT__RENDER:
				setRender((T)null);
				return;
			case ModelPackage.RENDER_OBJECT__SOURCE:
				setSource(SOURCE_EDEFAULT);
				return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean eIsSet(int featureID) {
		switch (featureID) {
			case ModelPackage.RENDER_OBJECT__MESH_CACHE:
				return meshCache != null;
			case ModelPackage.RENDER_OBJECT__RENDER:
				return render != null;
			case ModelPackage.RENDER_OBJECT__SOURCE:
				return SOURCE_EDEFAULT == null ? source != null : !SOURCE_EDEFAULT.equals(source);
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eInvoke(int operationID, EList<?> arguments)
			throws InvocationTargetException {
		switch (operationID) {
			case ModelPackage.RENDER_OBJECT___GET_MESH:
				return getMesh();
		}
		return super.eInvoke(operationID, arguments);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String toString() {
		if (eIsProxy()) return super.toString();

		StringBuffer result = new StringBuffer(super.toString());
		result.append(" (render: ");
		result.append(render);
		result.append(", source: ");
		result.append(source);
		result.append(')');
		return result.toString();
	}
} // RenderObjectImpl
