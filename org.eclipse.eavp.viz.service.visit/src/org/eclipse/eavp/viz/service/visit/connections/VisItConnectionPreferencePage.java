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
package org.eclipse.eavp.viz.service.visit.connections;

import java.util.ArrayList;

import org.eclipse.eavp.viz.datastructures.ui.IVizEntryContentProvider;
import org.eclipse.eavp.viz.datastructures.ui.VizEntry;
import org.eclipse.eavp.viz.service.connections.preferences.ConnectionTable;
import org.eclipse.eavp.viz.service.connections.preferences.IVizConnectionPreferences;
import org.eclipse.eavp.viz.service.connections.preferences.KeyEntry;
import org.eclipse.eavp.viz.service.connections.preferences.KeyEntryContentProvider;
import org.eclipse.eavp.viz.service.connections.preferences.VizConnectionPreferencePage;
import org.eclipse.eavp.viz.service.visit.VisItVizService;
import org.eclipse.remote.core.IRemoteConnection;
import org.eclipse.remote.ui.widgets.RemoteFileWidget;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.IWorkbench;

/**
 * This class provides a preferences page for the VisIt viz service's
 * connections.
 * 
 * @author Jordan Deyton
 * 
 */
public class VisItConnectionPreferencePage extends VizConnectionPreferencePage {

