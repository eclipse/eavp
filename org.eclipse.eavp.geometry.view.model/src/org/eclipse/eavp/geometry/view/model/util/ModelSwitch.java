/**
 */
package org.eclipse.eavp.geometry.view.model.util;

import org.eclipse.eavp.geometry.view.model.*;

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
 * @see org.eclipse.eavp.geometry.view.model.ModelPackage
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
			case ModelPackage.IRENDER_ELEMENT: {
				IRenderElement<?> iRenderElement = (IRenderElement<?>)theEObject;
				T1 result = caseIRenderElement(iRenderElement);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ModelPackage.DISPLAY_OPTION: {
				DisplayOption<?> displayOption = (DisplayOption<?>)theEObject;
				T1 result = caseDisplayOption(displayOption);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ModelPackage.OPACITY_OPTION: {
				OpacityOption<?> opacityOption = (OpacityOption<?>)theEObject;
				T1 result = caseOpacityOption(opacityOption);
				if (result == null) result = (T1)caseDisplayOption(opacityOption);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ModelPackage.SCALE_OPTION: {
				ScaleOption<?> scaleOption = (ScaleOption<?>)theEObject;
				T1 result = caseScaleOption(scaleOption);
				if (result == null) result = (T1)caseDisplayOption(scaleOption);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ModelPackage.WIREFRAME_OPTION: {
				WireframeOption<?> wireframeOption = (WireframeOption<?>)theEObject;
				T1 result = caseWireframeOption(wireframeOption);
				if (result == null) result = (T1)caseDisplayOption(wireframeOption);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ModelPackage.COLOR_OPTION: {
				ColorOption<?> colorOption = (ColorOption<?>)theEObject;
				T1 result = caseColorOption(colorOption);
				if (result == null) result = (T1)caseDisplayOption(colorOption);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ModelPackage.IDISPLAY_OPTION_DATA: {
				IDisplayOptionData iDisplayOptionData = (IDisplayOptionData)theEObject;
				T1 result = caseIDisplayOptionData(iDisplayOptionData);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ModelPackage.COMBO_DISPLAY_OPTION_DATA: {
				ComboDisplayOptionData comboDisplayOptionData = (ComboDisplayOptionData)theEObject;
				T1 result = caseComboDisplayOptionData(comboDisplayOptionData);
				if (result == null) result = caseIDisplayOptionData(comboDisplayOptionData);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ModelPackage.DOUBLE_TEXT_DISPLAY_OPTION_DATA: {
				DoubleTextDisplayOptionData doubleTextDisplayOptionData = (DoubleTextDisplayOptionData)theEObject;
				T1 result = caseDoubleTextDisplayOptionData(doubleTextDisplayOptionData);
				if (result == null) result = caseIDisplayOptionData(doubleTextDisplayOptionData);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ModelPackage.INTEGER_TEXT_DISPLAY_OPTION_DATA: {
				IntegerTextDisplayOptionData integerTextDisplayOptionData = (IntegerTextDisplayOptionData)theEObject;
				T1 result = caseIntegerTextDisplayOptionData(integerTextDisplayOptionData);
				if (result == null) result = caseIDisplayOptionData(integerTextDisplayOptionData);
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
	 * Returns the result of interpreting the object as an instance of '<em>Display Option</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Display Option</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public <T> T1 caseDisplayOption(DisplayOption<T> object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Opacity Option</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Opacity Option</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public <T> T1 caseOpacityOption(OpacityOption<T> object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Scale Option</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Scale Option</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public <T> T1 caseScaleOption(ScaleOption<T> object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Wireframe Option</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Wireframe Option</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public <T> T1 caseWireframeOption(WireframeOption<T> object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Color Option</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Color Option</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public <T> T1 caseColorOption(ColorOption<T> object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>IDisplay Option Data</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>IDisplay Option Data</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T1 caseIDisplayOptionData(IDisplayOptionData object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Combo Display Option Data</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Combo Display Option Data</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T1 caseComboDisplayOptionData(ComboDisplayOptionData object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Double Text Display Option Data</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Double Text Display Option Data</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T1 caseDoubleTextDisplayOptionData(DoubleTextDisplayOptionData object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Integer Text Display Option Data</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Integer Text Display Option Data</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T1 caseIntegerTextDisplayOptionData(IntegerTextDisplayOptionData object) {
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
