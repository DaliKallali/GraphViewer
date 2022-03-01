package de.iils.dc43.graph.gef.editor;

import java.io.IOException;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.gef.DefaultEditDomain;
import org.eclipse.gef.palette.PaletteRoot;
import org.eclipse.gef.ui.parts.GraphicalEditorWithFlyoutPalette;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.IFileEditorInput;
import org.eclipse.ui.PartInitException;

import de.iils.dc43.graph.gef.editor.part.GraphEditPartFactoryGef;
import de.iils.dc43.graph.Graph;
import de.iils.dc43.graph.GraphPackage;

public class GraphGraphicalEditor extends GraphicalEditorWithFlyoutPalette {

	public GraphGraphicalEditor() {
		setEditDomain(new DefaultEditDomain(this));
	}

	private Resource graphResource;
	private Graph outputGraph;

	@Override
	public void init(IEditorSite site, IEditorInput input) throws PartInitException {
		super.init(site, input);

		GraphPackage.eINSTANCE.eClass(); // This initializes the GraphPackage singleton implementation.
		ResourceSet resourceSet = new ResourceSetImpl();
		if (input instanceof IFileEditorInput) {
			IFileEditorInput fileInput = (IFileEditorInput) input;
			IFile file = fileInput.getFile();
			graphResource = resourceSet.createResource(URI.createURI(file.getLocationURI().toString()));
			try {
				graphResource.load(null);
				outputGraph = (Graph) graphResource.getContents().get(0);
			} catch (IOException e) {
				// TODO do something smarter.
				e.printStackTrace();
				graphResource = null;
			}
		}
	}

	@Override
	protected void initializeGraphicalViewer() {
		super.initializeGraphicalViewer();
		getGraphicalViewer().setContents(outputGraph);
	}

	@Override
	protected void configureGraphicalViewer() {
		super.configureGraphicalViewer();
		getGraphicalViewer().setEditPartFactory(new GraphEditPartFactoryGef());
	}

	@Override
	protected PaletteRoot getPaletteRoot() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void doSave(IProgressMonitor monitor) {
		// TODO Auto-generated method stub

	}

}
