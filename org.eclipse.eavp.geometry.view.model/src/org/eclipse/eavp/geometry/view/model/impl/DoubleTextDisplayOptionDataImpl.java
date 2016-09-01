/**
 */
package org.eclipse.eavp.geometry.view.model.impl;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.eavp.geometry.view.model.DisplayOption;
import org.eclipse.eavp.geometry.view.model.DisplayOptionType;
import org.eclipse.eavp.geometry.view.model.DoubleTextDisplayOptionData;
import org.eclipse.eavp.geometry.view.model.ModelPackage;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

/**
 * <!-- begin-user-doc --> An implementation of the model object '<em><b>Double
 * Text Display Option Data</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 * <li>{@link org.eclipse.eavp.geometry.view.model.impl.DoubleTextDisplayOptionDataImpl#getDisplayOption
 * <em>Display Option</em>}</li>
 * <li>{@link org.eclipse.eavp.geometry.view.model.impl.DoubleTextDisplayOptionDataImpl#getPropertyToValueMap
 * <em>Property To Value Map</em>}</li>
 * <li>{@link org.eclipse.eavp.geometry.view.model.impl.DoubleTextDisplayOptionDataImpl#getPropertyMinValues
 * <em>Property Min Values</em>}</li>
 * <li>{@link org.eclipse.eavp.geometry.view.model.impl.DoubleTextDisplayOptionDataImpl#getPropertyMaxValues
 * <em>Property Max Values</em>}</li>
 * </ul>
 *
 * @generated
 */
