package org.kevoree.library.javase.modelProvider;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import kevoree.ComponentInstance;
import kevoree.ContainerRoot;
import kevoree.KevoreePackage;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;
import org.eclipse.viatra2.emf.incquery.runtime.exception.IncQueryRuntimeException;
import org.eclipse.viatra2.emf.incquery.runtime.extensibility.BuilderRegistry;
import org.eclipse.viatra2.gtasm.patternmatcher.incremental.rete.misc.DeltaMonitor;
import org.kevoree.ContainerNode;
import org.kevoree.DictionaryValue;
import org.kevoree.api.service.core.script.KevScriptEngine;
import org.kevoree.framework.KevoreePropertyHelper;
import org.kevoree.framework.KevoreeXmiHelper;
import org.kevoree.kompare.KevoreeKompareBean;
import org.kevoree.tools.emf.compat.TransModelHelper;
import org.kevoreeAdaptation.AdaptationModel;
import org.kevoreeAdaptation.AdaptationPrimitive;

import patternbuilders.test.PatternBuilderForshareSameLocation2;
import patternmatchers.test.ShareSameLocation2Matcher;
import signatures.test.ShareSameLocation2Signature;


public class IncQueryAnalyzer {

	private ResourceSet resourceSetMetamodel; 
	private Resource resourceModel;
	private ContainerRoot rootElement;
	private ShareSameLocation2Matcher matcherShareSameLocation;
	private DeltaMonitor<ShareSameLocation2Signature> monitorShareSameLocation;
	private org.kevoree.ContainerRoot loadedContainerRoot;
	private DummyGUI gui;
	private TransModelHelper transModelHelper;

	public IncQueryAnalyzer(org.kevoree.ContainerRoot cr, KevScriptEngine kse) {
		transModelHelper = new TransModelHelper();
		registerMetaModel();
		initContainerRootModel(cr, kse);
		loadCurrentModel(loadedContainerRoot, kse);
		loadContainerRoot();
		registerPatternMatcher();
		initPatternMatcher();
		initMonitorShareSameLocation2();
		gui = new DummyGUI();
		gui.setVisible(true);
		gui.setTitle("IncQueryAnalyzer");		
	}

	public void displayLoadedModel() {
		System.out.println(KevoreeXmiHelper.saveToString(loadedContainerRoot,
				true));
	}

	public void displayPatternResultShareSameLocation() {
		System.out.println("PATTERN MATCHING RESULT");
		HashMap<String, ArrayList<String>> archLocationEntities = new HashMap<String, ArrayList<String>>();
		for (ShareSameLocation2Signature x : monitorShareSameLocation.matchFoundEvents) {
			if (!archLocationEntities.keySet().contains(x.getValueOfVLOCN1())) {
				archLocationEntities.put((String) x.getValueOfVLOCN1(),
						new ArrayList<String>());
			}
			if (!archLocationEntities.get((String) x.getValueOfVLOCN1())
					.contains(((ComponentInstance) x.getValueOfC1()).getName())) {
				archLocationEntities.get((String) x.getValueOfVLOCN1()).add(
						((ComponentInstance) x.getValueOfC1()).getName());
			}
			if (!archLocationEntities.get((String) x.getValueOfVLOCN1())
					.contains(((ComponentInstance) x.getValueOfC2()).getName())) {
				archLocationEntities.get((String) x.getValueOfVLOCN1()).add(
						((ComponentInstance) x.getValueOfC2()).getName());
			}
		}
		String res = "**** incQuery result : "+new Date().getTime() +" ****\n";
		for (String s : archLocationEntities.keySet()) {
			res = res + s + " : ";
			for (String me : archLocationEntities.get(s)) {
				res = res + " " + me;
			}
			res = res + "\n";
		}
		res = res+ "*************************\n";
		System.out.println(res);
		gui.updateTextArea(res);
		
	}

	public void initContainerRootModel(org.kevoree.ContainerRoot cr,
			KevScriptEngine kse) {
		// LOAD THE MODEL
		Chrono ch = new Chrono();
		ch.start();
		try {
			
			for (org.kevoree.ContainerNode n : cr.getNodesForJ()) {
				for (org.kevoree.ComponentInstance c : n.getComponentsForJ()) {
					if (c.getTypeDefinition().getName().equals("MovableEntity")) {
						String loc = KevoreePropertyHelper
								.getPropertyForComponent(cr, c.getName(),
										"location").get().toString();
						kse.append("updateDictionary " + c.getName() + "@"
								+ n.getName() + "{\"location\"=\"" + loc
								+ "\"} ");
					}
				}
			}
			cr = kse.interpret();
			
			//rootElement = (ContainerRoot) transModelHelper.konvert(cr);
			
			gui.updateTextArea(transModelHelper.konvert(cr).toString());
			
			ByteArrayOutputStream outStream = new ByteArrayOutputStream();
			KevoreeXmiHelper.saveStream(outStream, cr);
			
			// System.out.println(KevoreeXmiHelper.saveToString(cr, true));
			
			resourceModel = resourceSetMetamodel.createResource(URI
					.createURI(KevoreePackage.eNS_URI));
			byte[] currentModel = outStream.toByteArray();
			ByteArrayInputStream inStream = new ByteArrayInputStream(
					currentModel);
			resourceModel.load(inStream, null);
			System.out.println(resourceModel);
			loadedContainerRoot = cr;
		} catch (Exception e) {
			System.out.println("error during the model loading step");
			System.out.println(e.toString());
			e.printStackTrace();
		}
		ch.stop();
		System.out.println("INIT CONTAINER ROOT MODEL : " + ch.displayTime());
	}

