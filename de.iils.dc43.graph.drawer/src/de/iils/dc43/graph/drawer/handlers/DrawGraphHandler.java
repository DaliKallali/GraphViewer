package de.iils.dc43.graph.drawer.handlers;

/** <b>Warning</b> : 
  As explained in <a href="http://wiki.eclipse.org/Eclipse4/RCP/FAQ#Why_aren.27t_my_handler_fields_being_re-injected.3F">this wiki page</a>, it is not recommended to define @Inject fields in a handler. <br/><br/>
  <b>Inject the values in the @Execute methods</b>
*/

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.Collections;
import java.util.Map;

import org.eclipse.core.commands.Command;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.NotEnabledException;
import org.eclipse.core.commands.NotHandledException;
import org.eclipse.core.commands.common.NotDefinedException;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.util.Diagnostician;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IEditorDescriptor;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorReference;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.commands.ICommandService;
import org.eclipse.ui.handlers.IHandlerService;
import org.eclipse.ui.part.FileEditorInput;
import org.eclipse.ui.services.IServiceLocator;
import org.jgrapht.ListenableGraph;
import org.jgrapht.graph.DefaultDirectedGraph;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.DefaultListenableGraph;
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

public class DrawGraphHandler {
	@Execute
	public void execute(IStructuredSelection selection, Shell shell) throws CoreException, IOException, ExportException,
			ImportException, ExecutionException, NotDefinedException, NotEnabledException, NotHandledException {

//			IWorkspaceRoot workspaceRoot = ResourcesPlugin.getWorkspace().getRoot();
//			System.out.println(workspaceRoot);
//			IProject[] projects = workspaceRoot.getProjects();
//			for (IProject iProject : projects) {
//				System.out.println(iProject.getName());

		if (selection == null || selection.isEmpty()) {
			System.out.println("Empty Selection");
			return;
		}

		IFile selectedFile = null;

		Object selectionElement = selection.getFirstElement();
		if (selectionElement instanceof IFile) {
			selectedFile = (IFile) selectionElement;
			if (selectedFile.getFileExtension() == "graphml")
				System.out.println("Selection is a graphml file");
			else
				System.out.println("Selection is not a graphml file");
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

		MessageDialog.openInformation(shell, "Done", "Graph successfully added!");

		File FiletoImport = new File(
				selectedProject.getLocation() + "\\powerTrain Graphs\\powerTrainConverterConceptN14.graphml");
		// File FiletoImport = new File(selectedProject.getLocation() +
		// "\\GraphML.txt");

		Reader reader = new FileReader(FiletoImport);
		ListenableGraph<String, DefaultEdge> graphToImport = new DefaultListenableGraph<>(
				new DefaultDirectedGraph<>(DefaultEdge.class));

		VertexProvider<String> vertexProvider = (label,
				attributes) -> (label + " " + attributes.get("EnergyConverter::pictogram[String]"));

		EdgeProvider<String, DefaultEdge> edgeProvider = (from, to, label, attributes) -> new DefaultEdge();

		GraphMLImporter<String, DefaultEdge> importer1 = new GraphMLImporter<>(vertexProvider, edgeProvider);
		importer1.setSchemaValidation(false);
		importer1.importGraph(graphToImport, reader);
		// outputMy.print(theGraph.getNodes().get(0));
		reader.close();

		System.out.println(graphToImport.toString());
		System.out.println("graph imported");
		GraphPackage.eINSTANCE.eClass();
		Graph outputGraph2 = GraphFactory.eINSTANCE.createGraph();

		outputGraph2.setName("theSecondGraph");
		String image = "";
		for (String importedNode : graphToImport.vertexSet()) {
			Node node = GraphFactory.eINSTANCE.createNode();
			String[] importedNodeAttributes = importedNode.split("\\ ");

			// setPosition(node, x, y);

			node.setId(importedNodeAttributes[0]);

			if (importedNodeAttributes[1] != null)
				image = importedNodeAttributes[1].replace("pictograms/", "");
			System.out.println(importedNodeAttributes[1]);

			if (!image.isEmpty() && image != null && !image.equals("null")) {

				System.out.println(image);
				node.setImage(image);
			} else {

				node.setImage("default.png");
			}

			outputGraph2.getNodes().add(node);
		}

		for (DefaultEdge edge : graphToImport.edgeSet()) {

			Edge newEdge = GraphFactory.eINSTANCE.createEdge();
			outputGraph2.getEdges().add(newEdge);
			for (Node node : outputGraph2.getNodes()) {
				if ((node.getId()).equals(graphToImport.getEdgeSource(edge).split("\\ ")[0]))
					newEdge.setSource(node);

				if ((node.getId()).equals(graphToImport.getEdgeTarget(edge).split("\\ ")[0]))
					newEdge.setTarget(node);

			}
			// newEdge.setId(" ");
			newEdge.setId(graphToImport.getEdgeSource(edge).split("\\ ")[0] + "->"
					+ graphToImport.getEdgeTarget(edge).split("\\ ")[0]);
			// newEdge.setId(Integer.toString(j-1)+" -> "+Integer.toString(j));

		}

		IFile newImportedGraphFile = selectedProject.getFile("newImportedGraphFile.graph");
		Resource.Factory.Registry reg1 = Resource.Factory.Registry.INSTANCE;
		Map<String, Object> m1 = reg1.getExtensionToFactoryMap();
		m1.put("graph", new XMIResourceFactoryImpl());
		ResourceSet resSet1 = new ResourceSetImpl();
		Resource resource1 = resSet1.createResource(
				URI.createFileURI(selectedProject.getLocation() + "\\" + newImportedGraphFile.getName()));
		resource1.getContents().add(outputGraph2);

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

		Diagnostic validate2 = Diagnostician.INSTANCE.validate(outputGraph2);
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
		IHandlerService ihandlerService = (IHandlerService) ((IServiceLocator) PlatformUI.getWorkbench())
				.getService(IHandlerService.class);
		ICommandService _commandService = (ICommandService) ((IServiceLocator) PlatformUI.getWorkbench())
				.getService(ICommandService.class);
		System.out.println(_commandService.getDefinedCommandIds());
		Command command = _commandService.getCommand("de.iils.dc43.graph.diagram.InitDiagramAction");
		// Parameterization[] params = new Parameterization[] { new Parameterization(
		// command.getParameter(" "), "true") };

		// ParameterizedCommand parametrizedCommand = new ParameterizedCommand(command,
		// params);
		System.out.println(command.isDefined());
		if (command.isDefined())
			ihandlerService.executeCommand("de.iils.dc43.graph.diagram.InitDiagramAction", null);
		selectedProject.refreshLocal(1, null);
		System.out.println("done");
	}

}
