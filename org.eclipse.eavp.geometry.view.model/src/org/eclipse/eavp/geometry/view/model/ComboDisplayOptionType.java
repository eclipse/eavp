/**
 */
package org.eclipse.eavp.geometry.view.model;

import java.util.Map;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Combo Display Option Type</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.eavp.geometry.view.model.ComboDisplayOptionType#getTextToPropertyValuesMap <em>Text To Property Values Map</em>}</li>
 * </ul>
 *
 * @see org.eclipse.eavp.geometry.view.model.ModelPackage#getComboDisplayOptionType()
 * @model
 * @generated
 */
public interface ComboDisplayOptionType extends IDisplayOptionData {
	/**
	 * Returns the value of the '<em><b>Text To Property Values Map</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Text To Property Values Map</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Text To Property Values Map</em>' attribute.
	 * @see #setTextToPropertyValuesMap(Map)
	 * @see org.eclipse.eavp.geometry.view.model.ModelPackage#getComboDisplayOptionType_TextToPropertyValuesMap()
	 * @model transient="true"
	 * @generated
	 */
	Map<String, Map<String, String>> getTextToPropertyValuesMap();

	/**
	 * Sets the value of the '{@link org.eclipse.eavp.geometry.view.model.ComboDisplayOptionType#getTextToPropertyValuesMap <em>Text To Property Values Map</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Text To Property Values Map</em>' attribute.
	 * @see #getTextToPropertyValuesMap()
	 * @generated
	 */
	void setTextToPropertyValuesMap(Map<String, Map<String, String>> value);

} // ComboDisplayOptionType
