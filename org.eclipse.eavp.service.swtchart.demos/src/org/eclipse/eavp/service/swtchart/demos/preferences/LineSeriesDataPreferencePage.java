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
import org.eclipse.jface.preference.IntegerFieldEditor;
import org.eclipse.jface.preference.StringFieldEditor;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;

public class LineSeriesDataPreferencePage extends FieldEditorPreferencePage implements IWorkbenchPreferencePage {

	public LineSeriesDataPreferencePage() {
		super(GRID);
		setPreferenceStore(Activator.getDefault().getPreferenceStore());
		setDescription("Set the data series settings.");
	}

	public void createFieldEditors() {

		addField(new SpacerFieldEditor(getFieldEditorParent()));
		addField(new LabelFieldEditor("Data Series", getFieldEditorParent()));
		addField(new SpacerFieldEditor(getFieldEditorParent()));
		//
		addField(new ComboFieldEditor(LineSeriesPreferenceConstants.P_ANTIALIAS, "Antialias:", PreferenceSupport.ANTIALIAS_OPTIONS, getFieldEditorParent()));
		addField(new StringFieldEditor(LineSeriesPreferenceConstants.P_DESCRIPTION, "Description:", getFieldEditorParent()));
		addField(new BooleanFieldEditor(LineSeriesPreferenceConstants.P_ENABLE_AREA, "Enable Area", getFieldEditorParent()));
		addField(new BooleanFieldEditor(LineSeriesPreferenceConstants.P_ENABLE_STACK, "Enable Stack", getFieldEditorParent()));
		addField(new BooleanFieldEditor(LineSeriesPreferenceConstants.P_ENABLE_STEP, "Enable Step", getFieldEditorParent()));
		addField(new ColorFieldEditor(LineSeriesPreferenceConstants.P_LINE_COLOR, "Line Color:", getFieldEditorParent()));
		addField(new ComboFieldEditor(LineSeriesPreferenceConstants.P_LINE_STYLE, "Line Style:", PreferenceSupport.LINE_STYLES, getFieldEditorParent()));
		addField(new IntegerFieldEditor(LineSeriesPreferenceConstants.P_LINE_WIDTH, "Line Width:", getFieldEditorParent()));
		addField(new ColorFieldEditor(LineSeriesPreferenceConstants.P_SYMBOL_COLOR, "Symbol Color:", getFieldEditorParent()));
		addField(new IntegerFieldEditor(LineSeriesPreferenceConstants.P_SYMBOL_SIZE, "Symbol Size:", getFieldEditorParent()));
		addField(new ComboFieldEditor(LineSeriesPreferenceConstants.P_SYMBOL_TYPE, "Symbol Type:", PreferenceSupport.SYMBOL_TYPES, getFieldEditorParent()));
		addField(new BooleanFieldEditor(LineSeriesPreferenceConstants.P_VISIBLE, "Visible", getFieldEditorParent()));
		addField(new BooleanFieldEditor(LineSeriesPreferenceConstants.P_VISIBLE_IN_LEGEND, "Visible in Legend", getFieldEditorParent()));
	}

	public void init(IWorkbench workbench) {

	}
}