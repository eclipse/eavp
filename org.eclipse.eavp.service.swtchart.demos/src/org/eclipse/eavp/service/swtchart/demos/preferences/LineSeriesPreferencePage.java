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

import java.util.Locale;

import org.eclipse.eavp.service.swtchart.demos.Activator;
import org.eclipse.jface.preference.BooleanFieldEditor;
import org.eclipse.jface.preference.ColorFieldEditor;
import org.eclipse.jface.preference.ComboFieldEditor;
import org.eclipse.jface.preference.FieldEditorPreferencePage;
import org.eclipse.jface.preference.StringFieldEditor;
import org.eclipse.swt.SWT;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;
import org.swtchart.IAxis.Position;
import org.swtchart.LineStyle;

public class LineSeriesPreferencePage extends FieldEditorPreferencePage implements IWorkbenchPreferencePage {

	public LineSeriesPreferencePage() {
		super(GRID);
		setPreferenceStore(Activator.getDefault().getPreferenceStore());
		setDescription("Set the preferences of the line series settings part.");
	}

	public void createFieldEditors() {

		String[][] legenPositions = new String[][]{//
				{"Left", Integer.toString(SWT.LEFT)}, //
				{"Right", Integer.toString(SWT.RIGHT)}, //
				{"Top", Integer.toString(SWT.TOP)}, //
				{"Bottom", Integer.toString(SWT.BOTTOM)}//
		};
		//
		String[][] orientations = new String[][]{//
				{"Horizontal", Integer.toString(SWT.HORIZONTAL)}, //
				{"Vertical", Integer.toString(SWT.VERTICAL)}//
		};
		//
		String[][] axisPositions = new String[][]{//
				{"Primary", Position.Primary.toString()}, //
				{"Secondary", Position.Secondary.toString()}//
		};
		//
		String[][] locales = new String[][]{//
				{"English", Locale.ENGLISH.getLanguage()}, //
				{"US", Locale.US.getLanguage()}, //
				{"German", Locale.GERMAN.getLanguage()}//
		};
		//
		String[][] gridLineStyles = new String[][]{//
				{"None", LineStyle.NONE.toString()}, //
				{"Solid", LineStyle.SOLID.toString()}, //
				{"Dash", LineStyle.DASH.toString()}, //
				{"Dot", LineStyle.DOT.toString()}, //
				{"Dash Dot", LineStyle.DASHDOT.toString()}, //
				{"Dash Dot Dot", LineStyle.DASHDOTDOT.toString()}//
		};
		//
		addField(new SpacerFieldEditor(getFieldEditorParent()));
		addField(new LabelFieldEditor("Chart Settings", getFieldEditorParent()));
		addField(new SpacerFieldEditor(getFieldEditorParent()));
		//
		addField(new BooleanFieldEditor(LineSeriesPreferenceConstants.P_ENABLE_RANGE_UI, "Enable Range UI", getFieldEditorParent()));
		addField(new BooleanFieldEditor(LineSeriesPreferenceConstants.P_VERTICAL_SLIDER_VISIBLE, "Vertical Slider Visible (see Bug #511257)", getFieldEditorParent()));
		addField(new BooleanFieldEditor(LineSeriesPreferenceConstants.P_HORIZONTAL_SLIDER_VISIBLE, "Horizontal Slider Visible", getFieldEditorParent()));
		addField(new StringFieldEditor(LineSeriesPreferenceConstants.P_TITLE, "Title:", getFieldEditorParent()));
		addField(new BooleanFieldEditor(LineSeriesPreferenceConstants.P_TITLE_VISIBLE, "Title Visible", getFieldEditorParent()));
		addField(new ColorFieldEditor(LineSeriesPreferenceConstants.P_TITLE_COLOR, "Title Color:", getFieldEditorParent()));
		addField(new ComboFieldEditor(LineSeriesPreferenceConstants.P_LEGEND_POSITION, "Legend Position:", legenPositions, getFieldEditorParent()));
		addField(new BooleanFieldEditor(LineSeriesPreferenceConstants.P_LEGEND_VISIBLE, "Legend Visible", getFieldEditorParent()));
		addField(new ComboFieldEditor(LineSeriesPreferenceConstants.P_ORIENTATION, "Orientation:", orientations, getFieldEditorParent()));
		addField(new ColorFieldEditor(LineSeriesPreferenceConstants.P_BACKGROUND, "Background:", getFieldEditorParent()));
		addField(new ColorFieldEditor(LineSeriesPreferenceConstants.P_BACKGROUND_IN_PLOT_AREA, "Background In Plot Area:", getFieldEditorParent()));
		addField(new BooleanFieldEditor(LineSeriesPreferenceConstants.P_ENABLE_COMPRESS, "Enable Compress", getFieldEditorParent()));
		addField(new BooleanFieldEditor(LineSeriesPreferenceConstants.P_USE_ZERO_X, "Use Zero X", getFieldEditorParent()));
		addField(new BooleanFieldEditor(LineSeriesPreferenceConstants.P_USE_ZERO_Y, "Use Zero Y", getFieldEditorParent()));
		addField(new BooleanFieldEditor(LineSeriesPreferenceConstants.P_USE_RANGE_RESTRICTION, "Use Range Restriction", getFieldEditorParent()));
		addField(new DoubleFieldEditor(LineSeriesPreferenceConstants.P_FACTOR_EXTEND_MIN_X, "Factor Extend Min X:", getFieldEditorParent()));
		addField(new DoubleFieldEditor(LineSeriesPreferenceConstants.P_FACTOR_EXTEND_MAX_X, "Factor Extend Max X:", getFieldEditorParent()));
		addField(new DoubleFieldEditor(LineSeriesPreferenceConstants.P_FACTOR_EXTEND_MIN_Y, "Factor Extend Min Y:", getFieldEditorParent()));
		addField(new DoubleFieldEditor(LineSeriesPreferenceConstants.P_FACTOR_EXTEND_MAX_X, "Factor Extend Max X:", getFieldEditorParent()));
		addField(new BooleanFieldEditor(LineSeriesPreferenceConstants.P_SHOW_POSITION_MARKER, "Show Position Marker", getFieldEditorParent()));
		addField(new BooleanFieldEditor(LineSeriesPreferenceConstants.P_SHOW_CENTER_MARKER, "Show Center Marker", getFieldEditorParent()));
		addField(new BooleanFieldEditor(LineSeriesPreferenceConstants.P_SHOW_POSITION_LEGEND, "Show Position Legend", getFieldEditorParent()));
		addField(new BooleanFieldEditor(LineSeriesPreferenceConstants.P_CREATE_MENU, "Create Menu", getFieldEditorParent()));
		//
		addField(new SpacerFieldEditor(getFieldEditorParent()));
		addField(new LabelFieldEditor("Line Settings", getFieldEditorParent()));
		addField(new SpacerFieldEditor(getFieldEditorParent()));
		//
		addField(new StringFieldEditor(LineSeriesPreferenceConstants.P_PRIMARY_X_AXIS_TITLE, "Primary X-Axis Title:", getFieldEditorParent()));
		addField(new StringFieldEditor(LineSeriesPreferenceConstants.P_PRIMARY_X_AXIS_DESCRIPTION, "Primary X-Axis Description:", getFieldEditorParent()));
		addField(new StringFieldEditor(LineSeriesPreferenceConstants.P_PRIMARY_X_AXIS_DECIMAL_FORMAT_PATTERN, "Primary X-Axis Format Pattern:", getFieldEditorParent()));
		addField(new ComboFieldEditor(LineSeriesPreferenceConstants.P_PRIMARY_X_AXIS_DECIMAL_FORMAT_LOCALE, "Primary X-Axis Format Locale:", locales, getFieldEditorParent()));
		addField(new ColorFieldEditor(LineSeriesPreferenceConstants.P_PRIMARY_X_AXIS_COLOR, "Primary X-Axis Color:", getFieldEditorParent()));
		addField(new ComboFieldEditor(LineSeriesPreferenceConstants.P_PRIMARY_X_AXIS_POSITION, "Primary X-Axis Position:", axisPositions, getFieldEditorParent()));
		addField(new BooleanFieldEditor(LineSeriesPreferenceConstants.P_PRIMARY_X_AXIS_VISIBLE, "Primary X-Axis Visible", getFieldEditorParent()));
		addField(new ComboFieldEditor(LineSeriesPreferenceConstants.P_PRIMARY_X_AXIS_GRID_LINE_STYLE, "Primary X-Axis Grid Line Style:", gridLineStyles, getFieldEditorParent()));
		//
		addField(new StringFieldEditor(LineSeriesPreferenceConstants.P_PRIMARY_Y_AXIS_TITLE, "Primary Y-Axis Title:", getFieldEditorParent()));
		addField(new StringFieldEditor(LineSeriesPreferenceConstants.P_PRIMARY_Y_AXIS_DESCRIPTION, "Primary Y-Axis Description:", getFieldEditorParent()));
		addField(new StringFieldEditor(LineSeriesPreferenceConstants.P_PRIMARY_Y_AXIS_DECIMAL_FORMAT_PATTERN, "Primary Y-Axis Format Pattern:", getFieldEditorParent()));
		addField(new ComboFieldEditor(LineSeriesPreferenceConstants.P_PRIMARY_Y_AXIS_DECIMAL_FORMAT_LOCALE, "Primary Y-Axis Format Locale:", locales, getFieldEditorParent()));
		addField(new ColorFieldEditor(LineSeriesPreferenceConstants.P_PRIMARY_Y_AXIS_COLOR, "Primary Y-Axis Color:", getFieldEditorParent()));
		addField(new ComboFieldEditor(LineSeriesPreferenceConstants.P_PRIMARY_Y_AXIS_POSITION, "Primary Y-Axis Position:", axisPositions, getFieldEditorParent()));
		addField(new BooleanFieldEditor(LineSeriesPreferenceConstants.P_PRIMARY_Y_AXIS_VISIBLE, "Primary Y-Axis Visible", getFieldEditorParent()));
		addField(new ComboFieldEditor(LineSeriesPreferenceConstants.P_PRIMARY_Y_AXIS_GRID_LINE_STYLE, "Primary Y-Axis Grid Line Style:", gridLineStyles, getFieldEditorParent()));
		//
		addField(new StringFieldEditor(LineSeriesPreferenceConstants.P_SECONDARY_X_AXIS_TITLE, "Secondary X-Axis Title:", getFieldEditorParent()));
		addField(new StringFieldEditor(LineSeriesPreferenceConstants.P_SECONDARY_X_AXIS_DESCRIPTION, "Secondary X-Axis Description:", getFieldEditorParent()));
		addField(new StringFieldEditor(LineSeriesPreferenceConstants.P_SECONDARY_X_AXIS_DECIMAL_FORMAT_PATTERN, "Secondary X-Axis Format Pattern:", getFieldEditorParent()));
		addField(new ComboFieldEditor(LineSeriesPreferenceConstants.P_SECONDARY_X_AXIS_DECIMAL_FORMAT_LOCALE, "Secondary X-Axis Format Locale:", locales, getFieldEditorParent()));
		addField(new ColorFieldEditor(LineSeriesPreferenceConstants.P_SECONDARY_X_AXIS_COLOR, "Secondary X-Axis Color:", getFieldEditorParent()));
		addField(new ComboFieldEditor(LineSeriesPreferenceConstants.P_SECONDARY_X_AXIS_POSITION, "Secondary X-Axis Position:", axisPositions, getFieldEditorParent()));
		addField(new BooleanFieldEditor(LineSeriesPreferenceConstants.P_SECONDARY_X_AXIS_VISIBLE, "Secondary X-Axis Visible", getFieldEditorParent()));
		addField(new ComboFieldEditor(LineSeriesPreferenceConstants.P_SECONDARY_X_AXIS_GRID_LINE_STYLE, "Secondary X-Axis Grid Line Style:", gridLineStyles, getFieldEditorParent()));
		//
		addField(new StringFieldEditor(LineSeriesPreferenceConstants.P_SECONDARY_Y_AXIS_TITLE, "Secondary Y-Axis Title:", getFieldEditorParent()));
		addField(new StringFieldEditor(LineSeriesPreferenceConstants.P_SECONDARY_Y_AXIS_DESCRIPTION, "Secondary Y-Axis Description:", getFieldEditorParent()));
		addField(new StringFieldEditor(LineSeriesPreferenceConstants.P_SECONDARY_Y_AXIS_DECIMAL_FORMAT_PATTERN, "Secondary Y-Axis Format Pattern:", getFieldEditorParent()));
		addField(new ComboFieldEditor(LineSeriesPreferenceConstants.P_SECONDARY_Y_AXIS_DECIMAL_FORMAT_LOCALE, "Secondary Y-Axis Format Locale:", locales, getFieldEditorParent()));
		addField(new ColorFieldEditor(LineSeriesPreferenceConstants.P_SECONDARY_Y_AXIS_COLOR, "Secondary Y-Axis Color:", getFieldEditorParent()));
		addField(new ComboFieldEditor(LineSeriesPreferenceConstants.P_SECONDARY_Y_AXIS_POSITION, "Secondary Y-Axis Position:", axisPositions, getFieldEditorParent()));
		addField(new BooleanFieldEditor(LineSeriesPreferenceConstants.P_SECONDARY_Y_AXIS_VISIBLE, "Secondary Y-Axis Visible", getFieldEditorParent()));
		addField(new ComboFieldEditor(LineSeriesPreferenceConstants.P_SECONDARY_Y_AXIS_GRID_LINE_STYLE, "Secondary Y-Axis Grid Line Style:", gridLineStyles, getFieldEditorParent()));
	}

	public void init(IWorkbench workbench) {

	}
}