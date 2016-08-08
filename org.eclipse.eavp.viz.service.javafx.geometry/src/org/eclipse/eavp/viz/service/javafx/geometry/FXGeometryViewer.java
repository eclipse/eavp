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

import java.util.List;

import org.eclipse.eavp.viz.service.geometry.widgets.ShapeTreeView;
import org.eclipse.eavp.viz.service.javafx.canvas.FXContentProvider;
import org.eclipse.eavp.viz.service.javafx.canvas.FXViewer;
import org.eclipse.eavp.viz.service.javafx.canvas.TransformGizmo;
import org.eclipse.eavp.viz.service.javafx.internal.model.FXCameraAttachment;
import org.eclipse.eavp.viz.service.javafx.internal.scene.camera.CenteredCameraController;
import org.eclipse.eavp.viz.service.javafx.scene.base.ICamera;
import org.eclipse.january.geometry.BoundingBox;
import org.eclipse.january.geometry.Shape;
import org.eclipse.january.geometry.Union;
import org.eclipse.january.geometry.Vertex;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseMoveListener;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IViewPart;
import org.eclipse.ui.PlatformUI;

import javafx.embed.swt.FXCanvas;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.AmbientLight;
import javafx.scene.Camera;
import javafx.scene.Group;
import javafx.scene.PointLight;
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
import javafx.scene.paint.PhongMaterial;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Box;
import javafx.scene.shape.DrawMode;
import model.IRenderElement;

/**
 * An extension of FX viewer for use with the geometry editor
 * 
 * @author Tony McCrary (tmccrary@l33tlabs.com), Robert Smith
 *
 */
public class FXGeometryViewer extends FXViewer {

	/**
	 * The gizmo containing the three lines that will display the axes.
	 */
	private TransformGizmo axes;
	
	/**
	 * The wireframe square that displays the XZ plane.
	 */
	private Box plane;
	
	/**
	 * The subScene containing the three dimensional geometry.
	 */
	private SubScene subScene;

	/**
	 * The default constructor.
	 * 
	 * @param parent
	 *            The composite in which the view will be drawn.
	 */
	public FXGeometryViewer(Composite parent) {
		super(parent);

		// Register the geometry attachment class with an attachment manager
		renderer.register(FXGeometryAttachment.class,
				new FXGeometryAttachmentManager());

		// Add a zoom to fit action to the context menu
		MenuItem zoomToFit = new MenuItem(contextMenu, SWT.PUSH);
		zoomToFit.setText("Zoom To Fit");
		zoomToFit.addListener(SWT.Selection, new Listener() {

			@Override
			public void handleEvent(Event event) {
				fitZoom();
			};
		});
	}