public class DoubleTextDisplayOptionDataImpl extends
		MinimalEObjectImpl.Container implements DoubleTextDisplayOptionData {
	/**
	 * The cached value of the '{@link #getDisplayOption() <em>Display
	 * Option</em>}' reference. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getDisplayOption()
	 * @generated
	 * @ordered
	 */
	protected DisplayOption<?> displayOption;

	/**
	 * The cached value of the '{@link #getPropertyToValueMap() <em>Property To
	 * Value Map</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getPropertyToValueMap()
	 * @generated
	 * @ordered
	 */
	protected Map<String, Double> propertyToValueMap;

	/**
	 * A map from property names to their minimum values.
	 */
	protected Map<String, Double> propertyMinValues;

	/**
	 * A map from property names to their maximum values.
	 */
	protected Map<String, Double> propertyMaxValues;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected DoubleTextDisplayOptionDataImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ModelPackage.Literals.DOUBLE_TEXT_DISPLAY_OPTION_DATA;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public DisplayOption<?> getDisplayOption() {
		if (displayOption != null && displayOption.eIsProxy()) {
			InternalEObject oldDisplayOption = (InternalEObject) displayOption;
			displayOption = (DisplayOption<?>) eResolveProxy(oldDisplayOption);
			if (displayOption != oldDisplayOption) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE,
							ModelPackage.DOUBLE_TEXT_DISPLAY_OPTION_DATA__DISPLAY_OPTION,
							oldDisplayOption, displayOption));
			}
		}
		return displayOption;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public DisplayOption<?> basicGetDisplayOption() {
		return displayOption;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public void setDisplayOption(DisplayOption<?> newDisplayOption) {
		DisplayOption<?> oldDisplayOption = displayOption;
		displayOption = newDisplayOption;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET,
					ModelPackage.DOUBLE_TEXT_DISPLAY_OPTION_DATA__DISPLAY_OPTION,
					oldDisplayOption, displayOption));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	@Override
	public Map<String, Double> getPropertyToValueMap() {
		if (propertyToValueMap != null) {
			return propertyToValueMap;
		} else {
			propertyToValueMap = new HashMap<String, Double>();
			return propertyToValueMap;
		}
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public void setPropertyToValueMap(
			Map<String, Double> newPropertyToValueMap) {
		Map<String, Double> oldPropertyToValueMap = propertyToValueMap;
		propertyToValueMap = newPropertyToValueMap;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET,
					ModelPackage.DOUBLE_TEXT_DISPLAY_OPTION_DATA__PROPERTY_TO_VALUE_MAP,
					oldPropertyToValueMap, propertyToValueMap));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	@Override
	public Map<String, Double> getPropertyMinValues() {
		if (propertyMinValues == null) {
			propertyMinValues = new HashMap<String, Double>();
		}
		return propertyMinValues;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public void setPropertyMinValues(Map<String, Double> newPropertyMinValues) {
		Map<String, Double> oldPropertyMinValues = propertyMinValues;
		propertyMinValues = newPropertyMinValues;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET,
					ModelPackage.DOUBLE_TEXT_DISPLAY_OPTION_DATA__PROPERTY_MIN_VALUES,
					oldPropertyMinValues, propertyMinValues));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	@Override
	public Map<String, Double> getPropertyMaxValues() {
		if (propertyMaxValues == null) {
			propertyMaxValues = new HashMap<String, Double>();
		}
		return propertyMaxValues;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public void setPropertyMaxValues(Map<String, Double> newPropertyMaxValues) {
		Map<String, Double> oldPropertyMaxValues = propertyMaxValues;
		propertyMaxValues = newPropertyMaxValues;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET,
					ModelPackage.DOUBLE_TEXT_DISPLAY_OPTION_DATA__PROPERTY_MAX_VALUES,
					oldPropertyMaxValues, propertyMaxValues));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	@Override
	public DisplayOptionType getDisplayOptionType() {
		return DisplayOptionType.DOUBLE_TEXT;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
		case ModelPackage.DOUBLE_TEXT_DISPLAY_OPTION_DATA__DISPLAY_OPTION:
			if (resolve)
				return getDisplayOption();
			return basicGetDisplayOption();
		case ModelPackage.DOUBLE_TEXT_DISPLAY_OPTION_DATA__PROPERTY_TO_VALUE_MAP:
			return getPropertyToValueMap();
		case ModelPackage.DOUBLE_TEXT_DISPLAY_OPTION_DATA__PROPERTY_MIN_VALUES:
			return getPropertyMinValues();
		case ModelPackage.DOUBLE_TEXT_DISPLAY_OPTION_DATA__PROPERTY_MAX_VALUES:
			return getPropertyMaxValues();
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
		case ModelPackage.DOUBLE_TEXT_DISPLAY_OPTION_DATA__DISPLAY_OPTION:
			setDisplayOption((DisplayOption<?>) newValue);
			return;
		case ModelPackage.DOUBLE_TEXT_DISPLAY_OPTION_DATA__PROPERTY_TO_VALUE_MAP:
			setPropertyToValueMap((Map<String, Double>) newValue);
			return;
		case ModelPackage.DOUBLE_TEXT_DISPLAY_OPTION_DATA__PROPERTY_MIN_VALUES:
			setPropertyMinValues((Map<String, Double>) newValue);
			return;
		case ModelPackage.DOUBLE_TEXT_DISPLAY_OPTION_DATA__PROPERTY_MAX_VALUES:
			setPropertyMaxValues((Map<String, Double>) newValue);
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
		case ModelPackage.DOUBLE_TEXT_DISPLAY_OPTION_DATA__DISPLAY_OPTION:
			setDisplayOption((DisplayOption<?>) null);
			return;
		case ModelPackage.DOUBLE_TEXT_DISPLAY_OPTION_DATA__PROPERTY_TO_VALUE_MAP:
			setPropertyToValueMap((Map<String, Double>) null);
			return;
		case ModelPackage.DOUBLE_TEXT_DISPLAY_OPTION_DATA__PROPERTY_MIN_VALUES:
			setPropertyMinValues((Map<String, Double>) null);
			return;
		case ModelPackage.DOUBLE_TEXT_DISPLAY_OPTION_DATA__PROPERTY_MAX_VALUES:
			setPropertyMaxValues((Map<String, Double>) null);
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
		case ModelPackage.DOUBLE_TEXT_DISPLAY_OPTION_DATA__DISPLAY_OPTION:
			return displayOption != null;
		case ModelPackage.DOUBLE_TEXT_DISPLAY_OPTION_DATA__PROPERTY_TO_VALUE_MAP:
			return propertyToValueMap != null;
		case ModelPackage.DOUBLE_TEXT_DISPLAY_OPTION_DATA__PROPERTY_MIN_VALUES:
			return propertyMinValues != null;
		case ModelPackage.DOUBLE_TEXT_DISPLAY_OPTION_DATA__PROPERTY_MAX_VALUES:
			return propertyMaxValues != null;
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public Object eInvoke(int operationID, EList<?> arguments)
			throws InvocationTargetException {
		switch (operationID) {
		case ModelPackage.DOUBLE_TEXT_DISPLAY_OPTION_DATA___GET_DISPLAY_OPTION_TYPE:
			return getDisplayOptionType();
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
		result.append(" (propertyToValueMap: ");
		result.append(propertyToValueMap);
		result.append(", propertyMinValues: ");
		result.append(propertyMinValues);
		result.append(", propertyMaxValues: ");
		result.append(propertyMaxValues);
		result.append(')');
		return result.toString();
	}

} // DoubleTextDisplayOptionDataImpl
