/**
 */
package geometry.impl;

import java.lang.reflect.InvocationTargetException;
import java.nio.file.Path;
import java.util.Collection;

import org.eclipse.eavp.STLStandaloneSetup;
import org.eclipse.eavp.sTL.Model;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.util.EDataTypeUniqueEList;
import org.eclipse.xtext.resource.XtextResource;
import org.eclipse.xtext.resource.XtextResourceSet;

import com.google.inject.Injector;

import geometry.ASCIISTLGeometryImporter;
import geometry.Geometry;
import geometry.GeometryFactory;
import geometry.GeometryPackage;
import geometry.Shape;
import geometry.Triangle;
import geometry.Vertex;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>ASCIISTL Geometry Importer</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 * <li>{@link geometry.impl.ASCIISTLGeometryImporterImpl#getFileTypes
 * <em>File Types</em>}</li>
 * <li>{@link geometry.impl.ASCIISTLGeometryImporterImpl#getDescription
 * <em>Description</em>}</li>
 * </ul>
 *
 * @generated
 */
public class ASCIISTLGeometryImporterImpl extends MinimalEObjectImpl.Container implements ASCIISTLGeometryImporter {
	/**
	 * The cached value of the '{@link #getFileTypes() <em>File Types</em>}'
	 * attribute list. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getFileTypes()
	 * @generated
	 * @ordered
	 */
	protected EList<String> fileTypes;

