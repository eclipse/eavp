package org.eclipse.eavp.viz.service.geometry.widgets;

import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.eclipse.eavp.viz.service.IRenderElementHolder;
import org.eclipse.eavp.viz.service.geometry.widgets.ShapeTreeContentProvider.BlankShape;
import org.eclipse.january.geometry.Geometry;
import org.eclipse.january.geometry.GeometryFactory;
import org.eclipse.january.geometry.INode;
import org.eclipse.january.geometry.PolyShape;
import org.eclipse.january.geometry.xtext.mTL.Material;
import org.eclipse.january.geometry.xtext.mtlimport.MTLImporter;
import org.eclipse.january.geometry.xtext.obj.importer.OBJGeometryImporter;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.ITreeSelection;
import org.eclipse.jface.viewers.TreePath;
import org.eclipse.swt.widgets.FileDialog;

import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import model.IRenderElement;

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
		if (filePath != null && ((filePath.endsWith(".stl")) || (filePath.endsWith(".obj"))) ) {
			// Get current selection in shape tree view
			ITreeSelection selection = (ITreeSelection) view.treeViewer.getSelection();
			TreePath[] paths = selection.getPaths();
			
			// Silently fail if multiple selections
			if (paths.length > 1) {
				return;
			}
			
			// Get the root geometry
			Geometry geometry = (Geometry) view.treeViewer.getInput();
			
			// Silently fail if there is no root geometry
			if (geometry == null) {
				return;
			}
			// Import the geometry using the STL importer
			Path path = FileSystems.getDefault().getPath(filePath);
			Geometry imported = null;
			if (filePath.endsWith(".stl")) {
				imported = GeometryFactory.eINSTANCE.createSTLGeometryImporter().load(path);
			} else if (filePath.endsWith(".obj")) {
				imported = new OBJGeometryImporter().load(path);
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
			// If no complex shape, import into the base geometry
			if (parentComplexShape == null) {
				synchronized (geometry) {
					for(int i=0; i<imported.getNodes().size(); i++) {
						INode node = (INode) imported.getNodes().get(i).clone();
						geometry.addNode(node);
						addedNodes.add(node);
					}
				}
			
				view.treeViewer.refresh();
				
			// Import into the parent shape
			} else {
				
				synchronized (geometry) {
					for(int i=0; i<imported.getNodes().size(); i++) {
						INode node = (INode) imported.getNodes().get(i).clone();
						parentComplexShape.addNode(node);
						addedNodes.add(node);
					}
				}
			
				view.treeViewer.refresh();
			}
			
			// Create a map of the materials and their names from the specified 
			// material source files
			Map<String, Material> materialMap = null;
			if (imported.getVertexSource() != null) {
				// Get the folder path
				String pathToFolder = filePath.substring(0, 
						filePath.lastIndexOf(FileSystems.getDefault().getSeparator()));
				// Get the material files
				List<String> matFiles = 
						imported.getVertexSource().getMaterialFiles();
				// Can only handle .mtl material files right now
				for(String file : matFiles) {
					if (file.endsWith(".mtl")) {
						// Get the path, load with the MTLImporter
						Path pathToMat = FileSystems.getDefault().getPath(pathToFolder + file);
						List<Material> newMaterials = new MTLImporter().load(pathToMat);
						// Put the loaded materials into the map
						for(Material mat : newMaterials) {
							materialMap.put(mat.getName(), mat);
						}
					}
				}
			}
			
			// Set the materials on the newly added nodes
			setMaterials(addedNodes, materialMap);
			
		}
		
	}
	
	private void setMaterials(List<INode> nodes, Map<String, Material> materialMap) {
		if (nodes != null && !nodes.isEmpty() && materialMap != null) {
			// Get the set of render elements from the view
			IRenderElementHolder holder = view.getHolder();
			for(INode node : nodes) {
				// Get the render of the new shape
				IRenderElement render = holder.getRender(node);
				
				if (render.getBase() instanceof PolyShape) {
					// Get the material
					PhongMaterial newMat = new PhongMaterial();
					Material readMat = (Material)materialMap.get(
								((PolyShape) render.getBase()).getMaterial().getPhongMatName());
					// Convert values to the phong material
					newMat.setDiffuseColor(convertColor(readMat.getDiffuse()));
					newMat.setSpecularColor(convertColor(readMat.getSpecular()));
					newMat.setSpecularPower(readMat.getSpecularExponent());
					render.setProperty("material", newMat);
				}
			}
		}
	}
	
	private Color convertColor(org.eclipse.january.geometry.xtext.mTL.Color diffuse) {
		Color clr = Color.rgb((int)diffuse.getRed(), (int)diffuse.getGreen(), (int)diffuse.getBlue());
		return clr;
	}
}