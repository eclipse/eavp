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
import org.eclipse.eavp.viz.modeling.Shape;
import org.eclipse.eavp.viz.modeling.Tube;
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
	 * The shape's rotation in the x, y, and z directions. A null value represents a default rotation of 0 on all axes.
	 */
	protected double[] rotation;

	/**
	 * The shape's scale in the x, y, and z directions. A null value represents a default rotation of 1 on all axes.
	 */
	protected double[] scale;
	
	/**
	 * The shape's translation in the x, y, and z directions. A null value represents a default rotation of 0 on all axes.
	 */
	protected double[] translation;

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
		rotation = null;
		scale = null;
		translation = null;
		children = null;
	}

	/**
	 * The default constructor.
	 * 
	 * @param source
	 */
	public PersistableShape(Shape source) {

		// The default values for the types are both null
		operatorType = null;
		shapeType = null;

		// Get the shape's attributes
		ID = source.getProperty(MeshProperty.ID);
		name = source.getProperty(MeshProperty.NAME);
		rotation = source.getRotation();
		scale = source.getScale();
		translation = source.getTranslation();
		
		double size = source.getSize();
		if(source.getSize() != 1.0){
			scale[0] = scale[0] * size;
			scale[1] = scale[1] * size;
			scale[2] = scale[2] * size;
		}

		// If the rotation, scale, or translation is the default, don't bother storing it
		if(rotation[0] == 0.0 && rotation[1] == 0.0 && rotation[2] == 0.0){
			rotation = null;
		}
		if(scale[0] == 1.0 && scale[1] == 1.0 && scale[2] == 1.0){
			scale = null;
		}
		if(translation[0] == 0.0 && translation[1] == 0.0 && translation[2] == 0.0){
			translation = null;
		}

		// Convert all children
		children = new ArrayList<PersistableShape>();
		for (ShapeController child : source.getEntitiesFromCategory(
				MeshCategory.CHILDREN, ShapeController.class)) {
			children.add(
					PersistableShape.compress((Shape) child.getModel()));
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
	public static PersistableShape compress(Shape source) {

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
	 * Getter method for the rotation.
	 * 
	 * @return The shape's rotation in the three directions, or null if the rotation is the default.
	 */
	@XmlAttribute(required = false)
	@XmlJavaTypeAdapter(TransformationAdapter.class)
	public double[] getRotation() {
		return rotation;
	}
	
	/**
	 * Getter method for the scale.
	 * 
	 * @return The shape's scale in the three directions, or null if the scale is the default.
	 */
	@XmlAttribute(required = false)
	@XmlJavaTypeAdapter(TransformationAdapter.class)
	public double[] getScale() {
		return scale;
	}
	
	/**
	 * Getter method for the translation.
	 * 
	 * @return The shape's translation in the three directions, or null if the translation is the default.
	 */
	@XmlAttribute(required = false)
	@XmlJavaTypeAdapter(TransformationAdapter.class)
	public double[] getTranslation() {
		return translation;
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
	 * Setter method for the rotation.
	 * 
	 * @param rotation The shape's new rotation.
	 */
	public void setRotation(double[] rotation){
		this.rotation = rotation;
	}
	
	/**
	 * Setter method for the scale.
	 * 
	 * @param scale The shape's new scale.
	 */
	public void setScale(double[] scale){
		this.scale = scale;
	}
	
	/**
	 * Setter method for the translation.
	 * 
	 * @param translation The shape's new translation.
	 */
	public void setTranslation(double[] translation){
		this.translation = translation;
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
		Shape mesh; 
		
		//If the shape isn't a tube, create a tube mesh
		if(!ShapeType.Tube.equals(shapeType)){
			mesh = new Shape();
		}
		
		//If it is, create the tube and set it up with the Geometry Editor defaults
		else {
			mesh = new Tube();
			((Tube) mesh).setAxialSamples(3);
			((Tube) mesh).setInnerRadius(40);
			((Tube) mesh).setLength(50);
			((Tube) mesh).setRadius(50);
		}

		// Assign it a controller and view
		IControllerProvider<ShapeController> provider = factory
				.createProvider(mesh);
		ShapeController shape = provider.createController(mesh);

		// Set all the shape's properties
		if (ID != null) {
			shape.setProperty(MeshProperty.ID, ID);
		}
		if(rotation != null){
			shape.setRotation(rotation[0], rotation[1], rotation[2]);
		}
		if(scale != null){
			shape.setScale(scale[0], scale[1], scale[2]);
		}
		if(translation != null){
			shape.setTranslation(translation[0], translation[1], translation[2]);
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
	 * An adapter for the transformation data members of the PersistableShape
	 * class. It will convert the length 3 array of doubles to and from a
	 * String of comma separated values so that it can be marshaled as an
	 * attribute instead of an element.
	 * 
	 * @author Robert Smith
	 *
	 */
	private static class TransformationAdapter
			extends XmlAdapter<String, double[]> {

		/*
		 * (non-Javadoc)
		 * 
		 * @see javax.xml.bind.annotation.adapters.XmlAdapter#marshal(java.lang.
		 * Object)
		 */
		@Override
		public String marshal(double[] arg0) throws Exception {

			if (arg0 != null) {
				// Add each of the values to the string, in order, separated by
				// commas
				String output = "";
				output += arg0[0] + "," + arg0[1] + "," + arg0[2];
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
		public double[] unmarshal(String arg0) throws Exception {

			if (arg0 != null) {
				// Obtain the values for the transformation variables by
				// splitting
				// along commas
				String[] values = arg0.split(",");

				// Create a new output array to populate
				double[] output = new double[3];

				// For each cell in the array, get the corresponding value and
				// convert it into a double
				for (int i = 0; i < 3; i++) {
						output[i] = Double.parseDouble(values[i]);
				}

				return output;
			}

			// If the input was null, just return null
			return null;
		}

	}
}
