package policyTools.guiEditor.commands;

import policyTools.guiEditor.graphicComponents.PolicyTextualEditor;

public class CommandUpdatePDP  extends Command{
	public CommandUpdatePDP(PolicyTextualEditor e, String nme, String desc) {
		super(e, nme, desc);
	}
	@Override
	public void execute() {
		editor.pdp.gui.updateTextArea("update policy");
		editor.pdp.updatePolicy();
	}  
} 