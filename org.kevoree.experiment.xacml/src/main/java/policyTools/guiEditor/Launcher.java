package policyTools.guiEditor;

import policyTools.KevPDP;
import policyTools.guiEditor.graphicComponents.PolicyTextualEditor;
import policy.*;

public class Launcher{
	private PolicyTextualEditor editor;
	public Launcher(Policy x){
		editor = new PolicyTextualEditor(x);
	}
	public void start(){
		editor.setVisible(true);
	}

} 