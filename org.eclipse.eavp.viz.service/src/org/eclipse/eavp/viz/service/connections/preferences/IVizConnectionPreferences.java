/*******************************************************************************
 * Copyright (c) 2017 UT-Battelle, LLC.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   Robert Smith - Initial API and implementation and/or initial documentation
 *   
 *******************************************************************************/
package org.eclipse.eavp.viz.service.connections.preferences;

/**
 * An interface for classes that store the preference information for an
 * IVizConnection to be displayed and edited in Eclipse's preferences menu.
 * 
 * @author Robert Smith
 *
 */
public interface IVizConnectionPreferences {

	/**
	 * Getter method for name of the host machine.
	 * 
	 * @return The hostname for the machine being connected to.
	 */
	String getHostName();

	/**
	 * Getter method for the connection's name. For connections of the same
	 * type, this should be unique.
	 * 
	 * @return The connection's name
	 */
	String getName();

	/**
	 * Getter method for the connection's port number.
	 * 
	 * @return The port number to connect over.
	 */
	int getPort();

	/**
	 * Getter method for the connection's username.
	 * 
	 * @return The username for use when connecting, or an empty string if no
	 *         username is to be used.
	 */
	String getUsername();

	/**
	 * Produce a comma delimited String representing the contents of this
	 * object.
	 * 
	 * Fields are to be placed in the string in alphabetical order for the base
	 * class which implements this interface, separated by commas. Each subclass
	 * is to append its own data members to the string created by the parent
	 * class.
	 * 
	 * @return A String representation of all this object's data.
	 */
	String serialize();

	/**
	 * Setter method for the name of the machine to be connected to.
	 * 
	 * @param hostName
	 *            the new host name
	 */
	void setHostName(String hostName);

	/**
	 * Setter method for the connection's name. Names should be unique among
	 * IVizConnections for the same IVizService.
	 * 
	 * @param name
	 *            The connection's new name
	 */
	void setName(String name);

	/**
	 * Setter method for the connection's port number.
	 * 
	 * @param port
	 *            The new port number for the connection.
	 */
	void setPort(int port);

	/**
	 * Setter method for the connection's username.
	 * 
	 * @param username
	 *            The username for use when connecting.
	 */
	void setUsername(String username);
}
