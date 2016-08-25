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
import java.util.List;

import org.eclipse.eavp.geometry.view.model.IRenderElement;
import org.eclipse.eavp.viz.service.IRenderElementHolder;
import org.eclipse.eavp.viz.service.geometry.widgets.RealSpinner;
import org.eclipse.eavp.viz.service.geometry.widgets.RealSpinnerListener;
import org.eclipse.eavp.viz.service.geometry.widgets.ShapeTreeView;
import org.eclipse.eavp.viz.service.geometry.widgets.TransformationPropertyWidget;
import org.eclipse.january.geometry.GeometryFactory;
import org.eclipse.january.geometry.INode;
import org.eclipse.january.geometry.Vertex;
import org.eclipse.jface.databinding.swt.IWidgetValueProperty;
import org.eclipse.jface.databinding.swt.WidgetProperties;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.events.FocusListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.views.properties.tabbed.AbstractPropertySection;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage;

public class ShapeSection extends AbstractPropertySection {

	/**
	 * The number of lines of properties to display in the view. If a shape has
	 * more than NUM_PROPERTIES properties defined, extra properties can not be
	 * displayed or edited in this view.
	 */
	final static private int NUM_PROPERTIES = 5;

	/**
	 * The object whose properties are displayed by this section.
	 */
	IRenderElement source;

	Text nameText;

	Text idText;

	int ID;

	/**
	 * The tree spinners that set the object's center
	 */
	private RealSpinner[] translateSpinners = new RealSpinner[3];

	/**
	 * A list of optional spinners which have been added to the view to expose
	 * all of the shape's properties.
	 */
	private ArrayList<RealSpinner> propertySpinners = new ArrayList<RealSpinner>();

	/**
	 * A combo box listing the options for how to display the shape.
	 */
	private Combo opacityCombo;

	/**
	 * The list of widgets which will allow the shape's properties to be
	 * displayed and edited.
	 */
	private ArrayList<TransformationPropertyWidget> propertyWidgets;

	public ShapeSection(int ID) {
		super();

		this.ID = ID;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.ui.views.properties.tabbed.AbstractPropertySection#
	 * createControls(org.eclipse.swt.widgets.Composite,
	 * org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage)
	 */
	@Override
	public void createControls(Composite parent,
			TabbedPropertySheetPage aTabbedPropertySheetPage) {

		// Create a scrolled composite - scroll bars!
		// ScrolledComposite scrolledParent = new ScrolledComposite(parent,
		// SWT.BORDER | SWT.H_SCROLL | SWT.V_SCROLL);
		// Create a sub-composite to hold the actual widgets
		// final Composite realParent = new Composite(scrolledParent, SWT.NONE);

		// Create the layout for the view
		parent.getParent().setLayout(new FormLayout());
		FormData parentData = new FormData();
		parentData.top = new FormAttachment(0);
		parentData.left = new FormAttachment(0);
		parentData.right = new FormAttachment(100);
		parentData.bottom = new FormAttachment(100);
		parent.setLayoutData(parentData);
		layout(parent);

		// Tell the scrolled composite to watch the real parent composite
		// scrolledParent.setContent(realParent);
		// Set the expansion sizes and minimum size of the scrolled composite
		// scrolledParent.setExpandHorizontal(true);
		// scrolledParent.setExpandVertical(true);
		// scrolledParent
		// .setMinSize(realParent.computeSize(SWT.DEFAULT, SWT.DEFAULT));
		// scrolledParent.setShowFocusedControl(true);
	}

	private void layout(Composite parent) {

		// Get the system's white color
		Color white = Display.getCurrent().getSystemColor(SWT.COLOR_WHITE);

		// Main layout
		parent.setLayout(new FormLayout());
		// parent.setLayoutData(
		// new GridData(SWT.FILL, SWT.FILL, false, false, 1, 1));

		Group dataGroup = createGroup(parent, "Data", 0, 0, 50, 100);

		Composite nameComp = new Composite(dataGroup, SWT.NONE);
		FormData nameData = new FormData();
		nameData.top = new FormAttachment(0, 5);
		nameData.left = new FormAttachment(0, 5);
		nameData.right = new FormAttachment(25);
		nameComp.setLayoutData(nameData);
		nameComp.setLayout(new GridLayout(2, false));
		nameComp.setBackground(white);

		Label nameLabel = new Label(nameComp, SWT.CENTER);
		nameLabel.setText("Name:");
		nameLabel.setBackground(white);

		nameText = new Text(nameComp, SWT.BORDER);
		nameText.setLayoutData(
				new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));

		nameText.addListener(SWT.DefaultSelection, new Listener() {

			@Override
			public void handleEvent(Event event) {

				// Validate the text and select all in the text box

				source.getBase().setName(nameText.getText());
				nameText.selectAll();
			}
		});

		nameText.addFocusListener(new FocusListener() {

			@Override
			public void focusGained(FocusEvent event) {

				// Select all the text in the Text widget

				nameText.selectAll();
			}

			@Override
			public void focusLost(FocusEvent event) {

				// Just validate the text

				source.getBase().setName(nameText.getText());
			}
		});

		Composite idComp = new Composite(dataGroup, SWT.NONE);
		FormData idData = new FormData();
		idData.top = new FormAttachment(0, 5);
		idData.left = new FormAttachment(nameComp, 5, 0);
		idData.right = new FormAttachment(35);
		idComp.setLayoutData(idData);
		idComp.setLayout(new GridLayout(2, false));
		idComp.setBackground(white);

		Label idLabel = new Label(idComp, SWT.NONE);
		idLabel.setText("ID:");
		idLabel.setBackground(white);

		idText = new Text(idComp, SWT.BORDER);
		idText.setLayoutData(
				new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));

