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
package org.eclipse.eavp.viz.service.connections.preferences;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

import org.eclipse.core.runtime.preferences.IEclipsePreferences;
import org.eclipse.eavp.viz.datastructures.VizObject.IVizUpdateable;
import org.eclipse.eavp.viz.datastructures.VizObject.IVizUpdateableListener;
import org.eclipse.eavp.viz.datastructures.ui.BasicVizEntryContentProvider;
import org.eclipse.eavp.viz.datastructures.ui.IVizEntryContentProvider;
import org.eclipse.eavp.viz.datastructures.ui.VizAllowedValueType;
import org.eclipse.eavp.viz.datastructures.ui.VizEntry;
import org.eclipse.eavp.viz.service.connections.IVizConnectionManager;
import org.eclipse.eavp.viz.service.preferences.AbstractVizPreferencePage;
import org.eclipse.eavp.viz.service.preferences.CustomScopedPreferenceStore;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.remote.core.IRemoteConnection;
import org.eclipse.remote.core.IRemoteConnectionType;
import org.eclipse.remote.core.IRemoteServicesManager;
import org.eclipse.remote.core.exception.RemoteConnectionException;
import org.eclipse.remote.ui.IRemoteUIConnectionService;
import org.eclipse.remote.ui.IRemoteUIConnectionWizard;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.VerifyEvent;
import org.eclipse.swt.events.VerifyListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.forms.DetailsPart;
import org.eclipse.ui.forms.IDetailsPage;
import org.eclipse.ui.forms.IDetailsPageProvider;
import org.eclipse.ui.forms.IFormPart;
import org.eclipse.ui.forms.IManagedForm;
import org.eclipse.ui.forms.ManagedForm;
import org.eclipse.ui.forms.MasterDetailsBlock;
import org.eclipse.ui.forms.SectionPart;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.Section;
import org.osgi.framework.BundleContext;
import org.osgi.framework.FrameworkUtil;
import org.osgi.framework.ServiceReference;
import org.osgi.service.prefs.BackingStoreException;

/**
 * This class provides a preference page for configuring multiple viz
 * connections. It currently contains a single table with an add and remove
 * button for adding or removing connections. Connections can be updated by
 * manipulating cells in the table.
 * 
 * @author Jordan Deyton
 *
 */
