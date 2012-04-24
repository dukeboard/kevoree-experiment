package lrbacTools.guiEditor.commands.commandsEditor;

import lrbacTools.guiEditor.commands.CommandRbac;
import lrbacTools.guiEditor.graphicComponents.TextPaneEditor;

public class CommandInitPolicyMonitor extends CommandRbac{
	
private TextPaneEditor textPaneEditor;
	
	public CommandInitPolicyMonitor(TextPaneEditor editor){
		textPaneEditor = editor;
		setName("initPolicymonitor");
	}

	public void execute(){
		textPaneEditor.getPolicyListenerLrbac().startMonitor();
	}
}
