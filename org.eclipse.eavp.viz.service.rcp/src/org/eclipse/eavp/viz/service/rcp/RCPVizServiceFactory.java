/*******************************************************************************
 * Copyright (c) 2017 UT-Battelle, LLC.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   Robert Smith - Initial API and implementation and/or
 *     initial documentation
 *******************************************************************************/
package org.eclipse.eavp.viz.service.rcp;

import org.eclipse.eavp.viz.service.BasicVizServiceFactory;
import org.eclipse.eavp.viz.service.IVizService;
import org.eclipse.ui.IFileEditorMapping;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.internal.registry.EditorDescriptor;
import org.eclipse.ui.internal.registry.EditorRegistry;
import org.eclipse.ui.internal.registry.FileEditorMapping;

/**
 * An extension of BasicVizServiceFactory that registers incoming services with
 * the Eclipse Workbench.
 * 
 * @author Robert Smith
 *
 */
public class RCPVizServiceFactory extends BasicVizServiceFactory {

	@Override
	public void register(IVizService service) {
		super.register(service);

		// Register the plot editor as default editor for all file
		// extensions handled by the new viz service
		for (String ext : service.getSupportedExtensions()) {
			EditorRegistry editorReg = (EditorRegistry) PlatformUI
					.getWorkbench().getEditorRegistry();
			EditorDescriptor editor = (EditorDescriptor) editorReg
					.findEditor("org.eclipse.eavp.viz.service.PlotEditor");
			FileEditorMapping mapping = new FileEditorMapping(ext);
			mapping.addEditor(editor);
			mapping.setDefaultEditor(editor);

			IFileEditorMapping[] mappings = editorReg.getFileEditorMappings();
			FileEditorMapping[] newMappings = new FileEditorMapping[mappings.length
					+ 1];
			for (int i = 0; i < mappings.length; i++) {
				newMappings[i] = (FileEditorMapping) mappings[i];
			}
			newMappings[mappings.length] = mapping;
			editorReg.setFileEditorMappings(newMappings);
		}
	}
}
