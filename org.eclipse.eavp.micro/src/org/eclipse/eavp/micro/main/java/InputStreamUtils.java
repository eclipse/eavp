/*******************************************************************************
 * Copyright (c) 2019 UT-Battelle, LLC.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   Robert Smith
 *******************************************************************************/
package org.eclipse.eavp.micro.main.java;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

/**
 * Utility functions for dealing with input streams
 * 
 * @author Robert Smith
 *
 */
public class InputStreamUtils {

	/**
	 * Read a full text stream from the input stream
	 * 
	 * @param stream The stream to read.
	 * @return The string formed from the stream.
	 */
	public static String readStringStream(InputStream stream) {
		
		//Buffer to hold chunks of the stream
		char[] buffer = new char[1024];
		
		//String builder that will hold the contents
		StringBuilder builder = new StringBuilder();
		
		try {
			
			//Create a reader for the stream
			InputStreamReader reader = new InputStreamReader(stream, "UTF-8");
			
			//Loop until the stream is exhausted, adding all content to the builder
			int size = reader.read(buffer, 0, 1024);
			
			while(size >= 0) {
				builder.append(buffer, 0, size);
				builder.append("\n");
				size = reader.read(buffer, 0, 1024);
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return builder.toString();
	}
}
