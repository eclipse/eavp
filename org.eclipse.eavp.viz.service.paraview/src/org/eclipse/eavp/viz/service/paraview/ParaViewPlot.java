/*******************************************************************************
 * Copyright (c) 2015 UT-Battelle, LLC.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   Jordan Deyton - Initial API and implementation and/or initial documentation
 *   
 *******************************************************************************/
package org.eclipse.eavp.viz.service.paraview;

import java.io.File;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.URI;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.eavp.viz.service.AbstractSeries;
import org.eclipse.eavp.viz.service.IRenderElementHolder;
import org.eclipse.eavp.viz.service.ISeries;
import org.eclipse.eavp.viz.service.connections.ConnectionPlot;
import org.eclipse.eavp.viz.service.connections.ConnectionPlotComposite;
import org.eclipse.eavp.viz.service.connections.ConnectionState;
import org.eclipse.eavp.viz.service.connections.IVizConnection;
import org.eclipse.eavp.viz.service.paraview.connections.ParaViewConnection;
import org.eclipse.eavp.viz.service.paraview.proxy.IParaViewProxy;
import org.eclipse.eavp.viz.service.paraview.web.IParaViewWebClient;
import org.eclipse.january.geometry.Geometry;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IFileEditorInput;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.browser.IWebBrowser;
import org.eclipse.ui.part.MultiPageEditorPart;
import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.JsonArray;
import com.google.gson.JsonPrimitive;
import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;

/**
 * This class is responsible for embedding ParaView-supported graphics inside
 * client {@link Composite}s.
 * <p>
 * Instances of this class should not be created manually. Instead, a plot
 * should be created via {@link ParaViewVizService#createPlot(URI)}.
 * </p>
 *
 * @see ParaViewVizService
 * 
 * @author Jordan Deyton
 *
 */
public class ParaViewPlot extends ConnectionPlot<IParaViewWebClient> {

	/**
	 * Whether or not the data has been loaded.
	 */
	private boolean loaded = false;
	/**
	 * Whether or not the data is currently being loaded.
	 */
	private boolean loading = false;

	/**
	 * A lock used to synchronize load requests, as the data should only be
	 * reloaded one at a time, and data should not be reloaded simultaneously.
	 */
	private final Lock loadLock = new ReentrantLock();

	/**
	 * Logger for handling event messages and other information.
	 */
	private static final Logger logger = LoggerFactory
			.getLogger(ParaViewPlot.class);

	/**
	 * The plot composite in which this plot is being displayed.
	 */
	private ParaViewPlotComposite plotComposite;

	/**
	 * A map containing all categories and types (dependent series), keyed on
	 * the categories. This is populated based on the {@link #proxy} at load
	 * time.
	 */
	private final Map<String, List<ISeries>> plotTypes = new HashMap<String, List<ISeries>>();

	/**
	 * The proxy associated with the current URI. It handles messages concerning
	 * the file going to and from the remote ParaView server.
	 * <p>
	 * This should be re-created whenever the data source URI is set. If it
	 * cannot be created, then the URI cannot be rendered via ParaView.
	 * </p>
	 */
	private IParaViewProxy proxy;

	/**
	 * A handle to the viz service that created this plot.
	 */
	private final ParaViewVizService vizService;

	/**
	 * The default constructor.
	 * 
	 * @param vizService
	 *            The service used to create this plot.
	 */
	public ParaViewPlot(ParaViewVizService vizService) {
		this.vizService = vizService;
	}

	/**
	 * Gets the current, normalized zoom based on the current scroll count.
	 * 
	 * @param y
	 *            The current scroll count. Should be the current or recent
	 *            value of {@link #scrollCount}.
	 * @return A normalized zoom value between 0 and 1.
	 */
	private double getNormalizedZoom(double y) {
		return Math.atan(0.005 * y) / Math.PI + 0.5;
	}

