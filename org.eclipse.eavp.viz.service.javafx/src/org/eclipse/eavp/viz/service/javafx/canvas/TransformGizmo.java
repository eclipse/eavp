/*******************************************************************************
 * Copyright (c) 2015 UT-Battelle, LLC.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   Tony McCrary (tmccrary@l33tlabs.com)
 *******************************************************************************/
package org.eclipse.eavp.viz.service.javafx.canvas;

import javafx.collections.ObservableList;
import javafx.scene.DepthTest;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Box;

/**
 *
 * @author Tony McCrary (tmccrary@l33tlabs.com)
 *
 */
public class TransformGizmo extends Group {

	/** */
	private Box handleX;
	private Box handleY;
	private Box handleZ;

	/** */
	private Box axisX;
	private Box axisY;
	private Box axisZ;

	/**
	 * The material for the x axis.
	 */
	PhongMaterial axisXMaterial;
	
	/**
	 * The material for the x axis.
	 */
	PhongMaterial axisYMaterial;
	
	/**
	 * The material for the x axis.
	 */
	PhongMaterial axisZMaterial;
	
	/**
	 * The size the axes will be displayed at.
	 */
	private double axisSize;
	
	/** */
	private float axisWidth = 2f;
	
	/**
	 * The current size to which the axes are scaled.
	 */
	private double currentSize;

	/** */
	private Node owner;

	/**
	 *
	 * @param axisSize
	 */
	public TransformGizmo(double axisSize) {
		super();

		if (axisSize > 0) {
			
			this.axisSize = axisSize;
			
			axisXMaterial = new PhongMaterial();
			axisXMaterial.setDiffuseColor(Color.RED);

			axisYMaterial = new PhongMaterial();
			axisYMaterial.setDiffuseColor(Color.GREEN);

			axisZMaterial = new PhongMaterial();
			axisZMaterial.setDiffuseColor(Color.BLUE);

			PhongMaterial handleMaterial = new PhongMaterial();
			handleMaterial.setDiffuseColor(Color.BLUE);

			setDepthTest(DepthTest.DISABLE);
			
			createAxes(axisSize, axisWidth);

			handleX = new Box(15, 15, 15);
			handleX.setDepthTest(DepthTest.DISABLE);
			handleX.setMaterial(handleMaterial);
			handleX.setTranslateX(axisSize);
			handleX.setTranslateY(0);
			handleX.setTranslateZ(0);
			
			handleY = new Box(15, 15, 15);
			handleY.setDepthTest(DepthTest.DISABLE);
			handleY.setMaterial(handleMaterial);
			handleY.setTranslateX(0);
			handleY.setTranslateY(-axisSize);
			handleY.setTranslateZ(0);

			handleZ = new Box(15, 15, 15);
			handleZ.setDepthTest(DepthTest.DISABLE);
			handleZ.setMaterial(handleMaterial);
			handleZ.setTranslateX(0);
			handleZ.setTranslateY(0);
			handleZ.setTranslateZ(axisSize);

			ObservableList<Node> children = getChildren();

			children.add(handleX);
			children.add(handleY);
			children.add(handleZ);
			
			//The gizmo will initially be drawn on a 1 to 1 scale
			currentSize = 1;
		}
	}
	
	/**
	 * Create axes with the given size.
	 * 
	 * @param length The length the axes should be, moving away from the origin
	 * @param width The size of the other two dimensions
	 */
	private void createAxes(double length, double width){
		
		ObservableList<Node> children = getChildren();
		children.remove(axisX);
		children.remove(axisY);
		children.remove(axisZ);
		
		axisX = new Box(length, width, width);
		axisX.setDepthTest(DepthTest.DISABLE);
		axisX.setMaterial(axisXMaterial);
		axisX.setTranslateX(length / 2f);
		axisX.setTranslateY(0);
		axisX.setTranslateZ(0);
		
		axisY = new Box(width, length, width);
		axisY.setDepthTest(DepthTest.DISABLE);
		axisY.setMaterial(axisYMaterial);
		axisY.setTranslateX(0);
		axisY.setTranslateY((length / 2f));
		axisY.setTranslateZ(0);
		
		axisZ = new Box(width, width, length);
		axisZ.setDepthTest(DepthTest.DISABLE);
		axisZ.setMaterial(axisZMaterial);
		axisZ.setTranslateX(0);
		axisZ.setTranslateY(0);
		axisZ.setTranslateZ(length / 2f);
		
		children.add(axisX);
		children.add(axisY);
		children.add(axisZ);
	}
	
	/**
	 * Get the current scale for the axes.
	 * 
	 * @return The ratio of the axes as they are currently drawn to their original size. 
	 */
	public double getScale(){
		return currentSize;
	}
	
	/**
	 * Set the size of the axes, relative to their original size.
	 * 
	 * @param size How long the axes will be. Width will be scaled down proportional to the ratio between size and the axes' original length. 
	 */
	public void setAxisSize(double size){
		
		//Save the new size
		currentSize = size;
		
		//Redraw the axes with the new scale
		createAxes(size * axisSize, axisWidth * size);
	}

	/**
	 *
	 * @param handles
	 */
	public void showHandles(boolean handles) {

		// Set the handles' visibility, if they exist
		if (handleX != null) {
			handleX.setVisible(handles);
			handleY.setVisible(handles);
			handleZ.setVisible(handles);
		}
	}

	public Node getOwner() {
		return owner;
	}

	public void setOwner(Node owner) {
		this.owner = owner;
	}
}
