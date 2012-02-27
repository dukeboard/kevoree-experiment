/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.kevoree.library.javase.modelProvider;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
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
import org.kevoree.annotation.ComponentType;
import org.kevoree.annotation.Library;
import org.kevoree.annotation.Start;
import org.kevoree.annotation.Stop;
import org.kevoree.annotation.Update;
import org.kevoree.api.service.core.handler.ModelListener;
import org.kevoree.framework.AbstractComponentType;
import org.kevoree.framework.KevoreePropertyHelper;
import org.kevoree.framework.KevoreeXmiHelper;

import patternbuilders.test.PatternBuilderForshareSameLocation2;
import patternmatchers.test.ShareSameLocation2Matcher;
import signatures.test.ShareSameLocation2Signature;

/**
 * 
 * @author obendavi
 */


@Library(name = "IncQuery SAMPLE")
@ComponentType()
public class Reasoner extends AbstractComponentType implements ModelListener {

	private ResourceSet resourceSetMetamodel;
	private Resource resourceModel;
	private ContainerRoot rootElement;
	private ShareSameLocation2Matcher matcherShareSameLocation;
	private DeltaMonitor<ShareSameLocation2Signature> monitorShareSameLocation;
	private HashMap<String, ArrayList<String>> archLocationEntities;
	private boolean lockUpdate;

	@Start
	public void start() {
		getModelService().registerModelListener(this);
		// INIT ATTRIBUTES TO DISPLAY RESULTS
		archLocationEntities = new HashMap<String, ArrayList<String>>();
		// setModelForIncQuery();
		lockUpdate = false;
	}

	public void registerMetaModel() {
		// REGISTER THE METAMODEL
		System.out.println("REGISTER THE METAMODEL");
		resourceSetMetamodel = new ResourceSetImpl();
		resourceSetMetamodel.getPackageRegistry().put(KevoreePackage.eNS_URI,
				KevoreePackage.eINSTANCE);
		resourceSetMetamodel.getResourceFactoryRegistry()
				.getExtensionToFactoryMap()
				.put("*", new XMIResourceFactoryImpl());
		// LOAD THE MODEL
		System.out.println("LOAD THE MODEL");
		ByteArrayOutputStream outStream = new ByteArrayOutputStream();
		KevoreeXmiHelper
				.saveStream(outStream, getModelService().getLastModel());
		resourceModel = resourceSetMetamodel.createResource(URI
				.createURI(KevoreePackage.eNS_URI));

		byte[] currentModel = outStream.toByteArray();
		// ByteArrayInputStream inStream = new ByteArrayInputStream(
		// outStream.toByteArray());
		ByteArrayInputStream inStream = new ByteArrayInputStream(currentModel);

		try {
			resourceModel.load(inStream, null);
		} catch (IOException e) {
			System.out.println("error during the model loading step");
			e.printStackTrace();
		}
	}

	public void loadContainerRoot() {
		// INSTANTIATE ROOTELEMENT WITH THE CONTAINERROOT OF THE LOADED MODEL
		System.out
				.println("INSTANTIATE ROOTELEMENT WITH THE CONTAINERROOT OF THE LOADED MODEL");
		rootElement = (ContainerRoot) resourceModel.getContents()
				.get(0);
	}

	public void registerPatternMatcher() {
		// REGISTER PATTERN MATCHERS
		System.out.println("REGISTER PATTERN MATCHERS");
		BuilderRegistry.getContributedStatelessPatternBuilders().put(
				ShareSameLocation2Matcher.FACTORY.getPatternName(),
				new PatternBuilderForshareSameLocation2());
	}

