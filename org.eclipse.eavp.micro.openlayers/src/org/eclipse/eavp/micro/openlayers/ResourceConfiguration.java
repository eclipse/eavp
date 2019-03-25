package org.eclipse.eavp.micro.openlayers;

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
		packages("org.eclipse.eavp.micro.openlayers");
		register(OpenLayersService.class);
	}

}