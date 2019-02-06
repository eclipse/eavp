/*******************************************************************************
 * Copyright (c) 2019 UT-Battelle, LLC.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   Robert Smith
 *******************************************************************************/
package org.eclipse.eavp.micro.vaadin.csv;

import org.dussan.vaadin.dcharts.base.renderers.TickRenderer;
import org.dussan.vaadin.dcharts.metadata.ticks.TickFormatters;
import org.dussan.vaadin.dcharts.metadata.ticks.TickMarks;

public class EditableTickRenderer extends TickRenderer {

	public EditableTickRenderer(TickMarks tickMarks, boolean showMark,
			boolean showGridline, boolean isMinorTick, int markSize,
			boolean show, boolean showLabel, TickFormatters tickFormatters,
			String prefix, String formatString, String fontFamily,
			String fontSize, String textColor) {
		super(tickMarks, showMark, showGridline, isMinorTick, markSize, show,
				showLabel, tickFormatters, prefix, formatString, fontFamily,
				fontSize, textColor);
	}

	@Override
	public String getFontFamily() {
		return super.getFontFamily();
	}

	@Override
	public String getFontSize() {
		return super.getFontSize();
	}

	@Override
	public String getFormatString() {
		return super.getFormatString();
	}

	@Override
	public String getPrefix() {
		return super.getPrefix();
	}

	@Override
	public boolean getShowGridline() {
		return super.getShowGridline();
	}

	@Override
	public int getMarkSize() {
		return super.getMarkSize();
	}

	@Override
	public boolean getIsMinorTick() {
		return super.getIsMinorTick();
	}

	@Override
	public boolean getShow() {
		return super.getShow();
	}

	@Override
	public boolean getShowLabel() {
		return super.getShowLabel();
	}

	@Override
	public boolean getShowMark() {
		return super.getShowMark();
	}

	@Override
	public String getTextColor() {
		return super.getTextColor();
	}

	@Override
	public TickRenderer setFontFamily(String newFontFamily) {
		return super.setFontFamily(newFontFamily);
	}

	@Override
	public TickRenderer setFontSize(String newFontSize) {
		return super.setFontSize(newFontSize);
	}

	@Override
	public TickRenderer setFormatString(String newFormatString) {
		return super.setFormatString(newFormatString);
	}

	@Override
	public TickRenderer setPrefix(String newPrefix) {
		return super.setPrefix(newPrefix);
	}

	@Override
	public TickRenderer setShowGridline(boolean newShowGridLine) {
		return super.setShowGridline(newShowGridLine);
	}

	public TickFormatters getTickFormatters() {
		return super.getFormatter();
	}

	@Override
	public TickRenderer setMarkSize(int newMarkSize) {
		return super.setMarkSize(newMarkSize);
	}

	@Override
	public TickRenderer setIsMinorTick(boolean newIsMinorTick) {
		return super.setIsMinorTick(newIsMinorTick);
	}

	@Override
	public TickRenderer setShow(boolean newShow) {
		return super.setShow(newShow);
	}

	@Override
	public TickRenderer setShowLabel(boolean newShowLabel) {
		return super.setShowLabel(newShowLabel);
	}

	@Override
	public TickRenderer setShowMark(boolean newShowMark) {
		return super.setShowMark(newShowMark);
	}

	public TickRenderer setTickFormatters(TickFormatters tickFormatters) {
		return super.setFormatter(tickFormatters);
	}

	@Override
	public TickRenderer setTextColor(String newTextColor) {
		return super.setTextColor(newTextColor);
	}

}
