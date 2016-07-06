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
package org.eclipse.eavp.viz.service.javafx.geometry.plant;

import org.eclipse.eavp.viz.modeling.base.IMesh;
import org.eclipse.eavp.viz.modeling.factory.BasicControllerProviderFactory;
import org.eclipse.eavp.viz.modeling.factory.IControllerProvider;
import org.eclipse.eavp.viz.service.geometry.reactor.HeatExchanger;
import org.eclipse.eavp.viz.service.geometry.reactor.JunctionController;
import org.eclipse.eavp.viz.service.geometry.reactor.JunctionRefactor;
import org.eclipse.eavp.viz.service.geometry.reactor.PipeRefactor;
import org.eclipse.eavp.viz.service.geometry.reactor.ReactorController;
import org.eclipse.eavp.viz.service.geometry.reactor.Reactor;

/**
 * A factory for creating JavaFX views and controllers for Reactor Analyzer
 * components.
 * 
 * @author Robert Smith
 *
 */
public class FXPlantViewControllerProviderFactory
		extends BasicControllerProviderFactory {

	/**
	 * The default constructor.
	 */
	public FXPlantViewControllerProviderFactory() {
		super();

		// Set the JunctionMesh provider
		typeMap.put(JunctionRefactor.class,
				new IControllerProvider<JunctionController>() {
					@Override
					public JunctionController createController(IMesh model) {

						// Create a FXJunction view for junctions
						FXJunctionView view = new FXJunctionView(
								(JunctionRefactor) model);
						return new JunctionController((JunctionRefactor) model,
								view);
					}
				});

		// Set the PipeMesh provider
		typeMap.put(PipeRefactor.class,
				new IControllerProvider<FXPipeController>() {
					@Override
					public FXPipeController createController(IMesh model) {

						// Create a FXPipeView for pipes
						FXPipeView view = new FXPipeView((PipeRefactor) model);
						return new FXPipeController((PipeRefactor) model, view);
					}
				});

		// Set the ReactorMesh provider
		typeMap.put(Reactor.class,
				new IControllerProvider<ReactorController>() {
					@Override
					public ReactorController createController(IMesh model) {

						// Create a FXReactorView for reactors
						FXReactorView view = new FXReactorView(
								(Reactor) model);
						return new ReactorController((Reactor) model, view);
					}
				});

		// Set the HeatExchangerMesh provider
		typeMap.put(HeatExchanger.class,
				new IControllerProvider<FXHeatExchangerController>() {
					@Override
					public FXHeatExchangerController createController(
							IMesh model) {

						// Create a FXHeatExchangerView for heat exchangers.
						FXHeatExchangerView view = new FXHeatExchangerView(
								model);
						return new FXHeatExchangerController(
								(HeatExchanger) model, view);
					}
				});
	}

}
