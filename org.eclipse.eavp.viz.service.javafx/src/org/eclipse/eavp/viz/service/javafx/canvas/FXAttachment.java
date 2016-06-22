/*******************************************************************************
 * Copyright (c) 2015 UT-Battelle, LLC.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   Tony McCrary (tmccrary@l33tlabs.com), Robert Smith
 *******************************************************************************/
package org.eclipse.eavp.viz.service.javafx.canvas;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.eavp.geometry.view.javafx.render.FXMeshCache;
import org.eclipse.eavp.geometry.view.javafx.render.FXRenderObject;
import org.eclipse.eavp.viz.service.javafx.internal.Util;
import org.eclipse.eavp.viz.service.javafx.scene.model.IAttachment;
import org.eclipse.eavp.viz.service.javafx.scene.model.INode;
import org.eclipse.eavp.viz.service.javafx.viewer.IAttachmentManager;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.impl.BasicNotifierImpl;
import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.util.EContentAdapter;

import geometry.Geometry;
import javafx.scene.Group;
import model.IRenderElement;

/**
 * <p>
 * JavaFX implementation of GeometryAttachment.
 * </p>
 * 
 * @author Tony McCrary (tmccrary@l33tlabs.com), Robert Smith
 *
 */
public class FXAttachment extends BasicAttachment {

	/**
	 * Node used to attach geometry to (instead of the root, to keep things
	 * easier to manipulate).
	 */
	protected Group fxAttachmentNode;

	/** The manager that owns this attachment. */
	private final IAttachmentManager manager;

	/** */
	protected List<Geometry> knownParts;

	/**
	 * The list of elements representing the rendered nodes.
	 */
	protected List<IRenderElement<Group>> renderedNodes;

	/**
	 * The cache of all rendered meshes for this attachment.
	 */
	protected FXMeshCache cache;

	/**
	 * <p>
	 * Creates an FXGeometryAttachment instance.
	 * </p>
	 * 
	 * @param manager
	 *            the manager that created this instance.
	 */
	public FXAttachment(IAttachmentManager manager) {
		this.manager = manager;
		fxAttachmentNode = new Group();
		renderedNodes = new ArrayList<IRenderElement<Group>>();
		cache = new FXMeshCache();
	}

	/**
	 * Create a RenderElement for the node.
	 * 
	 * This function returns a simple FXRenderObject by default. It is intended
	 * that subclasses will override this function in order to create a
	 * hierarchy of IRenderDecorators appropriate for the rending functionality
	 * they offer.
	 * 
	 * @param node
	 *            The node which will serve as the source for the new
	 *            RenderElement
	 * @return An IRenderElement which can be used to access all of the
	 *         rendering functionality offered by this type of attachment, with
	 *         the given node as the data source.
	 */
	protected IRenderElement<Group> createElement(geometry.INode node) {
		return new FXRenderObject(node, cache);
	}

	/**
	 * A function invoked when the attachment receives an update from its
	 * contained modeling parts. This function does nothing by default and is
	 * intended to be implemented by subclasses
	 * 
	 * @param source
	 *            The controller which triggered the update
	 */
	protected void handleUpdate(Geometry geom, Notification notification) {

		// If a notification was given, check it for any changes to the list of
		// nodes.
		if (notification != null) {

			// If something was added to the list, create a render object for it
			if (notification.getEventType() == Notification.ADD) {
				renderedNodes.add(createElement(
						(geometry.INode) notification.getNewValue()));
			}

			// If something was removed, remove the render object for it
			else if (notification.getEventType() == Notification.REMOVE) {

				// Cast the removed object
				geometry.INode removed = (geometry.INode) notification
						.getOldValue();

				// Search for the render element that is based on the removed
				// node and remove it from the list.
				for (IRenderElement<Group> render : renderedNodes) {
					if (render.getBase().equals(removed)) {
						renderedNodes.remove(render);
						break;
					}
				}
			}
		}

		// Clear the node of all children so the scene will be refreshed.
		fxAttachmentNode.getChildren().clear();

		// Handle the children for each of the rendered nodes
		for (IRenderElement<Group> render : renderedNodes) {

			// Get the source object's children
			List<geometry.INode> children = render.getBase().getNodes();

			// If children exist, handle them
			if (!children.isEmpty()) {

				// The list of rendered objects that are based on one of
				// render's source's children
				EList<IRenderElement<Group>> childRenders = new BasicEList<IRenderElement<Group>>();

				// Search through the list of all renders
				for (IRenderElement<Group> renderCandidate : renderedNodes) {

					// If a render's source is in the list of child sources, add
					// that render to the list of child renders
					if (children.contains(renderCandidate.getBase())) {
						childRenders.add(renderCandidate);
					}
				}

				// Have the render element handle its children
				render.handleChildren(childRenders);
			}

		}

		// Add each render to the scene
		for (IRenderElement<Group> render : renderedNodes) {
			fxAttachmentNode.getChildren().add(render.getMesh());
		}
	}

