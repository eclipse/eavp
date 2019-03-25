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

package org.eclipse.eavp.micro.openlayers;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.eclipse.eavp.micro.main.java.InputStreamUtils;
import org.eclipse.rdf4j.model.util.ModelBuilder;
import org.eclipse.rdf4j.query.BindingSet;
import org.eclipse.rdf4j.query.QueryResults;
import org.eclipse.rdf4j.repository.Repository;
import org.eclipse.rdf4j.repository.http.HTTPRepository;
import org.eclipse.rdf4j.repository.util.Repositories;

/**
 * This class is the microservice for communicating with the RDF4J rdf store.
 * 
 * @author Robert Smith
 *
 */
@Path("/openlayers")
public class OpenLayersService {

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
	public OpenLayersService() {
	}
	
	@GET
	@Path("/map")
	@Produces(MediaType.APPLICATION_OCTET_STREAM)
	public InputStream delete(@QueryParam("map") String service) {
		return new ByteArrayInputStream(("<script src=\"https://cdn.rawgit.com/openlayers/openlayers.github.io/master/en/v5.3.0/build/ol.js\"></script>\n" + 
				"    <div id=\"map\" class=\"map\"></div>\n" + 
				"    <script type=\"text/javascript\">\n" + 
				"      var map = new ol.Map({\n" + 
				"        target: 'map',\n" + 
				"        layers: [\n" + 
				"          new ol.layer.Tile({\n" + 
				"            source: new ol.source.OSM()\n" + 
				"          })\n" + 
				"        ],\n" + 
				"        view: new ol.View({\n" + 
				"          center: ol.proj.fromLonLat([37.41, 8.82]),\n" + 
				"          zoom: 4\n" + 
				"        })\n" + 
				"      });\n" + 
				"    </script>"
				+ "<script>"
				+ "var script = document.createElement('script');\n" + 
				"script.type = 'text/javascript';\n" + 
				"script.src = 'https://cdn.rawgit.com/openlayers/openlayers.github.io/master/en/v5.3.0/build/ol.js';    \n" + 
				"\n" + 
				"document.getElementsByTagName('head')[0].appendChild(script);"
				+ "</script>").getBytes()); 
	}
}
