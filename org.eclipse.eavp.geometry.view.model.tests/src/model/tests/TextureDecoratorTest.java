/**
 */
package model.tests;

import model.ModelFactory;
import model.TextureDecorator;

/**
 * <!-- begin-user-doc --> A test case for the model object '<em><b>Texture
 * Decorator</b></em>'. <!-- end-user-doc -->
 * 
 * @generated
 */
public class TextureDecoratorTest extends RenderObjectDecoratorTest {

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public static void main(String[] args) {
	}

	/**
	 * Constructs a new Texture Decorator test case with the given name. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public TextureDecoratorTest() {
		super();
	}

	/**
	 * Returns the fixture for this Texture Decorator test case. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	protected TextureDecorator<?> getFixture() {
		return (TextureDecorator<?>) fixture;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see junit.framework.TestCase#setUp()
	 * @generated
	 */
	@Override
	protected void setUp() throws Exception {
		setFixture(ModelFactory.eINSTANCE.createTextureDecorator());
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

} // TextureDecoratorTest
