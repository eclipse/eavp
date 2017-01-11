/**
 */
package org.eclipse.eavp.tests.geometry.view.model;

import java.util.Map;

import org.eclipse.eavp.geometry.view.model.ComboDisplayOptionData;
import org.eclipse.eavp.geometry.view.model.DisplayOptionType;
import org.eclipse.eavp.geometry.view.model.IDisplayOptionData;
import org.eclipse.eavp.geometry.view.model.ModelFactory;
import org.eclipse.eavp.geometry.view.model.WireframeOption;
import org.eclipse.eavp.geometry.view.model.impl.OpacityOptionImpl;
import org.junit.Test;

import junit.textui.TestRunner;

/**
 * <!-- begin-user-doc --> A test case for the model object '<em><b>Wireframe
 * Option</b></em>'. <!-- end-user-doc -->
 * 
 * @generated
 */
public class WireframeOptionTest extends DisplayOptionTest {

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public static void main(String[] args) {
		TestRunner.run(WireframeOptionTest.class);
	}

	/**
	 * Constructs a new Wireframe Option test case with the given name. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public WireframeOptionTest(String name) {
		super(name);
	}

	/**
	 * Returns the fixture for this Wireframe Option test case. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	protected WireframeOption<?> getFixture() {
		return (WireframeOption<?>) fixture;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see junit.framework.TestCase#setUp()
	 * @generated
	 */
	@Override
	protected void setUp() throws Exception {
		setFixture(ModelFactory.eINSTANCE.createWireframeOption());
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
	 * Check that the object provides the right data
	 */
	@Test
	public void checkData() {
		// Create an opacity decorator for it
		WireframeOption decorator = ModelFactory.eINSTANCE
				.createWireframeOption();
		decorator.setParent(ModelFactory.eINSTANCE.createRenderObject());

		// Check that the data is of the right type
		IDisplayOptionData genericData = decorator.getDisplayOptionData();
		assertTrue(genericData instanceof ComboDisplayOptionData);
		ComboDisplayOptionData data = (ComboDisplayOptionData) genericData;

		// Check that the data members are right
		assertTrue(data.getDisplayOptionType() == DisplayOptionType.COMBO);

		// The map of combo selections to property values
		Map<String, Map<String, Object>> map = data
				.getTextToPropertyValuesMap();

		// Check that opacity is set correctly for each option
		assertTrue(
				map.keySet().contains(OpacityOptionImpl.PROPERTY_NAME_OPACITY));

		// Check that opacity is set correctly for each option
		assertTrue(map.get("Opaque")
				.get(OpacityOptionImpl.PROPERTY_NAME_OPACITY).equals(false));
		assertTrue(map.get("Wireframe")
				.get(OpacityOptionImpl.PROPERTY_NAME_OPACITY).equals(true));
		assertTrue(map.get("default")
				.get(OpacityOptionImpl.PROPERTY_NAME_OPACITY).equals(false));
	}

} // WireframeOptionTest
