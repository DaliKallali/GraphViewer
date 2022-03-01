package de.iils.dc43.graph.draw;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.Collections;
import java.util.Map;

import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.NotEnabledException;
import org.eclipse.core.commands.NotHandledException;
import org.eclipse.core.commands.common.NotDefinedException;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.Path;
import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.util.Diagnostician;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;
import org.eclipse.gmf.runtime.diagram.core.services.ViewService;
import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IEditorDescriptor;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorReference;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.part.FileEditorInput;
import org.jgrapht.ListenableGraph;
import org.jgrapht.graph.DefaultDirectedGraph;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.DefaultListenableGraph;
import org.jgrapht.io.Attribute;
import org.jgrapht.io.EdgeProvider;
import org.jgrapht.io.ExportException;
import org.jgrapht.io.GraphMLImporter;
import org.jgrapht.io.ImportException;
import org.jgrapht.io.VertexProvider;

import de.iils.dc43.graph.Edge;
import de.iils.dc43.graph.Graph;
import de.iils.dc43.graph.GraphFactory;
import de.iils.dc43.graph.GraphPackage;
import de.iils.dc43.graph.Node;
import de.iils.dc43.graph.diagram.edit.parts.GraphEditPart;
import de.iils.dc43.graph.diagram.part.GraphDiagramEditorPlugin;

public class ExecutionHelper {

