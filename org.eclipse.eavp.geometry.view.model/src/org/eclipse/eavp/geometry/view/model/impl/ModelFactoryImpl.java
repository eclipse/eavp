/**
 */
package org.eclipse.eavp.geometry.view.model.impl;

import org.eclipse.eavp.geometry.view.model.ColorOption;
import org.eclipse.eavp.geometry.view.model.ComboDisplayOptionData;
import org.eclipse.eavp.geometry.view.model.DisplayOption;
import org.eclipse.eavp.geometry.view.model.DisplayOptionType;
import org.eclipse.eavp.geometry.view.model.DoubleTextDisplayOptionData;
import org.eclipse.eavp.geometry.view.model.IntegerTextDisplayOptionData;
import org.eclipse.eavp.geometry.view.model.MeshCache;
import org.eclipse.eavp.geometry.view.model.ModelFactory;
import org.eclipse.eavp.geometry.view.model.ModelPackage;
import org.eclipse.eavp.geometry.view.model.OpacityOption;
import org.eclipse.eavp.geometry.view.model.RenderObject;
import org.eclipse.eavp.geometry.view.model.ScaleOption;
import org.eclipse.eavp.geometry.view.model.WireframeOption;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.impl.EFactoryImpl;
import org.eclipse.emf.ecore.plugin.EcorePlugin;
import org.eclipse.january.geometry.INode;
import org.eclipse.january.geometry.Triangle;

/**
 * <!-- begin-user-doc --> An implementation of the model <b>Factory</b>. <!--
 * end-user-doc -->
 * @generated
 */
public class ModelFactoryImpl extends EFactoryImpl implements ModelFactory {
	/**
	 * Creates the default factory implementation.
	 * <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * @generated
	 */
	public static ModelFactory init() {
		try {
			ModelFactory theModelFactory = (ModelFactory)EPackage.Registry.INSTANCE.getEFactory(ModelPackage.eNS_URI);
			if (theModelFactory != null) {
				return theModelFactory;
			}
		}
		catch (Exception exception) {
			EcorePlugin.INSTANCE.log(exception);
		}
		return new ModelFactoryImpl();
	}

