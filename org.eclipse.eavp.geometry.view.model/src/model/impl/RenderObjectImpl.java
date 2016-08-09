/**
 */
package model.impl;

import java.lang.reflect.InvocationTargetException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;
import org.eclipse.january.geometry.INode;
import org.eclipse.swt.widgets.Display;

import model.IRenderElement;
import model.MeshCache;
import model.ModelFactory;
import model.ModelPackage;
import model.RenderObject;

/**
 * <!-- begin-user-doc --> An implementation of the model object ' <em><b>Render
 * Object</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 * <li>{@link model.impl.RenderObjectImpl#getMeshCache <em>Mesh Cache</em>}</li>
 * <li>{@link model.impl.RenderObjectImpl#getRender <em>Render</em>}</li>
 * <li>{@link model.impl.RenderObjectImpl#getSource <em>Source</em>}</li>
 * <li>{@link model.impl.RenderObjectImpl#getChildren <em>Children</em>}</li>
 * </ul>
 *
 * @generated
 */
public class RenderObjectImpl<T> extends MinimalEObjectImpl.Container
		implements RenderObject<T> {
	/**
	 * The cached value of the '{@link #getMeshCache() <em>Mesh Cache</em>}'
	 * reference. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getMeshCache()
	 * @generated
	 * @ordered
	 */
	protected MeshCache<?> meshCache;

	/**
	 * The cached value of the '{@link #getRender() <em>Render</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getRender()
	 * @generated
	 * @ordered
	 */
	protected T render;

	/**
	 * The map of rendering properties. Property names are the keys.
	 * 
	 * @generated NOT
	 */
	protected Map<String, Object> properties;

	/**
	 * The default value of the '{@link #getSource() <em>Source</em>}'
	 * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getSource()
	 * @generated
	 * @ordered
	 */
	protected static final INode SOURCE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getSource() <em>Source</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getSource()
	 * @generated
	 * @ordered
	 */
	protected INode source = SOURCE_EDEFAULT;

	/**
	 * The cached value of the '{@link #getChildren() <em>Children</em>}'
	 * containment reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getChildren()
	 * @generated
	 * @ordered
	 */
	protected EList<IRenderElement<T>> children;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	protected RenderObjectImpl() {
		super();

		properties = new HashMap<String, Object>();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ModelPackage.Literals.RENDER_OBJECT;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	@SuppressWarnings("unchecked")
	public MeshCache<?> getMeshCache() {
		if (meshCache != null && meshCache.eIsProxy()) {
			InternalEObject oldMeshCache = (InternalEObject) meshCache;
			meshCache = (MeshCache<?>) eResolveProxy(oldMeshCache);
			if (meshCache != oldMeshCache) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE,
							ModelPackage.RENDER_OBJECT__MESH_CACHE,
							oldMeshCache, meshCache));
			}
		}
		return meshCache;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public MeshCache<?> basicGetMeshCache() {
		return meshCache;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public void setMeshCache(MeshCache<?> newMeshCache) {

		// Fail silently if the new value is already set
		if (newMeshCache != meshCache) {

			MeshCache<?> oldMeshCache = meshCache;
			meshCache = newMeshCache;
			if (eNotificationRequired())
				eNotify(new ENotificationImpl(this, Notification.SET,
						ModelPackage.RENDER_OBJECT__MESH_CACHE, oldMeshCache,
						meshCache));
		}
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public T getRender() {
		return render;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void setRender(T newRender) {

		// Fail silently if the new value is already set
		if (newRender != render) {

			T oldRender = render;
			render = newRender;
			if (eNotificationRequired())
				eNotify(new ENotificationImpl(this, Notification.SET,
						ModelPackage.RENDER_OBJECT__RENDER, oldRender, render));
		}
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public INode getSource() {
		return source;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	@Override
	public void setSource(INode newSource) {

		// Fail silently if the new value is already set
		if (newSource != source) {

			INode oldSource = source;
			source = newSource;
			if (eNotificationRequired())
				eNotify(new ENotificationImpl(this, Notification.SET,
						ModelPackage.RENDER_OBJECT__SOURCE, oldSource, source));
		}
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EList<IRenderElement<T>> getChildren() {
		if (children == null) {
			children = new EObjectContainmentEList<IRenderElement<T>>(
					IRenderElement.class, this,
					ModelPackage.RENDER_OBJECT__CHILDREN);
		}
		return children;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	@Override
	public T getMesh() {

		// Handle any child nodes
		handleChildren(children);

		return render;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	@Override
	public INode getBase() {

		// A render object is at the bottom of the decorator hierarchy always
		// has the base data object as its source.
		return source;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	@Override
	public void handleChildren(EList<IRenderElement<T>> children) {
		//Nothing to do for the base implementation
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	@Override
	public Object getProperty(String property) {
		return properties.get(property);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	@Override
	public void setProperty(String property, Object value) {

		// Fail silently if the new value is already set
		if (properties.get(property) == null
				|| !properties.get(property).equals(value)) {

			properties.put(property, value);

			// Send a notification for the shape's set property method with the
			// property name placed instead of the previous value. By
			// convention,
			// decorator classes will be set to interpret this non-standard
			// notification message correctly and other listeners will ignore
			// it.
			eNotify(new ENotificationImpl(this, Notification.SET,
					ModelPackage.RENDER_OBJECT___SET_PROPERTY__STRING_OBJECT,
					property, value));
		}
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	@Override
	public void copy(Object source) {

		// If the source object is not a RenderObject, fail silently
		if (source instanceof RenderObject) {

			// Cast the source object
			RenderObjectImpl<T> castSource = (RenderObjectImpl<T>) source;

			// Copy the reference to the mesh cache
			meshCache = castSource.getMeshCache();

			// Remove the current rendering
			render = null;

			// Clear the properties map
			properties.clear();

			// Copy each property from the source
			for (String property : castSource.properties.keySet()) {
				properties.put(property, castSource.properties.get(property));
			}

			// Clone the INode on which the other object is based
			this.source = (INode) castSource.getSource().clone();
		}
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	@Override
	public Object clone() {

		// Create a new Render Object
		RenderObject<T> clone = ModelFactory.eINSTANCE.createRenderObject();

		// Make it a copy of this
		clone.copy(this);
		return clone;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd,
			int featureID, NotificationChain msgs) {
		switch (featureID) {
		case ModelPackage.RENDER_OBJECT__CHILDREN:
			return ((InternalEList<?>) getChildren()).basicRemove(otherEnd,
					msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
		case ModelPackage.RENDER_OBJECT__MESH_CACHE:
			if (resolve)
				return getMeshCache();
			return basicGetMeshCache();
		case ModelPackage.RENDER_OBJECT__RENDER:
			return getRender();
		case ModelPackage.RENDER_OBJECT__SOURCE:
			return getSource();
		case ModelPackage.RENDER_OBJECT__CHILDREN:
			return getChildren();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
		case ModelPackage.RENDER_OBJECT__MESH_CACHE:
			setMeshCache((MeshCache<?>) newValue);
			return;
		case ModelPackage.RENDER_OBJECT__RENDER:
			setRender((T) newValue);
			return;
		case ModelPackage.RENDER_OBJECT__SOURCE:
			setSource((INode) newValue);
			return;
		case ModelPackage.RENDER_OBJECT__CHILDREN:
			getChildren().clear();
			getChildren()
					.addAll((Collection<? extends IRenderElement<T>>) newValue);
			return;
		}
		super.eSet(featureID, newValue);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public void eUnset(int featureID) {
		switch (featureID) {
		case ModelPackage.RENDER_OBJECT__MESH_CACHE:
			setMeshCache((MeshCache<?>) null);
			return;
		case ModelPackage.RENDER_OBJECT__RENDER:
			setRender((T) null);
			return;
		case ModelPackage.RENDER_OBJECT__SOURCE:
			setSource(SOURCE_EDEFAULT);
			return;
		case ModelPackage.RENDER_OBJECT__CHILDREN:
			getChildren().clear();
			return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
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
			return SOURCE_EDEFAULT == null ? source != null
					: !SOURCE_EDEFAULT.equals(source);
		case ModelPackage.RENDER_OBJECT__CHILDREN:
			return children != null && !children.isEmpty();
		}
		return super.eIsSet(featureID);
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
		case ModelPackage.RENDER_OBJECT___GET_MESH:
			return getMesh();
		case ModelPackage.RENDER_OBJECT___GET_BASE:
			return getBase();
		case ModelPackage.RENDER_OBJECT___HANDLE_CHILDREN__ELIST:
			handleChildren((EList<IRenderElement<T>>) arguments.get(0));
			return null;
		case ModelPackage.RENDER_OBJECT___GET_PROPERTY__STRING:
			return getProperty((String) arguments.get(0));
		case ModelPackage.RENDER_OBJECT___SET_PROPERTY__STRING_OBJECT:
			setProperty((String) arguments.get(0), arguments.get(1));
			return null;
		case ModelPackage.RENDER_OBJECT___COPY__OBJECT:
			copy(arguments.get(0));
			return null;
		case ModelPackage.RENDER_OBJECT___CLONE:
			return clone();
		}
		return super.eInvoke(operationID, arguments);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public String toString() {
		if (eIsProxy())
			return super.toString();

		StringBuffer result = new StringBuffer(super.toString());
		result.append(" (render: ");
		result.append(render);
		result.append(", source: ");
		result.append(source);
		result.append(')');
		return result.toString();
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
} // RenderObjectImpl
