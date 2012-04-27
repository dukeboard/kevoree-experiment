package lrbacTools.guiEditor.commands.commands;

import lrbacTools.guiEditor.commands.Command;
import lrbacTools.guiEditor.graphicComponents.RbacTextualEditor;
import lrbacTools.guiEditor.graphicComponents.TextPaneEditor;

public class CommandInitPolicyMonitor extends Command{
	
	public CommandInitPolicyMonitor(RbacTextualEditor editor, String id){
		super(editor, id);
	}

	public void execute(){
		System.out.println("startMonitor");
		getEditor().kevoreeLauncher.getModelService().registerModelListener(getEditor().kevoreeLauncher);
		getEditor().policyListenerLrbac.startMonitor();
	}
}
