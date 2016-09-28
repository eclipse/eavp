/**
 */
package org.eclipse.eavp.geometry.view.model;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Color Decorator</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * A decorator which applies a color to a rendered object.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.eavp.geometry.view.model.ColorDecorator#getRed <em>Red</em>}</li>
 *   <li>{@link org.eclipse.eavp.geometry.view.model.ColorDecorator#getGreen <em>Green</em>}</li>
 *   <li>{@link org.eclipse.eavp.geometry.view.model.ColorDecorator#getBlue <em>Blue</em>}</li>
 * </ul>
 *
 * @see org.eclipse.eavp.geometry.view.model.ModelPackage#getColorDecorator()
 * @model
 * @generated
 */
public interface ColorDecorator<T> extends RenderObjectDecorator<T> {
	/**
	 * Returns the value of the '<em><b>Red</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The amount of red in the object's color, on a scale from 0 to 255.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Red</em>' attribute.
	 * @see #setRed(int)
	 * @see org.eclipse.eavp.geometry.view.model.ModelPackage#getColorDecorator_Red()
	 * @model
	 * @generated
	 */
	int getRed();

	/**
	 * Sets the value of the '{@link org.eclipse.eavp.geometry.view.model.ColorDecorator#getRed <em>Red</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Red</em>' attribute.
	 * @see #getRed()
	 * @generated
	 */
	void setRed(int value);

	/**
	 * Returns the value of the '<em><b>Green</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The amount of green in the object's color, on a scale from 0 to 255.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Green</em>' attribute.
	 * @see #setGreen(int)
	 * @see org.eclipse.eavp.geometry.view.model.ModelPackage#getColorDecorator_Green()
	 * @model
	 * @generated
	 */
	int getGreen();

	/**
	 * Sets the value of the '{@link org.eclipse.eavp.geometry.view.model.ColorDecorator#getGreen <em>Green</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Green</em>' attribute.
	 * @see #getGreen()
	 * @generated
	 */
	void setGreen(int value);

	/**
	 * Returns the value of the '<em><b>Blue</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The amount of blue in the object's color, on a scale from 0 to 255.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Blue</em>' attribute.
	 * @see #setBlue(int)
	 * @see org.eclipse.eavp.geometry.view.model.ModelPackage#getColorDecorator_Blue()
	 * @model
	 * @generated
	 */
	int getBlue();

	/**
	 * Sets the value of the '{@link org.eclipse.eavp.geometry.view.model.ColorDecorator#getBlue <em>Blue</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Blue</em>' attribute.
	 * @see #getBlue()
	 * @generated
	 */
	void setBlue(int value);

} // ColorDecorator
