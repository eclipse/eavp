/*******************************************************************************
 * Copyright (c) 2017 UT-Battelle, LLC.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   Robert Smith - Initial API and implementation and/or initial documentation
 *   
 *******************************************************************************/
package org.eclipse.eavp.viz.service.connections.preferences;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.eavp.viz.datastructures.ui.VizEntry;
import org.eclipse.remote.core.IRemoteConnection;
import org.eclipse.remote.core.IRemoteConnectionType;
import org.eclipse.remote.core.IRemoteServicesManager;
import org.eclipse.remote.core.exception.RemoteConnectionException;
import org.eclipse.remote.ui.IRemoteUIConnectionService;
import org.eclipse.remote.ui.IRemoteUIConnectionWizard;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.PlatformUI;
import org.osgi.framework.BundleContext;
import org.osgi.framework.FrameworkUtil;
import org.osgi.framework.ServiceReference;

/**
 * An extension of VizConnectionPreferencePage which adds a combo for selecting
 * a PTP connection to base the connection preferences on.
 * 
 * @author Robert Smith
 *
 */
abstract public class RCPConnectionPreferencePage
		extends VizConnectionPreferencePage {

	/**
	 * The combo box containing list of names for available connections.
	 */
	protected Combo hostCombo;

	@Override
	public void createDetailsPageContents(Composite parent) {

		// Set the current preferences based on the name
		// of the row selected in the table
		ArrayList<Integer> selectedRows = table.getSelectedRows();
		ArrayList<VizEntry> selectedRow = null;
		if (!selectedRows.isEmpty()) {
			selectedRow = table.getRow(selectedRows.get(0));
		}

		if (selectedRow != null) {

			// Create a combo for the connection names
			hostCombo = new Combo(parent, SWT.NONE);
			hostCombo.add("localhost");

			BundleContext context = FrameworkUtil.getBundle(getClass())
					.getBundleContext();

			// Get the remote services manager
			if (context != null) {
				ServiceReference<IRemoteServicesManager> ref = context
						.getServiceReference(IRemoteServicesManager.class);

				// Get the local connections
				IRemoteServicesManager manager = context.getService(ref);
				localConnections = manager.getLocalConnectionType();

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
							hostCombo.add(connection.getName());
						}
					}
				}
			}

			hostCombo.addSelectionListener(new SelectionAdapter() {

				@Override
				public void widgetSelected(SelectionEvent event) {

					// Update the preferences
					// when
					// the combo is changed.
					((RCPVizConnectionPreferences) currentPreferences)
							.setConnectionName(hostCombo.getText());
				}
			});

			// Add a button to create a new PTP
			// connection
			Button newConnectionButton = new Button(parent, SWT.PUSH);
			newConnectionButton.setText("New Connection");

			newConnectionButton.addSelectionListener(new SelectionAdapter() {
				@Override
				public void widgetSelected(SelectionEvent event) {

					// Get a final reference to
					// the
					// connection type
					final IRemoteConnectionType finalType = sshConnections;

					// Open the Remote
					// Connection
					// Wizard
					Display.getDefault().asyncExec(new Runnable() {
						@Override
						public void run() {

							// Get
							// the
							// UI
							// Connection
							// Service
							IRemoteUIConnectionService remoteUI = finalType
									.getService(
											IRemoteUIConnectionService.class);

							// Create
							// a
							// UI
							// Connection
							// Wizard
							IRemoteUIConnectionWizard wizard = remoteUI
									.getConnectionWizard(
											PlatformUI.getWorkbench()
													.getActiveWorkbenchWindow()
													.getShell());

							// If
							// valid,
							// open
							// it
							// and
							// save/open
							// the
							// IRemoteConnection
							if (wizard != null) {
								try {
									IRemoteConnection newConnection = wizard
											.open().save();
									if (newConnection != null) {
										hostCombo.add(newConnection.getName());
									}
								} catch (RemoteConnectionException e) {
									// logger.error(getClass().getName()
									// +
									// "Exception!",
									// e);
								}
							}
						}
					});

				}
			});

			// The list of all options in the combo
			String[] comboConnections = hostCombo.getItems();

			// Search for the option in the preferences
			// and set it. If the connection is missing,
			// the combo will default to localhost.
			for (int i = 0; i < comboConnections.length; i++) {
				if (((RCPVizConnectionPreferences) currentPreferences)
						.getConnectionName().equals(comboConnections[i])) {
					hostCombo.select(i);
					break;
				}
			}
		}

	}

}
