/**
 */
package geometry;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>IShape Observer</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * An interface for objects which listen to an ISubjectShape as part of the observer pattern.
 * <!-- end-model-doc -->
 *
 *
 * @see geometry.GeometryPackage#getIShapeObserver()
 * @model interface="true" abstract="true"
 * @generated
 */
public interface IShapeObserver extends EObject {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Notify the observer that a change has taken place in one of the ISubjectShapes to which it is listening.
	 * @param event The type of event which prompted the update.
	 * @param source The shape from which the update originated.
	 * <!-- end-model-doc -->
	 * @model
	 * @generated
	 */
	void update(ShapeChangeEvent event, ISubjectShape source);

} // IShapeObserver
