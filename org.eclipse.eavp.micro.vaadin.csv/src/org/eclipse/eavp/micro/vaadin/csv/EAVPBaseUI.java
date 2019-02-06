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
package org.eclipse.eavp.micro.vaadin.csv;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Properties;
import java.io.File;
import java.io.IOException;

import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;

import org.apache.commons.io.FileUtils;
import org.dussan.vaadin.dcharts.DCharts;
import org.dussan.vaadin.dcharts.metadata.XYaxes;
import org.dussan.vaadin.dcharts.metadata.renderers.AxisRenderers;
import org.eclipse.eavp.micro.main.java.CSVGrid;
import org.springframework.context.annotation.Import;

import com.google.gson.Gson;
import com.vaadin.annotations.Push;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.navigator.PushStateNavigation;
import com.vaadin.server.DeploymentConfiguration;
import com.vaadin.server.ServiceException;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinResponse;
import com.vaadin.server.VaadinServlet;
import com.vaadin.server.VaadinServletService;
import com.vaadin.server.VaadinSession;
import com.vaadin.shared.communication.PushMode;
import com.vaadin.shared.ui.ui.Transport;
import com.vaadin.ui.Button;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.HorizontalLayout;

/**
 * This is the main entry point UI for the VAADIN visualization service. HTML
 * requests to the /ui/* space will be caught by init() and the UI will be
 * populated accordingly.
 * 
 * @author Robert Smith
 *
 */
@SuppressWarnings("serial")
@PushStateNavigation
@Push(transport = Transport.LONG_POLLING)
@Theme("mytheme")
public class EAVPBaseUI extends UI {
	
	/**
	 * The attribute name under which it is expected that data will be sent to the
	 * UI.
	 */
	static final String DATA_ATTRIBUTE_NAME = "org.eclipse.eavp.micro.vaadin.main.Data";
	
