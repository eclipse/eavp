/*******************************************************************************
 * Copyright (c) 2015 UT-Battelle, LLC.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   Tony McCrary, Robert Smith
 *******************************************************************************/
package org.eclipse.eavp.viz.service.javafx.geometry.datatypes;

import org.eclipse.eavp.viz.datastructures.VizObject.IManagedUpdateable;
import org.eclipse.eavp.viz.datastructures.VizObject.SubscriptionType;
import org.eclipse.eavp.viz.modeling.Shape;
import org.eclipse.eavp.viz.modeling.ShapeController;
import org.eclipse.eavp.viz.modeling.Tube;
import org.eclipse.eavp.viz.modeling.base.BasicView;
import org.eclipse.eavp.viz.modeling.base.IController;
import org.eclipse.eavp.viz.modeling.base.IMesh;
import org.eclipse.eavp.viz.modeling.base.ITransparentView;
import org.eclipse.eavp.viz.modeling.base.IWireframeView;
import org.eclipse.eavp.viz.modeling.base.Representation;
import org.eclipse.eavp.viz.modeling.properties.MeshProperty;
import org.eclipse.eavp.viz.service.geometry.shapes.GeometryMeshProperty;
import org.eclipse.eavp.viz.service.geometry.shapes.ShapeType;
import org.eclipse.eavp.viz.service.javafx.canvas.TransformGizmo;
import org.eclipse.eavp.viz.service.javafx.internal.Util;

import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.paint.Material;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Box;
import javafx.scene.shape.Cylinder;
import javafx.scene.shape.DrawMode;
import javafx.scene.shape.MeshView;
import javafx.scene.shape.Shape3D;
import javafx.scene.shape.Sphere;
import javafx.scene.shape.TriangleMesh;

/**
 * A class which creates and maintains the JavaFX graphical representation of a
 * Shape.
 * 
 * @author Tony McCrary, Robert Smith
 *
 */
