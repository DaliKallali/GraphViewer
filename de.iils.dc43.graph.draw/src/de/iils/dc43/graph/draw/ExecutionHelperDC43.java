//package de.iils.dc43.graph.draw;
//
//import java.io.IOException;
//import java.util.Map;
//import java.util.Set;
//
//import org.eclipse.core.commands.ExecutionException;
//import org.eclipse.core.commands.NotEnabledException;
//import org.eclipse.core.commands.NotHandledException;
//import org.eclipse.core.commands.common.NotDefinedException;
//import org.eclipse.core.resources.IFile;
//import org.eclipse.core.resources.IFolder;
//import org.eclipse.core.runtime.CoreException;
//import org.eclipse.core.runtime.IProgressMonitor;
//import org.eclipse.e4.core.di.annotations.Execute;
//import org.eclipse.emf.common.util.Diagnostic;
//import org.eclipse.emf.common.util.EList;
//import org.eclipse.emf.common.util.URI;
//import org.eclipse.emf.ecore.EAttribute;
//import org.eclipse.emf.ecore.EClass;
//import org.eclipse.emf.ecore.EObject;
//import org.eclipse.emf.ecore.resource.Resource;
//import org.eclipse.emf.ecore.resource.ResourceSet;
//import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
//import org.eclipse.emf.ecore.util.Diagnostician;
//import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;
//import org.eclipse.gmf.runtime.diagram.core.services.ViewService;
//import org.eclipse.gmf.runtime.notation.Diagram;
//import org.jgrapht.io.ExportException;
//import org.jgrapht.io.ImportException;
//
//import com.google.common.collect.BiMap;
//import com.google.common.collect.HashBiMap;
//
//import de.iils.dc43.core.IDC43Project;
//import de.iils.dc43.core.graph.UnmodifiableDirectedGraph;
//import de.iils.dc43.graph.Edge;
//import de.iils.dc43.graph.Graph;
//import de.iils.dc43.graph.GraphFactory;
//import de.iils.dc43.graph.GraphPackage;
//import de.iils.dc43.graph.Node;
//import de.iils.dc43.graph.diagram.edit.parts.GraphEditPart;
//import de.iils.dc43.graph.diagram.part.GraphDiagramEditorPlugin;
//import de.iils.dc43.graph.diagram.part.GraphDiagramEditorUtil;
//
//public class ExecutionHelperDC43 {
//
//	@Execute
//	public void execute(IDC43Project project, IProgressMonitor monitor)
//			throws CoreException, IOException, ExportException, ImportException, ExecutionException,
//			NotDefinedException, NotEnabledException, NotHandledException {
//
//		IFolder nodeImagesFolder = project.getProject().getFolder("nodeImages");
//		System.out.println(nodeImagesFolder);
//		if (!nodeImagesFolder.exists()) {
//			nodeImagesFolder.create(true, true, null);
//		} else {
//			System.out.println(nodeImagesFolder + "folder already exists.");
//		}
//
//		GraphPackage.eINSTANCE.eClass();
//		Graph outputGraph = GraphFactory.eINSTANCE.createGraph();
//		String fileNameWithoutExtension = project.getName().replaceFirst("[.][^.]+$", "");
//		outputGraph.setName(fileNameWithoutExtension);
//
//		UnmodifiableDirectedGraph designGraph = project.getGraph().asJGraphT();
//
////		Set<EObject> nodes = project.getGraph().allInstances();
////		for (EObject Node : nodes) {
////			System.out.println(Node.eClass());
////			Node ecoreNode = GraphFactory.eINSTANCE.createNode();
////		}
//
//		Set<EObject> designGraphNodes = project.getGraph().allInstances();
////		for (EObject designGraphNode : designGraphNodes) {
////			System.out.println(designGraphNode.eClass().getEIDAttribute());
////		}
//		BiMap<EObject, Node> designGraphNodeToOutputNode = HashBiMap.create();
//
//		int i = 0;
//
//		Set<EObject> designGraphSet = designGraph.vertexSet();
//
////		for (EObject designGraphNode : designGraphSet) {
////			String className = designGraphNode.eClass().getName().toUpperCase();
////			if (className.equals("LAYOUTSETTINGS")) {
////				designGraph.removeVertex(designGraphNode);
////			}
////		}
////		Set<EObject> changedDesignGraphSet = designGraph.vertexSet();
//
//		for (EObject designGraphNode : designGraphSet) {
//
//			Node outputNode = GraphFactory.eINSTANCE.createNode();
//			designGraphNodeToOutputNode.put(designGraphNode, outputNode);
//
//			EList<EAttribute> eAtributes = designGraphNode.eClass().getEAllAttributes();
//
//			String image = "";
//			String id = "";
//			for (EAttribute eAttribute : eAtributes) {
//				if ("id".equals(eAttribute.getName())) {
////					System.out.println(eAttribute.getName());
////					System.out.println(eAttribute.getEAttributeType());
//					id = (String) designGraphNode.eGet(eAttribute);
//
//				}
//
//			}
//
//			// ecoreNode.setImage("default.png");
//			EClass x = designGraphNode.eClass();
//
//			if (designGraphNode.eClass() != null) {
//				image = designGraphNode.eClass().getName() + ".svg";
//
//			}
//
//			if (image == "" || image == null) {
//				image = "default.png";
//			}
//
//			if (image != "" && image != null) {
//				outputNode.setImage(image);
//			}
//
//			if (id != null && id != "") {
//				outputNode.setId(id);
//			} else {
//				outputNode.setId("n" + Integer.toString(i));
//			}
//			if (!designGraphNode.eClass().getName().toUpperCase().equals("LAYOUTSETTINGS")) {
//				outputGraph.getNodes().add(outputNode);
//				++i;
//			}
//		}
//		int j = 0;
//		for (de.iils.dc43.core.graph.Edge<EObject> designGraphEdge : designGraph.edgeSet()) {
//
//			EObject designGraphEdgeSource = designGraphEdge.getSource();
//			EObject designGraphEdgeTarget = designGraphEdge.getTarget();
//
//			Node outputSource = designGraphNodeToOutputNode.get(designGraphEdgeSource);
//			Node outputTarget = designGraphNodeToOutputNode.get(designGraphEdgeTarget);
//
//			Edge outputEdge = GraphFactory.eINSTANCE.createEdge();
//			outputGraph.getEdges().add(outputEdge);
//			outputEdge.setSource(outputSource);
//			outputEdge.setTarget(outputTarget);
//
//			outputEdge.setId("e" + Integer.toString(j));
////			newEdge.setId(((GraphObject) designGraph.getEdgeSource(edge)).getId() + "->"
////					+ ((GraphObject) designGraph.getEdgeTarget(edge)).getId());
//			// newEdge.setId(Integer.toString(j-1)+" -> "+Integer.toString(j));
//			++j;
//		}
//
//		IFile newExportedGraphFile = project.getProject().getFile(project.getProject().getName() + ".graph");
//		Resource.Factory.Registry reg1 = Resource.Factory.Registry.INSTANCE;
//		Map<String, Object> extensionToFactoryMap = reg1.getExtensionToFactoryMap();
//		extensionToFactoryMap.put("graph", new XMIResourceFactoryImpl());
//		ResourceSet resSet1 = new ResourceSetImpl();
//		// Resource resource1 = resSet1.createResource(
//		// URI.createFileURI(selectedProject.getLocation() + "\\" +
//		// newImportedGraphFile.getName()));
//		Resource resource1 = resSet1.createResource(URI.createFileURI(newExportedGraphFile.getLocation().toString()));
//		resource1.getContents().add(outputGraph);
//		Map<?, ?> saveOptions = GraphDiagramEditorUtil.getSaveOptions();
//		resource1.save(saveOptions);
//
//		project.getProject().refreshLocal(1, null);
//
////		IWorkbenchPage activePage = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();
////		IEditorDescriptor desc = PlatformUI.getWorkbench().getEditorRegistry()
////				.getDefaultEditor(newExportedGraphFile.getName());
////		FileEditorInput fileEditorInput = new FileEditorInput(newExportedGraphFile);
////		activePage.openEditor(fileEditorInput, desc.getId());
////		IEditorReference editorReference = (IEditorReference) PlatformUI.getWorkbench().getActiveWorkbenchWindow()
////				.getActivePage().getActivePartReference();
////		IEditorInput editorInput = editorReference.getEditorInput();
//
//		Diagnostic validate2 = Diagnostician.INSTANCE.validate(outputGraph);
//		System.err.println(Diagnostic.ERROR + "/" + validate2.getSeverity());
//
//		/*
//		 * try {
//		 * 
//		 * URI ModelURI =
//		 * URI.createPlatformResourceURI(newImportedGraphFile.getFullPath().toString(),
//		 * false); URI newProcessURI = URI
//		 * .createPlatformResourceURI(newImportedGraphFile.getFullPath().toString() +
//		 * ".graph_diagram", false); // Resource resource =
//		 * resourceSet.getResource(ModelURI, true); // diagramRoot = (EObject)
//		 * resource.getContents().get(0);
//		 * 
//		 * Resource diagram = GraphDiagramEditorUtil.createDiagram(newProcessURI,
//		 * ModelURI, new NullProgressMonitor());
//		 * 
//		 * if (diagram != null) { try { GraphDiagramEditorUtil.openDiagram(diagram); }
//		 * catch (PartInitException e) {
//		 * ErrorDialog.openError(Display.getDefault().getActiveShell(),
//		 * "Open New Diagram", null, e.getStatus()); } }
//		 * 
//		 * return; } catch (Exception ex) { ex.printStackTrace();
//		 * 
//		 * }
//		 * //---------------------------------------------------------------------------
//		 * --- URI domainModelURI =
//		 * URI.createPlatformResourceURI(newImportedGraphFile.getFullPath().toString(),
//		 * true);
//		 * 
//		 * TransactionalEditingDomain editingDomain =
//		 * GMFEditingDomainFactory.INSTANCE.createEditingDomain(); ResourceSet
//		 * resourceSet = editingDomain.getResourceSet(); EObject diagramRoot = null;
//		 * 
//		 * Resource resource = resourceSet.getResource(domainModelURI, true);
//		 * diagramRoot = (EObject) resource.getContents().get(0);
//		 * 
//		 * Wizard wizard = new GraphNewDiagramFileWizard(domainModelURI, diagramRoot,
//		 * editingDomain);
//		 * 
//		 * // Resource diagram = GraphDiagramEditorUtil.createDiagram(newProcessURI, //
//		 * ModelURI, new NullProgressMonitor());
//		 * wizard.setWindowTitle(NLS.bind(Messages.InitDiagramFile_WizardTitle,
//		 * GraphEditPart.MODEL_ID)); GraphDiagramEditorUtil.runWizard(shell, wizard,
//		 * "InitDiagramFile"); // $NON-NLS-1$
//		 */
//		// --------------------------------------------------------------------------------
//
//		URI uri = URI.createFileURI(newExportedGraphFile.getFullPath().toString());
//		System.out.println(uri.toString());
//
////		Resource resource = new GMFResource(uri);
////
////		resource.getContents().add(outputGraph);
////
////		Map<String, String> options = new HashMap<String, String>();
////		options.put(XMIResource.OPTION_ENCODING, "UTF-8"); //$NON-NLS-1$
////		resource.save(options);
//		Diagram diagram = ViewService.createDiagram(outputGraph,
//
//				GraphEditPart.MODEL_ID, GraphDiagramEditorPlugin.DIAGRAM_PREFERENCES_HINT);
//
//		diagram.setElement(outputGraph);
//		diagram.setName(outputGraph.getName());
//
//		// TransactionalEditingDomain editingDomain = //
//		// GMFEditingDomainFactory.INSTANCE.createEditingDomain();
//
//		// ResourceSet rSet = resource.getResourceSet(); // EObject diagramRoot = null;
//		ResourceSet rSet = new ResourceSetImpl();
//		// EditingDomain domain = //
//		// AdapterFactoryEditingDomain.getEditingDomainFor(diagram);
//
//		// ResourceSet rSet = domain.getResourceSet();
//
//		Resource diagramResource = rSet
//				.createResource(URI.createFileURI(newExportedGraphFile.getFullPath().toString() + "_diagram"));
//
//		diagramResource.getContents().add(diagram);
//
//		diagramResource.save(saveOptions);
//
//		// --------------------------------------------------------------------------------------
//
////		ResourceSet resourceSet = new ResourceSetImpl();
////		Diagram diagram = NotationFactory.eINSTANCE.createDiagram();
////		diagram.setMeasurementUnit(MeasurementUnit.PIXEL_LITERAL);
////		diagram.getStyles().add(NotationFactory.eINSTANCE.createDiagramStyle());
////		diagram.setElement(outputGraph); // your EObject that should be referenced to this diagram (probably an ecore
////											// file)
////		diagram.setType("Ecore");
////		URI diagUri = URI.createFileURI(newImportedGraphFile.getLocation().toOSString() + ".graph_diagram");
////		System.err.println(newImportedGraphFile.getLocation().toOSString() + ".graph_diagram");
////		Resource diagramResource = resourceSet.createResource(diagUri);
////		diagramResource.getContents().add(diagram);
////		diagramResource.save(null);
//
//		project.getProject().refreshLocal(1, null);
//
//		System.out.println("Graph successfully exported");
//
//		String x = project.getGraph().allInstances().getClass().getName();
//
////		ElkLayout elkLayout = new ElkLayout();
////		elkLayout.defaultElkLayout(diagramResource, project);
//
//	}
//}
