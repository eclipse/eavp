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

/**
 * A version of TickRenderer that exposes the data members for editing
 * 
 * @author Robert Smith
 *
 */
public class EditableTickRenderer extends TickRenderer {

	/**
	 * The default constructor.
	 * 
	 * @param tickMarks
	 * @param showMark
	 * @param showGridline
	 * @param isMinorTick
	 * @param markSize
	 * @param show
	 * @param showLabel
	 * @param tickFormatters
	 * @param prefix
	 * @param formatString
	 * @param fontFamily
	 * @param fontSize
	 * @param textColor
	 */
	public EditableTickRenderer(TickMarks tickMarks, boolean showMark,
			boolean showGridline, boolean isMinorTick, int markSize,
			boolean show, boolean showLabel, TickFormatters tickFormatters,
			String prefix, String formatString, String fontFamily,
			String fontSize, String textColor) {
		super(tickMarks, showMark, showGridline, isMinorTick, markSize, show,
				showLabel, tickFormatters, prefix, formatString, fontFamily,
				fontSize, textColor);
	}

	/*
	 * (non-Javadoc)
	 * @see org.dussan.vaadin.dcharts.base.renderers.TickRenderer#getFontFamily()
	 */
	@Override
	public String getFontFamily() {
		return super.getFontFamily();
	}

	/*
	 * (non-Javadoc)
	 * @see org.dussan.vaadin.dcharts.base.renderers.TickRenderer#getFontSize()
	 */
	@Override
	public String getFontSize() {
		return super.getFontSize();
	}

	/*
	 * (non-Javadoc)
	 * @see org.dussan.vaadin.dcharts.base.renderers.TickRenderer#getFormatString()
	 */
	@Override
	public String getFormatString() {
		return super.getFormatString();
	}

	/*
	 * (non-Javadoc)
	 * @see org.dussan.vaadin.dcharts.base.renderers.TickRenderer#getPrefix()
	 */
	@Override
	public String getPrefix() {
		return super.getPrefix();
	}

	/*
	 * (non-Javadoc)
	 * @see org.dussan.vaadin.dcharts.base.renderers.TickRenderer#getShowGridline()
	 */
	@Override
	public boolean getShowGridline() {
		return super.getShowGridline();
	}

	/*
	 * (non-Javadoc)
	 * @see org.dussan.vaadin.dcharts.base.renderers.TickRenderer#getMarkSize()
	 */
	@Override
	public int getMarkSize() {
		return super.getMarkSize();
	}

	/*
	 * (non-Javadoc)
	 * @see org.dussan.vaadin.dcharts.base.renderers.TickRenderer#getIsMinorTick()
	 */
	@Override
	public boolean getIsMinorTick() {
		return super.getIsMinorTick();
	}

	/*
	 * (non-Javadoc)
	 * @see org.dussan.vaadin.dcharts.base.renderers.TickRenderer#getShow()
	 */
	@Override
	public boolean getShow() {
		return super.getShow();
	}

	/*
	 * (non-Javadoc)
	 * @see org.dussan.vaadin.dcharts.base.renderers.TickRenderer#getShowLabel()
	 */
	@Override
	public boolean getShowLabel() {
		return super.getShowLabel();
	}

	/*
	 * (non-Javadoc)
	 * @see org.dussan.vaadin.dcharts.base.renderers.TickRenderer#getShowMark()
	 */
	@Override
	public boolean getShowMark() {
		return super.getShowMark();
	}

	/*
	 * (non-Javadoc)
	 * @see org.dussan.vaadin.dcharts.base.renderers.TickRenderer#getTextColor()
	 */
	@Override
	public String getTextColor() {
		return super.getTextColor();
	}

	/*
	 * (non-Javadoc)
	 * @see org.dussan.vaadin.dcharts.base.renderers.TickRenderer#setFontFamily(java.lang.String)
	 */
	@Override
	public TickRenderer setFontFamily(String newFontFamily) {
		return super.setFontFamily(newFontFamily);
	}

	/*
	 * (non-Javadoc)
	 * @see org.dussan.vaadin.dcharts.base.renderers.TickRenderer#setFontSize(java.lang.String)
	 */
	@Override
	public TickRenderer setFontSize(String newFontSize) {
		return super.setFontSize(newFontSize);
	}

	/*
	 * (non-Javadoc)
	 * @see org.dussan.vaadin.dcharts.base.renderers.TickRenderer#setFormatString(java.lang.String)
	 */
	@Override
	public TickRenderer setFormatString(String newFormatString) {
		return super.setFormatString(newFormatString);
	}

	/*
	 * (non-Javadoc)
	 * @see org.dussan.vaadin.dcharts.base.renderers.TickRenderer#setPrefix(java.lang.String)
	 */
	@Override
	public TickRenderer setPrefix(String newPrefix) {
		return super.setPrefix(newPrefix);
	}

	/*
	 * (non-Javadoc)
	 * @see org.dussan.vaadin.dcharts.base.renderers.TickRenderer#setShowGridline(boolean)
	 */
	@Override
	public TickRenderer setShowGridline(boolean newShowGridLine) {
		return super.setShowGridline(newShowGridLine);
	}

	/**
	 * Getter method for the formatter.
	 * 
	 * @return The formatter
	 */
	public TickFormatters getTickFormatters() {
		return super.getFormatter();
	}

	/*
	 * (non-Javadoc)
	 * @see org.dussan.vaadin.dcharts.base.renderers.TickRenderer#setMarkSize(int)
	 */
	@Override
	public TickRenderer setMarkSize(int newMarkSize) {
		return super.setMarkSize(newMarkSize);
	}

	/*
	 * (non-Javadoc)
	 * @see org.dussan.vaadin.dcharts.base.renderers.TickRenderer#setIsMinorTick(boolean)
	 */
	@Override
	public TickRenderer setIsMinorTick(boolean newIsMinorTick) {
		return super.setIsMinorTick(newIsMinorTick);
	}

	/*
	 * (non-Javadoc)
	 * @see org.dussan.vaadin.dcharts.base.renderers.TickRenderer#setShow(boolean)
	 */
	@Override
	public TickRenderer setShow(boolean newShow) {
		return super.setShow(newShow);
	}

	/*
	 * (non-Javadoc)
	 * @see org.dussan.vaadin.dcharts.base.renderers.TickRenderer#setShowLabel(boolean)
	 */
	@Override
	public TickRenderer setShowLabel(boolean newShowLabel) {
		return super.setShowLabel(newShowLabel);
	}

	/*
	 * (non-Javadoc)
	 * @see org.dussan.vaadin.dcharts.base.renderers.TickRenderer#setShowMark(boolean)
	 */
	@Override
	public TickRenderer setShowMark(boolean newShowMark) {
		return super.setShowMark(newShowMark);
	}

	/**
	 * Setter method for the tick formatters
	 * 
	 * @param tickFormatters The new tick formatters
	 * @return The new tick renderer
	 */
	public TickRenderer setTickFormatters(TickFormatters tickFormatters) {
		return super.setFormatter(tickFormatters);
	}

	/*
	 * (non-Javadoc)
	 * @see org.dussan.vaadin.dcharts.base.renderers.TickRenderer#setTextColor(java.lang.String)
	 */
	@Override
	public TickRenderer setTextColor(String newTextColor) {
		return super.setTextColor(newTextColor);
	}

}
