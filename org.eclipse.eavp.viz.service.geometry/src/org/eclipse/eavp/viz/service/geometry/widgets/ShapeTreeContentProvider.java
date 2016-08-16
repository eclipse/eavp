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

import java.util.List;

import org.eclipse.eavp.geometry.view.model.IRenderElement;
import org.eclipse.eavp.viz.service.IRenderElementHolder;
import org.eclipse.january.geometry.Geometry;
import org.eclipse.january.geometry.INode;
import org.eclipse.jface.viewers.IContentProvider;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.Viewer;

/**
 * <p>
 * Provides IShape objects for a TreeViewer, given a parent IShape
 * </p>
 * 
 * @author Andrew P. Belt
 */
public class ShapeTreeContentProvider implements ITreeContentProvider {

	/**
	 * <p>
	 * Temporary variable for setting the return value of getChildren when the
	 * visit() operation is called
	 * </p>
	 * 
	 */
	private Object[] temporaryChildren = null;

	/**
	 * The object holding the full list of IRenderElements.
	 */
	private IRenderElementHolder holder;

	/**
	 * The default constructor.
	 * 
	 * @param holder
	 *            The holder class for the render elements accessed by the
	 *            provider.
	 */
	public ShapeTreeContentProvider(IRenderElementHolder holder) {
		this.holder = holder;
	}

	/**
	 * Setter method for the holder from which the content provider will pull
	 * IRenderElements.
	 * 
	 * @param holder
	 */
	public void setRenderElementHolder(IRenderElementHolder holder) {
		this.holder = holder;
	}

	/**
	 * <p>
	 * Returns the child shapes of the given parent shape, if any
	 * </p>
	 * <p>
	 * If a PrimitiveShape, an empty ComplexShape, or null is passed, this
	 * operation returns an empty array of Objects.
	 * </p>
	 * 
	 * @param parentShape
	 *            <p>
	 *            The parent IShape element
	 *            </p>
	 * @return
	 *         <p>
	 *         The child IShapes
	 *         </p>
	 */
	@Override
	public Object[] getChildren(Object parentShape) {

		// If the element is an IShape, call its accept() operation to
		// trigger the visit() call

		if (parentShape instanceof IRenderElement) {
			temporaryChildren = null;

			// Call the parentShape's accept operation to call the appropriate
			// visit member function in this class

			// TODO Find a cleaner way to do this than listing all the operators
			String type = ((IRenderElement) parentShape).getBase().getType();

			if ("union".equals(type) || "complement".equals(type)
					|| "intersection".equals(type)) {

				// IShape is a ComplexShape, so put its children in the
				// temporary children field

				List childrenList = ((IRenderElement) parentShape).getBase()
						.getNodes();

				// If the input is a geometry, get its children
				temporaryChildren = new Object[childrenList.size()];

				// Replace each node with its wrapping RenderElement
				for (int i = 0; i < temporaryChildren.length; i++) {
					temporaryChildren[i] = holder
							.getRender((INode) childrenList.get(i));
				}

				// Use a blank state if there are no children to display

				if (temporaryChildren.length == 0) {
					temporaryChildren = new Object[] {
							new BlankShape((IRenderElement) parentShape) };
				}
			} else {

				// IShape is a PrimitiveShape, so it has no children :(

				temporaryChildren = new Object[0];
			}

			return temporaryChildren;

		} else if (parentShape instanceof Geometry) {

			List childrenList = ((Geometry) parentShape).getNodes();

			// If the input is a geometry, get its children
			temporaryChildren = new Object[childrenList.size()];

			// Replace each node with its wrapping RenderElement
			for (int i = 0; i < temporaryChildren.length; i++) {
				temporaryChildren[i] = holder
						.getRender((INode) childrenList.get(i));
			}

			// If the geometry has no children, display nothing
			if (temporaryChildren.length == 0) {
				temporaryChildren = new Object[0];
			}

			return temporaryChildren;
		} else {
			return null;
		}

	}