public abstract class VizConnectionPreferencePage
		extends AbstractVizPreferencePage
		implements ISelectionChangedListener, IVizUpdateableListener {

	/**
	 * The category of remote connections containing the connections relevant to
	 * this page, the SSH connections.
	 */
	protected IRemoteConnectionType sshConnections;

	/**
	 * A map of all connection names to the preferences for that connection.
	 */
	protected Map<String, IVizConnectionPreferences> connections;

	/**
	 * The preferences for the connection currently displayed in the details
	 * section.
	 */
	protected IVizConnectionPreferences currentPreferences;

	/**
	 * Default value for the port field. Individual subclasses should overwrite
	 * this so that each has a unique default port.
	 */
	protected String defaultPortNumber = "9599";

	protected IManagedForm form;

	/**
	 * The combo box containing list of names for available connections.
	 */
	protected Combo hostCombo;

	/**
	 * The category for local connections.
	 */
	protected IRemoteConnectionType localConnections;

	/**
	 * The text widget for the port number.
	 */
	protected Text portText;

	/**
	 * The {@code ConnectionTable} used by this preference page. It is
	 * represented by a {@link TableComponentComposite} on the page.
	 */
	private ConnectionTable table;

	private SectionPart tablePart;

	/**
	 * The default constructor.
	 */
	public VizConnectionPreferencePage() {
		super(GRID);

		sshConnections = null;
		connections = new HashMap<String, IVizConnectionPreferences>();
	}

	/**
	 * Creates the table of connection preferences. This method may be
	 * overridden to provide a custom table with more properties.
	 * 
	 * @return A table of connections.
	 */
	protected ConnectionTable createConnectionTable() {
		return new ConnectionTable() {
			@Override
			protected ArrayList<VizEntry> createConnectionTemplate() {
				// // Get the default connection template so we can add
				// additional
				// // columns.
				ArrayList<VizEntry> template = super.createConnectionTemplate();

				// TODO These Entries need descriptions.

				IVizEntryContentProvider contentProvider;

				// ---- proxy ---- //
				contentProvider = new BasicVizEntryContentProvider();
				contentProvider.setDefaultValue("");
				VizEntry proxyEntry = new VizEntry(contentProvider);
				proxyEntry.setName("ID");
				template.add(proxyEntry);

				// ---- visit user ---- //
				contentProvider = new BasicVizEntryContentProvider();
				contentProvider
						.setAllowedValueType(VizAllowedValueType.Discrete);
				ArrayList<String> allowedValues = new ArrayList<String>();
				allowedValues.add("VisIt");
				allowedValues.add("ParaView");
				contentProvider.setAllowedValues(allowedValues);
				contentProvider.setDefaultValue("");
				VizEntry visitUserEntry = new VizEntry(contentProvider);
				visitUserEntry.setName("VisIt User");
				template.add(visitUserEntry);

				Iterator<VizEntry> it = template.iterator();
				while (it.hasNext()) {
					VizEntry entry = it.next();
					if ("Path".equals(entry.getName())) {

						// The default path for the VisIt installation
						String defaultPath = "";

						// Get the system name
						String os = System.getProperty("os.name", "generic")
								.toLowerCase(Locale.ENGLISH);

						// Set the path to VisIt based on default/common paths
						// for each operating system
						if ((os.indexOf("mac") >= 0)
								|| (os.indexOf("darwin") >= 0)) {
							defaultPath = "/Applications/VisIt";
						} else if (os.indexOf("win") >= 0) {
							defaultPath = "C:\\Users\\"
									+ System.getProperty("user.name")
									+ "\\AppData\\Local\\Programs\\LLNL";
						} else if (os.indexOf("nux") >= 0) {
							defaultPath = System.getProperty("user.home");
						}

						// Set the value of the path entry to the default
						entry.setValue(defaultPath);

					}
				}

				// ---- visit password ---- //
				// contentProvider = new BasicEntryContentProvider();
				// contentProvider.setAllowedValueType(AllowedValueType.Undefined);
				// contentProvider.setDefaultValue("");
				// Entry visitPasswordEntry = new Entry(contentProvider);
				// visitPasswordEntry.setName("VisIt Password");
				// template.add(visitPasswordEntry);

				return template;
			}
		};

	}

	/**
	 * Overrides the default behavior to provide a connection table below the
	 * default connection preference {@code FieldEditor}s.
	 */
	@Override
	protected Control createContents(Composite parent) {

		// createConnectionTable();

		// Create the default layout initially. This also gives us the return
		// value.
		Control control = super.createContents(parent);

		// Get the created parent Composite for all FieldEditors. We need its
		// layout to make sure the connection table spans all horizontal space.
		Composite container = getFieldEditorParent();
		container.setLayout(new FillLayout());

		// // Create a ConnectionComposite to show all of the cached connection
		// // preferences.
		// TableComponentComposite connections = new TableComponentComposite(
		// container, SWT.NONE);
		// GridData gridData = new GridData(SWT.FILL, SWT.FILL, true, true,
		// gridLayout.numColumns, 1);
		// connections.setLayoutData(gridData);
		//
		// // Set the custom Composite's TableComponent to fill the table.
		// connections.setTableComponent(table);

		VizConnectionPreferencePage selfReference = this;

		MasterDetailsBlock block = new MasterDetailsBlock() {

			@Override
			protected void createMasterPart(IManagedForm managedForm,
					Composite parent) {

				FormToolkit toolkit = managedForm.getToolkit();
				Section tableSection = toolkit.createSection(parent,
						Section.TITLE_BAR);
				tableSection.setText("Connections");
				Composite masterComposite = toolkit
						.createComposite(tableSection, SWT.NONE);
				masterComposite.setLayout(new GridLayout());

				// Composite tableComposite = new Composite(masterComposite,
				// SWT.NONE);
				// tableComposite.setLayout(new FillLayout());

				// Table table = new Table(tableComposite, SWT.NONE);
				// //masterComposite.setBackground(new
				// org.eclipse.swt.graphics.Color(null, 70, 0 , 140));
				// table.setLayout(new FillLayout());

				// table = createConnectionTable();
				table.register(selfReference);
				// table = new ConnectionTable() {
				// /**
				// * Gets the row template for a connection as defined in the
				// * {@link ConnectionTable documentation for the class}.
				// *
				// * @return An {@code ArrayList<Entry>} containing the template
				// {@code Entry}
				// * s for each exposed connection property.
				// */
				// protected ArrayList<VizEntry> createConnectionTemplate() {
				// ArrayList<VizEntry> template = new ArrayList<VizEntry>();
				//
				// IVizEntryContentProvider contentProvider;
				//
				// // ---- name ---- //
				// KeyEntryContentProvider keyContentProvider = new
				// KeyEntryContentProvider(
				// keyManager);
				// VizEntry keyEntry = new KeyEntry(keyContentProvider);
				// keyEntry.setName("ID");
				// keyEntry.setDescription(
				// "The name of the connection. This must be unique.");
				// template.add(keyEntry);
				// // ---- host ---- //
				// contentProvider = new BasicVizEntryContentProvider();
				// contentProvider.setDefaultValue("ice-plot");
				// contentProvider.setAllowedValueType(VizAllowedValueType.Discrete);
				// ArrayList<String> allowedValues = new ArrayList<String>();
				//
				// IConfigurationElement[] elements =
				// Platform.getExtensionRegistry()
				// .getConfigurationElementsFor(
				// "org.eclipse.eavp.viz.service.IVizService");
				// try {
				// IVizService factory = (IVizService)
				// elements[0].createExecutableExtension("class");
				// //for(String serviceName : factory.getServiceNames()) {
				// allowedValues.add(factory.getName());
				// //}
				// } catch (CoreException e) {
				// // TODO Auto-generated catch block
				// e.printStackTrace();
				// }
				// allowedValues.add("VisIt");
				// allowedValues.add("ParaView");
				// contentProvider.setAllowedValues(allowedValues);
				// VizEntry hostEntry = new VizEntry(contentProvider);
				// hostEntry.setName("Visualization Service");
				// hostEntry.setDescription("The FQDN or IP address of the
				// remote host.%n"
				// + "For local launches, this should be \"localhost\".");
				// template.add(hostEntry);
				//
				//
				// return template;
				// }
				// };

				// // Create a ConnectionComposite to show all of the cached
				// connection
				// // preferences.
				TableComponentComposite connections = new TableComponentComposite(
						masterComposite, SWT.NONE);
				GridData gridData = new GridData(SWT.FILL, SWT.FILL, true, true,
						1, 1);
				connections.setLayoutData(gridData);
				connections.setTableComponent(table);

				connections.getTableViewer()
						.addSelectionChangedListener(selfReference);

				tableSection.setClient(masterComposite);
				tablePart = new SectionPart(tableSection);
				managedForm.addPart(tablePart);

				table.register(new IVizUpdateableListener() {

					@Override
					public void update(IVizUpdateable component) {

						if (table == component) {

							ArrayList<Integer> selectedRows = table
									.getSelectedRows();
							if (selectedRows == null) {
								selectedRows = new ArrayList<Integer>();
							}
							StructuredSelection selection = new StructuredSelection(
									selectedRows);
							// managedForm.fireSelectionChanged(tablePart,
							// selection);
						}

					}
				});

				// TableColumn column1 = new TableColumn(table, SWT.NONE);
				// column1.setText("ID");
				// column1.pack();
				// TableColumn column2 = new TableColumn(table, SWT.NONE);
				// column2.setText("Service");
				// column2.pack();
				//
				// TableItem item = new TableItem(table, SWT.NONE);
				// item.setText("item");
				// item.setText(0, "ID");
				// item.setText(1, "typwe");
				//
				// Composite buttonComposite = new Composite(masterComposite,
				// SWT.NONE);
				// buttonComposite.setLayout(new GridLayout());
				//
				// Button addButton = new Button(buttonComposite, SWT.PUSH);
				// addButton.setText("+");
				//
				// Button removeButton = new Button(buttonComposite, SWT.PUSH);
				// removeButton.setText("-");

			}

			@Override
			protected void registerPages(DetailsPart detailsPart) {
				// detailsPart.registerPage(Object.class, new IDetailsPage() {
				//
				// @Override
				// public void initialize(IManagedForm form) {
				// // TODO Auto-generated method stub
				//
				// }
				//
				// @Override
				// public void dispose() {
				// // TODO Auto-generated method stub
				//
				// }
				//
				// @Override
				// public boolean isDirty() {
				// // TODO Auto-generated method stub
				// return false;
				// }
				//
				// @Override
				// public void commit(boolean onSave) {
				// // TODO Auto-generated method stub
				//
				// }
				//
				// @Override
				// public boolean setFormInput(Object input) {
				// // TODO Auto-generated method stub
				// return false;
				// }
				//
				// @Override
				// public void setFocus() {
				// // TODO Auto-generated method stub
				//
				// }
				//
				// @Override
				// public boolean isStale() {
				// // TODO Auto-generated method stub
				// return false;
				// }
				//
				// @Override
				// public void refresh() {
				// // TODO Auto-generated method stub
				//
				// }
				//
				// @Override
				// public void selectionChanged(IFormPart part, ISelection
				// selection) {
				// System.out.println(selection);
				// }
				//
				// @Override
				// public void createContents(Composite parent) {
				// // TODO Auto-generated method stub
				//
				// }
				//
				// });
				detailsPart.setPageProvider(new IDetailsPageProvider() {

					Map<Object, IDetailsPage> pages = new HashMap<Object, IDetailsPage>();

					@Override
					public Object getPageKey(Object object) {
						return object;
					}

					@Override
					public IDetailsPage getPage(Object key) {

						// If this page has been displayed before, retrieve it
						if (pages.containsKey(key)) {
							return pages.get(key);
						}

						// Otherwise instantiate a new page
						IDetailsPage page = new IDetailsPage() {

							@Override
							public void initialize(IManagedForm form) {
								System.out.println("Initialize");
							}

							@Override
							public void dispose() {
								System.out.println("dispose");
							}

							@Override
							public boolean isDirty() {
								// TODO Auto-generated method stub
								return false;
							}

							@Override
							public void commit(boolean onSave) {
								// TODO Auto-generated method stub

							}

							@Override
							public boolean setFormInput(Object input) {
								System.out.println("set form input");
								return false;
							}

							@Override
							public void setFocus() {
								System.out.println("set focus");
							}

							@Override
							public boolean isStale() {
								// TODO Auto-generated method stub
								return false;
							}

							@Override
							public void refresh() {

							}

							@Override
							public void selectionChanged(IFormPart part,
									ISelection selection) {

								Object tableEntry = ((ArrayList) ((StructuredSelection) selection)
										.getFirstElement()).get(0);

								if (KeyEntry.class == tableEntry.getClass()) {

									String connectionName = ((KeyEntry) tableEntry)
											.getValue();
									IVizConnectionPreferences preferences = connections
											.get(connectionName);

									hostCombo.removeAll();
									hostCombo.add("localhost");

									// Refresh the list of available connections
									BundleContext context = FrameworkUtil
											.getBundle(getClass())
											.getBundleContext();

									// Get the remote services manager
									if (context != null) {
										ServiceReference<IRemoteServicesManager> ref = context
												.getServiceReference(
														IRemoteServicesManager.class);

										List<IRemoteConnectionType> types = context
												.getService(ref)
												.getRemoteConnectionTypes();

										// Find the SSH connections
										for (IRemoteConnectionType type : types) {
											if ("SSH".equals(type.getName())) {

												// Save this type
												sshConnections = type;

												// Get the names of all current
												// connections
												for (IRemoteConnection connection : type
														.getConnections()) {
													hostCombo.add(connection
															.getName());
												}
											}
										}
									}

									// The list of all options in the combo
									String[] comboConnections = hostCombo
											.getItems();

									// Search for the option in the preferences
									// and set it. If the connection is missing,
									// the combo will default to localhost.
									for (int i = 0; i < comboConnections.length; i++) {
										if (comboConnections[i]
												.equals(currentPreferences
														.getConnectionName())) {
											hostCombo.select(i);
											break;
										}
									}

									// Load port value
									portText.setText(Integer
											.toString(preferences.getPort()));
								}

							}

							@Override
							public void createContents(Composite parent) {

								parent.setLayout(new GridLayout());

								// Get the system's white color
								Color white = Display.getCurrent()
										.getSystemColor(SWT.COLOR_WHITE);

								Composite nameComp = new Composite(parent,
										SWT.NONE);
								nameComp.setLayoutData(new GridData(
										SWT.BEGINNING, SWT.BEGINNING, false,
										false, 1, 1));
								nameComp.setLayout(new GridLayout(2, false));
								nameComp.setBackground(white);

								// Create a combo for the connection names
								hostCombo = new Combo(parent, SWT.NONE);
								hostCombo.add("localhost");

								BundleContext context = FrameworkUtil
										.getBundle(getClass())
										.getBundleContext();

								// Get the remote services manager
								if (context != null) {
									ServiceReference<IRemoteServicesManager> ref = context
											.getServiceReference(
													IRemoteServicesManager.class);

									// Get the local connections
									IRemoteServicesManager manager = context
											.getService(ref);
									localConnections = manager
											.getLocalConnectionType();

									List<IRemoteConnectionType> types = manager
											.getRemoteConnectionTypes();

									// Find the SSH connections
									for (IRemoteConnectionType type : types) {
										if ("SSH".equals(type.getName())) {

											// Save this type
											if (sshConnections == null) {
												sshConnections = type;
											}

											// Get the names of all current
											// connections
											for (IRemoteConnection connection : type
													.getConnections()) {
												hostCombo.add(
														connection.getName());
											}
										}
									}
								}

								hostCombo.addSelectionListener(
										new SelectionAdapter() {

											@Override
											public void widgetSelected(
													SelectionEvent event) {

												// Update the preferences when
												// the combo is changed.
												currentPreferences
														.setConnectionName(
																hostCombo
																		.getText());
											}
										});

								Button newConnectionButton = new Button(parent,
										SWT.PUSH);
								newConnectionButton.setText("New Connection");

								newConnectionButton.addSelectionListener(
										new SelectionAdapter() {
											@Override
											public void widgetSelected(
													SelectionEvent event) {

												// Get a final reference to the
												// connection type
												final IRemoteConnectionType finalType = sshConnections;

												// Open the Remote Connection
												// Wizard
												Display.getDefault().asyncExec(
														new Runnable() {
															@Override
															public void run() {

																// Get the UI
																// Connection
																// Service
																IRemoteUIConnectionService remoteUI = finalType
																		.getService(
																				IRemoteUIConnectionService.class);

																// Create a UI
																// Connection
																// Wizard
																IRemoteUIConnectionWizard wizard = remoteUI
																		.getConnectionWizard(
																				PlatformUI
																						.getWorkbench()
																						.getActiveWorkbenchWindow()
																						.getShell());

																// If valid,
																// open it and
																// save/open the
																// IRemoteConnection
																if (wizard != null) {
																	// wizard.setConnectionName(host);
																	try {
																		IRemoteConnection newConnection = wizard
																				.open()
																				.save();
																		if (newConnection != null) {
																			hostCombo
																					.add(newConnection
																							.getName());
																		}
																	} catch (RemoteConnectionException e) {
																		// logger.error(getClass().getName()
																		// + "
																		// Exception!",
																		// e);
																	}
																}
															}
														});
											}
										});

								// A composite for the port information
								Composite portComp = new Composite(parent,
										SWT.NONE);
								portComp.setLayoutData(new GridData(
										SWT.BEGINNING, SWT.BEGINNING, false,
										false, 1, 1));
								portComp.setLayout(new GridLayout(2, false));
								portComp.setBackground(white);

								// A label reading "Port:"
								Label portLabel = new Label(portComp,
										SWT.CENTER);
								portLabel.setText("Port:");
								portLabel.setBackground(white);

								// A text box in which the connection's port
								// will be displayed and can be
								// edited.
								portText = new Text(portComp, SWT.BORDER);
								portText.setLayoutData(new GridData(SWT.FILL,
										SWT.FILL, true, true, 1, 1));
								portText.setText(defaultPortNumber);

								// Allow only valid port numbers to be entered
								portText.addVerifyListener(
										new VerifyListener() {

											@Override
											public void verifyText(
													VerifyEvent e) {

												// Get the edited widget
												Text widget = (Text) e.widget;

												// Construct the port number as
												// it would be if the proposed
												// change where applied
												String newValue = widget
														.getText()
														.substring(0, e.start)
														+ e.text
														+ widget.getText()
																.substring(
																		e.end);

												try {

													// If the port is outside
													// the range of port
													// numbers, reject the
													// change
													int port = Integer
															.valueOf(newValue);
													if (port < 0
															|| port > 65535) {
														e.doit = false;
													}
												} catch (NumberFormatException f) {

													// If the port entered text
													// is not an integer, reject
													// the change
													e.doit = false;
												}
											}
										});

								portText.addModifyListener(
										new ModifyListener() {

											@Override
											public void modifyText(
													ModifyEvent e) {
												currentPreferences.setPort(
														Integer.valueOf(portText
																.getText()));
											}

										});

								// Set the current preferences based on the name
								// of the row selected in the table
								currentPreferences = connections.get(table
										.getRow(table.getSelectedRows().get(0))
										.get(0).getValue());

								// Load the setting's defaults into the widgets
								portText.setText(Integer.toString(
										currentPreferences.getPort()));

								// The list of all options in the combo
								String[] comboConnections = hostCombo
										.getItems();

								// Search for the option in the preferences
								// and set it. If the connection is missing,
								// the combo will default to localhost.
								for (int i = 0; i < comboConnections.length; i++) {
									if (currentPreferences.getConnectionName()
											.equals(comboConnections[i])) {
										hostCombo.select(i);
										break;
									}
								}

								selfReference.createDetailsPageContents(parent);
							}
						};

						// Store the page for future use
						pages.put(key, page);
						return page;
					}
				});
			}

			@Override
			protected void createToolBarActions(IManagedForm managedForm) {
				// TODO Auto-generated method stub

			}

		};

		form = new ManagedForm(container);
		block.createContent(form);

		return control;
	}

	/**
	 * Create any additional controls specific to the connection type that
	 * should appear in the details page.
	 * 
	 * @param parent
	 *            The composite in which the controls will be drawn.
	 */
	abstract public void createDetailsPageContents(Composite parent);

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.jface.preference.FieldEditorPreferencePage#createFieldEditors
	 * ()
	 */
	@Override
	protected void createFieldEditors() {
		// No field editors yet, just the table of connections.
	}

	/**
	 * Create an IVizConnectionPreferences appropriate to the subclass,
	 * optionally loaded from a string.
	 * 
	 * @param data
	 *            The string representation of an IVizConnectionPreferences to
	 *            be loaded into the new object. An empty string or null will
	 *            create a new object through the default constructor instead.
	 * 
	 * @return A new IVizConnectionPreferences of the correct type
	 */
	abstract protected IVizConnectionPreferences createVizConnectionPreferences(
			String data);

	/**
	 * Gets the delimiter used when saving/loading connection preferences.
	 * 
	 * @return The delimiter for serializing connection preferences.
	 */
	protected String getConnectionPreferenceDelimiter() {
		return IVizConnectionManager.DEFAULT_CONNECTION_PREFERENCE_DELIMITER;
	}

	/**
	 * Gets the ID of the {@link IEclipsePreferences} node in the store.
	 * Connections will be stored under this node.
	 * 
	 * @return The ID of the connection preferences node.
	 */
	protected abstract String getConnectionsPreferenceNodeId();

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.eavp.viz.service.preferences.AbstractVizPreferencePage#init(
	 * org.eclipse.ui.IWorkbench)
	 */
	@Override
	public void init(IWorkbench workbench) {
		/*
		 * This method is called every time the page is loaded.
		 */

		// Perform the required basic initialization.
		super.init(workbench);

		// Load the current preferences into the table.
		table = createConnectionTable();
		loadPreferences(table);

		return;
	}

	/**
	 * Loads the specified {@link ConnectionTable} based on the current
	 * preferences.
	 * 
	 * @param table
	 *            The table to load the current preferences into.
	 */
	private void loadPreferences(ConnectionTable table) {
		// Get the preference node for connection preferences.
		CustomScopedPreferenceStore store = (CustomScopedPreferenceStore) getPreferenceStore();
		IEclipsePreferences node = store
				.getNode(getConnectionsPreferenceNodeId());

		// Load the current preferences into the table.
		String[] existingKeys;
		try {
			existingKeys = node.keys();
			for (String key : existingKeys) {
				int index = table.addRow();
				List<VizEntry> row = table.getRow(index);

				// Update the key/name in the table.
				row.get(0).setValue(key);
				// Update the other properties.
				connections.put(key,
						createVizConnectionPreferences(node.get(key, null)));

				// String[] preferences = unserializeConnectionPreferences(
				// node.get(key, null));
				// for (int i = 0; i < preferences.length; i++) {
				// row.get(i + 1).setValue(preferences[i]);
				// }
			}
		} catch (BackingStoreException e) {
			e.printStackTrace();
		}

		return;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jface.preference.FieldEditorPreferencePage#performOk()
	 */
	@Override
	public boolean performOk() {
		/*
		 * This method is called every time OK or Apply is clicked.
		 */
		boolean ok = super.performOk();

		// Apply the preferences from the table.
		savePreferences(table);

		return ok;
	}

	/**
	 * Saves the preferences from the specified {@link ConnectionTable}.
	 * 
	 * @param table
	 *            The table containing the new connection preferences to store.
	 */
	private void savePreferences(ConnectionTable table) {
		// Get the preference node for connection preferences.
		CustomScopedPreferenceStore store = (CustomScopedPreferenceStore) getPreferenceStore();
		IEclipsePreferences node = store
				.getNode(getConnectionsPreferenceNodeId());

		// Get a set of connection names from the table. At the end of this
		// operation, only the connections in the table will be in the
		// preferences.
		Set<String> updated = new HashSet<String>(table.getConnectionNames());
		List<VizEntry> row;

		// Update old connections in the preferences.
		String[] existingNames;
		try {
			existingNames = node.keys();
			for (String name : existingNames) {
				IVizConnectionPreferences preferences = connections.get(name);
				// If the connection name has a row in the table, it will need
				// to be updated in the preferences.
				if (preferences != null) {
					node.put(name, preferences
							.serialize(getConnectionPreferenceDelimiter()));
					updated.remove(name);
				}
				// Otherwise, the connection was removed from the table and
				// should be removed from the preferences.
				else {
					node.remove(name);
				}
			}
		} catch (BackingStoreException e) {
			e.printStackTrace();
		}

		// Add all new connections to the preferences.
		for (String name : updated) {
			IVizConnectionPreferences preferences = connections.get(name);
			node.put(name,
					preferences.serialize(getConnectionPreferenceDelimiter()));
		}

		return;
	}

	/**
	 * Serializes the specified connection preferences into a string that can be
	 * stored in the preferences.
	 * 
	 * @param connection
	 *            The table row for the connection.
	 * @return The serialized connection preferences.
	 */
	protected String serializeConnectionPreferences(List<VizEntry> connection) {
		String preferences = "";
		String delimiter = getConnectionPreferenceDelimiter();

		if (connection.size() >= 2) {
			// Add the first preference.
			preferences += connection.get(1).getValue();
			// Add all remaining preferences.
			for (int i = 2; i < connection.size(); i++) {
				preferences += delimiter + connection.get(i).getValue();
			}
		}

		return preferences;
	}

	/**
	 * Converts the serialized connection preferences into an array of string
	 * values that can be used to update a row in the connection table.
	 * 
	 * @param preferences
	 *            The serialized connection preferences.
	 * @return An array of all preferences for the row.
	 */
	protected String[] unserializeConnectionPreferences(String preferences) {
		return preferences.split(getConnectionPreferenceDelimiter(), -1);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.jface.preference.FieldEditorPreferencePage#performDefaults()
	 */
	@Override
	public void performDefaults() {

		// Delete the first row for each row in the table
		for (int i : table.getRowIds()) {
			table.deleteRow(0);
		}

		// Add a default row
		table.addRow();
	}

	@Override
	public void selectionChanged(SelectionChangedEvent event) {
		form.fireSelectionChanged(tablePart, event.getSelection());
	}

	@Override
	public void update(IVizUpdateable component) {

		ArrayList<String> oldConnections = new ArrayList<String>();

		if (component == table) {
			if (table.numberOfRows() < connections.keySet().size()) {
				for (String connection : connections.keySet()) {

					boolean inTable = false;

					for (int i = 0; i < table.numberOfRows(); i++) {
						if (connection
								.equals(table.getRow(i).get(0).getValue())) {
							inTable = true;
							break;
						}
					}

					if (!inTable) {
						oldConnections.add(connection);
					}
				}

				for (String connection : oldConnections) {
					connections.remove(connection);
				}
			}

			else if (table.numberOfRows() > connections.keySet().size()) {
				for (int i = 0; i < table.numberOfRows(); i++) {

					String name = table.getRow(i).get(0).getValue();

					if (!connections.keySet().contains(name)) {

						IVizConnectionPreferences preferences = createVizConnectionPreferences(
								"");
						preferences.setName(name);
						connections.put(name, preferences);
					}
				}
			}

			if (table.getSelectedRows() != null) {
				String selectedConnectionName = table
						.getRow(table.getSelectedRows().get(0)).get(0)
						.getValue();
				currentPreferences = connections.get(selectedConnectionName);
			}
		}

	}
}
