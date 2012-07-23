package policyTools;

import org.kevoree.annotation.ComponentType;
import org.kevoree.annotation.Library;
import org.kevoree.annotation.Start;
import org.kevoree.annotation.Stop;
import org.kevoree.annotation.Update;
import org.kevoree.framework.AbstractComponentType;

import policyTools.guiEditor.graphicComponents.PolicyTextualEditor;

@Library(name = "XACML")
@ComponentType()
public class Editor extends AbstractComponentType {

	private PolicyTextualEditor editor;

	@Start
	public void start() {
		editor = new PolicyTextualEditor();
		editor.setVisible(true);
		editor.update();
	}

	@Stop
	public void stop() {

	}

	@Update
	public void update() {

	}

}