		Group centerGroup = createGroup(dataGroup, "Center", null, null, idComp,
				0, null, 70, null, null);// new Group(dataGroup, SWT.NONE);
		centerGroup.setLayout(new GridLayout(3, true));
		// FormData centerGroupData = new FormData();
		// centerGroupData.left = new FormAttachment(10, 0);
		// centerGroupData.right = new FormAttachment(40, 0);
		// centerGroup.setLayoutData(centerGroupData);
		// centerGroup.setText("Center");
		// centerGroup.setBackground(white);

		// Coordinate labels

		Label labelX = new Label(centerGroup, SWT.NONE);
		labelX.setLayoutData(
				new GridData(SWT.CENTER, SWT.CENTER, false, false, 1, 1));
		labelX.setBackground(white);
		labelX.setText("X");

		Label labelY = new Label(centerGroup, SWT.NONE);
		labelY.setLayoutData(
				new GridData(SWT.CENTER, SWT.CENTER, false, false, 1, 1));
		labelY.setText("Y");
		labelY.setBackground(white);

		Label labelZ = new Label(centerGroup, SWT.NONE);
		labelZ.setLayoutData(
				new GridData(SWT.CENTER, SWT.CENTER, false, false, 1, 1));
		labelZ.setText("Z");
		labelZ.setBackground(white);

		// Translation
		for (int i = 0; i < 3; i++) {
			translateSpinners[i] = new RealSpinner(centerGroup);
			translateSpinners[i].getControl().setLayoutData(
					new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
			translateSpinners[i].getControl().setBackground(white);
			translateSpinners[i].setBounds(-1.0e6, 1.0e6);
		}

		Group displayGroup = createGroup(parent, "Display", 0, 50, 100, 100);

		Group opacityGroup = new Group(displayGroup, SWT.NONE);
		FormData opacityGroupData = new FormData();
		opacityGroupData.left = new FormAttachment(centerGroup, 0);
		opacityGroup.setLayoutData(opacityGroupData);
		opacityGroup.setText("Opacity");
		opacityGroup.setBackground(white);
		opacityGroup.setLayout(new GridLayout());

		// // Create a label for the opacity combo
		// Label opacityLabel = new Label(parent, SWT.NONE);
		// // opacityLabel.setLayoutData(
		// // new GridData(SWT.NONE, SWT.NONE, false, false, 1, 1));
		// opacityLabel.setText("Opacity:");
		// opacityLabel.setBackground(white);

		// Initialize the opacity combo box
		opacityCombo = new Combo(opacityGroup, SWT.READ_ONLY);
		// opacityCombo.setLayoutData(
		// new GridData(SWT.FILL, SWT.FILL, false, false, 1, 1));
		opacityCombo
				.setItems(new String[] { "Solid", "Wireframe", "Transparent" });

		Group meshPropertiesGroup = createGroup(dataGroup, "Mesh Properties",
				25, 0, 100, 60);

		// Create property widgets to display any properties the shape has.
		propertyWidgets = new ArrayList<TransformationPropertyWidget>();
		for (int i = 0; i < NUM_PROPERTIES; i++) {

			// Create a child composite to hold the widget
			Composite widgetComp = new Composite(meshPropertiesGroup, SWT.NONE);
			// widgetComp.setLayoutData(
			// new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1));
			widgetComp.setLayout(new GridLayout(2, false));
			widgetComp.setBackground(white);

			// Create the widget and add it to the list
			propertyWidgets.add(new TransformationPropertyWidget(widgetComp));
		}

		// Empty the old list of property spinners
		propertySpinners.clear();

		// // Iterate through the shape's properties, giving each one its own
		// // section in the view
		// if (source != null) {
		// for (String property : source.getBase().getPropertyNames()) {
		//
		// // Create a label with the property's name
		// Label propertyLabel = new Label(parent, SWT.NONE);
		// // propertyLabel.setLayoutData(
		// // new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1));
		// propertyLabel.setText(property + ":");
		//
		// // Create a spinner for the property's value. All properties
		// // have
		// // doubles for values.
		// RealSpinner propertySpinner = new RealSpinner(parent);
		// // propertySpinner.getControl().setLayoutData(
		// // new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		// propertySpinner.setBounds(-1.0e6, 1.0e6);
		//
		// // Set the spinner's name
		// propertySpinner.setName(property);
		//
		// // Add the spinner to the list
		// propertySpinners.add(propertySpinner);
		// }
		//
		// // Create an extra spinner to control scale
		// // Create a label with the property's name
		// Label propertyLabel = new Label(parent, SWT.NONE);
		// // propertyLabel.setLayoutData(
		// // new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1));
		// propertyLabel.setText("scale:");
		//
		// // Create a spinner for the property's value. All properties
		// // have
		// // doubles for values.
		// RealSpinner propertySpinner = new RealSpinner(parent);
		// // propertySpinner.getControl().setLayoutData(
		// // new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		// propertySpinner.setBounds(-1.0e6, 1.0e6);
		//
		// // Set the spinner's name
		// propertySpinner.setName("scale");
		//
		// // Add the spinner to the list
		// propertySpinners.add(propertySpinner);
		// }

		// Set the initial shape
		createListeners();

		refresh();
	}

