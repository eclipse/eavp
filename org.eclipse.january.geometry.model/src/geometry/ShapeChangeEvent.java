/**
 */
package geometry;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Shape Change Event</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * A description of an event that triggered an updated from an ISubjectShape. A ShapeChangeEvent describes the change which prompted the shape to fire a notification.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link geometry.ShapeChangeEvent#getType <em>Type</em>}</li>
 *   <li>{@link geometry.ShapeChangeEvent#getValue <em>Value</em>}</li>
 * </ul>
 *
 * @see geometry.GeometryPackage#getShapeChangeEvent()
 * @model
 * @generated
 */
public interface ShapeChangeEvent extends EObject {
	/**
	 * Returns the value of the '<em><b>Type</b></em>' attribute.
	 * The literals are from the enumeration {@link geometry.ShapeChangeEventType}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The type of change which created this event.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Type</em>' attribute.
	 * @see geometry.ShapeChangeEventType
	 * @see #setType(ShapeChangeEventType)
	 * @see geometry.GeometryPackage#getShapeChangeEvent_Type()
	 * @model
	 * @generated
	 */
	ShapeChangeEventType getType();

	/**
	 * Sets the value of the '{@link geometry.ShapeChangeEvent#getType <em>Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Type</em>' attribute.
	 * @see geometry.ShapeChangeEventType
	 * @see #getType()
	 * @generated
	 */
	void setType(ShapeChangeEventType value);

	/**
	 * Returns the value of the '<em><b>Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * A specific description of the exact change this event represents. For example, a PropChange type event may have a value of "radius" to show that the radius property was changed.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Value</em>' attribute.
	 * @see #setValue(String)
	 * @see geometry.GeometryPackage#getShapeChangeEvent_Value()
	 * @model
	 * @generated
	 */
	String getValue();

	/**
	 * Sets the value of the '{@link geometry.ShapeChangeEvent#getValue <em>Value</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Value</em>' attribute.
	 * @see #getValue()
	 * @generated
	 */
	void setValue(String value);

} // ShapeChangeEvent