	/**
	 * The attribute name under which options will be sent to the UI.
	 */
	static final String OPTIONS_ATTRIBUTE_NAME = "org.eclipse.eavp.micro.vaadin.main.Options";

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.vaadin.ui.UI#init(com.vaadin.server.VaadinRequest)
	 */
	@Override
	protected void init(VaadinRequest vaadinRequest) {

		// Support Firefox and Chrome drag and drop
		setMobileHtml5DndEnabled(true);

		final UI self = this;

		// The base layout that everything in the UI will descend from.
		VerticalLayout layout = new VerticalLayout();

		if (vaadinRequest.getPathInfo() != null) {

			if (vaadinRequest.getPathInfo().startsWith("/plot")) {

				// Get the POSTed JSON data from the session.
				String json = (String) getSession().getAttribute(DATA_ATTRIBUTE_NAME);
				HashMap<String, String> optionMap = (HashMap<String, String>) getSession().getAttribute(OPTIONS_ATTRIBUTE_NAME);
				//layout.addComponent(new Label(optionMap.get(optionMap.keySet().iterator().next())));
				// Convert the JSON into a grid
				CSVGrid grid = new Gson().fromJson(json, CSVGrid.class);

				// Create a CSVGraph and add it to the layout
				CSVGraph chart = new CSVGraph(grid);
				
				chart.setSeriesDisplayOptions(optionMap);
				chart.pushChanges();
				chart.setSizeFull();
				chart.show();
				layout.addComponents(chart);

				// Layout for the additional charts
				VerticalLayout secondaryChartLayout = new VerticalLayout();

				// Add the special controls for qclimax plots
				if (vaadinRequest.getPathInfo().startsWith("/plot/qclimax")) {

					// the number of q values
					int numQs = 0;

					// Find the number of qs
					for (String col : grid.getColumnNames()) {

						// Find the Q data columns columns
						if (col.startsWith("Q") && col.endsWith(" Data")) {

							numQs++;
						}
					}

					// The layout for the q buttons
					HorizontalLayout qButtonLayout = new HorizontalLayout();

					// Add a button for each q value
					for (int i = 1; i <= numQs; i++) {

						// The list of columns for this q
						ArrayList<String> qCols = new ArrayList<String>();

						// Add each column for a function for the current q
						for (String col : grid.getColumnNames()) {

							if (col.startsWith("Q" + i + " ") && !col.endsWith(" Error")) {
								qCols.add(col);
							}
						}

						// The name for the error series
						ArrayList<String> errorSeries = new ArrayList<String>();
						errorSeries.add("Q" + i + " Error");

						// Create the button to graph the data, all functions, and the model for the
						// current q value
						Button qButton = new Button("Q" + i);

						qButton.addClickListener(new ClickListener() {

							/*
							 * (non-Javadoc)
							 * 
							 * @see com.vaadin.ui.Button.ClickListener#buttonClick(com.vaadin.ui.
							 * Button.ClickEvent)
							 */
							@Override
							public void buttonClick(ClickEvent event) {

								// Plot the current Q's columns against the energy, with a logarithmic scale
								chart.setData("Energy", qCols);
								chart.getLocalOptions().getAxes().getAxis(XYaxes.Y).setRenderer(AxisRenderers.LOG);
								chart.getLocalOptions().getAxes().getAxis(XYaxes.Y).getTickOptions()
										.setFormatString("%.3e");
								chart.createTicks(XYaxes.Y);
								chart.pushChanges();

								// A chart for the error graph
								CSVGraph errorChart = new CSVGraph(grid);
								errorChart.setSizeFull();
								errorChart.show();

								// Graph the errors against the energy
								errorChart.setData("Energy", errorSeries);

								// Set the error graph as the sole secondary graph
								secondaryChartLayout.removeAllComponents();
								secondaryChartLayout.addComponent(errorChart);
							}

						});

						// Add the q button to the layout
						qButtonLayout.addComponent(qButton);
					}

					// The layout for the parameter buttons
					VerticalLayout paramButtonLayout = new VerticalLayout();

					// The current row of buttons being populated.
					HorizontalLayout paramRowLayout = new HorizontalLayout();
					paramButtonLayout.addComponent(paramRowLayout);
					paramRowLayout.setMargin(false);

					// Number of buttons in the current row
					int currButtons = 0;

					// A layout for holding the parameter buttons
					for (String col : grid.getColumnNames()) {

						// Parameter names match the pattern functionNamne_parameterName
						if (col.contains("_") && !col.contains(" ")) {

							// Create a new row for the sixth button
							if (currButtons == 5) {

								// The new row is empty
								currButtons = 0;

								// Create and add the new row
								paramRowLayout = new HorizontalLayout();
								paramButtonLayout.addComponent(paramRowLayout);
								paramRowLayout.setMargin(false);
							}

							// Create a button for the parameter
							Button paramButton = new Button(col);
							paramRowLayout.addComponent(paramButton);

							// The parameter will be graphed when the button is clicked
							paramButton.addClickListener(new ClickListener() {

								/*
								 * (non-Javadoc)
								 * 
								 * @see com.vaadin.ui.Button.ClickListener#buttonClick(com.vaadin.ui.Button.
								 * ClickEvent)
								 */
								@Override
								public void buttonClick(ClickEvent event) {

									// Set the parameter series to be graphed against Q
									ArrayList<String> seriesList = new ArrayList<String>();
									seriesList.add(col);
									chart.setData("Q", seriesList);

									// Set the chart to a linear scale
									chart.getLocalOptions().getAxes().getAxis(XYaxes.Y)
											.setRenderer(AxisRenderers.LINEAR);

									//Set scientific notation on/off as approrpiate
									if (Math.abs((double) chart.getLocalOptions().getAxes().getAxis(XYaxes.Y)
											.getMax()) < 0.01) {
										chart.getLocalOptions().getAxes().getAxis(XYaxes.Y).getTickOptions()
												.setFormatString("%.3e");
									} else {
										chart.getLocalOptions().getAxes().getAxis(XYaxes.Y).getTickOptions()
												.setFormatString("%.3f");
									}

									chart.createTicks(XYaxes.Y);
									chart.pushChanges();

									// Remove the secondary graph
									secondaryChartLayout.removeAllComponents();
								}
							});
						}
					}

					// Set up the layouts and components
					layout.addComponent(secondaryChartLayout);
					layout.addComponent(qButtonLayout);
					layout.addComponent(paramButtonLayout);
					paramButtonLayout.setMargin(false);
				}
				
				setContent(layout);
			} else {
				layout.addComponent(
						new Label(vaadinRequest.getPathInfo() + " not found"));
				setContent(layout);
			}
		} else {
			layout.addComponent(new Label("Request not found."));
		}

		
		// Delete the temporary zip file and make another for use
		File toBeDeleted = new File("output/temp/zip");
		try {
			FileUtils.deleteDirectory(toBeDeleted);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		new File("output/temp/zip").mkdirs();
	}

	/**
	 * The servlet for containing the VAADIN microservice. It must run on both
	 * "ui/*" and "VAADIN/*" in order for VAADIN to be able to access some of its
	 * own internal resources.
	 * 
	 * @author Robert Smith
	 *
	 */
	@WebServlet(urlPatterns = { "/ui/*", "/VAADIN/*" }, name = "EAVPUIServlet", asyncSupported = true, initParams = { @WebInitParam(name = "serviceUrl", value="EAVP") })
	@VaadinServletConfiguration(ui = EAVPBaseUI.class, productionMode = false)
	public static class MyUIServlet extends VaadinServlet {

		/*
		 * (non-Javadoc)
		 * 
		 * @see com.vaadin.server.VaadinServlet#createServletService(com.vaadin.
		 * server.DeploymentConfiguration)
		 */
		@Override
		protected VaadinServletService createServletService(DeploymentConfiguration deploymentConfiguration)
				throws ServiceException {

			// Create a custom service that will process requests
			VaadinServletService service = new VaadinServletService(this, deploymentConfiguration) {

				/*
				 * (non-Javadoc)
				 * 
				 * @see com.vaadin.server.VaadinService#requestEnd(com.vaadin.server.
				 * VaadinRequest, com.vaadin.server.VaadinResponse,
				 * com.vaadin.server.VaadinSession)
				 */
				@Override
				public void requestEnd(VaadinRequest request, VaadinResponse response, VaadinSession session) {
					super.requestEnd(request, response, session);

					// If a request contains POST data, move it from the request
					// into the session, in order to avoid a bug whereby the
					// original bootstrapping of VAADIN fails to copy POST data
					// on to the actual init() request
					if (request.getParameterMap().keySet().contains(DATA_ATTRIBUTE_NAME)) {
						session.setAttribute(DATA_ATTRIBUTE_NAME, request.getParameter(DATA_ATTRIBUTE_NAME));
					}
					
					//Save all options in the same way as a map.
					if (request.getParameterMap().keySet().contains(OPTIONS_ATTRIBUTE_NAME)) {
						
						//Map of keys to values
						HashMap<String, String> options = new HashMap<String, String>();
						
						//Get the keys and values, delimited by :, from the request
						String optionString = request.getParameter(OPTIONS_ATTRIBUTE_NAME);
						String[] tokens = optionString.split(":");
						
						//Put each option of the form "option=value" into the map
						for(int i = 0; i < tokens.length; i++) {
							
							options.put(tokens[i].substring(0, tokens[i].indexOf("=")), tokens[i].substring(tokens[i].indexOf("=") + 1));
						}
						
						session.setAttribute(OPTIONS_ATTRIBUTE_NAME, options);
					}
				}
			};
			service.init();
			return service;
		}
	}
	
}
