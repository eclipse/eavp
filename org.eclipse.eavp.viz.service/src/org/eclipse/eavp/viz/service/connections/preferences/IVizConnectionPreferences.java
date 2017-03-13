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
	 * Getter method for name of the remote connection from which some
	 * preferences are drawn.
	 * 
	 * @return
	 */
	String getConnectionName();

	/**
	 * Getter method for the connection's name.
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
	 * Produce a comma delimited String representing the contents of this
	 * object.
	 * 
	 * @return A String representation of all this object's data.
	 */
	String serialize();

	/**
	 * Setter method for the name of the remote connection from which some
	 * preferences are drawn. The connection name should be either localhost or
	 * the name of a PTP remote connection.
	 * 
	 * @param connectionName
	 *            the new connection name
	 */
	void setConnectionName(String connectionName);

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
}
