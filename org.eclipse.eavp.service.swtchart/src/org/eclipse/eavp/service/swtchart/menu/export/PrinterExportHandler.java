/*******************************************************************************
 * Copyright (c) 2017, 2018 Lablicate GmbH.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 * Dr. Philip Wenig - initial API and implementation
 *******************************************************************************/
package org.eclipse.eavp.service.swtchart.menu.export;

import org.eclipse.eavp.service.swtchart.core.ScrollableChart;
import org.eclipse.eavp.service.swtchart.images.ImageSupplier;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.printing.PrintDialog;
import org.eclipse.swt.printing.Printer;
import org.eclipse.swt.printing.PrinterData;
import org.eclipse.swt.widgets.Shell;

public class PrinterExportHandler extends AbstractSeriesExportHandler implements ISeriesExportConverter {

	public static final String NAME = "Print";
	private static final String TITLE = "Save Selection";

	@Override
	public String getName() {

		return NAME;
	}

	@Override
	public void execute(Shell shell, ScrollableChart scrollableChart) {

		/*
		 * Print using the installed printer dialog.
		 */
		PrintDialog dialog = new PrintDialog(shell);
		PrinterData printerData = dialog.open();
		if(printerData != null) {
			Printer printer = new Printer(printerData);
			if(printer.startJob("PrinterExport")) {
				/*
				 * Create a page
				 */
				GC gc = new GC(printer);
				printer.startPage();
				/*
				 * Print the data.
				 */
				Rectangle trim = printer.computeTrim(0, 0, 0, 0);
				Rectangle clientArea = printer.getClientArea();
				ImageSupplier imageSupplier = new ImageSupplier();
				ImageData imageData = imageSupplier.getImageData(scrollableChart.getBaseChart());
				Image image = new Image(printer, imageData);
				int srcWidth = imageData.width;
				int srcHeight = imageData.height;
				if(srcWidth > 0) {
					double scalingFactor = srcHeight / (srcWidth * 1.0d);
					int destWidth = clientArea.width + trim.x; // trim is negative
					int destHeight = (int)(clientArea.width * scalingFactor) + trim.y; // trim is negative
					gc.drawImage(image, 0, 0, srcWidth, srcHeight, -trim.x, -trim.y, destWidth, destHeight);
				}
				/*
				 * Dispose the elements.
				 */
				image.dispose();
				gc.dispose();
				printer.endPage();
				printer.endJob();
				//
				MessageDialog.openInformation(shell, TITLE, MESSAGE_OK);
			}
			printer.dispose();
		}
	}
}
