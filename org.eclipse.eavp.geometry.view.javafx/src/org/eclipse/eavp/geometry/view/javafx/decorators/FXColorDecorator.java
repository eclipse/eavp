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
package org.eclipse.eavp.geometry.view.javafx.decorators;

import org.eclipse.eavp.geometry.view.model.ColorDecorator;
import org.eclipse.eavp.geometry.view.model.impl.ColorDecoratorImpl;
import org.eclipse.emf.common.notify.Notification;

import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.paint.Material;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.MeshView;

/**
 * A Decorator for an FXRenderObject that sets the displayed material for the
 * object and all of its children.
 * 
 * The decorator will read properties from its source IRenderElement in order to
 * configure itself. The properties used for this decorator are:
 * 
 * "red"- The integer red RGB value for the color. A negative value means that
 * the decorator will attempt to use the material instead.
 * 
 * "green"- The integer green RGB value for the color. A negative value means
 * that the decorator will attempt to use the material instead.
 * 
 * "blue"- The integer blue RGB value for the color. A negative value means that
 * the decorator will attempt to use the material instead.
 * 
 * "material"- The JavaFX PhongMaterial that will be displayed for the element
 * if no color is set. A null value will mean that the decorator will not change
 * the element's color.
 * 
 * @author Robert Smith
 *
 */
public class FXColorDecorator extends ColorDecoratorImpl<Group> {

	/**
	 * The material used for this decorator. If this is not set, then the
	 * default material with a color given by the red, green, and blue channels
	 * will be used instead.
	 */
	private PhongMaterial material;

	/**
	 * Gets the material used for this decorator. If this is null, will rather
	 * use the red, green, and blue channels set for this decorator.
	 * 
	 * @return The material for the decorator
	 */
	public PhongMaterial getMaterial() {
		return material;
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
	 * @see model.impl.RenderObjectDecoratorImpl#getMesh()
	 */
	@Override
	public Group getMesh() {

		// Get the mesh
		Group group = source.getMesh();

		PhongMaterial material = getMaterial();

		if (material == null) {
			// Negative color values will serve as a signal to allow the
			// children
			// meshes to keep their current colors.
			if (red >= 0 && green >= 0 && blue >= 0) {

				// Create a material of the specified color and set it.
				material = new PhongMaterial(Color.rgb(red, green, blue));
				material.setSpecularColor(Color.WHITE);
				this.setMaterial(material);
				
				// Set the material for the group and pass it along
				setMaterial(group, material);
			}

		}

		else {

			if (red >= 0 && green >= 0 && blue >= 0) {
				material.setDiffuseColor(
						Color.color(red / 255.0, green / 255.0, blue / 255.0));
				setMaterial(material);

				// Set the material for the group and pass it along
				setMaterial(group, material);
			}

		}


		return group;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * model.impl.RenderObjectDecoratorImpl#handleUpdate(org.eclipse.emf.common.
	 * notify.Notification)
	 */
	@Override
	protected void handleUpdate(Notification notification) {
		// Get the property
		Object property = notification.getOldValue();

		// Check if the property is any of the RGB colors. If so,
		// set the decorator's color correctly
		int colorVal = -1;
		if (notification.getNewValue() instanceof Integer) {
			colorVal = (int) notification.getNewValue();
		} else if (notification.getNewValue() instanceof Double) {
			colorVal = ((Double) notification.getNewValue()).intValue();
		}

		// Set the red, green, or blue values
		if ("red".equals(property)) {
			setRed(colorVal);
		} else if ("green".equals(property)) {
			setGreen(colorVal);
		} else if ("blue".equals(property)) {
			setBlue(colorVal);
		}

		// Set the material, if it is valid
		if ("material".equals(property)
				&& notification.getNewValue() instanceof PhongMaterial) {
			setMaterial((PhongMaterial) notification.getNewValue());
		}

		// Pass the update along to own listeners
		super.handleUpdate(notification);
	}

	/**
	 * Sets the phong material used for this decorator. Setting this to null
	 * will allow the red, green, and blue channels to control the material
	 * color.
	 * 
	 * @param newMaterial
	 *            The material to use for this decorator
	 */
	public void setMaterial(PhongMaterial newMaterial) {
		material = newMaterial;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see model.impl.ColorDecoratorImpl#clone()
	 */
	@Override
	public Object clone() {

		// Create a new color decorator
		FXColorDecorator clone = new FXColorDecorator();

		// Copy this object's data into the clone
		clone.copy(this);
		return clone;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see model.impl.RenderObjectDecoratorImpl#copy(java.lang.Object)
	 */
	@Override
	public void copy(Object source) {

		// If the source is not a ColorDecorator, fail silently
		if (source instanceof ColorDecorator) {

			// Copy the supertype data
			super.copy(source);

			// Cast the other object
			FXColorDecorator castSource = (FXColorDecorator) source;

			// Copy the color fields
			red = castSource.getRed();
			green = castSource.getGreen();
			blue = castSource.getBlue();

			// Copy the material
			material = new PhongMaterial(
					castSource.getMaterial().getDiffuseColor());
		}
	}
}
