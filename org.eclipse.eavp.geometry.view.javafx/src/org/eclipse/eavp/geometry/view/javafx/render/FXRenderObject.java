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
package org.eclipse.eavp.geometry.view.javafx.render;

import org.eclipse.eavp.geometry.view.model.DisplayOption;
import org.eclipse.eavp.geometry.view.model.IRenderElement;
import org.eclipse.eavp.geometry.view.model.MeshCache;
import org.eclipse.eavp.geometry.view.model.impl.MeshCacheImpl;
import org.eclipse.eavp.geometry.view.model.impl.RenderObjectImpl;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.impl.AdapterImpl;
import org.eclipse.emf.common.util.EList;
import org.eclipse.january.geometry.GeometryPackage;
import org.eclipse.january.geometry.INode;
import org.eclipse.january.geometry.Vertex;

import javafx.scene.Group;
import javafx.scene.shape.MeshView;
import javafx.scene.shape.TriangleMesh;

/**
 * A JavaFX implementation of a RenderObject. It is responsible for maintaining
 * a Group, which will contain all graphical elements necessary for the source
 * object to be displayed.
 * 
 * @author Robert Smith
 *
 */
public class FXRenderObject extends RenderObjectImpl<Group> {

	/**
	 * The default constructor.
	 * 
	 * @param source
	 *            The object to be rendered.
	 * @param meshCache
	 *            The cache of TriangleMeshes from which this object will draw
	 *            the mesh to render.
	 */
	public FXRenderObject(INode source, MeshCache<TriangleMesh> meshCache) {
		super();

		// Save the data members
		this.source = source;
		this.meshCache = meshCache;

		// Create a new, empty group for the render
		render = new Group();

		// Try to get a mesh based on the source's type.
		TriangleMesh mesh = meshCache.getMesh(source.getType());

		// If there was no mesh for the source's type, instead specify one by a
		// mesh of triangles
		if (mesh == null) {
			mesh = meshCache.getMesh(source.getTriangles());
		}

		// If a mesh was returned, create a view from it and add it to the
		// render group. Otherwise, we will use an empty group to render the
		// object.
		if (mesh != null) {
			render.getChildren().add(new MeshView(mesh));
		}

		// Register as a listener to the source object
		source.eAdapters().add(new AdapterImpl() {
			@Override
			public void notifyChanged(Notification notification) {
				handleUpdate(notification);
			}
		});

		// Get the center of the data object from the source
		Vertex center = source.getCenter();

		// Move the render to the correct location
		if (center != null) {
			render.setTranslateX(center.getX());
			render.setTranslateY(center.getY());
			render.setTranslateZ(center.getZ());
		}
	}

	/**
	 * Handle a notification from the source object.
	 * 
	 * @param notification
	 *            The message of from the source object, specifying what has
	 *            changed.
	 */
	protected void handleUpdate(Notification notification) {

		// If a changeDecoratorProperty() function was called, then update own
		// properties according to the special message format of the old value
		// being the property name and the new value being the property value
		if (notification.getFeatureID(
				GeometryPackage.class) == GeometryPackage.INODE___CHANGE_DECORATOR_PROPERTY__STRING_OBJECT) {

			String propertyName = notification.getOldStringValue();
			Object propertyValue = notification.getNewValue();

			// If the property already had this value, ignore it
			if (propertyValue == null
					|| !propertyValue.equals(properties.get(propertyName))) {
				properties.put(notification.getOldStringValue(),
						notification.getNewValue());
			} else {

				// If nothing changed, there are no need for further updates
				return;
			}
		}

		// Cast the cache as a cache of TriangleMeshes
		MeshCacheImpl<TriangleMesh> castCache = (MeshCacheImpl<TriangleMesh>) meshCache;

		// For the base implementation, we simply clear the render group of all
		// nodes and add the new mesh to it.
		render = new Group();

		// Try to get the mesh based on type
		TriangleMesh mesh = castCache.getMesh(source.getType());

		// If no mesh was found, specify one with triangles
		if (mesh == null) {
			mesh = castCache.getMesh(source.getTriangles());
		}

		// If a mesh was found, create a view for it and add it to the render
		// group.
		if (mesh != null) {
			render.getChildren().add(new MeshView(mesh));
		}

		// Get the center of the data object from the source
		Vertex center = source.getCenter();

		// Move the render to the correct location
		if (center != null) {
			render.setTranslateX(center.getX());
			render.setTranslateY(center.getY());
			render.setTranslateZ(center.getZ());
		}

		// Pass the update on to own observer
		eNotify(notification);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * model.impl.RenderObjectImpl#handleChildren(org.eclipse.emf.common.util.
	 * EList)
	 */
	@Override
	public void handleChildren(EList<IRenderElement<Group>> children) {

		// Save the list of children
		this.children = children;

		// If there are no children, there is nothing to do
		if (children != null) {

			// See if there is anything to do by checking the source's type.
			if ("union".equals(source.getType())) {

				// Empty the current list of children
				render.getChildren().clear();

				// For unions, we take each child mesh
				for (IRenderElement<Group> child : children) {
					render.getChildren().add(child.getMesh());
				}
			}

		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.eavp.geometry.view.model.impl.RenderObjectImpl#registerOption
	 * (org.eclipse.eavp.geometry.view.model.DisplayOption)
	 */
	@Override
	public void registerOption(DisplayOption option) {
		super.registerOption(option);

		// Listen to the option, refreshing the mesh when it changes
		option.eAdapters().add(new AdapterImpl() {
			@Override
			public void notifyChanged(Notification notification) {
				handleUpdate(notification);
			}
		});
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see model.impl.RenderObjectImpl#clone()
	 */
	@Override
	public Object clone() {

		// Create a new render object
		FXRenderObject clone = new FXRenderObject((INode) source.clone(),
				(MeshCache<TriangleMesh>) meshCache);

		// Copy this object's data into the clone
		clone.copy(this);
		return clone;
	}

}
