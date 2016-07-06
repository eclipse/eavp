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
package org.eclipse.eavp.viz.service.javafx.geometry.plant.test;

import static org.junit.Assert.assertTrue;

import org.eclipse.eavp.viz.modeling.base.Representation;
import org.eclipse.eavp.viz.service.geometry.reactor.JunctionRefactor;
import org.eclipse.eavp.viz.service.geometry.reactor.JunctionController;
import org.eclipse.eavp.viz.service.geometry.reactor.PipeRefactor;
import org.eclipse.eavp.viz.service.geometry.reactor.ReactorMeshCategory;
import org.eclipse.eavp.viz.service.javafx.geometry.plant.FXJunctionView;
import org.eclipse.eavp.viz.service.javafx.geometry.plant.FXPipeController;
import org.eclipse.eavp.viz.service.javafx.geometry.plant.FXPipeView;
import org.junit.Test;

import javafx.scene.Group;
import javafx.scene.shape.DrawMode;
import javafx.scene.shape.Shape3D;

/**
 * A class to test the functionality of FXJunctionView
 * 
 * @author Robert Smith
 *
 */
public class FXJunctionViewTester {

	/**
	 * Test that FXJunctionViews are cloned correctly
	 */
	@Test
	public void checkClone() {

		// Create a cloned view and check that it is identical to the original
		JunctionRefactor mesh = new JunctionRefactor();
		FXJunctionView view = new FXJunctionView(mesh);
		Object clone = view.clone();

		// A cloned view is not necessarily equal to the original due to JavaFX
		// objects' equals implementations, so check that the clone is of the
		// proper type
		assertTrue(clone instanceof FXJunctionView);
	}

	/**
	 * Check that the junction is drawn in the right position to cover the ends
	 * of its pipes.
	 */
	@Test
	public void checkPosition() {

		// Create a view on a junction with no connecting pipes
		JunctionRefactor mesh = new JunctionRefactor();
		FXJunctionView view = new FXJunctionView(mesh);
		JunctionController junction = new JunctionController(mesh, view);

		// Check that the junction is centered at the origin by default
		assertTrue(view.getCenter()[0] == 0d);
		assertTrue(view.getCenter()[1] == 0d);
		assertTrue(view.getCenter()[2] == 0d);

		// Create a pipe
		PipeRefactor pipeMesh = new PipeRefactor();
		pipeMesh.setLength(100);
		pipeMesh.setInnerRadius(5);
		pipeMesh.setRadius(5);
		pipeMesh.setAxialSamples(3);
		FXPipeView pipeView = new FXPipeView(pipeMesh);
		FXPipeController pipe = new FXPipeController(pipeMesh, pipeView);

		// Add the pipe as input
		junction.addEntityToCategory(pipe, ReactorMeshCategory.INPUT);

		// The junction's center point
		double[] center = view.getCenter();

		// Since the pipe is an input to the junction, it will be centered about
		// the pipe's upper edge, at (0, 50, 0).
		assertTrue(Math.abs(center[0] - 0d) < 1);
		assertTrue(Math.abs(center[1] - 50d) < 1);
		assertTrue(Math.abs(center[2] - 0d) < 1);

		// Add the other end of the pipe to the junction, so that the junction
		// is completely enveloping the pipe
		junction.addEntityToCategory(pipe, ReactorMeshCategory.OUTPUT);

		// The junction should now be centered at the origin, as it is a
		// rectangular bounding box around the pipe which is also centered on
		// the origin
		center = view.getCenter();
		assertTrue(Math.abs(center[0] - 0d) < 1);
		assertTrue(Math.abs(center[1] - 0d) < 1);
		assertTrue(Math.abs(center[2] - 0d) < 1);

		// Set the junction to only have the pipe as output
		junction.removeEntity(pipe);
		junction.addEntityToCategory(pipe, ReactorMeshCategory.OUTPUT);

		// The junction should centered on the other side of the pipe, at (0,
		// -50, 0)
		center = view.getCenter();
		assertTrue(Math.abs(center[0] - 0d) < 1);
		assertTrue(Math.abs(center[1] - -50d) < 1);
		assertTrue(Math.abs(center[2] - 0d) < 1);

		// Rotate the pipe 90 degrees about the x axis.
		pipe.setRotation(Math.PI / 2, 0, 0);

		// The pipe should now be lying on its side, with the mouth at (0, 0,
		// -50)
		center = view.getCenter();
		assertTrue(Math.abs(center[0] - 0d) < 1);
		assertTrue(Math.abs(center[1] - 0d) < 1);
		assertTrue(Math.abs(center[2] - -50d) < 1);

		// Create a second pipe
		PipeRefactor pipeMesh2 = new PipeRefactor();
		pipeMesh2.setLength(100);
		pipeMesh2.setInnerRadius(5);
		pipeMesh2.setRadius(5);
		pipeMesh2.setAxialSamples(3);
		FXPipeView pipeView2 = new FXPipeView(pipeMesh2);
		FXPipeController pipe2 = new FXPipeController(pipeMesh2, pipeView2);

		// Set the pipe as input
		junction.addEntityToCategory(pipe2, ReactorMeshCategory.INPUT);

		// The junction is now covering two circles of radius 5, one centered
		// on (0, 50, 0) on the XZ plane and the other centered on (0, 0, 50) on
		// the XY plane. Thus the center should be at (0, 12.5, -12.5)
		center = view.getCenter();
		assertTrue(Math.abs(center[0] - 0) < 1);
		assertTrue(Math.abs(center[1] - 22.5d) < 1);
		assertTrue(Math.abs(center[2] - -22.5d) < 1);
	}