	/**
	 * Get the list of all modeling parts which have been added to this
	 * attachment.
	 * 
	 * @return All parts contained in this attachment
	 */
	public List<Geometry> getKnownParts() {
		return knownParts;
	}

	/**
	 * @see BasicAttachment#attach(INode)
	 */
	@Override
	public void attach(INode owner) {
		super.attach(owner);

		if (fxAttachmentNode == null) {
			fxAttachmentNode = new Group();
		}

		Group fxNode = Util.getFxGroup(owner);
		fxNode.getChildren().add(fxAttachmentNode);
	}

	/**
	 * @see IAttachment#detach(INode)
	 */
	@Override
	public void detach(INode owner) {
		Group fxNode = Util.getFxGroup(owner);

		if (fxAttachmentNode != null) {
			fxNode.getChildren().remove(fxAttachmentNode);
		}

		super.detach(owner);
	}

	/**
	 * @see IAttachment#isSingleton()
	 */
	@Override
	public boolean isSingleton() {
		return false;
	}

	/**
	 * @see IModelPart#setVisible(boolean)
	 */
	@Override
	public void setVisible(boolean visible) {
		super.setVisible(visible);

		fxAttachmentNode.setVisible(visible);
	}

	@Override
	public void addGeometry(Geometry geom) {
		super.addGeometry(geom);

		if (fxAttachmentNode == null) {
			fxAttachmentNode = new Group();
		}

		if (knownParts == null) {
			knownParts = new ArrayList<>();
		}

		// If the geometry is not recognized, add it
		if (!knownParts.contains(geom)) {

			// Register to listen for changes from the geometry or its children
			// INodes
			((BasicNotifierImpl) geom.getNodes()).eAdapters()
					.add(new EContentAdapter() {

						@Override
						public void notifyChanged(Notification notification) {
							handleUpdate(geom, notification);
						}
					});

			// Add the geometry to the list of known parts
			knownParts.add(geom);

			// Have the geometry refreshed when it is added
			handleUpdate(geom, null);

		}
	}

	/**
	 * <p>
	 * Generates JavaFX shapes from the input IShape.
	 * </p>
	 * 
	 * @param shape
	 *            an ICE Geometry IShape
	 */
	@Override
	public void processShape(geometry.INode shape) {
		// Nothing to do.
	}

	/**
	 * 
	 */
	@Override
	protected void disposeShape(geometry.INode shape) {
	}

	/**
	 *
	 * @param copy
	 * @return
	 */
	@Override
	public List<geometry.INode> getShapes(boolean copy) {
		return super.getShapes(copy);
	}

	/**
	 *
	 */
	@Override
	public void clearShapes() {
		super.clearShapes();
	}

	/**
	 * 
	 * @return
	 */
	public IAttachmentManager getManager() {
		return manager;
	}

	/**
	 *
	 * @return
	 */
	public javafx.scene.Node getFxParent() {
		return fxAttachmentNode;
	}

	/**
	 *
	 * @return
	 */
	public Group getFxNode() {
		return fxAttachmentNode;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.eavp.viz.service.javafx.scene.model.IAttachment#getType()
	 */
	@Override
	public Class<?> getType() {
		return BasicAttachment.class;
	}

	/**
	 * 
	 */
	public String getName() {
		if (fxAttachmentNode == null) {
			return "UNNAMED";
		}

		return fxAttachmentNode.getId();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.eavp.viz.service.javafx.canvas.IModelPart#removeGeometry(
	 * geometry.Geometry)
	 */
	@Override
	public void removeGeometry(Geometry geom) {

		// Remove the part from the list of seen parts
		knownParts.remove(geom);
	}

}
