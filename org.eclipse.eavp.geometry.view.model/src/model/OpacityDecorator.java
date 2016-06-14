/**
 */
package model;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Opacity Decorator</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * A decorator which sets the wrapped object's rendering to a specified amount of opacity.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link model.OpacityDecorator#getOpacity <em>Opacity</em>}</li>
 * </ul>
 *
 * @see model.ModelPackage#getOpacityDecorator()
 * @model
 * @generated
 */
public interface OpacityDecorator<T> extends RenderObjectDecorator<T> {
	/**
	 * Returns the value of the '<em><b>Opacity</b></em>' attribute.
	 * The default value is <code>"100.0"</code>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * How opaque to render the object. Opacity is measured on a scale from 0 (fully transparent) to 1 (fully opaque).
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Opacity</em>' attribute.
	 * @see #setOpacity(double)
	 * @see model.ModelPackage#getOpacityDecorator_Opacity()
	 * @model default="100.0"
	 * @generated
	 */
	double getOpacity();

	/**
	 * Sets the value of the '{@link model.OpacityDecorator#getOpacity <em>Opacity</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Opacity</em>' attribute.
	 * @see #getOpacity()
	 * @generated
	 */
	void setOpacity(double value);

} // OpacityDecorator
