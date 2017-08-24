/*******************************************************************************
 * Copyright (c) 2017 UT-Battelle, LLC.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Robert Smith - initial API and implementation 
 *      and/or initial documentation
 *******************************************************************************/
package org.eclipse.eavp.viz.service.rcp.csv;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.eavp.viz.service.IPlot;
import org.eclipse.eavp.viz.service.IVizCanvas;
import org.eclipse.eavp.viz.service.VizProgressMonitor;
import org.eclipse.eavp.viz.service.csv.CSVPlot;
import org.eclipse.eavp.viz.service.rcp.RCPDrawHandler;
import org.eclipse.swt.SWT;
import org.eclipse.swt.SWTException;
import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IFileEditorInput;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.editors.text.TextEditor;
import org.eclipse.ui.part.MultiPageEditorPart;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * A DrawHandler which will draw the contents of a CSVPlot to an SWT Composite
 * in an Eclipse Workbench.
 * 
 * @author Robert Smith
 *
 */
public class RCPCSVDrawHandler extends RCPDrawHandler {

	/**
	 * Logger for handling event messages and other information.
	 */
	private static final Logger logger = LoggerFactory
			.getLogger(RCPCSVDrawHandler.class);

	/**
	 * The TextEditor which contains the plot's data in an editable text form.
	 */
	private IEditorPart editor;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.eavp.viz.service.rcp.RCPDrawHandler#createAdditionalPage(org.
	 * eclipse.ui.part.MultiPageEditorPart, org.eclipse.ui.IFileEditorInput,
	 * int)
	 */
	@Override
	public String createAdditionalPage(MultiPageEditorPart parent,
			IFileEditorInput file, int pageNum) {

		// Create the specified page
		switch (pageNum) {

		// Page 2 is the file's data displayed in text
		case 1:

			// Create a text editor with the file as input and add its page with
			// the name Data
			try {
				editor = (IEditorPart) new TextEditor();
				parent.addPage(editor, file);
				return "Data";
			} catch (PartInitException e) {
				logger.error(
						"Error initializing text editor for CSV Plot Editor.");
			}
			break;
		}

		// If the page number is not supported, return null
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.eavp.viz.service.drawhandler.IDrawHandler#draw(org.eclipse.
	 * eavp.viz.service.IVizCanvas)
	 */
	@Override
	public void draw(IVizCanvas canvas) {

		// If necessary, create a new plot composite.
		if (result == null) {
			// Check the parent Composite.
			if (parent == null) {
				throw new SWTException(SWT.ERROR_NULL_ARGUMENT, "IPlot error: "
						+ "Cannot draw plot in a null Composite.");
			} else if (parent.isDisposed()) {
				throw new SWTException(SWT.ERROR_WIDGET_DISPOSED,
						"IPlot error: "
								+ "Cannot draw plot in a disposed Composite.");
			}
			// Create a plot composite.
			result = new CSVPlotComposite(parent, SWT.BORDER);
			((CSVPlotComposite) result).setPlot((IPlot) canvas);

			//Load the data to the canvas
			((CSVPlot) canvas).load();
			
			// Make sure the plot data has been loaded
			while (!((CSVPlot) canvas).isLoaded()) {
				try {
					Thread.sleep(200);
				} catch (InterruptedException e1) {
					logger.error(
							"RCPCSVDrawHandler exception, interupted while waiting for canvas to load.");
				}
			}

			// Tell it to update based on the new plot.
			((CSVPlotComposite) result).refresh();

			// When the composite is disposed, unset the reference to it so a
			// new composite can be drawn later.
			((CSVPlotComposite) result)
					.addDisposeListener(new DisposeListener() {
						@Override
						public void widgetDisposed(DisposeEvent e) {
							result = null;
						}
					});
		}
		// Otherwise, ignore the parent argument and refresh.
		else {
			((CSVPlotComposite) result).refresh();
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.eavp.viz.service.IPlot#getNumAdditionalPages()
	 */
	@Override
	public int getNumAdditionalPages() {
		return 1;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.eavp.viz.service.drawhandler.IDrawHandler#save(org.eclipse.
	 * eavp.viz.service.VizProgressMonitor, boolean)
	 */
	@Override
	public boolean save(VizProgressMonitor monitor, boolean saveAs) {

		if (saveAs) {

			// Only the text editor can be saved
			editor.doSaveAs();
		} else {

			// Only the text editor can be saved
			editor.doSave((IProgressMonitor) monitor.getMonitor());
		}

		return true;
	}

}
