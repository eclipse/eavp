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
import org.eclipse.eavp.geometry.view.model.IRenderElement;
import org.eclipse.eavp.viz.datastructures.VizObject.IManagedUpdateable;
import org.eclipse.eavp.viz.datastructures.VizObject.IManagedUpdateableListener;
import org.eclipse.eavp.viz.datastructures.VizObject.SubscriptionType;
import org.eclipse.eavp.viz.modeling.base.IController;
import org.eclipse.eavp.viz.service.javafx.internal.Util;
import org.eclipse.eavp.viz.service.javafx.scene.model.IAttachment;
import org.eclipse.eavp.viz.service.javafx.scene.model.INode;
import org.eclipse.eavp.viz.service.javafx.viewer.IAttachmentManager;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.impl.AdapterImpl;
import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.util.EContentAdapter;
import org.eclipse.january.geometry.Geometry;

import javafx.scene.Group;

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

	/** */
	protected List<IController> knownPartControllers;

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
		renderedNodes = new ArrayList<IRenderElement>();
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
	protected IRenderElement<Group> createElement(
			org.eclipse.january.geometry.INode node) {
		return new FXRenderObject(node, cache);
	}

	/**
	 * A function invoked when the attachment receives an update from its
	 * contained modeling parts.
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

				// A list of all the new nodes to add
				ArrayList<org.eclipse.january.geometry.INode> newNodes = new ArrayList<org.eclipse.january.geometry.INode>();

				// Add all the new node's descendents
				newNodes.addAll(getAllNodes(
						(org.eclipse.january.geometry.INode) notification
								.getNewValue()));

				// Add each node to the scene
				for (org.eclipse.january.geometry.INode node : newNodes) {

					// Whether the node was found in the attachment
					boolean found = false;

					// Search the rendered elements to see if the shape is
					// already in the list
					for (IRenderElement<Group> render : renderedNodes) {
						if (render.getBase().equals(node)) {
							found = true;
							break;
						}
					}

					// If the node wasn't found, render it and add it to the
					// list
					if (!found) {
						IRenderElement element = createElement(node);
						renderedNodes.add(element);

						// Register as a listener for the new render element
						element.eAdapters().add(new AdapterImpl() {
							@Override
							public void notifyChanged(
									Notification notification) {
								handleUpdate(geom, notification);
							}
						});
					}
				}
			}

			// If something was removed, remove the render object for it
			else if (notification.getEventType() == Notification.REMOVE) {

				// Cast the removed object
				org.eclipse.january.geometry.INode removed = (org.eclipse.january.geometry.INode) notification
						.getOldValue();

				// Get a list of all the node's children
				ArrayList<org.eclipse.january.geometry.INode> removedNodes = new ArrayList<org.eclipse.january.geometry.INode>();
				removedNodes.add(removed);
				for (int i = 0; i < removedNodes.size(); i++) {
					removedNodes.addAll(removedNodes.get(i).getNodes());
				}

				// The list of renders to be removed
				ArrayList<IRenderElement> oldRenders = new ArrayList<IRenderElement>();

				// Search for the render element(s) that are based on the
				// removed nodes and add them to the list to remove.
				for (IRenderElement<Group> render : renderedNodes) {
					if (removedNodes.contains(render.getBase())) {
						oldRenders.add(render);
					}
				}

				// Remove all the renders for old objects
				renderedNodes.removeAll(oldRenders);

			}
		} else if (renderedNodes.isEmpty()) {
			// A list of all the new nodes to add
			ArrayList<org.eclipse.january.geometry.INode> newNodes = new ArrayList<org.eclipse.january.geometry.INode>();

			// Add all the new node's descendents
			newNodes.addAll(geom.getNodes());

			// Add each node to the scene
			for (org.eclipse.january.geometry.INode node : newNodes) {

				// Whether the node was found in the attachment
				boolean found = false;

				// Search the rendered elements to see if the shape is
				// already in the list
				for (IRenderElement<Group> render : renderedNodes) {
					if (render.getBase().equals(node)) {
						found = true;
						break;
					}
				}

				// If the node wasn't found, render it and add it to the
				// list
				if (!found) {
					IRenderElement element = createElement(node);
					renderedNodes.add(element);

					// Register as a listener for the new render element
					element.eAdapters().add(new AdapterImpl() {
						@Override
						public void notifyChanged(Notification notification) {
							handleUpdate(geom, notification);
						}
					});
				}
			}
		}

		// Clear the node of all children so the scene will be refreshed.
		fxAttachmentNode.getChildren().clear();

		// The top level renders for the tree
		ArrayList<IRenderElement<Group>> topRenders = (ArrayList<IRenderElement<Group>>) renderedNodes
				.clone();

		// Handle the children for each of the rendered nodes
		for (IRenderElement<Group> render : renderedNodes) {

			// Get the source object's children
			List<org.eclipse.january.geometry.INode> children = render.getBase()
					.getNodes();

			// The list of rendered objects that are based on one of
			// render's source's children
			EList<IRenderElement<Group>> childRenders = new BasicEList<IRenderElement<Group>>();

			// If children exist, handle them
			if (!children.isEmpty()) {

				// Search through the list of all renders
				for (IRenderElement<Group> renderCandidate : renderedNodes) {

					// If a render's source is in the list of child sources, add
					// that render to the list of child renders
					if (children.contains(renderCandidate.getBase())) {
						childRenders.add(renderCandidate);

						// This render had a parent, so it is not on the top
						// level of the hierarchy
						topRenders.remove(renderCandidate);
					}
				}

			}

			// Have the render element handle its children
			render.handleChildren(childRenders);

		}

		// Add each render to the scene
		for (IRenderElement<Group> render : topRenders) {
			fxAttachmentNode.getChildren().add(render.getMesh());
		}
	}

	/**
	 * A function invoked when the attachment receives an update from its
	 * contained modeling parts. This function does nothing by default and is
	 * intended to be implemented by subclasses
	 * 
	 * @param source
	 *            The controller which triggered the update
	 */
	protected void handleUpdate(IController source) {
		// Nothing to do
	}

	/**
	 * Get the list of all modeling parts which have been added to this
	 * attachment.
	 * 
	 * @return All parts contained in this attachment
	 */
	public List<IController> getKnownPartControllers() {
		return knownPartControllers;
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
	 * Get a list of all nodes in the tree with the given node as the root.
	 * 
	 * @param base
	 *            The node whose descendant nodes are sought.
	 * @return An ArrayList consisting of base and all of its descendants in
	 *         depth first order.
	 */
	private ArrayList<org.eclipse.january.geometry.INode> getAllNodes(
			org.eclipse.january.geometry.INode base) {

		// The list collecting all the nodes in the tree
		ArrayList<org.eclipse.january.geometry.INode> list = new ArrayList<org.eclipse.january.geometry.INode>();

		// Start by adding the base node
		list.add(base);

		// Recursively add the tree with each child node as the root
		for (org.eclipse.january.geometry.INode child : base.getNodes()) {
			list.addAll(getAllNodes(child));
		}

		return list;
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.eavp.viz.service.javafx.canvas.BasicAttachment#addGeometry(
	 * org.eclipse.eavp.viz.modeling.base.IController)
	 */
	@Override
	public void addGeometry(IController geom) {
		super.addGeometry(geom);

		if (fxAttachmentNode == null) {
			fxAttachmentNode = new Group();
		}

		if (knownPartControllers == null) {
			knownPartControllers = new ArrayList<>();
		}

		if (!knownPartControllers.contains(geom)) {

			geom.register(new IManagedUpdateableListener() {
				@Override
				public void update(IManagedUpdateable component,
						SubscriptionType[] type) {

					javafx.application.Platform.runLater(new Runnable() {
						@Override
						public void run() {

							// Invoke the update function
							handleUpdate(geom);
						}
					});
				}

				@Override
				public ArrayList<SubscriptionType> getSubscriptions(
						IManagedUpdateable source) {

					// Register to receive all updates
					ArrayList<SubscriptionType> types = new ArrayList<SubscriptionType>();
					types.add(SubscriptionType.ALL);
					return types;
				}
			});

			// Have the geometry refreshed when it is added
			knownPartControllers.add(geom);
			handleUpdate(geom);


		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.eavp.viz.service.javafx.canvas.BasicAttachment#addGeometry(
	 * org.eclipse.january.geometry.Geometry)
	 */
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
			geom.eAdapters().add(new EContentAdapter() {

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
	public void processShape(org.eclipse.january.geometry.INode shape) {
		// Nothing to do.
	}

	/**
	 * 
	 */
	@Override
	protected void disposeShape(org.eclipse.january.geometry.INode shape) {
	}

	/**
	 *
	 * @param copy
	 * @return
	 */
	@Override
	public List<org.eclipse.january.geometry.INode> getShapes(boolean copy) {
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
