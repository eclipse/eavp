/*******************************************************************************
 * Copyright (c) 2016 UT-Battelle, LLC.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   Initial API and implementation and/or initial documentation - 
 *   Robert Smith
 *******************************************************************************/
package org.eclipse.eavp.viz.service.geometry.internal;

import org.eclipse.january.geometry.model.importer.IGeometryImporterServiceFactory;

/**
 * Holder class for a GeometryImporterServiceFactory.
 * 
 * @author Robert Smith
 *
 */
public class GeometryImporterFactoryHolder {
	/**
	 * The held factory.
	 */
	private static IGeometryImporterServiceFactory factory;

	/**
	 * Getter for the held GeometryImporterServiceFactory.
	 * 
	 * @return the held GeometryImporterServiceFactory
	 */
	public static IGeometryImporterServiceFactory getFactory() {
		return factory;
	}

	/**
	 * Setter for the GeometryImporterServiceFactory.
	 * 
	 * @param input
	 *            the GeometryImporterServiceFactory to hold
	 */
	public static void setGeometryImporterServiceFactory(
			IGeometryImporterServiceFactory input) {
		GeometryImporterFactoryHolder.factory = input;
		return;
	}

	/**
	 * Remove the given GeometryImporterServiceFactory if it is held by this
	 * class.
	 * 
	 * @input A GeometryImporterServiceFactory to remove.
	 */
	public static void unsetGeometryImporterServiceFactory(
			IGeometryImporterServiceFactory input) {
		if (input == factory) {
			factory = null;
		}
		return;
	}

}