/*******************************************************************************
 * Copyright (c) 2017, 2018 Lablicate GmbH.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 * Dr. Philip Wenig - initial API and implementation
 *******************************************************************************/
package org.eclipse.eavp.service.swtchart;

import java.io.IOException;
import java.net.URL;

import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Platform;
import org.osgi.framework.Bundle;

public class PathResolver {

	/**
	 * Returns a absolute path of the specified Folder. For example
	 * TESTDATA_IMPORT_EMPTY as an absolute Path:
	 * $PluginPath$/testdata/files/...
	 * 
	 * @param string
	 * @return String absolutePath
	 */
	public static String getAbsolutePath(String string) {

		Bundle bundle = Platform.getBundle(Activator.getDefault().getBundle().getSymbolicName());
		IPath path = new Path(string);
		URL url = FileLocator.find(bundle, path, null);
		try {
			return FileLocator.resolve(url).getPath();
		} catch(IOException e) {
			e.printStackTrace();
		}
		return null;
	}
}