	/**
	 * The default value of the '{@link #getDescription() <em>Description</em>}'
	 * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getDescription()
	 * @generated
	 * @ordered
	 */
	protected static final String DESCRIPTION_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getDescription() <em>Description</em>}'
	 * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getDescription()
	 * @generated
	 * @ordered
	 */
	protected String description = DESCRIPTION_EDEFAULT;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected ASCIISTLGeometryImporterImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return GeometryPackage.Literals.ASCIISTL_GEOMETRY_IMPORTER;
	}

	/**
	 * <!-- begin-user-doc --> 
	 * Gets the file type extensions that this class can load from
	 * <!-- end-user-doc -->
	 * 
	 */
	@Override
	public EList<String> getFileTypes() {
		if (fileTypes == null) {
			fileTypes = new EDataTypeUniqueEList<String>(String.class, this,
					GeometryPackage.ASCIISTL_GEOMETRY_IMPORTER__FILE_TYPES);
			fileTypes.add(".stl");
			fileTypes.add(".STL");
		}
		return fileTypes;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public String getDescription() {
		return description;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public void setDescription(String newDescription) {
		String oldDescription = description;
		description = newDescription;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET,
					GeometryPackage.ASCIISTL_GEOMETRY_IMPORTER__DESCRIPTION, oldDescription, description));
	}

	/**
	 * <!-- begin-user-doc --> Loads a geometry from the specified STL file. If
	 * the STL file cannot be loaded with the given path, will return null. <!--
	 * end-user-doc -->
	 *
	 */
	@Override
	public Geometry load(Path path) {
		// TODO: implement this method
		// Ensure that you remove @generated or mark it @generated NOT
		Injector injector = new STLStandaloneSetup().createInjectorAndDoEMFRegistration();
		XtextResourceSet resourceSet = injector.getInstance(XtextResourceSet.class);
		resourceSet.addLoadOption(XtextResource.OPTION_RESOLVE_ALL, Boolean.TRUE);
		Resource resource = resourceSet.getResource(URI.createFileURI("file:" + path.toString()), true);

		// Get the model (it is assumed to be the first element from the
		// grammar)
		Model model = (Model) resource.getContents().get(0);

		return getGeometryFromModel(model);
	}

	/**
	 * Gets the geometry from the XText EMF model
	 * 
	 * @param model
	 *            The model to read the data from
	 * @return Returns the Geometry from the Model
	 */
	private Geometry getGeometryFromModel(Model model) {
		// Create a new geometry variable
		Geometry geom = null;
		
		// Checks if the model was loaded successfully
		if (model != null && !model.getTriangles().isEmpty()) {
			
			// Instantiate the geometry instance
			geom= GeometryFactory.eINSTANCE.createGeometry();
	
			// Create a shape to add to the geometry
			Shape newShape = GeometryFactory.eINSTANCE.createShape();
	
			// Get the list of triangles from the model
			EList<org.eclipse.eavp.sTL.Triangle> triList = model.getTriangles();
	
			// Create a Geometry.Triangle from each STL triangle, add to the list of
			// triangles in shape
			for (org.eclipse.eavp.sTL.Triangle t : triList) {
	
				// Create a triangle
				Triangle newTri = GeometryFactory.eINSTANCE.createTriangle();
	
				// Set the new triangle's normal
				Vertex norm = newTri.getNormal();
				norm.setX(t.getNormal().getVector().getX());
				norm.setY(t.getNormal().getVector().getY());
				norm.setZ(t.getNormal().getVector().getZ());
	
				// Create vertex 1, set it's values
				Vertex v1 = GeometryFactory.eINSTANCE.createVertex();
				v1.setX(t.getVerticies().getV1().getX());
				v1.setY(t.getVerticies().getV1().getY());
				v1.setZ(t.getVerticies().getV1().getZ());
	
				// Create vertex 2, set it's values
				Vertex v2 = GeometryFactory.eINSTANCE.createVertex();
				v2.setX(t.getVerticies().getV2().getX());
				v2.setY(t.getVerticies().getV2().getY());
				v2.setZ(t.getVerticies().getV2().getZ());
	
				// Create vertex 3, set it's values
				Vertex v3 = GeometryFactory.eINSTANCE.createVertex();
				v3.setX(t.getVerticies().getV3().getX());
				v3.setY(t.getVerticies().getV3().getY());
				v3.setZ(t.getVerticies().getV3().getZ());
	
				// Add the new vertices to the triangle
				newTri.getVertices().add(v1);
				newTri.getVertices().add(v2);
				newTri.getVertices().add(v3);
	
				// Add the new triangle to the shape
				newShape.getTriangles().add(newTri);
			}
	
			// Set the geometry name from the model
			geom.setName(model.getName());
		
		}

		// Return the geometry
		return geom;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
		case GeometryPackage.ASCIISTL_GEOMETRY_IMPORTER__FILE_TYPES:
			return getFileTypes();
		case GeometryPackage.ASCIISTL_GEOMETRY_IMPORTER__DESCRIPTION:
			return getDescription();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
		case GeometryPackage.ASCIISTL_GEOMETRY_IMPORTER__FILE_TYPES:
			getFileTypes().clear();
			getFileTypes().addAll((Collection<? extends String>) newValue);
			return;
		case GeometryPackage.ASCIISTL_GEOMETRY_IMPORTER__DESCRIPTION:
			setDescription((String) newValue);
			return;
		}
		super.eSet(featureID, newValue);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public void eUnset(int featureID) {
		switch (featureID) {
		case GeometryPackage.ASCIISTL_GEOMETRY_IMPORTER__FILE_TYPES:
			getFileTypes().clear();
			return;
		case GeometryPackage.ASCIISTL_GEOMETRY_IMPORTER__DESCRIPTION:
			setDescription(DESCRIPTION_EDEFAULT);
			return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public boolean eIsSet(int featureID) {
		switch (featureID) {
		case GeometryPackage.ASCIISTL_GEOMETRY_IMPORTER__FILE_TYPES:
			return fileTypes != null && !fileTypes.isEmpty();
		case GeometryPackage.ASCIISTL_GEOMETRY_IMPORTER__DESCRIPTION:
			return DESCRIPTION_EDEFAULT == null ? description != null : !DESCRIPTION_EDEFAULT.equals(description);
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public Object eInvoke(int operationID, EList<?> arguments) throws InvocationTargetException {
		switch (operationID) {
		case GeometryPackage.ASCIISTL_GEOMETRY_IMPORTER___LOAD__PATH:
			return load((Path) arguments.get(0));
		}
		return super.eInvoke(operationID, arguments);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public String toString() {
		if (eIsProxy())
			return super.toString();

		StringBuffer result = new StringBuffer(super.toString());
		result.append(" (fileTypes: ");
		result.append(fileTypes);
		result.append(", description: ");
		result.append(description);
		result.append(')');
		return result.toString();
	}

} // ASCIISTLGeometryImporterImpl
