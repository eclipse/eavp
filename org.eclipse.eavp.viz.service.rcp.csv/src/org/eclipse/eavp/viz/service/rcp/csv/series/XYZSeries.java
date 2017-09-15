/*******************************************************************************
 * Copyright (c) 2017 UT-Battelle, LLC.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   Initial API and implementation and/or initial documentation
 *   - Robert Smith
 *******************************************************************************/
package org.eclipse.eavp.viz.service.rcp.csv.series;

import org.eclipse.eavp.viz.service.csv.CSVSeries;
import org.eclipse.swt.graphics.Color;

import ca.odell.glazedlists.BasicEventList;
import ca.odell.glazedlists.EventList;

/**
 * A CSV series implementation specific to the XYZGraph.
 * 
 * @author Robert Smith
 *
 */
public class XYZSeries extends CSVSeries {

	/**
	 * Constructor, creates a new csv series with no data and the default style.
	 */
	public XYZSeries(Color color) {
		this(new BasicEventList<Double>(), color);
	}

	/**
	 * Creates a new CSV Series with the given source list.
	 * 
	 * @param source
	 */
	protected XYZSeries(EventList<Double> source, Color color) {
		super(source);
		style = new XYZSeriesStyle(color);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#clone()
	 */
	@Override
	public Object clone() {
		XYZSeries clone = new XYZSeries((Color) style.getProperty("color"));
		clone.copy(this);
		return clone;
	}
}
