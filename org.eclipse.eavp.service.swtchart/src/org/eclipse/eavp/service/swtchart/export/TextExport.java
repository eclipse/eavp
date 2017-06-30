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

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

import org.eclipse.eavp.service.swtchart.core.BaseChart;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Shell;
import org.swtchart.ISeries;

public class TextExport {

	public static void exportTabSeparated(Shell shell, BaseChart baseChart) {

		FileDialog fileDialog = new FileDialog(shell, SWT.SAVE);
		fileDialog.setText("Save As Tab Separated Text");
		fileDialog.setFilterExtensions(new String[]{"*.tsv"});
		//
		String fileName = fileDialog.open();
		if(fileName != null) {
			PrintWriter printWriter = null;
			try {
				printWriter = new PrintWriter(new File(fileName));
				/*
				 * Header
				 */
				printWriter.print("X");
				printWriter.print("\t");
				printWriter.println("Y");
				//
				int widthPlotArea = baseChart.getPlotArea().getBounds().width;
				ISeries[] series = baseChart.getSeriesSet().getSeries();
				for(ISeries dataSeries : series) {
					printWriter.println(dataSeries.getId());
					if(dataSeries != null) {
						/*
						 * Data
						 */
						double[] xSeries = dataSeries.getXSeries();
						double[] ySeries = dataSeries.getYSeries();
						int size = dataSeries.getXSeries().length;
						//
						for(int i = 0; i < size; i++) {
							Point point = dataSeries.getPixelCoordinates(i);
							if(point.x >= 0 && point.x <= widthPlotArea) {
								printWriter.print(xSeries[i]);
								printWriter.print("\t");
								printWriter.println(ySeries[i]);
							}
						}
					}
					printWriter.println("");
				}
				//
				printWriter.flush();
			} catch(FileNotFoundException e) {
				System.out.println(e);
			} finally {
				if(printWriter != null) {
					printWriter.close();
				}
			}
		}
	}
}
