/**
 */
package model.tests;

import model.ColorDecorator;
import model.ModelFactory;

/**
 * <!-- begin-user-doc --> A test case for the model object '<em><b>Color
 * Decorator</b></em>'. <!-- end-user-doc -->
 * 
 * @generated
 */
public class ColorDecoratorTest extends RenderObjectDecoratorTest {

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public static void main(String[] args) {
	}

	/**
	 * Constructs a new Color Decorator test case with the given name. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public ColorDecoratorTest() {
		super();
	}

	/**
	 * Returns the fixture for this Color Decorator test case. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	protected ColorDecorator<?> getFixture() {
		return (ColorDecorator<?>) fixture;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see junit.framework.TestCase#setUp()
	 * @generated
	 */
	@Override
	protected void setUp() throws Exception {
		setFixture(ModelFactory.eINSTANCE.createColorDecorator());
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

} // ColorDecoratorTest
