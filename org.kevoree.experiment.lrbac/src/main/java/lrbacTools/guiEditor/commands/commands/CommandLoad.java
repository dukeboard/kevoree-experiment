package lrbacTools.guiEditor.commands.commands;

import java.io.IOException;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;

import lrbac.LrbacPackage;
import lrbac.Policy;
import lrbacTools.guiEditor.commands.Command;
import lrbacTools.guiEditor.graphicComponents.FileChooser;
import lrbacTools.guiEditor.graphicComponents.RbacTextualEditor;

public class CommandLoad extends Command {

	public CommandLoad(RbacTextualEditor editor, String id) {
		super(editor, id);
	}

	public void execute() {
		ResourceSet resourceSetMetamodel;
		Resource resourceModel;
		Policy rootElement;
		
		// REGISTER THE METAMODEL
		resourceSetMetamodel = new ResourceSetImpl();
		resourceSetMetamodel.getPackageRegistry().put(LrbacPackage.eNS_URI,
				LrbacPackage.eINSTANCE);
		resourceSetMetamodel.getResourceFactoryRegistry()
				.getExtensionToFactoryMap()
				.put("xmi", new XMIResourceFactoryImpl());
	
		FileChooser fc = new FileChooser(getEditor());
		System.out.println(fc.getChooser().getSelectedFile().getAbsolutePath());
		String path = fc.getChooser().getSelectedFile().getAbsolutePath();
		
		// LOAD THE MODEL
		resourceModel = resourceSetMetamodel.createResource(URI
				.createFileURI(path));
		try {
			resourceModel.load(null);
		} catch (IOException e) {
			System.out.println("error during the model loading step");
			e.printStackTrace();
		}
		// INSTANTIATE ROOTELEMENT WITH THE CONTAINERROOT OF THE LOADED MODEL
		getEditor().setPolicy((Policy) resourceModel.getContents().get(0));
		getEditor().graphMonitor.update();
		
	}

}