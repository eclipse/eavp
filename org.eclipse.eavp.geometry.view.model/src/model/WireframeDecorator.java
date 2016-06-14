/**
 */
package model;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Wireframe Decorator</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * A decorator which can change the object's rendering to appear in wireframe mode.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link model.WireframeDecorator#isWireframe <em>Wireframe</em>}</li>
 * </ul>
 *
 * @see model.ModelPackage#getWireframeDecorator()
 * @model
 * @generated
 */
public interface WireframeDecorator<T> extends RenderObjectDecorator<T> {
	/**
	 * Returns the value of the '<em><b>Wireframe</b></em>' attribute.
	 * The default value is <code>"false"</code>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Whether the object is to be rendered as a wireframe. If true, it will be displayed as a wireframe and if false it will be displayed as a solid object.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Wireframe</em>' attribute.
	 * @see #setWireframe(boolean)
	 * @see model.ModelPackage#getWireframeDecorator_Wireframe()
	 * @model default="false"
	 * @generated
	 */
	boolean isWireframe();

	/**
	 * Sets the value of the '{@link model.WireframeDecorator#isWireframe <em>Wireframe</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Wireframe</em>' attribute.
	 * @see #isWireframe()
	 * @generated
	 */
	void setWireframe(boolean value);

} // WireframeDecorator
