/*******************************************************************************
 * Copyright (c) 2017 Lablicate GmbH.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 * Dr. Philip Wenig - initial API and implementation
 *******************************************************************************/
package org.eclipse.eavp.service.swtchart.core;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

public class RangeInfoUI extends Composite {

	public RangeInfoUI(Composite parent, int style) {
		super(parent, style);
		createControl();
	}

	private void createControl() {

		setLayout(new GridLayout(8, false));
		//
		Text textStartX = new Text(this, SWT.BORDER);
		textStartX.setText("292");
		textStartX.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		//
		Text textStopX = new Text(this, SWT.BORDER);
		textStopX.setText("292");
		textStopX.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		//
		Label labelX = new Label(this, SWT.NONE);
		labelX.setText("X Scale");
		//
		Text textStartY = new Text(this, SWT.BORDER);
		textStartY.setText("292");
		textStartY.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		//
		Text textStopY = new Text(this, SWT.BORDER);
		textStopY.setText("292");
		textStopY.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		//
		Label labelY = new Label(this, SWT.NONE);
		labelY.setText("Y Scale");
		//
		Button buttonSetRange = new Button(this, SWT.PUSH);
		buttonSetRange.setText("Set Range");
		//
		Button buttonHide = new Button(this, SWT.PUSH);
		buttonHide.setText("Hide");
		buttonHide.addSelectionListener(new SelectionAdapter() {

			@Override
			public void widgetSelected(SelectionEvent e) {

				GridData gridData = (GridData)getLayoutData();
				gridData.exclude = true;
				setVisible(false);
				getParent().layout(false);
			}
		});
	}
}
