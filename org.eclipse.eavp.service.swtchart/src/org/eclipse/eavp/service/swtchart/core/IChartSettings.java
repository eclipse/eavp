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

import java.util.List;
import java.util.Set;

import org.eclipse.eavp.service.swtchart.menu.IMenuEntry;
import org.eclipse.swt.graphics.Color;

public interface IChartSettings {

	boolean isEnableRangeSelector();

	void setEnableRangeSelector(boolean enableRangeSelector);

	int getRangeSelectorDefaultAxisX();

	void setRangeSelectorDefaultAxisX(int rangeSelectorDefaultAxisX);

	int getRangeSelectorDefaultAxisY();

	void setRangeSelectorDefaultAxisY(int rangeSelectorDefaultAxisY);

	Color getColorHintRangeSelector();

	void setColorHintRangeSelector(Color colorHintRangeSelector);

	boolean isVerticalSliderVisible();

	void setVerticalSliderVisible(boolean verticalSliderVisible);

	boolean isHorizontalSliderVisible();

	void setHorizontalSliderVisible(boolean horizontalSliderVisible);

	String getTitle();

	void setTitle(String title);

	boolean isTitleVisible();

	void setTitleVisible(boolean titleVisible);

	Color getTitleColor();

	void setTitleColor(Color titleColor);

	boolean isLegendVisible();

	void setLegendVisible(boolean legendVisible);

	int getLegendPosition();

	/**
	 * Use:
	 * SWT.LEFT
	 * SWT.RIGHT
	 * SWT.TOP
	 * SWT.BOTTOM
	 * 
	 * @param legendPosition
	 */
	void setLegendPosition(int legendPosition);

	IPrimaryAxisSettings getPrimaryAxisSettingsX();

	IPrimaryAxisSettings getPrimaryAxisSettingsY();

	List<ISecondaryAxisSettings> getSecondaryAxisSettingsListX();

	List<ISecondaryAxisSettings> getSecondaryAxisSettingsListY();

	int getOrientation();

	/**
	 * SWT.HORIZONTAL or SWT.VERTICAL
	 * See:http://www.swtchart.org/doc/index.html#Chart_Orientation
	 * 
	 * @param orientation
	 */
	void setOrientation(int orientation);

	Color getBackground();

	void setBackground(Color background);

	Color getBackgroundChart();

	void setBackgroundChart(Color backgroundChart);

	Color getBackgroundPlotArea();

	void setBackgroundPlotArea(Color backgroundPlotArea);

	boolean isEnableCompress();

	void setEnableCompress(boolean enableCompress);

	RangeRestriction getRangeRestriction();

	boolean isShowPositionMarker();

	void setShowPositionMarker(boolean showPositionMarker);

	Color getColorPositionMarker();

	void setColorPositionMarker(Color colorPositionMarker);

	boolean isShowCenterMarker();

	void setShowCenterMarker(boolean showCenterMarker);

	Color getColorCenterMarker();

	void setColorCenterMarker(Color colorCenterMarker);

	boolean isShowPositionLegend();

	void setShowPositionLegend(boolean showPositionLegend);

	Color getColorPositionLegend();

	void setColorPositionLegend(Color colorPositionLegend);

	boolean isCreateMenu();

	void setCreateMenu(boolean createMenu);

	void addMenuEntry(IMenuEntry menuEntry);

	void removeMenuEntry(IMenuEntry menuEntry);

	Set<IMenuEntry> getMenuEntries();

	void clearMenuEntries();
}