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

public class ImageRScriptExport implements ISeriesExportConverter {

	private static final String FILE_EXTENSION = "*.R";
	private static final String NAME = "Image R-Script (" + FILE_EXTENSION + ")";
	//
	private static final String TITLE = "Save As R-Script Image";
	//
	private static final String AXIS_X = "x";
	private static final String AXIS_Y = "y";

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
		fileDialog.setOverwrite(true);
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
					IAxisScaleConverter axisScaleConverterX = null;
					if(axisSettingsX instanceof ISecondaryAxisSettings) {
						ISecondaryAxisSettings secondaryAxisSettings = (ISecondaryAxisSettings)axisSettingsX;
						axisScaleConverterX = secondaryAxisSettings.getAxisScaleConverter();
					}
					/*
					 * Y Axis Settings
					 */
					IAxisSettings axisSettingsY = baseChart.getYAxisSettingsMap().get(indexAxisY);
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
						ISeries[] series = baseChart.getSeriesSet().getSeries();
						int seriesSize = series.length;
						//
						printWriter.println("# Header");
						printWriter.println("xValueList<-vector(\"list\", " + seriesSize + ")");
						printWriter.println("yValueList<-vector(\"list\", " + seriesSize + ")");
						printWriter.println("");
						/*
						 * Data
						 */
						printWriter.println("# Data");
						int widthPlotArea = baseChart.getPlotArea().getBounds().width;
						int index = 1;
						for(ISeries dataSeries : series) {
							if(dataSeries != null) {
								/*
								 * Series
								 */
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
										printValue(AXIS_X, index, printWriter, xSeries[i], indexAxisX, BaseChart.ID_PRIMARY_X_AXIS, axisScaleConverterX);
										printValue(AXIS_Y, index, printWriter, ySeries[i], indexAxisY, BaseChart.ID_PRIMARY_Y_AXIS, axisScaleConverterY);
									}
								}
								//
								index++;
							}
						}
						printWriter.println("");
						/*
						 * Footer
						 */
						printWriter.println("#  Footer");
						printWriter.println("colorList<-c(\"black\", \"red\", \"blue\", \"green\", \"grey\", \"purple\", \"brown\", \"pink\", \"yellow\", \"orange\")");
						//
						printWriter.println("");
						printWriter.println("plot(");
						printWriter.println("	xValueList[[1]], yValueList[[1]],");
						printWriter.println("	xlim=c(range(xValueList)[1], range(xValueList)[2]),");
						printWriter.println("	ylim=c(range(yValueList)[1], range(yValueList)[2]),");
						printWriter.println("	type='l',");
						printWriter.println("	col=colorList[1],");
						printWriter.println("	ylab='" + axisSettingsY.getLabel() + "',");
						printWriter.println("	xlab='" + axisSettingsX.getLabel() + "'");
						printWriter.println(")");
						printWriter.println("");
						//
						if(seriesSize > 1) {
							printWriter.println("for(i in 2:" + seriesSize + "){");
							printWriter.println("	points(xValueList[[i]], yValueList[[i]], type='l', col=colorList[(i+8)%%9+1])");
							printWriter.println("}");
							printWriter.println("");
						}
						//
						int k;
						printWriter.println("legend('topleft',");
						printWriter.println("		c(");
						k = 0;
						for(ISeries dataSeries : series) {
							if(dataSeries != null) {
								printWriter.print("			'Series " + dataSeries.getDescription() + "'");
								if(k < seriesSize - 1) {
									printWriter.print(",");
								}
								printWriter.println();
								k++;
							}
						}
						printWriter.println("		),");
						printWriter.println("		col=c(");
						k = 0;
						for(ISeries dataSeries : series) {
							if(dataSeries != null) {
								printWriter.print("			colorList[(" + (k + 1) + "+8)%%9+1]");
								if(k < seriesSize - 1) {
									printWriter.print(",");
								}
								printWriter.println();
								k++;
							}
						}
						printWriter.println("		),");
						printWriter.println("		lwd=2");
						printWriter.println("	)");
						printWriter.println("");
						//
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

	private void printValue(String axis, int index, PrintWriter printWriter, double value, int indexAxis, int indexPrimaryAxis, IAxisScaleConverter axisScaleConverter) {

		if(indexAxis == indexPrimaryAxis || axisScaleConverter == null) {
			if(axis.equals(AXIS_X)) {
				printWriter.println("xValueList[[" + index + "]]<-c(xValueList[[" + index + "]]," + value + ")");
			} else if(axis.equals(AXIS_Y)) {
				printWriter.println("yValueList[[" + index + "]]<-c(yValueList[[" + index + "]]," + value + ")");
			}
		} else {
			if(axisScaleConverter != null) {
				if(axis.equals(AXIS_X)) {
					printWriter.println("xValueList[[" + index + "]]<-c(xValueList[[" + index + "]]," + axisScaleConverter.convertToSecondaryUnit(value) + ")");
				} else if(axis.equals(AXIS_Y)) {
					printWriter.println("yValueList[[" + index + "]]<-c(yValueList[[" + index + "]]," + axisScaleConverter.convertToSecondaryUnit(value) + ")");
				}
			}
		}
	}
}
