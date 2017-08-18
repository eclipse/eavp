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
package org.eclipse.eavp.service.swtchart.axisconverter;

import org.eclipse.eavp.service.swtchart.core.AbstractAxisScaleConverter;
import org.eclipse.eavp.service.swtchart.core.IAxisScaleConverter;

public class PassThroughConverter extends AbstractAxisScaleConverter implements IAxisScaleConverter {

	@Override
	public double convertToSecondaryUnit(double primaryValue) {

		return primaryValue;
	}

	@Override
	public double convertToPrimaryUnit(double secondaryValue) {

		return secondaryValue;
	}
}
