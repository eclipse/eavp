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

public class LineSeriesPreferenceInitializer extends AbstractPreferenceInitializer {

	public void initializeDefaultPreferences() {

		IPreferenceStore store = Activator.getDefault().getPreferenceStore();
		//
		store.setDefault(LineSeriesPreferenceConstants.P_ENABLE_RANGE_UI, LineSeriesPreferenceConstants.DEF_ENABLE_RANGE_UI);
		//
		store.setDefault(LineSeriesPreferenceConstants.P_VERTICAL_SLIDER_VISIBLE, LineSeriesPreferenceConstants.DEF_VERTICAL_SLIDER_VISIBLE);
		store.setDefault(LineSeriesPreferenceConstants.P_HORIZONTAL_SLIDER_VISIBLE, LineSeriesPreferenceConstants.DEF_HORIZONTALSLIDER_VISIBLE);
		//
		store.setDefault(LineSeriesPreferenceConstants.P_TITLE, LineSeriesPreferenceConstants.DEF_TITLE);
		store.setDefault(LineSeriesPreferenceConstants.P_TITLE_VISIBLE, LineSeriesPreferenceConstants.DEF_TITLE_VISIBLE);
		store.setDefault(LineSeriesPreferenceConstants.P_TITLE_COLOR, LineSeriesPreferenceConstants.DEF_TITLE_COLOR);
		//
		store.setDefault(LineSeriesPreferenceConstants.P_LEGEND_POSITION, LineSeriesPreferenceConstants.DEF_LEGEND_POSITION);
		store.setDefault(LineSeriesPreferenceConstants.P_LEGEND_VISIBLE, LineSeriesPreferenceConstants.DEF_LEGEND_VISIBLE);
		//
		store.setDefault(LineSeriesPreferenceConstants.P_ORIENTATION, LineSeriesPreferenceConstants.DEF_ORIENTATION);
		store.setDefault(LineSeriesPreferenceConstants.P_BACKGROUND, LineSeriesPreferenceConstants.DEF_BACKGROUND);
		store.setDefault(LineSeriesPreferenceConstants.P_BACKGROUND_IN_PLOT_AREA, LineSeriesPreferenceConstants.DEF_BACKGROUND_IN_PLOT_AREA);
		//
		store.setDefault(LineSeriesPreferenceConstants.P_ENABLE_COMPRESS, LineSeriesPreferenceConstants.DEF_ENABLE_COMPRESS);
		store.setDefault(LineSeriesPreferenceConstants.P_USE_ZERO_X, LineSeriesPreferenceConstants.DEF_USE_ZERO_X);
		store.setDefault(LineSeriesPreferenceConstants.P_USE_ZERO_Y, LineSeriesPreferenceConstants.DEF_USE_ZERO_Y);
		store.setDefault(LineSeriesPreferenceConstants.P_USE_RANGE_RESTRICTION, LineSeriesPreferenceConstants.DEF_USE_RANGE_RESTRICTION);
		store.setDefault(LineSeriesPreferenceConstants.P_FACTOR_EXTEND_MIN_X, LineSeriesPreferenceConstants.DEF_FACTOR_EXTEND_MIN_X);
		store.setDefault(LineSeriesPreferenceConstants.P_FACTOR_EXTEND_MAX_X, LineSeriesPreferenceConstants.DEF_FACTOR_EXTEND_MAX_X);
		store.setDefault(LineSeriesPreferenceConstants.P_FACTOR_EXTEND_MIN_Y, LineSeriesPreferenceConstants.DEF_FACTOR_EXTEND_MIN_Y);
		store.setDefault(LineSeriesPreferenceConstants.P_FACTOR_EXTEND_MAX_Y, LineSeriesPreferenceConstants.DEF_FACTOR_EXTEND_MAX_Y);
		//
		store.setDefault(LineSeriesPreferenceConstants.P_SHOW_POSITION_MARKER, LineSeriesPreferenceConstants.DEF_SHOW_POSITION_MARKER);
		store.setDefault(LineSeriesPreferenceConstants.P_SHOW_CENTER_MARKER, LineSeriesPreferenceConstants.DEF_SHOW_CENTER_MARKER);
		store.setDefault(LineSeriesPreferenceConstants.P_SHOW_POSITION_LEGEND, LineSeriesPreferenceConstants.DEF_SHOW_POSITION_LEGEND);
		//
		store.setDefault(LineSeriesPreferenceConstants.P_CREATE_MENU, LineSeriesPreferenceConstants.DEF_CREATE_MENU);
	}
}
