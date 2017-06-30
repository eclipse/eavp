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

public class ImageExport {

	public static void exportAsBitmap(Shell shell, BaseChart baseChart) {

		FileDialog fileDialog = new FileDialog(shell, SWT.SAVE);
		fileDialog.setText("Save As Image (Bitmap)");
		fileDialog.setFilterExtensions(new String[]{"*.jpeg", "*.jpg", "*.png"});
		//
		String fileName = fileDialog.open();
		if(fileName != null) {
			/*
			 * Select the format.
			 */
			int imageFormat = SWT.IMAGE_UNDEFINED;
			if(fileName.endsWith(".jpg") || fileName.endsWith(".jpeg")) {
				imageFormat = SWT.IMAGE_JPEG;
			} else if(fileName.endsWith(".png")) {
				imageFormat = SWT.IMAGE_PNG;
			}
			//
			if(imageFormat != SWT.IMAGE_UNDEFINED) {
				baseChart.save(fileName, imageFormat);
			}
		}
	}
}
