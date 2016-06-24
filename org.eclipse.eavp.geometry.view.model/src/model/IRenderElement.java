/**
 */
package model;

import geometry.INode;
import org.eclipse.emf.common.util.EList;
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

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Get the source data object on whose properties the render is based. If the IRenderElement's direct source is another IRenderElement, the INode at the bottom of the sequence of IRenderElements will be returned.
	 * <!-- end-model-doc -->
	 * @model kind="operation" dataType="model.INode"
	 * @generated
	 */
	INode getBase();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Handle the source object's children by applying them to the mesh in accordance to the object's type. 
	 * <!-- end-model-doc -->
	 * @model childrenMany="false"
	 * @generated
	 */
	void handleChildren(EList<IRenderElement<T>> children);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model dataType="model.Object"
	 * @generated
	 */
	Object getProperty(String property);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Set the given rendering property to the new value.
	 * <!-- end-model-doc -->
	 * @model valueDataType="model.Object"
	 * @generated
	 */
	void setProperty(String property, Object value);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Makes this object into a deep copy of the source object, if it is an appropriate type.
	 * <!-- end-model-doc -->
	 * @model sourceDataType="model.Object"
	 * @generated
	 */
	void copy(Object source);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Make a new copy of this object.
	 * <!-- end-model-doc -->
	 * @model dataType="model.Object"
	 * @generated
	 */
	Object clone();

} // IRenderElement
