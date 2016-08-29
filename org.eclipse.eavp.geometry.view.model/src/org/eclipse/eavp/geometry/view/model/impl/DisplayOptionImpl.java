/**
 */
package org.eclipse.eavp.geometry.view.model.impl;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;

import org.eclipse.eavp.geometry.view.model.DisplayOption;
import org.eclipse.eavp.geometry.view.model.DisplayOptionType;
import org.eclipse.eavp.geometry.view.model.IDisplayOptionData;
import org.eclipse.eavp.geometry.view.model.ModelPackage;
import org.eclipse.eavp.geometry.view.model.RenderObject;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

/**
 * <!-- begin-user-doc --> An implementation of the model object '<em><b>Display
 * Option</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.eavp.geometry.view.model.impl.DisplayOptionImpl#isActive <em>Active</em>}</li>
 *   <li>{@link org.eclipse.eavp.geometry.view.model.impl.DisplayOptionImpl#getOptionGroup <em>Option Group</em>}</li>
 *   <li>{@link org.eclipse.eavp.geometry.view.model.impl.DisplayOptionImpl#getParent <em>Parent</em>}</li>
 *   <li>{@link org.eclipse.eavp.geometry.view.model.impl.DisplayOptionImpl#getType <em>Type</em>}</li>
 * </ul>
 *
 * @generated
 */
