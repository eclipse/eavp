/*******************************************************************************
 * Copyright (c) 2016 UT-Battelle, LLC.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   Robert Smith
 *******************************************************************************/
package org.eclipse.eavp.viz.service.geometry.persistence;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlAnyElement;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlElementRefs;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.adapters.XmlAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import org.eclipse.eavp.viz.modeling.ShapeController;
import org.eclipse.eavp.viz.modeling.ShapeMesh;
import org.eclipse.eavp.viz.modeling.factory.IControllerProvider;
import org.eclipse.eavp.viz.modeling.factory.IControllerProviderFactory;
import org.eclipse.eavp.viz.modeling.properties.MeshCategory;
import org.eclipse.eavp.viz.modeling.properties.MeshProperty;
import org.eclipse.eavp.viz.service.geometry.shapes.GeometryMeshProperty;
import org.eclipse.eavp.viz.service.geometry.shapes.OperatorType;
import org.eclipse.eavp.viz.service.geometry.shapes.ShapeType;

/**
 * A compact description of a shape in a CSG tree for use with JAXB for
 * persistence. A PersistableShape should map one-to-one with a ShapeMesh, and
 * it should be possible to reconstruct a CSG tree of ShapeControllers and their
 * held ShapeMeshes from its representation using PersistableShapes.
 * 
 * Conversion to and from a PersistableShape will remove all of its properties
 * other than ID and NAME. Its size will be factored into its three scale values
 * and reset to the default size of 1.
 * 
 * @author Robert Smith
 *
 */
@XmlRootElement(name = "Geometry")
public class PersistableShape {

	/**
	 * The index for the rotation values in the transformation array
	 */
	private final int ROTATION = 0;

	/**
	 * The index for the scale values in the transformation array
	 */
	private final int SCALE = 1;

	/**
	 * The index for the translation values in the transformation array.
	 */
	private final int TRANSLATION = 2;

	/**
	 * The OperatorType used by ShapeMeshes compressed by this object. It is
	 * intended that each subclass will be responsible for setthing this
	 * correctly.
	 */
	protected OperatorType operatorType;

	/**
	 * The ShapeType used by ShapeMeshes compressed by this object. It is
	 * intended that each subclass will be responsible for setting this
	 * correctly.
	 */
	protected ShapeType shapeType;

	/**
	 * The list of this node's child shapes.
	 */
	private ArrayList<PersistableShape> children;

	/**
	 * This shape's non-unique ID
	 */
	private String ID;

	/**
	 * The shape's name. A blank value here means that the subclass should use
	 * its default name
	 */
	protected String name;

	/**
	 * Array containing the rotation, scale, and translation information.
	 */
	private double[][] transformation;

	/**
	 * The nullary constructor. This is a simple constructor only intended for
	 * use by JAXB.
	 */
	public PersistableShape() {

		// Set all values to null.
		operatorType = null;
		shapeType = null;
		ID = null;
		name = null;
		transformation = null;
		children = null;
	}

	/**
	 * The default constructor.
	 * 
	 * @param source
	 */
	public PersistableShape(ShapeMesh source) {

		// The default values for the types are both null
		operatorType = null;
		shapeType = null;

		// Get the shape's attributes
		ID = source.getProperty(MeshProperty.ID);
		name = source.getProperty(MeshProperty.NAME);
		transformation = new double[3][3];
		transformation[ROTATION] = source.getRotation();
		transformation[SCALE] = source.getScale();
		transformation[TRANSLATION] = source.getTranslation();

		// If the transformation is the default, don't bother storing it
		if (transformation[ROTATION][0] == 0.0
				&& transformation[ROTATION][1] == 0.0
				&& transformation[ROTATION][2] == 0.0
				&& transformation[SCALE][0] == 1.0
				&& transformation[SCALE][1] == 1.0
				&& transformation[SCALE][2] == 1.0
				&& transformation[TRANSLATION][0] == 0.0
				&& transformation[TRANSLATION][1] == 0.0
				&& transformation[TRANSLATION][2] == 0.0) {
			transformation = null;
		}

		// Convert all children
		children = new ArrayList<PersistableShape>();
		for (ShapeController child : source.getEntitiesFromCategory(
				MeshCategory.CHILDREN, ShapeController.class)) {
			children.add(
					PersistableShape.compress((ShapeMesh) child.getModel()));
		}
	}

