package org.eclipse.eavp.viz.service.geometry.widgets;

import java.nio.file.FileSystems;
import java.nio.file.Path;

import org.eclipse.january.geometry.Geometry;
import org.eclipse.january.geometry.GeometryFactory;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.ITreeSelection;
import org.eclipse.jface.viewers.TreePath;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.ui.internal.util.BundleUtility;
import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;

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

		// Load the delete.gif ImageDescriptor from the bundle's
		// `icons` directory

		//Bundle bundle = FrameworkUtil.getBundle(getClass());
		//URL imagePath = BundleUtility.find(bundle, "icons/import.gif");
		//imageDescriptor = ImageDescriptor.createFromURL(imagePath);

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
		
		FileDialog dialog = new FileDialog(view.getSite().getShell());
		
		String filePath = dialog.open();
		
		if (filePath != null && (filePath.endsWith(".stl"))) {
			ITreeSelection selection = (ITreeSelection) view.treeViewer.getSelection();
			TreePath[] paths = selection.getPaths();
			
			if (paths.length > 1) {
				return;
			}
			
			Geometry geometry = (Geometry) view.treeViewer.getInput();
			
			if (geometry == null) {
				return;
			}
			Path path = FileSystems.getDefault().getPath(filePath);
			Geometry imported = GeometryFactory.eINSTANCE.createSTLGeometryImporter().load(path);
			synchronized (geometry) {
				geometry.addNode(imported.getNodes().get(0));
			}
			
			view.treeViewer.refresh();
		}
		

	}
}