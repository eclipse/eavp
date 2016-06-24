/*******************************************************************************
 * Copyright (c) 2012, 2014 UT-Battelle, LLC.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   Initial API and implementation and/or initial documentation - Jay Jay Billings,
 *   Jordan H. Deyton, Dasha Gorin, Alexander J. McCaskey, Taylor Patterson,
 *   Claire Saunders, Matthew Wang, Anna Wojtowicz
 *******************************************************************************/
package org.eclipse.eavp.viz.service.geometry.widgets;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.eavp.viz.service.geometry.widgets.ShapeTreeContentProvider.BlankShape;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.ITreeSelection;
import org.eclipse.jface.viewers.TreePath;
import org.eclipse.ui.internal.util.BundleUtility;
import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;

import geometry.Geometry;
import geometry.GeometryFactory;
import geometry.INode;
import model.IRenderElement;

/**
 * <p>
 * Action for adding a specific shape to the ShapeTreeView
 * </p>
 * 
 * @author Andrew P. Belt
 */
public class ActionAddShape extends Action {
	/**
	 * <p>
	 * The current ShapeTreeViewer associated with the AddShape action
	 * </p>
	 * 
	 */
	private ShapeTreeView view;
	/**
	 * <p>
	 * The ShapeType used to create new PrimitiveShapes when the AddShape action
	 * is triggered
	 * </p>
	 * <p>
	 * If the value is null, the Operator is used to create ComplexShapes.
	 * </p>
	 * 
	 */
	private String type;

	/**
	 * <p>
	 * The current used default shape number appended to the name of every newly
	 * created shape
	 * </p>
	 * <p>
	 * Starting from zero, this number increments every time a new shape is
	 * added to the tree.
	 * </p>
	 * 
	 */
	private int currentShapeId;

	/**
	 * The image to display as the action's icon
	 */
	private ImageDescriptor imageDescriptor;

	/**
	 * A list of all the primitive shape types that this kind of action can add.
	 */
	private ArrayList<String> shapeTypes;

	/**
	 * A list of all the complex operator types that this kind of action can
	 * add.
	 */
	private ArrayList<String> operatorTypes;

	/**
	 * <p>
	 * Constructor for creating new PrimitiveShapes with a given ShapeType
	 * </p>
	 * 
	 * @param view
	 *            <p>
	 *            The current ShapeTreeViewer
	 *            </p>
	 * @param shapeType
	 *            <p>
	 *            The type of PrimitiveShape to create with the action is
	 *            triggered
	 *            </p>
	 */
	public ActionAddShape(ShapeTreeView view, String shapeType) {

		// Initialize the data members
		this.view = view;
		type = shapeType;
		currentShapeId = 0;
		shapeTypes = new ArrayList<String>();
		operatorTypes = new ArrayList<String>();

		// Populate the type lists
		shapeTypes.add("sphere");
		shapeTypes.add("cube");
		shapeTypes.add("cylinder");
		shapeTypes.add("tube");
		operatorTypes.add("union");
		operatorTypes.add("intersection");
		operatorTypes.add("complement");

		// Set the Action's name to "Add {ShapeType}"
		setText("Add " + shapeType);

		// Create a map which stores the filenames of the icons, relative
		// to the icons/ directory
		Map<String, String> shapeIcons = new HashMap<String, String>();

		// Add the shape types to the map
		for (String type : shapeTypes) {
			shapeIcons.put(type, type + ".gif");
		}

		// Add the operator types to the map
		for (String type : operatorTypes) {
			shapeIcons.put(type, type + ".gif");
		}

		// Create the image descriptor from the file path
		Bundle bundle = FrameworkUtil.getBundle(getClass());
		URL imagePath = BundleUtility.find(bundle,
				"icons/" + shapeIcons.get(shapeType));
		imageDescriptor = ImageDescriptor.createFromURL(imagePath);

		return;
	}

