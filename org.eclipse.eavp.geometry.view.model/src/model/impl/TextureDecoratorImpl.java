/**
 */
package model.impl;

import org.eclipse.emf.ecore.EClass;

import model.ModelFactory;
import model.ModelPackage;
import model.TextureDecorator;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>Texture Decorator</b></em>'. <!-- end-user-doc -->
 *
 * @generated
 */
public class TextureDecoratorImpl<T> extends RenderObjectDecoratorImpl<T>
		implements TextureDecorator<T> {
	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	protected TextureDecoratorImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ModelPackage.Literals.TEXTURE_DECORATOR;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see model.impl.RenderObjectDecoratorImpl#clone()
	 */
	@Override
	public Object clone() {

		// Create a new opacity decorator
		TextureDecorator<T> clone = ModelFactory.eINSTANCE
				.createTextureDecorator();

		// Copy this object's data into the clone
		clone.copy(this);
		return clone;
	}

} // TextureDecoratorImpl
