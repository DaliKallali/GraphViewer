package de.iils.dc43.graph.gef.editor.part;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.draw2d.FreeformLayer;
import org.eclipse.draw2d.FreeformLayout;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.LineBorder;
import org.eclipse.gef.editparts.AbstractGraphicalEditPart;

import de.iils.dc43.graph.Graph;
import de.iils.dc43.graph.Node;

public class GraphEditPart extends AbstractGraphicalEditPart {
	@Override
	protected IFigure createFigure() {
		FreeformLayer layer = new FreeformLayer();
		layer.setLayoutManager(new FreeformLayout());
		layer.setBorder(new LineBorder(1));
		return layer;
	}

	@Override
	protected void createEditPolicies() {
		// TODO Auto-generated method stub
	}

	@Override
	protected List<Node> getModelChildren() {
		List<Node> retVal = new ArrayList<Node>();
		Graph outputGraph = (Graph) getModel();
		retVal.addAll(outputGraph.getNodes());
		return retVal;
	}
}