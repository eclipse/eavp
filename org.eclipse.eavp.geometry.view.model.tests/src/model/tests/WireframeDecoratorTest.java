/**
 */
package model.tests;

import model.ModelFactory;
import model.WireframeDecorator;

/**
 * <!-- begin-user-doc --> A test case for the model object '<em><b>Wireframe
 * Decorator</b></em>'. <!-- end-user-doc -->
 * 
 * @generated
 */
public class WireframeDecoratorTest extends RenderObjectDecoratorTest {

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public static void main(String[] args) {
	}

	/**
	 * Constructs a new Wireframe Decorator test case with the given name. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public WireframeDecoratorTest() {
		super();
	}

	/**
	 * Returns the fixture for this Wireframe Decorator test case. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	protected WireframeDecorator<?> getFixture() {
		return (WireframeDecorator<?>) fixture;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see junit.framework.TestCase#setUp()
	 * @generated
	 */
	@Override
	protected void setUp() throws Exception {
		setFixture(ModelFactory.eINSTANCE.createWireframeDecorator());
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see junit.framework.TestCase#tearDown()
	 * @generated
	 */
	@Override
	protected void tearDown() throws Exception {
		setFixture(null);
	}

} // WireframeDecoratorTest