	/**
	 * A static method to create a PersistableShape subclass of a type
	 * appropriate to the object being compressed.
	 * 
	 * @param source
	 *            The shape which is to be compressed into a PersistableShape
	 * @return A PersistableShape of a subclass appropriate to the source
	 *         ShapeMesh, which represents that ShapeMesh's information in a
	 *         more compact format.
	 */
	public static PersistableShape compress(ShapeMesh source) {

		// Get the shape's type, interpreting an unset value as "None".
		String sourceShapeType = source.getProperty(MeshProperty.TYPE);
		ShapeType sType;
		if (sourceShapeType == null) {
			sType = ShapeType.None;
		} else {
			sType = ShapeType.valueOf(source.getProperty(MeshProperty.TYPE));
		}

		// Create a PersistableCube for cubes
		if (sType.equals(ShapeType.Cube)) {
			return new PersistableCube(source);
		}

		// Create a PersistableCylinder for cylinders
		else if (sType.equals(ShapeType.Cylinder)) {
			return new PersistableCylinder(source);
		}

		// Create a PersistableSphere for spheres
		else if (sType.equals(ShapeType.Sphere)) {
			return new PersistableSphere(source);
		}

		// Create a PersistableTube for tubes
		else if (sType.equals(ShapeType.Tube)) {
			return new PersistableTube(source);
		}

		// Handle non-primitives
		else {

			// Get the shape's operator type, interpreting an unset value as
			// "None"
			String sourceOperatorType = source
					.getProperty(GeometryMeshProperty.OPERATOR);
			OperatorType oType;
			if (sourceOperatorType != null) {
				oType = OperatorType.valueOf(sourceOperatorType);
			} else {
				oType = OperatorType.None;
			}

			// Create a persistable union for unions
			if (OperatorType.Union.equals(oType)) {
				return new PersistableUnion(source);
			}

			// For generic shapes which are neither some known shape type nor a
			// type of operation, create a generic PersistableShape
			else {
				return new PersistableShape(source);
			}
		}
	}

	/**
	 * Getter method for the children
	 * 
	 * @return The list of the shape's children
	 */
	@XmlAnyElement
	@XmlElementRefs({ @XmlElementRef(type = PersistableCube.class),
			@XmlElementRef(type = PersistableCylinder.class),
			@XmlElementRef(type = PersistableShape.class),
			@XmlElementRef(type = PersistableSphere.class),
			@XmlElementRef(type = PersistableTube.class),
			@XmlElementRef(type = PersistableUnion.class) })
	public ArrayList<PersistableShape> getChildren() {
		return children;
	}

	/**
	 * Getter method for the ID
	 * 
	 * @return The shape's ID, or null for a shape without an ID
	 */
	@XmlAttribute(required = false)
	public String getID() {
		return ID;
	}

	/**
	 * Getter method for the name
	 * 
	 * @return The shape's name, or null for the default name, which is equal to
	 *         its ShapeType or OperatorType, whichever one is not "None."
	 */
	@XmlAttribute(required = false)
	public String getName() {
		return name;
	}

	/**
	 * Getter method for the operator type
	 * 
	 * @return The shape's operator type, or null for type None
	 */
	@XmlTransient
	public OperatorType getOperatorType() {
		return operatorType;
	}

	/**
	 * Getter method for the shape type
	 * 
	 * @return The shape's shape type, or null for type None
	 */
	@XmlTransient
	public ShapeType getShapeType() {
		return shapeType;
	}

	/**
	 * Getter method for the transformation
	 * 
	 * @return The shape's operator type, or null if the transformation is the
	 *         default
	 */
	@XmlAttribute(required = false)
	@XmlJavaTypeAdapter(TransformationAdapter.class)
	public double[][] getTransformation() {
		return transformation;
	}

	/**
	 * Setter method for the children.
	 * 
	 * @param children
	 *            The list of the shape's new children
	 */
	public void setChildren(ArrayList<PersistableShape> children) {
		this.children = children;
	}

	/**
	 * Setter method for the ID.
	 * 
	 * @param ID
	 *            The shape's new ID, or null to remove its ID.
	 */
	public void setID(String ID) {
		this.ID = ID;
	}

	/**
	 * Setter method for the name.
	 * 
	 * @param name
	 *            The shape's new name, or null to signify it should use the
	 *            default name of its shapeType or operatorType, whichever is
	 *            not equal to "None."
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Setter method for the operatorType.
	 * 
	 * @param operatorType
	 *            The shape's new operatorType. Null will be interpreted as
	 *            OperatorType.None.
	 */
	public void setOperatorType(OperatorType operatorType) {
		this.operatorType = operatorType;
	}

