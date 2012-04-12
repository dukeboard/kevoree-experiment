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
import patternbuilders.nodeInfo.PatternBuilderFornodeObject;
import patternbuilders.nodeInfo.PatternBuilderFornodeSubject;
import patternmatchers.nodeInfo.NodeMatcher;
import patternmatchers.nodeInfo.NodeObjectMatcher;
import patternmatchers.nodeInfo.NodeSubjectMatcher;
import signatures.nodeInfo.NodeObjectSignature;
import signatures.nodeInfo.NodeSignature;
import signatures.nodeInfo.NodeSubjectSignature;
import utils.time.Chrono;

/**
 * 
 * @author obendavi
 * 
 **/

@Library(name = "ArchitectureIncQueryZer")
@ComponentType()
public class ReasonerIncQuery extends AbstractComponentType implements ModelListener {
	
	private ReasonerIncQueryGUI gui;	
	private TransModelHelper transModelHelper;
	private ContainerRoot kevRootElement;
	private org.kemf.compat.kevoree.ContainerRoot emfRootElement;
	private ResourceSet resourceSetMetamodel; 
	private Resource resourceModel;
	
	// Analyze system at runtime : each time the runtime model changes a notification is received by the reasoner
	// For each notification do :
	//1 get the current model
	//2 update the model associated to incQuery
	//3 analyze results of incQuery monitor
	
	public void saveKevoreeEMFModel(String path) {
		resourceSetMetamodel = new ResourceSetImpl();
		resourceSetMetamodel.getPackageRegistry().put(org.kemf.compat.kevoree.KevoreePackage.eNS_URI, org.kemf.compat.kevoree.KevoreePackage.eINSTANCE);
		resourceSetMetamodel.getResourceFactoryRegistry().getExtensionToFactoryMap().put("*", new XMIResourceFactoryImpl());
		resourceModel = resourceSetMetamodel.createResource(URI.createFileURI("models/" + path + ".xmi"));
		resourceModel.getContents().add(emfRootElement);
		try {
			resourceModel.save(Collections.EMPTY_MAP);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void analyze() {
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
		
		//analyze architecture to detect nodes containing subject address book 
		chrono.start();
		nodeSubjectInfo();
		chrono.stop();
		processTime.put("nodeSubjectInfo();;", chrono.displayTime());

		//analyze architecture to detect nodes containing object address book
		chrono.start();
		nodeObjectInfo();
		chrono.stop();
		processTime.put("nodeObjectInfo();", chrono.displayTime());
		
				
		//serialize kev model emf xmi
//		chrono.start();
//		saveKevoreeEMFModel("boukiki");
//		chrono.stop();
//		processTime.put("saveKevoreeEMFModel(\"boukiki\")", chrono.displayTime());		
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
			e.printStackTrace();
		}		
		emfRootElement = (org.kemf.compat.kevoree.ContainerRoot) resourceModel.getContents().get(0);
	}
	
	public void nodeInfo(){
		BuilderRegistry.getContributedStatelessPatternBuilders().put(NodeMatcher.FACTORY.getPatternName(),new PatternBuilderFornode());
		NodeMatcher matcher =null;
		try {
			matcher = NodeMatcher.FACTORY.getMatcher(emfRootElement);
		} catch (IncQueryRuntimeException e) {
			e.printStackTrace();
		}
		gui.updateTextArea("number of nodes : "+matcher.countMatches());
		for(NodeSignature sig : matcher.getAllMatchesAsSignature()){
			gui.updateTextArea("sig : "+sig.getValueOfX());
		}
	}
	
	public void nodeSubjectInfo(){
		BuilderRegistry.getContributedStatelessPatternBuilders().put(NodeSubjectMatcher.FACTORY.getPatternName(),new PatternBuilderFornodeSubject());
		NodeSubjectMatcher matcher =null;
		try {
			matcher = NodeSubjectMatcher.FACTORY.getMatcher(emfRootElement);
		} catch (IncQueryRuntimeException e) {
			e.printStackTrace();
		}
		gui.updateTextArea("number of nodes with RBAC subjects: "+matcher.countMatches());
		for(NodeSubjectSignature sig : matcher.getAllMatchesAsSignature()){
			gui.updateTextArea("sig : "+sig.getValueOfN());
		}
	}
		
	public void nodeObjectInfo(){
		BuilderRegistry.getContributedStatelessPatternBuilders().put(NodeObjectMatcher.FACTORY.getPatternName(),new PatternBuilderFornodeObject());
		NodeObjectMatcher matcher =null;
		try {
			matcher = NodeObjectMatcher.FACTORY.getMatcher(emfRootElement);
		} catch (IncQueryRuntimeException e) {
			e.printStackTrace();
		}
		gui.updateTextArea("number of nodes with RBAC objects : "+matcher.countMatches());
		for(NodeObjectSignature sig : matcher.getAllMatchesAsSignature()){
			gui.updateTextArea("sig : "+sig.getValueOfN());
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
	public void update(){
	}

	@Override
	public boolean initUpdate(ContainerRoot arg0, ContainerRoot arg1) {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean preUpdate(org.kevoree.ContainerRoot currentModel,
			org.kevoree.ContainerRoot proposedModel) {
		return true;
	}
	
	@Override
	public void modelUpdated() {
		Chrono c = new Chrono();
		c.start();
		analyze();
		c.stop();
		gui.updateTextArea("time analyze :"+c.displayTime());
	}
}