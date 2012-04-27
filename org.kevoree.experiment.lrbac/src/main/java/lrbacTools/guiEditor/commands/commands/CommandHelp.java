package lrbacTools.guiEditor.commands.commands;

import javax.swing.JOptionPane;

import lrbacTools.guiEditor.commands.Command;
import lrbacTools.guiEditor.graphicComponents.RbacTextualEditor;
import lrbacTools.guiEditor.graphicComponents.TextPaneEditor;

public class CommandHelp  extends Command{
	
	public CommandHelp(RbacTextualEditor editor, String id){
		super(editor, id);
	}
	
	@Override
	public void execute() {
		JOptionPane.showMessageDialog(getEditor().textPaneEditor, "This editor aims to manage Rbac policies at runtime", "Help", JOptionPane.INFORMATION_MESSAGE);
	}

}
