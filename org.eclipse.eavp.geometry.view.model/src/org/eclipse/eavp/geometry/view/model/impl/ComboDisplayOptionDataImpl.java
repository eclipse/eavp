/**
 */
package org.eclipse.eavp.geometry.view.model.impl;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.eavp.geometry.view.model.ComboDisplayOptionData;
import org.eclipse.eavp.geometry.view.model.DisplayOption;
import org.eclipse.eavp.geometry.view.model.DisplayOptionType;
import org.eclipse.eavp.geometry.view.model.ModelPackage;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

/**
 * <!-- begin-user-doc --> An implementation of the model object '<em><b>Combo
 * Display Option Data</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.eavp.geometry.view.model.impl.ComboDisplayOptionDataImpl#getDisplayOption <em>Display Option</em>}</li>
 *   <li>{@link org.eclipse.eavp.geometry.view.model.impl.ComboDisplayOptionDataImpl#getTextToPropertyValuesMap <em>Text To Property Values Map</em>}</li>
 * </ul>
 *
 * @generated
 */
public class ComboDisplayOptionDataImpl extends MinimalEObjectImpl.Container
		implements ComboDisplayOptionData {
	/**
	 * The cached value of the '{@link #getDisplayOption() <em>Display Option</em>}' reference.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #getDisplayOption()
	 * @generated
	 * @ordered
	 */
	protected DisplayOption<?> displayOption;

	/**
	 * The cached value of the '{@link #getTextToPropertyValuesMap() <em>Text To Property Values Map</em>}' attribute.
	 * <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * @see #getTextToPropertyValuesMap()
	 * @generated
	 * @ordered
	 */
	protected Map<String, Map<String, Object>> textToPropertyValuesMap;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	protected ComboDisplayOptionDataImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ModelPackage.Literals.COMBO_DISPLAY_OPTION_DATA;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public DisplayOption<?> getDisplayOption() {
		if (displayOption != null && displayOption.eIsProxy()) {
			InternalEObject oldDisplayOption = (InternalEObject)displayOption;
			displayOption = (DisplayOption<?>)eResolveProxy(oldDisplayOption);
			if (displayOption != oldDisplayOption) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, ModelPackage.COMBO_DISPLAY_OPTION_DATA__DISPLAY_OPTION, oldDisplayOption, displayOption));
			}
		}
		return displayOption;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public DisplayOption<?> basicGetDisplayOption() {
		return displayOption;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setDisplayOption(DisplayOption<?> newDisplayOption) {
		DisplayOption<?> oldDisplayOption = displayOption;
		displayOption = newDisplayOption;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ModelPackage.COMBO_DISPLAY_OPTION_DATA__DISPLAY_OPTION, oldDisplayOption, displayOption));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	@Override
	public Map<String, Map<String, Object>> getTextToPropertyValuesMap() {
		if (textToPropertyValuesMap != null) {
			return textToPropertyValuesMap;
		} else {
			textToPropertyValuesMap = new HashMap<String, Map<String, Object>>();
			return textToPropertyValuesMap;
		}
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setTextToPropertyValuesMap(
			Map<String, Map<String, Object>> newTextToPropertyValuesMap) {
		Map<String, Map<String, Object>> oldTextToPropertyValuesMap = textToPropertyValuesMap;
		textToPropertyValuesMap = newTextToPropertyValuesMap;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ModelPackage.COMBO_DISPLAY_OPTION_DATA__TEXT_TO_PROPERTY_VALUES_MAP, oldTextToPropertyValuesMap, textToPropertyValuesMap));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	@Override
	public DisplayOptionType getDisplayOptionType() {
		return DisplayOptionType.COMBO;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case ModelPackage.COMBO_DISPLAY_OPTION_DATA__DISPLAY_OPTION:
				if (resolve) return getDisplayOption();
				return basicGetDisplayOption();
			case ModelPackage.COMBO_DISPLAY_OPTION_DATA__TEXT_TO_PROPERTY_VALUES_MAP:
				return getTextToPropertyValuesMap();
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
			case ModelPackage.COMBO_DISPLAY_OPTION_DATA__DISPLAY_OPTION:
				setDisplayOption((DisplayOption<?>)newValue);
				return;
			case ModelPackage.COMBO_DISPLAY_OPTION_DATA__TEXT_TO_PROPERTY_VALUES_MAP:
				setTextToPropertyValuesMap((Map<String, Map<String, Object>>)newValue);
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
			case ModelPackage.COMBO_DISPLAY_OPTION_DATA__DISPLAY_OPTION:
				setDisplayOption((DisplayOption<?>)null);
				return;
			case ModelPackage.COMBO_DISPLAY_OPTION_DATA__TEXT_TO_PROPERTY_VALUES_MAP:
				setTextToPropertyValuesMap((Map<String, Map<String, Object>>)null);
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
			case ModelPackage.COMBO_DISPLAY_OPTION_DATA__DISPLAY_OPTION:
				return displayOption != null;
			case ModelPackage.COMBO_DISPLAY_OPTION_DATA__TEXT_TO_PROPERTY_VALUES_MAP:
				return textToPropertyValuesMap != null;
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
			case ModelPackage.COMBO_DISPLAY_OPTION_DATA___GET_DISPLAY_OPTION_TYPE:
				return getDisplayOptionType();
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
		result.append(" (textToPropertyValuesMap: ");
		result.append(textToPropertyValuesMap);
		result.append(')');
		return result.toString();
	}

} // ComboDisplayOptionDataImpl
