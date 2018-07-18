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
package org.eclipse.eavp.service.swtchart.core;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.eclipse.eavp.service.swtchart.events.IHandledEventProcessor;
import org.eclipse.eavp.service.swtchart.events.MouseDownEvent;
import org.eclipse.eavp.service.swtchart.events.MouseMoveCursorEvent;
import org.eclipse.eavp.service.swtchart.events.MouseMoveSelectionEvent;
import org.eclipse.eavp.service.swtchart.events.MouseMoveShiftEvent;
import org.eclipse.eavp.service.swtchart.events.MouseUpEvent;
import org.eclipse.eavp.service.swtchart.events.ResetSeriesEvent;
import org.eclipse.eavp.service.swtchart.events.SelectDataPointEvent;
import org.eclipse.eavp.service.swtchart.events.SelectHideSeriesEvent;
import org.eclipse.eavp.service.swtchart.events.UndoRedoEvent;
import org.eclipse.eavp.service.swtchart.events.ZoomEvent;
import org.eclipse.eavp.service.swtchart.menu.IChartMenuEntry;
import org.eclipse.eavp.service.swtchart.menu.RedoSelectionHandler;
import org.eclipse.eavp.service.swtchart.menu.ResetChartHandler;
import org.eclipse.eavp.service.swtchart.menu.ResetSelectedSeriesHandler;
import org.eclipse.eavp.service.swtchart.menu.UndoSelectionHandler;
import org.eclipse.eavp.service.swtchart.menu.export.BMPExportHandler;
import org.eclipse.eavp.service.swtchart.menu.export.CSVExportHandler;
import org.eclipse.eavp.service.swtchart.menu.export.JPGExportHandler;
import org.eclipse.eavp.service.swtchart.menu.export.LaTeXTableExportHandler;
import org.eclipse.eavp.service.swtchart.menu.export.PNGExportHandler;
import org.eclipse.eavp.service.swtchart.menu.export.PrinterExportHandler;
import org.eclipse.eavp.service.swtchart.menu.export.RScriptExportHandler;
import org.eclipse.eavp.service.swtchart.menu.export.TSVExportHandler;
import org.eclipse.eavp.service.swtchart.menu.toggle.ToggleAxisZeroMarkerHandler;
import org.eclipse.eavp.service.swtchart.menu.toggle.ToggleLabelTooltipsHandler;
import org.eclipse.eavp.service.swtchart.menu.toggle.ToggleLegendMarkerHandler;
import org.eclipse.eavp.service.swtchart.menu.toggle.TogglePlotCenterMarkerHandler;
import org.eclipse.eavp.service.swtchart.menu.toggle.TogglePositionMarkerHandler;
import org.eclipse.eavp.service.swtchart.menu.toggle.ToggleRangeSelectorHandler;
import org.eclipse.eavp.service.swtchart.menu.toggle.ToggleSeriesLabelMarkerHandler;
import org.eclipse.eavp.service.swtchart.menu.toggle.ToggleSeriesLegendHandler;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.widgets.Display;

public class ChartSettings implements IChartSettings {

	private boolean enableRangeSelector;
	private boolean showRangeSelectorInitially;
	private Color colorHintRangeSelector;
	private int rangeSelectorDefaultAxisX;
	private int rangeSelectorDefaultAxisY;
	//
	private boolean verticalSliderVisible;
	private boolean horizontalSliderVisible;
	//
	private String title;
	private boolean titleVisible;
	private Color titleColor;
	//
	private int legendPosition;
	private boolean legendVisible;
	//
	private IPrimaryAxisSettings primaryAxisSettingsX;
	private IPrimaryAxisSettings primaryAxisSettingsY;
	private List<ISecondaryAxisSettings> secondaryAxisSettingsListX;
	private List<ISecondaryAxisSettings> secondaryAxisSettingsListY;
	//
	private int orientation;
	private Color background;
	private Color backgroundChart;
	private Color backgroundPlotArea;
	private boolean enableCompress;
	private RangeRestriction rangeRestriction;
	//
	private boolean showPositionMarker;
	private Color colorPositionMarker;
	private boolean showPlotCenterMarker;
	private Color colorPlotCenterMarker;
	private boolean showLegendMarker;
	private Color colorLegendMarker;
	private boolean showAxisZeroMarker;
	private Color colorAxisZeroMarker;
	private boolean showSeriesLabelMarker;
	private Color colorSeriesLabelMarker;
	//
	private boolean createMenu;
	private Set<IChartMenuEntry> menuEntries;
	private Set<IHandledEventProcessor> handledEventProcessors;
	//
	private boolean supportDataShift;
	private boolean enableTooltips;

