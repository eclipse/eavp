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
import javax.ws.rs.GET;
import javax.ws.rs.POST;
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
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;

/**
 * This class is the main microservice in EAVP. It handles incoming file
 * requests and produces html to display the correct visualization service.
 * 
 * @author Robert Smith
 *
 */
@Path("/api")
public class VisualizationService {
	
	//TODO THis should come in through HATEOS
	private final String CSV_SERVICE_URL = "http://localhost:8080";
	private final String BASE_SERVLET_NAME = "/org.eclipse.eavp.micro-1.0-SNAPSHOT";
	private final String CSV_SERVLET_NAME = "/org.eclipse.eavp.micro.vaadin.csv-1.0-SNAPSHOT";

	/**
	 * The next ID number to assign to a returned html div.
	 */
	private int id = 0;

	/**
	 * The default constructor.
	 */
	public VisualizationService() {
	}

	/**
	 * Helper function to accept GETs to the service selector.
	 * 
	 * @param name
	 *            The name of the file being visualized.
	 * @param path
	 *            The file path of the local file to be visualized if the parameter
	 *            input is not provided.
	 * @param input
	 *            The contents of the file being visualized.
	 * @return HTML output displaying the EAVP visualization for the automatically
	 *         detected service used to visualize the given file,
	 **/
	@GET
	@Produces(MediaType.APPLICATION_OCTET_STREAM)
	public InputStream getSelectService(@QueryParam("identifier") String identifier, @QueryParam("filename") String name, @QueryParam("filepath") String path,
			InputStream input) {
		return selectService(identifier, name, path, input);
	}

	/**
	 * Helper function to accept POSTs to the service selector.
	 * 
	 * @param name
	 *            The name of the file being visualized.
	 * @param path
	 *            The file path of the local file to be visualized if the parameter
	 *            input is not provided.
	 * @param input
	 *            The contents of the file being visualized.
	 * @return HTML output displaying the EAVP visualization for the automatically
	 *         detected service used to visualize the given file,
	 **/
	@POST
	@Consumes(MediaType.APPLICATION_OCTET_STREAM)
	@Produces(MediaType.APPLICATION_OCTET_STREAM)
	public InputStream postSelectService(@QueryParam("identifier") String identifier, @QueryParam("filename") String name, @QueryParam("filepath") String path,
			InputStream input) {
		return selectService(identifier, name, path, input);
	}

	/**
	 * Select and return the output from the appropriate service, given the file
	 * type and contents.
	 * 
	 * @param name
	 *            The name of the file being visualized.
	 * @param path
	 *            The file path of the local file to be visualized if the parameter
	 *            input is not provided.
	 * @param input
	 *            The contents of the file being visualized.
	 * @return HTML output displaying the EAVP visualization for the automatically
	 *         detected service used to visualize the given file, or an error
	 *         message if the file could not be visualized.
	 */
	public InputStream selectService(@QueryParam("identifier") String identifier, @QueryParam("filename") String name, @QueryParam("filepath") String path,
			InputStream input) {
		
		// csv, dat, and txt files should be handled by the plotting service.
		if (name.endsWith("csv") || name.endsWith(".dat") || name.endsWith(".txt")) {
			return plot(identifier, name, path, null, null, "default", input);
		}

		try {
			return new ByteArrayInputStream(("Could not render file named \"" + name + "\" with contents: " + input).getBytes("UTF-8"));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new ByteArrayInputStream(null);
		}
	}

