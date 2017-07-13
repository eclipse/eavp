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
package org.eclipse.eavp.service.swtchart.demos.preferences;

import java.util.Locale;

import org.eclipse.swt.SWT;
import org.swtchart.IAxis.Position;
import org.swtchart.LineStyle;

public class PreferenceSupport {

	public static String[][] LEGEND_POSITIONS = new String[][]{//
			{"Left", Integer.toString(SWT.LEFT)}, //
			{"Right", Integer.toString(SWT.RIGHT)}, //
			{"Top", Integer.toString(SWT.TOP)}, //
			{"Bottom", Integer.toString(SWT.BOTTOM)}//
	};
	//
	public static String[][] ORIENTATIONS = new String[][]{//
			{"Horizontal", Integer.toString(SWT.HORIZONTAL)}, //
			{"Vertical", Integer.toString(SWT.VERTICAL)}//
	};
	//
	public static String[][] AXIS_POSITIONS = new String[][]{//
			{"Primary", Position.Primary.toString()}, //
			{"Secondary", Position.Secondary.toString()}//
	};
	//
	public static String[][] LOCALES = new String[][]{//
			{"English", Locale.ENGLISH.getLanguage()}, //
			{"US", Locale.US.getLanguage()}, //
			{"German", Locale.GERMAN.getLanguage()}//
	};
	//
	public static String[][] GRID_LINE_STYLES = new String[][]{//
			{"None", LineStyle.NONE.toString()}, //
			{"Solid", LineStyle.SOLID.toString()}, //
			{"Dash", LineStyle.DASH.toString()}, //
			{"Dot", LineStyle.DOT.toString()}, //
			{"Dash Dot", LineStyle.DASHDOT.toString()}, //
			{"Dash Dot Dot", LineStyle.DASHDOTDOT.toString()}//
	};
}
