package lrbacTools.guiEditor.commands.commands;

import lrbacTools.guiEditor.commands.Command;
import lrbacTools.guiEditor.graphicComponents.RbacTextualEditor;

public class CommandGraphMonitorEnforcedRule extends Command{

	public CommandGraphMonitorEnforcedRule(RbacTextualEditor editor, String id){
		super(editor, id);
	}
	
	@Override
	public void execute() {
		getEditor().graphMonitorEnforcedRule.update();
		getEditor().graphMonitorEnforcedRule.setVisible(true);
	}
}