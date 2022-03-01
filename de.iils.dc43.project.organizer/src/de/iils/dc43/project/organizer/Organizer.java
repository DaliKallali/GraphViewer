package de.iils.dc43.project.organizer;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.IWizard;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.ui.internal.dialogs.ImportWizard;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Random;

public class Organizer {

	public Organizer() {
		// empty
	}
	private final Random random = new Random();
	public void useResourcesPlugin(IStructuredSelection selection) throws CoreException, FileNotFoundException {
//		IWorkspaceRoot workspaceRoot = ResourcesPlugin.getWorkspace().getRoot();
//		System.out.println(workspaceRoot);
//		IProject[] projects = workspaceRoot.getProjects();
//		for (IProject iProject : projects) {
//			System.out.println(iProject.getName());
//		}

		if (selection.isEmpty()) {
			System.out.println("Empty Selection");
			return;
		}

		IProject selectedProject = null;

		Object selectionElement = selection.getFirstElement();
		if (selectionElement instanceof IProject) {
			selectedProject = (IProject) selectionElement;
		} else {
			System.out.println("Selection is not a project.");
			return;
		}

		IFolder nodeImagesFolder = selectedProject.getFolder("nodeImages");
		System.out.println(nodeImagesFolder.exists());
		System.out.println(nodeImagesFolder);
		
		nodeImagesFolder.create(true, true, null);
		System.out.println(nodeImagesFolder.exists());
		
		//IWizard thewizard = "graph"  ;
		
                      //new FileInputStream((File) thewizard.getPage("graph"))
		/*IFile g2 = selectedProject.getFile("2g.graph");
		System.out.println(g2);*/
		//InputStream is = new BufferedInputStream(new FileInputStream(""));
		IFile graphFile = selectedProject.getFile("firstGraph");
		graphFile.create(new ByteArrayInputStream(createBytes(5000)), IResource.ALLOW_MISSING_LOCAL , null);
			
		
		System.out.println(nodeImagesFolder);
		System.out.println(nodeImagesFolder.exists());
	}
	   private byte[] createBytes(int length) {
	        byte[] bytes = new byte[length];
	        random.nextBytes(bytes);
	        return bytes;
	    }
}
