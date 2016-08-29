/**
 */
package org.eclipse.eavp.geometry.view.model.impl;

import org.eclipse.eavp.geometry.view.model.ColorOption;
import org.eclipse.eavp.geometry.view.model.ComboDisplayOptionData;
import org.eclipse.eavp.geometry.view.model.DisplayOption;
import org.eclipse.eavp.geometry.view.model.DisplayOptionType;
import org.eclipse.eavp.geometry.view.model.DoubleTextDisplayOptionData;
import org.eclipse.eavp.geometry.view.model.IDisplayOptionData;
import org.eclipse.eavp.geometry.view.model.IRenderElement;
import org.eclipse.eavp.geometry.view.model.IntegerTextDisplayOptionData;
import org.eclipse.eavp.geometry.view.model.MeshCache;
import org.eclipse.eavp.geometry.view.model.ModelFactory;
import org.eclipse.eavp.geometry.view.model.ModelPackage;
import org.eclipse.eavp.geometry.view.model.OpacityOption;
import org.eclipse.eavp.geometry.view.model.RenderObject;
import org.eclipse.eavp.geometry.view.model.ScaleOption;
import org.eclipse.eavp.geometry.view.model.WireframeOption;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EGenericType;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.ETypeParameter;

import org.eclipse.emf.ecore.impl.EPackageImpl;
import org.eclipse.january.geometry.INode;
import org.eclipse.january.geometry.Triangle;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Package</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class ModelPackageImpl extends EPackageImpl implements ModelPackage {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass meshCacheEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass renderObjectEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass iRenderElementEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass displayOptionEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass opacityOptionEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass scaleOptionEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass wireframeOptionEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass colorOptionEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass iDisplayOptionDataEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass comboDisplayOptionDataEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass doubleTextDisplayOptionDataEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass integerTextDisplayOptionDataEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EEnum displayOptionTypeEEnum = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EDataType iNodeEDataType = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EDataType triangleEDataType = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EDataType objectEDataType = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EDataType doubleEDataType = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EDataType integerEDataType = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EDataType booleanEDataType = null;

	/**
	 * Creates an instance of the model <b>Package</b>, registered with
	 * {@link org.eclipse.emf.ecore.EPackage.Registry EPackage.Registry} by the package
	 * package URI value.
	 * <p>Note: the correct way to create the package is via the static
	 * factory method {@link #init init()}, which also performs
	 * initialization of the package, or returns the registered package,
	 * if one already exists.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.emf.ecore.EPackage.Registry
	 * @see org.eclipse.eavp.geometry.view.model.ModelPackage#eNS_URI
	 * @see #init()
	 * @generated
	 */
	private ModelPackageImpl() {
		super(eNS_URI, ModelFactory.eINSTANCE);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static boolean isInited = false;

	/**
	 * Creates, registers, and initializes the <b>Package</b> for this model, and for any others upon which it depends.
	 * 
	 * <p>This method is used to initialize {@link ModelPackage#eINSTANCE} when that field is accessed.
	 * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #eNS_URI
	 * @see #createPackageContents()
	 * @see #initializePackageContents()
	 * @generated
	 */
	public static ModelPackage init() {
		if (isInited) return (ModelPackage)EPackage.Registry.INSTANCE.getEPackage(ModelPackage.eNS_URI);

		// Obtain or create and register package
		ModelPackageImpl theModelPackage = (ModelPackageImpl)(EPackage.Registry.INSTANCE.get(eNS_URI) instanceof ModelPackageImpl ? EPackage.Registry.INSTANCE.get(eNS_URI) : new ModelPackageImpl());

		isInited = true;

		// Create package meta-data objects
		theModelPackage.createPackageContents();

		// Initialize created meta-data
		theModelPackage.initializePackageContents();

		// Mark meta-data to indicate it can't be changed
		theModelPackage.freeze();

  
		// Update the registry and return the package
		EPackage.Registry.INSTANCE.put(ModelPackage.eNS_URI, theModelPackage);
		return theModelPackage;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getMeshCache() {
		return meshCacheEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EOperation getMeshCache__GetMesh__String() {
		return meshCacheEClass.getEOperations().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EOperation getMeshCache__GetMesh__EList() {
		return meshCacheEClass.getEOperations().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getRenderObject() {
		return renderObjectEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getRenderObject_MeshCache() {
		return (EReference)renderObjectEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getRenderObject_Render() {
		return (EAttribute)renderObjectEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getRenderObject_Source() {
		return (EAttribute)renderObjectEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getRenderObject_Children() {
		return (EReference)renderObjectEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getRenderObject_DisplayOptions() {
		return (EReference)renderObjectEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EOperation getRenderObject__GetMesh() {
		return renderObjectEClass.getEOperations().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EOperation getRenderObject__RegisterOption__DisplayOption() {
		return renderObjectEClass.getEOperations().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getIRenderElement() {
		return iRenderElementEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EOperation getIRenderElement__GetMesh() {
		return iRenderElementEClass.getEOperations().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EOperation getIRenderElement__GetBase() {
		return iRenderElementEClass.getEOperations().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EOperation getIRenderElement__HandleChildren__EList() {
		return iRenderElementEClass.getEOperations().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EOperation getIRenderElement__GetProperty__String() {
		return iRenderElementEClass.getEOperations().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EOperation getIRenderElement__SetProperty__String_Object() {
		return iRenderElementEClass.getEOperations().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EOperation getIRenderElement__Copy__Object() {
		return iRenderElementEClass.getEOperations().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EOperation getIRenderElement__Clone() {
		return iRenderElementEClass.getEOperations().get(6);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getDisplayOption() {
		return displayOptionEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getDisplayOption_Active() {
		return (EAttribute)displayOptionEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getDisplayOption_OptionGroup() {
		return (EAttribute)displayOptionEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDisplayOption_Parent() {
		return (EReference)displayOptionEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getDisplayOption_Type() {
		return (EAttribute)displayOptionEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EOperation getDisplayOption__Modify__Object() {
		return displayOptionEClass.getEOperations().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EOperation getDisplayOption__GetDefaultProperties() {
		return displayOptionEClass.getEOperations().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EOperation getDisplayOption__GetDisplayOptionData() {
		return displayOptionEClass.getEOperations().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getOpacityOption() {
		return opacityOptionEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getScaleOption() {
		return scaleOptionEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getWireframeOption() {
		return wireframeOptionEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getColorOption() {
		return colorOptionEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getIDisplayOptionData() {
		return iDisplayOptionDataEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getIDisplayOptionData_DisplayOption() {
		return (EReference)iDisplayOptionDataEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EOperation getIDisplayOptionData__GetDisplayOptionType() {
		return iDisplayOptionDataEClass.getEOperations().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getComboDisplayOptionData() {
		return comboDisplayOptionDataEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getComboDisplayOptionData_TextToPropertyValuesMap() {
		return (EAttribute)comboDisplayOptionDataEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getDoubleTextDisplayOptionData() {
		return doubleTextDisplayOptionDataEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getDoubleTextDisplayOptionData_PropertyToValueMap() {
		return (EAttribute)doubleTextDisplayOptionDataEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getIntegerTextDisplayOptionData() {
		return integerTextDisplayOptionDataEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getIntegerTextDisplayOptionData_PropertyToValueMap() {
		return (EAttribute)integerTextDisplayOptionDataEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EEnum getDisplayOptionType() {
		return displayOptionTypeEEnum;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EDataType getINode() {
		return iNodeEDataType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EDataType getTriangle() {
		return triangleEDataType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EDataType getObject() {
		return objectEDataType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EDataType getDouble() {
		return doubleEDataType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EDataType getInteger() {
		return integerEDataType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EDataType getBoolean() {
		return booleanEDataType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ModelFactory getModelFactory() {
		return (ModelFactory)getEFactoryInstance();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private boolean isCreated = false;

	/**
	 * Creates the meta-model objects for the package.  This method is
	 * guarded to have no affect on any invocation but its first.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void createPackageContents() {
		if (isCreated) return;
		isCreated = true;

		// Create classes and their features
		meshCacheEClass = createEClass(MESH_CACHE);
		createEOperation(meshCacheEClass, MESH_CACHE___GET_MESH__STRING);
		createEOperation(meshCacheEClass, MESH_CACHE___GET_MESH__ELIST);

		renderObjectEClass = createEClass(RENDER_OBJECT);
		createEReference(renderObjectEClass, RENDER_OBJECT__MESH_CACHE);
		createEAttribute(renderObjectEClass, RENDER_OBJECT__RENDER);
		createEAttribute(renderObjectEClass, RENDER_OBJECT__SOURCE);
		createEReference(renderObjectEClass, RENDER_OBJECT__CHILDREN);
		createEReference(renderObjectEClass, RENDER_OBJECT__DISPLAY_OPTIONS);
		createEOperation(renderObjectEClass, RENDER_OBJECT___GET_MESH);
		createEOperation(renderObjectEClass, RENDER_OBJECT___REGISTER_OPTION__DISPLAYOPTION);

		iRenderElementEClass = createEClass(IRENDER_ELEMENT);
		createEOperation(iRenderElementEClass, IRENDER_ELEMENT___GET_MESH);
		createEOperation(iRenderElementEClass, IRENDER_ELEMENT___GET_BASE);
		createEOperation(iRenderElementEClass, IRENDER_ELEMENT___HANDLE_CHILDREN__ELIST);
		createEOperation(iRenderElementEClass, IRENDER_ELEMENT___GET_PROPERTY__STRING);
		createEOperation(iRenderElementEClass, IRENDER_ELEMENT___SET_PROPERTY__STRING_OBJECT);
		createEOperation(iRenderElementEClass, IRENDER_ELEMENT___COPY__OBJECT);
		createEOperation(iRenderElementEClass, IRENDER_ELEMENT___CLONE);

		displayOptionEClass = createEClass(DISPLAY_OPTION);
		createEAttribute(displayOptionEClass, DISPLAY_OPTION__ACTIVE);
		createEAttribute(displayOptionEClass, DISPLAY_OPTION__OPTION_GROUP);
		createEReference(displayOptionEClass, DISPLAY_OPTION__PARENT);
		createEAttribute(displayOptionEClass, DISPLAY_OPTION__TYPE);
		createEOperation(displayOptionEClass, DISPLAY_OPTION___MODIFY__OBJECT);
		createEOperation(displayOptionEClass, DISPLAY_OPTION___GET_DEFAULT_PROPERTIES);
		createEOperation(displayOptionEClass, DISPLAY_OPTION___GET_DISPLAY_OPTION_DATA);

		opacityOptionEClass = createEClass(OPACITY_OPTION);

		scaleOptionEClass = createEClass(SCALE_OPTION);

		wireframeOptionEClass = createEClass(WIREFRAME_OPTION);

		colorOptionEClass = createEClass(COLOR_OPTION);

		iDisplayOptionDataEClass = createEClass(IDISPLAY_OPTION_DATA);
		createEReference(iDisplayOptionDataEClass, IDISPLAY_OPTION_DATA__DISPLAY_OPTION);
		createEOperation(iDisplayOptionDataEClass, IDISPLAY_OPTION_DATA___GET_DISPLAY_OPTION_TYPE);

		comboDisplayOptionDataEClass = createEClass(COMBO_DISPLAY_OPTION_DATA);
		createEAttribute(comboDisplayOptionDataEClass, COMBO_DISPLAY_OPTION_DATA__TEXT_TO_PROPERTY_VALUES_MAP);

		doubleTextDisplayOptionDataEClass = createEClass(DOUBLE_TEXT_DISPLAY_OPTION_DATA);
		createEAttribute(doubleTextDisplayOptionDataEClass, DOUBLE_TEXT_DISPLAY_OPTION_DATA__PROPERTY_TO_VALUE_MAP);

		integerTextDisplayOptionDataEClass = createEClass(INTEGER_TEXT_DISPLAY_OPTION_DATA);
		createEAttribute(integerTextDisplayOptionDataEClass, INTEGER_TEXT_DISPLAY_OPTION_DATA__PROPERTY_TO_VALUE_MAP);

		// Create enums
		displayOptionTypeEEnum = createEEnum(DISPLAY_OPTION_TYPE);

		// Create data types
		iNodeEDataType = createEDataType(INODE);
		triangleEDataType = createEDataType(TRIANGLE);
		objectEDataType = createEDataType(OBJECT);
		doubleEDataType = createEDataType(DOUBLE);
		integerEDataType = createEDataType(INTEGER);
		booleanEDataType = createEDataType(BOOLEAN);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private boolean isInitialized = false;

	/**
	 * Complete the initialization of the package and its meta-model.  This
	 * method is guarded to have no affect on any invocation but its first.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void initializePackageContents() {
		if (isInitialized) return;
		isInitialized = true;

		// Initialize package
		setName(eNAME);
		setNsPrefix(eNS_PREFIX);
		setNsURI(eNS_URI);

		// Create type parameters
		ETypeParameter meshCacheEClass_T = addETypeParameter(meshCacheEClass, "T");
		ETypeParameter renderObjectEClass_T = addETypeParameter(renderObjectEClass, "T");
		ETypeParameter iRenderElementEClass_T = addETypeParameter(iRenderElementEClass, "T");
		ETypeParameter displayOptionEClass_T = addETypeParameter(displayOptionEClass, "T");
		addETypeParameter(opacityOptionEClass, "T");
		addETypeParameter(scaleOptionEClass, "T");
		addETypeParameter(wireframeOptionEClass, "T");
		addETypeParameter(colorOptionEClass, "T");

		// Set bounds for type parameters

		// Add supertypes to classes
		EGenericType g1 = createEGenericType(this.getIRenderElement());
		EGenericType g2 = createEGenericType(renderObjectEClass_T);
		g1.getETypeArguments().add(g2);
		renderObjectEClass.getEGenericSuperTypes().add(g1);
		opacityOptionEClass.getESuperTypes().add(this.getDisplayOption());
		scaleOptionEClass.getESuperTypes().add(this.getDisplayOption());
		wireframeOptionEClass.getESuperTypes().add(this.getDisplayOption());
		colorOptionEClass.getESuperTypes().add(this.getDisplayOption());
		comboDisplayOptionDataEClass.getESuperTypes().add(this.getIDisplayOptionData());
		doubleTextDisplayOptionDataEClass.getESuperTypes().add(this.getIDisplayOptionData());
		integerTextDisplayOptionDataEClass.getESuperTypes().add(this.getIDisplayOptionData());

		// Initialize classes, features, and operations; add parameters
		initEClass(meshCacheEClass, MeshCache.class, "MeshCache", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		EOperation op = initEOperation(getMeshCache__GetMesh__String(), null, "getMesh", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, ecorePackage.getEString(), "type", 0, 1, IS_UNIQUE, IS_ORDERED);
		g1 = createEGenericType(meshCacheEClass_T);
		initEOperation(op, g1);

		op = initEOperation(getMeshCache__GetMesh__EList(), null, "getMesh", 0, 1, IS_UNIQUE, IS_ORDERED);
		g1 = createEGenericType(ecorePackage.getEEList());
		g2 = createEGenericType(this.getTriangle());
		g1.getETypeArguments().add(g2);
		addEParameter(op, g1, "triangles", 0, 1, IS_UNIQUE, IS_ORDERED);
		g1 = createEGenericType(meshCacheEClass_T);
		initEOperation(op, g1);

		initEClass(renderObjectEClass, RenderObject.class, "RenderObject", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		g1 = createEGenericType(this.getMeshCache());
		g2 = createEGenericType();
		g1.getETypeArguments().add(g2);
		initEReference(getRenderObject_MeshCache(), g1, null, "meshCache", null, 0, 1, RenderObject.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		g1 = createEGenericType(renderObjectEClass_T);
		initEAttribute(getRenderObject_Render(), g1, "render", null, 0, 1, RenderObject.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getRenderObject_Source(), this.getINode(), "source", null, 0, 1, RenderObject.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		g1 = createEGenericType(this.getIRenderElement());
		g2 = createEGenericType(renderObjectEClass_T);
		g1.getETypeArguments().add(g2);
		initEReference(getRenderObject_Children(), g1, null, "children", null, 0, -1, RenderObject.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		g1 = createEGenericType(this.getDisplayOption());
		g2 = createEGenericType();
		g1.getETypeArguments().add(g2);
		initEReference(getRenderObject_DisplayOptions(), g1, null, "displayOptions", null, 0, -1, RenderObject.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		op = initEOperation(getRenderObject__GetMesh(), null, "getMesh", 0, 1, IS_UNIQUE, IS_ORDERED);
		g1 = createEGenericType(renderObjectEClass_T);
		initEOperation(op, g1);

		op = initEOperation(getRenderObject__RegisterOption__DisplayOption(), null, "registerOption", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, this.getDisplayOption(), "option", 0, 1, IS_UNIQUE, IS_ORDERED);

		initEClass(iRenderElementEClass, IRenderElement.class, "IRenderElement", IS_ABSTRACT, IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		op = initEOperation(getIRenderElement__GetMesh(), null, "getMesh", 0, 1, IS_UNIQUE, IS_ORDERED);
		g1 = createEGenericType(iRenderElementEClass_T);
		initEOperation(op, g1);

		initEOperation(getIRenderElement__GetBase(), this.getINode(), "getBase", 0, 1, IS_UNIQUE, IS_ORDERED);

		op = initEOperation(getIRenderElement__HandleChildren__EList(), null, "handleChildren", 0, 1, IS_UNIQUE, IS_ORDERED);
		g1 = createEGenericType(ecorePackage.getEEList());
		g2 = createEGenericType(this.getIRenderElement());
		g1.getETypeArguments().add(g2);
		EGenericType g3 = createEGenericType(iRenderElementEClass_T);
		g2.getETypeArguments().add(g3);
		addEParameter(op, g1, "children", 0, 1, IS_UNIQUE, IS_ORDERED);

		op = initEOperation(getIRenderElement__GetProperty__String(), this.getObject(), "getProperty", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, ecorePackage.getEString(), "property", 0, 1, IS_UNIQUE, IS_ORDERED);

		op = initEOperation(getIRenderElement__SetProperty__String_Object(), null, "setProperty", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, ecorePackage.getEString(), "property", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, this.getObject(), "value", 0, 1, IS_UNIQUE, IS_ORDERED);

		op = initEOperation(getIRenderElement__Copy__Object(), null, "copy", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, this.getObject(), "source", 0, 1, IS_UNIQUE, IS_ORDERED);

		initEOperation(getIRenderElement__Clone(), this.getObject(), "clone", 0, 1, IS_UNIQUE, IS_ORDERED);

		initEClass(displayOptionEClass, DisplayOption.class, "DisplayOption", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getDisplayOption_Active(), ecorePackage.getEBoolean(), "active", "true", 0, 1, DisplayOption.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getDisplayOption_OptionGroup(), ecorePackage.getEString(), "optionGroup", null, 0, 1, DisplayOption.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		g1 = createEGenericType(this.getRenderObject());
		g2 = createEGenericType();
		g1.getETypeArguments().add(g2);
		initEReference(getDisplayOption_Parent(), g1, null, "parent", null, 0, 1, DisplayOption.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getDisplayOption_Type(), this.getDisplayOptionType(), "type", null, 0, 1, DisplayOption.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		op = initEOperation(getDisplayOption__Modify__Object(), null, "modify", 0, 1, IS_UNIQUE, IS_ORDERED);
		g1 = createEGenericType(displayOptionEClass_T);
		addEParameter(op, g1, "element", 0, 1, IS_UNIQUE, IS_ORDERED);

		op = initEOperation(getDisplayOption__GetDefaultProperties(), null, "getDefaultProperties", 0, 1, IS_UNIQUE, IS_ORDERED);
		g1 = createEGenericType(ecorePackage.getEMap());
		g2 = createEGenericType(ecorePackage.getEString());
		g1.getETypeArguments().add(g2);
		g2 = createEGenericType(this.getObject());
		g1.getETypeArguments().add(g2);
		initEOperation(op, g1);

		initEOperation(getDisplayOption__GetDisplayOptionData(), this.getIDisplayOptionData(), "getDisplayOptionData", 0, 1, IS_UNIQUE, IS_ORDERED);

		initEClass(opacityOptionEClass, OpacityOption.class, "OpacityOption", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(scaleOptionEClass, ScaleOption.class, "ScaleOption", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(wireframeOptionEClass, WireframeOption.class, "WireframeOption", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(colorOptionEClass, ColorOption.class, "ColorOption", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(iDisplayOptionDataEClass, IDisplayOptionData.class, "IDisplayOptionData", IS_ABSTRACT, IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		g1 = createEGenericType(this.getDisplayOption());
		g2 = createEGenericType();
		g1.getETypeArguments().add(g2);
		initEReference(getIDisplayOptionData_DisplayOption(), g1, null, "displayOption", null, 0, 1, IDisplayOptionData.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEOperation(getIDisplayOptionData__GetDisplayOptionType(), this.getDisplayOptionType(), "getDisplayOptionType", 0, 1, IS_UNIQUE, IS_ORDERED);

		initEClass(comboDisplayOptionDataEClass, ComboDisplayOptionData.class, "ComboDisplayOptionData", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		g1 = createEGenericType(ecorePackage.getEMap());
		g2 = createEGenericType(ecorePackage.getEString());
		g1.getETypeArguments().add(g2);
		g2 = createEGenericType(ecorePackage.getEMap());
		g1.getETypeArguments().add(g2);
		g3 = createEGenericType(ecorePackage.getEString());
		g2.getETypeArguments().add(g3);
		g3 = createEGenericType(this.getObject());
		g2.getETypeArguments().add(g3);
		initEAttribute(getComboDisplayOptionData_TextToPropertyValuesMap(), g1, "textToPropertyValuesMap", null, 0, 1, ComboDisplayOptionData.class, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(doubleTextDisplayOptionDataEClass, DoubleTextDisplayOptionData.class, "DoubleTextDisplayOptionData", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		g1 = createEGenericType(ecorePackage.getEMap());
		g2 = createEGenericType(ecorePackage.getEString());
		g1.getETypeArguments().add(g2);
		g2 = createEGenericType(this.getDouble());
		g1.getETypeArguments().add(g2);
		initEAttribute(getDoubleTextDisplayOptionData_PropertyToValueMap(), g1, "propertyToValueMap", null, 0, 1, DoubleTextDisplayOptionData.class, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(integerTextDisplayOptionDataEClass, IntegerTextDisplayOptionData.class, "IntegerTextDisplayOptionData", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		g1 = createEGenericType(ecorePackage.getEMap());
		g2 = createEGenericType(ecorePackage.getEString());
		g1.getETypeArguments().add(g2);
		g2 = createEGenericType(this.getInteger());
		g1.getETypeArguments().add(g2);
		initEAttribute(getIntegerTextDisplayOptionData_PropertyToValueMap(), g1, "propertyToValueMap", null, 0, 1, IntegerTextDisplayOptionData.class, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		// Initialize enums and add enum literals
		initEEnum(displayOptionTypeEEnum, DisplayOptionType.class, "DisplayOptionType");
		addEEnumLiteral(displayOptionTypeEEnum, DisplayOptionType.COMBO);
		addEEnumLiteral(displayOptionTypeEEnum, DisplayOptionType.DOUBLE_TEXT);
		addEEnumLiteral(displayOptionTypeEEnum, DisplayOptionType.INTEGER_TEXT);

		// Initialize data types
		initEDataType(iNodeEDataType, INode.class, "INode", IS_SERIALIZABLE, !IS_GENERATED_INSTANCE_CLASS);
		initEDataType(triangleEDataType, Triangle.class, "Triangle", IS_SERIALIZABLE, !IS_GENERATED_INSTANCE_CLASS);
		initEDataType(objectEDataType, Object.class, "Object", IS_SERIALIZABLE, !IS_GENERATED_INSTANCE_CLASS);
		initEDataType(doubleEDataType, Double.class, "Double", IS_SERIALIZABLE, !IS_GENERATED_INSTANCE_CLASS);
		initEDataType(integerEDataType, Integer.class, "Integer", IS_SERIALIZABLE, !IS_GENERATED_INSTANCE_CLASS);
		initEDataType(booleanEDataType, Boolean.class, "Boolean", IS_SERIALIZABLE, !IS_GENERATED_INSTANCE_CLASS);

		// Create resource
		createResource(eNS_URI);
	}

} //ModelPackageImpl
