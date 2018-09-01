/*******************************************************************************
 * Copyright (c) 2017, 2018 Lablicate GmbH.
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
import org.eclipse.eavp.service.swtchart.preferences.PreferenceSupport;
import org.eclipse.jface.preference.BooleanFieldEditor;
import org.eclipse.jface.preference.ColorFieldEditor;
import org.eclipse.jface.preference.ComboFieldEditor;
import org.eclipse.jface.preference.FieldEditorPreferencePage;
import org.eclipse.jface.preference.IntegerFieldEditor;
import org.eclipse.jface.preference.StringFieldEditor;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;

public class BarSeriesDataPreferencePage extends FieldEditorPreferencePage implements IWorkbenchPreferencePage {

	public BarSeriesDataPreferencePage() {
		super(GRID);
		setPreferenceStore(Activator.getDefault().getPreferenceStore());
		setDescription("Set the data series settings.");
	}

	public void createFieldEditors() {

		addField(new SpacerFieldEditor(getFieldEditorParent()));
		addField(new LabelFieldEditor("Bar Series 1", getFieldEditorParent()));
		addField(new SpacerFieldEditor(getFieldEditorParent()));
		//
		addField(new StringFieldEditor(BarSeriesPreferenceConstants.P_DESCRIPTION_SERIES_1, "Description:", getFieldEditorParent()));
		addField(new BooleanFieldEditor(BarSeriesPreferenceConstants.P_VISIBLE_SERIES_1, "Visible", getFieldEditorParent()));
		addField(new BooleanFieldEditor(BarSeriesPreferenceConstants.P_VISIBLE_IN_LEGEND_SERIES_1, "Visible in Legend", getFieldEditorParent()));
		addField(new ColorFieldEditor(BarSeriesPreferenceConstants.P_BAR_COLOR_SERIES_1, "Bar Color:", getFieldEditorParent()));
		addField(new IntegerFieldEditor(BarSeriesPreferenceConstants.P_BAR_PADDING_SERIES_1, "Bar Padding:", getFieldEditorParent()));
		addField(new IntegerFieldEditor(BarSeriesPreferenceConstants.P_BAR_WIDTH_SERIES_1, "Bar Width:", getFieldEditorParent()));
		addField(new ComboFieldEditor(BarSeriesPreferenceConstants.P_BAR_WIDTH_STYLE_SERIES_1, "Bar Width Style:", PreferenceSupport.BAR_WIDTH_STYLES, getFieldEditorParent()));
		//
		addField(new SpacerFieldEditor(getFieldEditorParent()));
		addField(new LabelFieldEditor("Bar Series 1 (Highlight)", getFieldEditorParent()));
		addField(new SpacerFieldEditor(getFieldEditorParent()));
		//
		addField(new BooleanFieldEditor(BarSeriesPreferenceConstants.P_VISIBLE_SERIES_1_HIGHLIGHT, "Visible", getFieldEditorParent()));
		addField(new BooleanFieldEditor(BarSeriesPreferenceConstants.P_VISIBLE_IN_LEGEND_SERIES_1_HIGHLIGHT, "Visible in Legend", getFieldEditorParent()));
		addField(new ColorFieldEditor(BarSeriesPreferenceConstants.P_BAR_COLOR_SERIES_1_HIGHLIGHT, "Bar Color:", getFieldEditorParent()));
		addField(new IntegerFieldEditor(BarSeriesPreferenceConstants.P_BAR_PADDING_SERIES_1_HIGHLIGHT, "Bar Padding:", getFieldEditorParent()));
		addField(new IntegerFieldEditor(BarSeriesPreferenceConstants.P_BAR_WIDTH_SERIES_1_HIGHLIGHT, "Bar Width:", getFieldEditorParent()));
		addField(new ComboFieldEditor(BarSeriesPreferenceConstants.P_BAR_WIDTH_STYLE_SERIES_1_HIGHLIGHT, "Bar Width Style:", PreferenceSupport.BAR_WIDTH_STYLES, getFieldEditorParent()));
	}

	public void init(IWorkbench workbench) {

	}
}