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

import org.eclipse.eavp.service.swtchart.demos.Activator;
import org.eclipse.jface.preference.BooleanFieldEditor;
import org.eclipse.jface.preference.ColorFieldEditor;
import org.eclipse.jface.preference.ComboFieldEditor;
import org.eclipse.jface.preference.FieldEditorPreferencePage;
import org.eclipse.jface.preference.StringFieldEditor;
import org.eclipse.swt.SWT;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;

public class LineSeriesPreferencePage extends FieldEditorPreferencePage implements IWorkbenchPreferencePage {

	public LineSeriesPreferencePage() {
		super(GRID);
		setPreferenceStore(Activator.getDefault().getPreferenceStore());
		setDescription("Set the preferences of the line series settings part.");
	}

	public void createFieldEditors() {

		addField(new BooleanFieldEditor(LineSeriesPreferenceConstants.P_ENABLE_RANGE_UI, "Enable Range UI", getFieldEditorParent()));
		//
		addField(new BooleanFieldEditor(LineSeriesPreferenceConstants.P_VERTICAL_SLIDER_VISIBLE, "Vertical Slider Visible (see Bug #511257)", getFieldEditorParent()));
		addField(new BooleanFieldEditor(LineSeriesPreferenceConstants.P_HORIZONTAL_SLIDER_VISIBLE, "Horizontal Slider Visible", getFieldEditorParent()));
		//
		addField(new StringFieldEditor(LineSeriesPreferenceConstants.P_TITLE, "Title:", getFieldEditorParent()));
		addField(new BooleanFieldEditor(LineSeriesPreferenceConstants.P_TITLE_VISIBLE, "Title Visible", getFieldEditorParent()));
		addField(new ColorFieldEditor(LineSeriesPreferenceConstants.P_TITLE_COLOR, "Title Color:", getFieldEditorParent()));
		//
		String[][] positions = new String[][]{//
				{"Left", Integer.toString(SWT.LEFT)}, //
				{"Right", Integer.toString(SWT.RIGHT)}, //
				{"Top", Integer.toString(SWT.TOP)}, //
				{"Bottom", Integer.toString(SWT.BOTTOM)}//
		};
		addField(new ComboFieldEditor(LineSeriesPreferenceConstants.P_LEGEND_POSITION, "Legend Position:", positions, getFieldEditorParent()));
		addField(new BooleanFieldEditor(LineSeriesPreferenceConstants.P_LEGEND_VISIBLE, "Legend Visible", getFieldEditorParent()));
		//
		String[][] orientations = new String[][]{//
				{"Horizontal", Integer.toString(SWT.HORIZONTAL)}, //
				{"Vertical", Integer.toString(SWT.VERTICAL)}//
		};
		addField(new ComboFieldEditor(LineSeriesPreferenceConstants.P_ORIENTATION, "Orientation:", orientations, getFieldEditorParent()));
		addField(new ColorFieldEditor(LineSeriesPreferenceConstants.P_BACKGROUND, "Background:", getFieldEditorParent()));
		addField(new ColorFieldEditor(LineSeriesPreferenceConstants.P_BACKGROUND_IN_PLOT_AREA, "Background In Plot Area:", getFieldEditorParent()));
		//
		addField(new BooleanFieldEditor(LineSeriesPreferenceConstants.P_ENABLE_COMPRESS, "Enable Compress", getFieldEditorParent()));
		addField(new BooleanFieldEditor(LineSeriesPreferenceConstants.P_USE_ZERO_X, "Use Zero X", getFieldEditorParent()));
		addField(new BooleanFieldEditor(LineSeriesPreferenceConstants.P_USE_ZERO_Y, "Use Zero Y", getFieldEditorParent()));
		addField(new BooleanFieldEditor(LineSeriesPreferenceConstants.P_USE_RANGE_RESTRICTION, "Use Range Restriction", getFieldEditorParent()));
		// store.setDefault(LineSeriesPreferenceConstants.P_FACTOR_EXTEND_MIN_X, LineSeriesPreferenceConstants.DEF_FACTOR_EXTEND_MIN_X);
		// store.setDefault(LineSeriesPreferenceConstants.P_FACTOR_EXTEND_MAX_X, LineSeriesPreferenceConstants.DEF_FACTOR_EXTEND_MAX_X);
		// store.setDefault(LineSeriesPreferenceConstants.P_FACTOR_EXTEND_MIN_Y, LineSeriesPreferenceConstants.DEF_FACTOR_EXTEND_MIN_Y);
		// store.setDefault(LineSeriesPreferenceConstants.P_FACTOR_EXTEND_MAX_Y, LineSeriesPreferenceConstants.DEF_FACTOR_EXTEND_MAX_Y);
		//
		//
		addField(new BooleanFieldEditor(LineSeriesPreferenceConstants.P_SHOW_POSITION_MARKER, "Show Position Marker", getFieldEditorParent()));
		addField(new BooleanFieldEditor(LineSeriesPreferenceConstants.P_SHOW_CENTER_MARKER, "Show Center Marker", getFieldEditorParent()));
		addField(new BooleanFieldEditor(LineSeriesPreferenceConstants.P_SHOW_POSITION_LEGEND, "Show Position Legend", getFieldEditorParent()));
		//
		addField(new BooleanFieldEditor(LineSeriesPreferenceConstants.P_CREATE_MENU, "Create Menu", getFieldEditorParent()));
	}

	public void init(IWorkbench workbench) {

	}
}