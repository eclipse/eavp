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

		ArrayList<ITabDescriptor> descriptors = new ArrayList<ITabDescriptor>();

		if (selection instanceof IStructuredSelection && !selection.isEmpty()) {

			IRenderElement element = (IRenderElement) ((IStructuredSelection) selection)
					.getFirstElement();

			ArrayList<INode> nodes = new ArrayList<INode>();
			nodes.add(element.getBase());

			for (int i = 0; i < nodes.size(); i++) {
				nodes.addAll(nodes.get(i).getNodes());
			}

			IRenderElementHolder holder = ((ShapeTreeView) PlatformUI
					.getWorkbench().getActiveWorkbenchWindow().getActivePage()
					.findView(ShapeTreeView.ID)).getHolder();

			for (int i = 0; i < nodes.size(); i++) {

				final IRenderElement finalElement = holder
						.getRender(nodes.get(i));

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

				ArrayList<ISectionDescriptor> sectionDescriptors = new ArrayList<ISectionDescriptor>();

				final int finalI = i;

				sectionDescriptors.add(new AbstractSectionDescriptor() {

					int ID = finalI;

					@Override
					public String getTargetTab() {
						return CATEGORY;
					}

					@Override
					public ISection getSectionClass() {
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

				tabDescriptor.setSectionDescriptors(sectionDescriptors);
			}

		}

		return descriptors.toArray(new ITabDescriptor[descriptors.size()]);
	}

}
