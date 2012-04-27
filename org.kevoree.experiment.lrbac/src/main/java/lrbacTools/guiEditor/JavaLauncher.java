package lrbacTools.guiEditor;

import lrbacTools.guiEditor.graphicComponents.RbacTextualEditor;

public class JavaLauncher {
	private RbacTextualEditor editor;
	
	public JavaLauncher(){
		editor = new RbacTextualEditor();
		editor.setVisible(true);
	}
	
	public static void main(String[] args){
		JavaLauncher l = new JavaLauncher();
		l.toString();
	}
}
