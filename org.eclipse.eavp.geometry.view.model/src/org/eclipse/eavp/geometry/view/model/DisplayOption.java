/**
 */
package org.eclipse.eavp.geometry.view.model;

import java.util.Map;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Display Option</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * This class contains additional optional rendering information that will be applied to the containing RenderObject's data. A DisplayOption will offer addional functionality beyond that provided by the RenderObject simply specifiyng the geometry for the shape.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.eavp.geometry.view.model.DisplayOption#isActive <em>Active</em>}</li>
 *   <li>{@link org.eclipse.eavp.geometry.view.model.DisplayOption#getOptionGroup <em>Option Group</em>}</li>
 *   <li>{@link org.eclipse.eavp.geometry.view.model.DisplayOption#getParent <em>Parent</em>}</li>
 *   <li>{@link org.eclipse.eavp.geometry.view.model.DisplayOption#getType <em>Type</em>}</li>
 * </ul>
 *
 * @see org.eclipse.eavp.geometry.view.model.ModelPackage#getDisplayOption()
 * @model
 * @generated
 */
public interface DisplayOption<T> extends EObject {
	/**
	 * Returns the value of the '<em><b>Active</b></em>' attribute.
	 * The default value is <code>"true"</code>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Whether the option is currently active. If false, the DisplayOption will not have any effect, the same as if it were not in the RenderObject's list of options. If true, it will apply its changes to the RenderObject's mesh.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Active</em>' attribute.
	 * @see #setActive(boolean)
	 * @see org.eclipse.eavp.geometry.view.model.ModelPackage#getDisplayOption_Active()
	 * @model default="true"
	 * @generated
	 */
	boolean isActive();

	/**
	 * Sets the value of the '{@link org.eclipse.eavp.geometry.view.model.DisplayOption#isActive <em>Active</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Active</em>' attribute.
	 * @see #isActive()
	 * @generated
	 */
	void setActive(boolean value);

	/**
	 * Returns the value of the '<em><b>Option Group</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The option group to which this DisplayOption belongs. The option group will determing the name under which the DisplayOption's controls will be published to users and will allow conceptually related options to share the same control.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Option Group</em>' attribute.
	 * @see #setOptionGroup(String)
	 * @see org.eclipse.eavp.geometry.view.model.ModelPackage#getDisplayOption_OptionGroup()
	 * @model
	 * @generated
	 */
	String getOptionGroup();

	/**
	 * Sets the value of the '{@link org.eclipse.eavp.geometry.view.model.DisplayOption#getOptionGroup <em>Option Group</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Option Group</em>' attribute.
	 * @see #getOptionGroup()
	 * @generated
	 */
	void setOptionGroup(String value);

	/**
	 * Returns the value of the '<em><b>Parent</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Parent</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Parent</em>' reference.
	 * @see #setParent(RenderObject)
	 * @see org.eclipse.eavp.geometry.view.model.ModelPackage#getDisplayOption_Parent()
	 * @model
	 * @generated
	 */
	RenderObject<?> getParent();

	/**
	 * Sets the value of the '{@link org.eclipse.eavp.geometry.view.model.DisplayOption#getParent <em>Parent</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Parent</em>' reference.
	 * @see #getParent()
	 * @generated
	 */
	void setParent(RenderObject<?> value);

	/**
	 * Returns the value of the '<em><b>Type</b></em>' attribute.
	 * The literals are from the enumeration {@link org.eclipse.eavp.geometry.view.model.DisplayOptionType}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The type of control which best corresponds to this class's configuraiton options.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Type</em>' attribute.
	 * @see org.eclipse.eavp.geometry.view.model.DisplayOptionType
	 * @see #setType(DisplayOptionType)
	 * @see org.eclipse.eavp.geometry.view.model.ModelPackage#getDisplayOption_Type()
	 * @model
	 * @generated
	 */
	DisplayOptionType getType();

	/**
	 * Sets the value of the '{@link org.eclipse.eavp.geometry.view.model.DisplayOption#getType <em>Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Type</em>' attribute.
	 * @see org.eclipse.eavp.geometry.view.model.DisplayOptionType
	 * @see #getType()
	 * @generated
	 */
	void setType(DisplayOptionType value);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Apply this option's modifications to the given mesh.
	 * <!-- end-model-doc -->
	 * @model
	 * @generated
	 */
	void modify(T element);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model kind="operation"
	 * @generated
	 */
	Map<String, Object> getDefaultProperties();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model kind="operation"
	 * @generated
	 */
	IDisplayOptionData getDisplayOptionData();

} // DisplayOption
