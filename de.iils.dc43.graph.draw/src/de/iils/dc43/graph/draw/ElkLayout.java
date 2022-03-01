package de.iils.dc43.graph.draw;

import java.io.IOException;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.elk.alg.layered.options.CrossingMinimizationStrategy;
import org.eclipse.elk.alg.layered.options.CycleBreakingStrategy;
import org.eclipse.elk.alg.layered.options.DirectionCongruency;
import org.eclipse.elk.alg.layered.options.EdgeLabelSideSelection;
import org.eclipse.elk.alg.layered.options.EdgeStraighteningStrategy;
import org.eclipse.elk.alg.layered.options.FixedAlignment;
import org.eclipse.elk.alg.layered.options.LayerConstraint;
import org.eclipse.elk.alg.layered.options.LayeredOptions;
import org.eclipse.elk.alg.layered.options.LayeringStrategy;
import org.eclipse.elk.alg.layered.options.NodeFlexibility;
import org.eclipse.elk.alg.layered.options.NodePlacementStrategy;
import org.eclipse.elk.alg.layered.options.WrappingStrategy;
import org.eclipse.elk.conn.gmf.GmfDiagramLayoutConnector;
import org.eclipse.elk.conn.gmf.GmfLayoutSetup;
import org.eclipse.elk.conn.gmf.GmfLayoutSetup.GmfLayoutModule;
import org.eclipse.elk.core.LayoutConfigurator;
import org.eclipse.elk.core.math.ElkPadding;
import org.eclipse.elk.core.options.CoreOptions;
import org.eclipse.elk.core.options.Direction;
import org.eclipse.elk.core.options.EdgeLabelPlacement;
import org.eclipse.elk.core.options.EdgeRouting;
import org.eclipse.elk.core.options.HierarchyHandling;
import org.eclipse.elk.core.options.NodeLabelPlacement;
import org.eclipse.elk.core.options.PortAlignment;
import org.eclipse.elk.core.options.SizeConstraint;
import org.eclipse.elk.core.options.SizeOptions;
import org.eclipse.elk.core.service.DiagramLayoutEngine;
import org.eclipse.elk.core.service.DiagramLayoutEngine.Parameters;
import org.eclipse.elk.graph.ElkNode;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.gef.EditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.DiagramEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ShapeNodeEditPart;
import org.eclipse.gmf.runtime.diagram.ui.parts.DiagramEditor;
import org.eclipse.jdt.ui.IPackagesViewPart;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionProvider;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TreePath;
import org.eclipse.jface.viewers.TreeSelection;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IPageLayout;
import org.eclipse.ui.ISelectionService;
import org.eclipse.ui.IViewPart;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.ide.IDE;
import org.eclipse.ui.part.ISetSelectionTarget;

import de.iils.dc43.core.IDC43Project;
import de.iils.dc43.graph.diagram.part.GraphDiagramEditor;

public class ElkLayout {

