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
 * A PersistableShape specific to spheres.
 * 
 * @author Robert Smith
 *
 */
@XmlRootElement(name = "sphere")
public class PersistableSphere extends PersistableShape {

	/**
	 * The nullary constructor. This is a simple constructor only intended for
	 * use by JAXB.
	 */
	public PersistableSphere() {
		super();

		// Set the default type and name
		shapeType = ShapeType.Sphere;
		name = "Sphere";
	}

	/**
	 * The default constructor.
	 * 
	 * @param mesh
	 *            The mesh to be compressed
	 */
	public PersistableSphere(Shape mesh) {
		super(mesh);

		// This shape is a cube
		shapeType = ShapeType.Sphere;

		// Remove the default name
		if ("Sphere".equals(name)) {
			name = null;
		}
	}

}
