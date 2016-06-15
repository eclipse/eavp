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

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.impl.AdapterImpl;

import geometry.INode;
import javafx.scene.Group;
import javafx.scene.shape.MeshView;
import javafx.scene.shape.TriangleMesh;
import model.impl.MeshCacheImpl;
import model.impl.RenderObjectImpl;

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
	public FXRenderObject(INode source, MeshCacheImpl<TriangleMesh> meshCache) {

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
	}

	/**
	 * Handle a notification from the source object.
	 * 
	 * @param notification
	 *            The message of from the source object, specifying what has
	 *            changed.
	 */
	protected void handleUpdate(Notification notification) {

		// Cast the cache as a cache of TriangleMeshes
		MeshCacheImpl<TriangleMesh> castCache = (MeshCacheImpl<TriangleMesh>) meshCache;

		// For the base implementation, we simply clear the render group of all
		// nodes and add the new mesh to it.
		render.getChildren().clear();

		// Try to get the mesh based on type
		TriangleMesh mesh = castCache.getMesh(source.getType());

		// If no mesh was found, specify one with triangles
		if (mesh == null) {
			mesh = castCache.getMesh(source.getType());
		}

		// If a mesh was found, create a view for it and add it to the render
		// group.
		if (mesh != null) {
			render.getChildren().add(new MeshView(mesh));
		}
	}
}
