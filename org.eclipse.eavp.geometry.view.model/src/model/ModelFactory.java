/**
 */
package model;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see model.ModelPackage
 * @generated
 */
public interface ModelFactory extends EFactory {
	/**
	 * The singleton instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	ModelFactory eINSTANCE = model.impl.ModelFactoryImpl.init();

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
	 * Returns a new object of class '<em>Render Object Decorator</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Render Object Decorator</em>'.
	 * @generated
	 */
	<T> RenderObjectDecorator<T> createRenderObjectDecorator();

	/**
	 * Returns a new object of class '<em>Texture Decorator</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Texture Decorator</em>'.
	 * @generated
	 */
	<T> TextureDecorator<T> createTextureDecorator();

	/**
	 * Returns a new object of class '<em>Opacity Decorator</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Opacity Decorator</em>'.
	 * @generated
	 */
	<T> OpacityDecorator<T> createOpacityDecorator();

	/**
	 * Returns a new object of class '<em>Scale Decorator</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Scale Decorator</em>'.
	 * @generated
	 */
	<T> ScaleDecorator<T> createScaleDecorator();

	/**
	 * Returns a new object of class '<em>Wireframe Decorator</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Wireframe Decorator</em>'.
	 * @generated
	 */
	<T> WireframeDecorator<T> createWireframeDecorator();

	/**
	 * Returns a new object of class '<em>Color Decorator</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Color Decorator</em>'.
	 * @generated
	 */
	<T> ColorDecorator<T> createColorDecorator();

	/**
	 * Returns the package supported by this factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the package supported by this factory.
	 * @generated
	 */
	ModelPackage getModelPackage();

} //ModelFactory
