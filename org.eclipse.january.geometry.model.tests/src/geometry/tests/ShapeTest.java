/**
 */
package geometry.tests;

import org.eclipse.emf.common.util.EList;
import org.eclipse.january.geometry.GeometryFactory;
import org.eclipse.january.geometry.Shape;

import junit.framework.TestCase;

import junit.textui.TestRunner;

/**
 * <!-- begin-user-doc -->
 * A test case for the model object '<em><b>Shape</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following operations are tested:
 * <ul>
 *   <li>{@link org.eclipse.january.geometry.Shape#getPropertyNames() <em>Get Property Names</em>}</li>
 *   <li>{@link org.eclipse.january.geometry.Shape#getProperty(java.lang.String) <em>Get Property</em>}</li>
 *   <li>{@link org.eclipse.january.geometry.Shape#setProperty(java.lang.String, double) <em>Set Property</em>}</li>
 *   <li>{@link geometry.ISubjectShape#register(geometry.IShapeObserver) <em>Register</em>}</li>
 *   <li>{@link geometry.ISubjectShape#unregister(geometry.IShapeObserver) <em>Unregister</em>}</li>
 * </ul>
 * </p>
 * @generated
 */
public class ShapeTest extends TestCase {

	/**
	 * The fixture for this Shape test case.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected Shape fixture = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static void main(String[] args) {
		TestRunner.run(ShapeTest.class);
	}

	/**
	 * Constructs a new Shape test case with the given name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ShapeTest(String name) {
		super(name);
	}

	/**
	 * Sets the fixture for this Shape test case.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void setFixture(Shape fixture) {
		this.fixture = fixture;
	}

	/**
	 * Returns the fixture for this Shape test case.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected Shape getFixture() {
		return fixture;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see junit.framework.TestCase#setUp()
	 * @generated
	 */
	@Override
	protected void setUp() throws Exception {
		setFixture(GeometryFactory.eINSTANCE.createShape());
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see junit.framework.TestCase#tearDown()
	 * @generated
	 */
	@Override
	protected void tearDown() throws Exception {
		setFixture(null);
	}

	/**
	 * Tests the '{@link org.eclipse.january.geometry.Shape#getPropertyNames() <em>Get Property Names</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.january.geometry.Shape#getPropertyNames()
	 * @generated NOT
	 */
	public void testGetPropertyNames() {
		
		//The shape for testing
		Shape shape = GeometryFactory.eINSTANCE.createShape();
		
		//The shape should initially have no properties
		EList<String> properties = shape.getPropertyNames();
		assertTrue(properties.isEmpty());
		
		//Add two new properties
		shape.setProperty("test", 0);
		shape.setProperty("test2", 0);
		
		//The properties should now have two names: one for each of the ones just added
		properties = shape.getPropertyNames();
		assertEquals(2, properties.size());
		assertTrue(properties.contains("test"));
		assertTrue(properties.contains("test2"));
	}

	/**
	 * Tests the '{@link org.eclipse.january.geometry.Shape#getProperty(java.lang.String) <em>Get Property</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.january.geometry.Shape#getProperty(java.lang.String)
	 * @generated NOT
	 */
	public void testGetProperty__String() {

		//The shape for testing
		Shape shape = GeometryFactory.eINSTANCE.createShape();
		
		//Set some properties for the shape
		shape.setProperty("test", 1);
		shape.setProperty("test2", 2);
		
		//Get the properties and check that they are equal
		assertEquals(1, shape.getProperty("test"));
		assertEquals(2, shape.getProperty("test2"));
	}

	/**
	 * Tests the '{@link org.eclipse.january.geometry.Shape#setProperty(java.lang.String, double) <em>Set Property</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.january.geometry.Shape#setProperty(java.lang.String, double)
	 * @generated NOT
	 */
	public void testSetProperty__String_double() {
		// Nothing to do here. See testGetProperty__String() instead.
	}

	/**
	 * Tests the '{@link geometry.ISubjectShape#register(geometry.IShapeObserver) <em>Register</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see geometry.ISubjectShape#register(geometry.IShapeObserver)
	 * @generated
	 */
	public void testRegister__IShapeObserver() {
		// TODO: implement this operation test method
		// Ensure that you remove @generated or mark it @generated NOT
		fail();
	}

	/**
	 * Tests the '{@link geometry.ISubjectShape#unregister(geometry.IShapeObserver) <em>Unregister</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see geometry.ISubjectShape#unregister(geometry.IShapeObserver)
	 * @generated
	 */
	public void testUnregister__IShapeObserver() {
		// TODO: implement this operation test method
		// Ensure that you remove @generated or mark it @generated NOT
		fail();
	}

} //ShapeTest