	public void defaultElkLayout(Resource diagramResource, IDC43Project project) throws PartInitException, IOException {
		String path = diagramResource.getURI().toString();
		IResource workspaceResource = ResourcesPlugin.getWorkspace().getRoot().findMember(new Path(path));
		// run(workspaceResource);
		IFolder nodeImagesFolder = workspaceResource.getProject().getFolder("nodeImages");
		IFile testFile = workspaceResource.getProject().getFile("test");
//		nodesImagesFolder.getFile()
		IWorkbenchPage page = PlatformUI.getWorkbench().getWorkbenchWindows()[0].getActivePage();
		// selectFileInCurrentView(diagramResource, page);
//		selectFileInCurrentView((Resource) workspaceResource, page);
//		selectFileInCurrentView(nodeImagesFolder, page);

		// if (workspaceResource instanceof IFile) {
//
//			Display.getDefault().syncExec(new Runnable() {
//				public void run() {
//					IWorkbench wb = PlatformUI.getWorkbench();
//					IWorkbenchWindow wbws[] = wb.getWorkbenchWindows();
//					IWorkbenchPage page = wbws[0].getActivePage();
//					IViewPart view = page.findView(IPageLayout.ID_PROJECT_EXPLORER);
//					ISelectionService selectionService = PlatformUI.getWorkbench().getWorkbenchWindows()[0]
//							.getSelectionService();
//					// selectionService.getSelection(path);
//					// ISelection selection = selectionService.getSelection();
////					if (view instanceof ISetSelectionTarget) {
////
////						StructuredSelection structuredSelection = new StructuredSelection(nodeImagesFolder);
////						ISelection selection = (ISelection) structuredSelection;
////						((ISetSelectionTarget) view).selectReveal(selection);
////
////					}
//
//					final ISelectionProvider selectionProvider = view.getSite().getSelectionProvider();
//					Object[] objectArray = { project, nodeImagesFolder };
//					TreePath path = new TreePath(objectArray);
//
//					TreeSelection selection = new TreeSelection(path);
//
//					if (selectionProvider instanceof Viewer) {
//						((Viewer) selectionProvider).setSelection((ISelection) selection, true);
//
//					}
//
//
//					System.out.println("checking...");
//				}
//
//			});
//
//		}

//		IViewPart view = page.findView(IPageLayout.ID_PROJECT_EXPLORER);
//		PackageExplorerPart.openInActivePerspective();
//		if (view instanceof PackageExplorerPart) {
//			PackageExplorerPart part = (PackageExplorerPart) view;
//			part.selectAndReveal(nodeImagesFolder);
//
//		}

		final IWorkbenchPart activePart = getActivePart();
		if (activePart != null && activePart instanceof IPackagesViewPart) {
			((IPackagesViewPart) activePart).selectAndReveal(project);
		}

		// selectRevealResource(nodeImagesFolder);

		selectTreeResource(nodeImagesFolder);

		System.out.println("checking Selection...");
		openDiagramEditor(workspaceResource, page);

		Set<EObject> designGraphNodes = project.getGraph().allInstances();

		Map<String, String> settingNameToValue = new HashMap<>();

		// String direction = "DOWN";

		settingNameToValue.put("LAYOUTNAME", "default");
		settingNameToValue.put("DIRECTION", "DOWN");
		settingNameToValue.put("ALGORITHM", "org.eclipse.elk.layered");
		settingNameToValue.put("EDGE_ROUTING", "ORTHOGONAL");
		settingNameToValue.put("SPACING_NODE_NODE", "40");
		settingNameToValue.put("ZOOM_TO_FIT", "true");
		settingNameToValue.put("ALIGNMENT", "CENTER");
		settingNameToValue.put("UNNECESSARY_BENDPOINTS", "false");
		settingNameToValue.put("MERGE_EDGES", "true");
		settingNameToValue.put("HYPERNODE", "false");
		settingNameToValue.put("COMPACTION_CONNECTED_COMPONENTS", "false");
		settingNameToValue.put("FEEDBACK_EDGES", "false");

		settingNameToValue.put("HIERARCHY_HANDLING", "INHERIT");
		settingNameToValue.put("HIGH_DEGREE_NODES_TREATMENT", "true");
		settingNameToValue.put("HIGH_DEGREE_NODES_TREE_HEIGHT", "100");
		settingNameToValue.put("LAYERING_STRATEGY", "INTERACTIVE");// COFFMAN_GRAHAM//INTERACTIVE
		settingNameToValue.put("MERGE_HIERARCHY_EDGES", "true");
		settingNameToValue.put("CROSSING_MINIMIZATION_STRATEGY", "LAYER_SWEEP");
		settingNameToValue.put("NODE_PLACEMENT_STRATEGY", "BRANDES_KOEPF");// BRANDES_KOEPF//NETWORK_SIMPLEX
		settingNameToValue.put("NODE_PLACEMENT_FAVOR_STRAIGHT_EDGES", "true");

		String settings = "";
		String layoutName = "";
		for (EObject designGraphNode : designGraphNodes) {
			Class<? extends EObject> nodeClass = designGraphNode.getClass();
			String name = designGraphNode.eClass().getName();

			if (name.toUpperCase().equals("LAYOUTSETTINGS")) {

				EList<EAttribute> atributes = designGraphNode.eClass().getEAllAttributes();

				for (EAttribute eAttribute : atributes) {
					if ("SETTINGS".equals(eAttribute.getName().toUpperCase())) {
						System.out.println(eAttribute.getName());
						System.out.println(eAttribute.getEAttributeType());
						settings = (String) designGraphNode.eGet(eAttribute);
						System.out.println(settings);

//						EStructuralFeature structFeature = designGraphNode.eClass().getEStructuralFeature("PowerDrive");
//						Object y = designGraphNode.eGet(structFeature);
					}
					if ("LAYOUTNAME".equals(eAttribute.getName().toUpperCase())) {
						System.out.println(eAttribute.getName());
						System.out.println(eAttribute.getEAttributeType());
						layoutName = (String) designGraphNode.eGet(eAttribute);
						System.out.println(layoutName);

//						EStructuralFeature structFeature = designGraphNode.eClass().getEStructuralFeature("PowerDrive");
//						Object y = designGraphNode.eGet(structFeature);
					}
				}

//				EStructuralFeature structFeature2 = designGraphNode.eClass().getEStructuralFeature("Direction");
//				Object direct = designGraphNode.eGet(structFeature2);
//				direction = direct.toString();

//				System.out.println(direct);
			}

//			String y = designGraphNode.getClass().getName();
//			if (y=="LayoutSettings") {}
		}

		String[] settingElemets = settings.split(";");
		if (settingElemets.length > 0) {
			for (String element : settingElemets) {
				String[] keyValues = element.split("=");
				if (keyValues.length > 1) {
					for (String keyValue : keyValues)
						settingNameToValue.put(keyValues[0].toUpperCase(), keyValues[1].toUpperCase());
				}
			}
		}
		if (layoutName != "") {
			settingNameToValue.put("LAYOUTNAME", layoutName);
		}
		System.out.println(settingNameToValue);

		switch (settingNameToValue.get("LAYOUTNAME").toUpperCase()) {
		case "FLOWSCHEMATIC":

			settingNameToValue.put("ALGORITHM", "org.eclipse.elk.layered");
			settingNameToValue.put("DIRECTION", "DOWN");
			settingNameToValue.put("EDGE_ROUTING", "ORTHOGONAL");
			settingNameToValue.put("SPACING_NODE_NODE", "10");
			settingNameToValue.put("ZOOM_TO_FIT", "true");
			settingNameToValue.put("ALIGNMENT", "CENTER");
			settingNameToValue.put("UNNECESSARY_BENDPOINTS", "false");

			break;

		case "POWERDRIVE":
			settingNameToValue.put("ALGORITHM", "org.eclipse.elk.layered");
			settingNameToValue.put("DIRECTION", "RIGHT");
			settingNameToValue.put("EDGE_ROUTING", "ORTHOGONAL");
			settingNameToValue.put("SPACING_NODE_NODE", "10");
			settingNameToValue.put("ZOOM_TO_FIT", "true");
			break;

		default:
			break;
		}

		System.out.println(settingNameToValue);

		// try {
//			IDE.openEditor(page, (IFile) workspaceResource, GraphDiagramEditor.ID);
//			// page.openEditor(new FileEditorInput((IFile) workspaceResource),
//			// GraphDiagramEditor.ID);
//		} catch (PartInitException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		// IWorkbenchPage page =
		// PlatformUI.getWorkbench().getWorkbenchWindows()[0].getActivePage();
		if (page != null) {

//			else {
//				(((IAdaptable) view).getAdapter(ISetSelectionTarget.class))
//						.selectReveal(new StructuredSelection("twoClasses.graph_diagram"));
//			}
			IEditorPart editorPart = page.getActiveEditor();
			IWorkbenchPart workbenchPart = page.getActivePart();
			DiagramEditor diagramEditor = getDiagramEditor(workbenchPart);

			// if (editor instanceof GraphDiagramEditor) {
			// GraphDiagramEditor graphicalEditor = (GraphDiagramEditor) editor;
			DiagramEditPart diagramEditPart = diagramEditor.getDiagramEditPart();

			EditPart editPart = getEditPart(diagramEditPart);

			List<EditPart> inspectList = editPart.getChildren();
			List<ShapeNodeEditPart> inspectSNEP = new ArrayList<ShapeNodeEditPart>();
			for (EditPart obj : inspectList) {

				ShapeNodeEditPart shapeNodeEditPart = getShapeNodeEditPart(obj);
				inspectSNEP.add(shapeNodeEditPart);
			}
			// ResourcesPlugin.getWorkspace().getRoot().getFile(diagramResource.getURI().path());
			// ViewService.getInstance().

			GmfLayoutSetup gmfLayoutSetup = new GmfLayoutSetup();

			GmfLayoutModule module = new GmfLayoutModule();
			boolean supp = gmfLayoutSetup.supports(diagramEditor);
			// if () {}
			// gmfLayoutSetup.createInjector(module);

			// IWorkbenchPart iWorkbenchPart =
			// PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage()
			// .getActivePart();
			// IWorkbenchPart iWorkbenchPart = diagramResource.getContents()

			// LayoutMapping layoutMapping = new LayoutMapping(diagram);
			GmfDiagramLayoutConnector gmfDiagramLayoutConnector = new GmfDiagramLayoutConnector();

			// GraphicalEditPart iGraphicalEditPart = diagramResource.getURI();
			// gmfDiagramLayoutConnector.getDiagramEditPart(editPart);
			// LayoutMapping layoutMapping =

//			IPropertyHolder propertySettings = null;
//			propertySettings.setProperty(CoreOptions.ALGORITHM, "org.eclipse.elk.layered");
			// gmfDiagramLayoutConnector.buildLayoutGraph(workbenchPart, diagramEditor);

			LayoutConfigurator layoutConfigurator = new LayoutConfigurator();

			layoutConfigurator.configure(ElkNode.class).setProperty(CoreOptions.ALGORITHM, "org.eclipse.elk.layered")

					.setProperty(CoreOptions.LAYOUT_ANCESTORS, true)
					// .setProperty(CoreOptions.PORT_ALIGNMENT_DEFAULT, PortAlignment.CENTER)
					.setProperty(CoreOptions.DIRECTION, Direction.valueOf(settingNameToValue.get("DIRECTION")))
					.setProperty(CoreOptions.EDGE_ROUTING, EdgeRouting.valueOf(settingNameToValue.get("EDGE_ROUTING")))
					.setProperty(CoreOptions.SPACING_NODE_NODE,
							Double.valueOf(settingNameToValue.get("SPACING_NODE_NODE")))
//			layoutConfigurator.configure(ElkNode.class).setProperty(LayeredOptions.SPACING_EDGE_NODE_BETWEEN_LAYERS,
//					20.0);Boolean.valueOf(settingNameToValue.get("ZOOM_TO_FIT"))

//					.setProperty(CoreOptions.PORT_CONSTRAINTS, PortConstraints.FIXED_SIDE)

					.setProperty(CoreOptions.ZOOM_TO_FIT, Boolean.valueOf(settingNameToValue.get("ZOOM_TO_FIT")))
					.setProperty(CoreOptions.ALIGNMENT,
							org.eclipse.elk.core.options.Alignment.valueOf(settingNameToValue.get("ALIGNMENT")))

					// .setProperty(LayeredOptions.LAYERING_STRATEGY,
					// LayeringStrategy.NETWORK_SIMPLEX)

					.setProperty(LayeredOptions.UNNECESSARY_BENDPOINTS,
							Boolean.valueOf(settingNameToValue.get("UNNECESSARY_BENDPOINTS")))// false
					.setProperty(LayeredOptions.MERGE_EDGES, Boolean.valueOf(settingNameToValue.get("MERGE_EDGES")))// true

					.setProperty(LayeredOptions.HYPERNODE, Boolean.valueOf(settingNameToValue.get("HYPERNODE")))// false
					// .setProperty(LayeredOptions.ASPECT_RATIO, 10.0)
					.setProperty(LayeredOptions.COMPACTION_CONNECTED_COMPONENTS,
							Boolean.valueOf(settingNameToValue.get("COMPACTION_CONNECTED_COMPONENTS")))// false
//					.setProperty(LayeredOptions.COMPACTION_POST_COMPACTION_STRATEGY,
//							GraphCompactionStrategy.LEFT_RIGHT_CONNECTION_LOCKING)
					.setProperty(LayeredOptions.EDGE_ROUTING_POLYLINE_SLOPED_EDGE_ZONE_WIDTH, 1.0)
					.setProperty(LayeredOptions.EDGE_THICKNESS, 1.0)
					.setProperty(LayeredOptions.FEEDBACK_EDGES,
							Boolean.valueOf(settingNameToValue.get("FEEDBACK_EDGES")))
					.setProperty(LayeredOptions.HIERARCHY_HANDLING,
							HierarchyHandling.valueOf(settingNameToValue.get("HIERARCHY_HANDLING")))

					.setProperty(LayeredOptions.HIGH_DEGREE_NODES_TREATMENT,
							Boolean.valueOf(settingNameToValue.get("HIGH_DEGREE_NODES_TREATMENT")))// true
					.setProperty(LayeredOptions.HIGH_DEGREE_NODES_TREE_HEIGHT,
							Integer.valueOf(settingNameToValue.get("HIGH_DEGREE_NODES_TREE_HEIGHT")))

//					.setProperty(LayeredOptions.LAYERING_NODE_PROMOTION_STRATEGY, NodePromotionStrategy.NO_BOUNDARY)
//					.setProperty(LayeredOptions.LAYERING_WIDE_NODES_ON_MULTIPLE_LAYERS, WideNodesStrategy.AGGRESSIVE)
//					
					.setProperty(LayeredOptions.LAYERING_STRATEGY,
							LayeringStrategy.valueOf(settingNameToValue.get("LAYERING_STRATEGY")))

					// .setProperty(LayeredOptions.LAYERING_LAYER_CONSTRAINT, LayerConstraint.NONE)

					.setProperty(LayeredOptions.MERGE_HIERARCHY_EDGES,
							Boolean.valueOf(settingNameToValue.get("MERGE_HIERARCHY_EDGES")))// true
					.setProperty(LayeredOptions.NODE_LABELS_PLACEMENT, NodeLabelPlacement.fixed())
					.setProperty(LayeredOptions.NODE_SIZE_OPTIONS, EnumSet.of(SizeOptions.SPACE_EFFICIENT_PORT_LABELS))
					.setProperty(LayeredOptions.NODE_LABELS_PADDING, new ElkPadding(10))
					.setProperty(LayeredOptions.NODE_PLACEMENT_BK_EDGE_STRAIGHTENING,
							EdgeStraighteningStrategy.IMPROVE_STRAIGHTNESS)
					.setProperty(LayeredOptions.NODE_PLACEMENT_BK_FIXED_ALIGNMENT, FixedAlignment.NONE)
					.setProperty(LayeredOptions.NORTH_OR_SOUTH_PORT, false)
					.setProperty(LayeredOptions.NODE_SIZE_CONSTRAINTS, SizeConstraint.minimumSize())
					.setProperty(LayeredOptions.PARTITIONING_ACTIVATE, true)
					.setProperty(LayeredOptions.PARTITIONING_PARTITION, 5)
					// .setProperty(LayeredOptions.SPACING_NODE_NODE_BETWEEN_LAYERS, 40d)
//					.setProperty(LayeredOptions.SPACING_NODE_NODE, 40d)
//					.setProperty(LayeredOptions.SPACING_EDGE_EDGE, 400d)
//					.setProperty(LayeredOptions.SPACING_EDGE_EDGE_BETWEEN_LAYERS, 200d)
//					.setProperty(LayeredOptions.SPACING_LABEL_NODE, 1d)
//					.setProperty(LayeredOptions.PORT_BORDER_OFFSET, 10d)
//					.setProperty(LayeredOptions.SPACING_COMPONENT_COMPONENT, 100d)
//					// .setProperty(LayeredOptions.SPACING_NODE_NODE, 0d)
					// .setProperty(LayeredOptions.PORT_SORTING_STRATEGY,
					// PortSortingStrategy.INPUT_ORDER)
					// .setProperty(LayeredOptions.COMPACTION_CONNECTED_COMPONENTS, true)
					.setProperty(LayeredOptions.CROSSING_MINIMIZATION_HIERARCHICAL_SWEEPINESS, -1.0)
					.setProperty(LayeredOptions.CROSSING_MINIMIZATION_STRATEGY,
							CrossingMinimizationStrategy
									.valueOf(settingNameToValue.get("CROSSING_MINIMIZATION_STRATEGY")))// LAYER_SWEEP
					// .setProperty(LayeredOptions.CROSSING_MINIMIZATION_GREEDY_SWITCH_TYPE,
					// GreedySwitchType.TWO_SIDED)
					// .setProperty(LayeredOptions.CONTENT_ALIGNMENT,
					// ContentAlignment.centerCenter())
					.setProperty(LayeredOptions.CYCLE_BREAKING_STRATEGY, CycleBreakingStrategy.GREEDY)
					.setProperty(LayeredOptions.NODE_PLACEMENT_STRATEGY,
							NodePlacementStrategy.valueOf(settingNameToValue.get("NODE_PLACEMENT_STRATEGY")))// BRANDES_KOEPF
					.setProperty(LayeredOptions.NODE_PLACEMENT_FAVOR_STRAIGHT_EDGES,
							Boolean.valueOf(settingNameToValue.get("NODE_PLACEMENT_FAVOR_STRAIGHT_EDGES")))// false
					.setProperty(LayeredOptions.DIRECTION_CONGRUENCY, DirectionCongruency.ROTATION)

//					.setProperty(CoreOptions.ANIMATE, true).setProperty(CoreOptions.LAYOUT_ANCESTORS, false)
//					.setProperty(CoreOptions.NODE_SIZE_MINIMUM, new KVector(100d, 50d))
//					.setProperty(CoreOptions.NODE_SIZE_CONSTRAINTS, SizeConstraint.fixed())
//					.setProperty(CoreOptions.HIERARCHY_HANDLING, HierarchyHandling.INCLUDE_CHILDREN)
//					.setProperty(CoreOptions.NODE_SIZE_OPTIONS, EnumSet.of(SizeOptions.DEFAULT_MINIMUM_SIZE))
//					.setProperty(CoreOptions.PROGRESS_BAR, true).setProperty(CoreOptions.ZOOM_TO_FIT, true)
//					.setProperty(CoreOptions.PADDING, new ElkPadding(24, 12, 12, 12))
//					.setProperty(CoreOptions.ALGORITHM, "org.eclipse.elk.layered.layered")
//					.setProperty(CoreOptions.VALIDATE_GRAPH, true)
//
//					.setProperty(LayeredOptions.SPACING_NODE_NODE, 10d)
//					.setProperty(LayeredOptions.DIRECTION, Direction.RIGHT)
//					.setProperty(LayeredOptions.EDGE_THICKNESS, 10d)
//					.setProperty(LayeredOptions.LAYERING_STRATEGY, LayeringStrategy.NETWORK_SIMPLEX)
//					.setProperty(LayeredOptions.COMPACTION_CONNECTED_COMPONENTS, true)
//					.setProperty(LayeredOptions.EDGE_ROUTING, EdgeRouting.ORTHOGONAL)
//					.setProperty(LayeredOptions.PADDING, new ElkPadding(24, 12, 12, 12))
//					.setProperty(LayeredOptions.LAYERING_NODE_PROMOTION_STRATEGY, NodePromotionStrategy.NONE)
//					.setProperty(LayeredOptions.NODE_PLACEMENT_NETWORK_SIMPLEX_NODE_FLEXIBILITY, NodeFlexibility.NONE)
//					.setProperty(LayeredOptions.NODE_SIZE_CONSTRAINTS, SizeConstraint.free())
//					.setProperty(LayeredOptions.NODE_SIZE_OPTIONS, EnumSet.of(SizeOptions.DEFAULT_MINIMUM_SIZE))
//					.setProperty(LayeredOptions.PORT_BORDER_OFFSET, 0d)
//					.setProperty(LayeredOptions.SPACING_COMPONENT_COMPONENT, 0d)
//					.setProperty(LayeredOptions.SPACING_NODE_NODE, 0d)
//					.setProperty(LayeredOptions.LAYERING_LAYER_CONSTRAINT, LayerConstraint.NONE)
//					.setProperty(LayeredOptions.PORT_LABELS_PLACEMENT, PortLabelPlacement.INSIDE)
			;

			// gmfDiagramLayoutConnector.applyLayout(layoutMapping,
			// settings);

			// DiagramLayoutEngine.Parameters params = new DiagramLayoutEngine.Parameters();

//			params.addLayoutRun(layoutConfigurator);
//			DiagramLayoutEngine.invokeLayout(null, workspaceResource.getAdapter(Diagram.class), null);

			// DiagramLayoutEngine.invokeLayout(workbenchPart, diagram, null);

//			boolean animation = true;
//			boolean zoomToFit = true;
//			boolean progressDialog = false;

			Parameters params = new Parameters();
			params.setOverrideDiagramConfig(true);
			params.addLayoutRun(layoutConfigurator);

//			params.getGlobalSettings().setProperty(CoreOptions.ALGORITHM, "org.eclipse.elk.layered")
//					.setProperty(CoreOptions.DIRECTION, Direction.LEFT).setProperty(CoreOptions.LAYOUT_ANCESTORS, false)
//					.setProperty(CoreOptions.ZOOM_TO_FIT, zoomToFit).setProperty(CoreOptions.DIRECTION, Direction.UP)
//					// .setProperty(LayeredOptions.LAYERING_STRATEGY, LayeringStrategy.INTERACTIVE)
//					.setProperty(CoreOptions.SPACING_EDGE_NODE, 21.0);
			ISelection selection = null;
			Object diagramPart = null;
			IWorkbench workbench = PlatformUI.getWorkbench();
			if (workbench != null) {
				ISelectionService selectionService = PlatformUI.getWorkbench().getWorkbenchWindows()[0]
						.getSelectionService();
				if (selectionService != null) {
					selection = selectionService.getSelection();
					if (selection instanceof IStructuredSelection && selection != null) {
						IStructuredSelection structuredSelection = (IStructuredSelection) selection;

						if (structuredSelection.size() == 1) {
							diagramPart = structuredSelection.getFirstElement();
						} else {
							diagramPart = structuredSelection.toList();
						}
					}
					// gmfDiagramLayoutConnector.buildLayoutGraph(diagramEditor, diagramPart);

					DiagramLayoutEngine.invokeLayout(workbenchPart, null, params);
//					DiagramLayoutEngine.invokeLayout(editorPart, diagramPart, animation, progressDialog, false,
//							zoomToFit);

				}
			}
			// page.saveEditor(editorPart, true);
			// diagramResource.save(null);
			saveEditor(page);
			// selectFileInCurrentView(diagramResource, page);
			System.out.println("Graph successfully layouted");

		}

	}

