/**
 */
package model;

import org.eclipse.emf.common.util.EList;
import org.eclipse.january.geometry.INode;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Render Object</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * The object responsible for maintaining the base mesh which will be used to render an INode. This mesh, of type T that is native to the graphical engine, will be retrieved from the MeshCache based on the properties of the source INode.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link model.RenderObject#getMeshCache <em>Mesh Cache</em>}</li>
 *   <li>{@link model.RenderObject#getRender <em>Render</em>}</li>
 *   <li>{@link model.RenderObject#getSource <em>Source</em>}</li>
 *   <li>{@link model.RenderObject#getChildren <em>Children</em>}</li>
 * </ul>
 *
 * @see model.ModelPackage#getRenderObject()
 * @model
 * @generated
 */
public interface RenderObject<T> extends IRenderElement<T> {
	/**
	 * Returns the value of the '<em><b>Mesh Cache</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The cache from which the mesh will be pulled. It is intended that all RenderObjects in the same context should share the same meshCache.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Mesh Cache</em>' reference.
	 * @see #setMeshCache(MeshCache)
	 * @see model.ModelPackage#getRenderObject_MeshCache()
	 * @model
	 * @generated
	 */
	MeshCache<?> getMeshCache();

	/**
	 * Sets the value of the '{@link model.RenderObject#getMeshCache <em>Mesh Cache</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Mesh Cache</em>' reference.
	 * @see #getMeshCache()
	 * @generated
	 */
	void setMeshCache(MeshCache<?> value);

	/**
	 * Returns the value of the '<em><b>Render</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The rendered object which can be provided to the graphics engine in order for a shape to be drawn.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Render</em>' attribute.
	 * @see model.ModelPackage#getRenderObject_Render()
	 * @model suppressedSetVisibility="true"
	 * @generated
	 */
	T getRender();

	/**
	 * Returns the value of the '<em><b>Source</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Source</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The wrapped source object whose data will be used to render a shape in the graphics engine.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Source</em>' attribute.
	 * @see #setSource(INode)
	 * @see model.ModelPackage#getRenderObject_Source()
	 * @model dataType="model.INode"
	 * @generated
	 */
	INode getSource();

	/**
	 * Sets the value of the '{@link model.RenderObject#getSource <em>Source</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Source</em>' attribute.
	 * @see #getSource()
	 * @generated
	 */
	void setSource(INode value);

	/**
	 * Returns the value of the '<em><b>Children</b></em>' containment reference list.
	 * The list contents are of type {@link model.IRenderElement}&lt;T>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The rendered objects corresponding to the souorce object's children.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Children</em>' containment reference list.
	 * @see model.ModelPackage#getRenderObject_Children()
	 * @model containment="true"
	 * @generated
	 */
	EList<IRenderElement<T>> getChildren();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model kind="operation"
	 *        annotation="http://www.eclipse.org/emf/2002/GenModel body='return render;'"
	 * @generated
	 */
	T getMesh();

} // RenderObject
