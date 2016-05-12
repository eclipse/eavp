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
package org.eclipse.eavp.viz.service.geometry.persistence;

import javax.xml.bind.annotation.XmlRootElement;

import org.eclipse.eavp.viz.modeling.ShapeMesh;
import org.eclipse.eavp.viz.service.geometry.shapes.OperatorType;

/**
 * A PersistableShape specific to cubes.
 * 
 * @author Robert Smith
 *
 */
@XmlRootElement(name = "union")
public class PersistableUnion extends PersistableShape {

	/**
	 * The nullary constructor. This is a simple constructor only intended for
	 * use by JAXB.
	 */
	public PersistableUnion() {
		super();
	}

	/**
	 * The default constructor.
	 * 
	 * @param mesh
	 *            The mesh to be compressed
	 */
	public PersistableUnion(ShapeMesh mesh) {
		super(mesh);

		// This shape is a operator
		operatorType = OperatorType.Union;

		// Remove the default name
		if ("Union".equals(name)) {
			name = null;
		}
	}

}
