/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.kevoree.library.javase.modelProvider;

import org.kevoree.ContainerRoot;
import org.kevoree.annotation.ComponentType;
import org.kevoree.annotation.Library;
import org.kevoree.annotation.Start;
import org.kevoree.annotation.Stop;
import org.kevoree.annotation.Update;
import org.kevoree.api.service.core.handler.ModelListener;
import org.kevoree.framework.AbstractComponentType;


/**
 * 
 * @author obendavi
 */

@Library(name = "IncQuery SAMPLE")
@ComponentType()
public class Reasoner extends AbstractComponentType implements ModelListener {

	private KevoreeAnalyzer ka;
	private IncQueryAnalyzer iqa; 
	
	
	@Start
	public void start() {
		org.kevoree.ContainerRoot cr = getModelService().getLastModel(); 
		ka = new KevoreeAnalyzer();
		iqa = new IncQueryAnalyzer(cr,getKevScriptEngineFactory().createKevScriptEngine(cr));
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
			System.out.println("----------------------MODEL UPDATED "+ getModelService().getLastModification()+"---------------------------");
			org.kevoree.ContainerRoot cr = getModelService().getLastModel();			
		
			System.out.println("ANALYZE VIA KEVOREE");
			Chrono c = new Chrono();
			c.start();
			//gui.updateTextArea(ka.analyze(cr));
			ka.analyze(cr);
			c.stop();
			//gui.updateTextArea(Chrono.displayTime());
			System.out.println(c.displayTime());
			System.out.println("END ANALYZE VIA KEVOREE : ");
			
			
			System.out.println("----------------------END MODEL UPDATED ---------------------------");

			System.out.println("----------------------TEST COMPARISON---------------------------");
			iqa.updateEMFloadedModel(cr);
			//System.out.println("loaded model");
			//iqa.displayLoadedModel();
			System.out.println("----------------------END  COMPARISON---------------------------");
			
//			System.out.println("ANALYZE VIA KEVOREE");
//			Chrono.start();	ka.analyze(cr);	Chrono.stop();System.out.println(Chrono.displayTime());
//			System.out.println("END ANALYZE VIA KEVOREE : ");
			
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