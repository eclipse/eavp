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

public class ScatterSeriesDataPreferencePage extends FieldEditorPreferencePage implements IWorkbenchPreferencePage {

	public ScatterSeriesDataPreferencePage() {
		super(GRID);
		setPreferenceStore(Activator.getDefault().getPreferenceStore());
		setDescription("Set the data series settings.");
	}

	public void createFieldEditors() {

		addField(new SpacerFieldEditor(getFieldEditorParent()));
		addField(new LabelFieldEditor("Scatter Series 1", getFieldEditorParent()));
		addField(new SpacerFieldEditor(getFieldEditorParent()));
		//
		addField(new StringFieldEditor(ScatterSeriesPreferenceConstants.P_DESCRIPTION_SERIES_1, "Description:", getFieldEditorParent()));
		addField(new ColorFieldEditor(ScatterSeriesPreferenceConstants.P_SYMBOL_COLOR_SERIES_1, "Symbol Color:", getFieldEditorParent()));
		addField(new IntegerFieldEditor(ScatterSeriesPreferenceConstants.P_SYMBOL_SIZE_SERIES_1, "Symbol Size:", getFieldEditorParent()));
		addField(new ComboFieldEditor(ScatterSeriesPreferenceConstants.P_SYMBOL_TYPE_SERIES_1, "Symbol Type:", PreferenceSupport.SYMBOL_TYPES, getFieldEditorParent()));
		addField(new BooleanFieldEditor(ScatterSeriesPreferenceConstants.P_VISIBLE_SERIES_1, "Visible", getFieldEditorParent()));
		addField(new BooleanFieldEditor(ScatterSeriesPreferenceConstants.P_VISIBLE_IN_LEGEND_SERIES_1, "Visible in Legend", getFieldEditorParent()));
	}

	public void init(IWorkbench workbench) {

	}
}