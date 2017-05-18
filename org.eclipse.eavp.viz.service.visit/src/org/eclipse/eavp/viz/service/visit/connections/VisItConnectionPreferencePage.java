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
import org.eclipse.eavp.viz.service.connections.preferences.RCPConnectionPreferencePage;
import org.eclipse.eavp.viz.service.visit.VisItVizService;
import org.eclipse.remote.core.IRemoteConnection;
import org.eclipse.remote.ui.widgets.RemoteFileWidget;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
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
public class VisItConnectionPreferencePage extends RCPConnectionPreferencePage {

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

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.eavp.viz.service.connections.preferences.
	 * VizConnectionPreferencePage#createDetailsPageContents(org.eclipse.swt.
	 * widgets.Composite)
	 */
	@Override
	public void createDetailsPageContents(Composite parent) {

		// Set the current preferences based on the name
		// of the row selected in the table
		ArrayList<Integer> selectedRows = table.getSelectedRows();
		ArrayList<VizEntry> selectedRow = null;
		if (!selectedRows.isEmpty()) {
			selectedRow = table.getRow(selectedRows.get(0));
		}

		// Only draw something if a row is selected
		if (selectedRow != null) {

			super.createDetailsPageContents(parent);

			// Get the system's white color
			Color white = Display.getCurrent().getSystemColor(SWT.COLOR_WHITE);

			// A composite for the proxy information
			Composite proxyComp = new Composite(parent, SWT.NONE);
			proxyComp.setLayoutData(
					new GridData(SWT.FILL, SWT.FILL, false, false, 5, 5));
			proxyComp.setLayout(new GridLayout(2, false));
			proxyComp.setBackground(white);

			// A label reading "Proxy:"
			Label proxyLabel = new Label(proxyComp, SWT.CENTER);
			proxyLabel.setText("Proxy:");
			proxyLabel.setBackground(white);

			// A text box in which the connection's port will be displayed and
			// can
			// be edited.
			Text proxyText = new Text(proxyComp, SWT.BORDER);
			proxyText.setLayoutData(
					new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));

			// A composite for the proxy port information
			Composite proxyPortComp = new Composite(parent, SWT.NONE);
			proxyPortComp.setLayoutData(new GridData(SWT.BEGINNING,
					SWT.BEGINNING, false, false, 1, 1));
			proxyPortComp.setLayout(new GridLayout(2, false));
			proxyPortComp.setBackground(white);

			// A label reading "Proxy:"
			Label proxyPortLabel = new Label(proxyComp, SWT.CENTER);
			proxyPortLabel.setText("Proxy Port:");
			proxyPortLabel.setBackground(white);

			// A text box in which the connection's port will be displayed and
			// can
			// be
			// edited.
			Text proxyPortText = new Text(proxyComp, SWT.BORDER);
			proxyPortText.setLayoutData(
					new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));

			// TODO Add remote file button
			// IRemoteServices services = RemoteServices
			// .getRemoteServices("org.eclipse.remote.JSch");
			// IRemoteUIServices uiServices = RemoteUIServices
			// .getRemoteUIServices(services);
			// IRemoteUIFileManager manager = uiServices.getUIFileManager();
			// manager.showConnections(true);
			// String file = manager.browseFile(shell,
			// "Select the remote files you would like to view in VisIt", ".",
			// SWT.MULTI);

			executableWidget = new RemoteFileWidget(parent, SWT.NONE, 0, "9700",
					"9701");

			// Get the current preferences cast to the correct subclass
			VisItVizConnectionPreferences castPreferences = (VisItVizConnectionPreferences) currentPreferences;
			String currentPath = ((VisItVizConnectionPreferences) currentPreferences)
					.getExecutablePath();

			executableWidget.setLayoutData(
					new GridData(SWT.FILL, SWT.FILL, true, false, 1, 1));

			executableWidget.addModifyListener(new ModifyListener() {

				@Override
				public void modifyText(ModifyEvent e) {
					((VisItVizConnectionPreferences) currentPreferences)
							.setExecutablePath(
									executableWidget.getLocationPath());
				}

			});

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

			// Set the UI elements correctly for the given preferences
			portText.setText(Integer.toString(currentPreferences.getPort()));
			portText.redraw();
			proxyText.setText(castPreferences.getProxy());
			proxyPortText
					.setText(Integer.toString(castPreferences.getProxyPort()));
			executableWidget.setLocationPath(currentPath);

			// Reset the current preferences execution path to its original
			// value
			// before being reset to the default by the change in connection
			((VisItVizConnectionPreferences) currentPreferences)
					.setExecutablePath(currentPath);

		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.eavp.viz.service.connections.preferences.
	 * VizConnectionPreferencePage#createVizConnectionPreferences()
	 */
	@Override
	protected IVizConnectionPreferences createVizConnectionPreferences(
			String data) {

		// If a data string was provided, load it into a preferences set and
		// return it. Otherwise, return a new default preferences.
		if (data != null && !"".equals(data)) {
			return new VisItVizConnectionPreferences(data);
		} else {
			return new VisItVizConnectionPreferences();
		}
	}

	/**
	 * Set the executable path widget's connection based on the content of the
	 * host combo.
	 */
	private void resetFileWidgetConnection() {

		// Get the user selected connection
		String remoteConnectionName = hostCombo.getText();

		// The new connection to set to the widget
		IRemoteConnection currentConnection;

		// Get a remote/local connection as required
		if (!"localhost".equals(remoteConnectionName)
				&& !remoteConnectionName.isEmpty()) {
			currentConnection = sshConnections
					.getConnection(remoteConnectionName);
		} else {
			currentConnection = localConnections.getConnections().get(0);
		}

		// Set the selected connection to the widget
		executableWidget.setConnection(currentConnection);
	}

}
