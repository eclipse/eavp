/**
 */
package org.eclipse.eavp.tests.geometry.view.model;

import org.eclipse.eavp.geometry.view.model.DisplayOptionType;
import org.eclipse.eavp.geometry.view.model.DoubleTextDisplayOptionData;
import org.eclipse.eavp.geometry.view.model.ModelFactory;

import junit.framework.TestCase;
import junit.textui.TestRunner;

/**
 * <!-- begin-user-doc --> A test case for the model object '<em><b>Double Text
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
public class DoubleTextDisplayOptionDataTest extends TestCase {

	/**
	 * The fixture for this Double Text Display Option Data test case. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected DoubleTextDisplayOptionData fixture = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public static void main(String[] args) {
		TestRunner.run(DoubleTextDisplayOptionDataTest.class);
	}

	/**
	 * Constructs a new Double Text Display Option Data test case with the given
	 * name. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public DoubleTextDisplayOptionDataTest(String name) {
		super(name);
	}

	/**
	 * Sets the fixture for this Double Text Display Option Data test case. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected void setFixture(DoubleTextDisplayOptionData fixture) {
		this.fixture = fixture;
	}

	/**
	 * Returns the fixture for this Double Text Display Option Data test case.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected DoubleTextDisplayOptionData getFixture() {
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
		setFixture(ModelFactory.eINSTANCE.createDoubleTextDisplayOptionData());
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
		DoubleTextDisplayOptionData type = ModelFactory.eINSTANCE
				.createDoubleTextDisplayOptionData();
		assertTrue(
				type.getDisplayOptionType() == DisplayOptionType.DOUBLE_TEXT);
	}

} // DoubleTextDisplayOptionDataTest
