/**
 */
package model;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>IRender Element</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * An element in the geometry which will be rendered inside the graphical engine. T is to be some data type native to the graphical engine.
 * <!-- end-model-doc -->
 *
 *
 * @see model.ModelPackage#getIRenderElement()
 * @model interface="true" abstract="true"
 * @generated
 */
public interface IRenderElement<T> extends EObject {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Get the rendered object.
	 * <!-- end-model-doc -->
	 * @model kind="operation"
	 * @generated
	 */
	T getMesh();

} // IRenderElement
