package org.kevoree.library.javase.modelProvider;

import java.util.Random;
import java.util.Vector;

import org.kevoree.ContainerRoot;
import org.kevoree.annotation.ComponentType;
import org.kevoree.annotation.Library;
import org.kevoree.annotation.Start;
import org.kevoree.annotation.Stop;
import org.kevoree.annotation.Update;
import org.kevoree.api.service.core.script.KevScriptEngine;
import org.kevoree.framework.AbstractComponentType;

@Library(name = "IncQuery SAMPLE")
@ComponentType()
public class Simulator extends AbstractComponentType {

	private DummyGUI gui;
	
	@Start
	public void start() {
		gui = new DummyGUI("simulator",this);
		gui.setVisible(true);
	}

	@Stop
	public void stop() {
	}

	@Update
	public void update() {
	}

	public void addNodeWithMovableEntity() {

	}

	public void changelocationOfMovableEntities() {		
		String toDisplay ="UPDATE RANDOMLY ALL MOVABLE ENTITY LOCATION \n";
		ContainerRoot cr = getModelService().getLastModel();
		KevScriptEngine kse = getKevScriptEngineFactory().createKevScriptEngine();
		Random generator = new Random();
		for (org.kevoree.ContainerNode n : cr.getNodesForJ()) {
			for (org.kevoree.ComponentInstance c : n.getComponentsForJ()) {
				if (c.getTypeDefinition().getName().equals("MovableEntity")) {					
					String loc = Integer.toString(generator.nextInt(3));
					toDisplay = toDisplay+n.getName()+" "+c.getName()+" "+loc+"\n";
					kse.append("updateDictionary " + c.getName() + "@"
							+ n.getName() + "{\"location\"=\"" + loc + "\"} ");
				}
			}
		}
		kse.atomicInterpretDeploy();
		gui.updateTextArea(toDisplay);
	}
	
	
	public void changelocationOfMovableEntity(String name) {		
		String toDisplay ="UPDATE RANDOMLY ONE MOVABLE ENTITY LOCATION\n";
		ContainerRoot cr = getModelService().getLastModel();
		KevScriptEngine kse = getKevScriptEngineFactory().createKevScriptEngine();
		Random generator = new Random();
		for (org.kevoree.ContainerNode n : cr.getNodesForJ()) {
			for (org.kevoree.ComponentInstance c : n.getComponentsForJ()) {					
				if (c.getName().equals(name)){
					String loc = Integer.toString(generator.nextInt(3));
					toDisplay = toDisplay+n.getName()+" "+c.getName()+" "+loc+"\n";
					kse.append("updateDictionary " + c.getName() + "@"
							+ n.getName() + "{\"location\"=\"" + loc + "\"} ");
				}
			}
		}
		kse.atomicInterpretDeploy();
		gui.updateTextArea(toDisplay);
	}
	
	public Vector<String> getMovableEntitiesList() {
		Vector<String> res = new Vector<String>();
		ContainerRoot cr = getModelService().getLastModel();
		for (org.kevoree.ContainerNode n : cr.getNodesForJ()) {
			for (org.kevoree.ComponentInstance c : n.getComponentsForJ()){
				if (c.getTypeDefinition().getName().equals("MovableEntity")){
					res.add(c.getName());
				}
			}
		}
		return res;
	}
	
	
}