/**
 */
package org.eclipse.eavp.geometry.view.model.util;

import org.eclipse.eavp.geometry.view.model.*;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notifier;

import org.eclipse.emf.common.notify.impl.AdapterFactoryImpl;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * The <b>Adapter Factory</b> for the model.
 * It provides an adapter <code>createXXX</code> method for each class of the model.
 * <!-- end-user-doc -->
 * @see org.eclipse.eavp.geometry.view.model.ModelPackage
 * @generated
 */
public class ModelAdapterFactory extends AdapterFactoryImpl {
	/**
	 * The cached model package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected static ModelPackage modelPackage;

	/**
	 * Creates an instance of the adapter factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ModelAdapterFactory() {
		if (modelPackage == null) {
			modelPackage = ModelPackage.eINSTANCE;
		}
	}

	/**
	 * Returns whether this factory is applicable for the type of the object.
	 * <!-- begin-user-doc -->
	 * This implementation returns <code>true</code> if the object is either the model's package or is an instance object of the model.
	 * <!-- end-user-doc -->
	 * @return whether this factory is applicable for the type of the object.
	 * @generated
	 */
	@Override
	public boolean isFactoryForType(Object object) {
		if (object == modelPackage) {
			return true;
		}
		if (object instanceof EObject) {
			return ((EObject)object).eClass().getEPackage() == modelPackage;
		}
		return false;
	}

	/**
	 * The switch that delegates to the <code>createXXX</code> methods.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ModelSwitch<Adapter> modelSwitch =
		new ModelSwitch<Adapter>() {
			@Override
			public <T> Adapter caseMeshCache(MeshCache<T> object) {
				return createMeshCacheAdapter();
			}
			@Override
			public <T> Adapter caseRenderObject(RenderObject<T> object) {
				return createRenderObjectAdapter();
			}
			@Override
			public <T> Adapter caseIRenderElement(IRenderElement<T> object) {
				return createIRenderElementAdapter();
			}
			@Override
			public <T> Adapter caseDisplayOption(DisplayOption<T> object) {
				return createDisplayOptionAdapter();
			}
			@Override
			public <T> Adapter caseOpacityOption(OpacityOption<T> object) {
				return createOpacityOptionAdapter();
			}
			@Override
			public <T> Adapter caseScaleOption(ScaleOption<T> object) {
				return createScaleOptionAdapter();
			}
			@Override
			public <T> Adapter caseWireframeOption(WireframeOption<T> object) {
				return createWireframeOptionAdapter();
			}
			@Override
			public <T> Adapter caseColorOption(ColorOption<T> object) {
				return createColorOptionAdapter();
			}
			@Override
			public Adapter caseIDisplayOptionData(IDisplayOptionData object) {
				return createIDisplayOptionDataAdapter();
			}
			@Override
			public Adapter caseComboDisplayOptionData(ComboDisplayOptionData object) {
				return createComboDisplayOptionDataAdapter();
			}
			@Override
			public Adapter caseDoubleTextDisplayOptionData(DoubleTextDisplayOptionData object) {
				return createDoubleTextDisplayOptionDataAdapter();
			}
			@Override
			public Adapter caseIntegerTextDisplayOptionData(IntegerTextDisplayOptionData object) {
				return createIntegerTextDisplayOptionDataAdapter();
			}
			@Override
			public Adapter defaultCase(EObject object) {
				return createEObjectAdapter();
			}
		};

	/**
	 * Creates an adapter for the <code>target</code>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param target the object to adapt.
	 * @return the adapter for the <code>target</code>.
	 * @generated
	 */
	@Override
	public Adapter createAdapter(Notifier target) {
		return modelSwitch.doSwitch((EObject)target);
	}


	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.eavp.geometry.view.model.MeshCache <em>Mesh Cache</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.eavp.geometry.view.model.MeshCache
	 * @generated
	 */
	public Adapter createMeshCacheAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.eavp.geometry.view.model.RenderObject <em>Render Object</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.eavp.geometry.view.model.RenderObject
	 * @generated
	 */
	public Adapter createRenderObjectAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.eavp.geometry.view.model.IRenderElement <em>IRender Element</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.eavp.geometry.view.model.IRenderElement
	 * @generated
	 */
	public Adapter createIRenderElementAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.eavp.geometry.view.model.DisplayOption <em>Display Option</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.eavp.geometry.view.model.DisplayOption
	 * @generated
	 */
	public Adapter createDisplayOptionAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.eavp.geometry.view.model.OpacityOption <em>Opacity Option</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.eavp.geometry.view.model.OpacityOption
	 * @generated
	 */
	public Adapter createOpacityOptionAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.eavp.geometry.view.model.ScaleOption <em>Scale Option</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.eavp.geometry.view.model.ScaleOption
	 * @generated
	 */
	public Adapter createScaleOptionAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.eavp.geometry.view.model.WireframeOption <em>Wireframe Option</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.eavp.geometry.view.model.WireframeOption
	 * @generated
	 */
	public Adapter createWireframeOptionAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.eavp.geometry.view.model.ColorOption <em>Color Option</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.eavp.geometry.view.model.ColorOption
	 * @generated
	 */
	public Adapter createColorOptionAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.eavp.geometry.view.model.IDisplayOptionData <em>IDisplay Option Data</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.eavp.geometry.view.model.IDisplayOptionData
	 * @generated
	 */
	public Adapter createIDisplayOptionDataAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.eavp.geometry.view.model.ComboDisplayOptionData <em>Combo Display Option Data</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.eavp.geometry.view.model.ComboDisplayOptionData
	 * @generated
	 */
	public Adapter createComboDisplayOptionDataAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.eavp.geometry.view.model.DoubleTextDisplayOptionData <em>Double Text Display Option Data</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.eavp.geometry.view.model.DoubleTextDisplayOptionData
	 * @generated
	 */
	public Adapter createDoubleTextDisplayOptionDataAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.eavp.geometry.view.model.IntegerTextDisplayOptionData <em>Integer Text Display Option Data</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.eavp.geometry.view.model.IntegerTextDisplayOptionData
	 * @generated
	 */
	public Adapter createIntegerTextDisplayOptionDataAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for the default case.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @generated
	 */
	public Adapter createEObjectAdapter() {
		return null;
	}

} //ModelAdapterFactory
