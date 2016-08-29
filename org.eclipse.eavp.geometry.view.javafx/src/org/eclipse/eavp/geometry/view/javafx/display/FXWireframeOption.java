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
package org.eclipse.eavp.geometry.view.javafx.display;

import org.eclipse.eavp.geometry.view.model.RenderObject;
import org.eclipse.eavp.geometry.view.model.impl.WireframeOptionImpl;

import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.shape.DrawMode;
import javafx.scene.shape.MeshView;

/**
 * A FXRenderElement that will allow them to be drawn in wireframe mode.
 * 
 * The decorator will read properties from its source IRenderElement in order to
 * configure itself. The properties used for this decorator are:
 * 
 * "Wireframe"- A boolean value. True makes the shape wireframe, false makes it
 * solid.
 * 
 * @author Robert Smith
 *
 */
public class FXWireframeOption extends WireframeOptionImpl<Group> {

	/**
	 * The default constructor.
	 * 
	 * @param parent
	 *            The parent render object which this option will be applied to.
	 */
	public FXWireframeOption(RenderObject parent) {
		super();

		this.parent = parent;
		parent.registerOption(this);
	}

	/**
	 * Set all the MeshViews in the group to the given draw mode, as well as the
	 * children of any child groups.
	 * 
	 * @param group
	 *            The group whose children will have their draw modes set.
	 * @param mode
	 *            The mode to be set to the group's children.
	 */
	private void setMode(Group group, DrawMode mode) {

		// Handle each of the group's children
		for (Node node : group.getChildren()) {

			// If the node is a mesh view, set its material
			if (node.getClass() == MeshView.class) {
				((MeshView) node).setDrawMode(mode);
			}

			// Otherwise, recursively handle the child group
			else if (node.getClass() == Group.class) {
				setMode((Group) node, mode);
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.eavp.geometry.view.model.impl.DisplayOptionImpl#modify(java.
	 * lang.Object)
	 */
	@Override
	public void modify(Group element) {

		boolean wireframe = (boolean) parent
				.getProperty(PROPERTY_NAME_WIREFRAME);

		// Set the group's children to the correct drawing mode.
		if (!wireframe) {
			setMode(element, DrawMode.FILL);
		} else {
			setMode(element, DrawMode.LINE);
		}
	}
}
