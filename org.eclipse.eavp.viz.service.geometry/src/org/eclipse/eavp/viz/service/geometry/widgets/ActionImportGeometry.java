/*******************************************************************************
 * Copyright (c) 2016 UT-Battelle, LLC.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   Kasper Gammeltoft, Robert Smith
 *******************************************************************************/
package org.eclipse.eavp.viz.service.geometry.widgets;

import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.eavp.geometry.view.model.DisplayOption;
import org.eclipse.eavp.geometry.view.model.IRenderElement;
import org.eclipse.eavp.geometry.view.model.RenderObject;
import org.eclipse.eavp.viz.service.IRenderElementHolder;
import org.eclipse.eavp.viz.service.geometry.internal.GeometryImporterFactoryHolder;
import org.eclipse.eavp.viz.service.geometry.widgets.ShapeTreeContentProvider.BlankShape;
import org.eclipse.january.geometry.Geometry;
import org.eclipse.january.geometry.GeometryFactory;
import org.eclipse.january.geometry.INode;
import org.eclipse.january.geometry.Shape;
import org.eclipse.january.geometry.Union;
import org.eclipse.january.geometry.model.importer.IGeometryImporterService;
import org.eclipse.january.geometry.model.importer.IGeometryImporterServiceFactory;
import org.eclipse.january.geometry.xtext.mTL.Material;
import org.eclipse.january.geometry.xtext.mtlimport.MTLImporter;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.dialogs.ErrorDialog;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.ITreeSelection;
import org.eclipse.jface.viewers.TreePath;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.FileDialog;

import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;

public class ActionImportGeometry extends Action {

	/**
	 * <p>
	 * The current ShapeTreeViewer associated with the DeleteShape action
	 * </p>
	 * 
	 */
	private ShapeTreeView view;

	/**
	 * The image descriptor associated with the import action's icon
	 */
	private ImageDescriptor imageDescriptor;

	/**
	 * <p>
	 * Constructor for setting the current ShapeTreeViewer
	 * </p>
	 * 
	 * @param view
	 *            <p>
	 *            The current ShapeTreeView
	 *            </p>
	 */
	public ActionImportGeometry(ShapeTreeView view) {

		this.view = view;

		this.setText("Import file");

	}

	/**
	 * Returns the image descriptor associated with the import action's icon
	 * 
	 * @return The ImageDescriptor with the loaded delete.gif file
	 * @see org.eclipse.jface.action.Action#getImageDescriptor()
	 */
	@Override
	public ImageDescriptor getImageDescriptor() {
		return imageDescriptor;
	}

