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
import org.eclipse.eavp.geometry.view.model.impl.ColorOptionImpl;

import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.Material;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.MeshView;

/**
 * A DisplayOption for an FXRenderObject that sets the displayed material for
 * the object and all of its children.
 * 
 * The decorator will read properties from its source IRenderElement in order to
 * configure itself. The properties used for this decorator are:
 * 
 * "Red"- The integer red RGB value for the color.
 * 
 * "Green"- The integer green RGB value for the color.
 * 
 * "Blue"- The integer blue RGB value for the color.
 * 
 * "Material"- The JavaFX PhongMaterial that will be displayed for the element
 * if no color is set. A null value will mean that the option will default to
 * the RGB value specified.
 * 
 * @author Robert Smith
 *
 */
public class FXColorOption extends ColorOptionImpl<Group> {

	/**
	 * The default constructor.
	 * 
	 * @param parent
	 *            The parent render object which this option will be applied to.
	 */
	public FXColorOption(RenderObject parent) {
		super();

		this.parent = parent;
		parent.registerOption(this);
	}

	/**
	 * Set all the MeshViews in the group to the given material, as well as the
	 * children of any child groups.
	 * 
	 * @param group
	 *            The group whose children will have their material set.
	 * @param material
	 *            The material to be set to the group's children.
	 */
	private void setMaterial(Group group, Material material) {

		// Handle each of the group's children
		for (Node node : group.getChildren()) {

			// If the node is a mesh view, set its material
			if (node.getClass() == MeshView.class) {
				((MeshView) node).setMaterial(material);
			}

			// Otherwise, recursively handle the child group
			else if (node.getClass() == Group.class) {
				setMaterial((Group) node, material);
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

		// Get the properties
		int red = (int) parent.getProperty(PROPERTY_NAME_RED);
		int green = (int) parent.getProperty(PROPERTY_NAME_GREEN);
		int blue = (int) parent.getProperty(PROPERTY_NAME_BLUE);

		// If all properties are valid, set the group's color
		if (red >= 0 && green >= 0 && blue >= 0) {

			// Create a material of the specified color and set it.
			PhongMaterial material = new PhongMaterial(
					Color.rgb(red, green, blue));
			material.setSpecularColor(Color.WHITE);
			material.setDiffuseMap(new Image(
					getClass().getResource("diffuse.jpg").toExternalForm()));

			// Set the material for the group and pass it along
			setMaterial(element, material);
		}
	}
}
