/**
 */
package org.eclipse.eavp.geometry.view.model;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>IDisplay Option Data</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * This interface is for objects which contain information on the configuration data for a DisplayOption.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.eavp.geometry.view.model.IDisplayOptionData#getDisplayOption <em>Display Option</em>}</li>
 * </ul>
 *
 * @see org.eclipse.eavp.geometry.view.model.ModelPackage#getIDisplayOptionData()
 * @model interface="true" abstract="true"
 * @generated
 */
public interface IDisplayOptionData extends EObject {
	/**
	 * Returns the value of the '<em><b>Display Option</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Display Option</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Display Option</em>' reference.
	 * @see #setDisplayOption(DisplayOption)
	 * @see org.eclipse.eavp.geometry.view.model.ModelPackage#getIDisplayOptionData_DisplayOption()
	 * @model
	 * @generated
	 */
	DisplayOption<?> getDisplayOption();

	/**
	 * Sets the value of the '{@link org.eclipse.eavp.geometry.view.model.IDisplayOptionData#getDisplayOption <em>Display Option</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Display Option</em>' reference.
	 * @see #getDisplayOption()
	 * @generated
	 */
	void setDisplayOption(DisplayOption<?> value);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model kind="operation"
	 * @generated
	 */
	DisplayOptionType getDisplayOptionType();

} // IDisplayOptionData
