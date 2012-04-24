package lrbacTools.guiEditor;

import lrbacTools.guiEditor.graphicComponents.RbacTextualEditor;
import org.kevoree.annotation.ComponentType;
import org.kevoree.annotation.Library;
import org.kevoree.annotation.Port;
import org.kevoree.annotation.PortType;
import org.kevoree.annotation.ProvidedPort;
import org.kevoree.annotation.Provides;
import org.kevoree.annotation.Start;
import org.kevoree.annotation.Stop;
import org.kevoree.annotation.Update;
import org.kevoree.framework.AbstractComponentType;
import javax.swing.JFrame;
import org.kevoree.api.service.core.script.KevScriptEngine;

@Library(name = "RBAC")
@ComponentType()

public class Launcher extends AbstractComponentType {
	
	private RbacTextualEditor editor;
	private KevScriptEngine kse;
	
	@Start
	public void start() {
		kse = getKevScriptEngineFactory().createKevScriptEngine();
	    editor = new RbacTextualEditor(kse);
		editor.setVisible(true);
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
	


	
	
	
}
