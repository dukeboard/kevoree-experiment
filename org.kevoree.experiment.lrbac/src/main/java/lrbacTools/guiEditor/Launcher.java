package lrbacTools.guiEditor;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.UUID;

import lrbacTools.guiEditor.graphicComponents.RbacTextualEditor;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;
import org.kevoree.annotation.ComponentType;
import org.kevoree.annotation.Library;
import org.kevoree.annotation.Start;
import org.kevoree.annotation.Stop;
import org.kevoree.annotation.Update;
import org.kevoree.api.service.core.handler.ModelHandlerLockCallBack;
import org.kevoree.api.service.core.handler.ModelListener;
import org.kevoree.framework.AbstractComponentType;
import org.kevoree.framework.KevoreeXmiHelper;


import javax.swing.JFrame;

import kevoree.ContainerRoot;
import kevoree.KevoreePackage;

@Library(name = "RBAC")
@ComponentType()

public class Launcher extends AbstractComponentType implements ModelListener {
	private RbacTextualEditor editor; 
	
	@Start
	public void start() {
	    editor = new RbacTextualEditor(this);
	    editor.setVisible(true);
	    editor.update();
	    //update();
	}

	@Stop
	public void stop() {
		editor.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    	editor.dispose();
    	editor = null;
	}

	@Update
	public void update() {
		
	}

	
	@Override
	public boolean initUpdate(org.kevoree.ContainerRoot arg0,
			org.kevoree.ContainerRoot arg1) {
		return true;
	}

	@Override
	public void modelUpdated() {
//		getModelService().acquireLock(new ModelHandlerLockCallBack() {
//			@Override
//			public void lockTimeout() {
//				// TODO Auto-generated method stub
//				
//			}
//			
//			@Override
//			public void lockRejected() {
//				// TODO Auto-generated method stub
//				
//			}
//			
//			@Override
//			public void lockAcquired(UUID arg0) {
//				// TODO Auto-generated method stub				
//			}
//		},(long) 3000);
		editor.update();
//		getModelService().releaseLock(new UUID(10, 10));
		
	}

	@Override
	public boolean preUpdate(org.kevoree.ContainerRoot arg0,
			org.kevoree.ContainerRoot arg1) {
		// TODO Auto-generated method stub
		return true;
	}
		
	
	
	
	public ContainerRoot getKevoreeModel() {
		
		ResourceSet resourceSetMetamodel;
		Resource resourceModel;
		org.kevoree.ContainerRoot kevRootElement;
		ContainerRoot emfRootElement;
		System.out.println("hello");
			resourceSetMetamodel = new ResourceSetImpl();
			resourceSetMetamodel.getPackageRegistry().put(KevoreePackage.eNS_URI, KevoreePackage.eINSTANCE);
			resourceSetMetamodel.getResourceFactoryRegistry().getExtensionToFactoryMap().put("*", new XMIResourceFactoryImpl());
			System.out.println("hello");
			kevRootElement = getModelService().getLastModel();
			ByteArrayOutputStream outStream = new ByteArrayOutputStream();
			KevoreeXmiHelper.saveStream(outStream, (org.kevoree.ContainerRoot) kevRootElement);
			resourceModel = resourceSetMetamodel.createResource(URI.createURI(KevoreePackage.eNS_URI));
			byte[] currentModel = outStream.toByteArray();
			ByteArrayInputStream inStream = new ByteArrayInputStream(currentModel);
			try {
				resourceModel.load(inStream, null);
			} catch (IOException e) {
				e.printStackTrace();
			}		
			emfRootElement = (ContainerRoot) resourceModel.getContents().get(0);
			System.out.println("hello");
			
		return emfRootElement;
	}

}
