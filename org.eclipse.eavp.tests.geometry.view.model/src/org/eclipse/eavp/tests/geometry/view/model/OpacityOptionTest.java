/**
 */
package org.eclipse.eavp.tests.geometry.view.model;

import java.util.Map;

import org.eclipse.eavp.geometry.view.model.DisplayOptionType;
import org.eclipse.eavp.geometry.view.model.DoubleTextDisplayOptionData;
import org.eclipse.eavp.geometry.view.model.IDisplayOptionData;
import org.eclipse.eavp.geometry.view.model.ModelFactory;
import org.eclipse.eavp.geometry.view.model.OpacityOption;
import org.eclipse.eavp.geometry.view.model.impl.OpacityOptionImpl;
import org.junit.Test;

import junit.textui.TestRunner;

/**
 * <!-- begin-user-doc --> A test case for the model object '<em><b>Opacity
 * Option</b></em>'. <!-- end-user-doc -->
 * 
 * @generated
 */
public class OpacityOptionTest extends DisplayOptionTest {

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public static void main(String[] args) {
		TestRunner.run(OpacityOptionTest.class);
	}

	/**
	 * Constructs a new Opacity Option test case with the given name. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public OpacityOptionTest(String name) {
		super(name);
	}

	/**
	 * Returns the fixture for this Opacity Option test case. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	protected OpacityOption<?> getFixture() {
		return (OpacityOption<?>) fixture;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see junit.framework.TestCase#setUp()
	 * @generated
	 */
	@Override
	protected void setUp() throws Exception {
		setFixture(ModelFactory.eINSTANCE.createOpacityOption());
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
		OpacityOption decorator = ModelFactory.eINSTANCE.createOpacityOption();
		decorator.setParent(ModelFactory.eINSTANCE.createRenderObject());

		// Check that the data is of the right type
		IDisplayOptionData genericData = decorator.getDisplayOptionData();
		assertTrue(genericData instanceof DoubleTextDisplayOptionData);
		DoubleTextDisplayOptionData data = (DoubleTextDisplayOptionData) genericData;

		// Check that the data members are right
		assertTrue(
				data.getDisplayOptionType() == DisplayOptionType.DOUBLE_TEXT);

		// The map of combo selections to property values
		Map<String, Double> map = data.getPropertyToValueMap();

		// Check that opacity is set correctly for each option
		assertTrue(
				map.keySet().contains(OpacityOptionImpl.PROPERTY_NAME_OPACITY));
	}

} // OpacityOptionTest
