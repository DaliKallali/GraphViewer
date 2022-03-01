package de.iils.dc43.graph.draw;

import java.util.Map;

import org.jgrapht.io.Attribute;
import org.jgrapht.io.VertexProvider;

public class ImportNodeVertexProvider implements VertexProvider<ImportNode> {

	@Override
	public ImportNode buildVertex(String id, Map<String, Attribute> attributes) {
		ImportNode node = new ImportNode(id, attributes);
		return node;
	}

}
