/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package reasoner;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;

import kevoree.KevoreePackage;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;
import org.eclipse.viatra2.emf.incquery.runtime.exception.IncQueryRuntimeException;
import org.eclipse.viatra2.emf.incquery.runtime.extensibility.BuilderRegistry;
import org.eclipse.viatra2.gtasm.patternmatcher.incremental.rete.misc.DeltaMonitor;
import org.kevoree.ContainerRoot;
import org.kevoree.annotation.ComponentType;
import org.kevoree.annotation.Library;
import org.kevoree.annotation.Start;
import org.kevoree.annotation.Stop;
import org.kevoree.annotation.Update;
import org.kevoree.api.service.core.handler.ModelListener;
import org.kevoree.framework.AbstractComponentType;
import org.kevoree.framework.KevoreeXmiHelper;
import org.kevoree.tools.emf.compat.TransModelHelper;

import patternbuilders.nodeInfo.PatternBuilderFornode;
import patternmatchers.nodeInfo.NodeMatcher;
import signatures.nodeInfo.NodeSignature;
import utils.time.Chrono;

/**
 * 
 * @author obendavi
 * 
 **/

@Library(name = "IncQuery SAMPLE")
@ComponentType()
public class ReasonerIncQuery extends AbstractComponentType implements ModelListener {
	
	private ReasonerIncQueryGUI gui;	
	private TransModelHelper transModelHelper;
	private ContainerRoot kevRootElement;
	private org.kemf.compat.kevoree.ContainerRoot emfRootElement;
	private ResourceSet resourceSetMetamodel; 
	private Resource resourceModel;
	


	public void saveKevoreeEMFModel(String path) {
		// REGISTER THE METAMODEL
		resourceSetMetamodel = new ResourceSetImpl();
		resourceSetMetamodel.getPackageRegistry().put(org.kemf.compat.kevoree.KevoreePackage.eNS_URI, org.kemf.compat.kevoree.KevoreePackage.eINSTANCE);
		resourceSetMetamodel.getResourceFactoryRegistry().getExtensionToFactoryMap().put("*", new XMIResourceFactoryImpl());

		resourceModel = resourceSetMetamodel.createResource(URI
				.createFileURI("models/" + path + ".xmi"));

		resourceModel.getContents().add(emfRootElement);

		try {
			resourceModel.save(Collections.EMPTY_MAP);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	public void loadLastModel() {
		Chrono chrono = new Chrono();
		HashMap<String, String> processTime = new HashMap<String, String>();
		
		//get current architecture model
		chrono.start();
		kevRootElement = getModelService().getLastModel();
		chrono.stop();
		processTime.put("getModelService().getLastModel();", chrono.displayTime());
		
		//transform current kevoree model into kevoree emf compliant model
		chrono.start();
		kev2emf();
		chrono.stop();
		processTime.put("kev2emf();", chrono.displayTime());
		
		//analyze architecture to detect nodes
		chrono.start();
		nodeInfo();
		chrono.stop();
		processTime.put("nodeInfo();", chrono.displayTime());
		
		//serialize kev model emf xmi
		chrono.start();
		saveKevoreeEMFModel("boukiki");
		chrono.stop();
		processTime.put("saveKevoreeEMFModel(\"boukiki\")", chrono.displayTime());
		
		//emfRootElement = transModelHelper.konvert(kevRootElement);
		chrono.start();
		gui.updateTextArea("kevRootElement : " + kevRootElement.toString());
		gui.updateTextArea("emfRootElement : " + emfRootElement.toString());
		chrono.stop();
		
		
		
		for (String s : processTime.keySet()){
			gui.updateTextArea(s+" : "+processTime.get(s));	
		}
		
		
		
	}

	public void kev2emf(){
		resourceSetMetamodel = new ResourceSetImpl();
		resourceSetMetamodel.getPackageRegistry().put(org.kemf.compat.kevoree.KevoreePackage.eNS_URI, org.kemf.compat.kevoree.KevoreePackage.eINSTANCE);
		resourceSetMetamodel.getResourceFactoryRegistry().getExtensionToFactoryMap().put("*", new XMIResourceFactoryImpl());
		kevRootElement = getModelService().getLastModel();
		ByteArrayOutputStream outStream = new ByteArrayOutputStream();
		KevoreeXmiHelper.saveStream(outStream, kevRootElement);
		resourceModel = resourceSetMetamodel.createResource(URI.createURI(KevoreePackage.eNS_URI));
		byte[] currentModel = outStream.toByteArray();
		ByteArrayInputStream inStream = new ByteArrayInputStream(currentModel);
		try {
			resourceModel.load(inStream, null);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		emfRootElement = (org.kemf.compat.kevoree.ContainerRoot) resourceModel.getContents().get(0);
	}
	
	public void nodeInfo(){
		BuilderRegistry.getContributedStatelessPatternBuilders().put(
				NodeMatcher.FACTORY.getPatternName(),
				new PatternBuilderFornode());
 

		NodeMatcher matcher =null;
		try {
			matcher = NodeMatcher.FACTORY.getMatcher(emfRootElement);
		} catch (IncQueryRuntimeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		matcher.countMatches();
		System.out.println("number of nodes : "+matcher.countMatches());
		gui.updateTextArea("number of nodes : "+matcher.countMatches());
		System.out.println();
		for(NodeSignature sig : matcher.getAllMatchesAsSignature()){
			System.out.println("sig : "+sig.getValueOfX());
			gui.updateTextArea("sig : "+sig.getValueOfX());
		}
		
		final DeltaMonitor<NodeSignature> mon = matcher.newDeltaMonitor(true);
		matcher.addCallbackAfterUpdates(new Runnable() {

			@Override
			public void run() {
				for (NodeSignature x : mon.matchFoundEvents) {
					System.out.println(x.getValueOfX());
					
				}
				mon.clear();

			}
		});

		for (NodeSignature x : mon.matchFoundEvents) {
			System.out.println(x.getValueOfX());
		}
	}
	
	
	@Start
	public void start() {
		kevRootElement = getModelService().getLastModel(); 		
		gui = new ReasonerIncQueryGUI(this);
		gui.setVisible(true);
		gui.setTitle("ReasonerIncQuery");
		getModelService().registerModelListener(this);
	}

	@Stop
	public void stop() {
	}

	@Update
	public void update() {
	}

	@Override
	public void modelUpdated() {
		kevRootElement = getModelService().getLastModel();						
	}

	@Override
	public boolean preUpdate(org.kevoree.ContainerRoot currentModel,
			org.kevoree.ContainerRoot proposedModel) {
		return true;
	}

	@Override
	public boolean initUpdate(ContainerRoot arg0, ContainerRoot arg1) {
		// TODO Auto-generated method stub
		return true;
	}
}