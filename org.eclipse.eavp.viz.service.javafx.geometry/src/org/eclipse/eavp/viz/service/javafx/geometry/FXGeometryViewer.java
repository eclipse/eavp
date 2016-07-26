/*******************************************************************************
 * Copyright (c) 2015 UT-Battelle, LLC.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   Tony McCrary (tmccrary@l33tlabs.com), Robert Smith
 *******************************************************************************/
package org.eclipse.eavp.viz.service.javafx.geometry;

import org.eclipse.eavp.viz.service.javafx.canvas.FXContentProvider;
import org.eclipse.eavp.viz.service.javafx.canvas.FXViewer;
import org.eclipse.eavp.viz.service.javafx.internal.model.FXCameraAttachment;
import org.eclipse.eavp.viz.service.javafx.internal.scene.camera.CenteredCameraController;
import org.eclipse.eavp.viz.service.javafx.scene.base.ICamera;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseMoveListener;
import org.eclipse.swt.widgets.Composite;

import javafx.embed.swt.FXCanvas;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Camera;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.SceneAntialiasing;
import javafx.scene.SubScene;
import javafx.scene.control.Button;
import javafx.scene.control.ToolBar;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;

/**
 * An extension of FX viewer for use with the geometry editor
 * 
 * @author Tony McCrary (tmccrary@l33tlabs.com), Robert Smith
 *
 */
public class FXGeometryViewer extends FXViewer {

	private SubScene subScene;

	public FXGeometryViewer(Composite parent) {
		super(parent);

		// Register the geometry attachment class with an attachment manager
		renderer.register(FXGeometryAttachment.class,
				new FXGeometryAttachmentManager());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.eavp.viz.service.javafx.canvas.FXViewer#createControl(org.
	 * eclipse.swt.widgets.Composite)
	 */
	@Override
	protected void createControl(Composite parent) {
		contentProvider = new FXContentProvider();

		fxCanvas = new FXCanvas(parent, SWT.NONE);

		// Create a pane with a toolbar containing the camera and cursor
		// positions at the bottom and set it as the root of the scene
		BorderPane pane = new BorderPane();

		// The file separator for the OS
		String separator = System.getProperty("file.separator");

		// Create a button with the reset icon
		Button cameraResetButton = new Button();
		cameraResetButton.setGraphic(new ImageView(new Image(
				separator + "icons" + separator + "iu_update_obj.gif")));
		cameraResetButton.setTooltip(new Tooltip("Reset Camera"));

		// The button will reset the camera when pressed
		cameraResetButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				cameraController.reset();
			}
		});

		// The toolbar displaying all controls for the editor
		ToolBar toolbar = new ToolBar(cameraResetButton);
		pane.setTop(toolbar);
		scene = new Scene(pane, 100, 100, true);

		// Create the root nodes
		internalRoot = new Group();
		root = new Group();
		internalRoot.getChildren().add(root);

		// Create a subscene which will house the editor and set it to the
		// center of the pane
		subScene = new SubScene(internalRoot, 2000, 2000, true,
				SceneAntialiasing.DISABLED);
		pane.setCenter(subScene);

		// Set the subscene to take up all the space it can get in the pane
		subScene.heightProperty()
				.bind(pane.heightProperty().subtract(toolbar.heightProperty()));
		subScene.widthProperty().bind(pane.widthProperty());

		// Hide the toolbar initially
		toolbar.setVisible(false);
		toolbar.setManaged(false);

		// The pane should be ignored by mouse interactions, allowing them to
		// intersect the fxCanvas's nodes.
		pane.setPickOnBounds(false);

		setupSceneInternals(internalRoot);

		// Set the scene's background color as a gradient from light to dark
		// grey
		Stop[] stops = new Stop[] { new Stop(0, Color.rgb(166, 166, 166)),
				new Stop(1, Color.rgb(83, 83, 83)) };
		pane.setBackground(
				new Background(new BackgroundFill(
						new LinearGradient(0, 0, 0, 1, true,
								CycleMethod.NO_CYCLE, stops),
						new CornerRadii(0), new Insets(0))));

		// Setup camera and input
		createDefaultCamera(internalRoot);
		wireSelectionHandling();

		// Set the scene to the canvas and add a mouse listener to manage the
		// controls
		fxCanvas.setScene(scene);
		fxCanvas.addMouseMoveListener(new MouseMoveListener() {

			// Whether or not the toolbar is currently in the visible state
			boolean toolbarShown = false;

			@Override
			public void mouseMove(MouseEvent e) {

				// If the mouse moves to the top of the screen while the toolbar
				// is invisible, display it
				if (!toolbarShown && e.y <= 30) {
					toolbarShown = true;
					toolbar.setVisible(true);
					toolbar.setManaged(true);
				}

				// If the mouse moves away from the toolbar, hide it
				else if (toolbarShown && e.y > 30) {
					toolbarShown = false;
					toolbar.setVisible(false);
					toolbar.setManaged(false);
				}
			}

		});

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.eavp.viz.service.javafx.canvas.FXViewer#updateCamera(org.
	 * eclipse.ice.viz.service.javafx.scene.base.ICamera)
	 */
	@Override
	protected void updateCamera(ICamera camera) {

		// Check the camera attachment for validity
		if (!(camera instanceof FXCameraAttachment)) {
			throw new IllegalArgumentException(
					"Invalid camera attached to Mesh Viewer.");
		}

		// Cast the camera attachment and check that it has a camera
		FXCameraAttachment attachment = (FXCameraAttachment) camera;
		Camera fxCamera = attachment.getFxCamera();

		if (fxCamera == null) {
			throw new NullPointerException(
					"No camera was attached to Mesh Viewer");
		}

		// Create a controller
		cameraController = new CenteredCameraController(fxCamera, scene,
				fxCanvas);

		// Set the camera on the scene
		scene.setCamera(null);
		subScene.setCamera(fxCamera);
		defaultCamera = fxCamera;

	}

}
