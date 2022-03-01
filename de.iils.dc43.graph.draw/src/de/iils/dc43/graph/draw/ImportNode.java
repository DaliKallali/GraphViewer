package de.iils.dc43.graph.draw;

import java.util.HashMap;
import java.util.Map;

import org.jgrapht.io.Attribute;

public class ImportNode {

	private Map<String, Attribute> attributes = new HashMap<>();
	private String id = "";

	public ImportNode(String id, Map<String, Attribute> attributes) {
		this.id = id;
		if (attributes != null) {
			this.attributes = attributes;
		}
	}

	public Map<String, Attribute> getAttributes() {
		return new HashMap<String, Attribute>(attributes);
	}

	public String getId() {
		return id;
	}

}
