/*******************************************************************************
 * Copyright (c) 2015 UT-Battelle, LLC.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   Robert Smith
 *******************************************************************************/
package org.eclipse.eavp.viz.service.javafx.mesh.datatypes;

import org.eclipse.eavp.viz.datastructures.VizObject.SubscriptionType;
import org.eclipse.eavp.viz.modeling.ShapeController;
import org.eclipse.eavp.viz.modeling.Vertex;
import org.eclipse.eavp.viz.modeling.base.BasicView;
import org.eclipse.eavp.viz.modeling.base.IController;
import org.eclipse.eavp.viz.modeling.base.IMesh;
import org.eclipse.eavp.viz.modeling.base.ITransparentView;
import org.eclipse.eavp.viz.modeling.base.IWireframeView;
import org.eclipse.eavp.viz.modeling.base.Representation;
import org.eclipse.eavp.viz.modeling.base.Transformation;
import org.eclipse.eavp.viz.modeling.properties.MeshProperty;
import org.eclipse.eavp.viz.service.javafx.internal.Util;
import org.eclipse.eavp.viz.service.mesh.datastructures.MeshEditorMeshProperty;

import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.DrawMode;
import javafx.scene.shape.Sphere;

/**
 * A class which provides a JavaFX graphical representation for a Vertex.
 * 
 * @author Robert Smith
 *
 */
public class FXVertexView extends BasicView
		implements ITransparentView, IWireframeView {

	/**
	 * A group containing the shape which represents the part and a gizmo which
	 * modifies the shape's appearance
	 */
	private Group node;

	/** */
	private Sphere mesh;

	/** */
	private PhongMaterial defaultMaterial;

	/** */
	private PhongMaterial selectedMaterial;

	/**
	 * The Material to display when the Vertex is first being made
	 */
	private PhongMaterial constructingMaterial;

	/**
	 * The scale of the application the vertex will be displayed in. The vertex
	 * will be drawn with each coordinate's value multiplied by the scale
	 */
	private int scale;

	/**
	 * Whether or not the view is transparent. If true, the vertex will be
	 * invisible. Otherwise it will be drawn visibly.
	 */
	private boolean transparent;

	/**
	 * Whether or not the view is drawn in wireframe mode. If true, the vertex
	 * will be a wireframe. Otherwise it will be drawn visibly.
	 */
	private boolean wireframe;

	/**
	 * The nullary constructor.
	 */
	public FXVertexView() {
		super();

		// Instantiate the class variables
		node = new Group();
		scale = 1;

		// Create the materials
		defaultMaterial = new PhongMaterial(Color.rgb(80, 30, 140));
		selectedMaterial = new PhongMaterial(Color.rgb(0, 127, 255));
		constructingMaterial = new PhongMaterial(Color.rgb(0, 255, 0));

	}

	/**
	 * The default constructor.
	 * 
	 * @param model
	 *            The model which this view will display
	 */
	public FXVertexView(Vertex model) {
		this();

		// Set the node's name
		node.setId(model.getProperty(MeshProperty.NAME));

		// Get the model's transformation
		Transformation localTransform = model.getTransformation();

		// Flatten the sphere into a circle
		localTransform.setScale(1, 1, 0.75);

		// Set the node's transformation
		node.getTransforms().setAll(Util.convertTransformation(localTransform));

		// Create a Shape3D for the model
		mesh = new Sphere(1);

		// Set the sphere to the correct wireframe and opacity states
		if (transparent) {
			mesh.setOpacity(0d);
		}
		if (wireframe) {
			mesh.setDrawMode(DrawMode.LINE);
		}

		// Set the sphere to be the constructing material by default
		mesh.setMaterial(constructingMaterial);
		node.getChildren().add(mesh);
	}

	/**
	 * Get the scale of the application the view is drawn in.
	 * 
	 * @return The conversion rate between internal units and JavaFX units
	 */
	public int getApplicationScale() {
		return scale;
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
		node.getProperties().put(ShapeController.class, mesh);
	}

	/**
	 * Sets the scale of the application this vertex will be displayed in. The
	 * vertex will now be drawn with all the coordinates in the VertexMesh
	 * multiplied by the scale.
	 * 
	 * @param scale
	 *            The conversion factor between JavaFX units and the logical
	 *            units used by the application.
	 */
	public void setApplicationScale(int scale) {
		this.scale = scale;

		// Notify listeners of the change
		SubscriptionType[] eventTypes = { SubscriptionType.PROPERTY };
		updateManager.notifyListeners(eventTypes);
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

		// Center the node on the vertex's location
		Transformation temp = (Transformation) model.getTransformation()
				.clone();
		temp.setTranslation(((Vertex) model).getX() * scale,
				((Vertex) model).getY() * scale, 0);

		// Set the node's transformation
		node.getTransforms().setAll(Util.convertTransformation(temp));

		// If the vertex is under construction, leave the material unchanged,
		// otherwise set it based on whether or not the vertex is selected
		if (!"True".equals(
				model.getProperty(MeshEditorMeshProperty.UNDER_CONSTRUCTION))) {

			// If the part is selected, set the selected material
			if ("True".equals(model.getProperty(MeshProperty.SELECTED))) {
				mesh.setMaterial(selectedMaterial);
			}

			// Otherwise set the non-selected material
			else {
				mesh.setMaterial(defaultMaterial);
			}
		}

		else {
			mesh.setMaterial(constructingMaterial);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.eavp.viz.modeling.AbstractView#clone()
	 */
	@Override
	public Object clone() {
		FXVertexView clone = new FXVertexView();
		clone.copy(this);
		return clone;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.eavp.viz.modeling.base.IWireframeView#isWireframe()
	 */
	@Override
	public boolean isWireframe() {
		return wireframe;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.eavp.viz.modeling.base.IWireframeView#setWireframeMode(
	 * boolean)
	 */
	@Override
	public void setWireframeMode(boolean on) {
		wireframe = on;

		// Set the mesh to the correct draw mode
		if (mesh != null) {
			if (wireframe) {
				mesh.setDrawMode(DrawMode.LINE);
			} else {
				mesh.setDrawMode(DrawMode.FILL);
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.eavp.viz.modeling.base.ITransparentView#isTransparent()
	 */
	@Override
	public boolean isTransparent() {
		return transparent;
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
		this.transparent = transparent;

		// Set the mesh to the correct opacity
		if (mesh != null) {
			if (transparent) {
				mesh.setOpacity(0d);
			} else {
				mesh.setOpacity(100d);
			}
		}
	}
}