	/**
	 * Adds an independent series based on the provided metadata in the proxy.
	 * This attempts to add a series based on the proxy's timesteps or on the
	 * point 0.0 if no timesteps are available.
	 */
	private void addIndependentSeries() {

		// Get the timesteps from the proxy. If there are no times, simply add a
		// single point 0.0.
		final List<Double> times = proxy.getTimesteps();
		if (times.isEmpty()) {
			times.add(0.0);
		}

		// Create a new series wrapping the time data.
		ISeries independentSeries = new AbstractSeries() {
			@Override
			public double[] getBounds() {
				double min = times.get(0);
				double max = times.get(times.size() - 1);
				// This is what the docs specify...
				return new double[] { min, max - min };
			}

			@Override
			public Object[] getDataPoints() {
				Double[] timeArray = new Double[times.size()];
				return times.toArray(timeArray);
			}

			@Override
			public String getLabel() {
				return "Time";
			}
		};

		// Set the independent series.
		super.setIndependentSeries(independentSeries);

		return;
	}

	/**
	 * Adds a new category of series to the {@link #plotTypes} map based on the
	 * specified category name and list of plot types.
	 * 
	 * @param category
	 * @param features
	 */
	private void addPlotCategory(String category, Set<String> features) {
		if (!features.isEmpty()) {
			// Add the category to the map.
			List<ISeries> seriesList = new ArrayList<ISeries>(features.size());
			plotTypes.put(category, seriesList);

			// Create a new series for each type.
			final String categoryRef = category;
			for (String type : features) {
				final String typeRef = type;

				// Add a new series with the category and label set.
				seriesList.add(new AbstractSeries() {
					@Override
					public String getCategory() {
						return categoryRef;
					}

					@Override
					public String getLabel() {
						return typeRef;
					}
				});
			}
		}
		return;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.eavp.viz.service.connections.ConnectionPlot#
	 * connectionStateChanged(org.eclipse.eavp.viz.service.connections.
	 * IVizConnection, org.eclipse.eavp.viz.service.connections.ConnectionState,
	 * java.lang.String)
	 */
	@Override
	public void connectionStateChanged(
			IVizConnection<IParaViewWebClient> connection,
			ConnectionState state, String message) {
		if (state == ConnectionState.Connected) {
			load();
		}
		return;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.eavp.viz.service.connections.ConnectionPlot#
	 * createPlotComposite(org.eclipse.swt.widgets.Composite)
	 */
	@Override
	protected ConnectionPlotComposite<IParaViewWebClient> createPlotComposite(
			Composite parent) {
		plotComposite = new ParaViewPlotComposite(parent, SWT.NONE);
		return plotComposite;
	}

	/**
	 * Uses the viz service's proxy factory to create and open a proxy from the
	 * current data source URI.
	 * 
	 * @return A new proxy.
	 * @throws Exception
	 *             If there was an error either opening the proxy over the
	 *             curret connection or if the file simply could not be opened.
	 */
	private IParaViewProxy createProxy() throws Exception {
		IParaViewProxy newProxy = null;

		// Attempt to create the IParaViewProxy. This will throw an exception if
		// the URI is null or its extension is invalid.
		URI uri = getDataSource();
		IParaViewProxy proxy = vizService.getProxyFactory().createProxy(uri);

		// Attempt to open the file. Wait until the process completes.
		if (proxy.open(getConnection()).get()) {
			newProxy = proxy;
		} else {
			throw new IllegalArgumentException("ParaViewPlot error: "
					+ "Cannot open the file \"" + uri.getPath()
					+ "\" using the existing connection.");
		}

		return newProxy;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.eavp.viz.service.AbstractPlot#getCategories()
	 */
	@Override
	public List<String> getCategories() {
		return new ArrayList<String>(plotTypes.keySet());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.eavp.viz.service.AbstractPlot#getDependentSeries(java.lang.
	 * String)
	 */
	@Override
	public List<ISeries> getDependentSeries(String category) {
		// Return a copy of the plot type series if the category is valid.
		List<ISeries> series = plotTypes.get(category);
		if (series != null) {
			series = new ArrayList<ISeries>(series);
		}
		return series;
	}

	/**
	 * Gets the proxy associated with the current URI. It handles messages
	 * concerning the file going to and from the remote ParaView server.
	 * <p>
	 * This is re-created whenever the data source URI is changed. If it cannot
	 * be created, then the URI cannot be rendered via ParaView.
	 * </p>
	 * 
	 * @return The current proxy, or {@code null} if the current URI/connection
	 *         cannot be used to create a proxy.
	 */
	protected IParaViewProxy getParaViewProxy() {
		return proxy;
	}

	/**
	 * Gets whether or not data has been loaded from the data source.
	 * 
	 * @return True if data has been loaded, false otherwise.
	 */
	public boolean isLoaded() {
		return loaded;
	}

	/**
	 * Loads the plot categories and types (dependent series) as well as the
	 * time/cycle data (independent series) from the data source using the viz
	 * connection's widget. Work is performed on a separate thread.
	 */
	private void load() {

		// Setting loaded to false effectively invalidates the plot (meaning the
		// data is stale).
		loaded = false;

		loadLock.lock();
		try {

			final URI uri = getDataSource();
			final IVizConnection<IParaViewWebClient> connection = getConnection();

			// If possible, try loading the data from the file.
			if (!loading && uri != null && connection != null
					&& connection.getState() == ConnectionState.Connected) {
				loading = true;

				// Remove all data.
				plotTypes.clear();
				proxy = null;

				Job loadJob = new Job("Loading ParaView Plot") {
					@Override
					protected IStatus run(IProgressMonitor monitor) {

						// Set the initial task name.
						monitor.beginTask("Loading ParaView Plot", 100);

						// Try to create the proxy.
						try {
							proxy = createProxy();
						} catch (Exception e) {
							// The proxy could not be opened.
							return new Status(Status.ERROR,
									"org.eclipse.eavp.viz.service", 1,
									"ParaViewPlot could not load the file.", e);
						}
						monitor.worked(30);

						// Set the default independent series.
						addIndependentSeries();
						monitor.worked(10);

						// Load all categories and features into series.
						Set<String> categories = proxy.getFeatureCategories();
						monitor.worked(10);
						if (!categories.isEmpty()) {
							int increment = 40 / categories.size();
							// Add all categories and features to the map.
							for (String category : proxy
									.getFeatureCategories()) {
								addPlotCategory(category,
										proxy.getFeatures(category));
								monitor.worked(increment);
							}
						} else {
							monitor.worked(40);
						}

						// Set the default feature.
						setInitialFeature();
						monitor.worked(10);

						// It is loaded, although it may not have any data.
						loadLock.lock();
						try {
							loading = false;
							loaded = true;
						} finally {
							loadLock.unlock();
						}

						// Notify the listeners that loading has completed.
						notifyPlotListeners("loaded", "true");
						monitor.worked(10);

						// Return a status.
						return new Status(Status.OK,
								"org.eclipse.eavp.viz.service", 1,
								"ParaView Plot Loaded", null);
					}
				};
				loadJob.schedule();
			}
		} finally {
			loadLock.unlock();
		}

		return;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.eavp.viz.service.connections.ConnectionPlot#setConnection(org
	 * .eclipse.eavp.viz.service.connections.IVizConnection)
	 */
	@Override
	public boolean setConnection(IVizConnection<IParaViewWebClient> connection)
			throws Exception {
		boolean changed = super.setConnection(connection);
		// If the connection has changed, we must re-load the data.
		if (changed) {
			load();
		}
		return changed;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.eavp.viz.service.connections.ConnectionPlot#setDataSource(
	 * java.net.URI)
	 */
	@Override
	public boolean setDataSource(URI uri) throws Exception {
		boolean changed = super.setDataSource(uri);
		// If the data source has changed, we must re-load the data.
		if (changed) {
			load();
		}
		return changed;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.eavp.viz.service.AbstractPlot#setIndependentSeries(org.
	 * eclipse.eavp.viz.service.ISeries)
	 */
	@Override
	public void setIndependentSeries(ISeries series) {
		// We do not allow the client code to set the independent series.
	}

	/**
	 * Finds the first dependent series, marking it as enabled. The priority is
	 * placed on the mesh, but if no mesh is available, other categories will be
	 * searched.
	 */
	private void setInitialFeature() {
		// Find the first available series and enable it.
		for (List<ISeries> seriesList : plotTypes.values()) {
			if (!seriesList.isEmpty()) {
				seriesList.get(0).setEnabled(true);
				break;
			}
		}
		return;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.eavp.viz.service.IPlot#createAdditionalPage(org.eclipse.ui.
	 * part.MultiPageEditorPart, org.eclipse.ui.IFileEditorInput, int)
	 */
	@Override
	public String createAdditionalPage(MultiPageEditorPart parent,
			IFileEditorInput file, int pageNum) {
		// No additional pages, so nothing to do
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.eavp.viz.service.AbstractPlot#getCustomActions()
	 */
	@Override
	public ArrayList<Action> getCustomActions() {

		// Get the plus icon image
		Bundle bundle = FrameworkUtil.getBundle(ParaViewPlot.class);
		String separator = System.getProperty("file.separator");
		URL inImageURL = bundle.getEntry("icons" + separator + "add.png");
		if (inImageURL == null) {
			inImageURL = getClass()
					.getResource("icons" + separator + "add.png");
		}
		if (inImageURL == null) {
			Path inImagePath = new Path(separator + "icons"
					+ separator + "add.png");
			inImageURL = FileLocator.find(bundle, inImagePath, null);
		}
		ImageDescriptor inDescriptor = ImageDescriptor
				.createFromURL(inImageURL);

		// The action to zoom the camera in
		Action zoomIn = new Action("Zoom In", inDescriptor) {
			@Override
			public void run() {

				// The state for the left mouse button and control key being
				// down, to create a zoom
				boolean[] state = new boolean[] { true, false, false, false,
						true, false, false };

				// The state with all buttons up, for the final mouse release at
				// the end of the action
				boolean[] endState = new boolean[] { false, false, false, false,
						false, false, false };

				// Simulate a mouse drag to zoom the camera
				connection.getWidget().event(proxy.getViewId(), 0,
						getNormalizedZoom(0), "down", state);
				connection.getWidget().event(proxy.getViewId(), 0,
						getNormalizedZoom(-50), "move", state);
				connection.getWidget().event(proxy.getViewId(), 0,
						getNormalizedZoom(-50), "up", endState);

				// Redraw the composite from the new camera position
				plotComposite.refresh();

			}
		};

		// Get the minus icon image
		URL outImageURL = bundle.getEntry("icons" + separator + "complement.gif");
		if (outImageURL == null) {
			outImageURL = getClass()
					.getResource("icons" + separator + "complement.gif");
		}
		if (outImageURL == null) {
			Path outImagePath = new Path(separator + "icons"
					+ separator + "complement.gif");
			outImageURL = FileLocator.find(bundle, outImagePath, null);
		}
		ImageDescriptor outDescriptor = ImageDescriptor
				.createFromURL(outImageURL);

		// The action to zoom the camera out
		Action zoomOut = new Action("Zoom Out", outDescriptor) {
			@Override
			public void run() {

				// The state for the left mouse button and control key being
				// down, to create a zoom
				boolean[] state = new boolean[] { true, false, false, false,
						true, false, false };

				// The state with all buttons up, for the final mouse release at
				// the end of the action
				boolean[] endState = new boolean[] { false, false, false, false,
						false, false, false };

				// Simulate a mouse drag to zoom the camera
				connection.getWidget().event(proxy.getViewId(), 0,
						getNormalizedZoom(0), "down", state);
				connection.getWidget().event(proxy.getViewId(), 0,
						getNormalizedZoom(50), "move", state);
				connection.getWidget().event(proxy.getViewId(), 0,
						getNormalizedZoom(50), "up", endState);

				// Redraw the composite from the new camera position
				plotComposite.refresh();

			}
		};

		// Get the reset icon image
		URL resetImageURL = bundle.getEntry("icons" + separator + "iu_update_obj.gif");
		if (resetImageURL == null) {
			resetImageURL = getClass()
					.getResource("icons" + separator + "iu_update_obj.gif");
		}
		if (resetImageURL == null) {
			Path resetImagePath = new Path(
					separator + "icons" + separator
							+ "iu_update_obj.gif");
			resetImageURL = FileLocator.find(bundle, resetImagePath, null);
		}
		ImageDescriptor resetDescriptor = ImageDescriptor
				.createFromURL(resetImageURL);

		// The action to zoom the camera in
		Action resetCamera = new Action("Reset Camera", resetDescriptor) {
			@Override
			public void run() {

				// The array of arguments to the camera update function
				JsonArray args = new JsonArray();

				// Add the view id
				JsonPrimitive view = new JsonPrimitive(proxy.getViewId());
				args.add(view);

				// Create a JsonPrimitive representation of zero for repeated
				// use
				JsonPrimitive zero = new JsonPrimitive(0);

				// Set the camera's focal point to the origin
				JsonArray focalPoint = new JsonArray();
				focalPoint.add(zero);
				focalPoint.add(zero);
				focalPoint.add(zero);
				args.add(focalPoint);

				// Set the camera to have the Y axis pointing upwards
				JsonArray viewUp = new JsonArray();
				viewUp.add(zero);
				viewUp.add(new JsonPrimitive(1));
				viewUp.add(zero);
				args.add(viewUp);

				// Set the camera back to a spatial position along the z axis: (0, 0,
				// 67)
				JsonArray position = new JsonArray();
				position.add(zero);
				position.add(zero);
				position.add(new JsonPrimitive(67));
				args.add(position);

				// Invoke the camera update method from ParaView
				connection.getWidget().call("viewport.camera.update", args);
				
				//Create a new set of arguments, this time only including the view ID
				JsonArray resetArgs = new JsonArray();
				resetArgs.add(view);
				
				//Invoke the reset method so that the camera will be moved to the default distance away from the origin
				connection.getWidget().call("viewport.camera.reset", resetArgs);

				// Redraw the composite from the new camera position
				plotComposite.refresh();

			}
		};

		// Add an action that will launch the web visualizer
		Action launchWeb = new Action("Launch in web visualizer") {

			@Override
			public void run() {

				// Get the path to the folder containing the file.
				String folder = uri.getPath();
				folder = folder.substring(0, folder.lastIndexOf("/"));

				// Get the connection parameters
				String port = connection.getProperty("visualizerPort");
				String host = connection.getHost();
				String path = connection.getPath();

				// The username for the remote machine
				String username = null;

				// If the host contains a username, split it off from the host
				if (host.contains("@")) {
					String[] tokens = host.split("@");
					username = tokens[0];
					host = tokens[1];
				}

				// Otherwise, use the current machine's username
				else {
					username = System.getProperty("user.name");
				}

				// The url where the visualizer can be accessed
				String urlString = "http://" + host + ":" + port
						+ "/apps/Visualizer/";

				// Whether or not we need to launch the visualizer before trying
				// to open it in the browser
				boolean launch = true;

				// Test if the server is already running
				try {

					// Attempt to connect to the server
					URL url = new URL(urlString);
					HttpURLConnection test = (HttpURLConnection) url
							.openConnection();
					test.setRequestMethod("HEAD");
					if (test.getResponseCode() == HttpURLConnection.HTTP_OK) {

						// If the server is already running, don't launch
						// another
						launch = false;
					}
				} catch (IOException e) {
					// An exception is expected to be thrown here if the
					// connection was refused (because the server wasn't running
					// yet) so there is nothing to do.
				}

				// If we did not get a response from the server, try to launch
				// it
				if (launch) {

					// Get the host's address
					InetAddress hostAddr = null;
					try {
						hostAddr = InetAddress.getByName(host);
					} catch (UnknownHostException e1) {
						logger.error(
								"ParaView Plot Editor could not identify host "
										+ host + "\"");
					}

					try {

						// The OS specific string that describes the path to
						// ParaView from the base ParaView directory given
						// the ParaView installation path.
						String osPath = "";

						// If it is the localhost, then launch the server
						// locally
						if (hostAddr.isAnyLocalAddress()
								|| hostAddr.isLoopbackAddress()
								|| NetworkInterface
										.getByInetAddress(hostAddr) != null) {

							// The builder which will create a process to open
							// the web visualizer
							ProcessBuilder builder = null;

							// Check the operating system and set the paths
							// accordingly.
							// TODO Add support for windows here
							String OS = System.getProperty("os.name", "generic")
									.toLowerCase(Locale.ENGLISH);

							// Set the paths for mac
							if ((OS.indexOf("mac") >= 0)
									|| (OS.indexOf("darwin") >= 0)) {

								// For Mac, go inside the application's contents
								osPath = "/paraview.app/Contents";

								// On an X display, open pvpython, run the web
								// visualizer server, and set the folder
								// containing the web visualizer, the directory
								// where the files to be visualized are located,
								// and the port number for the server to listen
								// to
								builder = new ProcessBuilder(
										path + osPath + "/bin/pvpython",
										path + osPath
												+ "/Python/paraview/web/pv_web_visualizer.py",
										"--content", path + osPath + "/www",
										"--data-dir",
										ResourcesPlugin.getWorkspace().getRoot()
												.getLocation().toString(),
										"--port", port);
							}

							// Otherwise, set the paths for Linux. The osPath
							// should stay as the empty string
							else {

								// The name of the paraview version. This is
								// expected to be of the form "paraview-"
								// followed by a decimal version number.
								// "paraview-5.0" for example.
								String version = "";

								// One copy of a folder with this name should be
								// in the lib directory.
								File lib = new File(path + osPath + "/lib");

								// Search /lib for a folder with the correct
								// name
								for (String name : lib.list()) {
									if (name.matches("paraview-(.*)")) {
										version = name;
										break;
									}
								}

								// Create a process builder that will open
								// pvpython, run the web visualizer server, and
								// set the folder containing the web visualizer,
								// the directory where the files to be
								// visualized are located, and the port number
								// for the server to listen to
								builder = new ProcessBuilder(
										path + osPath + "/bin/pvpython",
										path + osPath + "/lib/" + version
												+ "/site-packages/paraview/web/pv_web_visualizer.py",
										"--content",
										path + osPath + "/share/" + version
												+ "/www",
										"--data-dir",
										ResourcesPlugin.getWorkspace().getRoot()
												.getLocation().toString(),
										"--port", port);
							}

							// The system delimiter for directories
							String delimiter = System
									.getProperty("file.separator");

							// If the path ends with the delimiter, remove it
							if (path.endsWith(delimiter)) {
								path = path.substring(0, path.length() - 1);
							}

							// Try to create the process and start a thread to
							// consume the processes's input
							try {

								// Redirect the process's error stream to its
								// output stream so we only have to deal with
								// one
								final Process process = builder
										.redirectErrorStream(true).start();

								// Create a thread to consume the process's
								// output ourselves, or else the process will
								// freeze once its IO buffer is full.
								new Thread(new Runnable() {

									@Override
									public void run() {

										// While the process is alive, keep
										// reading
										// and
										// discarding its output
										while (process.isAlive()) {
											try {
												process.getInputStream().read();
											} catch (IOException e) {
												logger.error(
														"Error while handling ParaView"
																+ "web viewer process's output "
																+ "stream.");
											}
										}
									}
								}).start();
							} catch (IOException e) {
								logger.error(
										"Problem while starting the ParaView web"
												+ " visualizer server.");
							}
						}

						// If the host is not local, then try to launch the
						// server on the remote machine
						else {

							// Get the remote os
							String os = connection.getProperty("remoteOS");

							// The command to be executed on the remote server
							String command = null;

							// Set the paths for mac
							if ((os.indexOf("mac") >= 0)
									|| (os.indexOf("darwin") >= 0)
									|| os.indexOf("OSx") >= 0) {

								// For Mac, go inside the application's contents
								osPath = "/paraview.app/Contents";

								// On an X display, open pvpython, run the web
								// visualizer server, and set the folder
								// containing the web visualizer, the directory
								// where the files to be visualized are located,
								// and the port number for the server to listen
								// to
								command = "DISPLAY:=0 " + path + osPath
										+ "/bin/pvpython " + path + osPath
										+ "/Python/paraview/web/pv_web_visualizer.py "
										+ "--content " + path + osPath + "/www "
										+ "--data-dir " + folder + " --port "
										+ port;
							}

							// Otherwise, set the paths for Linux.
							else {

								// For Linux, look in the bin folder
								osPath = "/bin";

								// The name of the paraview version.
								String version = "paraview-" + connection
										.getProperty("remoteVersion");

								// On an X display, open pvpython, run the web
								// visualizer server, and set the folder
								// containing the web visualizer, the directory
								// where the files to be visualized are located,
								// and the port number for the server to listen
								// to
								command = "DISPLAY=:0 " + path + osPath
										+ "/pvpython " + path + "/lib/"
										+ version
										+ "/site-packages/paraview/web/pv_web_visualizer.py "
										+ "--content " + path + "/share/"
										+ version + "/www " + "--data-dir "
										+ folder + " --port " + port;
							}

							// The hostname to connect to
							String mGateway = host;

							// If the host has a @, then it is split into a
							// username and
							// host machine.
							if (mGateway.indexOf("@") > 0) {
								mGateway = host.substring(host.indexOf("@"));
							}

							try {

								// Connect to the specified remote host
								Session session = ((ParaViewConnection) connection)
										.getSession();

								// A channel used to execute the paraview launch
								// command
								ChannelExec channel = (ChannelExec) session
										.openChannel("exec");

								// Run the command
								channel.setCommand(command);
								channel.setInputStream(System.in, true);
								channel.connect();

							} catch (JSchException e) {
								logger.error(
										"A JSch exception was raised during an attempt to launch and connect to "
												+ "the ParaView Web Visualizer on remote host "
												+ mGateway);
							}
						}
					} catch (SocketException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				// A non-final reference to the opened browser
				IWebBrowser tempBrowser = null;

				// Try to open the internal web browser and maximize it.
				try {
					tempBrowser = PlatformUI.getWorkbench().getBrowserSupport()
							.createBrowser("ParaView Web Visualizer");
				} catch (PartInitException e) {
					logger.error(
							"Error attempting to open internal web browser");
				}

				// A final reference to the URL from which the visualizer is
				// reachable
				final String webURL = urlString;

				// Get a final reference to the web browser.
				final IWebBrowser browser = tempBrowser;

				// Start a thread to set the browser to the right page when the
				// server is ready
				new Thread() {

					@Override
					public void run() {

						// The URL for the web visualizer
						URL url = null;
						try {
							url = new URL(webURL);
						} catch (MalformedURLException e1) {
							logger.error(
									"Error parsing the url for the ParaView "
											+ "web visualizer");
						}

						// The number of connection attempts made;
						int i = 0;

						// Keep trying to connect until the maximum number of
						// tries have elapsed.
						while (i < 40) {
							try {

								// A test connection to the server
								HttpURLConnection test = (HttpURLConnection) url
										.openConnection();
								test.setRequestMethod("HEAD");

								// Check if the server is reachable
								if (test.getResponseCode() == HttpURLConnection.HTTP_OK) {

									// Get a final reference to the url
									final URL finalURL = new URL(webURL);

									// If it is, create a thread to send it to
									// the visualizer's url
									Display.getDefault()
											.asyncExec(new Runnable() {

												@Override
												public void run() {

													// Try to open the url in
													// the browser
													try {
														browser.openURL(
																finalURL);
													} catch (PartInitException e) {
														logger.error(
																"Error attempting to open url: "
																		+ finalURL);
													}
												}

											});
									return;
								}
							} catch (IOException e) {
								// An exception is expected to be thrown here
								// when the server is not yet running, so
								// there's nothing to do
							}

							// Increment the number of attempts and wait two
							// seconds before trying again.
							i++;
							try {
								Thread.sleep(2000);
							} catch (InterruptedException e) {
								logger.error("The ParaView web browser setting "
										+ "thread was interupted unexpectedly.");
							}
						}

					}
				}.start();

			}
		};

		// Create a list of the actions and return it
		ArrayList<Action> actions = new ArrayList<Action>();
		actions.add(zoomIn);
		actions.add(zoomOut);
		actions.add(resetCamera);
		actions.add(launchWeb);
		return actions;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.eavp.viz.service.IPlot#getNumAdditionalPages()
	 */
	@Override
	public int getNumAdditionalPages() {
		return 0;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.eavp.viz.service.IVizCanvas#getRenderElementHolder(geometry.
	 * Geometry)
	 */
	@Override
	public IRenderElementHolder getRenderElementHolder(Geometry geometry) {
		// ParaView does not use IRenderElements
		return null;
	}
}
