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
package org.eclipse.eavp.tests.viz.service.connections.paraview;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.InputStream;
import java.net.URI;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Dictionary;
import java.util.List;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.eavp.viz.service.connections.IVizConnection;
import org.eclipse.eavp.viz.service.connections.VizConnection;
import org.eclipse.eavp.viz.service.paraview.connections.ParaViewConnection;
import org.eclipse.eavp.viz.service.paraview.connections.ParaViewVizConnectionPreferences;
import org.eclipse.remote.core.IRemoteConnection;
import org.eclipse.remote.core.IRemoteConnectionChangeListener;
import org.eclipse.remote.core.IRemoteConnectionType;
import org.eclipse.remote.core.IRemoteConnectionType.Service;
import org.eclipse.remote.core.IRemoteConnectionWorkingCopy;
import org.eclipse.remote.core.IRemoteServicesManager;
import org.eclipse.remote.core.RemoteConnectionChangeEvent;
import org.eclipse.remote.core.exception.RemoteConnectionException;
import org.junit.Before;
import org.junit.Test;
import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;
import org.osgi.framework.BundleException;
import org.osgi.framework.BundleListener;
import org.osgi.framework.Filter;
import org.osgi.framework.FrameworkListener;
import org.osgi.framework.InvalidSyntaxException;
import org.osgi.framework.ServiceFactory;
import org.osgi.framework.ServiceListener;
import org.osgi.framework.ServiceObjects;
import org.osgi.framework.ServiceReference;
import org.osgi.framework.ServiceRegistration;

/**
 * Tests {@link ParaViewConnection}'s implementation of {@link VizConnection}.
 * 
 * @author Jordan Deyton
 *
 */
public class ParaViewConnectionTester {

	/**
	 * The connection that is tested in each test.
	 */
	private ParaViewConnection connection;

	/**
	 * Initializes the class variables used in each test.
	 */
	@Before
	public void beforeEachTest() {
		connection = new ParaViewConnection();
	}

	/**
	 * Checks the default connection properties for ParaView connections.
	 */
	@Test
	public void checkDefaultProperties() {

		// Check the default values for the basic connection properties.
		assertEquals("Connection1", connection.getName());
		assertEquals("", connection.getDescription());
		assertEquals("localhost", connection.getHost());
		assertEquals(50000, connection.getPort());
		assertEquals("", connection.getPath());

		// Check that they can be set.
		String name = "newname";
		String desc = "some description";
		String host = "newhost";
		int port = 50001;
		String path = "/some/path";

		assertTrue(connection.setName(name));
		assertEquals(name, connection.getName());
		assertTrue(connection.setDescription(desc));
		assertEquals(desc, connection.getDescription());
		assertTrue(connection.setHost(host));
		assertEquals(host, connection.getHost());
		assertTrue(connection.setPort(port));
		assertEquals(port, connection.getPort());
		assertTrue(connection.setPath(path));
		assertEquals(path, connection.getPath());

		return;
	}

	/**
	 * Checks the default connection properties for ParaView connections when
	 * accessed by their actual property names via
	 * {@link IVizConnection#getProperty(String)} and
	 * {@link VizConnection#setProperty(String, String)}.
	 */
	@Test
	public void checkDefaultPropertyNames() {

		// Check the default values for the basic connection properties.
		assertEquals("Connection1", connection.getProperty("Name"));
		assertEquals("", connection.getProperty("Description"));
		assertEquals("localhost", connection.getProperty("Host"));
		assertEquals("50000", connection.getProperty("Port"));
		assertEquals("", connection.getProperty("Path"));

		// Check that they can be set.
		String name = "newname";
		String desc = "some description";
		String host = "newhost";
		String port = "50001";
		String path = "/some/path";

		assertTrue(connection.setProperty("Name", name));
		assertEquals(name, connection.getProperty("Name"));
		assertTrue(connection.setProperty("Description", desc));
		assertEquals(desc, connection.getProperty("Description"));
		assertTrue(connection.setProperty("Host", host));
		assertEquals(host, connection.getProperty("Host"));
		assertTrue(connection.setProperty("Port", port));
		assertEquals(port, connection.getProperty("Port"));
		assertTrue(connection.setProperty("Path", path));
		assertEquals(path, connection.getProperty("Path"));

		return;
	}

