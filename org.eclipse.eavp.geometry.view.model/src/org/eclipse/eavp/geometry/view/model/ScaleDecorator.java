/**
 */
package org.eclipse.eavp.geometry.view.model;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Scale Decorator</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * A decorator which scales the wrapped object's rendering to a specified multiple of the base size.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.eavp.geometry.view.model.ScaleDecorator#getScale <em>Scale</em>}</li>
 * </ul>
 *
 * @see org.eclipse.eavp.geometry.view.model.ModelPackage#getScaleDecorator()
 * @model
 * @generated
 */
public interface ScaleDecorator<T> extends RenderObjectDecorator<T> {
	/**
	 * Returns the value of the '<em><b>Scale</b></em>' attribute.
	 * The default value is <code>"1"</code>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * How large the object is to be displayed in comparision to its actual data. A value of 1 is equivalent to the original object and a value of less than 0 will cause the decorator to make no changes to the object.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Scale</em>' attribute.
	 * @see #setScale(double)
	 * @see org.eclipse.eavp.geometry.view.model.ModelPackage#getScaleDecorator_Scale()
	 * @model default="1"
	 * @generated
	 */
	double getScale();

	/**
	 * Sets the value of the '{@link org.eclipse.eavp.geometry.view.model.ScaleDecorator#getScale <em>Scale</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Scale</em>' attribute.
	 * @see #getScale()
	 * @generated
	 */
	void setScale(double value);

} // ScaleDecorator
