/**
 */
package org.eclipse.eavp.tests.geometry.view.model;

import org.eclipse.eavp.geometry.view.model.DisplayOptionType;
import org.eclipse.eavp.geometry.view.model.IntegerTextDisplayOptionData;
import org.eclipse.eavp.geometry.view.model.ModelFactory;

import junit.framework.TestCase;
import junit.textui.TestRunner;

/**
 * <!-- begin-user-doc --> A test case for the model object '<em><b>Integer Text
 * Display Option Data</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following operations are tested:
 * <ul>
 * <li>{@link org.eclipse.eavp.geometry.view.model.IDisplayOptionData#getDisplayOptionType()
 * <em>Get Display Option Type</em>}</li>
 * </ul>
 * </p>
 * 
 * @generated
 */
public class IntegerTextDisplayOptionDataTest extends TestCase {

	/**
	 * The fixture for this Integer Text Display Option Data test case. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected IntegerTextDisplayOptionData fixture = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public static void main(String[] args) {
		TestRunner.run(IntegerTextDisplayOptionDataTest.class);
	}

	/**
	 * Constructs a new Integer Text Display Option Data test case with the
	 * given name. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public IntegerTextDisplayOptionDataTest(String name) {
		super(name);
	}

	/**
	 * Sets the fixture for this Integer Text Display Option Data test case.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected void setFixture(IntegerTextDisplayOptionData fixture) {
		this.fixture = fixture;
	}

	/**
	 * Returns the fixture for this Integer Text Display Option Data test case.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected IntegerTextDisplayOptionData getFixture() {
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
		setFixture(ModelFactory.eINSTANCE.createIntegerTextDisplayOptionData());
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
	 * '{@link org.eclipse.eavp.geometry.view.model.IDisplayOptionData#getDisplayOptionType()
	 * <em>Get Display Option Type</em>}' operation. <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @see org.eclipse.eavp.geometry.view.model.IDisplayOptionData#getDisplayOptionType()
	 * @generated NOT
	 */
	public void testGetDisplayOptionType() {
		// Check that the proper type is returned.
		IntegerTextDisplayOptionData type = ModelFactory.eINSTANCE
				.createIntegerTextDisplayOptionData();
		assertTrue(
				type.getDisplayOptionType() == DisplayOptionType.INTEGER_TEXT);
	}

} // IntegerTextDisplayOptionDataTest
