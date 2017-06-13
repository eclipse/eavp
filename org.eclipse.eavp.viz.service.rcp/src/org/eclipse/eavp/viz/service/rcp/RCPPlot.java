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

import org.eclipse.eavp.viz.service.AbstractPlot;
import org.eclipse.ui.IFileEditorInput;
import org.eclipse.ui.part.MultiPageEditorPart;

/**
 * An extension of AbstractPlot featuring additional functions specific to drawing the plot in an RCP environment.
 * 
 * @author Robert Smith
 *
 */
abstract public class RCPPlot extends AbstractPlot{
	
	/**
	 * Directs the service to draw one of its additional pages and add it to the
	 * MultiPageEditorPart. The expected use is to call
	 * creatAddionalPage(parent, file, 1), createAdditonalPage(parent, file, 2),
	 * etc. until all additional pages have been drawn in separate tabs.
	 * 
	 * @param parent
	 *            The part which the page will be added to.
	 * @param file
	 *            The file to use as input for the page.
	 * @param pageNum
	 *            The id of the page to be drawn.
	 * 
	 * @return The name of the new page, to be displayed as the title of the tab
	 *         containing it
	 */
	abstract public String createAdditionalPage(MultiPageEditorPart parent,
			IFileEditorInput file, int pageNum);
	
	/**
	 * Gets the number of pages this service will display in the plot editor in
	 * addition to the main canvas.
	 * 
	 * @return The number of additional pages displayed by the plot editor. 0 if
	 *         there is only one page.
	 */
	abstract public int getNumAdditionalPages();
}
