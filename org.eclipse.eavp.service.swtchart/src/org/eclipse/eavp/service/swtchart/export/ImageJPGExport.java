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
package org.eclipse.eavp.service.swtchart.export;

import org.eclipse.eavp.service.swtchart.core.BaseChart;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Shell;

public class ImageJPGExport implements ISeriesExportConverter {

	private static final String NAME = "Image (*.jpg)";

	@Override
	public String getName() {

		return NAME;
	}

	@Override
	public void export(Shell shell, BaseChart baseChart) {

		FileDialog fileDialog = new FileDialog(shell, SWT.SAVE);
		fileDialog.setText(NAME);
		fileDialog.setFilterExtensions(new String[]{"*.jpeg", "*.jpg"});
		//
		String fileName = fileDialog.open();
		if(fileName != null) {
			/*
			 * Select the format.
			 */
			baseChart.save(fileName, SWT.IMAGE_JPEG);
		}
	}
}
