/*******************************************************************************
 * Copyright (c) 2016 UT-Battelle, LLC.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   Initial API and implementation and/or initial documentation - 
 *   Kasper Gammeltoft
 *******************************************************************************/
package org.eclipse.eavp.viz.service.geometry.widgets;

import org.eclipse.eavp.STLStandaloneSetup;
import org.eclipse.eavp.sTL.Model;
import org.eclipse.eavp.sTL.Triangle;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.jface.action.Action;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.xtext.resource.XtextResource;
import org.eclipse.xtext.resource.XtextResourceSet;

import com.google.inject.Injector;

/**
 * Opens a dialog from the Shape view, allowing the user to import a STL file
 * and view it in the Geometry editor.
 * 
 * @author Kasper Gammeltoft
 *
 */
public class ActionImportSTL extends Action {

	/**
	 * <p>
	 * The current ShapeTreeView
	 * </p>
	 * 
	 */
	private ShapeTreeView view;

	/**
	 * Initialize the import action with its respective shape tree view.
	 */
	public ActionImportSTL(ShapeTreeView view) {

		// Set the view and text
		this.view = view;
		
		this.setText("STL");

	}

	/**
	 * <p>
	 * Opens the import stl dialog and reads in the mesh data.
	 * </p>
	 */
	@Override
	public void run() {

		// Create the import dialog
		FileDialog dialog = new FileDialog(view.getSite().getShell());

		// Get the stl file path
		String absPath = dialog.open();

		if (absPath == null) {
			System.err.println("absPath null");
		} else {
		
			// If it is a valid file, load it in
			if (absPath.endsWith(".stl")) {
				loadSTLFile(absPath);
			}
		}

	}

	/**
	 * Loads the stl file at the specified filePath
	 * 
	 * @param filePath
	 *            Specifies the absolute file path of the stl file
	 * 
	 */
	private void loadSTLFile(String filePath) {

		// Create the stl standalone injector, get the resources and pull out
		// the EMF model from the file
		Injector injector = new STLStandaloneSetup().createInjectorAndDoEMFRegistration();
		XtextResourceSet resourceSet = injector.getInstance(XtextResourceSet.class);
		resourceSet.addLoadOption(XtextResource.OPTION_RESOLVE_ALL, Boolean.TRUE);
		Resource resource = resourceSet.getResource(URI.createURI("file:"+filePath), true);

		// Get the model (it is assumed to be the first element from the grammar)
		Model model = (Model) resource.getContents().get(0);
		
		System.out.println("reading solid: "+model.getName());
		EList<Triangle> triangles = model.getTriangles();
		
		for(Triangle tri : triangles) {
			System.out.println("Triangle: n: "+ tri.getNormal().getVector().getX() +" " +
			tri.getNormal().getVector().getY()+" "+
			tri.getNormal().getVector().getZ()+" "+ "v1: " +
			tri.getVerticies().getV1().getX()+" "+
			tri.getVerticies().getV1().getY()+" "+
			tri.getVerticies().getV1().getZ()+" "+ "v2: " +
			tri.getVerticies().getV2().getX()+" "+
			tri.getVerticies().getV2().getY()+" "+
			tri.getVerticies().getV2().getZ()+" "+ "v3: " +
			tri.getVerticies().getV3().getX()+" "+
			tri.getVerticies().getV3().getY()+" "+
			tri.getVerticies().getV3().getZ()+" ");
		}
	}

}