	public ChartSettings() {
		//
		Display display = Display.getDefault();
		/*
		 * Set the chart.
		 */
		enableRangeSelector = false;
		showRangeSelectorInitially = true;
		colorHintRangeSelector = display.getSystemColor(SWT.COLOR_RED);
		rangeSelectorDefaultAxisX = 0;
		rangeSelectorDefaultAxisY = 0;
		//
		verticalSliderVisible = false; // https://bugs.eclipse.org/bugs/show_bug.cgi?id=511257
		horizontalSliderVisible = true;
		/*
		 * If the title is empty, it won't be displayed.
		 * To display a space on top of the chart, a default
		 * title is set and WHITE is used to hide it.
		 */
		title = "Chart Title";
		titleVisible = true;
		titleColor = display.getSystemColor(SWT.COLOR_WHITE);
		//
		legendPosition = SWT.RIGHT;
		legendVisible = false;
		//
		primaryAxisSettingsX = new PrimaryAxisSettings(BaseChart.DEFAULT_TITLE_X_AXIS);
		primaryAxisSettingsY = new PrimaryAxisSettings(BaseChart.DEFAULT_TITLE_Y_AXIS);
		secondaryAxisSettingsListX = new ArrayList<ISecondaryAxisSettings>();
		secondaryAxisSettingsListY = new ArrayList<ISecondaryAxisSettings>();
		//
		orientation = SWT.HORIZONTAL;
		background = display.getSystemColor(SWT.COLOR_WHITE);
		backgroundChart = display.getSystemColor(SWT.COLOR_WHITE);
		backgroundPlotArea = display.getSystemColor(SWT.COLOR_WHITE);
		enableCompress = true;
		rangeRestriction = new RangeRestriction();
		rangeRestriction.setZeroX(true);
		rangeRestriction.setZeroY(true);
		rangeRestriction.setRestrictZoom(true);
		//
		showPositionMarker = false;
		colorPositionMarker = display.getSystemColor(SWT.COLOR_DARK_GRAY);
		showPlotCenterMarker = false;
		colorPlotCenterMarker = display.getSystemColor(SWT.COLOR_DARK_GRAY);
		showLegendMarker = false;
		colorLegendMarker = display.getSystemColor(SWT.COLOR_DARK_GRAY);
		showAxisZeroMarker = false;
		colorAxisZeroMarker = display.getSystemColor(SWT.COLOR_DARK_GRAY);
		showSeriesLabelMarker = false;
		colorSeriesLabelMarker = display.getSystemColor(SWT.COLOR_DARK_GRAY);
		/*
		 * Default menu entries.
		 */
		createMenu = false;
		menuEntries = new HashSet<IChartMenuEntry>();
		menuEntries.add(new ResetChartHandler());
		menuEntries.add(new ResetSelectedSeriesHandler());
		menuEntries.add(new ToggleRangeSelectorHandler());
		menuEntries.add(new ToggleLegendMarkerHandler());
		menuEntries.add(new TogglePositionMarkerHandler());
		menuEntries.add(new ToggleSeriesLegendHandler());
		menuEntries.add(new TogglePlotCenterMarkerHandler());
		menuEntries.add(new ToggleAxisZeroMarkerHandler());
		menuEntries.add(new ToggleSeriesLabelMarkerHandler());
		menuEntries.add(new ToggleLabelTooltipsHandler());
		menuEntries.add(new JPGExportHandler());
		menuEntries.add(new PNGExportHandler());
		menuEntries.add(new BMPExportHandler());
		menuEntries.add(new TSVExportHandler());
		menuEntries.add(new CSVExportHandler());
		menuEntries.add(new LaTeXTableExportHandler());
		menuEntries.add(new RScriptExportHandler());
		menuEntries.add(new PrinterExportHandler());
		menuEntries.add(new UndoSelectionHandler());
		menuEntries.add(new RedoSelectionHandler());
		/*
		 * Events processors ... Mouse Move, Key Up ...
		 */
		handledEventProcessors = new HashSet<IHandledEventProcessor>();
		handledEventProcessors.add(new SelectHideSeriesEvent());
		handledEventProcessors.add(new ResetSeriesEvent());
		handledEventProcessors.add(new SelectDataPointEvent());
		handledEventProcessors.add(new ZoomEvent());
		handledEventProcessors.add(new MouseDownEvent());
		handledEventProcessors.add(new MouseMoveSelectionEvent());
		handledEventProcessors.add(new MouseMoveShiftEvent());
		handledEventProcessors.add(new MouseMoveCursorEvent());
		handledEventProcessors.add(new MouseUpEvent());
		handledEventProcessors.add(new UndoRedoEvent());
		//
		supportDataShift = false;
	}

	@Override
	public boolean isEnableRangeSelector() {

		return enableRangeSelector;
	}

	@Override
	public void setEnableRangeSelector(boolean enableRangeSelector) {

		this.enableRangeSelector = enableRangeSelector;
	}

