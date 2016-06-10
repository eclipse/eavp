/**
 */
package geometry;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>ISubject Shape</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * 
 * <!-- end-model-doc -->
 *
 *
 * @see geometry.GeometryPackage#getISubjectShape()
 * @model interface="true" abstract="true"
 * @generated
 */
public interface ISubjectShape extends EObject {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 
	 * @param observer The observer which will listen to this object.
	 * <!-- end-model-doc -->
	 * @model
	 * @generated
	 */
	void register(IShapeObserver observer);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Remove an IShapeObserver from the list of registered listeners, so that it will no longer receive updates from this object.
	 * @param observer The observer which will no longer listen to this object.
	 * <!-- end-model-doc -->
	 * @model
	 * @generated
	 */
	void unregister(IShapeObserver observer);

} // ISubjectShape
