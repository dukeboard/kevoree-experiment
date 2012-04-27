package lrbacTools.guiEditor;

import lrbacTools.guiEditor.graphicComponents.RbacTextualEditor;
import org.kevoree.annotation.ComponentType;
import org.kevoree.annotation.Library;
import org.kevoree.annotation.Start;
import org.kevoree.annotation.Stop;
import org.kevoree.annotation.Update;
import org.kevoree.framework.AbstractComponentType;
import javax.swing.JFrame;

@Library(name = "RBAC")
@ComponentType()

public class Launcher extends AbstractComponentType {
	private RbacTextualEditor editor;
	
	@Start
	public void start() {
		System.out.println("hello editor");
	    editor = new RbacTextualEditor(this);
	    System.out.println("editor instantiated");
	    editor.setVisible(true);
		System.out.println("you should be visible :)");
	}

	@Stop
	public void stop() {
		editor.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    	editor.dispose();
    	editor = null;
	}

	@Update
	public void update() {
		editor.setVisible(true);
		System.out.println("you should be visible :)");
	}
		
	
	
	
	
}
