package policyTools;

import java.io.File;

import org.kevoree.annotation.ComponentType;
import org.kevoree.annotation.Library;
import org.kevoree.annotation.Port;
import org.kevoree.annotation.PortType;
import org.kevoree.annotation.ProvidedPort;
import org.kevoree.annotation.Provides;
import org.kevoree.annotation.RequiredPort;
import org.kevoree.annotation.Requires;
import org.kevoree.annotation.Start;
import org.kevoree.annotation.Stop;
import org.kevoree.annotation.Update;
import org.kevoree.framework.AbstractComponentType;
import org.kevoree.framework.MessagePort;

import policyTools.guiEditor.graphicComponents.PolicyTextualEditor;
import xacml.pdp.PDPX; 

@Library(name = "XACML")
@ComponentType()

@Provides({
    @ProvidedPort(name = "evaluate", type = PortType.MESSAGE),
    @ProvidedPort(name = "constructResult", type = PortType.MESSAGE) 
})
@Requires({
    @RequiredPort(name = "result", type = PortType.MESSAGE, optional = true),
    @RequiredPort(name = "accessResource", type = PortType.MESSAGE, optional = true)
})

public class KevPDP extends AbstractComponentType {

	private PolicyTextualEditor editor;
	private PDPX pdp;
	
	@Start
	public void start() {
		pdp = new PDPX();
		editor = new PolicyTextualEditor(pdp);
		editor.setVisible(true);
		editor.update();
	}

	@Stop
	public void stop() {

	}

	@Update
	public void update() {

	}

	  @Port(name = "evaluate")
		public void evaluate(Object o) {
				String res = o.toString(); 
				boolean resEvaluation = (boolean) pdp.evaluate((File) o);
				if(resEvaluation){
					getPortByName("accessResource",MessagePort.class).process(resEvaluation);
				}
				else{
					getPortByName("result",MessagePort.class).process("not allowed");
				}
		}
	   
	  @Port(name = "constructResult")
		public void constructResult(Object o) {
				String res = o.toString();
				pdp.gui.updateTextArea(res);
				getPortByName("result",MessagePort.class).process(res);
		}
}