	@Execute
	public void execute(IStructuredSelection selection, Shell shell) throws CoreException, IOException, ExportException,
			ImportException, ExecutionException, NotDefinedException, NotEnabledException, NotHandledException {

//		IWorkspaceRoot workspaceRoot = ResourcesPlugin.getWorkspace().getRoot();
//		System.out.println(workspaceRoot);
//		IProject[] projects = workspaceRoot.getProjects();
//		for (IProject iProject : projects) {
//			System.out.println(iProject.getName());

		if (selection == null || selection.isEmpty()) {
			System.out.println("Empty Selection");
			return;
		}

		IFile selectedFile = null;

		Object selectionElement = selection.getFirstElement();
		if (selectionElement instanceof IFile) {
			selectedFile = (IFile) selectionElement;
			if (selectedFile.getFileExtension().equals("graphml"))
				System.out.println("Selection is a graphml file");
			else {
				System.out.println("Selection is not a graphml file");
				return;
			}
		} else {
			System.out.println("Selection is not a file.");
			return;
		}
		IProject selectedProject = selectedFile.getProject();
		IFolder nodeImagesFolder = selectedProject.getFolder("nodeImages");
		System.out.println(nodeImagesFolder);
		if (!nodeImagesFolder.exists()) {
			nodeImagesFolder.create(true, true, null);
		} else {
			System.out.println(nodeImagesFolder + "folder alrady exists.");
		}

		System.out.println(nodeImagesFolder);
		System.out.println(nodeImagesFolder.exists());

		// Object firstGraph = selection.getFirstElement();

		// List<Node> nodes = VertexToIntegerMapping(Graph) ;
		// Iterator<Node> iterator = new DepthFirstIterator<>(graph);
		if (selection == null || selection.isEmpty()) {
			return;
		}

		// ICommandService commandService = (ICommandService)
		// getSite().getService(ICommandService.class);

		// Command generateCmd = commandService.getCommand("my_command_id");

		System.out.println(
				ResourcesPlugin.getWorkspace().getRoot().findFilesForLocationURI(selectedFile.getLocationURI()));
		IFile FiletoImport = selectedFile;
		System.out.println(FiletoImport.getFullPath().toOSString());
		System.out.println(selectedFile.getLocation());
		// File FiletoImport = new File(ResourcesPlugin.getWorkspace().getRoot()
		// .findFilesForLocationURI(selectedFile.getLocationURI()).toString());
		// File FiletoImport = new File(
		// selectedProject.getLocation() + "\\powerTrain
		// Graphs\\powerTrainConverterConceptN14.graphml");
		// File FiletoImport = new File(selectedProject.getLocation() +
		// "\\GraphML.txt");

		Reader reader = new FileReader(selectedFile.getLocation().toString());
		ListenableGraph<ImportNode, DefaultEdge> graphToImport = new DefaultListenableGraph<>(
				new DefaultDirectedGraph<>(DefaultEdge.class));

		VertexProvider<ImportNode> vertexProvider = new ImportNodeVertexProvider();

//		VertexProvider<String> vertexProvider = (label, attributes) -> (label + " "
//				+ attributes.get("EnergyConverter::pictogram[String]") + " " + attributes.get("label"));

		EdgeProvider<ImportNode, DefaultEdge> edgeProvider = (from, to, label, attributes) -> new DefaultEdge();

		GraphMLImporter<ImportNode, DefaultEdge> importer1 = new GraphMLImporter<>(vertexProvider, edgeProvider);
		importer1.setSchemaValidation(false);
		importer1.importGraph(graphToImport, reader);
		// outputMy.print(theGraph.getNodes().get(0));
		reader.close();

		System.out.println(graphToImport.toString());
		System.out.println("graph imported");
		GraphPackage.eINSTANCE.eClass();
		Graph outputGraph = GraphFactory.eINSTANCE.createGraph();
		String fileNameWithoutExt = selectedFile.getName().replaceFirst("[.][^.]+$", "");
		outputGraph.setName(fileNameWithoutExt);

		for (ImportNode importedNode : graphToImport.vertexSet()) {
			Node ecoreNode = GraphFactory.eINSTANCE.createNode();
			Map<String, Attribute> importedNodeAttributes = importedNode.getAttributes();
			String image = "";
			for (String key : importedNodeAttributes.keySet()) {
				System.out.println(key + ": " + importedNodeAttributes.get(key).getValue() + " as "
						+ importedNodeAttributes.get(key).getType());
				if ("EnergyConverter::pictogram[String]".equals(key)) {

				}
			}

			ecoreNode.setImage("default.png");

			if (importedNodeAttributes.get("EnergyConverter::pictogram[String]") != null) {
				image = importedNodeAttributes.get("EnergyConverter::pictogram[String]").getValue()
						.replace("pictograms/", "");

				System.out.println("pictogram: " + image);
			}

			if (image == "" || image == null) {
				if (importedNodeAttributes.get("label") != null) {
					image = importedNodeAttributes.get("label") + ".png";
					System.out.println("label: " + image);
				}
			}

			if (image != "" && image != null) {
				ecoreNode.setImage(image);
			}

			// setPosition(node, x, y);

			ecoreNode.setId(importedNode.getId());

			if (importedNodeAttributes.get("x") != null) {
				String X = importedNodeAttributes.get("x").getValue();

				ecoreNode.setX(Float.valueOf(X));

			}

			if (importedNodeAttributes.get("x") != null) {
				String Y = importedNodeAttributes.get("y").getValue();

				ecoreNode.setY(Float.valueOf(Y));

			}

//			System.out.println(importedNodeAttributes);

//			if (!image.isEmpty() && image != null && !image.equals("null")) {
//
//				System.out.println(image);
//				ecoreNode.setImage(image);
//			} else if (importedNodeAttributes[2] != null) {
//				image = importedNodeAttributes[2] + ".png";
//				ecoreNode.setImage(image);
//			} else {

//			}

			outputGraph.getNodes().add(ecoreNode);
		}

		for (DefaultEdge edge : graphToImport.edgeSet()) {

			Edge newEdge = GraphFactory.eINSTANCE.createEdge();
			outputGraph.getEdges().add(newEdge);
			for (Node node : outputGraph.getNodes()) {
				if ((node.getId()).equals(graphToImport.getEdgeSource(edge).getId())) {
					newEdge.setSource(node);
				}

				if ((node.getId()).equals(graphToImport.getEdgeTarget(edge).getId())) {
					newEdge.setTarget(node);
				}

			}
			// newEdge.setId(" ");
			newEdge.setId(graphToImport.getEdgeSource(edge).getId() + "->" + graphToImport.getEdgeTarget(edge).getId());
			// newEdge.setId(Integer.toString(j-1)+" -> "+Integer.toString(j));

		}

		IFile newImportedGraphFile = selectedProject.getFile(selectedFile.getName() + ".graph");
		Resource.Factory.Registry reg1 = Resource.Factory.Registry.INSTANCE;
		Map<String, Object> mapExtensionToFactory = reg1.getExtensionToFactoryMap();
		mapExtensionToFactory.put("graph", new XMIResourceFactoryImpl());
		ResourceSet resSet1 = new ResourceSetImpl();
		// Resource resource1 = resSet1.createResource(
		// URI.createFileURI(selectedProject.getLocation() + "\\" +
		// newImportedGraphFile.getName()));
		Resource resource1 = resSet1.createResource(URI.createFileURI(newImportedGraphFile.getLocation().toString()));
		resource1.getContents().add(outputGraph);

		resource1.save(Collections.EMPTY_MAP);

		selectedProject.refreshLocal(1, null);

		IWorkbenchPage activePage = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();
		IEditorDescriptor desc = PlatformUI.getWorkbench().getEditorRegistry()
				.getDefaultEditor(newImportedGraphFile.getName());
		FileEditorInput fileEditorInput = new FileEditorInput(newImportedGraphFile);
		activePage.openEditor(fileEditorInput, desc.getId());
		IEditorReference editorReference = (IEditorReference) PlatformUI.getWorkbench().getActiveWorkbenchWindow()
				.getActivePage().getActivePartReference();
		IEditorInput editorInput = editorReference.getEditorInput();

		Diagnostic validate2 = Diagnostician.INSTANCE.validate(outputGraph);
		System.err.println(Diagnostic.ERROR + "/" + validate2.getSeverity());

		/*
		 * if (AdapterFactoryEditingDomain.getEditingDomainFor(theGraph) != null) { for
		 * (Resource r : _editor.getEditingDomain().getResourceSet().getResources())
		 * r.getContents().add(theGraph); }
		 */
		/*
		 * IHandlerService handlerService = (IHandlerService)
		 * getSite().getService(IHandlerService.class); try {
		 * handlerService.executeCommand("add.command", null); } catch (Exception ex) {
		 * throw new RuntimeException("add.command not found"); // Give message }
		 */

		// Command command =
		// commandService.getCommand("de.iils.dc43.graph.diagram.InitDiagramAction");

		// check if the command is defined
		// System.out.println(command.isDefined());
		// if (command.isDefined()) {
		// prepare execution of command
		// ParameterizedCommand cmd =
		// commandService.createCommand("de.iils.dc43.graph.diagram.InitDiagramAction",
		// }
		// }
//		IHandlerService ihandlerService = (IHandlerService) ((IServiceLocator) PlatformUI.getWorkbench())
//				.getService(IHandlerService.class);
//		ICommandService _commandService = (ICommandService) ((IServiceLocator) PlatformUI.getWorkbench())
//				.getService(ICommandService.class);
//		System.out.println(_commandService.getDefinedCommandIds());
//		Command command = _commandService.getCommand("de.iils.dc43.graph.diagram.InitDiagramAction");
		// Parameterization[] params = new Parameterization[] { new Parameterization(
		// command.getParameter(" "), "true") };

		// ParameterizedCommand parametrizedCommand = new ParameterizedCommand(command,
		// params);
//		System.out.println(command.isDefined());
//		if (command.isDefined())
//			ihandlerService.executeCommand("de.iils.dc43.graph.diagram.InitDiagramAction", null);
		/*
		 * try {
		 * 
		 * URI ModelURI =
		 * URI.createPlatformResourceURI(newImportedGraphFile.getFullPath().toString(),
		 * false); URI newProcessURI = URI
		 * .createPlatformResourceURI(newImportedGraphFile.getFullPath().toString() +
		 * ".graph_diagram", false); // Resource resource =
		 * resourceSet.getResource(ModelURI, true); // diagramRoot = (EObject)
		 * resource.getContents().get(0);
		 * 
		 * Resource diagram = GraphDiagramEditorUtil.createDiagram(newProcessURI,
		 * ModelURI, new NullProgressMonitor());
		 * 
		 * if (diagram != null) { try { GraphDiagramEditorUtil.openDiagram(diagram); }
		 * catch (PartInitException e) {
		 * ErrorDialog.openError(Display.getDefault().getActiveShell(),
		 * "Open New Diagram", null, e.getStatus()); } }
		 * 
		 * return; } catch (Exception ex) { ex.printStackTrace();
		 * 
		 * }
		 * //---------------------------------------------------------------------------
		 * --- URI domainModelURI =
		 * URI.createPlatformResourceURI(newImportedGraphFile.getFullPath().toString(),
		 * true);
		 * 
		 * TransactionalEditingDomain editingDomain =
		 * GMFEditingDomainFactory.INSTANCE.createEditingDomain(); ResourceSet
		 * resourceSet = editingDomain.getResourceSet(); EObject diagramRoot = null;
		 * 
		 * Resource resource = resourceSet.getResource(domainModelURI, true);
		 * diagramRoot = (EObject) resource.getContents().get(0);
		 * 
		 * Wizard wizard = new GraphNewDiagramFileWizard(domainModelURI, diagramRoot,
		 * editingDomain);
		 * 
		 * // Resource diagram = GraphDiagramEditorUtil.createDiagram(newProcessURI, //
		 * ModelURI, new NullProgressMonitor());
		 * wizard.setWindowTitle(NLS.bind(Messages.InitDiagramFile_WizardTitle,
		 * GraphEditPart.MODEL_ID)); GraphDiagramEditorUtil.runWizard(shell, wizard,
		 * "InitDiagramFile"); // $NON-NLS-1$
		 */
		// --------------------------------------------------------------------------------

//		URI uri = URI.createFileURI(newImportedGraphFile.getFullPath().toString());
//		System.out.println(uri.toString());
//
//		Resource resource = new GMFResource(uri);
//
//		resource.getContents().add(outputGraph);
//
//		Map<String, String> options = new HashMap<>();
//		options.put(XMIResource.OPTION_ENCODING, "UTF-8"); //$NON-NLS-1$
//		resource.save(options);
//		
		Diagram diagram = ViewService.createDiagram(outputGraph,

				GraphEditPart.MODEL_ID, GraphDiagramEditorPlugin.DIAGRAM_PREFERENCES_HINT);

		diagram.setElement(outputGraph);
		diagram.setName(outputGraph.getName());
		// TransactionalEditingDomain editingDomain = //
		// GMFEditingDomainFactory.INSTANCE.createEditingDomain();

		// ResourceSet rSet = resource.getResourceSet(); // EObject diagramRoot = null;
		ResourceSet rSet = new ResourceSetImpl();
		// EditingDomain domain = //
		// AdapterFactoryEditingDomain.getEditingDomainFor(diagram);

		// ResourceSet rSet = domain.getResourceSet();

		Resource diagramResource = rSet
				.createResource(URI.createFileURI(newImportedGraphFile.getFullPath().toString() + "_diagram"));

		diagramResource.getContents().add(diagram);

		diagramResource.save(Collections.EMPTY_MAP);

		// resource.save(Collections.EMPTY_MAP);
		// --------------------------------------------------------------------------------------

//		ResourceSet resourceSet = new ResourceSetImpl();
//		Diagram diagram = NotationFactory.eINSTANCE.createDiagram();
//		diagram.setMeasurementUnit(MeasurementUnit.PIXEL_LITERAL);
//		diagram.getStyles().add(NotationFactory.eINSTANCE.createDiagramStyle());
//		diagram.setElement(outputGraph); // your EObject that should be referenced to this diagram (probably an ecore
//											// file)
//		diagram.setType("Ecore");
//		URI diagUri = URI.createFileURI(newImportedGraphFile.getLocation().toOSString() + ".graph_diagram");
//		System.err.println(newImportedGraphFile.getLocation().toOSString() + ".graph_diagram");
//		Resource diagramResource = resourceSet.createResource(diagUri);
//		diagramResource.getContents().add(diagram);
//		diagramResource.save(null);

		selectedProject.refreshLocal(1, null);
		// MessageDialog.openInformation(shell, "Done", "Graph successfully imported!");

		String path = diagramResource.getURI().toString();
		IResource workspaceResource = ResourcesPlugin.getWorkspace().getRoot().findMember(new Path(path));

// IDE.openEditor(activePage, (IFile) workspaceResource, GraphDiagramEditor.ID);
//		ElkLayout elkLayout = new ElkLayout();
//
//		elkLayout.graphmlElkLayout(diagramResource);
		System.out.println("done");
	}
}
