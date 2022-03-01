package de.iils.dc43.graph.gef.editor.part;

import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPartFactory;

import de.iils.dc43.graph.Graph;
import de.iils.dc43.graph.Node;

public class GraphEditPartFactoryGef implements EditPartFactory {

	@Override
	public EditPart createEditPart(EditPart context, Object model) {
		EditPart part = null;

		if (model instanceof Graph) {
			part = new GraphEditPart();
		} else if (model instanceof Node) {
			part = new NodeEditPart();
		}

		if (part != null) {
			part.setModel(model);
		}

		return part;
	}
}