	/**
	 * Set the zoom to fit tightly about the current selection
	 */
	private void fitZoom() {
		// Get the selection from the shape tree view
		IViewPart treeView = PlatformUI.getWorkbench()
				.getActiveWorkbenchWindow().getActivePage()
				.findView(ShapeTreeView.ID);
		List<IRenderElement> selection = ((ShapeTreeView) treeView)
				.getSelectedElements();

		// The bounds of the area to fit on the camera
		BoundingBox bounds = null;
		
		//Fail silently if nothing is selected
		if(selection.isEmpty()){
			return;
		}

		// Handle each of the selected shapes
		for (IRenderElement selected : selection) {
			if (selected.getBase() instanceof Shape) {
				Shape shape = (Shape) selected.getBase();

				// Add the shape's bounds
				if (bounds == null) {
					bounds = org.eclipse.january.geometry.BoundingBox
							.getBounds(shape);
				} else {
					bounds.addArea(org.eclipse.january.geometry.BoundingBox
							.getBounds(shape));
				}

			} else if (selected.getBase() instanceof Union) {
				Union union = (Union) selected.getBase();

				// Add the union's bounds
				if (bounds == null) {
					bounds = org.eclipse.january.geometry.BoundingBox
							.getBounds(union);
				} else {
					bounds.addArea(org.eclipse.january.geometry.BoundingBox
							.getBounds(union));
				}
			}

			// If a non-Node was in the selection, fail silently
			else {
				return;
			}
		}

		// Get the dimensions of the object
		double xDim = bounds.getMaxX() - bounds.getMinX();
		double yDim = bounds.getMaxY() - bounds.getMinY();
		double zDim = bounds.getMaxZ() - bounds.getMinZ();

		// The center of the object
		Vertex center = org.eclipse.january.geometry.BoundingBox
				.getCenter(bounds);

		// Handle the case where the x dimension is the smallest, so
		// the camera will look down the x axis
		if (xDim <= yDim && xDim <= yDim) {

			// Find the size of the largest perpindeiculr dimension
			double size = Math.max(yDim, zDim);

			// Reset the camera to look down the x axis
			cameraController.setDefaultAngle(0, 90, 0);
			cameraController.reset();

			// Move the camera to the center point
			cameraController.strafeCamera(-center.getZ());
			cameraController.raiseCamera(-center.getY());
			cameraController.thrustCamera(center.getX());

			// Move the camera 2000 units forward (to adjust for the
			// default position being 2000 units away from the
			// center)
			// and pull back a bit more than twice the object's
			// largest
			// dimension, so that the whole thing will fit on screen
			cameraController.thrustCamera(2000 - (2.1 * size));

		}

		// Handle the case where the y dimension is smallest, so the
		// camera will look down the y axis
		else if (yDim <= xDim && yDim <= zDim) {

			// Get the size of the largest perpendicular dimension
			double size = Math.max(xDim, zDim);
			cameraController.setDefaultAngle(90, 0, 0);
			cameraController.reset();

			// Move the camera to the center point
			cameraController.strafeCamera(center.getX());
			cameraController.raiseCamera(-center.getZ());
			cameraController.thrustCamera(-center.getY());

			// Move the camera 2000 units forward (to adjust for the
			// default position being 2000 units away from the
			// center)
			// and pull back a bit more than twice the object's
			// largest
			// dimension, so that the whole thing will fit on screen
			cameraController.thrustCamera(2000 - (2.1 * size));
		}

		// Handle the case where the x dimension is smallest and the
		// camera will look down the x asix
		else {

			// The size of the largest perpendicular dimension
			double size = Math.max(xDim, yDim);

			// Reset the camera
			cameraController.setDefaultAngle(0, 0, 0);
			cameraController.reset();

			// Move the camera to the center
			cameraController.strafeCamera(-center.getX());
			cameraController.raiseCamera(-center.getY());
			cameraController.thrustCamera(center.getZ());

			// Move the camera 2000 units forward (to adjust for the
			// default position being 2000 units away from the
			// center)
			// and pull back a bit more than twice the object's
			// largest
			// dimension, so that the whole thing will fit on screen
			cameraController.thrustCamera(2000 - (2.1 * size));
		}
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
		Button cameraResetButton = new Button("Reset Camera");
		//TODO Figure out how to draw on images from bundle for JavaFX
//		cameraResetButton.setGraphic(new ImageView(new Image(
//				separator + "icons" + separator + "iu_update_obj.gif")));
		cameraResetButton.setTooltip(new Tooltip("Reset Camera"));

		// The button will reset the camera when pressed
		cameraResetButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				cameraController.setDefaultAngle(0, 0, 0);
				cameraController.reset();
			}
		});

		// Create a button with the fit icon
		Button zoomFitButton = new Button("Zoom to fit");
		// zoomFitButton.setGraphic(new ImageView(
		// new Image(separator + "icons" + separator + "fit_icon.png")));
		zoomFitButton.setTooltip(new Tooltip("Zoom to fit"));

		// The button will fit the zoom to the current selection when pressed
		zoomFitButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				fitZoom();
			}
		});
		
		Button toggleAxesButton = new Button("Toggle Axes");
		toggleAxesButton.setTooltip(new Tooltip("Toggle Axes"));
		
		// The button will remove/add the axes and plane when pressed.
		toggleAxesButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				axes.setVisible(!axes.isVisible());
				plane.setVisible(!plane.isVisible());
			}
		});
		
		//Add a button to set the axis size
		Button changeAxesSizeButton = new Button("Set Axis Size");
		changeAxesSizeButton.setTooltip(new Tooltip("Set Axis Size"));
		
		changeAxesSizeButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				
				//Pop open a dialog to prompt the user for the new scale
				Display d = Display.getCurrent();
				Shell s = new Shell(d);
				double f = axes.getScale();
				
				AxisSizeDialog dialog = new AxisSizeDialog(new Shell(Display.getCurrent()), axes.getScale());
				dialog.open();
				double input = dialog.getInput();
				
				//If the user selected value was valid, set the axis size
				if(input > 0){
				axes.setAxisSize(input);
				}
			}
		});

		// The toolbar displaying all controls for the editor
		ToolBar toolbar = new ToolBar(cameraResetButton, zoomFitButton, toggleAxesButton, changeAxesSizeButton);
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
		subScene.heightProperty().bind(pane.heightProperty());
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

	protected void setupSceneInternals(Group parent) {
		// Create scene plane for frame of reference.
		plane = new Box(1000, 0, 1000);
		plane.setMouseTransparent(true);
		plane.setDrawMode(DrawMode.LINE);
		plane.setMaterial(new PhongMaterial(Color.ANTIQUEWHITE));

		AmbientLight ambientLight = new AmbientLight(Color.rgb(100, 100, 100));

		PointLight light1 = new PointLight(Color.ANTIQUEWHITE);
		light1.setMouseTransparent(true);
		light1.setTranslateY(-350);

		PointLight light2 = new PointLight(Color.ANTIQUEWHITE);
		light2.setMouseTransparent(true);
		light2.setTranslateZ(350);

		PointLight light3 = new PointLight(Color.ANTIQUEWHITE);
		light3.setMouseTransparent(true);
		light3.setTranslateZ(-350);

		PointLight light4 = new PointLight(Color.ANTIQUEWHITE);
		light4.setMouseTransparent(true);
		light4.setTranslateZ(350);

		axes = new TransformGizmo(1000);
		axes.showHandles(false);

		parent.getChildren().addAll(axes, plane, light1, light2, light3, light4,
				ambientLight);

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
