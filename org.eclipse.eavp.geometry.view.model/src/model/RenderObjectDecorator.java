/**
 */
package model;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Render Object Decorator</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * A decorator for a RenderObject. A RenderObjectDecorator will be responsible for applying a single, well defined graphical change to the RenderObject to which it is applied.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link model.RenderObjectDecorator#getSource <em>Source</em>}</li>
 * </ul>
 *
 * @see model.ModelPackage#getRenderObjectDecorator()
 * @model
 * @generated
 */
public interface RenderObjectDecorator<T> extends IRenderElement<T> {
	/**
	 * Returns the value of the '<em><b>Source</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The wrapped object to which this object will apply its change.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Source</em>' reference.
	 * @see #setSource(IRenderElement)
	 * @see model.ModelPackage#getRenderObjectDecorator_Source()
	 * @model
	 * @generated
	 */
	IRenderElement<T> getSource();

	/**
	 * Sets the value of the '{@link model.RenderObjectDecorator#getSource <em>Source</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Source</em>' reference.
	 * @see #getSource()
	 * @generated
	 */
	void setSource(IRenderElement<T> value);

} // RenderObjectDecorator
