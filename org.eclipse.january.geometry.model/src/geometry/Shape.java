/**
 */
package geometry;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Shape</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * This class defines a shape as a collection of triangles and a center, or origin, of the space. This is also a base class for common and complex shapes. Common shapes are commonly known shapes like spheres, cubes, cylinders and others that form the basis of Constructive Solid Geometry packages. Complex shapes are common shapes that have been combined using boolean operations such as unions and intersections.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link geometry.Shape#getTriangles <em>Triangles</em>}</li>
 *   <li>{@link geometry.Shape#getCenter <em>Center</em>}</li>
 *   <li>{@link geometry.Shape#getType <em>Type</em>}</li>
 *   <li>{@link geometry.Shape#getMaterial <em>Material</em>}</li>
 * </ul>
 *
 * @see geometry.GeometryPackage#getShape()
 * @model
 * @generated
 */
public interface Shape extends ISubjectShape, INode {
	/**
	 * Returns the value of the '<em><b>Triangles</b></em>' containment reference list.
	 * The list contents are of type {@link geometry.Triangle}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The surface of every shape can be represented by a collection of triangles. This collection of triangles only approximates the exact geometry of the shape in practice, although in the limit where the number of triangles goes to infinity the representation is exact.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Triangles</em>' containment reference list.
	 * @see geometry.GeometryPackage#getShape_Triangles()
	 * @model containment="true"
	 * @generated
	 */
	EList<Triangle> getTriangles();

	/**
	 * Returns the value of the '<em><b>Center</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Each shape is centered on a special vertex.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Center</em>' reference.
	 * @see #setCenter(Vertex)
	 * @see geometry.GeometryPackage#getShape_Center()
	 * @model required="true"
	 * @generated
	 */
	Vertex getCenter();

	/**
	 * Sets the value of the '{@link geometry.Shape#getCenter <em>Center</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Center</em>' reference.
	 * @see #getCenter()
	 * @generated
	 */
	void setCenter(Vertex value);

	/**
	 * Returns the value of the '<em><b>Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * A string representing the type of geometry this Shape is in a human readable way. Examples include "cube" or "sphere."
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Type</em>' attribute.
	 * @see #setType(String)
	 * @see geometry.GeometryPackage#getShape_Type()
	 * @model
	 * @generated
	 */
	String getType();

	/**
	 * Sets the value of the '{@link geometry.Shape#getType <em>Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Type</em>' attribute.
	 * @see #getType()
	 * @generated
	 */
	void setType(String value);

	/**
	 * Returns the value of the '<em><b>Material</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Material</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Material</em>' containment reference.
	 * @see #setMaterial(Material)
	 * @see geometry.GeometryPackage#getShape_Material()
	 * @model containment="true"
	 * @generated
	 */
	Material getMaterial();

	/**
	 * Sets the value of the '{@link geometry.Shape#getMaterial <em>Material</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Material</em>' containment reference.
	 * @see #getMaterial()
	 * @generated
	 */
	void setMaterial(Material value);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Get the names of all properties set for this shape.
	 * <!-- end-model-doc -->
	 * @model kind="operation"
	 * @generated
	 */
	EList<String> getPropertyNames();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Get the value for one of the shape's properties.
	 * @param property The name of the property whose value is to be returned.
	 * <!-- end-model-doc -->
	 * @model
	 * @generated
	 */
	double getProperty(String property);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Set one of the Shape's properties.
	 * @param property The name of the property whose value is being set.
	 * @param value The property's new value.
	 * <!-- end-model-doc -->
	 * @model
	 * @generated
	 */
	void setProperty(String property, double value);

} // Shape
