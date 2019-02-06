/*******************************************************************************
 * Copyright (c) 2019 UT-Battelle, LLC.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   Robert Smith
 *******************************************************************************/
package org.eclipse.eavp.micro.main.java;

import org.glassfish.jersey.media.multipart.MultiPartFeature;
import org.glassfish.jersey.server.ResourceConfig;

/**
 * The EAVP Jersey resource configuration.
 * 
 * @author Robert Smith
 *
 */
public class ResourceConfiguration extends ResourceConfig {

	/**
	 * The default constructor.
	 */
	public ResourceConfiguration() {
		super(MultiPartFeature.class);

		// Register the services
		packages("org.eclipse.eavp.micro.main.java");
		register(VisualizationService.class);
		register(FileParsingService.class);
		//register(FileServerService.class);
		//register(ICEMANRedirectService.class);
	}

}