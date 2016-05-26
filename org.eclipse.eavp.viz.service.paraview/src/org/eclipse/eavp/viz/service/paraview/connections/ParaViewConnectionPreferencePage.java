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
import java.util.Locale;

import org.eclipse.eavp.viz.datastructures.BasicVizEntryContentProvider;
import org.eclipse.eavp.viz.datastructures.IVizEntryContentProvider;
import org.eclipse.eavp.viz.datastructures.VizEntry;
import org.eclipse.eavp.viz.service.connections.preferences.ConnectionTable;
import org.eclipse.eavp.viz.service.connections.preferences.PortEntry;
import org.eclipse.eavp.viz.service.connections.preferences.PortEntryContentProvider;
import org.eclipse.eavp.viz.service.connections.preferences.VizConnectionPreferencePage;
import org.eclipse.eavp.viz.service.paraview.ParaViewVizService;
import org.eclipse.ui.IWorkbench;

/**
 * This class provides a preference page for the ParaView visualization
 * service's connections.
 * 
 * @author Jordan Deyton
 *
 */
public class ParaViewConnectionPreferencePage
		extends VizConnectionPreferencePage {

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

				// Create a default template
				ArrayList<VizEntry> template = super.createConnectionTemplate();

				// Create a provider for arbitrary strings
				IVizEntryContentProvider provider = new BasicVizEntryContentProvider();
				provider.setDefaultValue(System.getProperty("user.home"));

				// Create a port provider
				PortEntryContentProvider portProvider = new PortEntryContentProvider();
				portProvider.setDefaultValue("9601");

				// Create a provider for operating system names
				IVizEntryContentProvider osProvider = new BasicVizEntryContentProvider();
				provider.setDefaultValue("");
				ArrayList<String> osNames = new ArrayList<String>();
				osNames.add("");
				osNames.add("Linux");
				osNames.add("OSx");
				osNames.add("Windows");
				osProvider.setAllowedValues(osNames);

				// Create a provider for arbitrary strings with the latest
				// paraview version as the default value
				IVizEntryContentProvider versionProvider = new BasicVizEntryContentProvider();
				versionProvider.setDefaultValue("5.0");

				// Create an entry for the path to the server script
				VizEntry serverEntry = new VizEntry(provider);
				serverEntry.setName("Server Script Path");

				// Create an entry for the web visualizer port
				VizEntry portEntry = new PortEntry(portProvider);
				portEntry.setName("Visualizer Port");

				// Create an entry for the remote OS
				VizEntry osEntry = new VizEntry(osProvider);
				osEntry.setName("Remote OS");

				// Create an entry for the version number
				VizEntry versionEntry = new VizEntry(versionProvider);
				versionEntry.setName("Remote ParaView Version Number");

				for (VizEntry entry : template) {
					if ("Path".equals(entry.getName())) {

						// The default path for the ParaView installation
						String defaultPath = "";

						// Get the name of the operating system
						String os = System.getProperty("os.name", "generic")
								.toLowerCase(Locale.ENGLISH);

						// Set the default path to a standard installation
						// directory for that kind of OS
						if (os.indexOf("darwin") >= 0
								|| os.indexOf("mac") >= 0) {
							defaultPath = "/Applications";
						} else if (os.indexOf("win") >= 0) {
							// TODO Find the default windows ParaView
							// installation location
						} else if (os.indexOf("nux") >= 0) {
							defaultPath = System.getProperty("user.home");
						}

						entry.setValue(defaultPath);
					}
				}

				// Add the custom entry to the template and return it
				template.add(serverEntry);
				template.add(portEntry);
				template.add(osEntry);
				template.add(versionEntry);
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

}
