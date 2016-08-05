/**
 */
package model.tests;

import static org.junit.Assert.assertTrue;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.january.geometry.impl.ShapeImpl;
import org.junit.Test;

import model.ModelFactory;
import model.RenderObjectDecorator;
import model.impl.RenderObjectDecoratorImpl;

/**
 * <!-- begin-user-doc --> A test case for the model object ' <em><b>Render
 * Object Decorator</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following operations are tested:
 * <ul>
 * <li>{@link model.IRenderElement#getMesh() <em>Get Mesh</em>}</li>
 * </ul>
 * </p>
 * 
 * @generated
 */
public class RenderObjectDecoratorTest {

	/**
	 * The fixture for this Render Object Decorator test case. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected RenderObjectDecorator<?> fixture = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public static void main(String[] args) {
	}

	/**
	 * Constructs a new Render Object Decorator test case with the given name.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public RenderObjectDecoratorTest() {
	}

	/**
	 * Sets the fixture for this Render Object Decorator test case. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected void setFixture(RenderObjectDecorator<?> fixture) {
		this.fixture = fixture;
	}

	/**
	 * Returns the fixture for this Render Object Decorator test case. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected RenderObjectDecorator<?> getFixture() {
		return fixture;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see junit.framework.TestCase#setUp()
	 * @generated
	 */
	protected void setUp() throws Exception {
		setFixture(ModelFactory.eINSTANCE.createRenderObjectDecorator());
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see junit.framework.TestCase#tearDown()
	 * @generated
	 */
	protected void tearDown() throws Exception {
		setFixture(null);
	}

	/**
	 * Tests the '{@link model.IRenderElement#getMesh() <em>Get Mesh</em>}'
	 * operation. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see model.IRenderElement#getMesh()
	 * @generated NOT
	 */
	@Test
	public void testGetMesh() {

		// The first layer decorator to test
		RenderObjectDecorator<String> decorator = ModelFactory.eINSTANCE
				.createRenderObjectDecorator();

		// The base object to be decorated
		TestRenderObject render = new TestRenderObject();

		// Set a shape of type test1 as the render's source
		render.setSource(new TestShape());

		// Decorate the object
		decorator.setSource(render);

		// The decorator should correctly delegate to the render to get its mesh
		String mesh = decorator.getMesh();
		assertTrue("value1".equals(decorator.getMesh()));

		// Add a second layer decorator
		RenderObjectDecorator<String> decorator2 = ModelFactory.eINSTANCE
				.createRenderObjectDecorator();
		decorator2.setSource(decorator);

		// The second decorator should also get the render's mesh
		assertTrue("value1".equals(decorator2.getMesh()));
	}

	/**
	 * Test that the decorator properly receives and handles notifications
	 * 
	 * @generated NOT
	 */
	@Test
	public void checkNotifications() {

		// The first layer decorator to test
		testDecorator decorator = new testDecorator();

		// The base object to be decorated
		TestRenderObject render = new TestRenderObject();

		// Set a shape of type test1 as the render's source
		render.setSource(new ShapeImpl() {
			{
				type = "test1";
			}
		});

		// Decorate the object
		decorator.setSource(render);

		// Change a property and check that the decorator updated
		render.setProperty("test", true);
		assertTrue(decorator.wasUpdated());

		// Add a second decorator above the first
		testDecorator decorator2 = new testDecorator();
		decorator2.setSource(decorator);

		// Change a property and check that the whole stack was updated.
		render.setProperty("test2", true);
		assertTrue(decorator2.wasUpdated());
	}

	/**
	 * A simple shape extension with the type "test1".
	 * 
	 * @author Robert Smith
	 *
	 */
	private class TestShape extends ShapeImpl {

		/**
		 * The default constructor.
		 */
		public TestShape() {
			super();
			type = "test1";
		}
	}

	/**
	 * A simple RenderObjectDecorator that tracks whether or not it has received
	 * an update for testing purposes.
	 * 
	 * @author Robert Smith
	 *
	 */
	private class testDecorator extends RenderObjectDecoratorImpl<String> {

		// Whether or not the decorated has received an updated since the last
		// time it was tested.
		private boolean updated = false;

		/*
		 * (non-Javadoc)
		 * 
		 * @see
		 * model.impl.RenderObjectDecoratorImpl#handleUpdate(org.eclipse.emf.
		 * common.notify.Notification)
		 */
		@Override
		protected void handleUpdate(Notification notification) {
			updated = true;
			super.handleUpdate(notification);
		}

		/**
		 * Check whether the decorator has received an update.
		 * 
		 * @return True if handleUpdate() was called since the last time
		 *         wasUpdated() was called. False otherwise.
		 */
		public boolean wasUpdated() {
			boolean temp = updated;
			updated = false;
			return temp;
		}

	}

} // RenderObjectDecoratorTest
