/**
 */
package org.eclipse.eavp.geometry.view.model.impl;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.eavp.geometry.view.model.ComboDisplayOptionData;
import org.eclipse.eavp.geometry.view.model.IDisplayOptionData;
import org.eclipse.eavp.geometry.view.model.ModelFactory;
import org.eclipse.eavp.geometry.view.model.ModelPackage;
import org.eclipse.eavp.geometry.view.model.WireframeOption;
import org.eclipse.emf.ecore.EClass;

/**
 * <!-- begin-user-doc --> An implementation of the model object
 * '<em><b>Wireframe Option</b></em>'. <!-- end-user-doc -->
 *
 * @generated
 */
public class WireframeOptionImpl<T> extends DisplayOptionImpl<T>
		implements WireframeOption<T> {

	/**
	 * This object's option group.
	 */
	protected final String GROUP = "Wireframe";

	/**
	 * The name of the IRenderElement property for wireframe mode.
	 */
	protected final String PROPERTY_NAME_WIREFRAME = "Wireframe";

	/**
	 * The text string to display that corresponds to a Wireframe property of
	 * false.
	 */
	protected final String PROPERTY_OPTION_OPAQUE = "Opaque";

	/**
	 * The text string to display that corresponds to a Wireframe property of
	 * true.
	 */
	protected final String PROPERTY_OPTION_WIREFRAME = "Wireframe";

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	protected WireframeOptionImpl() {
		super();
		optionGroup = GROUP;
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

		// Set the wireframe property to solid by default
		map.put(PROPERTY_NAME_WIREFRAME, false);

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
		ComboDisplayOptionData data = ModelFactory.eINSTANCE
				.createComboDisplayOptionData();

		// A map setting the wireframe property to false
		HashMap<String, Object> falseMap = new HashMap<String, Object>();
		falseMap.put(PROPERTY_NAME_WIREFRAME, false);

		// A map setting the wireframe property to true
		HashMap<String, Object> trueMap = new HashMap<String, Object>();
		trueMap.put(PROPERTY_NAME_WIREFRAME, true);

		// Set up the data map for each option
		Map<String, Map<String, Object>> dataMap = data
				.getTextToPropertyValuesMap();
		dataMap.put(PROPERTY_OPTION_OPAQUE, falseMap);
		dataMap.put(PROPERTY_OPTION_WIREFRAME, trueMap);

		// Set the default value of wireframe to false
		dataMap.put("default", falseMap);

		return data;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ModelPackage.Literals.WIREFRAME_OPTION;
	}

} // WireframeOptionImpl
