/*******************************************************************************
 * Copyright (c) 2017 Lablicate GmbH.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 * Philip Wenig - initial API and implementation
 *******************************************************************************/
package org.eclipse.eavp.service.swtchart.demos.preferences;

import org.eclipse.core.runtime.preferences.AbstractPreferenceInitializer;
import org.eclipse.eavp.service.swtchart.demos.Activator;
import org.eclipse.jface.preference.IPreferenceStore;

public class ScatterSeriesPreferenceInitializer extends AbstractPreferenceInitializer {

	public void initializeDefaultPreferences() {

		IPreferenceStore preferenceStore = Activator.getDefault().getPreferenceStore();
		/*
		 * Scatter Series
		 */
		preferenceStore.setDefault(ScatterSeriesPreferenceConstants.P_ENABLE_RANGE_SELECTOR, ScatterSeriesPreferenceConstants.DEF_ENABLE_RANGE_SELECTOR);
		preferenceStore.setDefault(ScatterSeriesPreferenceConstants.P_COLOR_HINT_RANGE_SELECTOR, ScatterSeriesPreferenceConstants.DEF_COLOR_HINT_RANGE_SELECTOR);
		preferenceStore.setDefault(ScatterSeriesPreferenceConstants.P_RANGE_SELECTOR_DEFAULT_AXIS_X, ScatterSeriesPreferenceConstants.DEF_RANGE_SELECTOR_DEFAULT_AXIS_X);
		preferenceStore.setDefault(ScatterSeriesPreferenceConstants.P_RANGE_SELECTOR_DEFAULT_AXIS_Y, ScatterSeriesPreferenceConstants.DEF_RANGE_SELECTOR_DEFAULT_AXIS_Y);
		preferenceStore.setDefault(ScatterSeriesPreferenceConstants.P_VERTICAL_SLIDER_VISIBLE, ScatterSeriesPreferenceConstants.DEF_VERTICAL_SLIDER_VISIBLE);
		preferenceStore.setDefault(ScatterSeriesPreferenceConstants.P_HORIZONTAL_SLIDER_VISIBLE, ScatterSeriesPreferenceConstants.DEF_HORIZONTALSLIDER_VISIBLE);
		preferenceStore.setDefault(ScatterSeriesPreferenceConstants.P_TITLE, ScatterSeriesPreferenceConstants.DEF_TITLE);
		preferenceStore.setDefault(ScatterSeriesPreferenceConstants.P_TITLE_VISIBLE, ScatterSeriesPreferenceConstants.DEF_TITLE_VISIBLE);
		preferenceStore.setDefault(ScatterSeriesPreferenceConstants.P_TITLE_COLOR, ScatterSeriesPreferenceConstants.DEF_TITLE_COLOR);
		preferenceStore.setDefault(ScatterSeriesPreferenceConstants.P_LEGEND_POSITION, ScatterSeriesPreferenceConstants.DEF_LEGEND_POSITION);
		preferenceStore.setDefault(ScatterSeriesPreferenceConstants.P_LEGEND_VISIBLE, ScatterSeriesPreferenceConstants.DEF_LEGEND_VISIBLE);
		preferenceStore.setDefault(ScatterSeriesPreferenceConstants.P_ORIENTATION, ScatterSeriesPreferenceConstants.DEF_ORIENTATION);
		preferenceStore.setDefault(ScatterSeriesPreferenceConstants.P_BACKGROUND, ScatterSeriesPreferenceConstants.DEF_BACKGROUND);
		preferenceStore.setDefault(ScatterSeriesPreferenceConstants.P_BACKGROUND_CHART, ScatterSeriesPreferenceConstants.DEF_BACKGROUND_CHART);
		preferenceStore.setDefault(ScatterSeriesPreferenceConstants.P_BACKGROUND_PLOT_AREA, ScatterSeriesPreferenceConstants.DEF_BACKGROUND_PLOT_AREA);
		preferenceStore.setDefault(ScatterSeriesPreferenceConstants.P_ENABLE_COMPRESS, ScatterSeriesPreferenceConstants.DEF_ENABLE_COMPRESS);
		preferenceStore.setDefault(ScatterSeriesPreferenceConstants.P_ZERO_X, ScatterSeriesPreferenceConstants.DEF_ZERO_X);
		preferenceStore.setDefault(ScatterSeriesPreferenceConstants.P_ZERO_Y, ScatterSeriesPreferenceConstants.DEF_ZERO_Y);
		preferenceStore.setDefault(ScatterSeriesPreferenceConstants.P_RESTRICT_ZOOM, ScatterSeriesPreferenceConstants.DEF_RESTRICT_ZOOM);
		preferenceStore.setDefault(ScatterSeriesPreferenceConstants.P_X_ZOOM_ONLY, ScatterSeriesPreferenceConstants.DEF_X_ZOOM_ONLY);
		preferenceStore.setDefault(ScatterSeriesPreferenceConstants.P_Y_ZOOM_ONLY, ScatterSeriesPreferenceConstants.DEF_Y_ZOOM_ONLY);
		preferenceStore.setDefault(ScatterSeriesPreferenceConstants.P_FORCE_ZERO_MIN_Y, ScatterSeriesPreferenceConstants.DEF_FORCE_ZERO_MIN_Y);
		preferenceStore.setDefault(ScatterSeriesPreferenceConstants.P_FACTOR_EXTEND_MIN_X, ScatterSeriesPreferenceConstants.DEF_FACTOR_EXTEND_MIN_X);
		preferenceStore.setDefault(ScatterSeriesPreferenceConstants.P_FACTOR_EXTEND_MAX_X, ScatterSeriesPreferenceConstants.DEF_FACTOR_EXTEND_MAX_X);
		preferenceStore.setDefault(ScatterSeriesPreferenceConstants.P_FACTOR_EXTEND_MIN_Y, ScatterSeriesPreferenceConstants.DEF_FACTOR_EXTEND_MIN_Y);
		preferenceStore.setDefault(ScatterSeriesPreferenceConstants.P_FACTOR_EXTEND_MAX_Y, ScatterSeriesPreferenceConstants.DEF_FACTOR_EXTEND_MAX_Y);
		preferenceStore.setDefault(ScatterSeriesPreferenceConstants.P_SHOW_POSITION_MARKER, ScatterSeriesPreferenceConstants.DEF_SHOW_POSITION_MARKER);
		preferenceStore.setDefault(ScatterSeriesPreferenceConstants.P_COLOR_POSITION_MARKER, ScatterSeriesPreferenceConstants.DEF_COLOR_POSITION_MARKER);
		preferenceStore.setDefault(ScatterSeriesPreferenceConstants.P_SHOW_PLOT_CENTER_MARKER, ScatterSeriesPreferenceConstants.DEF_SHOW_PLOT_CENTER_MARKER);
		preferenceStore.setDefault(ScatterSeriesPreferenceConstants.P_COLOR_PLOT_CENTER_MARKER, ScatterSeriesPreferenceConstants.DEF_COLOR_PLOT_CENTER_MARKER);
		preferenceStore.setDefault(ScatterSeriesPreferenceConstants.P_SHOW_LEGEND_MARKER, ScatterSeriesPreferenceConstants.DEF_SHOW_LEGEND_MARKER);
		preferenceStore.setDefault(ScatterSeriesPreferenceConstants.P_COLOR_LEGEND_MARKER, ScatterSeriesPreferenceConstants.DEF_COLOR_LEGEND_MARKER);
		preferenceStore.setDefault(ScatterSeriesPreferenceConstants.P_SHOW_AXIS_ZERO_MARKER, ScatterSeriesPreferenceConstants.DEF_SHOW_AXIS_ZERO_MARKER);
		preferenceStore.setDefault(ScatterSeriesPreferenceConstants.P_COLOR_AXIS_ZERO_MARKER, ScatterSeriesPreferenceConstants.DEF_COLOR_AXIS_ZERO_MARKER);
		preferenceStore.setDefault(ScatterSeriesPreferenceConstants.P_SHOW_SERIES_LABEL_MARKER, ScatterSeriesPreferenceConstants.DEF_SHOW_SERIES_LABEL_MARKER);
		preferenceStore.setDefault(ScatterSeriesPreferenceConstants.P_COLOR_SERIES_LABEL_MARKER, ScatterSeriesPreferenceConstants.DEF_COLOR_SERIES_LABEL_MARKER);
		preferenceStore.setDefault(ScatterSeriesPreferenceConstants.P_CREATE_MENU, ScatterSeriesPreferenceConstants.DEF_CREATE_MENU);
		preferenceStore.setDefault(ScatterSeriesPreferenceConstants.P_PRIMARY_X_AXIS_TITLE, ScatterSeriesPreferenceConstants.DEF_PRIMARY_X_AXIS_TITLE);
		preferenceStore.setDefault(ScatterSeriesPreferenceConstants.P_PRIMARY_X_AXIS_DESCRIPTION, ScatterSeriesPreferenceConstants.DEF_PRIMARY_X_AXIS_DESCRIPTION);
		preferenceStore.setDefault(ScatterSeriesPreferenceConstants.P_PRIMARY_X_AXIS_DECIMAL_FORMAT_PATTERN, ScatterSeriesPreferenceConstants.DEF_PRIMARY_X_AXIS_DECIMAL_FORMAT_PATTERN);
		preferenceStore.setDefault(ScatterSeriesPreferenceConstants.P_PRIMARY_X_AXIS_DECIMAL_FORMAT_LOCALE, ScatterSeriesPreferenceConstants.DEF_PRIMARY_X_AXIS_DECIMAL_FORMAT_LOCALE);
		preferenceStore.setDefault(ScatterSeriesPreferenceConstants.P_PRIMARY_X_AXIS_COLOR, ScatterSeriesPreferenceConstants.DEF_PRIMARY_X_AXIS_COLOR);
		preferenceStore.setDefault(ScatterSeriesPreferenceConstants.P_PRIMARY_X_AXIS_POSITION, ScatterSeriesPreferenceConstants.DEF_PRIMARY_X_AXIS_POSITION);
		preferenceStore.setDefault(ScatterSeriesPreferenceConstants.P_PRIMARY_X_AXIS_VISIBLE, ScatterSeriesPreferenceConstants.DEF_PRIMARY_X_AXIS_VISIBLE);
		preferenceStore.setDefault(ScatterSeriesPreferenceConstants.P_PRIMARY_X_AXIS_GRID_LINE_STYLE, ScatterSeriesPreferenceConstants.DEF_PRIMARY_X_AXIS_GRID_LINE_STYLE);
		preferenceStore.setDefault(ScatterSeriesPreferenceConstants.P_PRIMARY_X_AXIS_ENABLE_LOG_SCALE, ScatterSeriesPreferenceConstants.DEF_PRIMARY_X_AXIS_ENABLE_LOG_SCALE);
		preferenceStore.setDefault(ScatterSeriesPreferenceConstants.P_PRIMARY_X_AXIS_EXTRA_SPACE_TITLE, ScatterSeriesPreferenceConstants.DEF_PRIMARY_X_AXIS_EXTRA_SPACE_TITLE);
		preferenceStore.setDefault(ScatterSeriesPreferenceConstants.P_PRIMARY_Y_AXIS_TITLE, ScatterSeriesPreferenceConstants.DEF_PRIMARY_Y_AXIS_TITLE);
		preferenceStore.setDefault(ScatterSeriesPreferenceConstants.P_PRIMARY_Y_AXIS_DESCRIPTION, ScatterSeriesPreferenceConstants.DEF_PRIMARY_Y_AXIS_DESCRIPTION);
		preferenceStore.setDefault(ScatterSeriesPreferenceConstants.P_PRIMARY_Y_AXIS_DECIMAL_FORMAT_PATTERN, ScatterSeriesPreferenceConstants.DEF_PRIMARY_Y_AXIS_DECIMAL_FORMAT_PATTERN);
		preferenceStore.setDefault(ScatterSeriesPreferenceConstants.P_PRIMARY_Y_AXIS_DECIMAL_FORMAT_LOCALE, ScatterSeriesPreferenceConstants.DEF_PRIMARY_Y_AXIS_DECIMAL_FORMAT_LOCALE);
		preferenceStore.setDefault(ScatterSeriesPreferenceConstants.P_PRIMARY_Y_AXIS_COLOR, ScatterSeriesPreferenceConstants.DEF_PRIMARY_Y_AXIS_COLOR);
		preferenceStore.setDefault(ScatterSeriesPreferenceConstants.P_PRIMARY_Y_AXIS_POSITION, ScatterSeriesPreferenceConstants.DEF_PRIMARY_Y_AXIS_POSITION);
		preferenceStore.setDefault(ScatterSeriesPreferenceConstants.P_PRIMARY_Y_AXIS_VISIBLE, ScatterSeriesPreferenceConstants.DEF_PRIMARY_Y_AXIS_VISIBLE);
		preferenceStore.setDefault(ScatterSeriesPreferenceConstants.P_PRIMARY_Y_AXIS_GRID_LINE_STYLE, ScatterSeriesPreferenceConstants.DEF_PRIMARY_Y_AXIS_GRID_LINE_STYLE);
		preferenceStore.setDefault(ScatterSeriesPreferenceConstants.P_PRIMARY_Y_AXIS_ENABLE_LOG_SCALE, ScatterSeriesPreferenceConstants.DEF_PRIMARY_Y_AXIS_ENABLE_LOG_SCALE);
		preferenceStore.setDefault(ScatterSeriesPreferenceConstants.P_PRIMARY_Y_AXIS_EXTRA_SPACE_TITLE, ScatterSeriesPreferenceConstants.DEF_PRIMARY_Y_AXIS_EXTRA_SPACE_TITLE);
		preferenceStore.setDefault(ScatterSeriesPreferenceConstants.P_SYMBOL_SIZE_SERIES, ScatterSeriesPreferenceConstants.DEF_SYMBOL_SIZE_SERIES);
		//
		preferenceStore.setDefault(ScatterSeriesPreferenceConstants.P_SYMBOL_COLOR_SERIES_LEFT_TOP, ScatterSeriesPreferenceConstants.DEF_SYMBOL_COLOR_SERIES_LEFT_TOP);
		preferenceStore.setDefault(ScatterSeriesPreferenceConstants.P_SYMBOL_TYPE_SERIES_LEFT_TOP, ScatterSeriesPreferenceConstants.DEF_SYMBOL_TYPE_SERIES_LEFT_TOP);
		preferenceStore.setDefault(ScatterSeriesPreferenceConstants.P_VISIBLE_SERIES_LEFT_TOP, ScatterSeriesPreferenceConstants.DEF_VISIBLE_SERIES_LEFT_TOP);
		//
		preferenceStore.setDefault(ScatterSeriesPreferenceConstants.P_SYMBOL_COLOR_SERIES_RIGHT_TOP, ScatterSeriesPreferenceConstants.DEF_SYMBOL_COLOR_SERIES_RIGHT_TOP);
		preferenceStore.setDefault(ScatterSeriesPreferenceConstants.P_SYMBOL_TYPE_SERIES_RIGHT_TOP, ScatterSeriesPreferenceConstants.DEF_SYMBOL_TYPE_SERIES_RIGHT_TOP);
		preferenceStore.setDefault(ScatterSeriesPreferenceConstants.P_VISIBLE_SERIES_RIGHT_TOP, ScatterSeriesPreferenceConstants.DEF_VISIBLE_SERIES_RIGHT_TOP);
		//
		preferenceStore.setDefault(ScatterSeriesPreferenceConstants.P_SYMBOL_COLOR_SERIES_LEFT_BOTTOM, ScatterSeriesPreferenceConstants.DEF_SYMBOL_COLOR_SERIES_LEFT_BOTTOM);
		preferenceStore.setDefault(ScatterSeriesPreferenceConstants.P_SYMBOL_TYPE_SERIES_LEFT_BOTTOM, ScatterSeriesPreferenceConstants.DEF_SYMBOL_TYPE_SERIES_LEFT_BOTTOM);
		preferenceStore.setDefault(ScatterSeriesPreferenceConstants.P_VISIBLE_SERIES_LEFT_BOTTOM, ScatterSeriesPreferenceConstants.DEF_VISIBLE_SERIES_LEFT_BOTTOM);
		//
		preferenceStore.setDefault(ScatterSeriesPreferenceConstants.P_SYMBOL_COLOR_SERIES_RIGHT_BOTTOM, ScatterSeriesPreferenceConstants.DEF_SYMBOL_COLOR_SERIES_RIGHT_BOTTOM);
		preferenceStore.setDefault(ScatterSeriesPreferenceConstants.P_SYMBOL_TYPE_SERIES_RIGHT_BOTTOM, ScatterSeriesPreferenceConstants.DEF_SYMBOL_TYPE_SERIES_RIGHT_BOTTOM);
		preferenceStore.setDefault(ScatterSeriesPreferenceConstants.P_VISIBLE_SERIES_RIGHT_BOTTOM, ScatterSeriesPreferenceConstants.DEF_VISIBLE_SERIES_RIGHT_BOTTOM);
	}
}
