package de.iils.dc43.graph.draw;

import java.io.FileNotFoundException;
import java.io.IOException;

import javax.inject.Named;

import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.NotEnabledException;
import org.eclipse.core.commands.NotHandledException;
import org.eclipse.core.commands.common.NotDefinedException;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.e4.ui.services.IServiceConstants;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.widgets.Shell;
import org.jgrapht.io.ExportException;
import org.jgrapht.io.ImportException;

public class DrawGraphHandler {

	@Execute // , IFile GraphFile
	public void execute(@Named(IServiceConstants.ACTIVE_SELECTION) IStructuredSelection selection, Shell shell)
			throws FileNotFoundException, CoreException, IOException, ExportException, ImportException,
			ExecutionException, NotDefinedException, NotEnabledException, NotHandledException {

		ExecutionHelper helper = new ExecutionHelper();
		helper.execute(selection, shell);

	}

}