public class FXShapeView extends BasicView
		implements ITransparentView, IWireframeView {

	/**
	 * A group containing the shape which represents the part and a gizmo which
	 * modifies the shape's appearance
	 */
	protected Group node;

	/**
	 * A JavaFX 3D shape for use with shapes that have default implementations
	 */
	protected Shape3D shape;

	/**
	 * The class which creates and manages a JavaFX triangle mesh for tubes.
	 */
	protected FXTube tubeShape;

	/** */
	private TransformGizmo gizmo;

	/**
	 * A user specified material which will be used if the shape is not
	 * selected. If customMaterial is not specified, the class will create a
	 * default material based on the type of the shape being rendered.
	 */
	protected PhongMaterial customMaterial;

	/** */
	protected PhongMaterial defaultMaterial;

	/** */
	private Material selectedMaterial = Util.DEFAULT_HIGHLIGHTED_MATERIAL;

	/** */
	private boolean selected;

	/**
	 * Whether the shape is being displayed transparently. The shape will be
	 * fully transparent (and thus invisible) if true, or visible if false.
	 */
	protected boolean transparent;

	/**
	 * Whether the shape is displaying in wireframe mode. The shape will be a
	 * wireframe if true, or solid if false.
	 */
	protected boolean wireframe;

	/**
	 * The nullary constructor.
	 */
	public FXShapeView() {
		super();

		// Instantiate the class variables
		node = new Group();
		gizmo = new TransformGizmo(0);
		gizmo.setVisible(false);
		node.getChildren().add(gizmo);
		wireframe = false;
		transparent = false;

	}

	/**
	 * The default constructor.
	 * 
	 * @param model
	 *            The model which this view will display
	 */
	public FXShapeView(Shape model) {
		super();

		// Initialize the JavaFX node
		node = new Group();
		node.setId(model.getProperty(MeshProperty.NAME));

		// Set the node's transformation
		node.getTransforms()
				.setAll(Util.convertTransformation(model.getTransformation()));

		// Create a gizmo with axis for the root node
		if ("True".equals(model.getProperty(MeshProperty.ROOT))) {
			gizmo = new TransformGizmo(100);
			gizmo.showHandles(false);
		} else {
			gizmo = new TransformGizmo(0);
		}

		gizmo.setVisible(false);
		node.getChildren().add(gizmo);

		// Create a Shape3D for the model
		createShape(model,
				ShapeType.valueOf(model.getProperty(MeshProperty.TYPE)));

	}

	/**
	 * Sets the representation to the appropriate type of Shape3D, according to
	 * properties taken form the model
	 * 
	 * @param model
	 *            The model on which the shape will be based
	 * 
	 * @param type
	 *            The type of shape to display
	 */
	protected void createShape(IMesh model, ShapeType type) {

		// Fail silently for complex shapes
		if (type == null) {
			return;
		}

		// Whether this is the first time creating a shape
		boolean initial = (shape == null ? true : false);

		// The previous shape
		Shape3D prevShape = null;

		// Based on the type, check if the correct shape has already been made.
		// If not, create a new shape, set its materials, and save it as this
		// view's representation
		switch (type) {
		case Cone:
			return;
		case Cube:
			// Save the old shape
			prevShape = shape;

			// Remove the old shape from the node
			node.getChildren().remove(shape);

			Box box = new Box(50, 50, 50);

			// If a material is not specified, create a new one
			if (customMaterial == null) {
				defaultMaterial = new PhongMaterial(Color.rgb(50, 50, 255));
				defaultMaterial.setSpecularColor(Color.WHITE);
				box.setMaterial(defaultMaterial);
			} else {

				// If a material is specified, set it
				box.setMaterial(customMaterial);
			}
			shape = box;

			break;
		case Cylinder:

			// Save the old shape
			prevShape = shape;

			Cylinder cyl = new Cylinder(50, 50);

			// If a material is not specified, create a new one
			if (customMaterial == null) {
				defaultMaterial = new PhongMaterial(Color.rgb(0, 181, 255));
				defaultMaterial.setSpecularColor(Color.WHITE);
				cyl.setMaterial(defaultMaterial);
			} else {

				// If a material is specified, set it
				cyl.setMaterial(customMaterial);
			}
			shape = cyl;

			break;
		case None:
			return;
		case Sphere:

			// Save the old shape
			prevShape = shape;

			Sphere sphere = new Sphere(50, 50);

			// If a material is not specified, create a new one
			if (customMaterial == null) {
				defaultMaterial = new PhongMaterial(Color.rgb(131, 0, 157));
				defaultMaterial.setSpecularColor(Color.WHITE);
				sphere.setMaterial(defaultMaterial);
			} else {

				// If a material is specified, set it
				sphere.setMaterial(customMaterial);
			}
			shape = sphere;

			break;
		case Tube:

			// Get the previous shape, if any
			if (shape != null) {
				prevShape = shape;
			}

			// Cast the model as a PipeComponent and get the parameters
			Tube pipe = (Tube) model;
			int axialSamples = pipe.getAxialSamples();
			double height = pipe.getLength();
			double outerRadius = pipe.getRadius();
			double innerRadius = pipe.getInnerRadius();

			// If the number of axial samples is less than 3 the tube cannot be
			// created.
			if (axialSamples < 3) {
				axialSamples = 3;
			}

			// Create the mesh
			tubeShape = new FXTube(height, innerRadius, outerRadius,
					axialSamples, 40);

			// Get the actual mesh and set it to a view
			TriangleMesh tubeMesh = tubeShape.getMesh();
			shape = new MeshView(tubeMesh);

			// If a material is not specified, create a new one
			if (customMaterial == null) {

				if (defaultMaterial == null) {
					defaultMaterial = new PhongMaterial(Color.CYAN);
					defaultMaterial.setSpecularColor(Color.WHITE);
				}
				shape.setMaterial(defaultMaterial);
			} else {
				// If a material is specified, set it
				shape.setMaterial(customMaterial);
			}
			break;
		default:
			return;
		}

		// Set the correct wireframe mode
		if (wireframe) {
			shape.setDrawMode(DrawMode.LINE);
		} else {
			shape.setDrawMode(DrawMode.FILL);
		}

		// Set the correct opacity
		if (transparent) {
			shape.setOpacity(0d);
		} else {
			shape.setOpacity(100d);
		}

		// If the function didn't return, a change has occurred. Replace the old
		// shape with the new shape in the group
		if (prevShape != null) {
			node.getChildren().remove(prevShape);
			node.getChildren().add(shape);
		}

		else if (initial) {
			node.getChildren().add(shape);
		}

	}

	/**
	 * Associates the view's controller with the representation's data
	 * structure, so that user interactions with the displayed shape will have a
	 * way be associated back to the controller.
	 * 
	 * @param controller
	 *            This view's controller
	 */
	public void setController(IController controller) {

		// Put the controller in the node's data structure
		node.getProperties().put(ShapeController.class, shape);

	}

	/**
	 * Set the shape's default material. The shape is not guaranteed to actually
	 * display in this material after the function returns, as other materials
	 * may be taking precedence over the default one, such as the
	 * selectedMaterial when the shape is selected.
	 * 
	 * @param material
	 */
	public void setMaterial(PhongMaterial material) {
		customMaterial = material;

		// If the shape is currently displaying the default material, set it to
		// the new one
		if (shape != null && shape.getMaterial() == defaultMaterial) {
			shape.setMaterial(material);
		}

		// Save this material as the default for future uses.
		defaultMaterial = material;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.eavp.viz.modeling.AbstractView#getRepresentation()
	 */
	@Override
	public Representation<Group> getRepresentation() {
		return new Representation<Group>(node);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.eavp.viz.modeling.AbstractView#refresh(org.eclipse. ice
	 * .viz.service.modeling.AbstractMeshComponent)
	 */
	@Override
	public void refresh(IMesh model) {

		// Set the node's transformation
		node.getTransforms()
				.setAll(Util.convertTransformation(model.getTransformation()));

		// Complex shapes have nothing else to refresh, as their children will
		// handle their own views.
		if (model.getProperty(GeometryMeshProperty.OPERATOR) == null) {

			// Create the shape if necessary
			createShape(model,
					ShapeType.valueOf(model.getProperty(MeshProperty.TYPE)));

			// If a shape was created, set its material based on whether or not
			// it is selected
			if (shape != null) {

				// Convert the model's selected property to a boolean
				if ("True".equals(model.getProperty(MeshProperty.SELECTED))) {
					// Set the material and activate the gizmo if selected
					shape.setMaterial(selectedMaterial);
					gizmo.setVisible(true);
				} else {

					// Set the material and deactivate the gizmo if selected
					shape.setMaterial(defaultMaterial);
					gizmo.setVisible(false);
				}
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.eavp.viz.modeling.AbstractView#clone()
	 */
	@Override
	public Object clone() {
		FXShapeView clone = new FXShapeView();
		clone.copy(this);
		return clone;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.eavp.viz.modeling.AbstractView#update(org.eclipse. ice.
	 * viz.service.datastructures.VizObject.IManagedUpdateable,
	 * org.eclipse.eavp.viz.service.datastructures.VizObject.SubscriptionType[])
	 */
	@Override
	public void update(IManagedUpdateable component, SubscriptionType[] type) {

		// Notify own listeners of update
		updateManager.notifyListeners(type);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.eavp.viz.modeling.base.ITransparentView#setTransparentMode(
	 * boolean)
	 */
	@Override
	public void setTransparentMode(boolean transparent) {

		// Save the new state
		this.transparent = transparent;

		// Set the current shape to the correct opacity
		if (shape != null) {
			if (transparent) {
				shape.setOpacity(0d);
			} else {
				shape.setOpacity(100d);
			}
		}

		// Notify listeners of the change
		SubscriptionType[] eventTypes = { SubscriptionType.PROPERTY };
		updateManager.notifyListeners(eventTypes);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.eavp.viz.modeling.WireFramePart#setWireFrameMode(
	 * boolean)
	 */
	@Override
	public void setWireframeMode(boolean on) {

		// Save the new state
		wireframe = on;

		// Set the current shape to the correct draw mode
		if (shape != null) {
			if (on) {
				shape.setDrawMode(DrawMode.LINE);
			} else {
				shape.setDrawMode(DrawMode.FILL);
			}
		}

		// Notify listeners of the change
		SubscriptionType[] eventTypes = { SubscriptionType.WIREFRAME };
		updateManager.notifyListeners(eventTypes);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.eavp.viz.modeling.base.IWireframeView#getWireFrameMode()
	 */
	@Override
	public boolean isWireframe() {
		return wireframe;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.eavp.viz.modeling.base.ITransparentView#getTransparentMode()
	 */
	@Override
	public boolean isTransparent() {
		return transparent;
	}
}
