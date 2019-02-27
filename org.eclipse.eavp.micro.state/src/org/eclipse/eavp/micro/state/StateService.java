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

package org.eclipse.eavp.micro.state;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.eclipse.eavp.micro.main.java.InputStreamUtils;

/**
 * This class is the microservice for managing other services' states and providing information on the services' previous actions.
 * 
 * @author Robert Smith
 *
 */
@Path("/state")
public class StateService {

	// TODO Should be gotten through hateos
	// The url for the RDF service
	private static final String BASE_URL = "http://localhost:8080/org.eclipse.eavp.micro.rdf-1.0-SNAPSHOT/rdf4j/";

	/**
	 * The default constructor.
	 */
	public StateService() {
	}
	
	/**
	 * Get the latest state for a session on a given service.
	 * 
	 * @param identifier The sessioin identifier
	 * @param service The service whose state is sought
	 * @return APplication specific data that communicates the last state of the visualization
	 */
	@GET
	@Path("/get")
	@Consumes(MediaType.APPLICATION_OCTET_STREAM)
	public InputStream get(@QueryParam("identifier") String identifier, @QueryParam("service") String service) {
		
		try {
			
			//Open the connection
			URL url = new URL(BASE_URL + "get?identifier=" + identifier + service + "&service=stateservice");
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			return connection.getInputStream();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return new ByteArrayInputStream(null);
	}
	
	/**
	 * Post a new state for a service, with the identifier being the concatination of the identifier and the source service.
	 * 
	 * @param identifier The unique identifier describing the session for which the service is updating its state
	 * @param service The name of the service whose state is being updated.
	 * @param state Application specific data that will communicate the service's new state after the last user interaction.
	 */
	@POST
	@Path("/post")
	@Consumes(MediaType.APPLICATION_OCTET_STREAM)
	public void post(@QueryParam("identifier") String identifier, @QueryParam("service") String service, InputStream state) {
		
		System.out.println("STATE POST");
		
		try {
			
			//Open the connection
			URL url = new URL(BASE_URL + "put?identifier=" + identifier + service + "&service=stateservice");
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setDoOutput(true);
			connection.setRequestMethod("PUT");
			connection.setRequestProperty("Content-Type", "application/octet-stream");
			
			//Send the content of the post on to the rdf service
			connection.getOutputStream().write(InputStreamUtils.readStringStream(state).getBytes());
			connection.getResponseCode();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