	@Override
	public boolean isShowRangeSelectorInitially() {

		return showRangeSelectorInitially;
	}

	@Override
	public void setShowRangeSelectorInitially(boolean showRangeSelectorInitially) {

		this.showRangeSelectorInitially = showRangeSelectorInitially;
	}

	@Override
	public Color getColorHintRangeSelector() {

		return colorHintRangeSelector;
	}

	@Override
	public void setColorHintRangeSelector(Color colorHintRangeSelector) {

		this.colorHintRangeSelector = colorHintRangeSelector;
	}

	@Override
	public int getRangeSelectorDefaultAxisX() {

		return rangeSelectorDefaultAxisX;
	}

	@Override
	public void setRangeSelectorDefaultAxisX(int rangeSelectorDefaultAxisX) {

		this.rangeSelectorDefaultAxisX = rangeSelectorDefaultAxisX;
	}

	@Override
	public int getRangeSelectorDefaultAxisY() {

		return rangeSelectorDefaultAxisY;
	}

	@Override
	public void setRangeSelectorDefaultAxisY(int rangeSelectorDefaultAxisY) {

		this.rangeSelectorDefaultAxisY = rangeSelectorDefaultAxisY;
	}

	@Override
	public boolean isVerticalSliderVisible() {

		return verticalSliderVisible;
	}

	@Override
	public void setVerticalSliderVisible(boolean verticalSliderVisible) {

		/*
		 * There is a bug when using the SWT.RIGHT_TO_LEFT orientation.
		 * https://bugs.eclipse.org/bugs/show_bug.cgi?id=511257
		 * That's why the vertical slider is not visible yet.
		 */
		// this.verticalSliderVisible = verticalSliderVisible;
		this.verticalSliderVisible = false;
		System.out.println("Can't set vertical slider true, see: https://bugs.eclipse.org/bugs/show_bug.cgi?id=511257");
	}

	@Override
	public boolean isHorizontalSliderVisible() {

		return horizontalSliderVisible;
	}

	@Override
	public void setHorizontalSliderVisible(boolean horizontalSliderVisible) {

		this.horizontalSliderVisible = horizontalSliderVisible;
	}

	@Override
	public String getTitle() {

		return title;
	}

	@Override
	public void setTitle(String title) {

		if(title != null) {
			this.title = title;
		} else {
			this.title = "";
		}
	}

	@Override
	public boolean isTitleVisible() {

		return titleVisible;
	}

	@Override
	public void setTitleVisible(boolean titleVisible) {

		this.titleVisible = titleVisible;
	}

	@Override
	public Color getTitleColor() {

		return titleColor;
	}

	@Override
	public void setTitleColor(Color titleColor) {

		this.titleColor = titleColor;
	}

	@Override
	public int getLegendPosition() {

		return legendPosition;
	}

	@Override
	public void setLegendPosition(int legendPosition) {

		this.legendPosition = legendPosition;
	}

	@Override
	public boolean isLegendVisible() {

		return legendVisible;
	}

	@Override
	public void setLegendVisible(boolean legendVisible) {

		this.legendVisible = legendVisible;
	}

	@Override
	public IPrimaryAxisSettings getPrimaryAxisSettingsX() {

		return primaryAxisSettingsX;
	}

	@Override
	public IPrimaryAxisSettings getPrimaryAxisSettingsY() {

		return primaryAxisSettingsY;
	}

	@Override
	public List<ISecondaryAxisSettings> getSecondaryAxisSettingsListX() {

		return secondaryAxisSettingsListX;
	}

	@Override
	public List<ISecondaryAxisSettings> getSecondaryAxisSettingsListY() {

		return secondaryAxisSettingsListY;
	}

	@Override
	public int getOrientation() {

		return orientation;
	}

	/**
	 * SWT.HORIZONTAL or SWT.VERTICAL
	 * See:http://www.swtchart.org/doc/index.html#Chart_Orientation
	 * 
	 * @param orientation
	 */
	@Override
	public void setOrientation(int orientation) {

		this.orientation = orientation;
	}

	@Override
	public Color getBackground() {

		return background;
	}

	@Override
	public void setBackground(Color background) {

		this.background = background;
	}

	@Override
	public Color getBackgroundChart() {

		return backgroundChart;
	}

	@Override
	public void setBackgroundChart(Color backgroundChart) {

		this.backgroundChart = backgroundChart;
	}

	@Override
	public Color getBackgroundPlotArea() {

		return backgroundPlotArea;
	}

	@Override
	public void setBackgroundPlotArea(Color backgroundPlotArea) {

		this.backgroundPlotArea = backgroundPlotArea;
	}

	@Override
	public boolean isEnableCompress() {

		return enableCompress;
	}

