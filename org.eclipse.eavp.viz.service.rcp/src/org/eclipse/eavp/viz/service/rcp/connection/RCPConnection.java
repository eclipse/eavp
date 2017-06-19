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
package org.eclipse.eavp.viz.service.rcp.connection;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.eavp.viz.service.connections.ConnectionState;
import org.eclipse.eavp.viz.service.connections.VizConnection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * An extension of VizConnection that launches connection attempts as Eclipse
 * jobs.
 * 
 * @author Robert Smith
 *
 */
abstract public class RCPConnection<T> extends VizConnection<T> {

	/**
	 * Logger for handling event messages and other information.
	 */
	private static final Logger logger = LoggerFactory
			.getLogger(RCPConnection.class);

	@Override
	protected Future<ConnectionState> startConnectThread() {
		executorService = Executors.newSingleThreadExecutor();

		// Create a new job to listen for the connection status. It will notify
		// users about the progress of the connection attempt.
		Job job = new Job("Vizualization Service") {
			@Override
			protected IStatus run(IProgressMonitor monitor) {

				/*
				 * Currently, we give the task 100 "ticks". The initial
				 * transition from "disconnected" to "connecting" is worth 30
				 * ticks. While the state is "connecting", a tick is added every
				 * timeout while waiting for the state to change. When the
				 * connection is established or fails, all remaining ticks are
				 * filled.
				 */

				// Set the initial task name.
				monitor.beginTask("Viz connection \""
						+ RCPConnection.this.getName() + "\"", 100);

				String message = null;
				long timeout = 250;

				// Wait for the connection to go from "Disconnected" to
				// "Connecting".
				message = RCPConnection.this.getStatusMessage();
				monitor.subTask(message);
				while (state == ConnectionState.Disconnected) {
					try {
						Thread.sleep(timeout);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				monitor.worked(30);

				// Wait for the connection to finish its connection attempt.
				message = RCPConnection.this.getStatusMessage();
				monitor.subTask(message);
				while (state == ConnectionState.Connecting) {
					try {
						Thread.sleep(timeout);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					monitor.worked(1);
				}
				monitor.worked(100);

				// Return the result.
				int statusFlag = Status.OK;
				message = RCPConnection.this.getStatusMessage();

				// Wait for the connection thread to end, then get its final
				// state.
				ConnectionState endState = null;
				try {
					endState = hookIntoConnectThread().get();
				} catch (InterruptedException | ExecutionException e) {
					logger.error("An error occurred while waiting for the "
							+ "connection thread to finish.");
				}

				// Add a helpful message if the connection attempt failed.
				if (endState == ConnectionState.Disconnected
						|| endState == ConnectionState.Failed) {
					statusFlag = Status.ERROR;

					// Append additional information to the message
					message += " " + errorMessage;
					errorMessage = "";
					message += "\nPlease check the settings for \""
							+ RCPConnection.this.getName()
							+ "\" under Windows > Preferences > Vizualization.";
				}
				return new Status(statusFlag, "org.eclipse.viz.service", 1,
						message, null);
			}
		};
		job.schedule();

		return executorService.submit(connectionStatusCallable);
	}

	/**
	 * Queues a disconnect task in the {@link #executorService}. This task will
	 * attempt to disconnect from the client. After completion, the service will
	 * be shut down.
	 * 
	 * @return The future task after which the connection will either be
	 *         connected or failed.
	 */
	@Override
	protected Future<ConnectionState> startDisconnectThread() {

		// Create a new job to listen for the connection status. It will notify
		// users about the progress of the disconnect attempt.
		Job job = new Job("Vizualization Service") {
			@Override
			protected IStatus run(IProgressMonitor monitor) {

				/*
				 * Currently, we give the task 100 "ticks". While the state is
				 * changing from "connected" to "disconnected" or "failed", a
				 * tick is added every timeout while waiting for the state to
				 * change. When the connection is disconnected or fails, all
				 * remaining ticks are filled.
				 */

				// Set the initial task name.
				monitor.beginTask("Viz connection \""
						+ RCPConnection.this.getName() + "\"", 100);

				String message = null;
				long timeout = 250;

				// Wait for the connection to go from "Connected" to
				// "Disconnected" or "Failed".
				message = RCPConnection.this.getStatusMessage();
				monitor.subTask(message);
				while (state == ConnectionState.Connected) {
					try {
						Thread.sleep(timeout);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					monitor.worked(1);
				}
				monitor.worked(100);

				// Return the result.
				int statusFlag = state == ConnectionState.Disconnected
						? Status.OK
						: Status.ERROR;
				message = RCPConnection.this.getStatusMessage();
				return new Status(statusFlag, "org.eclipse.viz.service", 1,
						message, null);
			}
		};
		job.schedule();

		// If necessary, create the executor service.
		if (executorService == null) {
			executorService = Executors.newSingleThreadExecutor();
		}
		return executorService.submit(disconnectionStatusCallable);
	}
}
