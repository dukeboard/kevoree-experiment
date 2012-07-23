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

public class CommandSave extends Command{
	
	public CommandSave(RbacTextualEditor editor, String id){
		super(editor,id);
	}
	
	public void execute(){
		ResourceSet resourceSetMetamodel;
		Resource resourceModel;
		
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
		
		// SAVE THE MODEL
		resourceModel = resourceSetMetamodel.createResource(URI
				.createFileURI(path));
		resourceModel.getContents().add(getEditor().getPolicy());
		try {
			resourceModel.save(null);
		} catch (IOException e) {
			System.out.println("error during the model saving step");
			e.printStackTrace();
		}
	}
}