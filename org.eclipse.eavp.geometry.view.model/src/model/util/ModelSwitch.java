/**
 */
package model.util;

import model.*;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.util.Switch;

/**
 * <!-- begin-user-doc -->
 * The <b>Switch</b> for the model's inheritance hierarchy.
 * It supports the call {@link #doSwitch(EObject) doSwitch(object)}
 * to invoke the <code>caseXXX</code> method for each class of the model,
 * starting with the actual class of the object
 * and proceeding up the inheritance hierarchy
 * until a non-null result is returned,
 * which is the result of the switch.
 * <!-- end-user-doc -->
 * @see model.ModelPackage
 * @generated
 */
public class ModelSwitch<T1> extends Switch<T1> {
	/**
	 * The cached model package
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected static ModelPackage modelPackage;

	/**
	 * Creates an instance of the switch.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ModelSwitch() {
		if (modelPackage == null) {
			modelPackage = ModelPackage.eINSTANCE;
		}
	}

	/**
	 * Checks whether this is a switch for the given package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param ePackage the package in question.
	 * @return whether this is a switch for the given package.
	 * @generated
	 */
	@Override
	protected boolean isSwitchFor(EPackage ePackage) {
		return ePackage == modelPackage;
	}

	/**
	 * Calls <code>caseXXX</code> for each class of the model until one returns a non null result; it yields that result.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the first non-null result returned by a <code>caseXXX</code> call.
	 * @generated
	 */
	@Override
	protected T1 doSwitch(int classifierID, EObject theEObject) {
		switch (classifierID) {
			case ModelPackage.MESH_CACHE: {
				MeshCache<?> meshCache = (MeshCache<?>)theEObject;
				T1 result = caseMeshCache(meshCache);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ModelPackage.RENDER_OBJECT: {
				RenderObject<?> renderObject = (RenderObject<?>)theEObject;
				T1 result = caseRenderObject(renderObject);
				if (result == null) result = caseIRenderElement(renderObject);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ModelPackage.RENDER_OBJECT_DECORATOR: {
				RenderObjectDecorator<?> renderObjectDecorator = (RenderObjectDecorator<?>)theEObject;
				T1 result = caseRenderObjectDecorator(renderObjectDecorator);
				if (result == null) result = caseIRenderElement(renderObjectDecorator);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ModelPackage.TEXTURE_DECORATOR: {
				TextureDecorator<?> textureDecorator = (TextureDecorator<?>)theEObject;
				T1 result = caseTextureDecorator(textureDecorator);
				if (result == null) result = caseRenderObjectDecorator(textureDecorator);
				if (result == null) result = caseIRenderElement(textureDecorator);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ModelPackage.OPACITY_DECORATOR: {
				OpacityDecorator<?> opacityDecorator = (OpacityDecorator<?>)theEObject;
				T1 result = caseOpacityDecorator(opacityDecorator);
				if (result == null) result = caseRenderObjectDecorator(opacityDecorator);
				if (result == null) result = caseIRenderElement(opacityDecorator);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ModelPackage.SCALE_DECORATOR: {
				ScaleDecorator<?> scaleDecorator = (ScaleDecorator<?>)theEObject;
				T1 result = caseScaleDecorator(scaleDecorator);
				if (result == null) result = caseRenderObjectDecorator(scaleDecorator);
				if (result == null) result = caseIRenderElement(scaleDecorator);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ModelPackage.WIREFRAME_DECORATOR: {
				WireframeDecorator<?> wireframeDecorator = (WireframeDecorator<?>)theEObject;
				T1 result = caseWireframeDecorator(wireframeDecorator);
				if (result == null) result = caseRenderObjectDecorator(wireframeDecorator);
				if (result == null) result = caseIRenderElement(wireframeDecorator);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ModelPackage.COLOR_DECORATOR: {
				ColorDecorator<?> colorDecorator = (ColorDecorator<?>)theEObject;
				T1 result = caseColorDecorator(colorDecorator);
				if (result == null) result = caseRenderObjectDecorator(colorDecorator);
				if (result == null) result = caseIRenderElement(colorDecorator);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ModelPackage.IRENDER_ELEMENT: {
				IRenderElement<?> iRenderElement = (IRenderElement<?>)theEObject;
				T1 result = caseIRenderElement(iRenderElement);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			default: return defaultCase(theEObject);
		}
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Mesh Cache</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Mesh Cache</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public <T> T1 caseMeshCache(MeshCache<T> object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Render Object</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Render Object</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public <T> T1 caseRenderObject(RenderObject<T> object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Render Object Decorator</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Render Object Decorator</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public <T> T1 caseRenderObjectDecorator(RenderObjectDecorator<T> object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Texture Decorator</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Texture Decorator</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public <T> T1 caseTextureDecorator(TextureDecorator<T> object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Opacity Decorator</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Opacity Decorator</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public <T> T1 caseOpacityDecorator(OpacityDecorator<T> object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Scale Decorator</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Scale Decorator</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public <T> T1 caseScaleDecorator(ScaleDecorator<T> object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Wireframe Decorator</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Wireframe Decorator</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public <T> T1 caseWireframeDecorator(WireframeDecorator<T> object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Color Decorator</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Color Decorator</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public <T> T1 caseColorDecorator(ColorDecorator<T> object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>IRender Element</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>IRender Element</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public <T> T1 caseIRenderElement(IRenderElement<T> object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>EObject</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch, but this is the last case anyway.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>EObject</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject)
	 * @generated
	 */
	@Override
	public T1 defaultCase(EObject object) {
		return null;
	}

} //ModelSwitch
