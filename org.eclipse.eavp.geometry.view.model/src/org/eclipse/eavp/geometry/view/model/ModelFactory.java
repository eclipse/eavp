/**
 */
package org.eclipse.eavp.geometry.view.model;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see org.eclipse.eavp.geometry.view.model.ModelPackage
 * @generated
 */
public interface ModelFactory extends EFactory {
	/**
	 * The singleton instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	ModelFactory eINSTANCE = org.eclipse.eavp.geometry.view.model.impl.ModelFactoryImpl.init();

	/**
	 * Returns a new object of class '<em>Mesh Cache</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Mesh Cache</em>'.
	 * @generated
	 */
	<T> MeshCache<T> createMeshCache();

	/**
	 * Returns a new object of class '<em>Render Object</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Render Object</em>'.
	 * @generated
	 */
	<T> RenderObject<T> createRenderObject();

	/**
	 * Returns a new object of class '<em>Display Option</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Display Option</em>'.
	 * @generated
	 */
	<T> DisplayOption<T> createDisplayOption();

	/**
	 * Returns a new object of class '<em>Opacity Option</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Opacity Option</em>'.
	 * @generated
	 */
	<T> OpacityOption<T> createOpacityOption();

	/**
	 * Returns a new object of class '<em>Scale Option</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Scale Option</em>'.
	 * @generated
	 */
	<T> ScaleOption<T> createScaleOption();

	/**
	 * Returns a new object of class '<em>Wireframe Option</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Wireframe Option</em>'.
	 * @generated
	 */
	<T> WireframeOption<T> createWireframeOption();

	/**
	 * Returns a new object of class '<em>Color Option</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Color Option</em>'.
	 * @generated
	 */
	<T> ColorOption<T> createColorOption();

	/**
	 * Returns a new object of class '<em>Combo Display Option Data</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Combo Display Option Data</em>'.
	 * @generated
	 */
	ComboDisplayOptionData createComboDisplayOptionData();

	/**
	 * Returns a new object of class '<em>Double Text Display Option Data</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Double Text Display Option Data</em>'.
	 * @generated
	 */
	DoubleTextDisplayOptionData createDoubleTextDisplayOptionData();

	/**
	 * Returns a new object of class '<em>Integer Text Display Option Data</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Integer Text Display Option Data</em>'.
	 * @generated
	 */
	IntegerTextDisplayOptionData createIntegerTextDisplayOptionData();

	/**
	 * Returns the package supported by this factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the package supported by this factory.
	 * @generated
	 */
	ModelPackage getModelPackage();

} //ModelFactory