	/**
	 * <p>
	 * Runs this action
	 * </p>
	 * <p>
	 * Each action implementation must define the steps needed to carry out this
	 * action.
	 * </p>
	 * 
	 */
	@Override
	public void run() {

		// Create a new dialog and get the file to import
		FileDialog dialog = new FileDialog(view.getSite().getShell());
		String filePath = dialog.open();

		// Only import if a valid stl file
		if (filePath != null && ((filePath.toLowerCase(Locale.ENGLISH)
				.endsWith(".stl"))
				|| (filePath.toLowerCase(Locale.ENGLISH).endsWith(".obj") || (filePath.toLowerCase(Locale.ENGLISH).endsWith(".vtk"))))) {

			// Get current selection in shape tree view
			ITreeSelection selection = (ITreeSelection) view.treeViewer
					.getSelection();
			TreePath[] paths = selection.getPaths();

			// Silently fail if multiple selections
			if (paths.length > 1) {
				return;
			}

			// Get the root geometry
			Geometry geometry = (Geometry) view.treeViewer.getInput();

			// Fail if there is no root geometry
			if (geometry == null) {
				ErrorDialog.openError(Display.getCurrent().getActiveShell(),
						"Error Importing Geometry File", "File import failed.",
						new Status(IStatus.ERROR,
								"org.eclipse.viz.service.geometry.widgets",
								"No Geometry Editor found. Open a Geometry Editor before importing a file."));
				return;
			}
			// Import the geometry using the STL importer
			Path path = FileSystems.getDefault().getPath(filePath);
			Geometry imported = null;

			// The file extension
			String extension = filePath.toLowerCase(Locale.ENGLISH)
					.substring(filePath.lastIndexOf('.') + 1);

			// Check each IGeometryImporter, importing from the first that can
			// handle this file type
			IGeometryImporterServiceFactory factory = GeometryImporterFactoryHolder
					.getFactory();
			try {
				for (IGeometryImporterService service : factory.getAll()) {
					if (service.getSupportedExtensions().contains(extension)) {
						imported = service.importFile(path);
						break;
					}
				}
			} catch (Exception e) {

				// Create a dialog if an error occurred
				ErrorDialog.openError(Display.getCurrent().getActiveShell(),
						"Error Importing Geometry File", "File import failed.",
						new Status(IStatus.ERROR,
								"org.eclipse.viz.service.geometry.widgets",
								e.toString()));
				return;
			}

			// Try to find a parent shape to import into
			INode parentComplexShape = null;

			if (paths.length == 1) {

				// Get the selected object from the single selection

				Object selectedObject = paths[0].getLastSegment();

				if (selectedObject instanceof IRenderElement) {

					// Get the selected shape's parent

					IRenderElement selectedShape = (IRenderElement) selectedObject;
					parentComplexShape = selectedShape.getBase().getParent();
				} else if (selectedObject instanceof BlankShape) {

					// Get the selected blank shape's parent

					BlankShape selectedBlank = (BlankShape) selectedObject;
					parentComplexShape = selectedBlank.getParent().getBase();
				}

			}

			ArrayList<INode> addedNodes = new ArrayList<INode>();

			// If multiple shapes are in the imported file, collect them into a
			// union
			if (imported.getNodes().size() > 1) {

				Union importedUnion = GeometryFactory.eINSTANCE.createUnion();
				importedUnion.setName(filePath.substring(
						filePath.lastIndexOf(
								System.getProperty("file.separator")) + 1,
						filePath.lastIndexOf('.')));
				importedUnion.setId(0);

				// Add each node to the union
				synchronized (geometry) {
					for (int i = 0; i < imported.getNodes().size(); i++) {
						INode node = (INode) imported.getNodes().get(i).clone();
						importedUnion.addNode(node);
						addedNodes.add(node);
					}
				}

				// If no complex shape, import into the base geometry
				if (parentComplexShape == null) {
					synchronized (geometry) {
						geometry.addNode(importedUnion);

						// Operators should have their color display options off
						// by default,
						// to allow their childrens' colors to take precedence.
						List<DisplayOption> options = ((RenderObject) view
								.getHolder().getRender(importedUnion))
										.getDisplayOptions();
						for (DisplayOption option : options) {
							if ("Color".equals(option.getOptionGroup())) {
								option.setActive(false);
							}
						}

					}

					// Import into the parent shape
				} else {

					synchronized (geometry) {
						parentComplexShape.addNode(importedUnion);

						// Operators should have their color display options off
						// by default,
						// to allow their childrens' colors to take precedence.
						List<DisplayOption> options = ((RenderObject) view
								.getHolder().getRender(importedUnion))
										.getDisplayOptions();
						for (DisplayOption option : options) {
							if ("Color".equals(option.getOptionGroup())) {
								option.setActive(false);
							}
						}
					}

				}

			}

			// If there was only one node, it can be added directly
			else {

				// The single new node
				INode importedNode = imported.getNodes().get(0);
				importedNode.setName(filePath.substring(
						filePath.lastIndexOf(
								System.getProperty("file.separator")) + 1,
						filePath.lastIndexOf('.')));
				addedNodes.add(importedNode);

				// If no complex shape, import into the base geometry
				if (parentComplexShape == null) {
					synchronized (geometry) {
						geometry.addNode(importedNode);

					}

					// Import into the parent shape
				} else {

					synchronized (geometry) {
						parentComplexShape.addNode(importedNode);
					}

				}
			}

			view.treeViewer.refresh();

			// Create a map of the materials and their names from the specified
			// material source files
			Map<String, Material> materialMap = new HashMap<String, Material>();
			if (imported.getVertexSource() != null) {

				// Get the folder path
				String pathToFolder = filePath.substring(0, filePath
						.lastIndexOf(FileSystems.getDefault().getSeparator()));
				pathToFolder += FileSystems.getDefault().getSeparator();

				// Get the material files
				List<String> matFiles = imported.getVertexSource()
						.getMaterialFiles();

				// Can only handle .mtl material files right now
				for (String file : matFiles) {
					if (file.endsWith(".mtl")) {

						// Get the path, load with the MTLImporter
						Path pathToMat = FileSystems.getDefault()
								.getPath(pathToFolder + file);
						List<Material> newMaterials = new MTLImporter()
								.load(pathToMat);
						if (newMaterials != null && !newMaterials.isEmpty()) {
							// Put the loaded materials into the map
							for (Material mat : newMaterials) {
								materialMap.put(mat.getName(), mat);
							}
						}
					}
				}
			}

			// Set the materials on the newly added nodes
			setMaterials(addedNodes, materialMap);

		} else if (filePath != null) {

			// Display an error if an unreadable file was selected.
			ErrorDialog.openError(Display.getCurrent().getActiveShell(),
					"Error Importing Geometry File", "File import failed.",
					new Status(IStatus.ERROR,
							"org.eclipse.viz.service.geometry.widgets",
							filePath + " is not of a recognized file format."));
		}

	}

	/**
	 * Sets the specified nodes with the materials given in the map
	 * 
	 * @param nodes
	 *            The nodes to set the materials for
	 * @param materialMap
	 *            A mapping of the material name to the acctual material
	 */
	private void setMaterials(List<INode> nodes,
			Map<String, Material> materialMap) {
		if (nodes != null && !nodes.isEmpty()
				&& !materialMap.keySet().isEmpty()) {
			// Get the set of render elements from the view
			IRenderElementHolder holder = view.getHolder();
			for (INode node : nodes) {
				// Get the render of the new shape
				IRenderElement render = holder.getRender(node);

				if (render.getBase() instanceof Shape) {
					// Get the material
					PhongMaterial newMat = new PhongMaterial();
					Material readMat = materialMap
							.get(((Shape) render.getBase()).getMaterial()
									.getPhongMatName());
					if (readMat != null) {
						// Convert values to the phong material
						newMat.setDiffuseColor(
								convertColor(readMat.getDiffuse()));
						newMat.setSpecularColor(
								convertColor(readMat.getSpecular()));
						newMat.setSpecularPower(readMat.getSpecularExponent());
						render.setProperty("material", newMat);
					}
				}
			}
		}
	}

	/**
	 * Converts the given model color to a javaFX color that the geometry editor
	 * can use.
	 * 
	 * @param diffuse
	 *            The Color to convert
	 * @return Returns a javaFX color for the render
	 */
	private Color convertColor(
			org.eclipse.january.geometry.xtext.mTL.Color diffuse) {
		Color clr = Color.rgb((int) diffuse.getRed() * 255,
				(int) diffuse.getGreen() * 255, (int) diffuse.getBlue() * 255);
		return clr;
	}
}