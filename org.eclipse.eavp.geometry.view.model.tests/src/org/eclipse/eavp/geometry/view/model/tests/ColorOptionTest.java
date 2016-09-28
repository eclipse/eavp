/**
 */
package org.eclipse.eavp.geometry.view.model.tests;

import java.util.Map;

import org.eclipse.eavp.geometry.view.model.ColorOption;
import org.eclipse.eavp.geometry.view.model.DisplayOptionType;
import org.eclipse.eavp.geometry.view.model.IDisplayOptionData;
import org.eclipse.eavp.geometry.view.model.IntegerTextDisplayOptionData;
import org.eclipse.eavp.geometry.view.model.ModelFactory;
import org.eclipse.eavp.geometry.view.model.impl.ColorOptionImpl;
import org.junit.Test;

import junit.textui.TestRunner;

/**
 * <!-- begin-user-doc --> A test case for the model object '<em><b>Color
 * Option</b></em>'. <!-- end-user-doc -->
 * 
 * @generated
 */
public class ColorOptionTest extends DisplayOptionTest {

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public static void main(String[] args) {
		TestRunner.run(ColorOptionTest.class);
	}

	/**
	 * Constructs a new Color Option test case with the given name. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public ColorOptionTest(String name) {
		super(name);
	}

	/**
	 * Returns the fixture for this Color Option test case. <!-- begin-user-doc
	 * --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	protected ColorOption<?> getFixture() {
		return (ColorOption<?>) fixture;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see junit.framework.TestCase#setUp()
	 * @generated
	 */
	@Override
	protected void setUp() throws Exception {
		setFixture(ModelFactory.eINSTANCE.createColorOption());
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
	 * Check that the option provides the right data.
	 */
	@Test
	public void checkData() {
		// Create an opacity decorator for it
		ColorOption decorator = ModelFactory.eINSTANCE.createColorOption();
		decorator.setParent(ModelFactory.eINSTANCE.createRenderObject());

		// Check that the data is of the right type
		IDisplayOptionData genericData = decorator.getDisplayOptionData();
		assertTrue(genericData instanceof IntegerTextDisplayOptionData);
		IntegerTextDisplayOptionData data = (IntegerTextDisplayOptionData) genericData;

		// Check that the data members are right
		assertTrue(
				data.getDisplayOptionType() == DisplayOptionType.INTEGER_TEXT);

		// The map of combo selections to property values
		Map<String, Integer> map = data.getPropertyToValueMap();

		// Check that opacity is set correctly for each option
		assertTrue(map.keySet().contains(ColorOptionImpl.PROPERTY_NAME_RED));
		assertTrue(map.keySet().contains(ColorOptionImpl.PROPERTY_NAME_GREEN));
		assertTrue(map.keySet().contains(ColorOptionImpl.PROPERTY_NAME_BLUE));

		// All values should have minimums of zero
		Map<String, Integer> minMap = data.getPropertyMinValues();
		for (String prop : minMap.keySet()) {
			assertTrue(0 == minMap.get(prop));
		}

		// All values should have maximums of 255
		Map<String, Integer> maxMap = data.getPropertyMaxValues();
		for (String prop : maxMap.keySet()) {
			assertTrue(255 == maxMap.get(prop));
		}
	}

} // ColorOptionTest