	/**
	 * Setter method for the transformation.
	 * 
	 * @param transformation
	 *            The shape's new transformation. Null will be interpreted as
	 *            the default transformation of all 0s for rotation and
	 *            translation and all 1s for scale.
	 */
	public void setTransformation(double[][] transformation) {
		this.transformation = transformation;
	}

	/**
	 * Recreate the ShapeMesh which was compressed in this object's construction
	 * and assign it a controller from the given factory.
	 * 
	 * @param factory
	 *            The factory that will give the IControllerProvider needed to
	 *            give the restored mesh a ShapeController
	 * @return A ShapeController of the kind appropriate to the factory, which
	 *         contains a ShapeMesh identical to the one create by persisting
	 */
	public ShapeController unpack(IControllerProviderFactory factory) {

		// Create a mesh
		ShapeMesh mesh = new ShapeMesh();

		// Assign it a controller and view
		IControllerProvider<ShapeController> provider = factory
				.createProvider(mesh);
		ShapeController shape = provider.createController(mesh);

		// Set all the shape's properties
		if (ID != null) {
			shape.setProperty(MeshProperty.ID, ID);
		}
		if (transformation != null) {
			shape.setRotation(transformation[ROTATION][0],
					transformation[ROTATION][1], transformation[ROTATION][2]);
			shape.setScale(transformation[SCALE][0], transformation[SCALE][1],
					transformation[SCALE][2]);
			shape.setTranslation(transformation[TRANSLATION][0],
					transformation[TRANSLATION][1],
					transformation[TRANSLATION][2]);
		}
		if (shapeType != null && !ShapeType.None.equals(shapeType)) {
			shape.setProperty(MeshProperty.TYPE, shapeType.toString());
		}
		if (operatorType != null && !OperatorType.None.equals(operatorType)) {
			shape.setProperty(GeometryMeshProperty.OPERATOR,
					operatorType.toString());
		}

		// Set the name, using a default value if one is not specified
		if (name != null) {
			shape.setProperty(MeshProperty.NAME, name);
		} else if (shapeType != null) {
			shape.setProperty(MeshProperty.NAME, shapeType.toString());
		} else if (operatorType != null) {
			shape.setProperty(MeshProperty.NAME, operatorType.toString());
		}

		// Also compress all of its children
		if (children != null) {
			for (PersistableShape child : children) {
				shape.addEntityToCategory(child.unpack(factory),
						MeshCategory.CHILDREN);
			}
		}

		return shape;
	}

	/**
	 * An adapter for the transformation data member of the PersistableShape
	 * class. It will convert the 2D array of doubles sized 3 x 3 to and from a
	 * String of comma separated values so that it can be marshaled as an
	 * attribute instead of an element.
	 * 
	 * @author Robert Smith
	 *
	 */
	private static class TransformationAdapter
			extends XmlAdapter<String, double[][]> {

		/*
		 * (non-Javadoc)
		 * 
		 * @see javax.xml.bind.annotation.adapters.XmlAdapter#marshal(java.lang.
		 * Object)
		 */
		@Override
		public String marshal(double[][] arg0) throws Exception {

			if (arg0 != null) {
				// Add each of the values to the string, in order, separated by
				// commas
				String output = "";
				output += arg0[0][0] + "," + arg0[0][1] + "," + arg0[0][2] + ","
						+ arg0[1][0] + "," + arg0[1][1] + "," + arg0[1][2] + ","
						+ arg0[2][0] + "," + arg0[2][1] + "," + arg0[2][2];
				return output;
			}

			// If the input is null, just return null
			return null;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see
		 * javax.xml.bind.annotation.adapters.XmlAdapter#unmarshal(java.lang.
		 * Object)
		 */
		@Override
		public double[][] unmarshal(String arg0) throws Exception {

			if (arg0 != null) {
				// Obtain the values for the transformation variables by
				// splitting
				// along commas
				String[] values = arg0.split(",");

				// Create a new output array to populate
				double[][] output = new double[3][3];

				// For each cell in the array, get the corresponding value and
				// convert it into a double
				for (int i = 0; i < 3; i++) {
					for (int j = 0; j < 3; j++) {
						output[i][j] = Double.parseDouble(values[i * 3 + j]);
					}
				}

				return output;
			}

			// If the input was null, just return null
			return null;
		}

	}
}