	/**
	 * Select the service to use to display this qclimax specific file.
	 * 
	 * @param name
	 *            The name of the file being visualized.
	 * @param path
	 *            The file path of the local file to be visualized if the parameter
	 *            input is not provided.
	 * @param input
	 *            The contents of the file being visualized.
	 * @return HTML output displaying the EAVP visualization for the automatically
	 *         detected service used to visualize the given file,
	 **/
	@GET
	@Path("/qclimax")
	@Produces(MediaType.APPLICATION_OCTET_STREAM)
	public InputStream selectServiceQClimax(@QueryParam("identifier") String identifier, @QueryParam("filename") String name, @QueryParam("filepath") String path,
			InputStream input) {

		// csv, dat, and txt files should be handled by the plotting service.
		if (name.endsWith("csv") || name.endsWith(".dat") || name.endsWith(".txt")) {
			return plot(identifier, name, path, null, null, "qclimax", input);
		}

		try {
			return new ByteArrayInputStream(("Could not render file named \"" + name + "\" with contents: " + input).getBytes("UTF-8"));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new ByteArrayInputStream(null);
		}
	}

	/**
	 * The Vaadin plotting service using DCharts. Returns an html <div> that will
	 * contain the plot for the given file.
	 * 
	 * Note that, after placing the <div> in an html document, it is neccesary to
	 * run:
	 * 
	 * var scripts = eavp_div.getElementsByTagName('script') for (var ix = 0; ix <
	 * scripts.length; ix++) { eval(scripts[ix].text); }
	 * 
	 * This will submit the hidden form that posts data to the Vaadin UI.
	 * 
	 * @param name
	 *            The name of the file to be visualized.
	 * @param path
	 *            The url encoded path to a local file to be visualized if input is
	 *            not provided.
	 * @param showLineFalse
	 *            A list of series names where the ShowLine option should be set to
	 *            false
	 * @param showMarkersFalse A list of series names where the ShowMarkers option should be set to false
	 * @param type
	 *            The type of the plot, which determines which extra domain specific
	 *            features are shown. Valid values are "default" and "qclimax"
	 * @param input
	 *            The contents of the file to be visualized.
	 * @return
	 */
	@POST
	@Path("/plot/{type}")
	@Consumes(MediaType.APPLICATION_OCTET_STREAM)
	@Produces(MediaType.APPLICATION_OCTET_STREAM)
	public InputStream plot(@QueryParam("identifier") String identifier, @QueryParam("filename") String name, @QueryParam("filepath") String path,
			@QueryParam("showlinefalse") String showLineFalse, @QueryParam("showmarkersfalse") String showMarkersFalse, @PathParam("type") String type, InputStream inputStream) {
		
		//String input = InputStreamUtils.readStringStream(inputStream);
		//System.out.println("INPUT STRING " + input);
		String gridJSON = getPlotJSON(name, path, inputStream);
		
		if ("default".equals(type)) {

			String html =

					// A hidden form that contains the JSON representation of the file
					// and will submit it to VAADIN. Each form/iframe pair will have a
					// unique id name to differentiate them from other EAVP divs.
					"<form action=\"" + CSV_SERVICE_URL + CSV_SERVLET_NAME + "/ui/plot?identifier=" + identifier + "\" id=\"eavp-hidden-form-"
							+ id + "\" method=\"post\" target=\"eavp-frame-" + id + "\">\n"

							// The hidden input field which will contain the file JSON
							+ "<input type=\"hidden\" name=\"org.eclipse.eavp.micro.vaadin.main.Data\" value=\""

							// Encode the JSON for html embedding
							+ gridJSON.replace("\"", "&quot;") + "\"><br>\n";
			
			//Map of series names to options
			HashMap<String, ArrayList<String>> optionMap = new HashMap<String, ArrayList<String>>();

			//Add an showline false series to the map
			if(showLineFalse != null) {
				
				String[] tokens = URLDecoder.decode(showLineFalse).split(",");
				
				for(int i = 0; i < tokens.length; i++) {
					if(!optionMap.containsKey(tokens[i])) {
						optionMap.put(tokens[i], new ArrayList<String>());
					}
					
					optionMap.get(tokens[i]).add("showlinefalse");
				}
			}
			
			//Add an showmarkers false series to the map
			if(showMarkersFalse != null) {
				
				String[] tokens = URLDecoder.decode(showMarkersFalse).split(",");
				
				for(int i = 0; i < tokens.length; i++) {
					if(!optionMap.containsKey(tokens[i])) {
						optionMap.put(tokens[i], new ArrayList<String>());
					}
					
					optionMap.get(tokens[i]).add("showmarkersfalse");
				}
			}
			
			if(!optionMap.isEmpty()) {
				
				// A hidden input field which will contain the options
				html += "<input type=\"hidden\" name=\"org.eclipse.eavp.micro.vaadin.main.Options\" value=\"";
				
				//Add each series to the html
				for (String seriesName : optionMap.keySet()) {
					
					//Start with the series name and an equals sign
					html += seriesName + "=";
					
					ArrayList<String> options = optionMap.get(seriesName);
					
					//Add each optionn from the map, seperated by commas
					if(options.contains("showlinefalse")) {
						html += "showline=false,";
					}
					if(options.contains("showmarkersfalse")) {
						html += "showmarkers=false,";
					}
					
					//Finish the series with a :
					html += ":";
				}
				
//				String[] showPathFalseSeries = showLineFalse.split(",");
//				
//				//Add an option for each series with no path
//				for(int i = 0; i < showPathFalseSeries.length; i++) {
//				
//					html += showPathFalseSeries[i] + "=showline=false:";
//				}
				
				html += "\"><br>\n";

			}
			

			html += "</form>"

					// The iframe containing the VAADIN UI.
					+ "<iframe style=\"width:100%; height:100%\" name=\"eavp-frame-" + id + "\"></iframe>"

					// A script that will programatically submit the form to the //
					// iframe.
					+ "<script language=\"javascript\">\n" + "        document.getElementById(\"eavp-hidden-form-" + id
					+ "\").submit();\n" + "    </script>";
			
			try {
				return new ByteArrayInputStream(html.getBytes("UTF-8"));
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return new ByteArrayInputStream(null);
			}

		} else if ("qclimax".equals(type)) {
			try {
				return new ByteArrayInputStream(("<form action=\"" + CSV_SERVICE_URL + CSV_SERVLET_NAME
						+ "/ui/plot/qclimax\" id=\"eavp-hidden-form-" + id + "\" method=\"post\" target=\"eavp-frame-"
						+ id + "\">\n"

						// The hidden input field which will contain the file JSON
						+ "<input type=\"hidden\" name=\"org.eclipse.eavp.micro.vaadin.main.Data\" value=\""

						// Encode the JSON for html embedding
						+ gridJSON.replace("\"", "&quot;") + "\"><br>\n" + "</form>"

						// The iframe containing the VAADIN UI.
						+ "<iframe style=\"width:100%; height:100%\" name=\"eavp-frame-" + id + "\"></iframe>"

						// A script that will programatically submit the form to the //
						// iframe.
						+ "<script language=\"javascript\">\n" + "        document.getElementById(\"eavp-hidden-form-" + id
						+ "\").submit();\n" + "    </script>").getBytes("UTF-8"));
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return new ByteArrayInputStream(null);
			}
		} else {
			return new ByteArrayInputStream(null);
		}
	}
	// /**
	// * The Vaadin plotting service using DCharts. Returns an html <div> that
	// * will contain the plot for the given file.
	// *
	// * Note that, after placing the <div> in an html document, it is neccesary
	// * to run:
	// *
	// * var scripts = eavp_div.getElementsByTagName('script') for (var ix = 0; ix
	// * < scripts.length; ix++) { eval(scripts[ix].text); }
	// *
	// * This will submit the hidden form that posts data to the Vaadin UI.
	// *
	// * @param name
	// * The name of the file to be visualized.
	// * @param path
	// * The url encoded path to a local file to be visualized if input
	// * is not provided.
	// * @param input
	// * The contents of the file to be visualized.
	// * @return
	// */
	// @GET
	// @Path("/plot/qclimax")
	// @Consumes(MediaType.TEXT_PLAIN)
	// @Produces(MediaType.TEXT_HTML)
	// public String plotQClimax(@QueryParam("filename") String name,
	// @QueryParam("filepath") String path, String input) {
	//
	// String gridJSON = getPlotJSON(name, path, input);
	//
	// return
	//
	// // A hidden form that contains the JSON representation of the file
	// // and will submit it to VAADIN. Each form/iframe pair will have a
	// // unique id name to differentiate them from other EAVP divs.
	// "<form action=\"http://" + System.getenv("ICEMAN_host") +
	// ":8913/ui/plot/qclimax\" id=\"eavp-hidden-form-"
	// + id + "\" method=\"post\" target=\"eavp-frame-" + id + "\">\n"
	//
	// // The hidden input field which will contain the file JSON
	// + "<input type=\"hidden\" name=\"org.eclipse.eavp.micro.vaadin.main.Data\"
	// value=\""
	//
	// // Encode the JSON for html embedding
	// + gridJSON.replace("\"", "&quot;") + "\"><br>\n" + "</form>"
	//
	// // The iframe containing the VAADIN UI.
	// + "<iframe style=\"width:100%; height:100%\" name=\"eavp-frame-"
	// + id + "\"></iframe>"
	//
	// // A script that will programatically submit the form to the //
	// // iframe.
	// + "<script language=\"javascript\">\n"
	// + " document.getElementById(\"eavp-hidden-form-" + id
	// + "\").submit();\n" + " </script>";
	// }

