/**
 */
package org.eclipse.eavp.geometry.view.model;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EEnum;
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
 * <!-- begin-model-doc -->
 * Get the value of the specified rendering property.
 * <!-- end-model-doc -->
 * @see org.eclipse.eavp.geometry.view.model.ModelFactory
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
	ModelPackage eINSTANCE = org.eclipse.eavp.geometry.view.model.impl.ModelPackageImpl.init();

	/**
	 * The meta object id for the '{@link org.eclipse.eavp.geometry.view.model.impl.MeshCacheImpl <em>Mesh Cache</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.eavp.geometry.view.model.impl.MeshCacheImpl
	 * @see org.eclipse.eavp.geometry.view.model.impl.ModelPackageImpl#getMeshCache()
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
	 * The meta object id for the '{@link org.eclipse.eavp.geometry.view.model.IRenderElement <em>IRender Element</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.eavp.geometry.view.model.IRenderElement
	 * @see org.eclipse.eavp.geometry.view.model.impl.ModelPackageImpl#getIRenderElement()
	 * @generated
	 */
	int IRENDER_ELEMENT = 2;

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
	 * The operation id for the '<em>Handle Children</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IRENDER_ELEMENT___HANDLE_CHILDREN__ELIST = 2;

	/**
	 * The operation id for the '<em>Get Property</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IRENDER_ELEMENT___GET_PROPERTY__STRING = 3;

	/**
	 * The operation id for the '<em>Set Property</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IRENDER_ELEMENT___SET_PROPERTY__STRING_OBJECT = 4;

	/**
	 * The operation id for the '<em>Copy</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IRENDER_ELEMENT___COPY__OBJECT = 5;

	/**
	 * The operation id for the '<em>Clone</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IRENDER_ELEMENT___CLONE = 6;

	/**
	 * The number of operations of the '<em>IRender Element</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IRENDER_ELEMENT_OPERATION_COUNT = 7;

	/**
	 * The meta object id for the '{@link org.eclipse.eavp.geometry.view.model.impl.RenderObjectImpl <em>Render Object</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.eavp.geometry.view.model.impl.RenderObjectImpl
	 * @see org.eclipse.eavp.geometry.view.model.impl.ModelPackageImpl#getRenderObject()
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
	 * The feature id for the '<em><b>Children</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RENDER_OBJECT__CHILDREN = IRENDER_ELEMENT_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Display Options</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RENDER_OBJECT__DISPLAY_OPTIONS = IRENDER_ELEMENT_FEATURE_COUNT + 4;

	/**
	 * The number of structural features of the '<em>Render Object</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RENDER_OBJECT_FEATURE_COUNT = IRENDER_ELEMENT_FEATURE_COUNT + 5;

	/**
	 * The operation id for the '<em>Get Base</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RENDER_OBJECT___GET_BASE = IRENDER_ELEMENT___GET_BASE;

	/**
	 * The operation id for the '<em>Handle Children</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RENDER_OBJECT___HANDLE_CHILDREN__ELIST = IRENDER_ELEMENT___HANDLE_CHILDREN__ELIST;

	/**
	 * The operation id for the '<em>Get Property</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RENDER_OBJECT___GET_PROPERTY__STRING = IRENDER_ELEMENT___GET_PROPERTY__STRING;

	/**
	 * The operation id for the '<em>Set Property</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RENDER_OBJECT___SET_PROPERTY__STRING_OBJECT = IRENDER_ELEMENT___SET_PROPERTY__STRING_OBJECT;

	/**
	 * The operation id for the '<em>Copy</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RENDER_OBJECT___COPY__OBJECT = IRENDER_ELEMENT___COPY__OBJECT;

	/**
	 * The operation id for the '<em>Clone</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RENDER_OBJECT___CLONE = IRENDER_ELEMENT___CLONE;

	/**
	 * The operation id for the '<em>Get Mesh</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RENDER_OBJECT___GET_MESH = IRENDER_ELEMENT_OPERATION_COUNT + 0;

	/**
	 * The operation id for the '<em>Register Option</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RENDER_OBJECT___REGISTER_OPTION__DISPLAYOPTION = IRENDER_ELEMENT_OPERATION_COUNT + 1;

	/**
	 * The number of operations of the '<em>Render Object</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RENDER_OBJECT_OPERATION_COUNT = IRENDER_ELEMENT_OPERATION_COUNT + 2;

	/**
	 * The meta object id for the '{@link org.eclipse.eavp.geometry.view.model.impl.DisplayOptionImpl <em>Display Option</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.eavp.geometry.view.model.impl.DisplayOptionImpl
	 * @see org.eclipse.eavp.geometry.view.model.impl.ModelPackageImpl#getDisplayOption()
	 * @generated
	 */
	int DISPLAY_OPTION = 3;

	/**
	 * The feature id for the '<em><b>Active</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DISPLAY_OPTION__ACTIVE = 0;

	/**
	 * The feature id for the '<em><b>Option Group</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DISPLAY_OPTION__OPTION_GROUP = 1;

	/**
	 * The feature id for the '<em><b>Parent</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DISPLAY_OPTION__PARENT = 2;

	/**
	 * The feature id for the '<em><b>Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DISPLAY_OPTION__TYPE = 3;

	/**
	 * The number of structural features of the '<em>Display Option</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DISPLAY_OPTION_FEATURE_COUNT = 4;

	/**
	 * The operation id for the '<em>Modify</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DISPLAY_OPTION___MODIFY__OBJECT = 0;

	/**
	 * The operation id for the '<em>Get Default Properties</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DISPLAY_OPTION___GET_DEFAULT_PROPERTIES = 1;

	/**
	 * The operation id for the '<em>Get Display Option Data</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DISPLAY_OPTION___GET_DISPLAY_OPTION_DATA = 2;

	/**
	 * The number of operations of the '<em>Display Option</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DISPLAY_OPTION_OPERATION_COUNT = 3;

	/**
	 * The meta object id for the '{@link org.eclipse.eavp.geometry.view.model.impl.OpacityOptionImpl <em>Opacity Option</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.eavp.geometry.view.model.impl.OpacityOptionImpl
	 * @see org.eclipse.eavp.geometry.view.model.impl.ModelPackageImpl#getOpacityOption()
	 * @generated
	 */
	int OPACITY_OPTION = 4;

	/**
	 * The feature id for the '<em><b>Active</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPACITY_OPTION__ACTIVE = DISPLAY_OPTION__ACTIVE;

	/**
	 * The feature id for the '<em><b>Option Group</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPACITY_OPTION__OPTION_GROUP = DISPLAY_OPTION__OPTION_GROUP;

	/**
	 * The feature id for the '<em><b>Parent</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPACITY_OPTION__PARENT = DISPLAY_OPTION__PARENT;

	/**
	 * The feature id for the '<em><b>Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPACITY_OPTION__TYPE = DISPLAY_OPTION__TYPE;

	/**
	 * The number of structural features of the '<em>Opacity Option</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPACITY_OPTION_FEATURE_COUNT = DISPLAY_OPTION_FEATURE_COUNT + 0;

	/**
	 * The operation id for the '<em>Modify</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPACITY_OPTION___MODIFY__OBJECT = DISPLAY_OPTION___MODIFY__OBJECT;

	/**
	 * The operation id for the '<em>Get Default Properties</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPACITY_OPTION___GET_DEFAULT_PROPERTIES = DISPLAY_OPTION___GET_DEFAULT_PROPERTIES;

	/**
	 * The operation id for the '<em>Get Display Option Data</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPACITY_OPTION___GET_DISPLAY_OPTION_DATA = DISPLAY_OPTION___GET_DISPLAY_OPTION_DATA;

	/**
	 * The number of operations of the '<em>Opacity Option</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPACITY_OPTION_OPERATION_COUNT = DISPLAY_OPTION_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.eclipse.eavp.geometry.view.model.impl.ScaleOptionImpl <em>Scale Option</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.eavp.geometry.view.model.impl.ScaleOptionImpl
	 * @see org.eclipse.eavp.geometry.view.model.impl.ModelPackageImpl#getScaleOption()
	 * @generated
	 */
	int SCALE_OPTION = 5;

	/**
	 * The feature id for the '<em><b>Active</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SCALE_OPTION__ACTIVE = DISPLAY_OPTION__ACTIVE;

	/**
	 * The feature id for the '<em><b>Option Group</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SCALE_OPTION__OPTION_GROUP = DISPLAY_OPTION__OPTION_GROUP;

	/**
	 * The feature id for the '<em><b>Parent</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SCALE_OPTION__PARENT = DISPLAY_OPTION__PARENT;

	/**
	 * The feature id for the '<em><b>Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SCALE_OPTION__TYPE = DISPLAY_OPTION__TYPE;

	/**
	 * The number of structural features of the '<em>Scale Option</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SCALE_OPTION_FEATURE_COUNT = DISPLAY_OPTION_FEATURE_COUNT + 0;

	/**
	 * The operation id for the '<em>Modify</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SCALE_OPTION___MODIFY__OBJECT = DISPLAY_OPTION___MODIFY__OBJECT;

	/**
	 * The operation id for the '<em>Get Default Properties</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SCALE_OPTION___GET_DEFAULT_PROPERTIES = DISPLAY_OPTION___GET_DEFAULT_PROPERTIES;

	/**
	 * The operation id for the '<em>Get Display Option Data</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SCALE_OPTION___GET_DISPLAY_OPTION_DATA = DISPLAY_OPTION___GET_DISPLAY_OPTION_DATA;

	/**
	 * The number of operations of the '<em>Scale Option</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SCALE_OPTION_OPERATION_COUNT = DISPLAY_OPTION_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.eclipse.eavp.geometry.view.model.impl.WireframeOptionImpl <em>Wireframe Option</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.eavp.geometry.view.model.impl.WireframeOptionImpl
	 * @see org.eclipse.eavp.geometry.view.model.impl.ModelPackageImpl#getWireframeOption()
	 * @generated
	 */
	int WIREFRAME_OPTION = 6;

	/**
	 * The feature id for the '<em><b>Active</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WIREFRAME_OPTION__ACTIVE = DISPLAY_OPTION__ACTIVE;

	/**
	 * The feature id for the '<em><b>Option Group</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WIREFRAME_OPTION__OPTION_GROUP = DISPLAY_OPTION__OPTION_GROUP;

	/**
	 * The feature id for the '<em><b>Parent</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WIREFRAME_OPTION__PARENT = DISPLAY_OPTION__PARENT;

	/**
	 * The feature id for the '<em><b>Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WIREFRAME_OPTION__TYPE = DISPLAY_OPTION__TYPE;

	/**
	 * The number of structural features of the '<em>Wireframe Option</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WIREFRAME_OPTION_FEATURE_COUNT = DISPLAY_OPTION_FEATURE_COUNT + 0;

	/**
	 * The operation id for the '<em>Modify</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WIREFRAME_OPTION___MODIFY__OBJECT = DISPLAY_OPTION___MODIFY__OBJECT;

	/**
	 * The operation id for the '<em>Get Default Properties</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WIREFRAME_OPTION___GET_DEFAULT_PROPERTIES = DISPLAY_OPTION___GET_DEFAULT_PROPERTIES;

	/**
	 * The operation id for the '<em>Get Display Option Data</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WIREFRAME_OPTION___GET_DISPLAY_OPTION_DATA = DISPLAY_OPTION___GET_DISPLAY_OPTION_DATA;

	/**
	 * The number of operations of the '<em>Wireframe Option</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WIREFRAME_OPTION_OPERATION_COUNT = DISPLAY_OPTION_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.eclipse.eavp.geometry.view.model.impl.ColorOptionImpl <em>Color Option</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.eavp.geometry.view.model.impl.ColorOptionImpl
	 * @see org.eclipse.eavp.geometry.view.model.impl.ModelPackageImpl#getColorOption()
	 * @generated
	 */
	int COLOR_OPTION = 7;

	/**
	 * The feature id for the '<em><b>Active</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COLOR_OPTION__ACTIVE = DISPLAY_OPTION__ACTIVE;

	/**
	 * The feature id for the '<em><b>Option Group</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COLOR_OPTION__OPTION_GROUP = DISPLAY_OPTION__OPTION_GROUP;

	/**
	 * The feature id for the '<em><b>Parent</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COLOR_OPTION__PARENT = DISPLAY_OPTION__PARENT;

	/**
	 * The feature id for the '<em><b>Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COLOR_OPTION__TYPE = DISPLAY_OPTION__TYPE;

	/**
	 * The number of structural features of the '<em>Color Option</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COLOR_OPTION_FEATURE_COUNT = DISPLAY_OPTION_FEATURE_COUNT + 0;

	/**
	 * The operation id for the '<em>Modify</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COLOR_OPTION___MODIFY__OBJECT = DISPLAY_OPTION___MODIFY__OBJECT;

	/**
	 * The operation id for the '<em>Get Default Properties</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COLOR_OPTION___GET_DEFAULT_PROPERTIES = DISPLAY_OPTION___GET_DEFAULT_PROPERTIES;

	/**
	 * The operation id for the '<em>Get Display Option Data</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COLOR_OPTION___GET_DISPLAY_OPTION_DATA = DISPLAY_OPTION___GET_DISPLAY_OPTION_DATA;

	/**
	 * The number of operations of the '<em>Color Option</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COLOR_OPTION_OPERATION_COUNT = DISPLAY_OPTION_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.eclipse.eavp.geometry.view.model.IDisplayOptionData <em>IDisplay Option Data</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.eavp.geometry.view.model.IDisplayOptionData
	 * @see org.eclipse.eavp.geometry.view.model.impl.ModelPackageImpl#getIDisplayOptionData()
	 * @generated
	 */
	int IDISPLAY_OPTION_DATA = 8;

	/**
	 * The feature id for the '<em><b>Display Option</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IDISPLAY_OPTION_DATA__DISPLAY_OPTION = 0;

	/**
	 * The number of structural features of the '<em>IDisplay Option Data</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IDISPLAY_OPTION_DATA_FEATURE_COUNT = 1;

	/**
	 * The operation id for the '<em>Get Display Option Type</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IDISPLAY_OPTION_DATA___GET_DISPLAY_OPTION_TYPE = 0;

	/**
	 * The number of operations of the '<em>IDisplay Option Data</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IDISPLAY_OPTION_DATA_OPERATION_COUNT = 1;

	/**
	 * The meta object id for the '{@link org.eclipse.eavp.geometry.view.model.impl.ComboDisplayOptionDataImpl <em>Combo Display Option Data</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.eavp.geometry.view.model.impl.ComboDisplayOptionDataImpl
	 * @see org.eclipse.eavp.geometry.view.model.impl.ModelPackageImpl#getComboDisplayOptionData()
	 * @generated
	 */
	int COMBO_DISPLAY_OPTION_DATA = 9;

	/**
	 * The feature id for the '<em><b>Display Option</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMBO_DISPLAY_OPTION_DATA__DISPLAY_OPTION = IDISPLAY_OPTION_DATA__DISPLAY_OPTION;

	/**
	 * The feature id for the '<em><b>Text To Property Values Map</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMBO_DISPLAY_OPTION_DATA__TEXT_TO_PROPERTY_VALUES_MAP = IDISPLAY_OPTION_DATA_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Combo Display Option Data</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMBO_DISPLAY_OPTION_DATA_FEATURE_COUNT = IDISPLAY_OPTION_DATA_FEATURE_COUNT + 1;

	/**
	 * The operation id for the '<em>Get Display Option Type</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMBO_DISPLAY_OPTION_DATA___GET_DISPLAY_OPTION_TYPE = IDISPLAY_OPTION_DATA___GET_DISPLAY_OPTION_TYPE;

	/**
	 * The number of operations of the '<em>Combo Display Option Data</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMBO_DISPLAY_OPTION_DATA_OPERATION_COUNT = IDISPLAY_OPTION_DATA_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.eclipse.eavp.geometry.view.model.impl.DoubleTextDisplayOptionDataImpl <em>Double Text Display Option Data</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.eavp.geometry.view.model.impl.DoubleTextDisplayOptionDataImpl
	 * @see org.eclipse.eavp.geometry.view.model.impl.ModelPackageImpl#getDoubleTextDisplayOptionData()
	 * @generated
	 */
	int DOUBLE_TEXT_DISPLAY_OPTION_DATA = 10;

	/**
	 * The feature id for the '<em><b>Display Option</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOUBLE_TEXT_DISPLAY_OPTION_DATA__DISPLAY_OPTION = IDISPLAY_OPTION_DATA__DISPLAY_OPTION;

	/**
	 * The feature id for the '<em><b>Property To Value Map</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOUBLE_TEXT_DISPLAY_OPTION_DATA__PROPERTY_TO_VALUE_MAP = IDISPLAY_OPTION_DATA_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Double Text Display Option Data</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOUBLE_TEXT_DISPLAY_OPTION_DATA_FEATURE_COUNT = IDISPLAY_OPTION_DATA_FEATURE_COUNT + 1;

	/**
	 * The operation id for the '<em>Get Display Option Type</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOUBLE_TEXT_DISPLAY_OPTION_DATA___GET_DISPLAY_OPTION_TYPE = IDISPLAY_OPTION_DATA___GET_DISPLAY_OPTION_TYPE;

	/**
	 * The number of operations of the '<em>Double Text Display Option Data</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOUBLE_TEXT_DISPLAY_OPTION_DATA_OPERATION_COUNT = IDISPLAY_OPTION_DATA_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.eclipse.eavp.geometry.view.model.impl.IntegerTextDisplayOptionDataImpl <em>Integer Text Display Option Data</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.eavp.geometry.view.model.impl.IntegerTextDisplayOptionDataImpl
	 * @see org.eclipse.eavp.geometry.view.model.impl.ModelPackageImpl#getIntegerTextDisplayOptionData()
	 * @generated
	 */
	int INTEGER_TEXT_DISPLAY_OPTION_DATA = 11;

	/**
	 * The feature id for the '<em><b>Display Option</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INTEGER_TEXT_DISPLAY_OPTION_DATA__DISPLAY_OPTION = IDISPLAY_OPTION_DATA__DISPLAY_OPTION;

	/**
	 * The feature id for the '<em><b>Property To Value Map</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INTEGER_TEXT_DISPLAY_OPTION_DATA__PROPERTY_TO_VALUE_MAP = IDISPLAY_OPTION_DATA_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Integer Text Display Option Data</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INTEGER_TEXT_DISPLAY_OPTION_DATA_FEATURE_COUNT = IDISPLAY_OPTION_DATA_FEATURE_COUNT + 1;

	/**
	 * The operation id for the '<em>Get Display Option Type</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INTEGER_TEXT_DISPLAY_OPTION_DATA___GET_DISPLAY_OPTION_TYPE = IDISPLAY_OPTION_DATA___GET_DISPLAY_OPTION_TYPE;

	/**
	 * The number of operations of the '<em>Integer Text Display Option Data</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INTEGER_TEXT_DISPLAY_OPTION_DATA_OPERATION_COUNT = IDISPLAY_OPTION_DATA_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.eclipse.eavp.geometry.view.model.DisplayOptionType <em>Display Option Type</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.eavp.geometry.view.model.DisplayOptionType
	 * @see org.eclipse.eavp.geometry.view.model.impl.ModelPackageImpl#getDisplayOptionType()
	 * @generated
	 */
	int DISPLAY_OPTION_TYPE = 12;

	/**
	 * The meta object id for the '<em>INode</em>' data type.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.january.geometry.INode
	 * @see org.eclipse.eavp.geometry.view.model.impl.ModelPackageImpl#getINode()
	 * @generated
	 */
	int INODE = 13;


	/**
	 * The meta object id for the '<em>Triangle</em>' data type.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.january.geometry.Triangle
	 * @see org.eclipse.eavp.geometry.view.model.impl.ModelPackageImpl#getTriangle()
	 * @generated
	 */
	int TRIANGLE = 14;


	/**
	 * The meta object id for the '<em>Object</em>' data type.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see java.lang.Object
	 * @see org.eclipse.eavp.geometry.view.model.impl.ModelPackageImpl#getObject()
	 * @generated
	 */
	int OBJECT = 15;


	/**
	 * The meta object id for the '<em>Double</em>' data type.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see java.lang.Double
	 * @see org.eclipse.eavp.geometry.view.model.impl.ModelPackageImpl#getDouble()
	 * @generated
	 */
	int DOUBLE = 16;


	/**
	 * The meta object id for the '<em>Integer</em>' data type.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see java.lang.Integer
	 * @see org.eclipse.eavp.geometry.view.model.impl.ModelPackageImpl#getInteger()
	 * @generated
	 */
	int INTEGER = 17;


	/**
	 * The meta object id for the '<em>Boolean</em>' data type.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see java.lang.Boolean
	 * @see org.eclipse.eavp.geometry.view.model.impl.ModelPackageImpl#getBoolean()
	 * @generated
	 */
	int BOOLEAN = 18;


	/**
	 * Returns the meta object for class '{@link org.eclipse.eavp.geometry.view.model.MeshCache <em>Mesh Cache</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Mesh Cache</em>'.
	 * @see org.eclipse.eavp.geometry.view.model.MeshCache
	 * @generated
	 */
	EClass getMeshCache();

	/**
	 * Returns the meta object for the '{@link org.eclipse.eavp.geometry.view.model.MeshCache#getMesh(java.lang.String) <em>Get Mesh</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the '<em>Get Mesh</em>' operation.
	 * @see org.eclipse.eavp.geometry.view.model.MeshCache#getMesh(java.lang.String)
	 * @generated
	 */
	EOperation getMeshCache__GetMesh__String();

	/**
	 * Returns the meta object for the '{@link org.eclipse.eavp.geometry.view.model.MeshCache#getMesh(org.eclipse.emf.common.util.EList) <em>Get Mesh</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the '<em>Get Mesh</em>' operation.
	 * @see org.eclipse.eavp.geometry.view.model.MeshCache#getMesh(org.eclipse.emf.common.util.EList)
	 * @generated
	 */
	EOperation getMeshCache__GetMesh__EList();

	/**
	 * Returns the meta object for class '{@link org.eclipse.eavp.geometry.view.model.RenderObject <em>Render Object</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Render Object</em>'.
	 * @see org.eclipse.eavp.geometry.view.model.RenderObject
	 * @generated
	 */
	EClass getRenderObject();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.eavp.geometry.view.model.RenderObject#getMeshCache <em>Mesh Cache</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Mesh Cache</em>'.
	 * @see org.eclipse.eavp.geometry.view.model.RenderObject#getMeshCache()
	 * @see #getRenderObject()
	 * @generated
	 */
	EReference getRenderObject_MeshCache();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.eavp.geometry.view.model.RenderObject#getRender <em>Render</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Render</em>'.
	 * @see org.eclipse.eavp.geometry.view.model.RenderObject#getRender()
	 * @see #getRenderObject()
	 * @generated
	 */
	EAttribute getRenderObject_Render();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.eavp.geometry.view.model.RenderObject#getSource <em>Source</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Source</em>'.
	 * @see org.eclipse.eavp.geometry.view.model.RenderObject#getSource()
	 * @see #getRenderObject()
	 * @generated
	 */
	EAttribute getRenderObject_Source();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.eavp.geometry.view.model.RenderObject#getChildren <em>Children</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Children</em>'.
	 * @see org.eclipse.eavp.geometry.view.model.RenderObject#getChildren()
	 * @see #getRenderObject()
	 * @generated
	 */
	EReference getRenderObject_Children();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.eavp.geometry.view.model.RenderObject#getDisplayOptions <em>Display Options</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Display Options</em>'.
	 * @see org.eclipse.eavp.geometry.view.model.RenderObject#getDisplayOptions()
	 * @see #getRenderObject()
	 * @generated
	 */
	EReference getRenderObject_DisplayOptions();

	/**
	 * Returns the meta object for the '{@link org.eclipse.eavp.geometry.view.model.RenderObject#getMesh() <em>Get Mesh</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the '<em>Get Mesh</em>' operation.
	 * @see org.eclipse.eavp.geometry.view.model.RenderObject#getMesh()
	 * @generated
	 */
	EOperation getRenderObject__GetMesh();

	/**
	 * Returns the meta object for the '{@link org.eclipse.eavp.geometry.view.model.RenderObject#registerOption(org.eclipse.eavp.geometry.view.model.DisplayOption) <em>Register Option</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the '<em>Register Option</em>' operation.
	 * @see org.eclipse.eavp.geometry.view.model.RenderObject#registerOption(org.eclipse.eavp.geometry.view.model.DisplayOption)
	 * @generated
	 */
	EOperation getRenderObject__RegisterOption__DisplayOption();

	/**
	 * Returns the meta object for class '{@link org.eclipse.eavp.geometry.view.model.IRenderElement <em>IRender Element</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>IRender Element</em>'.
	 * @see org.eclipse.eavp.geometry.view.model.IRenderElement
	 * @generated
	 */
	EClass getIRenderElement();

	/**
	 * Returns the meta object for the '{@link org.eclipse.eavp.geometry.view.model.IRenderElement#getMesh() <em>Get Mesh</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the '<em>Get Mesh</em>' operation.
	 * @see org.eclipse.eavp.geometry.view.model.IRenderElement#getMesh()
	 * @generated
	 */
	EOperation getIRenderElement__GetMesh();

	/**
	 * Returns the meta object for the '{@link org.eclipse.eavp.geometry.view.model.IRenderElement#getBase() <em>Get Base</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the '<em>Get Base</em>' operation.
	 * @see org.eclipse.eavp.geometry.view.model.IRenderElement#getBase()
	 * @generated
	 */
	EOperation getIRenderElement__GetBase();

	/**
	 * Returns the meta object for the '{@link org.eclipse.eavp.geometry.view.model.IRenderElement#handleChildren(org.eclipse.emf.common.util.EList) <em>Handle Children</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the '<em>Handle Children</em>' operation.
	 * @see org.eclipse.eavp.geometry.view.model.IRenderElement#handleChildren(org.eclipse.emf.common.util.EList)
	 * @generated
	 */
	EOperation getIRenderElement__HandleChildren__EList();

	/**
	 * Returns the meta object for the '{@link org.eclipse.eavp.geometry.view.model.IRenderElement#getProperty(java.lang.String) <em>Get Property</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the '<em>Get Property</em>' operation.
	 * @see org.eclipse.eavp.geometry.view.model.IRenderElement#getProperty(java.lang.String)
	 * @generated
	 */
	EOperation getIRenderElement__GetProperty__String();

	/**
	 * Returns the meta object for the '{@link org.eclipse.eavp.geometry.view.model.IRenderElement#setProperty(java.lang.String, java.lang.Object) <em>Set Property</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the '<em>Set Property</em>' operation.
	 * @see org.eclipse.eavp.geometry.view.model.IRenderElement#setProperty(java.lang.String, java.lang.Object)
	 * @generated
	 */
	EOperation getIRenderElement__SetProperty__String_Object();

	/**
	 * Returns the meta object for the '{@link org.eclipse.eavp.geometry.view.model.IRenderElement#copy(java.lang.Object) <em>Copy</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the '<em>Copy</em>' operation.
	 * @see org.eclipse.eavp.geometry.view.model.IRenderElement#copy(java.lang.Object)
	 * @generated
	 */
	EOperation getIRenderElement__Copy__Object();

	/**
	 * Returns the meta object for the '{@link org.eclipse.eavp.geometry.view.model.IRenderElement#clone() <em>Clone</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the '<em>Clone</em>' operation.
	 * @see org.eclipse.eavp.geometry.view.model.IRenderElement#clone()
	 * @generated
	 */
	EOperation getIRenderElement__Clone();

	/**
	 * Returns the meta object for class '{@link org.eclipse.eavp.geometry.view.model.DisplayOption <em>Display Option</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Display Option</em>'.
	 * @see org.eclipse.eavp.geometry.view.model.DisplayOption
	 * @generated
	 */
	EClass getDisplayOption();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.eavp.geometry.view.model.DisplayOption#isActive <em>Active</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Active</em>'.
	 * @see org.eclipse.eavp.geometry.view.model.DisplayOption#isActive()
	 * @see #getDisplayOption()
	 * @generated
	 */
	EAttribute getDisplayOption_Active();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.eavp.geometry.view.model.DisplayOption#getOptionGroup <em>Option Group</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Option Group</em>'.
	 * @see org.eclipse.eavp.geometry.view.model.DisplayOption#getOptionGroup()
	 * @see #getDisplayOption()
	 * @generated
	 */
	EAttribute getDisplayOption_OptionGroup();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.eavp.geometry.view.model.DisplayOption#getParent <em>Parent</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Parent</em>'.
	 * @see org.eclipse.eavp.geometry.view.model.DisplayOption#getParent()
	 * @see #getDisplayOption()
	 * @generated
	 */
	EReference getDisplayOption_Parent();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.eavp.geometry.view.model.DisplayOption#getType <em>Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Type</em>'.
	 * @see org.eclipse.eavp.geometry.view.model.DisplayOption#getType()
	 * @see #getDisplayOption()
	 * @generated
	 */
	EAttribute getDisplayOption_Type();

	/**
	 * Returns the meta object for the '{@link org.eclipse.eavp.geometry.view.model.DisplayOption#modify(java.lang.Object) <em>Modify</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the '<em>Modify</em>' operation.
	 * @see org.eclipse.eavp.geometry.view.model.DisplayOption#modify(java.lang.Object)
	 * @generated
	 */
	EOperation getDisplayOption__Modify__Object();

	/**
	 * Returns the meta object for the '{@link org.eclipse.eavp.geometry.view.model.DisplayOption#getDefaultProperties() <em>Get Default Properties</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the '<em>Get Default Properties</em>' operation.
	 * @see org.eclipse.eavp.geometry.view.model.DisplayOption#getDefaultProperties()
	 * @generated
	 */
	EOperation getDisplayOption__GetDefaultProperties();

	/**
	 * Returns the meta object for the '{@link org.eclipse.eavp.geometry.view.model.DisplayOption#getDisplayOptionData() <em>Get Display Option Data</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the '<em>Get Display Option Data</em>' operation.
	 * @see org.eclipse.eavp.geometry.view.model.DisplayOption#getDisplayOptionData()
	 * @generated
	 */
	EOperation getDisplayOption__GetDisplayOptionData();

	/**
	 * Returns the meta object for class '{@link org.eclipse.eavp.geometry.view.model.OpacityOption <em>Opacity Option</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Opacity Option</em>'.
	 * @see org.eclipse.eavp.geometry.view.model.OpacityOption
	 * @generated
	 */
	EClass getOpacityOption();

	/**
	 * Returns the meta object for class '{@link org.eclipse.eavp.geometry.view.model.ScaleOption <em>Scale Option</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Scale Option</em>'.
	 * @see org.eclipse.eavp.geometry.view.model.ScaleOption
	 * @generated
	 */
	EClass getScaleOption();

	/**
	 * Returns the meta object for class '{@link org.eclipse.eavp.geometry.view.model.WireframeOption <em>Wireframe Option</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Wireframe Option</em>'.
	 * @see org.eclipse.eavp.geometry.view.model.WireframeOption
	 * @generated
	 */
	EClass getWireframeOption();

	/**
	 * Returns the meta object for class '{@link org.eclipse.eavp.geometry.view.model.ColorOption <em>Color Option</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Color Option</em>'.
	 * @see org.eclipse.eavp.geometry.view.model.ColorOption
	 * @generated
	 */
	EClass getColorOption();

	/**
	 * Returns the meta object for class '{@link org.eclipse.eavp.geometry.view.model.IDisplayOptionData <em>IDisplay Option Data</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>IDisplay Option Data</em>'.
	 * @see org.eclipse.eavp.geometry.view.model.IDisplayOptionData
	 * @generated
	 */
	EClass getIDisplayOptionData();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.eavp.geometry.view.model.IDisplayOptionData#getDisplayOption <em>Display Option</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Display Option</em>'.
	 * @see org.eclipse.eavp.geometry.view.model.IDisplayOptionData#getDisplayOption()
	 * @see #getIDisplayOptionData()
	 * @generated
	 */
	EReference getIDisplayOptionData_DisplayOption();

	/**
	 * Returns the meta object for the '{@link org.eclipse.eavp.geometry.view.model.IDisplayOptionData#getDisplayOptionType() <em>Get Display Option Type</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the '<em>Get Display Option Type</em>' operation.
	 * @see org.eclipse.eavp.geometry.view.model.IDisplayOptionData#getDisplayOptionType()
	 * @generated
	 */
	EOperation getIDisplayOptionData__GetDisplayOptionType();

	/**
	 * Returns the meta object for class '{@link org.eclipse.eavp.geometry.view.model.ComboDisplayOptionData <em>Combo Display Option Data</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Combo Display Option Data</em>'.
	 * @see org.eclipse.eavp.geometry.view.model.ComboDisplayOptionData
	 * @generated
	 */
	EClass getComboDisplayOptionData();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.eavp.geometry.view.model.ComboDisplayOptionData#getTextToPropertyValuesMap <em>Text To Property Values Map</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Text To Property Values Map</em>'.
	 * @see org.eclipse.eavp.geometry.view.model.ComboDisplayOptionData#getTextToPropertyValuesMap()
	 * @see #getComboDisplayOptionData()
	 * @generated
	 */
	EAttribute getComboDisplayOptionData_TextToPropertyValuesMap();

	/**
	 * Returns the meta object for class '{@link org.eclipse.eavp.geometry.view.model.DoubleTextDisplayOptionData <em>Double Text Display Option Data</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Double Text Display Option Data</em>'.
	 * @see org.eclipse.eavp.geometry.view.model.DoubleTextDisplayOptionData
	 * @generated
	 */
	EClass getDoubleTextDisplayOptionData();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.eavp.geometry.view.model.DoubleTextDisplayOptionData#getPropertyToValueMap <em>Property To Value Map</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Property To Value Map</em>'.
	 * @see org.eclipse.eavp.geometry.view.model.DoubleTextDisplayOptionData#getPropertyToValueMap()
	 * @see #getDoubleTextDisplayOptionData()
	 * @generated
	 */
	EAttribute getDoubleTextDisplayOptionData_PropertyToValueMap();

	/**
	 * Returns the meta object for class '{@link org.eclipse.eavp.geometry.view.model.IntegerTextDisplayOptionData <em>Integer Text Display Option Data</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Integer Text Display Option Data</em>'.
	 * @see org.eclipse.eavp.geometry.view.model.IntegerTextDisplayOptionData
	 * @generated
	 */
	EClass getIntegerTextDisplayOptionData();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.eavp.geometry.view.model.IntegerTextDisplayOptionData#getPropertyToValueMap <em>Property To Value Map</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Property To Value Map</em>'.
	 * @see org.eclipse.eavp.geometry.view.model.IntegerTextDisplayOptionData#getPropertyToValueMap()
	 * @see #getIntegerTextDisplayOptionData()
	 * @generated
	 */
	EAttribute getIntegerTextDisplayOptionData_PropertyToValueMap();

	/**
	 * Returns the meta object for enum '{@link org.eclipse.eavp.geometry.view.model.DisplayOptionType <em>Display Option Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>Display Option Type</em>'.
	 * @see org.eclipse.eavp.geometry.view.model.DisplayOptionType
	 * @generated
	 */
	EEnum getDisplayOptionType();

	/**
	 * Returns the meta object for data type '{@link org.eclipse.january.geometry.INode <em>INode</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for data type '<em>INode</em>'.
	 * @see org.eclipse.january.geometry.INode
	 * @model instanceClass="org.eclipse.january.geometry.INode"
	 * @generated
	 */
	EDataType getINode();

	/**
	 * Returns the meta object for data type '{@link org.eclipse.january.geometry.Triangle <em>Triangle</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for data type '<em>Triangle</em>'.
	 * @see org.eclipse.january.geometry.Triangle
	 * @model instanceClass="org.eclipse.january.geometry.Triangle"
	 * @generated
	 */
	EDataType getTriangle();

	/**
	 * Returns the meta object for data type '{@link java.lang.Object <em>Object</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for data type '<em>Object</em>'.
	 * @see java.lang.Object
	 * @model instanceClass="java.lang.Object"
	 * @generated
	 */
	EDataType getObject();

	/**
	 * Returns the meta object for data type '{@link java.lang.Double <em>Double</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for data type '<em>Double</em>'.
	 * @see java.lang.Double
	 * @model instanceClass="java.lang.Double"
	 * @generated
	 */
	EDataType getDouble();

	/**
	 * Returns the meta object for data type '{@link java.lang.Integer <em>Integer</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for data type '<em>Integer</em>'.
	 * @see java.lang.Integer
	 * @model instanceClass="java.lang.Integer"
	 * @generated
	 */
	EDataType getInteger();

	/**
	 * Returns the meta object for data type '{@link java.lang.Boolean <em>Boolean</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for data type '<em>Boolean</em>'.
	 * @see java.lang.Boolean
	 * @model instanceClass="java.lang.Boolean"
	 * @generated
	 */
	EDataType getBoolean();

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
		 * The meta object literal for the '{@link org.eclipse.eavp.geometry.view.model.impl.MeshCacheImpl <em>Mesh Cache</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.eavp.geometry.view.model.impl.MeshCacheImpl
		 * @see org.eclipse.eavp.geometry.view.model.impl.ModelPackageImpl#getMeshCache()
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
		 * The meta object literal for the '{@link org.eclipse.eavp.geometry.view.model.impl.RenderObjectImpl <em>Render Object</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.eavp.geometry.view.model.impl.RenderObjectImpl
		 * @see org.eclipse.eavp.geometry.view.model.impl.ModelPackageImpl#getRenderObject()
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
		 * The meta object literal for the '<em><b>Children</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference RENDER_OBJECT__CHILDREN = eINSTANCE.getRenderObject_Children();

		/**
		 * The meta object literal for the '<em><b>Display Options</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference RENDER_OBJECT__DISPLAY_OPTIONS = eINSTANCE.getRenderObject_DisplayOptions();

		/**
		 * The meta object literal for the '<em><b>Get Mesh</b></em>' operation.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EOperation RENDER_OBJECT___GET_MESH = eINSTANCE.getRenderObject__GetMesh();

		/**
		 * The meta object literal for the '<em><b>Register Option</b></em>' operation.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EOperation RENDER_OBJECT___REGISTER_OPTION__DISPLAYOPTION = eINSTANCE.getRenderObject__RegisterOption__DisplayOption();

		/**
		 * The meta object literal for the '{@link org.eclipse.eavp.geometry.view.model.IRenderElement <em>IRender Element</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.eavp.geometry.view.model.IRenderElement
		 * @see org.eclipse.eavp.geometry.view.model.impl.ModelPackageImpl#getIRenderElement()
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
		 * The meta object literal for the '<em><b>Handle Children</b></em>' operation.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EOperation IRENDER_ELEMENT___HANDLE_CHILDREN__ELIST = eINSTANCE.getIRenderElement__HandleChildren__EList();

		/**
		 * The meta object literal for the '<em><b>Get Property</b></em>' operation.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EOperation IRENDER_ELEMENT___GET_PROPERTY__STRING = eINSTANCE.getIRenderElement__GetProperty__String();

		/**
		 * The meta object literal for the '<em><b>Set Property</b></em>' operation.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EOperation IRENDER_ELEMENT___SET_PROPERTY__STRING_OBJECT = eINSTANCE.getIRenderElement__SetProperty__String_Object();

		/**
		 * The meta object literal for the '<em><b>Copy</b></em>' operation.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EOperation IRENDER_ELEMENT___COPY__OBJECT = eINSTANCE.getIRenderElement__Copy__Object();

		/**
		 * The meta object literal for the '<em><b>Clone</b></em>' operation.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EOperation IRENDER_ELEMENT___CLONE = eINSTANCE.getIRenderElement__Clone();

		/**
		 * The meta object literal for the '{@link org.eclipse.eavp.geometry.view.model.impl.DisplayOptionImpl <em>Display Option</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.eavp.geometry.view.model.impl.DisplayOptionImpl
		 * @see org.eclipse.eavp.geometry.view.model.impl.ModelPackageImpl#getDisplayOption()
		 * @generated
		 */
		EClass DISPLAY_OPTION = eINSTANCE.getDisplayOption();

		/**
		 * The meta object literal for the '<em><b>Active</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DISPLAY_OPTION__ACTIVE = eINSTANCE.getDisplayOption_Active();

		/**
		 * The meta object literal for the '<em><b>Option Group</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DISPLAY_OPTION__OPTION_GROUP = eINSTANCE.getDisplayOption_OptionGroup();

		/**
		 * The meta object literal for the '<em><b>Parent</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DISPLAY_OPTION__PARENT = eINSTANCE.getDisplayOption_Parent();

		/**
		 * The meta object literal for the '<em><b>Type</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DISPLAY_OPTION__TYPE = eINSTANCE.getDisplayOption_Type();

		/**
		 * The meta object literal for the '<em><b>Modify</b></em>' operation.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EOperation DISPLAY_OPTION___MODIFY__OBJECT = eINSTANCE.getDisplayOption__Modify__Object();

		/**
		 * The meta object literal for the '<em><b>Get Default Properties</b></em>' operation.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EOperation DISPLAY_OPTION___GET_DEFAULT_PROPERTIES = eINSTANCE.getDisplayOption__GetDefaultProperties();

		/**
		 * The meta object literal for the '<em><b>Get Display Option Data</b></em>' operation.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EOperation DISPLAY_OPTION___GET_DISPLAY_OPTION_DATA = eINSTANCE.getDisplayOption__GetDisplayOptionData();

		/**
		 * The meta object literal for the '{@link org.eclipse.eavp.geometry.view.model.impl.OpacityOptionImpl <em>Opacity Option</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.eavp.geometry.view.model.impl.OpacityOptionImpl
		 * @see org.eclipse.eavp.geometry.view.model.impl.ModelPackageImpl#getOpacityOption()
		 * @generated
		 */
		EClass OPACITY_OPTION = eINSTANCE.getOpacityOption();

		/**
		 * The meta object literal for the '{@link org.eclipse.eavp.geometry.view.model.impl.ScaleOptionImpl <em>Scale Option</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.eavp.geometry.view.model.impl.ScaleOptionImpl
		 * @see org.eclipse.eavp.geometry.view.model.impl.ModelPackageImpl#getScaleOption()
		 * @generated
		 */
		EClass SCALE_OPTION = eINSTANCE.getScaleOption();

		/**
		 * The meta object literal for the '{@link org.eclipse.eavp.geometry.view.model.impl.WireframeOptionImpl <em>Wireframe Option</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.eavp.geometry.view.model.impl.WireframeOptionImpl
		 * @see org.eclipse.eavp.geometry.view.model.impl.ModelPackageImpl#getWireframeOption()
		 * @generated
		 */
		EClass WIREFRAME_OPTION = eINSTANCE.getWireframeOption();

		/**
		 * The meta object literal for the '{@link org.eclipse.eavp.geometry.view.model.impl.ColorOptionImpl <em>Color Option</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.eavp.geometry.view.model.impl.ColorOptionImpl
		 * @see org.eclipse.eavp.geometry.view.model.impl.ModelPackageImpl#getColorOption()
		 * @generated
		 */
		EClass COLOR_OPTION = eINSTANCE.getColorOption();

		/**
		 * The meta object literal for the '{@link org.eclipse.eavp.geometry.view.model.IDisplayOptionData <em>IDisplay Option Data</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.eavp.geometry.view.model.IDisplayOptionData
		 * @see org.eclipse.eavp.geometry.view.model.impl.ModelPackageImpl#getIDisplayOptionData()
		 * @generated
		 */
		EClass IDISPLAY_OPTION_DATA = eINSTANCE.getIDisplayOptionData();

		/**
		 * The meta object literal for the '<em><b>Display Option</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference IDISPLAY_OPTION_DATA__DISPLAY_OPTION = eINSTANCE.getIDisplayOptionData_DisplayOption();

		/**
		 * The meta object literal for the '<em><b>Get Display Option Type</b></em>' operation.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EOperation IDISPLAY_OPTION_DATA___GET_DISPLAY_OPTION_TYPE = eINSTANCE.getIDisplayOptionData__GetDisplayOptionType();

		/**
		 * The meta object literal for the '{@link org.eclipse.eavp.geometry.view.model.impl.ComboDisplayOptionDataImpl <em>Combo Display Option Data</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.eavp.geometry.view.model.impl.ComboDisplayOptionDataImpl
		 * @see org.eclipse.eavp.geometry.view.model.impl.ModelPackageImpl#getComboDisplayOptionData()
		 * @generated
		 */
		EClass COMBO_DISPLAY_OPTION_DATA = eINSTANCE.getComboDisplayOptionData();

		/**
		 * The meta object literal for the '<em><b>Text To Property Values Map</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute COMBO_DISPLAY_OPTION_DATA__TEXT_TO_PROPERTY_VALUES_MAP = eINSTANCE.getComboDisplayOptionData_TextToPropertyValuesMap();

		/**
		 * The meta object literal for the '{@link org.eclipse.eavp.geometry.view.model.impl.DoubleTextDisplayOptionDataImpl <em>Double Text Display Option Data</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.eavp.geometry.view.model.impl.DoubleTextDisplayOptionDataImpl
		 * @see org.eclipse.eavp.geometry.view.model.impl.ModelPackageImpl#getDoubleTextDisplayOptionData()
		 * @generated
		 */
		EClass DOUBLE_TEXT_DISPLAY_OPTION_DATA = eINSTANCE.getDoubleTextDisplayOptionData();

		/**
		 * The meta object literal for the '<em><b>Property To Value Map</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DOUBLE_TEXT_DISPLAY_OPTION_DATA__PROPERTY_TO_VALUE_MAP = eINSTANCE.getDoubleTextDisplayOptionData_PropertyToValueMap();

		/**
		 * The meta object literal for the '{@link org.eclipse.eavp.geometry.view.model.impl.IntegerTextDisplayOptionDataImpl <em>Integer Text Display Option Data</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.eavp.geometry.view.model.impl.IntegerTextDisplayOptionDataImpl
		 * @see org.eclipse.eavp.geometry.view.model.impl.ModelPackageImpl#getIntegerTextDisplayOptionData()
		 * @generated
		 */
		EClass INTEGER_TEXT_DISPLAY_OPTION_DATA = eINSTANCE.getIntegerTextDisplayOptionData();

		/**
		 * The meta object literal for the '<em><b>Property To Value Map</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute INTEGER_TEXT_DISPLAY_OPTION_DATA__PROPERTY_TO_VALUE_MAP = eINSTANCE.getIntegerTextDisplayOptionData_PropertyToValueMap();

		/**
		 * The meta object literal for the '{@link org.eclipse.eavp.geometry.view.model.DisplayOptionType <em>Display Option Type</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.eavp.geometry.view.model.DisplayOptionType
		 * @see org.eclipse.eavp.geometry.view.model.impl.ModelPackageImpl#getDisplayOptionType()
		 * @generated
		 */
		EEnum DISPLAY_OPTION_TYPE = eINSTANCE.getDisplayOptionType();

		/**
		 * The meta object literal for the '<em>INode</em>' data type.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.january.geometry.INode
		 * @see org.eclipse.eavp.geometry.view.model.impl.ModelPackageImpl#getINode()
		 * @generated
		 */
		EDataType INODE = eINSTANCE.getINode();

		/**
		 * The meta object literal for the '<em>Triangle</em>' data type.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.january.geometry.Triangle
		 * @see org.eclipse.eavp.geometry.view.model.impl.ModelPackageImpl#getTriangle()
		 * @generated
		 */
		EDataType TRIANGLE = eINSTANCE.getTriangle();

		/**
		 * The meta object literal for the '<em>Object</em>' data type.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see java.lang.Object
		 * @see org.eclipse.eavp.geometry.view.model.impl.ModelPackageImpl#getObject()
		 * @generated
		 */
		EDataType OBJECT = eINSTANCE.getObject();

		/**
		 * The meta object literal for the '<em>Double</em>' data type.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see java.lang.Double
		 * @see org.eclipse.eavp.geometry.view.model.impl.ModelPackageImpl#getDouble()
		 * @generated
		 */
		EDataType DOUBLE = eINSTANCE.getDouble();

		/**
		 * The meta object literal for the '<em>Integer</em>' data type.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see java.lang.Integer
		 * @see org.eclipse.eavp.geometry.view.model.impl.ModelPackageImpl#getInteger()
		 * @generated
		 */
		EDataType INTEGER = eINSTANCE.getInteger();

		/**
		 * The meta object literal for the '<em>Boolean</em>' data type.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see java.lang.Boolean
		 * @see org.eclipse.eavp.geometry.view.model.impl.ModelPackageImpl#getBoolean()
		 * @generated
		 */
		EDataType BOOLEAN = eINSTANCE.getBoolean();

	}

} //ModelPackage