	private Group createGroup(Composite parent, String name, int top, int left,
			int right, int bottom) {
		return createGroup(parent, name, null, top, null, left, null, right,
				null, bottom);
	}

	private Group createGroup(Composite parent, String name,
			Control topNeighbor, Integer topOffset, Control leftNeighbor,
			Integer leftOffset, Control rightNeighbor, Integer rightOffset,
			Control bottomNeighbor, Integer bottomOffset) {

		Group group = new Group(parent, SWT.NONE);
		group.setText(name);

		FormData data = new FormData();

		if (topOffset != null) {
			data.top = topNeighbor == null ? new FormAttachment(topOffset)
					: new FormAttachment(topNeighbor, 5, topOffset);
		}
		if (leftOffset != null) {
			data.left = leftNeighbor == null ? new FormAttachment(leftOffset)
					: new FormAttachment(leftNeighbor, 5, leftOffset);
		}
		if (rightOffset != null) {
			data.right = rightNeighbor == null ? new FormAttachment(rightOffset)
					: new FormAttachment(rightNeighbor, 5, rightOffset);
		}
		if (bottomOffset != null) {
			data.bottom = bottomNeighbor == null
					? new FormAttachment(bottomOffset)
					: new FormAttachment(bottomNeighbor, 5, bottomOffset);
		}

		group.setLayoutData(data);

		group.setBackground(
				Display.getCurrent().getSystemColor(SWT.COLOR_WHITE));

		group.setLayout(new FormLayout());

		return group;
	}

