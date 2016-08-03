/*******************************************************************************
 * Copyright (c) 2015 UT-Battelle, LLC.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   Robert Smith
 *******************************************************************************/
package org.eclipse.eavp.viz.service.javafx.geometry.datatypes;

import org.eclipse.eavp.viz.modeling.Shape;
import org.eclipse.eavp.viz.modeling.Tube;
import org.eclipse.eavp.viz.modeling.base.IMesh;
import org.eclipse.eavp.viz.modeling.factory.BasicControllerProviderFactory;
import org.eclipse.eavp.viz.modeling.factory.IControllerProvider;

/**
 * A factory for creating instances of Shape with FXShapeViews for use with
 * JavaFX.
 * 
 * @author Robert Smith
 *
 */
public class FXShapeControllerProviderFactory
		extends BasicControllerProviderFactory {

	/**
	 * The default cosntructor.
	 */
	public FXShapeControllerProviderFactory() {
		super();

		// Set the ShapeMesh provider
		typeMap.put(Shape.class,
				new IControllerProvider<FXShapeController>() {
					@Override
					public FXShapeController createController(IMesh model) {

						// Create an FXShapeView for the model, then wrap them
						// both in a
						// shape controller
						FXShapeView view = new FXShapeView((Shape) model);
						return new FXShapeController((Shape) model, view);
					}
				});

		// Set the TubeMesh provider
		typeMap.put(Tube.class,
				new IControllerProvider<FXShapeController>() {
					@Override
					public FXShapeController createController(IMesh model) {

						// Create an FXShapeView for the model, then wrap them
						// both in a
						// shape controller
						FXShapeView view = new FXShapeView((Tube) model);
						return new FXShapeController((Shape) model, view);
					}
				});
	}

}
