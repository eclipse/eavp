/*******************************************************************************
 * Copyright (c) 2016 UT-Battelle, LLC.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   Robert Smith
 *******************************************************************************/
package org.eclipse.eavp.viz.service.geometry.properties;

import java.util.ArrayList;

import org.eclipse.eavp.geometry.view.model.IRenderElement;
import org.eclipse.eavp.viz.service.IRenderElementHolder;
import org.eclipse.eavp.viz.service.geometry.widgets.ShapeTreeContentProvider.BlankShape;
import org.eclipse.eavp.viz.service.geometry.widgets.ShapeTreeView;
import org.eclipse.january.geometry.INode;
import org.eclipse.jface.viewers.IFilter;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.views.properties.tabbed.AbstractSectionDescriptor;
import org.eclipse.ui.views.properties.tabbed.AbstractTabDescriptor;
import org.eclipse.ui.views.properties.tabbed.ISection;
import org.eclipse.ui.views.properties.tabbed.ISectionDescriptor;
import org.eclipse.ui.views.properties.tabbed.ITabDescriptor;
import org.eclipse.ui.views.properties.tabbed.ITabDescriptorProvider;

/**
 * This class provides the Properties View for the ShapeTreeView.
 * 
 * @author Robert Smith
 *
 */
public class ShapeTabDescriptorProvider implements ITabDescriptorProvider {

	/**
	 * A list of previously created ISectionDescriptors. The descriptor at index
	 * i is set up to display the ith tab.
	 */
	ArrayList<ISectionDescriptor> sectionDescriptors = new ArrayList<ISectionDescriptor>();

	/**
	 * The category for the properties. This must match exactly the category
	 * specified in the plugin.xml file extension point.
	 */
	private static final String CATEGORY = "GeometrySelection";

	/**
	 * The filter the SectionDescriptors will use to determine whether or not to
	 * create a tab.
	 */
	private final IFilter filter = new IFilter() {
		@Override
		public boolean select(Object toTest) {

			// To be valid, the selection must be an IRenderElement
			return (toTest instanceof IRenderElement);
		}
	};

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.ui.views.properties.tabbed.ITabDescriptorProvider#
	 * getTabDescriptors(org.eclipse.ui.IWorkbenchPart,
	 * org.eclipse.jface.viewers.ISelection)
	 */
	@Override
	public ITabDescriptor[] getTabDescriptors(IWorkbenchPart part,
			ISelection selection) {

		// The list of all descriptors for this selection
		ArrayList<ITabDescriptor> descriptors = new ArrayList<ITabDescriptor>();

		// If the selection is invalid, do nothing
		if (selection instanceof IStructuredSelection && !selection.isEmpty()) {

			// If the selection is a dummy shape, there are not tabs to make
			if (((IStructuredSelection) selection).getFirstElement()
					.getClass() == BlankShape.class) {
				return new ITabDescriptor[] {};
			}

			// TODO Handle multiple-element selection
			// Get the selected element
			IRenderElement element = (IRenderElement) ((IStructuredSelection) selection)
					.getFirstElement();

			// The list of all INodes for which a tab will be displayed
			ArrayList<INode> nodes = new ArrayList<INode>();
			nodes.add(element.getBase());

			// For each node, add all of its children to the list
			for (int i = 0; i < nodes.size(); i++) {
				nodes.addAll(nodes.get(i).getNodes());
			}

			// Get the holder that associates INodes with IRenderElements
			IRenderElementHolder holder = ((ShapeTreeView) PlatformUI
					.getWorkbench().getActiveWorkbenchWindow().getActivePage()
					.findView(ShapeTreeView.ID)).getHolder();

			// Create a tab for each node
			for (int i = 0; i < nodes.size(); i++) {

				// A final reference to the current node
				final IRenderElement finalElement = holder
						.getRender(nodes.get(i));

				// Create a tab descriptor that combines the element's name and
				// ID
				AbstractTabDescriptor tabDescriptor = new AbstractTabDescriptor() {

					@Override
					public String getCategory() {
						return CATEGORY;
					}

					@Override
					public String getId() {
						return finalElement.getBase().getName()
								+ finalElement.getBase().getId();
					}

					@Override
					public String getLabel() {
						return finalElement.getBase().getName() + " "
								+ finalElement.getBase().getId();
					}

				};

				descriptors.add(tabDescriptor);

				if (sectionDescriptors.size() <= i) {

					// The index of where in the list of all nodes in the
					// PropertiesView the section should display
					final int finalI = i;

					sectionDescriptors.add(new AbstractSectionDescriptor() {

						int ID = finalI;

						@Override
						public String getTargetTab() {
							return CATEGORY;
						}

						@Override
						public ISection getSectionClass() {
							// Create a shape section pointing to the correct
							// index
							return new ShapeSection(ID);
						}

						@Override
						public String getId() {
							return "Properties" + ".general";
						}

						@Override
						public IFilter getFilter() {
							return filter;
						}
					});
				}

				// Get the current section descriptor and set it to the tab
				// descriptor
				ArrayList<ISectionDescriptor> currentDescriptors = new ArrayList<ISectionDescriptor>();
				currentDescriptors.add(sectionDescriptors.get(i));
				tabDescriptor.setSectionDescriptors(currentDescriptors);
			}

		}

		return descriptors.toArray(new ITabDescriptor[descriptors.size()]);
	}

}