	public void graphmlElkLayout(Resource diagramResource) {
		String path = diagramResource.getURI().toString();
		IResource workspaceResource = ResourcesPlugin.getWorkspace().getRoot().findMember(new Path(path));

		IWorkbenchPage page = PlatformUI.getWorkbench().getWorkbenchWindows()[0].getActivePage();
		openDiagramEditor(workspaceResource, page);

		IWorkbenchPart workbenchPart = page.getActivePart();

		LayoutConfigurator layoutConfigurator = new LayoutConfigurator();

		layoutConfigurator.configure(ElkNode.class).setProperty(CoreOptions.ALGORITHM, "org.eclipse.elk.layered")

				.setProperty(CoreOptions.LAYOUT_ANCESTORS, false)
				// .setProperty(CoreOptions.PORT_ALIGNMENT_DEFAULT, PortAlignment.CENTER)
				.setProperty(CoreOptions.DIRECTION, Direction.DOWN)
				.setProperty(CoreOptions.EDGE_ROUTING, EdgeRouting.ORTHOGONAL)

//		layoutConfigurator.configure(ElkNode.class).setProperty(LayeredOptions.SPACING_EDGE_NODE_BETWEEN_LAYERS,
//				20.0);Boolean.valueOf(settingNameToValue.get("ZOOM_TO_FIT"))

//				.setProperty(CoreOptions.PORT_CONSTRAINTS, PortConstraints.FIXED_SIDE)

				.setProperty(CoreOptions.ZOOM_TO_FIT, true).setProperty(CoreOptions.ANIMATE, true)
				// .setProperty(CoreOptions.ALIGNMENT,
				// org.eclipse.elk.core.options.Alignment.RIGHT)

				// .setProperty(LayeredOptions.LAYERING_STRATEGY,
				// LayeringStrategy.NETWORK_SIMPLEX)

				.setProperty(LayeredOptions.MERGE_EDGES, false)
				.setProperty(LayeredOptions.UNNECESSARY_BENDPOINTS, false).setProperty(LayeredOptions.HYPERNODE, false)
				// .setProperty(LayeredOptions.ASPECT_RATIO, 10.0)
//				.setProperty(LayeredOptions.COMPACTION_POST_COMPACTION_STRATEGY,
//						GraphCompactionStrategy.LEFT_RIGHT_CONNECTION_LOCKING)
				// .setProperty(LayeredOptions.EDGE_ROUTING_POLYLINE_SLOPED_EDGE_ZONE_WIDTH,
				// 1.0)
				// .setProperty(LayeredOptions.EDGE_THICKNESS, 40.0)
				.setProperty(LayeredOptions.FEEDBACK_EDGES, false)
				.setProperty(LayeredOptions.HIERARCHY_HANDLING, HierarchyHandling.INHERIT)

				.setProperty(LayeredOptions.HIGH_DEGREE_NODES_TREATMENT, false)
				// .setProperty(LayeredOptions.HIGH_DEGREE_NODES_TREE_HEIGHT, 100)

//				.setProperty(LayeredOptions.LAYERING_NODE_PROMOTION_STRATEGY, NodePromotionStrategy.NO_BOUNDARY)
//				.setProperty(LayeredOptions.LAYERING_WIDE_NODES_ON_MULTIPLE_LAYERS, WideNodesStrategy.AGGRESSIVE)
//				
				.setProperty(LayeredOptions.LAYERING_STRATEGY, LayeringStrategy.INTERACTIVE)
//				.setProperty(LayeredOptions.LAYERING_NODE_PROMOTION_STRATEGY, NodePromotionStrategy.NONE)
				.setProperty(LayeredOptions.NODE_PLACEMENT_NETWORK_SIMPLEX_NODE_FLEXIBILITY, NodeFlexibility.NONE)
				.setProperty(LayeredOptions.LAYERING_LAYER_CONSTRAINT, LayerConstraint.NONE)

				.setProperty(LayeredOptions.MERGE_HIERARCHY_EDGES, false)
				.setProperty(LayeredOptions.NODE_LABELS_PLACEMENT, NodeLabelPlacement.fixed())
				.setProperty(LayeredOptions.EDGE_LABELS_PLACEMENT, EdgeLabelPlacement.UNDEFINED)
				// .setProperty(LayeredOptions.NODE_SIZE_OPTIONS,
				// EnumSet.of(SizeOptions.DEFAULT_MINIMUM_SIZE))
				// .setProperty(LayeredOptions.NODE_LABELS_PADDING, new ElkPadding(10))
				.setProperty(LayeredOptions.NODE_PLACEMENT_BK_EDGE_STRAIGHTENING,
						EdgeStraighteningStrategy.IMPROVE_STRAIGHTNESS)
				.setProperty(LayeredOptions.NODE_PLACEMENT_BK_FIXED_ALIGNMENT, FixedAlignment.NONE)
				.setProperty(LayeredOptions.NORTH_OR_SOUTH_PORT, false)
				.setProperty(LayeredOptions.NODE_SIZE_CONSTRAINTS, SizeConstraint.fixed())
				// .setProperty(LayeredOptions.PARTITIONING_ACTIVATE, true)
				.setProperty(LayeredOptions.PORT_ALIGNMENT_DEFAULT, PortAlignment.JUSTIFIED)
				.setProperty(LayeredOptions.EDGE_LABELS_SIDE_SELECTION, EdgeLabelSideSelection.SMART_DOWN)
				// .setProperty(CoreOptions.PORT_BORDER_OFFSET, 2d)
//				.setProperty(LayeredOptions.SPACING_NODE_NODE_BETWEEN_LAYERS, 40d)
//				.setProperty(LayeredOptions.SPACING_NODE_NODE, 40d).setProperty(LayeredOptions.SPACING_EDGE_EDGE, 400d)
//				.setProperty(LayeredOptions.SPACING_EDGE_EDGE_BETWEEN_LAYERS, 200d)
//				.setProperty(LayeredOptions.SPACING_LABEL_NODE, 1d).setProperty(LayeredOptions.PORT_BORDER_OFFSET, 10d)
//				.setProperty(LayeredOptions.SPACING_COMPONENT_COMPONENT, 100d)
				// .setProperty(LayeredOptions.SPACING_NODE_NODE, 0d)
				// .setProperty(LayeredOptions.PORT_SORTING_STRATEGY,
				// PortSortingStrategy.INPUT_ORDER)
				.setProperty(LayeredOptions.CYCLE_BREAKING_STRATEGY, CycleBreakingStrategy.DEPTH_FIRST)

				.setProperty(LayeredOptions.WRAPPING_STRATEGY, WrappingStrategy.SINGLE_EDGE)
				.setProperty(LayeredOptions.COMPACTION_CONNECTED_COMPONENTS, true)
				.setProperty(LayeredOptions.CROSSING_MINIMIZATION_HIERARCHICAL_SWEEPINESS, -1.0)
				.setProperty(LayeredOptions.CROSSING_MINIMIZATION_STRATEGY, CrossingMinimizationStrategy.LAYER_SWEEP)
				// .setProperty(LayeredOptions.CROSSING_MINIMIZATION_GREEDY_SWITCH_TYPE,
				// GreedySwitchType.TWO_SIDED)
				// .setProperty(LayeredOptions.CONTENT_ALIGNMENT,
				// ContentAlignment.centerCenter())
				.setProperty(LayeredOptions.NODE_PLACEMENT_STRATEGY, NodePlacementStrategy.BRANDES_KOEPF)
				.setProperty(LayeredOptions.NODE_PLACEMENT_FAVOR_STRAIGHT_EDGES, true)
				.setProperty(LayeredOptions.DIRECTION_CONGRUENCY, DirectionCongruency.READING_DIRECTION);
		Parameters params = new Parameters();
		params.setOverrideDiagramConfig(true);
		params.addLayoutRun(layoutConfigurator);
		DiagramLayoutEngine.invokeLayout(workbenchPart, null, params);
		saveEditor(page);
		// selectFileInCurrentView(diagramResource, page);
		System.out.println("Graph successfully layouted");
	}