	/**
	 * <p>
	 * Runs this action
	 * </p>
	 * <p>
	 * Each action implementation must define the steps needed to carry out this
	 * action.
	 * </p>
	 * 
	 */
	@Override
	public void run() {

		// Get the selection

		ITreeSelection selection = (ITreeSelection) view.treeViewer
				.getSelection();
		TreePath[] paths = selection.getPaths();

		// Fail silently if multiple items are selected

		if (paths.length > 1) {
			return;
		}
		// Get the GeometryComponent from the ShapeTreeView's TreeViewer

		Geometry geometry = (Geometry) view.treeViewer.getInput();

		if (geometry == null) {
			return;
		}
		// Get the parent shape, regardless of whether an IShape or BlankShape
		// is selected

		INode parentComplexShape = null;

		if (paths.length == 1) {

			// Get the selected object from the single selection

			Object selectedObject = paths[0].getLastSegment();

			if (selectedObject instanceof IRenderElement) {

				// Get the selected shape's parent

				IRenderElement selectedShape = (IRenderElement) selectedObject;
				parentComplexShape = selectedShape.getBase().getParent();
			} else if (selectedObject instanceof BlankShape) {

				// Get the selected blank shape's parent

				BlankShape selectedBlank = (BlankShape) selectedObject;
				parentComplexShape = selectedBlank.getParent().getBase();
			}

		}

		// Add a child shape to either the GeometryComponent or the parent
		// ComplexShape

		INode childShape = createShape();

		if (parentComplexShape == null) {

			// Add a new shape to the root GeometryComponent

			synchronized (geometry) {
				geometry.addNode(childShape);
			}

			view.treeViewer.refresh();
		}

		else {

			// Create a new shape and add it to the parentComplexShape

			synchronized (geometry) {
				parentComplexShape.addNode(childShape);
			}

			view.treeViewer.refresh(parentComplexShape);
		}

		// Expand the child in the tree if a ComplexShape was added

		if (childShape != null && operatorTypes.contains(type)) {
			view.treeViewer.expandToLevel(childShape, 1);
		}
	}

	/**
	 * Returns the appropriate image descriptor for the action's icon
	 * 
	 * @return The ImageDescriptor generated from the appropriate ShapeType or
	 *         OperatorType
	 * @see org.eclipse.jface.action.Action#getImageDescriptor()
	 */
	@Override
	public ImageDescriptor getImageDescriptor() {
		return imageDescriptor;
	}

	/**
	 * <p>
	 * Creates a shape corresponding to this Action's ShapeType or OperatorType
	 * </p>
	 * 
	 * @return
	 *         <p>
	 *         The newly created shape
	 *         </p>
	 */
	public INode createShape() {

		INode shape = null;

		// Determine which type of shape should be created

		// Instantiate a PrimitiveShape and set its name and ID
		INode shapeComponent;

		// Create a node of the approrpiate type
		if ("cube".equals(type)) {
			shapeComponent = GeometryFactory.eINSTANCE.createCube();
		} else if ("cylinder".equals(type)) {
			shapeComponent = GeometryFactory.eINSTANCE.createCylinder();
		} else if ("sphere".equals(type)) {
			shapeComponent = GeometryFactory.eINSTANCE.createSphere();
		} else if ("tube".equals(type)) {
			shapeComponent = GeometryFactory.eINSTANCE.createTube();
		} else if ("union".equals(type)) {
			shapeComponent = GeometryFactory.eINSTANCE.createUnion();
		} else if ("complement".equals(type)) {
			shapeComponent = GeometryFactory.eINSTANCE.createComplement();
		} else if ("intersection".equals(type)) {
			shapeComponent = GeometryFactory.eINSTANCE.createIntersection();
		}

		currentShapeId++;
		shape.setName(type);
		shape.setId(currentShapeId);

		// Return the shape
		// If none of the conditions above passed, this will return null.

		return shape;

	}
}