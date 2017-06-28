/*******************************************************************************
 * Copyright (c) 2017 UT-Battelle, LLC.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   Robert Smith
 *******************************************************************************/
package org.eclipse.eavp.viz.service.javafx.canvas;

import org.eclipse.eavp.viz.service.drawhandler.AbstractDrawHandler;

/**
 * An abstract Draw Handler with basic implementation for drawing 3D
 * visualizations with JavaFX.
 * 
 * @author Robert Smith
 *
 * @param <T>
 *            The type of the windowing system specific base UI element on which
 *            to draw the canvas.
 */
abstract public class FX3DDrawHandler<T> extends AbstractDrawHandler<T> {

}
