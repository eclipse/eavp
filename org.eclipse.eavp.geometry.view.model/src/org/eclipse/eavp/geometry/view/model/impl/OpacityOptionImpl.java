/**
 */
package org.eclipse.eavp.geometry.view.model.impl;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.eavp.geometry.view.model.DoubleTextDisplayOptionData;
import org.eclipse.eavp.geometry.view.model.IDisplayOptionData;
import org.eclipse.eavp.geometry.view.model.ModelFactory;
import org.eclipse.eavp.geometry.view.model.ModelPackage;
import org.eclipse.eavp.geometry.view.model.OpacityOption;
import org.eclipse.emf.ecore.EClass;

/**
 * <!-- begin-user-doc --> An implementation of the model object '<em><b>Opacity
 * Option</b></em>'. <!-- end-user-doc -->
 *
 * @generated
 */
public class OpacityOptionImpl<T> extends DisplayOptionImpl<T>
		implements OpacityOption<T> {

	/**
	 * This object's option group.
	 */
	protected final String GROUP = "Opacity";

	/**
	 * The name of the opacity property.
	 */
	public final static String PROPERTY_NAME_OPACITY = "Opacity";

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	protected OpacityOptionImpl() {
		super();
		optionGroup = GROUP;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.eavp.geometry.view.model.impl.DisplayOptionImpl#
	 * getDefaultProperties()
	 */
	@Override
	public Map<String, Object> getDefaultProperties() {
		HashMap<String, Object> map = new HashMap<String, Object>();

		// Set the opacity option to opaque
		map.put(PROPERTY_NAME_OPACITY, 100d);
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
		DoubleTextDisplayOptionData data = ModelFactory.eINSTANCE
				.createDoubleTextDisplayOptionData();

		// Set the data's reference to its parent option
		data.setDisplayOption(this);

		// Get the opacity property from the parent object and set it to the
		// data's map
		Map<String, Double> dataMap = data.getPropertyToValueMap();
		dataMap.put(PROPERTY_NAME_OPACITY,
				(double) parent.getProperty(PROPERTY_NAME_OPACITY));

		// The minimum value is 0
		Map<String, Double> min = new HashMap<String, Double>();
		min.put(PROPERTY_NAME_OPACITY, 0d);
		data.setPropertyMinValues(min);

		// The maximum value is 100
		Map<String, Double> max = new HashMap<String, Double>();
		max.put(PROPERTY_NAME_OPACITY, 0d);
		data.setPropertyMinValues(max);

		return data;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ModelPackage.Literals.OPACITY_OPTION;
	}

} // OpacityOptionImpl
