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

package org.eclipse.eavp.micro.rdf4j;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.net.URLDecoder;
import java.nio.file.Files;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustSelfSignedStrategy;
import org.apache.http.entity.InputStreamEntity;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.ssl.SSLContextBuilder;
import org.eclipse.eavp.micro.main.java.InputStreamUtils;
import org.eclipse.rdf4j.model.util.ModelBuilder;
import org.eclipse.rdf4j.query.BindingSet;
import org.eclipse.rdf4j.query.QueryResults;
import org.eclipse.rdf4j.repository.Repository;
import org.eclipse.rdf4j.repository.http.HTTPRepository;
import org.eclipse.rdf4j.repository.util.Repositories;

import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;

/**
 * This class is the microservice for communicating with the RDF4J rdf store.
 * 
 * @author Robert Smith
 *
 */
@Path("/rdf4j")
public class RDF4JService {

	// TODO Should be settable instead of hard coded
	// The url for the RDF server
	private static final String BASE_URL = "http://localhost:8080/rdf4j-server/";

	// Namespace used for the eavp database
	private static final String NAMESPACE = "http://eclipse.org/eavp/";

	// The name of the repo on the server
	private static final String REPO_NAME = "eavp";

	/**
	 * The repository to which all calls will be directed
	 */
	private Repository repo;

	/**
	 * The default constructor.
	 */
	public RDF4JService() {
		repo = new HTTPRepository(BASE_URL, REPO_NAME);
	}
	
	/**
	 * Delete an artifact from the database.
	 * 
	 * @param identifier The unique identifier for the artifact to be deleted
	 * @param service The service which produced the artifact to be deleted
	 */
	@DELETE
	@Path("/delete")
	public void delete(@QueryParam("identifier") String identifier, @QueryParam("service") String service) {
		Repositories.consume(repo, (c) -> {
			c.prepareUpdate("DELETE {?a ?b ?c . ?d ?e ?c} WHERE{?a ?b ?c FILTER("
					+ "str(?a)=\"" + NAMESPACE + service + "\"^^xsd:string &&"
					+ "str(?b)=\"" + NAMESPACE + "service\"^^xsd:string"
					+ ") . ?d ?e ?c FILTER ("
					+ "str(?d)=\"" + NAMESPACE + identifier + "\"^^xsd:string &&"
					+ "str(?e)=\"" + NAMESPACE + "identifier\"^^xsd:string"
					+ ") }").execute();
		});
	}
	
	/**
	 * Perform a get for the service and identifier
	 * 
	 * @param identifier
	 *            The unique identifier for the artifact.
	 * @param service
	 *            The name of the service that generated the artifact.
	 * @return An octet stream containing the artifact or containing nothing if it
	 *         was not found.
	 */
	@GET
	@Path("/get")
	@Produces(MediaType.APPLICATION_OCTET_STREAM)
	public InputStream get(@QueryParam("identifier") String identifier, @QueryParam("service") String service) {
		
		// Perform the get on the repo
		List<BindingSet> results = Repositories
				.tupleQuery(
						repo, "Select ?artifact WHERE{ <" + NAMESPACE + identifier + "> <" + NAMESPACE + "identifier> ?artifact" 
								+ " . <" + NAMESPACE + service + "> <" + NAMESPACE + "service> ?artifact}",
						r -> QueryResults.asList(r));

		// If something was found, return it as a byte array
		if (!results.isEmpty()) {
			try {
				return new ByteArrayInputStream(
						results.get(0).iterator().next().getValue().stringValue().getBytes("UTF-8"));
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		// If the real value couldn't be found, return an empty stream
		return new ByteArrayInputStream(null);
	}

	/**
	 * Perform a get for the service and identifier, deleting the retrieved artifact
	 * in the database.
	 * 
	 * @param identifier
	 *            The unique identifier for the artifact.
	 * @param service
	 *            The name of the service that generated the artifact.
	 * @return An octet stream containing the artifact or containing nothing if it
	 *         was not found.
	 */
	@GET
	@Path("/get/destructive")
	@Produces(MediaType.APPLICATION_OCTET_STREAM)
	public InputStream getDestructive(@QueryParam("identifier") String identifier,
			@QueryParam("service") String service) {

		// Perform the get on the repo
		List<BindingSet> results = Repositories
				.tupleQuery(
						repo, "Select ?artifact WHERE{ <" + NAMESPACE + identifier + "> <" + NAMESPACE + "artifact> ?artifact" 
								+ " . <" + NAMESPACE + identifier + "> <" + NAMESPACE + "service> \"" + service + "\"}",
						r -> QueryResults.asList(r));

		// If something was found, return it as a byte array
		if (!results.isEmpty()) {

			//Perform the delete on the repo for the discovered artifact
			delete(identifier, service);

			try {
				return new ByteArrayInputStream(
						results.get(0).iterator().next().getValue().stringValue().getBytes("UTF-8"));
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		// If the real value couldn't be found, return an empty stream
		return new ByteArrayInputStream(null);
	}
	
	/**
	 * Put a new artifact in the database, removing any other with the same identifier and service.
	 * 
	 * @param identifier The unique identifier for th new artifact
	 * @param service The service which created the new artifact
	 * @param artifact The artifact to be stored in the database
	 */
	@POST
	@Path("/put")
	public void put(@QueryParam("identifier") String identifier,
			@QueryParam("service") String service, InputStream artifact) {
		
		//Delete any previous artifact
		delete(identifier, service);
		
		String identifierString = NAMESPACE + identifier;
		String serviceString = NAMESPACE + service;
		
		//Get the string representation of the artifact
		String artifactRep = InputStreamUtils.readStringStream(artifact);
		
		//Build a graph with the artifact as the subject and fields for both identifier and service
		ModelBuilder builder = new ModelBuilder();
		builder.setNamespace("eavp", NAMESPACE);
		builder.subject(identifierString).add("eavp:identifier", artifactRep);
		builder.subject(serviceString).add("eavp:service", artifactRep);
		
		repo.getConnection().add(builder.build());
	}
}
