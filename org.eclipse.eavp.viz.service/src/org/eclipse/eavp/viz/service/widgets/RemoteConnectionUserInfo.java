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
package org.eclipse.eavp.viz.service.widgets;

import java.util.concurrent.atomic.AtomicBoolean;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.PlatformUI;

import com.jcraft.jsch.UserInfo;

/**
 * This class is responsible for prompting the user for required information
 * about remote visualization connections opened through Jsch and storing their
 * responses for later use.
 * 
 * @author Robert Smith
 *
 */
public class RemoteConnectionUserInfo implements UserInfo {

	/**
	 * The parent shell used to prompt the user.
	 */
	Shell parent;

	/**
	 * The user input passphrase for the remote machine.
	 */
	String passphrase;

	/**
	 * The user input password for the remote machine.
	 */
	String password;

	/**
	 * Whether or not the user selected yes in the dialog box the last time they
	 * were prompted.
	 */
	AtomicBoolean selection;

	/**
	 * The default constructor.
	 */
	public RemoteConnectionUserInfo() {

		// Create a new shell
		PlatformUI.getWorkbench().getDisplay().syncExec(new Runnable() {

			@Override
			public void run() {
				parent = new Shell(Display.getCurrent());
			}

		});
	}

	/**
	 * Set the user's input password
	 * 
	 * @param password
	 *            The new password input by the user
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * Set the user's yes/no selection.
	 * 
	 * @param selection
	 *            The user's selection. True will indicate a selection of "yes"
	 *            while false will indicate any other action (selecting no,
	 *            canceling the dialog, etc.)
	 */
	public void setSelection(boolean selection) {
		this.selection.set(selection);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.jcraft.jsch.UserInfo#getPassphrase()
	 */
	@Override
	public String getPassphrase() {
		return passphrase;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.jcraft.jsch.UserInfo#getPassword()
	 */
	@Override
	public String getPassword() {
		return password;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.jcraft.jsch.UserInfo#promptPassword(java.lang.String)
	 */
	@Override
	public boolean promptPassword(String message) {

		// Get a final reference to the message, parent. and this object.
		final String finalMessage = message;

		// Reset the selection
		selection = new AtomicBoolean();

		// Open a message dialog to prompt the user
		parent.getDisplay().syncExec(new Runnable() {

			/*
			 * (non-Javadoc)
			 * 
			 * @see java.lang.Runnable#run()
			 */
			@Override
			public void run() {

				// Create a shell with a grid layout
				Shell shell = new Shell(parent, parent.getStyle());
				shell.setText(finalMessage);
				GridLayout grid = new GridLayout();
				grid.numColumns = 2;
				shell.setLayout(grid);

				// Add a text label telling the user to input the password
				Label passwordLabel = new Label(shell, SWT.NONE);
				passwordLabel.setText("Password: ");
				passwordLabel.setLayoutData(
						new GridData(SWT.RIGHT, SWT.CENTER, false, false));

				// Create a text box for the user to type in
				final Text passwordField = new Text(shell,
						SWT.PASSWORD | SWT.BORDER);
				passwordField.setText("");
				passwordField.setLayoutData(new GridData(300, SWT.DEFAULT));

				Composite buttonComp = new Composite(shell, SWT.NONE);
				buttonComp.setLayoutData(
						new GridData(SWT.CENTER, SWT.CENTER, true, true, 2, 1));
				buttonComp.setLayout(new GridLayout(2, true));

				// Create a cancel button
				Button cancelButton = new Button(buttonComp, SWT.PUSH);
				cancelButton.setText("Cancel");
				cancelButton.setLayoutData(new GridData(100, SWT.DEFAULT));
				cancelButton.addSelectionListener(new SelectionAdapter() {
					@Override
					public void widgetSelected(SelectionEvent event) {
						password = "";
						passwordField.setText("");
						selection.set(false);
						shell.close();
					}
				});

				// Create an ok button
				Button okButton = new Button(buttonComp, SWT.PUSH);
				okButton.setText("OK");
				okButton.setLayoutData(new GridData(100, SWT.DEFAULT));
				okButton.addSelectionListener(new SelectionAdapter() {
					@Override
					public void widgetSelected(SelectionEvent event) {
						password = passwordField.getText();
						selection.set(true);
						shell.close();
					}
				});

				// Set the OK button as the default
				shell.setDefaultButton(okButton);

				// Open the dialog
				shell.layout();
				shell.pack();
				shell.open();

				// Wait for the user to close the shell
				while (!shell.isDisposed()) {
					if (!parent.getDisplay().readAndDispatch()) {
						parent.getDisplay().sleep();
					}
				}
			}
		});

		return selection.get();

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.jcraft.jsch.UserInfo#promptPassphrase(java.lang.String)
	 */
	@Override
	public boolean promptPassphrase(String message) {

		// Get a final reference to the message, parent. and this object.
		final String finalMessage = message;

		// Reset the selection
		selection = new AtomicBoolean();

		// Open a message dialog to prompt the user
		parent.getDisplay().syncExec(new Runnable() {

			/*
			 * (non-Javadoc)
			 * 
			 * @see java.lang.Runnable#run()
			 */
			@Override
			public void run() {

				// Create a shell
				Shell shell = new Shell(parent, parent.getStyle());
				shell.setText(finalMessage);
				shell.setLayout(new GridLayout());

				// Add a text label telling the user to input the password
				Label passwordLabel = new Label(shell, SWT.NONE);
				passwordLabel.setText("Passphrase: ");
				passwordLabel.setLayoutData(
						new GridData(SWT.RIGHT, SWT.CENTER, false, false));

				// Create a text box for the user to type in
				final Text passwordField = new Text(shell,
						SWT.PASSWORD | SWT.BORDER);
				passwordField.setText("");
				passwordField.setLayoutData(new GridData(300, SWT.DEFAULT));

				Composite buttonComp = new Composite(shell, SWT.NONE);
				buttonComp.setLayoutData(
						new GridData(SWT.RIGHT, SWT.CENTER, true, true, 2, 1));
				buttonComp.setLayout(new GridLayout(2, true));

				// Create a cancel button
				Button cancelButton = new Button(parent, SWT.PUSH);
				cancelButton.setText("Cancel");
				cancelButton.setLayoutData(new GridData(100, SWT.DEFAULT));
				cancelButton.addSelectionListener(new SelectionAdapter() {
					@Override
					public void widgetSelected(SelectionEvent event) {
						passphrase = "";
						passwordField.setText("");
						selection.set(false);
						shell.close();
					}
				});

				// Create an ok button
				Button okButton = new Button(parent, SWT.PUSH);
				okButton.setText("OK");
				okButton.setLayoutData(new GridData(100, SWT.DEFAULT));
				okButton.addSelectionListener(new SelectionAdapter() {
					@Override
					public void widgetSelected(SelectionEvent event) {
						passphrase = passwordField.getText();
						selection.set(true);
						shell.close();
					}
				});

				// Set the OK button as the default
				shell.setDefaultButton(okButton);

				// Open the dialog
				shell.layout();
				shell.pack();
				shell.open();

				// Wait for the user to close the shell
				while (!shell.isDisposed()) {
					if (!parent.getDisplay().readAndDispatch()) {
						parent.getDisplay().sleep();
					}
				}
			}
		});

		return selection.get();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.jcraft.jsch.UserInfo#promptYesNo(java.lang.String)
	 */
	@Override
	public boolean promptYesNo(String message) {

		// Get a final reference to the message and this object.
		final String finalMessage = message;
		final RemoteConnectionUserInfo self = this;

		// The default assumption is "no"
		selection = new AtomicBoolean();

		// Open a message dialog to prompt the user
		parent.getDisplay().syncExec(new Runnable() {

			/*
			 * (non-Javadoc)
			 * 
			 * @see java.lang.Runnable#run()
			 */
			@Override
			public void run() {

				// Create a message box from the given message
				MessageBox box = new MessageBox(parent,
						SWT.ICON_WARNING | SWT.NO | SWT.YES);
				box.setMessage(finalMessage);

				// If they selected yes, return true
				if (box.open() == SWT.YES) {
					self.setSelection(true);
				}
			}
		});

		return selection.get();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.jcraft.jsch.UserInfo#showMessage(java.lang.String)
	 */
	@Override
	public void showMessage(String message) {

		// Create a message dialog with the given text
		MessageBox messageBox = new MessageBox(parent, SWT.OK);
		messageBox.setText(message);
	}

}
