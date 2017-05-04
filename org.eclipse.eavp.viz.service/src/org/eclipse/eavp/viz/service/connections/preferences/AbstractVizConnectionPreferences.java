package org.eclipse.eavp.viz.service.connections.preferences;

/**
 * The base abstract implementation for IVizConnectionPreferences
 * 
 * @author Robert Smith
 *
 */
abstract public class AbstractVizConnectionPreferences
		implements IVizConnectionPreferences {

	/**
	 * The name of the remote connection (or "localhost") which will be used for
	 * some of the preferences.
	 */
	private String connectionName;

	/**
	 * The connection's name. This should be unique among
	 * VizConnectionPreferences for the same IVizService.
	 */
	private String name;

	/**
	 * The port number for the connection
	 */
	private int port;

	/**
	 * The default constructor.
	 */
	public AbstractVizConnectionPreferences() {
		connectionName = "localhost";
		name = "";
		port = 0;
	}

	/**
	 * A constructor for loading preferences from a string, using the same
	 * format as those created by serialize().
	 * 
	 * @param data
	 *            The string representation of an
	 *            AbstractVizConnectionPreferences to load into the new object.
	 * @param delimiter
	 *            The delimiter separating data fields within the data string.
	 */
	public AbstractVizConnectionPreferences(String data, String delimiter) {

		// Split the string by the delimiter to separate the data fields
		String[] tokens = data.split(delimiter, -1);

		// If string wasn't properly formatted, use the default values instead
		if (tokens.length < 3) {
			connectionName = "localhost";
			name = "";
			port = 0;
		} else {

			// Apply the data to the fields in alphabetic order
			connectionName = tokens[0];
			name = tokens[1];

			try {
				port = Integer.parseInt(tokens[2]);
			} catch (NumberFormatException e) {

				// If port was not a number, use the default
				port = 0;
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.eavp.viz.service.connections.preferences.
	 * IVizConnectionPreferences#getConnectionName()
	 */
	@Override
	public String getConnectionName() {
		return connectionName;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.eavp.viz.service.connections.preferences.
	 * IVizConnectionPreferences#getName()
	 */
	@Override
	public String getName() {
		return name;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.eavp.viz.service.connections.preferences.
	 * IVizConnectionPreferences#getPort()
	 */
	@Override
	public int getPort() {
		return port;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.eavp.viz.service.connections.preferences.
	 * IVizConnectionPreferences#serialize()
	 */
	@Override
	public String serialize(String delimiter) {
		return connectionName + delimiter + name + delimiter + port;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.eavp.viz.service.connections.preferences.
	 * IVizConnectionPreferences#setConnectionName(java.lang.String)
	 */
	@Override
	public void setConnectionName(String connectionName) {
		this.connectionName = connectionName;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.eavp.viz.service.connections.preferences.
	 * IVizConnectionPreferences#setName(java.lang.String)
	 */
	@Override
	public void setName(String name) {
		this.name = name;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.eavp.viz.service.connections.preferences.
	 * IVizConnectionPreferences#setPort(int)
	 */
	@Override
	public void setPort(int port) {
		this.port = port;
	}
}
