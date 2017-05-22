/*******************************************************************************
 * Copyright (c) 2017 UT-Battelle, LLC.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   Initial API and implementation and/or initial documentation - Robert Smith
 *******************************************************************************/
package org.eclipse.eavp.tests.viz.service.visit.connections;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.InputStream;
import java.net.URI;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Dictionary;
import java.util.List;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.eavp.viz.service.connections.preferences.AbstractVizConnectionPreferences;
import org.eclipse.eavp.viz.service.visit.connections.VisItVizConnectionPreferences;
import org.eclipse.remote.core.IRemoteConnection;
import org.eclipse.remote.core.IRemoteConnectionChangeListener;
import org.eclipse.remote.core.IRemoteConnectionType;
import org.eclipse.remote.core.IRemoteConnectionType.Service;
import org.eclipse.remote.core.IRemoteConnectionWorkingCopy;
import org.eclipse.remote.core.IRemoteServicesManager;
import org.eclipse.remote.core.RemoteConnectionChangeEvent;
import org.eclipse.remote.core.exception.RemoteConnectionException;
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
 * A class to test the VisItVizConnectionPreferences for the ability to
 * correctly manage a set of preferences through the IRemoteConnectionManager.
 * 
 * @author Robert Smith
 *
 */
public class VisItVizConnectionPreferencesTester {

	/**
	 * Check that the null constructor is working correctly.
	 */
	@Test
	public void checkConstruction() {
		VisItVizConnectionPreferences prefs = new VisItVizConnectionPreferences();

		// Check that the null constructor correctly initializes the
		// preferences.
		assertEquals("", prefs.getExecutablePath());
		assertEquals("", prefs.getProxy());
		assertEquals(0, prefs.getProxyPort());
	}

	/**
	 * Check that the preferences can be serialized and reconstructed from the
	 * string representation.
	 */
	@Test
	public void checkSerialize() {

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

		TestVisItVizConnectionPreferences prefs = new TestVisItVizConnectionPreferences(
				context);

		// Set up a connection
		prefs.setHostName("Remote Host");
		prefs.setName("Test Connection");
		prefs.setPort(1234);
		prefs.setUsername("EAVP User");
		prefs.setConnectionName("Remote Connection");
		prefs.setExecutablePath("path/to/visit.exe");
		prefs.setProxy("Test Proxy");
		prefs.setProxyPort(9876);

		// Check that variables were stored correctly. HostName and Username
		// should have been overwritten based on what was found in the remote
		// connection.
		assertEquals("ADDRESS_ATTR", prefs.getHostName());
		assertEquals("Test Connection", prefs.getName());
		assertEquals(1234, prefs.getPort());
		assertEquals("USERNAME_ATTR", prefs.getUsername());
		assertEquals("Remote Connection", prefs.getConnectionName());
		assertEquals("path/to/visit.exe", prefs.getExecutablePath());
		assertEquals("Test Proxy", prefs.getProxy());
		assertEquals(9876, prefs.getProxyPort());

		// Get the serialized representation of the preferences
		String serialized = prefs.serialize();

		// Check that the preferences string is as expected
		assertEquals(
				"ADDRESS_ATTR,Test Connection,1234,USERNAME_ATTR,Remote Connection,path/to/visit.exe,Test Proxy,9876",
				serialized);

		// Create a new preferences set from the string
		AbstractVizConnectionPreferences deserialized = new AbstractVizConnectionPreferences(
				serialized) {

		};

		// Check that the preferences are correctly configured
		assertEquals("ADDRESS_ATTR", deserialized.getHostName());
		assertEquals("Test Connection", deserialized.getName());
		assertEquals(1234, deserialized.getPort());
		assertEquals("USERNAME_ATTR", deserialized.getUsername());
		assertEquals("Remote Connection", prefs.getConnectionName());
		assertEquals("path/to/visit.exe", prefs.getExecutablePath());
		assertEquals("Test Proxy", prefs.getProxy());
		assertEquals(9876, prefs.getProxyPort());
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
	 * An extension of VisItVizConnectionPreferences which allows a custom
	 * BundleContext to be set to the class for testing purposes.
	 * 
	 * @author Robert Smith
	 *
	 */
	private class TestVisItVizConnectionPreferences
			extends VisItVizConnectionPreferences {

		/**
		 * The Nullary Constructor.
		 * 
		 * @param context
		 *            The bundle context from which to draw the
		 *            IRemoteServicesManager.
		 */
		public TestVisItVizConnectionPreferences(BundleContext context) {
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
		public TestVisItVizConnectionPreferences(String data,
				BundleContext context) {
			super(data);
			this.context = context;
		}
	}
}