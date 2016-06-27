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
 *   <li>{@link geometry.Shape#getMaterial <em>Material</em>}</li>
 * </ul>
 *
 * @see geometry.GeometryPackage#getShape()
 * @model
 * @generated
 */
public interface Shape extends INode {
	/**
	 * Returns the value of the '<em><b>Material</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Material</em>' containment reference isn't clear,
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
	 * Set one of the Shape's properties.
	 * @param property The name of the property whose value is being set.
	 * @param value The property's new value.
	 * <!-- end-model-doc -->
	 * @model annotation="http://www.eclipse.org/emf/2002/GenModel body='properties.put(property, value);'"
	 * @generated
	 */
	void setProperty(String property, double value);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Get the value for one of the shape's properties.
	 * @param property The name of the property whose value is to be returned.
	 * <!-- end-model-doc -->
	 * @model annotation="http://www.eclipse.org/emf/2002/GenModel body='return properties.get(property);'"
	 * @generated
	 */
	double getProperty(String property);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Get the names of all properties set for this shape.
	 * <!-- end-model-doc -->
	 * @model kind="operation"
	 *        annotation="http://www.eclipse.org/emf/2002/GenModel body='//Return a list of the properties\' keys.\r\nreturn new BasicEList<String>(properties.keySet());'"
	 * @generated
	 */
	EList<String> getPropertyNames();

} // Shape
