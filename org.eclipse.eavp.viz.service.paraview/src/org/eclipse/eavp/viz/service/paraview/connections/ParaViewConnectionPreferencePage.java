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

import org.eclipse.eavp.viz.datastructures.BasicVizEntryContentProvider;
import org.eclipse.eavp.viz.datastructures.IVizEntryContentProvider;
import org.eclipse.eavp.viz.datastructures.VizEntry;
import org.eclipse.eavp.viz.service.connections.preferences.ConnectionTable;
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
				provider.setDefaultValue("");

				// Create an entry for the path to the server script
				VizEntry entry = new VizEntry(provider);
				entry.setName("Server Script Path");

				// Add the custom entry to the template and return it
				template.add(entry);
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
