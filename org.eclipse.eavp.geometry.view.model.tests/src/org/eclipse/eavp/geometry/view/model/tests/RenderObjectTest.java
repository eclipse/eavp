/**
 */
package org.eclipse.eavp.geometry.view.model.tests;

import org.eclipse.eavp.geometry.view.model.DisplayOption;
import org.eclipse.eavp.geometry.view.model.ModelFactory;
import org.eclipse.eavp.geometry.view.model.RenderObject;
import org.eclipse.january.geometry.impl.ShapeImpl;
import org.junit.Test;

import junit.framework.TestCase;
import junit.textui.TestRunner;

/**
 * <!-- begin-user-doc --> A test case for the model object '<em><b>Render
 * Object</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following operations are tested:
 * <ul>
 * <li>{@link org.eclipse.eavp.geometry.view.model.RenderObject#getMesh()
 * <em>Get Mesh</em>}</li>
 * <li>{@link org.eclipse.eavp.geometry.view.model.RenderObject#registerOption(org.eclipse.eavp.geometry.view.model.DisplayOption)
 * <em>Register Option</em>}</li>
 * <li>{@link org.eclipse.eavp.geometry.view.model.IRenderElement#getBase()
 * <em>Get Base</em>}</li>
 * <li>{@link org.eclipse.eavp.geometry.view.model.IRenderElement#handleChildren(org.eclipse.emf.common.util.EList)
 * <em>Handle Children</em>}</li>
 * <li>{@link org.eclipse.eavp.geometry.view.model.IRenderElement#getProperty(java.lang.String)
 * <em>Get Property</em>}</li>
 * <li>{@link org.eclipse.eavp.geometry.view.model.IRenderElement#setProperty(java.lang.String, java.lang.Object)
 * <em>Set Property</em>}</li>
 * <li>{@link org.eclipse.eavp.geometry.view.model.IRenderElement#copy(java.lang.Object)
 * <em>Copy</em>}</li>
 * <li>{@link org.eclipse.eavp.geometry.view.model.IRenderElement#clone()
 * <em>Clone</em>}</li>
 * </ul>
 * </p>
 * 
 * @generated
 */
public class RenderObjectTest extends TestCase {

	/**
	 * The fixture for this Render Object test case. <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected RenderObject<?> fixture = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public static void main(String[] args) {
		TestRunner.run(RenderObjectTest.class);
	}

	/**
	 * Constructs a new Render Object test case with the given name. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public RenderObjectTest(String name) {
		super(name);
	}

	/**
	 * Sets the fixture for this Render Object test case. <!-- begin-user-doc
	 * --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected void setFixture(RenderObject<?> fixture) {
		this.fixture = fixture;
	}

	/**
	 * Returns the fixture for this Render Object test case. <!-- begin-user-doc
	 * --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected RenderObject<?> getFixture() {
		return fixture;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see junit.framework.TestCase#setUp()
	 * @generated
	 */
	@Override
	protected void setUp() throws Exception {
		setFixture(ModelFactory.eINSTANCE.createRenderObject());
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

	/**
	 * Tests the
	 * '{@link org.eclipse.eavp.geometry.view.model.IRenderElement#getMesh()
	 * <em>Get Mesh</em>}' operation. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @see org.eclipse.eavp.geometry.view.model.IRenderElement#getMesh()
	 * @generated NOT
	 */
	@Test
	public void testGetMesh() {
		// The object to test
		TestRenderObject render = new TestRenderObject();

		// Set a shape of type test1 as the render's source
		render.setSource(new ShapeImpl() {
			{
				type = "test1";
			}
		});

		// The object should get the string "value1" from the test cache.
		assertTrue("value1".equals(render.getMesh()));
	}

	/**
	 * Tests the
	 * '{@link org.eclipse.eavp.geometry.view.model.RenderObject#registerOption(org.eclipse.eavp.geometry.view.model.DisplayOption)
	 * <em>Register Option</em>}' operation. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @see org.eclipse.eavp.geometry.view.model.RenderObject#registerOption(org.eclipse.eavp.geometry.view.model.DisplayOption)
	 * @generated NOT
	 */
	public void testRegisterOption__DisplayOption() {

		// Create an option and object to test
		RenderObject object = ModelFactory.eINSTANCE.createRenderObject();
		DisplayOption option = ModelFactory.eINSTANCE.createDisplayOption();

		// Register the option
		object.registerOption(option);

		// The object should have the option in its list
		assertTrue(object.getDisplayOptions().contains(option));

		// The option should have the object as its parent
		assertTrue(option.getParent() == object);
	}

	/**
	 * Tests the
	 * '{@link org.eclipse.eavp.geometry.view.model.IRenderElement#getBase()
	 * <em>Get Base</em>}' operation. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @see org.eclipse.eavp.geometry.view.model.IRenderElement#getBase()
	 * @generated NOT
	 */
	public void testGetBase() {
	}

	/**
	 * Tests the
	 * '{@link org.eclipse.eavp.geometry.view.model.IRenderElement#handleChildren(org.eclipse.emf.common.util.EList)
	 * <em>Handle Children</em>}' operation. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @see org.eclipse.eavp.geometry.view.model.IRenderElement#handleChildren(org.eclipse.emf.common.util.EList)
	 * @generated NOT
	 */
	public void testHandleChildren__EList() {
	}

	/**
	 * Tests the
	 * '{@link org.eclipse.eavp.geometry.view.model.IRenderElement#getProperty(java.lang.String)
	 * <em>Get Property</em>}' operation. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @see org.eclipse.eavp.geometry.view.model.IRenderElement#getProperty(java.lang.String)
	 * @generated NOT
	 */
	public void testGetProperty__String() {
	}

	/**
	 * Tests the
	 * '{@link org.eclipse.eavp.geometry.view.model.IRenderElement#setProperty(java.lang.String, java.lang.Object)
	 * <em>Set Property</em>}' operation. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @see org.eclipse.eavp.geometry.view.model.IRenderElement#setProperty(java.lang.String,
	 *      java.lang.Object)
	 * @generated NOT
	 */
	public void testSetProperty__String_Object() {
	}

	/**
	 * Tests the
	 * '{@link org.eclipse.eavp.geometry.view.model.IRenderElement#copy(java.lang.Object)
	 * <em>Copy</em>}' operation. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see org.eclipse.eavp.geometry.view.model.IRenderElement#copy(java.lang.Object)
	 * @generated NOT
	 */
	public void testCopy__Object() {
	}

	/**
	 * Tests the
	 * '{@link org.eclipse.eavp.geometry.view.model.IRenderElement#clone()
	 * <em>Clone</em>}' operation. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see org.eclipse.eavp.geometry.view.model.IRenderElement#clone()
	 * @generated NOT
	 */
	public void testClone() {
	}

} // RenderObjectTest