	/**
	 * The widget for selecting the VisIt executable.
	 */
	RemoteFileWidget executableWidget;

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.eavp.viz.service.connections.preferences.
	 * VizConnectionPreferencePage#createConnectionTable()
	 */
	@Override
	protected ConnectionTable createConnectionTable() {
		return new ConnectionTable() {

			/*
			 * (non-Javadoc)
			 * 
			 * @see org.eclipse.eavp.viz.service.connections.preferences.
			 * ConnectionTable#createConnectionTemplate()
			 */
			@Override
			protected ArrayList<VizEntry> createConnectionTemplate() {

				// // Create a default template
				// ArrayList<VizEntry> template =
				// super.createConnectionTemplate();
				//
				// // Create a provider for arbitrary strings
				// IVizEntryContentProvider provider = new
				// BasicVizEntryContentProvider();
				// provider.setDefaultValue(System.getProperty("user.home"));
				//
				// // Create a port provider
				// PortEntryContentProvider portProvider = new
				// PortEntryContentProvider();
				// portProvider.setDefaultValue("9601");
				//
				// // Create a provider for operating system names
				// IVizEntryContentProvider osProvider = new
				// BasicVizEntryContentProvider();
				// provider.setDefaultValue("");
				// ArrayList<String> osNames = new ArrayList<String>();
				// osNames.add("");
				// osNames.add("Linux");
				// osNames.add("OSx");
				// osNames.add("Windows");
				// osProvider.setAllowedValues(osNames);
				//
				// // Create a provider for arbitrary strings with the latest
				// // paraview version as the default value
				// IVizEntryContentProvider versionProvider = new
				// BasicVizEntryContentProvider();
				// versionProvider.setDefaultValue("5.0");
				//
				// // Create an entry for the path to the server script
				// VizEntry serverEntry = new VizEntry(provider);
				// serverEntry.setName("Server Script Path");
				//
				// // Create an entry for the web visualizer port
				// VizEntry portEntry = new PortEntry(portProvider);
				// portEntry.setName("Visualizer Port");
				//
				// // Create an entry for the remote OS
				// VizEntry osEntry = new VizEntry(osProvider);
				// osEntry.setName("Remote OS");
				//
				// // Create an entry for the version number
				// VizEntry versionEntry = new VizEntry(versionProvider);
				// versionEntry.setName("Remote ParaView Version Number");
				//
				// for (VizEntry entry : template) {
				// if ("Path".equals(entry.getName())) {
				//
				// // The default path for the ParaView installation
				// String defaultPath = "";
				//
				// // Get the name of the operating system
				// String os = System.getProperty("os.name", "generic")
				// .toLowerCase(Locale.ENGLISH);
				//
				// // Set the default path to a standard installation
				// // directory for that kind of OS
				// if (os.indexOf("darwin") >= 0
				// || os.indexOf("mac") >= 0) {
				// defaultPath = "/Applications";
				// } else if (os.indexOf("win") >= 0) {
				// // TODO Find the default windows ParaView
				// // installation location
				// } else if (os.indexOf("nux") >= 0) {
				// defaultPath = System.getProperty("user.home");
				// }
				//
				// entry.setValue(defaultPath);
				// }
				// }
				//
				// // Add the custom entry to the template and return it
				// template.add(serverEntry);
				// template.add(portEntry);
				// template.add(osEntry);
				// template.add(versionEntry);
				// return template;

				ArrayList<VizEntry> template = new ArrayList<VizEntry>();

				IVizEntryContentProvider contentProvider;

				// ---- name ---- //
				KeyEntryContentProvider keyContentProvider = new KeyEntryContentProvider(
						keyManager);
				VizEntry keyEntry = new KeyEntry(keyContentProvider);
				keyEntry.setName("Connection Name");
				keyEntry.setDescription(
						"The name of the connection. This must be unique.");
				template.add(keyEntry);

				// IConfigurationElement[] elements =
				// Platform.getExtensionRegistry()
				// .getConfigurationElementsFor(
				// "org.eclipse.eavp.viz.service.IVizServiceFactory");
				// try {
				// IVizServiceFactory factory = (IVizServiceFactory)
				// elements[0].createExecutableExtension("class");
				// for(String serviceName : factory.getServiceNames()) {
				// allowedValues.add(factory.get(serviceName).getName());
				// }
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

				return template;
			}
		};
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.eavp.viz.service.connections.preferences.
	 * VizConnectionPreferencePage#getConnectionsPreferenceNodeId()
	 */
	@Override
	protected String getConnectionsPreferenceNodeId() {
		return VisItVizService.CONNECTIONS_NODE_ID;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.eavp.viz.service.connections.preferences.
	 * VizConnectionPreferencePage#init(org.eclipse.ui.IWorkbench)
	 */
	@Override
	public void init(IWorkbench workbench) {
		super.init(workbench);

		// Replace the default title.
		setDescription("VisIt Visualization Preferences");
	}

	@Override
	public void createDetailsPageContents(Composite parent) {

		// Get the system's white color
		Color white = Display.getCurrent().getSystemColor(SWT.COLOR_WHITE);

		// A composite for the proxy information
		Composite proxyComp = new Composite(parent, SWT.NONE);
		proxyComp.setLayoutData(
				new GridData(SWT.BEGINNING, SWT.BEGINNING, false, false, 1, 1));
		proxyComp.setLayout(new GridLayout(2, false));
		proxyComp.setBackground(white);

		// A label reading "Proxy:"
		Label proxyLabel = new Label(proxyComp, SWT.CENTER);
		proxyLabel.setText("Proxy:");
		proxyLabel.setBackground(white);

		// A text box in which the connection's port will be displayed and can
		// be
		// edited.
		Text proxyText = new Text(proxyComp, SWT.BORDER);
		proxyText.setLayoutData(
				new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));

		// A composite for the proxy port information
		Composite proxyPortComp = new Composite(parent, SWT.NONE);
		proxyPortComp.setLayoutData(
				new GridData(SWT.BEGINNING, SWT.BEGINNING, false, false, 1, 1));
		proxyPortComp.setLayout(new GridLayout(2, false));
		proxyPortComp.setBackground(white);

		// A label reading "Proxy:"
		Label proxyPortLabel = new Label(proxyComp, SWT.CENTER);
		proxyPortLabel.setText("Proxy Port:");
		proxyPortLabel.setBackground(white);

		// A text box in which the connection's port will be displayed and can
		// be
		// edited.
		Text proxyPortText = new Text(proxyComp, SWT.BORDER);
		proxyPortText.setLayoutData(
				new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));

		// IRemoteServices services = RemoteServices
		// .getRemoteServices("org.eclipse.remote.JSch");
		// IRemoteUIServices uiServices = RemoteUIServices
		// .getRemoteUIServices(services);
		// IRemoteUIFileManager manager = uiServices.getUIFileManager();
		// manager.showConnections(true);
		// String file = manager.browseFile(shell,
		// "Select the remote files you would like to view in VisIt", ".",
		// SWT.MULTI);

		resetFileWidgetConnection();

		// Change the connection when the host combo is changed
		hostCombo.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				resetFileWidgetConnection();
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				// Nothing to do
			}

		});

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.eavp.viz.service.connections.preferences.
	 * VizConnectionPreferencePage#createVizConnectionPreferences()
	 */
	@Override
	protected IVizConnectionPreferences createVizConnectionPreferences() {
		return new VisItVizConnectionPreferences();
	}

	private void resetFileWidgetConnection() {

		// Get the user selected connection
		String remoteConnectionName = hostCombo.getText();

		// The new connection to set to the widget
		IRemoteConnection currentConnection;

		// Get a remote/local connection as required
		if (!"localhost".equals(remoteConnectionName)) {
			currentConnection = sshConnections
					.getConnection(remoteConnectionName);
		} else {
			currentConnection = localConnections.getConnections().get(0);
		}

		// Set the selected connection to the widget
		executableWidget.setConnection(currentConnection);
	}

}