	protected DiagramEditor getDiagramEditor(final IWorkbenchPart workbenchPart) {
		if (workbenchPart instanceof GraphDiagramEditor) {
			return (DiagramEditor) workbenchPart;
		} else if (workbenchPart instanceof IAdaptable) {
			return ((IAdaptable) workbenchPart).getAdapter(DiagramEditor.class);
		}
		return null;
	}

	protected EditPart getEditPart(final DiagramEditPart diagramEditPart) {
		if (diagramEditPart instanceof EditPart) {
			return (EditPart) diagramEditPart;
		} else if (diagramEditPart instanceof IAdaptable) {
			return ((IAdaptable) diagramEditPart).getAdapter(DiagramEditPart.class);
		}

		return null;
	}

	protected ShapeNodeEditPart getShapeNodeEditPart(final EditPart editPart) {
		if (editPart instanceof ShapeNodeEditPart) {
			return (ShapeNodeEditPart) editPart;
		} else if (editPart instanceof IAdaptable) {
			return ((IAdaptable) editPart).getAdapter(ShapeNodeEditPart.class);
		}

		return null;
	}

	protected void selectRevealResource(IResource resource) throws PartInitException {
		if (resource == null)
			return;
		IWorkbenchPage page = PlatformUI.getWorkbench().getWorkbenchWindows()[0].getActivePage();
		IViewPart view = page.findView(IPageLayout.ID_PROJECT_EXPLORER);
		Runnable runnable = new Runnable() {
			public void run() {
				if (view instanceof ISetSelectionTarget) {
					StructuredSelection structuredSelection = new StructuredSelection(resource);
					ISelection selection = (ISelection) structuredSelection;
					((ISetSelectionTarget) view).selectReveal(selection);
				}
			}
		};
		Display display = getDisplay();
		display.syncExec(runnable);
	}

