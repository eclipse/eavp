/**
 */
package model.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.impl.ENotificationImpl;

import model.ModelFactory;
import model.ModelPackage;
import model.OpacityDecorator;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>Opacity Decorator</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 * <li>{@link model.impl.OpacityDecoratorImpl#getOpacity <em>Opacity</em>}</li>
 * </ul>
 *
 * @generated
 */
public class OpacityDecoratorImpl<T> extends RenderObjectDecoratorImpl<T>
		implements OpacityDecorator<T> {
	/**
	 * The default value of the '{@link #getOpacity() <em>Opacity</em>}'
	 * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getOpacity()
	 * @generated
	 * @ordered
	 */
	protected static final double OPACITY_EDEFAULT = 100.0;

	/**
	 * The cached value of the '{@link #getOpacity() <em>Opacity</em>}'
	 * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getOpacity()
	 * @generated
	 * @ordered
	 */
	protected double opacity = OPACITY_EDEFAULT;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected OpacityDecoratorImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ModelPackage.Literals.OPACITY_DECORATOR;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public double getOpacity() {
		return opacity;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	@Override
	public void setOpacity(double newOpacity) {

		// Fail silently if the new value is already set
		if (newOpacity != opacity) {

			double oldOpacity = opacity;
			opacity = newOpacity;
			if (eNotificationRequired())
				eNotify(new ENotificationImpl(this, Notification.SET,
						ModelPackage.OPACITY_DECORATOR__OPACITY, oldOpacity,
						opacity));
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
		case ModelPackage.OPACITY_DECORATOR__OPACITY:
			return getOpacity();
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
		case ModelPackage.OPACITY_DECORATOR__OPACITY:
			setOpacity((Double) newValue);
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
		case ModelPackage.OPACITY_DECORATOR__OPACITY:
			setOpacity(OPACITY_EDEFAULT);
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
		case ModelPackage.OPACITY_DECORATOR__OPACITY:
			return opacity != OPACITY_EDEFAULT;
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
		result.append(" (opacity: ");
		result.append(opacity);
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
		OpacityDecorator<T> clone = ModelFactory.eINSTANCE
				.createOpacityDecorator();

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

		// If the source is not a OpacityDecorator, fail silently
		if (source instanceof OpacityDecorator) {

			// Copy the supertype data
			super.copy(source);

			// Copy the opacity
			opacity = ((OpacityDecorator) source).getOpacity();
		}
	}

} // OpacityDecoratorImpl
