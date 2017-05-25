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
package org.eclipse.eavp.viz.service.paraview.connections;

import java.util.ArrayList;

import org.eclipse.eavp.viz.datastructures.ui.IVizEntryContentProvider;
import org.eclipse.eavp.viz.datastructures.ui.VizEntry;
import org.eclipse.eavp.viz.service.connections.preferences.ConnectionTable;
import org.eclipse.eavp.viz.service.connections.preferences.IVizConnectionPreferences;
import org.eclipse.eavp.viz.service.connections.preferences.KeyEntry;
import org.eclipse.eavp.viz.service.connections.preferences.KeyEntryContentProvider;
import org.eclipse.eavp.viz.service.connections.preferences.RCPConnectionPreferencePage;
import org.eclipse.eavp.viz.service.paraview.ParaViewVizService;
import org.eclipse.remote.core.IRemoteConnection;
import org.eclipse.remote.ui.widgets.RemoteFileWidget;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.events.VerifyEvent;
import org.eclipse.swt.events.VerifyListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.IWorkbench;

/**
 * This class provides a preference page for the ParaView visualization
 * service's connections.
 * 
 * @author Jordan Deyton
 *
 */
public class ParaViewConnectionPreferencePage
		extends RCPConnectionPreferencePage {
	/**
	 * The widget for selecting the ParaView executable.
	 */
	RemoteFileWidget executableWidget;

	/**
	 * The widget for selecting the paraview http server script.
	 */
	RemoteFileWidget scriptWidget;

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
		return ParaViewVizService.CONNECTIONS_NODE_ID;
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
		setDescription("ParaView Visualization Preferences");
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

			// A composite for the visualizer port information
			Composite visPortComp = new Composite(parent, SWT.NONE);
			visPortComp.setLayoutData(
					new GridData(SWT.FILL, SWT.FILL, false, false, 5, 5));
			visPortComp.setLayout(new GridLayout(2, false));
			visPortComp.setBackground(white);

			// A label reading "Visualizer Port:"
			Label visPortLabel = new Label(visPortComp, SWT.CENTER);
			visPortLabel.setText("Visualizer Port:");
			visPortLabel.setBackground(white);

			// A text box in which the visualizer's port will be displayed and
			// can be edited.
			Text visPortText = new Text(visPortComp, SWT.BORDER);
			visPortText.setLayoutData(
					new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));

			// Get a final reference to the text
			final Text finalVisPortTextRef = visPortText;

			// Allow only valid port numbers to be
			// entered
			visPortText.addVerifyListener(new VerifyListener() {

				@Override
				public void verifyText(VerifyEvent e) {

					// Get the edited widget
					Text widget = (Text) e.widget;

					// Construct the port number
					// as
					// it would be if the
					// proposed
					// change where applied
					String newValue = widget.getText().substring(0, e.start)
							+ e.text + widget.getText().substring(e.end);

					try {

						// If the port is
						// outside
						// the range of port
						// numbers or equal to the regular port, reject the
						// change
						int port = Integer.valueOf(newValue);
						if (port < 0 || port > 65535
								|| newValue.equals(portText.getText())) {
							e.doit = false;
						}
					} catch (NumberFormatException f) {

						// If the port entered
						// text
						// is not an integer,
						// reject
						// the change
						e.doit = false;
					}
				}
			});

			visPortText.addModifyListener(new ModifyListener() {

				@Override
				public void modifyText(ModifyEvent e) {
					((ParaViewVizConnectionPreferences) currentPreferences)
							.setVisualizerPort(Integer
									.valueOf(finalVisPortTextRef.getText()));
				}

			});

			// Load the setting's defaults into the
			// widgets
			visPortText.setText(Integer.toString(
					((ParaViewVizConnectionPreferences) currentPreferences)
							.getVisualizerPort()));

			// Create a label with directions for the ParaView executable
			Label executableLabel = new Label(parent, SWT.CENTER);
			executableLabel.setText("Select the ParaView executable:");
			executableLabel.setBackground(white);

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
			ParaViewVizConnectionPreferences castPreferences = (ParaViewVizConnectionPreferences) currentPreferences;
			String currentPath = ((ParaViewVizConnectionPreferences) currentPreferences)
					.getExecutablePath();

			executableWidget.setLayoutData(
					new GridData(SWT.FILL, SWT.FILL, true, false, 1, 1));

			executableWidget.addModifyListener(new ModifyListener() {

				@Override
				public void modifyText(ModifyEvent e) {
					((ParaViewVizConnectionPreferences) currentPreferences)
							.setExecutablePath(
									executableWidget.getLocationPath());
				}

			});

			// Create a label with directions for the server script
			Label scriptLabel = new Label(parent, SWT.CENTER);
			scriptLabel.setText("Select the http_pvw_server.py file:");
			scriptLabel.setBackground(white);

			scriptWidget = new RemoteFileWidget(parent, SWT.NONE, 0, "9702",
					"9703");

			// Get the current preferences cast to the correct subclass
			String currentScriptPath = ((ParaViewVizConnectionPreferences) currentPreferences)
					.getScriptPath();

			scriptWidget.setLayoutData(
					new GridData(SWT.FILL, SWT.FILL, true, false, 1, 1));

			scriptWidget.addModifyListener(new ModifyListener() {

				@Override
				public void modifyText(ModifyEvent e) {
					((ParaViewVizConnectionPreferences) currentPreferences)
							.setScriptPath(scriptWidget.getLocationPath());
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
			visPortText.setText(
					Integer.toString(castPreferences.getVisualizerPort()));
			executableWidget.setLocationPath(currentPath);
			scriptWidget.setLocationPath(castPreferences.getScriptPath());

			// Reset the current preferences execution path to its original
			// value
			// before being reset to the default by the change in connection
			((ParaViewVizConnectionPreferences) currentPreferences)
					.setExecutablePath(currentPath);
			((ParaViewVizConnectionPreferences) currentPreferences)
					.setScriptPath(currentScriptPath);

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
			return new ParaViewVizConnectionPreferences(data);
		} else {
			return new ParaViewVizConnectionPreferences();
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
		scriptWidget.setConnection(currentConnection);
	}

}