	public void selectTreeResource(IResource resource) throws PartInitException {
		if (resource == null)
			return;
		IWorkbenchPage page = PlatformUI.getWorkbench().getWorkbenchWindows()[0].getActivePage();
		IViewPart view = page.findView(IPageLayout.ID_PROJECT_EXPLORER);

		ISelectionProvider selectionProvider = view.getSite().getSelectionProvider();
		IProject project = resource.getProject();
		Runnable runnable = new Runnable() {
			public void run() {

				Object[] objectArray = { project, resource };
				TreePath path = new TreePath(objectArray);

				TreeSelection selection = new TreeSelection(path);

				if (selectionProvider instanceof Viewer) {
					((Viewer) selectionProvider).setSelection((ISelection) selection, true);

				}
			}

		};
		Display display = getDisplay();
		display.syncExec(runnable);
	}

	protected void selectFileInCurrentView(final IFolder file, IWorkbenchPage page) {
		final IWorkbenchPart activePart = page.getActivePart();
		final Display display = getDisplay();
		if (activePart instanceof ISetSelectionTarget) {
			final ISelection targetSelection = new StructuredSelection(file);

			IViewPart view = page.findView(IPageLayout.ID_PROJECT_EXPLORER);

			Runnable runnable = new Runnable() {
				public void run() {
					ISetSelectionTarget setSelectionTarget = (ISetSelectionTarget) view;
					setSelectionTarget.selectReveal(targetSelection);
				}
			};

			display.syncExec(runnable);

		}
	}

