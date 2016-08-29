/**
 */
package org.eclipse.eavp.geometry.view.model;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.util.Enumerator;

/**
 * <!-- begin-user-doc -->
 * A representation of the literals of the enumeration '<em><b>Display Option Type</b></em>',
 * and utility methods for working with them.
 * <!-- end-user-doc -->
 * <!-- begin-model-doc -->
 * The type of control which a DisplayOption will be associated with.
 * <!-- end-model-doc -->
 * @see org.eclipse.eavp.geometry.view.model.ModelPackage#getDisplayOptionType()
 * @model
 * @generated
 */
public enum DisplayOptionType implements Enumerator {
	/**
	 * The '<em><b>COMBO</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #COMBO_VALUE
	 * @generated
	 * @ordered
	 */
	COMBO(0, "COMBO", "COMBO"), /**
	 * The '<em><b>DOUBLE TEXT</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #DOUBLE_TEXT_VALUE
	 * @generated
	 * @ordered
	 */
	DOUBLE_TEXT(1, "DOUBLE_TEXT", "DOUBLE_TEXT"), /**
	 * The '<em><b>INTEGER TEXT</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #INTEGER_TEXT_VALUE
	 * @generated
	 * @ordered
	 */
	INTEGER_TEXT(2, "INTEGER_TEXT", "INTEGER_TEXT")
	;

	/**
	 * The '<em><b>COMBO</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>COMBO</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #COMBO
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int COMBO_VALUE = 0;

/**
	 * The '<em><b>DOUBLE TEXT</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>DOUBLE TEXT</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #DOUBLE_TEXT
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int DOUBLE_TEXT_VALUE = 1;

/**
	 * The '<em><b>INTEGER TEXT</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>INTEGER TEXT</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #INTEGER_TEXT
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int INTEGER_TEXT_VALUE = 2;

	/**
	 * An array of all the '<em><b>Display Option Type</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static final DisplayOptionType[] VALUES_ARRAY =
		new DisplayOptionType[] {
			COMBO,
			DOUBLE_TEXT,
			INTEGER_TEXT,
		};

	/**
	 * A public read-only list of all the '<em><b>Display Option Type</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final List<DisplayOptionType> VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

	/**
	 * Returns the '<em><b>Display Option Type</b></em>' literal with the specified literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param literal the literal.
	 * @return the matching enumerator or <code>null</code>.
	 * @generated
	 */
	public static DisplayOptionType get(String literal) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			DisplayOptionType result = VALUES_ARRAY[i];
			if (result.toString().equals(literal)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>Display Option Type</b></em>' literal with the specified name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param name the name.
	 * @return the matching enumerator or <code>null</code>.
	 * @generated
	 */
	public static DisplayOptionType getByName(String name) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			DisplayOptionType result = VALUES_ARRAY[i];
			if (result.getName().equals(name)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>Display Option Type</b></em>' literal with the specified integer value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the integer value.
	 * @return the matching enumerator or <code>null</code>.
	 * @generated
	 */
	public static DisplayOptionType get(int value) {
		switch (value) {
			case COMBO_VALUE: return COMBO;
			case DOUBLE_TEXT_VALUE: return DOUBLE_TEXT;
			case INTEGER_TEXT_VALUE: return INTEGER_TEXT;
		}
		return null;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private final int value;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private final String name;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private final String literal;

	/**
	 * Only this class can construct instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private DisplayOptionType(int value, String name, String literal) {
		this.value = value;
		this.name = name;
		this.literal = literal;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int getValue() {
	  return value;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getName() {
	  return name;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getLiteral() {
	  return literal;
	}

	/**
	 * Returns the literal value of the enumerator, which is its string representation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String toString() {
		return literal;
	}
	
} //DisplayOptionType
