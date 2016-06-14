/**
 */
package model.tests;

import geometry.impl.ShapeImpl;
import junit.framework.TestCase;

import junit.textui.TestRunner;

import model.ModelFactory;
import model.RenderObject;
import model.RenderObjectDecorator;
import model.impl.RenderObjectImpl;

/**
 * <!-- begin-user-doc -->
 * A test case for the model object '<em><b>Render Object Decorator</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following operations are tested:
 * <ul>
 *   <li>{@link model.IRenderElement#getMesh() <em>Get Mesh</em>}</li>
 * </ul>
 * </p>
 * @generated
 */
public class RenderObjectDecoratorTest extends TestCase {

	/**
	 * The fixture for this Render Object Decorator test case.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected RenderObjectDecorator<?> fixture = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static void main(String[] args) {
		TestRunner.run(RenderObjectDecoratorTest.class);
	}

	/**
	 * Constructs a new Render Object Decorator test case with the given name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public RenderObjectDecoratorTest(String name) {
		super(name);
	}

	/**
	 * Sets the fixture for this Render Object Decorator test case.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void setFixture(RenderObjectDecorator<?> fixture) {
		this.fixture = fixture;
	}

	/**
	 * Returns the fixture for this Render Object Decorator test case.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected RenderObjectDecorator<?> getFixture() {
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
		setFixture(ModelFactory.eINSTANCE.createRenderObjectDecorator());
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
	 * Tests the '{@link model.IRenderElement#getMesh() <em>Get Mesh</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see model.IRenderElement#getMesh()
	 * @generated NOT
	 */
	public void testGetMesh() {
		
		//The first layer decorator to test
		RenderObjectDecorator<String> decorator = ModelFactory.eINSTANCE.createRenderObjectDecorator();
		
		//The base object to be decorated
		TestRenderObject render = new TestRenderObject();	
		
		//Set a shape of type test1 as the render's source
		render.setSource(new ShapeImpl(){
			{
				type = "test1";
			}
		});
		
		//Decorate the object
		decorator.setSource(render);
		
		//The decorator should correctly delegate to the render to get its mesh
		assertTrue("value1".equals(decorator.getMesh()));
		
		//Add a second layer decorator
		RenderObjectDecorator<String> decorator2 = ModelFactory.eINSTANCE.createRenderObjectDecorator();
		decorator2.setSource(decorator);
		
		//The second decorator should also get the render's mesh
		assertTrue("value1".equals(decorator2.getMesh()));
	}

} //RenderObjectDecoratorTest
