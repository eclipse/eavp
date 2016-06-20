/*******************************************************************************
 * Copyright (c) 2015 UT-Battelle, LLC.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   Tony McCrary (tmccrary@l33tlabs.com), Robert Smith, Kasper Gammeltoft
 *******************************************************************************/
package org.eclipse.eavp.viz.service.javafx.internal.scene.camera;

import javafx.embed.swt.FXCanvas;
import javafx.geometry.Point3D;
import javafx.scene.Camera;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;
import javafx.scene.transform.Affine;
import javafx.scene.transform.Rotate;

/**
 * A camera centered around and gazing towards the origin. The user can rotate
 * the camera about the origin by dragging the mouse and zoom through scrolling
 * the mouse wheel.
 * 
 * @author Robert Smith
 *
 */
public class CenteredCameraController extends BasicCameraController {

	/**
	 * The speed at which the camera turns when using the pitch/roll/yaw
	 * functions
	 */
	final private double ROTATION_SPEED = 15;

	/**
	 * The speed at which the camera will be moved for raise/strafe/thrust
	 */
	final private double SPEED = 50;

	private boolean dragStarted;

	/**
	 * The X rotation applied to the camera.
	 */
	private Rotate x;

	/**
	 * The Y rotation applied to the camera.
	 */
	private Rotate y;

	/**
	 * The Z rotation applied to the camera.
	 */
	private Rotate z;

	/**
	 * Flag indicating if the x rotation and the x-directional translations
	 * should be inverted when the user drags the mouse.
	 */
	private boolean shouldInvertX;

	/**
	 * Flag indicating if the y-directional translation should be inverted when
	 * the user drags the mouse.
	 */
	private boolean shouldInvertY;

	/**
	 * The default constructor.
	 * 
	 * @param camera
	 *            The camera this controller will manage.
	 * @param scene
	 *            The scene the camera is viewing.
	 * @param canvas
	 *            The FXCanvas containing the scene.
	 */
	public CenteredCameraController(Camera camera, Scene scene, FXCanvas canvas) {
		super(camera, scene, canvas);

		// Initialize the data members
		dragStarted = false;
		shouldInvertX = false;
		shouldInvertY = false;

		// Set the x axis rotation for the affine transformation
		x = new Rotate();
		x.setAxis(Rotate.X_AXIS);
		xform.getTransforms().add(x);

		// Set the y axis rotation for the affine transformation
		y = new Rotate();
		y.setAxis(Rotate.Y_AXIS);

		// Set the y axis rotation for the affine transformation
		z = new Rotate();
		z.setAxis(Rotate.Z_AXIS);

		// Apply the rotations and the affine to the camera
		xform.getTransforms().setAll(x, y, z, affine);

	}

