/**
 */
package org.eclipse.eavp.geometry.view.model.impl;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.eavp.geometry.view.model.ColorOption;
import org.eclipse.eavp.geometry.view.model.IDisplayOptionData;
import org.eclipse.eavp.geometry.view.model.IntegerTextDisplayOptionData;
import org.eclipse.eavp.geometry.view.model.ModelFactory;
import org.eclipse.eavp.geometry.view.model.ModelPackage;
import org.eclipse.emf.ecore.EClass;

/**
 * <!-- begin-user-doc --> An implementation of the model object '<em><b>Color
 * Option</b></em>'. <!-- end-user-doc -->
 *
 * @generated NOT
 */
public class ColorOptionImpl<T> extends DisplayOptionImpl<T>
		implements ColorOption<T> {

	/**
	 * This object's option group.
	 */
	protected final String GROUP = "Color";

	/**
	 * The property name for the red channel of the color.
	 */
	public static final String PROPERTY_NAME_RED = "Red";

	/**
	 * The property name for the green channel of the color.
	 */
	public static final String PROPERTY_NAME_GREEN = "Green";

	/**
	 * The property name for the blue channel of the color.
	 */
	public static final String PROPERTY_NAME_BLUE = "Blue";

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	protected ColorOptionImpl() {
		super();
		optionGroup = GROUP;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ModelPackage.Literals.COLOR_OPTION;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.eavp.geometry.view.model.impl.DisplayOptionImpl#
	 * getDefaultProperties()
	 * 
	 * @generated NOT
	 */
	@Override
	public Map<String, Object> getDefaultProperties() {
		HashMap<String, Object> map = new HashMap<String, Object>();

		map.put(PROPERTY_NAME_RED, 127);
		map.put(PROPERTY_NAME_GREEN, 127);
		map.put(PROPERTY_NAME_BLUE, 127);

		return map;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.eavp.geometry.view.model.impl.DisplayOptionImpl#
	 * getDisplayOptionData()
	 * 
	 * @generated NOT
	 */
	@Override
	public IDisplayOptionData getDisplayOptionData() {
		IntegerTextDisplayOptionData data = ModelFactory.eINSTANCE
				.createIntegerTextDisplayOptionData();

		// Instantiate all the properties with their values in the map
		Map<String, Integer> dataMap = data.getPropertyToValueMap();
		dataMap.put(PROPERTY_NAME_RED,
				(int) parent.getProperty(PROPERTY_NAME_RED));
		dataMap.put(PROPERTY_NAME_GREEN,
				(int) parent.getProperty(PROPERTY_NAME_GREEN));
		dataMap.put(PROPERTY_NAME_BLUE,
				(int) parent.getProperty(PROPERTY_NAME_BLUE));

		return data;
	}

} // ColorOptionImpl
