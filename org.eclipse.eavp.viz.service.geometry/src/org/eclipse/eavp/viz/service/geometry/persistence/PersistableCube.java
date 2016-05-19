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

import org.eclipse.eavp.viz.modeling.Shape;
import org.eclipse.eavp.viz.service.geometry.shapes.ShapeType;

/**
 * A PersistableShape specific to cubes.
 * 
 * @author Robert Smith
 *
 */
@XmlRootElement(name = "cube")
public class PersistableCube extends PersistableShape {

	/**
	 * The nullary constructor. This is a simple constructor only intended for
	 * use by JAXB.
	 */
	public PersistableCube() {
		super();

		// Set the default type and name
		shapeType = ShapeType.Cube;
		name = "Cube";
	}

	/**
	 * The default constructor.
	 * 
	 * @param mesh
	 *            The mesh to be compressed
	 */
	public PersistableCube(Shape mesh) {
		super(mesh);

		// This shape is a cube
		shapeType = ShapeType.Cube;

		// Remove the default name
		if ("Cube".equals(name)) {
			name = null;
		}
	}

}
