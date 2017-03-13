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
