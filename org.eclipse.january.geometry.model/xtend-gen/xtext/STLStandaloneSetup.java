/**
 * Copyright (c) 2016 UT-Battelle, LLC.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *   Initial API and implementation and/or initial documentation -
 *   Kasper Gammeltoft
 */
package xtext;

import xtext.STLStandaloneSetupGenerated;

/**
 * Initialization support for running Xtext languages without Equinox extension registry.
 */
@SuppressWarnings("all")
public class STLStandaloneSetup extends STLStandaloneSetupGenerated {
  public static void doSetup() {
    STLStandaloneSetup _sTLStandaloneSetup = new STLStandaloneSetup();
    _sTLStandaloneSetup.createInjectorAndDoEMFRegistration();
  }
}
