package de.iils.dc43.graph.gef.editor.part;

import java.net.URL;
import java.util.Random;

import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Platform;
import org.eclipse.draw2d.IFigure;
import org.eclipse.gef.editparts.AbstractGraphicalEditPart;
import org.eclipse.gmf.runtime.draw2d.ui.render.factory.RenderedImageFactory;
import org.eclipse.gmf.runtime.draw2d.ui.render.figures.ScalableImageFigure;
import org.osgi.framework.Bundle;

import de.iils.dc43.graph.Node;

public class NodeEditPart extends AbstractGraphicalEditPart {
	Random rand = new Random();

	/*
	 * @Override protected IFigure createFigure() { return new NodeFigure(); }
	 */

	/**
	 * @generated NOT
	 */
	@Override
	protected IFigure createFigure() {
		Bundle bundle = Platform.getBundle("de.iils.dc43.graph.gef");
		URL url = FileLocator.find(bundle, new Path("images/2.png"), //$NON-NLS-1$
				null);
		return new ScalableImageFigure(RenderedImageFactory.getInstance(url), true, true, true);
	}

	@Override
	protected void createEditPolicies() {
		// TODO Auto-generated method stub

	}

	@Override
	protected void refreshVisuals() {
		IFigure figure = getFigure();
		Node model = (Node) getModel();
		GraphEditPart parent = (GraphEditPart) getParent();

		// figure.getLabel().setText(model.getId());
		IFigure layout = createFigure();
		parent.setLayoutConstraint(this, figure, layout);
	}

}