	/**
	 * Test that the view's representation is set to the opacity.
	 */
	@Test
	public void checkTransparency() {
		// Create a view on a junction with no connecting pipes
		JunctionRefactor mesh = new JunctionRefactor();
		FXJunctionView view = new FXJunctionView(mesh);
		JunctionController junction = new JunctionController(mesh, view);

		// The box should be solid by default
		Representation<Group> representation = junction.getRepresentation();
		assertTrue(((Shape3D) representation.getData().getChildren().get(0))
				.getOpacity() == 100d);

		// Set the junction to transparent mode and check that the box is drawn
		// correctly
		junction.setTransparentMode(true);
		representation = junction.getRepresentation();
		assertTrue(((Shape3D) representation.getData().getChildren().get(0))
				.getOpacity() == 0d);

		// Turn off transparent mode and check that the box has been returned to
		// normal
		junction.setTransparentMode(false);
		representation = junction.getRepresentation();
		assertTrue(((Shape3D) representation.getData().getChildren().get(0))
				.getOpacity() == 100d);
	}

	/**
	 * Test that the view's representation is set to the proper draw mode for
	 * wireframe drawing.
	 */
	@Test
	public void checkWireframe() {
		// Create a view on a junction with no connecting pipes
		JunctionRefactor mesh = new JunctionRefactor();
		FXJunctionView view = new FXJunctionView(mesh);
		JunctionController junction = new JunctionController(mesh, view);

		// The box should be solid by default
		Representation<Group> representation = junction.getRepresentation();
		assertTrue(((Shape3D) representation.getData().getChildren().get(0))
				.getDrawMode() == DrawMode.FILL);

		// Set the junction to wireframe mode and check that the box is drawn
		// correctly
		junction.setWireframeMode(true);
		representation = junction.getRepresentation();
		assertTrue(((Shape3D) representation.getData().getChildren().get(0))
				.getDrawMode() == DrawMode.LINE);

		// Turn off wireframe mode and check that the box has been returned to
		// normal
		junction.setWireframeMode(false);
		representation = junction.getRepresentation();
		assertTrue(((Shape3D) representation.getData().getChildren().get(0))
				.getDrawMode() == DrawMode.FILL);
	}

}
