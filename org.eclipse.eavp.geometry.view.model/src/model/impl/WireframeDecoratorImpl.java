/**
 */
package model.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.impl.ENotificationImpl;

import model.ModelFactory;
import model.ModelPackage;
import model.WireframeDecorator;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>Wireframe Decorator</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 * <li>{@link model.impl.WireframeDecoratorImpl#isWireframe
 * <em>Wireframe</em>}</li>
 * </ul>
 *
 * @generated
 */
public class WireframeDecoratorImpl<T> extends RenderObjectDecoratorImpl<T>
		implements WireframeDecorator<T> {
	/**
	 * The default value of the '{@link #isWireframe() <em>Wireframe</em>}'
	 * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #isWireframe()
	 * @generated
	 * @ordered
	 */
	protected static final boolean WIREFRAME_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isWireframe() <em>Wireframe</em>}'
	 * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #isWireframe()
	 * @generated
	 * @ordered
	 */
	protected boolean wireframe = WIREFRAME_EDEFAULT;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected WireframeDecoratorImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ModelPackage.Literals.WIREFRAME_DECORATOR;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public boolean isWireframe() {
		return wireframe;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	@Override
	public void setWireframe(boolean newWireframe) {

		// Fail silently if the new value is already set
		if (newWireframe != wireframe) {

			boolean oldWireframe = wireframe;
			wireframe = newWireframe;
			if (eNotificationRequired())
				eNotify(new ENotificationImpl(this, Notification.SET,
						ModelPackage.WIREFRAME_DECORATOR__WIREFRAME,
						oldWireframe, wireframe));
		}
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
		case ModelPackage.WIREFRAME_DECORATOR__WIREFRAME:
			return isWireframe();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
		case ModelPackage.WIREFRAME_DECORATOR__WIREFRAME:
			setWireframe((Boolean) newValue);
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
		case ModelPackage.WIREFRAME_DECORATOR__WIREFRAME:
			setWireframe(WIREFRAME_EDEFAULT);
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
		case ModelPackage.WIREFRAME_DECORATOR__WIREFRAME:
			return wireframe != WIREFRAME_EDEFAULT;
		}
		return super.eIsSet(featureID);
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
		result.append(" (wireframe: ");
		result.append(wireframe);
		result.append(')');
		return result.toString();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see model.impl.RenderObjectDecoratorImpl#clone()
	 */
	@Override
	public Object clone() {

		// Create a new opacity decorator
		WireframeDecorator<T> clone = ModelFactory.eINSTANCE
				.createWireframeDecorator();

		// Copy this object's data into the clone
		clone.copy(this);
		return clone;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see model.impl.RenderObjectDecoratorImpl#copy(java.lang.Object)
	 */
	@Override
	public void copy(Object source) {

		// If the source is not a WireframeDecorator, fail silently
		if (source instanceof WireframeDecorator) {

			// Copy the supertype data
			super.copy(source);

			// Copy the opacity
			wireframe = ((WireframeDecorator) source).isWireframe();
		}
	}

} // WireframeDecoratorImpl
