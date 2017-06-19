/*******************************************************************************
 * Copyright (c) 2017 UT-Battelle, LLC.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   Initial API and implementation and/or initial documentation -
 *   Robert Smith
 *******************************************************************************/
package org.eclipse.eavp.viz.service.drawhandler;

import org.eclipse.eavp.viz.service.IVizCanvas;
import org.eclipse.eavp.viz.service.VizProgressMonitor;

/**
 * An IDrawHandler is responsible for drawing the contents of an IVizCanvas to
 * some implementation specific element of type T. The IDrawHandler will be set
 * to a parent T and, where possible, will draw the IVizCanvas's contents to a
 * new T object which is a child of the original.
 * 
 * @author Robert Smith
 *
 */
public interface IDrawHandler<T> {

	/**
	 * Draw the given canvas to a new T object in the parent.
	 * 
	 * @param canvas
	 *            The canvas to be drawn.
	 */
	void draw(IVizCanvas canvas);

	/**
	 * Return the drawn representation of the last canvas for which draw() was
	 * invoked.
	 * 
	 * @return A T object in which a canvas has been drawn or null if this
	 *         handler has not drawn anything (meaning isDrawn() returns false).
	 *         In the event of an error, the IDrawHandler is responsible for
	 *         drawing a T containing an error message if possible, rather than
	 *         returning null.
	 */
	T getResult();

	/**
	 * Check whether this handler has ever successfully drawn a canvas (meaning
	 * getResult() will return a non-null value).
	 * 
	 * @return False if draw() has never been invoked or if it has been invoked
	 *         and nothing could be drawn, not even an error message (such as
	 *         might be the case for draw(null) being called). Otherwise, true.
	 */
	boolean isDrawn();

	/**
	 * A convenience method that tries to save the canvas if possible,
	 * equivalent to save(monitor, false).
	 * 
	 * @param monitor
	 *            The monitor that will display progress for the saving
	 *            operation.
	 * @return True if the information was saved correctly, false otherwise.
	 */
	boolean save(VizProgressMonitor monitor);

	/**
	 * Save the contents of the drawn canvas.
	 * 
	 * @param monitor
	 *            The monitor that will display progress for the saving
	 *            operation
	 * @param saveAs
	 *            Whether to do the saving operation as a plain save (if false)
	 *            or to perform a save as (if true), where a window to select
	 *            the output file will be opened.
	 * @return True if the information was saved correctly, false otherwise.
	 */
	boolean save(VizProgressMonitor monitor, boolean saveAs);

	/**
	 * Set the parent object in which the canvas is to be drawn.
	 * 
	 * @param parent
	 *            The object which will serve as a parent to
	 */
	void setParent(T parent);
}