public class DisplayOptionImpl<T> extends MinimalEObjectImpl.Container
		implements DisplayOption<T> {
	/**
	 * The default value of the '{@link #isActive() <em>Active</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #isActive()
	 * @generated
	 * @ordered
	 */
	protected static final boolean ACTIVE_EDEFAULT = true;

	/**
	 * The cached value of the '{@link #isActive() <em>Active</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #isActive()
	 * @generated
	 * @ordered
	 */
	protected boolean active = ACTIVE_EDEFAULT;

	/**
	 * The default value of the '{@link #getOptionGroup() <em>Option Group</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #getOptionGroup()
	 * @generated
	 * @ordered
	 */
	protected static final String OPTION_GROUP_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getOptionGroup() <em>Option Group</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #getOptionGroup()
	 * @generated
	 * @ordered
	 */
	protected String optionGroup = OPTION_GROUP_EDEFAULT;

	/**
	 * The cached value of the '{@link #getParent() <em>Parent</em>}' reference.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #getParent()
	 * @generated
	 * @ordered
	 */
	protected RenderObject<?> parent;

	/**
	 * The default value of the '{@link #getType() <em>Type</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #getType()
	 * @generated
	 * @ordered
	 */
	protected static final DisplayOptionType TYPE_EDEFAULT = DisplayOptionType.COMBO;

	/**
	 * The cached value of the '{@link #getType() <em>Type</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #getType()
	 * @generated
	 * @ordered
	 */
	protected DisplayOptionType type = TYPE_EDEFAULT;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	protected DisplayOptionImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ModelPackage.Literals.DISPLAY_OPTION;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean isActive() {
		return active;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setActive(boolean newActive) {
		boolean oldActive = active;
		active = newActive;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ModelPackage.DISPLAY_OPTION__ACTIVE, oldActive, active));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getOptionGroup() {
		return optionGroup;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setOptionGroup(String newOptionGroup) {
		String oldOptionGroup = optionGroup;
		optionGroup = newOptionGroup;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ModelPackage.DISPLAY_OPTION__OPTION_GROUP, oldOptionGroup, optionGroup));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public RenderObject<?> getParent() {
		if (parent != null && parent.eIsProxy()) {
			InternalEObject oldParent = (InternalEObject)parent;
			parent = (RenderObject<?>)eResolveProxy(oldParent);
			if (parent != oldParent) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, ModelPackage.DISPLAY_OPTION__PARENT, oldParent, parent));
			}
		}
		return parent;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public RenderObject<?> basicGetParent() {
		return parent;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setParent(RenderObject<?> newParent) {
		RenderObject<?> oldParent = parent;
		parent = newParent;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ModelPackage.DISPLAY_OPTION__PARENT, oldParent, parent));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public DisplayOptionType getType() {
		return type;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setType(DisplayOptionType newType) {
		DisplayOptionType oldType = type;
		type = newType == null ? TYPE_EDEFAULT : newType;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ModelPackage.DISPLAY_OPTION__TYPE, oldType, type));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void modify(T element) {
		// TODO: implement this method
		// Ensure that you remove @generated or mark it @generated NOT
		throw new UnsupportedOperationException();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Map<String, Object> getDefaultProperties() {
		// TODO: implement this method
		// Ensure that you remove @generated or mark it @generated NOT
		throw new UnsupportedOperationException();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public IDisplayOptionData getDisplayOptionData() {
		// TODO: implement this method
		// Ensure that you remove @generated or mark it @generated NOT
		throw new UnsupportedOperationException();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case ModelPackage.DISPLAY_OPTION__ACTIVE:
				return isActive();
			case ModelPackage.DISPLAY_OPTION__OPTION_GROUP:
				return getOptionGroup();
			case ModelPackage.DISPLAY_OPTION__PARENT:
				if (resolve) return getParent();
				return basicGetParent();
			case ModelPackage.DISPLAY_OPTION__TYPE:
				return getType();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case ModelPackage.DISPLAY_OPTION__ACTIVE:
				setActive((Boolean)newValue);
				return;
			case ModelPackage.DISPLAY_OPTION__OPTION_GROUP:
				setOptionGroup((String)newValue);
				return;
			case ModelPackage.DISPLAY_OPTION__PARENT:
				setParent((RenderObject<?>)newValue);
				return;
			case ModelPackage.DISPLAY_OPTION__TYPE:
				setType((DisplayOptionType)newValue);
				return;
		}
		super.eSet(featureID, newValue);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eUnset(int featureID) {
		switch (featureID) {
			case ModelPackage.DISPLAY_OPTION__ACTIVE:
				setActive(ACTIVE_EDEFAULT);
				return;
			case ModelPackage.DISPLAY_OPTION__OPTION_GROUP:
				setOptionGroup(OPTION_GROUP_EDEFAULT);
				return;
			case ModelPackage.DISPLAY_OPTION__PARENT:
				setParent((RenderObject<?>)null);
				return;
			case ModelPackage.DISPLAY_OPTION__TYPE:
				setType(TYPE_EDEFAULT);
				return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean eIsSet(int featureID) {
		switch (featureID) {
			case ModelPackage.DISPLAY_OPTION__ACTIVE:
				return active != ACTIVE_EDEFAULT;
			case ModelPackage.DISPLAY_OPTION__OPTION_GROUP:
				return OPTION_GROUP_EDEFAULT == null ? optionGroup != null : !OPTION_GROUP_EDEFAULT.equals(optionGroup);
			case ModelPackage.DISPLAY_OPTION__PARENT:
				return parent != null;
			case ModelPackage.DISPLAY_OPTION__TYPE:
				return type != TYPE_EDEFAULT;
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eInvoke(int operationID, EList<?> arguments)
			throws InvocationTargetException {
		switch (operationID) {
			case ModelPackage.DISPLAY_OPTION___MODIFY__OBJECT:
				modify((T)arguments.get(0));
				return null;
			case ModelPackage.DISPLAY_OPTION___GET_DEFAULT_PROPERTIES:
				return getDefaultProperties();
			case ModelPackage.DISPLAY_OPTION___GET_DISPLAY_OPTION_DATA:
				return getDisplayOptionData();
		}
		return super.eInvoke(operationID, arguments);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String toString() {
		if (eIsProxy()) return super.toString();

		StringBuffer result = new StringBuffer(super.toString());
		result.append(" (active: ");
		result.append(active);
		result.append(", optionGroup: ");
		result.append(optionGroup);
		result.append(", type: ");
		result.append(type);
		result.append(')');
		return result.toString();
	}

} // DisplayOptionImpl