	@Override
	public void setEnableCompress(boolean enableCompress) {

		this.enableCompress = enableCompress;
	}

	@Override
	public RangeRestriction getRangeRestriction() {

		return rangeRestriction;
	}

	@Override
	public boolean isShowPositionMarker() {

		return showPositionMarker;
	}

	@Override
	public void setShowPositionMarker(boolean showPositionMarker) {

		this.showPositionMarker = showPositionMarker;
	}

	@Override
	public Color getColorPositionMarker() {

		return colorPositionMarker;
	}

	@Override
	public void setColorPositionMarker(Color colorPositionMarker) {

		this.colorPositionMarker = colorPositionMarker;
	}

	@Override
	public boolean isShowPlotCenterMarker() {

		return showPlotCenterMarker;
	}

	@Override
	public void setShowPlotCenterMarker(boolean showPlotCenterMarker) {

		this.showPlotCenterMarker = showPlotCenterMarker;
	}

	@Override
	public Color getColorPlotCenterMarker() {

		return colorPlotCenterMarker;
	}

	@Override
	public void setColorPlotCenterMarker(Color colorPlotCenterMarker) {

		this.colorPlotCenterMarker = colorPlotCenterMarker;
	}

	@Override
	public boolean isShowLegendMarker() {

		return showLegendMarker;
	}

	@Override
	public void setShowLegendMarker(boolean showLegendMarker) {

		this.showLegendMarker = showLegendMarker;
	}

	@Override
	public Color getColorLegendMarker() {

		return colorLegendMarker;
	}

	@Override
	public void setColorLegendMarker(Color colorLegendMarker) {

		this.colorLegendMarker = colorLegendMarker;
	}

	@Override
	public boolean isShowAxisZeroMarker() {

		return showAxisZeroMarker;
	}

	@Override
	public void setShowAxisZeroMarker(boolean showAxisZeroMarker) {

		this.showAxisZeroMarker = showAxisZeroMarker;
	}

	@Override
	public Color getColorAxisZeroMarker() {

		return colorAxisZeroMarker;
	}

	@Override
	public void setColorAxisZeroMarker(Color colorAxisZeroMarker) {

		this.colorAxisZeroMarker = colorAxisZeroMarker;
	}

	@Override
	public boolean isShowSeriesLabelMarker() {

		return showSeriesLabelMarker;
	}

	@Override
	public void setShowSeriesLabelMarker(boolean showSeriesLabelMarker) {

		this.showSeriesLabelMarker = showSeriesLabelMarker;
	}

	@Override
	public Color getColorSeriesLabelMarker() {

		return colorSeriesLabelMarker;
	}

	@Override
	public void setColorSeriesLabelMarker(Color colorSeriesLabelMarker) {

		this.colorSeriesLabelMarker = colorSeriesLabelMarker;
	}

	@Override
	public boolean isCreateMenu() {

		return createMenu;
	}

	@Override
	public void setCreateMenu(boolean createMenu) {

		this.createMenu = createMenu;
	}

	@Override
	public void addMenuEntry(IChartMenuEntry menuEntry) {

		menuEntries.add(menuEntry);
	}

	@Override
	public void removeMenuEntry(IChartMenuEntry menuEntry) {

		menuEntries.remove(menuEntry);
	}

	@Override
	public Set<IChartMenuEntry> getMenuEntries() {

		return Collections.unmodifiableSet(menuEntries);
	}

	@Override
	public IChartMenuEntry getChartMenuEntry(String name) {

		for(IChartMenuEntry chartMenuEntry : menuEntries) {
			if(chartMenuEntry.getName().equals(name)) {
				return chartMenuEntry;
			}
		}
		//
		return null;
	}

	@Override
	public void clearMenuEntries() {

		menuEntries.clear();
	}

	@Override
	public boolean isSupportDataShift() {

		return supportDataShift;
	}

	@Override
	public void setSupportDataShift(boolean supportDataShift) {

		this.supportDataShift = supportDataShift;
	}

	@Override
	public void addHandledEventProcessor(IHandledEventProcessor handledEventProcessor) {

		handledEventProcessors.add(handledEventProcessor);
	}

	@Override
	public void removeHandledEventProcessor(IHandledEventProcessor handledEventProcessor) {

		handledEventProcessors.remove(handledEventProcessor);
	}

	@Override
	public Set<IHandledEventProcessor> getHandledEventProcessors() {

		return Collections.unmodifiableSet(handledEventProcessors);
	}

	@Override
	public void clearHandledEventProcessors() {

		handledEventProcessors.clear();
	}

	@Override
	public void setEnableTooltips(boolean enable) {

		enableTooltips = enable;
	}

	@Override
	public boolean isEnableTooltips() {

		return enableTooltips;
	}
}
