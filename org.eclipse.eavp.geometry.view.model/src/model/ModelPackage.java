/**
 */
package model;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

/**
 * <!-- begin-user-doc -->
 * The <b>Package</b> for the model.
 * It contains accessors for the meta objects to represent
 * <ul>
 *   <li>each class,</li>
 *   <li>each feature of each class,</li>
 *   <li>each operation of each class,</li>
 *   <li>each enum,</li>
 *   <li>and each data type</li>
 * </ul>
 * <!-- end-user-doc -->
 * @see model.ModelFactory
 * @model kind="package"
 * @generated
 */
public interface ModelPackage extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "model";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "http://www.eclipse.org/";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "eavp";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	ModelPackage eINSTANCE = model.impl.ModelPackageImpl.init();

	/**
	 * The meta object id for the '{@link model.impl.MeshCacheImpl <em>Mesh Cache</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see model.impl.MeshCacheImpl
	 * @see model.impl.ModelPackageImpl#getMeshCache()
	 * @generated
	 */
	int MESH_CACHE = 0;

	/**
	 * The number of structural features of the '<em>Mesh Cache</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MESH_CACHE_FEATURE_COUNT = 0;

	/**
	 * The operation id for the '<em>Get Mesh</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MESH_CACHE___GET_MESH__STRING = 0;

	/**
	 * The operation id for the '<em>Get Mesh</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MESH_CACHE___GET_MESH__ELIST = 1;

	/**
	 * The number of operations of the '<em>Mesh Cache</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MESH_CACHE_OPERATION_COUNT = 2;

	/**
	 * The meta object id for the '{@link model.IRenderElement <em>IRender Element</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see model.IRenderElement
	 * @see model.impl.ModelPackageImpl#getIRenderElement()
	 * @generated
	 */
	int IRENDER_ELEMENT = 7;

	/**
	 * The number of structural features of the '<em>IRender Element</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IRENDER_ELEMENT_FEATURE_COUNT = 0;

	/**
	 * The operation id for the '<em>Get Mesh</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IRENDER_ELEMENT___GET_MESH = 0;

	/**
	 * The operation id for the '<em>Get Base</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IRENDER_ELEMENT___GET_BASE = 1;

	/**
	 * The number of operations of the '<em>IRender Element</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IRENDER_ELEMENT_OPERATION_COUNT = 2;

	/**
	 * The meta object id for the '{@link model.impl.RenderObjectImpl <em>Render Object</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see model.impl.RenderObjectImpl
	 * @see model.impl.ModelPackageImpl#getRenderObject()
	 * @generated
	 */
	int RENDER_OBJECT = 1;

	/**
	 * The feature id for the '<em><b>Mesh Cache</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RENDER_OBJECT__MESH_CACHE = IRENDER_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Render</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RENDER_OBJECT__RENDER = IRENDER_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Source</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RENDER_OBJECT__SOURCE = IRENDER_ELEMENT_FEATURE_COUNT + 2;

	/**
	 * The number of structural features of the '<em>Render Object</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RENDER_OBJECT_FEATURE_COUNT = IRENDER_ELEMENT_FEATURE_COUNT + 3;

	/**
	 * The operation id for the '<em>Get Base</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RENDER_OBJECT___GET_BASE = IRENDER_ELEMENT___GET_BASE;

	/**
	 * The operation id for the '<em>Get Mesh</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RENDER_OBJECT___GET_MESH = IRENDER_ELEMENT_OPERATION_COUNT + 0;

	/**
	 * The number of operations of the '<em>Render Object</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RENDER_OBJECT_OPERATION_COUNT = IRENDER_ELEMENT_OPERATION_COUNT + 1;

	/**
	 * The meta object id for the '{@link model.impl.RenderObjectDecoratorImpl <em>Render Object Decorator</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see model.impl.RenderObjectDecoratorImpl
	 * @see model.impl.ModelPackageImpl#getRenderObjectDecorator()
	 * @generated
	 */
	int RENDER_OBJECT_DECORATOR = 2;

	/**
	 * The feature id for the '<em><b>Source</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RENDER_OBJECT_DECORATOR__SOURCE = IRENDER_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Render Object Decorator</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RENDER_OBJECT_DECORATOR_FEATURE_COUNT = IRENDER_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The operation id for the '<em>Get Mesh</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RENDER_OBJECT_DECORATOR___GET_MESH = IRENDER_ELEMENT___GET_MESH;

	/**
	 * The operation id for the '<em>Get Base</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RENDER_OBJECT_DECORATOR___GET_BASE = IRENDER_ELEMENT___GET_BASE;

	/**
	 * The number of operations of the '<em>Render Object Decorator</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RENDER_OBJECT_DECORATOR_OPERATION_COUNT = IRENDER_ELEMENT_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link model.impl.TextureDecoratorImpl <em>Texture Decorator</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see model.impl.TextureDecoratorImpl
	 * @see model.impl.ModelPackageImpl#getTextureDecorator()
	 * @generated
	 */
	int TEXTURE_DECORATOR = 3;

	/**
	 * The feature id for the '<em><b>Source</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEXTURE_DECORATOR__SOURCE = RENDER_OBJECT_DECORATOR__SOURCE;

	/**
	 * The number of structural features of the '<em>Texture Decorator</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEXTURE_DECORATOR_FEATURE_COUNT = RENDER_OBJECT_DECORATOR_FEATURE_COUNT + 0;

	/**
	 * The operation id for the '<em>Get Mesh</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEXTURE_DECORATOR___GET_MESH = RENDER_OBJECT_DECORATOR___GET_MESH;

	/**
	 * The operation id for the '<em>Get Base</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEXTURE_DECORATOR___GET_BASE = RENDER_OBJECT_DECORATOR___GET_BASE;

	/**
	 * The number of operations of the '<em>Texture Decorator</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEXTURE_DECORATOR_OPERATION_COUNT = RENDER_OBJECT_DECORATOR_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link model.impl.OpacityDecoratorImpl <em>Opacity Decorator</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see model.impl.OpacityDecoratorImpl
	 * @see model.impl.ModelPackageImpl#getOpacityDecorator()
	 * @generated
	 */
	int OPACITY_DECORATOR = 4;

	/**
	 * The feature id for the '<em><b>Source</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPACITY_DECORATOR__SOURCE = RENDER_OBJECT_DECORATOR__SOURCE;

	/**
	 * The feature id for the '<em><b>Opacity</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPACITY_DECORATOR__OPACITY = RENDER_OBJECT_DECORATOR_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Opacity Decorator</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPACITY_DECORATOR_FEATURE_COUNT = RENDER_OBJECT_DECORATOR_FEATURE_COUNT + 1;

	/**
	 * The operation id for the '<em>Get Mesh</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPACITY_DECORATOR___GET_MESH = RENDER_OBJECT_DECORATOR___GET_MESH;

	/**
	 * The operation id for the '<em>Get Base</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPACITY_DECORATOR___GET_BASE = RENDER_OBJECT_DECORATOR___GET_BASE;

	/**
	 * The number of operations of the '<em>Opacity Decorator</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPACITY_DECORATOR_OPERATION_COUNT = RENDER_OBJECT_DECORATOR_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link model.impl.WireframeDecoratorImpl <em>Wireframe Decorator</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see model.impl.WireframeDecoratorImpl
	 * @see model.impl.ModelPackageImpl#getWireframeDecorator()
	 * @generated
	 */
	int WIREFRAME_DECORATOR = 5;

	/**
	 * The feature id for the '<em><b>Source</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WIREFRAME_DECORATOR__SOURCE = RENDER_OBJECT_DECORATOR__SOURCE;

	/**
	 * The feature id for the '<em><b>Wireframe</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WIREFRAME_DECORATOR__WIREFRAME = RENDER_OBJECT_DECORATOR_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Wireframe Decorator</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WIREFRAME_DECORATOR_FEATURE_COUNT = RENDER_OBJECT_DECORATOR_FEATURE_COUNT + 1;

	/**
	 * The operation id for the '<em>Get Mesh</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WIREFRAME_DECORATOR___GET_MESH = RENDER_OBJECT_DECORATOR___GET_MESH;

	/**
	 * The operation id for the '<em>Get Base</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WIREFRAME_DECORATOR___GET_BASE = RENDER_OBJECT_DECORATOR___GET_BASE;

	/**
	 * The number of operations of the '<em>Wireframe Decorator</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WIREFRAME_DECORATOR_OPERATION_COUNT = RENDER_OBJECT_DECORATOR_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link model.impl.ColorDecoratorImpl <em>Color Decorator</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see model.impl.ColorDecoratorImpl
	 * @see model.impl.ModelPackageImpl#getColorDecorator()
	 * @generated
	 */
	int COLOR_DECORATOR = 6;

	/**
	 * The feature id for the '<em><b>Source</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COLOR_DECORATOR__SOURCE = RENDER_OBJECT_DECORATOR__SOURCE;

	/**
	 * The feature id for the '<em><b>Red</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COLOR_DECORATOR__RED = RENDER_OBJECT_DECORATOR_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Green</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COLOR_DECORATOR__GREEN = RENDER_OBJECT_DECORATOR_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Blue</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COLOR_DECORATOR__BLUE = RENDER_OBJECT_DECORATOR_FEATURE_COUNT + 2;

	/**
	 * The number of structural features of the '<em>Color Decorator</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COLOR_DECORATOR_FEATURE_COUNT = RENDER_OBJECT_DECORATOR_FEATURE_COUNT + 3;

	/**
	 * The operation id for the '<em>Get Mesh</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COLOR_DECORATOR___GET_MESH = RENDER_OBJECT_DECORATOR___GET_MESH;

	/**
	 * The operation id for the '<em>Get Base</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COLOR_DECORATOR___GET_BASE = RENDER_OBJECT_DECORATOR___GET_BASE;

	/**
	 * The number of operations of the '<em>Color Decorator</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COLOR_DECORATOR_OPERATION_COUNT = RENDER_OBJECT_DECORATOR_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '<em>INode</em>' data type.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see geometry.INode
	 * @see model.impl.ModelPackageImpl#getINode()
	 * @generated
	 */
	int INODE = 8;


	/**
	 * The meta object id for the '<em>Triangle</em>' data type.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see geometry.Triangle
	 * @see model.impl.ModelPackageImpl#getTriangle()
	 * @generated
	 */
	int TRIANGLE = 9;


	/**
	 * Returns the meta object for class '{@link model.MeshCache <em>Mesh Cache</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Mesh Cache</em>'.
	 * @see model.MeshCache
	 * @generated
	 */
	EClass getMeshCache();

	/**
	 * Returns the meta object for the '{@link model.MeshCache#getMesh(java.lang.String) <em>Get Mesh</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the '<em>Get Mesh</em>' operation.
	 * @see model.MeshCache#getMesh(java.lang.String)
	 * @generated
	 */
	EOperation getMeshCache__GetMesh__String();

	/**
	 * Returns the meta object for the '{@link model.MeshCache#getMesh(org.eclipse.emf.common.util.EList) <em>Get Mesh</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the '<em>Get Mesh</em>' operation.
	 * @see model.MeshCache#getMesh(org.eclipse.emf.common.util.EList)
	 * @generated
	 */
	EOperation getMeshCache__GetMesh__EList();

	/**
	 * Returns the meta object for class '{@link model.RenderObject <em>Render Object</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Render Object</em>'.
	 * @see model.RenderObject
	 * @generated
	 */
	EClass getRenderObject();

	/**
	 * Returns the meta object for the reference '{@link model.RenderObject#getMeshCache <em>Mesh Cache</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Mesh Cache</em>'.
	 * @see model.RenderObject#getMeshCache()
	 * @see #getRenderObject()
	 * @generated
	 */
	EReference getRenderObject_MeshCache();

	/**
	 * Returns the meta object for the attribute '{@link model.RenderObject#getRender <em>Render</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Render</em>'.
	 * @see model.RenderObject#getRender()
	 * @see #getRenderObject()
	 * @generated
	 */
	EAttribute getRenderObject_Render();

	/**
	 * Returns the meta object for the attribute '{@link model.RenderObject#getSource <em>Source</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Source</em>'.
	 * @see model.RenderObject#getSource()
	 * @see #getRenderObject()
	 * @generated
	 */
	EAttribute getRenderObject_Source();

	/**
	 * Returns the meta object for the '{@link model.RenderObject#getMesh() <em>Get Mesh</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the '<em>Get Mesh</em>' operation.
	 * @see model.RenderObject#getMesh()
	 * @generated
	 */
	EOperation getRenderObject__GetMesh();

	/**
	 * Returns the meta object for class '{@link model.RenderObjectDecorator <em>Render Object Decorator</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Render Object Decorator</em>'.
	 * @see model.RenderObjectDecorator
	 * @generated
	 */
	EClass getRenderObjectDecorator();

	/**
	 * Returns the meta object for the reference '{@link model.RenderObjectDecorator#getSource <em>Source</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Source</em>'.
	 * @see model.RenderObjectDecorator#getSource()
	 * @see #getRenderObjectDecorator()
	 * @generated
	 */
	EReference getRenderObjectDecorator_Source();

	/**
	 * Returns the meta object for class '{@link model.TextureDecorator <em>Texture Decorator</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Texture Decorator</em>'.
	 * @see model.TextureDecorator
	 * @generated
	 */
	EClass getTextureDecorator();

	/**
	 * Returns the meta object for class '{@link model.OpacityDecorator <em>Opacity Decorator</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Opacity Decorator</em>'.
	 * @see model.OpacityDecorator
	 * @generated
	 */
	EClass getOpacityDecorator();

	/**
	 * Returns the meta object for the attribute '{@link model.OpacityDecorator#getOpacity <em>Opacity</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Opacity</em>'.
	 * @see model.OpacityDecorator#getOpacity()
	 * @see #getOpacityDecorator()
	 * @generated
	 */
	EAttribute getOpacityDecorator_Opacity();

	/**
	 * Returns the meta object for class '{@link model.WireframeDecorator <em>Wireframe Decorator</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Wireframe Decorator</em>'.
	 * @see model.WireframeDecorator
	 * @generated
	 */
	EClass getWireframeDecorator();

	/**
	 * Returns the meta object for the attribute '{@link model.WireframeDecorator#isWireframe <em>Wireframe</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Wireframe</em>'.
	 * @see model.WireframeDecorator#isWireframe()
	 * @see #getWireframeDecorator()
	 * @generated
	 */
	EAttribute getWireframeDecorator_Wireframe();

	/**
	 * Returns the meta object for class '{@link model.ColorDecorator <em>Color Decorator</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Color Decorator</em>'.
	 * @see model.ColorDecorator
	 * @generated
	 */
	EClass getColorDecorator();

	/**
	 * Returns the meta object for the attribute '{@link model.ColorDecorator#getRed <em>Red</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Red</em>'.
	 * @see model.ColorDecorator#getRed()
	 * @see #getColorDecorator()
	 * @generated
	 */
	EAttribute getColorDecorator_Red();

	/**
	 * Returns the meta object for the attribute '{@link model.ColorDecorator#getGreen <em>Green</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Green</em>'.
	 * @see model.ColorDecorator#getGreen()
	 * @see #getColorDecorator()
	 * @generated
	 */
	EAttribute getColorDecorator_Green();

	/**
	 * Returns the meta object for the attribute '{@link model.ColorDecorator#getBlue <em>Blue</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Blue</em>'.
	 * @see model.ColorDecorator#getBlue()
	 * @see #getColorDecorator()
	 * @generated
	 */
	EAttribute getColorDecorator_Blue();

	/**
	 * Returns the meta object for class '{@link model.IRenderElement <em>IRender Element</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>IRender Element</em>'.
	 * @see model.IRenderElement
	 * @generated
	 */
	EClass getIRenderElement();

	/**
	 * Returns the meta object for the '{@link model.IRenderElement#getMesh() <em>Get Mesh</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the '<em>Get Mesh</em>' operation.
	 * @see model.IRenderElement#getMesh()
	 * @generated
	 */
	EOperation getIRenderElement__GetMesh();

	/**
	 * Returns the meta object for the '{@link model.IRenderElement#getBase() <em>Get Base</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the '<em>Get Base</em>' operation.
	 * @see model.IRenderElement#getBase()
	 * @generated
	 */
	EOperation getIRenderElement__GetBase();

	/**
	 * Returns the meta object for data type '{@link geometry.INode <em>INode</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for data type '<em>INode</em>'.
	 * @see geometry.INode
	 * @model instanceClass="geometry.INode"
	 * @generated
	 */
	EDataType getINode();

	/**
	 * Returns the meta object for data type '{@link geometry.Triangle <em>Triangle</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for data type '<em>Triangle</em>'.
	 * @see geometry.Triangle
	 * @model instanceClass="geometry.Triangle"
	 * @generated
	 */
	EDataType getTriangle();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	ModelFactory getModelFactory();

	/**
	 * <!-- begin-user-doc -->
	 * Defines literals for the meta objects that represent
	 * <ul>
	 *   <li>each class,</li>
	 *   <li>each feature of each class,</li>
	 *   <li>each operation of each class,</li>
	 *   <li>each enum,</li>
	 *   <li>and each data type</li>
	 * </ul>
	 * <!-- end-user-doc -->
	 * @generated
	 */
	interface Literals {
		/**
		 * The meta object literal for the '{@link model.impl.MeshCacheImpl <em>Mesh Cache</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see model.impl.MeshCacheImpl
		 * @see model.impl.ModelPackageImpl#getMeshCache()
		 * @generated
		 */
		EClass MESH_CACHE = eINSTANCE.getMeshCache();

		/**
		 * The meta object literal for the '<em><b>Get Mesh</b></em>' operation.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EOperation MESH_CACHE___GET_MESH__STRING = eINSTANCE.getMeshCache__GetMesh__String();

		/**
		 * The meta object literal for the '<em><b>Get Mesh</b></em>' operation.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EOperation MESH_CACHE___GET_MESH__ELIST = eINSTANCE.getMeshCache__GetMesh__EList();

		/**
		 * The meta object literal for the '{@link model.impl.RenderObjectImpl <em>Render Object</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see model.impl.RenderObjectImpl
		 * @see model.impl.ModelPackageImpl#getRenderObject()
		 * @generated
		 */
		EClass RENDER_OBJECT = eINSTANCE.getRenderObject();

		/**
		 * The meta object literal for the '<em><b>Mesh Cache</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference RENDER_OBJECT__MESH_CACHE = eINSTANCE.getRenderObject_MeshCache();

		/**
		 * The meta object literal for the '<em><b>Render</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute RENDER_OBJECT__RENDER = eINSTANCE.getRenderObject_Render();

		/**
		 * The meta object literal for the '<em><b>Source</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute RENDER_OBJECT__SOURCE = eINSTANCE.getRenderObject_Source();

		/**
		 * The meta object literal for the '<em><b>Get Mesh</b></em>' operation.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EOperation RENDER_OBJECT___GET_MESH = eINSTANCE.getRenderObject__GetMesh();

		/**
		 * The meta object literal for the '{@link model.impl.RenderObjectDecoratorImpl <em>Render Object Decorator</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see model.impl.RenderObjectDecoratorImpl
		 * @see model.impl.ModelPackageImpl#getRenderObjectDecorator()
		 * @generated
		 */
		EClass RENDER_OBJECT_DECORATOR = eINSTANCE.getRenderObjectDecorator();

		/**
		 * The meta object literal for the '<em><b>Source</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference RENDER_OBJECT_DECORATOR__SOURCE = eINSTANCE.getRenderObjectDecorator_Source();

		/**
		 * The meta object literal for the '{@link model.impl.TextureDecoratorImpl <em>Texture Decorator</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see model.impl.TextureDecoratorImpl
		 * @see model.impl.ModelPackageImpl#getTextureDecorator()
		 * @generated
		 */
		EClass TEXTURE_DECORATOR = eINSTANCE.getTextureDecorator();

		/**
		 * The meta object literal for the '{@link model.impl.OpacityDecoratorImpl <em>Opacity Decorator</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see model.impl.OpacityDecoratorImpl
		 * @see model.impl.ModelPackageImpl#getOpacityDecorator()
		 * @generated
		 */
		EClass OPACITY_DECORATOR = eINSTANCE.getOpacityDecorator();

		/**
		 * The meta object literal for the '<em><b>Opacity</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute OPACITY_DECORATOR__OPACITY = eINSTANCE.getOpacityDecorator_Opacity();

		/**
		 * The meta object literal for the '{@link model.impl.WireframeDecoratorImpl <em>Wireframe Decorator</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see model.impl.WireframeDecoratorImpl
		 * @see model.impl.ModelPackageImpl#getWireframeDecorator()
		 * @generated
		 */
		EClass WIREFRAME_DECORATOR = eINSTANCE.getWireframeDecorator();

		/**
		 * The meta object literal for the '<em><b>Wireframe</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute WIREFRAME_DECORATOR__WIREFRAME = eINSTANCE.getWireframeDecorator_Wireframe();

		/**
		 * The meta object literal for the '{@link model.impl.ColorDecoratorImpl <em>Color Decorator</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see model.impl.ColorDecoratorImpl
		 * @see model.impl.ModelPackageImpl#getColorDecorator()
		 * @generated
		 */
		EClass COLOR_DECORATOR = eINSTANCE.getColorDecorator();

		/**
		 * The meta object literal for the '<em><b>Red</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute COLOR_DECORATOR__RED = eINSTANCE.getColorDecorator_Red();

		/**
		 * The meta object literal for the '<em><b>Green</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute COLOR_DECORATOR__GREEN = eINSTANCE.getColorDecorator_Green();

		/**
		 * The meta object literal for the '<em><b>Blue</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute COLOR_DECORATOR__BLUE = eINSTANCE.getColorDecorator_Blue();

		/**
		 * The meta object literal for the '{@link model.IRenderElement <em>IRender Element</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see model.IRenderElement
		 * @see model.impl.ModelPackageImpl#getIRenderElement()
		 * @generated
		 */
		EClass IRENDER_ELEMENT = eINSTANCE.getIRenderElement();

		/**
		 * The meta object literal for the '<em><b>Get Mesh</b></em>' operation.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EOperation IRENDER_ELEMENT___GET_MESH = eINSTANCE.getIRenderElement__GetMesh();

		/**
		 * The meta object literal for the '<em><b>Get Base</b></em>' operation.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EOperation IRENDER_ELEMENT___GET_BASE = eINSTANCE.getIRenderElement__GetBase();

		/**
		 * The meta object literal for the '<em>INode</em>' data type.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see geometry.INode
		 * @see model.impl.ModelPackageImpl#getINode()
		 * @generated
		 */
		EDataType INODE = eINSTANCE.getINode();

		/**
		 * The meta object literal for the '<em>Triangle</em>' data type.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see geometry.Triangle
		 * @see model.impl.ModelPackageImpl#getTriangle()
		 * @generated
		 */
		EDataType TRIANGLE = eINSTANCE.getTriangle();

	}

} //ModelPackage
