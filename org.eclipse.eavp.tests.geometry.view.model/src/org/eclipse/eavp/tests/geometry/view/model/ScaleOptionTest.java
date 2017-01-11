/**
 */
package org.eclipse.eavp.tests.geometry.view.model;

import java.util.Map;

import org.eclipse.eavp.geometry.view.model.DisplayOptionType;
import org.eclipse.eavp.geometry.view.model.DoubleTextDisplayOptionData;
import org.eclipse.eavp.geometry.view.model.IDisplayOptionData;
import org.eclipse.eavp.geometry.view.model.ModelFactory;
import org.eclipse.eavp.geometry.view.model.ScaleOption;
import org.eclipse.eavp.geometry.view.model.impl.ScaleOptionImpl;
import org.junit.Test;

import junit.textui.TestRunner;

/**
 * <!-- begin-user-doc --> A test case for the model object '<em><b>Scale
 * Option</b></em>'. <!-- end-user-doc -->
 * 
 * @generated
 */
public class ScaleOptionTest extends DisplayOptionTest {

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public static void main(String[] args) {
		TestRunner.run(ScaleOptionTest.class);
	}

	/**
	 * Constructs a new Scale Option test case with the given name. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public ScaleOptionTest(String name) {
		super(name);
	}

	/**
	 * Returns the fixture for this Scale Option test case. <!-- begin-user-doc
	 * --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	protected ScaleOption<?> getFixture() {
		return (ScaleOption<?>) fixture;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see junit.framework.TestCase#setUp()
	 * @generated
	 */
	@Override
	protected void setUp() throws Exception {
		setFixture(ModelFactory.eINSTANCE.createScaleOption());
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
		// Create an scale decorator for it
		ScaleOption decorator = ModelFactory.eINSTANCE.createScaleOption();
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
		assertTrue(map.keySet().contains(ScaleOptionImpl.PROPERTY_NAME_SCALE));
	}

} // ScaleOptionTest