	/**
	 * Zoom the camera towards/away from the center point
	 * 
	 * @param distance
	 *            The amount to zoom by. Positive values zoom forward, negative
	 *            values zoom backward.
	 */
	private void zoom(double distance) {

		// Get the current z position and modify it by the distance
		double z = camera.getTranslateZ();
		double newZ = z + distance;
		camera.setTranslateZ(newZ);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see org.eclipse.eavp.viz.service.javafx.internal.scene.camera.
	 * AbstractCameraController#handleMouseDragged(javafx.scene.input.
	 * MouseEvent)
	 */
	@Override
	public void handleMouseDragged(MouseEvent event) {

		// Replace the old mouse position
		mouseOldX = mousePosX;
		mouseOldY = mousePosY;

		// Get the current mouse position
		mousePosX = event.getSceneX();
		mousePosY = event.getSceneY();

		// If a drag action is in progress, adjust the camera angle
		if (dragStarted) {

			// Calculate the change in mouse position
			mouseDeltaX = (mousePosX - mouseOldX);
			mouseDeltaY = (mousePosY - mouseOldY);

			// Switches value of certain parameters if the camera is inverted.
			int invX = 1;
			int invY = 1;

			// If the primary button is held, then change the camera's position
			if (event.isPrimaryButtonDown()) {

				// If the camera is inverted (used to invert x coordinate
				// changes)
				if (shouldInvertX) {
					invX = -1;
				}

				// If the camera is inverted (used to invert the y-translation
				// changes)
				if (shouldInvertY) {
					invY = -1;
				}

				if (!event.isShiftDown()) {

					// If neither control nor shift are down, rotate about the
					// center
					if (!event.isControlDown()) {
						y.setAngle(y.getAngle() + mouseDeltaX);
						x.setAngle(x.getAngle() - mouseDeltaY * invX);
					}

					// If control is down, zoom
					else {
						zoom(-mouseDeltaY);
					}
				}

				// If shift is down, change the center and move the rotation
				// pivot
				// point
				else {
					// The translation
					affine.appendTranslation(-mouseDeltaX * invX, -mouseDeltaY *invY, 0);

					// Update the camera's rotation pivot point
					x.setPivotX(x.getPivotX() - mouseDeltaX * invX);
					x.setPivotY(x.getPivotY() - mouseDeltaY * invY);
					y.setPivotX(y.getPivotX() - mouseDeltaX * invX);
					y.setPivotY(y.getPivotY() - mouseDeltaY * invY);
				}
			}
		}

		// Ignore the first event of a drag action, as the mouse position will
		// not be properly initialized during it. See if the mouse inputs need
		// to be inverted
		else {
			dragStarted = true;

			// Get positive angle from the y rotation, 0-359
			double yRotation = y.getAngle() % 360;
			if (yRotation < 0) {
				yRotation += 360;
			}

			// Update the invert instance variable
			if (yRotation > 90 && yRotation < 270) {
				shouldInvertX = true;
			} else {
				shouldInvertX = false;
			}

			// Get positive angle from the y rotation, 0-359
			double xRotation = x.getAngle() % 360;
			if (xRotation < 0) {
				xRotation += 360;
			}

			// Update the invert instance variable
			if (xRotation > 90 && xRotation < 270) {
				shouldInvertY = true;
			} else {
				shouldInvertY = false;
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.eavp.viz.service.javafx.internal.scene.camera.
	 * AbstractCameraController#handleMouseReleased(javafx.scene.input.
	 * MouseEvent)
	 */
	@Override
	public void handleMouseReleased(MouseEvent event) {

		// End the current drag action, if any
		dragStarted = false;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see org.eclipse.eavp.viz.service.javafx.internal.scene.camera.
	 * AbstractCameraController#handleMouseScroll(javafx.scene.input.
	 * ScrollEvent)
	 */
	@Override
	public void handleMouseScroll(ScrollEvent event) {

		// Zoom by the amount of scrolling
		zoom(event.getDeltaY());

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.eavp.viz.service.javafx.internal.scene.camera.
	 * AbstractCameraController#reset()
	 */
	@Override
	public void reset() {

		// Handle the rotations if they exist
		if (x != null) {
			// Reset the camera to its initial angles
			x.setAngle(defaultX.getAngle());
			y.setAngle(defaultY.getAngle());
			z.setAngle(defaultZ.getAngle());
		}

		// Zoom the camera back to a default distance from the origin.
		affine = new Affine();
		affine.appendTranslation(0, 0, -2000);

		// If x, y, and z exist, apply them to the camera
		if (x != null) {
			xform.getTransforms().setAll(x, y, z, affine);
		}

		// Otherwise only apply the affine to the camera
		else {
			xform.getTransforms().setAll(affine);
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.eavp.viz.service.javafx.internal.scene.camera.
	 * ICameraController#pitchCamera(double)
	 */
	@Override
	public void pitchCamera(double radians) {
		affine.append(new Rotate(radians * 180 / Math.PI, new Point3D(1, 0, 0)));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.eavp.viz.service.javafx.internal.scene.camera.
	 * ICameraController#rollCamera(double)
	 */
	@Override
	public void rollCamera(double radians) {
		affine.append(new Rotate(radians * 180 / Math.PI, new Point3D(0, 0, 1)));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.eavp.viz.service.javafx.internal.scene.camera.
	 * ICameraController#raiseCamera(double)
	 */
	@Override
	public void raiseCamera(double distance) {
		affine.appendTranslation(0, -distance * SPEED, 0);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.eavp.viz.service.javafx.internal.scene.camera.
	 * ICameraController#strafeCamera(double)
	 */
	@Override
	public void strafeCamera(double distance) {
		affine.appendTranslation(distance * SPEED, 0, 0);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.eavp.viz.service.javafx.internal.scene.camera.
	 * ICameraController#thrustCamera(double)
	 */
	@Override
	public void thrustCamera(double distance) {
		affine.appendTranslation(0, 0, distance * SPEED);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.eavp.viz.service.javafx.internal.scene.camera.
	 * ICameraController#yawCamera(double)
	 */
	@Override
	public void yawCamera(double radians) {
		affine.append(new Rotate(radians * 180 / Math.PI, new Point3D(0, 1, 0)));
	}

	@Override
	protected void initCamera() {
		// TODO Auto-generated method stub

	}

	@Override
	public void handleKeyPressed(KeyEvent event) {
		// TODO Auto-generated method stub

	}

	@Override
	public void handleMousePressed(MouseEvent event) {
		// TODO Auto-generated method stub

	}

}
