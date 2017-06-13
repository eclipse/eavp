/*******************************************************************************
 * Copyright (c) 2017 UT-Battelle, LLC.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   Robert Smith - Initial API and implementation and/or
 *     initial documentation
 *******************************************************************************/
package org.eclipse.eavp.viz.service.rcp;

import org.eclipse.eavp.viz.service.AbstractVizService;
import org.eclipse.eavp.viz.service.rcp.preferences.CustomScopedPreferenceStore;
import org.eclipse.jface.preference.IPreferenceStore;

/**
 * An extension of AbstractVizService with additional features specific to an
 * RCP environment.
 * 
 * @author Robert Smith
 *
 */
abstract public class RCPVizService extends AbstractVizService {

	/**
	 * A reference to the associated preference page's {@link IPreferenceStore}.
	 * If this has been determined previously, then it should be returned in
	 * {@link #getPreferenceStore()}.
	 */
	private IPreferenceStore preferenceStore = null;

	/**
	 * Gets the {@link IPreferenceStore} for the associated preference page.
	 * 
	 * @return The {@code IPreferenceStore} whose defaults should be set.
	 */
	protected IPreferenceStore getPreferenceStore() {
		if (preferenceStore == null) {
			// Get the PreferenceStore for the bundle.
			preferenceStore = new CustomScopedPreferenceStore(getClass());
		}
		return preferenceStore;
	}
}
