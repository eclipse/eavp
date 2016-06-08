/**
 */
package geometry;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Named Node</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link geometry.NamedNode#getName <em>Name</em>}</li>
 *   <li>{@link geometry.NamedNode#getId <em>Id</em>}</li>
 * </ul>
 *
 * @see geometry.GeometryPackage#getNamedNode()
 * @model
 * @generated
 */
public interface NamedNode extends INode {
	/**
	 * Returns the value of the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The name of the node.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Name</em>' attribute.
	 * @see #setName(String)
	 * @see geometry.GeometryPackage#getNamedNode_Name()
	 * @model
	 * @generated
	 */
	String getName();

	/**
	 * Sets the value of the '{@link geometry.NamedNode#getName <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Name</em>' attribute.
	 * @see #getName()
	 * @generated
	 */
	void setName(String value);

	/**
	 * Returns the value of the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * An optional node id to identify the node numerically.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Id</em>' attribute.
	 * @see #setId(long)
	 * @see geometry.GeometryPackage#getNamedNode_Id()
	 * @model
	 * @generated
	 */
	long getId();

	/**
	 * Sets the value of the '{@link geometry.NamedNode#getId <em>Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Id</em>' attribute.
	 * @see #getId()
	 * @generated
	 */
	void setId(long value);

} // NamedNode