	/**
	 * <p>
	 * Returns the child shape elements of a root GeometryComponent when the
	 * input of the shape TreeViewer is set or reset
	 * </p>
	 * 
	 * @param inputElement
	 *            <p>
	 *            The input GeometryComponent
	 *            </p>
	 * @return
	 *         <p>
	 *         The child IShapes
	 *         </p>
	 */
	@Override
	public Object[] getElements(Object inputElement) {
		// Return an array of a geometry's nodes
		if (inputElement instanceof Geometry) {
			List childrenList = ((Geometry) inputElement).getNodes();

			Object[] children = new Object[childrenList.size()];

			// Replace each node with its wrapping RenderElement
			for (int i = 0; i < children.length; i++) {
				children[i] = holder.getRender((INode) childrenList.get(i));
				if (children[i] == null) {
					
				}
			}

			return children;
		} else if (inputElement instanceof INode) {
			// Return an array of the GeometryComponent's shapes
			IRenderElement parentGeometry = holder
					.getRender((INode) inputElement);
			Object[] children = parentGeometry.getBase().getNodes().toArray();

			// Replace each node with its wrapping RenderElement
			for (int i = 0; i < temporaryChildren.length; i++) {
				temporaryChildren[i] = holder
						.getRender((INode) temporaryChildren[i]);
			}

			return children;
		} else {
			return new Object[] {};
		}

	}

	/**
	 * <p>
	 * Returns the parent of the element if it exists
	 * </p>
	 * 
	 * @param element
	 *            <p>
	 *            The child IShape
	 *            </p>
	 * @return
	 *         <p>
	 *         The parent IShape
	 *         </p>
	 */
	@Override
	public Object getParent(Object element) {

		// Return null if the element is not an IShape

		if (!(element instanceof INode)) {
			return null;
		}
		// Return the object's parent

		INode shape = (INode) element;
		return holder.getRender(shape.getParent());

	}

	/**
	 * <p>
	 * Returns whether the given element has children
	 * </p>
	 * <p>
	 * In this implementation, there are no optimizations to quickly retrieve
	 * whether the element has children, so the array of child objects is found
	 * and counted.
	 * </p>
	 * 
	 * @param element
	 *            <p>
	 *            The IShape to check for children
	 *            </p>
	 * @return
	 *         <p>
	 *         Represents whether the element has children
	 *         </p>
	 */
	@Override
	public boolean hasChildren(Object element) {

		// Get the children from the getChildren operation and return whether
		// there are elements in the array

		// No optimizations can be made to quickly retrieve the number of
		// children from an object, so the best we can do is retrieve the
		// entire array of objects and count the number of elements.

		Object[] elements = getChildren(element);

		if (elements != null) {
			return elements.length > 0;
		} else {
			return false;
		}
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see IContentProvider#dispose()
	 */
	@Override
	public void dispose() {

		// We don't need to dispose of anything.

	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see IContentProvider#inputChanged(Viewer viewer, Object oldInput, Object
	 *      newInput)
	 */
	@Override
	public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
		// The state of this class does not depend on the input, so we do not
		// need to change the state when the input of the TreeViewer changes.

	}

	/**
	 * The blank state item to display in the shape TreeViewer when a
	 * ComplexShape has no children
	 * 
	 * @author Andrew P. Belt
	 * 
	 */
	public class BlankShape {

		/**
		 * The default label of the BlankShape, designed to be as generic as
		 * possible
		 */
		public static final String TEXT = "<add shape>";

		/**
		 * The shape which "contains" this blank shape object
		 */
		private IRenderElement parent;

		/**
		 * Initializes the BlankShape with a parent
		 * 
		 * @param parent
		 *            The parent shape in the TreeViewer hierarchy
		 */
		public BlankShape(IRenderElement parent) {
			this.parent = parent;
		}

		/**
		 * Returns the parent of this BlankShape
		 * 
		 * @return The parent shape
		 */
		public IRenderElement getParent() {
			return parent;
		}
	}
}