	// INIT NOTIFIER ON THE PATTERNMATCHER
	public void initMonitorShareSameLocation2() {
		matcherShareSameLocation.addCallbackAfterUpdates(new Runnable() {
			@Override
			public void run() {
				System.out.println("architecture have changed below the new set of elements satisfying the pattern searched : ");
				displayPatternResultShareSameLocation();
			}
		});
	}

	public void initPatternMatcher() {
		// INIT PATTERNMATCHER AND MONITORPATTERMATCHER
		Chrono c = new Chrono();
		c.start();
		try {
			matcherShareSameLocation = new ShareSameLocation2Matcher.Factory()
					.getMatcher(rootElement);
		} catch (IncQueryRuntimeException e) {
			e.printStackTrace();
		}
		monitorShareSameLocation = matcherShareSameLocation
				.newDeltaMonitor(true);
		c.stop();
		System.out.println("INIT PATTERNMATCHER AND MONITORPATTERMATCHER : "
				+ c.displayTime());
	}

	public void kompare(org.kevoree.ContainerRoot cr) {
		Chrono c = new Chrono();
		c.start();
		KevoreeKompareBean kkb = new KevoreeKompareBean();
		
		AdaptationModel am = kkb.kompare(loadedContainerRoot, cr, "node4");
				
		for (AdaptationPrimitive ap : am.getAdaptationsForJ()) {
			// return the reference object on which a primitive adaptation is
			// performed
			System.out.println("ap.getRef() : " + ap.getRef());
			// return the name of the adaptation primitive
			System.out.println("ap.getPrimitiveType().getName() : "
					+ ap.getPrimitiveType().getName());

			if (ap.getRef() instanceof org.kevoree.impl.ComponentInstanceImpl) {
				System.out.println(((org.kevoree.impl.ComponentInstanceImpl) ap
						.getRef()).getName());
				
				String newVal = " ";
				for (DictionaryValue dv : ((org.kevoree.impl.ComponentInstanceImpl) ap
						.getRef()).getDictionary().get().getValuesForJ()) {
					System.out.println("new model : " +dv.getAttribute()+"  "+ dv.getValue());
					if(dv.getAttribute().getName().equals("location")){
						newVal =  dv.getValue();
					}
				}
				
				for (kevoree.DictionaryValue dv : getLoadedComponentByName(
						((org.kevoree.impl.ComponentInstanceImpl) ap.getRef())
								.getName()).getDictionary().getValues()) {
				
					System.out.println("loaded model : " +dv.getAttribute()+"  "+dv.getValue());
					if(dv.getAttribute().getName().equals("location")){
						dv.setValue(newVal);
					}
					System.out.println("loaded model : " +dv.getAttribute()+"  "+dv.getValue());
				}
			}
		}
		c.stop();
		System.out
				.println("KOMPARE LoadedContainerRoot WITH CurrentContainerRoot : "
						+ c.displayTime());
	}
	
	public void updateEMFloadedModel(org.kevoree.ContainerRoot cr){
		Chrono c = new Chrono();
		c.start();
		KevoreeKompareBean kkb = new KevoreeKompareBean();
		ArrayList<AdaptationPrimitive> toAttend = new ArrayList<AdaptationPrimitive>(); 		
		for (String s : getNodeNameListToCheck()){			
			AdaptationModel ami = kkb.kompare(loadedContainerRoot, cr, s);
			for (AdaptationPrimitive ap : ami.getAdaptationsForJ()) {
				if (ap.getRef() instanceof org.kevoree.impl.ComponentInstanceImpl) {
					toAttend.add(ap);
				}
			}			
		}
		for (AdaptationPrimitive ap : toAttend) {
			if (ap.getRef() instanceof org.kevoree.impl.ComponentInstanceImpl) {
				String newVal = " ";				
				for (DictionaryValue dv : ((org.kevoree.impl.ComponentInstanceImpl) ap
						.getRef()).getDictionary().get().getValuesForJ()) {
					if(dv.getAttribute().getName().equals("location")){
						newVal =  dv.getValue();
						System.out.println("new val "+((org.kevoree.impl.ComponentInstanceImpl) ap.getRef())
								.getName()+": "+newVal);
					}
				}				
				for (kevoree.DictionaryValue dv : getLoadedComponentByName(
						((org.kevoree.impl.ComponentInstanceImpl) ap.getRef())
								.getName()).getDictionary().getValues()) {
					if(dv.getAttribute().getName().equals("location")){
						System.out.println("old val "+((org.kevoree.impl.ComponentInstanceImpl) ap.getRef())
								.getName()+": "+dv.getValue());
						dv.setValue(newVal);
						//updateDicoLoadedContainerRootModel(((org.kevoree.impl.ComponentInstanceImpl) ap.getRef())
						//		.getName(), newVal);
					}
				}
			}
		}
		loadedContainerRoot = cr;
		c.stop();
		System.out.println("KOMPARE2 : "+ c.displayTime());
	}

