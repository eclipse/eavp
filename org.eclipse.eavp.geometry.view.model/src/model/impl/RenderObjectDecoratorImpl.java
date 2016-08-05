/**
 */
package model.impl;

import java.lang.reflect.InvocationTargetException;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.impl.AdapterImpl;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;
import org.eclipse.january.geometry.INode;
import org.eclipse.swt.widgets.Display;

import model.IRenderElement;
import model.ModelFactory;
import model.ModelPackage;
import model.RenderObjectDecorator;

/**
 * <!-- begin-user-doc --> An implementation of the model object ' <em><b>Render
 * Object Decorator</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 * <li>{@link model.impl.RenderObjectDecoratorImpl#getSource
 * <em>Source</em>}</li>
 * </ul>
 *
 * @generated
 */
public class RenderObjectDecoratorImpl<T> extends MinimalEObjectImpl.Container
		implements RenderObjectDecorator<T> {
	/**
	 * The cached value of the '{@link #getSource() <em>Source</em>}' reference.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getSource()
	 * @generated
	 * @ordered
	 */
	protected IRenderElement<T> source;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected RenderObjectDecoratorImpl() {
		super();
	}

	/**
	 * Handle an update from the RenderObject that is being decorated.
	 * 
	 * @param notification
	 *            A notification from the render object. It is expected that the
	 *            old value will be the property name and the new value the
	 *            property's new value.
	 * 
	 * @generated NOT
	 */
	protected void handleUpdate(Notification notification) {

		// Nothing to do for the base implementation but pass the update along
		eNotify(notification);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ModelPackage.Literals.RENDER_OBJECT_DECORATOR;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	@SuppressWarnings("unchecked")
	public IRenderElement<T> getSource() {
		if (source != null && source.eIsProxy()) {
			InternalEObject oldSource = (InternalEObject) source;
			source = (IRenderElement<T>) eResolveProxy(oldSource);
			if (source != oldSource) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE,
							ModelPackage.RENDER_OBJECT_DECORATOR__SOURCE,
							oldSource, source));
			}
		}
		return source;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public IRenderElement<T> basicGetSource() {
		return source;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	@Override
	public void setSource(IRenderElement<T> newSource) {

		// Fail silently if the new value is already set
		if (newSource != source) {

			IRenderElement<T> oldSource = source;
			source = newSource;

			// Register to listen to updates form the decorated object
			source.eAdapters().add(new AdapterImpl() {

				@Override
				public void notifyChanged(Notification notification) {
					handleUpdate(notification);
				}

			});

			if (eNotificationRequired())
				eNotify(new ENotificationImpl(this, Notification.SET,
						ModelPackage.RENDER_OBJECT_DECORATOR__SOURCE, oldSource,
						source));
		}
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	@Override
	public T getMesh() {

		// Delegate to the source by default.
		return source.getMesh();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	@Override
	public INode getBase() {

		// Get the base from the next lower element in the hierarchy
		return source.getBase();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	@Override
	public void handleChildren(EList<IRenderElement<T>> children) {

		// Delegate to the source object
		source.handleChildren(children);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	@Override
	public Object getProperty(String property) {
		return source.getProperty(property);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	@Override
	public void setProperty(String property, Object value) {
		source.setProperty(property, value);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	@Override
	public void copy(Object source) {

		// If the other object is not a RenderObjectDecorator, fail silently
		if (source instanceof RenderObjectDecorator) {

			// Set the source to a clone of the other object's source
			this.source = (IRenderElement<T>) ((RenderObjectDecorator<T>) source)
					.getSource().clone();
		}
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	@Override
	public Object clone() {

		// Create a new RenderObjectDecorator
		RenderObjectDecorator<T> clone = ModelFactory.eINSTANCE
				.createRenderObjectDecorator();

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
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
		case ModelPackage.RENDER_OBJECT_DECORATOR__SOURCE:
			if (resolve)
				return getSource();
			return basicGetSource();
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
		case ModelPackage.RENDER_OBJECT_DECORATOR__SOURCE:
			setSource((IRenderElement<T>) newValue);
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
		case ModelPackage.RENDER_OBJECT_DECORATOR__SOURCE:
			setSource((IRenderElement<T>) null);
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
		case ModelPackage.RENDER_OBJECT_DECORATOR__SOURCE:
			return source != null;
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
		case ModelPackage.RENDER_OBJECT_DECORATOR___GET_MESH:
			return getMesh();
		case ModelPackage.RENDER_OBJECT_DECORATOR___GET_BASE:
			return getBase();
		case ModelPackage.RENDER_OBJECT_DECORATOR___HANDLE_CHILDREN__ELIST:
			handleChildren((EList<IRenderElement<T>>) arguments.get(0));
			return null;
		case ModelPackage.RENDER_OBJECT_DECORATOR___GET_PROPERTY__STRING:
			return getProperty((String) arguments.get(0));
		case ModelPackage.RENDER_OBJECT_DECORATOR___SET_PROPERTY__STRING_OBJECT:
			setProperty((String) arguments.get(0), arguments.get(1));
			return null;
		case ModelPackage.RENDER_OBJECT_DECORATOR___COPY__OBJECT:
			copy(arguments.get(0));
			return null;
		case ModelPackage.RENDER_OBJECT_DECORATOR___CLONE:
			return clone();
		}
		return super.eInvoke(operationID, arguments);
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
			if (currDisplay != null
					&& Thread.currentThread() == currDisplay.getThread()) {

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

} // RenderObjectDecoratorImpl
