/**
 */
package geometry;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

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
 *   <li>{@link geometry.Shape#getName <em>Name</em>}</li>
 * </ul>
 *
 * @see geometry.GeometryPackage#getShape()
 * @model
 * @generated
 */
public interface Shape extends EObject {
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
	 * Returns the value of the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The name of the shape.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Name</em>' attribute.
	 * @see #setName(String)
	 * @see geometry.GeometryPackage#getShape_Name()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.String"
	 * @generated
	 */
	String getName();

	/**
	 * Sets the value of the '{@link geometry.Shape#getName <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Name</em>' attribute.
	 * @see #getName()
	 * @generated
	 */
	void setName(String value);

} // Shape