	public void initPatternMatcher() {
		// INIT PATTERNMATCHER AND MONITORPATTERMATCHER
		System.out.println("INIT PATTERNMATCHER AND MONITORPATTERMATCHER");
		try {
			matcherShareSameLocation = new ShareSameLocation2Matcher.Factory()
					.getMatcher(rootElement);
		} catch (IncQueryRuntimeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		monitorShareSameLocation = matcherShareSameLocation
				.newDeltaMonitor(true);
	}

	// ALLOW TO LOAD THE CURRENT MODEL FOR INCQUERY PERFORM PATTERNMATCHING
	public void incQueryze() {
		lockUpdate = true;
		registerMetaModel();
		loadContainerRoot();
		registerPatternMatcher();
		initPatternMatcher();
	}

	// INIT NOTIFIER ON THE PATTERNMATCHER
	public void initMonitorShareSameLocation2() {
		matcherShareSameLocation.addCallbackAfterUpdates(new Runnable() {
			@Override
			public void run() {
			}
		});
	}

	// TO MANAGE MOVEMENT OF ENTITIES IN STRUCTURE FOR VISUALIZATION
	public void moveArchLocationEntity(String loc, String ent) {
		for (String s : archLocationEntities.keySet()) {
			if (archLocationEntities.get(s).contains(ent)) {
				archLocationEntities.get(s).remove(ent);
			}
		}
		if (!archLocationEntities.keySet().contains(loc)) {
			archLocationEntities.put(loc, new ArrayList<String>());
		}
		archLocationEntities.get(loc).add(ent);
	}


	public void displayPatternResultShareSameLocation() {
		System.out.println("PATTERN MATCHING RESULT");
		archLocationEntities = new HashMap<String, ArrayList<String>>();
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
		String res = "";
		for (String s : archLocationEntities.keySet()) {
			res = res + s + " : ";
			for (String me : archLocationEntities.get(s)) {
				res = res + " " + me;
			}
			res = res + "\n";
		}
		System.out.println(res);
	}

	@Stop
	public void stop() {
	}

	@Update
	public void update() {
	}

	public void analyzeWithoutIncQuery() {
		archLocationEntities = new HashMap<String, ArrayList<String>>();
		org.kevoree.ContainerRoot cr = getModelService().getLastModel();
		for (org.kevoree.ContainerNode n : cr.getNodesForJ()) {
			for (org.kevoree.ComponentInstance c : n.getComponentsForJ()) {
				if (c.getTypeDefinition().getName().equals("MovableEntity")) {
					String loc = KevoreePropertyHelper
							.getPropertyForComponent(cr, c.getName(),
									"location").get().toString();
					if (!archLocationEntities.containsKey(loc)) {
						archLocationEntities.put(loc,
								new ArrayList<String>());
					}
					archLocationEntities.get(loc).add(c.getName());
				}
			}
		}
		String res = "";
		for (String s : archLocationEntities.keySet()) {
			res = res + s + " : ";
			for (String me : archLocationEntities.get(s)) {
				res = res + " " + me;
			}
			res = res + "\n";
		}
		System.out.println(res);
	}

	@Override
	public void modelUpdated() {
		if (!lockUpdate) {
			System.out.println("\n\n");
			System.out.println("----------------------MODEL UPDATED "
					+ getModelService().getLastModification()
					+ "---------------------------");
			
			System.out.println("ANALYZE VIA INCQUERY");
			Chrono.start();
			incQueryze();
			initMonitorShareSameLocation2();
			displayPatternResultShareSameLocation();
			Chrono.stop();
			System.out.println("number of patterns occurences founded : "
					+ (monitorShareSameLocation.matchFoundEvents.size()/2));
			System.out.println("END ANALYZE VIA INCQUERY : "
					+ Chrono.displayTime());
			
			
			
			
			System.out.println("ANALYZE VIA KEVOREE");
			Chrono.start();
			analyzeWithoutIncQuery();
			Chrono.stop();
			System.out.println("END ANALYZE VIA KEVOREE : "
					+ Chrono.displayTime());
			
			
			System.out
					.println("----------------------END MODEL UPDATED ---------------------------");
			System.out.println("\n\n");
			lockUpdate = false;
		} else {
			System.out
					.println("this notification will not have impact, an other process using IncQuery is running !");
		}
	}
	
	@Override
	public boolean preUpdate(org.kevoree.ContainerRoot currentModel,
			org.kevoree.ContainerRoot proposedModel) {
		// TODO Auto-generated method stub

		return true;
	}

}