	/**
	 * Create the listeners for each control on the view.
	 */
	private void createListeners() {

		IWidgetValueProperty property = WidgetProperties.selection();

		// Create anonymous listener

		RealSpinnerListener listener = new RealSpinnerListener() {

			@Override
			public void update(RealSpinner realSpinner) {

				// Handle a null currentShape

				if (source == null) {
					return;
				}

				// Get the center point from the spinners
				Vertex center = GeometryFactory.eINSTANCE.createVertex();
				center.setX(translateSpinners[0].getValue());
				center.setY(translateSpinners[1].getValue());
				center.setZ(translateSpinners[2].getValue());

				// Reset the shape's center
				if (!source.getBase().getCenter().equals(center)) {
					source.getBase().setCenter(center);
				}
			}
		};

		// Add the listener to each spinner
		for (RealSpinner spinner : translateSpinners) {
			spinner.listen(listener);
		}

		// Add a listener for the combo that will set the shape's opacity
		opacityCombo.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent e) {

				// Get the combo's contents
				String selection = opacityCombo.getText();

				// If it is opaque, set both properties to false
				if ("Solid".equals(selection)) {
					source.setProperty("opacity", 100d);
					source.setProperty("wireframe", false);
				}

				// If it is transparent, set it to transparent, leaving its
				// wireframe value alone
				else if ("Transparent".equals(selection)) {
					source.setProperty("opacity", 0d);
				}

				// If it is wireframe, set transparency off but wireframe on
				else {
					source.setProperty("opacity", 100d);
					source.setProperty("wireframe", true);
				}

			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				// Nothing to do
			}

		});

		// Create a spinner listener that will update the shape's properties
		RealSpinnerListener propertyListener = new RealSpinnerListener() {

			@Override
			public void update(RealSpinner realSpinner) {

				// Get the spinner's name and value
				String name = realSpinner.getName();
				double value = realSpinner.getValue();

				if (!"scale".equals(name)) {
					// If the value in the spinner has been changed, set the new
					// value to the shape
					if (value != source.getBase().getProperty(name)) {
						source.setProperty(realSpinner.getName(),
								realSpinner.getValue());
					}
				}

				else {
					source.setProperty("scale", value);
				}
			}
		};

		// Add the listener to each of the property spinners
		for (RealSpinner spinner : propertySpinners) {
			spinner.listen(propertyListener);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.ui.views.properties.tabbed.AbstractPropertySection#refresh()
	 */
	@Override
	public void refresh() {
		if (source != null) {

			nameText.setText(source.getBase().getName());
			idText.setText(String.valueOf(source.getBase().getId()));

			// Get the shape's current status
			double opacity = source.getProperty("opacity") == null ? 100d
					: (double) source.getProperty("opacity");
			boolean wireframe = source.getProperty("wireframe") == null ? false
					: (boolean) source.getProperty(("wireframe"));

			// Set the opacity combo's value based on the shape's transparency
			// and wireframe status
			if (opacity == 0) {
				opacityCombo.select(2);
			} else if (wireframe) {
				opacityCombo.select(1);
			} else {
				opacityCombo.select(0);
			}

			// Set the spinner values

			Vertex center = source.getBase().getCenter();
			double[] translations = new double[] { center.getX(), center.getY(),
					center.getZ() };
			for (int i = 0; i < 3; i++) {
				translateSpinners[i].setValue(translations[i]);
			}

			// Set the properties
			List<String> properties = source.getBase().getPropertyNames();
			properties.add("scale");

			// Pad the list to the correct number of properties
			while (properties.size() < NUM_PROPERTIES) {
				properties.add(null);
			}

			// Set the property widgets for the new shape's properties
			for (int i = 0; i < NUM_PROPERTIES; i++) {

				// Get the property name
				String property = properties.get(i);

				if (!"scale".equals(property)) {
					double value = (property != null)
							? source.getBase().getProperty(property) : 0d;

					// Set the property widget to display this property
					propertyWidgets.get(i).setProperty(source.getBase(),
							property, value);
				}

				// Handle scale
				else {
					double value = (source.getProperty("scale") != null)
							? (double) source.getProperty(property) : 1d;

					// Set the property widget to display this property
					propertyWidgets.get(i).setProperty(source.getBase(),
							property, value);
				}
			}
		}

		// Set the enabled state of the spinners, depending on whether the
		// shape parameter is null
		// Enable each spinner
		for (RealSpinner translateSpinner : translateSpinners) {
			translateSpinner.getControl().setEnabled(source != null);
		}
		for (RealSpinner spinner : propertySpinners) {
			spinner.getControl().setEnabled(source != null);
		}
		opacityCombo.setEnabled(source != null);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.ui.views.properties.tabbed.AbstractPropertySection#setInput(
	 * org.eclipse.ui.IWorkbenchPart, org.eclipse.jface.viewers.ISelection)
	 */
	@Override
	public void setInput(IWorkbenchPart part, ISelection selection) {
		super.setInput(part, selection);

		if (selection instanceof IStructuredSelection && !selection.isEmpty()) {

			ArrayList<INode> elements = new ArrayList<INode>();

			elements.add(((IRenderElement) ((IStructuredSelection) selection)
					.getFirstElement()).getBase());

			IRenderElementHolder holder = ((ShapeTreeView) PlatformUI
					.getWorkbench().getActiveWorkbenchWindow().getActivePage()
					.findView(ShapeTreeView.ID)).getHolder();

			for (int i = 0; i < elements.size(); i++) {

				if (ID < elements.size()) {
					source = holder.getRender(elements.get(ID));
					refresh();
					return;
				}

				elements.addAll(elements.get(i).getNodes());
			}

			refresh();
		}

		return;
	}

}
