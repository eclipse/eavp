/**
 */
package org.eclipse.eavp.geometry.view.model.impl;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.eavp.geometry.view.model.DoubleTextDisplayOptionData;
import org.eclipse.eavp.geometry.view.model.IDisplayOptionData;
import org.eclipse.eavp.geometry.view.model.ModelFactory;
import org.eclipse.eavp.geometry.view.model.ModelPackage;
import org.eclipse.eavp.geometry.view.model.ScaleOption;
import org.eclipse.emf.ecore.EClass;

/**
 * <!-- begin-user-doc --> An implementation of the model object '<em><b>Scale
 * Option</b></em>'. <!-- end-user-doc -->
 *
 * @generated
 */
public class ScaleOptionImpl<T> extends DisplayOptionImpl<T>
		implements ScaleOption<T> {

	/**
	 * This object's option group.
	 */
	protected final String GROUP = "Scale";

	/**
	 * The name for the scale property
	 */
	protected final String PROPERTY_NAME_SCALE = "Scale";

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	protected ScaleOptionImpl() {
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

		// Set the scale property to 1:1 scale
		map.put(PROPERTY_NAME_SCALE, 1d);

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

		// Get the scale property from the parent object and set it to the
		// data's map
		Map<String, Double> dataMap = data.getPropertyToValueMap();
		dataMap.put(PROPERTY_NAME_SCALE,
				(double) parent.getProperty(PROPERTY_NAME_SCALE));

		return data;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ModelPackage.Literals.SCALE_OPTION;
	}

} // ScaleOptionImpl
