/**
 */
package geometry.tests;

import geometry.GeometryFactory;
import geometry.Tube;

import junit.textui.TestRunner;

/**
 * <!-- begin-user-doc -->
 * A test case for the model object '<em><b>Tube</b></em>'.
 * <!-- end-user-doc -->
 * @generated
 */
public class TubeTest extends ShapeTest {

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static void main(String[] args) {
		TestRunner.run(TubeTest.class);
	}

	/**
	 * Constructs a new Tube test case with the given name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TubeTest(String name) {
		super(name);
	}

	/**
	 * Returns the fixture for this Tube test case.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected Tube getFixture() {
		return (Tube)fixture;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see junit.framework.TestCase#setUp()
	 * @generated
	 */
	@Override
	protected void setUp() throws Exception {
		setFixture(GeometryFactory.eINSTANCE.createTube());
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
	 * Check that only valid values are accepted for the properties.
	 * 
	 * @generated NOT
	 */
	public void checkProperties(){
		
		//The tube to be tested
		Tube tube = GeometryFactory.eINSTANCE.createTube();
		
		//Check that height can be set
		tube.setHeight(1);
		assertEquals(1, tube.getHeight());
		
		//Check that negative values will be rejected
		tube.setHeight(-2);
		assertEquals(1, tube.getHeight());
		
		//Check that radius can be set
		tube.setRadius(1);
		assertEquals(1, tube.getRadius());
		
		//Check that negative values will be rejected
		tube.setRadius(-2);
		assertEquals(1, tube.getRadius());
		
		//Check that inner radius can be set
		tube.setInnerRadius(0.5);
		assertEquals(0.5, tube.getInnerRadius());
		
		//Check that negative values will be rejected
		tube.setInnerRadius(-2);
		assertEquals(0.5, tube.getInnerRadius());
		
		//Check that the inner radius is constrained by the outer radius
		tube.setInnerRadius(2);
		assertEquals(0.5, tube.getInnerRadius());
		tube.setInnerRadius(1);
		assertEquals(1, tube.getInnerRadius());
		
		//Check that the radius is constrained by the inner radius
		tube.setRadius(0.5);
		assertEquals(1, tube.getRadius());
	}

} //TubeTest
