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

public class LaTeXTableExport implements ISeriesExportConverter {

	private static final String NAME = "LaTeX Table (*.tex)";

	@Override
	public String getName() {

		return NAME;
	}

	@Override
	public void export(Shell shell, BaseChart baseChart) {

		FileDialog fileDialog = new FileDialog(shell, SWT.SAVE);
		fileDialog.setText(NAME);
		fileDialog.setFilterExtensions(new String[]{"*.tex"});
		//
		String fileName = fileDialog.open();
		if(fileName != null) {
			if(fileName != null) {
				PrintWriter printWriter = null;
				try {
					printWriter = new PrintWriter(new File(fileName));
					/*
					 * Header
					 */
					printWriter.println("\\begin{center}");
					printWriter.println("\\begin{tabular}{ c c }");
					printWriter.println("	X & Y \\\\");
					//
					int widthPlotArea = baseChart.getPlotArea().getBounds().width;
					ISeries[] series = baseChart.getSeriesSet().getSeries();
					for(ISeries dataSeries : series) {
						printWriter.println("	\\hline");
						printWriter.println("\t" + dataSeries.getId() + " & \\\\");
						printWriter.println("	\\hline");
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
									printWriter.print("\t");
									printWriter.print(xSeries[i]);
									printWriter.print(" & ");
									printWriter.print(ySeries[i]);
									printWriter.println(" \\\\");
								}
							}
						}
					}
					//
					printWriter.println("\t  &  \\\\");
					//
					printWriter.println("\\end{tabular}");
					printWriter.println("\\end{center}");
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
}
