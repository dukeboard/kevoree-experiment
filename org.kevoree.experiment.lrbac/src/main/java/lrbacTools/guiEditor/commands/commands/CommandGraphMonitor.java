package lrbacTools.guiEditor.commands.commands;

import lrbacTools.guiEditor.commands.Command;
import lrbacTools.guiEditor.graphicComponents.RbacTextualEditor;
import lrbacTools.guiEditor.graphicComponents.TextPaneEditor;


public class CommandGraphMonitor  extends Command{

	public CommandGraphMonitor(RbacTextualEditor editor, String id){
		super(editor, id);
	}
	
	@Override
	public void execute() {
		getEditor().graphMonitor.update();
		getEditor().graphMonitor.setVisible(true);
	}
}