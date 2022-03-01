package de.iils.dc43.graph.draw;

import java.io.IOException;

import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.NotEnabledException;
import org.eclipse.core.commands.NotHandledException;
import org.eclipse.core.commands.common.NotDefinedException;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.jgrapht.io.ExportException;
import org.jgrapht.io.ImportException;

import de.iils.dc43.core.IDC43Project;
import de.iils.dc43.core.interfaces.DC43Interface;
import de.iils.dc43.core.interfaces.IDC43Execution;

public class DC43Execution extends DC43Interface implements IDC43Execution {

	public DC43Execution() {
	}

	@Override
	public String getLabel() {
		return "View Graph as 2D";
	}

	@Override
	public void execute(IDC43Project project, IProgressMonitor monitor) {
		ExecutionHelperDC43 helper = new ExecutionHelperDC43();
		try {
			helper.execute(project, monitor);
		} catch (ExecutionException | NotDefinedException | NotEnabledException | NotHandledException | CoreException
				| IOException | ExportException | ImportException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
