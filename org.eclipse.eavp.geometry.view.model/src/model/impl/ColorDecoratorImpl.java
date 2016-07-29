/**
 */
package model.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.impl.ENotificationImpl;

import javafx.scene.paint.PhongMaterial;
import model.ColorDecorator;
import model.ModelFactory;
import model.ModelPackage;

/**
 * <!-- begin-user-doc --> An implementation of the model object ' <em><b>Color
 * Decorator</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 * <li>{@link model.impl.ColorDecoratorImpl#getRed <em>Red</em>}</li>
 * <li>{@link model.impl.ColorDecoratorImpl#getGreen <em>Green</em>}</li>
 * <li>{@link model.impl.ColorDecoratorImpl#getBlue <em>Blue</em>}</li>
 * </ul>
 *
 * @generated
 */
public class ColorDecoratorImpl<T> extends RenderObjectDecoratorImpl<T>
		implements ColorDecorator<T> {

	/**
	 * The material used for this decorator. If this is not set, then the
	 * default material with a color given by the red, green, and blue channels
	 * will be used instead.
	 */
	private PhongMaterial material;

	/**
	 * The default value of the '{@link #getRed() <em>Red</em>}' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getRed()
	 * @generated
	 * @ordered
	 */
	protected static final int RED_EDEFAULT = 0;

	/**
	 * The cached value of the '{@link #getRed() <em>Red</em>}' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getRed()
	 * @generated
	 * @ordered
	 */
	protected int red = RED_EDEFAULT;

	/**
	 * The default value of the '{@link #getGreen() <em>Green</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getGreen()
	 * @generated
	 * @ordered
	 */
	protected static final int GREEN_EDEFAULT = 0;

	/**
	 * The cached value of the '{@link #getGreen() <em>Green</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getGreen()
	 * @generated
	 * @ordered
	 */
	protected int green = GREEN_EDEFAULT;

	/**
	 * The default value of the '{@link #getBlue() <em>Blue</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getBlue()
	 * @generated
	 * @ordered
	 */
	protected static final int BLUE_EDEFAULT = 0;

	/**
	 * The cached value of the '{@link #getBlue() <em>Blue</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getBlue()
	 * @generated
	 * @ordered
	 */
	protected int blue = BLUE_EDEFAULT;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected ColorDecoratorImpl() {
		super();
		material = null;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ModelPackage.Literals.COLOR_DECORATOR;
	}

	/**
	 * Gets the material used for this decorator. If this is null, will rather
	 * use the red, green, and blue channels set for this decorator.
	 * 
	 * @return The material for the decorator
	 */
	public PhongMaterial getMaterial() {
		return material;
	}

	/**
	 * Sets the phong material used for this decorator. Setting this to null
	 * will allow the red, green, and blue channels to control the material
	 * color.
	 * 
	 * @param newMaterial
	 *            The material to use for this decorator
	 */
	public void setMaterial(PhongMaterial newMaterial) {
		material = newMaterial;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public int getRed() {
		return red;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	@Override
	public void setRed(int newRed) {

		// Fail silently if the new value is already set
		if (newRed != red) {

			int oldRed = red;
			red = newRed;
			if (eNotificationRequired())
				eNotify(new ENotificationImpl(this, Notification.SET,
						ModelPackage.COLOR_DECORATOR__RED, oldRed, red));
		}
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public int getGreen() {
		return green;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	@Override
	public void setGreen(int newGreen) {

		// Fail silently if the new value is already set
		if (newGreen != green) {

			int oldGreen = green;
			green = newGreen;
			if (eNotificationRequired())
				eNotify(new ENotificationImpl(this, Notification.SET,
						ModelPackage.COLOR_DECORATOR__GREEN, oldGreen, green));
		}
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public int getBlue() {
		return blue;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	@Override
	public void setBlue(int newBlue) {

		if (newBlue != blue) {

			int oldBlue = blue;
			blue = newBlue;
			if (eNotificationRequired())
				eNotify(new ENotificationImpl(this, Notification.SET,
						ModelPackage.COLOR_DECORATOR__BLUE, oldBlue, blue));
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
		case ModelPackage.COLOR_DECORATOR__RED:
			return getRed();
		case ModelPackage.COLOR_DECORATOR__GREEN:
			return getGreen();
		case ModelPackage.COLOR_DECORATOR__BLUE:
			return getBlue();
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
		case ModelPackage.COLOR_DECORATOR__RED:
			setRed((Integer) newValue);
			return;
		case ModelPackage.COLOR_DECORATOR__GREEN:
			setGreen((Integer) newValue);
			return;
		case ModelPackage.COLOR_DECORATOR__BLUE:
			setBlue((Integer) newValue);
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
		case ModelPackage.COLOR_DECORATOR__RED:
			setRed(RED_EDEFAULT);
			return;
		case ModelPackage.COLOR_DECORATOR__GREEN:
			setGreen(GREEN_EDEFAULT);
			return;
		case ModelPackage.COLOR_DECORATOR__BLUE:
			setBlue(BLUE_EDEFAULT);
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
		case ModelPackage.COLOR_DECORATOR__RED:
			return red != RED_EDEFAULT;
		case ModelPackage.COLOR_DECORATOR__GREEN:
			return green != GREEN_EDEFAULT;
		case ModelPackage.COLOR_DECORATOR__BLUE:
			return blue != BLUE_EDEFAULT;
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
		result.append(" (red: ");
		result.append(red);
		result.append(", green: ");
		result.append(green);
		result.append(", blue: ");
		result.append(blue);
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

		// Create a new color decorator
		ColorDecorator<T> clone = ModelFactory.eINSTANCE.createColorDecorator();

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

		// If the source is not a ColorDecorator, fail silently
		if (source instanceof ColorDecorator) {

			// Copy the supertype data
			super.copy(source);

			// Cast the other object
			ColorDecorator<T> castSource = (ColorDecorator<T>) source;

			// Copy the color fields
			red = castSource.getRed();
			green = castSource.getGreen();
			blue = castSource.getBlue();
		}
	}

} // ColorDecoratorImpl
