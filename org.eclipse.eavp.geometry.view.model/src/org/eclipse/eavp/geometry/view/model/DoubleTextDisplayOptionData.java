/**
 */
package org.eclipse.eavp.geometry.view.model;

import java.util.Map;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Double Text Display Option Data</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.eavp.geometry.view.model.DoubleTextDisplayOptionData#getPropertyToValueMap <em>Property To Value Map</em>}</li>
 * </ul>
 *
 * @see org.eclipse.eavp.geometry.view.model.ModelPackage#getDoubleTextDisplayOptionData()
 * @model
 * @generated
 */
public interface DoubleTextDisplayOptionData extends IDisplayOptionData {
	/**
	 * Returns the value of the '<em><b>Property To Value Map</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Property To Value Map</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Property To Value Map</em>' attribute.
	 * @see #setPropertyToValueMap(Map)
	 * @see org.eclipse.eavp.geometry.view.model.ModelPackage#getDoubleTextDisplayOptionData_PropertyToValueMap()
	 * @model transient="true"
	 * @generated
	 */
	Map<String, Double> getPropertyToValueMap();

	/**
	 * Sets the value of the '{@link org.eclipse.eavp.geometry.view.model.DoubleTextDisplayOptionData#getPropertyToValueMap <em>Property To Value Map</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Property To Value Map</em>' attribute.
	 * @see #getPropertyToValueMap()
	 * @generated
	 */
	void setPropertyToValueMap(Map<String, Double> value);

} // DoubleTextDisplayOptionData
