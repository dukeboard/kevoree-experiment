package lrbacTools.guiEditor.commands.commands;

import lrbacTools.guiEditor.commands.Command;
import lrbacTools.guiEditor.graphicComponents.RbacTextualEditor;

public class CommandCompGraphMonitor extends Command{

	public CommandCompGraphMonitor(RbacTextualEditor editor, String id){
		super(editor, id);
	}
	
	@Override
	public void execute() {
		getEditor().graphMonitorCompPolicyRule.update();
		getEditor().graphMonitorCompPolicyRule.setVisible(true);
	}
}