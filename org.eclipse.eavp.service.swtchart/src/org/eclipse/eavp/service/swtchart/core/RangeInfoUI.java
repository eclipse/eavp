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
import org.swtchart.Range;

public class RangeInfoUI extends Composite {

	private Text textStartX;
	private Text textStopX;
	private Label labelScaleX;
	private Text textStartY;
	private Text textStopY;
	private Label labelScaleY;

	public RangeInfoUI(Composite parent, int style) {
		super(parent, style);
		createControl();
	}

	public void setXYLabels(String scaleX, String scaleY) {

		labelScaleX.setText(scaleX);
		labelScaleY.setText(scaleY);
	}

	public void setXYRanges(Range rangeX, Range rangeY) {

		if(rangeX != null && rangeY != null) {
			textStartX.setText(Double.toString(rangeX.lower));
			textStopX.setText(Double.toString(rangeX.upper));
			textStartY.setText(Double.toString(rangeY.lower));
			textStopY.setText(Double.toString(rangeY.upper));
		}
	}

	private void createControl() {

		setLayout(new GridLayout(8, false));
		//
		textStartX = new Text(this, SWT.BORDER);
		textStartX.setText("");
		textStartX.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		//
		textStopX = new Text(this, SWT.BORDER);
		textStopX.setText("");
		textStopX.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		//
		labelScaleX = new Label(this, SWT.NONE);
		labelScaleX.setText("");
		//
		textStartY = new Text(this, SWT.BORDER);
		textStartY.setText("");
		textStartY.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		//
		textStopY = new Text(this, SWT.BORDER);
		textStopY.setText("");
		textStopY.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		//
		labelScaleY = new Label(this, SWT.NONE);
		labelScaleY.setText("");
		//
		Button buttonSetRange = new Button(this, SWT.PUSH);
		buttonSetRange.setText("Set");
		buttonSetRange.addSelectionListener(new SelectionAdapter() {

			@Override
			public void widgetSelected(SelectionEvent e) {

				System.out.println("Implement Set Range.");
			}
		});
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
