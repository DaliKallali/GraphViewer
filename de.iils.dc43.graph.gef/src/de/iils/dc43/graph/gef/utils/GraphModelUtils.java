package de.iils.dc43.graph.gef.utils;

import de.iils.dc43.graph.Edge;
import de.iils.dc43.graph.Graph;
import de.iils.dc43.graph.GraphFactory;
import de.iils.dc43.graph.Node;

public enum GraphModelUtils {
	INSTANCE;

	private GraphFactory factory = GraphFactory.eINSTANCE;

	public Graph createModel() {
		Graph outputGraph = factory.createGraph();
		Node node1 = factory.createNode();
		node1.setId("n1");
		outputGraph.getNodes().add(node1);
		Node node2 = factory.createNode();
		node2.setId("n2");
		outputGraph.getNodes().add(node2);
		Edge edge1 = factory.createEdge();
		edge1.setSource(node1);
		edge1.setTarget(node2);
		outputGraph.getEdges().add(edge1);
		return outputGraph;
	}
}