	/**
	 * Get the json representation for the data of a CSV plot.
	 * 
	 * @param name
	 *            The name of the file to be visualized.
	 * @param path
	 *            The url encoded path to a local file to be visualized if input is
	 *            not provided.
	 * @param input
	 *            The contents of the file to be visualized
	 * 
	 * @return The String representation of the json for the input
	 */
	private String getPlotJSON(String name, String path, InputStream input) {
		
		// The string containing the json representation of the csvgrid
		String gridJSON = "";
		
//		// Read file if no POST data
//		if ((input == null || "".equals(input)) && path != null) {
//			File file = new File(URLDecoder.decode(path));
//			try {
//				input = new String(Files.readAllBytes(file.toPath()));
//			} catch (IOException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		}

		try {

			// We're connecting to ourselves, so disable security
			HttpClient httpclient = HttpClients.custom().setSSLSocketFactory(new SSLConnectionSocketFactory(SSLContextBuilder.create().loadTrustMaterial(new TrustSelfSignedStrategy()).build(), new NoopHostnameVerifier())).build();

			URIBuilder builder = new URIBuilder(CSV_SERVICE_URL + BASE_SERVLET_NAME + "/fileio/csv/csvgrid/fulljson");
			builder.setParameter("filename", name);

			HttpPost httppost = new HttpPost(builder.build());

//			// Request parameters and other properties.
//			List<NameValuePair> params = new ArrayList<NameValuePair>(2);
//			// params.add(new BasicNameValuePair("filename", name));
//			params.add(new BasicNameValuePair("input", input));
//			try {
//				httppost.setEntity(new UrlEncodedFormEntity(params, "UTF-8"));
//			} catch (UnsupportedEncodingException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
			httppost.setEntity(new InputStreamEntity(input));

			// Execute and get the response.
			HttpResponse response;
			try {

				response = httpclient.execute(httppost);

				HttpEntity entity = response.getEntity();

				if (entity != null) {
					InputStream instream = entity.getContent();
					try {
						BufferedReader reader = new BufferedReader(new InputStreamReader(instream));
						String line = reader.readLine();
						while (line != null) {
							gridJSON += line;
							line = reader.readLine();
						}
					} finally {
						instream.close();
					}
				}
			} catch (ClientProtocolException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (URISyntaxException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (KeyManagementException | NoSuchAlgorithmException | KeyStoreException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}

		// Get the next id number

		id++;
		
		return gridJSON;
	}
}
