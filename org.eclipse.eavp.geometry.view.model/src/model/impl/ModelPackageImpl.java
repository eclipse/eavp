/**
 */
package model.impl;

import model.ColorDecorator;
import model.IRenderElement;
import model.MeshCache;
import model.ModelFactory;
import model.ModelPackage;
import model.OpacityDecorator;
import model.RenderObject;
import model.RenderObjectDecorator;
import model.TextureDecorator;
import model.WireframeDecorator;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
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
	private EClass renderObjectDecoratorEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass textureDecoratorEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass opacityDecoratorEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass wireframeDecoratorEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass colorDecoratorEClass = null;

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
	 * @see model.ModelPackage#eNS_URI
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
	public EOperation getRenderObject__GetMesh() {
		return renderObjectEClass.getEOperations().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getRenderObjectDecorator() {
		return renderObjectDecoratorEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getRenderObjectDecorator_Source() {
		return (EReference)renderObjectDecoratorEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getTextureDecorator() {
		return textureDecoratorEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getOpacityDecorator() {
		return opacityDecoratorEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getOpacityDecorator_Opacity() {
		return (EAttribute)opacityDecoratorEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getWireframeDecorator() {
		return wireframeDecoratorEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getWireframeDecorator_Wireframe() {
		return (EAttribute)wireframeDecoratorEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getColorDecorator() {
		return colorDecoratorEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getColorDecorator_Red() {
		return (EAttribute)colorDecoratorEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getColorDecorator_Green() {
		return (EAttribute)colorDecoratorEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getColorDecorator_Blue() {
		return (EAttribute)colorDecoratorEClass.getEStructuralFeatures().get(2);
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
		createEOperation(renderObjectEClass, RENDER_OBJECT___GET_MESH);

		renderObjectDecoratorEClass = createEClass(RENDER_OBJECT_DECORATOR);
		createEReference(renderObjectDecoratorEClass, RENDER_OBJECT_DECORATOR__SOURCE);

		textureDecoratorEClass = createEClass(TEXTURE_DECORATOR);

		opacityDecoratorEClass = createEClass(OPACITY_DECORATOR);
		createEAttribute(opacityDecoratorEClass, OPACITY_DECORATOR__OPACITY);

		wireframeDecoratorEClass = createEClass(WIREFRAME_DECORATOR);
		createEAttribute(wireframeDecoratorEClass, WIREFRAME_DECORATOR__WIREFRAME);

		colorDecoratorEClass = createEClass(COLOR_DECORATOR);
		createEAttribute(colorDecoratorEClass, COLOR_DECORATOR__RED);
		createEAttribute(colorDecoratorEClass, COLOR_DECORATOR__GREEN);
		createEAttribute(colorDecoratorEClass, COLOR_DECORATOR__BLUE);

		iRenderElementEClass = createEClass(IRENDER_ELEMENT);
		createEOperation(iRenderElementEClass, IRENDER_ELEMENT___GET_MESH);
		createEOperation(iRenderElementEClass, IRENDER_ELEMENT___GET_BASE);
		createEOperation(iRenderElementEClass, IRENDER_ELEMENT___HANDLE_CHILDREN__ELIST);
		createEOperation(iRenderElementEClass, IRENDER_ELEMENT___GET_PROPERTY__STRING);
		createEOperation(iRenderElementEClass, IRENDER_ELEMENT___SET_PROPERTY__STRING_OBJECT);
		createEOperation(iRenderElementEClass, IRENDER_ELEMENT___COPY__OBJECT);
		createEOperation(iRenderElementEClass, IRENDER_ELEMENT___CLONE);

		// Create data types
		iNodeEDataType = createEDataType(INODE);
		triangleEDataType = createEDataType(TRIANGLE);
		objectEDataType = createEDataType(OBJECT);
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
		ETypeParameter renderObjectDecoratorEClass_T = addETypeParameter(renderObjectDecoratorEClass, "T");
		ETypeParameter textureDecoratorEClass_T = addETypeParameter(textureDecoratorEClass, "T");
		ETypeParameter opacityDecoratorEClass_T = addETypeParameter(opacityDecoratorEClass, "T");
		ETypeParameter wireframeDecoratorEClass_T = addETypeParameter(wireframeDecoratorEClass, "T");
		ETypeParameter colorDecoratorEClass_T = addETypeParameter(colorDecoratorEClass, "T");
		ETypeParameter iRenderElementEClass_T = addETypeParameter(iRenderElementEClass, "T");

		// Set bounds for type parameters

		// Add supertypes to classes
		EGenericType g1 = createEGenericType(this.getIRenderElement());
		EGenericType g2 = createEGenericType(renderObjectEClass_T);
		g1.getETypeArguments().add(g2);
		renderObjectEClass.getEGenericSuperTypes().add(g1);
		g1 = createEGenericType(this.getIRenderElement());
		g2 = createEGenericType(renderObjectDecoratorEClass_T);
		g1.getETypeArguments().add(g2);
		renderObjectDecoratorEClass.getEGenericSuperTypes().add(g1);
		g1 = createEGenericType(this.getRenderObjectDecorator());
		g2 = createEGenericType(textureDecoratorEClass_T);
		g1.getETypeArguments().add(g2);
		textureDecoratorEClass.getEGenericSuperTypes().add(g1);
		g1 = createEGenericType(this.getRenderObjectDecorator());
		g2 = createEGenericType(opacityDecoratorEClass_T);
		g1.getETypeArguments().add(g2);
		opacityDecoratorEClass.getEGenericSuperTypes().add(g1);
		g1 = createEGenericType(this.getRenderObjectDecorator());
		g2 = createEGenericType(wireframeDecoratorEClass_T);
		g1.getETypeArguments().add(g2);
		wireframeDecoratorEClass.getEGenericSuperTypes().add(g1);
		g1 = createEGenericType(this.getRenderObjectDecorator());
		g2 = createEGenericType(colorDecoratorEClass_T);
		g1.getETypeArguments().add(g2);
		colorDecoratorEClass.getEGenericSuperTypes().add(g1);

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

		op = initEOperation(getRenderObject__GetMesh(), null, "getMesh", 0, 1, IS_UNIQUE, IS_ORDERED);
		g1 = createEGenericType(renderObjectEClass_T);
		initEOperation(op, g1);

		initEClass(renderObjectDecoratorEClass, RenderObjectDecorator.class, "RenderObjectDecorator", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		g1 = createEGenericType(this.getIRenderElement());
		g2 = createEGenericType(renderObjectDecoratorEClass_T);
		g1.getETypeArguments().add(g2);
		initEReference(getRenderObjectDecorator_Source(), g1, null, "source", null, 0, 1, RenderObjectDecorator.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(textureDecoratorEClass, TextureDecorator.class, "TextureDecorator", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(opacityDecoratorEClass, OpacityDecorator.class, "OpacityDecorator", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getOpacityDecorator_Opacity(), ecorePackage.getEDouble(), "opacity", "100.0", 0, 1, OpacityDecorator.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(wireframeDecoratorEClass, WireframeDecorator.class, "WireframeDecorator", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getWireframeDecorator_Wireframe(), ecorePackage.getEBoolean(), "wireframe", "false", 0, 1, WireframeDecorator.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(colorDecoratorEClass, ColorDecorator.class, "ColorDecorator", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getColorDecorator_Red(), ecorePackage.getEInt(), "red", null, 0, 1, ColorDecorator.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getColorDecorator_Green(), ecorePackage.getEInt(), "green", null, 0, 1, ColorDecorator.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getColorDecorator_Blue(), ecorePackage.getEInt(), "blue", null, 0, 1, ColorDecorator.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

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

		// Initialize data types
		initEDataType(iNodeEDataType, INode.class, "INode", IS_SERIALIZABLE, !IS_GENERATED_INSTANCE_CLASS);
		initEDataType(triangleEDataType, Triangle.class, "Triangle", IS_SERIALIZABLE, !IS_GENERATED_INSTANCE_CLASS);
		initEDataType(objectEDataType, Object.class, "Object", IS_SERIALIZABLE, !IS_GENERATED_INSTANCE_CLASS);

		// Create resource
		createResource(eNS_URI);
	}

} //ModelPackageImpl