	/**
	 * Check that the service can configure itself correctly using an
	 * ParaViewVizConnectionPreferences.
	 */
	@Test
	public void checkSetPreferences() {
		BundleContext context = new BundleContext() {

			@Override
			public String getProperty(String key) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public Bundle getBundle() {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public Bundle installBundle(String location, InputStream input)
					throws BundleException {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public Bundle installBundle(String location)
					throws BundleException {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public Bundle getBundle(long id) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public Bundle[] getBundles() {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public void addServiceListener(ServiceListener listener,
					String filter) throws InvalidSyntaxException {
				// TODO Auto-generated method stub

			}

			@Override
			public void addServiceListener(ServiceListener listener) {
				// TODO Auto-generated method stub

			}

			@Override
			public void removeServiceListener(ServiceListener listener) {
				// TODO Auto-generated method stub

			}

			@Override
			public void addBundleListener(BundleListener listener) {
				// TODO Auto-generated method stub

			}

			@Override
			public void removeBundleListener(BundleListener listener) {
				// TODO Auto-generated method stub

			}

			@Override
			public void addFrameworkListener(FrameworkListener listener) {
				// TODO Auto-generated method stub

			}

			@Override
			public void removeFrameworkListener(FrameworkListener listener) {
				// TODO Auto-generated method stub

			}

			@Override
			public ServiceRegistration<?> registerService(String[] clazzes,
					Object service, Dictionary<String, ?> properties) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ServiceRegistration<?> registerService(String clazz,
					Object service, Dictionary<String, ?> properties) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public <S> ServiceRegistration<S> registerService(Class<S> clazz,
					S service, Dictionary<String, ?> properties) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public <S> ServiceRegistration<S> registerService(Class<S> clazz,
					ServiceFactory<S> factory,
					Dictionary<String, ?> properties) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ServiceReference<?>[] getServiceReferences(String clazz,
					String filter) throws InvalidSyntaxException {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ServiceReference<?>[] getAllServiceReferences(String clazz,
					String filter) throws InvalidSyntaxException {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ServiceReference<?> getServiceReference(String clazz) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public <S> ServiceReference<S> getServiceReference(Class<S> clazz) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public <S> Collection<ServiceReference<S>> getServiceReferences(
					Class<S> clazz, String filter)
					throws InvalidSyntaxException {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public <S> S getService(ServiceReference<S> reference) {
				return (S) new FakeRemoteServicesManager();
			}

			@Override
			public boolean ungetService(ServiceReference<?> reference) {
				// TODO Auto-generated method stub
				return false;
			}

			@Override
			public <S> ServiceObjects<S> getServiceObjects(
					ServiceReference<S> reference) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public File getDataFile(String filename) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public Filter createFilter(String filter)
					throws InvalidSyntaxException {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public Bundle getBundle(String location) {
				// TODO Auto-generated method stub
				return null;
			}

		};

		// Set up a connection
		TestParaViewVizConnectionPreferences prefs = new TestParaViewVizConnectionPreferences(
				context);
		prefs.setHostName("Remote Host");
		prefs.setName("Test Connection");
		prefs.setPort(1234);
		prefs.setUsername("EAVP User");
		prefs.setConnectionName("Remote Connection");
		prefs.setExecutablePath("path/to/paraview.exe");
		prefs.setScriptPath("/path/to/script.py");
		prefs.setVisualizerPort(9876);

		// Set the preferences to the connection
		connection.setPreferences(prefs);

		// Check that the connection was properly configured
		assertEquals(prefs.getHostName(), connection.getHost());
		assertEquals(prefs.getName(), connection.getName());
		assertEquals(prefs.getPort(), connection.getPort());
		assertEquals(prefs.getUsername(), connection.getProperty("username"));
		assertEquals(prefs.getExecutablePath(), connection.getPath());
		assertEquals(prefs.getScriptPath(),
				connection.getProperty("serverPath"));
		assertEquals(prefs.getVisualizerPort(),
				Integer.parseInt(connection.getProperty("visualizerPort")));

	}

	/**
	 * A minimal IRemoteServicesManager implementation which will return a SSH
	 * service that has a single connection whose working copy will each
	 * property name as its value when getProperty() is invoked. This class is
	 * for use with testing the RCPVizConnectionPreferences by injecting this
	 * class as a dependency. The returned connection will have the name "Remote
	 * Connection"
	 * 
	 * @author Robert Smith
	 *
	 */
	public class FakeRemoteServicesManager implements IRemoteServicesManager {

		@Override
		public void addRemoteConnectionChangeListener(
				IRemoteConnectionChangeListener arg0) {
			// TODO Auto-generated method stub

		}

		@Override
		public void fireRemoteConnectionChangeEvent(
				RemoteConnectionChangeEvent arg0) {
			// TODO Auto-generated method stub

		}

		@Override
		public List<IRemoteConnectionType> getAllConnectionTypes() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public List<IRemoteConnection> getAllRemoteConnections() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public IRemoteConnectionType getConnectionType(String arg0) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public IRemoteConnectionType getConnectionType(URI arg0) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public List<IRemoteConnectionType> getConnectionTypesByService(
				Class<? extends Service>... arg0) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public List<IRemoteConnectionType> getConnectionTypesSupporting(
				Class<? extends org.eclipse.remote.core.IRemoteConnection.Service>... arg0) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public IRemoteConnectionType getLocalConnectionType() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public List<IRemoteConnectionType> getRemoteConnectionTypes() {
			ArrayList<IRemoteConnectionType> types = new ArrayList<IRemoteConnectionType>();
			IRemoteConnectionType ssh = new IRemoteConnectionType() {

				@Override
				public boolean canAdd() {
					// TODO Auto-generated method stub
					return false;
				}

				@Override
				public boolean canEdit() {
					// TODO Auto-generated method stub
					return false;
				}

				@Override
				public boolean canRemove() {
					// TODO Auto-generated method stub
					return false;
				}

				@Override
				public IRemoteConnection getConnection(String arg0) {
					// TODO Auto-generated method stub
					return null;
				}

				@Override
				public IRemoteConnection getConnection(URI arg0) {
					// TODO Auto-generated method stub
					return null;
				}

				@Override
				public List<String> getConnectionServices() {
					// TODO Auto-generated method stub
					return null;
				}

				@Override
				public List<IRemoteConnection> getConnections() {
					ArrayList<IRemoteConnection> connections = new ArrayList<IRemoteConnection>();
					connections.add(new IRemoteConnection() {

						@Override
						public void addConnectionChangeListener(
								IRemoteConnectionChangeListener arg0) {
							// TODO Auto-generated method stub

						}

						@Override
						public void close() {
							// TODO Auto-generated method stub

						}

						@Override
						public void fireConnectionChangeEvent(int arg0) {
							// TODO Auto-generated method stub

						}

						@Override
						public String getAttribute(String arg0) {
							// TODO Auto-generated method stub
							return null;
						}

						@Override
						public IRemoteConnectionType getConnectionType() {
							// TODO Auto-generated method stub
							return null;
						}

						@Override
						public String getName() {
							return "Remote Connection";
						}

						@Override
						public String getProperty(String arg0) {
							// TODO Auto-generated method stub
							return null;
						}

						@Override
						public String getSecureAttribute(String arg0) {
							// TODO Auto-generated method stub
							return null;
						}

						@Override
						public <T extends Service> T getService(Class<T> arg0) {
							// TODO Auto-generated method stub
							return null;
						}

						@Override
						public IRemoteConnectionWorkingCopy getWorkingCopy() {
							return new IRemoteConnectionWorkingCopy() {

								@Override
								public void addConnectionChangeListener(
										IRemoteConnectionChangeListener arg0) {
									// TODO Auto-generated method stub

								}

								@Override
								public void close() {
									// TODO Auto-generated method stub

								}

								@Override
								public void fireConnectionChangeEvent(
										int arg0) {
									// TODO Auto-generated method stub

								}

								@Override
								public String getAttribute(String arg0) {
									// Simply return every attribute's name.
									return arg0;
								}

								@Override
								public IRemoteConnectionType getConnectionType() {
									// TODO Auto-generated method stub
									return null;
								}

								@Override
								public String getName() {
									// TODO Auto-generated method stub
									return null;
								}

								@Override
								public String getProperty(String arg0) {
									// TODO Auto-generated method stub
									return null;
								}

								@Override
								public String getSecureAttribute(String arg0) {
									// TODO Auto-generated method stub
									return null;
								}

								@Override
								public <T extends Service> T getService(
										Class<T> arg0) {
									// TODO Auto-generated method stub
									return null;
								}

								@Override
								public IRemoteConnectionWorkingCopy getWorkingCopy() {
									// TODO Auto-generated method stub
									return null;
								}

								@Override
								public <T extends Service> boolean hasService(
										Class<T> arg0) {
									// TODO Auto-generated method stub
									return false;
								}

								@Override
								public boolean isOpen() {
									// TODO Auto-generated method stub
									return false;
								}

								@Override
								public void open(IProgressMonitor arg0)
										throws RemoteConnectionException {
									// TODO Auto-generated method stub

								}

								@Override
								public void removeConnectionChangeListener(
										IRemoteConnectionChangeListener arg0) {
									// TODO Auto-generated method stub

								}

								@Override
								public IRemoteConnection getOriginal() {
									// TODO Auto-generated method stub
									return null;
								}

								@Override
								public boolean isDirty() {
									// TODO Auto-generated method stub
									return false;
								}

								@Override
								public IRemoteConnection save()
										throws RemoteConnectionException {
									// TODO Auto-generated method stub
									return null;
								}

								@Override
								public void setAttribute(String arg0,
										String arg1) {
									// TODO Auto-generated method stub

								}

								@Override
								public void setName(String arg0) {
									// TODO Auto-generated method stub

								}

								@Override
								public void setSecureAttribute(String arg0,
										String arg1) {
									// TODO Auto-generated method stub

								}

							};
						}

						@Override
						public <T extends Service> boolean hasService(
								Class<T> arg0) {
							// TODO Auto-generated method stub
							return false;
						}

						@Override
						public boolean isOpen() {
							// TODO Auto-generated method stub
							return false;
						}

						@Override
						public void open(IProgressMonitor arg0)
								throws RemoteConnectionException {
							// TODO Auto-generated method stub

						}

						@Override
						public void removeConnectionChangeListener(
								IRemoteConnectionChangeListener arg0) {
							// TODO Auto-generated method stub

						}

					});
					return connections;
				}

				@Override
				public String getId() {
					// TODO Auto-generated method stub
					return null;
				}

				@Override
				public String getName() {
					return "SSH";
				}

				@Override
				public List<String> getProcessServices() {
					// TODO Auto-generated method stub
					return null;
				}

				@Override
				public IRemoteServicesManager getRemoteServicesManager() {
					// TODO Auto-generated method stub
					return null;
				}

				@Override
				public String getScheme() {
					// TODO Auto-generated method stub
					return null;
				}

				@Override
				public <T extends Service> T getService(Class<T> arg0) {
					// TODO Auto-generated method stub
					return null;
				}

				@Override
				public List<String> getServices() {
					// TODO Auto-generated method stub
					return null;
				}

				@Override
				public <T extends org.eclipse.remote.core.IRemoteConnection.Service> boolean hasConnectionService(
						Class<T> arg0) {
					// TODO Auto-generated method stub
					return false;
				}

				@Override
				public <T extends org.eclipse.remote.core.IRemoteProcess.Service> boolean hasProcessService(
						Class<T> arg0) {
					// TODO Auto-generated method stub
					return false;
				}

				@Override
				public <T extends Service> boolean hasService(Class<T> arg0) {
					// TODO Auto-generated method stub
					return false;
				}

				@Override
				public IRemoteConnectionWorkingCopy newConnection(String arg0)
						throws RemoteConnectionException {
					// TODO Auto-generated method stub
					return null;
				}

				@Override
				public void removeConnection(IRemoteConnection arg0)
						throws RemoteConnectionException {
					// TODO Auto-generated method stub

				}

			};
			types.add(ssh);
			return types;
		}

		@Override
		public void removeRemoteConnectionChangeListener(
				IRemoteConnectionChangeListener arg0) {
			// TODO Auto-generated method stub

		}
	}

	/**
	 * An extension of ParaViewVizConnectionPreferences which allows a custom
	 * BundleContext to be set to the class for testing purposes.
	 * 
	 * @author Robert Smith
	 *
	 */
	private class TestParaViewVizConnectionPreferences
			extends ParaViewVizConnectionPreferences {

		/**
		 * The Nullary Constructor.
		 * 
		 * @param context
		 *            The bundle context from which to draw the
		 *            IRemoteServicesManager.
		 */
		public TestParaViewVizConnectionPreferences(BundleContext context) {
			super();
			this.context = context;
		}

		/**
		 * The constructor for the serialized representation.
		 * 
		 * @param data
		 *            The serialized representation of the preferences.
		 * @param context
		 *            The bundle context from which to draw the
		 *            IRemoteServicesManager.
		 */
		public TestParaViewVizConnectionPreferences(String data,
				BundleContext context) {
			super(data);
			this.context = context;
		}
	}
}
