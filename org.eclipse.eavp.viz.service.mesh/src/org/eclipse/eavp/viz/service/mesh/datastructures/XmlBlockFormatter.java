/*******************************************************************************
 * Copyright (c) 2016 UT-Battelle, LLC.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   Robert Smith
 *******************************************************************************/
package org.eclipse.eavp.viz.service.mesh.datastructures;

import java.io.UnsupportedEncodingException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The xmlBlockFormatter takes as input a String representation of an .xml file
 * and formats matrix formatted content inside of it correctly by adding
 * whitespace.
 * 
 * The XmlBlockFormatter expects well formed XML, except for the blocks it will
 * handle itself, which are expected to be lines of data within a single XML
 * node, each separated by a newline character and no additional whitespace at
 * the start of each line. No tags should be present inside the block, and it is
 * expected that the tags beginning and ending the block are separated from the
 * first/last line by only a new line character and no additional white space.
 * 
 * The end result will be to shift each such line in the block content so that
 * they each begin one level of indentation further than the tags for the node
 * which contains it. The xml will be returned in the form of a byte array.
 * 
 * @author Robert Smith
 *
 */
public class XmlBlockFormatter {

	/**
	 * The logger which will handle error messages for this class.
	 */
	private static final Logger logger = LoggerFactory
			.getLogger(XmlBlockFormatter.class);

	/**
	 * Format the input byte array representation of an xml file according to
	 * the rules for the XmlBlockFormatter.
	 * 
	 * @param input
	 *            The xml data to be formatted.
	 * @return The xml data with blocks of content and their tags indented to
	 *         the proper levels for human readability.
	 */
	public byte[] format(byte[] input) {

		String xml = null;
		try {
			xml = new String(input, "UTF-8");
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		// The current number of spaces of indentation to apply to the next line
		int indentation = 0;

		// Whether or not the formatter is at the start of a new line
		boolean newLine = false;

		// Whether or not the formatter is currently reading through a group of
		// spaces to measure the current level of indentation
		boolean readingIndentation = false;

		// Whether or not the formatter is currently inside a block of content
		// which needs to be formatted
		boolean block = false;

		// The string representing the formatted xml file
		String formatted = "";

		// Consider each character in the xml
		for (int i = 0; i < xml.length(); i++) {
			char currChar = xml.charAt(i);

			// If the character is a line break, add it to the output and note
			// that a new line has begun
			if (currChar == '\n') {
				formatted += currChar;
				newLine = true;
			} else {
				if (newLine) {

					// If the first character of a new line is a space, begin
					// measuring the currently level of indentation
					if (currChar == ' ') {
						indentation = 1;
						readingIndentation = true;
					}

					// If the first character is not the start of a tag, then we
					// have found a block of content which needs to be
					// formatted.
					else if (currChar != '<') {

						// Add spaces equal to the last measured level of
						// indentation, then four more so that the block will be
						// one level deeper than its tags.
						for (int j = 0; j < indentation + 4; j++) {
							formatted += ' ';
						}

						// Note that we are currently inside a block of format
						// requiring content
						block = true;
					}

					// If the first character is the beginning of a tag and we
					// are reading a block, then this line is the end tag for
					// that block.
					else if (currChar == '<' && block) {

						// Indent the tag to the level of the start tag
						for (int j = 0; j < indentation; j++) {
							formatted += ' ';
						}

						// We have finished the current block of content.
						block = false;
					}

					// The start of the current line has been processed.
					newLine = false;

				}

				// While inside an indentation, count the number of spaces
				// encountered until the first non-space character of the line
				else if (readingIndentation) {
					if (currChar == ' ') {
						indentation++;
					} else {
						readingIndentation = false;
					}
				}

				// Add the processed character to the formated string
				formatted += currChar;
			}
		}

		// Return the formatted output as a byte array
		try {
			return formatted.getBytes("UTF-8");
		} catch (UnsupportedEncodingException e) {
			return null;
		}
	}
}
