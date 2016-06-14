/**
 */
package model.impl;

import model.ModelPackage;
import model.WireframeDecorator;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Wireframe Decorator</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link model.impl.WireframeDecoratorImpl#isWireframe <em>Wireframe</em>}</li>
 * </ul>
 *
 * @generated
 */
public class WireframeDecoratorImpl<T> extends RenderObjectDecoratorImpl<T> implements WireframeDecorator<T> {
	/**
	 * The default value of the '{@link #isWireframe() <em>Wireframe</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isWireframe()
	 * @generated
	 * @ordered
	 */
	protected static final boolean WIREFRAME_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isWireframe() <em>Wireframe</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isWireframe()
	 * @generated
	 * @ordered
	 */
	protected boolean wireframe = WIREFRAME_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected WireframeDecoratorImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ModelPackage.Literals.WIREFRAME_DECORATOR;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isWireframe() {
		return wireframe;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setWireframe(boolean newWireframe) {
		boolean oldWireframe = wireframe;
		wireframe = newWireframe;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ModelPackage.WIREFRAME_DECORATOR__WIREFRAME, oldWireframe, wireframe));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
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
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case ModelPackage.WIREFRAME_DECORATOR__WIREFRAME:
				setWireframe((Boolean)newValue);
				return;
		}
		super.eSet(featureID, newValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
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
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
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
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String toString() {
		if (eIsProxy()) return super.toString();

		StringBuffer result = new StringBuffer(super.toString());
		result.append(" (wireframe: ");
		result.append(wireframe);
		result.append(')');
		return result.toString();
	}

} //WireframeDecoratorImpl
