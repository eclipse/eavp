/**
 */
package geometry;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.util.Enumerator;

/**
 * <!-- begin-user-doc -->
 * A representation of the literals of the enumeration '<em><b>Shape Change Event Type</b></em>',
 * and utility methods for working with them.
 * <!-- end-user-doc -->
 * <!-- begin-model-doc -->
 * 
 * <!-- end-model-doc -->
 * @see geometry.GeometryPackage#getShapeChangeEventType()
 * @model
 * @generated
 */
public enum ShapeChangeEventType implements Enumerator {
	/**
	 * The '<em><b>Name Change</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #NAME_CHANGE_VALUE
	 * @generated
	 * @ordered
	 */
	NAME_CHANGE(0, "NameChange", "NameChange"),

	/**
	 * The '<em><b>Prop Change</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #PROP_CHANGE_VALUE
	 * @generated
	 * @ordered
	 */
	PROP_CHANGE(1, "PropChange", "PropChange"),

	/**
	 * The '<em><b>Shape Change</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #SHAPE_CHANGE_VALUE
	 * @generated
	 * @ordered
	 */
	SHAPE_CHANGE(2, "ShapeChange", "ShapeChange");

	/**
	 * The '<em><b>Name Change</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 
	 * <!-- end-model-doc -->
	 * @see #NAME_CHANGE
	 * @model name="NameChange"
	 * @generated
	 * @ordered
	 */
	public static final int NAME_CHANGE_VALUE = 0;

	/**
	 * The '<em><b>Prop Change</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * An event fired by a change in one or more of the ISubjectShape's properties.
	 * <!-- end-model-doc -->
	 * @see #PROP_CHANGE
	 * @model name="PropChange"
	 * @generated
	 * @ordered
	 */
	public static final int PROP_CHANGE_VALUE = 1;

	/**
	 * The '<em><b>Shape Change</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 
	 * <!-- end-model-doc -->
	 * @see #SHAPE_CHANGE
	 * @model name="ShapeChange"
	 * @generated
	 * @ordered
	 */
	public static final int SHAPE_CHANGE_VALUE = 2;

	/**
	 * An array of all the '<em><b>Shape Change Event Type</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static final ShapeChangeEventType[] VALUES_ARRAY =
		new ShapeChangeEventType[] {
			NAME_CHANGE,
			PROP_CHANGE,
			SHAPE_CHANGE,
		};

	/**
	 * A public read-only list of all the '<em><b>Shape Change Event Type</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final List<ShapeChangeEventType> VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

	/**
	 * Returns the '<em><b>Shape Change Event Type</b></em>' literal with the specified literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param literal the literal.
	 * @return the matching enumerator or <code>null</code>.
	 * @generated
	 */
	public static ShapeChangeEventType get(String literal) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			ShapeChangeEventType result = VALUES_ARRAY[i];
			if (result.toString().equals(literal)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>Shape Change Event Type</b></em>' literal with the specified name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param name the name.
	 * @return the matching enumerator or <code>null</code>.
	 * @generated
	 */
	public static ShapeChangeEventType getByName(String name) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			ShapeChangeEventType result = VALUES_ARRAY[i];
			if (result.getName().equals(name)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>Shape Change Event Type</b></em>' literal with the specified integer value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the integer value.
	 * @return the matching enumerator or <code>null</code>.
	 * @generated
	 */
	public static ShapeChangeEventType get(int value) {
		switch (value) {
			case NAME_CHANGE_VALUE: return NAME_CHANGE;
			case PROP_CHANGE_VALUE: return PROP_CHANGE;
			case SHAPE_CHANGE_VALUE: return SHAPE_CHANGE;
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
	private ShapeChangeEventType(int value, String name, String literal) {
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
	
} //ShapeChangeEventType
