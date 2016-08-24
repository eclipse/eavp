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
import org.eclipse.jface.viewers.IFilter;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.views.properties.tabbed.AbstractSectionDescriptor;
import org.eclipse.ui.views.properties.tabbed.AbstractTabDescriptor;
import org.eclipse.ui.views.properties.tabbed.ISection;
import org.eclipse.ui.views.properties.tabbed.ISectionDescriptor;
import org.eclipse.ui.views.properties.tabbed.ITabDescriptor;
import org.eclipse.ui.views.properties.tabbed.ITabDescriptorProvider;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage;

/**
 * This class provides the Properties View for the ShapeTreeView.
 * 
 * @author Robert Smith
 *
 */
public class ShapeTabDescriptorProvider implements ITabDescriptorProvider {

	/**
	 * The category for the properties. This must match exactly the category specified in the plugin.xml file extension point. 
	 */
	private static final String CATEGORY = "GeometrySelection";
	
	/**
	 * The filter the SectionDescriptors will use to determine whether or not to create a tab.
	 */
	private final IFilter filter = new IFilter() {
		@Override
		public boolean select(Object toTest) {
			
			//To be valid, the selection must be an IRenderElement
			return (toTest instanceof IRenderElement);
		}
	};
	
	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.views.properties.tabbed.ITabDescriptorProvider#getTabDescriptors(org.eclipse.ui.IWorkbenchPart, org.eclipse.jface.viewers.ISelection)
	 */
	@Override
	public ITabDescriptor[] getTabDescriptors(IWorkbenchPart part, ISelection selection) {
		
		ArrayList<ITabDescriptor> descriptors = new ArrayList<ITabDescriptor>();
		
		if(selection instanceof IStructuredSelection && !selection.isEmpty()){
			
			IRenderElement element = (IRenderElement) ((IStructuredSelection) selection).getFirstElement();
			
			AbstractTabDescriptor tabDescriptor = new AbstractTabDescriptor(){

				@Override
				public String getCategory() {
					return CATEGORY;
				}

				@Override
				public String getId() {
					return "Properties";
				}

				@Override
				public String getLabel() {
					return element.getBase().getName() + " " + element.getBase().getId();
				}
				
			};
			
			descriptors.add(tabDescriptor);
			
			ArrayList<ISectionDescriptor> sectionDescriptors = new ArrayList<ISectionDescriptor>();
			
			sectionDescriptors.add(new AbstractSectionDescriptor() {
				@Override
				public String getTargetTab() {
					return CATEGORY;
				}

				@Override
				public ISection getSectionClass() {
					return new ISection(){

						@Override
						public void createControls(Composite parent, TabbedPropertySheetPage tabbedPropertySheetPage) {
							// TODO Auto-generated method stub
							
						}

						@Override
						public void setInput(IWorkbenchPart part, ISelection selection) {
							// TODO Auto-generated method stub
							
						}

						@Override
						public void aboutToBeShown() {
							// TODO Auto-generated method stub
							
						}

						@Override
						public void aboutToBeHidden() {
							// TODO Auto-generated method stub
							
						}

						@Override
						public void dispose() {
							// TODO Auto-generated method stub
							
						}

						@Override
						public int getMinimumHeight() {
							// TODO Auto-generated method stub
							return 0;
						}

						@Override
						public boolean shouldUseExtraSpace() {
							// TODO Auto-generated method stub
							return false;
						}

						@Override
						public void refresh() {
							// TODO Auto-generated method stub
							
						}
						
					};
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
		
		return descriptors.toArray(new ITabDescriptor[descriptors.size()]);
	}

}