	protected static Display getDisplay() {
		Display display = Display.getCurrent();
		// may be null if outside the UI thread
		if (display == null)
			display = Display.getDefault();
		return display;
	}

	protected void openDiagramEditor(IResource workspaceResource, IWorkbenchPage page) {

		Runnable runnable = new Runnable() {
			public void run() {
				try {
					IDE.openEditor(page, (IFile) workspaceResource, GraphDiagramEditor.ID);
				} catch (PartInitException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		};

		Display display = getDisplay();
		display.syncExec(runnable);
	}

	protected void saveEditor(IWorkbenchPage page) {
		IEditorPart editorPart = page.getActiveEditor();
		Runnable runnable = new Runnable() {
			public void run() {
				editorPart.doSave(new NullProgressMonitor());
				// page.saveEditor(editorPart, false);
				// page.saveAllEditors(true);
				// ISaveablePart editorPart;

			}
		};

		Display display = getDisplay();
		display.syncExec(runnable);

	}

	private IWorkbenchPart getActivePart() {
		final IWorkbench workbench = PlatformUI.getWorkbench();
		final IWorkbenchWindow activeWindow = workbench.getActiveWorkbenchWindow();
		if (activeWindow != null) {
			final IWorkbenchPage activePage = activeWindow.getActivePage();
			if (activePage != null) {
				return activePage.getActivePart();
			}
		}
		return null;
	}

}
