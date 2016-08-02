/**
 */
package model.tests;

import model.ModelFactory;
import model.OpacityDecorator;

/**
 * <!-- begin-user-doc --> A test case for the model object '<em><b>Opacity
 * Decorator</b></em>'. <!-- end-user-doc -->
 * 
 * @generated
 */
public class OpacityDecoratorTest extends RenderObjectDecoratorTest {

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public static void main(String[] args) {
	}

	/**
	 * Constructs a new Opacity Decorator test case with the given name. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public OpacityDecoratorTest() {
		super();
	}

	/**
	 * Returns the fixture for this Opacity Decorator test case. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	protected OpacityDecorator<?> getFixture() {
		return (OpacityDecorator<?>) fixture;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see junit.framework.TestCase#setUp()
	 * @generated
	 */
	@Override
	protected void setUp() throws Exception {
		setFixture(ModelFactory.eINSTANCE.createOpacityDecorator());
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

} // OpacityDecoratorTest