	public ArrayList<String> getNodeNameListToCheck() {
		ArrayList<String> res = new ArrayList<String>();
		for (org.kevoree.ContainerNode n : loadedContainerRoot.getNodesForJ()) {
			for (org.kevoree.ComponentInstance c : n.getComponentsForJ()){
				if (c.getTypeDefinition().getName().equals("MovableEntity")){
					res.add(n.getName());
				}
			}
		}
		return res;
	}

	public void updateDicoLoadedContainerRootModel(String componentName, String value){
		for (ContainerNode cn :  loadedContainerRoot.getNodesForJ()){
			for (org.kevoree.ComponentInstance ci : cn.getComponentsForJ()){
				if (ci.getName().equals(componentName)){
					for (DictionaryValue dv : ci.getDictionary().get().getValuesForJ()){
						if (dv.getAttribute().getName().equals("location")){
							dv.setValue(value);
						}
					}
				}
			}	
		}
	}
	
	public kevoree.ComponentInstance getLoadedComponentByName(String name) {
		kevoree.ComponentInstance res = null;
		for (kevoree.ContainerNode n : rootElement.getNodes()) {
			for (kevoree.ComponentInstance ci : n.getComponents()) {
				if (ci.getName().equals(name)) {
					res = ci;
				}
			}
		}
		return res;
	}

	public void updateDictionnary(kevoree.ContainerRoot cr,
			kevoree.ContainerNode cn, kevoree.ComponentInstance ci) {
		cr.getNodes().get(cr.getNodes().indexOf(cn))
				.setDictionary(ci.getDictionary());
	}

	public void loadContainerRoot() {
		Chrono c = new Chrono();
		c.start();
		rootElement = (ContainerRoot) resourceModel.getContents().get(0);
		c.stop();
		System.out
				.println("INSTANTIATE ROOTELEMENT WITH THE CONTAINERROOT OF THE LOADED MODEL : "
						+ c.displayTime());
	}

	public void loadCurrentModel(org.kevoree.ContainerRoot cr,
			KevScriptEngine kse) {
		// LOAD THE MODEL
		Chrono ch = new Chrono();
		ch.start();
		try {
			ByteArrayOutputStream outStream = new ByteArrayOutputStream();
			for (org.kevoree.ContainerNode n : cr.getNodesForJ()) {
				for (org.kevoree.ComponentInstance c : n.getComponentsForJ()) {
					if (c.getTypeDefinition().getName().equals("MovableEntity")) {
						String loc = KevoreePropertyHelper
								.getPropertyForComponent(cr, c.getName(),
										"location").get().toString();
						kse.append("updateDictionary " + c.getName() + "@"
								+ n.getName() + "{\"location\"=\"" + loc
								+ "\"} ");
					}
				}
			}
			cr = kse.interpret();
			KevoreeXmiHelper.saveStream(outStream, cr);
			// System.out.println(KevoreeXmiHelper.saveToString(cr, true));
			resourceModel = resourceSetMetamodel.createResource(URI
					.createURI(KevoreePackage.eNS_URI));
			byte[] currentModel = outStream.toByteArray();
			ByteArrayInputStream inStream = new ByteArrayInputStream(
					currentModel);
			resourceModel.load(inStream, null);
			System.out.println(resourceModel);
		} catch (Exception e) {
			System.out.println("error during the model loading step");
			System.out.println(e.toString());
			e.printStackTrace();
		}
		ch.stop();
		System.out.println("LOAD THE MODEL : " + ch.displayTime());
	}

	public void registerMetaModel() {
		// REGISTER THE METAMODEL
		Chrono c = new Chrono();
		c.start();
		resourceSetMetamodel = new ResourceSetImpl();
		resourceSetMetamodel.getPackageRegistry().put(KevoreePackage.eNS_URI,
				KevoreePackage.eINSTANCE);
		resourceSetMetamodel.getResourceFactoryRegistry()
				.getExtensionToFactoryMap()
				.put("*", new XMIResourceFactoryImpl());
		c.stop();
		System.out.println("REGISTER METAMODEL : " + c.displayTime());
	}

	public void registerPatternMatcher() {
		// REGISTER PATTERN MATCHERS
		Chrono c = new Chrono();
		c.start();
		BuilderRegistry.getContributedStatelessPatternBuilders().put(
				ShareSameLocation2Matcher.FACTORY.getPatternName(),
				new PatternBuilderForshareSameLocation2());
		c.stop();
		System.out.println("REGISTER PATTERN MATCHERS : "
				+ c.displayTime());
	}
}