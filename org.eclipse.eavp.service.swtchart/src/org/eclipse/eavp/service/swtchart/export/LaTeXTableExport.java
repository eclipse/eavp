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
import java.text.DecimalFormat;

import org.eclipse.eavp.service.swtchart.core.BaseChart;
import org.eclipse.eavp.service.swtchart.core.IAxisScaleConverter;
import org.eclipse.eavp.service.swtchart.core.IAxisSettings;
import org.eclipse.eavp.service.swtchart.core.ISecondaryAxisSettings;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Shell;
import org.swtchart.ISeries;

public class LaTeXTableExport implements ISeriesExportConverter {

	private static final String FILE_EXTENSION = "*.tex";
	private static final String NAME = "LaTeX Table (" + FILE_EXTENSION + ")";
	//
	private static final String TITLE = "Save As LaTeX Table";
	private static final String TAB = "\t";
	private static final String DELIMITER = " & ";
	private static final String HORIZONTAL_LINE = "\\hline";
	private static final String LINE_END = " \\\\";

	@Override
	public String getName() {

		return NAME;
	}

	@Override
	public void export(Shell shell, BaseChart baseChart) {

		/*
		 * Select the export file.
		 */
		FileDialog fileDialog = new FileDialog(shell, SWT.SAVE);
		fileDialog.setText(TITLE);
		fileDialog.setFilterExtensions(new String[]{FILE_EXTENSION});
		//
		String fileName = fileDialog.open();
		if(fileName != null) {
			/*
			 * Select the X and Y axis to export.
			 */
			ExportSettingsDialog exportSettingsDialog = new ExportSettingsDialog(shell, baseChart);
			exportSettingsDialog.create();
			if(exportSettingsDialog.open() == Window.OK) {
				//
				int indexAxisX = exportSettingsDialog.getIndexAxisSelectionX();
				int indexAxisY = exportSettingsDialog.getIndexAxisSelectionY();
				//
				if(indexAxisX >= 0 && indexAxisY >= 0) {
					/*
					 * X Axis Settings
					 */
					IAxisSettings axisSettingsX = baseChart.getXAxisSettingsMap().get(indexAxisX);
					DecimalFormat decimalFormatX = axisSettingsX.getDecimalFormat();
					IAxisScaleConverter axisScaleConverterX = null;
					if(axisSettingsX instanceof ISecondaryAxisSettings) {
						ISecondaryAxisSettings secondaryAxisSettings = (ISecondaryAxisSettings)axisSettingsX;
						axisScaleConverterX = secondaryAxisSettings.getAxisScaleConverter();
					}
					/*
					 * Y Axis Settings
					 */
					IAxisSettings axisSettingsY = baseChart.getYAxisSettingsMap().get(indexAxisY);
					DecimalFormat decimalFormatY = axisSettingsY.getDecimalFormat();
					IAxisScaleConverter axisScaleConverterY = null;
					if(axisSettingsY instanceof ISecondaryAxisSettings) {
						ISecondaryAxisSettings secondaryAxisSettings = (ISecondaryAxisSettings)axisSettingsY;
						axisScaleConverterY = secondaryAxisSettings.getAxisScaleConverter();
					}
					/*
					 * Print the XY data.
					 */
					PrintWriter printWriter = null;
					try {
						printWriter = new PrintWriter(new File(fileName));
						/*
						 * Header
						 */
						printWriter.println("\\begin{center}");
						printWriter.println("\\begin{tabular}{ c c }");
						//
						printWriter.print(TAB);
						printWriter.print(axisSettingsX.getLabel());
						printWriter.print(DELIMITER);
						printWriter.print(axisSettingsY.getLabel());
						printWriter.println(LINE_END);
						/*
						 * Data
						 */
						int widthPlotArea = baseChart.getPlotArea().getBounds().width;
						ISeries[] series = baseChart.getSeriesSet().getSeries();
						for(ISeries dataSeries : series) {
							if(dataSeries != null) {
								/*
								 * Series
								 */
								printWriter.println(TAB + HORIZONTAL_LINE);
								printWriter.println(TAB + dataSeries.getId() + DELIMITER + LINE_END);
								printWriter.println(TAB + HORIZONTAL_LINE);
								//
								double[] xSeries = dataSeries.getXSeries();
								double[] ySeries = dataSeries.getYSeries();
								int size = dataSeries.getXSeries().length;
								//
								for(int i = 0; i < size; i++) {
									/*
									 * Only export if the data point is visible.
									 */
									Point point = dataSeries.getPixelCoordinates(i);
									if(point.x >= 0 && point.x <= widthPlotArea) {
										printWriter.print(TAB);
										printValue(printWriter, xSeries[i], indexAxisX, BaseChart.ID_PRIMARY_X_AXIS, decimalFormatX, axisScaleConverterX);
										printWriter.print(DELIMITER);
										printValue(printWriter, ySeries[i], indexAxisY, BaseChart.ID_PRIMARY_Y_AXIS, decimalFormatY, axisScaleConverterY);
										printWriter.println(LINE_END);
									}
								}
								//
								printWriter.print(TAB);
								printWriter.print(DELIMITER);
								printWriter.println(LINE_END);
							}
						}
						//
						printWriter.println("\\end{tabular}");
						printWriter.println("\\end{center}");
						printWriter.flush();
						MessageDialog.openInformation(shell, TITLE, MESSAGE_OK);
					} catch(FileNotFoundException e) {
						MessageDialog.openError(shell, TITLE, MESSAGE_ERROR);
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

	private void printValue(PrintWriter printWriter, double value, int indexAxis, int indexPrimaryAxis, DecimalFormat decimalFormat, IAxisScaleConverter axisScaleConverter) {

		if(indexAxis == indexPrimaryAxis) {
			printWriter.print(value);
		} else {
			if(axisScaleConverter != null) {
				printWriter.print(decimalFormat.format(axisScaleConverter.convertToSecondaryUnit(value)));
			} else {
				printWriter.print(decimalFormat.format(value));
			}
		}
	}
}