	/**
	 * Creates an instance of the factory.
	 * <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * @generated
	 */
	public ModelFactoryImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EObject create(EClass eClass) {
		switch (eClass.getClassifierID()) {
			case ModelPackage.MESH_CACHE: return createMeshCache();
			case ModelPackage.RENDER_OBJECT: return createRenderObject();
			case ModelPackage.DISPLAY_OPTION: return createDisplayOption();
			case ModelPackage.OPACITY_OPTION: return createOpacityOption();
			case ModelPackage.SCALE_OPTION: return createScaleOption();
			case ModelPackage.WIREFRAME_OPTION: return createWireframeOption();
			case ModelPackage.COLOR_OPTION: return createColorOption();
			case ModelPackage.COMBO_DISPLAY_OPTION_DATA: return createComboDisplayOptionData();
			case ModelPackage.DOUBLE_TEXT_DISPLAY_OPTION_DATA: return createDoubleTextDisplayOptionData();
			case ModelPackage.INTEGER_TEXT_DISPLAY_OPTION_DATA: return createIntegerTextDisplayOptionData();
			default:
				throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object createFromString(EDataType eDataType, String initialValue) {
		switch (eDataType.getClassifierID()) {
			case ModelPackage.DISPLAY_OPTION_TYPE:
				return createDisplayOptionTypeFromString(eDataType, initialValue);
			case ModelPackage.INODE:
				return createINodeFromString(eDataType, initialValue);
			case ModelPackage.TRIANGLE:
				return createTriangleFromString(eDataType, initialValue);
			case ModelPackage.OBJECT:
				return createObjectFromString(eDataType, initialValue);
			case ModelPackage.DOUBLE:
				return createDoubleFromString(eDataType, initialValue);
			case ModelPackage.INTEGER:
				return createIntegerFromString(eDataType, initialValue);
			case ModelPackage.BOOLEAN:
				return createBooleanFromString(eDataType, initialValue);
			default:
				throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String convertToString(EDataType eDataType, Object instanceValue) {
		switch (eDataType.getClassifierID()) {
			case ModelPackage.DISPLAY_OPTION_TYPE:
				return convertDisplayOptionTypeToString(eDataType, instanceValue);
			case ModelPackage.INODE:
				return convertINodeToString(eDataType, instanceValue);
			case ModelPackage.TRIANGLE:
				return convertTriangleToString(eDataType, instanceValue);
			case ModelPackage.OBJECT:
				return convertObjectToString(eDataType, instanceValue);
			case ModelPackage.DOUBLE:
				return convertDoubleToString(eDataType, instanceValue);
			case ModelPackage.INTEGER:
				return convertIntegerToString(eDataType, instanceValue);
			case ModelPackage.BOOLEAN:
				return convertBooleanToString(eDataType, instanceValue);
			default:
				throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public <T> MeshCache<T> createMeshCache() {
		MeshCacheImpl<T> meshCache = new MeshCacheImpl<T>();
		return meshCache;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public <T> RenderObject<T> createRenderObject() {
		RenderObjectImpl<T> renderObject = new RenderObjectImpl<T>();
		return renderObject;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public <T> DisplayOption<T> createDisplayOption() {
		DisplayOptionImpl<T> displayOption = new DisplayOptionImpl<T>();
		return displayOption;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public <T> OpacityOption<T> createOpacityOption() {
		OpacityOptionImpl<T> opacityOption = new OpacityOptionImpl<T>();
		return opacityOption;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public <T> ScaleOption<T> createScaleOption() {
		ScaleOptionImpl<T> scaleOption = new ScaleOptionImpl<T>();
		return scaleOption;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public <T> WireframeOption<T> createWireframeOption() {
		WireframeOptionImpl<T> wireframeOption = new WireframeOptionImpl<T>();
		return wireframeOption;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public ComboDisplayOptionData createComboDisplayOptionData() {
		ComboDisplayOptionDataImpl comboDisplayOptionData = new ComboDisplayOptionDataImpl();
		return comboDisplayOptionData;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public DoubleTextDisplayOptionData createDoubleTextDisplayOptionData() {
		DoubleTextDisplayOptionDataImpl doubleTextDisplayOptionData = new DoubleTextDisplayOptionDataImpl();
		return doubleTextDisplayOptionData;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public IntegerTextDisplayOptionData createIntegerTextDisplayOptionData() {
		IntegerTextDisplayOptionDataImpl integerTextDisplayOptionData = new IntegerTextDisplayOptionDataImpl();
		return integerTextDisplayOptionData;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public DisplayOptionType createDisplayOptionTypeFromString(
			EDataType eDataType, String initialValue) {
		DisplayOptionType result = DisplayOptionType.get(initialValue);
		if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
		return result;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public String convertDisplayOptionTypeToString(EDataType eDataType,
			Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public INode createINodeFromString(EDataType eDataType,
			String initialValue) {
		return (INode)super.createFromString(eDataType, initialValue);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public String convertINodeToString(EDataType eDataType,
			Object instanceValue) {
		return super.convertToString(eDataType, instanceValue);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public Triangle createTriangleFromString(EDataType eDataType,
			String initialValue) {
		return (Triangle)super.createFromString(eDataType, initialValue);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public String convertTriangleToString(EDataType eDataType,
			Object instanceValue) {
		return super.convertToString(eDataType, instanceValue);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public Object createObjectFromString(EDataType eDataType,
			String initialValue) {
		return super.createFromString(eDataType, initialValue);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public String convertObjectToString(EDataType eDataType,
			Object instanceValue) {
		return super.convertToString(eDataType, instanceValue);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public Double createDoubleFromString(EDataType eDataType,
			String initialValue) {
		return (Double)super.createFromString(eDataType, initialValue);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public String convertDoubleToString(EDataType eDataType,
			Object instanceValue) {
		return super.convertToString(eDataType, instanceValue);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public Integer createIntegerFromString(EDataType eDataType,
			String initialValue) {
		return (Integer)super.createFromString(eDataType, initialValue);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public String convertIntegerToString(EDataType eDataType,
			Object instanceValue) {
		return super.convertToString(eDataType, instanceValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Boolean createBooleanFromString(EDataType eDataType, String initialValue) {
		return (Boolean)super.createFromString(eDataType, initialValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertBooleanToString(EDataType eDataType, Object instanceValue) {
		return super.convertToString(eDataType, instanceValue);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public ModelPackage getModelPackage() {
		return (ModelPackage)getEPackage();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @deprecated
	 * @generated
	 */
	@Deprecated
	public static ModelPackage getPackage() {
		return ModelPackage.eINSTANCE;
	}

	@Override
	public <T> ColorOption<T> createColorOption() {
		// TODO Auto-generated method stub
		return null;
	}

} // ModelFactoryImpl
