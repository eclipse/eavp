/**
 */
package org.eclipse.eavp.geometry.view.model.tests;

import org.eclipse.eavp.geometry.view.model.ComboDisplayOptionData;
import org.eclipse.eavp.geometry.view.model.DisplayOptionType;
import org.eclipse.eavp.geometry.view.model.ModelFactory;

import junit.framework.TestCase;
import junit.textui.TestRunner;

/**
 * <!-- begin-user-doc --> A test case for the model object '<em><b>Combo
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
public class ComboDisplayOptionDataTest extends TestCase {

	/**
	 * The fixture for this Combo Display Option Data test case. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected ComboDisplayOptionData fixture = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public static void main(String[] args) {
		TestRunner.run(ComboDisplayOptionDataTest.class);
	}

	/**
	 * Constructs a new Combo Display Option Data test case with the given name.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public ComboDisplayOptionDataTest(String name) {
		super(name);
	}

	/**
	 * Sets the fixture for this Combo Display Option Data test case. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected void setFixture(ComboDisplayOptionData fixture) {
		this.fixture = fixture;
	}

	/**
	 * Returns the fixture for this Combo Display Option Data test case. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected ComboDisplayOptionData getFixture() {
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
		setFixture(ModelFactory.eINSTANCE.createComboDisplayOptionData());
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
		ComboDisplayOptionData type = ModelFactory.eINSTANCE
				.createComboDisplayOptionData();
		assertTrue(type.getDisplayOptionType() == DisplayOptionType.COMBO);
	}

} // ComboDisplayOptionDataTest
