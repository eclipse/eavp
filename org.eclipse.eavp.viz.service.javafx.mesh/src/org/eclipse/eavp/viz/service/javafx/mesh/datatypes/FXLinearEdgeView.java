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

import org.eclipse.eavp.viz.datastructures.VizObject.IManagedUpdateable;
import org.eclipse.eavp.viz.datastructures.VizObject.SubscriptionType;
import org.eclipse.eavp.viz.modeling.EdgeMesh;
import org.eclipse.eavp.viz.modeling.ShapeController;
import org.eclipse.eavp.viz.modeling.base.BasicView;
import org.eclipse.eavp.viz.modeling.base.IController;
import org.eclipse.eavp.viz.modeling.base.IMesh;
import org.eclipse.eavp.viz.modeling.base.Representation;
import org.eclipse.eavp.viz.modeling.properties.MeshCategory;
import org.eclipse.eavp.viz.modeling.properties.MeshProperty;
import org.eclipse.eavp.viz.service.javafx.internal.Util;
import org.eclipse.eavp.viz.service.mesh.datastructures.MeshEditorMeshProperty;

import javafx.geometry.Point3D;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Cylinder;
import javafx.scene.transform.Rotate;

/**
 * A class which creates and manages the JavaFX graphical representation for a
 * LinearEdge.
 * 
 * @author Robert Smith
 *
 */
public class FXLinearEdgeView extends BasicView {

	/**
	 * A group containing the shape which represents the part and a gizmo which
	 * modifies the shape's appearance
	 */
	private Group node;

	/** */
	private Cylinder mesh;

	/** */
	private PhongMaterial defaultMaterial;

	/** */
	private PhongMaterial selectedMaterial;

	/**
	 * The Material to display when the Vertex is first being made
	 */
	private PhongMaterial constructingMaterial;

	/** */
	private boolean selected;

	/**
	 * The nullary constructor.
	 */
	public FXLinearEdgeView() {
		super();

		// Instantiate the class variables
		node = new Group();

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
	public FXLinearEdgeView(EdgeMesh model) {
		this();

		// Initialize the JavaFX node
		node.setId(model.getProperty(MeshProperty.NAME));

		// Set the node's transformation
		node.getTransforms().setAll(Util.convertTransformation(transformation));

	}

	/**
	 * Creates a cylinder between the start and end points of the edge.
	 * 
	 * @return A JavaFX Cylinder representing the given LinearEdgeComponent
	 */
	private Cylinder createShape(EdgeMesh edgeComponent) {

		// If the edge does not have two vertices, a new shape cannot be created
		if (edgeComponent.getEntitiesFromCategory(MeshCategory.VERTICES)
				.size() != 2) {
			return null;
		}

		// Get the scale the vertices are being drawn at
		int scale = ((FXVertexController) edgeComponent
				.getEntitiesFromCategory(MeshCategory.VERTICES).get(0))
						.getApplicationScale();

		// Get the edge's endpoints
		double[] start = ((org.eclipse.eavp.viz.modeling.EdgeController) edgeComponent
				.getController()).getStartLocation();
		double[] end = ((org.eclipse.eavp.viz.modeling.EdgeController) edgeComponent
				.getController()).getEndLocation();

		for (int i = 0; i < 3; i++) {
			start[i] = start[i] * scale;
			end[i] = end[i] * scale;
		}

		// Create a cylinder situated at the edge's midpoint with the edge's
		// length.
		Cylinder edge = new Cylinder(.6,
				Math.sqrt((Math.pow(start[0] - end[0], 2))
						+ (Math.pow(start[1] - end[1], 2))
						+ (Math.pow(start[2] - end[2], 2))));
		edge.setTranslateX((start[0] + end[0]) / 2);
		edge.setTranslateY((start[1] + end[1]) / 2);
		edge.setTranslateZ((start[2] + end[2]) / 2);

		// Get the angle between the two points
		Point3D start3D = new Point3D(start[0], start[1], start[2]);
		Point3D end3D = new Point3D(end[0], end[1], end[2]);
		Point3D angle = end3D.subtract(start3D);

		// Get the axis of rotation for the cylinder
		Point3D axis = angle.crossProduct(0f, 1f, 0f);

		// Calculate the number of degrees to rotate about the axis.
		double rotationAmount = Math
				.acos(angle.normalize().dotProduct(0, 1, 0));

		// Apply the rotation to the cylinder
		Rotate rotation = new Rotate(-Math.toDegrees(rotationAmount), axis);
		edge.getTransforms().addAll(rotation);

		return edge;
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.eavp.viz.modeling.AbstractView#getRepresentation()
	 */
	@Override
	public Representation<Group> getRepresentation() {
		return new Representation<Group>(node);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.eavp.viz.modeling.AbstractView#refresh(org.eclipse.
	 * ice .viz.service.modeling.AbstractMeshComponent)
	 */
	@Override
	public void refresh(IMesh model) {

		// Set the node's transformation
		node.getTransforms().setAll(Util.convertTransformation(transformation));

		// Clear the current shapes
		node.getChildren().clear();

		// If the edge does not have two vertices, there is nothing to draw
		if (model.getEntitiesFromCategory(MeshCategory.VERTICES).size() == 2) {

			// Create a shape based on the model and set it as the node's child
			mesh = createShape(((EdgeMesh) model));
			node.getChildren().add(mesh);

			// If the vertex is under construction, leave the material
			// unchanged,
			// otherwise set it based on whether or not the vertex is selected
			if (!"True".equals(model
					.getProperty(MeshEditorMeshProperty.UNDER_CONSTRUCTION))) {

				// Convert the model's selected property to a boolean
				if ("True".equals(model.getProperty(MeshProperty.SELECTED))) {
					mesh.setMaterial(selectedMaterial);
				}

				else {
					mesh.setMaterial(defaultMaterial);
				}
			}

			else {
				mesh.setMaterial(constructingMaterial);
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
		FXLinearEdgeView clone = new FXLinearEdgeView();
		clone.copy(this);

		// Force an update from the transformation
		clone.transformation.setSize(clone.transformation.getSize());
		return clone;
	}

	@Override
	public void update(IManagedUpdateable component, SubscriptionType[] type) {

		// If the transformation updated, update the JavaFX transformation
		if (component == transformation) {
			// Set the node's transformation
			node.getTransforms()
					.setAll(Util.convertTransformation(transformation));
		}

		super.update(component, type);
	}
}
