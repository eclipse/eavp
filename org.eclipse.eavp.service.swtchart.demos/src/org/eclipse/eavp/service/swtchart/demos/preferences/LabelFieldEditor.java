/*******************************************************************************
 * Copyright (c) 2017, 2018 Lablicate GmbH.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 * Philip Wenig - initial API and implementation
 *******************************************************************************/
package org.eclipse.eavp.service.swtchart.demos.preferences;

import org.eclipse.jface.preference.FieldEditor;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

/**
 * This editor can be used to show a label in the preference page.
 * 
 * @author Dr. Philip Wenig
 */
public class LabelFieldEditor extends FieldEditor {

	private Label label;
	private static final String LABEL_X = "LABELX";

	public LabelFieldEditor(String labelText, Composite parent) {
		super(LABEL_X, labelText, parent);
	}

	@Override
	protected void adjustForNumColumns(int numColumns) {

		GridData gridData = (GridData)this.label.getLayoutData();
		gridData.horizontalSpan = numColumns;
	}

	@Override
	protected void doFillIntoGrid(Composite parent, int numColumns) {

		this.label = getLabelControl(parent);
		GridData gridData = new GridData();
		gridData.horizontalSpan = numColumns;
		gridData.horizontalAlignment = GridData.FILL;
		gridData.grabExcessHorizontalSpace = false;
		gridData.verticalAlignment = GridData.CENTER;
		gridData.grabExcessVerticalSpace = false;
		this.label.setLayoutData(gridData);
	}

	@Override
	protected void doLoad() {

	}

	@Override
	protected void doLoadDefault() {

	}

	@Override
	protected void doStore() {

	}

	@Override
	public int getNumberOfControls() {

		return 1;
	}
}
