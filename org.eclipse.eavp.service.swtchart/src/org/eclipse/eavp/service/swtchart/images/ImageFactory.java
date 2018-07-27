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
package org.eclipse.eavp.service.swtchart.images;

import org.eclipse.eavp.service.swtchart.core.ScrollableChart;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

public class ImageFactory<T extends ScrollableChart> {

	private T t;
	private ImageSupplier imageSupplier;
	private ImageData imageData = null;

	/**
	 * The width and height of the current display is allowed.
	 * If width or height are greater, then the display bounds are used.
	 * 
	 * @param width
	 * @param height
	 * @return Shell
	 * @throws IllegalAccessException
	 * @throws InstantiationException
	 */
	public ImageFactory(Class<T> clazz, int width, int height) throws InstantiationException, IllegalAccessException {
		//
		t = clazz.newInstance();
		imageSupplier = new ImageSupplier();
		//
		Display display = Display.getDefault();
		if(display != null) {
			width = (width > display.getBounds().width) ? display.getBounds().width : width;
			height = (height > display.getBounds().height) ? display.getBounds().height : height;
			Shell shell = t.getShell();
			shell.setSize(width, height);
		}
	}

	public T getChart() {

		return t;
	}

	/**
	 * Returns the image data of the chart.
	 * 
	 * @return ImageData.
	 */
	public ImageData getImageData() {

		if(imageData == null) {
			Shell shell = t.getShell();
			shell.open();
			imageData = imageSupplier.getImageData(t.getBaseChart());
			shell.close();
		}
		return imageData;
	}

	/**
	 * Path to the file and the format.
	 * e.g.: SWT.IMAGE_PNG
	 * 
	 * @param fileName
	 * @param format
	 */
	public void saveImage(String fileName, int format) {

		ImageData imageData = getImageData();
		imageSupplier.saveImage(imageData, fileName, format);
	}
}
