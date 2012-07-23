package lrbacTools.guiEditor.commands.commands;

import lrbacTools.guiEditor.commands.Command;
import lrbacTools.guiEditor.graphicComponents.RbacTextualEditor;

public class CommandGraphMonitorPolicyRule extends Command{

	public CommandGraphMonitorPolicyRule(RbacTextualEditor editor, String id){
		super(editor, id);
	}
	
	@Override
	public void execute() {
		getEditor().graphMonitorPolicyRule.update();
		getEditor().graphMonitorPolicyRule.setVisible(true);
	}
}