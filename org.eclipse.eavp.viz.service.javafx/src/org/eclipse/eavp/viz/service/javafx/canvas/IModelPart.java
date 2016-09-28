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

import java.util.List;

import org.eclipse.eavp.viz.modeling.base.IController;
import org.eclipse.january.geometry.Geometry;

/**
 * <p>
 * Interface for accepting and working with ICE modeling parts.
 * </p>
 * 
 * @author Tony McCrary (tmccrary@l33tlabs.com), Robert Smith
 *
 */
public interface IModelPart {

	/**
	 * <p>
	 * Adds a Geometry instance to this entity.
	 * </p>
	 * 
	 * @param geom
	 *            an ICE Geometry instance
	 */
	public void addGeometry(IController geom);

	/**
	 * Adds a Geometry instance to this entity.
	 * 
	 * @param geom
	 *            Geometry instance
	 */
	public void addGeometry(Geometry geom);

	/**
	 * Adds a shape to this entity.
	 * 
	 * @param shape
	 *            An EAVP CSG INode instance.
	 */
	public void addShape(org.eclipse.january.geometry.INode shape);

	/**
	 * Removes a geometry from those displayed in the model.
	 * 
	 * @param geom
	 *            The geometry to be removed.
	 */
	public void removeGeometry(Geometry geom);

	/**
	 * Removes the supplied INode from this entity.
	 * 
	 * @param shape
	 *            The EAVP INode to remove.
	 */
	public void removeShape(org.eclipse.january.geometry.INode shape);

	/**
	 * Returns true if the entity contains the supplied INode, false otherwise.
	 * 
	 * @param shape
	 *            EAVP CSG INode to test for
	 * 
	 * @return true if the entity contains the supplied INode, false otherwise.
	 */
	public boolean hasShape(org.eclipse.january.geometry.INode shape);

	/**
	 * <p>
	 * Returns the IShape at the specified index or null if it cannot be found.
	 * </p>
	 * 
	 * @param index
	 *            the index to retrieve the IShape at
	 * 
	 * @return a CSG INode instance or null if one cannot be found
	 */
	public org.eclipse.january.geometry.INode getShape(int index);

	/**
	 * <p>
	 * Returns a list of the shapes associated with this entity.
	 * </p>
	 * 
	 * <p>
	 * Optionally, a copy can be made of the list.
	 * </p>
	 * 
	 * @param copy
	 *            if true, the returned list will be a copy
	 * 
	 * @return a List of INodes associated with this shape.
	 */
	public List<org.eclipse.january.geometry.INode> getShapes(boolean copy);

	/**
	 * <p>
	 * Removes all shapes associated with this entity.
	 * </p>
	 */
	public void clearShapes();

	/**
	 * <p>
	 * Sets the entity to be immutable, which means it's values cannot be
	 * changed (no new geometry or shapes).
	 * </p>
	 * 
	 * @param immutable
	 *            if true, the entity will be made immutable otherwise the
	 *            entity will be mutable
	 */
	void setImmutable(boolean immutable);

	/**
	 * <p>
	 * Returns true if the entity is immutable, false otherwise.
	 * </p>
	 */
	boolean isImmutable();

	/**
	 * <p>
	 * Sets the entity to be visible or not visible in the scene.
	 * </p>
	 * 
	 * @param visible
	 *            if true, the entity will be made visible otherwise the entity
	 *            will not be visible
	 */
	void setVisible(boolean visible);

	/**
	 * <p>
	 * Returns true if the entity is visible, false otherwise.
	 * </p>
	 */
	boolean isVisible();

}
