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
package org.eclipse.eavp.viz.service.javafx.geometry;

import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.IMessageProvider;
import org.eclipse.jface.dialogs.TitleAreaDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

/**
 * A dialog which prompts the user for what size the axes for a FXGeometryCanvas should be drawn.
 * 
 * @author Robert Smith
 *
 */
public class AxisSizeDialog extends TitleAreaDialog{
	
	/**
	 * The size input by the user.
	 */
	double size;

	/**
	 * The default constructor.
	 * 
	 * @param parent The parent shell in which to draw the dialog  
	 * @param size The current size for the axes.
	 */
	public AxisSizeDialog(Shell parent, double size) {
		super(parent);
		
		setShellStyle(SWT.TITLE | SWT.MODELESS | SWT.RESIZE);
		
		//Initialize the size to the current value
		this.size = size;
		
	}
	
	/**
	 * Get the user input.
	 * 
	 * @return The size selected by the user, or -1 if the cancel button was pressed.
	 */
	public double getInput(){
		return size;
	}
	
	@Override
	protected void cancelPressed(){
		super.cancelPressed();
		
		//Set the size to an invalid value to signal that no change should be made
		size = -1;
	}
	
	@Override
	public void create() {
		super.create();

		// Set the title and help message for this TitleAreaDialog
		setTitle("Set axis size");
		setMessage("Select the scale for the axis display. 1 will display the axes normally. Numbers between 0 and 1 will scale them down, while higher numbers will scale them up.",
				IMessageProvider.INFORMATION);

		return;
	}
	
	/*
	 * (non-Javadoc)
	 * @see org.eclipse.jface.dialogs.Dialog#createDialogArea(org.eclipse.swt.widgets.Composite)
	 */
	@Override
	protected Control createDialogArea(Composite parent) {
		Composite composite = (Composite) super.createDialogArea(parent);
		
		// Set the layout to a vertical grid layout with some margins.
		GridLayout gridLayout = new GridLayout();
		gridLayout.marginWidth = 10;
		composite.setLayout(gridLayout);

		// Create a label with instructions.
		Label info = new Label(composite, SWT.NONE);
		info.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));
		info.setText("Scale:");
		
		//Create a text box to allow the user to input the size
		Text scale = new Text(composite, SWT.NONE);
		scale.setText("1.0");
		scale.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));
		
		scale.addModifyListener(new ModifyListener(){

			@Override
			public void modifyText(ModifyEvent e) {
				String value = scale.getText();

				//Convert the user input to a double and save it as the current value
				try {

					double newValue = Double.valueOf(value);

					size = newValue;
				} catch (NumberFormatException exception) {

					//If the input was not a valid double, then restore the text box to the previous value
					scale.setText(Double.toString(size));
				}
			}
		});
		
		return composite;
	}

}
