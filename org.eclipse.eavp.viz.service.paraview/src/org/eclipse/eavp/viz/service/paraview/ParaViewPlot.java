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
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.eavp.viz.service.AbstractSeries;
import org.eclipse.eavp.viz.service.ISeries;
import org.eclipse.eavp.viz.service.connections.ConnectionPlot;
import org.eclipse.eavp.viz.service.connections.ConnectionPlotComposite;
import org.eclipse.eavp.viz.service.connections.ConnectionState;
import org.eclipse.eavp.viz.service.connections.IVizConnection;
import org.eclipse.eavp.viz.service.paraview.proxy.IParaViewProxy;
import org.eclipse.eavp.viz.service.paraview.web.IParaViewWebClient;
import org.eclipse.jface.action.Action;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IFileEditorInput;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.browser.IWebBrowser;
import org.eclipse.ui.part.MultiPageEditorPart;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
		return new ParaViewPlotComposite(parent, SWT.NONE);
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

		// Add an action that will launch the web visualizer
		Action launchWeb = new Action("Launch in web visualizer") {

			@Override
			public void run() {

				// Get the connection parameters
				String port = connection.getProperty("visualizerPort");
				String urlString = "http://localhost:" + port
						+ "/apps/Visualizer/";
				String path = connection.getPath();

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

				// The OS specific string that describes the path to
				// ParaView from the base ParaView directory given the ParaView
				// installation path.
				String osPath = "";

				// The builder which will create a process to open the web
				// visualizer
				ProcessBuilder builder = null;

				// Check the operating system and set the paths
				// accordingly.
				// TODO Add support for windows here
				String OS = System.getProperty("os.name", "generic")
						.toLowerCase(Locale.ENGLISH);

				// Set the paths for mac
				if ((OS.indexOf("mac") >= 0) || (OS.indexOf("darwin") >= 0)) {

					// For Mac, go inside the application's contents
					osPath = "/paraview.app/Contents";
					builder = new ProcessBuilder(
							path + osPath + "/bin/pvpython",
							path + osPath
									+ "/Python/paraview/web/pv_web_visualizer.py",
							"--content", path + osPath + "/www",
							"--data-dir", ResourcesPlugin.getWorkspace()
									.getRoot().getLocation().toString(),
							"--port", port);
				}

				// Otherwise, set the paths for Linux. The osPath should stay as
				// the empty string
				else {

					// The name of the paraview version. This is expected to be
					// of the form "paraview-" followed by a decimal version
					// number. "paraview-5.0" for example.
					String version = "";

					// One copy of a folder with this name should be in the /lib
					// directory.
					File lib = new File(path + osPath + "/lib");

					// Search /lib for a folder with the correct name
					for (String name : lib.list()) {
						if (name.matches("paraview-(.*)")) {
							version = name;
							break;
						}
					}

					builder = new ProcessBuilder(
							path + osPath + "/bin/pvpython",
							path + osPath + "/lib/" + version
									+ "/site-packages/paraview/web/pv_web_visualizer.py",
							"--content",
							path + osPath + "/share/" + version + "/www",
							"--data-dir", ResourcesPlugin.getWorkspace()
									.getRoot().getLocation().toString(),
							"--port", port);
				}

				// The system delimiter for directories
				String delimiter = System.getProperty("file.separator");

				// If the path ends with the delimiter, remove it
				if (path.endsWith(delimiter)) {
					path = path.substring(0, path.length() - 1);
				}

				// Try to create the process and start a thread to consume the
				// processe's input
				try {

					// Redirect the process's error stream to its output
					// stream so we only have to deal with one
					final Process process = builder.redirectErrorStream(true)
							.start();

					// Create a thread to consume the process's output
					// ourselves, or else the process will freeze once its IO
					// buffer is
					// full.
					new Thread(new Runnable() {

						@Override
						public void run() {

							// While the process is alive, keep reading and
							// discarding its output
							while (process.isAlive()) {
								try {
									process.getInputStream().read();
								} catch (IOException e) {
									logger.error("Error while handling ParaView"
											+ "web viewer process's output "
											+ "stream.");
								}
							}
						}
					}).start();
				} catch (IOException e) {
					logger.error("Problem while starting the ParaView web"
							+ " visualizer server.");
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

				// The URL from which the visualizer is reachable
				final String webURL = "http://localhost:" + port
						+ "/apps/Visualizer/";

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
}
