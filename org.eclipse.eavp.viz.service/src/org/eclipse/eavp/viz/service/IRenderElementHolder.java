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
package org.eclipse.eavp.viz.service;

import geometry.INode;
import model.IRenderElement;

/**
 * An interface for classes that serve as a holder for RenderElements and allows
 * them to be associated with their source INodes.
 * 
 * @author Robert Smith
 *
 */
public interface IRenderElementHolder {

	/**
	 * Get the IRenderElement for the given node.
	 * 
	 * @param node
	 *            The node whose IRenderElement is sought.
	 * @return The IRenderElement containing the given node as its source.
	 */
	IRenderElement getRender(INode node);
}
